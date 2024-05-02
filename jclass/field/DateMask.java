// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarValidator.java

package jclass.field;

import java.io.Serializable;

// Referenced classes of package jclass.field:
//            DateFormat, JCCalendarValidator, ReturnableInt

class DateMask
    implements Cloneable, Serializable
{

    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException _ex)
        {
            return null;
        }
    }

    DateMask()
    {
    }

    int symbol;
    int field;
    int length;
    String chars;
    String strings[];
    DateMask absolute_replacement;
}
