export MAVEN_HOME=/chj/app/maven
export PATH=$MAVEN_HOME/bin:$PATH
rm -rf target
mvn package
if [ $? -eq 0 ] ; then
rsync -avr target/agent.jar root@192.168.54.15:/chj/app/monitor/lib/
ssh root@192.168.54.15 "cd /chj/app/monitor/bin; sh agent restart"
fi
