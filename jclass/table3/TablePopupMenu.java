// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TablePopupMenu.java

package jclass.table3;

import java.awt.event.ActionListener;
import jclass.util.BasePopupMenu;

// Referenced classes of package jclass.table3:
//            LocaleBundle

public class TablePopupMenu extends BasePopupMenu
{

    public TablePopupMenu(ActionListener actionlistener)
    {
        super(actionlistener);
    }

    protected String[] getCommands()
    {
        return commands;
    }

    public boolean enableMenuItem(String s, boolean flag)
    {
        return super.enableMenuItem(LocaleBundle.string(s), flag);
    }

    static final String commands[] = {
        LocaleBundle.string("Hide Column..."), LocaleBundle.string("Show Column..."), LocaleBundle.string("Go to Row..."), LocaleBundle.string("Print..."), LocaleBundle.string("Print Preview...")
    };

}
