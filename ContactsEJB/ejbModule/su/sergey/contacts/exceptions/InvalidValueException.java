package su.sergey.contacts.exceptions;

public class InvalidValueException extends Exception {
        /**
         * Название неправильно заданного параметра
         * */
        private String paramName;

        /**
         * Создаёт новое исключение
         * */
        public InvalidValueException(String message, String paramName) {
            super(message);

            this.paramName = paramName;
        }

        /**
         * Возвращает название неправильно заданного параметра
         * */
        public String getParamName() {

            return paramName;
        }
}
