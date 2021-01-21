properties([pipelineTriggers([githubPush()])])


pipeline { 
    environment { 
        registry = "dpetrocelli/test" 
        registryCredential = 'dpetrocelli' 
        dockerImage = '' 
		PROD_COMMIT="no"
    }
    agent any 
	
    stages { 
		stage('Ask for repo commits (tags)') {
			steps {

				sh '''#!/bin/bash
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				
				if [[ $release = prd-* ]] ; then
					PROD_COMMIT="yes"
				fi
				'''
			}
		}
        stage('Cloning Git') { 
            
			steps { 
				withEnv(["PROD_COMMIT=yes"]) {
						
						echo 'Cloning '
						sh 'rm -rf jenkins-pipeline ; git clone https://github.com/dpetrocelli/jenkins-pipeline'
						echo "Repo has been cloned"
				}
			}
        } 
        
		stage ('Compiling Stage')  {
			steps {
				withEnv(["PROD_COMMIT=yes"]) {
					sh 'cd jenkins-pipeline ; cd java-code ; mvn -B -DskipTests clean package ; cd target ; cp demo-0.0.1-SNAPSHOT.jar ../automation/server.jar'	
					echo ".jar created, and moved to the correct folder "
				}
			}
		}
		
		stage('Building image') { 
            steps { 
                script { 
					sh 'cd jenkins-pipeline ; cd java-code ; cd automation ; docker build . -t dpetrocelli/test2:latest'
                    //dockerImage = docker.build registry + ":$BUILD_NUMBER" 
					echo " image has been built "

					withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]){
          				sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          				sh 'docker push dpetrocelli/test2:latest'
						echo " docker image has been published"
					}
                }
            } 
        }


		stage('Testing.. nothing yet') { 
            steps { 
                script { 
					sh "this is a lie :D "
				}
            } 
        }

		stage('Kubernetes..') { 
            steps { 
                script { 
					withCredentials([kubeconfigFile(credentialsId: 'oktetokubeconf', variable: 'KUBECONFIG')]) {
						//sh 'echo $KUBECONFIG' // environment variable; not pipeline variable
						sh "this is a lie :D "
					}
				}
			}
		}
    }
}