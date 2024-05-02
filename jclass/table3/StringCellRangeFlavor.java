// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringCellRangeFlavor.java

package jclass.table3;

import java.awt.datatransfer.StringSelection;

// Referenced classes of package jclass.table3:
//            JCCellRange, JCTblEnum, Table, TableDataView

public class StringCellRangeFlavor extends StringSelection
{

    public StringCellRangeFlavor(Table table, JCCellRange jccellrange)
    {
        super(getData(table, jccellrange));
    }

    static String getData(Table table, JCCellRange jccellrange)
    {
        String s = "";
        if(jccellrange.end_row == 0x7fffffff)
        {
            jccellrange.start_row = -1;
            jccellrange.end_row = table.getNumRows() - 1;
        }
        if(jccellrange.end_column == 0x7fffffff)
        {
            jccellrange.start_column = -1;
            jccellrange.end_column = table.getNumColumns() - 1;
        }
        for(int i = jccellrange.start_row; i <= jccellrange.end_row; i++)
        {
            for(int j = jccellrange.start_column; j < jccellrange.end_column; j++)
                s = s + table.dataView.getData(i, j) + "\t";

            s = s + table.dataView.getData(i, jccellrange.end_column) + "\n";
        }

        return s;
    }
}
