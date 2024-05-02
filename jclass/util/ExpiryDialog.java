// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCVersion.java

package jclass.util;

import java.awt.*;
import java.util.Date;

// Referenced classes of package jclass.util:
//            ExpiryDialogComponent, JCAlignLayout, JCEnvironment, JCString, 
//            JCStringComponent, JCVersion, JClassInfo, RegistryListener

final class ExpiryDialog extends Frame
    implements Runnable, RegistryListener
{

    public ExpiryDialog(String s)
    {
        super(s);
        timerThread = null;
        expiryBox = null;
        timerThread = new Thread(this);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        expiryBox = new Panel();
        expiryBox.setLayout(new JCAlignLayout(3, 5, 5));
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        panel.add(expiryBox);
        Panel panel1 = new Panel();
        panel1.setLayout(new BorderLayout());
        ExpiryDialogComponent expirydialogcomponent = new ExpiryDialogComponent("[VERT_SPACE=10][FONT=arial-plain-12]You are using time-limited, evaluation copies of the following [COLOR=red]J[COLOR=blue]Class[DEFAULT_COLOR] products:[/ALIGN]", 0, new Rectangle(10, 1, 10, 1));
        panel1.add("North", expirydialogcomponent);
        panel1.add("Center", panel);
        expirydialogcomponent = new ExpiryDialogComponent(JClassInfo.getEvalMessage("<product>", "<product>", "<install dir>"), 0, new Rectangle(10, 1, 10, 1));
        panel1.add("South", expirydialogcomponent);
        add("Center", panel1);
        expirydialogcomponent = new ExpiryDialogComponent(new JCString("www.klg.com", JClassInfo.makeKLImage(this), 3), 1, new Rectangle(1, 10, 1, 1));
        add("North", expirydialogcomponent);
        panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        panel.add(new Button("OK"));
        add("South", panel);
        timerThread.start();
    }

    public final boolean action(Event event, Object obj)
    {
        hide();
        synchronized(this)
        {
            notifyAll();
        }
        return true;
    }

    public void expiryNotify(String s)
    {
    }

    public final boolean handleEvent(Event event)
    {
        if(event.id == 201)
        {
            action(event, this);
            return true;
        } else
        {
            return super.handleEvent(event);
        }
    }

    public final void run()
    {
        do
        {
            updateMessage();
            if(JCEnvironment.isVisualCafe())
                try
                {
                    Thread.sleep(5000L);
                }
                catch(InterruptedException _ex) { }
            pack();
            show();
            synchronized(this)
            {
                while(isVisible()) 
                    try
                    {
                        wait();
                    }
                    catch(InterruptedException _ex) { }
            }
            try
            {
                Thread.sleep(0x36ee80L);
            }
            catch(InterruptedException _ex) { }
        } while(true);
    }

    private final void updateMessage()
    {
        String as[] = JClassInfo.getEvalPackageNames();
        expiryBox.removeAll();
        expiryBox.add(new JCStringComponent("[FONT=arial-bold-12]Product"));
        expiryBox.add(new JCStringComponent("[FONT=arial-bold-12]Expired"));
        expiryBox.add(new JCStringComponent("[FONT=arial-bold-12]Expiry Date"));
        for(int i = 0; i < as.length; i++)
            if(true)//JCVersion.isEval(as[i]))
            {
                expiryBox.add(new JCStringComponent("[FONT=arial-plain-12][COLOR=blue]" + "BWT"));//JCVersion.getProductName(as[i])));
                expiryBox.add(new JCStringComponent("[FONT=arial-plain-12]" + (false/*JCVersion.isExpired(as[i])*/ ? "[COLOR=red]Yes" : "[COLOR=green]No")));
                Date date = null;//JCVersion.getEvalExpiryDate(JCVersion.getProductName(as[i]));
                if(date == null)
                    expiryBox.add(new JCStringComponent("[FONT=arial-plain-12][COLOR=red]Unknown"));
                else
                    expiryBox.add(new JCStringComponent("[FONT=arial-plain-12]" + date.toLocaleString()));
            }

    }

    private Thread timerThread;
    private Panel expiryBox;
    private static final String evalNotice = "[VERT_SPACE=10][FONT=arial-plain-12]You are using time-limited, evaluation copies of the following [COLOR=red]J[COLOR=blue]Class[DEFAULT_COLOR] products:[/ALIGN]";
}
