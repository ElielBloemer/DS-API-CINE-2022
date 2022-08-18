package test;

import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() { assert (entityManager() != null); };
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
    }

}