# coh-rest
Simple Coherence REST application (EAR = GAR + JPA + WAR)

## Overview

This is a self-contained application to demonstrate Coherence Federation using GAR deployment on WebLogic Server.

Using the provided scripts, two weblogic domains are created and configured for Coherence Federation. Cache on Domain1 is populated when a GET request for REST Resource is accessed via browser. This triggers cachestore to load data from the embedded Java DB. Subsequently, the object is replicated to domain2 and can be accessed by the browser connected to WebLogic Server on Domain2.



## Installation Requirements:
1. Install JDK 8
2. Install WLS 12c (12.2.1.2)


## Steps:
1. Rename startWebLogic.cmd.FIXME to startWebLogic.cmd (for windows)
  * Modify startWebLogic.cmd to match your directory and environment (MW_HOME and JAVA_HOME)
2. (Optional) Rename startWebLogic.sh.FIXME to startWebLogic.sh (for Linux)
  * Update startWebLogic.cmd and startWebLogic.sh to match your directory/environment (MW_HOME and JAVA_HOME)
3. (Optional)  Update basicMCSDomain.py when using Linux Guest in VirtualBox.
  *   Create and start Domain1 - startWebLogic.sh|cmd site1 7101
  *  Create and start Domain2 - startWebLogic.sh|cmd site2 7201

## RUN

1. Use browser to access http://localhost:7201/my-demo-app-war/rest/Person
The response will be an empty <collection>

2. Use browser to access http://localhost:7101/my-demo-app-war/rest/Person/1.json
You should see the following response

{"age":10,"id":1,"name":"Foo10"}

3. Use browser to access http://localhost:7201/my-demo-app-war/rest/Person/3.json
You should see the following response
{"age":30,"id":3,"name":"Foo30"}

4. Access http://localhost:7101/my-demo-app-war/rest/Person to see the following response
<collection>
<age>10</age>
<id>1</id>
<name>Foo10</name>
<age>30</age>
<id>3</id>
<name>Foo30</name>
</collection>

Note: The REST URL alternates between two domains/WebLogic Server (port 7101 and 7201) to demonstrate active-active replication.





