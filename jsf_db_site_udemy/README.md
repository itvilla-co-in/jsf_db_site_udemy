JSF/Primefaces/MySQL - CMS web application.

In order to successfully start application without errors, MySQL database must be started, SQL must be imported from file jsf_admin_SQL.sql into it, and Apache Tomcat 7 on port 8383 must be started. If you don't know how to set up these things, please check course lessons where it is covered on blank project.

When importing project in Eclipse IDE, if any errors still occur be sure to configure properties properly. Right click on project in Eclipse, click on Properties, then click on Project Facets. Following items must be checked:

Dynamic Web module: 3.0
Java: 1.7
JavaScript: 1.0
JavaServer Faces: 2.2

If Eclipse says Further configuration required, click on it, popup will occur, click Next, Next and then choose Disable Library Configuration. Then press OK. Popup will close, click OK.

Add Apache Tomcat 7 to Servers tab. 
Go again to Project Facets and add Apache Tomcat 7 to Runtime. 

Then go to Eclipse main menu, choose Project -> Clean -> Jsf_db_site

See also course lesson about setting these properties.