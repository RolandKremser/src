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
// RCSID -- $RCSfile: JCOutlinerFolderNode.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:10:51 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** This class describes a folder node in a JCOutliner component.
 * @see JCOutliner
 */
public class JCOutlinerFolderNode extends JCOutlinerNode {

/** Folder's state. */
protected int state = BWTEnum.FOLDER_OPEN_ALL;

/** Sub-nodes of this folder. */
protected JCVector children;

// Private
int shortcut_x = BWTEnum.NOVALUE, shortcut_y;

/** Creates an empty folder with state FOLDER_OPEN_ALL. */
public JCOutlinerFolderNode(Object label) {
	this(null, BWTEnum.FOLDER_OPEN_ALL, label);
}

/** Creates an empty folder with state FOLDER_OPEN_ALL with the specified parent. */
public JCOutlinerFolderNode(Object label, JCOutlinerFolderNode parent) {
	this(null, BWTEnum.FOLDER_OPEN_ALL, label);
	parent.addNode(this);
}

/** Creates a folder with state FOLDER_OPEN_ALL with the specified children. */
public JCOutlinerFolderNode(JCVector children, Object label) {
	this(children, BWTEnum.FOLDER_OPEN_ALL, label);
}

/** Creates a folder with an initial state with the specified children. */
public JCOutlinerFolderNode(JCVector children, int state, Object label) {
	super(label);
	this.state = state;
	this.children = children;
}

/** Adds a node with the specified label to this folder.
 * @return the new node
 */
public JCOutlinerNode addNode(Object label) {
	JCOutlinerNode node = new JCOutlinerNode(label);
	addNode(node);
	return node;
}

/** Adds a node to this folder. */
public void addNode(JCOutlinerNode node) {
	addNode(node, BWTEnum.MAXINT);
}

/** Adds a node to this folder at the specified position. */
public void addNode(JCOutlinerNode node, int index) {
	if (node.parent != null) 
 		node.parent.removeChild(node);
	if (children == null)
		children = new JCVector();
	if (index > children.size())
		children.addElement(node);
	else
		children.insertElementAt(node, index);
	node.parent = this;

	// check if new node style height is bigger than the current outliner height
	if (outliner != null) {
		int	styleHeight = outliner.calculateStyleHeight(node.style) ;
		if (styleHeight > outliner.getNodeHeight()) 
			outliner.setNodeHeight(styleHeight);
	}

}

/** Adds a folder with the specified label to this folder.
 * @return the new node
 */
public JCOutlinerFolderNode addFolderNode(Object label) {
	JCOutlinerFolderNode node = new JCOutlinerFolderNode(label);
	addNode(node);
	return node;
}

/** Gets the folder's children. */
public JCVector getChildren() { return children; }

/** Adds a list of children, replacing the previous list (if any). */
public void setChildren(JCVector children) {
	this.children = children;
}

/** Removes all children (if any). */
public void removeChildren() { setChildren(null); }

/** Removes a child (if it exists). */
public void removeChild(JCOutlinerNode child) {
	if (child != null && children != null)
		children.removeElement(child);
}

/** Gets the folder's state. */
public int getState() { return state; }

/** Sets the folder's state:
 * <pre>
BWTEnum.FOLDER_CLOSED       Folder closed; no children visible (default)
BWTEnum.FOLDER_OPEN_NONE    Folder open; no children visible
BWTEnum.FOLDER_OPEN_FOLDERS Folder open; only folder children visible
BWTEnum.FOLDER_OPEN_ITEMS   Folder open; only non-folder children visible
BWTEnum.FOLDER_OPEN_ALL     Folder open; all children visible
 * </pre>
 */
public void setState(int v) { state = v; }

/** Creates a clone of the folder. */
public synchronized Object clone() {
	Object l = label;
	if (label instanceof String)
		l = new String((String)label);
	JCOutlinerFolderNode folder = new JCOutlinerFolderNode(l);
	folder.style = style;
	folder.userData = userData;
	folder.state = state;
	if (children != null) {
		for (int i=0; i < children.size(); i++) {
			JCOutlinerNode node = (JCOutlinerNode) children.elementAt(i);
			if (node instanceof JCOutlinerFolderNode)
				node = (JCOutlinerFolderNode) ((JCOutlinerFolderNode)node).clone();
			else if (node != null)
				node = (JCOutlinerNode) node.clone();
			folder.addNode(node);
		}
	}
	return folder;
}

}
