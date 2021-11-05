pipeline {
    agent any
    tools {
        maven 'Maven'
    }
//        docker {
//            image 'maven:3.8.1-adoptopenjdk-11'
//            alwaysPull false
//        }
//    git branch: 'master',
//            credentialsId: '12345-1234-4696-af25-123455',
//            url: 'ssh://git@bitbucket.org:company/repo.git' //https://github.com/ld1995/test
    git branch: 'master',
            url: 'https://github.com/ld1995/test.git'
    stages {
//        stage('Checkout') {
//            steps {
//                git branch: 'master', url: 'ssh://git@github.com:ld1995/test.git', credentialsId: ''
//            }
//        }
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
        stage('Test') {
            steps {
                script {
                    sh 'mvn clean test'
                    sh 'mvn clean install'
                }
            }
        }
    }
    post {
        always {
            mail(
                    to: "ld1995@tut.by",
                    subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                    body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
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