spring.datasource.jndi-name=java:comp/env/jdbc/MyDB

DB_USER="your_db_user"
DB_PASSWORD="your_db_password"
DB_HOST="your_db_host"
DB_NAME="your_database"

<Context>
    <Resource name="jdbc/MyDB"
              auth="Container"
              type="javax.sql.DataSource"
              factory="org.apache.commons.dbcp2.BasicDataSourceFactory"
              username="${DB_USER}"
              password="${DB_PASSWORD}"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://${DB_HOST}:3306/${DB_NAME}"
              maxActive="50"
              maxIdle="10"
              maxWait="-1"/>
</Context>