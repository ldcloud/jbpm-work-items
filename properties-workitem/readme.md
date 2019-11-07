## PropertiesWorkItemHandler
A work item that reads a java properties file from disk.
### Install
1. Add work item handler: `org.jbpm.process.workitem.properties.PropertiesWorkItemHandler("directory")` (Resolver: Reflection, NOT MVEL), Replace `directory` to point to directory where properties files are stored.  
![picture](images/Deployments.png)

### How to use
Add the `Properties` work item to your business process, assign properties file name to the `fileName` input parameter and assign output parameter `properties` a process variable of type `java.util.Map`.
![picture](images/DataAssignments.png)