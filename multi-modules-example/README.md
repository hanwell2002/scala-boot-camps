# generate a sbt project

sbt new scala/scala-seed.g8


#check Ubuntu firewall status
sudo ufw status verbose

# 1. Start the ZooKeeper service
   bin/zookeeper-server-start.sh config/zookeeper.properties
# 2. Start the Kafka broker service
    #open another terminal
   bin/kafka-server-start.sh config/server.properties

# 3. run producer: 
sbt "project producer" run 

# 4. run consumer
sbt "project consumer" run

# 5. run Main
sbt "runMain com.newhopebootcamps.application.Main Consumer"

sbt "runMain com.newhopebootcamps.application.Main Producer"

sbt "runMain com.newhopebootcamps.task.Log4jUsage"


## generic command line template
sbt "project name" "run arg1 arg2"

sbt Producer/run arg1 arg2

# start Producer
java -jar multi-module-assembly-fatjar-1.0.jar com.newhopebootcamps.application.Main Producer

# start Consumer
java -jar multi-module-assembly-fatjar-1.0.jar com.newhopebootcamps.application.Main Consumer

http://www.baremetalsoft.com/baretail/