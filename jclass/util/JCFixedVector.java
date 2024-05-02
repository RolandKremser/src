// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCFixedVector.java

package jclass.util;

import java.util.Vector;

class JCFixedVector extends Vector
{

    JCFixedVector(int i)
    {
        super(i);
    }

    void addAtTop(Object obj)
    {
        if(super.elementCount + 1 > super.elementData.length)
            removeElementAt(super.elementCount - 1);
        insertElementAt(obj, 0);
    }
}
