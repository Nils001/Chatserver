
public class ChatClient extends Client
{
    List room;
    String update;

    /**
     * Constructor for objects of class ChatClient
     */
    public ChatClient(/*String pServerIP, int pServerPort*/)
    {
        //super(pServerIP, pServerPort); 
        super("127.0.0.1", 2000); 
        List room = new List();
        update = " ";
    }

    public String getMainUpdateString()
    {
        return update;
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
        update = update + pMessage+"\n";
        System.out.println(pMessage);
        /*char[] msg = pMessage.toCharArray();
        char aus = '!';
        if(msg[0]!= aus)
        {
        update = update + pMessage+"\n";//System.out.println(pMessage);
        System.out.println(pMessage);
        }
        else
        {
        String[] separated = pMessage.split(" ");
        switch(separated[0])
        {
        case "!rooms":
        //String[] split = pMessage.split("|");
        for(int i = 1;i < separated.length; i = i + 2)
        {
        //if(split[i] != "!rooms")
        String hraum = separated[i];
        //String[] raum = split[i].split(" ");
        String raumid = hraum;
        int rid = Integer.parseInt(raumid);
        //char[] huser = separated[i+1].toCharArray();
        String huser = separated[i+1];
        //for(int b = 1;b < huser.length; b++)
        //{
        //ser = ser + huser[b];
        //}
        String userzahl = huser;
        Room a = new Room(rid,null); 
        room.append(a);
        }
        //break;
        }*/
    }
}

