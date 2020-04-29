## Overview
This program is a deep web crawler to go upto provided depth (max limits apply). Deep crawling service is exposed as REST endpoint and have basic HTTP authentication. 
It also uses caching mechanism to improve performance for repeated urls.

## Implementation
The solution delivered here is a Java project implemented as a Spring Boot / Maven project.


## Building the program
In order to build the program, the following is required

- Java 8 JDK
- Maven


## Running the program in local mode
After building the application you can run the service by performing the following steps:

mvn clean install
Right click on WebCrawlerApplication and Run main() method


## Endpoints:
Endpoint to fetch the result:
http://localhost:8080/webCrawler (POST)

Endpoint to fetch the status of the request
http://localhost:8080/webCrawler/status?{url} (GET)
