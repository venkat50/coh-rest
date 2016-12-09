#=======================================================================================
# This sample uses the demo Derby Server that is installed with your product.
# Before starting the Administration Server, you should start the demo Derby server
# by issuing one of the following commands:
#
# startdb
#
# Please note that some of the values used in this script are subject to change based on 
# your WebLogic installation and the template you are using.
#
# Usage: 
#      java weblogic.WLST <WLST_script> 
#
# Where: 
#      <WLST_script> specifies the full path to the WLST script.
#=======================================================================================

#=======================================================================================
# Open a domain template.
#=======================================================================================

WL_HOME=os.environ["WL_HOME"]

selectTemplate('Basic WebLogic Server Domain')
loadTemplates()

#=======================================================================================
# Configure the Administration Server and SSL port.
#
# To enable access by both local and remote processes, you should not set the 
# listen address for the server instance (that is, it should be left blank or not set). 
# In this case, the server instance will determine the address of the machine and 
# listen on it. 
#=======================================================================================

print sys.argv[1]
print sys.argv[2]



cd('Servers/AdminServer')
set('ListenAddress','localhost')
#Uncomment for Linux guest in VirtualBox
#set('ListenAddress','10.0.2.15')
set('ListenPort', int(sys.argv[2]))



#=======================================================================================
# Define the user password for weblogic.
#=======================================================================================

cd('/')
cd('Security/base_domain/User/weblogic')
cmo.setPassword('welcome1')


#=======================================================================================
# Configure Coherence Cluster
#=======================================================================================
cd('/')

create(str(sys.argv[1]), 'CoherenceClusterSystemResource')



#=======================================================================================
# Create and configure a JDBC Data Source, and sets the JDBC user.
#=======================================================================================

cd('/')
create('sample', 'JDBCSystemResource')
cd('JDBCSystemResource/sample/JdbcResource/sample')
create('myDriverParams','JDBCDriverParams')
cd('JDBCDriverParams/NO_NAME_0')
set('DriverName','org.apache.derby.jdbc.EmbeddedDriver')
set('URL','jdbc:derby:sample;create=true')
set('PasswordEncrypted', 'app')
set('UseXADataSourceInterface', 'false')
create('myProps','Properties')
cd('Properties/NO_NAME_0')
create('user', 'Property')
cd('Property/user')
cmo.setValue('app')

cd('/JDBCSystemResource/sample/JdbcResource/sample')
create('myJdbcDataSourceParams','JDBCDataSourceParams')
cd('JDBCDataSourceParams/NO_NAME_0')
set('JNDIName', java.lang.String("jdbc/sample"))


#=======================================================================================
# Target resources to the servers. 
#=======================================================================================

cd('/')
assign('JDBCSystemResource', 'sample', 'Target', 'AdminServer')
#assign('CoherenceClusterSystemResource', 'Coherence-1', 'Target', 'AdminServer')

#=======================================================================================

#=======================================================================================
# Write the domain and close the domain template.
#=======================================================================================

setOption('OverwriteDomain', 'true')

DOMAIN_HOME=os.environ["DOMAIN_HOME"]
writeDomain(DOMAIN_HOME)
closeTemplate()

readDomain(DOMAIN_HOME)

cd('/CoherenceClusterSystemResource/'+sys.argv[1]+'/CoherenceResource/'+sys.argv[1])
create('myfedparams','CoherenceFederationParams')
cd('CoherenceFederationParams/NO_NAME_0')

set('FederationTopology','active-active')
set('RemoteCoherenceClusterListenPort',7574)
if sys.argv[1] == 'site1':
  set('RemoteCoherenceClusterName','site2')
else:
  set('RemoteCoherenceClusterName','site1')
set('RemoteParticipantHost','localhost')
#FIXME
#Use the following for Linux Guest in VirtualBox
#set('RemoteParticipantHost','10.0.2.15')

updateDomain()
closeDomain()

#=======================================================================================
# Exit WLST.
#=======================================================================================

exit()
