package CHATGUI.Logic.modules.toserver;

import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Интерфейс описывающий договоренное поведение
 * для отправки команд внешнему пользователю
 */
public interface ISetData {
    void toServer(CmdAdaptor listener, String args);
}
