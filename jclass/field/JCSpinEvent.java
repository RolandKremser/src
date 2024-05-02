// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSpinEvent.java

package jclass.field;

import java.util.EventObject;

public class JCSpinEvent extends EventObject
{

    public JCSpinEvent(Object obj, int i, String s)
    {
        super(obj);
        direction = i;
        display_text = s;
    }

    public String getDisplayText()
    {
        return display_text;
    }

    public int getDirection()
    {
        return direction;
    }

    public static int NO_SPIN;
    public static int SPIN_UP = 1;
    public static int SPIN_DOWN = 2;
    protected String display_text;
    protected int direction;

}
