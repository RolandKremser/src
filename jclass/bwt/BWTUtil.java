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
// RCSID -- $RCSfile: BWTUtil.java $ $Revision: 2.10 $ $Date: 2000/11/09 20:09:12 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;
import java.util.*;

/** Various static utility routines. */
public class BWTUtil {

/**
 * Returns a lengthened copy of a list. 
 * @param list list to be lengthened
 * @param def default value for new items
 * @param len the desired new length. If the list's length is less than 
 * or equal to this value, the list is simply returned
 */
public final static Object[] copyList(Object[] list, int len, Object def) {
	if (list != null && len <= list.length) 
		return list;
	Object old_list[] = list;
	int old_list_len = (old_list != null) ? old_list.length : 0;
	list = new Object[len];
	if (old_list != null)
		System.arraycopy(old_list, 0, list, 0, old_list.length);
	for (int i=old_list_len; i < list.length; i++)
		list[i] = def;
	return list;
}

/**
 * Returns a lengthened copy of a list. 
 * @param list list to be lengthened
 * @param def default value for new items
 * @param len the desired new length. If the list's length is less than 
 * or equal to this value, the list is simply returned
 */
public final static int[]
copyList(int[] list, int len, int def) {
	if (list != null && len <= list.length) 
		return list;
	int old_list[] = list, 
		old_list_len = (old_list != null) ? old_list.length : 0;
	list = new int[len];
	if (old_list != null)
		System.arraycopy(old_list, 0, list, 0, old_list.length);
	for (int i=old_list_len; i < list.length; i++)
		list[i] = def;
	return list;
}

/**
 * Returns a lengthened copy of a list. 
 * @param list list to be lengthened
 * @param def default value for new items
 * @param len the desired new length. If the list's length is less than 
 * or equal to this value, the list is simply returned
 */
public final static boolean[] copyList(boolean[] list, int len, boolean def) {
	if (list != null && len <= list.length) 
		return list;
	boolean old_list[] = list;
	int old_list_len = (old_list != null) ? old_list.length : 0;
	list = new boolean[len];
	if (old_list != null)
		System.arraycopy(old_list, 0, list, 0, old_list.length);
	for (int i=old_list_len; i < list.length; i++)
		list[i] = def;
	return list;
}

/** Checks whether the rectangle intersects the component. */
public final static boolean intersects(Component comp, int x, int y,
									   int w, int h) {
	return !(comp.size().width <= x || comp.size().height <= y ||
			 x + w < 0 || y + h < 0);
}

/** Counts the occurrences of the char between start and end-1. */
public static int countChar(String s, char c, int start, int end) {
	if (s == null || start >= s.length() || start >= end) return 0;
	if (start < 0) start = 0;
	int count = 0;
	for (start = s.indexOf(c, start); start < end; 
		 start = s.indexOf(c, start)) {
		if (start == -1) break;
		start++;
		count++;
	}
	return count;
}
 
/**
 * Returns the index within the array of the first occurrence of the specified 
 * character, starting the search at fromIndex.
 * @return -1 if not found
 */
static final int indexOf(char[] array, int array_len, int ch, int from) {
	for (int i = from; i < array_len; i++) {
	    if (array[i] == ch) 
			return i;
	}
	return -1;
}

/** Counts the occurrences of the char between start and end-1. */
public static int countChar(char[] array, int array_len,
							char c, int start, int end) {
	if (array == null || start >= array_len || start >= end) return 0;
	end = Math.min(end, array_len);
	if (start < 0) start = 0;
	int count = 0;
	for (start = indexOf(array, array_len, c, start); start < end; 
		 start = indexOf(array, array_len, c, start)) {
		if (start == -1) break;
		start++;
		count++;
	}
	return count;
}

/**
 * Translates a point in a child's co-ordinate system to its parent's. 
 * @param parent a parent or grandparent of the child
 * @param x,y co-ordinates in child's system
 */
public static Point translateToParent(Container parent, Component child,
									  int x, int y) {
	return jclass.base.BaseComponent.translateToParent(parent, child, x, y);
}

/**
 * Translates a point in a parent's co-ordinate system to its child's. 
 * @param parent a parent or grandparent of the child
 * @param x,y co-ordinates in parent's system
 */
public static Point translateFromParent(Container parent, Component child,
										int x, int y) {
	return jclass.base.BaseComponent.translateFromParent(parent, child, x, y);
}

/** Returns the position that ensures that the component is visible on the screen.
 * @param x,y component's location
 * @param w,h component's size
 */
public static Point getVisibleScreenLoc(Component comp,
										int x, int y, int w, int h) {
	return jclass.base.BaseComponent.getVisibleScreenLoc(comp, x, y, w, h);
}

/** Creates an image if necessary. */
static synchronized Image createImage(Component comp, Image image,
									  int width, int height) {
	return jclass.base.BaseComponent.createImage(comp, image, width, height);
}

/** Creates an image and caches it. */
static synchronized Image createImage(Component comp, int width, int height) {
	return jclass.base.BaseComponent.createImage(comp, width, height);
}

/** Gets the component's parent frame. */
public static Frame getFrame(Component comp) {
	return jclass.base.BaseComponent.getFrame(comp);
}

/** Gets the component's parent window (either a Frame or a Dialog). */
public static Window getWindow(Component comp) {
	return jclass.base.BaseComponent.getWindow(comp);
}

/**
 * Follows the component's parents until 
 * a parent applet is found.  Returns null if no applet is found.
 */
public static Applet getApplet(Component comp) {
	return jclass.base.BaseComponent.getApplet(comp);
}

/** Gets the component's applet's context. The applet context
 * lets an applet control the applet's environment which is
 * usually the browser or the applet viewer.
 * @return null if the component is not in an applet or the applet is not in
 * a browser.
 * @see java.applet.Applet#getAppletContext
 */
public static AppletContext getAppletContext(Applet applet) {
	return jclass.base.BaseComponent.getAppletContext(applet);
}

/** Returns true if the component is in an applet in a browser
 * (ie its AppletContext is not null).
 */
public static boolean inBrowser(Component comp) {
	return getAppletContext(getApplet(comp)) != null;
}

/** Calculates the color even when it is saturated, for example,
 * when it is black or white
 */
public static Color brighter(Color color) {
	return jclass.base.Border.brighter(color);
}

/** Calculates the color even when it is saturated, for example,
 * when it is black or white.
 */
public static Color darker(Color color) {
	return jclass.base.Border.darker(color);
}

/** Returns true if one of the object's superclasses' names contains the string */
public static boolean instanceOf(Object obj, String name) {
	if (obj == null) return false;
	Class c = obj.getClass();
	boolean status;

	while (!(status = c.getName().indexOf(name) != -1))
		if ((c = c.getSuperclass()) == null)
			break;
	return status;
}

/** Returns true if value is a JCString. */
static boolean is_jcstring(Object v) {
	return instanceOf(v, "JCString");
}

/** Returns true if the container is equal to the component,
 *  or is its parent or grandparent. 
 */
public static boolean isParent(Component parent, Component comp) {
	if (comp == parent) return true;
	if (parent == null || comp == null || !(parent instanceof Container))
		return false;
	Container temp = comp.getParent();
	while (temp != null) {
		if (temp == parent) return true;
		temp = temp.getParent();
	}
	return false;
}

/** Gets the total width of all the vector's elements. */
public static int getWidth(Vector value, JCMultiColumnInterface comp) {
	if (value == null) return 0;
	int last_col = Math.min(comp.getNumColumns(), value.size());
	if (last_col == 0) return 0;
	int w = 0;
	for (int i=0; i < last_col; i++)
		w += comp.getColumnWidth(i);
	return w;
}

/** Gets the value's width using the component's font. */
public static int getWidth(Object value, Component comp) {
	return getWidth(value, comp, comp.getFont());
}

/** Gets the value's width using the specified font. 
 * If the value is a Vector, the sum of all its element's widths is returned.
 */
public static int getWidth(Object value, Component comp, Font font) {
	if (value == null)
		return 0;
	else if (value instanceof Image)
		return ((Image)value).getWidth(null);
	else if (is_jcstring(value))
		return ((JCString)value).getWidth(comp, font);
	else if (value instanceof Vector 
			 && comp instanceof JCMultiColumnInterface) {
		return getWidth((Vector)value, (JCMultiColumnInterface)comp);
	}
	else if (value instanceof Vector) {
		int w = 0;
		for (int i=0; i < ((Vector)value).size(); i++)
			w += getWidth(((Vector)value).elementAt(i), comp);
		return w;
	}

	String string = value.toString();
	if (string == null || string.length() == 0)
		return 0;

	FontMetrics fm = comp.getToolkit().getFontMetrics(font);

	// Manually calculate width of multi-line string
	if (string.indexOf('\n') != -1) {
		int start = 0, end, width = 0;
		for (; (end = string.indexOf('\n', start)) != -1;
			 start = end+1) {
			width = Math.max(width,
							 stringWidth(fm, font, string.substring(start, end)));
			if (font.isItalic())
				width += 5;
		}
		return Math.max(width,
					stringWidth(fm, font, string.substring(start, string.length())));
	}
	else
		return stringWidth(fm, font, string);
}

/** Accounts for bug in AWT which does not account for italic fonts. */
static int stringWidth(FontMetrics fm, Font font, String s) {
	return fm.stringWidth(s) + (font.isItalic() ? font.getSize()/3+1 : 0);
}

/** Gets the value's height using the component's font. 
 * If the value is a Vector, the largest of all its element's heights is returned.
 */
public static int getHeight(Object value, Component comp) {
	return getHeight(value, comp, comp.getFont());
}

/** Gets the value's height using the specified font. */
public static int getHeight(Object value, Component comp, Font font) {
	if (value == null)
		return 0;
	else if (value instanceof Image)
		return ((Image)value).getHeight(null);
	else if (is_jcstring(value)) 
		return ((JCString)value).getHeight(comp, font);
	else if (value instanceof Vector) {
		Vector v = (Vector) value;
		int h = 0;
		for (int i=0; i < v.size(); i++)
			h = Math.max(h, getHeight(v.elementAt(i), comp, font));
		return h;
	}

	return comp.getToolkit().getFontMetrics(font).getHeight()
		* getNumLines(value.toString());
}

private static int getNumLines(String s) {
	if (s == null || s.length() == 0)
		return 0;
	int lines = 1;
	for (int i=0; i < s.length(); i++)
		if (s.charAt(i) == '\n') lines++;
	return lines;
}

/** Gets the number of newlines in the value. */
public static int getNumLines(Object value) {
	return getNumLines(toString(value));
}

/** Gets the string representation of the value. */
static String toString(Object value) {
	if (value == null)
		return null;
	else if (value instanceof Image)
		return null;
	else if (is_jcstring(value))
		return ((JCString)value).getString();
	else if (value instanceof Vector) {
		String s;
		for (int i=0; i < ((Vector)value).size(); i++) 
			if ((s = toString(((Vector)value).elementAt(i))) != null)
				return s;
		return null;
	}
	else
		return value.toString();
}

/** Returns true if the first (uppercased) character of the string equivalent 
 * of the value starts with the specified character.
 */
static boolean startsWith(Object value, char c) {
	String s = toString(value);
	return (s != null && s.length() > 0) ?
		(Character.toUpperCase(s.charAt(0)) == c) : false;
}

// JCString enums
public static final int LEFT = 0;		// JCString.ALIGN_LEFT
public static final int CENTER = 1;		// JCString.ALIGN_CENTER;
public static final int RIGHT = 2;		// JCString.ALIGN_RIGHT;

/** Gets the horizontal component of an alignment. 
 * @return BWTUtil.LEFT, CENTER or RIGHT 
 */
public static int toHorizAlignment(int align) {
	return isCenter(align) ? CENTER : (isRight(align) ? RIGHT : LEFT);
}

static boolean isRight(int align) {
	return align == BWTEnum.TOPRIGHT
		|| align == BWTEnum.MIDDLERIGHT
		|| align == BWTEnum.BOTTOMRIGHT;
}

static boolean isCenter(int align) {
	return align == BWTEnum.TOPCENTER
		|| align == BWTEnum.MIDDLECENTER
		|| align == BWTEnum.BOTTOMCENTER;
}

static boolean isTop(int align) {
	return align == BWTEnum.TOPLEFT
		|| align == BWTEnum.TOPCENTER
		|| align == BWTEnum.TOPRIGHT;
}

static boolean isMiddle(int align) {
	return align == BWTEnum.MIDDLELEFT
		|| align == BWTEnum.MIDDLECENTER
		|| align == BWTEnum.MIDDLERIGHT;
}

static boolean isBottom(int align) {
	return align == BWTEnum.BOTTOMLEFT
		|| align == BWTEnum.BOTTOMCENTER
		|| align == BWTEnum.BOTTOMRIGHT;
}

/** Draws the value as a String, JCString or Image.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @param draw_rect rectangle within which to draw the value
 */
public synchronized static void draw(Component comp, Graphics gc,
									 Object value, int alignment,
	 Rectangle draw_rect) {
	if (value == null)
		return;
	int align = toHorizAlignment(alignment);

	if (is_jcstring(value)) {
		((JCString)value).draw(comp, gc, draw_rect, align);
		return;
	}

	else if (value instanceof Image) {
		gc.drawImage((Image)value, draw_rect.x, draw_rect.y, null);
		return;
	}

	String string = value.toString();
	if (string == null || string.length() == 0)
		return;

	FontMetrics fm = gc.getFontMetrics();
	Font font = gc.getFont();
	int h = fm.getHeight(), offset = 0, line_space = h - fm.getAscent();
	int y = draw_rect.y + h - line_space;
	int str_height = isTop(alignment) ? 0 : getHeight(string, comp, font);

	if (isBottom(alignment)) 
		y += draw_rect.height - str_height;
	else if (isMiddle(alignment)) 
		y += (draw_rect.height - str_height) / 2;

	// Manually draw multi-line string
	if (string.indexOf('\n') != -1) {
		int start = 0, end, x = draw_rect.x;

		for (; (end = string.indexOf('\n', start)) != -1;
			 start = end+1, y += h) {
			String s = string.substring(start, end);
			if (align == CENTER)
				offset = (draw_rect.width - stringWidth(fm, font, s)) / 2;
			else if (align == RIGHT)
				offset = draw_rect.width - stringWidth(fm, font, s);
			gc.drawString(s, draw_rect.x+offset, y);
		}
		String s = string.substring(start, string.length());
		if (align == CENTER)
			offset = (draw_rect.width - stringWidth(fm, font, s)) / 2;
		else if (align == RIGHT)
			offset = draw_rect.width - stringWidth(fm, font, s);
		gc.drawString(s, draw_rect.x+offset, y);
	}
	else {
		if (align == CENTER)
			offset = (draw_rect.width - stringWidth(fm, font, string)) / 2;
		else if (align == RIGHT)
			offset = draw_rect.width - stringWidth(fm, font, string);
		gc.drawString(string, draw_rect.x+offset, y);
	}
}

/** DEBUG */
public static void trace() {
 	try {
		throw new ArrayIndexOutOfBoundsException("");
 	} catch (Exception e) {
 	    e.printStackTrace(System.out);
 	}
}

/** Draws a dashed line. */
public static void drawDashedLine(Graphics gc, int x1, int y1,
								  int x2, int y2) {
	int from_x = Math.min(x1, x2), from_y = Math.min(y1, y2);
	int to_x = Math.max(x1, x2), to_y = Math.max(y1, y2);

	if (x2 == x1) {
		for (int y=from_y; y < to_y; y = Math.min(y+3, to_y))
			gc.drawLine(x1, y, x1, y+1);
	}
	else {
		for (int x=from_x; x < to_x; x = Math.min(x+3, to_x))
			gc.drawLine(x, y1, x+1, y1);
	}
}

/** Draws a dashed rectangle. */
public static void drawDashedRect(Graphics gc, int x, int y, int w, int h) {
	drawDashedLine(gc, x, y, x + w, y);
	drawDashedLine(gc, x + w, y, x + w, y + h);
	drawDashedLine(gc, x, y, x, y + h);
	drawDashedLine(gc, x, y + h, x + w, y + h);
}

/**
 * Paints a highlighted 3-D rectangle using the current color.
 * @param raised a boolean that states whether the rectangle is raised 
 */
static void  fill3DRect(Graphics gc, int x, int y, int w, int h,
						boolean raised) {
	Color c = gc.getColor(), brighter = brighter(c), darker = darker(c);

	if (!raised) 
	    gc.setColor(darker);
	gc.fillRect(x+1, y+1, w-2, h-2);
	gc.setColor(raised ? brighter : darker);
	gc.drawLine(x, y, x, y+h-1);
	gc.drawLine(x+1, y, x+w-2, y);
	gc.setColor(raised ? darker : brighter);
	gc.drawLine(x+1, y+h-1, x+w-1, y+h-1);
	gc.drawLine(x+w-1, y, x+w-1, y+h-2);
	gc.setColor(c);
}    

/**
 * Paints a highlighted 3-D rectangle using the current color.
 * A 1-pixel black line is drawn around the border.
 * @param raised a boolean that states whether the rectangle is raised 
 */
static void fill3DEdgeRect(Graphics gc, int x, int y, int w, int h,
						   boolean raised) {
	Color c = gc.getColor();
	fill3DRect(gc, x+1, y+1, w-2, h-2, raised);
	gc.setColor(Color.black);
	gc.drawRect(x, y, w-1, h-1);
	gc.setColor(c);
}    

/** Draws the image repeatedly across the component, 
 * from left to right and top to bottom. 
 */
public static void wallPaper(Component comp, Graphics gc, Image image) {
	Dimension size = comp.size();
	if (!JCUtilConverter.waitForImage(comp, image))
		return;

	int w = image.getWidth(comp), h = image.getHeight(comp);
	for (int r=0; r < size.width; r += w) {
		for (int c=0; c < size.height; c += h)
            gc.drawImage(image, r, c, comp);
	}
}

/** Returns the mouse button that was pressed: 1, 2, or 3 (left, middle, right) */
public static int getMouseButton(Event ev) {
	if (ev.metaDown()) return 3;
	else if ((ev.modifiers & Event.ALT_MASK) != 0) return 2;
	else return 1;
}

/** Sets the cursor in the parent frame. */
public static void setCursor(Component comp, int cursor) {
	/* 
	Cursor c = Cursor.getPredefinedCursor(cursor);
	if (comp.getCursor() != c)
		comp.setCursor(c);
	*/
/* JDK102_START 
	Frame frame = getFrame(comp);
	if (frame != null && frame.getCursorType() != cursor) {
		frame.setCursor(cursor);
		frame.getToolkit().sync();
	}
 JDK102_END */
/* JDK110_START */
	Window w = getWindow(comp);
	// for up to at least JDK 1.1.5 the Java VM deadlocks if we
	// set the cursor on a MODAL dialog.  So as a work around we
	// do NOT set the cursor if the parent Window is a MODAL dialog
	if (w instanceof Dialog) {
		if (((Dialog) w).isModal() == true) {
			// Don't set the cursor since this will cause a deadlock
			return;
		}
	}

	if (w != null) {
		w.setCursor(Cursor.getPredefinedCursor(cursor));
		comp.getToolkit().sync();
	}
/* JDK110_END */
}

}
