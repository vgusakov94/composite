package by.gusakov.task2.parser.impl;

import org.apache.logging.log4j.Level;
import by.gusakov.task2.composite.Component;
import by.gusakov.task2.composite.CompositeTool;
import by.gusakov.task2.composite.PartType;
import by.gusakov.task2.parser.BaseParser;
import by.gusakov.task2.exception.IncorrectDataException;
import by.gusakov.task2.interpreter.ExpressionCalculator;
import by.gusakov.task2.service.TextSearchService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.gusakov.task2.reader.TextCreator.LOGGER;

public class LexemeParser implements BaseParser
{
    private SymbolParser symbPar;
    final String LEXEME_REGEX="\\s";
    final String MATH_EXPRESSION_REGEX="(--)|(\\+\\+)|(\\d)|([+*/])";

    public LexemeParser(SymbolParser symbPar)
    {
        this.symbPar = symbPar;
    }

    @Override
    public void parse(Component sentenceComposite,String sentence)
    {
        ExpressionCalculator expCalc;
        Component lexemeComposite;
        TextSearchService ppn=new TextSearchService();
        Pattern pattern = Pattern.compile(MATH_EXPRESSION_REGEX);
        String[] lexems =sentence.split(LEXEME_REGEX);
        for (String lexeme : lexems)
        {
            Matcher matcher = pattern.matcher(lexeme);
            if (matcher.find())
            {
                try
                {
                    lexeme = ppn.calcExpr(lexeme);
                    expCalc = new ExpressionCalculator();
                    expCalc.addOperations(lexeme);
                    lexeme = expCalc.calcExpression();
                    lexemeComposite = new CompositeTool(PartType.LEXEME);
                    symbPar.parse(lexemeComposite, lexeme);
                    sentenceComposite.add(lexemeComposite);
                }
                catch (IncorrectDataException ex)
                {
                    LOGGER.log(Level.ERROR,ex.getMessage());
                }
            }
            else
            {
                lexemeComposite=new CompositeTool(PartType.LEXEME);
                symbPar.parse(lexemeComposite,lexeme);
                sentenceComposite.add(lexemeComposite);
            }
        }
    }

}
