properties([pipelineTriggers([githubPush()])])

pipeline{
	agent any
	stages {
		
	
        /* checkout repo */
        stage('Checkout SCM') {
            steps {
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
}