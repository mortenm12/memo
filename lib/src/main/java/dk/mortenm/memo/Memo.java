package dk.mortenm.memo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memo {

    private final List<String> entries = new ArrayList<>();

    public void add(String entry) {
        if (entry == null || entry.isBlank()) {
            throw new IllegalArgumentException("Entry must not be blank");
        }
        entries.add(entry);
    }

    public List<String> getAll() {
        return Collections.unmodifiableList(entries);
    }

    public int size() {
        return entries.size();
    }

    public void clear() {
        entries.clear();
    }
}
