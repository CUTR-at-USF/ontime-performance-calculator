# ontime-performance-calculator [![Build Status](https://travis-ci.org/CUTR-at-USF/ontime-performance-calculator.svg?branch=master)](https://travis-ci.org/CUTR-at-USF/ontime-performance-calculator)

An application to calculate on-time performance using GTFS-realtime data that has been archived using the [gtfsrdb](https://github.com/ CUTR-at-USF/gtfsrdb) tool.

For more about what this tool does, please see the chapter "Producing On-time Performance from GTFS-realtime Data" in [this final report](https://www.dropbox.com/s/v05z1w7td1kpqd9/Transit_Service_Reliability_Final_Report.pdf?dl=0).

## Prerequisites

The On-time Performance Calculator is written in Java. Maven is the build management tool for this project. 

Following are the requirements to get the project up and running:

* [JDK 6 or higher](http://www.oracle.com/technetwork/es/java/javase/downloads/index.html)
* [Apache Maven](https://maven.apache.org/)
* *(Optional)* [Git](https://git-scm.com/) to clone the Github repository

Note that you can also use an integrated development environment (IDE) such as [Netbeans](https://netbeans.org/) or [IntelliJ](https://www.jetbrains.com/idea/) to build the project, which usually includes integrated support for Maven and Git.

You'll also need data:

* **GTFS data** - A zip file containing GTFS data from the same time period as the archived GTFS-realtime data.  Check out [Transitland](https://transit.land/) (and in particular the [feed_versions API](http://transit.land/api/v1/feed_versions)), [TransitFeeds.com](http://transitfeeds.com/) or [GTFS Data Exchange *(Deprecated)*](http://www.gtfs-data-exchange.com/) to find archived GTFS data. 
* **Archived GTFS-realtime data** - Data in a relational database (e.g., MS SQL Server) that has been archived using the [gtfsrdb](https://github.com/mattwigway/gtfsrdb) tool (or another tool that uses the same database schema) 

The following instructions are for building the project from the command line using Maven.

## 1. Download the code

The source files are needed in order to build the jar file. You can obtain them by downloading the files directly or by cloning the Git repository (recommended).

  * Download zipped version of the repository

    Download the current snapshot of the project to your local machine using the "Download Zip" link on the project home page. (https://github.com/CUTR-at-USF/ontime-performance-calculator)

  - Clone this repository to your local machine.

    With git installed on the system clone the repository to your local machine.

    `git clone https://github.com/CUTR-at-USF/ontime-performance-calculator.git`

## 2. Build the project

From the command-line:

`mvn install`

## 3. Run the application

The above step will generate an executable file in the `target/` directory with all the dependencies needed to run the application.

Before running the application, create an **info.txt** file in project's `src/main/resources` folder. This file should contain information needed to connect to Microsoft SQL Server database:
  - `info.txt` file should have the following information. Each line contains a tag and it's value separated by `:` 
    * server : name of the server to connect to
    * username : name of the user
    * password : user password
    * database : name of the database to connect to
  
Finally, to run the application execute the command:

`java -jar target/ontime-performance-calculator.jar <path/to/GTFS_file.zip> <arrival_time OR departure_time> [number of records to fetch]`

  - The application takes three arguments    
    1.	Path to static GTFS zip file
    2.	‘arrival_time’ or ‘departure_time’. This argument tells the application whether to calculate schedule_deviation using           arrival_time or departure_time at each stop
    3.	Specify number of records to fetch from database table. This argument is optional. If it’s not provided, we retrieve all records from the table

Note that this project is currently configured to connect to a SQL Server database, but other relational databases are also supported.
