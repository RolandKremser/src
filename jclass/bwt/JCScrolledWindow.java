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
// RCSID -- $RCSfile: JCScrolledWindow.java $ $Revision: 2.18 $ $Date: 2000/11/09 20:10:59 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
//import jclass.util.*;
import java.applet.*;
import java.awt.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */
/* SWING11_START
import com.sun.java.swing.JComponent;
 SWING11_END */
/* SWING12_START */
//import javax.swing.JComponent;
/* SWING12_END */
/**
 * The JCScrolledWindow class combines one or two Scrollbars
 * and a viewing area to implement a visible window (a "viewport")
 * onto some other (usually larger) data display. The visible
 * part of the window can be scrolled through the larger
 * display by the use of the scrollbars.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>   <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * ColumnAlignments    </td><td><a href=#setColumnAlignments>setColumnAlignments</a></td></tr><tr><td>
 * ColumnLeftMargin    </td><td><a href=#setColumnLeftMargin>setColumnLeftMargin</a></td></tr><tr><td>
 * ColumnRightMargin   </td><td><a href=#setColumnRightMargin>setColumnRightMargin</a></td></tr><tr><td>
 * ColumnWidths        </td><td><a href=#setColumnWidths>setColumnWidths</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * NumColumns          </td><td><a href=#setNumColumns>setNumColumns</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ScrollbarDisplay    </td><td><a href=#setScrollbarDisplay>setScrollbarDisplay</a></td></tr><tr><td>
 * ScrollbarOffset     </td><td><a href=#setScrollbarOffset>setScrollbarOffset</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table>
 */
public class JCScrolledWindow extends JCContainer implements
/* JDK110_START */
java.awt.event.KeyListener,
/* JDK110_END */
JCAdjustmentListener
{

public static final int DISPLAY_AS_NEEDED = BWTEnum.DISPLAY_AS_NEEDED;
public static final int DISPLAY_ALWAYS = BWTEnum.DISPLAY_ALWAYS;
public static final int DISPLAY_NONE = BWTEnum.DISPLAY_NONE;
public static final int DISPLAY_VERTICAL_ONLY = BWTEnum.DISPLAY_VERTICAL_ONLY;
public static final int DISPLAY_HORIZONTAL_ONLY = BWTEnum.DISPLAY_HORIZONTAL_ONLY;

int 		sb_display = DISPLAY_AS_NEEDED;
int 		sb_offset = 0;
private JCScrollbar  vert_sb = new JCScrollbar(JCScrollbar.VERTICAL);
private JCScrollbar  horiz_sb = new JCScrollbar(JCScrollbar.HORIZONTAL);
int			keystroke = 0;

private static final String base = "scrolledwindow";
private static int nameCounter = 0;

/** The component used as the viewing area into the scrolled component. */
protected Component	viewport;
protected boolean smartscroll = true;

/** Creates an empty window. No parameters are read from an HTML file. */
public JCScrolledWindow() {
	this(null, null);
}

/** Creates a window which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCScrolledWindow(Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(null);
	viewport = new Viewport();
	if (getClass().getName().equals("jclass.bwt.JCScrolledWindow"))
		 getParameters(applet);

	addInternal(vert_sb);
	vert_sb.addAdjustmentListener(this);

	addInternal(horiz_sb);
	horiz_sb.addAdjustmentListener(this);
/* JDK102_START
	vert_sb.scrolled_window = horiz_sb.scrolled_window = this;
 JDK102_END */
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ScrolledWindowConverter.getParams(this);
}

/** Gets the ScrollbarDisplay value.
 * @see #setScrollbarDisplay
 */
public int getScrollbarDisplay() { return sb_display; }

