// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTblConverter.java

package jclass.table3;

import java.applet.Applet;
import java.awt.Component;
import java.util.StringTokenizer;
import java.util.Vector;
import jclass.util.*;

// Referenced classes of package jclass.table3:
//            JCCellPosition, JCCellRange, JCTblEnum, Series, 
//            SeriesUtil, Table

public class JCTblConverter
{

    public static int toEnum(String s, String s1)
    {
        int i;
        for(i = 0; i < enum_strings.length; i++)
            if(enum_strings[i][0].equalsIgnoreCase(s))
                break;

        if(i == enum_strings.length)
            return 0x80000000;
        s1 = s1.trim();
        for(int j = 1; j < enum_strings[i].length; j++)
            if(s1.equalsIgnoreCase(enum_strings[i][j]))
                return enum_values[i][j - 1];

        try
        {
            return Integer.parseInt(s1);
        }
        catch(Exception _ex)
        {
            return 0x80000000;
        }
    }

    public static int toEnum(String s, String s1, String s2, int i)
    {
        int j = toEnum(s2, s);
        if(j == 0x80000000)
            JCUtilConverter.error("Error converting '" + s + "' to " + s1);
        if(j != 0x80000000)
            return j;
        else
            return i;
    }

    static String fromEnum(String s, int i)
    {
        int j;
        for(j = 0; j < enum_strings.length; j++)
            if(enum_strings[j][0].equalsIgnoreCase(s))
                break;

        if(j == enum_strings.length)
            return null;
        for(int k = 0; k < enum_values[j].length; k++)
            if(enum_values[j][k] == i)
                return enum_strings[j][k + 1];

        try
        {
            return String.valueOf(i);
        }
        catch(Exception _ex)
        {
            return null;
        }
    }

    public static String fromEnum(int i, String s, String s1, String s2)
    {
        String s3 = fromEnum(s1, i);
        if(s3 == null)
            JCUtilConverter.error("Error converting '" + i + "' to " + s);
        if(s3 != null)
            return s3;
        else
            return s2;
    }

    private static int toTableInt(String s)
    {
        if(s == null)
            return 0x80000000;
        s = s.trim();
        if(s.equalsIgnoreCase("all"))
            return -998;
        if(s.equalsIgnoreCase("allcells"))
            return -997;
        if(s.equalsIgnoreCase("variable"))
            return 33001;
        try
        {
            return Integer.parseInt(s);
        }
        catch(Exception _ex)
        {
            return 0x80000000;
        }
    }

    private static int toTableInt2(String s)
    {
        if(s == null)
            return 0x80000000;
        s = s.trim();
        if(s.equalsIgnoreCase("label"))
            return -1;
        if(s.equalsIgnoreCase("maxint"))
            return 0x7fffffff;
        if(s.equalsIgnoreCase("novalue"))
            return -999;
        try
        {
            return Integer.parseInt(s);
        }
        catch(Exception _ex)
        {
            return 0x80000000;
        }
    }

    public static int toInt(String s, int i)
    {
        int j = toTableInt(s);
        if(j == 0x80000000)
            j = toTableInt2(s);
        if(j != 0x80000000)
            return j;
        else
            return i;
    }

    private static boolean toPosition(String s)
    {
        comma = false;
        value2 = -999;
        if(s == null || s.length() == 0)
            return false;
        s = s.trim();
        if(s.equalsIgnoreCase("all"))
            value1 = -998;
        else
        if(s.equalsIgnoreCase("allcells"))
            value1 = -997;
        else
        if(s.equalsIgnoreCase("label"))
            value1 = -1;
        else
        if(s.equalsIgnoreCase("maxint"))
        {
            value1 = 0x7fffffff;
        } else
        {
            String s2 = null;
            int k;
            String s1;
            if((k = s.indexOf('-')) != -1 && k < s.length() - 1)
            {
                s1 = s.substring(0, k);
                s2 = s.substring(k + 1);
            } else
            if((k = s.indexOf(',')) != -1 && k < s.length() - 1)
            {
                s1 = s.substring(0, k);
                s2 = s.substring(k + 1);
                comma = true;
            } else
            {
                s1 = s;
            }
            int i;
            if((i = toTableInt2(s1)) == 0x80000000)
                return false;
            value1 = i;
            if(s2 != null)
            {
                int j;
                if((j = toTableInt2(s2)) == 0x80000000)
                    return false;
                value2 = j;
            }
        }
        return true;
    }

