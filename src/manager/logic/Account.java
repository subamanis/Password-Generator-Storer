package manager.logic;

public final class Account
{
    private String siteName;
    private String username;
    private String email;
    private String extraInfo;
    private String password;

    public Account(String siteName, String username, String email, String extraInfo, String password){
        this.siteName = (siteName.trim().equals("")) ? "-" : siteName;
        this.username = (username.trim().equals("")) ? "-" : username;
        this.email = (email.trim().equals("")) ? "-" : email;
        this.extraInfo = (extraInfo.trim().equals("")) ? "-" : extraInfo;
        this.password = (password.trim().equals("")) ? "-" : password;
    }

    public String getSiteName(){
        return this.siteName;
    }

    public void changeSiteName(String siteName){
        this.siteName = siteName;
    }

    public String getUsername(){
        return this.username;
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return this.email;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public String getExtraInfo(){
        return this.extraInfo;
    }

    public void changeExtraInfo(String extraInfo){
        this.extraInfo = extraInfo;
    }

    public String getPassword(){
        return this.password;
    }

    public void changePassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "\n"+this.siteName+" \nUsername: "+this.username+"\nEmail: "+this.email+"\nAdditional info: "+this.extraInfo;
    }
}
