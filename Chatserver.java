
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
                    this.send(pClientIP,pClientPort,"Bitte zuerst einloggen!");
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
                                System.out.println(user.getEingeloggt());
                                if (huser.getEingeloggt() == false)
                                {
                                    if(user != null)
                                    {
                                        user.setEingeloggt(true);
                                        user.setName(separated[1]);
                                        send(pClientIP, pClientPort, "Anmeldung war erfolgreich!");
                                    }
                                    else
                                    {
                                        send(pClientIP, pClientPort, "Identität ist nicht bekannt!");
                                    }
                                }
                                else 
                                {
                                    huser.setEingeloggt(false);
                                    huser.setName(null);
                                    String bClientIP = huser.getIp();
                                    int bClientPort = huser.getPort();
                                    send(bClientIP, bClientPort, "Sie wurden abgemeldet!");
                                    user.setEingeloggt(true);
                                    user.setName(separated[1]);
                                    send(pClientIP, pClientPort, "Anmeldung war erfolgreich!!");
                                }
                            }
                            else
                            {
                                send(pClientIP, pClientPort, "Falsche Benutzerdaten!");
                            }
                        }
                        else
                        {
                            send(pClientIP, pClientPort, "Falsche Benutzerdaten!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Bitte Benutzername und Passwort eingeben!");
                    }
                    break;

                    case "!logout":
                    Identitaet user = this.getIdentitaet(pClientIP,pClientPort);
                    user.setEingeloggt(false);
                    user.setName(null);
                    send(pClientIP, pClientPort, "Erfolgreich abgemeldet!");
                    break;

                    case "!updaterequest":
                    break;

                    case "!quit":
                    this.closeConnection(pClientIP, pClientPort);
                    break;

                    /*Private Nachricht mit !p <Username> <Nachricht>*/
                    case "!p":
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
                            send(pClientIP, pClientPort, "Bitte Nachricht eingeben!");
                        }
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Bitte Empfänger angeben!");
                    }
                    break;

                    /*Raum betreten mit !r <Raumnummer>*/
                    case "!r":
                    if (separated[1] != null)
                    {
                        //joinRoom(separated[1]); 
                    }
                    else
                    {
                        send(pClientIP, pClientPort, "Bitte Raumnummer eingeben!");
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
            if(a.getName().equals(pName))
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

    public void processClosedConnection(String pClientIP, int pClientPort)
    {
        this.sendToAll(pClientIP + " " + pClientPort + " auf Wiedersehen");
    }
}
