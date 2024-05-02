// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Validate.java

package jclass.table3;

import java.util.Date;
import java.util.Vector;
import jclass.util.JCUtilConverter;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCTable, JCTblEnum, JCValidateCellEvent, JCValidateCellListener, 
//            TblConvertUtil

class Validate
{

    static boolean value(JCTable jctable, JCValidateCellEvent jcvalidatecellevent, int i, int j, boolean flag, Object obj)
    {
        jcvalidatecellevent.datatype = jctable.getDatatype(i, j);
        jcvalidatecellevent.value = obj;
        if(jcvalidatecellevent.datatype != 5 && !checkValue(obj, jcvalidatecellevent.datatype))
            return false;
        if((obj instanceof String) && jcvalidatecellevent.datatype != 5)
            jcvalidatecellevent.value = TblConvertUtil.convert((String)obj, jcvalidatecellevent.datatype);
        jcvalidatecellevent.row = i;
        jcvalidatecellevent.column = j;
        jcvalidatecellevent.changed = flag;
        jcvalidatecellevent.doit = true;
        JCVector jcvector = jctable.validateCellListeners;
        for(int k = 0; k < jcvector.size(); k++)
            ((JCValidateCellListener)jcvector.elementAt(k)).validateCellBegin(jcvalidatecellevent);

        return jcvalidatecellevent.doit;
    }

    static void cells(JCTable jctable, JCVector jcvector)
    {
        if(jcvector == null)
            return;
        JCValidateCellEvent jcvalidatecellevent = new JCValidateCellEvent(jctable);
        for(int i = 0; i < jcvector.size(); i++)
        {
            JCVector jcvector1 = (JCVector)jcvector.elementAt(i);
            if(jcvector1 != null)
            {
                for(int j = 0; j < jcvector1.size(); j++)
                    if(value(jctable, jcvalidatecellevent, i, j, true, jcvector1.elementAt(j)))
                    {
                        jcvector1.setElementAt(j, jcvalidatecellevent.value);
                    } else
                    {
                        JCVector jcvector2 = jctable.getCells();
                        JCVector jcvector3 = null;
                        if(jcvector2 != null && jcvector2.size() > i)
                            jcvector3 = (JCVector)jcvector2.elementAt(i);
                        if(jcvector3 != null && jcvector3.size() > j)
                            jcvector1.setElementAt(j, jcvector3.elementAt(j));
                        else
                            jcvector1.setElementAt(j, null);
                    }

            }
        }

    }

    static boolean checkBoolean(String s)
    {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false") || s.equals("t") || s.equals("f");
    }

    static boolean checkValue(Object obj, int i)
    {
        if(obj == null)
            return true;
        switch(i)
        {
        case 0: // '\0'
            if(obj instanceof Boolean)
                return true;
            String s = JCUtilConverter.trim(obj);
            if(s.length() == 0)
                return true;
            else
                return checkBoolean(s);

        case 1: // '\001'
            if(obj instanceof Date)
                return true;
            String s1 = JCUtilConverter.trim(obj);
            if(s1.length() == 0)
                return true;
            try
            {
                Date.parse(s1);
                return true;
            }
            catch(Exception _ex)
            {
                return false;
            }

        case 2: // '\002'
            if(obj instanceof Double)
                return true;
            String s2 = JCUtilConverter.trim(obj);
            if(s2.length() == 0)
                return true;
            try
            {
                Double.valueOf(s2).doubleValue();
                return true;
            }
            catch(Exception _ex)
            {
                return false;
            }

        case 3: // '\003'
            if(obj instanceof Float)
                return true;
            String s3 = JCUtilConverter.trim(obj);
            if(s3.length() == 0)
                return true;
            try
            {
                Float.valueOf(s3).floatValue();
                return true;
            }
            catch(Exception _ex)
            {
                return false;
            }

        case 4: // '\004'
            if(obj instanceof Integer)
                return true;
            String s4 = JCUtilConverter.trim(obj);
            if(s4.length() == 0)
                return true;
            try
            {
                Integer.valueOf(s4).intValue();
                return true;
            }
            catch(Exception _ex)
            {
                return false;
            }
        }
        return true;
    }

    Validate()
    {
    }
}
