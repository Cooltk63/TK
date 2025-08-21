I am completely confused by all the mess you show just keep the everything in Properties files for now like jwt secret key and everything lets just focus on one thing i have already running the 4 containers
Radis Running Port: 6379 , Product service Running Port:8081,  Fincore Service Running Port: 8089, Api-gateway Running Port: 8080
Just tell me I am doing right or wrong for testing I am using the postman and I have written the service to service call using the service names like product service calling the fincore Services via name (dns name) and it also works in local my dev environment

How I test this jwt and radis for api Gateway with product service and Fincoreservice call must go through the api Gateway and with proper jwt and jti radis authentication process I have followed don't know right or work or wrong
I have to use auth/login to build new jwt and radis
token then use this jwt token to access the product service and fincoreservice Gateway

