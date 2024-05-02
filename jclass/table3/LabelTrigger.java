// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LabelTrigger.java

package jclass.table3;

import java.awt.event.InputEvent;
import java.io.Serializable;

public class LabelTrigger
    implements Serializable
{

    LabelTrigger()
    {
    }

    public LabelTrigger(int i, int j)
    {
        setModifiers(i);
        setAction(j);
    }

    public int getModifiers()
    {
        return modifiers;
    }

    public synchronized void setModifiers(int i)
    {
        modifiers = adjustModifier(i);
    }

    static int adjustModifier(int i)
    {
        if((i & 0x10) == 0 && (i & 4) == 0)
            i |= 0x10;
        return i;
    }

    public int getAction()
    {
        return action;
    }

    public synchronized void setAction(int i)
    {
        if(i == action)
            return;
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            action = i;
            return;
        }
        throw new IllegalArgumentException("Invalid action specified for LabelTrigger");
    }

    int modifiers;
    int action;
    public static final int SELECT = 0;
    public static final int SORT = 1;
    public static final int DRAG = 2;
    public static final int MENU = 3;
    public static final String trigger_strings[] = {
        "None", "Select", "Sort", "Drag"
    };
    public static final int trigger_values[] = {
        -1, 0, 1, 2, 3
    };

}
