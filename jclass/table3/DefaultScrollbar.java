// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultScrollbar.java

package jclass.table3;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JComponent;
import jclass.util.JCImageCreator;
import jclass.util.JCVector;

// Referenced classes of package jclass.table3:
//            JCAdjustable, JCTblEnum, JDKSupport, Table

public class DefaultScrollbar extends JComponent
    implements JCAdjustable, Runnable
{

    public DefaultScrollbar(int i)
    {
        listeners = new JCVector();
        line = 21;
        page = 200;
        btnSize = 16;
        dragStart = -999;
        dragValueStart = -999;
        repeatScroll = true;
        trough_rect = new Rectangle();
        btn_rect = new Rectangle();
        new_fg = true;
        dir = i;
        enableEvents(16L);
        enableEvents(32L);
    }

    public int getOrientation()
    {
        return dir;
    }

    public synchronized int getValue()
    {
        return value;
    }

    protected int check(int i)
    {
        return Math.min(Math.max(min, i), max - slider);
    }

    public synchronized void setValue(int i)
    {
        i = check(i);
        if(i != value)
        {
            value = i;
            repaint();
        }
    }

    public void setValue(int i, boolean flag)
    {
        i = check(i);
        if(i == value)
            return;
        value = i;
        repaint();
        if(!flag)
            return;
        Event event = mouse;
        if(btnPress == 0 || event == null)
        {
            event = new Event(this, 605, new Integer(i));
            event.when = System.currentTimeMillis();
        }
        scroll(event, getValue());
    }

    public void scroll(Event event, int i)
    {
        byte byte0 = 5;
        switch(event.id)
        {
        case 602: // Event.SCROLL_LINE_DOWN
            byte0 = 1;
            break;

        case 601: // Event.SCROLL_LINE_UP
            byte0 = 2;
            break;

        case 604: // Event.SCROLL_PAGE_DOWN
            byte0 = 4;
            break;

        case 603: // Event.SCROLL_PAGE_UP
            byte0 = 3;
            break;
        }
        AdjustmentEvent adjustmentevent = new AdjustmentEvent(this, 601, byte0, i);
        for(int j = 0; j < listeners.size(); j++)
            ((AdjustmentListener)listeners.elementAt(j)).adjustmentValueChanged(adjustmentevent);

    }

    public int getMinimum()
    {
        return min;
    }

    public int getMaximum()
    {
        return max;
    }

    public int getVisibleAmount()
    {
        return slider;
    }

    public void setUnitIncrement(int i)
    {
        line = i;
    }

    public int getUnitIncrement()
    {
        return line;
    }

    public void setBlockIncrement(int i)
    {
        page = i;
    }

    public int getBlockIncrement()
    {
        return page;
    }

    public synchronized void setValues(int i, int j, int k, int l)
    {
        if(l < k)
            l = k;
        page = slider = j;
        min = k;
        max = l;
        value = i;
        if(isDisplayable())
        {
            int i1 = barSize;
            int j1 = sliderSize;
            int k1 = sliderStart;
            setSliderSize();
            if(i1 != barSize || j1 != sliderSize || k1 != sliderStart)
                repaint();
        }
    }

    public synchronized void setMinimum(int i)
    {
        setValues(value, slider, i, max);
    }

    public synchronized void setMaximum(int i)
    {
        setValues(value, slider, min, i);
    }

    public synchronized void setVisibleAmount(int i)
    {
        setValues(value, i, min, max);
    }

    public boolean atEnd()
    {
        return value == 0 || value >= max - slider;
    }

    public boolean dragging()
    {
        return dragStart != -999;
    }

    public void setFilterTime(long l)
    {
        filter_time = l;
    }

    public boolean isRepeatScroll()
    {
        return repeatScroll;
    }

    public void setRepeatScroll(boolean flag)
    {
        repeatScroll = flag;
    }

    protected int preferredWidth()
    {
        return dir != 1 ? 100 : 16;
    }

    protected int preferredHeight()
    {
        return dir != 0 ? 100 : 16;
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        if(dragging())
        {
            int k = value;
            value = -999;
            setValue(k, true);
        }
        btnPress = 0;
        mouse = null;
        dragStart = dragValueStart = -999;
        repaint();
        return true;
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(getParent() instanceof Table)
        {
            Table table = (Table)getParent();
            if(table.resize_column != -999 || table.resize_row != -999)
                return true;
        }
        if(sliderSize == barSize)
            return true;
        if(event.when - last_time < 20L)
            return true;
        last_time = event.when;
        if(btnPress != 0)
            return true;
        int k = dir != 0 ? j : i;
        int l = btnSize;
        if(k < 0)
            return true;
        if(k > btnSize * 2 + barSize)
            return true;
        if(k > l + sliderStart && k < l + sliderStart + sliderSize)
            return true;
        if(event.controlDown())
        {
            if(k < l + sliderStart + sliderSize / 2)
                setValue(min, true);
            else
                setValue(max - slider, true);
            return true;
        }
        arrow_pressed = 0;
        if(k < l)
        {
            btnPress = arrow_pressed = -line;
            event.id = 601;
        } else
        if(k < l + sliderStart)
        {
            btnPress = -page;
            event.id = 603;
        } else
        if(k < l + barSize)
        {
            btnPress = page;
            event.id = 604;
        } else
        {
            btnPress = arrow_pressed = line;
            event.id = 602;
        }
        mouse = event;
        setValue(value + btnPress, true);
        if(arrow_pressed != 0 && repeatScroll)
            (new Thread(this)).start();
        repaint();
        return true;
    }

    public void run()
    {
        int i = 250;
        do
        {
            try
            {
                Thread.currentThread();
                Thread.sleep(i);
            }
            catch(Throwable _ex) { }
            if(btnPress == 0)
            {
                Thread.currentThread().stop();
                repaint();
                return;
            }
            setValue(value + btnPress, true);
            i = 50;
        } while(true);
    }

    public boolean mouseDrag(Event event, int i, int j)
    {
        if(getParent() instanceof Table)
        {
            Table table = (Table)getParent();
            if(table.resize_column != -999 || table.resize_row != -999)
                return true;
        }
        if(sliderSize == barSize)
            return true;
        if(btnPress != 0)
            return true;
        int k = dir != 0 ? event.y : event.x;
        if(dragStart == -999)
        {
            dragStart = k;
            dragValueStart = value;
        } else
        {
            boolean flag = event.when - last_time >= filter_time;
            if(flag)
                last_time = event.when;
            int l = toValue(k - dragStart);
            setValue(dragValueStart + l, flag);
        }
        return true;
    }

    public boolean keyDown(Event event, int i)
    {
        if(sliderSize == barSize)
            return true;
        if(i == 1002)
        {
            event.id = 603;
            setValue(value - page, true);
            return true;
        }
        if(i == 1003)
        {
            event.id = 604;
            setValue(value + page, true);
            return true;
        }
        if(i == 1000)
        {
            setValue(min, true);
            return true;
        }
        if(i == 1001)
        {
            setValue(max - slider, true);
            return true;
        } else
        {
            return super.keyDown(event, i);
        }
    }

    public void setBounds(Rectangle rectangle)
    {
        setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void setBounds(int i, int j, int k, int l)
    {
        major = dir != 0 ? l : k;
        minor = dir != 0 ? k : l;
        super.setBounds(i, j, k, l);
    }

    void drawTroughAndSlider()
    {
        if(!isDisplayable())
            return;
        if(dir == 0)
            JDKSupport.setBounds(trough_rect, btnSize, 1, major - 2 * btnSize, minor - 2);
        else
            JDKSupport.setBounds(trough_rect, 1, btnSize, minor - 2, major - 2 * btnSize);
        Graphics g = image.getGraphics();
        g.clipRect(trough_rect.x, trough_rect.y, trough_rect.width, trough_rect.height);
        paintComponent(g);
        g.dispose();
        g = getGraphics();
        g.clipRect(trough_rect.x, trough_rect.y, trough_rect.width, trough_rect.height);
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    void setSliderSize()
    {
        barSize = Math.max(10, major - 32);
        if(min >= max)
            sliderSize = barSize;
        else
            sliderSize = Math.min(barSize, Math.max(toPixels(slider), 8));
        sliderStart = Math.max(0, Math.min(toPixels(value - min), barSize - sliderSize - 1));
    }

    private void fill3DRect(Graphics g, int i, int j, int k, int l, boolean flag)
    {
        Color color = g.getColor();
        Color color1 = color.equals(Color.white) ? BRIGHTER : color.brighter();
        Color color2 = color.equals(Color.white) ? DARKER : color.darker();
        if(!flag)
            g.setColor(color2);
        g.fillRect(i + 1, j + 1, k - 2, l - 2);
        g.setColor(flag ? color1 : color2);
        g.drawLine(i, j, i, (j + l) - 1);
        g.drawLine(i + 1, j, (i + k) - 2, j);
        g.setColor(flag ? color2 : color1);
        g.drawLine(i + 1, (j + l) - 1, (i + k) - 1, (j + l) - 1);
        g.drawLine((i + k) - 1, j, (i + k) - 1, (j + l) - 2);
        g.setColor(color);
    }

    private void fill3DEdgeRect(Graphics g, int i, int j, int k, int l, boolean flag)
    {
        Color color = g.getColor();
        fill3DRect(g, i + 1, j + 1, k - 2, l - 2, flag);
        g.setColor(Color.black);
        g.drawRect(i, j, k - 1, l - 1);
        g.setColor(color);
    }

    protected void drawSlider(Graphics g)
    {
        g.setColor(getBackground());
        if(dir == 0)
        {
            fill3DEdgeRect(g, sliderStart + btnSize, 0, sliderSize + 1, minor, true);
            g.setColor(Color.black);
            g.drawLine(btnSize, 0, (btnSize + major) - 1, 0);
            g.drawRect(btnSize, minor - 1, (btnSize + major) - 1, minor - 1);
            return;
        } else
        {
            fill3DEdgeRect(g, 0, sliderStart + btnSize, minor, sliderSize + 1, true);
            g.setColor(Color.black);
            g.drawLine(0, btnSize, 0, major - 1);
            g.drawRect(minor - 1, btnSize, minor - 1, major - 1);
            return;
        }
    }

    public void update(Graphics g)
    {
    }

    public synchronized void paint(Graphics g)
    {
        if(size().height == 0 || size().width == 0)
            return;
        if(image == null || image.getHeight(null) < size().height || image.getWidth(null) < size().width)
            image = createImage(size().width, size().height);
        Graphics g1 = image.getGraphics();
        Rectangle rectangle = g.getClipRect();
        if(rectangle != null)
            g1.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        paintComponent(g1);
        g.drawImage(image, 0, 0, null);
        g1.dispose();
    }

    public void paintComponent(Graphics g)
    {
        setSliderSize();
        g.setColor(getBackground().equals(Color.white) ? BRIGHTER : getBackground().brighter());
        g.fillRect(0, 0, size().width, size().height);
        g.setColor(getBackground());
        if(btnPress == 0)
            arrow_pressed = 0;
        Rectangle rectangle = g.getClipRect();
        if(rectangle == null)
            rectangle = new Rectangle(size());
        if(dir == 0)
        {
            JDKSupport.setBounds(btn_rect, 0, 0, btnSize, minor);
            if(btn_rect.intersects(rectangle))
            {
                fill3DEdgeRect(g, 0, 0, btnSize, minor, arrow_pressed > -line);
                drawImage(g, arrow_left_pixels, arrow_left_image, 5, 4);
            }
            JDKSupport.setBounds(btn_rect, major - btnSize, 0, btnSize, minor);
            if(btn_rect.intersects(rectangle))
            {
                fill3DEdgeRect(g, major - btnSize, 0, btnSize, minor, arrow_pressed < line);
                drawImage(g, arrow_right_pixels, arrow_right_image, (major - btnSize) + 6, 3);
            }
            drawSlider(g);
            return;
        }
        if(dir == 1)
        {
            JDKSupport.setBounds(btn_rect, 0, 0, minor, btnSize);
            if(btn_rect.intersects(rectangle))
            {
                fill3DEdgeRect(g, 0, 0, minor, btnSize, arrow_pressed > -line);
                drawImage(g, arrow_up_pixels, arrow_up_image, 4, 6);
            }
            JDKSupport.setBounds(btn_rect, 0, major - btnSize, minor, btnSize);
            if(btn_rect.intersects(rectangle))
            {
                fill3DEdgeRect(g, 0, major - btnSize, minor, btnSize, arrow_pressed < line);
                drawImage(g, arrow_down_pixels, arrow_down_image, 4, (major - btnSize) + 5);
            }
            drawSlider(g);
        }
    }

    protected int toPixels(int i)
    {
        if(max == min)
        {
            return 0;
        } else
        {
            int j = (int)(((long)barSize * (long)i) / (long)(max - min));
            return Math.max(j, 0);
        }
    }

    protected int toValue(int i)
    {
        if(barSize == 0)
            return 0;
        else
            return (int)(((long)i * (long)(max - min)) / (long)barSize);
    }

    public void setForeground(Color color)
    {
        new_fg = color != getForeground();
        super.setForeground(color);
        repaint();
    }

    private Image createImage(String as[], Image image1)
    {
        if(!new_fg && image1 != null)
            return image1;
        if(creator == null)
            creator = new JCImageCreator(this);
        creator.setColor('b', getForeground().darker());
        new_fg = false;
        creator.setSize(as[0].length(), as.length);
        return creator.create(as);
    }

    private void drawImage(Graphics g, String as[], Image image1, int i, int j)
    {
        image1 = createImage(as, image1);
        g.drawImage(image1, i, j, null);
    }

    protected String paramString()
    {
        return super.paramString() + ",val=" + value + ",vis=" + getVisibleAmount() + ",min=" + min + ",max=" + max + (dir != 1 ? ",horz" : ",vert");
    }

    public void addAdjustmentListener(AdjustmentListener adjustmentlistener)
    {
        if(!listeners.contains(adjustmentlistener))
            listeners.addElement(adjustmentlistener);
    }

    public void removeAdjustmentListener(AdjustmentListener adjustmentlistener)
    {
        listeners.removeElement(adjustmentlistener);
    }

    protected void processMouseEvent(MouseEvent mouseevent)
    {
        Event event = new Event(this, mouseevent.getWhen(), mouseevent.getID(), mouseevent.getX(), mouseevent.getY(), 0, mouseevent.getModifiers());
        event.clickCount = mouseevent.getClickCount();
        switch(event.id)
        {
        case 501: // Event.MOUSE_DOWN
            mouseDown(event, event.x, event.y);
            break;

        case 502: // Event.MOUSE_UP
            mouseUp(event, event.x, event.y);
            break;

        case 504: // Event.MOUSE_EVENT
            mouseEnter(event, event.x, event.y);
            break;

        case 505: // Event.MOUSE_EXIT
            mouseExit(event, event.x, event.y);
            break;
        }
        super.processMouseEvent(mouseevent);
    }

    protected void processMouseMotionEvent(MouseEvent mouseevent)
    {
        Event event = new Event(this, mouseevent.getWhen(), mouseevent.getID(), mouseevent.getX(), mouseevent.getY(), 0, mouseevent.getModifiers());
        switch(event.id)
        {
        case 503: // Event.MOUSE_MOVE
            mouseMove(event, event.x, event.y);
            break;

        case 506: // Event.MOUSE_DRAG
            mouseDrag(event, event.x, event.y);
            break;
        }
        super.processMouseMotionEvent(mouseevent);
    }

    void dispose()
    {
        if(creator != null)
            creator = null;
    }

    public Graphics getGraphics()
    {
        Graphics g = super.getGraphics();
        Rectangle rectangle = getVisibleRect();
        g.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        return g;
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    int value;
    int min;
    int max;
    int dir;
    protected int slider;
    transient Image image;
    JCVector listeners;
    int line;
    int page;
    int arrow_pressed;
    long filter_time;
    long last_time;
    private static final int MIN_SLIDERSIZE = 10;
    private static final int MIN_BTNSIZE = 3;
    private static final int MAX_BTNSIZE = 16;
    int btnPress;
    private int btnSize;
    private int barSize;
    private int sliderStart;
    private int sliderSize;
    private int major;
    private int minor;
    private static final Color BRIGHTER = new Color(200, 200, 200);
    private static final Color DARKER = new Color(140, 140, 140);
    private int dragStart;
    private int dragValueStart;
    private Event mouse;
    private boolean repeatScroll;
    Rectangle trough_rect;
    Rectangle btn_rect;
    private final String arrow_down_pixels[] = {
        "       ", "bbbbbbb", " bbbbb ", "  bbb  ", "   b   "
    };
    private transient Image arrow_down_image;
    private final String arrow_up_pixels[] = {
        "   b   ", "  bbb  ", " bbbbb ", "bbbbbbb"
    };
    private transient Image arrow_up_image;
    private final String arrow_left_pixels[] = {
        "   b", "  bb", " bbb", "bbbb", " bbb", "  bb", "   b"
    };
    private transient Image arrow_left_image;
    private final String arrow_right_pixels[] = {
        "    ", "b   ", "bb  ", "bbb ", "bbbb", "bbb ", "bb  ", "b   "
    };
    private transient Image arrow_right_image;
    private transient JCImageCreator creator;
    private boolean new_fg;

}
