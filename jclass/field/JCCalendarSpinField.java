// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarSpinField.java

package jclass.field;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import jclass.bwt.JCTextInterface;

// Referenced classes of package jclass.field:
//            JCFieldSpin, JCCalendarValidator, JCField, JCSpin, 
//            JCStringValidator, JCValidator

public class JCCalendarSpinField extends JCFieldSpin
{

    public JCCalendarSpinField()
    {
        this(new JCCalendarValidator());
    }

    public JCCalendarSpinField(Date date)
    {
        this(new JCCalendarValidator(), date);
    }

    public JCCalendarSpinField(long l)
    {
        this(new JCCalendarValidator(), l);
    }

    public JCCalendarSpinField(JCCalendarValidator jccalendarvalidator)
    {
        Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
        Date date = new Date();
        calendar.setTime(date);
        initSpinField(jccalendarvalidator, null, calendar);
    }

    public JCCalendarSpinField(JCCalendarValidator jccalendarvalidator, Date date)
    {
        Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
        calendar.setTime(date);
        initSpinField(jccalendarvalidator, calendar, calendar);
    }

    public JCCalendarSpinField(JCCalendarValidator jccalendarvalidator, long l)
    {
        Date date = new Date(l);
        Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
        calendar.setTime(date);
        initSpinField(jccalendarvalidator, calendar, calendar);
    }

    void initSpinField(JCCalendarValidator jccalendarvalidator, Calendar calendar, Calendar calendar1)
    {
        createField(jccalendarvalidator, calendar, calendar1);
        super.field.setIncrementPolicy(1);
        text_field = getTextField();
        text_field.setOverstrike(true);
        ((JCCalendarValidator)super.field.getValidator()).setMaskInput(true);
    }

    public void addNotify()
    {
        super.addNotify();
        super.field.addNotify();
    }

    public void setValue(Calendar calendar)
    {
        super.setValue(calendar);
    }

    public Calendar getValue()
    {
        return (Calendar)super.field.getValue();
    }

    public void setValue(Date date)
    {
        if(date == null)
        {
            setValue(((Calendar) (null)));
            return;
        } else
        {
            JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
            Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
            calendar.setTime(date);
            super.setValue(calendar);
            return;
        }
    }

    public void setValue(long l)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
        Date date = new Date(l);
        calendar.setTime(date);
        super.setValue(calendar);
    }

    public String getFormat()
    {
        return ((JCCalendarValidator)super.field.getValidator()).getFormat();
    }

    public void setFormat(String s)
    {
        ((JCCalendarValidator)super.field.getValidator()).setFormat(s);
        super.field.setValue(super.field.internal_value);
        if(isDisplayable())
            super.field.validate(null);
    }

    public String getPlaceHolderChars()
    {
        return ((JCCalendarValidator)super.field.getValidator()).getPlaceHolderChars();
    }

    public void setPlaceHolderChars(String s)
    {
        ((JCCalendarValidator)super.field.getValidator()).setPlaceHolderChars(s);
        super.field.setValue(super.field.internal_value);
        if(isDisplayable())
            super.field.validate(null);
    }

    public int getDefaultDetail()
    {
        return ((JCCalendarValidator)super.field.getValidator()).getDefaultDetail();
    }

    public void setDefaultDetail(int i)
    {
        ((JCCalendarValidator)super.field.getValidator()).setDefaultDetail(i);
        super.field.setValue(super.field.internal_value);
    }

    public String[] getEditFormats()
    {
        return ((JCCalendarValidator)super.field.getValidator()).getEditFormats();
    }

    public void setEditFormats(String as[])
    {
        ((JCCalendarValidator)super.field.getValidator()).setEditFormats(as);
    }

    public void addEditFormat(String s)
    {
        ((JCCalendarValidator)super.field.getValidator()).addEditFormat(s);
    }

    public Calendar getDefaultValue()
    {
        return (Calendar)super.field.getDefaultValue();
    }

    public void setDefaultValue(Calendar calendar)
    {
        super.field.setDefaultValue(calendar);
    }

    protected JCTextInterface text_field;
}
