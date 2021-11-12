pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    parameters {
        choice(
                name: 'ENV',
                choices: ['test', 'qa', 'live', 'copy'],
                description: 'An environment type.'
        )
        string(
                name: 'SPRINT',
                defaultValue: 'VXXX.X',
                description: 'The Fix Version for the build.'
        )
        string(
                name: 'BRANCH',
                defaultValue: 'release',
                description: 'Which branch to use.'
        )
    }
    environment {
        CLOUD_APP = "$ENV-miscellaneous-$BUILD_NUMBER"
    }
    stages {
        stage('Test') {
            steps {
                script {
                    sh 'mvn clean test'
                    sh 'mvn clean install'
                }
            }
        }
        stage('Push on S3') {
            steps {
                sh """
                    cd "$WORKSPACE/"
                    tar czf ${CLOUD_APP}.tgz target/*-0.0.1-SNAPSHOT.jar
                """
            }
        }
        stage('Trigger build') {
            steps {
                build job: 'displayParams', parameters: [
                        string(name: 'BUILD_NUMBER', value: BUILD_NUMBER),
                        string(name: 'ENV', value: ENV),
                        string(name: 'SPRINT', value: SPRINT),
                        string(name: 'BRANCH', value: BRANCH),
                ]
            }
        }
    }
    post {
        always {
            mail(
                    to: "ld1995@tut.by",
                    subject: "Jenkins Build ${currentBuild.currentResult}: Job $JOB_NAME",
                    body: "${currentBuild.currentResult}: Job $JOB_NAME build $BUILD_NUMBER\n More info at: $BUILD_URL"
            )
        }
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