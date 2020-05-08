podTemplate(containers: [
  containerTemplate(name: 'maven', image: 'maven:3.6.0-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
  containerTemplate(name: 'docker-dind', image: 'docker:19-dind', alwaysPullImage: true, privileged: true, envVars: [ envVar(key: 'DOCKER_TLS_CERTDIR', value: '')],),
  containerTemplate(name: 'docker', image: 'docker:19', alwaysPullImage: true, ttyEnabled: true, command: 'cat',envVars: [envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')],),
  containerTemplate(name: 'helm', image: 'dtzar/helm-kubectl:3.2.0', ttyEnabled: true, command: 'cat', privileged: true),
]) {

  node(POD_LABEL) {
    
    stage('Run tests') {
      git 'https://github.com/techreturners/spring_boot_helm.git'
      container('maven') {
          sh 'mvn test'
      }
    }

    stage('Package') {
      container('maven') {
          sh 'mvn package'
      }
    }

    stage('Docker Build') {

      script {
        def now = new Date()
        DATETIME_TAG = now.format("yy-MM-dd_HHmm", TimeZone.getTimeZone('UTC'))
      } 
      container('docker') {
        echo "Building docker image..."
        docker.build("course-day-service", ".")
      }
    }

    stage('Push to ECR') {
      container('docker') {
        docker.withRegistry("${ECR_PROTOCOL}${ECR_ADDRESS}", "ecr:eu-west-2:aws-access") {
          docker.image("course-day-service").push("${DATETIME_TAG}")
        }
      }
    }

    stage('Kubernetes deploy') {
      container('helm') {
        sh "helm install --set image.repository=${ECR_ADDRESS} --set image.tag=${DATETIME_TAG} course-day-service ./helm/spring-boot"
      }
    }
  }
}