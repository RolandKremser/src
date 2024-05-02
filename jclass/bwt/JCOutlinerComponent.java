/*****************************************************************************
 *
 * (c) Copyright 1997-2000, SITRAKA INC.
 * ALL RIGHTS RESERVED
 *
 *  THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED
 * AND COPIED ONLY IN ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND
 * WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE.  THIS SOFTWARE OR
 * ANY OTHER COPIES THEREOF MAY NOT BE PROIDED OR OTHERWISE MADE
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
// RCSID -- $RCSfile: JCOutlinerComponent.java $ $Revision: 2.36 $ $Date: 2000/11/09 20:10:50 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.util.*;
import java.awt.*;
import java.applet.*;

/* JDK110_START */
import java.io.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import jclass.bwt.resources.*;
/* JDK110_END */
/* SWING11_START
import com.sun.java.swing.border.Border;
 SWING11_END */
/* SWING12_START */
import javax.swing.border.Border;
/* SWING12_END */

class OutlinerMultiColumnData extends JCMultiColumnData {

OutlinerMultiColumnData() { super(); }

/** Creates an instance and registers a component. */
OutlinerMultiColumnData(JCMultiColumnInterface multi) {
	super(multi);
}

/** Adjusts the drawing rectangle for the value.
 * @param col value's column
 * @param row_rect rectangle within which to draw the row
 * @param rect rectangle within which to draw the value (read-write)
 */
protected void adjustDrawingRect(int col, Rectangle row_rect, Rectangle rect) {
	if (row_rect == null || col > 0) {
		super.adjustDrawingRect(col, row_rect, rect);
	}
	else {
		rect.width -= (rect.x - multi.getColumnPosition(0));
	}
}

}

/**
 * The internal private component of a JCOutliner.
 * @see JCOutliner
 */
public class JCOutlinerComponent extends JCComponent
implements JCOutlinerInterface,
	JCMultiColumnInterface,
	JCSortInterface,
	JCScrollableInterface {

private static final boolean TRACE = false;

public static final int FOLDER_CLOSED = BWTEnum.FOLDER_CLOSED;
public static final int FOLDER_OPEN_NONE = BWTEnum.FOLDER_OPEN_NONE;
public static final int FOLDER_OPEN_FOLDERS = BWTEnum.FOLDER_OPEN_FOLDERS;
public static final int FOLDER_OPEN_ITEMS = BWTEnum.FOLDER_OPEN_ITEMS;
public static final int FOLDER_OPEN_ALL = BWTEnum.FOLDER_OPEN_ALL;

public final static int DRAGDROP_NONE = 0;
public final static int DRAGDROP_TARGET = 1;
public final static int DRAGDROP_SOURCE = 2;
public final static int DRAGDROP_ALL = 4;

public boolean clearSelectionOnSingleClickInRange = false;

JCOutlinerNode 	root;				// the root of the tree
boolean			multiple_select = false;
boolean 		auto_select = true;
int				visible_rows = 0;
int 			num_rows = 0;		// total rows in the tree
public JCVector		selected_nodes = new JCVector();		// selected node
JCOutlinerNode 	focus_node;			// node which has focus
boolean			batched = false;
boolean			needs_recalc = false, needs_repaint = false;
Event			last_event;
int 			node_height = 0;
int 			node_indent = 15;
int 			horiz_origin, vert_origin;
int				spacing = 0;
int 			state_list[] = { FOLDER_CLOSED, FOLDER_OPEN_ALL };
boolean 		root_visible = true;
JCVector 		visible_nodes = new JCVector();
int 			bottom_y;

/** The object which maintains the multi-column data. */
protected OutlinerMultiColumnData	data = new OutlinerMultiColumnData(this);

/** Parent window. */
transient protected JCMultiColumnWindow scrolled_window = null;

/** List of all nodes, in drawing order. */
protected 		JCVector node_list = new JCVector();
boolean 		root_shortcut;
boolean 		bCopy=false;

int sort_column;
int sort_direction;
JCSortInterface sort_method;
int				pref_height_internal, pref_width_internal;

protected JCOutlinerNodeStyle style_list[];
protected JCOutlinerNodeStyle default_style;
transient protected Image shortcut_open_icon, shortcut_close_icon;
protected static final int SHORTCUT_SIZE = 9;

protected final static int BEGIN = 0;
protected final static int END = 1;

/* JDK110_START */
static ByteArrayOutputStream output_stream;
static ByteArrayInputStream input_stream;
static ObjectOutputStream out;
static ObjectInputStream in;
protected ResourceBundle li = null; // Our LocaleInfo bundle
protected PopupMenu popup_menu = null;
protected boolean show_popup = false;
protected int popup_x, popup_y;
/* JDK110_END */

// private
protected int 	full_node_height;		// node_height + spacing + highlight
Rectangle 		draw_rect = new Rectangle();

/** List of JCOutlinerEvent listeners */
protected JCVector itemListeners = new JCVector(0);

/** List of JCActionEvent listeners */
protected JCVector actionListeners = new JCVector(0);

private static final String base = "outliner";
private static int nameCounter = 0;

static final String item_icon[] = {
"                ",
"   .......      ",
"   .XXXXXX.     ",
"   .XXXXXXX.    ",
"   .X.....XX.   ",
"   .XXXXXXXX.   ",
"   .X......X.   ",
"   .XXXXXXXX.   ",
"   .X......X.   ",
"   .XXXXXXXX.   ",
"   .X......X.   ",
"   .XXXXXXXX.   ",
"   .X......X.   ",
"   .XXXXXXXX.   ",
"   ..........   ",
"                "};

static final String folder_open_icon[] = {
"  ......        ",
" .XXXXXX.       ",
" .XXXXXX.       ",
".XXXXXXXX..     ",
".XXXXXXXXXX.    ",
".XXXXXXXXXX.    ",
".XXX........... ",
".XXX.ooooooooo. ",
".XX.oooooooooo. ",
".XX.ooooooooo.  ",
".X.oooooooooo.  ",
".X.ooooooooo.   ",
"..oooooooooo.   ",
" ...........    ",
"                ",
"                "};

static final String folder_closed_icon[] = {
"  ........      ",
" .XXXXXXXX.     ",
" .XXXXXXXX.     ",
"............... ",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
".XXXXXXXXXXXXXX.",
" ...............",
"                ",
"                "};

static final String shortcut_open_icon_string[] = {
".........",
".       .",
".   .   .",
".   .   .",
". ..... .",
".   .   .",
".   .   .",
".       .",
".........",
};

static final String shortcut_close_icon_string[] = {
".........",
".       .",
".       .",
".       .",
". ..... .",
".       .",
".       .",
".       .",
".........",
};

/** Creates an empty JCOutliner. */
public JCOutlinerComponent() {
	this(null, null, null);
}

/** Creates a new JCOutliner with the specified root node. */
public JCOutlinerComponent(JCOutlinerNode root) {
	this(root, null, null);
}

/**
 * Creates an outliner with the specified number of visible lines.
 * @param rows the number of items to show.
 * If set to 0, the outliner will attempt to show all its items.
 * @param multipleSelections if true then multiple selections are allowed.
 */
public JCOutlinerComponent(int rows, boolean multipleSelections) {
	this(null, null, null);
	visible_rows = rows;
	multiple_select = multipleSelections;
}

/** Creates an outliner which reads parameters from the applet's HTML file.
 * @param root the root node (may be null, and set later with setRootNode())
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setRootNode
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCOutlinerComponent(JCOutlinerNode root, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	this.root = root;
	insets = new Insets(5,5,5,5);
	style_list = new JCOutlinerNodeStyle[1];
	default_style = new JCOutlinerNodeStyle();

	JCImageCreator im = new JCImageCreator(this, 16, 16);
	im.setColor('X', Color.white);
	im.setColor('.', Color.black);
	im.setColor('o', Color.white);
	default_style.item = default_style.item_selected = im.create(item_icon);

	im.setColor('X', Color.yellow);
	default_style.folder_closed_selected = default_style.folder_closed =
		im.create(folder_closed_icon);

	im.setColor('o', Color.lightGray);
	default_style.folder_open_selected = default_style.folder_open =
		im.create(folder_open_icon);

	style_list[0]= default_style;

/* JDK110_START */
	li = ResourceBundle.getBundle("jclass.bwt.resources.LocaleInfo",
							  Locale.getDefault());
/* JDK110_END */
	if (getClass().getName().equals("jclass.bwt.JCOutlinerComponent"))
		 getParameters(applet);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	OutlinerConverter.getParams(this);
}

/** Overridden to prevent a border being drawn */
/* SWING_START */
public void setBorder(Border border) {
}
/* SWING_END */

/**
 * Sets the font of the component.
 * This value may be set from an HTML file using a PARAM tag with a "Font"
 * name and a Font value.
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	super.setFont(f);
	if (!isDisplayable()) return;
	calcColumnWidths();

	// Force recalc of node heights
	setNodeHeight(0);
}

void calcColumnWidths() {
	if (batched)
		needs_recalc = true;
	else
		data.calcColumnWidths();
}

/** Sets the node_list. */
protected void processRootNode() {
	if (batched) {
		needs_recalc = true;
		return;
	}
	num_rows = countRows(root);
	node_list = new JCVector(countAllNodes(root, null));
	countAllNodes(root, node_list);
	if (focus_node != null && node_list.contains(focus_node))
		return;
	JCOutlinerNode node = null;
	for (int i=0, len = node_list.size(); i < len; i++) {
		node = (JCOutlinerNode) node_list.elementAt(i);
		if (shouldBeDrawn(node))
			break;
	}
	setFocusNode(node, null);
}

/** Gets the parent's header. */
JCHeader getHeader() {
	return (scrolled_window != null
			&& scrolled_window instanceof JCMultiColumnWindow) ?
		((JCMultiColumnWindow)scrolled_window).getHeader() : null;
}

JCMultiColumnWindow getScrolledWindow() {
	return (scrolled_window != null
			&& scrolled_window instanceof JCMultiColumnWindow) ?
		(JCMultiColumnWindow)scrolled_window : null;
}

/** If the list is a child of a JCMultiColumnList,
 * sets a header for the window with the specified labels.
 * @see JCMultiColumnWindow#setColumnLabels
 */
public synchronized void setColumnLabels(JCVector labels) {
	if (getScrolledWindow() != null)
		getScrolledWindow().setColumnLabels(labels);
}

/** If the outliner is a child of a JCOutliner,
 * sets a header for the window with buttons with the specified labels.
 * @see JCMultiColumnWindow#setColumnButtons
 */
public synchronized void setColumnButtons(JCVector labels) {
	if (getScrolledWindow() != null)
		getScrolledWindow().setColumnButtons(labels);
}

/** Notifies the outliner that the number of a folder's children has changed.
 * This must be called only if the outliner has been made visible.
 */
public void folderChanged(JCOutlinerNode node) {
	if (!isDisplayable()) return;

	synchronized (this) {
		if (batched) {
			needs_recalc = true;
			return;
		}
	}

	if (node != null)
		processRootNode();
	calcColumnWidths();

	// Align header labels with outliner's columns
	if (getHeader() != null)
		getHeader().recalc();
	updateParent();
	repaint();
}

/** Gets the root node.
 * @see #setRootNode
 */
public JCOutlinerNode getRootNode() { return root; }

/** Sets the root node.
 */
public synchronized void setRootNode(JCOutlinerNode node) {
	root = node;
	folderChanged(node);
}

/** Gets the current node hierarchy as a string list.
 * @see #setTree
 */
public String[] getTree() { return OutlinerConverter.getTree(this); }

/** Sets the node hierarchy by parsing the string list.
 * Each line in the list represents a node. Spaces or tabs at the start of each
 * line specify the tree hierarchy. Lines with the same indentation level are
 * children.
 * @exception IllegalArgumentException if the indentation levels are invalid
 */
public synchronized void setTree(String[] s) {
	setRootNode(OutlinerConverter.createTree(this, s));
}

/** Gets the RootVisible value.
 * @see #setRootVisible
 */
public boolean getRootVisible() { return root_visible; }

/** If true (default), the root node is drawn. */
public synchronized void setRootVisible(boolean v) {
	root_visible = v;
	repaint();
}

/** Gets the Batched value.
 * @see #setBatched
 */
public boolean getBatched() { return batched; }

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public synchronized void setBatched(boolean v) {
	if (batched == v) return;
	batched = v;
	if (batched) return;
	if (needs_recalc)
		folderChanged(null);
	if (needs_recalc || needs_repaint)
		repaint();
	needs_recalc = needs_repaint = false;
}

/** Gets the NodeHeight value.
 * @see #setNodeHeight
 */
