// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableDataSupport.java

package jclass.table3;

import java.io.Serializable;
import java.util.Enumeration;
import jclass.util.JCListenerList;

// Referenced classes of package jclass.table3:
//            TableData, TableDataEvent, TableDataListener

public abstract class TableDataSupport
    implements TableData, Serializable
{

    public synchronized void addTableDataListener(TableDataListener tabledatalistener)
    {
        listeners = JCListenerList.add(listeners, tabledatalistener);
    }

    public synchronized void removeTableDataListener(TableDataListener tabledatalistener)
    {
        listeners = JCListenerList.remove(listeners, tabledatalistener);
    }

    public void fireTableDataEvent(TableDataEvent tabledataevent)
    {
        Enumeration enumeration = JCListenerList.elements(listeners);
        synchronized(this)
        {
            TableDataListener tabledatalistener;
            for(; enumeration.hasMoreElements(); tabledatalistener.dataChanged(tabledataevent))
                tabledatalistener = (TableDataListener)enumeration.nextElement();

        }
    }

    public void fireValueChanged(int i, int j)
    {
        fireTableDataEvent(new TableDataEvent(this, i, j, 0, 0, 1));
    }

    public void fireRowChanged(int i)
    {
        fireTableDataEvent(new TableDataEvent(this, i, 0, 0, 0, 2));
    }

    public void fireRowsAdded(int i, int j)
    {
        fireTableDataEvent(new TableDataEvent(this, i, 0, j, 0, 3));
    }

    public void fireRowDeleted(int i, int j)
    {
        fireTableDataEvent(new TableDataEvent(this, i, 0, j, 0, 4));
    }

    public void fireColumnChanged(int i)
    {
        fireTableDataEvent(new TableDataEvent(this, 0, i, 0, 0, 5));
    }

    public void fireColumnsAdded(int i, int j)
    {
        fireTableDataEvent(new TableDataEvent(this, 0, i, j, 0, 6));
    }

    public void fireColumnsDeleted(int i, int j)
    {
        fireTableDataEvent(new TableDataEvent(this, 0, i, j, 0, 7));
    }

    public void fireRowLabelChanged(int i)
    {
        fireTableDataEvent(new TableDataEvent(this, i, 0, 0, 0, 8));
    }

    public void fireColumnLabelChanged(int i)
    {
        fireTableDataEvent(new TableDataEvent(this, 0, i, 0, 0, 9));
    }

    public void fireRowsMoved(int i, int j, int k)
    {
        fireTableDataEvent(new TableDataEvent(this, i, 0, j, k, 10));
    }

    public void fireColumnsMoved(int i, int j, int k)
    {
        fireTableDataEvent(new TableDataEvent(this, 0, i, j, k, 11));
    }

    public void fireNumRowsChanged()
    {
        fireTableDataEvent(new TableDataEvent(this, 0, 0, 0, 0, 12));
    }

    public void fireNumColumnsChanged()
    {
        fireTableDataEvent(new TableDataEvent(this, 0, 0, 0, 0, 13));
    }

    public void fireDataReset()
    {
        fireTableDataEvent(new TableDataEvent(this, 0, 0, 0, 0, 14));
    }

    public boolean hasListeners()
    {
        return listeners != null;
    }

    public void dispose()
    {
    }

    public TableDataSupport()
    {
    }

    public abstract Object getTableDataItem(int i, int j);

    public abstract int getNumRows();

    public abstract int getNumColumns();

    public abstract Object getTableRowLabel(int i);

    public abstract Object getTableColumnLabel(int i);

    JCListenerList listeners;
}
