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
// RCSID -- $RCSfile: TabArrowPanel.java $ $Revision: 2.7 $ $Date: 2000/11/09 20:11:49 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

/** A private container which manages a JCTabManager's tab scroller arrows.
 * @see JCTabManager
 */
public class TabArrowPanel extends JCContainer implements JCActionListener {

TabPanel tab_panel;
JCArrowButton left_arrow, right_arrow;
static final int ARROW_SIZE = 15;
static final int WIDTH = (int)(2.5 * ARROW_SIZE);

private static final String base = "tabarrowpanel";
private static int nameCounter = 0;

public TabArrowPanel(TabPanel tab_panel) {
	super();
	setName(base + nameCounter++);
	this.tab_panel = tab_panel;
	setLayout(null);

	add(left_arrow = new JCArrowButton(BWTEnum.LEFT));
	left_arrow.addActionListener(this);
	add(right_arrow = new JCArrowButton(BWTEnum.RIGHT));
	right_arrow.addActionListener(this);

	left_arrow.initial_delay = right_arrow.initial_delay = 250;
}

/** 
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}
		
/** Draws a jagged edge along the left side if any tab is covered. */
public void fillBackground(Graphics gc) {
	if (!coversTabs()) {
		super.fillBackground(gc);
		return;
	}
	synchronized (this) {
		Polygon p = new Polygon();
		int w = size().width, h = size().height;

		// this draws the jagged line
		p.addPoint(0,0);
		for (int x0=1, x=0, y=2; y < h; y += 3) {
			p.addPoint(x, y);
			if (x == x0) x = x0-1;
			else if (x == x0-1) x = x0+1;
			else if (x == x0+1) x = x0;
		}
		// this fills in the rest of the tab arrow panel starting from the
		// bottom left to the bottom right, and then to the upper right.
		p.addPoint(0,h);
		p.addPoint(w,h);
		p.addPoint(w,0);
		gc.fillPolygon(p);

		gc.setColor(BWTUtil.brighter(getBackground()));
		for (int i=1; i < p.npoints-2; i++)
			gc.drawLine(p.xpoints[i-1], p.ypoints[i-1], p.xpoints[i], p.ypoints[i]);
	}
}

/** Returns true if any tab button is obscured by this panel. */
synchronized boolean coversTabs() {
	if (tab_panel.countTabs() == 0) return false;
	
	Component[] child = tab_panel.getComponents();
	Component last_tab = null;

	// find the last tab. Not all children in the Tab panel are Tab Buttons.
	// They can be scroll arrows
	for (int i=0; i < child.length; i++) { 
		if (child[i] instanceof JCTabButton) {
			last_tab = child[i];
		}
    }

	if (last_tab == null) return false;

	return last_tab.bounds().x + last_tab.bounds().width >= bounds().x;
}

/** Positions the arrows. */
public void layout() {
	int h = tab_panel.size().height;
	int side = tab_panel.tab_manager.getTabSide();
	int y = (side == BWTEnum.BOTTOM) ? 0 : h-ARROW_SIZE;

	JCComponent.setBounds(this, tab_panel.size().width-WIDTH, 0, WIDTH, h);
	JCComponent.setBounds(left_arrow, WIDTH-2*ARROW_SIZE, y, 
						  ARROW_SIZE, ARROW_SIZE);
	JCComponent.setBounds(right_arrow, WIDTH-ARROW_SIZE, y, 
						  ARROW_SIZE, ARROW_SIZE);

	int first_tab = tab_panel.tab_manager.getFirstVisibleTab();
	left_arrow.enable(first_tab > 0);
	right_arrow.enable(coversTabs());
}

/** Scrolls the tabs depending on the arrow button hit. */
public void actionPerformed(JCActionEvent ev) {
	boolean covers_tabs = coversTabs();
	int first_tab = tab_panel.tab_manager.getFirstVisibleTab();
	if (ev.getSource() == left_arrow)
		tab_panel.tab_manager.setFirstVisibleTab(--first_tab);
	else if (ev.getSource() == right_arrow)
		tab_panel.tab_manager.setFirstVisibleTab(++first_tab);

	// update the page area to remove or draw shadows under the tabs
	tab_panel.tab_manager.page_area.drawBorder(
				tab_panel.tab_manager.page_area.getGraphics());
	
    // Remove or paint jagged edge if necessary
	if (covers_tabs != coversTabs()) 
		repaint();
}

}
