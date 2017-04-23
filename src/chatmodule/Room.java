package chatmodule;

public class Room// extends Thread
{
	public String name;
	public UserSqlManager usl = new UserSqlManager();
//	public Queue<Msg> com = new ArrayDeque<Msg>();
	public Room(String name)
	{
		this.name = name;
//		start();
	}
//	@Override
//	public void run()
//	{
//		// TODO Auto-generated method stub
//		
//	}
}
