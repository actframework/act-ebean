# act-ebean CHANGELOG

1.7.4
* Revert changes that makes `JPADao` be `@Stateless` #25
* update act to 1.8.9
* update act-sql-common to 1.4.4

1.7.3 - 30/Oct/2018
* update act to 1.8.8
* update act-sql-common to 1.4.3
* Error connecting to sqlserver2008 #18

1.7.2 - 19/Jun/2018
* update act to 1.8.8-RC10
* update act-sql-common to 1.4.2
* update ebean to 11.17.5

1.7.1 - 7/Jun/2018
* update act to 1.8.8-RC9
* update act-sql-common to 1.4.1
* update ebean to 11.17.1
* Prevent double DDL generation when creating readOnly ebean server #24
* Add `!=` operator #18

1.7.0 - 29/May/2018
* update act to 1.8.8-RC8
* update act-sql-common to 1.4.0
* update ebean to 11.15.10

1.6.5 - 19/May/2018
* update act to 1.8.8-RC5
* update act-sql-common to 1.3.4
* update ebean to 11.15.9

1.6.4 - 14/May/2018
* update ebean to 11.15.8
* java.lang.ClassCastException: java.lang.String cannot be cast to [C #22

1.6.3 - 13/May/2018
* update act to 1.8.8-RC4
* Disable Ebean classpath search #21
* Register global mapping filter to avoid copying ebean enhanced fields #20

1.6.2 - 02/Mar/2018
* update act to 1.8.5
* update ebean to 11.15.3

1.6.1 - 25/Mar/2018
* rename package from `act.ebean2` to `act.ebean`

1.6.0 - 25/Mar/2018
* rename to act-ebean
* update act to 1.8.2
* update act-sql-common to 1.3.2
* update ebean to 11.15.1

---------------------------------------

Previous named act-ebean2

1.5.1 - 11/Mar/2018
* update to act-1.8.1
* update to act-sql-common-1.3.1
* update to ebean-11.14.3

1.5.0 - 4/Mar/2018
* update to act-1.8.0
* update to ebean-11.13.1
* Support act timestamp annotation #19

1.4.0
* update to act-1.7.0

1.3.0
* update to act-1.6.5
* update act-sql-common to 1.2.1
* update ebean to 11.9.1

1.2.1
* add `@BindOn` annotation to `EbeanConfigLoaded` event to allow early binding of listeners

1.2.0
* update act to 1.6.0
* update act-sql-common to 1.2.0

1.1.5
* update act to 1.5.1
* update act-sql-common to 1.1.3
* update ebean to 11.5.1

1.1.2
* update to act-1.4.11, act-sql-common-1.1.1
* apply oslg-bootstrap version mechanism
* improve maven build process

1.1.1
* Don't provide `EbeanServer` to `EbeanDao` if it is already provided #12 
* Suppress warning `cannot find job by id: __act_app__db_svc_loaded` #11 

1.1.0
* catch up to act-1.4.0, act-sql-common-1.1.0

1.0.4
- NPE when no third party datasource configured #6 
- update act-sql-common to 1.0.2

1.0.3
- update act-sql-common to 1.0.1
- It doesn't start with MySQL jdbc driver 5.x #3 
- Ebean Agent loaded twice if there are two ebean db services #4
- The datasource created in sql-common not used when creating ebean server #5 

1.0.2
- does not work when app start in PROD mode #2 

1.0.1
- It should use ebean's naming convention by default #1 

1.0.0 - baseline version
