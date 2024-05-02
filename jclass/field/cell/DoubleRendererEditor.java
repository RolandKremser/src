// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DoubleRendererEditor.java

package jclass.field.cell;

import jclass.bwt.JCTextField;
import jclass.field.JCDoubleValidator;

// Referenced classes of package jclass.field.cell:
//            RendererEditor

public class DoubleRendererEditor extends RendererEditor
{

    public DoubleRendererEditor()
    {
        super(new JCTextField(), new JCDoubleValidator());
    }
}