public int getNodeHeight() { return node_height; }

/** Sets the height of each node (pixels).
 * If 0 (default), the height is determined using the largest font and
 * image height in the list of node styles.
 */
public void setNodeHeight(int v) {
	node_height = v;
	updateParent();
	repaint();
}

/** Gets the current number of non-collapsed nodes (ie the number of
 * open folders and their children).
 */
public int getNumNodes() { return num_rows; }

/**
 * Returns true if this outliner allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean getAllowMultipleSelections() { return multiple_select; }

/**
 * Sets whether the outliner allows multiple selections.
 * <strong>HTML param name/value:</strong> "AllowMultipleSelections"/boolean
 * @see #getAllowMultipleSelections
 */
public void setAllowMultipleSelections(boolean v) {
	multiple_select = v;
}

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect() { return auto_select; }

/** If true (default), the node which currently has focus is also selected.
 * @see #setFocusNode
 * @see #selectNode
 */
public synchronized void setAutoSelect(boolean v) {
	auto_select = v;
	repaint();
}

/** Gets the NodeIndent value.
 * @see #setNodeIndent
 */
public int getNodeIndent() { return node_indent; }

/** Sets the indentation of each node relative to its parent (pixels) (default: 15).
 */
public synchronized void setNodeIndent(int v) {
	node_indent = v;
	repaint();
}

/** Gets the current StateList.
 * @see #setStateList
 */
public int[] getStateList() { return state_list; }

/** Recursively map state in folder with currently valid state_list. */
private void mapStateInNode(JCOutlinerNode node) {
	if (!(node instanceof JCOutlinerFolderNode)) return;
	JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
	if (folder.children == null)
		return;

	boolean	foundState = false;

	// recursively change folder state
	for (int i=0; i < folder.children.size(); i++) {
		JCOutlinerNode child = (JCOutlinerNode) folder.children.elementAt(i);
		if (child instanceof JCOutlinerFolderNode)
			mapStateInNode(child);
	}

	// find state
	for (int i=0; i < state_list.length; i++)
		if (folder.state == state_list[i]) {
			foundState = true;
			break;
		}

	// map state
	if (!foundState) {
		int	newDist, minDist=5, j=0;

		// if state closest to "folder.state"
		for (int i=0; i < state_list.length; i++) {
			newDist = Math.abs(folder.state - state_list[i]);
			if (newDist < minDist) {
				minDist = newDist;
				j = i;
			}
		}
		folder.state = state_list[j];
	}
}

/** Sets a list of values used for double-clicking on a folder or clicking
 * it shortcut button. If this list is changed, the outliner will be redrawn.
 * The folder's state is cycled through the list. Valid values:
 * <pre>
 * FOLDER_CLOSED       Folder closed; no children visible
 * FOLDER_OPEN_NONE    Folder open; no children visible
 * FOLDER_OPEN_FOLDERS Folder open; only folder children visible
 * FOLDER_OPEN_ITEMS   Folder open; only non-folder children visible
 * FOLDER_OPEN_ALL     Folder open; all children visible
 * </pre>
 * Default value: { FOLDER_CLOSED, FOLDER_OPEN_ALL }
 */
public synchronized void setStateList(int[] list) {
	state_list = list;

	// perform recursive state mapping
	mapStateInNode(root);

	// relayout and repaint
	folderChanged(root);
}

/** Gets the current StyleList.
 * @see #setStyleList
 */
public JCOutlinerNodeStyle[] getStyleList() { return style_list; }

/** Sets the list of node styles. The list is only used for determining
 * the node height if it has not been set.
 * @see #setNodeHeight
 */
public synchronized void setStyleList(JCOutlinerNodeStyle[] list) {
	style_list = list;

	// set up to recalculate node height
	node_height = 0;
	updateParent();
	repaint();
}

public synchronized void setForeground(Color c) {
	super.setForeground(c);
	createShortcutIcons();
}

public synchronized void setBackground(Color c) {
	super.setBackground(c);
	createShortcutIcons();
}

/** Gets the default node style. */
public JCOutlinerNodeStyle getDefaultNodeStyle() { return default_style; }

/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing() { return spacing; }

/** Sets the spacing between items (default: 0).
 * This value increases the HighlightThickness value.
 * @see JCComponent#setHighlightThickness
 */
public synchronized void setSpacing(int v) {
	spacing = v;
	updateParent();
	repaint();
}

/**
 * Returns the number of visible lines.
 * @see #setVisibleRows
 */
public int getVisibleRows() { return visible_rows; }

/** Sets the number of visible rows. If set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.
 */
public synchronized void setVisibleRows(int v) {
	visible_rows = v;
	updateParent();
	repaint();
}

/**
 * Gets the row of the first selected node,
 * or BWTEnum.NOTFOUND if no node is selected.
 * @see #selectNode
 */
public synchronized int getSelectedIndex() {
	return (selected_nodes.isEmpty()) ?
		BWTEnum.NOTFOUND : ((JCOutlinerNode)selected_nodes.firstElement()).row;
}

/**
 * Returns an array containing the selected indexes,
 * or null if no nodes are selected.
 */
public synchronized int[] getSelectedIndexes() {
	int[] items = new int[selected_nodes.size()];

	for (int i = 0; i < selected_nodes.size(); i++)
		items[i] = ((JCOutlinerNode)selected_nodes.elementAt(i)).row;
	return items.length > 0 ? items : null;
}

/**
 * Returns the an array (length 1) containing the
 * label of the selected node, or null if no node is selected.
 */
public synchronized Object[] getSelectedObjects() {
	Object[] items = new Object[selected_nodes.size()];

	for (int i = 0; i < selected_nodes.size(); i++)
		items[i] = ((JCOutlinerNode)selected_nodes.elementAt(i)).getLabelString();
	return items.length > 0 ? items : null;
}

/** Returns the currently selected node, or null. */
public synchronized JCOutlinerNode getSelectedNode() {
	return (JCOutlinerNode) selected_nodes.getFirst();
}

/** Returns an array of the currently selected nodes,
 * or null if no nodes are selected.
 */
public synchronized JCOutlinerNode[] getSelectedNodes() {
	JCOutlinerNode[] nodes = new JCOutlinerNode[selected_nodes.size()];

	for (int i = 0; i < selected_nodes.size(); i++)
		nodes[i] = (JCOutlinerNode) selected_nodes.elementAt(i);
	return nodes.length > 0 ? nodes : null;
}

/**
 * Selects a node, and posts a JCOutlinerEvent.
 * <B>Single Select Posting sequence</B><p>
 * 1) outlinerNodeSelectBegin (can abort by calling e.setAllowChange(false)<p>
 * 2) outlinerNodeUnselectBegin (on perviously selected node; cannot be aborted)<p>
 * 3) itemStateChanged (on previously selected node)<p>
 * 4) outlinerNodeUnselectEnd (on previously selected node)<p>
 * 5) itemStateChanged<p>
 * 6) outlinerNodeSelectEnd<p>
 * <B>Multiple Selection Mode</B>
 * 1) outlinerNodeSelectBegin (can avoid selecting specified node by calling e.setAllowChange(false) - one call for each node selected<p>
 * 2) outlinerNodeUnselectBegin (for all nodes to be unselected; calling e.setAllowChange(false) can avoid the node begin unselected)<p>
 * 3) itemStateChanged (once for all for unselected nodes, if any)<p>
 * 4) outlinerNodeUnselectEnd (once for each unselected node)<p>
 * 5) itemStateChanged (once for all newly selected nodes<p>
 * 6) outlinerNodeSelectEnd (once for each selected node)<p>
 * @param node if null, the currently selected node is deselected
 * @param ev event which caused the selection
 * @see #addItemListener
 */
public void selectNode(JCOutlinerNode node, Event ev) {
	if (multiple_select && isMultiSelectable(node, ev)) {
		// Call seperate routine to handle shift clicks and ctrl clicks
		// since we allow them because the multiple_select flag is set
		multiSelectNode(node, ev);
		return;
	}

	if (node != null && selected_nodes.contains(node) &&
		!(multiple_select == true && clearSelectionOnSingleClickInRange == true)) {
		// return if the node to select is already selected
		// but only if multiselect is no
		return;
	}

	JCOutlinerNode first_node = null;
	JCOutlinerEvent item_ev = null;
	JCOutlinerInterface caller =
		scrolled_window != null ? (JCOutlinerInterface)scrolled_window : this;
	JCVector il = null;
	JCVector selected_nodes = null;

	synchronized (this) {
		// Clone the listeners
		if (itemListeners.size() > 0) {
			il = (JCVector) itemListeners.clone();
		}

		// clone the selected node list
		if (!this.selected_nodes.isEmpty()) {
			selected_nodes = (JCVector) this.selected_nodes.clone();
		}
	}

	// Post NodeSelectBegin Event
	if (node != null && il != null && il.size() > 0) {
		// Create event with the node to be selected
		item_ev = new JCOutlinerEvent(caller, node, true);
		for (int i=0; i < il.size(); i++) {
			JCItemListener l = (JCItemListener) il.elementAt(i);
			if (l instanceof JCOutlinerListener) {
				((JCOutlinerListener)l).outlinerNodeSelectBegin(item_ev);
			}
			// User can call setAllowChange(false) which sets the doit flag
			// when can abort this operation
			if (!item_ev.doit) {
				return;
			}
		}
	}

	if (selected_nodes != null && !selected_nodes.isEmpty()) {
		// We need to unselect the currently selected nodes
		JCOutlinerNode selected_node;
		// we do the loop backwards since each iteration makes that list smaller
		for (int sn = selected_nodes.size() - 1; sn >= 0; sn--) {
			selected_node = (JCOutlinerNode) selected_nodes.elementAt(sn);
			item_ev = new JCOutlinerEvent(caller, selected_node, false);
			// Create event with the node to be selected
			if (selected_node != null && il != null && il.size() > 0) {
				// post deselection Begin event
				for (int i=0; i < il.size(); i++) {
					JCItemListener l = (JCItemListener) il.elementAt(i);
					if (l instanceof JCOutliner2Listener) {
						((JCOutliner2Listener)l).outlinerNodeUnselectBegin(item_ev);
					}
				}
			}
			if (item_ev.doit == false) {
				// allow unselection to be aborted by change to event
				// passed to outlinerNodeUnselectBegin
				continue;
			}

			// clear the node of selection
			selected_node.selected = false;
			drawNode(selected_node, true);

			if (selected_node != null && il != null && il.size() > 0) {
				// post deselection Begin event
				for (int i=0; i < il.size(); i++) {
					JCItemListener l = (JCItemListener) il.elementAt(i);
					if (l instanceof JCOutliner2Listener) {
						((JCOutliner2Listener)l).outlinerNodeUnselectEnd(item_ev);
					}
				}
			}
		}

		// Post list style DESELECTED event once for all deselected nodes
		if (il != null && il.size() > 0) {
			for (int i=0; i < il.size(); i++) {
				JCItemListener l = (JCItemListener) il.elementAt(i);
				l.itemStateChanged(item_ev);
			}
		}

		// clear selected list
		this.selected_nodes = new JCVector(0);
	}

	// Only deselect the previously selected node
	if (node == null) {
		return;
	}

	synchronized (this) {
		if (itemListeners.size() > 0) {
			il = (JCVector) itemListeners.clone();
			item_ev = new JCOutlinerEvent(caller, node, true);
		}
	}

	node.selected = true;
	il = null;

	synchronized (this) {
		this.selected_nodes.addElement(node);
		drawNode(node, false);
		if (itemListeners.size() > 0) {
			il = (JCVector) itemListeners.clone();
			item_ev = new JCOutlinerEvent(caller, node, true);
			if (!item_ev.doit) {
				return;
			}
		}
	}

	if (il != null && il.size() > 0) {
		item_ev = new JCOutlinerEvent(caller, node, true);
		for (int i=0; i < il.size(); i++) {
			JCItemListener l = (JCItemListener) il.elementAt(i);
			l.itemStateChanged(item_ev);
			if (l instanceof JCOutlinerListener)
				((JCOutlinerListener)l).outlinerNodeSelectEnd(item_ev);
		}
	}

	if (auto_select)
		focus_node = node;
}

/** Selects a node.
 * @param node if null, the currently selected node is deselected
 */
protected void selectNode(JCOutlinerNode node) {
	if (node != null && selected_nodes.contains(node)) return;
	if (!selected_nodes.isEmpty()) {
		JCOutlinerNode first_node = (JCOutlinerNode) selected_nodes.getFirst();
		first_node.selected = false;
		drawNode(first_node, true);
		selected_nodes.removeElement(first_node);  // remove from vector
	}

	if (node != null) {
		node.selected = true;
		selected_nodes.addElement(node);
		drawNode(node, false);
	}
	if (auto_select)
		focus_node = node;
}

