
public class UserRoom
{
    private List userlist;
    private int roomid;

    //null für Raum ohne PW
    public UserRoom(int pRoomid)
    {
        roomid = pRoomid;
        userlist = new List();
    }

    public int getRoomid()
    {
        return roomid;
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
        return false;
    }

    public void addUser(Identitaet pIdentitaet)
    {
        if (pIdentitaet != null)
        {
            String name = pIdentitaet.getName();
            if (checkUser(name))
            {
                userlist.append(pIdentitaet);
            }
        }
    }
}
