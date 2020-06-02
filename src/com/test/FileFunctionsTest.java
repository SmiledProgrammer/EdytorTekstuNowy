
import com.editor.FileFunctions;

import org.junit.*;
import javax.swing.*;
import com.editor.NotepadWindow;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class FileFunctionsTest {

    String input;
    public FileFunctionsTest(String input) {
        this.input = input;
    }


    @Parameterized.Parameters(name = "Run {index}: input ={0}")
    public static Iterable<Object[]> data() throws Throwable
    {
        return Arrays.asList(new Object[][] {
                { "blabla"},
                { ""},
                { "kfdshkfsdkjfhjsdhfsdj"},
                { "blabla"},
                { ""},
                { "kfdshkfsdkjfhjsdhfsdj"},
        });
    }

    @Test
    public void appendStringTest() throws BadLocationException {
        // arrange
        NotepadWindow window = new NotepadWindow();
        FileFunctions sut = new FileFunctions(window);
        JTextPane textPane = new JTextPane();

        String str =  input;

        //act
        String expected = str;
        FileFunctions.appendString(textPane, str);

        //assert
        StyledDocument doc = (StyledDocument)textPane.getDocument();
        String actual = doc.getText(0,str.length());
        assertEquals(actual,expected);
    }
}