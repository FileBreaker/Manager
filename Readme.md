<img src="logo.png" width="120">

# FileBreaker Manager App

## Synopsis/Motivation

Root canal treatment prognosis, and therefore the survival of the treated tooth, depends on the asepsis achieved within the root canal system prior to root canal obturation. The separation of the endodontic instruments inside the root canal system prevents the disinfection of the same; predisposing the formation of endodontic infections secondary to root canal treatment if the separate fragment is not removed.

The root canal instrumentation characterized by an accentuated curvature, subjects the rotary instruments to stress-compression cycles at the point of maximum curvature; which can condition its fracture within the root canal. 

The design of this prototype is intended to simulate the operator's movements during the treatment of root canal, in order to subject the instruments to all kinds of conditions to study their behavior. A better knowledge of the behavior of endodontic instruments during the root canal treatment, will allow to avoid intraoperative complications such as the separation of the instruments.

## Installation

You can build the project using the [Maven](https://maven.apache.org/) wrapper included. In Linux/Mac OS X you should type the following: 

```
$ ./mvnw package
```

Or in Windows:

```
mvnw.cmd package
```

This will create an executable JAR under target/ folder called _"manager-{version}-shaded.jar"_. Once the executable jar was created, to execute the project you should type the following:

```
$ java -jar target/manager-{version}-shaded.jar
```

## Requirements

* Windows, MacOS X or Linux platform
* Java 8


## Contributors

* [dortegau](http://twitter.com/dortegau)

## License

![Apache 2.0 Licence](https://img.shields.io/hexpm/l/plug.svg)

This project is licensed under the [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0) license - see the [LICENSE.txt](LICENSE.txt) file for details.