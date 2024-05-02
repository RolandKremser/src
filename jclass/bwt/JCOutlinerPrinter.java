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

package jclass.bwt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import jclass.util.JCVector;

/** 
 * The JCOutlinerPrinter component allows the user to add
 * enhanced printing functionality under JDK 1.1. <P>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr>
 * <tr><td>HeaderOnEachPage</td><td><a href=#setHeaderOnEachPage>setHeaderOnEachPage</a></td></tr>
 * <tr><td>MarginUnits</td><td><a href=#setMarginUnits>setMarginUnits</a></td></tr>
 * <tr><td>OutlineOnEachPage</td><td><a href=#setOutlineOnEachPage>setOutlineOnEachPage</a></td></tr>
 * <tr><td>PageDimensions</td><td><a href=#setPageDimensions>setPageDimensions</a></td></tr>
 * <tr><td>PageHeight</td><td><a href=#setPageHeight>setPageHeight</a></td></tr>
 * <tr><td>PageMargins</td><td><a href=#setPageMargins>setPageMargins</a></td></tr>
 * <tr><td>PageResolution</td><td><a href=#setPageResolution>setPageResolution</a></td></tr>
 * <tr><td>PageWidth</td><td><a href=#setPageWidth>setPageWidth</a></td></tr>
 * <tr><td>PrintAllNodes</td><td><a href=#setPrintAllNodes>setPrintAllNodes</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * PrintListener    </td><td><a href=jclass.bwt.JCOutliner.html#addPrintListener>addPrintListener</a></td> <td>Posted when a page header or page footer is printed.</td></tr><tr><td>
 * </table>
 */

