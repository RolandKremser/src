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
// RCSID -- $RCSfile: JCHeader.java $ $Revision: 2.21 $ $Date: 2000/11/09 20:10:34 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import jclass.util.JCqsort;
import java.awt.*;
import java.applet.*;

/** JCHeader's labels. */
class HeaderLabel extends JCLabel {

JCHeader header;

HeaderLabel(JCHeader header, Object label) {
	super(label, null, null);
	this.header = header;
	insets.left = insets.right = 5;
	border = highlight = 1;
/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
/* JDK110_END */
}

/**
 * Tracks the cursor.
 */
public boolean mouseMove(Event ev, int x, int y) { return header.mouseMove(ev); }

public boolean mouseExit(Event ev, int x, int y) { return header.mouseExit(ev); }

public boolean mouseDown(Event ev, int x, int y) { return header.mouseDown(ev); }

public boolean mouseDrag(Event ev, int x, int y) { return header.mouseDrag(ev); }

public boolean mouseUp(Event ev, int x, int y) { return header.mouseUp(ev); }

}

/** JCHeader's buttons. */
class HeaderButton extends JCButton {

JCHeader header;
int direction = JCqsort.ASCENDING;

HeaderButton(JCHeader header, Object label) {
	super(label, null, null);
	this.header = header;
	border = highlight = 1;
	traversable = false;
	insets.left = insets.right = 5;
/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
/* JDK110_END */
}

/** Draws a 1-pixel black border around the button. */
protected void drawHighlight(Graphics gc, boolean on) {
	gc.setColor(highlight_color != null ? highlight_color : Color.black);
	gc.drawRect(highlight-1, highlight-1,
				size().width-2*highlight+1, size().height-2*highlight+1);
}

/**
 * Tracks the cursor.
 */
public boolean
mouseMove(Event ev, int x, int y) { return header.mouseMove(ev); }

public boolean
mouseExit(Event ev, int x, int y) {
	header.mouseExit(ev);
	return super.mouseExit(ev, x, y);
}

public boolean
mouseDown(Event ev, int x, int y) {
	return header.mouseDown(ev) || super.mouseDown(ev, x, y);
}

public boolean
mouseDrag(Event ev, int x, int y) { return header.mouseDrag(ev); }

public boolean
mouseUp(Event ev, int x, int y) {
	header.mouseUp(ev);
	return super.mouseUp(ev, x, y);
}
}

/**
 * A component which displays a row of labels, commonly used to show column
 * labels for a multi-column component. The user can drag the separator between
 * labels to resize a column.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * ColumnAlignments    </td><td><a href=#setColumnAlignments>setColumnAlignments</a></td></tr><tr><td>
 * ColumnDisplayWidths </td><td><a href=#setColumnDisplayWidths>setColumnDisplayWidths</a></td></tr><tr><td>
 * ColumnLeftMargin    </td><td><a href=#setColumnLeftMargin>setColumnLeftMargin</a></td></tr><tr><td>
 * ColumnResizePolicy  </td><td><a href=#setColumnResizePolicy>setColumnResizePolicy</a></td></tr><tr><td>
 * ColumnRightMargin   </td><td><a href=#setColumnRightMargin>setColumnRightMargin</a></td></tr><tr><td>
 * ColumnWidths        </td><td><a href=#setColumnWidths>setColumnWidths</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Labels              </td><td><a href=#setLabels>setLabels</a></td></tr><tr><td>
 * Buttons             </td><td><a href=#setButtons>setButtons</a></td></tr><tr><td>
 * NumColumns          </td><td><a href=#setNumColumns>setNumColumns</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Posted when a button's is clicked</td></tr>
 * </table>
 */
