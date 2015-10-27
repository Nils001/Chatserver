
public class ChatClient extends Client
{
    List room;

    /**
     * Constructor for objects of class ChatClient
     */
    public ChatClient(String pServerIP, int pServerPort)
    {
        super(pServerIP, pServerPort); 
        List room = new List();

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
        if(pMessage != null)
        {
            char[] msg = pMessage.toCharArray();
            char aus = '!';
            if(msg[0]!=(aus))
            {
                System.out.println(pMessage);
            }
            else
            {
                String[] separated_ur = pMessage.split(" ", 2);
                if(separated_ur[0] != null)
                {
                    switch (separated_ur[0])
                    {
                        case "!ur":

                        break;
                    }
                }
            }
        }
    }
}
