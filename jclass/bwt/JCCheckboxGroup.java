/*****************************************************************************
 *
 * (c) Copyright 1997-2000, SITRAKA INC.  
 * ALL RIGHTS RESERVED 
 *
 *  THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED
 * AND COPIED ONLY IN ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND
 * WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE.  THIS SOFTWARE OR
 * ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE
 * AVAILABLE TO ANY OTHER PERSON.  NO TITLE TO AND OWNERSHIP OF THE
 * SOFTWARE IS HEREBY TRANSFERRED.
 *
 *  THE INFORMATION IN THIS SOFTWARE IS SUBJECT TO CHANGE WITHOUT
 * NOTICE AND SHOULD NOT BE CONSTRUED AS A COMMITMENT BY SITRAKA INC.
 * OR ITS THIRD PARTY SUPPLIERS.
 *
 *  SITRAKA INC. AND ITS THIRD PARTY SUPPLIERS, ASSUME NO RESPONSIBILITY 
 * FOR THE USE OR INABILITY TO USE ANY OF ITS SOFTWARE.   THIS SOFTWARE IS 
 * PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, AND SITRAKA INC. EXPRESSLY 
 * DISCLAIMS ALL IMPLIED WARRANTIES, INCLUDING BUT NOT LIMITED TO THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Notice:  Notwithstanding any other lease or license that may pertain to,
 * or accompany the delivery of, this computer software, the rights of the
 * Government regarding its use, reproduction and disclosure are as set
 * forth in Section 52.227-19 of the FARS Computer Software-Restricted
 * Rights clause.
 *
 * (c) Copyright 1997-2000 Sitraka Inc.  Unpublished - all
 * rights reserved under the Copyright laws of the United States.
 *
 * RESTRICTED RIGHTS NOTICE:  Use, duplication, or disclosure by the
 * Government is subject to the restrictions as set forth in subparagraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software clause
 * at DFARS 52.227-7013.
 *
 * Sitraka Inc.
 * 260 King Street East
 * Suite 300
 * Toronto, Ontario
 * Canada M5A 1K3
 * (416) 594-1026
 * (416) 594-1919 (FAX)
 *
 * RESTRICTED RIGHTS LEGEND:  This computer software is submitted with
 * "restricted rights."  Use, duplication or disclosure is subject to the
 * restrictions as set forth in NASA FAR SUP 18-52.227-79 (April 1985)
 * "Commercial Computer Software- Restricted Rights (April 1985)."  Sitraka
 * Inc., 300-260 King Street E., Toronto, Ontario, Canada M5A 1K3.  If
 * the contract contains the Clause at 18-52.227-74 "Rights in Data General"
 * then the "Alternate III" clause applies.
 *
 * (c) Copyright 1997-2000 Sitraka Inc.
 * ALL RIGHTS RESERVED
 *
 *****************************************************************************/
// RCSID -- $RCSfile: JCCheckboxGroup.java $ $Revision: 2.7 $ $Date: 2000/11/09 20:09:30 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import java.awt.*;
import java.applet.*;

/**
 * Creates a box for a set of JCCheckbox buttons. By default, all the buttons
 * are given a radio-button style, and only one is "on" at a time.
 * The AWT CheckboxGroup class does not allow access to the
 * checkboxes it manages. This makes the task of manipulating
 * checkbox groups awkward. JCCheckboxGroup stores the checkboxes in a list.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * RadioBehavior       </td><td><a href=#setRadioBehavior>setRadioBehavior</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCItemEvent      </td><td><a href=#addItemListener>addItemListener</a></td> <td>Posted when the button's state changes</td></tr>
 * </table><p>
 *
 * Example:
 <PRE>
   public myForm extends Panel {

   int colorValues      = { BLUE, GREEN, VIOLET };
   String colorStrings  = {"Blue", "Green", "Violet"};
   JCCheckbox colorG;

   public myForm() {
	 add(colorG = JCCheckbox.makeGroup(colorStrings, colorValues, true));
   }

   private int getColor();
   private void setColor(int);

   public void getData() {
     colorG.set(colorValues, getColor());
   }

   public void setData() {
     setColor(colorG.getCurrentJC().value);
   }
   }
 </PRE>
 * If items change, simply change the colorValues and colorStrings arrays.
 */
