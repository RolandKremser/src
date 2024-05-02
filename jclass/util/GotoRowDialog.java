// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GotoRowDialog.java

package jclass.util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class GotoRowDialog extends Dialog
    implements ActionListener
{

    public GotoRowDialog(Frame frame, int i)
    {
        super(frame, "Go to Row", true);
        row = i;
        text = new TextField(String.valueOf(i), 15);
        text.selectAll();
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(2));
        panel.add(text);
        buttonOK = new Button("OK");
        buttonCancel = new Button("Cancel");
        Panel panel1 = new Panel();
        panel1.setLayout(new FlowLayout(2));
        panel1.add(buttonOK);
        panel1.add(buttonCancel);
        setLayout(new BorderLayout(10, 4));
        add("Center", panel);
        add("South", panel1);
        buttonCancel.addActionListener(this);
        buttonOK.addActionListener(this);
        pack();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        Object obj = actionevent.getSource();
        if(obj == buttonOK)
            try
            {
                row = Integer.valueOf(text.getText()).intValue();
                setVisible(false);
                return;
            }
            catch(NumberFormatException _ex)
            {
                return;
            }
        if(obj == buttonCancel)
            setVisible(false);
    }

    public int getRow()
    {
        return row;
    }

    protected void center()
    {
        int i = 0;
        int j = 0;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        i = (dimension.width - getSize().width) / 2;
        j = (dimension.height - getSize().height) / 2;
        i = Math.max(i, 0);
        j = Math.max(j, 0);
        setLocation(i, j);
    }

    public void open()
    {
        center();
        show();
    }

    private int row;
    private TextField text;
    private Button buttonOK;
    private Button buttonCancel;
}
