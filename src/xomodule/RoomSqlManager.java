package xomodule;

import java.util.ArrayList;
import java.util.Iterator;

public class RoomSqlManager implements Iterable<Room>
{
	private ArrayList<Room> mock = new ArrayList<Room>();
	private RoomSqlManager(){}
	private static class SingletonHolder
	{
		private static RoomSqlManager instance = new RoomSqlManager();
	}
	public static RoomSqlManager getInstance()
	{
		return SingletonHolder.instance;
	}
	public Room get(String s)
	{
		for(int i = 0; i < mock.size(); i++)
			if(mock.get(i).name.equals(s))
				return mock.get(i);
		return null;
	}
	
	public boolean isExist(String login)
	{
		if(get(login) != null)
			return true;
		return false;
	}
	
	public String register(Room m)
	{
		m.name = m.name.trim();
		if(!isExist(m.name))
			mock.add(m);
		else
		{
			int i = 0;
			while(isExist(m.name+i))
				i++;
			m.name+=i;
			mock.add(m);
		}
		return m.name;
	}

	public void show()
	{
		mock.forEach((m)->{System.out.println("\"" + m.name + "\"");});
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Room> all()
	{
		return (ArrayList<Room>) mock.clone();
	}
	public void remove(Room r)
	{
		mock.remove(r);
		
	}
	@Override
	public Iterator<Room> iterator()
	{
		// TODO Auto-generated method stub
		return new Iter();
	}
	
	class Iter implements Iterator<Room>
	{
		int i = 0;
		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return i<mock.size();
		}

		@Override
		public Room next()
		{
			// TODO Auto-generated method stub
			return mock.get(i);
		}
		
	}
}
