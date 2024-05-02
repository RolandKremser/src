// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCSpin.java

package jclass.field;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.EventObject;
import javax.swing.JComponent;
import javax.swing.border.Border;
import jclass.base.BaseComponent;
import jclass.base.TransientComponent;
import jclass.bwt.BWTEnum;
import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCArrowButton;
import jclass.bwt.JCButton;
import jclass.bwt.JCComponent;
import jclass.bwt.JCContainer;
import jclass.bwt.JCTextComponent;
import jclass.bwt.JCTextCursorListener;
import jclass.bwt.JCTextField;
import jclass.bwt.JCTextInterface;
import jclass.bwt.JCTextListener;
import jclass.util.JCListenerList;

// Referenced classes of package jclass.field:
//            JCSpinEvent, JCSpinListener, SpinArrowButton, SpinField

public abstract class JCSpin extends JCContainer
    implements JCActionListener, JCTextInterface
{
    class textKeyListener
        implements KeyListener
    {

        public void keyTyped(KeyEvent keyevent)
        {
        }

        public void keyPressed(KeyEvent keyevent)
        {
            JCSpinEvent jcspinevent = null;
            int i = keyevent.getKeyCode();
            switch(i)
            {
            case 38: // '&'
                if(arrow_key_spinning_allowed)
                {
                    jcspinevent = new JCSpinEvent(this, JCSpinEvent.SPIN_UP, text.getText());
                    keyevent.consume();
                }
                break;

            case 40: // '('
                if(arrow_key_spinning_allowed)
                {
                    jcspinevent = new JCSpinEvent(this, JCSpinEvent.SPIN_DOWN, text.getText());
                    keyevent.consume();
                }
                break;
            }
            if(jcspinevent != null)
            {
                JCSpinListener jcspinlistener;
                for(Enumeration enumeration = JCListenerList.elements(listener_list); enumeration.hasMoreElements(); jcspinlistener.spin(jcspinevent))
                    jcspinlistener = (JCSpinListener)enumeration.nextElement();

                checkArrowButtons();
            }
        }

        public void keyReleased(KeyEvent keyevent)
        {
        }

        textKeyListener()
        {
        }
    }


    public JCSpin(int i)
    {
        this();
        text.setColumns(i);
    }

    public JCSpin()
    {
        auto_arrow_disable = true;
        arrow_key_spinning_allowed = true;
        border = 2;
        super.setLayout(null);
        add(text = createTextField());
        add(incr_arrow = new SpinArrowButton(10, this));
        add(decr_arrow = new SpinArrowButton(9, this));
        text.addKeyListener(new textKeyListener());
        incr_arrow.setInitialRepeatDelay(250);
        decr_arrow.setInitialRepeatDelay(250);
        incr_arrow.setTraversable(false);
        decr_arrow.setTraversable(false);
        text.setShadowThickness(0);
        incr_arrow.addActionListener(this);
        decr_arrow.addActionListener(this);
    }

    public void requestFocus()
    {
        if(text != null)
            text.requestFocus();
    }

    protected JCTextField createTextField()
    {
        return new SpinField();
    }

    public JCTextField getTextField()
    {
        return text;
    }

    public JCArrowButton getDecrementArrow()
    {
        return decr_arrow;
    }

    public JCArrowButton getIncrementArrow()
    {
        return incr_arrow;
    }

    public boolean getAutoArrowDisable()
    {
        return auto_arrow_disable;
    }

    public void setAutoArrowDisable(boolean flag)
    {
        auto_arrow_disable = flag;
        checkArrowButtons();
    }

    public boolean isArrowKeySpinningAllowed()
    {
        return arrow_key_spinning_allowed;
    }

    public void setArrowKeySpinningAllowed(boolean flag)
    {
        arrow_key_spinning_allowed = flag;
    }

    public void addSpinListener(JCSpinListener jcspinlistener)
    {
        listener_list = JCListenerList.add(listener_list, jcspinlistener);
    }

    public void removeSpinListener(JCSpinListener jcspinlistener)
    {
        listener_list = JCListenerList.remove(listener_list, jcspinlistener);
    }

    protected int preferredWidth()
    {
        return text.getPreferredSize().width + incr_arrow.getPreferredSize().width + 2 * border;
    }

    protected int preferredHeight()
    {
        return 2 * border + text.getPreferredSize().height;
    }

    public void setBackground(Color color)
    {
        super.setBackground(color);
        decr_arrow.setBackground(color);
        incr_arrow.setBackground(color);
    }

    public void setForeground(Color color)
    {
        super.setForeground(color);
        decr_arrow.setForeground(color);
        incr_arrow.setForeground(color);
    }

    public synchronized void doLayout()
    {
        int i = text.getPreferredSize().height;
        int j = i / 2;
        int k = incr_arrow.getPreferredSize().width;
        int l = getSize().width - k - border;
        int i1 = i % 2;
        text.setBounds(border, border, getSize().width - k - 2 * border, j * 2);
        incr_arrow.setBounds(l, border, k, j);
        decr_arrow.setBounds(l, j + border, k, j + i1);
        checkArrowButtons();
    }

    public void setBorder(Border border1)
    {
        super.swing_border = border1;
        setChildrenBorder(border1);
    }

    public void paintInterior(Graphics g)
    {
        g.clearRect(0, 0, getSize().width, getSize().height);
        if(super.swing_border != null)
        {
            super.swing_border.paintBorder(this, g, 0, 0, size().width, size().height);
            return;
        } else
        {
            jclass.base.Border.draw(g, 8, border, 0, 0, getSize().width, getSize().height, getBackground(), getForeground());
            return;
        }
    }

    public abstract void checkArrowButtons();

    public void actionPerformed(JCActionEvent jcactionevent)
    {
        if(TRACE)
            System.out.println("JCSpin.actionPerformed()");
        int i = JCSpinEvent.NO_SPIN;
        if(jcactionevent.getSource() == incr_arrow)
            i = JCSpinEvent.SPIN_UP;
        else
        if(jcactionevent.getSource() == decr_arrow)
            i = JCSpinEvent.SPIN_DOWN;
        if(TRACE)
            System.out.println("\tposting spin event");
        JCSpinEvent jcspinevent = new JCSpinEvent(this, i, text.getText());
        JCSpinListener jcspinlistener;
        for(Enumeration enumeration = JCListenerList.elements(listener_list); enumeration.hasMoreElements(); jcspinlistener.spin(jcspinevent))
        {
            jcspinlistener = (JCSpinListener)enumeration.nextElement();
            if(TRACE)
                System.out.println("\t\tcalling l.spin()");
        }

        if(TRACE)
            System.out.println("\tabout to check arrow buttons");
        checkArrowButtons();
        if(TRACE)
            System.out.println("\tarrow buttons checked");
    }

    public final void setLayout(LayoutManager layoutmanager)
    {
    }

    public String getSelectedText()
    {
        return text.getSelectedText();
    }

    public boolean isEditable()
    {
        return text.isEditable();
    }

    public boolean getEditable()
    {
        return text.getEditable();
    }

    public void setEditable(boolean flag)
    {
        text.setEditable(flag);
    }

    public int getSelectionStart()
    {
        return text.getSelectionStart();
    }

    public int getSelectionEnd()
    {
        return text.getSelectionEnd();
    }

    public void setSelectionStart(int i)
    {
        text.setSelectionStart(i);
    }

    public void setSelectionEnd(int i)
    {
        text.setSelectionEnd(i);
    }

    public void select(int i, int j)
    {
        text.select(i, j);
    }

    public void selectAll()
    {
        text.selectAll();
    }

    public void insert(String s, int i)
    {
        text.insert(s, i);
    }

    public void append(String s)
    {
        text.append(s);
    }

    public void replaceRange(String s, int i, int j)
    {
        text.replaceRange(s, i, j);
    }

    public int getColumns()
    {
        return text.getColumns();
    }

    public void setColumns(int i)
    {
        text.setColumns(i);
    }

    public int getAlignment()
    {
        return text.getAlignment();
    }

    public void setAlignment(int i)
    {
        text.setAlignment(i);
    }

    public int getMaximumLength()
    {
        return text.getMaximumLength();
    }

    public void setMaximumLength(int i)
    {
        text.setMaximumLength(i);
    }

    public int getStringCase()
    {
        return text.getStringCase();
    }

    public void setStringCase(int i)
    {
        text.setStringCase(i);
    }

    public void beep()
    {
        text.beep();
    }

    public boolean getChanged()
    {
        return text.getChanged();
    }

    public Dimension getMinimumSize(int i)
    {
        return text.getMinimumSize(i);
    }

    public int[] getSelectionList()
    {
        return text.getSelectionList();
    }

    public void setSelectionList(int ai[])
    {
        text.setSelectionList(ai);
    }

    public Color getSelectedBackground()
    {
        return text.getSelectedBackground();
    }

    public void setSelectedBackground(Color color)
    {
        text.setSelectedBackground(color);
    }

    public Color getSelectedForeground()
    {
        return text.getSelectedForeground();
    }

    public void setSelectedForeground(Color color)
    {
        text.setSelectedForeground(color);
    }

    public int pointToPosition(int i, int j)
    {
        return text.pointToPosition(i, j);
    }

    public void showPosition(int i)
    {
        text.showPosition(i);
    }

    public int getCursorPosition()
    {
        return text.getCursorPosition();
    }

    public void setCursorPosition(int i)
    {
        text.setCursorPosition(i);
    }

    public void setOverstrike(boolean flag)
    {
        text.setOverstrike(flag);
    }

    public boolean getOverstrike()
    {
        return text.getOverstrike();
    }

    public int getLastPosition()
    {
        return text.getLastPosition();
    }

    public boolean getShowCursorPosition()
    {
        return text.getShowCursorPosition();
    }

    public void setShowCursorPosition(boolean flag)
    {
        text.setShowCursorPosition(flag);
    }

    public void addTextListener(JCTextListener jctextlistener)
    {
        text.addTextListener(jctextlistener);
    }

    public void removeTextListener(JCTextListener jctextlistener)
    {
        text.removeTextListener(jctextlistener);
    }

    public void addTextCursorListener(JCTextCursorListener jctextcursorlistener)
    {
        text.addTextCursorListener(jctextcursorlistener);
    }

    public void removeTextCursorListener(JCTextCursorListener jctextcursorlistener)
    {
        text.removeTextCursorListener(jctextcursorlistener);
    }

    public void addFocusListener(FocusListener focuslistener)
    {
        if(text != null)
            text.addFocusListener(focuslistener);
    }

    public void removeFocusListener(FocusListener focuslistener)
    {
        if(text != null)
            text.removeFocusListener(focuslistener);
    }

    public void addKeyListener(KeyListener keylistener)
    {
        if(text != null)
            text.addKeyListener(keylistener);
    }

    public void removeKeyListener(KeyListener keylistener)
    {
        if(text != null)
            text.removeKeyListener(keylistener);
    }

    public int getShadowThickness()
    {
        return border;
    }

    public void setShadowThickness(int i)
    {
        border = i;
    }

    public int getHighlightThickness()
    {
        return text.getHighlightThickness();
    }

    public void setHighlightThickness(int i)
    {
        text.setHighlightThickness(i);
    }

    public Color getHighlightColor()
    {
        return text.getHighlightColor();
    }

    public void setHighlightColor(Color color)
    {
        text.setHighlightColor(color);
    }

    public synchronized void setText(String s)
    {
    }

    public synchronized String getText()
    {
        return "";
    }

    protected JCTextField text;
    protected SpinArrowButton decr_arrow;
    protected SpinArrowButton incr_arrow;
    protected Object value;
    protected boolean auto_arrow_disable;
    protected boolean arrow_key_spinning_allowed;
    private static boolean TRACE;
    protected int border;
    protected static final int NONE = 0;
    protected static final int INCREMENT = 1;
    protected static final int DECREMENT = -1;
    static final int BEGIN = 0;
    static final int END = 1;
    protected JCListenerList listener_list;
}
