package su.sergey.contacts.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public final class Roles implements RoleNames {
	private static Roles instance = null;
	
	private final Map roles;
	
	private Roles() {
		roles = new HashMap();
		roles.put(SERGEY, "������ ����");
		roles.put(VIEWER, "������������ � ������� �� ������������ ��������");
		roles.put(EDITOR, "������������ � ������� �� ������������ �������������� ������");
		roles.put(SERVER, "������� ����������");
		roles.put(ALL_AUTHENTICATED, "�����, ��� ��������� ���� ������");
		roles.put(EVERYONE, "��� ������");
		roles.put(DENY_ALL, "����������� �.");
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
