// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AboutDialog.java

package jclass.beans;

import java.awt.*;
import java.awt.event.*;
import jclass.util.*;

public class AboutDialog extends Frame
    implements WindowListener, ActionListener
{

    public AboutDialog(Component component)
    {
        this(component, "", "", "");
    }

    public AboutDialog(Component component, String s, String s1, String s2)
    {
        this(component, s, s1, s2, null);
    }

    public AboutDialog(Component component, String s, String s1, String s2, ItemListener itemlistener)
    {
        super("About Dialog");
        message = new JCString("");
        supercomp = null;
        okb = new Button("OK");
        noMoreCB = new Checkbox("Don't bring up this dialog again");
        c = null;
        pnl1 = new Panel();
        productName = "";
        packageName = "";
        versionNumber = "";
        reactivated = false;
        productName = s;
        packageName = s1;
        versionNumber = s2;
        supercomp = component;
        addWindowListener(this);
        setLayout(new BorderLayout());
        init();
        GridBagLayout gridbaglayout = new GridBagLayout();
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        pnl1.setLayout(gridbaglayout);
        gridbagconstraints.gridwidth = 1;
        gridbagconstraints.gridx = -1;
        gridbagconstraints.gridy = 0;
        if(itemlistener != null)
        {
            noMoreCB.addItemListener(itemlistener);
            if(JCEnvironment.isJBuilder())
            {
                noMoreCB.setState(true);
                itemlistener.itemStateChanged(new ItemEvent(noMoreCB, 0, noMoreCB.getLabel(), 1));
            } else
            {
                noMoreCB.setState(false);
                itemlistener.itemStateChanged(new ItemEvent(noMoreCB, 0, noMoreCB.getLabel(), 2));
            }
            pnl1.add(noMoreCB, gridbagconstraints);
            gridbagconstraints.gridy++;
        }
        pnl1.add(okb, gridbagconstraints);
        add("South", pnl1);
        okb.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        hide();
    }

    public void init()
    {
        if(c != null)
            remove(c);
        c = new JCAboutCanvas(productName, packageName, versionNumber, message, null);
        add("Center", c);
    }

    public void setMessage(JCString jcstring)
    {
        message = JCString.parse(null, String.valueOf(jcstring.toString2()));
        init();
    }

    public void show()
    {
        if(supercomp.getClass().getName().equals("jclass.table3.PropertyEditorTable"))
        {
            return;
        } else
        {
            pack();
            super.show();
            Dimension dimension = c.getPreferredSize();
            setSize(dimension.width + 5, dimension.height + 60);
            Toolkit toolkit = supercomp.getToolkit();
            Dimension dimension1 = toolkit.getScreenSize();
            setLocation((dimension1.width - dimension.width - 5) / 2, (dimension1.height - dimension.height - 60) / 2);
            return;
        }
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowClosing(WindowEvent windowevent)
    {
        hide();
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
        if(isVisible() && !reactivated)
        {
            toFront();
            reactivated = true;
        }
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    protected static final String IDE_TEXT = "";
    protected JCString message;
    protected Component supercomp;
    protected Button okb;
    protected Checkbox noMoreCB;
    protected JCAboutCanvas c;
    protected Panel pnl1;
    protected String productName;
    protected String packageName;
    protected String versionNumber;
    protected final int heightadd = 60;
    protected final int widthadd = 5;
    protected boolean reactivated;
}
