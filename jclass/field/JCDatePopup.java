// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCDatePopup.java

package jclass.field;

import java.util.Calendar;
import java.util.Locale;

// Referenced classes of package jclass.field:
//            JCCalendarPopup, JCCalendar, JCDateValidator

public class JCDatePopup extends JCCalendarPopup
{

    public JCDatePopup()
    {
        this(null, Locale.getDefault());
        super.cal.setHideTimeSpinner(true);
    }

    public JCDatePopup(Calendar calendar, Locale locale)
    {
        super(new JCDateValidator(), calendar, locale);
        super.cal.setHideTimeSpinner(true);
    }
}
