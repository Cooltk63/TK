javac -d target/classes -cp "src/main/resources/lib/*;src/main/resources/sso_lib/*" src/main/java/com/crs/SsoLoginService/*.java


java -cp "target/classes;src/main/resources/lib/*;src/main/resources/sso_lib/*" com.crs.SsoLoginService.SsoService


rm -rf target
javac -d target/classes -cp "src/main/resources/lib/*;src/main/resources/sso_lib/*" src/main/java/com/crs/SsoLoginService/*.java