public class JCOutlinerPrinter implements JCSerializable {

JCOutlinerComponent outliner;

int			page_width = 0;
int			page_height = 0;
int			page_resolution = 72;

boolean		repeat_header = true;
boolean		repeat_outline = true;

boolean		print_all_nodes = true;

int			internal_width = 0;
int			internal_height = 0;

Insets		margin = new Insets(page_resolution, page_resolution, 
								page_resolution, page_resolution);
int			margin_unit = MARGIN_IN_PIXELS;

int			current_page = 0;

public static final int MARGIN_IN_INCHES = 0;
public static final int MARGIN_IN_PIXELS = 1;

/**
 * Controls JCOutliner printing
 */
public JCOutlinerPrinter(JCOutlinerComponent outliner) {
	this.outliner = outliner;
}

/**
 * Returns true if a header is printed on each page
 */
public boolean getHeaderOnEachPage() {
	return repeat_header;
}

/**
 * If true, the header is printed on each page
 */
public void setHeaderOnEachPage(boolean v) {
	this.repeat_header = v;
}

/**
 * Gets the units for margin calculations
 */
public int getMarginUnits() {
	return margin_unit;
}

/**
 * Sets the units for margin calculations
 */
public void setMarginUnits(int margin_units) {
	if(margin_unit == MARGIN_IN_INCHES)
		this.margin_unit = MARGIN_IN_INCHES;
	else
		this.margin_unit = MARGIN_IN_PIXELS;
}

/**
 * Returns the number of pages required to print the table.
 */
public int getNumPages() {
	if(page_width * page_height == 0)
		return 0;
	return getNumHorizontalPages() * getNumVerticalPages();
}

/**
 * Returns true if an outline is printed on each page
 */
public boolean getOutlineOnEachPage() {
	return repeat_outline;
}

/**
 * If true, the outline is printed on each page
 */
public void setOutlineOnEachPage(boolean v) {
	this.repeat_outline = v;
}

/**
 * Returns the page dimensions
 */
public Dimension getPageDimensions() {
	return new Dimension(page_width, page_height);
}

/**
 * Sets the dimensions of a page
 */
public void setPageDimensions(int page_width, int page_height) {
	this.page_width = page_width;
	this.page_height = page_height;
	setInternalDimensions();
}

/**
 * Returns the height of a page
 */
public int getPageHeight() {
	return page_height;
}

/**
 * Sets the height of a page
 */
public void setPageHeight(int page_height) {
	this.page_height = page_height;
	setInternalDimensions();
}

/**
 * Returns the page margins
 */
public Insets getPageMargins() {
	return margin;
}

/**
 * Returns the default margins
 */
public Insets getDefaultPageMargins() {
	this.margin_unit = MARGIN_IN_PIXELS;
	this.margin = new Insets(72, 72, 72, 72);
	return margin;
}

/**
 * Sets the page margins.  Passing null sets the margins to the default.
 */
public void setPageMargins(Insets margin) {
	if(margin == null)
		this.margin = getDefaultPageMargins();
	else
		this.margin = margin;
	setInternalDimensions();
}

/**
 * Returns the page resolution
 */
public int getPageResolution() {
	return page_resolution;
}

/**
 * Sets the page resolution in pixels/inch
 */
public void setPageResolution(int page_resolution) {
	this.page_resolution = page_resolution;
	setInternalDimensions();
}

/**
 * Returns the width of a page
 */
public int getPageWidth() {
	return page_width;
}

/**
 * Sets the width of a page
 */
public void setPageWidth(int page_width) {
	this.page_width = page_width;
	setInternalDimensions();
}

/**
 * Returns true if all nodes are printed (regardless of collapse state).
 */
public boolean getPrintAllNodes() {
	return print_all_nodes;
}

/**
 * Set true if all nodes are printed (regardless of collapse state).
 */
public void setPrintAllNodes(boolean value) {
	this.print_all_nodes = value;
}

/**
 * Returns the number of horizontal pages
 */
public int getNumHorizontalPages() {
	int current;
	int width;
	int horiz_pages = 1;

	int columns = outliner.getNumColumns();
	if(columns == 0)
		return 0;

	current = outliner.getColumnWidth(0);
	// can't print the outliner if the outline column is wider than a page
	if(current > internal_width)
		return 0;

	for(int c = 1; c < columns; c++) {
		width = outliner.getColumnWidth(c);
		current += width;
		if(repeat_outline) {
			if((current == outliner.getColumnWidth(0) + outliner.getColumnWidth(c))
				&& (current > internal_width)) {
				current = outliner.getColumnWidth(0);
				horiz_pages++;
			} else {
				if(current > internal_width) {
					current = outliner.getColumnWidth(0) + outliner.getColumnWidth(c);
					horiz_pages++;
				}
			}
		} else {
			if((current == outliner.getColumnWidth(c)) && (current > internal_width)) {
				current = 0;
				horiz_pages++;
			} else {
				if(current > internal_width) {
					current = outliner.getColumnWidth(c);
					horiz_pages++;
				}
			}
		}
	}
	return horiz_pages;
}

/**
 * Returns the number of vertical pages
 */
public int getNumVerticalPages() {
	int pages = 0;

	// no pages if there are no nodes in the outliner
	int rows = outliner.getNumNodes();
	if(rows == 0)
		return pages;

	// no pages if the header height is greater than the page height
	int header_height = JCComponent.getPreferredSize(
									 outliner.getHeader()).height;
	if(header_height > internal_height)
		return pages;

	int horiz_pages = getNumHorizontalPages();
	
	// get the node height
	int node_height = outliner.getNodeHeight();
	for(int i = 0; i < outliner.style_list.length; i++)
		node_height = Math.max(node_height, outliner.calculateStyleHeight(outliner.style_list[i]));
	node_height += 2 * outliner.getHighlightThickness() +
		outliner.getSpacing();

	int index = 0;
	int size = outliner.node_list.size();

	int page_node_height = internal_height -
		2 * (outliner.getBorderThickness() + outliner.getHighlightThickness()) -
		outliner.getInsets().top - outliner.getInsets().bottom;
	int current_height = 0;
	int page_height = 0;

	JCOutlinerNode node = outliner.getRootNode();

	// loop through each node and increment the page count as required
	for(int n = 0; n < size; n++) {
		if(print_all_nodes || outliner.shouldBeDrawn(node)) {
			// determine whether to increment the page count
			if(current_height + node_height > page_height) {
				page_height = page_node_height;
				if(repeat_header || pages == 0)
					page_height -= JCComponent.getPreferredSize(
												outliner.getHeader()).height;
				pages++;
				current_height = node_height;
			} else
				current_height += node_height;
		}
		node = outliner.getNextNode(node);
		if(node == null)
			break;
	}
	return pages;			
}

/**
 * Draws a page.
 * @exception IllegalArgumentException If the page doesn't exist
 */
public void drawPage(Graphics gc, int page) throws IllegalArgumentException {
	current_page = page;

	int working_page = page - 1;

	if(working_page < 0 || working_page >= getNumPages())
	    throw new IllegalArgumentException("invalid page number: " + page);

	int start_column = getPageStartColumn(working_page);

	int horiz_pages = getNumHorizontalPages();

	if(horiz_pages == 0)
	    return;
	int rp = working_page / horiz_pages + 1;
	int cp = working_page % horiz_pages + 1;

	int end_column = outliner.getNumColumns() - 1;
	if(cp == horiz_pages)
		end_column = outliner.getNumColumns() - 1;
	else 
		end_column = getPageStartColumn(working_page + 1) - 1;
		
	// print the background
	gc.setColor(outliner.getBackground());
	gc.fillRect(0, 0, page_width, page_height);

	// print the column headers
	JCHeader header = outliner.getHeader();
	JCComponent columns[] = header.getLabels();
	
	int x = margin.left;
	int y = margin.top;

	outliner.calcColumnWidths();

	int header_height = 0;
	if (repeat_header || rp == 1) {
		header_height = JCComponent.getPreferredSize(header).height;
		// print the outline column
		if (columns.length > 0 && (repeat_outline || cp == 1)) {
			int width = columns[0].getSize().width;

			columns[0].setDoubleBuffer(false);
			Graphics draw_gc = gc.create();
			draw_gc.translate(x, y);
			columns[0].repaint(draw_gc, 0, 0, width, header_height);
			draw_gc.dispose();
			columns[0].setDoubleBuffer(true);
			x += width - 1;
		}
		// print the remaining columns
		for(int c = start_column; c <= end_column; c++) {
			int width = columns[c].getSize().width;
			
			columns[c].setDoubleBuffer(false);
			Graphics draw_gc = gc.create();
			draw_gc.translate(x, y);
			if(x + width - margin.left > internal_width)
				width = internal_width - x + margin.left;
			columns[c].repaint(draw_gc, 0, 0, width, header_height);
			draw_gc.dispose();
			columns[c].setDoubleBuffer(true);
			x += width - 1;
		}
	}
	// print the nodes
	Rectangle draw_rect = new Rectangle(margin.left, y + header_height,
			internal_width, internal_height - header_height);

	int[] col;
	int start = 0;
	if(repeat_outline || cp == 1) {
		col = new int[2 + end_column - start_column];
		col[0] = 0;
		start++;
	} else {
		col = new int[1 + end_column - start_column];
	}
	for(int c = start_column; c <= end_column; c++)
		col[start++] = c;	
	
	outliner.paintOutliner(gc, draw_rect, getPageStartNode(working_page), col, print_all_nodes);

	// notify listeners that the page header/footer can be drawn
	doPrintPageHeader(gc, page);
	doPrintPageFooter(gc, page);
}

/**
 * Returns the position on the current page where outliner printing begins.
 */
int getCurrentPageTopPosition() {
	int working_page = current_page - 1;
	int horiz_pages = getNumHorizontalPages();
	
	if(horiz_pages == 0)
	    return 0;
	int rp = working_page / horiz_pages + 1;
	int cp = working_page % horiz_pages + 1;

	if(repeat_header || rp == 1)
		return margin.top + JCComponent.getPreferredSize(
											outliner.getHeader()).height;
	else
		return margin.top;
}

/**
 * Returns the node at the top of a page
*/
private JCOutlinerNode getPageStartNode(int page) {
	
	int horiz_pages = getNumHorizontalPages();
	
	if(horiz_pages == 0)
	    return null;
	int rp = page / horiz_pages + 1;
	int cp = page % horiz_pages + 1;

	if(rp == 1)
		return outliner.getRootNode();
	
	// get the node height
	int node_height = outliner.getNodeHeight();
	for(int i = 0; i < outliner.style_list.length; i++)
		node_height = Math.max(node_height, outliner.calculateStyleHeight(outliner.style_list[i]));
	node_height += 2 * outliner.getHighlightThickness() +
		outliner.getSpacing();

	int index = 0;
	// go through each page and figure out what node starts at the top of the page
	for(int p = 1; p < rp; p++) {
		// calculate the height of the working area on the page
		int height = internal_height - 2 * (outliner.getBorderThickness() +
											outliner.getHighlightThickness())
					- outliner.getInsets().top - outliner.getInsets().bottom;
		if(repeat_header || p == 1)
			height -= JCComponent.getPreferredSize(
											outliner.getHeader()).height;
		index += height / node_height;	
	}
	index++;

	if(index > outliner.node_list.size() - 1)
		return null;

	// determine which node is at the index position
	int cnt = 1;
	JCOutlinerNode node = outliner.getRootNode();
	while(node != null && cnt < index) {
		if(print_all_nodes || outliner.shouldBeDrawn(node))
			cnt++;
		node = outliner.getNextNode(node);
	}
	return node;
}

/**
 * Returns the column at the left of the page
 */
private int getPageStartColumn(int page) {

	int horiz_pages = getNumHorizontalPages();
	
	if(horiz_pages == 0)
	    return 0;
	int rp = page / horiz_pages + 1;
	int cp = page % horiz_pages + 1;

	if(cp == 1)
		return 1;

	int column = 1;
	int start_column = 1;
	int outline_width = outliner.getColumnWidth(0);
	for(int p = 1; p <= cp; p++) {
		int current = 0;
		if(repeat_outline || p == 1)
			current += outline_width;
		column = start_column;
		for(int c = start_column; c < outliner.getNumColumns(); c++) {
			if(repeat_outline && c == start_column && ((outline_width + outliner.getColumnWidth(c)) > internal_width)) {
				start_column = c + 1;
				break;
			} else if(!repeat_outline && c == start_column && (outliner.getColumnWidth(c) > internal_width)) {
				start_column = c + 1;
				break;
			} else {
				current += outliner.getColumnWidth(c);
				if(current > internal_width) {
					start_column = c;
					break;
				}
			}
		}
	}
	return column;
}

/**
 * Sets the internal dimensions (page dimensions minus margins)
 */
private void setInternalDimensions() {
	int unit_conversion = (margin_unit == MARGIN_IN_INCHES)
			? page_resolution : 1;

	internal_width = page_width - unit_conversion * (margin.left + margin.right);
	internal_height = page_height - unit_conversion * (margin.top + margin.bottom);
}

/**
 * Notifies outliner print listeners that the page header is being printed
 */
private void doPrintPageHeader(Graphics gc, int page) {
	Graphics gc_header = gc.create();
	
	int unit_conversion = (margin_unit == MARGIN_IN_INCHES) 
			? page_resolution : 1;
	
	gc_header.clipRect( unit_conversion * margin.left, 0,
						internal_width, unit_conversion * margin.top);
	gc_header.translate( unit_conversion * margin.left, 0);

	JCOutlinerPrintEvent ev = new JCOutlinerPrintEvent(outliner,
								gc_header, page + 1, JCOutlinerPrintEvent.HEADER);
	JCVector v = outliner.printListeners;
	for(int i = 0; i < v.size(); i++)
		((JCOutlinerPrintListener)v.elementAt(i)).printPageHeader(ev);

	gc_header.dispose();
}

/**
 * Notifies outliner print listeners that the page footer is being printed
 */
private void doPrintPageFooter(Graphics gc, int page) {
	Graphics gc_footer = gc.create();

	int unit_conversion = (margin_unit == MARGIN_IN_INCHES) 
			? page_resolution : 1;
	
	gc_footer.clipRect( unit_conversion * margin.left,
						page_height - unit_conversion * margin.bottom,
						internal_width, unit_conversion * margin.bottom);
	gc_footer.translate(unit_conversion * margin.left,
						page_height - unit_conversion * margin.bottom);

	JCOutlinerPrintEvent ev = new JCOutlinerPrintEvent(outliner,
								gc_footer, page, JCOutlinerPrintEvent.FOOTER);
	JCVector v = outliner.printListeners;
	for(int i = 0; i < v.size(); i++)
		((JCOutlinerPrintListener)v.elementAt(i)).printPageFooter(ev);

	gc_footer.dispose();
}

}
