// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCFieldCombo.java

package jclass.field;

import java.awt.*;
import java.awt.event.KeyEvent;
import jclass.bwt.*;
import jclass.cell.*;

// Referenced classes of package jclass.field:
//            JCField, JCValidateInterface, JCValidator, JCVersion, 
//            JCFieldListener

public abstract class JCFieldCombo extends JCComboBox
    implements ValidateInterface
{

    public JCFieldCombo()
    {
    }

    public JCFieldCombo(JCValidator jcvalidator, Object obj, Object obj1)
    {
        createField(jcvalidator, obj, obj1);
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
    }

    public void setEditable(boolean flag)
    {
        super.getTextField().setEditable(flag);
        field.setValue(field.getValue());
    }

    protected void createField(JCValidator jcvalidator, Object obj, Object obj1)
    {
        field = new JCField(this, jcvalidator, obj, obj1);
    }

    public void addNotify()
    {
        super.addNotify();
        if(field.text != null)
        {
            field.setValidBackground(((Component)field.text).getBackground());
            field.setValidForeground(((Component)field.text).getForeground());
            return;
        } else
        {
            field.setValidBackground(field.vc.getBackground());
            field.setValidForeground(field.vc.getForeground());
            return;
        }
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

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        field.initialize(initialevent, cellinfo, obj);
        super.initialize(initialevent, cellinfo, getText());
    }

    public Object getCellEditorValue()
    {
        return field.getCellEditorValue();
    }

    public boolean isModified()
    {
        return field.isModified();
    }

    public boolean stopCellEditing()
    {
        return field.stopCellEditing();
    }

    public KeyModifier[] getReservedKeys()
    {
        return Utilities.addKey(super.getReservedKeys(), new KeyModifier(27, 16));
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        field.addValidateListener(validatelistener);
    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        field.removeValidateListener(validatelistener);
    }

    public void draw(Graphics g, CellInfo cellinfo, Object obj, boolean flag)
    {
        String s = field.getValidator().format(obj);
        super.draw(g, cellinfo, s, flag);
    }

    public Dimension getPreferredSize(CellInfo cellinfo, Object obj)
    {
        String s = field.getValidator().format(obj);
        return super.getPreferredSize(cellinfo, s);
    }

    protected JCField field;
    public static final String version = JCVersion.getVersionString();
    public static final String about = JCVersion.getAboutString();

}
