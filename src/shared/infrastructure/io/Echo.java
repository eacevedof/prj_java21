package shared.infrastructure.io;

public final class Echo {

    public static Echo getInstance() {
        return (new Echo());
    }

    public static void console(String message) {
        System.out.println(message);
    }

}
