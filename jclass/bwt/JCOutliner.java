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
// RCSID -- $RCSfile: JCOutliner.java $ $Revision: 2.12 $ $Date: 2000/11/09 20:10:47 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.ImageIcon;

/**
 * JCOutliner provides an easy way to display hierarchically-organized data.
 * The data items can be organized into columns for easier display.
 * The data is assumed to be in the form of a tree. Each item is either a
 * <a href=jclass.bwt.JCOutlinerFolderNode.html>folder</a> (a tree branch)
 * or an <a href=jclass.bwt.JCOutlinerNode.html>item</a> (a tree leaf).
 * Each folder or item is displayed with an icon to its left. The appearance
 * of each node is specified by a <a href=jclass.bwt.JCOutlinerNodeStyle.html>style
 * </a>. <a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabels>Column labels</a> can also be optionally added.
 * <p>
 * The user can control
 * whether the data contained in a folder is to be displayed. The user can
 * collapse or uncollapse a folder by either double-clicking on the folder,
 * hitting the space key,
 * or clicking on the optional shortcut button displayed to the left of the folder. Typing a character will cause the first item which starts with the
 * character to be made visible and selected. If no item can be found, no action
 * is taken.
 * <p>
 * The shortcut button contains a character that indicates whether a folder is
 * collapsed by displaying either a "+" (collapsed), or "-" (uncollapsed).
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AllowMultipleSelections   </td><td><a href=#setAllowMultipleSelections>setAllowMultipleSelections</a></td></tr><tr><td>
 * AutoSelect          </td><td><a href=#setAutoSelect>setAutoSelect</a></td></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Batched             </td><td><a href=#setBatched>setBatched</a></td></tr><tr><td>
 * ColumnAlignments    </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnAlignments>setColumnAlignments</a></td></tr><tr><td>
 * ColumnButtons       </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnButtons>setColumnButtons</a></td></tr><tr><td>
 * ColumnLabels        </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabels>setColumnLabels</a></td></tr><tr><td>
 * ColumnLabelSort     </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabelSort>setColumnLabelSort</a></td></tr><tr><td>
 * ColumnLabelSortMethod </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabelSort>setColumnLabelSortMethod</a></td></tr><tr><td>
 * ColumnLeftMargin    </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLeftMargin>setColumnLeftMargin</a></td></tr><tr><td>
 * ColumnRightMargin   </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnRightMargin>setColumnRightMargin</a></td></tr><tr><td>
 * ColumnWidths        </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnWidths>setColumnWidths</a></td></tr><tr><td>
 * defaultShortcut                </td><td><a href=#setdefaultShortcut>setdefaultShortcut</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * NodeHeight          </td><td><a href=#setNodeHeight>setNodeHeight</a></td></tr><tr><td>
 * NodeIndent          </td><td><a href=#setNodeIndent>setNodeIndent</a></td></tr><tr><td>
 * NumColumns          </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setNumColumns>setNumColumns</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * RootVisible         </td><td><a href=#setRootVisible>setRootVisible</a></td></tr><tr><td>
 * ScrollbarDisplay    </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarDisplay>setScrollbarDisplay</a></td></tr><tr><td>
 * ScrollbarOffset     </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarOffset>setScrollbarOffset</a></td></tr><tr><td>
 * Spacing             </td><td><a href=jclass.bwt.JCMultiColumnList.html#setSpacing>setSpacing</a></td></tr><tr><td>
 * StateList           </td><td><a href=#setStateList>setStateList</a></td></tr><tr><td>
 * Tree           </td><td><a href=#setTree>setTree</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr><tr><td>
 * VisibleRows         </td><td><a href=jclass.bwt.JCMultiColumnList.html#setVisibleRows>setVisibleRows</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Posted when an item is double-clicked, or the ENTER key is hit</td></tr><tr><td>
 * JCOutlinerEvent    </td><td><a href=#addItemListener>addItemListener</a></td> <td>Posted when an item is clicked or the space key is hit</td></tr>
 * </table>
 */
