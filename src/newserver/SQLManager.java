package newserver;

import java.util.ArrayList;

public class SQLManager
{
	private static volatile ArrayList<Modyle> mock = new ArrayList<Modyle>();
	
	public static Modyle get(String s)
	{
		for(int i = 0; i < mock.size(); i++)
			if(mock.get(i).login.equals(s))
				return mock.get(i);
		return null;
	}
	
	public static boolean isExist(String login)
	{
		if(get(login) != null || login.equals("Server") || login.equals(""))
			return true;
		return false;
	}
	
	public static void register(Modyle m)
	{
		m.login = m.login.trim();
		if(!isExist(m.login))
			mock.add(m);
	}

	public static void show()
	{
		mock.forEach((m)->{System.out.println("\"" + m.login + "\"");});
		
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Modyle> all()
	{
		return (ArrayList<Modyle>) mock.clone();
		
	}

}
