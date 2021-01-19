properties([pipelineTriggers([githubPush()])])

pipeline{
	agent {
        label 'github-ci'
    }

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