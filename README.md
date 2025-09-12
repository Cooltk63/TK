9298bcdf7827:/$ /opt/kafka/bin/kafka-storage.sh format \
  --config /opt/kafka/config/kraft/server.properties \
  --cluster-id CuI2IUPsR1uaQ6cstDVP9g
Exception in thread "main" java.nio.file.NoSuchFileException: /opt/kafka/config/kraft/server.properties
        at java.base/sun.nio.fs.UnixException.translateToIOException(Unknown Source)
        at java.base/sun.nio.fs.UnixException.rethrowAsIOException(Unknown Source)
        at java.base/sun.nio.fs.UnixException.rethrowAsIOException(Unknown Source)
        at java.base/sun.nio.fs.UnixFileSystemProvider.newByteChannel(Unknown Source)
        at java.base/java.nio.file.Files.newByteChannel(Unknown Source)
        at java.base/java.nio.file.Files.newByteChannel(Unknown Source)
        at java.base/java.nio.file.spi.FileSystemProvider.newInputStream(Unknown Source)
        at java.base/java.nio.file.Files.newInputStream(Unknown Source)
        at org.apache.kafka.common.utils.Utils.loadProps(Utils.java:655)
        at kafka.tools.StorageTool$.$anonfun$execute$1(StorageTool.scala:79)
        at scala.Option.flatMap(Option.scala:283)
        at kafka.tools.StorageTool$.execute(StorageTool.scala:79)
        at kafka.tools.StorageTool$.main(StorageTool.scala:46)
        at kafka.tools.StorageTool.main(StorageTool.scala)
