// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTblRevConverter.java

package jclass.table3;

import java.awt.Dimension;
import java.util.Vector;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCCellPosition, JCCellRange, JCTable, JCTblConverter, 
//            JCTblEnum, SeriesValue, Table

public class JCTblRevConverter
{

    public static String fromDimension(Dimension dimension)
    {
        return dimension.width + "," + dimension.height;
    }

    public static String fromInt(int i)
    {
        if(i == 0x7fffffff)
            return "maxint";
        else
            return Integer.toString(i);
    }

    public static String fromVector(JCVector jcvector, char c)
    {
        if(jcvector == null)
            return "";
        String s = "";
        for(int i = 0; i < jcvector.size(); i++)
        {
            String s1 = null;
            if(jcvector.elementAt(i) instanceof String)
                s1 = (String)jcvector.elementAt(i);
            if(i > 0)
                s = s + c;
            if(s1 != null)
                s = s + s1;
        }

        return s;
    }

    public static String fromCellValues(JCVector jcvector, char c)
    {
        if(jcvector == null)
            return "";
        String s = "";
        boolean flag = true;
        for(int i = 0; i < jcvector.size(); i++)
        {
            boolean flag1 = true;
            String s2 = "";
            if(jcvector.elementAt(i) instanceof JCVector)
            {
                JCVector jcvector1 = (JCVector)jcvector.elementAt(i);
                for(int j = 0; j < jcvector1.size(); j++)
                {
                    String s1 = null;
                    if(jcvector1.elementAt(j) instanceof String)
                        s1 = (String)jcvector1.elementAt(j);
                    else
                        try
                        {
                            s1 = jcvector1.elementAt(j).toString();
                        }
                        catch(Exception _ex) { }
                    if(flag1)
                        flag1 = false;
                    else
                        s2 = s2 + c;
                    if(s1 != null)
                        s2 = s2 + s1;
                }

            }
            if(s2.length() == 0)
                s2 = c + " " + c;
            s = s + "(" + s2 + ")";
        }

        return s;
    }

    public static String fromCellSize(int i)
    {
        switch(i)
        {
        case 33001: 
            return "variable";

        case 33000: 
            return "as_is";

        case -999: 
            return "novalue";
        }
        return String.valueOf(i);
    }

    public static String fromCellPosition(JCCellPosition jccellposition)
    {
        if(jccellposition == null)
            return null;
        else
            return fromPosition(jccellposition.row) + " " + fromPosition(jccellposition.column);
    }

    public static String fromRowRange(JCCellRange jccellrange)
    {
        String s = "";
        if(jccellrange.start_row == 0 && jccellrange.end_row == 0x7fffffff)
        {
            s = s + "all";
        } else
        {
            s = s + fromPosition(jccellrange.start_row);
            if(jccellrange.end_row != -999 && jccellrange.start_row != jccellrange.end_row)
            {
                s = s + "-";
                s = s + fromPosition(jccellrange.end_row);
            }
        }
        return s;
    }

    public static String fromColumnRange(JCCellRange jccellrange)
    {
        String s = "";
        if(jccellrange.start_column == 0 && jccellrange.end_column == 0x7fffffff)
        {
            s = s + "all";
        } else
        {
            s = s + fromPosition(jccellrange.start_column);
            if(jccellrange.end_column != -999 && jccellrange.start_column != jccellrange.end_column)
            {
                s = s + "-";
                s = s + fromPosition(jccellrange.end_column);
            }
        }
        return s;
    }

    public static String fromRange(JCCellRange jccellrange)
    {
        return fromRowRange(jccellrange) + " " + fromColumnRange(jccellrange);
    }

    public static String fromRangeList(JCVector jcvector)
    {
        String s = "";
        for(int i = 0; i < jcvector.size(); i++)
        {
            s = s + "(";
            if(jcvector.elementAt(i) instanceof JCCellRange)
            {
                JCCellRange jccellrange = (JCCellRange)jcvector.elementAt(i);
                s = s + fromRange(jccellrange);
            }
            s = s + ")";
        }

        return s;
    }

    public static String fromPosition(int i)
    {
        String s = "";
        switch(i)
        {
        case -999: 
            break;

        case -998: 
            s = s + "all";
            break;

        case -997: 
            s = s + "allcells";
            break;

        case -1: 
            s = s + "label";
            break;

        default:
            if(i == 0x7fffffff)
                s = s + "maxint";
            else
                s = s + String.valueOf(i);
            break;
        }
        return s;
    }

