

public class Userpass
{
    private String name, passwort;

    public Userpass(String pName, String pPasswort)
    {
        name = pName;
        passwort = pPasswort;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String pName)
    {
        name = pName;
    }

    public String getPasswort()
    {
        return passwort;
    }

    public void setPasswort(String pPasswort)
    {
        passwort = pPasswort;
    }
}
