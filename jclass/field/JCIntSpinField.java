// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCIntSpinField.java

package jclass.field;


// Referenced classes of package jclass.field:
//            JCFieldSpin, JCField, JCIntValidator, JCNumberValidator, 
//            JCValidator

public class JCIntSpinField extends JCFieldSpin
{

    public JCIntSpinField()
    {
        createField(new JCIntValidator(), null, new Integer(0));
    }

    public JCIntSpinField(Integer integer)
    {
        createField(new JCIntValidator(), integer, integer);
    }

    public JCIntSpinField(int i)
    {
        createField(new JCIntValidator(), new Integer(i), new Integer(i));
    }

    public void addNotify()
    {
        super.addNotify();
        super.field.addNotify();
    }

    public void setValue(Integer integer)
    {
        super.setValue(integer);
    }

    public Integer getValue()
    {
        return (Integer)super.field.getValue();
    }

    public void setValue(int i)
    {
        super.setValue(new Integer(i));
    }

    public void setMax(int i)
    {
        ((JCIntValidator)super.field.getValidator()).setMax(i);
        checkArrowButtons();
    }

    public int getMax()
    {
        return ((Number)((JCIntValidator)super.field.getValidator()).getMax()).intValue();
    }

    public void setMin(int i)
    {
        ((JCIntValidator)super.field.getValidator()).setMin(i);
        checkArrowButtons();
    }

    public int getMin()
    {
        return ((Number)((JCIntValidator)super.field.getValidator()).getMin()).intValue();
    }

    public void setRange(int i, int j)
    {
        ((JCIntValidator)super.field.getValidator()).setRange(new Integer(i), new Integer(j));
        checkArrowButtons();
    }

    public int getIncrement()
    {
        return ((JCIntValidator)super.field.getValidator()).getIncrement().intValue();
    }

    public void setIncrement(int i)
    {
        ((JCIntValidator)super.field.getValidator()).setIncrement(i);
        checkArrowButtons();
    }

    public Integer[] getPickList()
    {
        return (Integer[])((JCIntValidator)super.field.getValidator()).getPickList();
    }

    public void setPickList(Integer ainteger[])
    {
        super.field.setPickList(ainteger);
        checkArrowButtons();
    }

    public String getDisplayPattern()
    {
        return ((JCNumberValidator)super.field.getValidator()).getDisplayPattern();
    }

    public void setDisplayPattern(String s)
    {
        ((JCNumberValidator)super.field.getValidator()).setDisplayPattern(s);
        super.field.setValue(super.field.internal_value);
    }

    public String getEditPattern()
    {
        return ((JCNumberValidator)super.field.getValidator()).getEditPattern();
    }

    public void setEditPattern(String s)
    {
        ((JCNumberValidator)super.field.getValidator()).setEditPattern(s);
        super.field.setValue(super.field.internal_value);
    }

    public Integer getDefaultValue()
    {
        return (Integer)super.field.getDefaultValue();
    }

    public void setDefaultValue(Integer integer)
    {
        super.field.setDefaultValue(integer);
    }
}
