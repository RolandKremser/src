// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseInitialEvent.java

package jclass.cell;

import java.awt.Event;

// Referenced classes of package jclass.cell:
//            InitialEvent

public class BaseInitialEvent
    implements InitialEvent
{

    public BaseInitialEvent(int i, int j)
    {
        x = 0;
        y = 0;
        key = 0;
        modifiers = 0;
        type = 2;
        key = i;
        modifiers = j;
    }

    public BaseInitialEvent(int i, int j, int k)
    {
        x = 0;
        y = 0;
        key = 0;
        modifiers = 0;
        type = 1;
        x = i;
        y = j;
        modifiers = k;
    }

    public BaseInitialEvent(Event event)
    {
        x = 0;
        y = 0;
        key = 0;
        modifiers = 0;
        modifiers = event.modifiers;
        switch(event.id)
        {
        case 501: // Event.MOUSE_DOWN
        case 502: // Event.MOUSE_UP
        case 503: // Event.MOUSE_MOVE
        case 504: // Event.MOUSE_EVENT
        case 505: // Event.MOUSE_EXIT
        case 506: // Event.MOUSE_DRAG
            type = 1;
            x = event.x;
            y = event.y;
            break;

        case 401: // Event.KEY_PRESS
        case 402: // Event.KEY_RELEASE
        case 403: // Event.KEY_ACTION
        case 404: // Event.KEY_ACTION_RELEASE
            type = 2;
            key = event.key;
            break;

        default:
            type = 2;
            break;
        }
    }

    public int getEventType()
    {
        return type;
    }

    public int getKey()
    {
        return key;
    }

    public int getModifiers()
    {
        return modifiers;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setKey(int i)
    {
        key = i;
    }

    public void setX(int i)
    {
        x = i;
    }

    public void setY(int i)
    {
        y = i;
    }

    public static final int MOUSE = 1;
    public static final int KEY = 2;
    public static final int ALT = 8;
    public static final int CTRL = 2;
    public static final int META = 4;
    public static final int SHIFT = 1;
    int type;
    int x;
    int y;
    int key;
    int modifiers;
}
