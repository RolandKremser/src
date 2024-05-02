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
// RCSID -- $RCSfile: TabPanel.java $ $Revision: 2.22 $ $Date: 2000/11/09 20:11:51 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

/** A private container which manages a JCTabManager's tab buttons.
 * @see JCTabManager
 * @see JCTabButton
 */
public class TabPanel extends JCContainer {

JCTabManager tab_manager;
TabArrowPanel arrow_panel;
int tab_height = 0, tab_width = 0;
int pref_width = 0;		// preferred width;
JCVector buttons = new JCVector(0);		// Children in relative z-order

private static final String base = "tabpanel";
private static int nameCounter = 0;

boolean allTabsShown = false;

/* SWING12_START */
private static javax.swing.JLabel label_workaround = new javax.swing.JLabel();
/* SWING12_END */

/** Creates an empty container with the specified parent. */
public TabPanel(JCTabManager tab_manager) {
	super();
	setName(base + nameCounter++);
	this.tab_manager = tab_manager;
	double_buffer = true;
	setLayout(null);
	// Create and add arrow panel here because MS VM does not
	// like adding a component to a container in container's
	// layout method.
	arrow_panel = new TabArrowPanel(this);
	add(arrow_panel);
	arrow_panel.hide();
	/* JDK110_START */
	enableEvents(AWTEvent.KEY_EVENT_MASK);
	/* JDK110_END */
}

/**
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

/** Sets the tab's shape: BWTEnum.RECTANGLE (default), or SLANTED.
 */
synchronized void setTabShape(int v) {
	for (int i=0; i < countComponents(); i++) {
		if (getComponents()[i] instanceof JCTabButton)
			((JCTabButton)getComponents()[i]).setShape(v);
	}
	if (isDisplayable()) {
		Dimension temp = JCComponent.getPreferredSize(this);
		layout();
		repaint();
		tab_manager.page_area.drawBorder(tab_manager.page_area.getGraphics());
	}
}

/** Gets the number of tab children. */
public int countTabs() {
	int count = 0;
	Component[] child = getComponents();
	for (int i=0; i < countComponents(); i++)
		if (child[i] instanceof JCTabButton) count++;
	return count;
}

/** Gets a list of all the tabs.
 * @see JCTabButton#setPage
 * @see jclass.bwt.JCTabManager#addPage
 */
public JCTabButton[] getTabs() {
	Component[] child = getComponents();
	JCTabButton[] tabs = new JCTabButton[countTabs()];
	for (int i=0, k=0; i < child.length; i++)
		if (child[i] instanceof JCTabButton) tabs[k++] = (JCTabButton)child[i];
	return tabs;
}

/** Finds the largest tab based on the tab manager's CurrentTabFont value
 * (if it is larger than the tab's font).
 * @see JCTabManager#setCurrentTabFont
 */
private void findLargestTab() {
	Component[] child = getComponents();
	tab_height = tab_width = 0;

	for (int i=0; i < child.length; i++) {
		if (!(child[i] instanceof JCTabButton)) continue;
		Dimension size = getTabSize((JCTabButton) child[i]);
		tab_height = Math.max(tab_height, size.height);
		if (tab_manager.tab_resize)
			tab_width = Math.max(tab_width, size.width);
	}
}

/** Get the preferred size of a tab button based on the manager's
 * CurrentTabFont value (if it is larger than the tab's font).
 */
Dimension getTabSize(JCTabButton btn) {
	Font current_font = JCTabButton.getCurrentTabFont(tab_manager);
	FontMetrics current_fm = getToolkit().getFontMetrics(current_font);
	Font f = btn.getFont();
	FontMetrics fm = btn.getToolkit().getFontMetrics(f);

	if (current_fm.getHeight() > fm.getHeight()
			|| current_fm.charWidth('W') > fm.charWidth('W'))
		f = current_font;
	btn.setLabelSize(btn.getLabel(), f);

	Dimension pref = JCComponent.getPreferredSize(btn);
	if (f == current_font)
		btn.setLabelSize(btn.getLabel(), btn.getFont());

	// Allow for tab's resize when it is made current
	pref.width += 4; pref.height += 4;
	return pref;
}

int getTabSpacing() {
	int spacing = tab_manager.tab_spacing;
	if (spacing == BWTEnum.DEFAULT) {
		if (tab_manager.getTabShape() == BWTEnum.RECTANGLE)
			spacing = 0;
		else if (tab_manager.getTabShape() == BWTEnum.SLANTED) {
			if (countTabs() > 0) {
				spacing = -JCComponent.getPreferredSize(
										getComponents()[0]).height/2;
			}
			else {
				spacing = 0;
			}
		}
	}
	return spacing;
}

/** Lays out the tabs according to the tab manager's tabSpacing, tabWidth
 * and tabStretch values.
 */
public void layout() {
	Component[] child = getComponents();
	JCTabButton cur_btn = null;
	int x = 0, h = 0;
	int arrow_panel_x = 0;
	int spacing = 0;
	boolean hide_tabs = false;
	boolean last_tab = false;

	synchronized (this) {

		if (!isDisplayable() || countTabs() == 0 || size().height == 0)
			return;

		// Find the largest tab
		findLargestTab();
		spacing = getTabSpacing();

		// If specified, stretch tabs if necessary to fill the entire side
		if (tab_manager.tab_stretch && (tab_width + spacing) * countTabs()
			< size().width)
			tab_width = size().width / countTabs() -
				spacing * Math.max(0, countTabs() - 1);

		// Allow for current tab resizing
		if (tab_width > 1)
			tab_width -= 1;
	}

	int first = tab_manager.getFirstVisibleTab();

	synchronized (this) {
		// Position all tabs
		x = 0;
		h = size().height;
		tab_manager.last_tab = first;
		arrow_panel_x = size().width;
		if (pref_width > size().width)
			arrow_panel_x -= TabArrowPanel.WIDTH;
	}

	int tab_count = 0;
	for (int i=0; i < child.length; i++) {
		if (!(child[i] instanceof JCTabButton)) continue;

		tab_count += 1;
		last_tab = true;

		// Hide tabs which have been scrolled off
		if (tab_count <= first) {
			if (child[i].isShowing()) {
				child[i].hide();
			}
			continue;
		}

		JCTabButton btn = (JCTabButton) child[i];
		int w = tab_width > 0 ? tab_width-1 : getTabSize(btn).width;
		if (x == 0) x = btn.getBorderThickness();
		int y = 0;
		if (tab_manager.getTabSide() == BWTEnum.TOP)
			y = btn.getBorderThickness();
		if (!btn.current_tab) {
			JCComponent.setBounds(btn, x, y, w, h - btn.getBorderThickness());
		}
		else {
			JCComponent.setBounds(btn, x - btn.getBorderThickness(),
						0, w + 2*btn.getBorderThickness(), h);
			cur_btn = btn;
		}

		// hide all tabs right of rightmost visible tab
		if (hide_tabs) {
			child[i].hide();
			continue;
		}

		btn.show();
		x += w + spacing;

		if (btn.location().x + btn.size().width < arrow_panel_x) {
			// tab count includes the first tab so we must subtract 1
			tab_manager.last_tab = tab_count - 1;
			last_tab = false;
		}

		if (last_tab) {
			hide_tabs = true;
		}
	}

	// make sure that the current button is in front
	if (cur_btn != null) {
		cur_btn.toFront();
	}

	// Display arrow buttons if necessary
	if (tab_manager.first_tab == 0 && pref_width <= size().width) {
		if (arrow_panel != null) {
			remove(arrow_panel);
		}
	}
	else {
		if (arrow_panel == null)
			arrow_panel = new TabArrowPanel(this);
		add(arrow_panel);
		arrow_panel.show();
		arrow_panel.layout();
	}
	if (tab_manager.getFirstVisibleTab() > 0 || tab_manager.last_tab < countTabs() - 1) {
		allTabsShown = false;
	} else {
		allTabsShown = true;
	}
}

/** Returns true if all tabs are visible. */
boolean allTabsVisible() {
	//return arrow_panel == null || !arrow_panel.isVisible();
	return allTabsShown;
}

/** Calculates the size based on the tab button's sizes. */
/* AWT_START 
public Dimension preferredSize() {
 AWT_END */
/* SWING_START */
public Dimension getPreferredSize() {
/* SWING_END */

	findLargestTab();
	int spacing = getTabSpacing();

	pref_width = 0;
	if (tab_width == 0) {
		Component[] child = getComponents();
		for (int i=0; i < child.length; i++)
			if (child[i] instanceof JCTabButton)
				pref_width += getTabSize((JCTabButton)child[i]).width +
					          spacing;
	}
	else
		pref_width =
			tab_width * countTabs() + spacing * Math.max(0, countTabs() - 1);
	return new Dimension(pref_width, tab_height);
}

void addButton(JCTabButton btn) {
	if (!buttons.contains(btn))
		buttons.addElement(btn);
}

private void removeButton(JCTabButton btn) {
	if (buttons.contains(btn))
		buttons.removeElement(btn);
}

/**
 * Adds the specified component to this container at the given position.
 * @param pos the position at which to insert the component. -1
 * means insert at the end.
 */
public Component add(Component comp, int pos) {
	if (comp instanceof JCTabButton)
		addButton((JCTabButton)comp);
	return super.add(comp, pos);
}

/**
 * Removes the specified component from this container.
 * @see #add
 */
public void remove(Component comp) {
	boolean gadget = false;

	synchronized (this) {
		if (comp.getParent() != this) return;
		super.remove(comp);
		if (!(comp instanceof JCTabButton)) return;
		JCTabButton btn = (JCTabButton) comp;
		gadget = (comp.isDisplayable());

		if (comp.isDisplayable())
			comp.removeNotify();
		if (buttons.contains(comp))
			buttons.removeElement(comp);

		btn.tab_panel = null;
		btn.tab_manager = null;
		btn.page = null;
	}
	// Hide the component if it is a gadget
	if (gadget) {
		Rectangle r = comp.bounds();
		repaint(r.x, r.y, r.width, r.height);
	}
	layout();
}

void toFront(Component comp) {
	if (comp.getParent() == this && comp instanceof JCTabButton) {
		removeButton((JCTabButton)comp);
		addButton((JCTabButton)comp);
	}
}

/**
 * Locates the component that contains the position.
 * @return null if the container does not contain the position;
 * returns the component otherwise.
 */
public Component locate(int x, int y) {
	if (!inside(x, y))
	    return null;
	for (int i = buttons.size()-1; i >= 0; i--) {
	    JCTabButton btn = (JCTabButton) buttons.elementAt(i);
	    if (btn != null && btn.isVisible()
			&& btn.inside(x-btn.location().x, y-btn.location().y)) {
			return btn;
		}
	}
	return this;
}

/** Restores the stacking order of the container's children to the order
 * in which they were added.
 */
void restoreStackingOrder() {
	if (buttons.size() == 0) return;
	Component[] child = getComponents();
	JCVector new_buttons = new JCVector(buttons.size());
	boolean needs_repaint = false;
	if (child.length != buttons.size())
		needs_repaint = true;
	for (int i=0; i < child.length; i++) {
		if (!needs_repaint && child[i] != buttons.elementAt(i))
			needs_repaint = true;
		if (buttons.contains(child[i]))
			new_buttons.addElement(child[i]);
	}
	if (needs_repaint) {
		buttons = new_buttons;
		repaint();
	}
}

/** Overrides Component.repaint(), which does not always call paint(). */
public void repaint() {
	repaint(0, 0, size().width, size().height);
}

/** Paints a child. */
private void paintChild(Graphics gc, JCTabButton child) {
	Point p = BWTUtil.translateToParent(this, child, 0, 0);
	Dimension size = child.size();
	if (!getPaintRect().intersects(new Rectangle(p, size)))
		return;

	child.setDoubleBuffer(false);
	Point loc = child.location();
	Graphics new_gc = gc.create(loc.x, loc.y, size.width, size.height);
	child.paint(new_gc);
	new_gc.dispose();
	child.setDoubleBuffer(true);
}

/** Paints all buttons. */
public void paintInterior(Graphics gc) {
	Rectangle rect = getPaintRect();

	gc.setColor(getBackground());
	for (int i=0; i < buttons.size(); i++) {
		JCTabButton button = (JCTabButton) buttons.elementAt(i);
		if (button.isShowing())
			paintChild(gc, button);
	}
}

/**
 * Overides the getComponent method to return null when the component is
 * an instance of a TabButton. This method is called by paintChildren in
 * the JComponent class.
 */
/* SWING_START */
public Component getComponent(int n) {

	// We want to control Tab Button drawing through our paintInterior
	// method.  However paintChildren in JComponent tries to paint the
	// tab buttons so we must override the getComponent to return null
	// whenever paintChildren tries to paint a TabButton.
	Component comp = super.getComponent(n);
	if (comp instanceof JCTabButton) {
/* SWING_END */
/* SWING11_START
       return null;
 SWING11_END */
/* SWING12_START */
		// Cannot return null for SWING12, as it causes and exception within
		// rectangeIsObscured.  We use a JLabel which is not added to
        // any container. This produces the same result as returning null
		return label_workaround;
/* SWING12_END */
/* SWING_START */
	}
	return comp;
}
/* SWING_END */


Component focus_target;

/** Calls the component's postEvent() */
private void sendEvent(Component comp, Event ev) {
	ev.target = comp;
	int old_x = ev.x, old_y = ev.y;
	Point p = BWTUtil.translateFromParent(this, comp, 0, 0);
	ev.translate(p.x, p.y);
	comp.postEvent(ev);
	ev.x = old_x; ev.y = old_y;
}

boolean dispatchEvent(Component comp, Event ev, int id) {
	if (comp == null) return false;
	ev.target = comp;
	int old_id = ev.id;
	ev.id = id;
	sendEvent(comp, ev);
	ev.id = old_id;
	return true;
}

public boolean mouseDown(Event ev, int x, int y) {
	if (ev.target != this) return true;
	Component comp = locate(ev.x, ev.y);
	if (comp != null && !comp.isEnabled()) comp = null;
	if (comp == null || comp == this) return true;
	dispatchEvent(comp, ev, ev.id);
	if (comp instanceof JCComponent
		&& ((JCComponent)comp).isTraversable()) {
		if (comp != focus_target) {
			if (focus_target != null)
				dispatchEvent(focus_target, ev, Event.LOST_FOCUS);
			focus_target = comp;
			dispatchEvent(comp, ev, Event.GOT_FOCUS);
		}
	}

	return true;
}

/* JDK110_START */
protected void processKeyEvent(KeyEvent key_ev) {
	int id = key_ev.getID();

	switch(id) {
	case KeyEvent.KEY_PRESSED:
		if (key_ev.isActionKey())
			id = Event.KEY_ACTION;
		int keyCode = key_ev.getKeyCode();
		if (keyCode == KeyEvent.VK_SHIFT ||
			keyCode == KeyEvent.VK_CONTROL ||
			keyCode == KeyEvent.VK_ALT) {
			return;  // suppress modifier keys in old event model
		}
		Event ev = jclass.base.TransientComponent.convertEvent(this, key_ev);
		keyDown(ev, ev.key);
	}
	super.processKeyEvent(key_ev);
}
/* JDK110_END */

public boolean keyDown(Event ev, int key) {
	if (ev.target != this) return false;
	boolean s = dispatchEvent(focus_target, ev, ev.id);
	if (!s) s = super.keyDown(ev, key);
	return s;
}

public boolean keyUp(Event ev, int key) {
	if (ev.target != this) return false;
	boolean s = dispatchEvent(focus_target, ev, ev.id);
	if (!s) s = super.keyUp(ev, key);
	return s;
}

}
