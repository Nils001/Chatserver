
/**
 * Write a description of class ChatClient here.
 * 
 * @author Norbert Gutsche
 * @version 09.05.12
 */
public class ChatClient extends Client
{
    

    /**
     * Constructor for objects of class ChatClient
     */
    public ChatClient(String pServerIP, int pServerPort)
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
        System.out.println(pMessage);
    }
    
}
