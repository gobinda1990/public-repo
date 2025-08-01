pipeline {
    agent any

    tools {
        jdk 'java17'           // Set up in Jenkins > Global Tool Configuration
        maven 'maven3.9.9'     // Set up Maven similarly
    }

    environment {
        GITHUB_REPO = 'https://github.com/gobinda1990/public-repo.git'
        PROJECT_NAME = 'GST_API_R9C'
        ARTIFACT_NAME = 'GST_API_R9C.jar'
        DEPLOY_DIR = "F:\\GST_JAR\\GST_API_R9C"  // Change to your desired Windows deployment path
    }

    stages {

        stage('Checkout Code') {
            steps {
               git branch: 'main', url: "${env.GITHUB_REPO}"
                // git credentialsId: 'a90189e7-266a-4547-81da-5f01576b55c3', url: "${env.GITHUB_REPO}", branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target\\*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
           // when {
          //      branch 'main'
         //   }
            steps {
                bat """
                if not exist "${DEPLOY_DIR}" mkdir "${DEPLOY_DIR}"
                copy /Y target\\*.jar "${DEPLOY_DIR}\\${ARTIFACT_NAME}"
                """
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build and deployment succeeded.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
