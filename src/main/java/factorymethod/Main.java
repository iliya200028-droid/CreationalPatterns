package factorymethod;

interface Notification {
    void send(String text);
}

class EmailNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[EMAIL] " + text);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[SMS] " + text);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[PUSH] " + text);
    }
}

abstract class Notifier {
    public abstract Notification createNotification();

    public void notifyUser(String text) {
        Notification notification = createNotification();
        notification.send(text);
    }
}

class EmailNotifier extends Notifier {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsNotifier extends Notifier {
    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}

class PushNotifier extends Notifier {
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите канал: email, sms или push");
            return;
        }

        Notifier notifier;

        switch (args[0].toLowerCase(java.util.Locale.ROOT)) {
            case "email":
                notifier = new EmailNotifier();
                break;
            case "sms":
                notifier = new SmsNotifier();
                break;
            case "push":
                notifier = new PushNotifier();
                break;
            default:
                System.out.println("Неизвестный канал: " + args[0]);
                return;
        }

        notifier.notifyUser("Hello!");
    }
}