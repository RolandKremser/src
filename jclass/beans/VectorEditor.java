// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VectorEditor.java

package jclass.beans;

import java.util.Vector;
import jclass.util.JCUtilConverter;
import jclass.util.JCVector;

// Referenced classes of package jclass.beans:
//            MultiLineEditor, JCTabEditor

public class VectorEditor extends MultiLineEditor
{

    public VectorEditor()
    {
    }

    public String getJavaInitializationString()
    {
        String s = valueToString(getValue(), "\\n");
        return "jclass.util.JCUtilConverter.toVector(null, new String(\"" + s + "\"), '\\n', true)";
    }

    public Object stringToValue(String s)
        throws IllegalArgumentException
    {
        JCVector jcvector = JCUtilConverter.toVector(null, s, '\n', true);
        if(jcvector == null)
            throw new IllegalArgumentException("invalid vector: " + s);
        else
            return jcvector;
    }

    public String valueToString(Object obj, String s)
    {
        if(obj == null || !(obj instanceof JCVector))
            return "";
        JCVector jcvector = (JCVector)obj;
        StringBuffer stringbuffer = new StringBuffer(jcvector.size());
        for(int i = 0; i < jcvector.size(); i++)
        {
            if(jcvector.elementAt(i) != null)
                stringbuffer.append(jcvector.elementAt(i).toString());
            if(i < jcvector.size() - 1)
                stringbuffer.append(s);
        }

        return new String(stringbuffer);
    }
}
