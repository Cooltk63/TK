javac -d out -classpath WebContent/WEB-INF/lib/* src/com/tcs/*.java


for war 

jar -cvf dist/Dashboard.war -C out . -C WebContent .