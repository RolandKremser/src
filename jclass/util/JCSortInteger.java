// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSortInteger.java

package jclass.util;


// Referenced classes of package jclass.util:
//            JCSortable

public class JCSortInteger
    implements JCSortable
{

    public JCSortInteger()
    {
        this(0);
    }

    public JCSortInteger(int i)
    {
        order = i;
    }

    public final long compare(Object obj, Object obj1)
    {
        long l = ((Integer)obj).longValue() - ((Integer)obj1).longValue();
        return order != 0 ? -l : l;
    }

    public int order;
    public static final int ASCENDING = 0;
    public static final int DESCENDING = 1;
}
