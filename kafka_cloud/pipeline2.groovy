def MICROSERVICE_NAME = "artemis-rest-adapter"
def MICROSERVICE_REPO_NAME = "artemis-rest-adapter"
// ---------------------------------------
def GIT_PROJECT = "sudo"
def GIT_REGISTRY = "sfera.inno.local"
def GIT_PORT = "7999"
def GIT_URL = "ssh://git@git.${GIT_REGISTRY}:${GIT_PORT}/${GIT_PROJECT}/${MICROSERVICE_REPO_NAME}.git"
def GIT_CRED_ID = "ssh_ci_si_sudo"
pipeline {
    agent any
    environment {
        ojdk17 = tool name: 'ojdk-17-linux-x64', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        sonarscaner = tool name: 'sonar-scanner-cli-4.4.0.2170', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        maven382 = tool name: 'maven-3.8.2', type: 'com.cloudbees.jenkins.plugins.customtools.CustomTool'
        DOCKER_CI_AUTH_REGISTRY_URL = "docker.repo-ci.sfera.inno.local"
        DOCKER_CI_REGISTRY_URL = "docker.repo-ci.sfera.inno.local/sudo-docker-lib/"
        DOCKER_DISTR_AUTH_REGISTRY_URL = "docker.repo-distr.sfera.inno.local"
        DOCKER_DISTR_REGISTRY_URL = "docker.repo-distr.sfera.inno.local/sudo-docker"
        // MICROSERVICE_NAME = "artemis-rest-adapter"
        POSTSCRIPT_STAND_URL = "http://dkt-pdou01-pdou-2207-dev.dkt-pdou01.corp.dev.vtb"
    }
    parameters {
        sferaCodeParameter(
                name: 'BRANCH',
                description: '',
                registryUrl: "${GIT_REGISTRY}",
                projectName: "${GIT_PROJECT}",
                // repositoryName: "${params.MICROSERVICE_NAME}",
                repositoryName: "${MICROSERVICE_NAME}",
                choiceType: 'BRANCH', //BRANCH, TAG, BRANCH_OR_TAG, COMMIT
                branchName: '',
                viewChoiceType: 'SINGLE_SELECT', //SINGLE_SELECT, MULTI_SELECT
                isFiltersEnabled: false,
                isQuickFilterEnabled: true,
                filterRegex: '.*',
                sortType: 'ASC', //ASC, DESC, RELEVANCE_DESC,
                defaultValue: ''
        )
    }
    stages {
        stage('Загрузка исходников') {
            steps {
//                git_pull()
                if ( ${BRANCH} == "master" ) {
                    
                }
                git url: "${GIT_URL}",
                        branch: '${BRANCH}',
                        credentialsId: "${GIT_CRED_ID}"
//
//                sh '''
//                git status
//                git log
//                '''
            }
        }
         stage('Сборка артефакта') {
             steps{
                 configFileProvider([configFile(fileId: 'maven-settings-0', variable: 'SETTINGS')]){
                     sh'''
                     #!/bin/bash
                     rm -rf "/app/jenkins-agent/.m2/repository/sudo/*"
                     ${maven382}/bin/mvn clean package -s $SETTINGS -DskipTests
                     ls -la
                     '''
                 }
             }
         }

        stage('Загрузка в Сферу') {
            steps{
                configFileProvider([configFile(fileId: 'maven-settings-1', variable: 'SETTINGS')]){
                    sh'''
                    #!/bin/bash
                    ${maven382}/bin/mvn deploy -s $SETTINGS
                    '''
                }
            }
        }
         stage('Build and Push docker image to nexus') {
             steps {
                 withCredentials([usernamePassword(credentialsId: 'genral-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                     sh "docker login -u ${USERNAME} -p '${PASSWORD}' ${DOCKER_CI_AUTH_REGISTRY_URL}"
                     sh"""
                     #!/bin/bash
                     curl -k --user "${USERNAME}:${PASSWORD}" https://sfera.inno.local/app/repo-ci-maven/api/repository/sudo-maven-lib/ru/vtb/tstr/opentelemetry-javaagent/2.1.0/opentelemetry-javaagent-2.1.0.jar -o opentelemetry-2.1.0.jar
                     curl -k --user "${USERNAME}:${PASSWORD}" https://sfera.inno.local/app/repo-ci-maven/api/repository/sudo-maven-lib/ru/vtb/tstr/tstr-kafka-exporter/1.2.0/tstr-kafka-exporter-1.2.0.jar -o tstr-kafka-exporter-1.2.0.jar
                     docker build . -f Dockerfile -t docker.repo-ci.sfera.inno.local/sudo-docker-lib/${MICROSERVICE_NAME}:0.0.0.${BUILD_NUMBER}
                     docker push docker.repo-ci.sfera.inno.local/sudo-docker-lib/${MICROSERVICE_NAME}:0.0.0.${BUILD_NUMBER}
                     docker image prune
                     docker logout ${DOCKER_CI_AUTH_REGISTRY_URL}
                     """
                 }
             }
         }
         stage('Tag Build Number') {
             steps {
                 tags()
             }
         }
    }

    post {
        always {
            cleanWs()
        }
    }
}