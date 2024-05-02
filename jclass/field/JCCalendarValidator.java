// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarValidator.java

package jclass.field;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Vector;
import jclass.bwt.JCTextInterface;
import jclass.util.JCVector;

// Referenced classes of package jclass.field:
//            JCStringValidator, DateFormat, DateMask, JCValidInfo, 
//            JCValidator, ReturnableInt

public class JCCalendarValidator extends JCStringValidator
{
    class WorkingCalendar
        implements Cloneable, Serializable
    {

        private void create(Calendar calendar)
        {
            for(int i = 0; i < fields.length; i++)
                if(calendar.isSet(i))
                    fields[i] = calendar.get(i);
                else
                    fields[i] = -1;

        }

        public void clear()
        {
            for(int i = 0; i < fields.length; i++)
                fields[i] = -1;

        }

        public int get(int i)
        {
            return fields[i];
        }

        public void set(int i, int j)
        {
            fields[i] = j;
        }

        public boolean isSet(int i)
        {
            return fields[i] != -1;
        }

        public TimeZone getTimeZone()
        {
            return tz;
        }

        public void setTimeZone(TimeZone timezone)
        {
            tz = timezone;
        }

        public Calendar toCalendar()
        {
            Calendar calendar = Calendar.getInstance(tz, locale);
            calendar.set(get(1), get(2), get(5), get(11), get(12), get(13));
            if(false)
                System.out.println("cal.set(" + get(1) + ", " + get(2) + ", " + get(5) + ", " + get(11) + ", " + get(12) + ", " + get(13));
            return calendar;
        }

        public Object clone()
        {
            try
            {
                return super.clone();
            }
            catch(CloneNotSupportedException _ex)
            {
                return null;
            }
        }

        private int fields[];

        public WorkingCalendar()
        {
            fields = new int[17];
            Calendar calendar = Calendar.getInstance(tz, locale);
            create(calendar);
        }

        public WorkingCalendar(Calendar calendar)
        {
            fields = new int[17];
            create(calendar);
        }
    }


    public JCCalendarValidator()
    {
        mask_input = false;
        edit_formats = new JCVector(0);
        compiled_edit_formats = new JCVector(0);
        increment_field = 10;
        millenium_threshold = 70;
        default_detail = 2;
        spin_policy = 1;
        if(tz == null)
            tz = TimeZone.getDefault();
        if(cal == null)
            cal = new WorkingCalendar(Calendar.getInstance(tz, super.locale));
        symbols = new DateFormatSymbols(super.locale);
        super.increment = new Integer(1);
        setFormat(getDefaultFormat());
        setEditFormats(getDefaultEditFormats());
    }

    public JCCalendarValidator(String s, String s1, String as[], boolean flag, int i, boolean flag1)
    {
        this();
        if(s != null && s.length() != 0)
            try
            {
                setFormat(s);
            }
            catch(IllegalArgumentException _ex)
            {
                setFormat(getDefaultFormat());
            }
        setPlaceHolderChars(s1);
        setEditFormats(as);
        setMaskInput(flag);
        setDefaultDetail(i);
        setAllowNull(flag1);
    }

    public String getDefaultFormat(Locale locale)
    {
        MessageFormat messageformat = new MessageFormat(super.li.getString("DateTime"));
        String as[] = new String[2];
        switch(default_detail)
        {
        case 0: // '\0'
            as[0] = super.li.getString("FullDate");
            as[1] = super.li.getString("FullTime");
            break;

        case 1: // '\001'
            as[0] = super.li.getString("LongDate");
            as[1] = super.li.getString("LongTime");
            break;

        case 2: // '\002'
            as[0] = super.li.getString("MediumDate");
            as[1] = super.li.getString("MediumTime");
            break;

        case 3: // '\003'
            as[0] = super.li.getString("ShortDate");
            as[1] = super.li.getString("ShortTime");
            break;
        }
        return messageformat.format(as);
    }

    public String getDefaultFormat()
    {
        return getDefaultFormat(super.locale);
    }

    public int getDefaultDetail()
    {
        return default_detail;
    }

    public void setDefaultDetail(int i)
    {
        if(i != 0 && i != 1 && i != 2 && i != 3)
            throw new IllegalArgumentException("invalid value for defaultDetail");
        if(getFormat().equals(getDefaultFormat()))
        {
            default_detail = i;
            setFormat(getDefaultFormat());
            return;
        } else
        {
            default_detail = i;
            return;
        }
    }

    private static String[] getStringArrayCopy(JCVector jcvector)
    {
        String as[] = new String[jcvector.size()];
        for(int i = 0; i < jcvector.size(); i++)
            as[i] = (String)jcvector.elementAt(i);

        return as;
    }

