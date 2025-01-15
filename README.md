<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-deploy-plugin</artifactId>
            <version>3.1.1</version> <!-- Stable version -->
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-site-plugin</artifactId>
            <version>3.11.0</version> <!-- Stable version -->
        </plugin>
    </plugins>
</build>



rm -rf ~/.m2/repository/org/apache/maven/plugins
mvn clean install -U