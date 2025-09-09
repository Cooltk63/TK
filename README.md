version: "2.2"

volumes:
  metadata_data: {}
  middle_var: {}
  historical_var: {}
  broker_var: {}
  coordinator_var: {}
  router_var: {}
  druid_shared: {}


services:
  postgres:
    container_name: postgres
    image: bitnami/mysql
    ports:
      - "5432:5432"
    volumes:
      - metadata_data:/var/lib/postgresql/data
    environment:
      - POSTGRES_PASSWORD=FoolishPassword
      - POSTGRES_USER=druid
      - POSTGRES_DB=druid

  # Need 3.5 or later for container nodes
  zookeeper:
    container_name: zookeeper
    image: bitnami/zookeeper
    ports:
      - "2181:2181"
    environment:
      - ZOO_MY_ID=1

  coordinator:
    image: apache/druid:34.0.0
    container_name: coordinator
    volumes:
      - druid_shared:/opt/shared
      - coordinator_var:/opt/druid/var
    depends_on:
      - zookeeper
      - postgres
    ports:
      - "8081:8081"
    command:
      - coordinator
    env_file:
      - environment

  broker:
    image: apache/druid:34.0.0
    container_name: broker
    volumes:
      - broker_var:/opt/druid/var
    depends_on:
      - zookeeper
      - postgres
      - coordinator
    ports:
      - "8082:8082"
    command:
      - broker
    env_file:
      - environment

  historical:
    image: apache/druid:34.0.0
    container_name: historical
    volumes:
      - druid_shared:/opt/shared
      - historical_var:/opt/druid/var
    depends_on: 
      - zookeeper
      - postgres
      - coordinator
    ports:
      - "8083:8083"
    command:
      - historical
    env_file:
      - environment

  middlemanager:
    image: apache/druid:34.0.0
    container_name: middlemanager
    volumes:
      - druid_shared:/opt/shared
      - middle_var:/opt/druid/var
    depends_on: 
      - zookeeper
      - postgres
      - coordinator
    ports:
      - "8091:8091"
      - "8100-8105:8100-8105"
    command:
      - middleManager
    env_file:
      - environment

  router:
    image: apache/druid:34.0.0
    container_name: router
    volumes:
      - router_var:/opt/druid/var
    depends_on:
      - zookeeper
      - postgres
      - coordinator
    ports:
      - "8888:8888"
    command:
      - router
    env_file:
      - environment




I am using the above docker compose file but I have changes the image names of Zookeeper and the mysql but not changed any paths inside the compose file so guide me for running the druid inside docker desktop using the below images and with its respective paths 
1- circleci/postgres:9-bullseye-ram
2- bitnami/zookeeper:latest


Getting the error in zookeeper console logs ::

zookeeper 12:22:04.28 INFO  ==> 

zookeeper 12:22:04.29 INFO  ==> Welcome to the Bitnami zookeeper container

zookeeper 12:22:04.31 INFO  ==> Subscribe to project updates by watching https://github.com/bitnami/containers⁠

zookeeper 12:22:04.33 INFO  ==> Did you know there are enterprise versions of the Bitnami catalog? For enhanced secure software supply chain features, unlimited pulls from Docker, LTS support, or application customization, see Bitnami Premium or Tanzu Application Catalog. See https://www.arrow.com/globalecs/na/vendors/bitnami/⁠ for more information.

zookeeper 12:22:04.34 INFO  ==> 

zookeeper 12:22:04.37 INFO  ==> ** Starting ZooKeeper setup **

zookeeper 12:22:04.43 ERROR ==> The ZOO_ENABLE_AUTH environment variable does not configure authentication. Set the environment variable ALLOW_ANONYMOUS_LOGIN=yes to allow unauthenticated users to connect to ZooKeeper.

2025-09-09T12:26:50,153 ERROR [main-SendThread(zookeeper:2181)] org.apache.zookeeper.client.StaticHostProvider - Unable to resolve address: zookeeper/<unresolved>:2181

java.net.UnknownHostException: zookeeper

	at java.base/java.net.InetAddress$CachedAddresses.get(InetAddress.java:801) ~[?:?]

	at java.base/java.net.InetAddress.getAllByName0(InetAddress.java:1533) ~[?:?]

	at java.base/java.net.InetAddress.getAllByName(InetAddress.java:1385) ~[?:?]

	at java.base/java.net.InetAddress.getAllByName(InetAddress.java:1306) ~[?:?]

	at org.apache.zookeeper.client.StaticHostProvider$1.getAllByName(StaticHostProvider.java:88) ~[zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.client.StaticHostProvider.resolve(StaticHostProvider.java:141) [zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.client.StaticHostProvider.next(StaticHostProvider.java:368) [zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1204) [zookeeper-3.8.4.jar:3.8.4]

2025-09-09T12:26:50,154 WARN [main-SendThread(zookeeper:2181)] org.apache.zookeeper.ClientCnxn - Session 0x0 for server zookeeper/<unresolved>:2181, Closing socket connection. Attempting reconnect except it is a SessionExpiredException.

java.lang.IllegalArgumentException: Unable to canonicalize address zookeeper/<unresolved>:2181 because it's not resolvable

	at org.apache.zookeeper.SaslServerPrincipal.getServerPrincipal(SaslServerPrincipal.java:78) ~[zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.SaslServerPrincipal.getServerPrincipal(SaslServerPrincipal.java:41) ~[zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.ClientCnxn$SendThread.startConnect(ClientCnxn.java:1157) ~[zookeeper-3.8.4.jar:3.8.4]

	at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1207) [zookeeper-3.8.4.jar:3.8.4]


 Guide me how to resolve this error only occuring inside zookeeper latest image from :: bitnami/zookeeper:latest
