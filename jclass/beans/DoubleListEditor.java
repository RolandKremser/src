// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DoubleListEditor.java

package jclass.beans;

import jclass.util.JCUtilConverter;

// Referenced classes of package jclass.beans:
//            MultiLineEditor, JCTabEditor

public class DoubleListEditor extends MultiLineEditor
{

    public DoubleListEditor()
    {
    }

    public String getJavaInitializationString()
    {
        String s = valueToString(getValue(), "\\n");
        return "jclass.util.JCUtilConverter.toDoubleList(new String(\"" + s + "\"), '\\n')";
    }

    public Object stringToValue(String s)
    {
        Double adouble[] = JCUtilConverter.toDoubleList(s, '\n');
        if(adouble.length == 0)
            adouble = null;
        return adouble;
    }

    public String valueToString(Object obj, String s)
    {
        if(obj == null || !(obj instanceof Double[]))
            return "";
        Double adouble[] = (Double[])obj;
        StringBuffer stringbuffer = new StringBuffer(adouble.length);
        for(int i = 0; i < adouble.length; i++)
        {
            stringbuffer.append(adouble[i]);
            if(i < adouble.length - 1)
                stringbuffer.append(s);
        }

        return new String(stringbuffer);
    }
}