    private static JCCellRange setRange(int i, int j, int k, int l)
    {
        if(comma)
        {
            if(k == -999)
                k = i;
            if(l == -999)
                l = j;
            return new JCCellRange(i, j, k, l);
        }
        if(j == -999)
            j = i;
        if(l == -999)
            l = k;
        return new JCCellRange(i, k, j, l);
    }

    public static JCVector toRangeList(String s)
    {
        JCVector jcvector = new JCVector();
        s = JCUtilConverter.trim(s);
        if(s == null || s.length() == 0)
            return jcvector;
        int i;
        while((i = s.indexOf('(')) != -1) 
        {
            s = s.substring(i + 1);
            JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
            String s1 = JCUtilConverter.trim(jcstringtokenizer.nextToken(')'));
            if(s1 == null || s1.length() == 0)
                return jcvector;
            s = JCUtilConverter.trim(s.substring(jcstringtokenizer.getPosition()));
            jcstringtokenizer = new JCStringTokenizer(s1);
            String s2 = jcstringtokenizer.nextToken();
            if(!toPosition(s2))
            {
                JCUtilConverter.error(null, s2);
                return jcvector;
            }
            int j = value1;
            int k = value2;
            s2 = jcstringtokenizer.nextToken();
            if(!toPosition(s2))
            {
                JCUtilConverter.error(null, s2);
                return jcvector;
            }
            int l = value1;
            int i1 = value2;
            JCCellRange jccellrange = setRange(j, k, l, i1);
            if(jccellrange.start_row == -998 || jccellrange.start_row == -997)
            {
                jccellrange.start_row = 0;
                jccellrange.end_row = 0x7fffffff;
            }
            if(jccellrange.start_column == -998 || jccellrange.start_column == -997)
            {
                jccellrange.start_column = 0;
                jccellrange.end_column = 0x7fffffff;
            }
            jcvector.addElement(jccellrange);
        }
        return jcvector;
    }

    public static int toCellSize(String s)
    {
        if(s == null)
            return 0x80000000;
        s = s.trim();
        if(s.equalsIgnoreCase("variable"))
            return 33001;
        if(s.equalsIgnoreCase("as_is"))
            return 33000;
        if(s.equalsIgnoreCase("novalue"))
            return -999;
        try
        {
            return Integer.parseInt(s);
        }
        catch(Exception _ex)
        {
            return 0x80000000;
        }
    }

    public static int toBorderSides(String s)
    {
        boolean flag = true;
        int j = 0;
        if(s == null || s.length() == 0)
            return 0x80000000;
        for(JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s); jcstringtokenizer.hasMoreTokens() && flag;)
        {
            String s1 = jcstringtokenizer.nextToken('+');
            int i;
            flag = (i = toEnum("BorderSides", s1)) != 0x80000000;
            if(flag)
                j |= i;
        }

