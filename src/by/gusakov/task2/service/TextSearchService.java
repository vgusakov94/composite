package by.gusakov.task2.service;

import org.apache.logging.log4j.Level;
import java.util.ArrayDeque;
import static by.gusakov.task2.reader.TextCreator.LOGGER;


public class TextSearchService
{
    private final int i=5;
    private final int j=17;
    private final String NUMBER = "[0-9]";
    private final String SYMBOL = "[^0-9)]";
    private final String INC_I = "(\\+\\+i)|(i\\+\\+)";
    private final String DEC_I = "(--i)|(i--)";
    private final String INC_J = "(\\+\\+j)|(j\\+\\+)";
    private final String DEC_J = "(--j)|(j--)";


    public String calcExpr(String mathExpr)
    {
        mathExpr = formatExpression(mathExpr);
        StringBuilder resultStr = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int index = 0; index < mathExpr.length(); index++)
        {
            char symbol = mathExpr.charAt(index);
            if (String.valueOf(symbol).matches(NUMBER))
            {
                resultStr.append(" ");
                resultStr.append(symbol);
                int chainOfNumbers = 0;
                for (int newIndex = index + 1; newIndex < mathExpr.length(); newIndex++)
                {
                    char nextSymbol = mathExpr.charAt(newIndex);
                    if (String.valueOf(nextSymbol).matches(NUMBER))
                    {
                        chainOfNumbers++;
                        resultStr.append(nextSymbol);
                    }
                    else
                    {
                        break;
                    }
                }
                index += chainOfNumbers;
            }
            else if (symbol == '(')
            {
                stack.push(symbol);
            }
            else if (symbol == ')')
            {
                while (true)
                {
                    char symbolFromStack = stack.pop();
                    if (symbolFromStack == '(')
                    {
                        break;
                    }
                    else
                    {
                        resultStr.append(" ");
                        resultStr.append(symbolFromStack);
                    }
                }
            } else
                {
                if (stack.isEmpty())
                {
                    stack.push(symbol);
                } else
                    {
                    int priorityOfSymbol = getSymbolPriority(symbol);
                    while (true)
                    {
                        int priorityOfSymbolFromStack = getSymbolPriority(stack.getFirst());

                        if (priorityOfSymbol > priorityOfSymbolFromStack)
                        {
                            stack.push(symbol);
                            break;
                        } else
                            {
                            resultStr.append(" ");
                            resultStr.append(stack.pop());
                        }
                        if (stack.isEmpty())
                        {
                            stack.push(symbol);
                            break;
                        }
                    }
                }
            }
        }
        while (stack.size() > 0)
        {
            resultStr.append(" ");
            resultStr.append(stack.pop());
        }
        LOGGER.log(Level.INFO,"Expression converted: "+resultStr.toString().trim());
        return resultStr.toString().trim();
    }

    public String formatExpression(String mathExpr)
    {
        mathExpr = mathExpr.replaceAll(INC_I, String.valueOf(i+1));
        mathExpr = mathExpr.replaceAll(DEC_I, String.valueOf(i-1));
        mathExpr = mathExpr.replaceAll(INC_J, String.valueOf(j+1));
        mathExpr = mathExpr.replaceAll(DEC_J, String.valueOf(j-1));
        mathExpr = String.join("", mathExpr.split("\\s"));
        StringBuilder strBld = new StringBuilder();
        for (int index = 0; index < mathExpr.length(); index++)
        {
            char symb = mathExpr.charAt(index);
            if (symb == '-')
            {
                if (index == 0)
                {
                    strBld.append(0);
                    strBld.append(symb);
                }
                else
                {
                    char preSymbol = mathExpr.charAt(index - 1);
                    if (String.valueOf(preSymbol).matches(SYMBOL))
                    {
                        strBld.append(0);
                        strBld.append(symb);
                    }
                    else
                    {
                        strBld.append(symb);
                    }
                }
            }
            else
            {
                strBld.append(symb);
            }
        }
        return strBld.toString();
    }

 private int getSymbolPriority(char operation)
    {
        switch (operation)
        {
            case '/':
                return 2;
            case '*':
                return 2;
            case '-':
                return 1;
            case '+':
                return 1;
            case '(':
                return 0;
            default:
                return 0;
        }
    }

}
