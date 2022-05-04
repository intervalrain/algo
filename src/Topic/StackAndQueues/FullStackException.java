package src.Topic.StackAndQueues;

public class FullStackException extends Exception{
    public FullStackException() {
        super();
    }

    public FullStackException(String message) {
        super(message);
    }

    public FullStackException(Throwable cause) {
        super(cause);
    }

    public FullStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullStackException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
