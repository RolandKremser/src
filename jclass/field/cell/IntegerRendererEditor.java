// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerRendererEditor.java

package jclass.field.cell;

import jclass.bwt.JCTextField;
import jclass.field.JCIntValidator;

// Referenced classes of package jclass.field.cell:
//            RendererEditor

public class IntegerRendererEditor extends RendererEditor
{

    public IntegerRendererEditor()
    {
        super(new JCTextField(), new JCIntValidator());
    }
}
