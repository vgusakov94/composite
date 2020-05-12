package by.gusakov.task2.parser.impl;

import by.gusakov.task2.composite.Component;
import by.gusakov.task2.composite.Leaf;
import by.gusakov.task2.composite.PartType;
import by.gusakov.task2.parser.BaseParser;


public class SymbolParser implements BaseParser
{
    @Override
    public void parse(Component lexemeComposite,String lexeme)
    {
        for (int i = 0; i < lexeme.length(); i++)
        {
            String s = String.valueOf(lexeme.charAt(i));
            if (s.matches("\\w"))
            {
                Component leaf=new Leaf(s.charAt(0),PartType.LETTER);
                lexemeComposite.add(leaf);
            }
            else
            {
                Component leaf=new Leaf(s.charAt(0),PartType.SYMBOL);
                lexemeComposite.add(leaf);
            }
        }
    }

}
