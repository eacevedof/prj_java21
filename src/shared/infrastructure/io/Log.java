package shared.infrastructure.io;

public final class Log {

    public Log() {}

    public static Log getInstance() {
        return (new Log());
    }

    public static void console(String message) {
        System.out.println(message);
    }

}
