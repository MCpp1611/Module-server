package newserver;

public class Msg
{
	String msg;
	//String name;
	String senderName;
	ListeningSocket senderObject;
	public Msg(String msg, String ls)
	{
		this.msg = msg;
		//this.name = name;
		senderName = ls;
	}
	public Msg(String msg, String ls, ListeningSocket sender)
	{
		this.msg = msg;
		//this.name = name;
		senderName = ls;
		senderObject = sender;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
//	public String getName()
//	{
//		return name;
//	}
//	public void setName(String name)
//	{
//		this.name = name;
//	}

}
