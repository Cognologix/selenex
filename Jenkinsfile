#!groovy
node('jenkinsb-qa-node-16') {
    try {
            stage("Cleanup Workspace"){
                deleteDir()
            }
            stage("Checkout selenex"){
                git branch: 'master', changelog: true, credentialsId: '79c99bb3-fd92-48bf-bf33-ca1f36fd1629', poll: true, url: 'git@github.com:4info/selenex.git'
            }
           stage("Build and Publish selenex to Nexus"){
                env.JAVA_HOME="${tool 'Java_Jenkins_JDK8u112'}"
                env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
                sh 'java -version'
                sh 'mvn clean install deploy -Dactive_profile=dev -DaltDeploymentRepository=Releases::default::http://nexus-01:8081/nexus/content/repositories/releases/'
             }
        }
        catch (err) {
            emailext (
                to: 'avalvekar@4info.com',
                subject: "${env.JOB_NAME} (#${env.BUILD_NUMBER}) failed",
                body: "Build URL: ${env.BUILD_URL}\n\nBuild Result: ${currentBuild.result}\n\nChanges:${currentBuild.changeSets}",
                attachLog: true,
            )
            throw err
        }
}
