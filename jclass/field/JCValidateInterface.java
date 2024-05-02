// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCValidateInterface.java

package jclass.field;

import jclass.bwt.*;

// Referenced classes of package jclass.field:
//            JCValidInfo

public interface JCValidateInterface
{

    public abstract String[] getDisplayList();

    public abstract Object[] getPickList();

    public abstract void setPickList(Object aobj[]);

    public abstract void setDisplayList(String as[]);

    public abstract String[] getStringList();

    public abstract void changeText(JCTextEvent jctextevent);

    public abstract Object copyValue(Object obj);

    public abstract boolean compareValues(Object obj, Object obj1);

    public abstract void setCursor(JCTextCursorEvent jctextcursorevent);

    public abstract JCValidInfo validateText(String s);

    public abstract JCValidInfo validate(Object obj);

    public abstract String format(Object obj);

    public abstract String formatForEdit(Object obj);

    public abstract boolean hasEditFormat();

    public abstract void inferSubField(JCTextInterface jctextinterface);

    public abstract Object spinUp(Object obj);

    public abstract Object spinDown(Object obj);

    public abstract int calculateSpinability(Object obj);

    public abstract int getFirstValidCursorPosition();

    public abstract int getPickListIndex(Object obj);

    public abstract boolean isSupported(Object obj);

    public abstract Object convertToSupported(Object obj);

    public abstract Object convertFromSupported(Object obj, String s);
}
