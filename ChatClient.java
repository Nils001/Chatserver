
public class ChatClient extends Client
{
    List room;

    /**
     * Constructor for objects of class ChatClient
     */
    public ChatClient(/*String pServerIP, int pServerPort*/)
    {
        //super(pServerIP, pServerPort); 
        super("127.0.0.1", 2000); 
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
                String[] separated = pMessage.split(" ");
                switch(separated[0])
                {
                    case "!rooms":
                    String[] split = pMessage.split("|");
                    for(int i = 0;i<split.length;i++)
                    {
                        if(split[i] != "!rooms")
                        {
                            
                            String[] raum = split[i].split(" ");
                            String raumid = raum[1];
                            int rid = Integer.parseInt(raumid);
                            String userzahl = raum[2];
                            Room a = new Room(rid,null); 
                        }

                    }

                    break;

                   
                }
            }
        }
    }
}
