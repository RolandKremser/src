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
// RCSID -- $RCSfile: JCTabButton.java $ $Revision: 2.11 $ $Date: 2000/11/09 20:11:10 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.applet.*;
import java.awt.*;
//import java.awt.peer.ComponentPeer;

/** A private class for use by a <a href=jclass.bwt.JCTabManager.html>JCTabManager</a> for its tabs.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=jclass.bwt.JCLabel.html#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Label               </td><td><a href=jclass.bwt.JCLabel.html#setLabel>setLabel</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=jclass.bwt.JCTabManager.html#addTabManagerListener>addTabManagerListener</a></td> <td>Posted when the button is pressed and then released</td></tr>
 * </table>
 */
public class JCTabButton extends JCButton {

Component 		page;
JCTabManager 	tab_manager;
TabPanel		tab_panel;
int				shape;
boolean			size_adjusted = false;

/** Button's shape (may be non-rectangular). */
protected Polygon polygon;

/** Indicates if this is the tab manager's current tab. True if it is. False if it isn't. */
protected boolean	current_tab = false;

private static final String base = "tabbutton";
private static int nameCounter = 0;

/** Creates an empty button. */
public JCTabButton(JCTabManager tab_manager) {
	this(tab_manager, null, null);
}

/** Creates a button which reads parameters from the applet's HTML file.
 * @param tab_manager parent JCTabManager
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCTabButton(JCTabManager tab_manager, Applet applet, String name) {
	super(null, applet, name);
	if (name == null) 
		setName(base + nameCounter++);
	this.tab_manager = tab_manager;
	traversable = true;
	highlight = arm_offset = 0;
	alignment = BWTEnum.NOVALUE;
	shape = tab_manager.getTabShape();
	if (getClass().getName().equals("jclass.bwt.JCTabButton"))
		 getParameters(applet);
	insets = new Insets(-1,5,-1,5);
}

/** Sets the tab's shape: BWTEnum.RECTANGLE (default), or SLANTED.
 */
void setShape(int v) {
	setShape(v, size().width, size().height);
}

public void toFront() {
	if (getParent() instanceof TabPanel) {
		((TabPanel)getParent()).toFront(this);
		repaint();
	}
}

void setShape(int v, int w, int h) {
	shape = v;
	polygon = new Polygon();
	switch (shape) {
	case BWTEnum.RECTANGLE:
		switch (tab_manager.getTabSide()) {
		case BWTEnum.TOP:
			polygon.addPoint(0, h);
			polygon.addPoint(0, 0);
			polygon.addPoint(w, 0);
			polygon.addPoint(w, h);
			break;
		case BWTEnum.BOTTOM:
			polygon.addPoint(0, 0);
			polygon.addPoint(0, h);
			polygon.addPoint(w, h);
			polygon.addPoint(w, 0);
			break;
		}
		break;
	case BWTEnum.SLANTED:
		switch (tab_manager.getTabSide()) {
		case BWTEnum.TOP:
			polygon.addPoint(0, h);
			polygon.addPoint(h/2, 0);
			polygon.addPoint(w-h/2, 0);
			polygon.addPoint(w, h);
			break;
		case BWTEnum.BOTTOM:
			polygon.addPoint(0, 0);
			polygon.addPoint(h/2, h);
			polygon.addPoint(w-h/2, h);
			polygon.addPoint(w, 0);
			break;
		}
		break;
	}
}

/** Returns true if the button is enabled and the point is inside the tab's perimeter. */
public boolean inside(int x, int y) { 
	return isEnabled() && polygon != null && polygon.inside(x, y); 
}

/** Gets the page for this tab.
 * @see #setPage
 */
public Component getPage() { return page; }

/** Sets the page for this tab. 
 * The page will be added to the tabManager if it is not a child.
 */
public void setPage(Component page) { 
	this.page = page; 
	if (tab_manager != null && tab_manager.page_area != null)
		tab_manager.page_area.addPage(page);
	if (tab_manager.getTabColorPolicy() == BWTEnum.COLOR_PAGE
		&& page != null && page.getBackground() != null)
		setBackground(page.getBackground());
}

/** Lays out the label using the tabManager's alignment if no value has been set. */
public synchronized void layout() {
	if (!isDisplayable()) return;
	int old_alignment = alignment;
	if (alignment == BWTEnum.NOVALUE)
		alignment = tab_manager.getAlignment();
	super.layout();
	alignment = old_alignment;
}

/** Get or calculate the tabManager's current tab font. */
static Font getCurrentTabFont(JCTabManager mgr) {
	if (mgr.tab_current_font != null)
		return mgr.tab_current_font;
	Font font = mgr.getFont();
	return new Font(font.getName(), Font.BOLD, font.getSize());
}

boolean valid = false;
boolean visible = true;

/**
 * Checks if this Component is valid. Components are invalidated when
 * they are first shown on the screen.
 * @see #validate
 * @see #invalidate
 */
public boolean isValid() {
	return (isDisplayable() && valid);
}

/**
 * Checks if this Component is showing on screen. This means that the 
 * component must be visible, and it must be in a container that is 
 * visible and showing.
 * @see #show
 * @see #hide
 */
public boolean isShowing() {
	if (visible && isDisplayable()) 
	    return getParent() == null || getParent().isShowing();
	return false;
}

/**
 * Shows the component. 
 * @see #isVisible
 * @see #hide
 */
public void show() {
	super.show();
	if (tab_panel == null || visible) return;
	visible = true;
	repaint();
}

/**
 * Hides the component.
 * @see #isVisible
 * @see #hide
 */
public void hide() {
	super.hide();
	if (tab_panel == null || !visible) return;
	visible = false;
	Rectangle r = bounds();
	tab_panel.repaint(r.x, r.y, r.width, r.height);
}

/**
 * Gets the peer of the component.
 */
public boolean isDisplayable() {
	return tab_panel != null ? tab_panel.isDisplayable() : super.isDisplayable();
}

/** 
 * Validates a component.  
 */
public void validate() {
	if (!valid && isDisplayable()) {
	    layout();
	    valid = true;
	}
}

/** 
 * Invalidates the component.  The component and all parents
 * above it are marked as needing to be laid out.
 */
public void invalidate() {
	valid = false;
	if (getParent() != null && getParent().isValid()) 
		getParent().invalidate();
}

/**
 * Checks if this Component is visible. Components are initially visible 
 * (with the exception of top level components such as Frame).
 * @see #show
 * @see #hide
 */
public boolean isVisible() { return visible; }

/** If the tab manager's TabColorPolicy is BWTEnum.COLOR_PAGE,
 * sets the tab's color to the page's color.
 */
public void addNotify() {
	if (getParent() instanceof TabPanel) {
		tab_panel = (TabPanel) getParent();
		tab_panel.addButton(this);
		if (getFont() == null) setFont(tab_panel.getFont());
		if (getBackground() == null) setBackground(tab_panel.getBackground());
		if (getForeground() == null) setForeground(tab_panel.getForeground());
		valid = false;
	}

	if (applet == null)
		applet = BWTUtil.getApplet(this);
	applet_context = getAppletContext();

	if (tab_manager.getTabColorPolicy() == BWTEnum.COLOR_PAGE
		&& page != null && page.getBackground() != null)
		setBackground(page.getBackground());
}

/** If this tab is slanted, allow for the slant. */
protected int preferredWidth() {
	int w = super.preferredWidth();
	if (shape == BWTEnum.SLANTED)
		w += preferredHeight();
	return w;
}

/** Fills the polygon with the background color. */	
protected void fillBackground(Graphics gc) {
	gc.fillPolygon(polygon);
}

/** Draws the button's label. */
protected synchronized void paintComponent(Graphics gc) {
	if (tab_manager.getTab(tab_manager.getCurrentTab()) == this)
		gc.setFont(getCurrentTabFont(tab_manager));
	else
		gc.setFont(getFont());

	int align = (alignment == BWTEnum.NOVALUE)
		 ? tab_manager.getAlignment() : alignment;
	super.paintComponent(gc);
}

/** Paints the component */
/* SWING_START */
public void paint(Graphics g) {
	// clip the gc to its ancestors
	clipGCToAncestors(g);
	super.paint(g);
}
/* SWING_END */

/** Draws the shadow. */
protected void drawBorder(Graphics gc) {
	if (shape == BWTEnum.RECTANGLE) 
		drawRectangleBorder(gc);
	else if (shape == BWTEnum.SLANTED)
		drawSlantedBorder(gc);
}
	
public Graphics getGraphics() {
	if (draw_gc != null) return draw_gc.create();
	Graphics g = getParent().getGraphics();
	if (g == null) return null;
	Rectangle b = bounds();
	Point p = BWTUtil.translateToParent(getParent(), this, 0, 0);
	Graphics g2 = g.create(p.x, p.y, b.width, b.height);
	g.dispose();
	return g2;
}

/** Draws or clears the highlight rectangle (called by paint).
 * @param on if true, draws the highlight rectangle and a dashed rectangle
 * around the label; otherwise clears the rect
 */
protected void drawHighlight(Graphics gc, boolean on) {}

/** Draws a rectangular shadow. */
protected void drawRectangleBorder(Graphics gc) {
	drawEdgeBorder(gc);
	gc.setColor(getParent().getBackground());

	int[] x = polygon.xpoints, y = polygon.ypoints;

	if (tab_manager.getTabSide() == BWTEnum.TOP) {
		gc.drawRect(x[1], y[1], 1, 1);	 				// Top-left point
		gc.drawRect(x[2]-2, y[2], 1, 1);	 			// Top-right point
		gc.setColor(BWTUtil.brighter(getBackground()));
		gc.drawLine(x[1]+1, y[1]+1, x[1]+1, y[1]+1);	// Top-left point
		gc.setColor(Color.black);
		gc.drawLine(x[2]-2, y[2]+1, x[2]-2, y[2]+1);	// Top-right point
	}
	else {
		gc.drawRect(x[1], y[1]-2, 1, 1);	 			// Top-left point
		gc.drawRect(x[2]-2, y[2]-2, 1, 1);	 			// Top-right point
		gc.setColor(BWTUtil.brighter(getBackground()));
		gc.drawLine(x[1]+1, y[1]-2, x[1]+1, y[1]-2);	// Top-left point
		gc.setColor(Color.black);
		gc.drawLine(x[2]-2, y[2]-2, x[2]-2, y[2]-2);	// Top-right point
	}
}
	
/** Draws a slanted shadow. */
protected void drawSlantedBorder(Graphics gc) {
	drawEdgeBorder(gc);
}

/** Draws a shadow between points 0-1, 1-2, 2-3 */
private void drawEdgeBorder(Graphics gc) {
	int[] x = polygon.xpoints, y = polygon.ypoints;

	// Left and top/bottom lines
	gc.setColor(BWTUtil.brighter(getBackground()));

	if (tab_manager.getTabSide() == BWTEnum.TOP) {
		gc.drawLine(x[0], y[0], x[1], y[1]);
		gc.drawLine(x[1], y[1], x[2]-1, y[2]);
	}
	else if (tab_manager.getTabSide() == BWTEnum.BOTTOM) {
		gc.drawLine(x[0], y[0], x[1], y[1]-1);
		gc.drawLine(x[1], y[1]-1, x[2]-1, y[2]-1);
	}

	// Right line
	gc.setColor(Color.black);
	if (tab_manager.getTabSide() == BWTEnum.TOP)
		gc.drawLine(x[2]-1, y[2], x[3]-1, y[3]-1);
	else if (tab_manager.getTabSide() == BWTEnum.BOTTOM) {
		if (shape == BWTEnum.SLANTED) 
			gc.drawLine(x[2], y[2]-1, x[3], y[3]-1);
		else
			gc.drawLine(x[2]-1, y[2]-3, x[3]-1, y[3]-2);
	}
}

/** Reshapes the internal polygon. */
/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */

