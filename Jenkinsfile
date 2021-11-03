pipeline {
    agent any
    tools {
        maven 'Maven'
    }
//        docker {
//            image 'maven:3.8.1-adoptopenjdk-11'
//            alwaysPull false
//        }
    stages {
        //pipeline {
        //    agent any
        //    stages {
        //        stage('Setup parameters') {
        //            steps {
        //                script {
        //                    properties([
        //                        parameters([
        //                            choice(
        //                                choices: ['ONE', 'TWO'],
        //                                name: 'PARAMETER_01'
        //                            ),
        //                            booleanParam(
        //                                defaultValue: true,
        //                                description: '',
        //                                name: 'BOOLEAN'
        //                            ),
        //                            text(
        //                                defaultValue: '''
        //                                this is a multi-line
        //                                string parameter example
        //                                ''',
        //                                 name: 'MULTI-LINE-STRING'
        //                            ),
        //                            string(
        //                                defaultValue: 'scriptcrunch',
        //                                name: 'STRING-PARAMETER',
        //                                trim: true
        //                            )
        //                        ])
        //                    ])
        //                }
        //            }
        //        }
        //    }
        //}
        stage ("Prompt for input") {
            steps {
                script {
                    env.USERNAME = input message: 'Please enter the username',
                            parameters: [string(defaultValue: '',
                                    description: '',
                                    name: 'Username')]
                    env.PASSWORD = input message: 'Please enter the password',
                            parameters: [password(defaultValue: '',
                                    description: '',
                                    name: 'Password')]
                }
                echo "Username: ${env.USERNAME}"
                echo "Password: ${env.PASSWORD}"
            }
        }
        stage('Test') {
            steps {
                script {

//                    properties([
//                            parameters([
//                                    choice(
//                                            choices: ['ONE', 'TWO'],
//                                            name: 'PARAMETER_01'
//                                    ),
//                                    booleanParam(
//                                            defaultValue: true,
//                                            description: '',
//                                            name: 'BOOLEAN'
//                                    ),
//                                    text(
//                                            defaultValue: '''
//                                                    this is a multi-line
//                                                    string parameter example
//                                                    ''',
//                                            name: 'MULTI-LINE-STRING'
//                                    ),
//                                    string(
//                                            defaultValue: 'scriptcrunch',
//                                            name: 'STRING-PARAMETER',
//                                            trim: true
//                                    )
//                            ])
//                    ])
                    sh 'mvn clean test'
                    sh 'mvn clean install'
                }
            }
//        stage('Docker Build') {
//            //dockerfile {
//            //        filename 'Dockerfile.build'
//            //        dir 'build'
//            //        label 'my-defined-label'
//            //        additionalBuildArgs  '--build-arg version=1.0.2'
//            //        args '-v /tmp:/tmp'
//            //    }
//            steps {
//                script {
//                    def dockerImage = docker.build("ld1995/test:latest", "-t .")
//                    docker.withRegistry('', dockerHub) {
//                        dockerImage.push("ld1995/test")
//                        dockerImage.push('latest')
//                    }
//                }
//            }
////            agent any
////            steps {
////                sh 'docker build -t ld1995/test:latest .'
////            }
//        }
//        stage('Docker Push') {
//            agent any
//            steps {
//                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
//                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
//                    sh 'docker push ld1995/test:latest'
//                }
//            }
//        }
        }
    }
}