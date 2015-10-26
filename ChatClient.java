
/**
 * Write a description of class ChatClient here.
 * 
 * @author Norbert Gutsche
 * @version 09.05.12
 */
public class ChatClient extends Client
{
    List room;

    /**
     * Constructor for objects of class ChatClient
     */
    public ChatClient(String pServerIP, int pServerPort)
    {
        super(pServerIP, pServerPort); 
        List room=new List();

    }

    public void nachrichtVersenden(String text)
    {
        send(text);        
    }

    public void beenden()
    {
        send("*bye*");        
    }

    public void updateRequest()
    {
        send("!updaterequest");

    }

    public void processMessage(String pMessage)
    {
        System.out.println(pMessage);
        String[] separated_ur = pMessage.split(" ", 2);
        if(separated[0] != null)
        {
            switch (separated_ur[0])
            {
                case "!ur"
                
                
                
                
                break;
                
                
                
            }

        
        }
    }
}