/** Sets when to display the scrollbars:
<pre>
    DISPLAY_ALWAYS           always displayed (default)
    DISPLAY_AS_NEEDED        displayed as necessary
    DISPLAY_VERTICAL_ONLY    always display vertical scrollbar only
    DISPLAY_HORIZONTAL_ONLY  always display horizontal scrollbar only
    DISPLAY_NONE             don't display scrollbars
</pre><p>
 * <strong>HTML param name/value:</strong> "ScrollbarDisplay"/enum
 * @exception IllegalArgumentException If an invalid value was set
 */
public void setScrollbarDisplay(int v) {
	ScrolledWindowConverter.checkDisplay(v);
	sb_display = v;
	layout();
}

/** Gets the ScrollbarOffset value.
 * @see #setScrollbarOffset
 */
public int getScrollbarOffset() { return sb_offset; }

/** Sets the distance between the scrollbars and the viewport (default: 0).<p>
 * <strong>HTML param name/value:</strong> "ScrollbarOffset"/int
 */
public void setScrollbarOffset(int v) {
	sb_offset = v;
	layout();
}

/**
 * Gets the value of SmartScroll.
 * @see #setScrollbarOffset
 */
public boolean getSmartScroll() { return smartscroll; }

/**
 * True (the default) means that scrolling will be performed by
 * moving the drawn area and only repainting the non-visible
 * area that is scrolled into view.  False forces a full repaint
 * each time a scroll occurs
 */
public void setSmartScroll(boolean v) {
	smartscroll = v;
}

/** Gets the viewport. */
public Component getViewport() { return viewport; }

int sb_size() {
	return (sb_display == DISPLAY_ALWAYS) ? BWTEnum.SB_SIZE + sb_offset : 0;
}

protected int preferredWidth() {
	int vp_width = (viewport != null) ?
		            JCComponent.getPreferredSize(viewport).width : 0;
	return (vp_width > 0) ? vp_width + sb_size() : 100;
}

protected int preferredHeight() {
	int vp_height = (viewport != null) ?
		             JCComponent.getPreferredSize(viewport).height : 0;
	return (vp_height > 0) ? vp_height -35+ sb_size() : 20;
}

/** Gets the viewport's "virtual width".
 * The default method returns the viewport's preferred width.
 */
protected int getViewportWidth() {
	return JCComponent.getPreferredSize(viewport).width;
}

/** Gets the viewport's "virtual height".
 * The default method returns the viewport's preferred height.
 */
protected int getViewportHeight() {
	return JCComponent.getPreferredSize(viewport).height;
}

/**
 * Adds the specified component to the viewport.
 */
public Component add(Component comp) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).add(comp);
	return comp;
}

/* JDK110_START */
public Component add(String name, Component comp) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).add(name, comp);
	return comp;
}

public Component add(Component comp, int index) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).add(comp, index);
	return comp;
}

public void add(Component comp, Object constraints) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).add(comp, constraints);
}

public void add(Component comp, Object constraints, int index) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).add(comp, constraints, index);
}
/* JDK110_END */

/* JDK102_START
protected void addInternal(Component comp) {
	if (comp.getParent() != this)
		super.add(comp);
}
 JDK102_END */

/* JDK110_START */
protected void addImpl(Component comp, Object constraints, int index) {
	if (viewport instanceof Viewport) {
		((Viewport)viewport).add(comp);
		if (!(comp instanceof Container))
			comp.addKeyListener(this);
	}
}

protected void addInternal(Component comp) {
	if (comp.getParent() != this)
		super.addImpl(comp, null, -1);
}
/* JDK110_END */

public void addNotify() {
	addInternal(viewport);
	super.addNotify();
}

public JCScrollbar getVertScrollbar() { return vert_sb; }
public JCScrollbar getHorizScrollbar() { return horiz_sb; }

/** This method may be overriden by a subclass to change the default values
 * of the horizontal scrollbar.
 */
protected void setHorizScrollbarValues() {}

/** This method may be overriden by a subclass to
 * set the horizontal scrollbar's values.
 * @param value the scrollbar's value
 * @param visible the amount visible per page (the slider size).
 * The page increment this is also set to this value.
 * @param min the minimum value of the scrollbar
 * @param max the maximum value of the scrollbar
 * @see Scrollbar#setValues
 */
