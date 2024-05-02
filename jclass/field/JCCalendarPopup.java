// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarPopup.java

package jclass.field;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JComponent;
import jclass.bwt.*;
import jclass.cell.*;
import jclass.util.JCEnvironment;

// Referenced classes of package jclass.field:
//            JCFieldCombo, CalendarMouseListener, CalendarPopupDialog, JCCalendar, 
//            JCCalendarEvent, JCCalendarListener, JCCalendarSpinField, JCCalendarValidator, 
//            JCField, JCSpin, JCStringValidator, JCValidator, 
//            PopupCalendar

public class JCCalendarPopup extends JCFieldCombo
    implements JCCalendarListener, KeyListener
{

    public JCCalendarPopup()
    {
        this(null, Locale.getDefault());
        super.field.setDefaultValue(Calendar.getInstance());
    }

    public JCCalendarPopup(Calendar calendar, Locale locale)
    {
        this(new JCCalendarValidator(), calendar, locale);
    }

    public JCCalendarPopup(JCCalendarValidator jccalendarvalidator, Calendar calendar, Locale locale)
    {
        using_window = false;
        Calendar calendar1 = calendar;
        Date date = new Date();
        if(calendar1 != null)
            calendar1.setTime(date);
        createField(jccalendarvalidator, calendar1, calendar1);
        arrow_button = getButton();
        text = getTextField();
        text.addKeyListener(this);
        if(calendar1 != null)
            cal = new PopupCalendar(this, (Calendar)calendar.clone(), locale);
        else
            cal = new PopupCalendar(this, null, locale);
        cal.addCalendarListener(this);
        cal.setNumHeaderChars(1);
        cal.setCellMargin(1);
        CalendarMouseListener calendarmouselistener = new CalendarMouseListener(cal);
        cal.addMouseMotionListener(calendarmouselistener);
        ((JCCalendar) (cal)).container.addMouseMotionListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_month.addMouseMotionListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_year.addMouseMotionListener(calendarmouselistener);
        ((JCCalendar) (cal)).calendar.addMouseMotionListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_time.addMouseMotionListener(calendarmouselistener);
        cal.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).container.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_month.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_month.getTextField().addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_year.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_year.getTextField().addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).calendar.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_time.addMouseListener(calendarmouselistener);
        ((JCCalendar) (cal)).spin_time.getTextField().addMouseListener(calendarmouselistener);
        setColumns(5);
    }

    protected int preferredWidth()
    {
        int i = text.getPreferredSize().width + getButton().getPreferredSize().width;
        if(cal.getPreferredSize().width <= 50)
            return 150;
        else
            return Math.max(i, cal.getPreferredSize().width);
    }

    public void addNotify()
    {
        super.addNotify();
        if(hasFocus())
            getTextField().requestFocus();
        super.field.addNotify();
    }

    public void setValue(Calendar calendar)
    {
        super.setValue(calendar);
    }

    public void showListAction(JCActionEvent jcactionevent)
    {
        showCalendarAction(jcactionevent);
    }

    public void showCalendarAction(JCActionEvent jcactionevent)
    {
        if(super.list_poppedup)
            return;
        cal.setBackground(text.getBackground());
        cal.setForeground(text.getForeground());
        cal.setHeaderBackground(text.getBackground());
        cal.setHeaderForeground(text.getForeground());
        cal.setSelectedBackground(text.getForeground());
        cal.setSelectedForeground(text.getBackground());
        cal.setFont(text.getFont());
        if(getValue() != null)
        {
            Calendar calendar = getValue();
            int i = calendar.get(1);
            int k = calendar.get(2);
            int i1 = calendar.get(5);
            ((JCCalendar) (cal)).spin_time.setValue(calendar.getTime());
            cal.setSelectedDate(i, k, i1);
        } else
        {
            cal.setValue(Calendar.getInstance());
        }
        java.awt.Frame frame = createPopupWindow(cal);
        int j = Math.max(getSize().width, cal.getPreferredSize().width);
        int l = cal.getPreferredSize().height;
        int j1 = Math.min(0, j - getSize().width);
        positionPopupWindow(cal, frame, j1, j, l);
        cal.exited = true;
        cal.setVisible(true);
        super.list_poppedup = true;
        if(super.list_window != null)
            super.list_window.toFront();
        else
            frame.toFront();
        cal.requestCalendarFocus();
        if(JCEnvironment.isVisualAge())
            calendar_popdown_time = System.currentTimeMillis();
    }

    protected void listPoppedDown()
    {
        text.requestFocus();
    }

    public void keyPressed(KeyEvent keyevent)
    {
        if(getEditable())
        {
            if(keyevent.getKeyCode() == 40)
            {
                showListAction(new JCActionEvent(text, keyevent.getID(), null, keyevent.getModifiers()));
                return;
            }
        } else
        if(super.field.isCellEditor())
        {
            if(keyevent.getKeyCode() != 40 && keyevent.getKeyCode() != 38 && keyevent.getKeyCode() != 39 && keyevent.getKeyCode() != 37 && keyevent.getKeyCode() != 33 && keyevent.getKeyCode() != 34 && keyevent.getKeyCode() != 36 && keyevent.getKeyCode() != 35 && keyevent.getKeyCode() != 27 && keyevent.getKeyCode() != 9)
            {
                showListAction(new JCActionEvent(text, keyevent.getID(), null, keyevent.getModifiers()));
                return;
            }
        } else
        if(keyevent.getKeyCode() != 9 && keyevent.getKeyCode() != 27)
            showListAction(new JCActionEvent(text, keyevent.getID(), null, keyevent.getModifiers()));
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public boolean keyDown(Event event, int i)
    {
        return true;
    }

    public void dateChanged(JCCalendarEvent jccalendarevent)
    {
    }

    public void dateSelected(JCCalendarEvent jccalendarevent)
    {
        Calendar calendar = jccalendarevent.getCalendar();
        Calendar calendar1 = getValue();
        if(calendar1 == null)
            calendar1 = (Calendar)calendar.clone();
        int i = calendar.get(1);
        int j = calendar.get(2);
        int k = calendar.get(5);
        calendar1.set(i, j, k);
        hidePopup();
        if(!cal.getHideTimeSpinner())
        {
            calendar1.set(10, calendar.get(10));
            calendar1.set(11, calendar.get(11));
            calendar1.set(12, calendar.get(12));
            calendar1.set(13, calendar.get(13));
            calendar1.set(9, calendar.get(9));
        }
        super.field.setAndValidateValue(calendar1);
        if(super.field.isCellEditor())
            getTextField().getCellEditorSupport().fireStopEditing(new CellEditorEvent(jccalendarevent));
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
        }
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
        {
            return;
        } else
        {
            Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
            calendar.setTime(date);
            super.setValue(calendar);
            return;
        }
    }

    public void setValue(long l)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
        {
            return;
        } else
        {
            Calendar calendar = Calendar.getInstance(jccalendarvalidator.getTimeZone(), jccalendarvalidator.getLocale());
            Date date = new Date(l);
            calendar.setTime(date);
            super.setValue(calendar);
            return;
        }
    }

    public boolean getMaskInput()
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return false;
        else
            return jccalendarvalidator.getMaskInput();
    }

    public void setMaskInput(boolean flag)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return;
        jccalendarvalidator.setMaskInput(flag);
        super.field.setValue(super.field.internal_value);
        if(flag)
        {
            text.setOverstrike(true);
            return;
        } else
        {
            text.setOverstrike(false);
            return;
        }
    }

    public String getFormat()
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return null;
        else
            return jccalendarvalidator.getFormat();
    }

    public void setFormat(String s)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return;
        jccalendarvalidator.setFormat(s);
        super.field.setValue(super.field.internal_value);
        if(isDisplayable())
            super.field.validate(null);
        if(getMaskInput())
        {
            text.setOverstrike(true);
            return;
        } else
        {
            text.setOverstrike(false);
            return;
        }
    }

    public String getPlaceHolderChars()
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return null;
        else
            return jccalendarvalidator.getPlaceHolderChars();
    }

    public void setPlaceHolderChars(String s)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return;
        jccalendarvalidator.setPlaceHolderChars(s);
        super.field.setValue(super.field.internal_value);
        if(isDisplayable())
            super.field.validate(null);
    }

    public int getDefaultDetail()
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return 0;
        else
            return jccalendarvalidator.getDefaultDetail();
    }

    public void setDefaultDetail(int i)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
        {
            return;
        } else
        {
            jccalendarvalidator.setDefaultDetail(i);
            super.field.setValue(super.field.internal_value);
            return;
        }
    }

    public String[] getEditFormats()
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
            return null;
        else
            return jccalendarvalidator.getEditFormats();
    }

    public void setEditFormats(String as[])
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
        {
            return;
        } else
        {
            jccalendarvalidator.setEditFormats(as);
            return;
        }
    }

    public void addEditFormat(String s)
    {
        JCCalendarValidator jccalendarvalidator = (JCCalendarValidator)super.field.getValidator();
        if(jccalendarvalidator == null)
        {
            return;
        } else
        {
            jccalendarvalidator.addEditFormat(s);
            return;
        }
    }

    public Calendar getDefaultValue()
    {
        return (Calendar)super.field.getDefaultValue();
    }

    public void setDefaultValue(Calendar calendar)
    {
        super.field.setDefaultValue(calendar);
    }

    public JCCalendar getJCCalendar()
    {
        return cal;
    }

    public void checkButton()
    {
    }

    public KeyModifier[] getReservedKeys()
    {
        if(super.field.isCellEditor())
            return text.getReservedKeys();
        else
            return Utilities.addKey(text.getReservedKeys(), new KeyModifier(40, 16, false));
    }

    protected JCArrowButton arrow_button;
    protected JCTextField text;
    protected PopupCalendar cal;
    protected boolean using_window;
    long calendar_popdown_time;
}
