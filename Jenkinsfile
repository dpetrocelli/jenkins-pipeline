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
				echo "at the begginning is: $PROD_COMMIT"
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				echo $release
				if [[ $release = production-* ]] ; then
					echo "new release - $release"
					PROD_COMMIT="yes"
					echo "at the end is: $PROD_COMMIT"
				fi
				
				'''
			}
		}
	
        
	}
}