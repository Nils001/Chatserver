
public class Passwortliste
{
    private List benutzer;
    
    public Passwortliste()
    {
        benutzer = new List();
        adden("admin", "admin");
        adden("user", "user");
    }

    public void adden(String pName, String pPasswort)
    {
        if (pName != null && pPasswort != null)
        {
            Userpass a = new Userpass(pName, pPasswort);
            benutzer.append(a);
        }
    }

    public Userpass suchen(String pName)
    {
        benutzer.toFirst();
        while(benutzer.hasAccess())
        {
            Userpass a = (Userpass)benutzer.getObject();
            if (a.getName().equals(pName))
            {
                return a;
            }
            else
            {
                benutzer.next();
            }
        }
        return null;
    }
}
