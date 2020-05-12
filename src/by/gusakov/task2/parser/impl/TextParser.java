package by.gusakov.task2.parser.impl;

import by.gusakov.task2.composite.Component;
import by.gusakov.task2.parser.BaseParser;

public class TextParser implements BaseParser
{
    private SymbolParser symbPar;
    private LexemeParser lexPar;
    private SentenceParser sentPar;
    private ParagraphParser parPars;
    Component textComposite;

    public TextParser()
    {
        symbPar=new SymbolParser();
        lexPar=new LexemeParser(symbPar);
        sentPar=new SentenceParser(lexPar);
        parPars=new ParagraphParser(sentPar);
    }

    public Component getTextComposite()
    {return textComposite;}

    @Override
    public void parse(Component textComposite ,String text)
    {
        if (parPars!=null)
        {
            parPars.parse(textComposite,text);
        }
        this.textComposite=textComposite;
    }

}

