# act-ebean2

[![APL v2](https://img.shields.io/badge/license-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) 
[![Maven Central](https://img.shields.io/maven-central/v/org.actframework/act-ebean2.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22act-ebean2%22)
[![Build Status](https://travis-ci.org/actframework/act-ebean2.svg?branch=master)](https://travis-ci.org/actframework/act-ebean2)
[![codecov](https://codecov.io/gh/actframework/act-ebean2/branch/master/graph/badge.svg)](https://codecov.io/gh/actframework/act-ebean2)
[![Javadocs](http://www.javadoc.io/badge/org.actframework/act-ebean2.svg?color=blue)](http://www.javadoc.io/doc/org.actframework/act-ebean2)


Ebean plugin for ACT Framework. 

## act-ebean vs act-ebean2

* act-ebean: support JDK7 and JDK8
* act-ebean2: uses latest ebean version but can only run on JDK8

**Note** 

[act-ebean](https://github.com/actframework/act-ebean) artifactId is now **act-ebean-java7**, and [act-ebean2](https://github.com/actframework/act-ebean2/)'s artifactId is now **act-ebean**
 
## Versions

Refer to https://github.com/actframework/actframework/blob/master/VERSION_MATRIX.md

## Configuration

For configuration items, please refer to [act-sql-common](https://github.com/actframework/act-sql-common)

If application needs to manipulate the loaded configuration before `EbeanServer` is created, e.g. configuring the multi-tenant support, try add the following method in any Class:
 
```java
@OnEvent(beforeAppStart = true)
public static void configureEbean(ServerConfig config) {
    // do whatever required on ebean's ServerConfig
}
```
