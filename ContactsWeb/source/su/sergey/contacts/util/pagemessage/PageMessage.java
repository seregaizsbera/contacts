package su.sergey.contacts.util.pagemessage;

/**
 * �������� ��������� ��� ������ �� ��������
 *
 * Author: 
 * Date: 22.08.2002
 * */
public class PageMessage {
    /*������ ���������*/
    private String message;

    /**
     * �����������
     *
     * @param message ������ ���������
     * */
    public PageMessage(String message) {

        this.message = message;
    }

    /**
     * ���������� ������ ���������
     *
     * @return ������ ���������
     * */
    public String getMessage() {

        return message;
    }
}
