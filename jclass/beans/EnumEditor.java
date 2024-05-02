// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnumEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public abstract class EnumEditor extends PropertyEditorSupport
{

    public EnumEditor(String as[], int ai[])
    {
        prepend = "";
        upperCaseEnum = true;
        strings = as;
        values = ai;
    }

    public EnumEditor(String as[], int ai[], String s)
    {
        this(as, ai);
        prepend = s;
    }

    public String getAsText()
    {
        return JCUtilConverter.fromEnum(((Integer)getValue()).intValue(), strings, values);
    }

    public String getJavaInitializationString()
    {
        if(getValue() == null)
            return "NULL";
        if(upperCaseEnum)
            return prepend + getAsText().toUpperCase();
        else
            return prepend + getAsText();
    }

    public String[] getTags()
    {
        return strings;
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        int i = JCUtilConverter.toEnum(s, strings, values, -999);
        if(i == -999)
        {
            throw new IllegalArgumentException("invalid enum: " + s);
        } else
        {
            setValue(new Integer(i));
            return;
        }
    }

    String strings[];
    int values[];
    String prepend;
    boolean upperCaseEnum;
}