	if (width < 0 || height < 0) return;

	boolean resized = false;
	
	synchronized (this) {
		setShape(shape, width, height);
		resized = (size().width != width || size().height != height);
		if (!resized && location().x == x && location().y == y) return;
		if (!isDisplayable() || tab_panel == null) {

/* AWT_START 
			super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
			super.setBounds(x, y, width, height);
/* SWING_END */

			return;
		}
	}

	Rectangle old_bounds = bounds();
	do_repaint = false;
	try {
/* AWT_START 
		super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
		super.setBounds(x, y, width, height);
/* SWING_END */
	} catch (Exception e) {
		do_repaint = true;
	}
	do_repaint = true;
	Rectangle new_bounds = bounds();

	if (resized) 
		invalidate();
	if (getParent().isValid()) 
		getParent().invalidate();

	// Repaint parent areas
	if (!new_bounds.intersects(old_bounds)) {
		tab_panel.repaint(old_bounds);
		repaint();
	}
	else {
		Rectangle union = new_bounds.union(old_bounds);
		tab_panel.repaint(union);
	}
}

/**
 * Repaints part of the component by calling paint directly.
 */
public void repaint(int x, int y, int width, int height) {
	if (!isDisplayable() || !isShowing() || width <= 0 || height <= 0
			|| in_repaint) return;

	int w = size().width, h = size().height;

	// Check that the area intersects the component
	if (w <= x || h <= y || x + width < 0 || y + height < 0) return;

	try {
		in_repaint = true;
		if (x+width > w) width = w - x;
		if (y+height > h) height = h - y;

		if (tab_panel != null) {
			Point p = BWTUtil.translateToParent(tab_panel, this, x, y);
			tab_panel.repaint(p.x, p.y, width, height);
		}
		else {
			Graphics gc = getGraphics();
			gc.clipRect(x, y, width, height);
			paint(gc);
			gc.dispose();
		}
	} catch (Exception e) {
		in_repaint = false;
	};
	in_repaint = false;
}

