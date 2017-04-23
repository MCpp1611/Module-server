package xomodule;

public class Room// extends Thread
{
	public String name;
//	public Queue<Msg> com = new ArrayDeque<Msg>();
	public User first, second;
	public String next_course;
	public char[][] map = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	public Room(String name, User first, User second)
	{
		this.name = name;
		this.first = first;
		this.second = second;
		next_course = first.id;
//		start();
	}
//	@Override
//	public void run()
//	{
//		// TODO Auto-generated method stub
//		
//	}
}
