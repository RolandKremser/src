// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public class StringEditor extends PropertyEditorSupport
{

    public StringEditor()
    {
    }

    public String getAsText()
    {
        if(getValue() == null)
            return "";
        else
            return JCUtilConverter.fromNewLine(getValue().toString());
    }

    public String getJavaInitializationString()
    {
        return "\"" + getAsText() + "\"";
    }

    public void setAsText(String s)
    {
        setValue(JCUtilConverter.toNewLine(s));
    }
}
