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
// RCSID -- $RCSfile: JCOutlinerNode.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:10:53 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import jclass.util.JCString;
/* JDK110_START */
import java.io.ObjectOutputStream;
import java.io.IOException;
/* JDK110_END */

/** This class describes a node in a JCOutliner component.
 * Each node has a style which describes its appearance. The default
 * style uses the outliner's colors and a default folder/item icon.
 * @see JCOutliner
 * @see JCOutlinerNodeStyle
 */
public class JCOutlinerNode implements Cloneable, JCSerializable {

protected Object label;
protected Object userData;
protected JCOutlinerFolderNode parent;
protected JCOutlinerNodeStyle style;
protected JCOutlinerComponent outliner;

int 		row;		// current row
int			indent;		// current indentation level
int			sequence;
boolean 	selected = false;

void draw() { 
	if (outliner != null 
		&& outliner.isVisible() && outliner.isShowing()
		   && outliner.visible_nodes.contains(this)) 
		outliner.drawNode(this, true); 
}

/** Gets the node's label.
 * @see #setLabel
 */
public Object getLabel() { return label; }

/** Gets the node's label as a String.
 * If the label is a Vector, the first element is returned.
 * @see #setLabel
 */
public String getLabelString() { 
	if (label instanceof String)
		return (String) label;
	else if (label instanceof JCVector && ((JCVector)label).size() > 0)
		return ""+((JCVector)label).elementAt(0);
	else
		return ""+label;
}

/** Sets the node's label. */
public void setLabel(Object v) { label = v; draw(); }

/** Gets the node's parent. If the node has no parent
 * it returns Null.
 * @see #setParent
 */
public JCOutlinerFolderNode getParent() { return parent; }

/** Sets the node's parent. */
public void setParent(JCOutlinerFolderNode folder) { 
	// If this node had a previous parent, remove parent link to this node
	if (parent != null) 
 		parent.removeChild(this);
	parent = folder; 
	outliner = parent.outliner;
}

/** Gets the node's style.
 * @see #setStyle
 */
public JCOutlinerNodeStyle getStyle() { return style; }

/** Sets the node's style object. */
public void setStyle(JCOutlinerNodeStyle s) { 
	style = s; 

	// Check if new node style height is bigger than the current outliner height
	if (outliner != null) {
		int	h = outliner.calculateStyleHeight(s) ;
		if (h > outliner.getNodeHeight()) {
			outliner.setNodeHeight(h);
			return;
		} 
	}
	draw(); 
}

/** Gets the node's userData. 
 * @see #setUserData
 */
public Object getUserData() { return userData; }

/** Sets the node's userData. This value is only for use by the application. */
public void setUserData(Object v) { userData = v; }

/** Gets the node's state (returns BWTEnum.NOVALUE if the node is not a folder).
 * @see JCOutlinerFolderNode#getState
 */
public int getState() { return BWTEnum.NOVALUE; }

/** Gets the node's children (returns null if the node is not a folder). 
 * @see JCOutlinerFolderNode#getChildren
 */
public JCVector getChildren() { return null; }

/** Gets the parent outliner. */
public JCOutliner getOutliner() { return (JCOutliner) outliner.scrolled_window;}

/** Gets the indentation level of the node. */
public final int getLevel() {
	JCOutlinerNode node = this;
	int level = 0;
	for (; node != null && node.parent != null; level++, node = node.parent)
		;
	return level;
}

/** Creates a clone of the node. */
public synchronized Object clone() {
	Object l = label;
	if (label instanceof String)
		l = new String((String)label);
	JCOutlinerNode node = new JCOutlinerNode(l);
	node.style = style;
	node.userData = userData;
	return node;
}

/** Creates a node with no parent. */
public JCOutlinerNode(Object label) {
	this.label = label;
}

/** Creates a node with the specified parent. */
public JCOutlinerNode(Object label, JCOutlinerFolderNode parent) {
	this(label);
	if (parent != null)
		parent.addNode(this);
}

public String toString() {
	return getLabelString();
}

/** Serializes the node. */
/* JDK110_START */
private void writeObject(ObjectOutputStream out) throws IOException {
	outliner = null;
	out.defaultWriteObject();
}
/* JDK110_END */

}
