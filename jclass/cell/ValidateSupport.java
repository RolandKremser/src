// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ValidateSupport.java

package jclass.cell;

import java.io.Serializable;
import java.util.Enumeration;
import jclass.util.JCListenerList;

// Referenced classes of package jclass.cell:
//            ValidateEvent, ValidateListener

public class ValidateSupport
    implements Serializable
{

    public ValidateSupport()
    {
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        validateListeners = JCListenerList.add(validateListeners, validatelistener);
    }

    public void fireStateIsInvalid(ValidateEvent validateevent)
    {
        if(validateevent == null)
            return;
        ValidateListener validatelistener;
        for(Enumeration enumeration = JCListenerList.elements(validateListeners); enumeration.hasMoreElements(); validatelistener.stateIsInvalid(validateevent))
            validatelistener = (ValidateListener)enumeration.nextElement();

    }

    public boolean fireValidateEvents(ValidateEvent validateevent)
    {
        fireValueChangedBegin(validateevent);
        boolean flag = validateevent.isValid();
        if(!validateevent.isValid())
        {
            validateevent.setBeep(true);
            fireStateIsInvalid(validateevent);
        } else
        {
            fireValueChangedEnd(validateevent);
        }
        return flag;
    }

    public void fireValueChangedBegin(ValidateEvent validateevent)
    {
        if(validateevent == null)
            return;
        ValidateListener validatelistener;
        for(Enumeration enumeration = JCListenerList.elements(validateListeners); enumeration.hasMoreElements(); validatelistener.valueChangedBegin(validateevent))
            validatelistener = (ValidateListener)enumeration.nextElement();

    }

    public void fireValueChangedEnd(ValidateEvent validateevent)
    {
        if(validateevent == null)
            return;
        ValidateListener validatelistener;
        for(Enumeration enumeration = JCListenerList.elements(validateListeners); enumeration.hasMoreElements(); validatelistener.valueChangedEnd(validateevent))
            validatelistener = (ValidateListener)enumeration.nextElement();

    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        validateListeners = JCListenerList.remove(validateListeners, validatelistener);
    }

    protected JCListenerList validateListeners;
}
