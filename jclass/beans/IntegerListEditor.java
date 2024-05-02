// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerListEditor.java

package jclass.beans;

import jclass.util.JCUtilConverter;

// Referenced classes of package jclass.beans:
//            MultiLineEditor, JCTabEditor

public class IntegerListEditor extends MultiLineEditor
{

    public IntegerListEditor()
    {
    }

    public String getJavaInitializationString()
    {
        String s = valueToString(getValue(), "\\n");
        return "jclass.util.JCUtilConverter.toIntegerList(new String(\"" + s + "\"), '\\n')";
    }

    public Object stringToValue(String s)
    {
        Integer ainteger[] = JCUtilConverter.toIntegerList(s, '\n');
        if(ainteger.length == 0)
            ainteger = null;
        return ainteger;
    }

    public String valueToString(Object obj, String s)
    {
        if(obj == null || !(obj instanceof Integer[]))
            return "";
        Integer ainteger[] = (Integer[])obj;
        StringBuffer stringbuffer = new StringBuffer(ainteger.length);
        for(int i = 0; i < ainteger.length; i++)
        {
            if(ainteger[i] != null)
                stringbuffer.append(ainteger[i]);
            if(i < ainteger.length - 1)
                stringbuffer.append(s);
        }

        return new String(stringbuffer);
    }
}
