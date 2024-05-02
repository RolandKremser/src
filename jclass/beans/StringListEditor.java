// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringListEditor.java

package jclass.beans;

import jclass.util.JCUtilConverter;

// Referenced classes of package jclass.beans:
//            MultiLineEditor, JCTabEditor

public class StringListEditor extends MultiLineEditor
{

    public StringListEditor()
    {
    }

    public String getJavaInitializationString()
    {
        String s = valueToString(getValue(), "\\n");
        return "jclass.util.JCUtilConverter.toStringList(new String(\"" + s + "\"), '\\n')";
    }

    public Object stringToValue(String s)
    {
        String as[] = JCUtilConverter.toStringList(s, '\n');
        if(as.length == 0)
            as = null;
        return as;
    }

    public String valueToString(Object obj, String s)
    {
        if(obj == null || !(obj instanceof String[]))
            return "";
        String as[] = (String[])obj;
        StringBuffer stringbuffer = new StringBuffer(as.length);
        for(int i = 0; i < as.length; i++)
        {
            if(as[i] != null)
                stringbuffer.append(as[i]);
            if(i < as.length - 1)
                stringbuffer.append(s);
        }

        return new String(stringbuffer);
    }
}
