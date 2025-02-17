pipeline {
    agent any
    tools {
        jdk 'openJDK-21'
    }

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'git-domi', url: 'https://github.com/qazplm5602/domi_todoDevOps'
            }
        }

        stage('backend test') {
            steps {
                dir('backend') {
                    sh './gradlew test'
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
