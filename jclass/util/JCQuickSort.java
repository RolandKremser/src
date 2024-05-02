// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCQuickSort.java

package jclass.util;


// Referenced classes of package jclass.util:
//            JCSortAlgorithm

public class JCQuickSort extends JCSortAlgorithm
{

    public JCQuickSort()
    {
    }

    void sort(int i, int j)
    {
        if(i >= j)
            return;
        swap(i, (i + j) / 2);
        int k = i;
        for(int l = i + 1; l <= j; l++)
            if(do_compare(i, l) > 0L)
                swap(++k, l);

        swap(i, k);
        sort(i, k - 1);
        sort(k + 1, j);
    }
}
