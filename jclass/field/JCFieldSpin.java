// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCFieldSpin.java

package jclass.field;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import javax.swing.JComponent;
import jclass.bwt.*;
import jclass.cell.*;
import jclass.util.JCVector;

// Referenced classes of package jclass.field:
//            JCSpin, JCField, JCSpinInterface, JCValidateInterface, 
//            JCValidator, JCVersion, JCFieldListener

public abstract class JCFieldSpin extends JCSpin
    implements JCSpinInterface, JCTextManagerInterface, CellEditor, CellRenderer, ValidateInterface
{

    public JCFieldSpin()
    {
        spin_listeners = new JCVector(0);
        position = -1;
    }

    protected void createField(JCValidator jcvalidator, Object obj, Object obj1)
    {
        field = new JCField(this, jcvalidator, obj, obj1);
        setValue(field.getValue());
    }

    public JCField getField()
    {
        return field;
    }

    public JCValidateInterface getValidator()
    {
        return field.getValidator();
    }

    public void setValidator(JCValidateInterface jcvalidateinterface)
    {
        field.setValidator(jcvalidateinterface);
    }

    public void setText(String s)
    {
        if(field != null && field.getAllowTextChange())
            super.setText(s);
    }

    public void setValue(Object obj)
    {
        field.setValue(obj);
        checkArrowButtons();
    }

    public void setEditable(boolean flag)
    {
        super.text.setEditable(flag);
        field.setValue(field.getValue());
    }

    protected boolean validate(Object obj)
    {
        return true;
    }

    public synchronized JCTextInterface getTextComponent()
    {
        return super.text;
    }

    public void checkArrowButtons()
    {
        if(field == null || field.getValidator() == null)
        {
            super.incr_arrow.enable(false);
            super.decr_arrow.enable(false);
            return;
        }
        int i;
        if(field.getState() == 2 || field.getValue() == null)
            i = field.getValidator().calculateSpinability(field.getDefaultValue());
        else
            i = field.getValidator().calculateSpinability(field.getValue());
        if(super.incr_arrow != null)
            super.incr_arrow.enable((i & 1) != 0);
        if(super.decr_arrow != null)
            super.decr_arrow.enable((i & 2) != 0);
    }

    public void addNotify()
    {
        super.addNotify();
        if(field.text != null)
        {
            field.setValidBackground(((Component)field.text).getBackground());
            field.setValidForeground(((Component)field.text).getForeground());
        } else
        {
            field.setValidBackground(field.vc.getBackground());
            field.setValidForeground(field.vc.getForeground());
        }
        setValue(field.getValue());
    }

    public char getEchoChar()
    {
        return getTextField().getEchoChar();
    }

    public void setEchoChar(char c)
    {
        getTextField().setEchoChar(c);
    }

    public String getEchoCharString()
    {
        return getTextField().getEchoCharString();
    }

    public void setEchoCharString(String s)
    {
        getTextField().setEchoCharString(s);
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String s)
    {
    }

    public String getAbout()
    {
        return about;
    }

    public synchronized void setAbout(String s)
    {
    }

    public boolean getBeepOnInvalid()
    {
        return field.getBeepOnInvalid();
    }

    public void setBeepOnInvalid(boolean flag)
    {
        field.setBeepOnInvalid(flag);
    }

    public Color getInvalidBackground()
    {
        return field.getInvalidBackground();
    }

    public void setInvalidBackground(Color color)
    {
        field.setInvalidBackground(color);
    }

    public Color getInvalidForeground()
    {
        return field.getInvalidForeground();
    }

    public void setInvalidForeground(Color color)
    {
        field.setInvalidForeground(color);
    }

    public Color getValidBackground()
    {
        return field.getValidBackground();
    }

    public void setValidBackground(Color color)
    {
        field.setValidBackground(color);
    }

    public Color getValidForeground()
    {
        return field.getValidForeground();
    }

    public void setValidForeground(Color color)
    {
        field.setValidForeground(color);
    }

    public int getInvalidPolicy()
    {
        return field.getInvalidPolicy();
    }

    public void setInvalidPolicy(int i)
    {
        field.setInvalidPolicy(i);
    }

    public int getState()
    {
        return field.getState();
    }

    public void setState(int i)
    {
    }

    public void addFieldListener(JCFieldListener jcfieldlistener)
    {
        field.addFieldListener(jcfieldlistener);
    }

    public void removeFieldListener(JCFieldListener jcfieldlistener)
    {
        field.removeFieldListener(jcfieldlistener);
    }

    public String getPromptText()
    {
        return field.getPromptText();
    }

    public void setPromptText(String s)
    {
        field.setPromptText(s);
    }

    public Component getPromptTarget()
    {
        return field.getPromptTarget();
    }

    public void setPromptTarget(Component component)
    {
        field.setPromptTarget(component);
    }

    public String[] getDisplayList()
    {
        return field.getDisplayList();
    }

    public void setDisplayList(String as[])
    {
        field.setDisplayList(as);
        field.setValue(field.getValue());
    }

    public boolean getAllowNull()
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
            return false;
        else
            return jcvalidator.getAllowNull();
    }

    public void setAllowNull(boolean flag)
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setAllowNull(flag);
            return;
        }
    }

    public String getValidChars()
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
            return null;
        else
            return jcvalidator.getValidChars();
    }

    public void setValidChars(String s)
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setValidChars(s);
            return;
        }
    }

    public String getInvalidChars()
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
            return null;
        else
            return jcvalidator.getInvalidChars();
    }

    public void setInvalidChars(String s)
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setInvalidChars(s);
            return;
        }
    }

    public int getCasePolicy()
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
            return 0;
        else
            return jcvalidator.getCasePolicy();
    }

    public void setCasePolicy(int i)
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setCasePolicy(i);
            return;
        }
    }

    public boolean getMatchPickList()
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
            return true;
        else
            return jcvalidator.getMatchPickList();
    }

    public void setMatchPickList(boolean flag)
    {
        JCValidator jcvalidator = (JCValidator)getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setMatchPickList(flag);
            field.setValue(field.getValue());
            return;
        }
    }

    public int getSpinPolicy()
    {
        JCValidator jcvalidator = (JCValidator)field.getValidator();
        if(jcvalidator == null)
            return 0;
        else
            return jcvalidator.getSpinPolicy();
    }

    public void setSpinPolicy(int i)
    {
        JCValidator jcvalidator = (JCValidator)field.getValidator();
        if(jcvalidator == null)
        {
            return;
        } else
        {
            jcvalidator.setSpinPolicy(i);
            return;
        }
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        field.addValidateListener(validatelistener);
    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        field.removeValidateListener(validatelistener);
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        field.initialize(initialevent, cellinfo, obj);
        setHighlightThickness(0);
        super.text.setHighlightThickness(0);
        setInsets(new Insets(0, 0, 0, 0));
        super.text.setInsets(cellinfo.getMarginInsets());
        setShadowThickness(cellinfo.getBorderInsets().top);
        setAlignment(cellinfo.getHorizontalAlignment());
        setFont(cellinfo.getFont());
        setBackground(cellinfo.getBackground());
        setForeground(cellinfo.getForeground());
        setSelectedBackground(cellinfo.getSelectedBackground());
        setSelectedForeground(cellinfo.getSelectedForeground());
        super.text.containedInitialize(initialevent, cellinfo, obj);
    }

    public Component getComponent()
    {
        return this;
    }

    public boolean stopCellEditing()
    {
        return field.stopCellEditing();
    }

    public Object getCellEditorValue()
    {
        return field.getValue();
    }

    public boolean isModified()
    {
        return field.isModified();
    }

    public void cancelCellEditing()
    {
        super.text.cancelCellEditing();
    }

    public KeyModifier[] getReservedKeys()
    {
        KeyModifier akeymodifier[] = Utilities.addKey(super.text.getReservedKeys(), new KeyModifier(27, 16));
        akeymodifier = Utilities.addKey(akeymodifier, new KeyModifier(38, 0));
        akeymodifier = Utilities.addKey(akeymodifier, new KeyModifier(40, 0));
        return akeymodifier;
    }

    public void addCellEditorListener(CellEditorListener celleditorlistener)
    {
        super.text.addCellEditorListener(celleditorlistener);
    }

    public void removeCellEditorListener(CellEditorListener celleditorlistener)
    {
        super.text.removeCellEditorListener(celleditorlistener);
    }

    public void draw(Graphics g, CellInfo cellinfo, Object obj, boolean flag)
    {
        String s = field.getValidator().format(obj);
        super.text.draw(g, cellinfo, s, flag);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        String s = field.getValidator().format(obj);
        return super.text.getPreferredSize(cellinfo, s);
    }

    private static final boolean TRACE = false;
    public static final String version = JCVersion.getVersionString();
    public static final String about = JCVersion.getAboutString();
    protected JCVector spin_listeners;
    protected JCField field;
    protected int position;
    protected String display_list[];
    protected String value;

}
