docker run -d --network my-app-network  --name product-service -p 8081:8081 product-service

docker run -d --network my-app-network  --name fincore-service -p 8089:8089 fincore-service

I am using this both commands for running the docker containers uisng my images 

so I have to do the things manually to 
1. Run mvn clean package --command for Jar creation
2. create image for application using the docker file 
3.create network for containers
4. run the commands manually on local cmd to run the containers 

Can i get the one stop solution for this like evrthing will be dynamic no need to so the mnaully work

Like in docker file or command or naything helpful which reduce this mnaual work.
