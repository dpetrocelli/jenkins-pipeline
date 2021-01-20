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
    agent {
        docker { image 'node:14-alpine' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'node --version'
            }
        }
    }
}