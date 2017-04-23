package newserver;

public class IRegistration implements ICommand
{

	@Override
	public void doCommand(Msg s)
	{
		String[] str = s.msg.split("[;]");
//		if(!SQLManager.isExist(str[1]))
//		{
			SQLManager.register(new Modyle(str[1], str[2], str[3]));
			s.senderObject.name = str[1];
			ClientsSingleton.getInstance().clients.add(s.senderObject);
			System.out.println("register");
//		}
		
	}

}
