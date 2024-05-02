// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DoubleEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public class DoubleEditor extends PropertyEditorSupport
{

    public DoubleEditor()
    {
    }

    public String getAsText()
    {
        String s = ((Double)getValue()).toString();
        return s;
    }

    public String getJavaInitializationString()
    {
        Double double1 = (Double)getValue();
        if(double1 == null)
            return "null";
        else
            return "new Double(" + getAsText() + ")";
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        if(s == null)
        {
            throw new IllegalArgumentException("invalid double: " + s);
        } else
        {
            double d = JCUtilConverter.toDouble(s, 0.0D);
            setValue(new Double(d));
            return;
        }
    }
}
