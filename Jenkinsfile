    pipeline{
        agent any

          environment {

                NEXUS_VERSION = "nexus3"
                NEXUS_PROTOCOL = "http"
                NEXUS_URL = "192.168.33.10:8081"
                NEXUS_CREDENTIALS = credentials('NEXUS_CRED')
                NEXUS_REPOSITORY = 'maven-central-repository'

            }


        options{
            buildDiscarder(logRotator( numToKeepStr: '5'))
        }
        stages{
                 stage('Checkout') {
                    steps {
                        script {
                            checkout scm
                        }}}
            stage('Scan'){
                steps{
                    withSonarQubeEnv(installationName: 'sq1' ){
                        sh 'mvn clean compile  sonar:sonar '
                    }
                }
            }
             stage(' jar') {
                                   steps {
                                      script {
                                              sh 'mvn install -DskipTests'
                                             }
                                        }
                                  }

             stage("Maven Build") {
                         steps {
                             script {
                                 sh "mvn package -D skipTests=true"
                             }
                         }
                     }

               stage("Clone code from VCS") {
                   steps {
                       checkout([$class: 'GitSCM', branches: [[name: 'HoussemJrad']], userRemoteConfigs: [[url: 'https://github.com/yosssrrr99/5SAE6_G3_KADDEM.git']]])
                   }
               }


                stage('Build and Deploy to Nexus') {
                             steps {
                                 script {
                                     // Read the POM file and perform Maven build
                                     pom = readMavenPom()
                                     // You can access pom properties like groupId, artifactId, version, etc.
                                     echo "Group ID: ${pom.groupId}"
                                     echo "Artifact ID: ${pom.artifactId}"
                                     echo "Version: ${pom.version}"

                                     // Perform Maven build, and deploy to Nexus as needed
                                     sh 'mvn clean deploy'
                                 }
                             }
                         }





        }







    }
