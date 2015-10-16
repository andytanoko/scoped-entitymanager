Managing EntityManager lifecycle when using JPA in a non enterprise environment (e.g. Tomcat) is a custom task you must take care of by yourself for you don't have the IoC (dependence injection) of a Java EE 5 to manage the EntityManager lifecycle.

Here is a little project to be included as a library in your web applications which don't use a EJB container.

For Configuring your web application to use this library you just need to add a listener and, optionally, the name of the persistence unit to use as an init parameter in your web.xml such as:

```
<context-param>
  <param-name>es.claro.persistence.PERSISTENCE_UNIT</param-name>
  <param-value>MyPersistenceUnit</param-value>
</context-param>

<listener>
  <description>ServletContext and Request Listener for managing EntityManager with request scope</description>
  <listener-class>es.claro.persistence.PersistenceAppRequestListener</listener-class>   
</listener>
```

You can omit the init parameter and thus set it programmatically:
```
PersistenceManager.setPersistenceUnit("MyPersistenceUnit");
```

If no persistence unit name is provided (neither as an init parameter nor programmatically), "DefaultPU" is assumed.