protected void setHorizScrollbarValues(int value, int visible, int min, int max) {
	visible = Math.min(max-min, visible);
	horiz_sb.setValues(value, visible, min, max);
	horiz_sb.setBlockIncrement(visible);
	horiz_sb.setUnitIncrement(10);
	setHorizScrollbarValues();
}

/** This method may be overriden by a subclass to
 * set the vertical scrollbar's values.
 * @param value the scrollbar's value
 * @param visible the amount visible per page (the slider size).
 * The page increment this is also set to this value.
 * @param min the minimum value of the scrollbar
 * @param max the maximum value of the scrollbar
 * @see Scrollbar#setValues
 */
protected void setVertScrollbarValues(int value, int visible, int min, int max) {
	visible = Math.min(max-min, visible);
	vert_sb.setValues(value, visible, min, max);
	vert_sb.setBlockIncrement(visible);
	vert_sb.setUnitIncrement(10);
	setVertScrollbarValues();
}

/** This method may be overriden by a subclass to change the default values
 * of the vertical scrollbar.
 */
protected void setVertScrollbarValues() {}

protected int reshapeHeader(int x, int y, int w) { return 0; }
protected int headerHeight() { return 0; }

/** Reshapes the viewport to the specified size. */
protected void reshapeViewport(int x, int y, int w, int h) {
	JCComponent.setBounds(viewport, x, y, w, h);
}

/** Overrides setBorder so the children get the border */
/* SWING11_START
public void setBorder(com.sun.java.swing.border.Border border) {
 SWING11_END */
/* SWING12_START */
public void setBorder(javax.swing.border.Border border) {
/* SWING12_END */
/* SWING_START */
	swing_border = border;
	setChildrenBorder(border);
}
/* SWING_END */

