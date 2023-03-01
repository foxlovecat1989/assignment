package assignment1;


import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;


public class RoundTest {
    @Test
    public void testGetterAndConstructor() {
        assertPojoMethodsForAll(Round.class)
                .quickly()
                .testing(Method.CONSTRUCTOR)
                .testing(Method.GETTER)
                .areWellImplemented();
    }
}




