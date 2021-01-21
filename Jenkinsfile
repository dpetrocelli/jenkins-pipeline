properties([pipelineTriggers([githubPush()])])

/*pipeline{
	agent any
	
	
	environment {
        PROD_COMMIT="no"
	}

	stages {
		stage('Ask for repo commits') {
			steps {

				sh '''#!/bin/bash
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				
				if [[ $release = prd-* ]] ; then
					PROD_COMMIT="yes"
				fi
				'''
			}
		}
	

		stage('Checkout SCM') {
			
				steps {
					withEnv(["PROD_COMMIT=yes"]) {
					
					echo 'Cloning '
					sh 'rm -rf jenkins-pipeline ; git clone https://github.com/dpetrocelli/jenkins-pipeline'
					}
					
				}
    	}

		stage ('Compile Stage')  {
			steps {
				
					sh 'cd jenkins-pipeline ; cd java-code ; mvn -B -DskipTests clean package'	
				
			}
		}

		stage ('Build Docker Image')  {
			steps {
				
					sh 'cd jenkins-pipeline ; cd java-code ; mvn -B -DskipTests clean package'	
				
			}
		}
	
	}
	
}
*/
pipeline { 
    environment { 
        registry = "dpetrocelli/test" 
        registryCredential = 'dpetrocelli' 
        dockerImage = '' 
		PROD_COMMIT="no"
    }
    agent any 
	
    stages { 
		stage('Ask for repo commits') {
			steps {

				sh '''#!/bin/bash
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				
				if [[ $release = prd-* ]] ; then
					PROD_COMMIT="yes"
				fi
				'''
			}
		}
        stage('Cloning our Git') { 
            /*steps { 
                git 'https://github.com/dpetrocelli/jenkins-pipeline.git' 
            }*/
			steps { 
				withEnv(["PROD_COMMIT=yes"]) {
						
						echo 'Cloning '
						sh 'rm -rf jenkins-pipeline ; git clone https://github.com/dpetrocelli/jenkins-pipeline'
				}
			}
        } 
        
		stage ('Compile Stage')  {
			steps {
				withEnv(["PROD_COMMIT=yes"]) {
					sh 'cd jenkins-pipeline ; cd java-code ; mvn -B -DskipTests clean package cd target ; cp demo-0.0.1-SNAPSHOT.jar ../automation/server2.jar'	
					// cd target ; cp demo-0.0.1-SNAPSHOT.jar ../automation/server2.jar'	
					echo ".jar created, and moved to the correct folder "
				}
			}
		}
		/*
		stage('Building our image') { 
            steps { 
                script { 
					sh ''
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Deploy our image') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        } 
        stage('Cleaning up') { 
            steps { 
                sh "docker rmi $registry:$BUILD_NUMBER" 
            }
        } 
		*/
    }
}