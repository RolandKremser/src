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
 ***************************************************************************/
// RCSID -- $RCSfile: JCComboBoxBeanInfo.java $ $Revision: 2.8 $ $Date: 2000/11/09 20:10:27 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.beans.*;

public class JCComboBoxBeanInfo extends jclass.beans.GeneralBeanInfo {

static final String[] names[] = {
	{ "alignment", "jclass.bwt.HorizAlignmentEditor" },
	{ "arrowKeySpinningAllowed", ""},
	{ "changed", "" },
	{ "columns", "" },
	{ "cursorPosition", "" },
	{ "editable", "" },
	{ "highlightColor", "" },
	{ "highlightThickness", "" },
	{ "items", "jclass.beans.StringListEditor"},
	{ "maximumLength", "" },
	{ "overstrike", "" },
	{ "selectedBackground", "" },
	{ "selectedForeground", "" },
	{ "selectionEnd", "" },
	{ "selectionStart", "" },
	{ "shadowThickness", "" },
	{ "showCursorPosition", "" },
	{ "stringCase", "jclass.bwt.StringCaseEditor" },
	{ "style", "jclass.bwt.ComboBoxStyleEditor" },
	{ "text", "" }, 
};

static final String[] omit_properties = { "layout" };

static final String[][] events = {
  { "action", "JCActionListener", "addActionListener", "removeActionListener" },
  { "comboBox", "JCComboBoxListener", "addComboBoxListener", "removeComboBoxListener" },
  { "cursor", "JCTextCursorListener", "addTextCursorListener", "removeTextCursorListener" },
  { "text", "JCTextListener", "addTextListener", "removeTextListener" },
};

static final String[][] listeners = {
  { "actionPerformed" },
  { "comboBoxListDisplayBegin", "comboBoxListDisplayEnd" },
  { "textCursorMoveBegin", "textCursorMoveEnd", },
  { "textValueChangeBegin", "textValueChangeEnd", },
};

public JCComboBoxBeanInfo() {
	super("jclass.bwt.JCComboBox", names, events, listeners);
	omit_props = omit_properties;
}

/**
 * Gets the specified icon image
 */
public java.awt.Image getIcon(int iconKind) {
	if (iconKind == ICON_MONO_16x16 || iconKind == ICON_COLOR_16x16) 
	    return loadImage("JCComboBox_1616.gif");
	if (iconKind == ICON_MONO_32x32 || iconKind == ICON_COLOR_32x32) 
	    return loadImage("JCComboBox_3232.gif");
	return null;
}

}