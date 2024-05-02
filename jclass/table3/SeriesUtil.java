// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SeriesUtil.java

package jclass.table3;

import java.util.Vector;

// Referenced classes of package jclass.table3:
//            JCCellRange, JCTblEnum, Series, SeriesValue, 
//            Shift, Table, WidgetSeriesValue

class SeriesUtil
{

    static boolean setValue(Table table, Series series, JCCellRange jccellrange, Object obj)
    {
        if(!table.isLiveTable && jccellrange.start_row == -998 && jccellrange.start_column == -998)
        {
            series.setDefault(obj);
            return true;
        }
        boolean flag = false;
        JCCellRange jccellrange1 = new JCCellRange();
        cvtRangeContextToRange(table, jccellrange, jccellrange1);
        for(int i = jccellrange1.start_row; i <= jccellrange1.end_row; i++)
        {
            for(int j = jccellrange1.start_column; j <= jccellrange1.end_column; j++)
                if(series.setValue(i, j, obj))
                    flag = true;

        }

        return flag;
    }

    static void cvtRangeContextToRange(Table table, JCCellRange jccellrange, JCCellRange jccellrange1)
    {
        if(jccellrange.start_row == -998 || jccellrange.start_row == -997)
            jccellrange1.start_row = jccellrange1.end_row = jccellrange.start_row;
        else
        if(jccellrange.start_row == -1 && jccellrange.end_row == 0x7fffffff)
            jccellrange1.start_row = jccellrange1.end_row = -998;
        else
        if(jccellrange.start_row == -1)
            jccellrange1.start_row = jccellrange1.end_row = -1;
        else
        if(jccellrange.start_row == 0 && jccellrange.end_row == 0x7fffffff)
            jccellrange1.start_row = jccellrange1.end_row = -997;
        else
        if(!table.isLiveTable)
        {
            jccellrange1.start_row = Math.min(table.rows - 1, jccellrange.start_row);
            jccellrange1.end_row = Math.min(table.rows - 1, jccellrange.end_row);
        } else
        {
            jccellrange1.start_row = jccellrange.start_row;
            jccellrange1.end_row = jccellrange.end_row;
        }
        if(jccellrange.start_column == -998 || jccellrange.start_column == -997)
            jccellrange1.start_column = jccellrange1.end_column = jccellrange.start_column;
        else
        if(jccellrange.start_column == -1 && jccellrange.end_column == 0x7fffffff)
            jccellrange1.start_column = jccellrange1.end_column = -998;
        else
        if(jccellrange.start_column == -1)
            jccellrange1.start_column = jccellrange1.end_column = -1;
        else
        if(jccellrange.start_column == 0 && jccellrange.end_column == 0x7fffffff)
            jccellrange1.start_column = jccellrange1.end_column = -997;
        else
        if(!table.isLiveTable)
        {
            jccellrange1.start_column = Math.min(table.columns - 1, jccellrange.start_column);
            jccellrange1.end_column = Math.min(table.columns - 1, jccellrange.end_column);
        } else
        {
            jccellrange1.start_column = jccellrange.start_column;
            jccellrange1.end_column = jccellrange.end_column;
        }
        if(jccellrange1.start_row > jccellrange1.end_row)
        {
            int i = jccellrange1.start_row;
            jccellrange1.start_row = jccellrange1.end_row;
            jccellrange1.end_row = i;
        }
        if(jccellrange1.start_column > jccellrange1.end_column)
        {
            int j = jccellrange1.start_column;
            jccellrange1.start_column = jccellrange1.end_column;
            jccellrange1.end_column = j;
        }
    }

    static void shiftRow(Series series, int i, int j, int k)
    {
        series.last_index = -999;
        for(int l = series.size() - 1; l >= 0; l--)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(l);
            if(k > 0)
            {
                if(seriesvalue.row >= i)
                    seriesvalue.row += j;
            } else
            if(seriesvalue.row >= i && seriesvalue.row < i + j)
            {
                if(seriesvalue.value instanceof WidgetSeriesValue)
                    ((WidgetSeriesValue)seriesvalue.value).destroy();
                series.removeElementAt(l);
            } else
            if(seriesvalue.row >= i + j)
                seriesvalue.row -= j;
        }

    }

    static void shiftColumn(Series series, int i, int j, int k)
    {
        series.last_index = -999;
        for(int l = series.size() - 1; l >= 0; l--)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(l);
            if(k > 0)
            {
                if(seriesvalue.column >= i)
                    seriesvalue.column += j;
            } else
            if(seriesvalue.column >= i && seriesvalue.column < i + j)
            {
                if(seriesvalue.value instanceof WidgetSeriesValue)
                    ((WidgetSeriesValue)seriesvalue.value).destroy();
                series.removeElementAt(l);
            } else
            if(seriesvalue.column >= i + j)
                seriesvalue.column -= j;
        }

    }

    static void moveColumns(Series series, int i, int j, int k)
    {
        series.last_index = -999;
        for(int l = 0; l < series.size(); l++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(l);
            seriesvalue.column = Shift.adjust(seriesvalue.column, i, j, k);
        }

    }

    static void moveRows(Series series, int i, int j, int k)
    {
        series.last_index = -999;
        for(int l = 0; l < series.size(); l++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(l);
            seriesvalue.row = Shift.adjust(seriesvalue.row, i, j, k);
        }

    }

    static void reorderRows(Series series, int ai[])
    {
        series.last_index = -999;
        for(int i = 0; i < series.size(); i++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(i);
            if(seriesvalue.row >= 0 && seriesvalue.row < ai.length)
                seriesvalue.row = ai[seriesvalue.row];
        }

    }

    static void reorderColumns(Series series, int ai[])
    {
        series.last_index = -999;
        for(int i = 0; i < series.size(); i++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(i);
            if(seriesvalue.column >= 0 && seriesvalue.column < ai.length)
                seriesvalue.column = ai[seriesvalue.column];
        }

    }

    static void swapColumns(Series series, int i, int j)
    {
        series.last_index = -999;
        for(int k = 0; k < series.size(); k++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(k);
            seriesvalue.column = Shift.swap(seriesvalue.column, i, j);
        }

    }

    static void swapRows(Series series, int i, int j)
    {
        series.last_index = -999;
        for(int k = 0; k < series.size(); k++)
        {
            SeriesValue seriesvalue = (SeriesValue)series.elementAt(k);
            seriesvalue.row = Shift.swap(seriesvalue.row, i, j);
        }

    }

    SeriesUtil()
    {
    }
}
