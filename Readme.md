## Synopsis

At the top of the file there should be a short introduction and/ or overview that explains **what** the project is. This description should match descriptions added for package managers (Gemspec, package.json, etc.)

## Motivation

A short description of the motivation behind the creation and maintenance of the project. This should explain **why** the project exists.

## Installation

You can build the project using the maven wrapper included. In Linux/Mac OS X you should type the following: 

```
$ ./mvnw package
```

Or in Windows:

```
mvnw.cmd package
```

This will create an executable JAR under target/ folder called "manager-{version}-shaded.jar". Once the executable jar was created, to execute the project you should type the following:

```
$ java -jar target/manager-{version}-shaded.jar
```

## Requirements

* Java 8


## Contributors

* [dortegau](http://twitter.com/dortegau)

## License

Read LICENSE.txt attached to the project