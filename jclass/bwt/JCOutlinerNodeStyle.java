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
// RCSID -- $RCSfile: JCOutlinerNodeStyle.java $ $Revision: 2.2 $ $Date: 2000/11/09 20:10:53 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

/** This class defines style values for a JCOutliner node.
 * @see JCOutliner
 * @see JCOutlinerNode
 */
public class JCOutlinerNodeStyle implements JCSerializable {

/** The node's background color when it is selected. */
protected Color	background_selected;

/** The node's font. */
protected Font		font;

/** The node's foreground color. */
protected Color	foreground;

/** The node's foreground color when it is selected. */
protected Color	foreground_selected;

/** The color of the line connecting a folder to its children. */
protected Color	line_color;

/** Whether a shortcut button should be drawn beside a folder. */
protected boolean	shortcut = false;

/** The node's icon if it is not a folder. */
protected transient Image	item;

/** The node's icon if it is not a folder and it is selected. */
protected transient Image	item_selected;

/** The node's icon if it is a closed folder. */
protected transient Image	folder_closed;

/** The node's icon if it is a closed folder and it is selected. */
protected transient Image	folder_closed_selected;

/** The node's icon if it is an open folder. */
protected transient Image	folder_open;

/** The node's icon if it is an open folder and it is selected. */
protected transient Image	folder_open_selected;

/** The horizontal spacing between the icon and the label. */
protected int		icon_spacing = 5;

/** Gets the node's background color when it is selected. */
public Color getBackgroundSelected() { return background_selected; }

/** Gets the node's font. */
public Font getFont() { return font; }

/** Gets the node's foreground color. */
public Color getForeground() { return foreground; }

/** Gets the node's foreground color when it is selected. */
public Color getForegroundSelected() { return foreground_selected; }

/** Gets the color of the line connecting a folder to its children. */
public Color getLineColor() { return line_color; }

/** Gets whether a shortcut button should be drawn beside a folder. */
public boolean getShortcut() { return shortcut; }

/** Gets the node's icon if it is not a folder. */
public Image getItemIcon() { return item; }

/** Gets the node's icon if it is not a folder and it is selected. */
public Image getItemSelectedIcon() { return item_selected; }

/** Gets the node's icon if it is a closed folder. */
public Image getFolderClosedIcon() { return folder_closed; }

/** Gets the node's icon if it is a closed folder and it is selected. */
public Image getFolderClosedSelectedIcon() { return folder_closed_selected; }

/** Gets the node's icon if it is an open folder. */
public Image getFolderOpenIcon() { return folder_open; }

/** Gets the node's icon if it is an open folder and it is selected. */
public Image getFolderOpenSelectedIcon() { return folder_open_selected; }

/** Gets the horizontal spacing between the icon and the label. */
public int getIconSpacing() { return icon_spacing; }

/** Sets the node's background color when it is selected
 * (default: outliner's foreground color).
 */
public void setBackgroundSelected(Color v) { background_selected = v; }

/** Sets the node's font (default: outliner's font). */
public void setFont(Font f) { font = f; }

/** Sets the node's foreground color (default: outliner's foreground color). */
public void setForeground(Color v) { foreground = v; }

/** Sets the node's foreground color when it is selected
 * (default: outliner's background color). */
public void setForegroundSelected(Color v) { foreground_selected = v; }

/** Sets the color of the line connecting a folder to its children (default: outliner's foreground color). */
public void setLineColor(Color v) { line_color = v; }

/** Sets whether a shortcut button should be drawn beside a folder (default: false). */
public void setShortcut(boolean v) { shortcut = v; }

/** Sets the horizontal spacing between the icon and the label (default: 5). */
public void setIconSpacing(int v) { icon_spacing = v; }

/** Sets the node's icon if it is not a folder. */
public void setItemIcon(Image i) { item = i; }

/** Sets the node's icon if it is not a folder and it is selected.
 * (default: same as item).
 */
public void setItemSelectedIcon(Image i) { item_selected = i; }

/** Sets the node's icon if it is a closed folder. */
public void setFolderClosedIcon(Image i) { folder_closed = i; }

/** Sets the node's icon if it is a closed folder and it is selected. */
public void setFolderClosedSelectedIcon(Image i) { folder_closed_selected = i; }

/** Sets the node's icon if it is an open folder. */
public void setFolderOpenIcon(Image i) { folder_open = i; }

/** Sets the node's icon if it is an open folder and it is selected.
 * (default: same as open).
 */
public void setFolderOpenSelectedIcon(Image i) { folder_open_selected = i; }

public JCOutlinerNodeStyle() {}

/** Creates a new style which is a copy of a style. */
public JCOutlinerNodeStyle(JCOutlinerNodeStyle s) {
	background_selected = s.background_selected;
	font = s.font;
	foreground = s.foreground;
	foreground_selected = s.foreground_selected;
	shortcut = s.shortcut;
	line_color = s.line_color;
	icon_spacing = s.icon_spacing;
	item = s.item;
	item_selected = s.item_selected;
	folder_closed = s.folder_closed;
	folder_closed_selected = s.folder_closed_selected;
	folder_open = s.folder_open;
	folder_open_selected = s.folder_open_selected;
}

}
