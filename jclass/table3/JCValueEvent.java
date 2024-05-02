// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCValueEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, JCTable

public class JCValueEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object obj)
    {
        value = obj;
    }

    public boolean getStore()
    {
        return store;
    }

    public void setStore(boolean flag)
    {
        store = flag;
    }

    public JCValueEvent(JCTable jctable, int i, int j)
    {
        super(jctable, 5011);
        store = false;
        row = i;
        column = j;
    }

    public static final int VALUE = 5011;
    int row;
    int column;
    Object value;
    boolean store;
}
