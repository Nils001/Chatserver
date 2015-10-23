
/**
 * Write a description of class Echoserver here.
 * 
 * @author Norbert Gutsche 
 * @version 02.05.2012
 */
public class Echoserver extends Server
{
    // instance variables - replace the example below with your own
    private final String ENDE = "*bye*";

    /**
     * Constructor for objects of class Echoserver
     */
    public Echoserver()
    {
        super (2000);   //Port 2000 ist der Port des Echoservers
    }

    /**
     * Wenn ein Client anfragt, bekommt er auftomatisch diese Verbindung 
     * und somit diesen Satz geschickt!
     */
    public void processNewConnection(String pClientIP, int pClientPort)
    {
        this.send(pClientIP, pClientPort, "Willkommen " + pClientIP + 
            " auf Port " + pClientPort + " beim Echo-Server!");
        
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {
        if (pMessage.equals(ENDE))
        {
            this.closeConnection(pClientIP, pClientPort);
        }
        else
        {
            this.send(pClientIP, pClientPort, pClientIP + " " + pClientPort
                + ": " + pMessage);
            
        }
    }

    public void processClosedConnection(String pClientIP, int pClientPort)
    {
        this.send(pClientIP, pClientPort, pClientIP + " " + pClientPort + 
        " auf Wiedersehen");
        
    }
}
