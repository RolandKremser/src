// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSpin.java

package jclass.field;

import java.awt.Event;
import jclass.bwt.JCArrowButton;
import jclass.bwt.JCButton;

// Referenced classes of package jclass.field:
//            JCSpin, SpinField

class SpinArrowButton extends JCArrowButton
{

    public SpinArrowButton(int i, JCSpin jcspin)
    {
        super(i);
        spin = jcspin;
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        boolean flag = super.mouseUp(event, i, j);
        SpinArrowButton spinarrowbutton = null;
        spinarrowbutton = equals(spin.decr_arrow) ? spin.incr_arrow : spin.decr_arrow;
        if(spinarrowbutton != null && spinarrowbutton.isArmed())
            spinarrowbutton.mouseUp(event, i, j);
        return flag;
    }

    JCSpin spin;
}
