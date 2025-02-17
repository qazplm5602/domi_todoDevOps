pipeline {
    agent any
    tools {
        jdk 'openJDK-21'
    }
    parameters {
        string(name: 'JWT_KEY', defaultValue: '', description: '테스트 할때 필요한 jwt키')
    }

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'git-domi', url: 'https://github.com/qazplm5602/domi_todoDevOps'
            }
        }

        // Dspring.config.location 인수가 안먹어서 강제적으로 변경
        stage("backend test config create") {
            steps {
                dir('backend/src/main/resources') {
                    sh "mv application.properties application.properties.tmp"
                    sh "echo -e \"spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1\n spring.datasource.username=sa\n spring.datasource.password=sa\n domi.jwt.secret=${params.JWT_KEY}\" > ./application.properties"
                }
            }
        }

        stage('backend test') {
            steps {
                dir('backend') {
                    // 권한 없으면 안됨
                    sh 'chmod +x ./gradlew'
                    sh './gradlew test'
                }
            }
        }

        // 다시 원본으로 돌림
        stage("backend live config apply") {
            steps {
                dir('backend/src/main/resources') {
                    sh "rm application.properties"
                    sh "mv application.properties.tmp application.properties"
                }
            }
        }

        stage('backend build') {
            steps {
                dir('backend') {
                    sh './gradlew build -x test'
                }
            }
        }

        stage('backend server apply') {
            steps {
                dir('backend') {
                    sshPublisher(
                        failOnError: true,
                        publishers: [
                            sshPublisherDesc(
                                configName: 'todo-backend',
                                verbose: true,
                                transfers: [
                                    sshTransfer(
                                        cleanRemote:false,
                                        sourceFiles: './build/libs/',
                                        removePrefix: './build/libs',
                                        remoteDirectory: '/domi',
                                    ),
                                    sshTransfer(
                                        execCommand: 'kill -SIGTERM 1'
                                    )
                                ]
                            )
                        ]
                    )
                }
            }
        }

        stage('frontend server apply') {
            steps {
                dir('frontend') {
                    sshPublisher(
                        failOnError: true,
                        publishers: [
                            sshPublisherDesc(
                                configName: 'todo-frontend',
                                verbose: true,
                                transfers: [
                                    sshTransfer(
                                        cleanRemote:false,
                                        sourceFiles: '.',
                                        removePrefix: '',
                                        remoteDirectory: '/domi',
                                    ),
                                    sshTransfer(
                                        execCommand: 'kill -SIGTERM 1'
                                    )
                                ]
                            )
                        ]
                    )
                }
            }
        }
    }

    post {
        cleanup {
            cleanWs()
        }
    }
}
