// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TblConvertUtil.java

package jclass.table3;

import java.util.Date;
import java.util.Vector;
import jclass.util.JCUtilConverter;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCTable, JCTblEnum, Table

public class TblConvertUtil
{

    public static Object convert(String s, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"))
                return Boolean.valueOf(s);
            else
                return s;

        case 1: // '\001'
            try
            {
                return new Date(s);
            }
            catch(Exception _ex)
            {
                return s;
            }

        case 2: // '\002'
            try
            {
                return Double.valueOf(s);
            }
            catch(Exception _ex)
            {
                return s;
            }

        case 3: // '\003'
            try
            {
                return Float.valueOf(s);
            }
            catch(Exception _ex)
            {
                return s;
            }

        case 4: // '\004'
            try
            {
                return Integer.valueOf(s);
            }
            catch(Exception _ex)
            {
                return s;
            }
        }
        return s;
    }

    public static void convert(Table table, JCVector jcvector, int i, int j)
    {
        if(jcvector == null)
            return;
        for(int k = 0; k < jcvector.size(); k++)
        {
            JCVector jcvector1;
            if((jcvector1 = (JCVector)jcvector.elementAt(k)) != null)
            {
                for(int l = 0; l < jcvector1.size(); l++)
                {
                    int i1 = ((JCTable)table).getDatatype(k + i, l + j);
                    if(i1 != 5)
                    {
                        Object obj = jcvector1.elementAt(l);
                        if(obj instanceof String)
                        {
                            String s = JCUtilConverter.trim(obj);
                            if(s.length() != 0)
                                jcvector1.setElementAt(l, convert(s, i1));
                        }
                    }
                }

            }
        }

    }

    public TblConvertUtil()
    {
    }
}
