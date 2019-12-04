  
FROM tomcat
 
ADD ./additionapp/target/*.war /usr/local/tomcat/webapps/

ADD ./restfulexample/target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080


CMD ["catalina.sh","run"]