/** Positions the scrollbars and viewport. */
public void layout() {
	if (!isDisplayable()) return;

    int inc, areaWidth, areaHeight;
    int hsbX, hsbY, vsbX, vsbY, viewportX, viewportY;
    boolean hasHSB, hasVSB;
	int hsbWidth = 0, vsbHeight = 0;
	int myWidth = 0;
	int myHeight = 0;
	int displayHeight = 0, displayWidth = 0;
	int viewport_full_width = 0;
	int viewport_full_height = 0;
	int header_height = headerHeight();
	viewport_full_width = getViewportWidth();
	viewport_full_height = getViewportHeight();

	synchronized (this) {

		myWidth = size().width - JCComponent.getInsets(this).left -
			JCComponent.getInsets(this).right;
		myHeight = size().height - JCComponent.getInsets(this).top -
			JCComponent.getInsets(this).bottom;
		displayHeight = myHeight - header_height;
		displayWidth = myWidth;

		/*
		 * Look at size and set the display dimensions accordingly. If the viewport
		 * fits easily into the space, and the scrollbars are not needed, set
		 * the display dimension to the size of the window and flag the scrollbars
		 * as false. If the viewport won't fit in either direction, or if the
		 * scrollbars are constantly in the way, set the display dimensions to the
		 * minimum area and flag both scrollbars as true. Otherwise, look at
		 * the dimensions, and see if either one needs a scrollbar.
		 */
		if (sb_display == BWTEnum.DISPLAY_NONE) {
			hasVSB = hasHSB = false;
		}
		else if (sb_display == BWTEnum.DISPLAY_ALWAYS) {
			hasVSB = hasHSB = true;
		}
		else if (displayHeight >= viewport_full_height
				 && displayWidth >= viewport_full_width
				 && sb_display == BWTEnum.DISPLAY_AS_NEEDED) {
			displayWidth = myWidth;
			displayHeight = myHeight;
			hasVSB = hasHSB = false;
		}
		else {
			hasVSB = sb_display != BWTEnum.DISPLAY_HORIZONTAL_ONLY;
			hasHSB = sb_display != BWTEnum.DISPLAY_VERTICAL_ONLY;

			boolean vert_needed = true;
			boolean horiz_needed = true;

			// Check if we need any scrollbars
			if (viewport_full_height <= displayHeight
				   && sb_display != BWTEnum.DISPLAY_VERTICAL_ONLY) {
				vert_needed = false;
			}

			if (viewport_full_width <= displayWidth
				   && sb_display != BWTEnum.DISPLAY_HORIZONTAL_ONLY) {
				horiz_needed = false;
			}

			// Since we may now have scrollbars adjust display dimensions.
			if (horiz_needed) {
				displayHeight -= BWTEnum.SB_SIZE + sb_offset;
			}
			if (vert_needed) {
				displayWidth -= BWTEnum.SB_SIZE + sb_offset;
			}

			// Recheck if we still need any of the scrollbars.
			if (viewport_full_height <= displayHeight
				   && sb_display != BWTEnum.DISPLAY_VERTICAL_ONLY) {
				displayWidth = myWidth;
				hasVSB = false;
			}
			else {
				displayWidth = myWidth - (BWTEnum.SB_SIZE + sb_offset);
			}

			if (viewport_full_width <= displayWidth
				   && sb_display != BWTEnum.DISPLAY_HORIZONTAL_ONLY) {
				displayHeight = myHeight;
				hasHSB = false;
			}
			else {
				displayHeight = myHeight - (BWTEnum.SB_SIZE + sb_offset);
			}
		}

		displayHeight = hasHSB ? myHeight - (BWTEnum.SB_SIZE + sb_offset) : myHeight;
		displayWidth = hasVSB ? myWidth - (BWTEnum.SB_SIZE + sb_offset) : myWidth;

		if (hasHSB) {
			if (!hasVSB)
				hsbWidth = myWidth;
			else
				hsbWidth = displayWidth;
		}
		if (hasVSB) {
			if (!hasHSB)
				vsbHeight = myHeight;
			else
				vsbHeight = displayHeight;
		}

		/*
		 * Move the scrollbars and viewport
		 */
		viewportX = JCComponent.getInsets(this).left;
		viewportY = JCComponent.getInsets(this).top;

		hsbX = JCComponent.getInsets(this).left;
		hsbY = hasHSB ? size().height - JCComponent.getInsets(this).bottom -
			BWTEnum.SB_SIZE: size().height;

		vsbX = hasVSB ? size().width - JCComponent.getInsets(this).right -
			BWTEnum.SB_SIZE : myWidth;
		vsbY = JCComponent.getInsets(this).top;
	}

	int h = reshapeHeader(viewportX, viewportY, displayWidth);
	int origin = 0;
	int old_origin = 0;
	int max = 0;
	synchronized (this) {
		viewportY += h;
		displayHeight -= h;
		vsbY += h;
		vsbHeight -= h;

		// Adjust vertical origin if necessary
		origin = vert_sb.getValue();
		old_origin = origin;
		origin = Math.max(0, Math.min(origin, viewport_full_height - displayHeight));
		if (origin != old_origin)
			scrollVertical(null, origin);

		// Set vertical scrollbar values
		max = viewport_full_height;
		inc = displayHeight;
		if (viewport instanceof JCComponent) {
			JCComponent comp = (JCComponent) viewport;
			h = 2 * comp.getBorderThickness() +
				comp.getInsets().top + comp.getInsets().bottom;
			inc -= h;
			max -= h;
		}
	}
	setVertScrollbarValues(origin, inc, 0, max);

	synchronized (this) {
		// Adjust horizontal origin if necessary
		old_origin = origin = horiz_sb.getValue();
		origin = Math.max(0, Math.min(origin, viewport_full_width - displayWidth));
		if (origin != old_origin)
			scrollHorizontal(null, origin);

		// Set horizontal scrollbar values
		max = viewport_full_width;
		inc = displayWidth;
		if (viewport instanceof JCComponent) {
			JCComponent comp = (JCComponent) viewport;
			int w = 2 * comp.getBorderThickness() +
				comp.getInsets().left + comp.getInsets().right;
			inc -= w;
			max -= w;
		}
	}
	setHorizScrollbarValues(origin, inc, 0, max);

	if (viewport instanceof Viewport) {
		((Viewport)viewport).resizeChild(viewport_full_width, viewport_full_height);
	}
	reshapeViewport(viewportX, viewportY, displayWidth, displayHeight);
	Point vp_loc = viewport.location();
	Dimension vp_size = viewport.size();

    if (hasVSB)
        JCComponent.setBounds(vert_sb, vp_loc.x + vp_size.width + sb_offset,
						vp_loc.y, BWTEnum.SB_SIZE, vsbHeight);
	vert_sb.show(hasVSB);

    if (hasHSB)
        JCComponent.setBounds(horiz_sb, vp_loc.x,
						 vp_loc.y + vp_size.height + sb_offset,
						 hsbWidth, BWTEnum.SB_SIZE);
	horiz_sb.show(hasHSB);
}

