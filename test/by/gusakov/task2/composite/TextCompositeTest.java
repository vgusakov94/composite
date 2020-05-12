package test.by.gusakov.task2.composite;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.gusakov.task2.composite.Component;
import by.gusakov.task2.composite.CompositeTool;
import by.gusakov.task2.composite.PartType;
import by.gusakov.task2.parser.impl.TextParser;
import by.gusakov.task2.reader.TextCreator;
import by.gusakov.task2.exception.DataFileException;
import static org.testng.AssertJUnit.fail;

public class TextCompositeTest
{
    private TextCreator textCreator;
    private TextParser textPars;
    private Component textComposite;
    String text;

    @BeforeClass
    public void setParam()
    {
        textCreator=new TextCreator();
        textPars = new TextParser();
        textComposite = new CompositeTool(PartType.TEXT);
    }

    @AfterClass
    public void clearParam()
    {
        textCreator=null;
        textPars=null;
        textComposite=null;
    }

    @Test
    public void simpleTextTest()
    {
        final String EXPECTED="\n"+"    It has survived - not centuries, but also the leap into 17.0 new world.";
        try
        {
            text = textCreator.readText("test/resources/test1.txt");
            textPars.parse(textComposite, text);
            textComposite = textPars.getTextComposite();
            Assert.assertEquals(EXPECTED,textComposite.toString());
        }
        catch (DataFileException ex)
        {
            fail();
        }
    }


}
