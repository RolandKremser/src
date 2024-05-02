// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTimeSpinField.java

package jclass.field;

import java.util.Date;

// Referenced classes of package jclass.field:
//            JCCalendarSpinField, JCTimeValidator

public class JCTimeSpinField extends JCCalendarSpinField
{

    public JCTimeSpinField()
    {
        super(new JCTimeValidator());
    }

    public JCTimeSpinField(Date date)
    {
        super(new JCTimeValidator(), date);
    }

    public JCTimeSpinField(long l)
    {
        super(new JCTimeValidator(), l);
    }
}