/**
 * Checks that a node can be multiply selected.
 * @param ev event which caused the selection
 * @return false if node is null, Ctrl or Shift key was not pressed,
 * there are currently no selected nodes, or if the node does not have the same
 * parent as the first selected node
 */
protected boolean isMultiSelectable(JCOutlinerNode node, Event ev) {
	boolean bCtrl = ev != null ? ev.controlDown() : false;
	boolean bShift = ev != null ? ev.shiftDown() : false;

	if (node == null || !(bCtrl || bShift) || selected_nodes.isEmpty()) {
		return false;
	}
	else if (node.getParent() ==
			 ((JCOutlinerNode)selected_nodes.firstElement()).getParent()) {
		return true;
	}
	else {
		return false;
	}
}

private void postNodeSelectEnd(JCOutlinerEvent item_ev) {
	if (itemListeners.size() > 0) {
		for (int i = 0; i < itemListeners.size(); i++) {
			JCItemListener l = (JCItemListener) itemListeners.elementAt(i);
			if (l instanceof JCOutlinerListener)
				((JCOutlinerListener)l).outlinerNodeSelectEnd(item_ev);
		}
	}
}

/**
 * MultiSelect support
 * Selects one or more nodes, and posts JCOutlinerEvents.
 * @param node if null, the currently selected node is deselected
 * @param ev event which caused the selection
 * @see #addItemListener
 */
public synchronized void multiSelectNode(JCOutlinerNode node, Event ev) {
	boolean bCtrl = ev != null ? ev.controlDown() : false;
	boolean bShift = ev != null ? ev.shiftDown() : false;
	JCOutlinerEvent item_ev = null;
	JCOutlinerInterface caller = scrolled_window != null ?
		(JCOutlinerInterface)scrolled_window : this;
	JCOutlinerNode test_node;

	if (bShift) {
		//
		// Shift key down, select multiples
		//

		// No change if user Shift-clicks on a selected node
		if (selected_nodes.contains(node)) {
			return;
		}

		test_node = (JCOutlinerNode) selected_nodes.lastElement();
		Vector siblings = node.getParent().getChildren();
		JCOutlinerNode endNode = null;

		// Find the end node at which to stop selecting
		int i = 0;
		for (; i < siblings.size(); i++) {
			if (siblings.elementAt(i) == test_node) {
				endNode = node;
				break;
			}
			else if (siblings.elementAt(i) == node) {
				endNode = test_node;
				break;
			}
		}

		// Begin selecting a range of nodes - stop at end node
		for (int j = i; j < siblings.size(); j++) {
			JCOutlinerNode curNode = (JCOutlinerNode) siblings.elementAt(j);

			// If not already selected, set as selected
			if (!curNode.selected) {
				curNode.selected = true;
				// Otherwise, select the clicked node and post begin event
				if (itemListeners.size() > 0) {
					item_ev = new JCOutlinerEvent(caller, curNode, true);

					for (int k = 0; k < itemListeners.size(); k++) {
						JCItemListener l = (JCItemListener) itemListeners.elementAt(k);
						if (l instanceof JCOutlinerListener) {
							((JCOutlinerListener)l).outlinerNodeSelectBegin(item_ev);
							if (item_ev.doit) {
								selected_nodes.addElement(curNode);
								drawNode(curNode, true);
								postNodeSelectEnd(item_ev);
							}
						}
						else {
							// redraw for unvetoable case
							selected_nodes.addElement(curNode);
							drawNode(curNode, true);
						}

						// post itemStateChanged once for all changes since
						// that is the way that the awt list component works
						l.itemStateChanged(item_ev);
					}
				}
				else {
					selected_nodes.addElement(curNode);
					drawNode(curNode, true);
				}
			}

			if (curNode == endNode)
                break;
		}
	}
	else if (bCtrl) {
		//
		// Toggle the node selected variable because CTRL key is down
		//

		if (!node.selected) {
			if (itemListeners.size() > 0) {
				item_ev = new JCOutlinerEvent(caller, node, true);
				for (int j = 0; j < itemListeners.size(); j++) {
					JCItemListener l = (JCItemListener) itemListeners.elementAt(j);
					if (l instanceof JCOutlinerListener) {
						((JCOutlinerListener)l).outlinerNodeSelectBegin(item_ev);
						if (item_ev.doit) {
							// selected the node as long as the listener
							// has not dissallowed it
							node.selected = true;
							selected_nodes.addElement(node);
							drawNode(node, true);
							postNodeSelectEnd(item_ev);
							// post itemStateChanged once for all changes since
							// that is the way that the awt list component
							// works
							l.itemStateChanged(item_ev);
						}
					}
					else {
						// redraw for unvetoable case
						selected_nodes.addElement(node);
						drawNode(node, true);
					}
				}
			}
			else {
				// Select the node if no listeners
				// no need to post since we know we have no listeners
				node.selected = true;
				selected_nodes.addElement(node);
				drawNode(node, true);
			}
		}
		else {
			//
			// Post Unselect event
			//
			if (itemListeners.size() > 0) {
				item_ev = new JCOutlinerEvent(caller, node, false);

				for (int j = 0; j < itemListeners.size(); j++) {
					JCItemListener l = (JCItemListener) itemListeners.elementAt(j);
					if (l instanceof JCOutliner2Listener) {
						((JCOutliner2Listener)l).outlinerNodeUnselectBegin(item_ev);
					}
				}

				if (item_ev.doit == true) {
					// unselect the node
					node.selected = false;
					selected_nodes.removeElement(node);
					drawNode(node, true);

					for (int j = 0; j < itemListeners.size(); j++) {
						JCItemListener l = (JCItemListener) itemListeners.elementAt(j);
						if (l instanceof JCOutliner2Listener) {
							// post "unselect end" event
							((JCOutliner2Listener)l).outlinerNodeUnselectEnd(item_ev);
						}
						// post itemStateChanged
						l.itemStateChanged(item_ev);
					}
				}
			}
			else {
				// no listeners
				// unselect the node
				node.selected = false;
				selected_nodes.removeElement(node);
				drawNode(node, true);
			}
		}
	}

	if (auto_select)
		focus_node = node;
}

/** Returns the node which currently has focus, or null. */
public JCOutlinerNode getFocusNode() { return focus_node; }

/** Sets a node to have the keyboard focus.
 * @param node if null, no the current focus node is unchanged
 * @param ev event which caused the focus change
 */
public void setFocusNode(JCOutlinerNode node, Event ev) {
	if (node == null || (node == focus_node && !multiple_select)) return;

	synchronized (this) {
		JCOutlinerNode old_focus = focus_node;
		focus_node = node;
		drawNode(old_focus, true);
		makeNodeVisible(focus_node);
		drawNode(focus_node, false);
	}
	if (auto_select)
		selectNode(node, ev);
}

/** Gets a list of the nodes which are currently displayed. */
public JCVector getVisibleNodes() { return visible_nodes; }

/** Finds the first node whose label starts with the specified character, or
 * null if none could be found.
 */
public JCOutlinerNode findNode(char c) {
	c = Character.toUpperCase(c);
	JCOutlinerNode node = root;
	for (; node != null; node = getNextVisibleNode(node)) {
		if (BWTUtil.startsWith(node.label, c))
			return node;
	}
	return null;
}

/** Calculates the height of a node (by looking at the node's style). */
public int calculateNodeHeight(JCOutlinerNode node) {
	return calculateStyleHeight(node.style);
}

/** Calculates the height of a node given its style characteristics. */
protected int calculateStyleHeight(JCOutlinerNodeStyle style) {
	if (style == null)
		return 0;

	int	h = 0;
	if (style.font != null)
		h = getToolkit().getFontMetrics(style.font).getHeight();
	if (style.item != null)
		h = Math.max(h, style.item.getHeight(null));
	if (style.item_selected != null)
		h = Math.max(h, style.item_selected.getHeight(null));
	if (style.folder_closed != null)
		h = Math.max(h, style.folder_closed.getHeight(null));
	if (style.folder_closed_selected != null)
		h = Math.max(h, style.folder_closed_selected.getHeight(null));
	if (style.folder_open != null)
		h = Math.max(h, style.folder_open.getHeight(null));
	if (style.folder_open_selected != null)
		h = Math.max(h, style.folder_open_selected.getHeight(null));

	return h;
}

/** Calculates the height based on the node heights. */
protected int preferredHeight() {
	default_style.font = getFont();
	if (node_height == 0) {
		for (int i=0; i < style_list.length; i++)
			node_height =
				Math.max(node_height, calculateStyleHeight(style_list[i]));
	}
	full_node_height = node_height + 2*highlight + spacing;
	int rows = visible_rows > 0 ? visible_rows : num_rows;
	return rows*full_node_height + 2*(border+highlight)
		+ insets.top + insets.bottom;
}

protected int preferredWidth() {
	if (pref_width_internal == 0) {
		calcColumnWidths();
	}
	return (pref_width_internal = data.preferredWidth());
}

/** Repaints the component if Batched is false.
 * @see #setBatched
 */
public synchronized void repaint() {
	if (batched)
		needs_repaint = true;
	else
		super.repaint();
}

void createShortcutIcons() {
	JCImageCreator im = new JCImageCreator(this, SHORTCUT_SIZE, SHORTCUT_SIZE);
	Color fg = getForeground(), bg = getBackground();
	im.setColor('.', fg != null ? fg : Color.black);
	im.setColor(' ', bg != null ? bg : Color.white);
	shortcut_open_icon = im.create(shortcut_open_icon_string);
	shortcut_close_icon = im.create(shortcut_close_icon_string);
}

/** Creates icons and processes nodes. */
public void addNotify() {
	super.addNotify();
	createShortcutIcons();
	processRootNode();
	JCHeader header = getHeader();
	if (getNumColumns() > 1
		|| (header != null && getNumColumns() < header.getLabels().length)) {
		calcColumnWidths();
		pref_width_internal = data.preferredWidth();
	}
}

/** Draws all visible nodes. */
protected void paintComponent(Graphics gc) {
	visible_nodes.removeAllElements();
	root_shortcut = getStyle(root).shortcut;
	getDrawingArea(draw_rect);
	bottom_y = draw_rect.y + draw_rect.height;

	drawNode(root, root_visible ? 0 : -1, root_visible ? 0 : -1, gc);

	//update selected_node list to remove row value if not visible
	for (int i = 0; i < selected_nodes.size(); i++) {
		JCOutlinerNode node = (JCOutlinerNode) selected_nodes.elementAt(i);
		if (visible_nodes.contains(node) == false) {
			node.row = BWTEnum.NOVALUE;
		}
	}
}

/** Sets the state of a folder.
 * @param node if not a folder, no action is taken
 * @param state one of:
 * <pre>
 * FOLDER_CLOSED       Folder closed; no children visible
 * FOLDER_OPEN_NONE    Folder open; no children visible
 * FOLDER_OPEN_FOLDERS Folder open; only folder children visible
 * FOLDER_OPEN_ITEMS   Folder open; only non-folder children visible
 * FOLDER_OPEN_ALL     Folder open; all children visible
 * </pre>
 * @param notify if true, a JCOutlinerEvent is posted for the node
 * @return false if the app disallowed the state change
 * @exception IllegalArgumentException If the value is invalid
 * @see #addActionListener
 * @see #addItemListener
 */
public boolean setNodeState(JCOutlinerNode node, int state, boolean notify) {
	if (node instanceof JCOutlinerFolderNode) {
		OutlinerConverter.checkState(state);
		if (notify) {
			JCOutlinerEvent ev = postActionEvent(last_event, BEGIN, node, state);
			if (ev != null) {
				if (!ev.doit) return false;
				state = ev.new_state;
			}
			if (!shouldBeDrawn(node) || !checkState(state)) return false;
		}
		JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
		int old_rows = (folder == root) ? num_rows : countRows(folder);
		folder.state = state;
		num_rows += countRows(folder) - old_rows;

		// Update variable-sized column's widths
		folderChanged(null);
		repaint();
	}
	if (notify)
		postActionEvent(last_event, END, node, state);
	return true;
}

/** Scrolls the display if necessary to make the node visible.
 * @return false if the node could not be made visible due to its parent's state
 */
