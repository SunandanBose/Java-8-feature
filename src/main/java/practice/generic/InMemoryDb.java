package practice.generic;

import practice.generic.exception.DuplicateKeyException;
import practice.generic.exception.KeyNotPresentException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDb<K> {

    public Map<String, K> db = new ConcurrentHashMap<>();

    // Create can become upsert
    public void create(String key, K value) throws DuplicateKeyException {

        if (db.containsKey(key)) {
            throw new DuplicateKeyException("Key already exists");
        }
        db.put(key, value);
    }

    public Optional<K> get(String key) {
        return Optional.ofNullable(db.get(key));
    }

    public synchronized void update(String key, K value) throws KeyNotPresentException {
        if (!db.containsKey(key)) {
            throw new KeyNotPresentException("Cannot update. Key not present.");
        }
        db.put(key, value);
    }

    public void delete(String key) {
    }

}
