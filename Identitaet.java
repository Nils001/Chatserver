
public class Identitaet
{
    String ip, name;
    int port;
    boolean eingeloggt;

    public Identitaet(String bIP, int bPort)
    {
        ip = bIP;
        port = bPort;
        eingeloggt = false;
    }

    public void setName(String bname)
    {
        name = bname;
    }

    public String getName()
    {
        return name;
    }

    public String getIp()
    {
        return ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setEingeloggt(boolean xyo)
    {
        eingeloggt = xyo;
    }

    public boolean getEingeloggt()
    {
        return eingeloggt;
    }
}
