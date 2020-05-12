package by.gusakov.task2.interpreter.impl;

import by.gusakov.task2.interpreter.BaseOperation;
import by.gusakov.task2.interpreter.Context;

public class OperationNumber implements BaseOperation
{
    private double number;

    public OperationNumber(double number)
    {
        this.number=number;
    }

    @Override
    public void interpret(Context context)
    {
        context.pushValue(number);
    }
}
