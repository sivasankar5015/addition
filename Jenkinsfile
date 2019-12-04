pipeline {
	agent any

	stages {
		stage("Maven-Build"){
			steps {
				sh 'mvn clean package'
			}
		}
		stage ("Docker-Login") {
			steps {
				sh 'docker login -u siva3100 -p Siva@5015'
			}
		}
		stage ("Deleting-Previous-Docker-Builds"){
			steps {
				sh 'docker rmi -f "$(docker images -aq)"'
			}
		}
		stage ("Docker-Build-Services") {
			steps {
				sh 'pwd'
				sh "docker build -t additionapp:0.${env.BUILD_ID} ./"
				sh "docker build -t restfulexample:0.${env.BUILD_ID} ./"
			}
		}
		stage ("Tagging-docker-images") {
			steps {
				sh "docker tag additionapp:0.${env.BUILD_ID} siva3100/additionapp:0.${env.BUILD_ID}"
				sh "docker tag restfulexample:0.${env.BUILD_ID} siva3100/restfulexample:0.${env.BUILD_ID}"
			}
		}
		stage ("Pushing-Images-to-Registry"){
			steps {
				sh "docker push siva3100/additionapp:0.${env.BUILD_ID}"
				sh "docker push siva3100/restfulexample:0.${env.BUILD_ID}"
			}
		}
		stage('DeployToProduction') {
            		steps {
                		kubernetesDeploy(
                    			kubeconfigId: 'kubeconfig',
                    			configs: 'Addition-Restful-Kube.yml'
                		)
            		}
        	}
	}
}
