package test.by.gusakov.task2.action;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.gusakov.task2.action.TextOperation;
import by.gusakov.task2.composite.Component;
import by.gusakov.task2.composite.CompositeTool;
import by.gusakov.task2.composite.PartType;
import by.gusakov.task2.parser.impl.TextParser;
import by.gusakov.task2.reader.TextCreator;
import by.gusakov.task2.exception.DataFileException;
import static org.testng.AssertJUnit.fail;
import static by.gusakov.task2.action.TextOperation.removeWord;
import static by.gusakov.task2.action.TextOperation.sortSentenciesByLexemes;

public class TextOperationTest
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

    @AfterMethod
    public void clearMethodParam()
    {
        textComposite=new CompositeTool(PartType.TEXT);
    }

    @Test
    public void removeWordTest()
    {
        final String EXPECTED="\n"+"    It survived - not centuries, but also the leap into 17.0 new world.";
        try
        {
            text = textCreator.readText("test/resources/test1.txt");
            textPars.parse(textComposite, text);
            textComposite = textPars.getTextComposite();
            textComposite=removeWord(textComposite,"has");
            Assert.assertEquals(EXPECTED,textComposite.toString());
        }
        catch (DataFileException ex)
        {
            fail();
        }
    }

    @Test
    public void sortLexemesTest()
    {
        final String EXPECTED="\n"+"    - It has not but the new also leap into 17.0 world. survived centuries,";
        try
        {
            text = textCreator.readText("test/resources/test1.txt");
            textPars.parse(textComposite, text);
            textComposite = textPars.getTextComposite();
            textComposite=sortSentenciesByLexemes(textComposite);
            Assert.assertEquals(EXPECTED,textComposite.toString());
        }
        catch (DataFileException ex)
        {
            fail();
        }
    }

    @Test
    public void sortParagraphsTest()
    {
        final String EXPECTED="\n"+"    It has survived - not centuries, but also the leap into 17.0 new world.\n" +
                "    It has survived - not centuries, but also the leap into 17.0 new world. It has survived - not centuries," +
                " but also the leap into 17.0 new world.";
        try
        {
            text = textCreator.readText("test/resources/test2.txt");
            textPars.parse(textComposite, text);
            textComposite = textPars.getTextComposite();
            textComposite= TextOperation.sortParagraphsBySentences(textComposite);
            Assert.assertEquals(EXPECTED,textComposite.toString());
        }
        catch (DataFileException ex)
        {
            fail();
        }
    }

}
