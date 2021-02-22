def checkoutGitRepo(url, branch, targetDir) {
    dir(targetDir) {
        checkout([
            $class: 'GitSCM',
            branches: [[name: "*/$branch"]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [[
                credentialsId: 'github_daws_svc_account',
                url: "$url"
            ]]
        ])
    }
}

def toolSh(command) {
    container('java-buildtools') {
        sh command
    }
}

pipeline {
    agent any
    
    environment {
        MFDFOLDER = 'test2_download'
    }
    stages {
        stage('Test ... ') {
            steps {
                echo "Cool test_pipeline_2:)"
            }
        }
    }
}