public boolean makeNodeVisible(JCOutlinerNode node) {
	if (!shouldBeDrawn(node))
        {
          //System.out.println("shouldnotBeDrawn");
          return false;
        }
	if (visible_nodes.contains(node))
		return true;
	int row, value = vert_origin;

	//JCOutlinerNode first = (JCOutlinerNode) visible_nodes.getFirst(),
	//	last = (JCOutlinerNode) visible_nodes.getLast();
	/*if (first == null
		|| (first != null && node.sequence < first.sequence)) {*/
		row = countPreviousRows(node);
		value = row * full_node_height;
                //System.out.println("makeNodeVisible 1:"+row+"/"+full_node_height);
	/*}
	else if (last != null && node.sequence > last.sequence) {
		//row = countRows(last, node);
		//value = vert_origin + row * full_node_height+10;
                row = countPreviousRows(node);
		value = row * full_node_height;
                System.out.println("makeNodeVisible 2"+row+"/"+full_node_height);
	}
        else
          System.out.println("makeNodeVisible 3");*/

	if (scrolled_window != null)
		scrolled_window.scrollVertical(value);
	return true;
}

/** Checks that a state is valid. */
protected boolean checkState(int state) {
	for (int i=0; i < state_list.length; i++)
		if (state == state_list[i]) return true;
	return false;
}

/** Gets the folder's next state,
 * or BWTEnum.NOVALUE if the node is not a folder.
 */
protected int getNextState(JCOutlinerNode folder) {
	if (!(folder instanceof JCOutlinerFolderNode))
		return BWTEnum.NOVALUE;
	int k;
	for (k=0; k < state_list.length; k++)
		if (folder.getState() == state_list[k]) break;
	k++;
	if (k >= state_list.length)
		k = 0;
	return state_list[k];
}

/** Returns the next node (not necessarily visible).
 * @see #getNextVisibleNode
 */
public JCOutlinerNode getNextNode(JCOutlinerNode node) {
	if (node == null || node.sequence >= node_list.size()-1) return null;
	return (JCOutlinerNode) node_list.elementAt(node.sequence+1);
}

/** Returns the next visible node, or null if none was found. */
public JCOutlinerNode getNextVisibleNode(JCOutlinerNode node) {
	if (node == null) return null;
	for (int i=node.sequence+1, len = node_list.size(); i < len; i++) {
		node = (JCOutlinerNode) node_list.elementAt(i);
		if (shouldBeDrawn(node))
			return node;
	}
	return null;
}

/** Returns the previous node (not necessarily visible).
 * @see #getPreviousVisibleNode
 */
public JCOutlinerNode getPreviousNode(JCOutlinerNode node) {
	if (node == null || node.sequence == 0) return null;
	return (JCOutlinerNode) node_list.elementAt(node.sequence-1);
}

/** Returns the previous visible node, or null if none was found. */
public JCOutlinerNode getPreviousVisibleNode(JCOutlinerNode node) {
	if (node == null) return null;
	for (int i=node.sequence-1; i >= 0; i--) {
		node = (JCOutlinerNode) node_list.elementAt(i);
		if (shouldBeDrawn(node))
			return node;
	}
	return null;
}

/** Sets the focus on the next node after the current node which has focus. */
protected boolean nextFocus(Event ev) {
	setFocusNode(getNextVisibleNode(focus_node), ev);
	return true;
}

/** Sets the focus on the previous node before the current node which has focus. */
protected boolean previousFocus(Event ev) {
	setFocusNode(getPreviousVisibleNode(focus_node), ev);
	return true;
}

/**
 * Adds the specified item listener to receive node events.
 * If the listener implements JCOutlinerListener (an JCItemListener subclass),
 * it will be passed JCOutlinerEvents.
 * @see JCItemEvent
 * @see JCOutlinerEvent
 * @see JCOutliner2Listener
 */
public void addItemListener(JCItemListener l) { itemListeners.addUnique(l); }

/**
 * Removes the specified item listener so it no longer receives node events.
 */
public void removeItemListener(JCItemListener l) { itemListeners.removeElement(l); }

/**
 * Adds the specified action listener to receive action events.
 * Action events occur when a node is double-clicked.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) { actionListeners.addUnique(l); }

/**
 * Removes the specified action listener so it no longer receives
 * action events.
 * @see #addActionListener
 */
public void removeActionListener(JCActionListener l) { actionListeners.removeElement(l); }

/** Posts a JCOutlinerEvent if the node is a folder,
 * or a JCActionEvent if the node is not a folder
 * @param stage BEGIN or END
 * @return the posted event, or null if no itemListeners/actionListeners
 * have been registered
 * @see #addActionListener
 * @see #addItemListener
 */
protected JCOutlinerEvent postActionEvent(Event ev, int stage,
										  JCOutlinerNode node, int new_state) {
	JCOutlinerEvent item_ev = null;
	JCOutlinerInterface caller =
		scrolled_window != null ? (JCOutlinerInterface) scrolled_window : this;
	if (node instanceof JCOutlinerFolderNode && itemListeners.size() > 0) {
		item_ev = new JCOutlinerEvent(caller, node, new_state);
		for (int i=0; i < itemListeners.size(); i++) {
			JCItemListener l = (JCItemListener) itemListeners.elementAt(i);
			if (stage == END)
				l.itemStateChanged(item_ev);
			if (l instanceof JCOutlinerListener) {
				JCOutlinerListener o = (JCOutlinerListener) l;
				if (stage == BEGIN)
					o.outlinerFolderStateChangeBegin(item_ev);
				else
					o.outlinerFolderStateChangeEnd(item_ev);
			}
		}
	}

	// Post ActionEvent for non-folder nodes
	if (stage == END && !(node instanceof JCOutlinerFolderNode)) {
		JCActionEvent a =
			new JCActionEvent(caller, JCActionEvent.ACTION_PERFORMED, null);
		for (int i=0; i < actionListeners.size(); i++)
			((JCActionListener)actionListeners.elementAt(i)).actionPerformed(a);
	}

	return item_ev;
}

/** Called when the node is double-clicked. */
protected boolean doubleClickAction(JCOutlinerNode node, Event ev) {
	return setNodeState(node, getNextState(node), true);
}

/** If the down arrow is hit, calls nextFocus.<p>
 * If the up arrow is hit, calls previousFocus.<p>
 * If RETURN is hit, calls setNodeState for the focus node.<p>
 * If space key is hit, selects focus node.
 * @see #nextFocus
 * @see #previousFocus
 * @see #setNodeState
 */
public boolean keyDown(Event ev, int key) {
/* JDK110_START */
	boolean no_error = true;
	if (ev.controlDown() /*|| (ev.modifiers & ev.META_MASK)>0*/) {
		
		if (cmpKeyStroke(LocaleInfo.COPYKEY, key)) 
				copyToClipboard(ev);
		else if (bCopy)
		{
			if (cmpKeyStroke(LocaleInfo.CUTKEY, key)) {
				cutToClipboard(ev);
			}
			else if (cmpKeyStroke(LocaleInfo.PASTEKEY, key)) {
				no_error = pasteFromClipboard(ev);
			}
		}
//		else
//		{
//			System.err.println("CNTR gedrückt");
//			if(focus_node != null && focus_node.getLevel() > 0) {
//	          JCVector Vec = focus_node.getParent().getChildren();
//	          int i = Vec.indexOf(focus_node);
//	          if (i>=0)
//	        	  selected_nodes.removeElement(focus_node);
//	          System.err.println("Selectiert:"+selected_nodes);
//	          setFocusNode((JCOutlinerNode)selected_nodes.at(i),ev);
//			}
//		}
		return true;
	}
/* JDK110_END */	
        if ((ev.modifiers & ev.ALT_MASK)>0)
        {
//          System.err.println("ALT gedrückt");
          return false;
        }
//        if ((ev.modifiers & ev.META_MASK)>0)
//        {
//          System.err.println("META gedrückt");
//          return false;
//        }

        if ((ev.modifiers & ev.SHIFT_MASK)>0)
        {
          //System.err.println("SHIFT gedrückt");
          if(ev.key == Event.DOWN)
          {
            if(focus_node != null && focus_node.getLevel() > 0) {
              JCVector Vec = focus_node.getParent().getChildren();
              int i = Vec.indexOf(focus_node);
              if(i == Vec.size()-1)
                return false;
              else {
                setFocusNode((JCOutlinerNode)Vec.elementAt(i + 1), ev);
                return true;
              }
            }
            else
              return false;
          }
          else if(ev.key == Event.UP)
          {
            if(focus_node != null && focus_node.getLevel() > 0) {
              JCVector Vec = focus_node.getParent().getChildren();
              int i = Vec.indexOf(focus_node);
              if(i < 1)
                return false;
              else {
                setFocusNode((JCOutlinerNode)Vec.elementAt(i - 1), ev);
                return true;
              }
            }
            else
              return false;
          }
          else if (ev.key == Event.LEFT)
          {
            for(int i = 0; i < selected_nodes.size(); i++) {
              JCOutlinerNode Nod = (JCOutlinerNode)selected_nodes.elementAt(i);
              if(Nod instanceof JCOutlinerFolderNode) {
                ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_CLOSED);
                folderChanged(Nod);
              }
            }
            return true;
          }
          else if (ev.key == Event.RIGHT)
          {
            for(int i = 0; i < selected_nodes.size(); i++) {
              JCOutlinerNode Nod = (JCOutlinerNode)selected_nodes.elementAt(i);
              if(Nod instanceof JCOutlinerFolderNode) {
                ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_OPEN_ALL);
                folderChanged(Nod);
              }
            }
          }
        }
        else
        if(ev.key == Event.DOWN)
          return nextFocus(ev);
        else if(ev.key == Event.UP)
          return previousFocus(ev);
        else if (ev.key == Event.LEFT)
        {
          for (int i=0;i<selected_nodes.size();i++)
          {
            JCOutlinerNode Nod = focus_node.getParent();//(JCOutlinerNode)selected_nodes.elementAt(i);
            if(Nod instanceof JCOutlinerFolderNode) {
              ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_CLOSED);
              setFocusNode(Nod,ev);
              folderChanged(Nod);
            }
          }
          return true;
        }
        else if (ev.key == Event.RIGHT)
        {
          for (int i=0;i<selected_nodes.size();i++)
          {
            JCOutlinerNode Nod = (JCOutlinerNode)selected_nodes.elementAt(i);
            if(Nod instanceof JCOutlinerFolderNode) {
              ((JCOutlinerFolderNode)Nod).setState(BWTEnum.FOLDER_OPEN_ALL);
              folderChanged(Nod);
              if (Nod.getChildren() != null && Nod.getChildren().size()>0)
                return nextFocus(ev);
            }
          }
          return true;
        }

        if (ev.key == Event.PGDN)
        {
          //System.out.println("PGDN gedrückt");
          //JCVector Vec=((JCOutlinerNode)selected_nodes.getFirst()).getParent().getChildren();
          setFocusNode((JCOutlinerNode)((JCOutlinerNode)selected_nodes.getFirst()).getParent().getChildren().getLast(),ev);
          return true;
        }
        else if (ev.key == Event.PGUP)
        {
          //System.out.println("PGDN gedrückt");
          setFocusNode((JCOutlinerNode)((JCOutlinerNode)selected_nodes.getFirst()).getParent().getChildren().getFirst(),ev);
          return true;
        }
        else if (ev.key == 10)
		return setNodeState(focus_node, getNextState(focus_node), true);
	else if (ev.key == (int)' ')  {
		makeNodeVisible(focus_node);
		selectNode(focus_node, ev);
		return true;
	}
	// Select node which starts with this char
	else {
		JCOutlinerNode node = findNode((char)(key));
		if (node != null) {
			makeNodeVisible(node);
			setFocusNode(node, ev);
			selectNode(node, ev);
			return true;
		}
	}
	return super.keyDown(ev, key);
}

/** Gets the node in which the event occurred. */
public JCOutlinerNode eventToNode(Event ev) {
	return eventToNode(ev, false);
}

/** Gets the node in which the event occurred.
 * @param inside if true, the event must be inside the node
 */
public JCOutlinerNode eventToNode(Event ev, boolean inside) {
	if (full_node_height == 0) return null;
	JCOutlinerNode node = findNode(root,
				(ev.y - border - insets.top + vert_origin) / full_node_height);
	if (node != null && inside) {
		getBounds(node, draw_rect);
		if (!draw_rect.inside(ev.x, ev.y))
			node = null;
	}
	return node;
}

/** Returns true if the node is a folder and the event is within the
 * folder's shortcut button.
 */
public boolean eventInShortcut(Event ev, JCOutlinerNode node) {
	if (node instanceof JCOutlinerFolderNode) {
		JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
		if (folder.shortcut_x != BWTEnum.NOVALUE) {
			JCComponent.setBounds(draw_rect,
								  folder.shortcut_x - SHORTCUT_SIZE/2,
								  folder.shortcut_y - SHORTCUT_SIZE/2,
								  SHORTCUT_SIZE, SHORTCUT_SIZE);
			return draw_rect.inside(ev.x, ev.y);
		}
	}
	return false;
}