Rectangle rect = new Rectangle();

/** Scrolls a scrollableInterface component horizontally. */
protected void scrollHorizontal(JCScrollableInterface scrollable,
								JCAdjustmentEvent ev, int value) {
	int delta = value - scrollable.getHorizOrigin(),
		abs_delta = Math.abs(delta);
	Component comp = (Component) scrollable;

	// Adjust origin
	scrollable.setHorizOrigin(value);

	if (comp instanceof JCComponent)
		((JCComponent)comp).getDrawingArea(rect);
	else
		rect.resize(comp.size().width, comp.size().height);

	// Scrolled more than one screen
	if (abs_delta >= rect.width) {
		comp.repaint();
		return;
	}

	int width = rect.width - abs_delta;

	JCComponent jc_comp = (comp instanceof JCComponent) ?
		(JCComponent) comp : null;

	// Copy the current screen contents
	Graphics gc = comp.getGraphics();
	if (jc_comp != null) {
		if (smartscroll) {
			jc_comp.copyArea(gc,
							 rect.x + Math.max(delta, 0), rect.y,
							 width, rect.height, -delta, 0);
			jc_comp.paintImmediately(rect.x + (delta > 0 ? width : 0), rect.y,
									 abs_delta, rect.height);
		}
		else {
			// force the whole thing to repaint
			jc_comp.paintImmediately(rect.x, rect.y,
									 rect.width, rect.height);
		}
	}
	else {
		if (smartscroll) {
			gc.copyArea(rect.x + Math.max(delta, 0), rect.y,
						width, rect.height, -delta, 0);
			comp.repaint(rect.x + (delta > 0 ? width : 0), rect.y,
						 abs_delta, rect.height);
		}
		else {
			// force the whole thing to repaint
			comp.repaint(rect.x, rect.y, rect.width, rect.height);
		}
	}
	gc.dispose();
}

