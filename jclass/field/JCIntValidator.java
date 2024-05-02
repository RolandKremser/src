// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCIntValidator.java

package jclass.field;

import java.io.PrintStream;
import java.text.*;
import java.util.*;
import jclass.bwt.JCTextEvent;
import jclass.bwt.JCTextInterface;

// Referenced classes of package jclass.field:
//            JCNumberValidator, JCValidInfo, JCValidator

public class JCIntValidator extends JCNumberValidator
{

    public JCIntValidator()
    {
        super.setMax(new Integer(0x7fffffff));
        super.setMin(new Integer(0x80000000));
        setIncrement(1);
    }

    public JCIntValidator(Number number, Number number1, Number anumber[], boolean flag, String as[], Integer integer, String s, 
            boolean flag1)
    {
        setMax(number1);
        setMin(number);
        setPickList(anumber);
        setMatchPickList(flag);
        setDisplayList(as);
        setIncrement(integer);
        if(s != null)
            setDisplayPattern(s);
        setAllowNull(flag1);
    }

    public void setMax(int i)
    {
        setMax(new Integer(i));
    }

    public void setMin(int i)
    {
        setMin(new Integer(i));
    }

    protected Object addIncrement(Object obj)
    {
        if(obj == null || super.increment == null)
            return null;
        int i = ((Number)obj).intValue() + ((Number)super.increment).intValue();
        if(i > ((Number)getMax()).intValue())
            return null;
        else
            return new Integer(i);
    }

    protected void setPatterns()
    {
        super.display_pattern = super.li.getString("DecimalPattern");
        super.edit_pattern = "0";
    }

