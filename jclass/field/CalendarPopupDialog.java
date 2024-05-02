// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCCalendarPopup.java

package jclass.field;

import java.awt.*;
import jclass.bwt.JCComboBox;

// Referenced classes of package jclass.field:
//            CalendarMouseListener, JCCalendarPopup, PopupCalendar

class CalendarPopupDialog extends Dialog
{

    CalendarPopupDialog(JCCalendarPopup jccalendarpopup, Frame frame)
    {
        super(frame, false);
        popup = jccalendarpopup;
    }

    public boolean handleEvent(Event event)
    {
        if(event.id == 201)
        {
            popup.hidePopup();
            return true;
        } else
        {
            return super.handleEvent(event);
        }
    }

    JCCalendarPopup popup;
}
