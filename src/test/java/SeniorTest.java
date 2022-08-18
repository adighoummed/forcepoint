import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SeniorTest {

    @Test
    public void countLines() throws IOException {
        Assert.assertEquals(SeniorExam.countLines(), 98820);
    }

    @Test
    public void countHostname() throws IOException{
        Assert.assertEquals(SeniorExam.countHostnames("Information Technology"), 99);
    }

    @Test
    public void countCategories() throws IOException{
        Assert.assertEquals(SeniorExam.countCategories("activity.windows.com"), 2);
    }
}
