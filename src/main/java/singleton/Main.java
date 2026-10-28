package singleton;

import java.util.UUID;

class ConnectionPool {
    private ConnectionPool() {
        System.out.println("Создан единственный пул соединений");
    }

    private static class Holder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool get() {
        return Holder.INSTANCE;
    }

    public String getConnection() {
        return "conn-" + UUID.randomUUID();
    }
}

class UserDao {
    private final ConnectionPool pool = ConnectionPool.get();

    public void findUsers() {
        System.out.println("UserDao: пул = "
                + System.identityHashCode(pool));
        System.out.println("UserDao: соединение = "
                + pool.getConnection());
    }
}

class OrderDao {
    private final ConnectionPool pool = ConnectionPool.get();

    public void findOrders() {
        System.out.println("OrderDao: пул = "
                + System.identityHashCode(pool));
        System.out.println("OrderDao: соединение = "
                + pool.getConnection());
    }
}

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        OrderDao orderDao = new OrderDao();

        userDao.findUsers();
        orderDao.findOrders();

        ConnectionPool first = ConnectionPool.get();
        ConnectionPool second = ConnectionPool.get();

        System.out.println("\nОдин и тот же экземпляр пула: "
                + (first == second));
    }
}