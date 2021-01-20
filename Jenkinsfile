properties([pipelineTriggers([githubPush()])])

pipeline{
	agent any

	environment {
        PROD_COMMIT="no"
	}

	stages {
		stage('Ask for repo commits') {
			steps {

				sh '''#!/bin/bash
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				
				if [[ $release = production-* ]] ; then
					PROD_COMMIT="yes"
				fi
				'''
			}
		}
	}

	stages {
		 stage('Checkout SCM') {
			
				steps {
					if (PROD_COMMIT == 'yes') {
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
					else{
						echo 'not changed'
					}
				}
    	}
	
        
	}
}