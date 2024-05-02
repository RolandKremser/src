// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCStringValidator.java

package jclass.field;

import jclass.bwt.JCTextCursorEvent;
import jclass.bwt.JCTextEvent;

// Referenced classes of package jclass.field:
//            JCValidator, JCValidInfo

public class JCStringValidator extends JCValidator
{

    public Object copyValue(Object obj)
    {
        return obj;
    }

    public void setNumMaskMatch(int i)
    {
        num_mask_match = i;
    }

    public int getNumMaskMatch()
    {
        return num_mask_match;
    }

    public void setMask(String s)
    {
        mask = s;
        compileMask();
    }

    public String getMask()
    {
        return mask;
    }

    public void setPlaceHolderChars(String s)
    {
        place_holder_chars = s;
    }

    public String getPlaceHolderChars()
    {
        return place_holder_chars;
    }

    public void setMaskChars(String s)
    {
        if(s.length() != mask_chars.length())
        {
            throw new IllegalArgumentException("Missing mask chars");
        } else
        {
            mask_chars = s;
            compileMask();
            return;
        }
    }

    public String getMaskChars()
    {
        return mask_chars;
    }

    public String getParsedMask()
    {
        if(mask != null && mask_length != 0)
            return String.copyValueOf(compiled_mask_ch);
        else
            return "";
    }

    public JCStringValidator()
    {
        mask = "";
        mask_chars = "#@HULA*^\\";
        num_mask_match = -1;
        super.spin_policy = 2;
    }

    public JCStringValidator(String s, int i, String s1, String as[], boolean flag, String s2, String s3, 
            boolean flag1)
    {
        this();
        setMask(s);
        setPickList(as);
        setMatchPickList(flag);
        setNumMaskMatch(i);
        setPlaceHolderChars(s1);
        setValidChars(s2);
        setInvalidChars(s3);
        setAllowNull(flag1);
    }

    public boolean inRange(Object obj)
    {
        return true;
    }

    protected void compileMask()
    {
        int j = 0;
        int k = 0;
        boolean flag = false;
        int i;
        if(mask == null || (i = mask.length()) == 0)
        {
            mask_length = 0;
            return;
        }
        compiled_mask_sym = new int[i];
        compiled_mask_ch = new char[i];
        num_editable_pos = 0;
        j = 0;
        for(k = 0; j < i; k++)
        {
            int l = mask_chars.indexOf(mask.charAt(j));
            if(l < 0)
            {
                compiled_mask_sym[k] = 8;
                compiled_mask_ch[k] = mask.charAt(j);
            } else
            if(mask.charAt(j) == mask_chars.charAt(8))
            {
                if(++j < i)
                {
                    compiled_mask_sym[k] = 8;
                    compiled_mask_ch[k] = mask.charAt(j);
                } else
                {
                    k--;
                }
            } else
            {
                compiled_mask_sym[k] = l;
                compiled_mask_ch[k] = ' ';
                num_editable_pos++;
            }
            j++;
        }

        mask_length = k;
    }

    protected boolean isValidChar(StringBuffer stringbuffer, int i, int j)
    {
        char c = stringbuffer.charAt(i);
        if(j >= mask_length)
            return false;
        switch(compiled_mask_sym[j])
        {
        case 0: // '\0'
            return Character.isDigit(c) || "+-,.".indexOf(c) >= 0;

        case 2: // '\002'
            return Character.isDigit(c) || "AaBbCcDdEeFf".indexOf(c) >= 0;

        case 1: // '\001'
            return Character.isDigit(c);

        case 3: // '\003'
            if(Character.isLetter(c))
            {
                stringbuffer.setCharAt(i, Character.toUpperCase(c));
                return true;
            } else
            {
                return false;
            }

        case 4: // '\004'
            if(Character.isLetter(c))
            {
                stringbuffer.setCharAt(i, Character.toLowerCase(c));
                return true;
            } else
            {
                return false;
            }

        case 5: // '\005'
            return Character.isLetter(c);

        case 6: // '\006'
            return true;

        case 8: // '\b'
            return c == compiled_mask_ch[j];

        case 7: // '\007'
            return Character.isLetter(c) || Character.isDigit(c);
        }
        return false;
    }

    protected int getNextCursorPosition(int i, boolean flag)
    {
        boolean flag1 = false;
        if(mask == null || mask_length == 0)
            return i;
        if(i == mask_length)
            return i;
        if(flag)
        {
            for(int j = i; j < mask_length; j++)
                if(compiled_mask_sym[j] != 8)
                    return j;

        } else
        {
            for(int k = i; k >= 0; k--)
                if(compiled_mask_sym[k] != 8)
                    return k;

        }
        return -1;
    }

    private void addPlaceHolder(StringBuffer stringbuffer, int i)
    {
        int j = 0;
        if(place_holder_chars != null)
            j = place_holder_chars.length();
        if(j > 0 && i < j)
        {
            stringbuffer.append(place_holder_chars.charAt(i));
            return;
        } else
        {
            stringbuffer.append(" ");
            return;
        }
    }