public class JCHeader extends JCContainer
implements JCMultiColumnInterface {

/** The data for this component. */
protected JCMultiColumnData	data = new JCMultiColumnData(this);

/** The component being controlled. */
protected JCMultiColumnInterface comp;

/** The mouse position, or the column currently being resized. */
protected int resize_col = BWTEnum.NOVALUE;

/** True if a column is being resized. */
protected boolean resizing = false;

boolean num_columns_set = false;	// Has setNumColumns() been called?
boolean update_parent = true;
boolean batched = false;

/** List of JCActionEvent listeners */
protected JCVector actionListeners = new JCVector(0);

private static final String base = "header";
private static int nameCounter = 0;
private static final boolean TRACE = false;

/** Creates an empty header. No parameters are read from an HTML file. */
public JCHeader() {
	this(null, null, null);
}

/** Creates a header with the specified labels.
 * No parameters are read from an HTML file.
 */
public JCHeader(String[] labels) {
	this(labels, null, null);
}

/** Creates a header with the specified labels.
 * No parameters are read from an HTML file.
 * @see #setLabels
 */
public JCHeader(JCVector labels) {
	this(null, null, null);
	if (labels != null)
		setLabels(labels);
}

/** Creates a header which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setLabels
 */
public JCHeader(String[] labels, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(null);
	if (getClass().getName().equals("jclass.bwt.JCHeader"))
		 getParameters(applet);
	if (labels != null)
		setLabels(new JCVector(labels));
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	HeaderConverter.getParams(this);
}

/**
 * Sets the font of the component.
 * This value may be set from an HTML file using a PARAM tag with a "Font"
 * name and a Font value.
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	super.setFont(f);
	if (!isDisplayable()) return;
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		((JCLabel)child[i]).setFontInternal(f);
	data.calcColumnWidths();
	resize(JCComponent.getPreferredSize(this).width, size().height);
	layout();
	repaint();
}

/** Gets the component controlled by the header.
 * @see #setMultiColumnComponent
 */
public JCMultiColumnInterface getMultiColumnComponent() { return comp; }

/** Sets the component which will be controlled by the header. */
public void setMultiColumnComponent(JCMultiColumnInterface comp) {
	if (this.comp == comp)
		return;
	this.comp = comp;
	if (comp instanceof JCComponent)
		insets = new Insets(0, ((JCComponent)comp).getInsets().left,
							0, ((JCComponent)comp).getInsets().right);

	// Copy external widths
	int[] widths = comp.getColumnWidths();
	int[] display_widths = comp.getColumnDisplayWidths();
	for (int i=0; i < comp.getNumColumns(); i++) {
		data.setColumnAlignment(i, comp.getColumnAlignment(i));
		data.setColumnRightMargin(i, comp.getColumnRightMargin(i));
		data.setColumnLeftMargin(i, comp.getColumnLeftMargin(i));
		if (i < widths.length) {
			data.setColumnWidth(i, widths[i]);
		}
		if (i < display_widths.length) {
			data.setColumnDisplayWidth(i, display_widths[i]);
		}
	}
	layout();
	repaint();
}

/** Gets the labels. */
public JCComponent[] getLabels() {
	Component[] child = getComponents();
	JCComponent[] c = new JCComponent[child.length];
	for (int i=0; i < child.length; i++)
		c[i] = (JCComponent) child[i];
	return c;
}

/** Gets the label at column i */
public JCComponent getLabel(int col) {
	Component[] child = getComponents();
	return (JCComponent) child[col];
}

/** Creates a JCLabel for each label.<p>
 * <strong>HTML param name/value:</strong> "Labels"/comma-separated list of labels
 * @see #addLabel
 * @see #addButton
 */
public void setColumnLabels(JCVector labels) {
	setLabels(labels);
}

/** Creates a JCLabel for each label.<p>
 * <strong>HTML param name/value:</strong> "Labels"/comma-separated list of labels
 * @see #addLabel
 * @see #addButton
 * @see jclass.util.JCConverter#toVector
 */
public void setLabels(JCVector labels) {
	removeAll();
	if (!num_columns_set)
		data.setNumColumns(0);
	update_parent = false;
	for (int i=0; labels != null && i < labels.size(); i++)
		addLabel(labels.elementAt(i));
	update_parent = true;
	recalc();
	updateParent();
}

/** Creates a JCButton for each label.<p>
 * <strong>HTML param name/value:</strong> "Buttons"/comma-separated list of labels<p>
 * @see #addButton
 * @see #addButton
 */
public void setColumnButtons(JCVector labels) {
	setButtons(labels);
}

/** Creates a JCButton for each label.<p>
 * <strong>HTML param name/value:</strong> "Buttons"/comma-separated list of labels<p>
 * @see #addButton
 * @see #addButton
 * @see jclass.util.JCConverter#toVector
 */
public void setButtons(JCVector labels) {
	removeAll();
	if (!num_columns_set)
		data.setNumColumns(0);
	update_parent = false;
	for (int i=0; labels != null && i < labels.size(); i++)
		addButton(labels.elementAt(i));
	update_parent = true;
	recalc();
	updateParent();
}

/** Creates a label with the specified value, and adds it to the header.
 * @return the new label
 * @see #addButton
 * @see JCLabel#setLabel
 */
public JCLabel addLabel(Object label) {
	HeaderLabel l = new HeaderLabel(this, label);
	if (!num_columns_set)
		data.setNumColumns(getNumColumns() + 1);
	add(l);
	updateParent();
	return l;
}

/** Creates a button with the specified value, and adds it to the header.
 * @return the new button
 * @see #addButton
 * @see JCLabel#setLabel
 */
public JCButton addButton(Object label) {
	HeaderButton b = new HeaderButton(this, label);
	for (int i=0; i < actionListeners.size(); i++)
		b.addActionListener((JCActionListener) actionListeners.elementAt(i));
	if (!num_columns_set)
		data.setNumColumns(getNumColumns() + 1);
	add(b);
	updateParent();
	return b;
}

/** Recalculates column widths, and forces the parent to relayout the header. */
public void updateParent() {
	if (update_parent && isDisplayable()) {
		data.calcColumnWidths();
		invalidate();
		Container parent = getParent();
		if (parent instanceof Viewport) parent = parent.getParent();
		parent.invalidate();
		parent.validate();
	}
}

/** Calculates column widths. */
public void addNotify() {
	super.addNotify();
	data.calcColumnWidths();
}

/** Positions all labels. */
public void layout() {
	if (TRACE) {
		System.out.println("JCHeader.layout()");
	}
	int num_columns = 0;
	synchronized (this) {
		num_columns = getNumColumns();
		if (num_columns == 0 && comp != null && !num_columns_set) {
			boolean s = num_columns_set;
			num_columns_set = true;
			setNumColumns((num_columns = comp.getNumColumns()));
			num_columns_set = s;
		}
	}

	Component[] child = getComponents();
	int x = 0, x0 = firstColumnOffset();
	for (int col = 0; col < child.length; col++) {
		JCLabel label = (JCLabel) child[col];
		if (col >= num_columns) {
			// if we have more labels than columns then hide the remainder
			label.hide();
			continue;
		}

		Insets insets = label.getInsets();
		insets.left = getColumnLeftMargin(col) - label.getBorderThickness();
		insets.right = getColumnRightMargin(col) - label.getBorderThickness();
		label.setInsets(insets);

		int w = getColumnDisplayWidth(col);
		if (TRACE) {
			System.out.println("\tcolumn " + col + " width = " + w + "\t max = " + getColumnWidth(col));
		}
		
		// shift the first label's inset from the left edge of header to
		// keep its value aligned with the list's 1st column's values.
		// (width was already bumped by getColumnWidth())
		if (col == 0) {
			insets = label.getInsets();
			insets.left += x0;
			label.setInsets(insets);
		} else if (col == child.length+1) {
			// last column
		}
		label.alignment = getColumnAlignment(col);
		JCComponent.setBounds(label, x, 0, w + label.getBorderThickness(), 
							  size().height);
		label.show();
		x += w;
	}
}

/** Determines the widths of variable-sized columns and repaints. */
public void recalc() {
	data.calcColumnWidths();
	resize(JCComponent.getPreferredSize(this).width, size().height);
	layout();
	repaint();
}

/** Returns the height of the largest label. */
protected int preferredHeight() {
	int h = 0;
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		h = Math.max(h, JCComponent.getPreferredSize(child[i]).height);
	return h + JCComponent.getInsets(this).top + 
		JCComponent.getInsets(this).bottom;
}

/** Returns the starting position of the component's 1st column. */
private int firstColumnOffset() {
	int x = (comp != null) ? comp.getColumnPosition(0) : 0;
	if (comp instanceof JCScrollableInterface)
		x += ((JCScrollableInterface)comp).getHorizOrigin();
	return x;
}

/**
 * Returns the preferred width of all labels
 */
protected int preferredWidth() {
    JCComponent comp;
	if ( (comp = (JCComponent) this.comp) == null) {
    	return(data.preferredWidth());
    }
    else {
		// add also list components inset and shadows and highlights
		return (data.preferredWidth()
				+ 2*comp.getBorderThickness() + 2*comp.getHighlightThickness()
				+ JCComponent.getInsets(this).left + 
				JCComponent.getInsets(this).right);
    }
}

/* AWT_START 
public Dimension preferredSize() {
 AWT_END */
/* SWING_START */
public Dimension getPreferredSize() {
/* SWING_END */

/* AWT_START 
	Dimension size = super.preferredSize();
 AWT_END */
/* SWING_START */
	Dimension size = super.getPreferredSize();
/* SWING_END */

	size.width = preferredWidth();
	return size;
}

/**
 * Adds the specified listener to receive action events from the buttons.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) {
	actionListeners.addUnique(l);
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		if (child[i] instanceof JCButton)
			((JCButton)child[i]).addActionListener(l);
}

/**
 * Removes the specified listener so it no longer receives
 * action events from the buttons.
 * @see #addActionListener
 */
public void removeActionListener(JCActionListener l) {
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		if (child[i] instanceof JCButton)
			((JCButton)child[i]).removeActionListener(l);
	actionListeners.removeElement(l);
}

/**
 * Tracks the cursor.
 */
protected boolean mouseMove(Event ev) {
	if (resizing) return true;
	JCComponent c = (JCComponent) ev.target;
	resize_col = getComponent(c);

	// If column resize is not RESIZE_NONE, i.e. resizing is allowed.
	if (getColumnResizePolicy() != BWTEnum.RESIZE_NONE) {
		if (ev.x > (c.size().width - 5)) {
			// near right hand side of column so set resize cursor
			c.setCursor(Frame.W_RESIZE_CURSOR);
			return(true);
		} else if (ev.x < 5) {
			// Near left hand side of column so this gets tricky.
			// We need to go backward one column.
			resize_col -= 1;

			// Make sure that we are not on the first non-hidden column
			while (data.getColumnDisplayWidth(resize_col) == 0 && resize_col >= 0) {
				// ignore this column since it is hidden i.e. width = 0
				resize_col -= 1;
			}

			// In RESIZE_COLLAPSE policy, going backward and can't find a 
			// column with width > 0, then we should resize column 0.
			// We need to check if the column 0 is actually zero column
			// width because column 0 may in full size and we may just 
			// put the cursor at the left edge of the header.  
			// In that case, we don't want to resize column 0.
			if (getColumnResizePolicy() == BWTEnum.RESIZE_COLLAPSE &&
			resize_col < 0 && data.getColumnDisplayWidth(0) == 0) {
				resize_col = Math.max(0, resize_col);
			}

			if (resize_col >= 0) {
				c.setCursor(Frame.W_RESIZE_CURSOR);
				return(true);
			}
		}
	}

	return mouseExit(ev);
}

/** Changes the cursor to the default. */
protected boolean mouseExit(Event ev) {
	if (resizing) return true;
	resize_col = BWTEnum.NOVALUE;
	setCursor(Frame.DEFAULT_CURSOR);
	return true;
}

private int mouse_down_x = BWTEnum.NOVALUE;

/**
 * If at a label boundary, starts a resize.
 */
protected boolean mouseDown(Event ev) {
	if (resize_col == BWTEnum.NOVALUE || (getColumnResizePolicy() == BWTEnum.RESIZE_NONE)) 
		return false;
	resizing = true;
	JCComponent c = (JCComponent) ev.target;
	mouse_down_x = ev.x + c.location().x;
	drawLine(false, mouse_down_x);
	return true;
}

/** If currently resizing a column, redraws the temporary line. */
protected boolean mouseDrag(Event ev) {
	if (!resizing || (getColumnResizePolicy() == BWTEnum.RESIZE_NONE)) return false;
	Component c = (Component) ev.target;
	int x = ev.x + c.location().x;

	if ((getColumnResizePolicy() == BWTEnum.RESIZE_SINGLE) && 
		(x - getColumnPosition(resize_col) - getColumnLeftMargin(resize_col) < 20)) {
		return false;
	}
	drawLine(true, 0);
	drawLine(false, x);
	return true;
}


/** If currently resizing a column, resizes the component's column. */
protected boolean mouseUp(Event ev) {
	if (!resizing) return false;
	Component c = (Component) ev.target;
	drawLine(true, 0);
	// translate x coord relative to labels container to make target component
	// irrlevant to resize; also subtract the initial offset
	int x = ev.x + c.location().x;
/* JDK110_START */
	x -= firstColumnOffset();
/* JDK110_END */
	int policy = getColumnResizePolicy();
	int w = 0;
	if (policy == BWTEnum.RESIZE_SINGLE) {
		w = Math.max(10, getColumnWidth(resize_col)
						 + x - getColumnPosition(resize_col + 1)
/* JDK110_START */
						 + firstColumnOffset()
/* JDK110_END */
					     );
	} else if (policy == BWTEnum.RESIZE_COLLAPSE) {
		w = x - mouse_down_x;
	}

	JCMultiColumnInterface mc = (comp != null) ? comp : this;
	if (policy == BWTEnum.RESIZE_SINGLE) {
		// display width equals column width
		mc.setColumnWidth(resize_col, w);
		mc.setColumnDisplayWidth(resize_col, w);
	} else if (policy == BWTEnum.RESIZE_COLLAPSE) {
		// if next column is collapsed, the current column is expanded 
		// up to its column width.  Otherwise, the current column is
		// expanded as in RESIZE_SINGLE
		// Column display width must be smaller or equal to column width.
		int delta_width = 0, current_width = 0;
		int width_remaining = Math.abs(w);
		boolean expanding = (w > 0);
		int current_col = resize_col;
		while (width_remaining > 0 && current_col >= 0) {
			int dw = getColumnDisplayWidth(current_col);
			int cw = getColumnWidth(current_col);
			if (TRACE) {
				System.out.println("column " + current_col + " width = " + getColumnDisplayWidth(current_col) + "\t max = " + getColumnWidth(current_col));
				System.out.print(((expanding)?"expanding" : "shrinking") + " col: " + current_col);
				System.out.println(" col width: " + cw + " display width: " + dw);
			}
			if (expanding) {
				if (dw >= cw && getColumnDisplayWidth(current_col+1) != 0) {
					// No need to expand any more since current column is
					// at column width and next column is not collapsed.
					break;
				}
				
				if (dw + width_remaining > cw) { 
					// Expand up to the column width
					current_width = cw;
					delta_width = Math.max(0, cw-dw);
				} else {
					current_width = dw + width_remaining;
					delta_width = width_remaining;
				}
			} else {
				delta_width = Math.min(getColumnDisplayWidth(current_col), width_remaining);
				current_width = getColumnDisplayWidth(current_col) - delta_width;
			}
			if (TRACE) {
				System.out.print((expanding)?"expanded" : "shrunk" + " col: " + current_col);
				System.out.println(" from " + dw + " to " + current_width);
			}
			mc.setColumnDisplayWidth(current_col, current_width);
			width_remaining -= delta_width;
			if (expanding) {
				// Going foreward when expanding
				current_col++;
			} else {
				// Going backward when shrinking
				current_col--;
			}
		}
	}

	resize_col = BWTEnum.NOVALUE;
	resizing = false;
	setCursor(Frame.DEFAULT_CURSOR);
	return true;
}

/**
 * Sets the cursor to the default cursor if not resizing a column.
 */
public boolean mouseExit(Event ev, int x, int y) {
	if (resizing) return false;
	resize_col = BWTEnum.NOVALUE;
	resizing = false;
	setCursor(Frame.DEFAULT_CURSOR);
	return true;
}

private static int x_save = BWTEnum.NOVALUE;

/**
 * Draw temporary dashed line while resizing.
 * @param clear If true, the previously-drawn line is erased (and the
 * remaining arguments are ignored).
 */
protected void drawLine(boolean clear, int x) {
	// Drawing the line again at the same position erases it
	if (!clear)
		x_save = x;
	else if (x_save == BWTEnum.NOVALUE)
		return;

	x = x_save;
	Component c = locate(x, 0);
	if (c == null) return;

	Graphics gc = c.getGraphics();
	gc.translate(-c.location().x, 0);
	gc.setXORMode(getBackground());
	gc.drawLine(x, 0, x, size().height);
	gc.dispose();
}

/** Calculates the width of a column.
 * If the multiColumn component's column width is not VARIABLE, it is returned.
 * Otherwise the larger of the label's width and the component's
 * column width is returned.
 */
public int calcWidth(int col) {
	Component[] comps = getComponents();

	if (col >= comps.length)
		return 0;
	JCLabel label = (JCLabel) comps[col];
	int comp_width = 0;
	int[] widths = comp != null ? comp.getColumnWidths() : new int[0];
	if (comp != null) {
		int extra = getColumnLeftMargin(col) + getColumnRightMargin(col);
		comp_width = comp.getColumnWidth(col);
		if (col < widths.length && widths[col] != BWTEnum.VARIABLE) {
			if (comp_width - extra > 0)
				return comp_width;
			comp_width = 0;
		}
	}
	Insets insets = label.getInsets();
	insets.left = getColumnLeftMargin(col);
	insets.right = getColumnRightMargin(col);
	label.setInsets(insets);

	int w = JCComponent.getPreferredSize(label).width - 
		    label.getBorderThickness();

	/* If the component's column is variable and the header's label is wider,
	 * set the component's width to the header's
	 */
	if (w > comp_width
		&& col < widths.length && widths[col] == BWTEnum.VARIABLE
		&& comp.getMultiColumnData() != null)
		comp.getMultiColumnData().setColumnWidthInternal(col, w);

	return Math.max(comp_width, w);
}

/** Gets a list of the column widths.
 * @see #setColumnWidths
 */
public int[] getColumnWidths() { return data.getColumnWidths(); }

/** Sets the list of column widths. If a column's value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.<p>
 * <strong>HTML param name/value:</strong> "ColumnWidths"
 * name and a comma-separated list of ints.
 */
public void setColumnWidths(int[] widths) {
	data.setColumnWidths(widths);
	layout();
	resize(JCComponent.getPreferredSize(this).width, size().height);
	repaint();
}

/**
 * Gets the current width of the column header button,
 * or 0 if the column does not exist. This gets the actual size off a label
 * The first and last columns are stretched over the inset of the component
 * and the thickness of the border and focus rectangle to make everything
 * line up visually and size correctly
 */
public int getColumnDisplayWidth(int col) {
	int w = data.getColumnDisplayWidth(col);
	if (col == 0 || col == (data.getNumColumns() - 1)) {
		// if first or last column
		if (this.comp != null && this.comp instanceof JCComponent) {
			JCComponent comp = (JCComponent) this.comp;
			// stretch first and last column headers to cover off the list
			// component's inset as well as it's highlight and shadows
			if (col == 0) {
				w += comp.getBorderThickness() + 
					JCComponent.getInsets(this).left;
			}
			else {
				// if the display width of the last column is zero and
				// the column resize policy is RESIZE_COLLAPSE, no
				// border and highlight thickness should be left.
				if (w > 0 && getColumnResizePolicy() == BWTEnum.RESIZE_COLLAPSE) {
					w += comp.getBorderThickness() + 
						JCComponent.getInsets(this).right + 
						comp.getHighlightThickness();
				}
			}
		}
	}

	return(w);
}

/**
 * Sets the width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidth(int col, int width) {
	int w, delta_w = width - data.getColumnWidth(col);
	data.setColumnWidth(col, width);
	if (getParent() == null) return;

	if (getParent() instanceof Viewport)
		w = Math.max(size().width+delta_w, getParent().size().width);
	else
		w = JCComponent.getPreferredSize(this).width;
	resize(w, size().height);
	updateParent();
	repaint();
}

/** Gets the current number of columns. */
public int getNumColumns() { return data.num_columns; }

/** Sets the current number of columns.
 * If set to BWTEnum.VARIABLE (default), all labels are displayed.<p>
 * <strong>HTML param name/value:</strong> "NumColumns"/int
 */
public void setNumColumns(int v) {
	data.setNumColumns(v != BWTEnum.VARIABLE ? v : countComponents());
	num_columns_set = (v != BWTEnum.VARIABLE);
	data.calcColumnWidths();
	if (!batched) {
		resize(JCComponent.getPreferredSize(this).width, size().height);
		updateParent();
	}
}

/** Gets the column alignments. */
public int[] getColumnAlignments() { return data.getColumnAlignments(); }

/** Gets a column's alignment (default: BWTEnum.MIDDLELEFT). */
public int getColumnAlignment(int col) { return data.getColumnAlignment(col); }

/** Sets a column's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignment(int col, int align) {
	data.setColumnAlignment(col, align);
	if (col < countComponents())
		((JCLabel) getComponent(col)).setAlignment(align);
	repaint();
}

/** Sets the column's alignments.<p>
 * <strong>HTML param name/value:</strong> "ColumnAlignments"/comma-separated list of enums<p>
 * @param align one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align) {
	data.setColumnAlignments(align);
	layout();
	repaint();
}

/** Gets the physical position of the left boundary of the column.
 */
public int getColumnPosition(int col) {
	return data.getColumnPosition(col) + JCComponent.getInsets(this).left;
}

/** Gets the column's left margin value (pixels). */
public int getColumnLeftMargin(int col) {
	return data.getColumnLeftMargin(col);
}

/** Sets the column's left margin value (pixels) (default: 5).<p>
 * <strong>HTML param name/value:</strong> "ColumnLeftMargins"/comma-separated list of ints, one for each column
 */
public void setColumnLeftMargin(int col, int value) {
	int current = getColumnLeftMargin(col);
	data.setColumnLeftMargin(col, value);
	if (getParent() == null || col >= getNumColumns()) return;
	resize(size().width+value-current, size().height);
	updateParent();
}

/** Gets the column's right margin value (pixels). */
public int getColumnRightMargin(int col) {
	return data.getColumnRightMargin(col);
}

/** Sets the column's right margin value (pixels) (default: 5).<p>
 * <strong>HTML param name/value:</strong> "ColumnRightMargins"/comma-separated list of ints, one for each column
 */
public void setColumnRightMargin(int col, int value) {
	int current = getColumnRightMargin(col);
	data.setColumnRightMargin(col, value);
	if (getParent() == null || col >= getNumColumns()) return;
	resize(size().width+value-current, size().height);
	updateParent();
}

/** Gets the component's JCMultiColumnData instance. */
public JCMultiColumnData getMultiColumnData() { return data; }

/** Gets a list of the column display widths.
 * @see #setColumnDisplayWidths
 */
public int[] getColumnDisplayWidths() { return data.getColumnDisplayWidths(); }

/** Sets the list of column display widths. */
public void setColumnDisplayWidths(int[] widths) {
	data.setColumnDisplayWidths(widths);
	layout();
	resize(JCComponent.getPreferredSize(this).width, size().height);
	repaint();
}

/** Gets the width of a column, or BWTEnum.NOVALUE if the column does not exist.
 */
public int getColumnWidth(int col) { return data.getColumnWidth(col); }

/** Sets the display width of a column.  */
public void setColumnDisplayWidth(int col, int width) {
	int w, delta_w = width - data.getColumnDisplayWidth(col);
	data.setColumnDisplayWidth(col, width);

	if (getParent() == null) return;

	if (getParent() instanceof Viewport)
		w = Math.max(size().width+delta_w, getParent().size().width);
	else
		w = JCComponent.getPreferredSize(this).width;
	resize(w, size().height);
	updateParent();
	repaint();
}

/** Gets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public int getColumnResizePolicy() {
	return data.getColumnResizePolicy();
}

/** Sets column resize policy. 
 * <p><strong>HTML param name/value:</strong> "ColumnResizePolicy"/int</p>
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public void setColumnResizePolicy(int policy) {
	data.setColumnResizePolicy(policy);
}

/**
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

}
