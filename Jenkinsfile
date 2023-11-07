    pipeline{
        agent any

          environment {
                NEXUS_CREDENTIALS = credentials('NEXUS_CRED')
                NEXUS_REPOSITORY = 'maven-central-repository'

            }


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
              stage('Build') {
                        steps {
                            sh 'mvn clean package'
                        }
                    }

             stage('Deploy to Nexus') {
                        steps {
                            script {
                                def mavenCmd = tool name: 'Maven', type: 'maven'
                                sh " mvn deploy -s settings.xml -D maven.repo.local=${WORKSPACE}/.m2/repository -DaltDeploymentRepository=nexus::default::http://192.168.33.10:8081/repository/${NEXUS_REPOSITORY}/ -D skipTests"
                            }
                        }
                    }




        }







    }