    public void changeText(JCTextEvent jctextevent)
    {
        Object obj = null;
        StringBuffer stringbuffer1 = new StringBuffer(50);
        StringBuffer stringbuffer2 = new StringBuffer(50);
        boolean flag = false;
        int k1 = jctextevent.getStartPosition();
        int l1 = jctextevent.getEndPosition();
        super.changeText(jctextevent);
        if(jctextevent.isDeletion() && mask != null && mask_length != 0)
        {
            if(compiled_mask_sym[k1] == 8 && l1 - k1 == 1)
            {
                int i2 = getNextCursorPosition(k1, false);
                if(i2 == -1)
                {
                    jctextevent.setAllowChange(false);
                    return;
                }
                addPlaceHolder(stringbuffer2, i2);
                for(int i = i2 + 1; i <= k1; i++)
                    if(compiled_mask_sym[i] == 8)
                        stringbuffer2.append(compiled_mask_ch[i]);
                    else
                        addPlaceHolder(stringbuffer2, i);

                jctextevent.setStartPosition(i2);
                jctextevent.setText(stringbuffer2.toString());
                return;
            }
            if(l1 - k1 > 1)
            {
                StringBuffer stringbuffer3 = new StringBuffer(jctextevent.getText());
                int j2 = stringbuffer3.length();
                int k2 = 0;
                for(int j = k1; j < l1 && compiled_mask_sym[j] == 8; j++)
                    k1++;

                for(int k = k1; k < l1; k++)
                    if(compiled_mask_sym[k] == 8)
                        stringbuffer2.append(compiled_mask_ch[k]);
                    else
                    if(!stringbuffer3.equals("") && k2 < j2 && isValidChar(stringbuffer3, k2, k))
                    {
                        stringbuffer2.append(stringbuffer3.charAt(k2));
                        k2++;
                    } else
                    {
                        addPlaceHolder(stringbuffer2, k);
                    }

                jctextevent.setStartPosition(k1);
                jctextevent.setText(stringbuffer2.toString());
                return;
            } else
            {
                addPlaceHolder(stringbuffer2, k1);
                jctextevent.setText(stringbuffer2.toString());
                return;
            }
        }
        String s = jctextevent.getText();
        if(s != null && s.length() != 0)
        {
            StringBuffer stringbuffer = checkValidInvalid(s);
            if(stringbuffer != null && stringbuffer.length() != 0)
            {
                int j1 = k1;
                if(mask != null && mask_length != 0)
                {
                    for(int l = 0; l < stringbuffer.length(); l++)
                    {
                        boolean flag1 = isValidChar(stringbuffer, l, j1);
                        if(flag1)
                        {
                            stringbuffer1.append(stringbuffer.charAt(l));
                            j1++;
                            if(l1 - k1 != 1)
                            {
                                for(int i1 = j1; i1 < mask_length && compiled_mask_sym[i1] == 8; i1++)
                                    stringbuffer1.append(compiled_mask_ch[i1]);

                            }
                            j1 = getNextCursorPosition(j1, true);
                        }
                    }

                    if(l1 - k1 != 1)
                        jctextevent.setEndPosition(jctextevent.getStartPosition() + stringbuffer1.length());
                } else
                {
                    stringbuffer1.append(stringbuffer);
                }
            }
            jctextevent.setText(stringbuffer1.toString());
            if(stringbuffer1.length() == 0)
                jctextevent.setAllowChange(false);
        }
    }

    public void setCursor(JCTextCursorEvent jctextcursorevent)
    {
        int i = jctextcursorevent.getNewPosition();
        int j = jctextcursorevent.getCurrentPosition();
        boolean flag = true;
        if(j > i)
            flag = false;
        int k = getNextCursorPosition(i, flag);
        if(k == -1)
        {
            k = getNextCursorPosition(i, !flag);
            if(k == -1)
            {
                jctextcursorevent.setNewPosition(jctextcursorevent.getCurrentPosition());
                return;
            }
        }
        jctextcursorevent.setNewPosition(k);
    }

    public JCValidInfo validateText(String s)
    {
        String s1;
        if((s == null || s.length() == 0) && getAllowNull() || mask == null || mask_length == 0)
        {
            s1 = s;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer(50);
            int j;
            for(j = s.length(); j > 0; j--)
                if(place_holder_chars != null && j <= place_holder_chars.length() ? s.charAt(j - 1) != place_holder_chars.charAt(j - 1) && compiled_mask_sym[j - 1] != 8 : s.charAt(j - 1) != ' ' && compiled_mask_sym[j - 1] != 8)
                    break;

            for(int i = 0; i < j; i++)
                if(compiled_mask_sym[i] != 8 || compiled_mask_ch[i] != s.charAt(i))
                    stringbuffer.append(s.charAt(i));

            s1 = stringbuffer.toString();
        }
        return internalValidate(s1);
    }

