package su.sergey.contacts.person.dao;

import java.util.ArrayList;
import java.util.List;

import su.sergey.contacts.person.searchparamters.PersonSearchParameters;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;

public class PersonSearchDAO extends AbstractDAO {
	private static PersonSearchDAO instance;

	/**
	 * Constructor for PersonSearchDAO
	 */
	private PersonSearchDAO() {}
	
	public List find(PersonSearchParameters searchParameters, int start, int length) throws DAOException {
		return new ArrayList();
	}

	public long count(PersonSearchParameters searchParameters) throws DAOException {
		return 0;
	}

	public static PersonSearchDAO getInstance() {
		if (instance == null) {
			instance = new PersonSearchDAO();
		}
		return instance;
	}
}

