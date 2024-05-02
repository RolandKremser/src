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
// RCSID -- $RCSfile: JCTabManager.java $ $Revision: 2.14 $ $Date: 2000/11/09 20:11:11 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import java.applet.*;
import java.awt.*;

/**
 * JCTabManager is a container which organizes its children into pages
 * to simulate a sequence of file folders.
 * A row of tab buttons allows users to select a page.
 * The page components are defined as children of the Tab Manager;
 * a property is then used to associated a page with a particular tab button.
 * Page components can be of any class (including any container).
 * Because AWT components must be rectangular, the tab buttons are not actual
 * buttons, but are simulated.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * CurrentTabFont      </td><td><a href=#setCurrentTabFont>setCurrentTabFont</a></td></tr><tr><td>
 * FirstVisibleTab     </td><td><a href=#setFirstVisibleTab>setFirstVisibleTab</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * TabColorPolicy      </td><td><a href=#setTabColorPolicy>setTabColorPolicy</a></td></tr><tr><td>
 * TabLabels           </td><td><a href=#setTabLabels>setTabLabels</a></td></tr><tr><td>
 * TabResize           </td><td><a href=#setTabResize>setTabResize</a></td></tr><tr><td>
 * TabShape            </td><td><a href=#setTabShape>setTabShape</a></td></tr><tr><td>
 * TabSide             </td><td><a href=#setTabSide>setTabSide</a></td></tr><tr><td>
 * TabSpacing          </td><td><a href=#setTabSpacing>setTabSpacing</a></td></tr><tr><td>
 * TabStretch          </td><td><a href=#setTabStretch>setTabStretch</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCTabManagerEvent    </td><td><a href=#addTabManagerListener>addTabManagerListener</a></td> <td>Posted when the page changes</td></tr>
 * </table>
 */
