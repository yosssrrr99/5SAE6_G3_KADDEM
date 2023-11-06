    pipeline{
        agent any

        options{
            buildDiscarder(logRotator( numToKeepStr: '5'))
        }
        stages{
            stage('Scan'){
                steps{
                    withSonarQubeEnv(installationName: 'sq1' ){
                        sh 'mvn clean compile  sonar:sonar '
                    }
                }
            }
        }

         stages{
                    stage('nexus'){
                        steps{

                                sh 'mvn install deploy '
                            }

                    }
                }

    }
