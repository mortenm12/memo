package dk.tinker.memo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemoTest {

    @Test
    void addAndRetrieve() {
        Memo memo = new Memo();
        memo.add("Hello");
        assertEquals(1, memo.size());
        assertEquals("Hello", memo.getAll().get(0));
    }

    @Test
    void blankEntryThrows() {
        Memo memo = new Memo();
        assertThrows(IllegalArgumentException.class, () -> memo.add("  "));
    }

    @Test
    void clearEmptiesList() {
        Memo memo = new Memo();
        memo.add("A");
        memo.clear();
        assertEquals(0, memo.size());
    }
}
