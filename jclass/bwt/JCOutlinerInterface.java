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
// RCSID -- $RCSfile: JCOutlinerInterface.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:10:52 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;

/** An interface which defines the API for components which have an outliner-like
 * behavior.
 * @see JCOutliner
 */
public interface JCOutlinerInterface 
extends JCMultiColumnInterface, JCItemSelectable {

/** Notifies the outliner that the number of a folder's children has changed. */
public void folderChanged(JCOutlinerNode node);

/** Gets the root node.
 * @see #setRootNode
 */
public JCOutlinerNode getRootNode();

/** Sets the root node. */
public void setRootNode(JCOutlinerNode node);

/** Gets the RootVisible value.
 * @see #setRootVisible
 */
public boolean getRootVisible();

/** If true, the root node is drawn. */
public void setRootVisible(boolean v);

/** Calculates the height of a node (by looking at the node's style). 
 */
public int calculateNodeHeight(JCOutlinerNode node);

/** Gets the NodeHeight value.
 * @see #setNodeHeight
 */
public int getNodeHeight();

/** Sets the height of each node (pixels).
 * If 0, the height is determined using the largest font and
 * image height in the list of node styles.
 */
public void setNodeHeight(int v);

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect();

/** If true, the node which currently has focus is also selected.
 * @see #setFocusNode
 * @see #selectNode
 */
public void setAutoSelect(boolean v);

/** Gets the NodeIndent value.
 * @see #setNodeIndent
 */
public int getNodeIndent();

/** Sets the indentation of each node relative to its parent (pixels). */
public void setNodeIndent(int v);

/** Gets the current StateList.
 * @see #setStateList
 */
public int[] getStateList();

/** Sets a list of values used for double-clicking on a folder or clicking
 * its shortcut button.
 * The folder's state is cycled through the list. Valid values:
 * <pre>
BWTEnum.FOLDER_CLOSED		Folder closed; no children visible
BWTEnum.FOLDER_OPEN_NONE	Folder open; no children visible
BWTEnum.FOLDER_OPEN_FOLDERS	Folder open; only folder children visible
BWTEnum.FOLDER_OPEN_ITEMS	Folder open; only non-folder children visible
BWTEnum.FOLDER_OPEN_ALL		Folder open; all children visible
 * </pre>
 */
public void setStateList(int[] list);

/** Gets the current StyleList.
 * @see #setStyleList
 */
public JCOutlinerNodeStyle[] getStyleList();

/** Sets the list of node styles. The list is only used for determining
 * the node height if it has not been set.
 * @see #setNodeHeight
 */
public void setStyleList(JCOutlinerNodeStyle[] list);

/** Gets the default node style. */
public JCOutlinerNodeStyle getDefaultNodeStyle();

/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing();

/** Sets the spacing between items (default: 0).
 * This value increases the HighlightThickness value.
 * @see JCComponent#setHighlightThickness
 */
public void setSpacing(int v);

/**
 * Returns the number of visible lines.
 * @see #setVisibleRows
 */
public int getVisibleRows();

/** Sets the number of visible rows. If set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.
 */
public void setVisibleRows(int v);

/** Returns the currently selected node, or null. */
public JCOutlinerNode getSelectedNode();

/** Selects a node, and posts a JCOutlinerEvent.
 * @param node if null, the currently selected node is deselected
 * @param ev event which caused the selection
 */
public void selectNode(JCOutlinerNode node, Event ev);

/** Returns the node which currently has focus, or null. */
public JCOutlinerNode getFocusNode();

/** Sets a node to have the keyboard focus.
 * @param node if null, no the current focus node is unchanged
 * @param ev event which caused the focus change
 */
public void setFocusNode(JCOutlinerNode node, Event ev);

/** Gets a list of the nodes which are currently displayed. */
public JCVector getVisibleNodes();

/** Sets the state of a folder.
 * @param node if not a folder, no action is taken
 * @param state one of:
 * <pre>
BWTEnum.FOLDER_CLOSED        Folder closed; no children visible
BWTEnum.FOLDER_OPEN_NONE     Folder open; no children visible
BWTEnum.FOLDER_OPEN_FOLDERS  Folder open; only folder children visible
BWTEnum.FOLDER_OPEN_ITEMS    Folder open; only non-folder children visible
BWTEnum.FOLDER_OPEN_ALL 	 Folder open; all children visible
 * </pre>
 * @param notify if true, a JCOutlinerEvent is posted for the node
 * @return false if the app disallowed the state change
 */
public boolean setNodeState(JCOutlinerNode node, int state, boolean notify);

/** Scrolls the display if necessary to make the node visible.
 * @return false if the node could not be made visible due to its parents state
*/
public boolean makeNodeVisible(JCOutlinerNode node);

/** Gets the current number of non-collapsed nodes (the number of
 * open folders and their children).
 */
public int getNumNodes();

/** Returns the next visible node, or null if none was found. */
public JCOutlinerNode getNextNode(JCOutlinerNode node);

/** Returns the previous visible node, or null if none was found. */
public JCOutlinerNode getPreviousNode(JCOutlinerNode node);

/** Gets the physical bounding rectangle of a node (icon and label)
 * (adjusted for scrolling and insets).
 * @param rect if not null, its members are set
 * @return null if rect is null, otherwise the bounding rectangle
 */
public Rectangle getBounds(JCOutlinerNode node, Rectangle rect);

/** Gets the node in which the event occurred. */
public JCOutlinerNode eventToNode(Event ev);

/** Returns true if the node is a folder and the event is within the
 * folder's shortcut button.
 */
public boolean eventInShortcut(Event ev, JCOutlinerNode node);

}

