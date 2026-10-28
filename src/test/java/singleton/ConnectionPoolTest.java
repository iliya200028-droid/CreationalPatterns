package singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void returnsSamePoolInstance() {
        ConnectionPool first = ConnectionPool.get();
        ConnectionPool second = ConnectionPool.get();

        assertSame(first, second);
        assertEquals(
                System.identityHashCode(first),
                System.identityHashCode(second)
        );
    }

    @Test
    void daoCallsKeepSamePoolInstance() {
        ConnectionPool before = ConnectionPool.get();

        new UserDao().findUsers();
        new OrderDao().findOrders();

        assertSame(before, ConnectionPool.get());
    }
}