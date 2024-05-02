// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCache.java

package jclass.util;

import java.util.Vector;

// Referenced classes of package jclass.util:
//            JCFixedVector

public class JCCache
{

    public JCCache(int i, int j)
    {
        buckets = null;
        keys = null;
        keys = new JCFixedVector[i];
        buckets = new JCFixedVector[i];
        for(int k = 0; k < keys.length; k++)
        {
            buckets[k] = new JCFixedVector(j);
            keys[k] = new JCFixedVector(j);
        }

    }

    public final void addToCache(Object obj, Object obj1)
    {
        try
        {
            int i = bucketIndex(obj);
            int j = keys[i].indexOf(obj);
            if(j >= 0)
            {
                buckets[i].setElementAt(obj1, j);
            } else
            {
                keys[i].addAtTop(obj);
                buckets[i].addAtTop(obj1);
            }
        }
        catch(Throwable throwable)
        {
            throwable.printStackTrace(System.out);
        }
    }

    private final int bucketIndex(Object obj)
    {
        return obj.hashCode() % keys.length;
    }

    public final Object getFromCache(Object obj)
    {
        Object obj1 = null;
        try
        {
            int i = bucketIndex(obj);
            int j = keys[i].indexOf(obj);
            if(j >= 0)
                obj1 = buckets[i].elementAt(j);
        }
        catch(Throwable _ex) { }
        return obj1;
    }

    public final String toString()
    {
        String s = new String();
        s = "Cache:\n";
        for(int i = 0; i < keys.length; i++)
        {
            s = s + "Bin: " + (i + 1) + "\n";
            for(int j = 0; j < keys[i].size(); j++)
            {
                s = s + "Element: " + (j + 1) + " = ";
                s = s + "(" + keys[i].elementAt(j).toString();
                s = s + "," + buckets[i].elementAt(j).toString() + ")\n";
            }

        }

        return s;
    }

    private JCFixedVector buckets[];
    private JCFixedVector keys[];
}
