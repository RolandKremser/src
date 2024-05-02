// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCFieldEvent.java

package jclass.field;

import jclass.cell.ValidateEvent;

// Referenced classes of package jclass.field:
//            JCField, JCValidInfo, JCValidateInterface, JCValidator

public class JCFieldEvent extends ValidateEvent
{

    public JCFieldEvent(Object obj, JCField jcfield, Object obj1, Object obj2, int i)
    {
        super(obj, obj1, obj2, true);
        field_source = jcfield;
        position = i;
    }

    public JCFieldEvent(Object obj, JCField jcfield, Object obj1, Object obj2, int i, boolean flag)
    {
        super(obj, obj1, obj2, true, flag);
        field_source = jcfield;
        position = i;
    }

    public JCField getFieldSource()
    {
        return field_source;
    }

    public void setValue(Object obj)
    {
        JCValidInfo jcvalidinfo = field_source.getValidator().validate(obj);
        if(!jcvalidinfo.valid)
        {
            throw new IllegalArgumentException("invalid value");
        } else
        {
            super.value = obj;
            position = jcvalidinfo.list_index;
            return;
        }
    }

    public int getPosition()
    {
        return position;
    }

    public int getState()
    {
        return field_source.getState();
    }

    public static final int NOT_FOUND = -1;
    protected JCField field_source;
    protected int position;
}
