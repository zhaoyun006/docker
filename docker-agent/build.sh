#!/bin/bash
git pull
export MAVEN_HOME=/chj/app/maven
export PATH=$MAVEN_HOME/bin:$PATH
mvn package
if [ $? -gt 0 ] ; then 
exit
fi
rm -rf /chj/app/tomcat_8080/webapps/ROOT
unzip /chj/app/workflow/target/Spring-activiti.war -d /chj/app/tomcat_8080/webapps/ROOT
ps aux |grep tomcat_8080|grep -v grep |awk '{print "kill -9 "$2}'|bash
cd /chj/app/tomcat_8080/bin
sh startup.sh
