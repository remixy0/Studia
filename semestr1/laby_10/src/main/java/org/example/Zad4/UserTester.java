package org.example.Zad4;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class UserTester {
    @Test
    public void testUsername() throws UserClassException {
        Throwable exception = assertThrows(UserClassException.class, () -> {
            new User("","marek@onet.pl",22);
        });
    }

    @Test
    public void testEmail() throws UserClassException {
        Throwable exception = assertThrows(UserClassException.class, () -> {
            new User("remixy0","marek",22);
        });
    }

    @Test
    public void testAge() throws UserClassException {
        Throwable exception = assertThrows(UserClassException.class, () -> {
            new User("marek","marek@onet.pl",-1);
        });
    }
}
