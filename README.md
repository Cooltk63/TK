<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
  
  <!-- Local repository location (optional) -->
  <localRepository>/path/to/local/repository</localRepository>

  <!-- Custom repositories -->
  <profiles>
    <profile>
      <id>custom-repo-profile</id>
      <repositories>
        <repository>
          <id>my-repo</id>
          <name>My Repository</name>
          <url>https://10.191.167.23:443/repository/</url>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <!-- Activate the profile by default -->
  <activeProfiles>
    <activeProfile>custom-repo-profile</activeProfile>
  </activeProfiles>

  <!-- Server credentials for authentication -->
  <servers>
    <server>
      <id>my-repo</id>
      <username>your-username</username>
      <password>your-password</password>
    </server>
  </servers>

  <!-- Proxy configuration if required -->
  <proxies>
    <proxy>
      <id>proxy-server</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy-host</host>
      <port>8080</port>
      <username>proxy-username</username>
      <password>proxy-password</password>
    </proxy>
  </proxies>

  <!-- Mirror for central repository -->
  <mirrors>
    <mirror>
      <id>central-mirror</id>
      <mirrorOf>central</mirrorOf>
      <url>https://repo.maven.apache.org/maven2</url>
    </mirror>
  </mirrors>
</settings>