package practice.generic;

import org.junit.jupiter.api.Test;
import practice.generic.exception.DuplicateKeyException;
import practice.generic.exception.KeyNotPresentException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDbTest {
    @Test
    public void shouldReturnACreatedEntityInTheDb() throws DuplicateKeyException {
        InMemoryDb<String> inMemoryDb = new InMemoryDb<>();
        String key = "key";
        String value = "value";

        inMemoryDb.create(key, value);
        Optional<String> optionalValue = inMemoryDb.get(key);

        assertTrue(optionalValue.isPresent());
        assertEquals(value, optionalValue.get());
    }

    @Test
    public void shouldReturnEmptyOptionalWhenKeyNotPresent(){
        InMemoryDb<String> inMemoryDb = new InMemoryDb<>();
        String key = "key";

        Optional<String> optionalValue = inMemoryDb.get(key);

        assertTrue(optionalValue.isEmpty());
    }

    @Test
    public void shouldThrowExceptionIfKeyAlreadyPresentInDb() throws DuplicateKeyException {
        InMemoryDb<String> inMemoryDb = new InMemoryDb<>();
        String key = "key";
        String value1 = "value1";

        inMemoryDb.create(key, value1);

        DuplicateKeyException exception = assertThrows(DuplicateKeyException.class, () -> {
            inMemoryDb.create(key, value1);
        });

        assertEquals("Key already exists", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionOnUpdateWhenKeyNotPresentInTheDb() throws KeyNotPresentException {
        InMemoryDb<String> inMemoryDb = new InMemoryDb<>();
        String key = "key";
        String value2 = "value2";

        KeyNotPresentException exception = assertThrows(KeyNotPresentException.class, () -> {
            inMemoryDb.update(key, value2);
        });

        assertEquals("Cannot update. Key not present.", exception.getMessage());
    }

    @Test
    public void shouldUpdateWhenKeyAlreadyPresentInTheDb() throws Exception, KeyNotPresentException {
        InMemoryDb<String> inMemoryDb = new InMemoryDb<>();
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";

        inMemoryDb.create(key, value1);
        inMemoryDb.update(key, value2);

        Optional<String> optionalValue = inMemoryDb.get(key);

        assertTrue(optionalValue.isPresent());
        assertEquals(value2, optionalValue.get());
    }
}