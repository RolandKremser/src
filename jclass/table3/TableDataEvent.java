// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableDataEvent.java

package jclass.table3;

import java.util.EventObject;

public class TableDataEvent extends EventObject
{

    public TableDataEvent(Object obj, int i, int j, int k, int l, int i1)
    {
        super(obj);
        row = i;
        column = j;
        num_affected = k;
        destination = l;
        command = i1;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public int getNumAffected()
    {
        return num_affected;
    }

    public int getDestination()
    {
        return destination;
    }

    public int getCommand()
    {
        return command;
    }

    public static final int CHANGE_VALUE = 1;
    public static final int CHANGE_ROW = 2;
    public static final int ADD_ROW = 3;
    public static final int REMOVE_ROW = 4;
    public static final int CHANGE_COLUMN = 5;
    public static final int ADD_COLUMN = 6;
    public static final int REMOVE_COLUMN = 7;
    public static final int CHANGE_ROW_LABEL = 8;
    public static final int CHANGE_COLUMN_LABEL = 9;
    public static final int MOVE_ROW = 10;
    public static final int MOVE_COLUMN = 11;
    public static final int NUM_ROWS = 12;
    public static final int NUM_COLUMNS = 13;
    public static final int RESET = 14;
    int row;
    int column;
    int num_affected;
    int destination;
    int command;
}
