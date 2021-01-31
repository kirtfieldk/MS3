# MS3

This repository reads a `.csv` file and inputs the data into 
a `sqlite` database. If any value is empty in the `.csv`,
then that row is not placed in the database. The output of
this program will be `<input-file>-bad.csv` and `<input-file>.log`. These created files 
will be found in the created `/log` directory. 

## How to Run:
- Need JDK 14
- Need Maven in PATH, so `mvn` cli operates
 
We need to construct a `.jar` file-- run:
```
mvn -f pom.xml clean package install
```
This places the `sqLite-MS3.jar` in the `./target` directory, and places the 
dependency jars in the `./target/libs` folder. To Execute run:
```
java -cp ./target/sqLite-MS3.jar Data <.csv_file_path>
```
## Overview

This is a `Maven` project that relies on the `sqlite-jdbc` and
`commons-csv` libraries. 



```java
public class DataMonster{
    ...
}
```
The DataMonster class is responsible for reading the `.csv`
file and input its entries into the database.

```java
public class FileWriterMonster{
    ...
}
```
The FileWriterMonster is responsible for writting  
the stats and error entries into files.

This application places data into a SQLite database but offers no querying method. To see the 
database, use another application to the values-`DB Browser for SQLite` or use `SELECT * FROM
csvValues`. 
### Assumptions
- The `.csv` file's header will always be `A,B,C,D,E,F,G,H,I,J`
- the file for argument one will always end in `.csv`
- The file can be accessed by full path or if the file is in the current directory
 
### No Dockerfile
No use of Dockerfile because it would be to cumbersome of a task. This would force that the 
`.csv` file must always be in the directory of `MS3` or copied into the container with
`docker cp <.csv_file_path> <container_name>:/Path/to/workdir`. Then to view the
`SQLite` db we would have to copy that dataase onto our local machine.