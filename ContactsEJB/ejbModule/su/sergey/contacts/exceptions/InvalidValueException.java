package su.sergey.contacts.exceptions;

public class InvalidValueException extends Exception {
        /**
         * �������� ����������� ��������� ���������
         * */
        private String paramName;

        /**
         * ������� ����� ����������
         * */
        public InvalidValueException(String message, String paramName) {
            super(message);

            this.paramName = paramName;
        }

        /**
         * ���������� �������� ����������� ��������� ���������
         * */
        public String getParamName() {

            return paramName;
        }
}
