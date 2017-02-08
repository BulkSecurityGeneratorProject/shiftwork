#!/usr/bin/groovy
@Library('github.com/fabric8io/fabric8-pipeline-library@master')
def failIfNoTests = ""
try {
  failIfNoTests = ITEST_FAIL_IF_NO_TEST
} catch (Throwable e) {
  failIfNoTests = "false"
}

def itestPattern = ""
try {
  itestPattern = ITEST_PATTERN
} catch (Throwable e) {
  itestPattern = "*KT"
}

def versionPrefix = ""
try {
  versionPrefix = VERSION_PREFIX
} catch (Throwable e) {
  versionPrefix = "1.0"
}

def canaryVersion = "${versionPrefix}.${env.BUILD_NUMBER}"
def utils = new io.fabric8.Utils()
def buildLabel = "mylabel.${env.JOB_NAME}.${env.BUILD_NUMBER}".replace('-', '_').replace('/', '_')

podTemplate(label: buildLabel, 
 containers: [containerTemplate(alwaysPullImage: false, args: 'cat', command: '/bin/sh -c', 
         
        image: 'jhipster/jhipster', name: 'maven', privileged: true, resourceLimitCpu: '', resourceLimitMemory: '', resourceRequestCpu: '', resourceRequestMemory: '', ttyEnabled: true)],
,
 serviceAccount: 'jenkins') {
    node(buildLabel) {
        container(name: 'maven') {
  def envProd = 'shiftwork-production'

  checkout scm



    stage 'Canary Release'
    mavenCanaryRelease{
      version = canaryVersion
    }

    stage 'Integration Test'
    mavenIntegrationTest{
      environment = 'Testing'
      failIfNoTests = localFailIfNoTests
      itestPattern = localItestPattern
    }

    stage 'Rolling Upgrade Production'
    def rc = readFile 'target/classes/kubernetes.json'
    kubernetesApply(file: rc, environment: envProd)
   }
  }
}