package su.sergey.contacts.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public final class Roles implements RoleNames {
	private static Roles instance = null;
	
	private final Map roles;
	
	private Roles() {
		roles = new HashMap();
		roles.put(SERGEY, "Хозяин базы");
		roles.put(VIEWER, "Пользователь с правами на ограниченный просмотр");
		roles.put(EDITOR, "Пользователь с правами на ограниченное редактирование данных");
		roles.put(SERVER, "Серевер приложений");
		roles.put(ALL_AUTHENTICATED, "Любой, кто правильно ввел пароль");
		roles.put(EVERYONE, "Кто попало");
		roles.put(DENY_ALL, "Посторонним В.");
	}
	
	public Collection getRoleNames() {
		return roles.keySet();
	}
	
	public String getRoleDescription(String roleName) {
		return (String) roles.get(roleName);
	}

	public static Roles getInstance() {
		if (instance == null) {
			instance = new Roles();
		}
		return instance;
	}
}
