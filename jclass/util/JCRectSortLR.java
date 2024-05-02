// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCRectSortLR.java

package jclass.util;

import java.awt.Rectangle;

// Referenced classes of package jclass.util:
//            JCRectNode, JCSortInterface

class JCRectSortLR
    implements JCSortInterface
{

    JCRectSortLR()
    {
    }

    public boolean compare(Object obj, Object obj1)
    {
        JCRectNode jcrectnode = (JCRectNode)obj;
        JCRectNode jcrectnode1 = (JCRectNode)obj1;
        return jcrectnode.rect.x - jcrectnode1.rect.x > 0;
    }
}
