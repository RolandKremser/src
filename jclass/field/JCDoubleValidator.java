// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCDoubleValidator.java

package jclass.field;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;
import jclass.bwt.*;

// Referenced classes of package jclass.field:
//            JCNumberValidator, JCValidInfo, JCValidator

public class JCDoubleValidator extends JCNumberValidator
{

    public JCDoubleValidator()
    {
        super.setMax(new Double(1.7E+100D));
        super.setMin(new Double(-1.7E+100D));
        setIncrement(1.0D);
    }

    public JCDoubleValidator(Double double1, Double double2, Double double3, String s, boolean flag)
    {
        setMax(double2);
        setMin(double1);
        setIncrement(double3);
        if(s != null)
            setDisplayPattern(s);
        setAllowNull(flag);
    }

    public void setMax(double d)
    {
        setMax(new Double(d));
    }

    public void setMin(double d)
    {
        setMin(new Double(d));
    }

    protected DecimalFormat createDisplayFormat(Locale locale)
    {
        return (DecimalFormat)NumberFormat.getNumberInstance(Locale.getDefault());
    }

    protected DecimalFormat createEditFormat(Locale locale)
    {
        return (DecimalFormat)NumberFormat.getNumberInstance(Locale.getDefault());
    }

    protected void setPatterns()
    {
        super.display_pattern = super.li.getString("DecimalPattern");
        super.edit_pattern = "0.###";
    }

    public void setCursor(JCTextCursorEvent jctextcursorevent)
    {
        jctextcursorevent.getNewPosition();
    }

    protected Object addIncrement(Object obj)
    {
        if(super.increment == null || obj == null)
            return null;
        double d = ((Number)obj).doubleValue() + ((Number)super.increment).doubleValue();
        if(d > ((Number)getMax()).doubleValue())
            return null;
        else
            return new Double(d);
    }

    protected Object subtractIncrement(Object obj)
    {
        if(super.increment == null || obj == null)
            return null;
        double d = ((Number)obj).doubleValue() - ((Number)super.increment).doubleValue();
        if(d < ((Number)getMin()).doubleValue())
            return null;
        else
            return new Double(d);
    }

    public Double getIncrement()
    {
        return (Double)super.increment;
    }

    public void setIncrement(Double double1)
    {
        if(double1.doubleValue() <= 0.0D)
        {
            throw new IllegalArgumentException("increment <= 0.0");
        } else
        {
            super.increment = double1;
            return;
        }
    }

    public void setIncrement(double d)
    {
        setIncrement(new Double(d));
    }

    public boolean inRange(double d)
    {
        return d >= ((Number)getMin()).doubleValue() && d <= ((Number)getMax()).doubleValue();
    }

    public boolean inRange(Number number)
    {
        return inRange(number.doubleValue());
    }

    public boolean inRange(Object obj)
    {
        if(obj instanceof Number)
            return inRange(((Number)obj).doubleValue());
        else
            return false;
    }

    public void changeText(JCTextEvent jctextevent)
    {
        Object obj = null;
        StringBuffer stringbuffer1 = new StringBuffer(50);
        boolean flag = false;
        String s1 = ((JCTextInterface)jctextevent.getSource()).getText();
        if(s1.indexOf(super.symbols.getDecimalSeparator()) != -1)
        {
            flag = true;
            boolean flag1 = jctextevent.getEndPosition() - jctextevent.getStartPosition() > 0;
            if(flag1)
            {
                int j = s1.substring(jctextevent.getStartPosition(), jctextevent.getEndPosition()).indexOf(super.symbols.getDecimalSeparator());
                if(j != -1)
                    flag = false;
            }
        }
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        if(jctextevent.getStartPosition() == 0)
            flag4 = true;
        else
        if(s1 != null && s1.length() > 1)
        {
            char c = s1.charAt(s1.length() - 1);
            if(c == super.symbols.getMinusSign())
                flag5 = true;
            else
            if(c == '+')
                flag5 = true;
            else
            if(c == ')')
                flag5 = true;
        }
        if(s1.indexOf(super.symbols.getMinusSign()) != -1)
            flag2 = true;
        else
        if(s1.indexOf('+') != -1)
            flag2 = true;
        else
        if(s1.indexOf('(') != -1)
        {
            flag2 = true;
            flag3 = true;
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
                    if(!flag5)
                        if(Character.isDigit(c1))
                            stringbuffer1.append(c1);
                        else
                        if(!flag && c1 == super.symbols.getDecimalSeparator())
                            stringbuffer1.append(c1);
                        else
                        if(!flag2 && c1 == super.symbols.getMinusSign())
                            stringbuffer1.append(c1);
                        else
                        if((c1 == '(' || c1 == ')') && super.edit_pattern.indexOf("(") != -1)
                            if(c1 == '(' && flag4)
                                stringbuffer1.append(c1);
                            else
                            if(c1 == ')' && flag3)
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
        jcvalidinfo.value = null;
        if((s == null || s.length() == 0) && getAllowNull())
        {
            jcvalidinfo.valid = true;
            return jcvalidinfo;
        }
        ParsePosition parseposition = new ParsePosition(0);
        Number number = super.editFormat.parse(s, parseposition);
        if(parseposition.getIndex() == 0)
        {
            if(!getAllowNull())
                jcvalidinfo.valid = false;
            else
                jcvalidinfo.valid = true;
        } else
        {
            jcvalidinfo.valid = true;
            if(number instanceof Double)
                jcvalidinfo.value = number;
            else
                jcvalidinfo.value = new Double(number.doubleValue());
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
        String s = null;
        JCValidInfo jcvalidinfo = formatFromDisplayList(obj);
        if(jcvalidinfo.valid)
            s = (String)jcvalidinfo.value;
        else
            s = super.editFormat.format(obj);
        return s;
    }

    public boolean hasEditFormat()
    {
        return true;
    }

    public String format(Object obj)
    {
        if(obj != null && !isSupported(obj))
            obj = convertToSupported(obj);
        JCValidInfo jcvalidinfo = formatFromDisplayList(obj);
        if(jcvalidinfo.valid)
            return (String)jcvalidinfo.value;
        if(obj == null)
            return "";
        else
            return super.displayFormat.format(obj);
    }

    public boolean isSupported(Object obj)
    {
        return obj instanceof Double;
    }

    public Object convertToSupported(Object obj)
    {
        if(obj instanceof Number)
            return new Double(((Number)obj).doubleValue());
        if(obj instanceof BigDecimal)
            return new Double(((BigDecimal)obj).doubleValue());
        else
            return null;
    }

    public Object convertFromSupported(Object obj, String s)
    {
        Double double1 = (Double)obj;
        if(s.equals("java.lang.Float"))
            return new Float(double1.floatValue());
        if(s.equals("java.math.BigDecimal"))
            return new BigDecimal(double1.doubleValue());
        else
            return null;
    }

    public static final double SAFE_DOUBLE_MAX = 1.7E+100D;
    public static final double SAFE_DOUBLE_MIN = -1.7E+100D;
    private static final boolean TRACE = false;
}
