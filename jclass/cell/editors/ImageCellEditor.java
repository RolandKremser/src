// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageCellEditor.java

package jclass.cell.editors;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.EventObject;
import jclass.cell.*;
import jclass.util.JCImageCreator;

public class ImageCellEditor extends Canvas
    implements CellEditor, KeyListener, MouseListener, FocusListener
{

    public ImageCellEditor()
    {
        support = new CellEditorSupport();
        isFullSize = true;
        modified = false;
        inDialog = false;
        initialEntry = false;
        contextPending = false;
        addFocusListener(this);
        addKeyListener(this);
        addMouseListener(this);
        tracker = new MediaTracker(this);
    }

    public void addNotify()
    {
        super.addNotify();
        getParent().addMouseListener(this);
    }

    public void removeNotify()
    {
        getParent().removeMouseListener(this);
        super.removeNotify();
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        modified = false;
        isFullSize = true;
        cellInfo = cellinfo;
        readData(obj);
        originalData = data;
    }

    public void setBounds(int i, int j, int k, int l)
    {
        Dimension dimension = getPreferredSize(cellInfo, data);
        if(k < dimension.width + 4 || l < dimension.height + 4)
        {
            super.setBounds(i, j, dimension.width + 4, dimension.height + 4);
            return;
        } else
        {
            super.setBounds(i, j, k, l);
            return;
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        Rectangle rectangle = getBounds();
        g.setColor(cellInfo.isEditable() ? cellInfo.getBackground().brighter() : cellInfo.getBackground());
        if(data == null)
            g.fillRect(0, 0, rectangle.width, rectangle.height);
        else
        if(isFullSize)
        {
            g.drawImage(data, 2, 2, g.getColor(), this);
        } else
        {
            Rectangle rectangle1 = cellInfo.getDrawingArea();
            g.drawImage(data, (rectangle.width - rectangle1.width) / 2, (rectangle.height - rectangle1.height) / 2, rectangle1.width, rectangle1.height, g.getColor(), this);
        }
        Utilities.drawBorder(g, 8, 2, 0, 0, rectangle.width, rectangle.height, Color.black, Color.white);
    }

    void readData(Object obj)
    {
        if(obj != null && !(obj instanceof Image))
        {
            data = JCImageCreator.getImage((byte[])obj, this);
            return;
        } else
        {
            data = (Image)obj;
            return;
        }
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    public Object getCellEditorValue()
    {
        if(isModified())
            return dataBytes;
        else
            return data;
    }

    public Component getComponent()
    {
        return this;
    }

    public boolean isModified()
    {
        return modified;
    }

    public boolean stopCellEditing()
    {
        return true;
    }

    public void cancelCellEditing()
    {
        data = originalData;
        toggleSize(true);
        repaint();
        modified = false;
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        if(data == null || !isFullSize)
        {
            Rectangle rectangle = cellinfo.getDrawingArea();
            return new Dimension(rectangle.width, rectangle.height);
        } else
        {
            waitForImage(data);
            return new Dimension(data.getWidth(null), data.getHeight(null));
        }
    }

    public KeyModifier[] getReservedKeys()
    {
        return keys;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        support.removeCellEditorListener(celleditorlistener);
    }

    boolean waitForImage(Image image)
    {
        try
        {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        }
        catch(Exception _ex)
        {
            return false;
        }
        return !tracker.isErrorAny();
    }

    void toggleSize()
    {
        isFullSize = !isFullSize;
        changeSize();
    }

    void toggleSize(boolean flag)
    {
        isFullSize = flag;
        changeSize();
    }

    void changeSize()
    {
        Rectangle rectangle = Utilities.getWholeCell(cellInfo, cellInfo.getDrawingArea());
        Rectangle rectangle1 = getBounds();
        setBounds(rectangle1.x, rectangle1.y, rectangle.width, rectangle.height);
    }

    public void keyPressed(KeyEvent keyevent)
    {
        switch(keyevent.getKeyCode())
        {
        case 10: // '\n'
            toggleSize();
            support.fireStopEditing(new CellEditorEvent(keyevent));
            return;

        case 27: // '\033'
            toggleSize(false);
            support.fireCancelEditing(new CellEditorEvent(keyevent));
            return;

        case 32: // ' '
            toggleSize();
            return;

        case 76: // 'L'
        case 79: // 'O'
            loadNewImage();
            return;
        }
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void focusGained(FocusEvent focusevent)
    {
        if(!inDialog)
        {
            initialEntry = isFullSize;
            if(!isFullSize)
                toggleSize(true);
        }
    }

    public void focusLost(FocusEvent focusevent)
    {
        if(!inDialog)
            toggleSize(false);
    }

    static Frame getFrame(Component component)
    {
        while((component = component.getParent()) != null) 
            if(component instanceof Frame)
                return (Frame)component;
        return null;
    }

    void loadNewImage()
    {
        FileDialog filedialog = new FileDialog(getFrame(this));
        filedialog.setMode(0);
        inDialog = true;
        filedialog.show();
        requestFocus();
        inDialog = false;
        if(filedialog.getFile() == null)
            return;
        String s = filedialog.getDirectory() + filedialog.getFile();
        dataBytes = null;
        try
        {
            FileInputStream fileinputstream = new FileInputStream(new File(s));
            int i = fileinputstream.available();
            if(i > 0)
                dataBytes = new byte[i];
            int j = fileinputstream.read(dataBytes);
            fileinputstream.close();
            if(j != i)
                dataBytes = null;
        }
        catch(Exception exception)
        {
            dataBytes = null;
            System.out.println(exception.toString());
        }
        if(dataBytes != null)
        {
            data = Toolkit.getDefaultToolkit().createImage(dataBytes);
            modified = true;
            readData(data);
            toggleSize(true);
            repaint();
        }
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        if(mouseevent.getSource() == this)
        {
            if(mouseevent.isPopupTrigger())
            {
                contextPending = true;
                return;
            }
        } else
        {
            toggleSize(false);
            initialEntry = false;
        }
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        handleMouseContext(mouseevent);
    }

    void handleMouseContext(MouseEvent mouseevent)
    {
        if(mouseevent.getSource() == this)
        {
            if(contextPending || mouseevent.isPopupTrigger())
            {
                loadNewImage();
                contextPending = false;
                return;
            }
            if(initialEntry)
            {
                initialEntry = false;
                return;
            }
            toggleSize();
        }
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        setCursor(Cursor.getDefaultCursor());
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    protected transient Image data;
    protected transient Image originalData;
    protected CellInfo cellInfo;
    protected CellEditorSupport support;
    protected boolean isFullSize;
    protected boolean modified;
    protected boolean inDialog;
    protected boolean initialEntry;
    protected boolean contextPending;
    protected transient byte dataBytes[];
    transient MediaTracker tracker;
    protected static final int BORDER_SIZE = 2;
    protected KeyModifier keys[];
}
