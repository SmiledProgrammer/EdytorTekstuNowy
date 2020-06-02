import com.editor.PatternFinder;

import org.junit.Assert;
import org.junit.Test;

public class PatternFinderTest {

    @Test
    public void createPrefixSuffixTable() {
        PatternFinder pf = new PatternFinder();
        String pattern = "ababd abacc ababd abeee ababd";
        pf.setStrings("", pattern);
        pf.createPrefixSuffixTable();
        int[] expectedResult = { 0,0,0,1,2,0,0,1,2,3,0,0,0,1,2,3,4,5,6,7,8,0,0,0,0,1,2,3,4,5 };
        for (int i = 0; i < pf.prefixSuffixTable.length; i++)
            Assert.assertEquals(expectedResult[i], pf.prefixSuffixTable[i]);
    }

    @Test
    public void findPattern() {
        //napiszę potem
    }

}