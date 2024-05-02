// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCNumberValidator.java

package jclass.field;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import jclass.bwt.JCTextCursorEvent;

// Referenced classes of package jclass.field:
//            JCValidator

public abstract class JCNumberValidator extends JCValidator
{

    public JCNumberValidator()
    {
        spin_policy = 0;
        setPatterns();
        displayFormat = createDisplayFormat(getLocale());
        if(display_pattern != null)
            displayFormat.applyPattern(display_pattern);
        editFormat = createEditFormat(getLocale());
        if(edit_pattern != null)
            editFormat.applyPattern(edit_pattern);
    }

    public Object copyValue(Object obj)
    {
        return obj;
    }

    protected abstract void setPatterns();

    public void setLocale(Locale locale)
    {
        super.setLocale(locale);
        symbols = new DecimalFormatSymbols(locale);
    }

    protected abstract DecimalFormat createDisplayFormat(Locale locale);

    protected abstract DecimalFormat createEditFormat(Locale locale);

    public void setCursor(JCTextCursorEvent jctextcursorevent)
    {
        jctextcursorevent.getNewPosition();
    }

    public String getDisplayPattern()
    {
        return display_pattern;
    }

    public void setDisplayPattern(String s)
    {
        display_pattern = s;
        displayFormat.applyPattern(s);
    }

    public String getEditPattern()
    {
        return edit_pattern;
    }

    public void setEditPattern(String s)
    {
        edit_pattern = s;
        editFormat.applyPattern(s);
    }

    public DecimalFormat getDisplayFormat()
    {
        return displayFormat;
    }

    public DecimalFormat getEditFormat()
    {
        return editFormat;
    }

    public void setDisplayFormat(DecimalFormat decimalformat)
    {
        if(decimalformat != null)
        {
            displayFormat = decimalformat;
            return;
        } else
        {
            throw new IllegalArgumentException("null DecimalFormat object invalid");
        }
    }

    public void setEditFormat(DecimalFormat decimalformat)
    {
        if(decimalformat != null)
        {
            editFormat = decimalformat;
            return;
        } else
        {
            throw new IllegalArgumentException("null DecimalFormat object invalid");
        }
    }

    public int getFirstValidCursorPosition()
    {
        return 0;
    }

    protected DecimalFormat displayFormat;
    protected DecimalFormat editFormat;
    protected DecimalFormatSymbols symbols;
    protected String display_pattern;
    protected String edit_pattern;
    protected int spin_policy;
}
