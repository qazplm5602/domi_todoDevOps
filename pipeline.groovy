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

        stage("backend test config") {
            steps {
                dir('backend') {
                    sh "echo -e \"spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1\n spring.datasource.username=sa\n spring.datasource.password=sa\n domi.jwt.secret=${params.JWT_KEY}\" > ./application-test.properties"
                }
            }
        }

        stage('backend test') {
            steps {
                dir('backend') {
                    // 권한 없으면 안됨
                    sh 'chmod +x ./gradlew'
                    sh './gradlew test -Dspring.config.location=./application-test.properties'
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
