// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateRendererEditor.java

package jclass.field.cell;

import jclass.bwt.JCComboBox;
import jclass.bwt.JCTextComponent;
import jclass.field.JCDatePopup;
import jclass.field.JCFieldCombo;

// Referenced classes of package jclass.field.cell:
//            FieldWrapper

public final class DateRendererEditor extends FieldWrapper
{

    public DateRendererEditor()
    {
        super(new JCDatePopup());
        JCDatePopup jcdatepopup = (JCDatePopup)getComponent();
        setField(jcdatepopup.getField());
        jcdatepopup.getTextField().setEditable(false);
        jcdatepopup.getTextField().setShowCursorPosition(false);
    }
}
