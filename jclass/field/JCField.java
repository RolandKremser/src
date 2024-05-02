// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCField.java

package jclass.field;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;
import jclass.bwt.*;
import jclass.cell.*;
import jclass.util.JCListenerList;

// Referenced classes of package jclass.field:
//            JCCalendar, JCCalendarPopup, JCDateValidator, JCFieldEvent, 
//            JCFieldListener, JCSpinInterface, JCValidInfo, JCValidateInterface, 
//            JCValidator, JCSpinEvent, JCSpinListener

public class JCField
    implements Serializable
{
    class Listeners
        implements JCTextListener, JCTextCursorListener, JCItemListener, JCSpinListener, FocusListener, KeyListener, Serializable
    {

        public void textValueChangeBegin(JCTextEvent jctextevent)
        {
            if(validator == null)
                return;
            if(internal_text_change)
                return;
            if(state != 1)
                setStateUnderEdit();
            if(validator != null)
                validator.changeText(jctextevent);
            if(!jctextevent.getAllowChange() && beep && okay_to_beep)
                vc.getToolkit().beep();
        }

        public void textValueChangeEnd(JCTextEvent jctextevent)
        {
            if(internal_text_change)
                return;
            else
                return;
        }

        public void textCursorMoveBegin(JCTextCursorEvent jctextcursorevent)
        {
            if(validator != null)
                validator.setCursor(jctextcursorevent);
        }

        public void textCursorMoveEnd(JCTextCursorEvent jctextcursorevent)
        {
        }

        private void print_to_prompt(String s)
        {
            if(prompt_target instanceof Label)
            {
                ((Label)prompt_target).setText(s);
                return;
            }
            if(prompt_target instanceof JCLabel)
            {
                ((JCLabel)prompt_target).setText(s);
                return;
            }
            if(prompt_target instanceof TextComponent)
            {
                ((TextComponent)prompt_target).setText(s);
                return;
            }
            if(prompt_target instanceof JCTextComponent)
                ((JCTextComponent)prompt_target).setText(s);
        }

        public void focusGained(FocusEvent focusevent)
        {
            if(is_cell_editor)
                return;
            has_focus = true;
            if(validator == null)
                return;
            if(prompt_target != null && prompt_text != null)
                print_to_prompt(prompt_text);
            if(text == null)
                return;
            if(!validator.hasEditFormat())
                return;
            if(!text.getEditable())
                return;
            if(state == 2)
                return;
            internal_text_change = true;
            setText(validator.formatForEdit(internal_value));
            internal_text_change = false;
            if(select_on_enter)
            {
                text.setSelectionStart(0);
                text.setSelectionEnd(text.getLastPosition());
            }
        }

        public void focusLost(FocusEvent focusevent)
        {
            if(is_cell_editor)
                return;
            has_focus = false;
            if(validator == null)
                return;
            if(prompt_target != null && prompt_text != null)
                print_to_prompt("");
            if(state == 1)
            {
                commitEdit(focusevent);
                return;
            }
            if(state != 2 && validator != null && validator.hasEditFormat())
                setValue(internal_value);
        }

        public void spin(JCSpinEvent jcspinevent)
        {
            Object obj = null;
            if(validator == null)
                return;
            commitEdit();
            Object obj1;
            if(internal_value == null || !validator.validate(internal_value).valid)
                obj1 = default_value;
            else
                obj1 = internal_value;
            if(increment_policy == 1)
                validator.inferSubField(text);
            if(jcspinevent.getDirection() == JCSpinEvent.SPIN_UP)
                obj = validator.spinUp(obj1);
            else
            if(jcspinevent.getDirection() == JCSpinEvent.SPIN_DOWN)
                obj = validator.spinDown(obj1);
            else
                return;
            if(obj != null)
            {
                setAndValidateValue(obj);
                setStateValid();
            }
        }

        public void keyTyped(KeyEvent keyevent)
        {
        }

        public void keyPressed(KeyEvent keyevent)
        {
            Object obj = null;
            boolean flag = false;
            boolean flag1 = false;
            if(validator == null)
                return;
            if(text != null && text.getEditable())
                flag = true;
            int i = keyevent.getKeyCode();
            switch(i)
            {
            case 37: // '%'
                if(allow_spin_keys && !flag)
                {
                    if(increment_policy == 1)
                        validator.inferSubField(text);
                    commitEdit(keyevent);
                    obj = validator.spinDown(internal_value);
                    keyevent.consume();
                }
                break;

            case 39: // '\''
                if(allow_spin_keys && !flag)
                {
                    if(increment_policy == 1)
                        validator.inferSubField(text);
                    commitEdit(keyevent);
                    obj = validator.spinUp(internal_value);
                    keyevent.consume();
                }
                break;

            case 27: // '\033'
                flag1 = true;
                if(state == 1 || state == 2)
                {
                    state = 0;
                    obj = internal_value;
                    keyevent.consume();
                }
                break;

            case 10: // '\n'
                commitEdit(keyevent);
                keyevent.consume();
                return;
            }
            if(obj != null)
                setValue(keyevent, obj);
            if(flag1 && select_on_enter)
            {
                text.setSelectionStart(0);
                text.setSelectionEnd(text.getLastPosition());
            }
        }

        public void keyReleased(KeyEvent keyevent)
        {
        }

        public void itemStateChanged(JCItemEvent jcitemevent)
        {
            if(jcitemevent.getStateChange() != 1)
                return;
            if(validator == null)
                return;
            Object aobj[] = validator.getPickList();
            int i = ((JCChoiceInterface)jcitemevent.getSource()).getSelectedIndex();
            if(aobj == null || i >= aobj.length || aobj.length <= 0)
                throw new ArrayIndexOutOfBoundsException("pickList does not match itemList");
            setValue(jcitemevent, aobj[i]);
            if(isCellEditor() && (text instanceof JCTextComponent))
                ((JCTextComponent)text).getCellEditorSupport().fireStopEditing(new CellEditorEvent(jcitemevent));
        }

        public void listItemSelectBegin(JCListEvent jclistevent)
        {
        }

        public void listItemSelectEnd(JCListEvent jclistevent)
        {
            if(!jclistevent.getAllowSelection())
                return;
            if(validator == null)
                return;
            int i = jclistevent.getRow();
            if(i < validator.getPickList().length)
            {
                throw new ArrayIndexOutOfBoundsException("pickList does not match itemList");
            } else
            {
                setValue(jclistevent, validator.getPickList()[i]);
                return;
            }
        }

        private final boolean TRACE = false;

        Listeners()
        {
        }
    }


    private void loadList()
    {
        if(validator == null)
            return;
        if(validator.getStringList() == null)
            return;
        if(choice != null)
            choice.setItems(validator.getStringList());
    }

    public void validate(AWTEvent awtevent)
    {
        if(validator == null)
            return;
        JCValidInfo jcvalidinfo = validator.validate(internal_value);
        if(jcvalidinfo.valid)
        {
            setValue(awtevent, jcvalidinfo.value);
            list_index = jcvalidinfo.list_index;
            setStateValid();
        } else
        {
            state = 2;
            setInvalidPolicy(invalid_policy);
        }
        if(spin != null)
            spin.checkArrowButtons();
    }

    private void initialize()
    {
        if(vc instanceof JCTextInterface)
            text = (JCTextInterface)vc;
        if(vc instanceof JCChoiceInterface)
            choice = (JCChoiceInterface)vc;
        if(vc instanceof JCSpinInterface)
            spin = (JCSpinInterface)vc;
        if(vc instanceof JCTextManagerInterface)
            text = ((JCTextManagerInterface)vc).getTextComponent();
        if(text == null && choice == null && spin == null)
            throw new ClassCastException("Visual Component does not support any Interface usable by JCField");
        invalid_background = null;
        invalid_foreground = null;
        select_on_enter = false;
        if(text != null)
        {
            ((Component)text).addFocusListener(listener);
            ((Component)text).addKeyListener(listener);
        } else
        {
            vc.addFocusListener(listener);
            vc.addKeyListener(listener);
        }
        if(text != null)
        {
            text.addTextListener(listener);
            text.addTextCursorListener(listener);
        }
        if(choice != null)
        {
            choice.addItemListener(listener);
            loadList();
        }
        if(spin != null)
            spin.addSpinListener(listener);
        validate(null);
    }

    public JCField(Component component, JCValidateInterface jcvalidateinterface, Object obj, Object obj1)
    {
        increment_policy = 0;
        allow_spin_keys = false;
        beep = true;
        internal_text_change = false;
        has_focus = false;
        validate_support = new ValidateSupport();
        listener = new Listeners();
        okay_to_beep = false;
        allow_text_change = false;
        is_cell_editor = false;
        user_set_value = false;
        select_all = false;
        is_setting_value = false;
        vc = component;
        validator = jcvalidateinterface;
        internal_value = obj;
        default_value = obj1;
        event_source = component;
        initialize();
    }

    public void addNotify()
    {
        okay_to_beep = true;
    }

    public Color getInvalidBackground()
    {
        if(invalid_background == null)
        {
            if(text != null)
                return ((Component)text).getBackground();
            else
                return vc.getBackground();
        } else
        {
            return invalid_background;
        }
    }

    public void setInvalidBackground(Color color)
    {
        invalid_background = color;
    }

    public Color getInvalidForeground()
    {
        if(invalid_foreground == null)
        {
            if(text != null)
                return ((Component)text).getForeground();
            else
                return vc.getForeground();
        } else
        {
            return invalid_foreground;
        }
    }

    public void setInvalidForeground(Color color)
    {
        invalid_foreground = color;
    }

    public Color getValidBackground()
    {
        return valid_background;
    }

    public void setValidBackground(Color color)
    {
        valid_background = color;
    }

    public Color getValidForeground()
    {
        return valid_foreground;
    }

    public void setValidForeground(Color color)
    {
        valid_foreground = color;
    }

    public boolean getBeepOnInvalid()
    {
        return beep;
    }

    public void setBeepOnInvalid(boolean flag)
    {
        beep = flag;
    }

    public boolean getAllowSpinKeys()
    {
        return allow_spin_keys;
    }

    public void setAllowSpinKeys(boolean flag)
    {
        allow_spin_keys = flag;
    }

    public int getIncrementPolicy()
    {
        return increment_policy;
    }

    public void setIncrementPolicy(int i)
    {
        if(i != 0 && i != 1)
        {
            throw new IllegalArgumentException("invalid incrementPolicy");
        } else
        {
            increment_policy = i;
            return;
        }
    }

    public Component getPromptTarget()
    {
        return prompt_target;
    }

    public void setPromptTarget(Component component)
    {
        if((component instanceof Label) || (component instanceof JCLabel) || (component instanceof JCTextComponent) || (component instanceof TextComponent) || component == null)
        {
            prompt_target = component;
            return;
        } else
        {
            throw new IllegalArgumentException("unsupported promptTarget");
        }
    }

    public String getPromptText()
    {
        return prompt_text;
    }

    public void setPromptText(String s)
    {
        prompt_text = s;
    }

    public int getInvalidPolicy()
    {
        return invalid_policy;
    }

    public void setInvalidPolicy(int i)
    {
        if(validator == null)
            return;
        if(i != 0 && i != 1 && i != 2 && i != 3)
            throw new IllegalArgumentException("invalid value for invalidPolicy");
        invalid_policy = i;
        if(state != 2)
            return;
        switch(invalid_policy)
        {
        case 0: // '\0'
            setStateInvalid();
            return;

        case 1: // '\001'
            setValue(default_value);
            return;

        case 2: // '\002'
            setValue(internal_value);
            return;

        case 3: // '\003'
            setText("");
            JCValidInfo jcvalidinfo = validator.validateText("");
            if(jcvalidinfo.valid)
            {
                setStateValid();
                return;
            } else
            {
                setStateInvalid();
                return;
            }
        }
    }

    public int getState()
    {
        return state;
    }

    public boolean getSelectOnEnter()
    {
        return select_on_enter;
    }

    public void setSelectOnEnter(boolean flag)
    {
        select_on_enter = flag;
    }

    public Object getValue()
    {
        if(validator == null)
            return null;
        else
            return validator.copyValue(internal_value);
    }

    public void setAndValidateValue(Object obj)
    {
        user_set_value = true;
        setValue(obj);
        user_set_value = false;
    }

    public void setValue(Object obj)
    {
        setValue(null, obj);
    }

    public void setValue(AWTEvent awtevent, Object obj)
        throws IllegalArgumentException
    {
        JCFieldEvent jcfieldevent = null;
        if(validator == null)
            return;
        if(is_setting_value)
            return;
        is_setting_value = true;
        if(internal_value != obj)
            jcfieldevent = new JCFieldEvent(event_source, this, internal_value, obj, validator.getPickListIndex(obj));
        if(user_set_value && jcfieldevent != null)
        {
            validate_support.fireValueChangedBegin(jcfieldevent);
            JCFieldListener jcfieldlistener;
            for(Enumeration enumeration = JCListenerList.elements(field_listeners); enumeration.hasMoreElements(); jcfieldlistener.valueChangedBegin(jcfieldevent))
                jcfieldlistener = (JCFieldListener)enumeration.nextElement();

            if(!jcfieldevent.isValid())
            {
                is_setting_value = false;
                throw new IllegalArgumentException();
            }
            obj = jcfieldevent.getValue();
        }
        internal_value = validator.copyValue(obj);
        internal_text_change = true;
        int i = text.getCursorPosition();
        int j = text.getSelectionStart();
        int k = text.getSelectionEnd();
        int l = text.getText().length();
        if(has_focus && validator.hasEditFormat() && text != null && text.getEditable())
            setText(validator.formatForEdit(internal_value));
        else
            setText(validator.format(internal_value));
        if(l == text.getText().length())
            if(j == k && j == i)
                text.setCursorPosition(i);
            else
                text.select(j, k);
        internal_text_change = false;
        if(jcfieldevent != null)
        {
            validate_support.fireValueChangedEnd(jcfieldevent);
            JCFieldListener jcfieldlistener1;
            for(Enumeration enumeration1 = JCListenerList.elements(field_listeners); enumeration1.hasMoreElements(); jcfieldlistener1.valueChangedEnd(jcfieldevent))
                jcfieldlistener1 = (JCFieldListener)enumeration1.nextElement();

        }
        validate(awtevent);
        is_setting_value = false;
    }

    protected Object getDefaultValue()
    {
        return default_value;
    }

    protected void setDefaultValue(Object obj)
    {
        default_value = obj;
    }

    public boolean getAllowTextChange()
    {
        return allow_text_change;
    }

    protected void setText(String s)
    {
        if(text == null)
        {
            return;
        } else
        {
            allow_text_change = true;
            text.setText(s);
            allow_text_change = false;
            return;
        }
    }

    public void addFieldListener(JCFieldListener jcfieldlistener)
    {
        field_listeners = JCListenerList.add(field_listeners, jcfieldlistener);
    }

    public void removeFieldListener(JCFieldListener jcfieldlistener)
    {
        field_listeners = JCListenerList.remove(field_listeners, jcfieldlistener);
    }

    public void addValidateListener(ValidateListener validatelistener)
    {
        validate_support.addValidateListener(validatelistener);
    }

    public void removeValidateListener(ValidateListener validatelistener)
    {
        validate_support.removeValidateListener(validatelistener);
    }

    public JCValidateInterface getValidator()
    {
        return validator;
    }

    public void setValidator(JCValidateInterface jcvalidateinterface)
    {
        if(validator == jcvalidateinterface)
            return;
        if(vc instanceof JCCalendarPopup)
            if(jcvalidateinterface instanceof JCDateValidator)
                ((JCCalendarPopup)vc).cal.setHideTimeSpinner(true);
            else
                ((JCCalendarPopup)vc).cal.setHideTimeSpinner(false);
        if(validator != null && jcvalidateinterface != null && validator.getClass() != jcvalidateinterface.getClass())
            internal_value = null;
        validator = jcvalidateinterface;
        loadList();
        setValue(internal_value);
    }

    protected void setStateInvalid()
    {
        if(validator == null)
            return;
        if(state != 2)
        {
            JCFieldEvent jcfieldevent = new JCFieldEvent(event_source, this, internal_value, internal_value, validator.getPickListIndex(internal_value), okay_to_beep && beep);
            validate_support.fireStateIsInvalid(jcfieldevent);
            JCFieldListener jcfieldlistener;
            for(Enumeration enumeration = JCListenerList.elements(field_listeners); enumeration.hasMoreElements(); jcfieldlistener.stateIsInvalid(jcfieldevent))
                jcfieldlistener = (JCFieldListener)enumeration.nextElement();

            internal_value = jcfieldevent.getValue();
            if(jcfieldevent.getBeep())
                vc.getToolkit().beep();
            if(text != null)
            {
                if(invalid_background != null)
                    ((Component)text).setBackground(invalid_background);
                if(invalid_foreground != null)
                    ((Component)text).setForeground(invalid_foreground);
            } else
            {
                if(invalid_background != null)
                    vc.setBackground(invalid_background);
                if(invalid_foreground != null)
                    vc.setForeground(invalid_foreground);
            }
            state = 2;
        }
    }

    protected void setStateValid()
    {
        if(state != 0)
            if(text != null)
            {
                if(invalid_background != null)
                    ((Component)text).setBackground(valid_background);
                if(invalid_foreground != null)
                    ((Component)text).setForeground(valid_foreground);
            } else
            {
                if(invalid_background != null)
                    vc.setBackground(valid_background);
                if(invalid_foreground != null)
                    vc.setForeground(valid_foreground);
            }
        state = 0;
    }

    protected void setStateUnderEdit()
    {
        if(state == 2)
            if(text != null)
            {
                ((Component)text).setBackground(valid_background);
                ((Component)text).setForeground(valid_foreground);
            } else
            {
                vc.setBackground(valid_background);
                vc.setForeground(valid_foreground);
            }
        state = 1;
    }

    public Component getComponent()
    {
        return vc;
    }

    public Object[] getPickList()
    {
        if(validator == null)
            return null;
        else
            return validator.getPickList();
    }

    public void setPickList(Object aobj[])
    {
        if(validator == null)
        {
            return;
        } else
        {
            validator.setPickList(aobj);
            loadList();
            validate(null);
            return;
        }
    }

    public Object getEventSource()
    {
        return event_source;
    }

    public void setEventSource(Object obj)
    {
        event_source = obj;
    }

    public String[] getDisplayList()
    {
        if(validator == null)
            return null;
        else
            return validator.getDisplayList();
    }

    public void setDisplayList(String as[])
    {
        if(validator == null)
        {
            return;
        } else
        {
            validator.setDisplayList(as);
            loadList();
            validate(null);
            return;
        }
    }

    public void commitEdit()
    {
        commitEdit(null);
    }

    public void commitEdit(AWTEvent awtevent)
    {
        if(validator == null)
            return;
        if(state != 1)
        {
            if(select_on_enter)
            {
                text.setSelectionStart(0);
                text.setSelectionEnd(text.getLastPosition());
            }
            return;
        }
        String s = text.getText();
        JCValidInfo jcvalidinfo;
        if(s == null || s.length() == 0)
            jcvalidinfo = validator.validate(null);
        else
            jcvalidinfo = validator.validateText(text.getText());
        if(jcvalidinfo.valid)
        {
            boolean flag = false;
            try
            {
                user_set_value = true;
                setValue(awtevent, jcvalidinfo.value);
                user_set_value = false;
            }
            catch(IllegalArgumentException _ex)
            {
                flag = true;
            }
            if(flag)
                setStateInvalid();
            else
                setStateValid();
        } else
        {
            setStateInvalid();
            setInvalidPolicy(invalid_policy);
        }
        if(select_on_enter)
        {
            text.setSelectionStart(0);
            text.setSelectionEnd(text.getLastPosition());
        }
    }

    public void initialize(InitialEvent initialevent, CellInfo cellinfo, Object obj)
    {
        is_cell_editor = true;
        has_focus = true;
        if(validator == null)
            return;
        edit_class = cellinfo.getDataType();
        if(obj != null && !validator.isSupported(obj))
            obj = validator.convertToSupported(obj);
        select_on_enter = cellinfo.getSelectAll();
        edit_initial_value = obj;
        setValue(obj);
    }

    public boolean isCellEditor()
    {
        return is_cell_editor;
    }

    public boolean stopCellEditing()
    {
        commitEdit();
        return getState() == 0;
    }

    public Object getCellEditorValue()
    {
        if(validator == null)
            return null;
        commitEdit();
        Object obj = internal_value;
        if(edit_class != null && obj != null && edit_class != obj.getClass())
            obj = validator.convertFromSupported(obj, edit_class.getName());
        return obj;
    }

    public boolean isModified()
    {
        if(validator == null)
            return true;
        return text.getChanged() || !validator.compareValues(internal_value, edit_initial_value);
    }

    private static final boolean TRACE = false;
    public static final int VALID = 0;
    public static final int UNDEREDIT = 1;
    public static final int INVALID = 2;
    public static final int SHOW_INVALID = 0;
    public static final int RESTORE_DEFAULT = 1;
    public static final int RESTORE_PREVIOUS = 2;
    public static final int CLEAR_FIELD = 3;
    public static final int INCREMENT_STATIC = 0;
    public static final int INFER_INCREMENT = 1;
    public static final int AS_IS = 0;
    public static final int UPPERCASE = 1;
    public static final int LOWERCASE = 2;
    protected Color invalid_background;
    protected Color invalid_foreground;
    protected Color valid_background;
    protected Color valid_foreground;
    protected boolean select_on_enter;
    protected Object internal_value;
    protected Object edit_initial_value;
    protected Class edit_class;
    protected int list_index;
    protected Object default_value;
    protected int default_list_index;
    protected JCValidateInterface validator;
    protected Locale current;
    protected Object event_source;
    protected Component vc;
    protected JCTextInterface text;
    protected JCChoiceInterface choice;
    protected JCSpinInterface spin;
    protected CellRenderer renderer;
    protected CellEditor editor;
    protected int increment_policy;
    protected int state;
    protected int invalid_policy;
    protected boolean allow_spin_keys;
    protected boolean beep;
    protected boolean internal_text_change;
    protected boolean has_focus;
    protected JCListenerList field_listeners;
    protected ValidateSupport validate_support;
    protected Listeners listener;
    protected String prompt_text;
    protected Component prompt_target;
    protected boolean okay_to_beep;
    protected boolean allow_text_change;
    protected boolean is_cell_editor;
    protected boolean user_set_value;
    protected boolean select_all;
    private boolean is_setting_value;
}
