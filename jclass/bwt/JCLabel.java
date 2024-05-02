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
// RCSID -- $RCSfile: JCLabel.java $ $Revision: 2.10 $ $Date: 2000/11/09 20:10:36 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;
import java.net.URL;

/**
 * A component that displays multiple lines of read-only text and/or images.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Label               </td><td><a href=#setLabel>setLabel</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.base.BaseComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>overridden JCComponent fields</strong><br>
 * <pre>
 * Name           New value    Reference
 * highlight      0            <a href=jclass.bwt.JCComponent.html#setHighlightThicknss>setHighlightThicknss</a>
 * insets         (2,5,2,5)    <a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a>
 * shadow         0            <a href=jclass.bwt.JCComponent.html#shadow>shadow</a>
 * traversable    false        <a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a>
 * </pre>
 */
public class JCLabel extends JCComponent {

public static final int TOPLEFT = BWTEnum.TOPLEFT;
public static final int LEFT = BWTEnum.LEFT;
public static final int TOPCENTER = BWTEnum.TOPCENTER;
public static final int CENTER = BWTEnum.CENTER;
public static final int TOPRIGHT = BWTEnum.TOPRIGHT;
public static final int RIGHT = BWTEnum.RIGHT;
public static final int MIDDLELEFT = BWTEnum.MIDDLELEFT;
public static final int MIDDLECENTER = BWTEnum.MIDDLECENTER;
public static final int MIDDLE = BWTEnum.MIDDLE;
public static final int MIDDLERIGHT = BWTEnum.MIDDLERIGHT;
public static final int BOTTOMLEFT = BWTEnum.BOTTOMLEFT;
public static final int BOTTOMCENTER = BWTEnum.BOTTOMCENTER;
public static final int BOTTOMRIGHT = BWTEnum.BOTTOMRIGHT;

public static final int SHADOW_NONE = BWTEnum.SHADOW_NONE;
public static final int SHADOW_ETCHED_IN = BWTEnum.SHADOW_ETCHED_IN;
public static final int SHADOW_ETCHED_OUT = BWTEnum.SHADOW_ETCHED_OUT;
public static final int SHADOW_IN = BWTEnum.SHADOW_IN;
public static final int SHADOW_OUT = BWTEnum.SHADOW_OUT;
public static final int SHADOW_PLAIN = BWTEnum.SHADOW_PLAIN;
public static final int SHADOW_FRAME_IN = BWTEnum.SHADOW_FRAME_IN;
public static final int SHADOW_FRAME_OUT = BWTEnum.SHADOW_FRAME_OUT;

int 			alignment = MIDDLECENTER;
Object 			label;
protected int	label_width, label_height;
protected Rectangle		label_rect = new Rectangle();

/** The url that the mouse is currently over. */
transient protected 		String url;
transient URL label_url;

private static final String base = "label";
private static int nameCounter = 0;

/** Creates an empty label. No parameters are read from an HTML file. */
public JCLabel() {
	this(null, null, null);
}

/** Creates a label with the specified text.
 * No parameters are read from an HTML file.
 * @see #setLabel
 */
public JCLabel(Object label) {
	this(label, null, null);
}

/** Creates a label from a String and an Image. 
 * @param layout the relative position of the string with respect to the image:
 * BWTEnum.STRING_LEFT, STRING_RIGHT, STRING_TOP or STRING_BOTTOM
 */
public JCLabel(String s, Image image, int layout) {
	this(new JCString(s, image, layout), null, null);
}

/** Creates a label constructed from a String and an Image file. 
 * @param applet the applet that is loading the image.  If the applet
 * is in a browser, Applet.getImage() is used in order to take
 * advantage of Applet.getDocumentBase()
 * @param image the file containing the image to be loaded; if an http protocol
 * is not specified (a ":" is not present), the current document base or
 * working directory is prepended to the file name
 * @param layout the relative position of the string with respect to the image:
 * BWTEnum.STRING_LEFT, STRING_RIGHT, STRING_TOP or STRING_BOTTOM
 */
public JCLabel(String s, String image, Applet applet, int layout) {
	this(null, null, null);
	setLabel(new JCString(s, conv.toImage(applet, image), layout));
}

/** Creates a label which reads parameters from the applet's HTML file.
 * @param label the initial label
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setLabel
 * @see java.applet.Applet#getParameter
 */
public JCLabel(Object label, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	highlight = border = 0;
	traversable = false;
	insets = new Insets(2,5,2,5);
	if (getClass().getName().equals("jclass.bwt.JCLabel"))
		 getParameters(applet);
	if (label != null)
		setLabel(label);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	LabelConverter.getParams(this);
}

/** Gets the label's alignment.
 * @see #setAlignment
 */
public int getAlignment() { return alignment; }

/** Sets the position of the value within the label:
Label.LEFT, CENTER or RIGHT; or
JCLabel.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER (default), MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT.<p>
 * <strong>HTML param name/value:</strong> "Alignment"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setAlignment(int v) {
	LabelConverter.checkAlignment(v);
	if (v != alignment) {
		alignment = v;
		layout();
		repaint();
	}
}

/**
 * Workaround method for Visual Cafe 2.5+ for SWING releases. This method 
 * should be used if the alignment property does not appear in the property
 * list.  The equivalent method is setAlignment.
 * @see #setAlignment 
 */
/* SWING_START */
public void setLabelAlignment(int v) {
	setAlignment(v);
}
/* SWING_END */

/**
 * Workaround method for Visual Cafe 2.5+ for SWING releases. This method 
 * should be used if the alignment property does not appear in the property
 * list.  The equivalent method is getAlignment.
 * @see #getAlignment 
 */
/* SWING_START */
public int getLabelAlignment() {
	return getAlignment();
}
/* SWING_END */

/** Sets the label's font. */
public void setFont(Font f) {
	boolean changed = (getFont() != null && !getFont().equals(f));
	super.setFont(f);
	if (changed) {
		setLabelSize(label);
		layout();
		repaint();
	}
}

/** Sets the label's font. */
void setFontInternal(Font f) {
	super.setFont(f);
	setLabelSize(label);
	layout();
}

/** Gets the label's value.
 * @see #setLabel
 */
public Object getLabel() { return label; }

/** Sets the value displayed in the label.<p>
 * <strong>HTML param name/value:</strong> "Label"/string<p>
 * @param v a String, Image, JCString, or any object (in which case the object's
 * toString() method is called to obtain a string).
 * @see jclass.util.JCString
 * @see jclass.util.JCConverter#toJCString
 */
public void setLabel(Object v) {
	label = v;
	setLabelSize(label);
	layout();
	repaint();
}

/**
 * Gets the text of this label as a String.
 * @see #setText
 */
public String getText() { return (label != null) ? label.toString() : null; }

/**
 * Sets the text for this label to the specified text.
 * @see #getText
 */
public void setText(String label) { setLabel(label); }

/**
 * Gets the text of this label as a String list.
 * @see #setTextList
 */
public String[] getTextList() { 
	if (label == null) return null;
	String s = label.toString();
	JCStringTokenizer string = new JCStringTokenizer(s);
	String list[] = new String[string.countTokens('\n')];
	for (int i=0; string.hasMoreTokens(); i++) 
		list[i] = string.nextToken('\n').trim();
	return list;
}

/**
 * Sets the text for this label to the specified String list. This creates
 * a multi-line label.
 * @see #getTextList
 */
public void setTextList(String[] l) {
	if (l == null) {
		setLabel(null);
		return;
	}
	StringBuffer buf = new StringBuffer();
	for (int i=0; i < l.length; i++) {
		buf.append(l[i]);
		if (i < l.length-1)
			buf.append('\n');
	}
	setLabel(new String(buf)); 
}

/**
 * Gets the URL which was used to create the label's image.
 * @see #setLabelImage
 * @return null if the label was not set using an URL
 */
public URL getLabelImage() { return label_url; }

/**
 * Sets the text for this label to the specified image.
 */
public void setLabelImage(URL label) {
	label_url = label;
	setLabel(getToolkit().getImage(label)); 
}

/** Gets the shadow style. 
 * @see #setShadowType
 */
public int getShadowType() { return border_style; }

/** Sets the shadow style. Valid values are:
<pre>
 SHADOW_ETCHED_IN        double line; label appears inset
 SHADOW_ETCHED_OUT       double line; label appears raised
 SHADOW_FRAME_IN         1-pixel shadow-in at edge; label appears framed
 SHADOW_FRAME_OUT        1-pixel shadow-out at edge; label appears framed
 SHADOW_IN               label appears inset (default)
 SHADOW_OUT              label appears raised
 SHADOW_PLAIN            shadow drawn in foreground color
 SHADOW_NONE             no shadow
</pre>
* The width of the shadow is specified by ShadowThickness, which must be greater 
* than 5 pixels to see the effects of the ETCHED and FRAME shadows.
* Note that the default thickness is 0, so no shadow is drawn.
* @see jclass.base.BaseComponent#setShadowThickness
*/
public void setShadowType(int v) {
	setBorderStyle(v);
}

/** Draws the interior value. */
protected synchronized void drawValue(Graphics gc, Object value) {
	if (!isEnabled()) {
		Color old_color = gc.getColor();
		gc.translate(1,1);
		gc.setColor(Color.white);
		BWTUtil.draw(this, gc, value, alignment, label_rect);
		gc.translate(-1,-1);
		gc.setColor(getBackground().darker());
		BWTUtil.draw(this, gc, value, alignment, label_rect);
		gc.setColor(old_color);
		return;
	} 
	else {
		BWTUtil.draw(this, gc, value, alignment, label_rect);
	}

}

/**
 * Draws value.
 */
protected void paintComponent(Graphics gc) {
	if (needs_layout)
		layout();
	drawValue(gc, label);
}

/** Set the value's size internally, for use in layout().
 * @see #layout
 */
protected void setLabelSize(Object value) {
	setLabelSize(value, getFont());
}

/**
 * Set the value's size internally, for use in layout().
 * @see #layout
 */
protected void setLabelSize(Object value, Font font) {
	if (!isDisplayable())
		return;
	label_width = BWTUtil.getWidth(value, this, font);
	label_height = BWTUtil.getHeight(value, this, font);
/* JDK110_START */
	if (applet_context != null && BWTUtil.is_jcstring(value)) 
		enableEvents(JCAWTEvent.MOUSE_MOTION_EVENT_MASK);
/* JDK110_END */
}

public void addNotify() {
	super.addNotify();
	setLabelSize(label);
	if (needs_layout)
		layout();
}

protected int preferredWidth() { 
	return Math.max(20, label_width);
}

protected int preferredHeight() { 
	return Math.max(20, label_height);
}

/** Gets the bounding rectangle for the label value. 
 * @see #setText
 */
public Rectangle getLabelBounds() { return label_rect; }

public void layout() {
	if (!isDisplayable())
		return;

	int height = 0, width = 0;

	synchronized (this) {

		getDrawingArea(label_rect);

		/* The following was added by GV to fix layout problems
		 * in JBuilder.  If addNotify() is called on an individual
		 * component before parent layout occurs, it is possible
		 * for the size() to be (0,0).  This causes invalid layout.
		 * The work-around is to defer layout until the first paint.
		 */
		if (label_rect.width <= 0 || label_rect.height <= 0) {
			needs_layout = true;
			return;
		}
		needs_layout = false;

		if (BWTUtil.isRight(alignment))
			label_rect.x += label_rect.width - label_width;

		else if (BWTUtil.isCenter(alignment))
			label_rect.x += (label_rect.width - label_width) / 2;

		if (BWTUtil.isMiddle(alignment))
			label_rect.y += (label_rect.height - label_height) / 2;

		else if (BWTUtil.isBottom(alignment))
			label_rect.y += label_rect.height - label_height;

		width = label_width;
		height = label_height;
	}

	label_rect.resize(width, height);
}

/** Lays out the label's internal elements, using the specified value. */
protected void layout(Object value) {
	setLabelSize(value);
	layout();
}

/** If the label is being displayed in a browser and the mouse is over an URL,
 * the cursor is changed.
 */
public boolean mouseMove(Event ev, int x, int y) {
	if (applet_context != null && BWTUtil.is_jcstring(label)) {
		url = JCString.getURL(applet, label, x, y);
		if (url != null) {
			setCursor(Frame.HAND_CURSOR);
			return true;
		}
	}
	url = null;
	setCursor(Frame.DEFAULT_CURSOR);
	return false;
}

/** If the mouse is over an URL, display it. */
public boolean mouseUp(Event ev, int x, int y) {
	if (url != null)
		JCString.showURL(url, applet_context, applet);
	return false;
}

/*
public String toString() {
	return label != null ? "'"+label+"' "+super.toString() : super.toString();
}
*/

}
