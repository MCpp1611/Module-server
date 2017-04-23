package CHATGUI;

/**
  Тестирование пользовательского интрефейса
 */

import CHATGUI.Logic.modules.toserver.GET_ROOMS;
import java.io.IOException;

public class Test implements Runnable{
	public static void main(String[] args) throws Exception {

//		String sep = ARG_SEP;
//		        String pvtSep = PVT_SEP;
//		        String ul = "USER_LIST";
//		        String rl = "ROOM_LIST";
//		        String pbmsg = "PUBLIC_MSG";
//		        String prvmsg = "PRIVATE_MSG";
//		        String nameRoom = "ROOM";
//		        String nameUser = "User";
//		        String newRoom = "NEW_ROOM";
//		        String exist = "EXIST";
//		        int count = 0;
//
//				ChatManager manager = ChatManager.getInstance();
//		        System.out.println("start");
//		
//		        while (manager.getMessage().isEmpty()) {
//		           Thread.sleep(300);
//		        }
//		        System.out.println(manager.getMessage());
//		
////		        System.out.println("serverCmd = " + serverCmd);
//		
//		        String nrCommand = newRoom + sep + PropertyBox.INFO_ROOM;
//		        String newRvtRoomCommand =
//		                newRoom + sep + nameUser + 1 + pvtSep + nameUser + 2;
//		        String ulCommand = ul;
//		        String rlCommand = rl;
//		
//		        for (int i = 0; i < 10; i++) {
//		            ulCommand += sep + nameUser + i;
//		            rlCommand += sep + nameRoom + i;
//		        }
//		
//		        manager.doCommand(nrCommand);
//		        manager.doCommand(ulCommand);
//		        manager.doCommand(rlCommand);
//		        manager.doCommand(newRvtRoomCommand);
//		        System.out.println();
//		        System.out.println(nrCommand);
//		        System.out.println(ulCommand);
//		        System.out.println(rlCommand);
//		        System.out.println(newRvtRoomCommand);
//		        System.out.println();
//		
//		        StringBuilder sb = new StringBuilder();
//		        for (int i = 0; i < 150; i++) {
//		            sb.append("Test ");
//		        }
//		        for (int i = 0; i < 20; i++) {
//		            manager.doCommand(prvmsg + sep + nameUser + 1 +
//		                    "_" + nameUser + 2 + sep + nameUser + 1 + sep + sb);
//		        }
//		        manager.doCommand(prvmsg + sep + PropertyBox.INFO_ROOM + sep +
//		                nameUser + 1 + sep + ". Ha ha ha! Tro lo lo! Test");
		new Thread(new Test()).start();
	}

	@Override
	public void run()
	{
		System.out.println(1);
		ChatManager manager = ChatManager.getInstance();
		NetworkManager nm = NetworkManager.getInstance();
		System.out.println(2);
		new GET_ROOMS().toServer(null, null);
		try
		{
			while(true)
			{
				System.out.println(3);
				manager.doCommand(nm.read());
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
