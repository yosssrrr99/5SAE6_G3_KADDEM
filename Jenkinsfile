pipeline{
    agent any

    options{
        buildDiscarder(logRotator( numToKeepStr: '5'))
    }
    stages{
        stage('Scan'){
            steps{
                withSonarQubeEnv(installationName: 'sq1' ){
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.3:sonar '
                }
            }
        }
    }

}
