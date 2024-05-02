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
// RCSID -- $RCSfile: JCSplitterWindow.java $ $Revision: 2.14 $ $Date: 2000/11/09 20:11:08 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.applet.*;
import java.awt.*;

/** A container similar to Motif's XmPanedWindow, which manages 1 or more
 * children oriented vertically or horizontally. A JCSeparator component
 * separates each pair of children.
 * The user can adjust the size of the panes by dragging the separator.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * MinChildSize        </td><td><a href=#setMinChildSize>setMinChildSize</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table>
 */
public class JCSplitterWindow extends JCContainer {

public static final int	HORIZONTAL = BWTEnum.HORIZONTAL;
public static final int	VERTICAL   = BWTEnum.VERTICAL;

int	dir = HORIZONTAL;
int min_child_size = 20;
protected boolean resized = false;		// Set if the user moves a separator

private static final String base = "splitterwindow";
private static int nameCounter = 0;

/** Creates an empty horizontal window (i.e. the children are layed out side-to-side). 
 * No parameters are read from an HTML file.
 */
public JCSplitterWindow() {
	this(HORIZONTAL, null, null); 
}

/** Creates an empty window. No parameters are read from an HTML file.
 * @param dir either HORIZONTAL or VERTICAL
 */
public JCSplitterWindow(int dir) {
	this(dir, null, null); 
}

/** Creates a pane which reads parameters from the applet's HTML file.
 * @param dir either HORIZONTAL or VERTICAL
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCSplitterWindow(int dir, Applet applet, String name) {
	super(applet, name);
	if (name == null) 
		setName(base + nameCounter++);
	this.dir = dir;
	if (getClass().getName().equals("jclass.bwt.JCSplitterWindow"))
		 getParameters(applet);
	setLayout(null);
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
	min_child_size = conv.toInt(getParam("MinChildSize"), min_child_size);
}

/** Gets the direction of the panes.
 * @see #setOrientation 
 */
public int getOrientation() { return dir; }

/** Sets the orientation: HORIZONTAL or VERTICAL.
 * @exception IllegalArgumentException If an invalid value was set
 */
public void setOrientation(int v) {
	jclass.util.JCUtilConverter.checkEnum(v, "orientation", orient_values);
	dir = v;
	// loop through children and switch the orientation on and separator
	// children
	Component[] child = getComponents();
	int flip = dir == VERTICAL ? HORIZONTAL : VERTICAL;
	for (int i = 0; i < child.length; i++) {
		if (child[i] instanceof JCSeparator) {
			// set the orientation of the separator to be opposite to that
			// of the splitter window			
			((JCSeparator)child[i]).setOrientation(flip);			
		}
	}
	// clear the resized flag so that we properly relayout in the
	// new orientation
	resized = false;
	layout();
}

/** Gets the MinChildSize value.
 * @see #setMinChildSize
 */
public int getMinChildSize() { return min_child_size; }

/** Sets the minimum size (in pixels) to which a child can be resized by the 
 * user (default: 20).
 */
public void setMinChildSize(int v) { min_child_size = v; }

/** 
 * Adds the specified component to this container at the given position.
 * A JCSeparator is added between each pair of children.
 * @param pos the position at which to insert the component.
 * -1 means insert at the end.
 * @see JCSeparator
 */
/* JDK102_START 
public Component add(Component comp) {
	if ((countComponents() % 2) != 0) 
		super.add(new SplitterWindowSeparator(this));
	return super.add(comp);
}
 JDK102_END */

/** 
 * Adds the specified component to this container at the given position.
 * A JCSeparator is added between each pair of children.
 * @param pos the position at which to insert the component.
 * -1 means insert at the end.
 * @see JCSeparator
/* JDK110_START */
protected void addImpl(Component comp, Object constraints, int index) {
	if ((countComponents() % 2) != 0)
		super.addImpl(new SplitterWindowSeparator(this), null, index);
	super.addImpl(comp, null, index);
	layout();
}
/* JDK110_END */

/** 
 * Removes the component and its adjoining JCSeparator from the window.
 */
public void remove(Component comp) {
	int index = getComponent(comp);
	if (index < 0) return;
	if (!(comp instanceof JCSeparator)) {
		Component sep = getComponent(index == 0 ? index+1 : index-1);
		if (sep instanceof JCSeparator) {
			super.remove(sep);
			sep.hide(); 	// JDK bug - RMF
			resized = false;
		}
	}
	super.remove(comp);
	layout();
}

/**
 * Returns the sum of all children's preferred height if orientation is
 * vertical; otherwise returns the maximum preferred height of all children. 
 */
protected int preferredHeight() {
	Component[] child = getComponents();
	if (child.length == 0) return 100;
	int h = 0;
	for (int i=0; i < countComponents(); i++) {
		int height = JCComponent.getPreferredSize(child[i]).height;
		if (dir == VERTICAL)
			h += height;
		else
			h = Math.max(h, height);
	}
	return h;
}

/**
 * Returns the sum of all children's preferred width if orientation is
 * horizontal; otherwise returns the maximum preferred width of all children. 
 */
protected int preferredWidth() {
	Component[] child = getComponents();
	if (child.length == 0) return 100;
	int w = 0;
	for (int i=0; i < countComponents(); i++) {
		int width = JCComponent.getPreferredSize(child[i]).width;
		if (dir == HORIZONTAL)
			w += width;
		else
			w = Math.max(w, width);
	}
	return w;
}

void layout(int[] sizes) {
	if (sizes == null) return;
	Rectangle rect = getDrawingArea();
	int x = rect.x, y = rect.y, w, h, dir = getOrientation();
	Component[] comps = getComponents();

	for (int i=0; i < Math.min(sizes.length, comps.length); i++) {
		Component child = comps[i];
		sizes[i] = Math.abs(sizes[i]);
		w = (dir == VERTICAL) ? rect.width : sizes[i];
		h = (dir == HORIZONTAL) ? rect.height : sizes[i];
		JCComponent.setBounds(child, x, y, w, h);
		child.layout();
		x += (dir == VERTICAL) ? 0 : sizes[i] + 1;
		y += (dir == VERTICAL) ? sizes[i] + 1 : 0;
	}
}

/** Returns the area inside the insets. */
protected Rectangle getDrawingArea() {
	return new Rectangle(JCComponent.getInsets(this).left, 
						 JCComponent.getInsets(this).top,
						 size().width - JCComponent.getInsets(this).left - 
						 JCComponent.getInsets(this).right,
						 size().height - JCComponent.getInsets(this).top - 
						 JCComponent.getInsets(this).bottom);
}

/** 
 * Gets the child component's size for layout (default: getPreferredSize()).
 * @see #layout
 */
protected Dimension getLayoutSize(Component child) {
	return JCComponent.getPreferredSize(child);
}

/** Positions the window's internal elements.
 * If the layout is not due to the user moving the separator,
 * getLayoutSize() is called to get each child's size.
 * @see getLayoutSize
 */
public void layout() {
	if (!isDisplayable()) return;
	int x, x0, y, y0, w, h, local_dir;
	int total_size = 0;
	Rectangle rect;
	Component[] comps = getComponents();
	if (comps.length == 0) return;
	int[] sizes = new int[comps.length];

	synchronized (this) {
		local_dir = getOrientation();
		rect = getDrawingArea();
		x = x0 = rect.x;
		y = y0 = rect.y;
	}
	int major_size = (local_dir == VERTICAL) ? rect.height : rect.width;

	// If only one child, set it to the window's size
	if (comps.length == 1) {
		JCComponent.setBounds(comps[0], x, y, rect.width, rect.height);
		comps[0].layout();
		return;
	}

	for (int i=0; i < comps.length; i++) {
		Component child = comps[i];
		Dimension size;
		if (resized && child.size().width > 0 && child.size().height > 0)
			size = child.size();
		else
			size = getLayoutSize(child);
		w = (local_dir == VERTICAL) ? rect.width : size.width;
		h = (local_dir == HORIZONTAL) ? rect.height : size.height;
		int s = (local_dir == VERTICAL) ? h : w;

		// Stretch the last child to the edge if necessary
		if (i == comps.length-1 && total_size+s < major_size) {
			w = (local_dir == VERTICAL) ? rect.width : rect.width - x;
			h = (local_dir == HORIZONTAL) ? rect.height : rect.height - y;
		}

		if (resized)
			JCComponent.setBounds(child, x, y, w, h);
		else
			sizes[i] = (local_dir == VERTICAL) ? h : w;
		total_size += sizes[i];

		x += (local_dir == VERTICAL) ? 0 : w + 1;
		y += (local_dir == VERTICAL) ? h + 1 : 0;
	}

	// Return if the separator has been moved by the user
	// since the rest of the layout code arranges all the children too fit
	// according to their preferred sizes and this would throw away any
	// size changes affected by the user through manual resizing
	if (resized) {		
		return;
	}

	// If children extend beyond the window's size, reduce each child,
	// starting with the largest.
	int diff = total_size - major_size;
	while (diff > 0) {
		int largest = 0, index = -1;
		for (int k=0; k < sizes.length; k++) {
			if (!(comps[k] instanceof JCSeparator) && sizes[k] >= largest) {
				largest = sizes[k];
				index = k;
			}
		}
		if (index == -1) break;
		Component child = comps[index];
		int new_size = Math.max(sizes[index]-diff, getMinChildSize());
		diff -= sizes[index] - new_size;
		sizes[index] = -new_size;	// Mark the item in the list as seen
	}

	layout(sizes);
}

/** 
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

}
