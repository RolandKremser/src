// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCValidator.java

package jclass.field;

import java.io.Serializable;
import java.util.*;
import jclass.bwt.*;

// Referenced classes of package jclass.field:
//            JCValidInfo, JCValidateInterface

public abstract class JCValidator
    implements JCValidateInterface, Serializable
{

    public JCValidator()
    {
        allow_null = false;
        case_policy = 0;
        match_picklist = true;
        spin_policy = 0;
        setLocale(Locale.getDefault());
    }

    public void changeText(JCTextEvent jctextevent)
    {
        String s = jctextevent.getText();
        if(s == null || s.length() == 0)
            return;
        StringBuffer stringbuffer = new StringBuffer(s.length());
        for(int i = 0; i < s.length(); i++)
            stringbuffer.append(convertCase(s.charAt(i)));

        jctextevent.setText(stringbuffer.toString());
    }

    public abstract void setCursor(JCTextCursorEvent jctextcursorevent);

    public abstract JCValidInfo validateText(String s);

    public JCValidInfo validate(Object obj)
    {
        JCValidInfo jcvalidinfo = new JCValidInfo();
        jcvalidinfo.value = obj;
        jcvalidinfo.valid = true;
        jcvalidinfo.list_index = getPickListIndex(obj);
        if(picklist != null && picklist.length > 0 && match_picklist)
            if(jcvalidinfo.list_index == -1)
                jcvalidinfo.valid = false;
            else
                jcvalidinfo.valid = true;
        if(!allow_null && obj == null)
            jcvalidinfo.valid = false;
        return jcvalidinfo;
    }

    public abstract Object copyValue(Object obj);

    public boolean compareValues(Object obj, Object obj1)
    {
        if(obj == obj1)
            return true;
        if(obj == null)
            return false;
        else
            return obj.equals(obj1);
    }

    protected JCValidInfo parseFromDisplayList(String s)
    {
        JCValidInfo jcvalidinfo = new JCValidInfo();
        jcvalidinfo.valid = false;
        jcvalidinfo.value = null;
        if(display_list != null && display_list.length > 0)
        {
            int i;
            for(i = 0; i < display_list.length; i++)
                if(s.equalsIgnoreCase(display_list[i]))
                    break;

            if(i == display_list.length)
                i = -1;
            if(i != -1 && i < picklist.length)
            {
                jcvalidinfo.value = picklist[i];
                jcvalidinfo.valid = true;
            } else
            {
                jcvalidinfo.value = new Integer(i);
            }
        }
        return jcvalidinfo;
    }

    protected JCValidInfo formatFromDisplayList(Object obj)
    {
        JCValidInfo jcvalidinfo = new JCValidInfo();
        jcvalidinfo.valid = false;
        jcvalidinfo.value = null;
        if(display_list != null && display_list.length > 0)
        {
            int i = getPickListIndex(obj);
            if(i != -1 && i < display_list.length)
            {
                jcvalidinfo.value = display_list[i];
                jcvalidinfo.valid = true;
            }
        }
        return jcvalidinfo;
    }

    public abstract String format(Object obj);

    public abstract String formatForEdit(Object obj);

    public abstract boolean hasEditFormat();

    public abstract int getFirstValidCursorPosition();

    public boolean getAllowNull()
    {
        return allow_null;
    }

    public void setAllowNull(boolean flag)
    {
        allow_null = flag;
    }

    public int getSpinPolicy()
    {
        return spin_policy;
    }

    public void setSpinPolicy(int i)
    {
        if(i != 1 && i != 0 && i != 2)
        {
            throw new IllegalArgumentException("invalid value for spinPolicy");
        } else
        {
            spin_policy = i;
            return;
        }
    }

    public void inferSubField(JCTextInterface jctextinterface)
    {
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale1)
    {
        if(locale == locale1)
            return;
        try
        {
            li = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale1);
        }
        catch(MissingResourceException missingresourceexception)
        {
            locale1 = new Locale(locale1.getLanguage(), locale1.getCountry());
            try
            {
                li = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale1);
            }
            catch(MissingResourceException _ex)
            {
                locale1 = new Locale(locale1.getLanguage(), "");
                try
                {
                    li = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale1);
                }
                catch(MissingResourceException _ex2)
                {
                    locale1 = null;
                    try
                    {
                        li = ResourceBundle.getBundle("jclass.field.resources.LocaleInfo", locale1);
                    }
                    catch(MissingResourceException _ex3)
                    {
                        throw missingresourceexception;
                    }
                }
            }
        }
        locale = locale1;
    }

    public int getCasePolicy()
    {
        return case_policy;
    }

    public void setCasePolicy(int i)
    {
        if(i != 0 && i != 1 && i != 2)
        {
            throw new IllegalArgumentException("invalid value for casePolicy");
        } else
        {
            case_policy = i;
            return;
        }
    }

    public char convertCase(char c)
    {
        switch(case_policy)
        {
        case 1: // '\001'
            c = Character.toUpperCase(c);
            break;

        case 2: // '\002'
            c = Character.toLowerCase(c);
            break;
        }
        return c;
    }

    public Object[] getPickList()
    {
        return picklist;
    }

    public void setPickList(Object aobj[])
    {
        picklist = aobj;
    }

    public int getPickListIndex(Object obj)
    {
        if(picklist == null || obj == null)
            return -1;
        for(int i = 0; i < picklist.length; i++)
            if(obj.equals(picklist[i]))
                return i;

        return -1;
    }

    public boolean getMatchPickList()
    {
        return match_picklist;
    }

    public void setMatchPickList(boolean flag)
    {
        match_picklist = flag;
    }

    public String[] getDisplayList()
    {
        return display_list;
    }

    public String[] getStringList()
    {
        if(picklist == null || picklist.length == 0)
            return null;
        if(display_list != null && display_list.length == picklist.length)
            return display_list;
        String as[] = new String[picklist.length];
        int i = 0;
        if(display_list != null)
            for(; i < picklist.length && i < display_list.length; i++)
                as[i] = display_list[i];

        for(; i < picklist.length; i++)
            as[i] = format(picklist[i]);

        return as;
    }

    public void setDisplayList(String as[])
    {
        display_list = as;
    }

    protected Object getNextValue(int i)
    {
        if(i + 1 >= 0 && i + 1 >= picklist.length)
            return null;
        else
            return picklist[i + 1];
    }

    protected Object getPreviousValue(int i)
    {
        if(i - 1 < 0)
            return null;
        else
            return picklist[i - 1];
    }

    public String getValidChars()
    {
        return valid_chars;
    }

    public void setValidChars(String s)
    {
        valid_chars = s;
    }

    public String getInvalidChars()
    {
        return invalid_chars;
    }

    public void setInvalidChars(String s)
    {
        invalid_chars = s;
    }

    public boolean isValidChar(char c)
    {
        if(invalid_chars != null)
        {
            for(int i = 0; i < invalid_chars.length(); i++)
                if(c == invalid_chars.charAt(i))
                    return false;

        }
        if(valid_chars == null || valid_chars.length() == 0)
            return true;
        for(int j = 0; j < valid_chars.length(); j++)
            if(c == valid_chars.charAt(j))
                return true;

        return false;
    }

    public Object getMax()
    {
        return max;
    }

    public void setMax(Object obj)
    {
        max = obj;
    }

    public Object getMin()
    {
        return min;
    }

    public void setMin(Object obj)
    {
        min = obj;
    }

    protected void setRange(Object obj, Object obj1)
    {
        min = obj;
        max = obj1;
    }

    abstract boolean inRange(Object obj);

    Object[] getList()
    {
        return picklist;
    }

    void setList(Object aobj[])
    {
        picklist = aobj;
    }

    String getItem(int i)
    {
        return picklist[i].toString();
    }

    protected abstract Object addIncrement(Object obj);

    protected abstract Object subtractIncrement(Object obj);

    public Object spinUp(Object obj)
    {
        if(picklist != null && picklist.length > 0)
        {
            int i = getPickListIndex(obj);
            if(i + 1 < picklist.length)
                return picklist[i + 1];
            if(spin_policy == 2)
                return picklist[0];
            else
                return null;
        }
        if(increment != null)
        {
            Object obj1 = addIncrement(obj);
            if(obj1 != null)
                return obj1;
            if(spin_policy == 2)
                return min;
        }
        return null;
    }

    public Object spinDown(Object obj)
    {
        if(picklist != null && picklist.length > 0)
        {
            int i = getPickListIndex(obj);
            if(i > 0)
                return picklist[i - 1];
            if(i == -1)
                return picklist[picklist.length - 1];
            if(spin_policy == 2)
                return picklist[picklist.length - 1];
            else
                return null;
        }
        if(increment != null)
        {
            Object obj1 = subtractIncrement(obj);
            if(obj1 != null)
                return obj1;
            if(spin_policy == 2)
                return max;
        }
        return null;
    }

    public int calculateSpinability(Object obj)
    {
        int i = 0;
        if(picklist != null && picklist.length > 0)
        {
            int j = getPickListIndex(obj);
            if(spin_policy == 2)
            {
                i |= 2;
                i |= 1;
            } else
            {
                if(j > 0 || j == -1)
                    i |= 2;
                if(j < picklist.length - 1)
                    i |= 1;
            }
            return i;
        }
        if(increment != null)
        {
            Object obj1 = subtractIncrement(obj);
            if(spin_policy == 2)
            {
                i |= 2;
                i |= 1;
            } else
            {
                if(obj1 != null && !obj1.equals(obj))
                    i |= 2;
                obj1 = addIncrement(obj);
                if(obj1 != null && !obj1.equals(obj))
                    i |= 1;
            }
            return i;
        } else
        {
            return 0;
        }
    }

    protected StringBuffer checkValidInvalid(String s)
    {
        StringBuffer stringbuffer = new StringBuffer(s.length());
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(isValidChar(c))
                stringbuffer.append(c);
        }

        return stringbuffer;
    }

    public abstract boolean isSupported(Object obj);

    public abstract Object convertToSupported(Object obj);

    public abstract Object convertFromSupported(Object obj, String s);

    private static final boolean TRACE = false;
    public static final int AS_IS = 0;
    public static final int UPPERCASE = 1;
    public static final int LOWERCASE = 2;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int SPIN_FIELD = 0;
    public static final int SPIN_SUBFIELD = 1;
    public static final int SPIN_WRAP = 2;
    public static final int NOT_FOUND = -1;
    protected Object max;
    protected Object min;
    protected Locale locale;
    protected Object increment;
    protected boolean allow_null;
    protected int case_policy;
    protected String invalid_chars;
    protected boolean match_picklist;
    protected String valid_chars;
    protected Object picklist[];
    protected String display_list[];
    protected int spin_policy;
    protected ResourceBundle li;
}
