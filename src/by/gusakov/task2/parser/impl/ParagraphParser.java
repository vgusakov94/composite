package by.gusakov.task2.parser.impl;

import by.gusakov.task2.composite.Component;
import by.gusakov.task2.composite.CompositeTool;
import by.gusakov.task2.composite.PartType;
import by.gusakov.task2.parser.BaseParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements BaseParser
{
    private SentenceParser sentPar;
    final private String PARAGRAPH_REGEX="(?sm)^[^\\s]+.*?\\.\\s*$";

    public ParagraphParser(SentenceParser sentPars)
    {
        this.sentPar=sentPars;
    }

    @Override
    public void parse(Component textComposite , String text)
    {
        Pattern paragraphPattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = paragraphPattern.matcher(text);
        String[] paragraphs = text.split(PARAGRAPH_REGEX);

        for (String paragraph : paragraphs)
        {
           paragraph=paragraph.trim();
            if (matcher.find())
            {
                paragraph =paragraph+" "+matcher.group();
                paragraph = paragraph.replaceAll("\n|\r\n", " ");
            }
            if (sentPar!=null)
            {
                Component paragraphComposite=new CompositeTool(PartType.PARAGRAPH);
                sentPar.parse(paragraphComposite, paragraph);
                textComposite.add(paragraphComposite);
            }
        }
    }
}
