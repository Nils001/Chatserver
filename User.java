
import org.json.*;
public class User
{
    private String name, passwort;

    public User(String benutzername, String benutzerpasswort)
    {   
        name = benutzername;
        passwort = benutzerpasswort;
    }

    public String gibName()
    {
        return name;
    }

    public String gibPasswort()
    {
        return passwort;
    }
}
