// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCDayOfWeek.java

package jclass.field;


public class JCDayOfWeek
{

    public JCDayOfWeek(int i)
    {
        if(i < 0 || i > 6)
        {
            throw new IllegalArgumentException("invalid value for dayOfWeek");
        } else
        {
            day_of_week = i;
            return;
        }
    }

    int day_of_week;
}
