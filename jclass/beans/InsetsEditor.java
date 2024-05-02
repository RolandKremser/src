// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsetsEditor.java

package jclass.beans;

import java.awt.Insets;
import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public class InsetsEditor extends PropertyEditorSupport
{

    public InsetsEditor()
    {
    }

    public String getAsText()
    {
        Insets insets = (Insets)getValue();
        return "" + insets.top + ',' + insets.left + ',' + insets.bottom + ',' + insets.right;
    }

    public String getJavaInitializationString()
    {
        return "new java.awt.Insets(" + getAsText() + ")";
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        if(s == null)
            throw new IllegalArgumentException("invalid insets: " + s);
        Insets insets = JCUtilConverter.toInsets(s, null);
        if(insets == null)
        {
            throw new IllegalArgumentException("invalid insets: " + s);
        } else
        {
            setValue(insets);
            return;
        }
    }
}
