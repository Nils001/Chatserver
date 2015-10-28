import org.json.*;

public class Chatserver extends Server
{
    // instance variables - replace the example below with your own
    private final String ENDE = "*bye*";
    private List identitat, raum;
    private Passwortliste passwortliste;

    public Chatserver()
    {
        super (2000);   //Port 2000 ist der Port des CHatservers
        identitat = new List();
        raum = new List();
        passwortliste = new Passwortliste();
    }

    /**
     * Wenn ein Client anfragt, bekommt er auftomatisch diese Verbindung 
     * und somit diesen Satz geschickt!
     */
    public void processNewConnection(String pClientIP, int pClientPort)
    {
        this.sendToAll("Server: Neue Verbindung von" + pClientIP + " auf Port " + pClientPort);
        send(pClientIP, pClientPort, "Server: Bitte Eingloggen!");
        Identitaet a = new Identitaet(pClientIP, pClientPort);
        identitat.append(a);
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
                    this.send(pClientIP,pClientPort,"Server: Bitte zuerst einloggen!");
                }
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
                                Identitaet huser = this.getIdentitaet2(separated[1]); //???????
                                Identitaet user = this.getIdentitaet(pClientIP,pClientPort);

                                if (huser == null)
                                {
                                    if(user != null)
                                    {
                                        user.setEingeloggt(true);
                                        user.setName(separated[1]);
                                        send(pClientIP, pClientPort, "Server: Anmeldung war erfolgreich!");
                                    }
                                    else
                                    {
                                        send(pClientIP, pClientPort, "Server: Identität ist nicht bekannt!");
                                    }
                                }
                                else 
                                {
                                    huser.setEingeloggt(false);
                                    huser.setName(null);
                                    String bClientIP = huser.getIp();
                                    int bClientPort = huser.getPort();
                                    send(bClientIP, bClientPort, "Server: Sie wurden abgemeldet!");
                                    user.setEingeloggt(true);
                                    user.setName(separated[1]);
                                    send(pClientIP, pClientPort, "Server: Anmeldung war erfolgreich!!");
                                }
                            }
                            else
                            {
                                send(pClientIP, pClientPort, "Server: Falsche Benutzerdaten!");
                            }
                        }
                        else
                        {
                            send(pClientIP, pClientPort, "Server: Falsche Benutzerdaten!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Server: Bitte Benutzername und Passwort eingeben!");
                    }
                    break;

                    case "!logout": //client aus allen räumen entfernen
                    if (getIdentitaet(pClientIP,pClientPort).getEingeloggt())
                    {
                        Identitaet user = this.getIdentitaet(pClientIP,pClientPort);
                        user.setEingeloggt(false);
                        user.setName(null);
                        send(pClientIP, pClientPort, "Server: Erfolgreich abgemeldet!");
                    }
                    break;

                    case "!updaterequest": //nur wenn angemeldet
                    JSONObject update = new JSONObject();
                    raum.toFirst();
                    int raumid = 0;
                    while(raum.hasAccess()&&!raum.isEmpty()) // geht durch alle Räume
                    {
                        Room pr =(Room) raum.getObject();
                        raum.toFirst();
                        List puser = pr.getList();
                        puser.toFirst();
                        int i = 0;
                        JSONObject preRoom = new JSONObject();
                        JSONArray preU = new JSONArray();//USer Array
                        while(puser.hasAccess()&& !puser.isEmpty()) //geht durch alle User eines Raumes
                        {
                            Identitaet pu = (Identitaet) puser.getObject();
                            String a = pu.getName();
                            try{
                                preU.put(i,a);
                            }
                            catch(Exception e)
                            {

                            }
                            i++;
                        }
                        try{
                            preRoom.put("user",preU);
                            preRoom.put("useranzahl",i);
                            update.put(Integer.toString(raumid),preRoom);
                        }
                        catch(Exception e)
                        {

                        }
                    }
                    break;

                    case "!quit":
                    this.closeConnection(pClientIP, pClientPort);
                    break;

                    /*Private Nachricht mit !p <Username> <Nachricht>*/
                    case "!p":
                    if (getIdentitaet(pClientIP,pClientPort).getEingeloggt())
                    {
                        if (separated[1] != null)
                        {
                            if (separated[2] != null)
                            {
                                Identitaet huser = this.getIdentitaet2(separated[1]);
                                String eClientIP = huser.getIp();
                                int eClientPort = huser.getPort();
                                int a = 3;
                                String message = separated[2];
                                while (separated[a] != null)
                                {
                                    message = message + " " + separated[a];
                                }
                                send(eClientIP, eClientPort, message);
                            }
                            else
                            {
                                send(pClientIP, pClientPort, "Server: Bitte Nachricht eingeben!");
                            }
                        }
                        else
                        {
                            send(pClientIP, pClientPort, "Server: Bitte Empfänger angeben!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Server: Bitte erst anmelden!");
                    }
                    break;

                    /*Raum betreten mit !jr <Raumnummer> <Passwort>*/
                    case "!jr":
                    if (getIdentitaet(pClientIP,pClientPort).getEingeloggt())
                    {
                        if (separated[1] != null)
                        {
                            raum.toFirst();
                            int b = Integer.parseInt(separated[1]);
                            while (raum.hasAccess() && !raum.isEmpty())
                            {
                                Room a = (Room) raum.getObject();
                                if(a.getRoomid() == b)
                                {
                                    if (a.checkPassword(separated[2]))
                                    {
                                        a.addUser(this.getIdentitaet(pClientIP, pClientPort));    
                                        send(pClientIP, pClientPort, "Server: Erfolgreich Raum " + b + " beigetreten");
                                        break;
                                    }
                                    else
                                    {
                                        send(pClientIP, pClientPort, "Server: Falsches Passwort!");
                                        break;
                                    }
                                }
                                else
                                {
                                    raum.next();
                                }
                            }
                            send(pClientIP, pClientPort, "Server: Raum nicht vorhanden!"); //wieso wird das ausgegeben?
                        }
                        else
                        {
                            send(pClientIP, pClientPort, "Server: Bitte Raumnummer eingeben!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Server: Bitte erst anmelden!");
                    }
                    break;

                    /*Raum erstellen mit !cr <Raumnummer> <Passowrt>*/
                    case "!cr":
                    if (getIdentitaet(pClientIP,pClientPort).getEingeloggt())
                    {
                        if (separated[1] != null)
                        {
                            if (separated[2] != null)   //gibt errors wenn kein passwort
                            {
                                int b = Integer.parseInt(separated[1]);
                                Room a = new Room(b, separated[2]);
                                raum.append(a);
                                send(pClientIP, pClientPort, "Server: Der Raum " + separated[1] + " wurde mit dem Passwort " + separated[2] + " erstellt.");
                            }
                            else
                            {
                                int b = Integer.parseInt(separated[1]);
                                Room a = new Room(b, null);
                                raum.append(a);
                                send(pClientIP, pClientPort, "Server: Der Raum " + separated[1] + " wurde erstellt");
                            }
                        }
                        else
                        {
                            send(pClientIP, pClientPort, "Server: Bitte Raumnummer eingeben!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Server: Bitte erst anmelden!");
                    }
                    break;
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
            {
                return (Identitaet) identitat.getObject();
            }
            else
            {
                identitat.next();
            }
        }
        return null;
    }

    private Identitaet getIdentitaet2(String pName)
    {
        identitat.toFirst();
        while (identitat.hasAccess() && !identitat.isEmpty())
        {
            Identitaet a = (Identitaet) identitat.getObject();
            if (a.getName() != null)
            {
                if(a.getName().equals(pName))
                {
                    return (Identitaet) identitat.getObject();
                }
                else
                {
                    identitat.next();
                }
            }
            else
            {
                identitat.next();
            }
        }
        return null;
    }

    public void processClosedConnection(String pClientIP, int pClientPort)
    {
        this.sendToAll("Server: " + pClientIP + " " + pClientPort + " auf Wiedersehen");
    }

    public void test()
    {

        JSONObject update = new JSONObject();
        raum.toFirst();
        int raumid = 0;
        while(raum.hasAccess()&&!raum.isEmpty()) // geht durch alle Räume
        {
            Room pr =(Room) raum.getObject();
            raum.toFirst();
            List puser = pr.getList();
            puser.toFirst();
            int i = 0;
            JSONArray preU = new JSONArray();
            while(puser.hasAccess()&& !puser.isEmpty()) //geht durch alle User eines Raumes
            {
                Userpass pu = (Userpass) puser.getObject();
                String a = pu.getName();
                try{
                    preU.put(i,a);
                }
                catch(Exception e)
                {

                }
            }
        }
    }
}