/** Process mouse down events.<p>
 * If a node is clicked, setFocusNode and selectNode are called.<p>
 * If a node is double-clicked, calls doubleClickAction.
 * If a node is a shortcut button is clicked, calls setNodeState.
 * @see #setFocusNode
 * @see #selectNode
 * @see #doubleClickAction
 * @see #setNodeState
 */
public boolean mouseDown(Event ev, int x, int y) {
	super.mouseDown(ev, x, y);
/* JDK110_START */
	if (BWTUtil.getMouseButton(ev) == 3 && show_popup == true) {
		// indicates that Right mouse button was depressed
		createPopup(this, x, y);
	}
/* JDK110_END */
	if (BWTUtil.getMouseButton(ev) != 1) return false;
	last_event = ev;
	JCOutlinerNode node = eventToNode(ev);
	if (node == null)
		return true;
	if (ev.controlDown())
	{
		if(node != null && node.getLevel() > 0) {
	          JCVector Vec = selected_nodes;//node.getParent().getChildren();
	          int i = Vec.indexOf(node);
	          if (i>=0)
	          {
	        	  return true;
//	        	  JCOutlinerNode NodSel=(JCOutlinerNode)selected_nodes.at(i>Vec.size()-2 ? i-1:i+1);
//	        	  setFocusNode(NodSel,ev);
//		          selected_nodes.removeElement(node);
//	        	  System.err.println("SelectiertM:"+NodSel+"/"+selected_nodes);
//		          repaint();
	          }
			}
		
	}
	
	// Check for folder shortcut
	if (eventInShortcut(ev, node))
		return setNodeState(node, getNextState(node), true);

	// Check that click was inside node bounds
	getBounds(node, draw_rect);
	if (!draw_rect.inside(x, y))
		return false;

	// Check for double-click
	boolean double_click = false;
	if (ev.clickCount == 2 && node.selected)
		double_click = true;

	setFocusNode(node, ev);
	if (!double_click && node.selected && !multiple_select)
		return true;
	if (!auto_select)
		selectNode(node, ev);

	boolean status = true;
	if (double_click)
		status = doubleClickAction(node, ev);
	last_event = null;
	return status;
}

/* DRAG & DROP START */
int				drag_drop = DRAGDROP_NONE;
static JCOutlinerNode	drag_node, drop_node;

/** Gets the DragAndDropPolicy value.
 * @see setDragAndDropPolicy
 */
public int getDragAndDropPolicy() { return drag_drop; }

/** Sets whether the outliner can be used as a drag and/or drop site.
 * @param v DRAGDROP_NONE (default), DRAGDROP_TARGET, DRAGDROP_SOURCE, DRAGDROP_ALL
 */
public void setDragAndDropPolicy(int v) {
	drag_drop = v;
/* JDK110_START */
	if (v != DRAGDROP_NONE) {
		enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}
/* JDK110_END */
}

/** Returns true if the node is a potential target for a drag & drop
 * operation by the drag node. It returns false if the nodes are equal, or
 * if the drag_node is the drop node's parent.
 */
protected boolean isDropTarget(JCOutlinerNode drag_node, JCOutlinerNode node) {
	if (drag_node == null || node == null || drag_node == node) return false;
	JCOutlinerNode parent = node.parent;
	while (parent != null && parent != drag_node)
		parent = parent.parent;
	return parent == null;
}

/**
 * Locates the top-most non-Container that contains the position.
 * @return null if none could be found
 */
Component locateComponent(Container c, int x, int y) {
	if (!c.inside(x, y))
	    return null;

	for (int i = c.countComponents()-1; i >= 0; i--) {
	    Component comp = c.getComponents()[i];
	    if (comp.isVisible() && comp.isEnabled()
			&& comp.inside(x-comp.location().x, y-comp.location().y)) {
			if (comp instanceof Container)
				return locateComponent((Container)comp,
									   x-comp.location().x, y-comp.location().y);
			return comp;
		}
	}
	return null;
}

boolean processDropOver(Event ev) {
	Window w = BWTUtil.getWindow(this);
	Point p = BWTUtil.translateToParent(w, this, ev.x, ev.y);
	Component c = locateComponent(w, p.x, p.y);
	if (c == this || !(c instanceof JCOutlinerComponent)) return false;
	p = BWTUtil.translateFromParent(w, c, p.x, p.y);
	ev.x = p.x; ev.y = p.y;
	return ((JCOutlinerComponent)c).dropOver(ev);
}

public boolean mouseDrag(Event ev, int x, int y) {
	if (BWTUtil.getMouseButton(ev) != 1) return false;
  if (drag_drop == DRAGDROP_TARGET) return false;
	if (inside(x, y) && dropOver(ev)) return true;
	return processDropOver(ev);
}

/** Called as the user drags over the outliner.
 * @return true if the drag was handled
 */
public boolean dropOver(Event ev) {
	if (drag_drop == DRAGDROP_NONE) return false;
	JCOutlinerNode node = eventToNode(ev, true);
	if (drag_node == null)
		drag_node = node;
	else if (drag_node != root && drop_node == null)
		drop_node = isDropTarget(drag_node, node) ? node : null;
	else if (drop_node != null && drop_node != node) {
		drop_node.selected = false;
		drawNode(drop_node, true);
		drop_node = isDropTarget(drag_node, node) ? node : null;
	}

	if (drag_node != root && drag_node != null) {
		int cursor =
			(drop_node != null) ? Frame.HAND_CURSOR : Frame.WAIT_CURSOR;
		if (drop_node == null && drag_node != root)
			cursor = Frame.HAND_CURSOR;
		setCursor(cursor);
	}

	if (drag_node != root && drop_node != null) {
		selectNode(drop_node);
		makeNodeVisible(drop_node);
	}
	return drop_node != null;
}

public boolean mouseUp(Event ev, int x, int y) {
/* JDK110_START */
	if ((BWTUtil.getMouseButton(ev) == 3) && show_popup == true) {
		 return false;
	}
/* JDK110_END */

	if (BWTUtil.getMouseButton(ev) != 1) return false;

	if (inside(x, y) && drop(ev)) return true;
	return processDrop(ev);
}

boolean processDrop(Event ev) {
	Window w = BWTUtil.getWindow(this);
	Point p = BWTUtil.translateToParent(w, this, ev.x, ev.y);
	Component c = locateComponent(w, p.x, p.y);
	if (c == this || !(c instanceof JCOutlinerComponent)) return false;
	p = BWTUtil.translateFromParent(w, c, p.x, p.y);
	ev.x = p.x; ev.y = p.y;
	return ((JCOutlinerComponent)c).drop(ev);
}

/** Called when the user finishes the drag & drop operation.
 * @return true if the drop was handled
 */
public boolean drop(Event ev) {
	if ((drag_drop == DRAGDROP_NONE) || (drag_drop == DRAGDROP_SOURCE)){
	  	setCursor(Frame.DEFAULT_CURSOR);
  		drag_node = drop_node = null;
      return false;
  }
	if (ev.target == this && drag_node == null) return false;
	if (drag_node == drop_node || drop_node == null || drag_node == root) {
		if (drag_node != root && drop_node != null) {
			drop_node.selected = true;
			drawNode(drop_node, true);
		}
		setCursor(Frame.DEFAULT_CURSOR);
		drag_node = drop_node = null;
		return true;
	}
	JCOutlinerFolderNode folder =
		(drop_node instanceof JCOutlinerFolderNode) ?
		(JCOutlinerFolderNode) drop_node : drop_node.getParent();
	int index = (drop_node == folder) ? 0 :
		folder.getChildren().indexOf(drop_node)+1;
	if (ev.target != this)
		drag_node = (JCOutlinerNode) drag_node.clone();
	folder.addNode(drag_node, index);
	folderChanged(folder);
	drop_node.selected = false;
	selected_nodes.removeAllElements();
	selectNode(drag_node);
	setCursor(Frame.DEFAULT_CURSOR);
	repaint();
	drag_node = drop_node = null;
	return true;
}

/* DRAG & DROP END */

JCOutlinerNodeStyle getStyle(JCOutlinerNode node) {
	return (node != null && node.style != null) ? node.style : default_style;
}

/**
 * Gets the physical bounding rectangle of a node (icon and label)
 * (ie adjusted for scrolling and insets).
 * @param rect if not null, its members are set
 * @return null if rect is null, otherwise the bounding rectangle
 */
public Rectangle getBounds(JCOutlinerNode node, Rectangle rect) {
	getDrawingArea(draw_rect);
	int x = draw_rect.x + node.indent * node_indent -
		horiz_origin + getColumnLeftMargin(0);
	int y = draw_rect.y + node.row * full_node_height - vert_origin;
	JCOutlinerNodeStyle style = getStyle(node);
	Font font = (style.font != null ? style.font : getFont());
	int width = BWTUtil.getWidth(node.label, this, font) + 2*highlight + 1;
	Image icon = getIcon(node);

	if (!root_visible && root_shortcut)
		x += node_indent / 2;

	if (icon != null)
		width += icon.getWidth(null) + getStyle(node).icon_spacing;

	if (rect != null) {
		JCComponent.setBounds(rect, x, y, width, full_node_height);
		return null;
	}
	return new Rectangle(x, y, width, full_node_height);
}

/** Gets the appropriate image for a node. */
protected Image getIcon(JCOutlinerNode node) {
	JCOutlinerNodeStyle style = getStyle(node);
	if (node instanceof JCOutlinerFolderNode) {
		int state = ((JCOutlinerFolderNode)node).state;
		if (state == FOLDER_CLOSED)
			return (node.selected && style.folder_closed_selected != null) ?
				style.folder_closed_selected : style.folder_closed;
		else
			return (node.selected && style.folder_open_selected != null) ?
				style.folder_open_selected : style.folder_open;
	}
	else {
		return (node.selected && style.item_selected != null) ?
			style.item_selected : style.item;
	}
}

/** Checks the node's folder to determine whether the node should be drawn. */
protected boolean shouldBeDrawn(JCOutlinerNode node) {
	if (node == root)
		return root_visible ? true : false;
	if (node == null || node.parent == null) return false;
	int state = node.parent.state;
	if (state == FOLDER_OPEN_ALL)
		return (node.parent != root) ? shouldBeDrawn(node.parent) : true;
	else if (state == FOLDER_CLOSED || state == FOLDER_OPEN_NONE)
		return false;
	else if (state == FOLDER_OPEN_FOLDERS
		&& !(node instanceof JCOutlinerFolderNode))
		return false;
	else if (state == FOLDER_OPEN_ITEMS
			 && node instanceof JCOutlinerFolderNode)
		return false;
	return (node.parent != root) ? shouldBeDrawn(node.parent) : true;
}

/** Checks whether the node should be visible. */
protected boolean isVisible(JCOutlinerNode node) {
	getDrawingArea(draw_rect);
	int y = draw_rect.y + node.row * full_node_height - vert_origin;
	return y + full_node_height > draw_rect.y
		&& y < draw_rect.y + draw_rect.height;
}

/*
 * get a gc that's a copy of the passed in GC but clipped to the first column
 */
protected Graphics getFirstColumnGC(Graphics gc) {
	if (gc == null) {
		return(null);
	}
	Rectangle clip_rect = getPaintRect();
	Rectangle rect;
/* JDK110_START */
	if (clip_rect != null) {
		rect = new Rectangle(clip_rect);
	}
	else {
		rect = new Rectangle(gc.getClipRect());
	}
/* JDK110_END */
/* JDK102_START
	Rectangle tmp;
	if (clip_rect != null) {
		tmp = clip_rect;
	}
	else {
		tmp = gc.getClipRect();
	}
	rect = new Rectangle(tmp.x, tmp.y, tmp.width, tmp.height);
 JDK102_END */

	rect.width = getColumnWidth(0);
	Graphics first_column_gc = gc.create();
	first_column_gc.clipRect(rect.x, rect.y, rect.width, rect.height);
	return(first_column_gc);
}


/** Draws a single node.
 * @param clear if true, the node's rectangle is cleared to the background color
 * @return false if the node is outside the drawing area
 */
protected boolean drawNode(JCOutlinerNode node, boolean clear) {
	if (!isDisplayable() || !isShowing() || !shouldBeDrawn(node)) return false;
	getDrawingArea(draw_rect);
	Graphics draw_gc = getGraphics();
    draw_gc.clipRect(draw_rect.x, draw_rect.y, draw_rect.width, draw_rect.height);
	boolean s = drawNode(node, getFirstColumnGC(draw_gc), draw_gc, clear);
	draw_gc.dispose();
	return s;
}

