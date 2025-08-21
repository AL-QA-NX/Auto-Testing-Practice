package lebedev.auto.testing.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PointTests {
    PointTask3 p = new PointTask3(8,4,6,3);

    @Test
    @DisplayName("Проверка что > 0")
    public void testArea1 () {
        assertTrue(p.distance() > 0);
    }

    @Test
    @DisplayName("Проверка что != 100")
    public void testArea2 (){
        assertNotEquals(100, p.distance());
    }
}
