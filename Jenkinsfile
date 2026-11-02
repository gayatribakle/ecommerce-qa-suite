pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    parameters {
        choice(name: 'SUITE',
               choices: ['testng.xml', 'testng-parallel.xml', 'testng-crossbrowser.xml'],
               description: 'Which TestNG suite to run')
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=${params.SUITE} -Dheadless=true"
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/**, screenshots/**, logs/**', allowEmptyArchive: true
        }
    }
}
