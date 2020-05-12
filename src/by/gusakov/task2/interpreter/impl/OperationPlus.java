package by.gusakov.task2.interpreter.impl;

import by.gusakov.task2.interpreter.BaseOperation;
import by.gusakov.task2.interpreter.Context;

public class OperationPlus implements BaseOperation
{

    @Override
    public void interpret(Context context)
    {
        context.pushValue(context.popValue()+context.popValue());
    }

}
