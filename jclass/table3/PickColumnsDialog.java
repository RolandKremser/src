// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PickColumnsDialog.java

package jclass.table3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PickColumnsDialog extends Dialog
    implements ActionListener
{

    PickColumnsDialog(Frame frame, boolean flag, java.util.Vector vector)
    {
        super(frame, (flag ? "Visible" : "Hidden") + " Columns", true);
        list = new List(8, true);
        for(int i = 0; i < vector.size(); i++)
            list.add((String)vector.elementAt(i));

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(2));
        panel.add(list);
        buttonOK = new Button("OK");
        Button button = new Button("Cancel");
        Panel panel1 = new Panel();
        panel1.setLayout(new FlowLayout(2));
        panel1.add(buttonOK);
        panel1.add(button);
        setLayout(new BorderLayout(10, 4));
        add("Center", panel);
        add("South", panel1);
        list.addActionListener(this);
        button.addActionListener(this);
        buttonOK.addActionListener(this);
        pack();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        Object obj = actionevent.getSource();
        if(obj == buttonOK || obj == list)
            selected = list.getSelectedIndexes();
        setVisible(false);
    }

    public int[] getSelected()
    {
        return selected;
    }

    void center()
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

    private int selected[];
    private List list;
    private Button buttonOK;
}
