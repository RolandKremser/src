// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageDialog.java

package jclass.util;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class MessageDialog
    implements Runnable, Serializable, ActionListener, WindowListener, KeyListener
{

    MessageDialog()
    {
        frame = null;
        useOwnFrame = false;
        dialog = null;
        title = "";
        listener = null;
        buttonPanel = new Panel();
        closeWindowCommand = "Close";
    }

    public MessageDialog(Frame frame1, String s, String s1)
    {
        JOptionPane.showMessageDialog(frame1, s1, s, 0);
    }

    public void run()
    {
        dialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        if(listener != null)
            listener.actionPerformed(actionevent);
        dialog.setVisible(false);
        dialog.dispose();
        if(useOwnFrame)
            frame.dispose();
    }

    public void windowClosing(WindowEvent windowevent)
    {
        fireCloseWindowEvent();
    }

    public void windowClosed(WindowEvent windowevent)
    {
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

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyPressed(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 27)
            fireCloseWindowEvent();
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    void fireCloseWindowEvent()
    {
        ActionEvent actionevent = new ActionEvent(this, 1001, closeWindowCommand);
        actionPerformed(actionevent);
    }

    public void setActionListener(ActionListener actionlistener)
    {
        listener = actionlistener;
    }

    void setFrame(Frame frame1)
    {
        frame = frame1;
    }

    void setTitle(String s)
    {
        title = s;
    }

    void setCloseWindowCommand(String s)
    {
        closeWindowCommand = s;
    }

    void addButton(String s, String s1)
    {
        Button button = new Button(s);
        if(s1 == null)
            s1 = s;
        button.setActionCommand(s1);
        button.addActionListener(this);
        button.addKeyListener(this);
        buttonPanel.add(button);
    }

    void show(String s)
    {
        if(frame == null)
        {
            frame = new Frame();
            useOwnFrame = true;
        } else
        {
            useOwnFrame = false;
        }
        dialog = new Dialog(frame, true);
        dialog.addWindowListener(this);
        dialog.addKeyListener(this);
        dialog.setTitle(title);
        dialog.setLayout(new BorderLayout(5, 5));
        Panel panel = createMessagePanel(s);
        dialog.add(panel, "Center");
        dialog.add(buttonPanel, "South");
        setDialogSize(200, 100);
        dialog.pack();
        centerDialog();
        Toolkit.getDefaultToolkit().beep();
        Thread thread = new Thread(this);
        thread.start();
    }

    Panel createMessagePanel(String s)
    {
        int i = 0;
        if(s == null)
            s = "";
        for(String s1 = new String(s); s1.length() > 0;)
        {
            int j = s1.indexOf('\n');
            if(j >= 0)
                s1 = s1.substring(j + 1);
            else
                s1 = "";
            i++;
        }

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(i, 1));
        String s2 = new String(s);
        for(int k = 0; k < i; k++)
        {
            int l = s2.indexOf('\n');
            String s3;
            if(l >= 0)
            {
                s3 = s2.substring(0, l);
                s2 = s2.substring(l + 1);
            } else
            {
                s3 = s2;
                s2 = "";
            }
            Label label = new Label("  " + s3);
            panel.add(label);
        }

        return panel;
    }

    void setDialogSize(int i, int j)
    {
        i = Math.max(i, dialog.getSize().width);
        j = Math.max(j, dialog.getSize().height);
        dialog.setSize(i, j);
    }

    void centerDialog()
    {
        int i = 0;
        int j = 0;
        if(!useOwnFrame)
        {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            i = (dimension.width - dialog.getSize().width) / 2;
            j = (dimension.height - dialog.getSize().height) / 2;
        } else
        {
            i = frame.getBounds().x + (frame.getBounds().width - dialog.getBounds().width) / 2;
            j = frame.getBounds().y + (frame.getBounds().height - dialog.getBounds().height) / 2;
        }
        i = Math.max(i, 0);
        j = Math.max(j, 0);
        dialog.setLocation(i, j);
    }

    static final long serialVersionUID = 0xa8f484714f542beL;
    private Frame frame;
    private boolean useOwnFrame;
    private Dialog dialog;
    private String title;
    private Panel buttonPanel;
    private ActionListener listener;
    private String closeWindowCommand;
}
