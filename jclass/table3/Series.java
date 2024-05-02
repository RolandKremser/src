// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Series.java

package jclass.table3;

import java.awt.Component;
import java.util.Vector;
import jclass.cell.CellEditor;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCTblEnum, SeriesValue, Table, WidgetSeriesValue

class Series extends JCVector
{

    Series()
    {
        last_index = -999;
        is_editor_series = false;
    }

    Series(Table table)
    {
        last_index = -999;
        is_editor_series = false;
        table.series_list.addElement(this);
    }

    Series(boolean flag, Table table)
    {
        this(table);
        is_editor_series = flag;
    }

    Series(Table table, Object obj)
    {
        last_index = -999;
        is_editor_series = false;
        setDefault(obj);
        table.series_list.addElement(this);
    }

    Series(Table table, int i)
    {
        this(table, new Integer(i));
    }

    Series(Table table, boolean flag)
    {
        this(table, new Boolean(flag));
    }

    Series(boolean flag, Table table, Object obj)
    {
        this(table, obj);
        is_editor_series = flag;
    }

    Series(boolean flag, Table table, int i)
    {
        this(flag, table, new Integer(i));
    }

    Series(boolean flag, Table table, boolean flag1)
    {
        this(flag, table, new Boolean(flag1));
    }

    private static boolean eqCell(Object obj, int i, int j)
    {
        return obj != null && ((SeriesValue)obj).row == i && ((SeriesValue)obj).column == j;
    }

    private boolean eqCell(int i, int j, int k)
    {
        SeriesValue seriesvalue = (SeriesValue)elementAt(i);
        return (seriesvalue.row == j || seriesvalue.row == -998 || j >= 0 && seriesvalue.row == -997) && (seriesvalue.column == k || seriesvalue.column == -998 || k >= 0 && seriesvalue.column == -997);
    }

    int findExact(int i, int j)
    {
        int k;
        if((k = last_index) != -999 && eqCell(elementAt(k), i, j))
            return k;
        for(int l = size() - 1; l >= 0; l--)
            if(eqCell(elementAt(l), i, j))
                return l;

        return -999;
    }

    boolean setValue(int i, int j, int k)
    {
        return setValue(i, j, new Integer(k));
    }

    boolean setValue(int i, int j, boolean flag)
    {
        return setValue(i, j, new Boolean(flag));
    }

    boolean setValue(int i, int j, Object obj)
    {
        if(i == -1 && j == -1)
        {
            boolean flag = setValue(-998, -1, obj);
            boolean flag1 = setValue(-1, -998, obj);
            return flag & flag1;
        }
        int k = findExact(i, j);
        if((obj instanceof Component) && (!is_editor_series || !(obj instanceof CellEditor)))
            obj = new WidgetSeriesValue((Component)obj);
        if(k >= 0 && k < size())
        {
            SeriesValue seriesvalue = (SeriesValue)elementAt(k);
            if(k < size() - 1)
            {
                Object obj1 = elementAt(k);
                removeElementAt(k);
                addElement(obj1);
            } else
            if(seriesvalue.eqValue(obj))
                return false;
            SeriesValue seriesvalue1 = (SeriesValue)elementAt(size() - 1);
            seriesvalue1.value = obj;
        } else
        {
            addElement(new SeriesValue(i, j, obj));
        }
        last_index = -999;
        return true;
    }

    void remove(int i, int j)
    {
        int k = findExact(i, j);
        if(k != -999)
            removeAt(k);
    }

    void removeAt(int i)
    {
        if(i < size())
        {
            removeElementAt(i);
            last_index = -999;
        }
    }

    int find(int i, int j)
    {
        int k = last_index;
        if(k != -999 && k < size())
        {
            SeriesValue seriesvalue = (SeriesValue)elementAt(k);
            if(seriesvalue.row == i && seriesvalue.column == j)
                return k;
        }
        for(int l = size() - 1; l >= 0; l--)
            if(eqCell(l, i, j))
            {
                last_index = l;
                return l;
            }

        return last_index = -999;
    }

    void setDefault(Object obj)
    {
        last_index = find(-998, -998);
        if(last_index == -999)
        {
            insertElementAt(new SeriesValue(-998, -998, obj), 0);
            return;
        } else
        {
            ((SeriesValue)elementAt(last_index)).value = obj;
            return;
        }
    }

    Object getDefault()
    {
        return getValue(-998, -998);
    }

    boolean containsValue(Object obj)
    {
        for(int i = 0; i < size(); i++)
            if(((SeriesValue)elementAt(i)).value.equals(obj))
                return true;

        return false;
    }

    Object getSingleValue()
    {
        if(size() == 0)
            return null;
        Object obj = ((SeriesValue)elementAt(0)).value;
        for(int i = 1; i < size(); i++)
            if(!((SeriesValue)elementAt(i)).value.equals(obj))
                return null;

        return obj;
    }

    Object getValue(int i)
    {
        if(i >= size())
            return null;
        else
            return ((SeriesValue)elementAt(i)).value;
    }

    Object getValue(int i, int j)
    {
        int k = find(i, j);
        if(k == -999)
            return null;
        else
            return ((SeriesValue)elementAt(k)).value;
    }

    int getIntValue(int i, int j)
    {
        return ((Integer)getValue(i, j)).intValue();
    }

    boolean getBooleanValue(int i, int j)
    {
        return ((Boolean)getValue(i, j)).booleanValue();
    }

    int last_index;
    boolean is_editor_series;
}
