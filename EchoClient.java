
/**
 * Write a description of class EchoClient here.
 * 
 * @author Norbert Gutsche
 * @version 09.05.12
 */
public class EchoClient extends Client
{
    

    /**
     * Constructor for objects of class EchoClient
     */
    public EchoClient(String pServerIP, int pServerPort)
    {
       super(pServerIP, pServerPort); 
       // receive braucht es nicht mehr, da es die neue Methode
       // processMasage gibt, die automatisch empfängt
    }

    public void nachrichtVersenden(String text)
    {
        send(text);        
    }
    
    public void beenden()
    {
        send("*bye*");        
    }
    
    public void processMessage(String pMessage)
    {
        System.out.println("Der Server sendet: " + pMessage);
    }
    
}
