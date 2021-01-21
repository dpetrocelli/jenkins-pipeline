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
2
    environment { 
3
        registry = "dpetrocelli/test" 
4
        registryCredential = 'dpetrocelli' 
5
        dockerImage = '' 
6
    }
7
    agent any 
8
    stages { 
9
        stage('Cloning our Git') { 
10
            steps { 
11
                git 'https://github.com/dpetrocelli/jenkins-pipeline.git' 
12
            }
13
        } 
14
        stage('Building our image') { 
15
            steps { 
16
                script { 
17
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
18
                }
19
            } 
20
        }
21
        stage('Deploy our image') { 
22
            steps { 
23
                script { 
24
                    docker.withRegistry( '', registryCredential ) { 
25
                        dockerImage.push() 
26
                    }
27
                } 
28
            }
29
        } 
30
        stage('Cleaning up') { 
31
            steps { 
32
                sh "docker rmi $registry:$BUILD_NUMBER" 
33
            }
34
        } 
35
    }
36
}