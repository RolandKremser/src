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
// RCSID -- $RCSfile: OutlinerConverter.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:11:37 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.util.Vector;

/** Reads JCOutlinerComponent parameters from an HTML file or external file. */
class OutlinerConverter {

final static String[] state_strings = {
	"FOLDER_CLOSED",
	"FOLDER_OPEN_NONE",
	"FOLDER_OPEN_FOLDERS",
	"FOLDER_OPEN_ITEMS",
	"FOLDER_OPEN_ALL",
};

final static int[] state_values = {
	BWTEnum.FOLDER_CLOSED,
	BWTEnum.FOLDER_OPEN_NONE,
	BWTEnum.FOLDER_OPEN_FOLDERS,
	BWTEnum.FOLDER_OPEN_ITEMS,
	BWTEnum.FOLDER_OPEN_ALL,
};

static void
getParams(JCOutlinerComponent comp) {
	JCConverter conv = comp.conv;

	MultiColumnConverter.getParams(comp, conv);
	comp.setRootVisible(conv.toBoolean(comp.getParam("RootVisible"), 
									   comp.getRootVisible()));
	comp.setNodeHeight(conv.toInt(comp.getParam("NodeHeight"), 
								  comp.getNodeHeight()));
	comp.setNodeIndent(conv.toInt(comp.getParam("NodeIndent"), 
								  comp.getNodeIndent()));
	comp.setAllowMultipleSelections(conv.toBoolean(comp.getParam("AllowMultipleSelections"), comp.getAllowMultipleSelections()));
	comp.setAutoSelect(conv.toBoolean(comp.getParam("AutoSelect"), 
									   comp.getAutoSelect()));
	comp.setStateList(conv.toEnumList(comp.getParam("StateList"), "StateList",
									  state_strings, state_values,
									  comp.getStateList()));
	comp.setSpacing(conv.toInt(comp.getParam("Spacing"), comp.getSpacing()));
	comp.setVisibleRows(conv.toInt(comp.getParam("VisibleRows"), 
								   comp.getVisibleRows()));
	String s = comp.getParam("Tree");
	if (s != null)
		comp.setTree(conv.toStringList(s));
}

static void checkState(int v) { 
	JCUtilConverter.checkEnum(v, "state", state_values);
}

static String[] getTree(JCOutlinerComponent comp) {
	String list[] = new String[comp.countChildren(comp.root)];
	addNode(list, comp.root, 0, 0);
	return list;
}

/** Adds the node's label to the list. */
static int addNode(String list[], JCOutlinerNode node, int indent, int row) {
	if (node == null)
		return 0;
	String s = "";
	for (int i=0; i < indent; i++)
		s += ' ';
	list[row] = s + JCUtilConverter.toString(node.label);
	int rows = 1;
	
	if (!(node instanceof JCOutlinerFolderNode)) return rows;
	JCOutlinerFolderNode folder = (JCOutlinerFolderNode) node;
	JCVector children = folder.children;
	if (children == null || children.size() == 0) {
		list[row] += "(FOLDER)";
		return rows;
	}

	for (int i=0; i < children.size(); i++) 
		rows += addNode(list, (JCOutlinerNode)children.elementAt(i), 
						indent+1, row+rows);
	
	return rows;
}

static boolean isFolder(String s) {
	int k = s.lastIndexOf("(FOLDER)");
	return k != -1 && k == s.length() - 8;
}

static Object getLabel(JCOutlinerComponent comp, String s) {
	int k = s.lastIndexOf("(FOLDER)");
	if (k != -1 && k == s.length() - 8) {
		if (k == 0) return null;
		s = s.substring(0, k);
	}

	JCVector v = comp.conv.toVector(comp, s, ',', true, null);
	for (int i=0; i < v.size(); i++) {
		Object o = v.elementAt(i);
		if (o instanceof String)
			v.setElementAt(i, JCUtilConverter.removeEscape((String)o));
	}
	return v;
}

/** Creates a tree heirarchy from the string list. 
 * Each line in the list represents a node's label.
 * Spaces or tabs at the start of each
 * line supply the tree hierarchy. Lines with the same indentation level are
 * children. Nodes without children are assumed to be items. To create a folder
 * without children, append the string "(FOLDER)" to the line.
 */
static JCOutlinerNode createTree(JCOutlinerComponent comp, String[] string) {
	if (string == null) return null;
		
	int i, start = 1, max_indent = 0;
	String line = string[0];

	JCOutlinerNode root, node, 
		folder = new JCOutlinerFolderNode(getLabel(comp, line.trim()));
	root = folder;
	root.indent = 0;
	Vector node_list = new Vector(string.length);
	node_list.addElement(folder);
	
	// Check whether a root has been specified
	for (i=0; i < string.length; i++) {
		String s = string[i];
		if (s.length() > 0 && s.charAt(0) != ' ' && s.charAt(0) != '\t')
			break;
	}
	if (i == string.length) {
		root.label = null;
		root.indent = -1;
		start = 0;
	}

	for (i=start; i < string.length; i++) {
		line = string[i];
		if (line == null) continue; // Blank line
		int k, indent = Math.min(max_indent+1, findIndent(line));
		for (k=node_list.size()-1; k >= 0; k--) {
			folder = (JCOutlinerNode) node_list.elementAt(k);
			if (folder.indent == indent-1) 
				break;
		}
		if (!(folder instanceof JCOutlinerFolderNode)) {
			JCOutlinerFolderNode parent = folder.parent;
			parent.removeChild(folder);
			folder = new JCOutlinerFolderNode(folder.label, parent);
			folder.indent = indent-1;
			node_list.setElementAt(folder, k);
		}
		Object label = getLabel(comp, line.trim());
		if (isFolder(line))
			node = new JCOutlinerFolderNode(label, (JCOutlinerFolderNode)folder);
		else
			node = new JCOutlinerNode(label, (JCOutlinerFolderNode)folder);
		node.indent = indent;
		if (indent > max_indent) max_indent = indent;
		node_list.addElement(node);
	}
	return root;
}

private static int findIndent(String s) {
	for (int i=0; i < s.length(); i++) 
		if (s.charAt(i) != ' ' && s.charAt(i) != '\t')
			return i;
	return 0;
}

}
