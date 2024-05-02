// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SeriesValue.java

package jclass.table3;

import java.io.Serializable;

class SeriesValue
    implements Serializable
{

    SeriesValue(int i, int j, Object obj)
    {
        row = i;
        column = j;
        value = obj;
    }

    boolean eqValue(Object obj)
    {
        if(value != null)
            if(obj != null)
                return value.equals(obj);
            else
                return false;
        return obj == null;
    }

    int row;
    int column;
    Object value;
}