    public static String fromTableInt(int i)
    {
        if(i == -1)
            return "label";
        if(i == 0x7fffffff)
            return "maxint";
        if(i == -999)
            return "novalue";
        else
            return String.valueOf(i);
    }

    public static String fromBorderSides(int i, String s, String s1)
    {
        boolean flag = true;
        StringBuffer stringbuffer = new StringBuffer();
        String s2 = null;
        int j = 8;
        boolean flag1 = true;
        if(i > 15)
            flag = false;
        else
        if(i == 15 || i == 0)
        {
            flag = (s2 = JCTblConverter.fromEnum(i, s, "Bordersides", null)) != null;
        } else
        {
            for(; j >= 1 && flag; j /= 2)
                if(i >= j)
                {
                    flag = (s2 = JCTblConverter.fromEnum(j, s, "Bordersides", null)) != null;
                    if(flag)
                    {
                        if(!flag1)
                            stringbuffer.append(" + ");
                        flag1 = false;
                        stringbuffer.append(s2);
                    }
                    i -= j;
                }

            if(flag)
                s2 = stringbuffer.toString();
        }
        if(flag)
            return s2;
        else
            return s1;
    }

    public static boolean resourceToString(String s, Table table, JCVector jcvector, JCVector jcvector1)
    {
        if(jcvector == null || jcvector1 == null)
            return false;
        try
        {
            Series series = null;
            if(s.equalsIgnoreCase("Alignment"))
                series = table.alignment_series;
            else
            if(s.equalsIgnoreCase("Background"))
                series = table.bg_series;
            else
            if(s.equalsIgnoreCase("BorderSides"))
                series = table.bordersides_series;
            else
            if(s.equalsIgnoreCase("BorderType"))
                series = table.bordertype_series;
            else
            if(s.equalsIgnoreCase("CharHeight"))
                series = table.charheight_series;
            else
            if(s.equalsIgnoreCase("CharWidth"))
                series = table.charwidth_series;
            else
            if(s.equalsIgnoreCase("DataType"))
                series = ((JCTable)table).datatype_series;
            else
            if(s.equalsIgnoreCase("Font"))
                series = table.font_series;
            else
            if(s.equalsIgnoreCase("Foreground"))
                series = table.fg_series;
            else
            if(s.equalsIgnoreCase("PixelHeight"))
                series = table.pixelheight_series;
            else
            if(s.equalsIgnoreCase("PixelWidth"))
                series = table.pixelwidth_series;
            for(int i = 0; i < series.size(); i++)
            {
                SeriesValue seriesvalue = (SeriesValue)series.elementAt(i);
                boolean flag = true;
                if(seriesvalue.row >= 0 && seriesvalue.column >= 0)
                {
                    for(int j = 0; j < jcvector.size() && flag; j++)
                    {
                        JCCellRange jccellrange = (JCCellRange)jcvector.elementAt(j);
                        if(jcvector1.elementAt(j) == seriesvalue.value)
                            if(jccellrange.inside(seriesvalue.row, seriesvalue.column))
                                flag = false;
                            else
                            if(jccellrange.start_column == jccellrange.end_column && seriesvalue.column == jccellrange.start_column)
                            {
                                flag = false;
                                if(seriesvalue.row == jccellrange.start_row - 1)
                                    jccellrange.start_row--;
                                else
                                if(seriesvalue.row == jccellrange.end_row + 1)
                                    jccellrange.end_row++;
                                else
                                    flag = true;
                            } else
                            if(jccellrange.start_row == jccellrange.end_row && seriesvalue.row == jccellrange.start_row)
                            {
                                flag = false;
                                if(seriesvalue.column == jccellrange.start_column - 1)
                                    jccellrange.start_column--;
                                else
                                if(seriesvalue.column == jccellrange.end_column + 1)
                                    jccellrange.end_column--;
                                else
                                    flag = true;
                            }
                    }

                }
                if(flag)
                {
                    jcvector.addElement(new JCCellRange(seriesvalue.row, seriesvalue.column, seriesvalue.row, seriesvalue.column));
                    jcvector1.addElement(seriesvalue.value);
                }
            }

        }
        catch(Throwable _ex)
        {
            return false;
        }
        return true;
    }

    public JCTblRevConverter()
    {
    }
}