/** Draws a single node.
 * @deprecated use drawNode(node, gc, gc, clear)
 */
protected boolean drawNode(JCOutlinerNode node, Graphics gc, boolean clear) {
	return drawNode(node, gc, gc, clear);
}


/** Draws a single node.
 * @param clear if true, the node's rectangle is cleared to the background color
 * @return false if the node is outside the drawing area
 */
protected boolean drawNode(JCOutlinerNode node,
						   Graphics first_column_gc, Graphics gc,
						   boolean clear) {

	if (TRACE) {
		System.out.println("JCOutlinerComponent...Drawing single node");
	}
	if (node == null) return false;
	if (node == root && !root_visible) return false;

	if (!isVisible(node))
		return false;

	getBounds(node, draw_rect);
	if (getPaintRect() != null && !draw_rect.intersects(getPaintRect()))
		return true;

	int draw_width = getDrawingAreaWidth() - draw_rect.x;

	JCOutlinerNodeStyle style = getStyle(node);
	Font usedFont = (style.font != null) ? style.font : getFont();
	int label_width = BWTUtil.getWidth(node.label, this, usedFont) + 2*highlight + 1;
	Image icon = getIcon(node);
	if (icon != null)
		label_width += icon.getWidth(null) + style.icon_spacing;

	if (isEnabled() && node == focus_node && !node.selected) {
		gc.setColor(Color.yellow);
		BWTUtil.drawDashedRect(gc, draw_rect.x, draw_rect.y,
							   label_width-1, draw_rect.height-1);
	}

	// Select this node
	if (node.selected) {
		Color c = style.background_selected;
/* JDK110_START */
		if (c == null && use_system_colors)
			c = SystemColor.textHighlight;
/* JDK110_END */
		gc.setColor(c != null ? c : getForeground());
		gc.fillRect(draw_rect.x, draw_rect.y,
						   label_width, draw_rect.height);
		if (node == focus_node) {
			gc.setColor(Color.yellow);
			BWTUtil.drawDashedRect(gc, draw_rect.x, draw_rect.y,
								   label_width-1, draw_rect.height-1);
		}
		c = style.foreground_selected;
/* JDK110_START */
		if (c == null && use_system_colors)
			c = SystemColor.textHighlightText;
/* JDK110_END */
		gc.setColor(c != null ? c : getBackground());
	}
	else if (clear) {
		gc.setColor(getBackground());
		gc.fillRect(draw_rect.x, draw_rect.y,
					label_width, draw_rect.height);
		Color c = style.foreground;
		gc.setColor(c != null ? c : getForeground());
	}
	else {
		Color c = style.foreground;
		gc.setColor(c != null ? c : getForeground());
	}

	gc.setFont(style.font != null ? style.font : getFont());
	draw_rect.translate(highlight, highlight);
	draw_rect.width -= 2 * highlight;
	draw_rect.height -= 2 * highlight;

	if (icon != null) {
		int y = draw_rect.y + (draw_rect.height - icon.getHeight(null)) / 2;
		first_column_gc.drawImage(icon, draw_rect.x, y, this);
		draw_rect.x += icon.getWidth(null) + style.icon_spacing;
	}

	// Have the MultiColumnData Class render the text of the node
	data.draw(gc, node.label, draw_rect);
	return true;
}

// Determines if the "i"'s entry is the last entry given the folder's state
private boolean isLastEntry(JCOutlinerFolderNode folder, int i) {
	if (folder.state == FOLDER_OPEN_ALL)
		return i == folder.children.size()-1;

	/* if FOLDER_OPEN_FOLDERS look for more folders
	 * if FOLDER_OPEN_ITEMS look for more items
	 */
	for (int j=i+1; j < folder.children.size(); j++) {
		JCOutlinerNode nextChild = (JCOutlinerNode)folder.children.elementAt(j);
		if (nextChild instanceof JCOutlinerFolderNode
			&& folder.state == FOLDER_OPEN_FOLDERS)
			return false;
		if (!(nextChild instanceof JCOutlinerFolderNode)
			 && folder.state == FOLDER_OPEN_ITEMS)
			return false;
	}

	return true;
}

/** Draws a node and its children.
 * @param indent indentation level at which to draw node
 * @param row row at which to draw node
 * @return the number of rows this node took to display
 */
protected int drawNode(JCOutlinerNode node, int indent, int row, Graphics gc) {
	if (node == null)
		return 0;

	if (TRACE) {
		System.out.println("JCOutlinerComponent...Drawing node tree");
	}

	int rows = 1;

	node.indent = indent;
	node.row = row;

	Graphics column_gc = getFirstColumnGC(gc);

	// Draw this node
	if (drawNode(node, column_gc, gc, false))
		visible_nodes.addElement(node);
	getBounds(node, draw_rect);

	// Draw a folder's children if it is visible and open
	if (draw_rect.y <= bottom_y && node instanceof JCOutlinerFolderNode) {
		JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
		int state = folder.state;
		if (folder.children == null
			|| state == FOLDER_CLOSED || state == FOLDER_OPEN_NONE)
			return rows;

		// Draw children
		int y = draw_rect.y + full_node_height, f2 = full_node_height/2;
		int x = draw_rect.x;
		for (int i=0; i < folder.children.size() && y <= bottom_y; i++) {
			JCOutlinerNode child =
				(JCOutlinerNode)folder.children.elementAt(i);
			if (!shouldBeDrawn(child))
				continue;
			int subrows = drawNode(child, indent+1, row+rows, gc);

			if (node != root
				|| root_shortcut
				|| (node == root && root_visible)) {
				int line_x = x + node_indent/2, y2 = y + full_node_height/2;

				// Set color to line_color
				JCOutlinerNodeStyle style = getStyle(child);
				column_gc.setColor(style.line_color != null ?
							style.line_color : getForeground());

				// Horizontal line
				column_gc.drawLine(line_x, y2, line_x+node_indent/2-1, y2);

				// Vertical line
				if (isLastEntry(folder, i))
					column_gc.drawLine(line_x, y, line_x, y+f2);
				else
					column_gc.drawLine(line_x, y, line_x, y+subrows*full_node_height+f2);
				if (isVisible(child))
					drawShortcut(child, column_gc, line_x, y2);
			}
			rows += subrows;
			y += subrows * full_node_height;
		}
	}

	column_gc.dispose();

	return rows;
}

/** Draws a folder's shortcut button. */
protected void drawShortcut(JCOutlinerNode node, Graphics gc, int x, int y) {
	if (!(node instanceof JCOutlinerFolderNode))
		return;
	JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
	JCOutlinerNodeStyle style = getStyle(node);

	if (style.shortcut) {
		Image image = (folder.state == FOLDER_CLOSED) ?
			shortcut_open_icon : shortcut_close_icon;
		gc.drawImage(image, x-SHORTCUT_SIZE/2, y-SHORTCUT_SIZE/2, this);
		folder.shortcut_x = x;
		folder.shortcut_y = y;
	}
	else
		folder.shortcut_x = BWTEnum.NOVALUE;
}

/** Returns the number of visible nodes and subnodes in a folder.
 */
protected int countRows(JCOutlinerNode node) {
	if (node == null || (node != root && node.parent == null))
		return 0;
	int state = (node != root) ? node.parent.state : node.getState();
	if (state == FOLDER_OPEN_FOLDERS
		&& !(node instanceof JCOutlinerFolderNode))
		return 0;
	else if (state == FOLDER_OPEN_ITEMS
			 && node != root
			 && node instanceof JCOutlinerFolderNode)
		return 0;
	else if (!(node instanceof JCOutlinerFolderNode))
		return 1;

	state = node.getState();
	JCVector children = node.getChildren();
	if (state == FOLDER_CLOSED || state == FOLDER_OPEN_NONE
		|| children == null)
		return 1;

	int rows = 1;
	for (int i=0, len = children.size(); i < len; i++)
		rows += countRows((JCOutlinerNode)children.elementAt(i));
	return rows;
}

/** Returns the number of nodes and subnodes in a folder.
 */
protected int countChildren(JCOutlinerNode node) {
	if (node == null || (node != root && node.parent == null))
		return 0;
	if (!(node instanceof JCOutlinerFolderNode))
		return 1;

	JCVector children = node.getChildren();
	if (children == null)
		return 1;

	int rows = 1;
	for (int i=0, len = children.size(); i < len; i++)
		rows += countChildren((JCOutlinerNode)children.elementAt(i));
	return rows;
}

/** Returns the number of visible nodes prior to a node.
 */
protected int countPreviousRows(JCOutlinerNode node) {
	return countRows(root, node);
}

/** Returns the number of visible nodes between 2 nodes.
 */
protected int countRows(JCOutlinerNode from, JCOutlinerNode to) {
	for (int i=from.sequence, rows=0, len = node_list.size(); i < len; i++) {
		JCOutlinerNode node = (JCOutlinerNode) node_list.elementAt(i);
		if (node == to)
			return rows;
		if (shouldBeDrawn(node)) rows++;
	}
	return 0;
}

/** Returns the number of nodes and subnodes in a folder, regardless of visibility
 * @param list if not null, the node is added to it
 */
protected int countAllNodes(JCOutlinerNode node, JCVector list) {
	if (node == null)
		return 0;
	if (list != null) {
		node.sequence = list.size();
		node.outliner = this;
		list.addElement(node);
	}
	if (!(node instanceof JCOutlinerFolderNode))
		return 1;

	int rows = 1;
	JCVector children = node.getChildren();
	if (children != null)
		for (int i=0, len = children.size(); i < len; i++)
			rows += countAllNodes((JCOutlinerNode)children.elementAt(i), list);
	return rows;
}

/** Returns a visible node at a position.
 * If not present, its visible children are checked.
 */
protected JCOutlinerNode findNode(JCOutlinerNode node, int row) {
	if (node == null || node.row == row)
		return node;
	if (!(node instanceof JCOutlinerFolderNode))
		return null;
	JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
	if (folder.children == null
		|| folder.state == FOLDER_CLOSED
		|| folder.state == FOLDER_OPEN_NONE)
		return null;
	JCOutlinerNode child, found;
	for (int i=0, len = folder.children.size(); i < len; i++) {
		child = (JCOutlinerNode) folder.children.elementAt(i);
		if (!shouldBeDrawn(child))
			continue;
		found = findNode(child, row);
		if (found != null)
			return found;
	}
	return null;
}

/** JCSortInterface comparison method.
 * Compares nodes based on their labels.
 */
public boolean compare(Object o1, Object o2) {
	JCOutlinerNode node1 = (JCOutlinerNode) o1;
	o1 = node1.label;
	if (o1 instanceof Vector)
		o1 = sort_column < ((Vector)o1).size() ?
			((Vector)o1).elementAt(sort_column) : null;
	else if (sort_column > 0)
		o1 = null;

	JCOutlinerNode node2 = (JCOutlinerNode) o2;
	o2 = node2.label;
	if (o2 instanceof Vector)
		o2 = sort_column < ((Vector)o2).size() ?
			((Vector)o2).elementAt(sort_column) : null;
	else if (sort_column > 0)
		o2 = null;

	if (o1 == null && o2 == null) {
		// consider that these are equal no matter what the compare
		// method for the sake of gnats 5136
		return true;
	}
	return JCqsort.gt(o1, o2, sort_method);
}

/**
 * Sorts the open nodes in the list based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 * @param direction sort direction: JCqsort.ASCENDING or DESCENDING
 */
public void sort(int column, JCSortInterface sort_if, int direction) {
	if (!(root instanceof JCOutlinerFolderNode)) return;
	sort_column = column;
	sort_direction = direction;
	sort_method = sort_if;
	sort((JCOutlinerFolderNode)root);
	processRootNode();
	repaint();
	requestFocus();
}

/** Sorts the nodes in a folder. */
void sort(JCOutlinerFolderNode folder) {
	if (folder == null || (folder != root && folder.parent == null)
		|| folder.children == null || folder.state == FOLDER_CLOSED)
		return;

	(new JCqsort(folder.children, this)).sort(0, sort_direction);

	for (int i=0, len = folder.children.size(); i < len; i++) {
		Object node = folder.children.elementAt(i);
		if (node instanceof JCOutlinerFolderNode)
			sort((JCOutlinerFolderNode)node);
	}
}

		//
		// JCMultiColumnInterface Implementation
		//
		// Invoked commonly by JCHeader and JCMultiColumn data
		//


