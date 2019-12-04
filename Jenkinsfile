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
				sh "docker build -t Additionapp:0.${env.BUILD_ID} Additionapp/"
				sh "docker build -t RESTfulExample:0.${env.BUILD_ID} RESTfulExample/"
			}
		}
		stage ("Tagging-docker-images") {
			steps {
				sh "docker tag Additionapp:0.${env.BUILD_ID} siva3100/Additionapp:0.${env.BUILD_ID}"
				sh "docker tag RESTfulExample:0.${env.BUILD_ID} siva3100/RESTfulExample:0.${env.BUILD_ID}"
			}
		}
		stage ("Pushing-Images-to-Registry"){
			steps {
				sh "docker push siva3100/Additionapp:0.${env.BUILD_ID}"
				sh "docker push siva3100/RESTfulExample:0.${env.BUILD_ID}"
			}
		}
		stage('DeployToProduction') {
            		steps {
                		kubernetesDeploy(
                    			kubeconfigId: 'kubeconfig',
                    			configs: 'flight-booking-kube.yml'
                		)
            		}
        	}
	}
}