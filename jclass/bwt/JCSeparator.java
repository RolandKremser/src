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
// RCSID -- $RCSfile: JCSeparator.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:11:01 $ $Locker: $  Sitraka Inc.

package jclass.bwt;

import jclass.base.*;
import java.awt.*;
import java.applet.*;

/**
 * JCSeparator is a simple component that separates items in a display.
 * The shadow is drawn centered horizontally or vertically, depending on
 * the separator's orientation.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table>
 */
public class JCSeparator extends JCComponent {

public static final int	HORIZONTAL = BWTEnum.HORIZONTAL;
public static final int	VERTICAL   = BWTEnum.VERTICAL;

int	dir = HORIZONTAL;

private static final String base = "separator";
private static int nameCounter = 0;

/**
 * Constructs a horizontal separator.
 */
public JCSeparator() {
	this(HORIZONTAL);
}

/**
 * Constructs a separator with the specified orientation.
 * @param dir either HORIZONTAL or VERTICAL
 */
public JCSeparator(int dir) {
	this(dir, null, null);
}

/** Creates a separator which reads parameters from the applet's HTML file.
 * @param dir either HORIZONTAL or VERTICAL
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCSeparator(int dir, Applet applet, String name) {
	super(applet, name);
	if (name == null) 
		setName(base + nameCounter++);
	border_style = Border.IN;
	if (getClass().getName().equals("jclass.bwt.JCSeparator"))
		 getParameters(applet);
	this.dir = dir;
 	traversable = false;
	highlight = 0;
}

final static String[] orient_strings = { "horizontal", "vertical" };
final static int[] orient_values = { HORIZONTAL, VERTICAL };

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	dir = conv.toEnum(getParam("Orientation"), "orientation", 
					  orient_strings, orient_values, dir);
}

/**
 * Gets the orientation.
 */
public int getOrientation() { return dir; }

/**
 * Sets the orientation. This must be called before the scrollbar is visible 
 * (ie before it is added to its parent).<p>
 * <strong>HTML param name/value:</strong> "Orientation"/enum
 * @param dir either HORIZONTAL or VERTICAL
 * @exception IllegalArgumentException If an invalid value was set
 */
public void setOrientation(int dir) { 
	jclass.util.JCUtilConverter.checkEnum(dir, "orientation", orient_values);
	this.dir = dir;
}

/** Returns the shadow thickness value if this is a vertical separator; 
 * 100 otherwise. 
 */
protected int preferredWidth() {
	return (dir == VERTICAL) ? 0 : 100;
}

/** Returns the shadow thickness value if this is a horizontal separator; 
 * 100 otherwise. 
 */
protected int preferredHeight() {
	return (dir == HORIZONTAL) ? 0 : 100;
}

/** Draws a shadow centered horizontally or vertically, depending on
 * the orientation.
 */
public void paint(Graphics gc) {
	Rectangle rect = getDrawingArea();
	int w = size().width, h = size().height;
	int x = (dir == HORIZONTAL) ? 0 : (w-border)/2,
		y = (dir == HORIZONTAL) ? (h-border)/2 : 0;
	int width = (dir == HORIZONTAL) 
		? w - getInsets().left - getInsets().right : 2*border;
	int height = (dir == HORIZONTAL) 
		? 2*border : h - getInsets().top - getInsets().bottom;
	Border.draw(gc, border_style, border, 
				x + getInsets().left, y + 
				getInsets().top, width, height,
				getBackground(), getForeground());
}

}
