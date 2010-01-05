package su.sergey.contacts.util.dao;

import java.sql.Connection;

/**
 * Отделяет функциональность класса {@link AbstractDAO} от конкретного
 * способа получения соединения с базой данных.
 * 
 * @author Сергей Богданов
 */
public interface ConnectionSource {
    /**
     * Возвращает соединение с базой данных. Всякое полученное таким
     * образом соединение в ОБЯЗАТЕЛЬНОМ порядке должно быть осовобождено
     * вызовом метода {@link #close(Connection)}.
     * 
     * @return соединение с базой данных
     */
    Connection getConnection();
    
    /**
     * Возвращает соединение с базой данных. Всякое полученное таким
     * образом соединение в ОБЯЗАТЕЛЬНОМ порядке должно быть осовобождено
     * вызовом метода {@link #close(Connection)}.
     * 
     * @param userName имя пользователя
     * @param password пароль
     * @return соединение с базой данных
     */
    Connection getConnection(String userName, String password);
    
    /**
     * Закрывает соединение с базой данных.
     * @param connection соединение с базой данных, полученное с помощью
     *        одного из методов getConnection().
     */
    void close(Connection connection);
}