    public String[] getDefaultEditFormats(Locale locale)
    {
        JCVector jcvector = new JCVector(0);
        ResourceBundle resourcebundle;
        if(super.locale == locale)
            resourcebundle = super.li;
        else
            resourcebundle = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale);
        String s = resourcebundle.getString("FullTime");
        String s1 = resourcebundle.getString("LongTime");
        String s2 = resourcebundle.getString("MediumTime");
        String s3 = resourcebundle.getString("ShortTime");
        String s4 = resourcebundle.getString("FullDate");
        String s5 = resourcebundle.getString("LongDate");
        String s6 = resourcebundle.getString("MediumDate");
        String s7 = resourcebundle.getString("ShortDate");
        MessageFormat messageformat = new MessageFormat(resourcebundle.getString("DateTime"));
        String as[] = {
            s4, s5, s6, s7
        };
        String as1[] = {
            s, s1, s2, s3
        };
        String as2[] = new String[2];
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                as2[0] = as[i];
                as2[1] = as1[j];
                jcvector.addElement(messageformat.format(as2));
            }

        }

        return getStringArrayCopy(jcvector);
    }

    public String[] getDefaultEditFormats()
    {
        return getDefaultEditFormats(super.locale);
    }

    public String[] getEditFormats()
    {
        return getStringArrayCopy(edit_formats);
    }

    public void setEditFormats(String as[])
    {
        if(as == null)
            return;
        edit_formats = new JCVector(0);
        edit_formats.copyFrom(as);
        compiled_edit_formats.setSize(0);
        for(int i = 0; i < as.length; i++)
            compiled_edit_formats.addElement(compileFormat(as[i]));

    }

    private int getSymbol(int i)
    {
        int j = 0;
        for(int k = 0; k < compiled_format.elements.length; k++)
        {
            j += compiled_format.getLength(k);
            if(i < j)
                return compiled_format.getSymbol(k);
        }

        return -1;
    }

    private int getIncrementField(int i, ReturnableInt returnableint)
    {
        byte byte0 = -1;
        if(returnableint != null)
            returnableint.value = 1;
        switch(i)
        {
        case 13: // '\r'
        case 14: // '\016'
            byte0 = 5;
            break;

        case 15: // '\017'
        case 16: // '\020'
            byte0 = 7;
            break;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
            byte0 = 2;
            break;

        case 23: // '\027'
        case 24: // '\030'
            byte0 = 1;
            break;

        case 2: // '\002'
            byte0 = 9;
            break;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            byte0 = 10;
            break;

        case 9: // '\t'
        case 10: // '\n'
            byte0 = 12;
            break;

        case 11: // '\013'
        case 12: // '\f'
            byte0 = 13;
            break;
        }
        return byte0;
    }

    public void inferSubField(JCTextInterface jctextinterface)
    {
        if(!mask_input || date_format == null)
            return;
        int l = jctextinterface.getSelectionStart();
        int i;
        if(l >= date_format.length())
            i = date_format.length() - 1;
        else
            i = l;
        ReturnableInt returnableint = new ReturnableInt();
        returnableint.value = 1;
        int k;
        for(k = -1; k == -1 && i >= 0; i--)
            k = getIncrementField(getSymbol(i), returnableint);

        for(int j = l; j < date_format.length() && k == -1; j++)
            k = getIncrementField(getSymbol(j), returnableint);

        if(k != -1)
        {
            setIncrementField(k);
            setIncrement(returnableint.value);
        }
    }

    public int getIncrement()
    {
        if(super.increment == null)
            return 0;
        else
            return ((Integer)super.increment).intValue();
    }

    public void setIncrement(int i)
    {
        super.increment = new Integer(i);
    }

    public int getIncrementField()
    {
        return increment_field;
    }

    public void setIncrementField(int i)
    {
        if(i < 0 && i >= 17)
        {
            throw new IllegalArgumentException("invalid value for incrementField");
        } else
        {
            increment_field = i;
            return;
        }
    }

    public int getMilleniumThreshold()
    {
        return millenium_threshold;
    }

    public void setMilleniumThreshold(int i)
    {
        if(i < 0 && i >= 100)
        {
            throw new IllegalArgumentException("invalid value for milleniumThreshold");
        } else
        {
            millenium_threshold = i;
            return;
        }
    }

    private void spinSubField(Calendar calendar, int i)
    {
        int j = increment_field;
        switch(j)
        {
        case 0: // '\0'
        case 3: // '\003'
        case 4: // '\004'
        case 8: // '\b'
        case 15: // '\017'
        case 16: // '\020'
            throw new IllegalArgumentException();

        case 9: // '\t'
            if(calendar.get(j) == 0)
            {
                calendar.set(j, 1);
                calendar.set(11, calendar.get(11) + 12);
                return;
            } else
            {
                calendar.set(j, 0);
                calendar.set(11, calendar.get(11) - 12);
                return;
            }

        case 6: // '\006'
            int k = (calendar.get(j) + i) % daysInYear(calendar.get(1));
            if(k == 0)
                k = daysInYear(calendar.get(1));
            calendar.set(j, k);
            return;

        case 7: // '\007'
            int l = (calendar.get(j) + i) % 7;
            if(l == 0)
                l = 7;
            calendar.set(j, l);
            return;

        case 10: // '\n'
            calendar.roll(11, i > 0);
            calendar.get(11);
            return;
        }
        calendar.roll(j, i > 0);
    }

    protected Object addIncrement(Object obj)
    {
        if(obj == null || super.increment == null)
            return null;
        int i = ((Integer)super.increment).intValue();
        if(i == 0)
            return null;
        Calendar calendar = (Calendar)obj;
        calendar = (Calendar)calendar.clone();
        if(spin_policy == 1)
            spinSubField(calendar, i);
        else
            calendar.add(increment_field, i);
        return calendar;
    }

    protected Object subtractIncrement(Object obj)
    {
        if(obj == null || super.increment == null)
            return null;
        int i = ((Integer)super.increment).intValue();
        if(i == 0)
            return null;
        Calendar calendar = (Calendar)obj;
        calendar = (Calendar)calendar.clone();
        if(spin_policy == 1)
            spinSubField(calendar, -i);
        else
            calendar.add(increment_field, -i);
        return calendar;
    }

    private void copy_fields(WorkingCalendar workingcalendar, WorkingCalendar workingcalendar1)
    {
        for(int i = 0; i < 17; i++)
            workingcalendar.set(i, workingcalendar1.get(i));

    }

    public void setLocale(Locale locale)
    {
        if(super.locale == locale)
            return;
        super.setLocale(locale);
        if(tz == null)
            tz = TimeZone.getDefault();
        cal = new WorkingCalendar(Calendar.getInstance(tz, locale));
        copy_fields(cal, cal);
        cal = cal;
        symbols = new DateFormatSymbols(locale);
    }

    public TimeZone getTimeZone()
    {
        return tz;
    }

    public void setTimeZone(TimeZone timezone)
    {
        tz = timezone;
        cal = new WorkingCalendar(Calendar.getInstance(tz, super.locale));
        copy_fields(cal, cal);
        cal = cal;
    }

    public boolean inRange(Object obj)
    {
        return true;
    }

    public void addEditFormat(String s)
    {
        edit_formats.addElement(s);
        compiled_edit_formats.addElement(compileFormat(s));
    }

    private String convertDateFormatToStringMask()
    {
        StringBuffer stringbuffer = new StringBuffer(date_format.length());
        if(compiled_format.size_ambiguous)
        {
            StringBuffer stringbuffer1 = new StringBuffer(date_format.length() + 10);
            for(int j = 0; j < compiled_format.elements.length; j++)
                if(compiled_format.elements[j].absolute_replacement == null)
                {
                    stringbuffer1.append(compiled_format.getChars(j));
                } else
                {
                    compiled_format.elements[j] = compiled_format.elements[j].absolute_replacement;
                    stringbuffer1.append(compiled_format.getChars(j));
                }

            date_format = stringbuffer1.toString();
        }
        for(int i = 0; i < compiled_format.elements.length; i++)
            if(compiled_format.getSymbol(i) != 1)
            {
                String as[] = compiled_format.getStrings(i);
                if(as == null || compiled_format.getSymbol(i) == 5 || compiled_format.getSymbol(i) == 6)
                {
                    for(int k = 0; k < compiled_format.getChars(i).length(); k++)
                        stringbuffer.append("@");

                    compiled_format.setLength(i, compiled_format.getChars(i).length());
                } else
                {
                    int l = as[0].length();
                    if(l == 0)
                    {
                        for(int i1 = 1; i1 < as.length; i1++)
                            l = Math.max(l, as[i1].length());

                    }
                    for(int j1 = 0; j1 < l; j1++)
                        stringbuffer.append("A");

                    compiled_format.setLength(i, as[0].length());
                }
            } else
            {
                stringbuffer.append(compiled_format.getChars(i));
                compiled_format.setLength(i, compiled_format.getChars(i).length());
            }

        return stringbuffer.toString();
    }

    public boolean getMaskInput()
    {
        return mask_input;
    }

    public void setMaskInput(boolean flag)
    {
        mask_input = flag;
        if(flag)
        {
            setMask(convertDateFormatToStringMask());
            return;
        } else
        {
            setMask("");
            return;
        }
    }

    public String getFormat()
    {
        return original_format;
    }

    public void setFormat(String s)
    {
        compiled_format = compileFormat(s);
        date_format = s;
        original_format = s;
        if(s != null)
        {
            if(mask_input)
            {
                setMask(convertDateFormatToStringMask());
                return;
            } else
            {
                setMask("");
                return;
            }
        } else
        {
            setMask("");
            return;
        }
    }

    public JCValidInfo validateText(String s)
    {
        boolean flag = false;
        JCValidInfo jcvalidinfo = new JCValidInfo();
        if(mask_input)
        {
            jcvalidinfo = super.validateText(s);
            if(!jcvalidinfo.valid)
                return jcvalidinfo;
            if(jcvalidinfo.value == null && getAllowNull())
                return jcvalidinfo;
        }
        WorkingCalendar workingcalendar;
        if(s.equalsIgnoreCase("now") || s.equalsIgnoreCase("today"))
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new java.util.Date());
            workingcalendar = new WorkingCalendar(calendar);
            flag = true;
        } else
        if(mask_input)
        {
            workingcalendar = new WorkingCalendar();
            flag = parseString(s, compiled_format.elements, workingcalendar);
        } else
        {
            workingcalendar = new WorkingCalendar();
            for(int i = 0; i < compiled_edit_formats.size(); i++)
            {
                flag = parseString(s, ((DateFormat)compiled_edit_formats.elementAt(i)).elements, workingcalendar);
                if(flag)
                    break;
            }

        }
        if(flag)
        {
            return internal_validate(workingcalendar);
        } else
        {
            jcvalidinfo.valid = false;
            jcvalidinfo.value = null;
            return jcvalidinfo;
        }
    }

    private boolean setDayOfWeek(WorkingCalendar workingcalendar, int i, int j, int k)
    {
        int l = dayOfWeek(i, j, k);
        if(workingcalendar.isSet(7))
        {
            if(l != workingcalendar.get(7))
                return false;
        } else
        {
            workingcalendar.set(7, l);
        }
        return true;
    }

    public Object copyValue(Object obj)
    {
        if(obj == null)
        {
            return null;
        } else
        {
            Calendar calendar = (Calendar)obj;
            return calendar.clone();
        }
    }

    public JCValidInfo validate(Object obj)
    {
        Calendar calendar = (Calendar)obj;
        if(obj == null)
            return internal_validate(null);
        else
            return internal_validate(new WorkingCalendar(calendar));
    }

    protected JCValidInfo internal_validate(WorkingCalendar workingcalendar)
    {
        JCValidInfo jcvalidinfo = new JCValidInfo();
        jcvalidinfo.value = workingcalendar;
        jcvalidinfo.valid = false;
        if(workingcalendar == null)
            if(super.allow_null)
            {
                jcvalidinfo.valid = true;
                return jcvalidinfo;
            } else
            {
                return jcvalidinfo;
            }
        Calendar calendar = Calendar.getInstance(tz, super.locale);
        calendar.setTime(new java.util.Date());
        WorkingCalendar workingcalendar1 = new WorkingCalendar(calendar);
        int i;
        if(workingcalendar.isSet(1))
        {
            i = workingcalendar.get(1);
        } else
        {
            i = workingcalendar1.get(1);
            workingcalendar.set(1, i);
        }
        int j = dayOfWeek(i, 0, 1);
        if(workingcalendar.isSet(3) && workingcalendar.isSet(7))
        {
            int k = workingcalendar.get(3);
            int k1 = workingcalendar.get(7);
            int k2 = (8 - j) + (k - 2) * 7 + k1;
            if(workingcalendar.isSet(6))
            {
                if(k2 != workingcalendar.get(6))
                    return jcvalidinfo;
            } else
            {
                workingcalendar.set(6, k2);
            }
        }
        if(workingcalendar.isSet(6))
        {
            int l = monthOfYear(i, workingcalendar.get(6));
            if(workingcalendar.isSet(2))
            {
                if(l != workingcalendar.get(2))
                    return jcvalidinfo;
            } else
            {
                workingcalendar.set(2, l);
            }
            int l1 = dayOfMonthOfYear(i, workingcalendar.get(6));
            if(workingcalendar.isSet(5))
            {
                if(l1 != workingcalendar.get(5))
                    return jcvalidinfo;
            } else
            {
                workingcalendar.set(5, l1);
            }
            if(!setDayOfWeek(workingcalendar, workingcalendar.get(1), workingcalendar.get(2), workingcalendar.get(5)))
                return jcvalidinfo;
            if(!workingcalendar.isSet(3))
                workingcalendar.set(3, weekOfYear(i, workingcalendar.get(6)));
            if(!workingcalendar.isSet(4))
                workingcalendar.set(4, weekOfMonth(i, workingcalendar.get(2), workingcalendar.get(5)));
        } else
        if(workingcalendar.isSet(2))
        {
            int i1 = workingcalendar.get(2);
            if(workingcalendar.isSet(5))
            {
                int i2 = workingcalendar.get(5);
                workingcalendar.set(6, dayOfYear(i, i1, i2));
                if(!setDayOfWeek(workingcalendar, i, i1, i2))
                    return jcvalidinfo;
                workingcalendar.set(3, weekOfYear(i, workingcalendar.get(6)));
                workingcalendar.set(4, weekOfMonth(i, i1, i2));
            } else
            if(workingcalendar.isSet(4) && workingcalendar.isSet(7))
                throw new IllegalStateException("Validation from only WeekOfMonth and DayOfWeek not currently supported");
        }
        if(workingcalendar.isSet(10) && workingcalendar.isSet(11) && workingcalendar.isSet(9))
        {
            if(workingcalendar.get(10) + 12 * workingcalendar.get(9) != workingcalendar.get(11))
                return jcvalidinfo;
        } else
        if(workingcalendar.isSet(10) && !workingcalendar.isSet(11))
        {
            if(!workingcalendar.isSet(9))
                if(workingcalendar.get(10) == 12)
                    workingcalendar.set(9, 1);
                else
                    workingcalendar.set(9, 0);
            setHourOfDayFromHour(workingcalendar);
        } else
        if(workingcalendar.isSet(11))
            setHourFromHourOfDay(workingcalendar);
        if(workingcalendar.isSet(11) || workingcalendar.isSet(12) || workingcalendar.isSet(13) || workingcalendar.isSet(14))
        {
            if(!workingcalendar.isSet(11))
            {
                workingcalendar.set(11, 0);
                setHourFromHourOfDay(workingcalendar);
            }
            if(!workingcalendar.isSet(12))
                workingcalendar.set(12, 0);
            if(!workingcalendar.isSet(13))
                workingcalendar.set(13, 0);
            if(!workingcalendar.isSet(14))
                workingcalendar.set(14, 0);
        } else
        {
            workingcalendar.set(11, workingcalendar1.get(11));
            setHourFromHourOfDay(workingcalendar);
            workingcalendar.set(12, workingcalendar1.get(12));
            workingcalendar.set(13, workingcalendar1.get(13));
            workingcalendar.set(14, workingcalendar1.get(14));
        }
        for(int j1 = 0; j1 < 17; j1++)
            if(!workingcalendar.isSet(j1))
                workingcalendar.set(j1, workingcalendar1.get(j1));

        int j2 = workingcalendar.get(2);
        if(!domain_check(j2 + 1, 19))
            return jcvalidinfo;
        int l2 = workingcalendar.get(6);
        if(l2 < 1 || l2 > daysInYear(i))
            return jcvalidinfo;
        int i3 = workingcalendar.get(5);
        if(i3 < 1 || i3 > daysInMonth(j2, i))
            return jcvalidinfo;
        if(!domain_check(workingcalendar.get(11), 7))
            return jcvalidinfo;
        if(!domain_check(workingcalendar.get(12), 9))
            return jcvalidinfo;
        if(!domain_check(workingcalendar.get(13), 11))
            return jcvalidinfo;
        if(!domain_check(workingcalendar.get(14), 25))
        {
            return jcvalidinfo;
        } else
        {
            jcvalidinfo.valid = true;
            jcvalidinfo.value = ((WorkingCalendar)jcvalidinfo.value).toCalendar();
            return jcvalidinfo;
        }
    }

    private void setHourFromHourOfDay(WorkingCalendar workingcalendar)
    {
        int i = workingcalendar.get(11);
        workingcalendar.set(9, i / 12);
        i %= 12;
        if(i == 0)
            i = 12;
        workingcalendar.set(10, i);
    }

    private void setHourOfDayFromHour(WorkingCalendar workingcalendar)
    {
        int i = workingcalendar.get(10) + 12 * workingcalendar.get(9);
        if(i == 12)
            i = 0;
        else
        if(i == 24)
            i = 12;
        workingcalendar.set(11, i);
    }

    private String formatDigits(int i, int j)
    {
        String s = Integer.toString(i);
        if(s.length() >= j)
            return s;
        StringBuffer stringbuffer = new StringBuffer(j);
        for(int k = 0; k < j - s.length(); k++)
            stringbuffer.append("0");

        stringbuffer.append(s);
        return stringbuffer.toString();
    }

    public String format(Object obj)
    {
        if(obj == null)
            return super.format(obj);
        if(!isSupported(obj))
            obj = convertToSupported(obj);
        Calendar calendar = (Calendar)obj;
        StringBuffer stringbuffer = new StringBuffer(30);
        for(int l2 = 0; l2 < compiled_format.elements.length; l2++)
            switch(compiled_format.getSymbol(l2))
            {
            default:
                break;

            case 5: // '\005'
                int i = calendar.get(compiled_format.getField(l2));
                if(i == 0)
                    i = 12;
                stringbuffer.append(Integer.toString(i));
                break;

            case 7: // '\007'
            case 9: // '\t'
            case 11: // '\013'
            case 13: // '\r'
                int j = calendar.get(compiled_format.getField(l2));
                stringbuffer.append(Integer.toString(j));
                break;

            case 19: // '\023'
                int k = calendar.get(compiled_format.getField(l2)) + 1;
                stringbuffer.append(Integer.toString(k));
                break;

            case 17: // '\021'
                int l = calendar.get(compiled_format.getField(l2)) + 1;
                stringbuffer.append(Integer.toString(l));
                break;

            case 24: // '\030'
                int i1 = calendar.get(compiled_format.getField(l2));
                stringbuffer.append(Integer.toString(i1));
                break;

            case 6: // '\006'
                int j1 = calendar.get(compiled_format.getField(l2));
                if(j1 == 0)
                    j1 = 12;
                stringbuffer.append(formatDigits(j1, 2));
                break;

            case 8: // '\b'
            case 10: // '\n'
            case 12: // '\f'
            case 14: // '\016'
                int k1 = calendar.get(compiled_format.getField(l2));
                stringbuffer.append(formatDigits(k1, 2));
                break;

            case 20: // '\024'
                int l1 = calendar.get(compiled_format.getField(l2)) + 1;
                stringbuffer.append(formatDigits(l1, 2));
                break;

            case 23: // '\027'
                int i2 = calendar.get(compiled_format.getField(l2));
                i2 %= 100;
                stringbuffer.append(formatDigits(i2, 2));
                break;

            case 18: // '\022'
                int j2 = calendar.get(compiled_format.getField(l2)) + 1;
                stringbuffer.append(formatDigits(j2, 3));
                break;

            case 2: // '\002'
            case 15: // '\017'
            case 16: // '\020'
            case 21: // '\025'
            case 22: // '\026'
                int k2 = calendar.get(compiled_format.getField(l2));
                stringbuffer.append(compiled_format.getString(l2, k2));
                break;

            case 3: // '\003'
            case 4: // '\004'
                int i3 = getZoneIndex();
                stringbuffer.append(compiled_format.getString(l2, i3));
                break;

            case 1: // '\001'
                stringbuffer.append(compiled_format.getChars(l2));
                break;
            }

        if(super.case_policy != 0)
        {
            for(int j3 = 0; j3 < stringbuffer.length(); j3++)
                stringbuffer.setCharAt(j3, convertCase(stringbuffer.charAt(j3)));

        }
        return stringbuffer.toString();
    }

    private int getZoneIndex()
    {
        String as[][] = symbols.getZoneStrings();
        for(int i = 0; i < as.length; i++)
            if(as[i][0].equals(tz.getID()))
                return i;

        return -1;
    }

    private String[] getZoneStrings(int i)
    {
        String as[][] = symbols.getZoneStrings();
        String as1[] = new String[as.length];
        for(int j = 0; j < as.length; j++)
            as1[j] = as[j][i];

        return as1;
    }

    private String[] getShortZoneStrings()
    {
        Calendar calendar = cal.toCalendar();
        if(tz.inDaylightTime(calendar.getTime()))
            return getZoneStrings(4);
        else
            return getZoneStrings(2);
    }

    private String[] getLongZoneStrings()
    {
        Calendar calendar = cal.toCalendar();
        if(tz.inDaylightTime(calendar.getTime()))
            return getZoneStrings(3);
        else
            return getZoneStrings(1);
    }

    private String[] getZoneCities()
    {
        return getZoneStrings(5);
    }

    private DateFormat compileFormat(String s)
    {
        Vector vector = new Vector();
        boolean flag = false;
        if(s == null || s.length() == 0)
            return null;
        int l2 = s.length();
        DateMask datemask;
        for(int i = 0; i < s.length(); vector.addElement(datemask))
        {
            datemask = new DateMask();
            switch(s.charAt(i))
            {
            case 100: // 'd'
                int j;
                for(j = 1; i + j != l2 && j < 2 && s.charAt(i + j) == 'd'; j++);
                datemask.chars = s.substring(i, i + j);
                i += j;
                datemask.field = 5;
                switch(j)
                {
                case 1: // '\001'
                    datemask.symbol = 13;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 14;
                    datemask.absolute_replacement.field = 5;
                    datemask.absolute_replacement.chars = "dd";
                    break;

                case 2: // '\002'
                    datemask.symbol = 14;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing day of month in \"" + s + "\"");
                }
                break;

            case 69: // 'E'
                int k;
                for(k = 1; i + k != l2 && k < 4 && s.charAt(i + k) == 'E'; k++);
                datemask.chars = s.substring(i, i + k);
                i += k;
                datemask.field = 7;
                switch(k)
                {
                case 1: // '\001'
                case 2: // '\002'
                    datemask.symbol = 15;
                    datemask.strings = symbols.getShortWeekdays();
                    break;

                case 3: // '\003'
                case 4: // '\004'
                    datemask.symbol = 16;
                    datemask.strings = symbols.getWeekdays();
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 15;
                    datemask.absolute_replacement.field = 7;
                    datemask.absolute_replacement.strings = symbols.getShortWeekdays();
                    datemask.absolute_replacement.chars = "EE";
                    break;

                default:
                    throw new IllegalArgumentException("error parsing week in \"" + s + "\"");
                }
                break;

            case 77: // 'M'
                int l;
                for(l = 1; i + l != l2 && l < 4 && s.charAt(i + l) == 'M'; l++);
                datemask.chars = s.substring(i, i + l);
                i += l;
                datemask.field = 2;
                switch(l)
                {
                case 1: // '\001'
                    datemask.symbol = 19;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 20;
                    datemask.absolute_replacement.field = 2;
                    datemask.absolute_replacement.chars = "MM";
                    break;

                case 2: // '\002'
                    datemask.symbol = 20;
                    break;

                case 3: // '\003'
                    datemask.symbol = 21;
                    datemask.strings = symbols.getShortMonths();
                    break;

                case 4: // '\004'
                    datemask.symbol = 22;
                    datemask.strings = symbols.getMonths();
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 21;
                    datemask.absolute_replacement.field = 2;
                    datemask.absolute_replacement.strings = symbols.getShortMonths();
                    datemask.absolute_replacement.chars = "MMM";
                    break;

                default:
                    throw new IllegalArgumentException("error parsing month in \"" + s + "\"");
                }
                break;

            case 89: // 'Y'
            case 121: // 'y'
                int i1;
                for(i1 = 1; i + i1 != l2 && i1 < 4 && i + i1 < s.length() && (s.charAt(i + i1) == 'Y' || s.charAt(i + i1) == 'y'); i1++);
                datemask.field = 1;
                datemask.chars = s.substring(i, i + i1);
                i += i1;
                switch(i1)
                {
                case 1: // '\001'
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 23;
                    datemask.absolute_replacement.field = 1;
                    datemask.absolute_replacement.chars = "YY";
                    // fall through

                case 2: // '\002'
                    datemask.symbol = 23;
                    break;

                case 3: // '\003'
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 24;
                    datemask.absolute_replacement.field = 1;
                    datemask.absolute_replacement.chars = "YYYY";
                    // fall through

                case 4: // '\004'
                    datemask.symbol = 24;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing year in \"" + s + "\"");
                }
                break;

            case 104: // 'h'
                int j1;
                for(j1 = 1; i + j1 != l2 && j1 < 2 && s.charAt(i + j1) == 'h'; j1++);
                datemask.field = 10;
                datemask.strings = symbols.getAmPmStrings();
                datemask.chars = s.substring(i, i + j1);
                i += j1;
                switch(j1)
                {
                case 1: // '\001'
                    datemask.symbol = 5;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 6;
                    datemask.absolute_replacement.field = 10;
                    datemask.absolute_replacement.chars = "hh";
                    break;

                case 2: // '\002'
                    datemask.symbol = 6;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing hour in \"" + s + "\"");
                }
                break;

            case 72: // 'H'
                int k1;
                for(k1 = 1; i + k1 != l2 && k1 < 2 && s.charAt(i + k1) == 'H'; k1++);
                datemask.field = 11;
                datemask.chars = s.substring(i, i + k1);
                i += k1;
                switch(k1)
                {
                case 1: // '\001'
                    datemask.symbol = 7;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 8;
                    datemask.absolute_replacement.field = 11;
                    datemask.absolute_replacement.chars = "HH";
                    break;

                case 2: // '\002'
                    datemask.symbol = 8;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing hour in \"" + s + "\"");
                }
                break;

            case 109: // 'm'
                int l1;
                for(l1 = 1; i + l1 != l2 && l1 < 2 && s.charAt(i + l1) == 'm'; l1++);
                datemask.field = 12;
                datemask.chars = s.substring(i, i + l1);
                i += l1;
                switch(l1)
                {
                case 1: // '\001'
                    datemask.symbol = 9;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 10;
                    datemask.absolute_replacement.field = 12;
                    datemask.absolute_replacement.chars = "mm";
                    break;

                case 2: // '\002'
                    datemask.symbol = 10;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing minute in \"" + s + "\"");
                }
                break;

            case 115: // 's'
                int i2;
                for(i2 = 1; i + i2 != l2 && i2 < 2 && i + i2 < s.length() && s.charAt(i + i2) == 's'; i2++);
                datemask.field = 13;
                datemask.chars = s.substring(i, i + i2);
                i += i2;
                switch(i2)
                {
                case 1: // '\001'
                    datemask.symbol = 11;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 12;
                    datemask.absolute_replacement.field = 13;
                    datemask.absolute_replacement.chars = "ss";
                    break;

                case 2: // '\002'
                    datemask.symbol = 12;
                    break;

                default:
                    throw new IllegalArgumentException("error parsing second in \"" + s + "\"");
                }
                break;

            case 97: // 'a'
            case 112: // 'p'
                datemask.symbol = 2;
                datemask.field = 9;
                datemask.strings = symbols.getAmPmStrings();
                datemask.chars = String.valueOf(s.charAt(i));
                i++;
                break;

            case 122: // 'z'
                int j2;
                for(j2 = 1; i + j2 != l2 && j2 < 4 && i + j2 < s.length() && s.charAt(i + j2) == 'z'; j2++);
                datemask.symbol = 4;
                datemask.field = -1;
                datemask.chars = s.substring(i, i + j2);
                i += j2;
                switch(j2)
                {
                case 1: // '\001'
                case 2: // '\002'
                    datemask.symbol = 3;
                    datemask.strings = getShortZoneStrings();
                    break;

                case 4: // '\004'
                    datemask.symbol = 4;
                    datemask.strings = getLongZoneStrings();
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 3;
                    datemask.absolute_replacement.field = -1;
                    datemask.absolute_replacement.strings = getShortZoneStrings();
                    datemask.absolute_replacement.chars = "zz";
                    break;

                case 3: // '\003'
                default:
                    throw new IllegalArgumentException("error parsing timezone in \"" + s + "\"");
                }
                break;

            case 68: // 'D'
                int k2;
                for(k2 = 1; i + k2 != l2 && k2 < 3 && s.charAt(i + k2) == 'D'; k2++);
                datemask.field = 6;
                datemask.chars = s.substring(i, i + k2);
                i += k2;
                switch(k2)
                {
                case 1: // '\001'
                    datemask.symbol = 17;
                    flag = true;
                    datemask.absolute_replacement = new DateMask();
                    datemask.absolute_replacement.symbol = 18;
                    datemask.absolute_replacement.field = 6;
                    datemask.absolute_replacement.chars = "DDD";
                    break;

                case 3: // '\003'
                    datemask.symbol = 18;
                    break;

                case 2: // '\002'
                default:
                    throw new IllegalArgumentException("error parsing day of year in \"" + s + "\"");
                }
                break;

            case 92: // '\\'
                boolean flag1 = true;
                i++;
                if(s.charAt(i) != 0)
                {
                    datemask.symbol = 1;
                    datemask.chars = String.valueOf(s.charAt(i));
                    i++;
                }
                break;

            default:
                datemask.symbol = 1;
                datemask.chars = String.valueOf(s.charAt(i));
                i++;
                break;
            }
        }

        DateMask adatemask[] = new DateMask[vector.size()];
        vector.copyInto(adatemask);
        DateFormat dateformat = new DateFormat();
        dateformat.elements = adatemask;
        dateformat.size_ambiguous = flag;
        return dateformat;
    }

    private static boolean domain_check(int i, int j)
    {
        switch(j)
        {
        case 5: // '\005'
        case 6: // '\006'
            return i >= 0 && i <= 12;

        case 7: // '\007'
        case 8: // '\b'
            return i >= 0 && i <= 23;

        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
            return i >= 0 && i <= 59;

        case 13: // '\r'
        case 14: // '\016'
            return i >= 1 && i <= 31;

        case 19: // '\023'
        case 20: // '\024'
            return i >= 1 && i <= 12;

        case 23: // '\027'
        case 24: // '\030'
            return i >= 0 && i <= 9999;

        case 17: // '\021'
        case 18: // '\022'
            return i >= 0 && i <= 365;

        case 25: // '\031'
            return i >= 0 && i <= 999;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 15: // '\017'
        case 16: // '\020'
        case 21: // '\025'
        case 22: // '\026'
        default:
            return false;
        }
    }

    private int patternSearch(String s, String as[])
    {
        int j = -1;
        boolean flag = false;
        if(s == null || s.length() == 0)
            return -1;
        for(int i = 0; i < as.length; i++)
        {
            StringBuffer stringbuffer = new StringBuffer(as[i]);
            int k;
            if((k = s.length()) < stringbuffer.length())
                stringbuffer.setLength(k);
            if(!stringbuffer.toString().equalsIgnoreCase(s))
                continue;
            if(flag)
                return -1;
            flag = true;
            j = i;
            break;
        }

        if(flag)
            return j;
        else
            return -1;
    }

    private int parseEnum(WorkingCalendar workingcalendar, String s, int i, int j, int k, String as[])
    {
        if(i >= s.length())
            return -1;
        int j1 = 0;
        int l;
        for(l = 0; l < as.length; l++)
            j1 = Math.max(j1, as[l].length());

        int k1 = -1;
        int l1 = 0;
        for(int i1 = i + 1; i1 - i <= j1; i1++)
        {
            int i2 = Math.min(i1, s.length());
            l = patternSearch(s.substring(i, i2), as);
            if(l != -1)
            {
                k1 = l;
                l1 = i2;
            }
        }

        if(k1 == -1)
            return -1;
        if(workingcalendar.isSet(k) && workingcalendar.get(k) != l)
        {
            return -1;
        } else
        {
            workingcalendar.set(k, k1);
            return l1;
        }
    }

    private int parseDigitField(WorkingCalendar workingcalendar, String s, int i, int j, int k, int l)
    {
        if(!Character.isDigit(s.charAt(i)) || i >= s.length())
            return -1;
        int i1;
        for(i1 = i; i1 < s.length() && Character.isDigit(s.charAt(i1)) && i1 - i < l; i1++);
        int j1 = Integer.parseInt(s.substring(i, i1));
        if(!domain_check(j1, j))
            return -1;
        if(workingcalendar.isSet(k) && workingcalendar.get(k) != j1)
        {
            return -1;
        } else
        {
            workingcalendar.set(k, j1);
            return i1;
        }
    }

    private int parseTimeZone(WorkingCalendar workingcalendar, String s, int i)
    {
        String as[][] = symbols.getZoneStrings();
        String s1 = "";
        String s2 = null;
        for(int j = 0; j < as.length; j++)
        {
            s1 = as[j][2];
            int k = Math.min(s.length(), i + s1.length());
            if(s.substring(i, k).equalsIgnoreCase(s1))
            {
                s2 = as[j][0];
                break;
            }
            s1 = as[j][3];
            k = Math.min(s.length(), i + s1.length());
            if(s.substring(i, k).equalsIgnoreCase(s1))
            {
                s2 = as[j][0];
                break;
            }
            s1 = as[j][4];
            k = Math.min(s.length(), i + s1.length());
            if(!s.substring(i, k).equalsIgnoreCase(s1))
                continue;
            s2 = as[j][0];
            break;
        }

        if(s2 == null)
        {
            String as1[] = TimeZone.getAvailableIDs();
            for(int l = 0; l < as1.length; l++)
            {
                s1 = as1[l];
                int i1 = Math.min(s.length(), i + s1.length());
                if(!s.substring(i, i1).equalsIgnoreCase(s1))
                    continue;
                s2 = as1[l];
                break;
            }

        }
        if(s2 == null)
        {
            return -1;
        } else
        {
            workingcalendar.setTimeZone(TimeZone.getTimeZone(s2));
            return i + s1.length();
        }
    }

    private int parseLiteral(WorkingCalendar workingcalendar, String s, int i, char c)
    {
        if(c == s.charAt(i))
            i++;
        else
        if(c != ' ')
            return -1;
        return i;
    }

    private boolean parseString(String s, DateMask adatemask[], WorkingCalendar workingcalendar)
    {
        if(s == null || s.length() == 0 || adatemask == null || adatemask.length == 0 && workingcalendar != null)
            return false;
        workingcalendar.clear();
        int i = 0;
        int j;
        for(j = 0; i < adatemask.length && j < s.length(); i++)
        {
            while(j < s.length() && Character.isSpaceChar(s.charAt(j))) 
                j++;
            switch(adatemask[i].symbol)
            {
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
                j = parseDigitField(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, 2);
                break;

            case 19: // '\023'
            case 20: // '\024'
                j = parseDigitField(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, 2);
                if(j == 0)
                    j = -1;
                if(j != -1)
                {
                    int k = workingcalendar.get(adatemask[i].field);
                    if(k == 0)
                    {
                        j = -1;
                    } else
                    {
                        k--;
                        workingcalendar.set(adatemask[i].field, k);
                    }
                }
                break;

            case 23: // '\027'
                j = parseDigitField(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, 2);
                if(j != -1)
                {
                    int l = workingcalendar.get(adatemask[i].field);
                    if(l < millenium_threshold)
                        l += 2000;
                    else
                        l += 1900;
                    workingcalendar.set(adatemask[i].field, l);
                }
                break;

            case 24: // '\030'
                j = parseDigitField(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, 4);
                break;

            case 17: // '\021'
            case 18: // '\022'
                j = parseDigitField(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, 3);
                break;

            case 2: // '\002'
            case 15: // '\017'
            case 16: // '\020'
            case 21: // '\025'
            case 22: // '\026'
                j = parseEnum(workingcalendar, s, j, adatemask[i].symbol, adatemask[i].field, adatemask[i].strings);
                break;

            case 3: // '\003'
            case 4: // '\004'
                j = parseTimeZone(workingcalendar, s, j);
                break;

            case 1: // '\001'
                j = parseLiteral(workingcalendar, s, j, adatemask[i].chars.charAt(0));
                break;
            }
            if(j == -1)
                return false;
        }

        for(; j < s.length() && Character.isSpaceChar(s.charAt(j)); j++);
        return j >= s.length();
    }

    public static boolean isLeap(int i)
    {
        if(i >= 0)
            return i % 4 == 0 && i % 100 != 0 || i % 400 == 0;
        else
            return false;
    }

    public static int dayOfYear(int i, int j, int k)
    {
        int i1 = isLeap(i) ? 1 : 0;
        for(int l = 0; l < j; l++)
            k += daytable[i1][l];

        return k;
    }

    public static int daysInMonth(int i, int j)
    {
        int k = isLeap(j) ? 1 : 0;
        return daytable[k][i];
    }

    public static int monthOfYear(int i, int j)
    {
        int l = isLeap(i) ? 1 : 0;
        int k;
        for(k = 0; j > daytable[l][k]; k++)
            j -= daytable[l][k];

        return k;
    }

    public static int dayOfMonthOfYear(int i, int j)
    {
        int l = isLeap(i) ? 1 : 0;
        for(int k = 0; j > daytable[l][k]; k++)
            j -= daytable[l][k];

        return j;
    }

    public static int daysInYear(int i)
    {
        return !isLeap(i) ? 365 : 366;
    }

    public static int dayOfWeek(int i, int j, int k)
    {
        long l = 0L;
        if(i > YEARMOD)
        {
            l = dayOfYear(i, j, k);
            for(int i1 = YEARMOD; i1 < i; i1++)
                l += daysInYear(i1);

        } else
        if(i == YEARMOD)
        {
            l = dayOfYear(i, j, k);
        } else
        {
            if(i < 0)
                return -1;
            l = daysInYear(i) - dayOfYear(i, j, k);
            for(int j1 = i + 1; j1 < YEARMOD; j1++)
                l += daysInYear(j1);

            int k1 = DAYMOD - (int)(l % 7L);
            if(k1 < 0)
                k1 += 7;
            return k1 + 1;
        }
        return (int)((l + (long)DAYMOD) % 7L) + 1;
    }

    public static int weekOfYear(int i, int j)
    {
        int k = dayOfWeek(i, 0, 0);
        return (j + (6 - k)) / 7 + 1;
    }

    public static int weekOfMonth(int i, int j, int k)
    {
        int l = dayOfWeek(i, j, 0);
        return (k + (6 - l)) / 7 + 1;
    }

    public boolean isSupported(Object obj)
    {
        return obj instanceof Calendar;
    }

    public Object convertToSupported(Object obj)
    {
        if(obj instanceof java.util.Date)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((java.util.Date)obj);
            return calendar;
        } else
        {
            return null;
        }
    }

    public Object convertFromSupported(Object obj, String s)
    {
        long l = ((Calendar)obj).getTime().getTime();
        if(s.equals("java.util.Date"))
            return new java.util.Date(l);
        if(s.equals("java.sql.Time"))
            return new Time(l);
        if(s.equals("java.sql.Timestamp"))
            return new Timestamp(l);
        if(s.equals("java.sql.Date"))
            return new Date(l);
        else
            return null;
    }

    static final int NOVALUE = -1;
    static final int NOTFOUND = -1;
    static final int PARSE_ERROR = -1;
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    static final int DATE_LITERAL = 1;
    static final int MERIDIEM = 2;
    static final int ABBREV_TIMEZONE = 3;
    static final int TIMEZONE = 4;
    static final int MERIDIEM_HOUR = 5;
    static final int ZERO_MERIDIEM_HOUR = 6;
    static final int MILITARY_HOUR = 7;
    static final int ZERO_MILITARY_HOUR = 8;
    static final int MINUTE = 9;
    static final int ZERO_MINUTE = 10;
    static final int SECOND = 11;
    static final int ZERO_SECOND = 12;
    static final int NUM_DAY = 13;
    static final int ZERO_NUM_DAY = 14;
    static final int ABBREV_ALPHA_DAY = 15;
    static final int ALPHA_DAY = 16;
    static final int DAY_OF_YEAR = 17;
    static final int ZERO_DAY_OF_YEAR = 18;
    static final int NUM_MONTH = 19;
    static final int ZERO_NUM_MONTH = 20;
    static final int ABBREV_ALPHA_MONTH = 21;
    static final int ALPHA_MONTH = 22;
    static final int SHORT_YEAR = 23;
    static final int LONG_YEAR = 24;
    static final int MILLISECOND = 25;
    static final int AMSET = -2;
    static final int PMSET = -3;
    static final int ZONE_ID = 0;
    static final int ZONE_STANDARD_LONG = 1;
    static final int ZONE_STANDARD_SHORT = 2;
    static final int ZONE_DAYLIGHT_LONG = 3;
    static final int ZONE_DAYLIGHT_SHORT = 4;
    static final int ZONE_CITY_NAME = 5;
    static final byte daytable[][] = {
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
            30, 31
        }, {
            31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 
            30, 31
        }
    };
    protected boolean mask_input;
    protected String date_format;
    protected DateFormat compiled_format;
    protected TimeZone tz;
    protected WorkingCalendar cal;
    protected DateFormatSymbols symbols;
    protected JCVector edit_formats;
    protected JCVector compiled_edit_formats;
    protected int increment_field;
    protected int millenium_threshold;
    protected int default_detail;
    protected int spin_policy;
    protected String original_format;
    private static final boolean TRACE = false;
    private static int YEARMOD = 1997;
    private static int DAYMOD = 2;


}
