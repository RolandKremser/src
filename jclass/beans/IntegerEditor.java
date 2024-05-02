// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public class IntegerEditor extends PropertyEditorSupport
{

    public IntegerEditor()
    {
    }

    public String getAsText()
    {
        String s = ((Integer)getValue()).toString();
        return s;
    }

    public String getJavaInitializationString()
    {
        Integer integer = (Integer)getValue();
        if(integer == null)
            return "null";
        else
            return "new Integer(" + getAsText() + ")";
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        if(s == null)
        {
            throw new IllegalArgumentException("invalid integer: " + s);
        } else
        {
            int i = JCUtilConverter.toInt(s, 0);
            setValue(new Integer(i));
            return;
        }
    }
}
