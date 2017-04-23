package CHATGUI.Logic.items;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Класс ресурс, содержащий все общие используемые поля
 */
public final class PropertyBox {

    public static final String INFO_ROOM = "INFO~ROOM";

    public static final String COMMAND_PATH_FROM_SRV = "CHATGUI.Logic.modules.fromserver.";
    public static final String COMMAND_PATH_TO_SRV = "CHATGUI.Logic.modules.toserver.";
    public static final String COMMAND_PATH_INNER = "CHATGUI.Logic.modules.inner.";

    public static final String ADMIN = "ADMIN";
    public static final String BAN = "!!!_";
    public static final String USER = "USER";
    public static final String ROOM = "ROOM";
    public static final String MESSAGE = "MESSAGE";
    public static final String ARG_SEP = ";";
    public static final String OUT_SEP = "*";
    public static final String PVT_SEP = "_";
    public static final String COM_SEP = ",";

    public static final int MAX_LOGIN_SIZE = 33;
    public static final int MIN_LOGIN_SIZE = 3;

    public static final Color BlueViolet = Color.decode("#8A2BE2");
    public static final Color DarkOliveGreen = Color.decode("#556B2F");
    public static final Color Indigo = Color.decode("#4B0082");
    public static final Color D_ORANGE = Color.decode("#FF8C00");
    public static final Color Lavender = Color.decode("#E6E6FA");
    public static final Color WHITE = Color.decode("#F8F8FF");
    public static final Color Gray = Color.decode("#808080");
    public static final Color Fuchsia = Color.decode("#FF00FF");
    public static final Color LightSalmon = Color.decode("#FFA07A");
    public static final Color PaleGreen = Color.decode("#98FB98");
    public static final Color LightBlue = Color.decode("#ADD8E6");

    public static final Border DEFAULT = BorderFactory.createEtchedBorder();
    public static final Border EMPTY = BorderFactory.createEmptyBorder();

    public enum toSrv {NEW_USER, NEW_ROOM, CONNECT_ROOM, CONNECT_USER, PRIVATE_MSG, PUBLIC_MSG, BAN, UNBAN, EXIT_ROOM, QUIT_ROOM, EXIT}

    public enum fromSrv {USER_LIST, ROOM_LIST, PRIVATE_MSG, PUBLIC_MSG, NEW_ROOM, NEW_USER, EXIT_USER, EXIST, EXIT_ROOM}

    public enum inner {ROOM, MESSAGE, QUIT_ROOM, USER, NEW_ROOM}
}
