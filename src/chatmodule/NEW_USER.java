package chatmodule;

import java.io.IOException;

public class NEW_USER implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		System.out.println(3);
		UserList ul = UserList.getInstance();
//		if(ul.users.isExist(str[2]))
//		{
//			System.out.println(4);
//			int i = 0;
//			while(ul.users.isExist(str[2]+i))
//			{
//				i++;
//				System.out.println(i);
//				System.out.println(str[2]+i);
//				System.out.println(ul.users.isExist(str[2]+i));
//			}
//			System.out.println(5);
//			ul.users.register(new User(str[2]+i, Integer.parseInt(str[3])));
//			try
//			{
//				NetworkManager.getInstance().dos.writeUTF("Redirect;CHAT;customprotokoladaptor;EXIST;"+str[2]+i+";" + str[3] + ";");
//				NetworkManager.getInstance().dos.flush();
//			} catch (IOException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(6);
//		}
//		else
//		{
			System.out.println(44);
			try
			{
				System.out.println(55);
				NetworkManager.getInstance().dos.writeUTF("Redirect;CHAT;customprotokoladaptor;EXIST;"+ul.users.register(new User(str[2], str[3]))+";" + str[3] + ";");
				NetworkManager.getInstance().dos.flush();
				System.out.println(66);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}

	}

}
