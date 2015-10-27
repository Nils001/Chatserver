
public class Room
{
    private List userlist;
    private int roomid;
    private String password;

    //null für Raum ohne PW
    public Room(int pRoomid, String pPassword)
    {
        roomid = pRoomid;
        password = pPassword;
        userlist = new List();
    }

    public int getRoomid()
    {
        return roomid;
    }

    public boolean checkPassword(String pPassword)
    {
        if (password.equals(pPassword))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Identitaet searchUser(String pName)
    {
        if (pName != null)
        {
            userlist.toFirst();
            while (userlist.hasAccess() && !userlist.isEmpty())
            {
                Identitaet a = (Identitaet) userlist.getObject();
                if (a.getName() != null)
                {
                    if(a.getName().equals(pName))
                    {
                        return (Identitaet) userlist.getObject();
                    }
                    else
                    {
                        userlist.next();
                    }
                }
                else
                {
                    userlist.next();
                }
            }
        }
        return null;
    }

    public boolean checkUser(String pName)
    {
        if (pName != null)
        {
            userlist.toFirst();
            while (userlist.hasAccess() && !userlist.isEmpty())
            {
                Identitaet a = (Identitaet) userlist.getObject();
                if (a.getName() != null)
                {
                    if(a.getName().equals(pName))
                    {
                        System.out.println("System: Benutzer schon vorhanden");
                        return true;
                    }
                    else
                    {
                        userlist.next();
                    }
                }
                else
                {
                    userlist.next();
                }
            }
        }
        System.out.println("System: Benutzer noch nicht vorhanden");
        return false;
    }

    public void addUser(Identitaet pIdentitaet)
    {
        if (pIdentitaet != null)
        {
            String name = pIdentitaet.getName();
            System.out.println("System: Benutzer akzeptiert");
            if (!checkUser(name))
            {
                userlist.append(pIdentitaet);
                System.out.println("System: Benutzer hinzugefügt");
            }
        }
    }

    public List getList()
    {
        return this.userlist;
    }

    public void removeUser(String pName)
    {
        if (pName != null)
        {
            userlist.toFirst();
            while (userlist.hasAccess() && !userlist.isEmpty())
            {
                Identitaet a = (Identitaet) userlist.getObject();
                if (a.getName() != null)
                {
                    if(a.getName().equals(pName))
                    {
                        userlist.remove();
                    }
                    else
                    {
                        userlist.next();
                    }
                }
                else
                {
                    userlist.next();
                }
            }
        }
    }
}
