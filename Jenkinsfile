    pipeline{
        agent any

          environment {

                NEXUS_VERSION = "nexus3"
                NEXUS_PROTOCOL = "http"
                NEXUS_URL = "192.168.33.10:8081"
                NEXUS_CREDENTIAL_ID  ='NEXUS_CRED'
                NEXUS_REPOSITORY = 'maven-central-repository'
                rootPassword = 'vagrant'


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


               stage("Nexus") {
                                      steps {
                                          script {
                                              pom = readMavenPom file: "pom.xml";
                                              filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                                              echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                                              artifactPath = filesByGlob[0].path;
                                              artifactExists = fileExists artifactPath;
                                              if(artifactExists) {
                                                  echo "* File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                                                  nexusArtifactUploader(
                                                      nexusVersion: NEXUS_VERSION,
                                                      protocol: NEXUS_PROTOCOL,
                                                      nexusUrl: NEXUS_URL,
                                                      groupId: pom.groupId,
                                                      version: pom.version,
                                                      repository: NEXUS_REPOSITORY,
                                                      credentialsId: NEXUS_CREDENTIAL_ID,
                                                      artifacts: [
                                                          [artifactId: pom.artifactId,
                                                          classifier: '',
                                                          file: artifactPath,
                                                          type: pom.packaging],
                                                          [artifactId: pom.artifactId,
                                                          classifier: '',
                                                          file: "pom.xml",
                                                          type: "pom"]
                                                      ]
                                                  );
                                              } else {
                                                  error "* File: ${artifactPath}, could not be found";
                                              }
                                          }
                                      }
                                  }
                 stage ("Build docker image") {
                                               steps {

                                                   sh "docker build -t houssemjrad/houssem-docker ."
                                                   sh "docker pull mysql:5.7"

                                               }
                                       }







        }







    }
