<filter>
    <filter-name>DecryptionFilter</filter-name>
    <filter-class>com.yourpackage.filter.DecryptionFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>DecryptionFilter</filter-name>
    <url-pattern>/api/*</url-pattern> <!-- Adjust as per your secured URL pattern -->
</filter-mapping>