/** Scrolls a scrollableInterface component vertically. */
protected void scrollVertical(JCScrollableInterface scrollable,
							  JCAdjustmentEvent ev, int value) {
	int delta = value - scrollable.getVertOrigin(), abs_delta = Math.abs(delta);
	Component comp = (Component) scrollable;

    // Adjust origin
	scrollable.setVertOrigin(value);

	if (comp instanceof JCComponent)
		((JCComponent)comp).getDrawingArea(rect);
	else
		rect.resize(comp.size().width, comp.size().height);

	// Scrolled more than one screen
	if (abs_delta >= rect.height) {
		comp.repaint();
		return;
	}

	int height = rect.height - abs_delta;

	JCComponent jc_comp = (comp instanceof JCComponent) ?
		(JCComponent) comp : null;

	// Copy the current screen contents and repaint the exposed area
	Graphics gc = comp.getGraphics();
	if (jc_comp != null) {
		if (smartscroll) {
			jc_comp.copyArea(gc, rect.x, rect.y + Math.max(delta, 0),
						 rect.width, height, 0, -delta);
			jc_comp.paintImmediately(rect.x, rect.y + (delta > 0 ? height : 0),
								 rect.width, abs_delta);
		}
		else {
			// force the whole thing to repaint
			jc_comp.paintImmediately(rect.x, rect.y,
									 rect.width, rect.height);
		}
	}
	else {
		if (smartscroll) {
			gc.copyArea(rect.x, rect.y + Math.max(delta, 0), rect.width, height,
					0, -delta);
			comp.repaint(rect.x, rect.y + (delta > 0 ? height : 0),
					 rect.width, abs_delta);
		}
		else {
			// force the whole thing to repaint
			comp.repaint(rect.x, rect.y, rect.width, rect.height);
		}
	}

	gc.dispose();
}

/** Scrolls a scrollableInterface component horizontally and vertically. */
protected void setScrollPosition(JCScrollableInterface scrollable, int x, int y) {
    int delta_x = x - scrollable.getHorizOrigin();
    int abs_delta_x = Math.abs(delta_x);
    int delta_y = y - scrollable.getVertOrigin();
    int abs_delta_y = Math.abs(delta_y);

    // Adjust origin
    scrollable.setHorizOrigin(x);
    scrollable.setVertOrigin(y);

    Component comp = (Component) scrollable;
    if (comp instanceof JCComponent)
		((JCComponent)comp).getDrawingArea(rect);
	else
		rect.resize(comp.size().width, comp.size().height);

    // Scrolled more than one screen
    if (abs_delta_x >= rect.width || abs_delta_y > rect.height) {
		comp.repaint();
		return;
    }

    int width = rect.width - abs_delta_x;
    int height = rect.height - abs_delta_y;

	JCComponent jc_comp = (comp instanceof JCComponent) ?
		(JCComponent) comp : null;

    // Copy the current screen contents and repaint the exposed area
    Graphics gc = comp.getGraphics();
	if (jc_comp != null) {
		jc_comp.copyArea(gc, rect.x + Math.max(delta_x, 0),
						 rect.y + Math.max(delta_y, 0),
						 width, height, -delta_x, -delta_y);
		jc_comp.paintImmediately(
						(delta_x >= 0 && delta_y >= 0 ? rect.x + width : rect.x),
						rect.y,
						(delta_y < 0 ? rect.width : abs_delta_x),
						(delta_y < 0 ? abs_delta_y : height));
		jc_comp.paintImmediately(
						(delta_x >= 0 && delta_y < 0 ? rect.x + width : rect.x),
						(delta_y < 0 ? rect.y + abs_delta_y : rect.y + height),
						(delta_y < 0 ? abs_delta_x : rect.width),
						(delta_y < 0 ? height : abs_delta_y));
	}
	else {
		gc.copyArea(rect.x + Math.max(delta_x, 0),
					rect.y + Math.max(delta_y, 0),
					width, height, -delta_x, -delta_y);
		comp.repaint((delta_x >= 0 && delta_y >= 0 ? rect.x + width : rect.x),
					 rect.y,
					 (delta_y < 0 ? rect.width : abs_delta_x),
					 (delta_y < 0 ? abs_delta_y : height));
		comp.repaint((delta_x >= 0 && delta_y < 0 ? rect.x + width : rect.x),
					 (delta_y < 0 ? rect.y + abs_delta_y : rect.y + height),
					 (delta_y < 0 ? abs_delta_x : rect.width),
					 (delta_y < 0 ? height : abs_delta_y));
	}
    gc.dispose();
}

/** Scrolls the window vertically to the specified value.
 * Subclasses may override this method to provide custom behavior.
 */
