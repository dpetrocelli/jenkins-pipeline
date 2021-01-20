properties([pipelineTriggers([githubPush()])])

pipeline{
	agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
	
	
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
					
					echo 'I only execute on the master branch'
					checkout([
					$class: 'GitSCM',
					branches: [[name: 'master']],
					userRemoteConfigs: [[
						url: 'https://github.com/dpetrocelli/jenkins-pipeline',
						credentialsId: '',
					]]
					])
					}
					
				}
    	}

		stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
	
	}
	
}