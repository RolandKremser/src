// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectEditor.java

package jclass.beans;

import java.beans.PropertyEditorSupport;
import jclass.util.*;

public class ObjectEditor extends PropertyEditorSupport
{

    public ObjectEditor()
    {
    }

    public String getAsText()
    {
        Object obj = getValue();
        if(obj == null)
            return "";
        if(obj instanceof JCString)
            return ((JCString)obj).toString2();
        else
            return JCUtilConverter.fromNewLine(obj.toString());
    }

    public String getJavaInitializationString()
    {
        Object obj = getValue();
        String s = getAsText();
        if(obj instanceof String)
            return "\"" + s + "\"";
        if(obj instanceof Integer)
            return "\"" + s + "\"";
        if(obj instanceof Boolean)
            return obj != Boolean.TRUE ? "\"false\"" : "\"true\"";
        else
            return "jclass.util.ConvertUtil.toCellValue(null, \"" + s + "\", true)";
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        if(s == null)
        {
            setValue(null);
            return;
        }
        Object obj = ConvertUtil.toCellValue(null, s, true);
        if(obj == null)
            throw new IllegalArgumentException("invalid object: " + s);
        if((obj instanceof String) && obj.equals(s))
        {
            setValue(obj);
            return;
        }
        try
        {
            setValue(new Integer(Integer.parseInt(s)));
            return;
        }
        catch(Exception _ex) { }
        if(s.equalsIgnoreCase("true"))
            setValue(Boolean.TRUE);
        else
        if(s.equalsIgnoreCase("false"))
            setValue(Boolean.FALSE);
        else
            setValue(JCUtilConverter.toNewLine(s));
    }
}
