pipeline {
    agent any 

    environment {
        GITHUB_REPO = 'https://github.com/gobinda1990/public-repo.git'
        PROJECT_NAME = 'GST_API_R9C'
        ARTIFACT_NAME = 'GST_API_R9C.jar'
        SONAR_HOME= tool "sonar"
    }

    stages {

        stage('Clone code from GitHub') {
            steps {
               git branch: 'main', url: "${env.GITHUB_REPO}"                
            }
        }

        stage('SonarQube Quality Analysis') {
            steps {
                withSonarQubeEnv("sonar"){
                    sh "$SONAR_HOME/bin/sonar-scanner -Dsonar.projectName=GST_API_R9C -Dsonar.projectKey=GST_API_R9C"
                }
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
