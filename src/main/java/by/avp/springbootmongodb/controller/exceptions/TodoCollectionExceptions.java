package by.avp.springbootmongodb.controller.exceptions;

public class TodoCollectionExceptions extends Exception {
    public static final long serialVersionUID = 1L;

    public TodoCollectionExceptions(String message) {
        super(message);
    }

    public static String notFoundException(String id) {
        return "Todo with " + id + " not found";
    }

    public static String todoAlreadyExists() {
        return "Todo with given name already exists";
    }
}
