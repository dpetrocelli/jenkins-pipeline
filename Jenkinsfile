properties([pipelineTriggers([githubPush()])])

pipeline{
	agent {
        label 'github-ci'
    }
	
	stage('Setting the variables values') {
    steps {
         sh '''
            #!/bin/bash
            curl -v "https://api.github.com/repos/libgit2/libgit2/tags" | grep name
         '''
   		}
	}
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