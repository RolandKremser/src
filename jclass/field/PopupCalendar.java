// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarPopup.java

package jclass.field;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Locale;
import jclass.bwt.JCComboBox;
import jclass.bwt.JCComponent;
import jclass.util.JCEnvironment;

// Referenced classes of package jclass.field:
//            JCCalendar, CalendarMouseListener, CalendarPopupDialog, JCCalendarComponent, 
//            JCCalendarPopup, JCSpin

class PopupCalendar extends JCCalendar
    implements KeyListener
{

    PopupCalendar(JCCalendarPopup jccalendarpopup, Calendar calendar, Locale locale)
    {
        super(calendar, locale);
        exited = true;
        box = jccalendarpopup;
        exited = false;
        super.calendar.addKeyListener(this);
        super.spin_time.addKeyListener(this);
        super.spin_month.addKeyListener(this);
        super.spin_year.addKeyListener(this);
        enableEvents(32L);
    }

    protected void lostCalendarFocus(Object obj)
    {
        if(JCEnvironment.isVisualAge())
        {
            long l = System.currentTimeMillis() - box.calendar_popdown_time;
            if(l >= 0L && l < 600L)
            {
                requestCalendarFocus();
                return;
            }
        }
        if(exited && (JCEnvironment.getJavaVersion() < 116 || JCEnvironment.isVisualAge()))
            box.hidePopup();
    }

    public void requestCalendarFocus()
    {
        super.calendar.requestFocus();
    }

    public boolean mouseMove(Event event, int i, int j)
    {
        exited = !inside(i, j);
        return super.mouseMove(event, i, j);
    }

    public boolean mouseEnter(Event event, int i, int j)
    {
        exited = !inside(i, j);
        return super.mouseEnter(event, i, j);
    }

    public boolean mouseExit(Event event, int i, int j)
    {
        exited = !inside(i, j);
        return super.mouseExit(event, i, j);
    }

    public void keyPressed(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 27)
            box.hidePopup();
        if(keyevent.getKeyCode() == 10)
            super.calendar.postDateSelectedEvent();
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    JCCalendarPopup box;
    boolean exited;
}
