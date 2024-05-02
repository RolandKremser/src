// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableScrollbar.java

package jclass.table3;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Vector;

// Referenced classes of package jclass.table3:
//            AdjustmentEventQueue, Clip, DefaultScrollbar, EditHandler, 
//            JCAdjustmentEvent, JCAdjustmentListener, JCCellRange, JCScrollEvent, 
//            JCTblEnum, JDKSupport, Jump, Table, 
//            Widget, JCAdjustable

public class TableScrollbar
    implements JCAdjustmentListener, Serializable, Runnable
{

    public TableScrollbar(Table table1, int i)
    {
        saveRect = new Rectangle();
        saveRange = new JCCellRange();
        queue = new AdjustmentEventQueue();
        table = table1;
        DefaultScrollbar defaultscrollbar = new DefaultScrollbar(i);
        adjustable = defaultscrollbar;
        component = defaultscrollbar;
        adjustable.addAdjustmentListener(this);
    }

    public Component getComponent()
    {
        return component;
    }

    public void setComponent(Component component1)
    {
        table.remove(component);
        component = component1;
        table.add(component);
        table.layout();
    }

    public void setAdjustable(JCAdjustable jcadjustable)
    {
        adjustable.removeAdjustmentListener(this);
        adjustable = jcadjustable;
        adjustable.addAdjustmentListener(this);
    }

    public JCAdjustable getAdjustable()
    {
        return adjustable;
    }

    public void adjustmentValueChanged(AdjustmentEvent adjustmentevent)
    {
        JCAdjustmentEvent jcadjustmentevent = new JCAdjustmentEvent(adjustmentevent);
        if(adjustable == null || component == null)
        {
            System.out.println("Scrollbar in an odd state");
            return;
        }
        queue.setEvent(jcadjustmentevent);
        if(queueThread == null)
        {
            queueThread = new Thread(this, "ScrollThread");
            queueThread.start();
        }
    }

    public void run()
    {
        while(adjustValue()) ;
        queueThread = null;
    }

    public boolean adjustValue()
    {
        JCAdjustmentEvent jcadjustmentevent = queue.getEvent();
        return adjustValue(jcadjustmentevent);
    }

    public boolean adjustValue(JCAdjustmentEvent jcadjustmentevent)
    {
        if(jcadjustmentevent != null)
        {
            int i = jcadjustmentevent.getValue();
            int j = adjustable.getOrientation();
            JCScrollEvent jcscrollevent = new JCScrollEvent(table, 1, this, j, i, jcadjustmentevent);
            table.fireJCScrollEvent(jcscrollevent);
            i = jcscrollevent.getValue();
            if(j == 0)
            {
                if((table.jump & 1) != 0 && table.isJumping)
                {
                    int k = i;
                    if((i = Jump.scroll(this, jcadjustmentevent, i)) == -999)
                        return true;
                    if(jcadjustmentevent.getAdjustmentType() == 5 && Math.abs(k - i) > 10)
                        return true;
                }
                adjustable.setValue(i);
                int l = table.XtoColumn(i + table.clip.xOffset());
                if(table.repaint)
                {
                    table.scrolling = true;
                    for(int j1 = 0; j1 < table.clip_list.length; j1++)
                    {
                        Clip clip = table.clip_list[j1];
                        if(clip.isHorizontal())
                            scroll_horiz(clip, i, saveRect);
                    }

                    if(table.hasText())
                        table.editHandler.move(0, i);
                    table.scrolling = false;
                }
                table.left_column = l;
                if(table.component_series.size() > 0)
                    Widget.scroll(table, 1);
            } else
            if(j == 1)
            {
                if((table.jump & 2) != 0 && table.isJumping && (i = Jump.scroll(this, jcadjustmentevent, i)) == -999)
                    return true;
                adjustable.setValue(i);
                int i1 = table.YtoRow(i + table.clip.yOffset());
                if(table.repaint)
                {
                    table.scrolling = true;
                    for(int k1 = 0; k1 < table.clip_list.length; k1++)
                    {
                        Clip clip1 = table.clip_list[k1];
                        if(clip1.isVertical())
                            scroll_vert(clip1, i, saveRect);
                    }

                    if(table.hasText())
                        table.editHandler.move(0, i);
                    table.scrolling = false;
                }
                table.top_row = i1;
                if(table.component_series.size() > 0)
                    Widget.scroll(table, 2);
            }
            jcscrollevent = new JCScrollEvent(table, 2, this, j, i, jcadjustmentevent);
            table.fireJCScrollEvent(jcscrollevent);
            return true;
        } else
        {
            return false;
        }
    }

    private void scroll_horiz(Clip clip, int i, Rectangle rectangle)
    {
        if(i == clip.horiz_origin)
        {
            return;
        } else
        {
            int j = i - clip.horiz_origin;
            Math.abs(j);
            clip.horiz_origin = i;
            JDKSupport.setBounds(rectangle, 0, 0, clip.size().width, clip.size().height);
            clip.repaint();
            return;
        }
    }

    private void scroll_vert(Clip clip, int i, Rectangle rectangle)
    {
        if(i == clip.vert_origin)
        {
            return;
        } else
        {
            int j = i - clip.vert_origin;
            Math.abs(j);
            clip.vert_origin = i;
            JDKSupport.setBounds(rectangle, 0, 0, clip.size().width, clip.size().height);
            clip.repaint();
            return;
        }
    }

    public void show(boolean flag)
    {
        component.show(flag);
    }

    public boolean isVisible()
    {
        return component.isVisible();
    }

    public void setBounds(Rectangle rectangle)
    {
        component.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void setBounds(int i, int j, int k, int l)
    {
        component.setBounds(i, j, k, l);
    }

    public void setUnitIncrement(int i)
    {
        adjustable.setUnitIncrement(i);
    }

    public int getUnitIncrement()
    {
        return adjustable.getUnitIncrement();
    }

    public void setValues(int i, int j, int k, int l)
    {
        if(!(adjustable instanceof Scrollbar))
        {
            adjustable.setMinimum(k);
            adjustable.setMaximum(l);
            adjustable.setVisibleAmount(j);
            adjustable.setValue(i);
            return;
        } else
        {
            ((Scrollbar)adjustable).setValues(i, j, k, l);
            return;
        }
    }

    public void setValue(int i)
    {
        adjustable.setValue(i);
        adjustmentValueChanged(new JCAdjustmentEvent(adjustable, 601, 5, i));
    }

    public int getOrientation()
    {
        return adjustable.getOrientation();
    }

    public boolean atEnd()
    {
        int i = adjustable.getValue();
        int j = adjustable.getMaximum();
        int k = adjustable.getVisibleAmount();
        return i == 0 || i >= j - k;
    }

    static boolean scroll(Table table1, String s)
    {
        if(!table1.repaint)
            return false;
        TableScrollbar tablescrollbar;
        if(s.equals("PAGELEFT") || s.equals("PAGERIGHT"))
            tablescrollbar = table1.horiz_sb;
        else
            tablescrollbar = table1.vert_sb;
        if(tablescrollbar == null || !tablescrollbar.isVisible())
            return false;
        int i;
        byte byte0;
        if(s.equals("PAGELEFT") || s.equals("PAGEUP"))
        {
            i = tablescrollbar.getAdjustable().getValue() - tablescrollbar.getAdjustable().getBlockIncrement();
            byte0 = 3;
        } else
        {
            i = tablescrollbar.getAdjustable().getValue() + tablescrollbar.getAdjustable().getBlockIncrement();
            byte0 = 4;
        }
        tablescrollbar.getAdjustable().setValue(i);
        i = tablescrollbar.getAdjustable().getValue();
        tablescrollbar.adjustmentValueChanged(new JCAdjustmentEvent(tablescrollbar.getAdjustable(), 601, byte0, i));
        return true;
    }

    void dispose()
    {
        if(component != null)
        {
            if(component instanceof DefaultScrollbar)
                ((DefaultScrollbar)component).dispose();
            component = null;
        }
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    JCAdjustable adjustable;
    Component component;
    Table table;
    Rectangle saveRect;
    JCCellRange saveRange;
    AdjustmentEventQueue queue;
    Thread queueThread;
}
