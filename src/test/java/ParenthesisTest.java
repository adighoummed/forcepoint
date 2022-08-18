import org.testng.Assert;
import org.testng.annotations.Test;

public class ParenthesisTest {

    @Test
    public void testParenthesisEmpty(){
        ParenthesisValidator pv = new ParenthesisValidator();
        Assert.assertEquals(true, pv.checkBalancedParenthesis(""));
    }

    @Test
    public void testParenthesisPair(){
        ParenthesisValidator pv = new ParenthesisValidator();
        Assert.assertEquals(true, pv.checkBalancedParenthesis("()"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("[]"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("{}"));
    }

    @Test
    public void testParenthesisSingle(){
        ParenthesisValidator pv = new ParenthesisValidator();
        Assert.assertEquals(false, pv.checkBalancedParenthesis(")"));
        Assert.assertEquals(false, pv.checkBalancedParenthesis("]"));
        Assert.assertEquals(false, pv.checkBalancedParenthesis("}"));
        Assert.assertEquals(false, pv.checkBalancedParenthesis("("));
        Assert.assertEquals(false, pv.checkBalancedParenthesis("["));
        Assert.assertEquals(false, pv.checkBalancedParenthesis("{"));
    }

    @Test
    public void testParenthesisLongString(){
        ParenthesisValidator pv = new ParenthesisValidator();
        Assert.assertEquals(true, pv.checkBalancedParenthesis("()()()()()()()()()()()()()()()()()()()()()"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("[][][][][][][][][][][][][][][][][][][][][]"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}"));

        Assert.assertEquals(true, pv.checkBalancedParenthesis("{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]"));
        Assert.assertEquals(true, pv.checkBalancedParenthesis("((((((((((((((((((((()))))))))))))))))))))"));

        Assert.assertEquals(true, pv.checkBalancedParenthesis( "()()()()()()()()()()()()()()()()()()()()()" +
                                                                        "[][][][][][][][][][][][][][][][][][][][][]" +
                                                                        "{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}"));
    }

    @Test
    public void testParenthesisWithLetters(){
        ParenthesisValidator pv = new ParenthesisValidator();
        Assert.assertEquals(true, pv.checkBalancedParenthesis(  "{qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfgh" +
                "jklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyu" +
                "iopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvb" +
                "nmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfg" +
                "hjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwerty" +
                "uiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcv" +
                "bnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdf" +
                "ghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwert" +
                "yuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxc" +
                "vbnmqwertyuiopasdfghjklzxcvbnm}"));

        Assert.assertEquals(false, pv.checkBalancedParenthesis(  "{qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfgh" +
                "jklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyu" +
                "iopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvb" +
                "nmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfg" +
                "hjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwerty" +
                "uiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcv" +
                "bnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdf" +
                "ghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwert" +
                "yuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxc" +
                "vbnmqwertyuiopasdfghjklzxcvbnm}}"));

        Assert.assertEquals(false, pv.checkBalancedParenthesis(  "{{qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfgh" +
                "jklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyu" +
                "iopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvb" +
                "nmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfg" +
                "hjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwerty" +
                "uiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcv" +
                "bnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdf" +
                "ghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwert" +
                "yuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxc" +
                "vbnmqwertyuiopasdfghjklzxcvbnm}"));
    }
}
