// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarPopup.java

package jclass.field;

import java.awt.*;
import java.awt.event.*;
import jclass.base.BaseComponent;

// Referenced classes of package jclass.field:
//            CalendarPopupDialog, JCCalendarPopup, PopupCalendar, JCCalendar

class CalendarMouseListener extends MouseAdapter
    implements MouseMotionListener
{

    public CalendarMouseListener(JCCalendar jccalendar)
    {
        cal = jccalendar;
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        location = BaseComponent.translateToParent(cal, mouseevent.getComponent(), mouseevent.getX(), mouseevent.getY());
        Event event = new Event(this, mouseevent.getWhen(), mouseevent.getID(), location.x, location.y, 0, mouseevent.getModifiers());
        cal.mouseEnter(event, location.x, location.y);
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        location = BaseComponent.translateToParent(cal, mouseevent.getComponent(), mouseevent.getX(), mouseevent.getY());
        Event event = new Event(this, mouseevent.getWhen(), mouseevent.getID(), location.x, location.y, 0, mouseevent.getModifiers());
        cal.mouseExit(event, location.x, location.y);
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        location = BaseComponent.translateToParent(cal, mouseevent.getComponent(), mouseevent.getX(), mouseevent.getY());
        Event event = new Event(this, mouseevent.getWhen(), mouseevent.getID(), location.x, location.y, 0, mouseevent.getModifiers());
        cal.mouseMove(event, location.x, location.y);
    }

    JCCalendar cal;
    Point location;
}
