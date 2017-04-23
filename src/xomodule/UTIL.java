package xomodule;

import java.util.ArrayList;

public class UTIL
{
	public void sendAll(String str)
	{
		ArrayList<User> al = UserList.getInstance().all();
		NetworkManager md = NetworkManager.getInstance();
		for(int i = 0; i < al.size(); i++)
			md.sendAuto(str, al.get(i).id);
	
	}
	public void sendAllWithoutOne(String str, String login)
	{
		ArrayList<User> al = UserList.getInstance().all();
		NetworkManager md = NetworkManager.getInstance();
		for(int i = 0; i < al.size(); i++)
			if(!al.get(i).login.equals(login))
				md.sendAuto(str, al.get(i).id);
	
	}
}
