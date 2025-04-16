pipeline {

    agent any

    parameters {
        choice(
            name: 'STAND',
            description: 'Choose the target environment:',
            choices: [
                        ['inno_cloud_pdou_dev', 'inno_cloud_sudo_dev'],
                        ['inno_cloud_pdou_st',  'inno_cloud_sudo_st'],
                        ['vtb_cloud_pdou_ift',  'vtb_cloud_sudo_ift'],
                        ['vtb_cloud_pdou_lt',   'vtb_cloud_sudo_lt'],
                        ['vtb_cloud_pdou_preprod','vtb_cloud_sudo_preprod']
                    ]
        )
    }

    environment {
        ansible4100 = tool name: 'ansible-4.10.0', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        python3116 = tool name: 'python-3.11.6-linux-x64', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
//        playbook_kafka_repo = "https://git.sfera.test.vtb.ru/PDOU_DEPLOY/kafka_topic.git"
        credentialsId = ''
        switch (STAND) {
            case 'inno_cloud_pdou_dev':
                credentialsId = 'kafka_service_inno_cloud_pdou_dev'
                break
            case 'inno_cloud_sudo_dev':
                credentialsId = 'kafka_service_inno_cloud_sudo_dev'
                break
            case 'inno_cloud_pdou_st':
                credentialsId = 'kafka_service_inno_cloud_pdou_st'
                break
            case 'inno_cloud_sudo_st':
                credentialsId = 'kafka_service_inno_cloud_sudo_st'
                break
            case 'vtb_cloud_pdou_ift':
                credentialsId = 'kafka_service_vtb_cloud_pdou_ift'
                break
            case 'vtb_cloud_sudo_ift':
                credentialsId = 'kafka_service_vtb_cloud_sudo_ift'
                break
            case 'vtb_cloud_pdou_lt':
                credentialsId = 'kafka_service_vtb_cloud_pdou_lt'
                break
            case 'vtb_cloud_sudo_lt':
                credentialsId = 'kafka_service_vtb_cloud_sudo_lt'
                break
            case 'vtb_cloud_pdou_preprod':
                credentialsId = 'kafka_service_vtb_cloud_pdou_preprod'
                break
            case 'vtb_cloud_sudo_preprod':
                credentialsId = 'kafka_service_vtb_cloud_sudo_preprod'
                break
        }
    }
    stages {
        stage('Run Ansible Playbook') {
            // вызываем ansible-playbook
            withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'CR_KAFKA_CLOUD_SA_ID', passwordVariable: 'CR_KAFKA_CLOUD_SA_PASSWORD')]) {
                try {
                    sh '''#!/bin/bash
                    source ${ansible4100}/bin/activate ${ansible4100} && \
                    ansible --version && \
                    ansible-playbook playbook.yml -i hosts.yml \
                    -e KAFKA_STAND=${STAND} \
                    -e CR_KAFKA_CLOUD_SA_ID=\$CR_KAFKA_CLOUD_SA_ID \
                    -e CR_KAFKA_CLOUD_SA_PASSWORD=\$CR_KAFKA_CLOUD_SA_PASSWORD
                '''
                } catch (Exception e) {
                    echo "Playbook execution failed: ${e.getMessage()}"
                    currentBuild.result = 'FAILURE'
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