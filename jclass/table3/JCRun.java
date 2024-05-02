// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Run.java

package jclass.table3;

import java.io.Serializable;

class JCRun
    implements Serializable
{

    void copy(JCRun run)
    {
        sum = run.sum;
        value = run.value;
        start = run.start;
        end = run.end;
    }

    JCRun()
    {
    }

    int sum;
    int value;
    int start;
    int end;
}
