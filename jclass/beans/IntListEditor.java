// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntListEditor.java

package jclass.beans;

import jclass.util.JCUtilConverter;

// Referenced classes of package jclass.beans:
//            MultiLineEditor, JCTabEditor

public class IntListEditor extends MultiLineEditor
{

    public IntListEditor()
    {
    }

    public String getJavaInitializationString()
    {
        String s = valueToString(getValue(), "\\n");
        return "jclass.util.JCUtilConverter.toIntList(new String(\"" + s + "\"), '\\n')";
    }

    public Object stringToValue(String s)
    {
        int ai[] = JCUtilConverter.toIntList(s, '\n');
        if(ai.length == 0)
            ai = null;
        return ai;
    }

    public String valueToString(Object obj, String s)
    {
        if(obj == null || !(obj instanceof int[]))
            return "";
        int ai[] = (int[])obj;
        StringBuffer stringbuffer = new StringBuffer(ai.length);
        for(int i = 0; i < ai.length; i++)
        {
            stringbuffer.append(ai[i]);
            if(i < ai.length - 1)
                stringbuffer.append(s);
        }

        return new String(stringbuffer);
    }
}