/**
 * Calculates the preferred width of a node's column, excluding the left
 * and right margins
 */
protected int calcWidth(JCOutlinerNode node, int col) {
	Object label = node.label;
	if (label == null) return 0;
	if (col > 0 && (!(label instanceof Vector) || BWTUtil.is_jcstring(label)))
		return 0;

	if (label instanceof Vector && !BWTUtil.is_jcstring(label)) {
		if (col >= ((Vector)label).size()) return 0;
		label = ((Vector)label).elementAt(col);
	}
	JCOutlinerNodeStyle style = getStyle(node);
	Font usedFont = (style.font != null ? style.font : getFont());
	int w = BWTUtil.getWidth(label, this, usedFont);
	if (col > 0)
		return w;
	Image icon = getIcon(node);
	if (icon != null)
		w += icon.getWidth(null) + getStyle(node).icon_spacing;
	return w + node.getLevel() * node_indent;
}

/**
 * Calculates the width of a column for all nodes.
 */
public int calcWidth(int col) {
	int w = 0;
	for (int i = 0, len = node_list.size(); i < len; i++) {
		JCOutlinerNode node = (JCOutlinerNode) node_list.elementAt(i);
		w = Math.max(w, calcWidth(node, col));
	}
	return w + getColumnLeftMargin(col) + getColumnRightMargin(col);
}

/** Gets a list of the column widths.
 * @see #setColumnWidths
 */
public int[] getColumnWidths() { return data.getColumnWidths(); }

/** Sets the list of column widths. If a column's value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.<p>
 * This value may be set from an HTML file using a PARAM tag with a "ColumnWidths"
 * name and a comma-separated list of ints.
 */
public void setColumnWidths(int[] widths) {
	data.setColumnWidths(widths);
	updateParent();
	repaint();
}

/** Gets the current width of a column, or 0 if the column does not exist.
 */
public int getColumnWidth(int col) { return data.getColumnWidth(col); }

/** Sets the width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidth(int col, int width) {
	data.setColumnWidth(col, width);
	if (getHeader() != null)
		getHeader().setColumnWidth(col, width);
	updateParent();
	repaint();
}


/** Gets the current number of columns. */
public int getNumColumns() { return data.num_columns; }

/** Sets the current number of columns (default: 1).<p>
 * <strong>HTML param name/value:</strong> "NumColumns"/int
 */
public void setNumColumns(int v) {
	data.setNumColumns(v);
	updateParent();
	repaint();
}

/** Gets the column alignments. */
public int[] getColumnAlignments() { return data.getColumnAlignments(); }

/** Gets a column's alignment. */
public int getColumnAlignment(int col) { return data.getColumnAlignment(col); }

/** Sets a column's alignment.
 * The first column's alignment (column 0) cannot be set.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT (default), MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignment(int col, int align) {
	if (col != 0)
		data.setColumnAlignment(col, align);
	repaint();
}

/** Sets the column's alignments.
 * The first column's alignment (column 0) cannot be set.<p>
 * <strong>HTML param name/value:</strong> "ColumnAlignments"/a comma-separated list of enums.<p>
 * @param align one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT (default), MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align) {
	if (align != null && align.length > 0) align[0] = BWTEnum.MIDDLELEFT;
	data.setColumnAlignments(align);
	repaint();
}

/** Gets the physical position of the left boundary of the column
 * (accounting for horizontal scrolling).
 */
public int getColumnPosition(int col) {
	return data.getColumnPosition(col)
		+ border + highlight + insets.left - horiz_origin;
}

/** Gets the column's left margin value (pixels). */
public int getColumnLeftMargin(int col) { return data.getColumnLeftMargin(col); }

/** Sets the column's left margin value (pixels) (default: 2).
 * The values for all columns may be set from an HTML file using a PARAM tag with a "ColumnLeftMargins"
 * name and a comma-separated list of ints.
*/
public void setColumnLeftMargin(int col, int value) {
	data.setColumnLeftMargin(col, value);
	calcColumnWidths();
	repaint();
}

/** Gets the column's right margin value (pixels). */
public int getColumnRightMargin(int col) { return data.getColumnRightMargin(col); }

/** Sets the column's right margin value (pixels) (default: 2).<p>
 * The values for all columns may be set from an HTML file using a PARAM tag with a "ColumnRightMargins"
 * name and a comma-separated list of ints.
 */
public void setColumnRightMargin(int col, int value) {
	data.setColumnRightMargin(col, value);
	calcColumnWidths();
	repaint();
}

/** Gets the component's JCMultiColumnData instance. */
public JCMultiColumnData getMultiColumnData() { return data; }

/** Gets a list of the column display widths.
 * @see #setColumnDisplayWidths
 */
public int[] getColumnDisplayWidths() { return data.getColumnDisplayWidths(); }

/** Sets the list of column display widths.  */
public void setColumnDisplayWidths(int[] widths) {
	data.setColumnDisplayWidths(widths);
	repaint();
}

/** Gets the current display width of a column, or 0 if the column does not exist.
 */
public int getColumnDisplayWidth(int col) { return data.getColumnDisplayWidth(col); }

