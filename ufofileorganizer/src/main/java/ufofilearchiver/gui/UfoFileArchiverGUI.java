/*
 * UfoFileArchiverGUI.java
 */

package ufofilearchiver.gui;

import java.util.EventObject;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class UfoFileArchiverGUI extends SingleFrameApplication {

    private UfoFileArchiverGUIView app = null;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
      app = new UfoFileArchiverGUIView(this);
      show(app);
      
      /*
       * Questa porzione di codice lancia specifici comandi all'uscita del programma 
       * c'ho messo due ore di ricerche per trovare questo pezzetto di codice maledetto!!
       * 
      */
       getApplication().addExitListener(new ExitListener(){

            public boolean canExit(EventObject arg0) {
              //System.out.println("Ce la farò a uscire?!?!");
              return true;
            }

            public void willExit(EventObject arg0) {
                app.close();
            }
           
           
        }); 
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of UfoFileArchiverGUI
     */
    public static UfoFileArchiverGUI getApplication() {
        return Application.getInstance(UfoFileArchiverGUI.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        //System.out.println(System.getProperty("user.dir"));
        launch(UfoFileArchiverGUI.class, args);
    }
}
