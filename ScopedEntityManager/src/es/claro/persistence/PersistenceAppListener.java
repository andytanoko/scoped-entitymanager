package es.claro.persistence;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 *
 * @author  puche
 *
 * Web application lifecycle listener.
 */

public class PersistenceAppListener implements ServletContextListener {

  private static final String PU_PARAMETER_NAME =
      "es.claro.persistence.PERSISTENCE_UNIT";

  public void contextInitialized(ServletContextEvent evt) {

    PersistenceManager pm = PersistenceManager.getInstance();
    
    if (pm instanceof ScopedPersistenceManager) {
      
      // If the name of the persistence unit has not been set yet
      if (PersistenceManager.getPersistenceUnit() == null) {
        String pu = evt.getServletContext().getInitParameter(PU_PARAMETER_NAME);
        PersistenceManager.setPersistenceUnit((pu != null)? pu
                               : PersistenceManager.DEFAULT_PERSISTENCE_UNIT);
      }
    }
  }

  public void contextDestroyed(ServletContextEvent evt) {
    
    PersistenceManager.getInstance().closeEntityManagerFactory();
  }
}