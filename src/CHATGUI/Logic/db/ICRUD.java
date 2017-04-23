package CHATGUI.Logic.db;

import java.util.List;
/**
 * Интерфейс описывающий возможные варианты поведений при работе с БД
 */
public interface ICRUD<E> {
    void create(E item);

    void create(String item);

    void create(List<E> list);

    void delete(E item);

    void delete(String login);

    List<E> read();

    List<String> readLogins();

    E readFromLogin(String login);
}
