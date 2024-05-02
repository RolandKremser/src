// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCListenerListEnumeration.java

package jclass.util;

import java.util.Enumeration;

// Referenced classes of package jclass.util:
//            JCListenerList

class JCListenerListEnumeration
    implements Enumeration
{

    JCListenerListEnumeration(JCListenerList jclistenerlist)
    {
        current = jclistenerlist;
    }

    public boolean hasMoreElements()
    {
        return current != null;
    }

    public Object nextElement()
    {
        Object obj = current.listener;
        current = current.next;
        return obj;
    }

    JCListenerList current;
}
