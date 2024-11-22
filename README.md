openssl s_client -showcerts -connect repo.maven.apache.org:443


keytool -import -trustcacerts -keystore D:\Java22\lib\security\cacerts -storepass changeit -noprompt -alias maven-repo-cert -file <path-to-maven-repo.crt>


<settings>
  <proxies>
    <proxy>
      <id>proxy</id>
      <active>true</active>
      <protocol>https</protocol>
      <host>proxy.example.com</host>
      <port>8080</port>
      <username>your-username</username>
      <password>your-password</password>
      <nonProxyHosts>www.google.com|*.example.com</nonProxyHosts>
    </proxy>
  </proxies>
</settings>


mvn -Dmaven.wagon.http.ssl.insecure=true clean install


<repositories>
  <repository>
    <id>central</id>
    <url>http://repo.maven.apache.org/maven2</url>
  </repository>
</repositories>