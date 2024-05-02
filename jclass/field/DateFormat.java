// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarValidator.java

package jclass.field;

import java.io.Serializable;

// Referenced classes of package jclass.field:
//            DateMask, JCCalendarValidator, ReturnableInt

class DateFormat
    implements Cloneable, Serializable
{

    int getField(int i)
    {
        return elements[i].field;
    }

    String getString(int i, int j)
    {
        return elements[i].strings[j];
    }

    String[] getStrings(int i)
    {
        return elements[i].strings;
    }

    String getChars(int i)
    {
        return elements[i].chars;
    }

    int getSymbol(int i)
    {
        return elements[i].symbol;
    }

    int getLength(int i)
    {
        return elements[i].length;
    }

    void setLength(int i, int j)
    {
        elements[i].length = j;
    }

    DateFormat()
    {
    }

    DateMask elements[];
    boolean size_ambiguous;
}
