# Ontime-Performance-Calculator
An application to calculate on-time performance using GTFS-realtime data
## Prerequisites

The Ontime-Performance-Calculator is built using Java technologies. Maven is the build management tool for this project. 

Following are the requirements to get the project up and running.

* Access to a terminal
* JDK installed on the system
* Maven installed on the system
* (optional) git installed on the system to clone the repository

## 1. Download the code

The source files would be needed in order to build the jar file. You can obtain them by downloading the files directly or by cloning the git repository (recommended).

  - Download zipped version of the repository

Download the current snapshot of the project to your local machine using the "Download Zip" link on the project home page. (https://github.com/CUTR-at-USF/ontime-performance-calculator)

  - Clone this repository to your local machine.

With git installed on the system clone the repository to your local machine.

git clone https://github.com/CUTR-at-USF/ontime-performance-calculator.git

## 2. Build the project

Using maven the project should be built. This process would create an executable jar.

With maven installed on the system package the project to build the executable.

`mvn install`

## 3. Run the application

The second step would generate an executable file in the `target/` directory with all the dependencies needed to run the application.

Before actually running the application, create an **info.txt** file in project's `target/` folder. This file should contain information needed to connect to remote microsoft SQL server
  - `info.txt` file should definitely have the following information. Each line basically contains a tag and it's value separated by `:` 
    * server : name of the server to connect to
    * username : name of the user
    * password : user password
    * database : name of the database to connect to
  
Finally, to run the application execute the command

`java -jar target/ontime-performance-calculator.jar path/to/GTFS_file.zip`
