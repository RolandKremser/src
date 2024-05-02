// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendar.java

package jclass.field;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormatSymbols;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import jclass.base.BaseComponent;
import jclass.base.TransientComponent;
import jclass.bwt.*;
import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.field:
//            JCCalendarComponent, JCCalendarEvent, JCCalendarListener, JCCalendarSpinField, 
//            JCFieldListener, JCFieldSpin, JCIntSpinField, JCSpin, 
//            JCTimeSpinField, JCValidator, JCVersion, JCFieldEvent, 
//            JCDateMonthYear, JCDayOfWeek, JCMonthDayOfMonth, JCMonthWeekDayOfWeek

public class JCCalendar extends JCContainer
    implements JCFieldListener, JCCalendarListener, FocusListener
{

    public void addNotify()
    {
        super.addNotify();
        calculateSpinYearSize();
        calculateSpinMonthSize();
        if(!hide_time_spinner)
            calculateSpinTimeSize();
    }

    protected void calculateContainerSize()
    {
        calculateSpinYearSize();
        spin_year.setSize(spin_year.getTextField().getPreferredSize());
        calculateSpinMonthSize();
        spin_month.setSize(spin_month.getTextField().getPreferredSize());
        if(!hide_time_spinner)
        {
            calculateSpinTimeSize();
            spin_time.setSize(spin_time.getTextField().getPreferredSize());
        }
        updateParent();
    }

    protected void calculateSpinYearSize()
    {
        FontMetrics fontmetrics = getFontMetrics(spin_year.getFont());
        int i = 0;
        int j = fontmetrics.getHeight();
        if(i < fontmetrics.stringWidth("0000"))
            i = fontmetrics.stringWidth("0000");
        spin_year.getTextField().setPreferredSize(i, j);
    }

    protected void calculateSpinMonthSize()
    {
        FontMetrics fontmetrics = getFontMetrics(spin_month.getFont());
        int i = 0;
        int j = fontmetrics.getHeight();
        for(int k = 0; k < months.length; k++)
            if(i < fontmetrics.stringWidth(months[k]))
                i = fontmetrics.stringWidth(months[k]);

        spin_month.getTextField().setPreferredSize(i, j);
    }

    protected void calculateSpinTimeSize()
    {
        FontMetrics fontmetrics = getFontMetrics(spin_time.getFont());
        int i = fontmetrics.getHeight();
        int j = fontmetrics.stringWidth("00:00:00");
        spin_time.getTextField().setPreferredSize(j, i);
    }

    public JCCalendar()
    {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    public JCCalendar(TimeZone timezone)
    {
        this(timezone, Locale.getDefault());
    }

    public JCCalendar(Locale locale)
    {
        this(TimeZone.getDefault(), locale);
    }

    public JCCalendar(TimeZone timezone, Locale locale)
    {
        this(Calendar.getInstance(timezone, locale), locale);
    }

    public JCCalendar(Calendar calendar1, Locale locale)
    {
        max_roll_date = Calendar.getInstance();
        min_roll_date = Calendar.getInstance();
        hide_time_spinner = false;
        container = new JPanel();
        DateFormatSymbols dateformatsymbols = new DateFormatSymbols(locale);
        months = dateformatsymbols.getMonths();
        super.setLayout(new BorderLayout());
        spin_month = new JCIntSpinField();
        spin_year = new JCIntSpinField();
        spin_time = new JCTimeSpinField();
        Calendar calendar2;
        if(calendar1 == null)
            calendar2 = Calendar.getInstance(locale);
        else
            calendar2 = calendar1;
        calendar = new JCCalendarComponent(calendar2, locale);
        calendar.addCalendarListener(this);
        calendar.addFocusListener(this);
        max_roll_date.set(0x7fffffff, 11, 31);
        min_roll_date.set(0, 0, 1);
        spin_month.setDisplayList(months);
        spin_month.setRange(0, 11);
        spin_month.setEditable(false);
        spin_month.setValue(calendar2.get(2));
        spin_month.setIncrement(1);
        spin_month.setSpinPolicy(2);
        spin_month.addFieldListener(this);
        spin_month.getTextField().setShowCursorPosition(false);
        spin_year.setRange(0, 0x7fffffff);
        spin_year.setDisplayPattern("#");
        spin_year.setValue(calendar2.get(1));
        spin_year.setIncrement(1);
        spin_year.setMin(1);
        spin_year.addFieldListener(this);
        spin_year.getTextField().addFocusListener(this);
        spin_time.setValue(calendar2.getTime());
        spin_time.getTextField().setAlignment(1);
        spin_time.addFieldListener(this);
        container.setLayout(new BorderLayout());
        container.add("West", spin_month);
        container.add("East", spin_year);
        add("North", container);
        add("Center", calendar);
        add("South", spin_time);
    }

    public void setBorder(Border border)
    {
        super.swing_border = border;
        setChildrenBorder(border);
        spin_year.setBorder(border);
        spin_month.setBorder(border);
        spin_time.setBorder(border);
    }

    public void valueChangedEnd(JCFieldEvent jcfieldevent)
    {
        Calendar calendar1 = calendar.getValue();
        int i = calendar1.get(1);
        int j = calendar1.get(2);
        int k = calendar1.get(5);
        if(jcfieldevent.getSource() == spin_year)
        {
            calendar.setSelectedDate(((Integer)jcfieldevent.getValue()).intValue(), j, k);
            return;
        }
        if(jcfieldevent.getSource() == spin_month)
        {
            calendar.setSelectedDate(i, ((Integer)jcfieldevent.getValue()).intValue(), k);
            return;
        } else
        {
            Calendar calendar2 = spin_time.getValue();
            calendar1.set(10, calendar2.get(10));
            calendar1.set(11, calendar2.get(11));
            calendar1.set(12, calendar2.get(12));
            calendar1.set(13, calendar2.get(13));
            calendar1.set(9, calendar2.get(9));
            calendar.setValue(calendar1);
            return;
        }
    }

    public void valueChangedBegin(JCFieldEvent jcfieldevent)
    {
    }

    public void stateIsInvalid(JCFieldEvent jcfieldevent)
    {
    }

    public void dateChanged(JCCalendarEvent jccalendarevent)
    {
        int i = jccalendarevent.getCalendar().get(1);
        int j = jccalendarevent.getCalendar().get(2);
        if(spin_year.getValue().intValue() != i)
            spin_year.setValue(i);
        if(spin_month.getValue().intValue() != j)
            spin_month.setValue(j);
    }

    public void dateSelected(JCCalendarEvent jccalendarevent)
    {
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent focusevent)
    {
        if(spin_time.getTextField().hasFocus() || spin_month.getTextField().hasFocus() || spin_year.getTextField().hasFocus() || calendar.hasFocus())
        {
            return;
        } else
        {
            lostCalendarFocus(this);
            return;
        }
    }

    protected void lostCalendarFocus(Object obj)
    {
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String s)
    {
    }

    public String getAbout()
    {
        return about;
    }

    public synchronized void setAbout(String s)
    {
    }

    public final void setLayout(LayoutManager layoutmanager)
    {
    }

    public void setSelectedDate(int i, int j, int k)
    {
        calendar.setSelectedDate(i, j, k);
    }

    public Calendar getValue()
    {
        return calendar.getValue();
    }

    public void setValue(Calendar calendar1)
    {
        calendar.setValue(calendar1);
    }

    public Color getBackground()
    {
        return super.getBackground();
    }

    public void setBackground(Color color)
    {
        super.setBackground(color);
        container.setBackground(color);
        calendar.setBackground(color);
        spin_year.setBackground(color);
        spin_month.setBackground(color);
    }

    public Color getForeground()
    {
        return super.getForeground();
    }

    public void setForeground(Color color)
    {
        super.setForeground(color);
        calendar.setForeground(color);
        spin_year.setForeground(color);
        spin_month.setForeground(color);
    }

    public Font getFont()
    {
        return super.getFont();
    }

    public void setFont(Font font)
    {
        super.setFont(font);
        calendar.setFont(font);
        spin_month.getTextField().repaint();
        if(isDisplayable())
            calculateContainerSize();
    }

    public Color getHeaderBackground()
    {
        return calendar.getHeaderBackground();
    }

    public void setHeaderBackground(Color color)
    {
        calendar.setHeaderBackground(color);
    }

    public Color getHeaderForeground()
    {
        return calendar.getHeaderForeground();
    }

    public void setHeaderForeground(Color color)
    {
        calendar.setHeaderForeground(color);
    }

    public Font getHeaderFont()
    {
        return calendar.getHeaderFont();
    }

    public void setHeaderFont(Font font)
    {
        calendar.setHeaderFont(font);
        if(isDisplayable())
            calculateContainerSize();
    }

    public Color getSelectedBackground()
    {
        return calendar.getSelectedBackground();
    }

    public void setSelectedBackground(Color color)
    {
        calendar.setSelectedBackground(color);
    }

    public Color getSelectedForeground()
    {
        return calendar.getSelectedForeground();
    }

    public void setSelectedForeground(Color color)
    {
        calendar.setSelectedForeground(color);
    }

    public Color getSpecialDayBackground()
    {
        return calendar.getSpecialDayBackground();
    }

    public void setSpecialDayBackground(Color color)
    {
        calendar.setSpecialDayBackground(color);
    }

    public Color getSpecialDayForeground()
    {
        return calendar.getSpecialDayForeground();
    }

    public void setSpecialDayForeground(Color color)
    {
        calendar.setSpecialDayForeground(color);
    }

    public int getNumHeaderChars()
    {
        return calendar.getNumHeaderChars();
    }

    public void setNumHeaderChars(int i)
    {
        calendar.setNumHeaderChars(i);
        if(isDisplayable())
            calculateContainerSize();
    }

    public int getCellMargin()
    {
        return calendar.getCellMargin();
    }

    public void setCellMargin(int i)
    {
        calendar.setCellMargin(i);
        if(isDisplayable())
            calculateContainerSize();
    }

    public int getCellHighlightThickness()
    {
        return calendar.getCellHighlightThickness();
    }

    public void setCellHighlightThickness(int i)
    {
        calendar.setCellHighlightThickness(i);
        if(isDisplayable())
            calculateContainerSize();
    }

    public Calendar getMinRollDate()
    {
        return min_roll_date;
    }

    public void setMinRollDate(Calendar calendar1)
    {
        min_roll_date = calendar1;
        spin_year.setMin(calendar1.get(1));
    }

    public Calendar getMaxRollDate()
    {
        return max_roll_date;
    }

    public void setMaxRollDate(Calendar calendar1)
    {
        max_roll_date = calendar1;
        spin_year.setMax(calendar1.get(1));
    }

    public boolean getHideTimeSpinner()
    {
        return hide_time_spinner;
    }

    public void setHideTimeSpinner(boolean flag)
    {
        if(flag == hide_time_spinner)
            return;
        hide_time_spinner = flag;
        if(flag)
        {
            remove(spin_time);
            spin_time.removeFieldListener(this);
            return;
        } else
        {
            add("South", spin_time);
            spin_time.addFieldListener(this);
            calculateContainerSize();
            return;
        }
    }

    public void addSpecialDay(JCDateMonthYear jcdatemonthyear)
    {
        calendar.addSpecialDay(jcdatemonthyear);
    }

    public void addSpecialDay(JCDayOfWeek jcdayofweek)
    {
        calendar.addSpecialDay(jcdayofweek);
    }

    public void addSpecialDay(JCMonthDayOfMonth jcmonthdayofmonth)
    {
        calendar.addSpecialDay(jcmonthdayofmonth);
    }

    public void addSpecialDay(JCMonthWeekDayOfWeek jcmonthweekdayofweek)
    {
        calendar.addSpecialDay(jcmonthweekdayofweek);
    }

    public void removeSpecialDay(JCDateMonthYear jcdatemonthyear)
    {
        calendar.removeSpecialDay(jcdatemonthyear);
    }

    public void removeSpecialDay(JCDayOfWeek jcdayofweek)
    {
        calendar.removeSpecialDay(jcdayofweek);
    }

    public void removeSpecialDay(JCMonthDayOfMonth jcmonthdayofmonth)
    {
        calendar.removeSpecialDay(jcmonthdayofmonth);
    }

    public void removeSpecialDay(JCMonthWeekDayOfWeek jcmonthweekdayofweek)
    {
        calendar.removeSpecialDay(jcmonthweekdayofweek);
    }

    public void addCalendarListener(JCCalendarListener jccalendarlistener)
    {
        calendar.addCalendarListener(jccalendarlistener);
    }

    public void removeCalendarListener(JCCalendarListener jccalendarlistener)
    {
        calendar.removeCalendarListener(jccalendarlistener);
    }

    public static final String version = JCVersion.getVersionString();
    public static final String about = JCVersion.getAboutString();
    protected JCIntSpinField spin_month;
    protected JCIntSpinField spin_year;
    protected JCTimeSpinField spin_time;
    protected JCCalendarComponent calendar;
    protected JPanel container;
    protected Calendar max_roll_date;
    protected Calendar min_roll_date;
    protected boolean hide_time_spinner;
    protected String months[];

}
