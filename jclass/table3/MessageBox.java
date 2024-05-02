// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageBox.java

package jclass.table3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.EventObject;

public class MessageBox extends Dialog
    implements Serializable, ActionListener
{

    public MessageBox(Frame frame, String s)
    {
        super(frame, s, true);
        setLayout(new BorderLayout());
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 8, 8));
        textLabel = new Label("");
        panel.add(textLabel);
        Panel panel1 = new Panel();
        panel1.setLayout(new FlowLayout(2, 8, 8));
        buttonOK = new Button("OK");
        panel1.add(buttonOK);
        add("Center", panel);
        add("South", panel1);
        buttonOK.addActionListener(this);
    }

    public void showMessage(String s)
    {
        setLocation();
        textLabel.setText(s);
        pack();
        show();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        if(actionevent.getSource() == buttonOK)
            setVisible(false);
    }

    private void setLocation()
    {
        setLocation(((Component) (getParent())));
    }

    public void setLocation(Component component)
    {
        Point point = component.getLocation();
        Dimension dimension = component.getSize();
        setLocation(point.x + dimension.width / 2, point.y + dimension.height / 2);
    }

    Label textLabel;
    Button buttonOK;
}