/** Sets the display width of a column.  */
public void setColumnDisplayWidth(int col, int width) {
	data.setColumnDisplayWidth(col, width);
	if (getHeader() != null)
		getHeader().setColumnDisplayWidth(col, width);
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
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE
 */
public void setColumnResizePolicy(int policy) {
	data.setColumnResizePolicy(policy);
}


/***********************************
 * JCScrollableInterface methods
 ***********************************/

/** Gets the horizontal origin. */
public int getHorizOrigin() { return horiz_origin; }

/** Sets the horizontal origin prior to scrolling. */
public void setHorizOrigin(int v) { horiz_origin = v; }

/** Gets the vertical origin. */
public int getVertOrigin() { return vert_origin; }

/** Sets the vertical origin prior to scrolling. */
public void setVertOrigin(int v) { vert_origin = v; }

/***********************************
 * Printing methods
 ***********************************/

/* JDK110_START */
protected JCOutlinerPrinter outlinerPrinter = new JCOutlinerPrinter(this);
protected JCVector printListeners = new JCVector(0);
protected Rectangle dataRect = new Rectangle();
/* JDK110_END */

/**
 * For JDK 1.1 printing, returns the JCOutlinerPrinter object
 */
/* JDK110_START */
public JCOutlinerPrinter getOutlinerPrinter() {
	return outlinerPrinter;
}
/* JDK110_END */

/** For JDK 1.1 printing, draws all visible nodes */
/* JDK110_START */
protected void paintOutliner(Graphics gc, Rectangle draw_rect, JCOutlinerNode node, int[] paintColumns, boolean print_all_nodes) {
	root_shortcut = getStyle(root).shortcut;

	draw_rect.height -= 2 * (border + highlight) + insets.top + insets.bottom;
	bottom_y = draw_rect.y + draw_rect.height;

	if(node == getRootNode())
		printNode(node, draw_rect, paintColumns, root_visible ? 0 : -1,
					root_visible ? 0 : -1, gc, node.row, print_all_nodes);
	else {
		int row;
		int draw_count = 0;
		JCOutlinerNode n;
		for(row = 0; row < node_list.size(); row++)	{
			n = (JCOutlinerNode)node_list.elementAt(row);
			if(!(n == getRootNode()) && (print_all_nodes || shouldBeDrawn(n)))
				draw_count++;
			if(node == n)
				break;
		}
		printNode(getRootNode(), draw_rect, paintColumns, root_visible ? 0 : -1,
					root_visible ? 0 : -1, gc, draw_count, print_all_nodes);
	}
}
/* JDK110_END */

/**
 * For JDK 1.1 printing, draws a node and its children.
 * @param indent indentation level at which to draw node
 * @param row row at which to draw node
 * @return the number of rows this node took to display
 */
/* JDK110_START */
protected int printNode(JCOutlinerNode node, Rectangle rect, int[] paintColumns, int indent, int row, Graphics gc, int start_row, boolean print_all_nodes) {
	if (node == null)
		return 0;
	int rows = 1;

	node.indent = indent;
	node.row = row;

	if(node.row >= start_row) {
		if(!printNode(node, rect, paintColumns, gc, false, start_row))
			rows--;
	}

	// get the bounds for the node during printing
	int nx = rect.x + node.indent * node_indent + getColumnLeftMargin(0);
	int ny = rect.y + (node.row - start_row) * full_node_height;
	JCOutlinerNodeStyle nstyle = getStyle(node);
	Font font = (nstyle.font != null ? nstyle.font : getFont());
	int nwidth = BWTUtil.getWidth(node.label, this, font) + 2*highlight + 1;
	Image nicon = getIcon(node);

	if (!root_visible && root_shortcut)
		nx += node_indent / 2;

	if (nicon != null)
		nwidth += nicon.getWidth(null) + getStyle(node).icon_spacing;

	JCComponent.setBounds(draw_rect, nx, ny, nwidth, full_node_height);

	// Draw a folder's children if it is visible and open
	if (draw_rect.y + draw_rect.height <= bottom_y && node instanceof JCOutlinerFolderNode) {
		JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
		int state = folder.state;
		if (folder.children == null || state == FOLDER_OPEN_NONE
			|| (state == FOLDER_CLOSED && !print_all_nodes))
			return rows;

		// Draw children
		int y = draw_rect.y + full_node_height, f2 = full_node_height/2;
		int x = draw_rect.x;

		for (int i=0; i < folder.children.size() && y <= bottom_y; i++) {
			JCOutlinerNode child =
				(JCOutlinerNode)folder.children.elementAt(i);
			int subrows = printNode(child, rect, paintColumns, indent+1, row+rows, gc, start_row, print_all_nodes);

			if (subrows != 0 && paintColumns[0] == 0 && child.row + subrows >= start_row &&
				(node != root || root_shortcut || (node == root && root_visible))) {
				int line_x = x + node_indent/2, y2 = y + full_node_height/2;

				// Set color to line_color
				JCOutlinerNodeStyle style = getStyle(child);
				gc.setColor(style.line_color != null ?
							style.line_color : getForeground());

				int min_y = outlinerPrinter.getCurrentPageTopPosition() + highlight;

				// Horizontal line
				if(y2 >= min_y)
					gc.drawLine(line_x, y2, line_x+node_indent/2-1, y2);

				// Vertical line
				if (isLastEntry(folder, i, print_all_nodes)) {
					if(y > min_y)
						gc.drawLine(line_x, y, line_x, y + f2);
				} else
					gc.drawLine(line_x, (y < min_y) ? min_y : y,
						line_x, y + subrows * full_node_height + f2);

				if(y2 >= min_y)
					printShortcut(child, gc, line_x, y2, print_all_nodes);
			}
			rows += subrows;
			y += subrows * full_node_height;
		}
	}

	return rows;
}
/* JDK110_END */

/**
 * For JDK 1.1 printing, draws a single node
 * @param clear if true, the node's rectangle is cleared to the background color
 * @return false if the node is outside the drawing area
 */
/* JDK110_START */
protected boolean printNode(JCOutlinerNode node, Rectangle rect, int[] paintColumns, Graphics gc, boolean clear, int start_row) {
	if (node == null) return false;
	if (node == root && !root_visible) return false;

	boolean draw_outline = (paintColumns[0] == 0);
	// get the bounds for the node during printing
	int nx;
	if(draw_outline)
		nx = rect.x + node.indent * node_indent + getColumnLeftMargin(0);
	else
		nx = rect.x;

	int ny = rect.y + (node.row - start_row) * full_node_height;
	JCOutlinerNodeStyle nstyle = getStyle(node);
	Font font = (nstyle.font != null ? nstyle.font : getFont());
	int nwidth = BWTUtil.getWidth(node.label, this, font) + 2*highlight + 1;

	if (draw_outline && !root_visible && root_shortcut)
		nx += node_indent / 2;

	Image nicon = getIcon(node);
	if (draw_outline && nicon != null)
		nwidth += nicon.getWidth(null) + getStyle(node).icon_spacing;

	if(ny + full_node_height > rect.y + rect.height) {  // - highlight
		return false;
	}

	JCComponent.setBounds(draw_rect, nx, ny, nwidth, full_node_height);

	int draw_width = outlinerPrinter.internal_width - draw_rect.x;

	JCOutlinerNodeStyle style = getStyle(node);
	Font usedFont = (style.font != null) ? style.font : getFont();
	int label_width = BWTUtil.getWidth(node.label, this, usedFont) + 2*highlight + 1;
	Image icon = getIcon(node);
	if (draw_outline && icon != null)
		label_width += icon.getWidth(null) + style.icon_spacing;

	if (clear) {
		gc.setColor(getBackground());
		gc.fillRect(draw_rect.x, draw_rect.y,
					label_width, draw_rect.height);
		Color c = style.foreground;
		gc.setColor(c != null ? c : getForeground());
	}
	else {
		Color c = style.foreground;
		gc.setColor(c != null ? c : getForeground());
	}

	gc.setFont(style.font != null ? style.font : getFont());
	draw_rect.translate(highlight, highlight);

	if (draw_outline && icon != null) {
		int y = draw_rect.y + (draw_rect.height - icon.getHeight(null)) / 2;
		gc.drawImage(icon, draw_rect.x, y, this);
		draw_rect.x += icon.getWidth(null) + style.icon_spacing;
	}

	draw_rect.width -= 2 * highlight;
	draw_rect.height -= 2 * highlight;

	if(draw_rect.x + draw_rect.width > rect.x + rect.width)
		draw_rect.width = rect.x + rect.width - draw_rect.x;


	printColumnData(gc, node.label, draw_rect, paintColumns);
	return true;
}
/* JDK110_END */

/**
 * For JDK 1.1 printing, draws the value as a String, Vector, JCString, or Image.
 * @param draw_rect rectangle within which to draw the value;
 * if null, it will be calculated
 */
/* JDK110_START */
protected void printColumnData(Graphics gc, Object value, Rectangle draw_rect, int paintColumns[]) {
	JCComponent.setBounds(dataRect, draw_rect.x, draw_rect.y,
					 draw_rect.width, draw_rect.height);

	if (!(value instanceof Vector) || BWTUtil.is_jcstring(value)) {
		if(paintColumns[0] == 0)
			BWTUtil.draw(this, gc, value, getColumnAlignment(0), draw_rect);
		return;
	}

	int col;
	int start_x = 0;

	int last_x = 0;
	int last_width = 0;

	for (int c=0; c < paintColumns.length; c++) {
		col = paintColumns[c];
		if(col >= ((Vector)value).size())
			break;

		if(c != 0) {
			last_x = dataRect.x;
			last_width = dataRect.width;
		}

		dataRect.width = getColumnWidth(col);
		if (dataRect.width < 0) break;

		// adjust the position
		int left = getColumnLeftMargin(col);
		if(col == 0) {
			start_x = getInsets().left + outlinerPrinter.getPageMargins().left + dataRect.width + getColumnRightMargin(col);
			dataRect.width -= getColumnRightMargin(col);
			if(dataRect.x + dataRect.width - outlinerPrinter.getPageMargins().left > getColumnWidth(col))
				dataRect.width = start_x - dataRect.x;
		} else if(c == 0) {
			start_x = outlinerPrinter.getPageMargins().left + dataRect.width - left;
			dataRect.width -= left + getColumnRightMargin(col);
		} else {
			dataRect.x = start_x + left;
			start_x = dataRect.x + dataRect.width - left;
			dataRect.width -= left + getColumnRightMargin(col);
		}

		// adjust the width if it goes over the page width
		if(dataRect.width > draw_rect.width - last_width + last_x - outlinerPrinter.getPageMargins().left
				- left - getColumnRightMargin(col)) {
			dataRect.width = draw_rect.width - last_width + last_x - outlinerPrinter.getPageMargins().left
				- left - getColumnRightMargin(col);
		}

		Graphics new_gc = gc.create();
		new_gc.clipRect(dataRect.x, dataRect.y, dataRect.width, dataRect.height);
		BWTUtil.draw(this, new_gc, ((Vector)value).elementAt(col),
					 getColumnAlignment(col), dataRect);
		new_gc.dispose();
	}
}
/* JDK110_END */

/** For JDK 1.1 printing, prints a folder's shortcut button. */
/* JDK110_START */
protected void printShortcut(JCOutlinerNode node, Graphics gc, int x, int y, boolean print_all_nodes) {
	if (!(node instanceof JCOutlinerFolderNode))
		return;
	JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
	JCOutlinerNodeStyle style = getStyle(node);

	if (style.shortcut) {
		Image image = (folder.state == FOLDER_CLOSED && !print_all_nodes) ?
			shortcut_open_icon : shortcut_close_icon;
		gc.drawImage(image, x-SHORTCUT_SIZE/2, y-SHORTCUT_SIZE/2, this);
		folder.shortcut_x = x;
		folder.shortcut_y = y;
	}
	else
		folder.shortcut_x = BWTEnum.NOVALUE;
}
/* JDK110_END */

// For JDK 1.1 printing, determines if the "i"'s entry is the last entry given the folder's state
/* JDK110_START */
private boolean isLastEntry(JCOutlinerFolderNode folder, int i, boolean print_all_nodes) {
	if (folder.state == FOLDER_OPEN_ALL || print_all_nodes)
		return i == folder.children.size()-1;

	// if FOLDER_OPEN_FOLDERS look for more folders
	// if FOLDER_OPEN_ITEMS look for more items
	for (int j=i+1; j < folder.children.size(); j++) {
		JCOutlinerNode nextChild = (JCOutlinerNode)folder.children.elementAt(j);
		if (nextChild instanceof JCOutlinerFolderNode
			&& (folder.state == FOLDER_OPEN_FOLDERS || print_all_nodes))
			return false;
		if (!(nextChild instanceof JCOutlinerFolderNode)
			 && (folder.state == FOLDER_OPEN_ITEMS || print_all_nodes))
			return false;
	}

	return true;
}
/* JDK110_END */

/** Prints the JCOutliner if running under JDK 1.1. */
/* JDK110_START */
public void print() {
	PrintJob job = getToolkit().getPrintJob(getFrame(), "Print", null);
	if(job == null) return;
	Dimension pd = job.getPageDimension();

	outlinerPrinter.setPageDimensions(pd.width, pd.height);
	int pages = outlinerPrinter.getNumPages();

	Graphics gc;
	for(int p = 1; p <= pages; p++) {
		gc = job.getGraphics();
		if(gc != null) {
			outlinerPrinter.drawPage(gc, p);
			gc.dispose();
		}
	}
	job.end();
}
/* JDK110_END */

/**
 * For JDK 1.1 printing, adds the specified print listener to receive print events.
 * Print events occur when a header or footer is printed.
 * @see JCOutlinerPrintEvent
 */
/* JDK110_START */
public void addPrintListener(JCOutlinerPrintListener l) { printListeners.addUnique(l); }
/* JDK110_END */

/**
 * For JDK 1.1 printing, removes the specified print listener so it no longer receives
 * print events.
 * @see #addPrintListener
 */
/* JDK110_START */
public void removePrintListener(JCOutlinerPrintListener l) { printListeners.removeElement(l); }
/* JDK110_END */


/***********************************
 * Cut, Copy, and Paste methods
 ***********************************/

/**
 * This method creates a popup menu when you right click on a node.
 * Menu items are cut, copy, and paste.
 * JDK1.1 only.
 */
/* JDK110_START */
protected void createPopup(Component comp, int x, int y) {
	popup_x = x;
	popup_y = y;

	if (popup_menu != null) {
		remove(popup_menu);
	}
	popup_menu = new PopupMenu();

	MenuItem item;

	boolean all_selected = false,
		no_selection = false;

	// Cut
	item = new MenuItem(li.getString(LocaleInfo.CUT));
	if (no_selection) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Cut());
	}
	popup_menu.add(item);

	// Copy
	item = new MenuItem(li.getString(LocaleInfo.COPY));
	if (no_selection) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Copy());
	}
	popup_menu.add(item);

	// Paste
	item = new MenuItem(li.getString(LocaleInfo.PASTE));
	item.addActionListener(new Paste());
	popup_menu.add(item);

	add(popup_menu);

	popup_menu.show(comp, x, y);
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "cut"
 * on the OutlinerComponent it belongs to.
 */
/* JDK110_START */
public class Cut implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		cutToClipboard(null);
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "copy"
 * on the OutlinerComponent it belongs to.
 */
/* JDK110_START */
public class Copy implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		copyToClipboard(null);
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "paste"
 * on the OutlinerComponent it belongs to.
 */
/* JDK110_START */
public class Paste implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		Event ev = new Event(this, 0 , null);
		ev.x = popup_x;
		ev.y = popup_y;
		pasteFromClipboard(ev);
	}
}
/* JDK110_END */

/* JDK110_START */
protected boolean cmpKeyStroke(String modkey, int key) {
	Integer cmpkey = (Integer) li.getObject(modkey);
	if (cmpkey == null) {
		return(false);
	}
	if (cmpkey.intValue() == key) {
		return(true);
	}
	return(false);
}
/* JDK110_END */

/**
 * This method removes all selected nodes from the outliner.
 * For JDK1.1 only.
 */
/* JDK110_START */
protected void deleteSelection() {
	JCOutlinerNode node;

	// loop through the selected nodes and remove them from the outliner.
	for (int i = 0; i < selected_nodes.size(); i++) {
		node = (JCOutlinerNode)selected_nodes.elementAt(i);

		if (node != null) {
			if (node == focus_node) {
				focus_node = null;
			}
			if (node.parent != null) {
				node.parent.removeChild(node);
				folderChanged(node.parent);
			}
			else if (node == root) {
				root = null;
				folderChanged(null);
			}
		}
	}
}
/* JDK110_END */

/**
 * This method adds any nodes which have been cut or copied into the
 * output stream buffer into the outliner.
 * For JDK1.1 only.
 */
/* JDK110_START */
public boolean pasteFromClipboard(Event ev) {

	JCVector pasted_nodes = null;
	JCOutlinerNode target_node;
	int index = BWTEnum.MAXINT;

	// if nothing is in the clipboard.
	if (output_stream == null) {
		return false;
	}

	try {
		// read data to paste from stream
		input_stream = null;
		input_stream = new ByteArrayInputStream(output_stream.toByteArray());
		in = new ObjectInputStream(input_stream);
		pasted_nodes = (JCVector)in.readObject();
		if (pasted_nodes == null || pasted_nodes.size() == 0) {
			return false;
		}
		in.close();
	}
	catch (Exception e) {
		return false;
	}

	// get the folder to paste nodes into. Paste new nodes right after
    // the target node. If the target node is a folder, the new nodes
	// will be added at the end of the child list
	target_node = focus_node;
	if (target_node != null) {
		if (!(target_node instanceof JCOutlinerFolderNode)) {
			index = target_node.parent.getChildren().indexOf(target_node) + 1;
			target_node = target_node.parent;
		}
		else {
			index = BWTEnum.MAXINT;
		}
	}

	// if there is no root, create one so we can paste the new nodes.
	if (target_node == null) {
		target_node = root = new JCOutlinerFolderNode(null);
	}

	// add all the pasted nodes to the folder
	for (int i = 0; i < pasted_nodes.size(); i++) {
		JCOutlinerNode node = (JCOutlinerNode)pasted_nodes.elementAt(i);
		node.selected = false;
		((JCOutlinerFolderNode)target_node).addNode(node, index);
		if (index != BWTEnum.MAXINT) {
			index += 1;
		}
	}

	// notify that the folder has changed
	folderChanged(target_node);

	return true;
}
/* JDK110_END */

/**
 * If nodes are currently selected, copy the selection to an output stream
 * and delete them from the outliner.
 * For JDK1.1 only
 */
/* JDK110_START */
protected void cutToClipboard(Event ev) {

	// perform and copy of the selected nodes and then delete them.
	if (selected_nodes.size() > 0) {
		copyToClipboard(ev);
		deleteSelection();
	}
}
/* JDK110_END */

/**
 * If nodes are currently selected, serialize them to an output stream.
 * For JDK1.1 only.
 */
/* JDK110_START */
public void copyToClipboard(Event ev) {
	
	//System.err.println("copyToClipboard:"+selected_nodes.size());
	//for (int i=0;i<selected_nodes.size();i++)
	//	System.err.println(i+":"+selected_nodes.elementAt(i));
	if (selected_nodes.size() > 0) {
		try {
			JCVector nodes = (JCVector)selected_nodes.clone();
			// create an output stream and serialize the selected nodes
			// vector since these are the nodes we want to copy
			output_stream = new ByteArrayOutputStream();
			out = new ObjectOutputStream(output_stream);
			out.writeObject(selected_nodes);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			System.err.println("copyToClipboard-Exception:"+e);
		}
	}
}
/* JDK110_END */

/**
 * Sets the outliner to show the a popup menu which contains copy, cut, and
 * paste functionality when right clicking on a node.
 * For JDK1.1 only.
 * @see #getShowPopupMenu
 */
/* JDK110_START */
public void setShowPopupMenu(boolean show_popup) {
	this.show_popup = show_popup;
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
	return (show_popup);
}
/* JDK110_END */

}






