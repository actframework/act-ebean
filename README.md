# act-ebean2

Ebean plugin for ACT Framework. 

## act-ebean vs act-ebean2

* act-ebean: support JDK7 and JDK8
* act-ebean2: uses latest ebean version but can only run on JDK8
 
## Versions

| ActFramework | act-ebean2 |
| ------------ | -------- |
| 1.0.x        | N/A | 
| 1.1.x        | 1.0.x |

## Configuration

For configuration items, please refer to [act-sql-common](https://github.com/actframework/act-sql-common)

If application needs to manipulate the loaded configuration before `EbeanServer` is created, e.g. configuring the multi-tenant support, try add the following method in any Class:
 
```java
@OnEvent(beforeAppStart = true)
public static void configureEbean(ServerConfig config) {
    // do whatever required on ebean's ServerConfig
}
```
