package su.sergey.contacts.util.dao;

import java.sql.Connection;

/**
 * Отделяет функционал AbstractDAO от конкреной
 * способа получения соединения с базой данных.
 * 
 * @author Сергей Богданов
 */
public interface ConnectionSource {
    /**
     * Возвращает соединение с базой данных. Всякое полученное таким
     * образом соединение в ОБЯЗАТЕЛЬНОМ порядке должно быть осовобождено
     * вызовом метода void close(Connection conn) throws DAOException.
     * 
     * @return соединение с базой данных
     * @throws DAOException в случае если подсоединиться в базе данных не
     * представляетяя возможным.
     */
    Connection getConnection() throws DAOException;
    
    /**
     * Возвращает соединение с базой данных. Всякое полученное таким
     * образом соединение в ОБЯЗАТЕЛЬНОМ порядке должно быть осовобождено
     * вызовом метода void close(Connection conn) throws DAOException.
     * 
     * @param userName имя пользователя
     * @param password пароль
     * @return соединение с базой данных
     * @throws DAOException в случае если подсоединиться в базе данных не
     * представляетяя возможным.
     */
    Connection getConnection(String userName, String password) throws DAOException;
    
    /**
     * Осовобождает ресурсы отведеннные под соединение.
     * @param conn соединение с базой данных полученоое с помощью метода
     * Connection getConnection() throws DAOException.
     * @throws DAOException в случае если возникают проблемы с освобождением
     * ресурсов отведенных под соединение.
     */
    void close(Connection conn) throws DAOException;
}
