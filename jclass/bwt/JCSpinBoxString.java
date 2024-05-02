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
// RCSID -- $RCSfile: JCSpinBoxString.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:11:07 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import java.awt.*;
import java.applet.*;

/**
 * JCSpinBoxString provides the capabilities of JCSpinBox which
 * allows the user to select a value from a ring of related choices which
 * are displayed in sequence.
 * By default, the text field is editable. If the user attempts to enter an
 * invalid value, it is disallowed.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AutoArrowDisable    </td><td><a href=jclass.bwt.JCSpinBox.html#setPosition>setAutoArrowDisable</a></td></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCContainer.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Position            </td><td><a href=#setPosition>setPosition</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * StringList          </td><td><a href=#setStringList>setStringList</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCSpinBoxEvent    </td><td><a href=jclass.bwt.JCSpinBox.html#addSpinBoxListener>addSpinBoxListener</a></td> <td>Posted when the user changes the value</td></tr>
 * </table>
 */
public class JCSpinBoxString extends JCSpinBox {

String[] list = new String[0];

/** Creates an empty SpinBox. No parameters are read from an HTML file. */
public JCSpinBoxString() {
	super();
}

/** Creates an empty SpinBox with the specified number of columns.
 * No parameters are read from an HTML file. 
 */
public JCSpinBoxString(int cols) {
	super(cols);
}

/** Creates a SpinBox which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCSpinBoxString(Applet applet, String name) {
	super(applet, name);
	if (getClass().getName().equals("jclass.bwt.JCSpinBoxString"))
		 getParameters(applet);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	SpinBoxStringConverter.getParams(this);
}

/** Gets the StringList value.
 * @see #setStringList
 */
public String[] getStringList() { return list; }

/** Sets the array of values to be displayed.<p>
 * <strong>HTML param name/value:</strong> "StringList"/comma-separated list
 */
public void setStringList(String[] v) {
	list = v;
	validate(value);
	if (text.getText().length() == 0) 
		initTextValue();
}

/** Gets the Position value.
 * @see #setPosition
 */
public int getPosition() { return position; }

/** Sets the position of the currently displayed item (default: 0)
 * (the index in the StringValues list).
 * <strong>HTML param name/value:</strong> "Position"/int
 * @see #setStringList
 */
public void setPosition(int v) {
	if (list.length == 0) {
		position = 0;
		value = null;
	}
	else {
		position = Math.max(Math.min(v, list.length-1), 0);
		value = list[position];
	}
	setTextValue(value);
	enableArrowButtons();
}

/** Sets the current value as an int. */
public void setIntValue(int v) {
	value = ""+v;
	setTextValue(value);
}

/** Gets the value to be displayed.
 * The position value is also updated.
 * @param dir INCREMENT, DECREMENT or NONE
 */
protected Object calcValue(int dir) {
	if (list.length == 0) return null;
	if (dir == NONE) return list[position];
	position =
		Math.max(Math.min(position + (dir == INCREMENT ? 1 : -1), list.length-1), 0);
	return list[position];
}

/** Sets the text field's initial value. */
protected void initTextValue() {
	value = (list != null && list.length > 0)  ? list[0] : null;
	position = 0;
	setTextValue(value);
}

/** Sets the text field's value. */
protected void setTextValue(Object value) {
	String v = (value != null) ? value.toString() : null;
	text.setText(v);
	postSpinBoxEvent(END, value);
}

/** Disables the arrow buttons if the end of the range is reached.
 * @see JCSpinBox#setAutoArrowDisable
 */
protected void enableArrowButtons() {
	if (!auto_arrow_disable) {
		incr_arrow.enable(true);
		decr_arrow.enable(true);
	}
	else {
		incr_arrow.enable(position < list.length-1);
		decr_arrow.enable(position > 0);
	}
}

/** Validates a key typed into the text field.
 * @return true always
 */
protected boolean validateKey(char key) { return true; }

/** Validates the text field's value after the user has changed it.
 * If the value is valid, the position is determined.
 * @see #setPosition
 * @return false if the value is invalid
 */
protected boolean validate(Object value) {
	if (value == null)
		return list.length == 0;
	String s = value.toString();
	for (int i=0; i < list.length; i++)
		if (list[i] != null && list[i].equals(s)) {
			position = i;
			return true;
		}
	return false;
}

}