    public JCValidInfo validate(Object obj)
    {
        return internalValidate(obj);
    }

    protected JCValidInfo internalValidate(Object obj)
    {
        boolean flag = false;
        int j = 0;
        JCValidInfo jcvalidinfo = super.validate(obj);
        String s = (String)jcvalidinfo.value;
        int k = -1;
        if(s == null || s.length() == 0 && getAllowNull())
        {
            jcvalidinfo.valid = true;
            jcvalidinfo.value = null;
            return jcvalidinfo;
        }
        if((s == null || s.length() == 0) && !getAllowNull())
            jcvalidinfo.valid = false;
        if(!jcvalidinfo.valid || mask == null || mask_length == 0)
            return jcvalidinfo;
        if(s != null)
        {
            StringBuffer stringbuffer = new StringBuffer(s);
            for(int i = 0; i < stringbuffer.length(); i++)
            {
                for(int l = j; l < mask_length && compiled_mask_sym[l] == 8; l++)
                    j++;

                if(!isValidChar(stringbuffer, i, j))
                {
                    jcvalidinfo.valid = false;
                    break;
                }
                j++;
                k = i;
            }

            if(num_mask_match >= 0)
            {
                if(num_mask_match > k + 1)
                    jcvalidinfo.valid = false;
                else
                    jcvalidinfo.valid = true;
            } else
            if(stringbuffer.length() != num_editable_pos)
                jcvalidinfo.valid = false;
        }
        return jcvalidinfo;
    }

    public String format(Object obj)
    {
        StringBuffer stringbuffer = new StringBuffer(50);
        int l = 0;
        if(obj != null && !isSupported(obj))
            obj = convertToSupported(obj);
        String s;
        if(obj == null)
            s = "";
        else
            s = (String)obj;
        if(mask != null && mask_length != 0)
        {
            if(s.length() == 0 && place_holder_chars != null && place_holder_chars.length() != 0)
            {
                stringbuffer.append(place_holder_chars);
                for(int i = place_holder_chars.length(); i < mask_length; i++)
                    if(compiled_mask_sym[i] == 8)
                        stringbuffer.append(compiled_mask_ch[i]);
                    else
                        addPlaceHolder(stringbuffer, i);

                return stringbuffer.toString();
            }
            for(int j = 0; j < s.length() && j < num_editable_pos; j++)
            {
                if(s.charAt(j) == compiled_mask_ch[l] && compiled_mask_sym[l] != 8)
                {
                    stringbuffer.append(compiled_mask_ch[l]);
                } else
                {
                    for(int i1 = l; compiled_mask_sym[i1] == 8; i1++)
                    {
                        stringbuffer.append(compiled_mask_ch[i1]);
                        l++;
                    }

                    stringbuffer.append(s.charAt(j));
                }
                l++;
            }

            for(int k = l; k < mask_length; k++)
                if(compiled_mask_sym[k] == 8)
                    stringbuffer.append(compiled_mask_ch[k]);
                else
                    addPlaceHolder(stringbuffer, k);

            return stringbuffer.toString();
        } else
        {
            return s;
        }
    }

    public int getFirstValidCursorPosition()
    {
        int i;
        if(mask != null && mask_length != 0)
        {
            if((i = getNextCursorPosition(0, true)) >= 0)
                return i;
            else
                return mask_length;
        } else
        {
            return 0;
        }
    }

    public String formatForEdit(Object obj)
    {
        return null;
    }

    public boolean hasEditFormat()
    {
        return false;
    }

    protected Object addIncrement(Object obj)
    {
        return null;
    }

    protected Object subtractIncrement(Object obj)
    {
        return null;
    }

    public boolean isSupported(Object obj)
    {
        return obj instanceof String;
    }

    public Object convertToSupported(Object obj)
    {
        if(obj instanceof StringBuffer)
            return ((StringBuffer)obj).toString();
        else
            return null;
    }

    public Object convertFromSupported(Object obj, String s)
    {
        if(s.equals("java.lang.StringBuffer"))
            return new StringBuffer((String)obj);
        else
            return null;
    }

    static final int NUMBER = 0;
    static final int DIGIT = 1;
    static final int HEX = 2;
    static final int UPPER = 3;
    static final int LOWER = 4;
    static final int NOCONVERT = 5;
    static final int STAR = 6;
    static final int ALPHANUMERIC = 7;
    static final int LITERAL = 8;
    static final String HEXDIGITS = "AaBbCcDdEeFf";
    static final String NUMBERDIGITS = "+-,.";
    protected int compiled_mask_sym[];
    protected char compiled_mask_ch[];
    protected int mask_length;
    protected int num_editable_pos;
    protected String mask;
    protected String place_holder_chars;
    protected String mask_chars;
    protected int num_mask_match;
}