public class JCCheckboxGroup extends JCGroupBox implements JCItemSelectable {

public static final int	HORIZONTAL = BWTEnum.HORIZONTAL;
public static final int	VERTICAL   = BWTEnum.VERTICAL;

/** The child checkboxes. */
protected JCVector 		boxes = new JCVector();

/** The current choice. */
protected JCCheckbox 	current = null;

int 					orientation = VERTICAL;
boolean					radio_behavior = true;

/** List of JCItemEvent listeners */
protected JCVector  itemListeners = new JCVector(0);

private static final String base = "checkboxgroup";
private static int nameCounter = 0;

/** Creates an empty vertical group which uses a GridLayout
 * with insets of (5,5,5,5).
 * No parameters are read from an HTML file.
 */
public JCCheckboxGroup() {
	this(null, null); 
}

/** Creates an empty vertical group with the specified title.
 * No parameters are read from an HTML file. 
 * @param title a String, Image, JCString, or any object (in which case the object's toString() method is called to obtain a string)
 * @see JCGroupBox#setTitle
 */
public JCCheckboxGroup(Object title) {
	this(null, null);
	if (title != null)
		setTitle(title);
}

/** Creates an empty vertical group which uses a GridLayout with insets of (5,5,5,5).
 * Parameters are read from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 * @see java.awt.GridLayout
 * @see JCContainer#setInsets
 */
public JCCheckboxGroup(Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(new GridLayout(0, 1));
	setInsets(new Insets(5, 5, 5, 5));

	if (getClass().getName().equals("jclass.bwt.JCCheckboxGroup"))
		 getParameters(applet);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	CheckboxGroupConverter.getParams(this);
}

/**
 * Adds a checkbox to the list of managed checkboxes. The checkbox is added
 * to this container. To specify that a checkbox be managed which is not a 
 * child of this group, use JCCheckbox.setCheckboxGroup() or manage
 * @see JCCheckbox#setCheckboxGroup
 * @see #manage
 */
public Component add(JCCheckbox box) {
	manage(box);
	return super.add(box);
}

/**
 * Adds a checkbox to the list of managed checkboxes. The checkbox is added
 * to this container. To specify that a checkbox be managed which is not a 
 * child of this group, use JCCheckbox.setCheckboxGroup() or manage
 * @see JCCheckbox#setCheckboxGroup
 * @see #manage
 */
public Component add(String name, JCCheckbox box) {
	manage(box);
	return super.add(name, box);
}

/**
 * Adds a checkbox to the list of managed checkboxes. The checkbox is added
 * to this container. To specify that a checkbox be managed which is not a 
 * child of this group, use JCCheckbox.setCheckboxGroup() or manage
 * @see JCCheckbox#setCheckboxGroup
 * @see #manage
 */
public Component add(JCCheckbox box, int index) {
	manage(box);
	return super.add(box, index);
}

/* JDK110_START */
public void add(JCCheckbox box, Object constraints) {
	manage(box);
	super.add(box, constraints);
}

public void add(JCCheckbox box, Object constraints, int index) {
	manage(box);
	super.add(box, constraints, index);
}
/* JDK110_END */

/**
 * Adds a checkbox to the list of managed checkboxes. The checkbox is not added
 * to this container.
 * @see #add
 */
public void manage(JCCheckbox box) {
	if (box == null) return;
	if (box.group != null)
		box.group.boxes.removeElement(box);
	box.group = this;
	boxes.addUnique(box);
	for (int i=0; i < itemListeners.size(); i++)
		box.addItemListener((JCItemListener) itemListeners.elementAt(i));
}

/**
 * Returns the number of Checkbox instances that have been added.
 * @see #add
 */
public int numCheckboxes() { return boxes.size(); }

/**
 * Gets the checkbox at the specified index.
 */
public JCCheckbox getCheckbox(int i) {
    if (i >= 0 && i < boxes.size())
         return (JCCheckbox) boxes.elementAt(i);
    return null;
}

/** Gets the orientation.
 * @see #setOrientation
 */
public int getOrientation() { return orientation; }

/** Sets the layout direction: VERTICAL (default) or HORIZONTAL.<p>
 * <strong>HTML param name/value:</strong> "Orientation"/enum
 * @see jclass.util.JCConverter#toEnum
 */
public void setOrientation(int v) {
	orientation = v;
	if (v == VERTICAL)
		setLayout(new GridLayout(0, 1));
	else if (v == HORIZONTAL)
		setLayout(new GridLayout(1, 0));
	else
		throw new IllegalArgumentException("invalid orientation: " + v);

	if (getParent() != null) {
		invalidate();
		getParent().invalidate();		// Netscape bug
		getParent().validate();
		repaint();
	}
}

/**
 * Gets the currently-selected checkbox.
 * @see #setCurrent
 */
public JCCheckbox getCurrent() { return current; }

/**
 * Returns a list of the indices of the selected checkboxes,
 * or null if no checkboxes are selected.
 * @see #getCheckbox
 * @see #getSelectedObjects
 */
public int[] getSelectedIndexes() {
	int num = 0;
	for (int i=0; i < boxes.size(); i++)
		if (getCheckbox(i).state != BWTEnum.OFF) num++;
	if (num == 0) return null;

	int[] items = new int[num];

	for (int i=0, k=0; i < boxes.size(); i++) {
		if (getCheckbox(i).state != BWTEnum.OFF)
			items[k++] = i;
	}
	return items;
}

/**
 * Returns the a list of the labels of the selected checkboxes,
 * or null if no checkboxes are selected.
 * @see #getSelectedIndexes
 */
public Object[] getSelectedObjects() {
	int list[] = getSelectedIndexes();
	if (list == null) return null;

	Object[] items = new Object[list.length];
	for (int i=0; i < list.length; i++)
		items[i] = getCheckbox(list[i]).label;
	return items;
}

/**
 * Sets the current choice to the specified checkbox, whose state is set
 * to BWTEnum.ON.
 * If RadioBehavior has been set, the previously current button is unselected.
 * @param box if this button belongs to a different group, there is no effect
 * @see #setRadioBehavior
 */
public void setCurrent(JCCheckbox box) {
	JCCheckbox oldChoice; 

	synchronized (this) {
		if (!radio_behavior || (box != null && box.group != this))
			return;
		oldChoice = current;
		current = box;
	}
	if (oldChoice != null && oldChoice != box) 
	    oldChoice.setState(BWTEnum.OFF);
	if (box != null && oldChoice != box && box.state != BWTEnum.ON) {
	    box.state = BWTEnum.ON;
		box.repaint();
	}
}

/** Gets the RadioBehavior value.
 * @see #setRadioBehavior
 */
public boolean getRadioBehavior() { return radio_behavior; }

/** If set to true (default), a RadioBox-type behavior is enforced on
 * all Checkboxes. When one button is selected and another is set interactively
 * or programmatically, the first button is unselected automatically.<p>
 * The checkboxGroup will set the indicator of any checkbox whose indicator 
 * has not been set explicitly via setIndicator:
 * <ul>
 * <li>If set to true, the Checkbox's indicator is set to INDICATOR_CIRCLE.
 * <li>If set to false, the Checkbox's indicator is set to INDICATOR_CROSS.
 * </ul><p>
 * This value may be set in an HTML file using the PARAM name "RadioBehavior"
 * with a boolean value.
 * @see #setCurrent
 * @see JCCheckbox#setState
 * @see JCCheckbox#setIndicator
 * @see jclass.util.JCConverter#toBoolean
 */
public void setRadioBehavior(boolean v) {
	radio_behavior = v;
	for (int i=0; i < boxes.size(); i++) 
		if (!getCheckbox(i).indicator_set)
			getCheckbox(i).setIndicator(v ? BWTEnum.INDICATOR_CIRCLE
										: BWTEnum.INDICATOR_CROSS);

	/* If switched to radio buttons,
	 * reset all controls and select first radio button
	 */
	if (radio_behavior) {
    	for (int i=0; i < boxes.size(); i++) {
			JCCheckbox checkbox = (JCCheckbox) boxes.elementAt(i);
			if (current == checkbox) {
	    		checkbox.setState(BWTEnum.ON);
			}
			else if (current == null && i == 0) {
				current = checkbox;
	    		checkbox.setState(BWTEnum.ON);
			} 
			else 
	    		checkbox.setState(BWTEnum.OFF);
		}
		repaint();
	}
}

/** Gets the current checkbox's UserData as an int. If the current button
 * has no UserData value, 0 is returned.
 * @return BWTEnum.NOTFOUND if no button is selected
 * @see #setValue
 * @see JCCheckbox
 * @see jclass.base.BaseComponent#getUserDataInt
 */
public int getValue() {
	if (current != null)
		return current.getUserDataInt();
	return BWTEnum.NOTFOUND;
}

/** Sets the group's current checkbox to the first checkbox whose UserData
 * is the specified value.
 * @return false if no button could be found which matches the value
 * @see #getValue
 */
public boolean setValue(int value) {
	for (int i=0; i < boxes.size(); i++)
		if (((JCCheckbox)boxes.elementAt(i)).getUserDataInt() == value) {
			setCurrent(getCheckbox(i));
			return true;
		}
	return false;
}

/**
 * Adds the specified item listener to receive item events from the checkboxes.
 * @see JCItemEvent
 */ 
public void addItemListener(JCItemListener l) {
	itemListeners.addUnique(l);
	for (int i=0; i < boxes.size(); i++)
		getCheckbox(i).addItemListener(l);
}

/**
 * Removes the specified item listener so it no longer receives
 * item events from the checkboxes.
 */ 
public void removeItemListener(JCItemListener l) {
	for (int i=0; i < boxes.size(); i++)
		getCheckbox(i).removeItemListener(l);
	itemListeners.removeElement(l);
}

/** Returns a list of the positions of all buttons whose state is ON. */
public int[] getSelected() {
	int i, num = 0;
	for (i=0; i < boxes.size(); i++)
		if (getCheckbox(i).state == BWTEnum.ON)
			num++;
	int list[] = new int[num];
	for (i=0, num=0; i < boxes.size(); i++)
		if (getCheckbox(i).state == BWTEnum.ON)
			list[num++] = i;

	return list;
}

/**
 * Sets the currently-selected checkbox to that containing the
 * value "val".  Typically, using JCCheckbox and JCCheckboxGroup
 * involves creating an array representing values assigned to each
 * checkbox. This function takes the array and the desired value,
 * finds the checkbox item, and makes that checkbox the current item.
 * This makes it relatively easy to set checkbox values based on user
 * data.  For example:
 * <PRE>
 int boxValues[] = { BLUE, GREEN, VIOLET };
 JCCheckboxGroup boxG;
 
 public getColor() {
	 boxG.set(boxValues, myObject.getColor());
 }
 </PRE>
 */
public void setValue(int[] values, int val) {
    int index = -1;
    for (int i = 0; i < values.length; i++) {
        if (values[i] == val) 
            index = i;
    }
    if (index >= 0 && index < boxes.size())
        setCurrent((JCCheckbox)boxes.elementAt(index));
}

/** If RadioBehavior is true and no button has been set, set the first. */
public void addNotify() {
	super.addNotify();
	if (radio_behavior) {
		JCCheckbox origCurrent = current;
		setRadioBehavior(true);
		if (current == null)
			setCurrent(getCheckbox(0));
		else if (origCurrent != current && origCurrent != null)
			setCurrent(origCurrent);
	}
}

}
