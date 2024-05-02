// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCValidateCellEvent.java

package jclass.table3;


// Referenced classes of package jclass.table3:
//            TableAWTEvent, JCTable

public class JCValidateCellEvent extends TableAWTEvent
{

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public boolean getChanged()
    {
        return changed;
    }

    public int getDataType()
    {
        return datatype;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object obj)
    {
        value = obj;
    }

    public boolean getAllowValueChange()
    {
        return doit;
    }

    public void setAllowValueChange(boolean flag)
    {
        doit = flag;
    }

    public JCValidateCellEvent(JCTable jctable)
    {
        super(jctable, 5010);
        doit = true;
    }

    public static final int VALIDATE_CELL = 5010;
    int row;
    int column;
    boolean changed;
    int datatype;
    Object value;
    boolean doit;
}
