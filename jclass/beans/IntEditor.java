// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public abstract class IntEditor extends PropertyEditorSupport
{

    public IntEditor(String as[], int ai[])
    {
        strings = as;
        values = ai;
    }

    public IntEditor(String as[], int ai[], String s)
    {
        strings = as;
        values = ai;
        prepend = s;
    }

    public String getAsText()
    {
        int i = ((Integer)getValue()).intValue();
        String s = JCUtilConverter.fromEnum(i, strings, values);
        if(s == null)
            s = getValue().toString();
        return s;
    }

    public String getJavaInitializationString()
    {
        Object obj = getValue();
        if(obj == null)
            return "NULL";
        else
            return prepend + getAsText();
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        int i = JCUtilConverter.toEnum(s, strings, values, 0x7fffffff);
        if(i == 0x80000001)
            i = Integer.parseInt(s);
        setValue(new Integer(i));
    }

    String strings[];
    int values[];
    String prepend;
}
