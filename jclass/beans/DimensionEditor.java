// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DimensionEditor.java

package jclass.beans;

import java.awt.Dimension;
import java.beans.PropertyEditorSupport;
import jclass.util.JCUtilConverter;

public class DimensionEditor extends PropertyEditorSupport
{

    public DimensionEditor()
    {
    }

    public String getAsText()
    {
        Dimension dimension = (Dimension)getValue();
        return "" + dimension.width + 'x' + dimension.height;
    }

    public String getJavaInitializationString()
    {
        Dimension dimension = (Dimension)getValue();
        if(dimension == null)
            return "null";
        else
            return "new java.awt.Dimension(" + dimension.width + "," + dimension.height + ")";
    }

    public void setAsText(String s)
        throws IllegalArgumentException
    {
        if(s == null)
            throw new IllegalArgumentException("invalid dimension: " + s);
        Dimension dimension = JCUtilConverter.toDimension(s, null);
        if(dimension == null)
        {
            throw new IllegalArgumentException("invalid dimension: " + s);
        } else
        {
            setValue(dimension);
            return;
        }
    }
}
