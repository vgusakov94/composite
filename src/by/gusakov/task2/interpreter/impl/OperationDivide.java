package by.gusakov.task2.interpreter.impl;

import by.gusakov.task2.interpreter.BaseOperation;
import by.gusakov.task2.interpreter.Context;


public class OperationDivide implements BaseOperation
{
    @Override
    public void interpret(Context context)
    {
        Double second = context.popValue();
        Double first = context.popValue();
        context.pushValue(first / second);
    }

}
