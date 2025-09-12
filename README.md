# Unique ID of the node
process.roles=broker,controller
node.id=1

# Internal KRaft communication
controller.quorum.voters=1@localhost:9093

# Directories for Kafka log data
log.dirs=E:/softwares/kafka/kafka-logs

# Listeners for clients and controller
listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
inter.broker.listener.name=PLAINTEXT
controller.listener.names=CONTROLLER

# Cluster metadata storage
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1