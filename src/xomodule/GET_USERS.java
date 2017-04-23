package xomodule;

public class GET_USERS implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		StringBuffer sb = new StringBuffer("USERS;");
		String[] str = command.split("[;]");
		UserList.getInstance().all().forEach((r)->{if(r.id.equals(str[2])) return; sb.append(r.login);sb.append(";");});
		NetworkManager.getInstance().sendAuto(sb.toString(), command.split("[;]")[2]);
		
	}

}
