properties([pipelineTriggers([githubPush()])])

pipeline{
	agent any

	environment {
        PROD_COMMIT="no"
	}

	stages {
		stage('Ask for repo commits') {
			steps {

				sh '''
				export PATH=/bin/bash:$PATH
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)
				echo $release
				if [[ $release = production-* ]] ; then
					echo "new release - $release"
					env.PROD_COMMIT='yes'
					echo $env.PROD_COMMIT
				fi
				
				'''
			}
		}
	
        
	}
}