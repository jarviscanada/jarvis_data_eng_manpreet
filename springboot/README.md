# Spring Boot Trading REST API
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Architecture](#Architecture)
* [REST API Usage](#RestAPI-Usage)
* [Docker Deployment](#Docker-Deployment)
* [Improvements](#Improvements)

# Introduction
 This is an Online simulation trading REST API Java application built on MVC architecture using microservices and  Springboot for dependency management. This application can help trader to buy and sell stock. It pulls the market information from IEX cloud, a third party, and stores the trader's and quote information in PostgreSQL database. This application can be accessed by various users by consuming REST API through web browser via swagger or by other open source applcations al well like postman.
 
# Quick Start
 ####  Prequiresites
  This application has the following prequiresites:
 *  `CentOS` machine or VM
 *  `Docker`
 *  Minimun `Java 8` version
 *  `Maven`
 *  `IEX Cloud` account for API token
 *  `Swagger` or `postman`
 #### Docker scritps
We need to set up few environement variables for our application to connect to with PostgreSQL

```
PSQL_URL
PSQL_USER
PSQL_PASSWORD
```
This application has 2 containers: one psql and another the application container. To make this application run, it is necessary for both of these containers to communicate. So, created a network within which both communicate. To assemble the images of psql, base image from dockerhub is used and other scripts that  initialise the database. Similarly, to assemble the image of application, used base image of jdk and maven and run my own scripts to package up the application.
```
#verify docker network
    docker network ls
    
    #start a docker container
    #attached the container to the trading-net network
    docker run --name trading-psql-dev \
    -e POSTGRES_PASSWORD=password \
    -e POSTGRES_DB=jrvstrading \
    -e POSTGRES_USER=postgres \
    --network trading-net \
    -d -p 5432:5432 trading-psl
    
    #verify
    docker ps
    
    #set IEX credential
    IEX_PUB_TOKEN="your_token"
    #start trading-app container which is attached to the trading-net docker network
    docker run --name trading-app-dev \
    -e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading" \
    -e "PSQL_USER=postgres" \
    -e "PSQL_PASSWORD=password" \
    -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \
    --network trading-net \
    -p 5000:5000 -t trading-app
    
    #list running containers
    # two running docker containers after ruunning below command
    docker container ls
    
    #test you app using swagger UI
```
#### Testing Application with Swagger 
Swagger is an open-source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful web services. While most users identify Swagger by the Swagger UI tool, the Swagger toolset includes support for automated documentation, code generation, and test-case generation. 
To run or consume the application, navigate through `http://localhost:8080/swagger-ui.html#/`

![Swagger application](/springboot/assets/swagger.PNG)

# Architecture
![Architecture](/springboot/assets/architecture.PNG)

* **Client Tier**: User Interactive layer which consumes REST API through http requests which is in turn handled by Tomcat server. After the information is processed, web server will send http responses to the client. 
* **Application tier**: This tier consist 3 main  layers: controller layer, service layer, and data access layer where the application is developed. This helps in decoupling our application and it is easier to make changes.  This tier maps the http request to corresponding controller method and generate http responses. Following is the explaination of all the tier.
		
    * **Springboot**:It is an open-source framework that consists of Spring MVC features (IOC), auto-configurations and embedded tomcat server. It is best ready to use the micro services platform.
	 	
    * **Controller Layer**: It receives the HTTP request and servlet will map the request to the method in the controller, and the controller is responsible for invoking the corresponding method in the service layer. This application has 4 controllers.
    
    * **Service Layer**: This layer implements the business logic provided. Each implemented method will be invoked by its corresponding controller. However, it will only invoke the DAO layer only if there is no error.
    
    * **Data Access Layer**: This layer will interact with the database tire and its responsibility is to insert, find, update or delete a row in the database. Moreover, it will also be able to pull market data from the IEX cloud. The DAO (data access object) is designed based on a repository pattern. Each DAO is responsible for one table in the database and uses the model created for its corresponding table.
* **Database Tier**:  This tier is responsible for data is storage. In this application, PostgreSQL database is used store data generated by the application and the IEX cloud is used as the source of market data. For IEX cloud, this application consumes the IEX cloud REST API and store the parsed data into the PSQL database.
* **IEX Cloud**: IEX Cloud is a third party that provides financial data for developers. It gives market data information which is consumed by this application and inturn stores the trader and quote information  into PSQL database.
# RestAPI Usage
#### Swagger
Swagger is an open-source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful web services. While most users identify Swagger by the Swagger UI tool, the Swagger toolset includes support for automated documentation, code generation, and test-case generation. 
To run or consume the application, navigate through `http://localhost:8080/swagger-ui.html#/`

#### Quote Controller
Endpointsof this application are as follows
* [**POST**] `/quote/tickerID/{tickerID}`
This endpoint will pull market data from the IEX cloud with the given ticker and save it to the database. It returns saved quote.
* [**PUT**] `/quote/`
This will pull the most recent market data from the IEX cloud for every quote that exists in the database. It will only update the quotes that are currently in the database.
* [**GET**] `/quote/iex/tickerID/{tickerId}`
This will get  market data from the IEX cloud with the specified ticker. It will return the market data directly.
* [**GET**] `/quote/dailyList`
Returns all the quotes currently stored in the database.
* [**PUT**] `/quote/iexMarketData`
This will update the market data information and stores in the database.
#### Trader Account Controller
* [**POST**] `/trader/`
This  will use the trader information in the HTTP request and create a trader with an associated account. It will return the trader's profile and account.
* [**POST**] `trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`
Here, the information of the trader is included in the endpoint URL instead of the HTTP request header.
* [**PUT**] `/trader/withdraw/traderId/{traderId}/amoount/{amount}`
This will withdraw the specified amount of fun from the given trader's account.
* [**PUT**] `/trader/deposit/traderId/{traderId}/amoount/{amount}`
This endpoint will deposit the specified amount of funds into a given trader's account and it will return the new account information.
* [**DELETE**] `/trader/traderId/{traderID}`
This endpoint will delete a trader and the account associated with that trader. 
#### Order Controller
* [**POST**] `/order/marketOrder`
It submits market order. It will determine whether to buy or sell the security, based on the value given for the position of security. In case of buying the security, a bad HTTP status code will be return if there is an insufficient fund to buy the security. In case of selling the security, a bad HTTP status code will be return again if there is insuffient fund to sell which is asked by the another trader. Otherwise, if the execution of the market order succeeds, it will return the executed security order.
#### Dashboard Controller
* [**GET**] `/dashboard/profile/traderId/{traderId}`
It returs the trader information along with the associated account information for the given trader ID.
* [**GET**] `/dashboard/portfolio/traderId/{traderId}`
It returs all the securities owned by the given trader.

# Docker Deployment
![Docker](/springboot/assets/Capture.PNG)
Docker is used to manage application deployment. The above diagram shows the working of docker deployment at the backend. This application has 2 containers: one psql and another the application container. To make this application run, it is necessary for both of these containers to communicate. So, created a network within which both communicate. To assemble the images of psql, base image from dockerhub is used and other scripts that  initialise the database. Similarly, to assemble the image of application, used base image of jdk and maven and run my own scripts to package up the application.

# Improvements
1. This application doesn't have its own UI as an open source application, Swagger provides minimal User Interface. Hence, a UI can be designed and developed to make this app more user friendly.
2. User authentication can be used to ensure security order initiated is with the consent of the trader.
3. Cron jobs can be used to update the quote information in the PostgresDB on timely basis.
4. Timestamps can be applied on security order which in turn can help our application to have some new features that displays the trends of buying/selling stocks.
5. Summarised view can be developed related to multiple accounts of single trader.