public class JCTabManager extends JCContainer {

public static final int TOPLEFT = BWTEnum.TOPLEFT;
public static final int LEFT = BWTEnum.LEFT;
public static final int TOPCENTER = BWTEnum.TOPCENTER;
public static final int CENTER = BWTEnum.CENTER;
public static final int TOPRIGHT = BWTEnum.TOPRIGHT;
public static final int RIGHT = BWTEnum.RIGHT;
public static final int MIDDLELEFT = BWTEnum.MIDDLELEFT;
public static final int MIDDLECENTER = BWTEnum.MIDDLECENTER;
public static final int MIDDLE = BWTEnum.MIDDLE;
public static final int MIDDLERIGHT = BWTEnum.MIDDLERIGHT;
public static final int BOTTOMLEFT = BWTEnum.BOTTOMLEFT;
public static final int BOTTOMCENTER = BWTEnum.BOTTOMCENTER;
public static final int BOTTOMRIGHT = BWTEnum.BOTTOMRIGHT;

public static final int COLOR_PAGE = BWTEnum.COLOR_PAGE;
public static final int COLOR_INHERIT = BWTEnum.COLOR_INHERIT;

public static final int RECTANGLE = BWTEnum.RECTANGLE;
public static final int SLANTED = BWTEnum.SLANTED;

public static final int TOP = BWTEnum.TOP;
public static final int BOTTOM = BWTEnum.BOTTOM;

Component	current_page;
int			current_tab = 0;
int			first_tab = 0;
int			last_tab = 0;		// Last fully visible tab
int 		tab_alignment = MIDDLECENTER;
int			tab_color_policy = COLOR_PAGE;
Font		tab_current_font;
boolean		tab_resize = true;
boolean		tab_stretch = false;
int			tab_side = TOP;
int			tab_spacing = BWTEnum.DEFAULT;
int			tab_shape = RECTANGLE;
boolean		show_page = true;
String[]	tab_labels = new String[0];

/** The container which holds the tab buttons. */
protected TabPanel		tab_panel;

/** The container which holds the pages. */
protected TabPageArea	page_area;

/** List of JCTabManagerEvent listeners */
protected JCVector listeners = new JCVector(0);
    
private static final String base = "tabmanager";
private static int nameCounter = 0;

/** Creates an empty TabManager. No parameters are read from an HTML file. */
public JCTabManager() {
	this(null, null);
}

/** Creates a TabManager which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCTabManager(Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	super.setLayout(new BorderLayout());
	tab_panel = new TabPanel(this);
	page_area = new TabPageArea(this);
	add("Center", page_area);
	setTabSide(TOP);

	if (getClass().getName().equals("jclass.bwt.JCTabManager"))
		 getParameters(applet);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	TabManagerConverter.getParams(this);
}

/**
 * Adds the specified JCTabManagerEvent listener to receive value change events.
 * @see JCTabManagerEvent
 */ 
public void addTabManagerListener(JCTabManagerListener l) {
	listeners.addUnique(l);
}

/**
 * Removes the specified listener so it no longer receives JCTabManagerEvents.
 * @see #addTabManagerListener
 */ 
public void removeTabManagerListener(JCTabManagerListener l) {
	listeners.removeElement(l);
}

/** Gets a list of all the pages.
 * @see JCTabButton#setPage
 * @see #addPage
 */
public Component[] getPages() { return page_area.getComponents(); }

/** Gets a list of all the tabs.
 * @see JCTabButton#setPage
 * @see #addPage
 */
public JCTabButton[] getTabs() { return tab_panel.getTabs(); }

/** Gets the position of the specified tab.
 * @return BWTEnum.NOTFOUND if the tab is not a child
 */
public int getTab(JCTabButton tab) {
	Component[] tabs = tab_panel.getComponents();
	for (int i=0, k=0; i < tabs.length; i++) {
		if (tabs[i] instanceof JCTabButton) {
		    if (tabs[i] == tab) {
			    return k;
			}
		    k++;
		}
	}
	return BWTEnum.NOTFOUND;
}

/** Gets the tab at the specified position.
 * @return null if the position is invalid
 */
public JCTabButton getTab(int pos) {
	Component[] tabs = tab_panel.getComponents();
	for (int i=0, k=0; i < tabs.length; i++) {
		if (tabs[i] instanceof JCTabButton) {
			if (pos == k) return (JCTabButton) tabs[i];
			k++;
		}
	}
	return null;
}

/** Gets the default tab alignment.
 * @see #setAlignment
 */
public int getAlignment() { return tab_alignment; }

/** Sets the default position of the tab's label within its shadows.
 * @param align one of TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, 
 * MIDDLECENTER (default), MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setAlignment(int align) {
	JCTabButton[] tabs = getTabs();

	synchronized (this) {
		LabelConverter.checkAlignment(align);
		tab_alignment = align;
		if (!isDisplayable()) return;
		for (int i=0; i < tabs.length; i++) {
			if (tabs[i].getAlignment() == BWTEnum.NOVALUE) {
				tabs[i].setAlignment(align);
				tabs[i].alignment = BWTEnum.NOVALUE;
			}
		}
	}
}

/** Gets the TabColorPolicy value.
 * @see #setTabColorPolicy
 */
public int getTabColorPolicy() { return tab_color_policy; }

/** Sets the policy for the tab's colors:<br>
 * <pre>
 * COLOR_PAGE        Sets the tab's color to the page's color (default)
 * COLOR_INHERIT     Sets the tab's color to the tab manager's color
 * </pre>
 * <strong>HTML param name/value:</strong> "TabColorPolicy"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setTabColorPolicy(int v) {
	synchronized (this) {
		TabManagerConverter.checkColorPolicy(v);
		tab_color_policy = v;
	}
	repaint();
}

/** Gets the TabSpacing value.
 * @see #setTabSpacing
 */
public int getTabSpacing() { return tab_spacing; }

/** Sets the spacing between tabs in pixels (default: 0).
 * When set to a negative value, the tabs overlap.<p>
 * <strong>HTML param name/value:</strong> "TabSpacing"/int
 */
public void setTabSpacing(int v) {
	tab_spacing = v;
	tab_panel.layout();
}

/** Gets the current tab.
 * @see #setCurrentTab
 */
public int getCurrentTab() { return current_tab; }

/**
 *	Sets the current tab.
 *	Most IDEs do not support visual interaction at design-time.  
 *	Use this property to change tab at design-time.
 *	@see #setCurrentTab
 */
public void setCurrentTab(int index) {
	setCurrentTab(index, true);
	makeTabVisible(index);
}

/** Sets the current tab, and posts a JCTabMangerEvent.
 * @param tab_pos the tab position
 * @param show_page if true, the tab's page is displayed - a blank page is
 * displayed if the tab is non-existent; if show_page is false, the current
 * page is not changed
 * @see #addTabManagerListener
 */
public void setCurrentTab(int tab_pos, boolean show_page) {
	JCTabButton current_btn = getTab(current_tab), 
				next_btn = getTab(tab_pos);
	if (next_btn == null) return;

	synchronized (this) {
		Component next_page = null;
		JCTabManagerEvent tab_ev = null;
		this.show_page = show_page;
		if (show_page)
			next_page = next_btn.page;

		if (show_page && listeners.size() > 0) {
			Event ev = new Event(this, 0, null);
			ev.when = System.currentTimeMillis();
			ev.target = this;
			ev.id = Event.ACTION_EVENT;

			tab_ev = new JCTabManagerEvent(ev, 
								current_page, current_btn, next_page, next_btn);
			for (int i=0; i < listeners.size(); i++) {
				((JCTabManagerListener) listeners.elementAt(i))
					.tabManagerChangeBegin(tab_ev);
				if (!tab_ev.doit)
					return;
				next_page = tab_ev.next_page;
				next_btn = getTab(tab_pos);
			}
		}

		tab_panel.restoreStackingOrder();

		if (current_btn != null && next_btn != current_btn)
			current_btn.resize(false);
		if (next_btn != null)
			next_btn.resize(true);
		current_tab = tab_pos;
		tab_panel.repaint();
		setCurrentPage(show_page ? next_page : current_page);

		if (show_page && listeners.size() > 0) {
			for (int i=0; i < listeners.size(); i++) 
				((JCTabManagerListener) listeners.elementAt(i))
					.tabManagerChangeEnd(tab_ev);
		}
	}
}


/** Gets the CurrentTabFont value.
 * @see #setCurrentTabFont
 */
public Font getCurrentTabFont() { return tab_current_font; }

/** Sets the current tab's font (default: bold version of default font).<P>
 * <strong>HTML param name/value:</strong> "CurrentTabFont"/Font<p>
 * @see jclass.util.JCConverter#toFont
 */
public synchronized void setCurrentTabFont(Font font) {
	tab_current_font = font;
	tab_panel.repaint();
}

/** Gets the current page.
 * @see #setCurrentPage
 */
public Component getCurrentPage() { return current_page; }

/** Sets the current page. The current tab is not changed - call setCurrentTab
 * to set a tab and its page as current.
 * @param page if null, a blank page is displayed
 * @see #setCurrentTab
 */
public synchronized void setCurrentPage(Component page) {
	current_page = page;
	page_area.showPage(page);
}

/** Gets the tab shape.
 * @see #setTabShape
 */
public int getTabShape() { return tab_shape; }

/** Sets the tab's shape: RECTANGLE (default), or SLANTED.<p>
 * <strong>HTML param name/value:</strong> "TabShape"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public synchronized void setTabShape(int v) {
	TabManagerConverter.checkTabShape(v);
	tab_shape = v;
	tab_panel.setTabShape(v);
}

/** Gets the tab labels as a list of Strings.
 * @see #setTabLabels
 */
public String[] getTabLabels() { return tab_labels; }

/** Sets the tab's labels.<p>
 * <strong>HTML param name/value:</strong> "TabLabels"/string list
 * @see #addPage
 */
public synchronized void setTabLabels(String[] v) {
	if (v == null) v = new String[0];
	for (int i=v.length; i < tab_labels.length; i++) 
		removeTab(i, true);
	tab_labels = v;

	for (int i=0; i < tab_labels.length; i++) {
		JCTabButton btn = getTab(i);
		if (btn == null) 
			setTabPage(i, tab_labels[i], null);
		else
			btn.setLabel(tab_labels[i]);
	}
	if (tab_labels.length == 0) 
		layout();
	else
		tab_panel.layout();
}

/** Gets the first visible tab.
 * @see #setFirstVisibleTab
 */
public int getFirstVisibleTab() { return first_tab; }

/** Sets the first tab currently visible.
 * Setting this value causes the tabs to scroll if necessary so that the
 * tab is fully visible.<p>
 * <strong>HTML param name/value:</strong> "FirstVisibleTab"/int
 */
public void setFirstVisibleTab(int tab_pos) {
	synchronized (this) {
		if (tab_panel.allTabsVisible()
			|| tab_pos < 0 || tab_pos > tab_panel.countTabs()-1) return;
		first_tab = tab_pos;
	}
	tab_panel.layout();
}

/**
 * Scrolls the tabs if necessary so that the tab is fully visible.
 * @see #setFirstVisibleTab
 */
public synchronized void makeTabVisible(int tab_pos) { 
	if (tab_panel.allTabsVisible()
		|| tab_pos < 0 || tab_pos > tab_panel.countTabs()-1) {
		return;
	}
	boolean covers_tabs = tab_panel.arrow_panel.coversTabs();

	if (tab_pos < first_tab) {
		setFirstVisibleTab(tab_pos);
	}
	else if (tab_pos > last_tab) {
		setFirstVisibleTab(first_tab + tab_pos - last_tab);
	}

	// remove jagged line if necessary.
	if (covers_tabs != tab_panel.arrow_panel.coversTabs()) {
		tab_panel.arrow_panel.repaint();
	}
}

/** Gets the TabResize value.
 * @see #setTabResize
 */
public boolean getTabResize() { return tab_resize; }

/** Sets whether all tabs should be resized to the width or height of the
 * largest tab in the same row, depending on the value of the TabSide value.
 * If true (default), this property is processed before TabStretch.<p>
 * <strong>HTML param name/value:</strong> "TabResize"/boolean
 * @see #setTabStretch
 */
public void setTabResize(boolean v) {
	tab_resize = v;
	tab_panel.layout();
}

/** Gets the TabSide value.
 * @see #setTabSide
 */
public int getTabSide() { return tab_side; }

/** Sets the side on which tabs are to be displayed: TOP (default),
 * or BOTTOM.<p>
 * <strong>HTML param name/value:</strong> "TabSide"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setTabSide(int v) {
	synchronized (this) {

		TabManagerConverter.checkTabSide(v);
		tab_side = v;
		String side;

		if (v == LEFT) side = "West";
		else if (v == RIGHT) side = "East";
		else if (v == BOTTOM) side = "South";
		else side = "North";
		remove(tab_panel);
		super.add(side, tab_panel);
		if (!isDisplayable()) return;
	}
	layout();
	repaint();
}

/** Gets the TabStretch value.
* @see #setTabStretch
*/
public boolean getTabStretch() { return tab_stretch; }

/** Sets whether all tabs should be stretched if necessary to fill the
* entire side (default: false).<p>
* <strong>HTML param name/value:</strong> "TabStretch"/boolean<p>
* @see #setTabResize
*/
public void setTabStretch(boolean v) {
tab_stretch = v;
tab_panel.layout();
}

/** Sets the page associated with a tab. Several tabs may share a common page.
 * @param tab_pos the position of the tab to be set; if this is greater
 * than the current number of tabs, a new tab will be created
 * @param label the tab's label;
 * this may be a String, Image, JCString, or any object (in which case the object's
 * toString() method is called to obtain a string) If null, any tab label
 * set previously using setTabLabels will be used.
 * @param page may be null if no page is to be associated with the tab;
 * a page may be set later using JCTabButton.setPage
 * @return the position of the tab that was set (which may be a new tab)
 * @see #getTab
 * @see #setTabLabels
 * @see JCTabButton#setPage
 * @see jclass.util.JCString
 */
public int setTabPage(int tab_pos, Object label, Component page) {
	JCTabButton btn = getTab(tab_pos);

	if (btn == null) {
		btn = new JCTabButton(this);
		tab_panel.add(btn);
	}
	synchronized (this) {
		page_area.addPage(page);
		btn.setPage(page);
		if (label == null && tab_labels != null) {
			tab_pos = getTab(btn);
			if (tab_labels.length > tab_pos)
				label = tab_labels[tab_pos];
		}
		btn.setLabel(label);
	}

	// Relayout the tab's parent to force it to be drawn
	if (isDisplayable()) {
		tab_panel.invalidate();
		validate();
	}
	return getTab(btn);
}

/** Adds a page and creates a tab for it.
 * @param label the tab's label;
 * this may be a String, Image, JCString or any object (in which case the object's
 * toString() method is called to obtain a string). If null, any tab label
 * set previously using setTabLabels will be used.
 * @param page may be null if no page is to be associated with the tab;
 * a page may be set later using JCTabButton.setPage
 * @return the position of the new tab
 * @see #getTab
 * @see #setTabLabels
 * @see JCTabButton#setPage
 * @see jclass.util.JCString
 */
public int addPage(Object label, Component page) {
	return setTabPage(BWTEnum.MAXINT, label, page);
}

/** Removes a tab and optionally its page.
 * @param tab_pos the position of the tab to be removed
 * @see #getTab
 */
public void removeTab(int tab_pos, boolean remove_page) {
	JCTabButton btn = getTab(tab_pos);
	
	synchronized (this) {
		if (btn == null || btn.getParent() != tab_panel) return;
		
		// update the first tab visible. Make the next tab visible if any.
		// if not , make the previous tab visible. If there are no other tabs,
		// just make first tab visible.
		if (tab_pos == first_tab) {
			if (getTab(tab_pos + 1) == null) {
				if (getTab(tab_pos - 1) == null) {
					setFirstVisibleTab(0);
				}
				else {
					setFirstVisibleTab(tab_pos - 1);
				}
			}
			else {
				setFirstVisibleTab(tab_pos + 1);
			}
		}

		if (remove_page && btn.page != null) 
			page_area.remove(btn.page);
	}
	tab_panel.remove(btn);
}

/** 
 * Adds the specified component as a page.
 * @see #addPage
 */
/* JDK102_START 
public Component add(Component page) {
	if (page instanceof TabPanel || page instanceof TabPageArea)
		return super.add(page);
	setTabPage(getPages().length, null, page);
	return page;
}
 JDK102_END */

/** 
 * Adds the specified component as a page.
 * @see #addPage
 */
/* JDK110_START */
protected void addImpl(Component page, Object constraints, int index) {
	if (page instanceof TabPanel || page instanceof TabPageArea)
		super.addImpl(page, constraints, index);
	else
		setTabPage(getPages().length, null, page);
}
/* JDK110_END */

/** 
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

boolean first = true;

/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */

	if (width < 0 || height < 0) return;
	synchronized (this) {
		if (x == location().x && y == location().y
			&& width == size().width && height == size().height)
			return;
	}
/* AWT_START 
	super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, width, height);
/* SWING_END */

	synchronized (this) {
		if (first) 
			setCurrentTab(current_tab, show_page);
		first = false;
	}
}

}