        if(flag)
            return j;
        else
            return 0x80000000;
    }

    public static JCCellPosition toCellPosition(String s)
    {
        if(s == null || s.length() == 0)
            return null;
        JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
        String s1;
        if((s1 = jcstringtokenizer.nextToken()) == null)
            return null;
        if(!toPosition(s1))
            return null;
        JCCellPosition jccellposition = new JCCellPosition(value1, 0);
        if((s1 = jcstringtokenizer.nextToken()) == null)
            return null;
        if(!toPosition(s1))
        {
            return null;
        } else
        {
            jccellposition.column = value1;
            return jccellposition;
        }
    }

    public static JCCellRange toRange(String s)
    {
        if(s == null || s.length() == 0)
            return null;
        JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
        String s1;
        if((s1 = jcstringtokenizer.nextToken()) == null)
            return null;
        if(!toPosition(s1))
            return null;
        int i = value1;
        int j = value2;
        if((s1 = jcstringtokenizer.nextToken()) == null)
            return null;
        if(!toPosition(s1))
        {
            return null;
        } else
        {
            int k = value1;
            int l = value2;
            return setRange(i, j, k, l);
        }
    }

    public static JCVector toCellValues(Component component, String s, char c, boolean flag)
    {
        JCVector jcvector = new JCVector();
        int j = 0;
        s = JCUtilConverter.trim(s);
        if(s == null || s.length() == 0)
            return jcvector;
        int i;
        while((i = s.indexOf('(')) != -1) 
        {
            s = s.substring(i + 1);
            JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
            jcstringtokenizer.strip_esc = false;
            String s2 = jcstringtokenizer.nextToken(')');
            String s1 = JCUtilConverter.trim(s2);
            if(s1 == null)
                s1 = "";
            s = JCUtilConverter.trim(s.substring(jcstringtokenizer.getPosition()));
            jcvector.setElementAt(j++, ConvertUtil.toVector(component, s1, c, flag));
        }
        return jcvector;
    }

    static Series toSeries(Applet applet, Table table, int i, String s)
    {
        Series series = new Series(table);
        if(s == null || s.length() == 0)
            return null;
        JCStringTokenizer jcstringtokenizer = new JCStringTokenizer(s);
        String s1;
        while((s1 = jcstringtokenizer.nextToken()) != null) 
        {
            int j;
            if((j = s1.indexOf('(')) == -1)
            {
                JCUtilConverter.error(s, s1);
                return null;
            }
            s1 = s1.substring(j + 1);
            if(s1.equals(")"))
                return null;
            if(!toPosition(s1))
            {
                JCUtilConverter.error(s, s1);
                return null;
            }
            int l = value1;
            int i1 = value2;
            int j1 = -999;
            int k1 = -999;
            switch(i)
            {
            case 5: // '\005'
            case 12: // '\f'
                j1 = -998;
                k1 = -999;
                break;

            case 6: // '\006'
            case 13: // '\r'
                j1 = l;
                k1 = i1;
                l = -998;
                i1 = -999;
                break;

            default:
                s1 = jcstringtokenizer.nextToken();
                if(!toPosition(s1))
                {
                    JCUtilConverter.error(s, s1);
                    return null;
                }
                j1 = value1;
                k1 = value2;
                break;
            }
            JCCellRange jccellrange = setRange(l, i1, j1, k1);
            s1 = jcstringtokenizer.nextToken(')');
            if(s1 == null)
            {
                JCUtilConverter.error("Missing value in '" + s + "'");
                return null;
            }
            s1 = s1.trim();
            if(s1.length() == 0)
            {
                JCUtilConverter.error("Missing value in '" + s + "'");
                return null;
            }
            Object obj = null;
            int k;
            switch(i)
            {
            case 1: // '\001'
                if((k = toEnum("Alignment", s1)) != 0x80000000)
                    obj = new Integer(k);
                break;

            case 2: // '\002'
                if(s1.equalsIgnoreCase("true"))
                    obj = Boolean.TRUE;
                else
                if(s1.equalsIgnoreCase("false"))
                    obj = Boolean.FALSE;
                break;

            case 3: // '\003'
                if((k = toBorderSides(s1)) != 0x80000000)
                    obj = new Integer(k);
                break;

            case 4: // '\004'
                if((k = toEnum("BorderType", s1)) != 0x80000000)
                    obj = new Integer(k);
                break;

            case 12: // '\f'
            case 13: // '\r'
                if((k = toCellSize(s1)) != 0x80000000)
                    obj = new Integer(k);
                break;

            case 8: // '\b'
                if(s1.indexOf(".") == -1)
                    s1 = "java.awt." + s1;
                try
                {
                    obj = Class.forName(s1).newInstance();
                }
                catch(Exception _ex) { }
                break;

            case 7: // '\007'
                if(s1.equalsIgnoreCase("repeat_row"))
                    obj = new Integer(-995);
                else
                if(s1.equalsIgnoreCase("repeat_column"))
                    obj = new Integer(-994);
                else
                    obj = JCUtilConverter.toColor(s1);
                break;

            case 9: // '\t'
                if((k = toEnum("DataType", s1)) != 0x80000000)
                    obj = new Integer(k);
                break;

            case 10: // '\n'
                obj = JCUtilConverter.toFont(s1);
                break;

            case 5: // '\005'
            case 6: // '\006'
            case 11: // '\013'
                if(i == 11 && s1.equalsIgnoreCase("maxint"))
                    obj = new Integer(0x7fffffff);
                else
                    try
                    {
                        obj = Integer.valueOf(s1);
                    }
                    catch(Exception _ex) { }
                break;

            case 14: // '\016'
                if((k = toEnum("StringCase", s1)) != 0x80000000)
                    obj = new Integer(k);
                break;
            }
            if(obj == null)
                JCUtilConverter.error(s, s1);
            else
            if(i1 == -999 && k1 == -999)
                series.setValue(l, j1, obj);
            else
                SeriesUtil.setValue(table, series, jccellrange, obj);
        }
        return series;
    }

    static Series toSeries(Applet applet, Table table, String s, int i, Series series, Object obj)
    {
        String s1 = JCUtilConverter.getParam(applet, table, table.name, s);
        if(s1 == null)
            return series;
        Series series1 = toSeries(applet, table, i, s1);
        if(series1 != null && series1.getDefault() == null)
            series1.setDefault(obj);
        if(series1 != null)
            return series1;
        else
            return series;
    }

    static Series toSeries(Applet applet, Table table, String s, int i, Series series, int j)
    {
        return toSeries(applet, table, s, i, series, new Integer(j));
    }

    static Series toSeries(Applet applet, Table table, String s, int i, Series series, boolean flag)
    {
        return toSeries(applet, table, s, i, series, new Boolean(flag));
    }

    static int getRangeInt(String s, boolean flag)
        throws NumberFormatException
    {
        if(s.length() == 0)
            return -999;
        s.trim();
        StringTokenizer stringtokenizer = new StringTokenizer(s, ",:; ");
        if(!stringtokenizer.hasMoreTokens())
            return -999;
        String s1 = stringtokenizer.nextToken();
        if(!flag)
        {
            if(!stringtokenizer.hasMoreTokens())
                return -999;
            s1 = stringtokenizer.nextToken();
        }
        return Integer.valueOf(s1).intValue();
    }

    public JCTblConverter()
    {
    }

    public static final int FAILURE = 0x80000000;
    static final int ALIGNMENT = 1;
    static final int BOOLEAN = 2;
    static final int BORDERSIDES = 3;
    static final int BORDERTYPE = 4;
    static final int CHARHEIGHT = 5;
    static final int CHARWIDTH = 6;
    static final int COLOR = 7;
    static final int COMPONENT = 8;
    static final int DATATYPE = 9;
    static final int FONT = 10;
    static final int INT = 11;
    static final int PIXELHEIGHT = 12;
    static final int PIXELWIDTH = 13;
    static final int STRINGCASE = 14;
    public static final String enum_strings[][] = {
        {
            "Alignment", "topleft", "left", "top", "topcenter", "center", "topright", "right", "middleleft", "middlecenter", 
            "middleright", "bottomleft", "bottom", "bottomcenter", "bottomright"
        }, {
            "Attachment", "attach_cells", "attach_side"
        }, {
            "AutoScroll", "auto_scroll_none", "auto_scroll_row", "auto_scroll_column", "auto_scroll_both"
        }, {
            "Bordersides", "borderside_none", "borderside_left", "borderside_top", "borderside_bottom", "borderside_right", "borderside_all"
        }, {
            "Bordertype", "border_none", "border_etched_in", "border_etched_out", "border_in", "border_out", "border_plain", "border_frame_in", "border_frame_out"
        }, {
            "Resize", "resize_none", "resize_column", "resize_row", "resize_all"
        }, {
            "SelectionPolicy", "select_none", "select_single", "select_range", "select_multirange"
        }, {
            "StringCase", "case_as_is", "case_lower", "case_upper"
        }, {
            "ColumnPlacement", "place_left", "place_right"
        }, {
            "Datatype", "type_boolean", "type_date", "type_double", "type_float", "type_integer", "type_string"
        }, {
            "ScrollbarDisplay", "sbdisplay_as_needed", "sbdisplay_always", "sbdisplay_never"
        }, {
            "Jump", "jump_none", "jump_horizontal", "jump_vertical", "jump_all"
        }, {
            "Mode", "mode_list", "mode_table"
        }, {
            "ScrollbarPosition", "sbposition_cells", "sbposition_side"
        }, {
            "RowPlacement", "place_top", "place_bottom"
        }, {
            "ValidatePolicy", "validate_never", "validate_user_edit", "validate_set", "validate_always"
        }, {
            "EditPolicy", "edit_size_to_cell", "edit_ensure_minimum_size", "edit_ensure_preferred_size"
        }
    };
    public static final int enum_values[][] = {
        {
            0, 0, 0, 1, 1, 2, 2, 3, 4, 5, 
            6, 6, 7, 8
        }, {
            0, 1
        }, {
            0, 1, 2, 3
        }, {
            0, 1, 2, 4, 8, 15
        }, {
            0, 1, 2, 3, 4, 5, 6, 7
        }, {
            0, 2, 4, 1
        }, {
            0, 1, 2, 3
        }, {
            0, 1, 2
        }, {
            0, 2
        }, {
            0, 1, 2, 3, 4, 5
        }, {
            0, 1, 2
        }, {
            0, 1, 2, 3
        }, {
            1, 0
        }, {
            0, 1
        }, {
            0, 6
        }, {
            0, 1, 2, 3
        }, {
            0, 1, 2
        }
    };
    static boolean comma;
    static int value1;
    static int value2;

}
