package CHATGUI.Logic.modules;

import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.items.PropertyBox.fromSrv;
import CHATGUI.Logic.items.PropertyBox.inner;

import java.util.ArrayList;
import java.util.List;

public class CmdTranslator {
    private static final String SEPARATOR = PropertyBox.ARG_SEP;

    public static List<String> strToList(String commandLine) {
        List<String> list = new ArrayList<>();
        String[] lines = commandLine.split(SEPARATOR);
        for (String line : lines) {
            list.add(line.trim());
        }
        return list;
    }

    public static String cmdToStr(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(SEPARATOR);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static toSrv getSrvCmd(String cmd) {
        return toSrv.valueOf(cmd.toUpperCase());
    }

    public static fromSrv getCltCmd(String cmd) {
        return fromSrv.valueOf(cmd.toUpperCase());
    }

    public static inner getInner(String cmd) {
        return inner.valueOf(cmd.toUpperCase());
    }
}
