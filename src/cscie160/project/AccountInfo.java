package cscie160.project;

import java.util.HashMap;

/**
 * This class provides account informations.
 * 
 * @author Kalpan
 * @version 1.0
 */
public class AccountInfo
{
    /** Account's PIN */
    private String PIN;
    /** hashmap for commands */
    private final HashMap<Commands, Boolean> permissions;

    /**Default constructor*/
    public AccountInfo()
    {
        permissions = new HashMap<Commands, Boolean>();
    }
//-----------------------------  Getter Setters  -----------------------------------------//
    /** Get PIN no. */
    public String getPIN()
    { return PIN; }
  
    /** Set PIN for account*/
    public void setPIN(String PIN)
    { this.PIN = PIN; }
  
    /**To set permissions*/
    public void setAccess(Commands type, boolean access)
    { permissions.put(type, access); }
  
    /**To get permission info*/
    public boolean getAccess(Commands type)
    { Boolean response = permissions.get(type);
        if (response == null) response = true;
        return response;
    }
}
