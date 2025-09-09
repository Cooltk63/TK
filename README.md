After this chnages my historical service get stopped with this logs

2025-09-09T12:48:04,055 INFO [main] org.apache.druid.java.util.common.lifecycle.Lifecycle$AnnotationBasedHandler - Starting lifecycle [SegmentBootstrapper#start]

2025-09-09T12:48:04,055 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper - Starting...

2025-09-09T12:48:04,083 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper - Fetching bootstrap segments from the coordinator with BroadcastDatasourceLoadingSpec mode[ALL].

2025-09-09T12:48:04,618 INFO [NodeRoleWatcher[COORDINATOR]] org.apache.druid.discovery.BaseNodeRoleWatcher - Node watcher of role [coordinator] is now initialized with 0 nodes.

2025-09-09T12:48:04,725 INFO [ServiceClientFactory-0] org.apache.druid.rpc.ServiceClientImpl - Service [coordinator] not available on attempt #1; retrying in 100 ms.

2025-09-09T12:48:04,851 INFO [ServiceClientFactory-1] org.apache.druid.rpc.ServiceClientImpl - Service [coordinator] not available on attempt #2; retrying in 200 ms.

2025-09-09T12:48:05,058 INFO [ServiceClientFactory-2] org.apache.druid.rpc.ServiceClientImpl - Service [coordinator] not available on attempt #3; retrying in 400 ms.

2025-09-09T12:48:05,471 INFO [ServiceClientFactory-2] org.apache.druid.rpc.ServiceClientImpl - Service [coordinator] not available on attempt #4; retrying in 800 ms.

2025-09-09T12:48:06,273 INFO [ServiceClientFactory-2] org.apache.druid.rpc.ServiceClientImpl - Service [coordinator] not available on attempt #5; retrying in 1,600 ms.

2025-09-09T12:48:07,899 WARN [main] org.apache.druid.server.coordination.SegmentBootstrapper - Error fetching bootstrap segments from the coordinator: [org.apache.druid.rpc.ServiceNotAvailableException: Service [coordinator] is not available]. 

2025-09-09T12:48:07,907 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper - Fetched [0] bootstrap segments in [3811]ms.

2025-09-09T12:48:07,946 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper$BackgroundSegmentAnnouncer - Skipping background segment announcing as announceIntervalMillis is [0].

2025-09-09T12:48:08,164 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper$BackgroundSegmentAnnouncer - Completed background segment announcing

2025-09-09T12:48:08,166 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper - Loaded [0] segments on startup in [257]ms.

2025-09-09T12:48:08,296 INFO [main] org.apache.druid.server.coordination.CuratorDataSegmentServerAnnouncer - Announcing self[DruidServerMetadata{name='172.19.0.7:8083', hostAndPort='172.19.0.7:8083', hostAndTlsPort='null', maxSize=300000000000, tier='_default_tier', type=historical, priority=0}] at [/druid/announcements/172.19.0.7:8083]

2025-09-09T12:48:08,410 INFO [main] org.apache.druid.server.coordination.SegmentBootstrapper - Started.

2025-09-09T12:48:08,416 INFO [main] org.apache.druid.java.util.common.lifecycle.Lifecycle$AnnotationBasedHandler - Starting lifecycle [LookupReferencesManager#start]

2025-09-09T12:48:08,423 INFO [main] org.apache.druid.query.lookup.LookupReferencesManager - Loading lookups using spec[LookupLoadingSpec{mode=ALL, lookupsToLoad=null}].

2025-09-09T12:48:08,449 INFO [main] org.apache.druid.java.util.common.lifecycle.Lifecycle - Starting lifecycle [module] stage [SERVER]

2025-09-09T12:48:08,592 INFO [main] org.eclipse.jetty.server.Server - jetty-9.4.57.v20241219; built: 2025-01-08T21:24:30.412Z; git: df524e6b29271c2e09ba9aea83c18dc9db464a31; jvm 17.0.15+6-Debian-1deb12u1

2025-09-09T12:48:09,121 INFO [main] org.eclipse.jetty.server.session - DefaultSessionIdManager workerName=node0

2025-09-09T12:48:09,122 INFO [main] org.eclipse.jetty.server.session - No SessionScavenger set, using defaults

2025-09-09T12:48:09,128 INFO [main] org.eclipse.jetty.server.session - node0 Scavenging every 600000ms

2025-09-09T12:48:10,154 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider as a provider class

2025-09-09T12:48:10,155 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering com.fasterxml.jackson.jaxrs.smile.JacksonSmileProvider as a provider class

2025-09-09T12:48:10,173 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.initialization.jetty.CustomExceptionMapper as a provider class

2025-09-09T12:48:10,174 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.initialization.jetty.ForbiddenExceptionMapper as a provider class

2025-09-09T12:48:10,174 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.initialization.jetty.BadRequestExceptionMapper as a provider class

2025-09-09T12:48:10,174 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.initialization.jetty.ServiceUnavailableExceptionMapper as a provider class

2025-09-09T12:48:10,174 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.initialization.jetty.HttpExceptionMapper as a provider class

2025-09-09T12:48:10,175 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Registering org.apache.druid.server.StatusResource as a root resource class

2025-09-09T12:48:10,210 INFO [main] com.sun.jersey.server.impl.application.WebApplicationImpl - Initiating Jersey application, version 'Jersey: 1.19.4 05/24/2017 03:20 PM'

2025-09-09T12:48:21,199 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding org.apache.druid.server.initialization.jetty.CustomExceptionMapper to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:21,218 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding org.apache.druid.server.initialization.jetty.ForbiddenExceptionMapper to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:21,226 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding org.apache.druid.server.initialization.jetty.BadRequestExceptionMapper to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:21,229 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding org.apache.druid.server.initialization.jetty.ServiceUnavailableExceptionMapper to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:21,232 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding org.apache.druid.server.initialization.jetty.HttpExceptionMapper to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:21,238 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider to GuiceManagedComponentProvider with the scope "Singleton"

2025-09-09T12:48:27,386 INFO [main] com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory - Binding com.fasterxml.jackson.jaxrs.smile.JacksonSmileProvider to GuiceManagedComponentProvider with the scope "Singleton"


