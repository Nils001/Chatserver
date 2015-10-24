
/**
 * 
 * 
 * @author  
 * @version 
 */
public class Chatserver extends Server
{
    // instance variables - replace the example below with your own
    private final String ENDE = "*bye*";
    private List identitat;
    private Passwortliste passwortliste;

    /**
     * Constructor for objects of class Echoserver
     */
    public Chatserver()
    {
        super (2000);   //Port 2000 ist der Port des Echoservers
        identitat = new List();
        passwortliste = new Passwortliste();
    }

    /**
     * Wenn ein Client anfragt, bekommt er auftomatisch diese Verbindung 
     * und somit diesen Satz geschickt!
     */
    public void processNewConnection(String pClientIP, int pClientPort)
    {
        this.sendToAll("Neue Verbindung von" + pClientIP + " auf Port " + pClientPort);
        send(pClientIP, pClientPort, "Bitte Eingloggen!!!!!11!!");
        Identitaet a = new Identitaet(pClientIP, pClientPort);
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {
        if(pMessage != null)
        {
            if (pMessage.equals(ENDE))
            {
                this.closeConnection(pClientIP, pClientPort);
            }
            

            char[] msg = pMessage.toCharArray();
            char aus = '!';
            if(msg[0]!=(aus))
            {
                if(this.getIdentitaet(pClientIP,pClientPort).getEingeloggt())
                {
                    this.sendToAll(pMessage);
                }
                else
                {
                    this.send(pClientIP,pClientPort,"Bitte zuerst einloggen!");
                }

                String[] separated = pMessage.split(" ");
                if (separated[0] != null)
                {
                    switch (separated[0])
                    {

                        /*Login mit !login <Username> <Passwort>*/
                        case "!login":
                        if (separated[1] != null && separated[2] != null)
                        {
                            Userpass a = passwortliste.suchen(separated[1]);
                            if (a != null)
                            {
                                String passwort = a.getPasswort();
                                if (passwort.equals(separated[2]))
                                {

                                    Identitaet user = this.getIdentitaet(pClientIP,pClientPort);
                                    if(user != null)
                                        user.setEingeloggt(true);
                                }
                            }
                        }

                    }
                }

            }
        }
    }

    private Identitaet getIdentitaet(String pClientIp,int pClientPort)
    {
        identitat.toFirst();
        while (identitat.hasAccess() && !identitat.isEmpty())
        {
            Identitaet a = (Identitaet) identitat.getObject();
            if(a.getIp().equals(pClientIp) && a.getPort() == pClientPort)
                return (Identitaet) identitat.getObject();
            else
                identitat.next();
        }
        return null;
    }

    public void processClosedConnection(String pClientIP, int pClientPort)
    {
        this.sendToAll(pClientIP + " " + pClientPort + " auf Wiedersehen");
    }
}
