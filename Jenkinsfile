pipeline {
    agent any
    tools {
        maven 'Maven'
    }
//        docker {
//            image 'maven:3.8.1-adoptopenjdk-11'
//            alwaysPull false
//        }
    parameters {
        choice(name: 'ENV', choices: ['test', 'qa'], description: 'An environment type.')
        string(name: 'SPRINT', defaultValue: 'VXXX.X', description: 'The Fix Version for the build.')
        string(name: 'BRANCH', defaultValue: 'release', description: 'Which branch to use.')
    }
    environment {
        APP_NAME = "${ENV.toUpperCase()}-application"
    }
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
        stage("Prompt for input") {
            steps {
                echo "SPRINT: $SPRINT"
                echo "BRANCH: $BRANCH"
                echo "ENV: ${ENV.toUpperCase()}"
                echo "App: $APP_NAME"
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn clean test'
                    sh 'mvn clean install'
                }
            }
        }
    }

//    post {
//            always {
//                node('awesome_node_label') {
//                    step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "ld1995@tut.by", sendToIndividuals: false])
//                }
//            }
//        }

    post {
        failure {
            mail(
                    to: "ld1995@tut.by",
                    subject: 'The Pipeline failed',
                    body: "$APP_NAME Pipeline filed for envirement: $ENV <br>Branch Number: $BRANCH <br> Sprint Number: $SPRINT"
            )
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