# MS3

This repository reads a `.csv` file and inputs the data into 
a `sqlite` database. If any value is empty in the `.csv`,
then that row is not placed in the database. The output of
this program will be `<input-file>-bad.csv` and `<input-file>.log`.

## How to Run:

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