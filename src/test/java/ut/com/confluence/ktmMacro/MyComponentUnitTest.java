package ut.com.confluence.ktmMacro;

import org.junit.Test;
import com.confluence.ktmMacro.api.MyPluginComponent;
import com.confluence.ktmMacro.impl.MyPluginComponentImpl;

import static org.junit.Assert.*;

public class MyComponentUnitTest {

    @Test
    public void componentLaunchTest() {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }

    @Test
    public void selectTextTest() {
        assertTrue(true);
    }

    @Test
    public void accessСontrolTest() {
        assertTrue(true);
    }

    @Test
    public void selectLabelTest() {
        assertTrue(true);
    }

    @Test
    public void selectDifficultyLevel() {
        assertTrue(true);
    }

    @Test
    public void userInputControl() {
        assertTrue(true);
    }

}