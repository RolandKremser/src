// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCTimeValidator.java

package jclass.field;

import java.util.Locale;
import java.util.ResourceBundle;

// Referenced classes of package jclass.field:
//            JCCalendarValidator, JCValidator

public class JCTimeValidator extends JCCalendarValidator
{

    public JCTimeValidator()
    {
    }

    public JCTimeValidator(String s, String s1, String as[], boolean flag, int i, boolean flag1)
    {
        super(s, s1, as, flag, i, flag1);
    }

    public String[] getDefaultEditFormats(Locale locale)
    {
        String as[] = new String[4];
        String s = super.li.getString("FullTime");
        String s1 = super.li.getString("LongTime");
        String s2 = super.li.getString("MediumTime");
        String s3 = super.li.getString("ShortTime");
        as[0] = s;
        as[1] = s1;
        as[2] = s2;
        as[3] = s3;
        return as;
    }

    public String getDefaultFormat(Locale locale)
    {
        ResourceBundle resourcebundle = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale);
        String s = null;
        switch(super.default_detail)
        {
        case 0: // '\0'
            s = resourcebundle.getString("FullTime");
            break;

        case 1: // '\001'
            s = resourcebundle.getString("LongTime");
            break;

        case 2: // '\002'
            s = resourcebundle.getString("MediumTime");
            break;

        case 3: // '\003'
            s = resourcebundle.getString("ShortTime");
            break;
        }
        return s;
    }
}
