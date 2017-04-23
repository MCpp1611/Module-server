package newserver;

public class Modyle
{
	String login;
	String password;
	String version;
	public Modyle(String login, String password, String version)
	{
		this.login = login;
		this.password = password;
		this.version = version;
	}
	public String getLogin()
	{
		return login;
	}
	public void setLogin(String login)
	{
		this.login = login;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	@Override
	public String toString()
	{
		return "Modyle [login=" + login + ", version=" + version + "]";
	}

}
