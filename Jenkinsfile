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
				release=$(curl "https://api.github.com/repos/dpetrocelli/jenkins-pipeline/commits" | grep "message" | head -1 | cut -d':' -f2 | cut -d'"' -f2)'
				if [[ $release = production-* ]] ; then
					echo $release
					env.PROD_COMMIT='yes'
				fi
				'''
			}
		}
	
        
	}
}