public class JCOutliner extends JCMultiColumnWindow
implements JCOutlinerInterface {

public static final int FOLDER_CLOSED = BWTEnum.FOLDER_CLOSED;
public static final int FOLDER_OPEN_NONE = BWTEnum.FOLDER_OPEN_NONE;
public static final int FOLDER_OPEN_FOLDERS = BWTEnum.FOLDER_OPEN_FOLDERS;
public static final int FOLDER_OPEN_ITEMS = BWTEnum.FOLDER_OPEN_ITEMS;
public static final int FOLDER_OPEN_ALL = BWTEnum.FOLDER_OPEN_ALL;

public final static int DRAGDROP_NONE = 0;
public final static int DRAGDROP_TARGET = 1;
public final static int DRAGDROP_SOURCE = 2;
public final static int DRAGDROP_ALL = 4;

JCOutlinerComponent outliner;

private static final String base = "outliner";
private static int nameCounter = 0;

/** Creates an empty outliner. No parameters are read from an HTML file */
public JCOutliner() {
	this(null, null, null);
}

/** Creates an outliner with the specified root node.
 * No parameters are read from an HTML file
 * @see #setRootNode
 */
public JCOutliner(JCOutlinerNode root) {
	this(root, null, null);
}

/**
 * Creates an outliner with the specified number of visible lines.
 * @param rows the number of items to show.
 * If set to 0, the outliner will attempt to show all its items.
 * @param multipleSelections if true then multiple selections are allowed.
 */
public JCOutliner(int rows, boolean multipleSelections) {
	this(null, null, null);
	outliner.visible_rows = rows;
	outliner.multiple_select = multipleSelections;
}


/** Creates a outliner which reads parameters from the applet's HTML file.
 * @param items the initial items
 * This may be a String, JCString or any object (in which case the object's
 * toString() method is called to obtain a string).
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCOutliner(JCOutlinerNode root, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setOutliner(new JCOutlinerComponent(root, applet, getName()));
	if (getClass().getName().equals("jclass.bwt.JCOutliner"))
		 getParameters(applet);
}

/** Gets the internal outliner component. */
public JCOutlinerComponent getOutliner() { return outliner; }

/** Replaces the current outliner. */
public void setOutliner(JCOutlinerComponent outliner) {
	this.outliner = outliner;
	/* AWT_START
	// We don't need to do this under SWING since JComponent tracks the current Applet
	outliner.setApplet(applet);
	 AWT_END */
	outliner.setBorderThickness(1);
	outliner.setHighlightThickness(1);
	outliner.setSpacing(1);
	setViewport(outliner);
	outliner.scrolled_window = this;
	if (isDisplayable())
		validate();
	outliner.enable(isEnabled());
/* JDK110_START */
	outliner.addKeyListener(this);
/* JDK110_END */
}

public void setCopy(boolean b)
{
	outliner.bCopy=b;
}

protected void setVertScrollbarValues() {
    getVertScrollbar().setUnitIncrement(outliner.full_node_height);
}

protected void setHorizScrollbarValues() {
    getHorizScrollbar().setUnitIncrement(20);
}

/** Sets the work area height to be the outliner's virtual height.
 */
protected int getViewportHeight() {
	int visible_rows = getVisibleRows();
	outliner.visible_rows = 0;
	int h = outliner.preferredHeight();
	outliner.visible_rows = visible_rows;
	return h;
}

/** Scrolls the list vertically. */
protected void scrollVertical(JCScrollableInterface scrollable,
							  JCAdjustmentEvent ev, int value) {
	super.scrollVertical(scrollable, ev, value);
	JCVector nodes = outliner.visible_nodes;
	if (nodes.size() == 0) return;
	JCOutlinerNode first = (JCOutlinerNode) nodes.elementAt(0);
	int last_node = Math.max(0, nodes.size() - 1);
	JCOutlinerNode last = (JCOutlinerNode) nodes.elementAt(last_node);

	switch (keystroke) {
	//case Event.PGUP:
	case Event.END:
		outliner.setFocusNode(last, null);
		break;
	//case Event.PGDN:
	case Event.HOME:
		outliner.setFocusNode(first, null);
		break;
	}
}

/***********************************
 * JCOutlinerInterface methods
 ***********************************/

/**
 * Adds the specified item listener to receive item events.
 * If the listener implements JCOutlinerListener (an JCItemListener subclass),
 * it will be passed JCOutlinerEvents.
 * @see JCItemEvent
 * @see JCOutlinerEvent
 * @see JCOutlinerListener
 */
public void addItemListener(JCItemListener l) { outliner.addItemListener(l); }

/**
 * Removes the specified item listener so it no longer receives
 * item events.
 */
public void removeItemListener(JCItemListener l) { outliner.removeItemListener(l); }

/**
 * Adds the specified action listener to receive action events.
 * Action events occur when a node is double-clicked.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) { outliner.addActionListener(l); }

/**
 * Removes the specified action listener so it no longer receives
 * action events.
 * @see #addActionListener
 */
public void removeActionListener(JCActionListener l) { outliner.removeActionListener(l); }

/** Notifies the outliner that the number of a folder's children has changed. */
public void folderChanged(JCOutlinerNode node) {
	outliner.folderChanged(node);
}

/** Gets the Batched value.
 * @see #setBatched
 */
public boolean getBatched() { return outliner.getBatched(); }

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public void setBatched(boolean v) { outliner.setBatched(v); }

public Image getImage()
{
  return BWTUtil.createImage(this,getWidth(),getHeight());
}

public ImageIcon getImageIcon()
{
  //folderChanged(getRootNode());
  //repaint();
  //getRootNode().setLabel(getRootNode().getLabel());
  return new ImageIcon(/*getOutliner().*/createImage(getImage().getSource()));
}

/** Gets the root node.
 * @see #setRootNode
 */
public JCOutlinerNode getRootNode() { return outliner.getRootNode(); }

/** Sets the root node. */
public void setRootNode(JCOutlinerNode node) { outliner.setRootNode(node); }

/** Gets the current node hierarchy as a String list.
 * @see #setTree
 */
public String[] getTree() { return outliner.getTree(); }

/** Sets the node hierarchy by parsing the string list.
 * Each line in the list represents a node's label.
 * Spaces or tabs at the start of each
 * line supply the tree hierarchy. Lines with the same indentation level are
 * children. Nodes without children are assumed to be items. To create a folder
 * without children, append the string "(FOLDER)" to the line.<p>
 * <strong>HTML param name/value:</strong> "Tree"/comma-separated lines<p>
 */
public void setTree(String[] s) { outliner.setTree(s); }

/** Gets the RootVisible value.
 * @see #setRootVisible
 */
public boolean getRootVisible() { return outliner.getRootVisible(); }

/** If true (default), the root node is drawn.<p>
 * <strong>HTML param name/value:</strong> "RootVisible"/boolean
 */
public void setRootVisible(boolean v) { outliner.setRootVisible(v); }

/** Gets the current number of non-collapsed nodes (ie the number of
 * open folders and their children).
 */
public int getNumNodes() { return outliner.getNumNodes(); }

/** Calculates the height of a node (by looking at the node's style).
 */
public int calculateNodeHeight(JCOutlinerNode node) {
	return outliner.calculateNodeHeight(node);
}

/** Gets the NodeHeight value.
 * @see #setNodeHeight
 */
public int getNodeHeight() { return outliner.getNodeHeight(); }

/** Sets the height of each node (pixels).
 * If 0 (default), the height is determined using the largest font and
 * image height in the list of node styles.<p>
 * <strong>HTML param name/value:</strong> "NodeHeight"/int
 */
public void setNodeHeight(int v) { outliner.setNodeHeight(v); }

/**
 * Returns true if this outliner allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean getAllowMultipleSelections() { return outliner.getAllowMultipleSelections(); }

/**
 * Sets whether the outliner allows multiple selections.
 * <strong>HTML param name/value:</strong> "AllowMultipleSelections"/boolean
 * @see #getAllowMultipleSelections
 */
public void setAllowMultipleSelections(boolean v) { outliner.setAllowMultipleSelections(v); }

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect() { return outliner.getAutoSelect(); }

/** If true (default), the node which currently has focus is also selected.<p>
 * <strong>HTML param name/value:</strong> "AutoSelect"/boolean
 * @see #setFocusNode
 * @see #selectNode
 */
public void setAutoSelect(boolean v) { outliner.setAutoSelect(v); }

/** Gets the NodeIndent value.
 * @see #setNodeIndent
 */
public int getNodeIndent() { return outliner.getNodeIndent(); }

/** Sets the indentation of each node relative to its parent (pixels).<p>
 * <strong>HTML param name/value:</strong> "NodeIndent"/int
 */
public void setNodeIndent(int v) { outliner.setNodeIndent(v); }

/** Gets the current StateList.
 * @see #setStateList
 */
public int[] getStateList() { return outliner.getStateList(); }

/** Sets a list of values used for double-clicking on a folder or clicking
 * its shortcut button.
 * The folder's state is cycled through the list. Valid values:
 * <pre>
FOLDER_CLOSED        Folder closed; no children visible
FOLDER_OPEN_NONE     Folder open; no children visible
FOLDER_OPEN_FOLDERS  Folder open; only folder children visible
FOLDER_OPEN_ITEMS    Folder open; only non-folder children visible
FOLDER_OPEN_ALL      Folder open; all children visible
 * </pre><p>
 * <strong>HTML param name/value:</strong> "StateList"/comma-separated list of enums<p>
 */
public void setStateList(int[] list) { outliner.setStateList(list); }

/** Gets the current StyleList.
 * @see #setStyleList
 */
public JCOutlinerNodeStyle[] getStyleList() { return outliner.getStyleList(); }

/** Sets the list of node styles. The list is only used for determining
 * the node height if it has not been set.
 * @see #setNodeHeight
 */
public void setStyleList(JCOutlinerNodeStyle[] list) { outliner.setStyleList(list); }

/** Gets the default node style. */
public JCOutlinerNodeStyle getDefaultNodeStyle() { return outliner.getDefaultNodeStyle(); }


/**
 *	Sets the shortcut in default node style.
 * 	@see #getDefaultNodeStyle
 */
public void setDefaultShortcut(boolean f) {
	getDefaultNodeStyle().setShortcut(f);
	repaint();
}

/**
 *	Gets the shortcut from default node style.
 * 	@see #setDefaultShortcut
 * 	@see #getDefaultNodeStyle
 */
public boolean getDefaultShortcut() {
	return getDefaultNodeStyle().getShortcut();
}


/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing() { return outliner.getSpacing(); }

/** Sets the spacing between items (default: 0).
 * This value increases the HighlightThickness value.<p>
 * <strong>HTML param name/value:</strong> "Spacing"/int<p>
 * @see JCComponent#setHighlightThickness
 */
public void setSpacing(int v) { outliner.setSpacing(v); }

/**
 * Returns the number of visible lines.
 * @see #setVisibleRows
 */
public int getVisibleRows() { return outliner.getVisibleRows(); }

/** Sets the number of visible rows. If set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.<p>
 * <strong>HTML param name/value:</strong> "VisibleRows"/int<p>
 */
public void setVisibleRows(int v) { outliner.setVisibleRows(v); }

/**
 * Gets the row of the first selected node,
 * or BWTEnum.NOTFOUND if no node is selected.
 * @see #selectNode
 */
public int getSelectedIndex() { return outliner.getSelectedIndex(); }

/**
 * Returns an array containing the rows of the selected nodes,
 * or null if no nodes are selected.
 */
public int[] getSelectedIndexes() { return outliner.getSelectedIndexes(); }

/**
 * Returns an array containing the
 * labels of the currently selected nodes, or null if no node is selected.
 */
public Object[] getSelectedObjects() { return outliner.getSelectedObjects(); }

/** Returns the currently selected node, or null. */
public JCOutlinerNode getSelectedNode() { return outliner.getSelectedNode(); }

/**
 * Returns an array of the currently selected nodes,
 * or null if no nodes are selected.
 */
public JCOutlinerNode[] getSelectedNodes() { return outliner.getSelectedNodes();}

/** Selects a node, and posts a JCOutlinerEvent.
 * @param node if null, the currently selected nodes are deselected
 * @param ev event which caused the selection (passed to listener)
 * @see #addItemListener
 */
public void selectNode(JCOutlinerNode node, Event ev) {
	outliner.selectNode(node, ev);
}

/** Returns the node which currently has focus, or null. */
public JCOutlinerNode getFocusNode() { return outliner.getFocusNode(); }

/** Sets a node to have the keyboard focus.
 * @param node if null, no the current focus node is unchanged
 * @param ev event which caused the focus change
 */
public void setFocusNode(JCOutlinerNode node, Event ev) {
	outliner.setFocusNode(node, ev);
}

/** Gets a list of the nodes which are currently displayed. */
public JCVector getVisibleNodes() { return outliner.getVisibleNodes(); }

/** Sets the state of a folder.
 * @param node if not a folder, no action is taken
 * @param state one of:
 * <pre>
 * FOLDER_CLOSED        Folder closed; no children visible
 * FOLDER_OPEN_NONE     Folder open; no children visible
 * FOLDER_OPEN_FOLDERS  Folder open; only folder children visible
 * FOLDER_OPEN_ITEMS    Folder open; only non-folder children visible
 * FOLDER_OPEN_ALL      Folder open; all children visible
 * </pre>
 * @param notify if true, a JCOutlinerEvent and JCActionEvent is posted for the node
 * @return false if the app disallowed the state change
 * @exception IllegalArgumentException If the value is invalid
 * @see #addActionListener
 * @see #addItemListener
 */
public boolean setNodeState(JCOutlinerNode node, int state, boolean notify) {
	return outliner.setNodeState(node, state, notify);
}

/** Scrolls the display if necessary to make the node visible.
 * @return false if the node could not be made visible due to its parent's state
*/
public boolean makeNodeVisible(JCOutlinerNode node) {
	return outliner.makeNodeVisible(node);
}

/** Returns the next visible node, or null if none was found. */
public JCOutlinerNode getNextNode(JCOutlinerNode node) {
	return outliner.getNextNode(node);
}

/** Returns the previous visible node, or null if none was found. */
public JCOutlinerNode getPreviousNode(JCOutlinerNode node) {
	return outliner.getPreviousNode(node);
}

/** Gets the physical bounding rectangle of a node (icon and label)
 * (ie adjusted for scrolling and insets).
 * @param rect if not null, its members are set
 * @return null if rect is null, otherwise the bounding rectangle
 */
public Rectangle getBounds(JCOutlinerNode node, Rectangle rect) {
	return outliner.getBounds(node, rect);
}

/** Gets the node in which the event occurred. */
public JCOutlinerNode eventToNode(Event ev) { return outliner.eventToNode(ev); }

/** Returns true if the node is a folder and the event is within the
 * folder's shortcut button.
 */
public boolean eventInShortcut(Event ev, JCOutlinerNode node) {
	return outliner.eventInShortcut(ev, node);
}

/**
 * Sorts the nodes in the list in ascending order based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 */
public void sortByColumn(int column, JCSortInterface sort_if) {
	outliner.sort(column, sort_if, ASCENDING);
}

/**
 * Sorts the nodes in the list based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 * @param direction sort direction: ASCENDING or DESCENDING
 */
public void sortByColumn(int column, JCSortInterface sort_if, int direction) {
	outliner.sort(column, sort_if, direction);
}

/** Gets the DragAndDropPolicy value.
 * @see setDragAndDropPolicy
 */
public int getDragAndDropPolicy() { return outliner.getDragAndDropPolicy(); }

/** Sets whether the outliner can be used as a drag and/or drop site.
 * @param v DRAGDROP_NONE (default), DRAGDROP_TARGET, DRAGDROP_SOURCE, DRAGDROP_ALL
 */
public void setDragAndDropPolicy(int v) { outliner.setDragAndDropPolicy(v); }

/***********************************
 * Printing methods
 ***********************************/

/**
 * Prints the JCOutliner component under JDK 1.1
 */
/* JDK110_START */
public void print() {
	outliner.print();
}
/* JDK110_END */

/**
 * Adds the specified print listener to receive JCOutlinerPrintEvent events.
 * @see JCOutlinerPrintEvent
 * @see JCOutlinerPrintListener
 */
/* JDK110_START */
public void addPrintListener(JCOutlinerPrintListener l) { outliner.addPrintListener(l); }
/* JDK110_END */

/**
 * Removes the specified print listener so it no longer receives
 * item events.
 */
/* JDK110_START */
public void removePrintListener(JCOutlinerPrintListener l) { outliner.removePrintListener(l); }
/* JDK110_END */

/**
 * Sets the outliner to show the a popup menu which contains copy, cut, and
 * paste functionality when right clicking on a node.
 * For JDK1.1 only.
 * @see #getShowPopupMenu
 */
/* JDK110_START */
public void setShowPopupMenu(boolean show_popup) {
	outliner.setShowPopupMenu(show_popup);
}
/* JDK110_END */

/**
 * Returns true if the cut, copy, and paste popup will appear when
 * right clicking on a node. Otherwise, it returns false.
 * For JDK1.1 only.
 * @see #setShowPopupMenu
 */
/* JDK110_START */
public boolean getShowPopupMenu() {
	return (outliner.getShowPopupMenu());
}
/* JDK110_END */

}