    protected DecimalFormat createDisplayFormat(Locale locale)
    {
        DecimalFormat decimalformat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.getDefault());
        decimalformat.setParseIntegerOnly(true);
        return decimalformat;
    }

    protected DecimalFormat createEditFormat(Locale locale)
    {
        DecimalFormat decimalformat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.getDefault());
        decimalformat.setParseIntegerOnly(true);
        return decimalformat;
    }

    protected Object subtractIncrement(Object obj)
    {
        if(obj == null || super.increment == null)
            return null;
        int i = ((Number)obj).intValue() - ((Number)super.increment).intValue();
        if(i < ((Number)getMin()).intValue())
            return null;
        else
            return new Integer(i);
    }

    public Integer getIncrement()
    {
        return (Integer)super.increment;
    }

    public void setIncrement(Integer integer)
    {
        if(integer.intValue() <= 0)
        {
            throw new IllegalArgumentException("increment <= 0");
        } else
        {
            super.increment = integer;
            return;
        }
    }

    public void setIncrement(int i)
    {
        setIncrement(new Integer(i));
    }

    public boolean inRange(int i)
    {
        return i >= ((Number)getMin()).intValue() && i <= ((Number)getMax()).intValue();
    }

    public boolean inRange(Number number)
    {
        return inRange(number.intValue());
    }

    public boolean inRange(Object obj)
    {
        if(obj instanceof Number)
            return inRange(((Number)obj).intValue());
        else
            return false;
    }

    public void changeText(JCTextEvent jctextevent)
    {
        Object obj = null;
        StringBuffer stringbuffer1 = new StringBuffer(50);
        super.changeText(jctextevent);
        String s1 = ((JCTextInterface)jctextevent.getSource()).getText();
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        if(jctextevent.getStartPosition() == 0)
            flag2 = true;
        else
        if(s1 != null && s1.length() > 1)
        {
            char c = s1.charAt(s1.length() - 1);
            if(c == super.symbols.getMinusSign())
                flag3 = true;
            else
            if(c == '+')
                flag3 = true;
            else
            if(c == ')')
                flag3 = true;
        }
        if(s1.indexOf(super.symbols.getMinusSign()) != -1)
            flag = true;
        else
        if(s1.indexOf('+') != -1)
            flag = true;
        else
        if(s1.indexOf('(') != -1)
        {
            flag = true;
            flag1 = true;
        }
        String s = jctextevent.getText();
        if(s != null && s.length() != 0)
        {
            StringBuffer stringbuffer = checkValidInvalid(s);
            if(stringbuffer != null && stringbuffer.length() != 0)
            {
                for(int i = 0; i < stringbuffer.length(); i++)
                {
                    char c1 = stringbuffer.charAt(i);
                    if(!flag3)
                        if(super.display_list != null)
                            stringbuffer1.append(c1);
                        else
                        if(Character.isDigit(c1))
                            stringbuffer1.append(c1);
                        else
                        if(!flag && c1 == super.symbols.getMinusSign())
                            stringbuffer1.append(c1);
                        else
                        if((c1 == '(' || c1 == ')') && super.edit_pattern.indexOf('(') != -1)
                            if(c1 == '(' && flag2)
                                stringbuffer1.append(c1);
                            else
                            if(c1 == ')' && flag1)
                                stringbuffer1.append(c1);
                }

            }
            jctextevent.setText(stringbuffer1.toString());
            if(stringbuffer1.length() == 0)
                jctextevent.setAllowChange(false);
        }
    }

    public JCValidInfo validateText(String s)
    {
        JCValidInfo jcvalidinfo = parseFromDisplayList(s);
        if(jcvalidinfo.valid)
            return jcvalidinfo;
        if((jcvalidinfo.value instanceof Integer) && ((Integer)jcvalidinfo.value).intValue() != -1)
        {
            jcvalidinfo.valid = true;
            return jcvalidinfo;
        }
        jcvalidinfo.value = null;
        jcvalidinfo.valid = false;
        if((s == null || s.length() == 0) && getAllowNull())
        {
            jcvalidinfo.valid = true;
            return jcvalidinfo;
        }
        if(s.length() >= Math.min(minstring.length(), maxstring.length()))
        {
            String s1;
            if(s.indexOf(super.symbols.getMinusSign()) != -1 || s.indexOf('(') != -1)
                s1 = minstring;
            else
                s1 = maxstring;
            StringBuffer stringbuffer = new StringBuffer(s.length());
            for(int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if(Character.isDigit(c))
                    stringbuffer.append(c);
            }

            if(stringbuffer.length() > s1.length())
                return jcvalidinfo;
            if(stringbuffer.length() == s1.length())
            {
                for(int j = 0; j < stringbuffer.length(); j++)
                    if(Integer.parseInt(String.valueOf(stringbuffer.charAt(j))) > Integer.parseInt(String.valueOf(s1.charAt(j))))
                        return jcvalidinfo;

            }
        }
        ParsePosition parseposition = new ParsePosition(0);
        Number number = super.editFormat.parse(s, parseposition);
        if(number != null && (number.longValue() < ((Number)super.min).longValue() || number.longValue() > ((Number)super.max).longValue()))
            jcvalidinfo.valid = false;
        else
        if(parseposition.getIndex() == 0)
        {
            if(!getAllowNull())
                jcvalidinfo.valid = false;
            else
                jcvalidinfo.valid = true;
        } else
        {
            jcvalidinfo.valid = true;
            jcvalidinfo.value = new Integer(number.intValue());
        }
        if(!jcvalidinfo.valid)
            return jcvalidinfo;
        else
            return validate(jcvalidinfo.value);
    }

    public JCValidInfo validate(Object obj)
    {
        JCValidInfo jcvalidinfo = super.validate(obj);
        if(!jcvalidinfo.valid)
            return jcvalidinfo;
        if(jcvalidinfo.value != null && !inRange(obj))
        {
            jcvalidinfo.valid = false;
            return jcvalidinfo;
        } else
        {
            return jcvalidinfo;
        }
    }

    public String formatForEdit(Object obj)
    {
        if(obj == null)
            return "";
        JCValidInfo jcvalidinfo = formatFromDisplayList(obj);
        if(jcvalidinfo.valid)
            return (String)jcvalidinfo.value;
        if(super.display_list != null && super.display_list.length > 0)
        {
            int i = ((Number)obj).intValue();
            if(i >= 0 && i < super.display_list.length)
                return super.display_list[i];
        }
        return super.editFormat.format(obj);
    }

    public boolean hasEditFormat()
    {
        return true;
    }

    public String format(Object obj)
    {
        if(obj == null)
            return "";
        if(!isSupported(obj))
            obj = convertToSupported(obj);
        JCValidInfo jcvalidinfo = formatFromDisplayList(obj);
        if(jcvalidinfo.valid)
            return (String)jcvalidinfo.value;
        if(super.display_list != null && super.display_list.length > 0)
        {
            int i = ((Number)obj).intValue();
            if(i >= 0 && i < super.display_list.length)
                return super.display_list[i];
        }
        return super.displayFormat.format(obj);
    }

    public boolean isSupported(Object obj)
    {
        return obj instanceof Integer;
    }

    public Object convertToSupported(Object obj)
    {
        if(obj instanceof Number)
            return new Integer(((Number)obj).intValue());
        else
            return null;
    }

    public Object convertFromSupported(Object obj, String s)
    {
        Integer integer = (Integer)obj;
        if(s.equals("java.lang.Byte"))
            return new Byte(integer.byteValue());
        if(s.equals("java.lang.Short"))
            return new Short(integer.shortValue());
        if(s.equals("java.lang.Long"))
            return new Long(integer.longValue());
        else
            return null;
    }

    private static final boolean TRACE = false;
    private static final String minstring = Integer.toString(0x80000000);
    private static final String maxstring = Integer.toString(0x7fffffff);

}