protected void scrollVertical(JCAdjustmentEvent ev, int value) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).scrollVertical(value);
	else if (viewport instanceof JCScrollableInterface)
		scrollVertical((JCScrollableInterface)viewport, ev, value);
}

/** Scrolls the window horizontally to the specified value.
 * Subclasses may override this method to provide custom behavior.
 */
protected void scrollHorizontal(JCAdjustmentEvent ev, int value) {
	if (viewport instanceof Viewport)
		((Viewport)viewport).scrollHorizontal(value);
	else if (viewport instanceof JCScrollableInterface)
		scrollHorizontal((JCScrollableInterface)viewport, ev, value);
}

/** Scrolls the window vertically to the specified value. */
public void scrollVertical(int v) {
	vert_sb.setValue(v);
	scrollVertical(null, vert_sb.getValue());
}

/** Scrolls the window horizontally to the specified value. */
public void scrollHorizontal(int v) {
	horiz_sb.setValue(v);
	scrollHorizontal(null, horiz_sb.getValue());
}

/**
 * Scrolls vertically and horizontally to the specified position.
 * Specifying a position outside of the legal scrolling bounds
 * of the child will scroll to the closest legal position.
 */
public void setScrollPosition(int x, int y) {
	horiz_sb.setValue(x);
	vert_sb.setValue(y);
	if (viewport instanceof Viewport) {
		((Viewport)viewport).scrollHorizontal(x);
		((Viewport)viewport).scrollVertical(y);
	}
	else if (viewport instanceof JCScrollableInterface) {
		setScrollPosition((JCScrollableInterface)viewport,
						  horiz_sb.getValue(), vert_sb.getValue());
	}
}


/* JDK110_START */
public void keyPressed(KeyEvent ev) {
	switch (ev.getKeyCode()) {
	case KeyEvent.VK_PAGE_UP:
		keystroke = Event.PGUP;
		scrollVertical(vert_sb.getValue() - vert_sb.getBlockIncrement());
		break;
	case KeyEvent.VK_PAGE_DOWN:
		keystroke = Event.PGDN;
		scrollVertical(vert_sb.getValue() + vert_sb.getBlockIncrement());
		break;
	case KeyEvent.VK_HOME:
		keystroke = Event.HOME;
		scrollVertical(0);
		break;
	case KeyEvent.VK_END:
		keystroke = Event.END;
		scrollVertical(vert_sb.getMaximum());
		break;
	case KeyEvent.VK_LEFT:
		keystroke = Event.LEFT;
		scrollHorizontal(horiz_sb.getValue() - horiz_sb.getUnitIncrement());
		break;
	case KeyEvent.VK_RIGHT:
		keystroke = Event.RIGHT;
		scrollHorizontal(horiz_sb.getValue() + horiz_sb.getUnitIncrement());
		break;
	}
	keystroke = 0;
}

public void keyReleased(KeyEvent ev) {}
public void keyTyped(KeyEvent ev) {}
/* JDK110_END */

/**
 * Sends the event to one the scrollbars if the
 * HOME, END, PGUP, PGDN or LEFT or RIGHT keys are hit.
 */
/* JDK102_START
public boolean keyDown(Event ev, int key) {
    keystroke = key;
	boolean s = false;
	if (key == Event.PGUP || key == Event.PGDN
		|| key == Event.HOME || key == Event.END)
		s = vert_sb.postEvent(ev);
	else if (key == Event.LEFT || key == Event.RIGHT)
	    s = horiz_sb.postEvent(ev);
    keystroke = 0;
	return s;
}
 JDK102_END */

/** Processes scrollbar events. */
public void adjustmentValueChanged(JCAdjustmentEvent ev) {
	if (ev.getSource() == vert_sb)
		scrollVertical(ev, vert_sb.getValue());
	else if (ev.getSource() == horiz_sb)
		scrollHorizontal(ev, horiz_sb.getValue());
}

/**
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

}
