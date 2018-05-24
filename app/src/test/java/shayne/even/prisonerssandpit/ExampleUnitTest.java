package shayne.even.prisonerssandpit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void countIterations() {
        int stateCount = 0;

        for (int i = 0; i < 10; i++) {
            stateCount += Math.pow(4, i);
        }



    }
}