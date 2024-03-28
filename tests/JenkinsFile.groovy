pipeline {
    agent any

    stages {
        stage('Run Ansible Playbook') {
            steps {
                script {
                    ansiblePlaybook(
                            playbook: 'tests/playbook.yml',
                            inventory: 'tests/hosts',
                            colorized: true,
                            extra_vars: [
                                HOSTS: 'localhost'
                            ]
                    )
                }
            }
        }
    }
}