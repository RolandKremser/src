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
// RCSID -- $RCSfile: JCMultiColumnInterface.java $ $Revision: 2.2 $ $Date: 2000/11/09 20:10:43 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** An interface which defines the API for components which have a 
 * multiple-column behavior.
 * @see JCOutliner
 * @see JCMultiColumnList
 * @see JCMultiColumnWindow
 */
public interface JCMultiColumnInterface {

/** Sets a header for the window with the specified labels. */
public void setColumnLabels(JCVector labels);

/** Sets a header for the window with buttons created from the specified labels.
 */
public void setColumnButtons(JCVector labels);

/** Gets a list of column widths. */
public int[] getColumnWidths();

/** Sets the list of column widths. If a column's value is set to 
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidths(int[] widths);

/** Gets the current width of a column, or 0 if the column does not exist.
 */
public int getColumnWidth(int col);

/** Gets the physical position of the left boundary of the column 
 * (accounting for horizontal scrolling).
 */
public int getColumnPosition(int col);

/** Sets the width of a column. If the value is set to 
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidth(int col, int width);

/** Gets the current number of columns. */
public int getNumColumns();

/** Sets the current number of columns. */
public void setNumColumns(int v);

/** Gets the column's left margin value (pixels). */
public int getColumnLeftMargin(int col);

/** Sets the column's left margin value (pixels). */
public void setColumnLeftMargin(int col, int value);

/** Gets the column's right margin value (pixels). */
public int getColumnRightMargin(int col);

/** Sets the column's right margin value (pixels). */
public void setColumnRightMargin(int col, int value);

/** Gets the column alignments. */
public int[] getColumnAlignments();

/** Gets a column's alignment (default: BWTEnum.MIDDLELEFT). */
public int getColumnAlignment(int col);

/** Sets a column's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignment(int col, int align);

/** Sets the column's alignments.
 * @param align one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align);

/** Calculates the width of a column. */
public int calcWidth(int col);

/** Gets the component's JCMultiColumnData instance. */
public JCMultiColumnData getMultiColumnData();


/** Gets a column's display width. */
public int getColumnDisplayWidth(int col);
/** Sets a column's display width. */
public void setColumnDisplayWidth(int col, int width);

/** Gets column's display widths. */
public int[] getColumnDisplayWidths();
/** Sets column's display widths. */
public void setColumnDisplayWidths(int[] widths);


/** Gets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public int getColumnResizePolicy();
/** Sets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public void setColumnResizePolicy(int policy);


}
