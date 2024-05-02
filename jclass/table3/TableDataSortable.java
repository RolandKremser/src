// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableDataSortable.java

package jclass.table3;

import java.util.Calendar;
import java.util.Date;
import jclass.util.JCSortable;
import jclass.util.JCString;

public class TableDataSortable
    implements JCSortable
{

    public long compare(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            return (long)EQUAL;
        if((obj instanceof String) && (obj1 instanceof String))
            return (long)((String)obj).compareTo((String)obj1);
        if((obj instanceof Integer) && (obj1 instanceof Integer))
        {
            int i = ((Integer)obj).intValue();
            int j = ((Integer)obj1).intValue();
            if(i < j)
                return (long)LESS_THAN;
            if(i == j)
                return (long)EQUAL;
            else
                return (long)GREATER_THAN;
        }
        if((obj instanceof Boolean) && (obj1 instanceof Boolean))
        {
            boolean flag = ((Boolean)obj).booleanValue();
            boolean flag1 = ((Boolean)obj1).booleanValue();
            if(flag && flag1)
                return (long)EQUAL;
            if(!flag && flag1)
                return (long)LESS_THAN;
            else
                return (long)GREATER_THAN;
        }
        if((obj instanceof Date) && (obj1 instanceof Date))
        {
            long l = ((Date)obj).getTime();
            long l1 = ((Date)obj1).getTime();
            if(l < l1)
                return (long)LESS_THAN;
            if(l == l1)
                return (long)EQUAL;
            else
                return (long)GREATER_THAN;
        }
        if((obj instanceof Double) && (obj1 instanceof Double))
        {
            double d = ((Double)obj).doubleValue();
            double d1 = ((Double)obj1).doubleValue();
            if(d < d1)
                return (long)LESS_THAN;
            if(d == d1)
                return (long)EQUAL;
            else
                return (long)GREATER_THAN;
        }
        if((obj instanceof Float) && (obj1 instanceof Float))
        {
            float f = ((Float)obj).floatValue();
            float f1 = ((Float)obj1).floatValue();
            if(f < f1)
                return (long)LESS_THAN;
            if(f == f1)
                return (long)EQUAL;
            else
                return (long)GREATER_THAN;
        }
        if((obj instanceof JCString) && (obj1 instanceof JCString))
            return (long)((JCString)obj).getString().compareTo(((JCString)obj1).getString());
        if((obj instanceof Calendar) && (obj1 instanceof Calendar))
        {
            Calendar calendar = (Calendar)obj;
            Calendar calendar1 = (Calendar)obj1;
            if(calendar.equals(calendar1))
                return (long)EQUAL;
            if(calendar.before(calendar1))
                return (long)LESS_THAN;
            else
                return (long)GREATER_THAN;
        } else
        {
            return (long)obj.toString().compareTo(obj1.toString());
        }
    }

    public TableDataSortable()
    {
        LESS_THAN = -1;
        GREATER_THAN = 1;
    }

    public int LESS_THAN;
    public int EQUAL;
    public int GREATER_THAN;
}
