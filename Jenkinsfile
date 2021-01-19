properties([pipelineTriggers([githubPush()])])

pipeline{
	agent {
        label 'github-ci'
    }
 '''
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
'''
	stages {
        stage('Example') {
            steps {
                script {
                    dir('git-source-code') {
                          git(
                            url: "https://github.com/dpetrocelli/jenkins-pipeline",
                            credentialsId: '',
                            branch: "master"
                          )      
                          def tagList = sh(returnStdout: true, script: "git for-each-ref --sort=-taggerdate --format '%(tag)' refs/tags").split()
                          tagList.each { nxtTag ->
                              echo nxtTag
                          }
                    }
                }
            }
        }
    }
}