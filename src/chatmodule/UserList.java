package chatmodule;

public class UserList
{
	private UserList(){}
	public UserSqlManager users = new UserSqlManager();
	private static class SingletonHolder
	{
		private static UserList instance = new UserList();
	}
	public static UserList getInstance()
	{
		return SingletonHolder.instance;
	}
}