/** Resizes the button.
 * @param current whether this is the tabManager's current tab; if true,
 * the tab will grow by its shadow thickness; otherwise it will shrink.
 */
protected void resize(boolean current) {
	current_tab = current;
	if (!isDisplayable()) return;

	int y = 0;
	if (tab_manager.getTabSide() == BWTEnum.TOP)
		y = border;
	if (current && !size_adjusted) {
		size_adjusted = true;
		JCComponent.setBounds(this, location().x - border, 0,
				size().width + 2 * border, size().height + border);
	}
	else if (!current && size_adjusted) {
		size_adjusted = false;
		JCComponent.setBounds(this, location().x + border, y,
				size().width - 2 * border, size().height - border);
	}
	if (current)
		toFront();
}

/**
 * Makes the tab visible.
 * @see JCTabManager#makeTabVisible
 */
public boolean gotFocus(Event ev, Object what) {
	if (isFocusTraversable()) 
		tab_manager.makeTabVisible(tab_manager.getTab(this));
	if (isFocusTraversable() && tab_panel != null) {
		if (tab_panel.focus_target != this) 
			tab_panel.dispatchEvent(tab_panel.focus_target, ev, Event.LOST_FOCUS);
		tab_panel.focus_target = this;
	}
	return super.gotFocus(ev, what);
}

/** Sets tabManager's current tab to this button. */
public void armAction(Event ev) {
	tab_manager.setCurrentTab(tab_manager.getTab(this), true);
}

/** Overrides JCButton's method - no action. */
public void disarmAction(Event ev) {}

}
