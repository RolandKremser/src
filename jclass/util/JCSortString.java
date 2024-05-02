// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSortString.java

package jclass.util;


// Referenced classes of package jclass.util:
//            JCSortable

public class JCSortString
    implements JCSortable
{

    public JCSortString()
    {
        this(0);
    }

    public JCSortString(int i)
    {
        order = i;
    }

    public final long compare(Object obj, Object obj1)
    {
        long l = ((String)obj).compareTo((String)obj1);
        return order != 0 ? -l : l;
    }

    public int order;
    public static final int ASCENDING = 0;
    public static final int DESCENDING = 1;
}
