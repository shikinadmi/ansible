pipeline {

    agent any
    environment {
        ansible4100 = tool name: 'ansible-4.10.0', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        python3116 = tool name: 'python-3.11.6-linux-x64', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        playbook_kafka_repo = "https://git.sfera.test.vtb.ru/PDOU_DEPLOY/kafka_topic.git"
    }

    stages {
        stage('Git pull helm-chart project') {
            steps {
                git url: "${playbook_kafka_repo}",
                        credentialsId: 'general-creds',
                        branch: "${startDeployment_VERSION}"
            }
        }
//        stage('Ansible-4.10.0 Help') {
//            steps {
//                sh '''#!/bin/bash
//                    source ${ansible4100}/bin/activate ${ansible4100}
//                    ansible --help
//                    ansible-playbook --help
//                   '''
//            }
//        }
        stage('Run Ansible Playbook') {
            steps {
                // CR_KAFKA_CLOUD_SA_ID
                // CR_KAFKA_CLOUD_SA_KEY
                withCredentials([usernamePassword(credentialsId: 'kafka_service', usernameVariable: 'CR_KAFKA_CLOUD_SA_ID', passwordVariable: 'CR_KAFKA_CLOUD_SA_KEY')]) {
                    script {
                        try {
                            sh '''#!/bin/bash
                            source ${ansible4100}/bin/activate ${ansible4100} && \
                            ansible --version && \
                            ansible-playbook playbook.yml -i hosts.yml -vvv \
                            -e KAFKA_STAND="dev" \
                            -e CR_KAFKA_CLOUD_SA_ID="$CR_KAFKA_CLOUD_SA_ID" \
                            -e CR_KAFKA_CLOUD_SA_KEY="$CR_KAFKA_CLOUD_SA_KEY"
                            '''
//                            ansiblePlaybook(
//                                    playbook: 'playbook.yml',
//                                    inventory: 'inventories/dev/hosts.yml',
//                                    varsFile: 'inventories/dev/kafka/main.yml',
//                                    credentialsId: 'kafka-service'
//                            )
                        } catch (Exception e) {
                            echo "Playbook execution failed: ${e.getMessage()}"
                            currentBuild.result = 'FAILURE'
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}