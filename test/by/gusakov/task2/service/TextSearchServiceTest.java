package test.by.gusakov.task2.service;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.gusakov.task2.service.TextSearchService;

public class TextSearchServiceTest
{

    private TextSearchService ppn;
    private String lexeme;

    @BeforeClass
    public void setParam()
    {
        ppn=new TextSearchService();
    }

    @AfterClass
    public void clearParam()
    {
        ppn=null;
    }

    @Test
    public void simpleExpressionTest()
    {
        final String EXPECTED="5 3 2 * +";
        lexeme="5+3*2";
        lexeme=ppn.calcExpr(lexeme);
        Assert.assertEquals(EXPECTED,lexeme);
    }

    @Test
    public void bracketsExpressionTest()
    {
        final String EXPECTED="5 3 + 2 * 1 18 3 / * -";
        lexeme="(5+3)*2-1*(18/3)";
        lexeme=ppn.calcExpr(lexeme);
        Assert.assertEquals(EXPECTED,lexeme);
    }

    @Test
    public void formatExpressionTest()
    {
        final String EXPECTED="71 2 4 * 3 2 1 2 / 2 * - 2 * * 10 2 / - - 6";
        lexeme="((71-(2*i--*(3*(2-1/2*2)2)-10/2))++i)";
        lexeme=ppn.calcExpr(lexeme);
        Assert.assertEquals(EXPECTED,lexeme);
    }
}
