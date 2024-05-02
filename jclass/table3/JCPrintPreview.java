// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCPrintPreview.java

package jclass.table3;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Properties;

// Referenced classes of package jclass.table3:
//            JCPrintPage, JCPrintTable

public class JCPrintPreview extends Frame
    implements WindowListener, ActionListener
{

    public JCPrintPreview(String s, JCPrintTable jcprinttable)
    {
        super(s);
        table = jcprinttable;
        try
        {
            Properties properties = new Properties();
            job = getToolkit().getPrintJob(this, "Table3", properties);
        }
        catch(Exception exception)
        {
            System.out.println("PrintJob error: " + exception.getMessage());
            return;
        }
        if(job == null)
            return;
        Dimension dimension = job.getPageDimension();
        if(dimension.width == 0 || dimension.height == 0)
        {
            dimension.width = 612;
            dimension.height = 792;
        }
        width = dimension.width;
        height = dimension.height;
        jcprinttable.setPageDimensions(dimension.width, dimension.height);
        num_pages = jcprinttable.getNumPages();
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 1, 1));
        panel.add(first_button = new Button("First"));
        first_button.addActionListener(this);
        panel.add(prev_button = new Button("Previous"));
        prev_button.addActionListener(this);
        panel.add(next_button = new Button("Next"));
        next_button.addActionListener(this);
        panel.add(last_button = new Button("Last"));
        last_button.addActionListener(this);
        panel.add(print_button = new Button("Print"));
        print_button.addActionListener(this);
        panel.add(printall_button = new Button("PrintAll"));
        printall_button.addActionListener(this);
        panel.add(close_button = new Button("Close"));
        close_button.addActionListener(this);
        add(panel, "North");
        Dimension dimension1 = new Dimension((int)((double)width * 1.05D), (int)((double)height * 1.05D));
        pane = new ScrollPane(0);
        pane.setSize(dimension1);
        print_page = new JCPrintPage(dimension1);
        pane.add(print_page);
        print_page.setSize(print_page.getPreferredSize());
        add(pane, "Center");
        add(status = new Label("", 0), "South");
        addWindowListener(this);
        setSize(600, 500);
        setVisible(true);
    }

    public void showPage(int i)
    {
        if(job == null)
        {
            return;
        } else
        {
            current_page = i;
            Image image = createImage(width, height);
            Graphics g = image.getGraphics();
            table.paintPage(g, i);
            print_page.setImage(image);
            g.dispose();
            status.setText("Page " + (current_page + 1) + " of " + num_pages + ".");
            return;
        }
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        if(actionevent.getActionCommand().equals("First"))
        {
            showPage(0);
            return;
        }
        if(actionevent.getActionCommand().equals("Previous"))
        {
            if(current_page > 0)
            {
                showPage(current_page - 1);
                return;
            }
        } else
        if(actionevent.getActionCommand().equals("Next"))
        {
            if(current_page + 1 < num_pages)
            {
                showPage(current_page + 1);
                return;
            }
        } else
        {
            if(actionevent.getActionCommand().equals("Last"))
            {
                showPage(num_pages - 1);
                return;
            }
            if(actionevent.getActionCommand().equals("Print"))
            {
                if(job != null)
                {
                    Graphics g = job.getGraphics();
                    table.paintPage(g, current_page);
                    g.dispose();
                    return;
                }
            } else
            if(actionevent.getActionCommand().equals("PrintAll"))
            {
                if(job != null)
                {
                    for(int i = 0; i < num_pages; i++)
                    {
                        Graphics g1 = job.getGraphics();
                        table.paintPage(g1, i);
                        g1.dispose();
                    }

                    return;
                }
            } else
            if(actionevent.getActionCommand().equals("Close"))
            {
                if(job != null)
                    job.end();
                setVisible(false);
                dispose();
            }
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
        setVisible(false);
        dispose();
    }

    public void windowDeactivated(WindowEvent windowevent)
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

    Button first_button;
    Button prev_button;
    Button next_button;
    Button last_button;
    Button print_button;
    Button printall_button;
    Button close_button;
    ScrollPane pane;
    JCPrintTable table;
    JCPrintPage print_page;
    Label status;
    int current_page;
    int num_pages;
    int width;
    int height;
    PrintJob job;
}
