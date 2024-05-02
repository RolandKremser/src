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
// RCSID -- $RCSfile: JCGroupBox.java $ $Revision: 2.5 $ $Date: 2000/11/09 20:10:32 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.applet.*;
import java.awt.*;

/**
 * JCGroupBox is a simple container used to enclose a single work
 * area child in a shadow drawn by JCGroupBox. It performs geometry
 * management so that its size always matches its child's outer
 * size plus the JCGroupBox's margins and shadow thickness.
 * It can also be assigned a title which is drawn at the top.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness     </td><td><a href=#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Title               </td><td><a href=#setTitle>setTitle</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table>
 */
public class JCGroupBox extends JCContainer {

public final static int LEFT = BWTEnum.LEFT;
public final static int CENTER = BWTEnum.CENTER;
public final static int RIGHT = BWTEnum.RIGHT;

/** The container's shadow thickness. */
protected int 		shadow = 2;
int			title_align;
Object		title = null;
int			title_width = 0, title_height = 0;
Rectangle	title_rect = new Rectangle();

private static final String base = "groupbox";
private static int nameCounter = 0;

/** Creates an empty groupBox. No parameters are read from an HTML file. */
public JCGroupBox() {
	this(null, null);
}

/** Creates a groupBox with the specified title.
 * No parameters are read from an HTML file. 
 * @param title a String, Image, JCString, or any object (in which case the object's toString() method is called to obtain a string)
 * @see #setTitle
 */
public JCGroupBox(Object title) {
	this(null, null);
	setTitle(title);
}

/** Creates a GroupBox which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCGroupBox(Applet applet, String name) {
	super(applet, name);

	// Force child to be same size as box
	setLayout(new GridLayout(1,1));

	if (name == null)
		setName(base + nameCounter++);
	if (getClass().getName().equals("jclass.bwt.JCGroupBox"))
		 getParameters(applet);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	GroupBoxConverter.getParams(this);
}

/** Gets the shadow's thickness.
 * @see #setShadowThickness
 */
public int getShadowThickness() { return shadow; }

/** Sets the shadow's thickness (default: 2).<p>
 * <strong>HTML param name/value:</strong> "ShadowThickness"/int
 */
public void setShadowThickness(int v) {
	shadow = v;
	layout();
	repaint();
}

/** Gets the title.
 * @see #setTitle
 */
public Object getTitle() { return title; }

/** Sets a string to be used as the panel's title, with LEFT alignment.<p>
 * <strong>HTML param name/value:</strong> "Title"/string<p>
 * @param title a String, Image, JCString, or any object (in which case the object's toString() method is called to obtain a string)
 * @see jclass.util.JCConverter#toJCString
 */
public void setTitle(Object title) {
	setTitle(title, LEFT);
}

/** Gets the title as a String.
 * @see #setTitle
 */
public String getTitleString() { return title != null ? title.toString() : null; }

/** Sets a string to be used as the panel's title, with LEFT alignment.<p>
 * <strong>HTML param name/value:</strong> "Title"/string<p>
 * @param title a String, Image, JCString, or any object (in which case the object's toString() method is called to obtain a string)
 * @see jclass.util.JCConverter#toJCString
 */
public void setTitleString(String title) {
	setTitle(title, LEFT);
}

final static int[] align_values = { LEFT, CENTER, RIGHT };

/** Sets a string to be used as the panel's title.
 * @param title a String, Image, JCString, or any object (in which case the object's toString() method is called to obtain a string)
 * @param alignment: LEFT, CENTER or RIGHT
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setTitle(Object title, int alignment) {
	jclass.util.JCUtilConverter.checkEnum(alignment, "alignment", align_values);
	this.title = title;
	title_align = alignment;
	calcTitleSize();
	layout();
	repaint();
}

public void addNotify() {
	super.addNotify();
	calcTitleSize();
}

/** Calculates the title size. */
protected void calcTitleSize() {
	if (!isDisplayable()) return;
	title_width = BWTUtil.getWidth(title, this);
	title_height = BWTUtil.getHeight(title, this);
}

/** Lays out the boxes's internal elements. */
public synchronized void layout() {
	if (!isDisplayable()) return;
	super.layout();
	int x, width = Math.max(0, size().width - 2 * shadow);

	if (BWTUtil.isRight(title_align))
		x = shadow + width - title_width - JCComponent.getInsets(this).right;

	else if (BWTUtil.isCenter(title_align))
		x = shadow + (width - title_width) / 2;

	else
		x = shadow + JCComponent.getInsets(this).left;
	FontMetrics fm = getGraphics().getFontMetrics();
	JCComponent.setBounds(title_rect, x, 
						  (fm.getHeight() - fm.getAscent())/2 - 1,
						  title_width, title_height);
}

/* AWT_START 
public void reshape(int x, int y, int w, int h) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int w, int h) {
/* SWING_END */

	boolean clear = (size().width > 0 && (w > size().width || h > size().height));
	if (isDisplayable() && isShowing() && clear && shadow > 0) {
		Graphics g = getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, w, h);
	}
	
/* AWT_START 
	super.reshape(x, y, w, h);
 AWT_END */
/* SWING_START */
 	super.setBounds(x, y, w, h);
/* SWING_END */

	layout();
}

/**
 * Modifies the default insets to account for the shadows and title.
 */
/* AWT_START 
public Insets insets() {
 AWT_END */
/* SWING_START */
public Insets getInsets() {
/* SWING_END */

/* AWT_START 
	Insets r = super.insets();
 AWT_END */
/* SWING_START */
	Insets r = super.getInsets();
/* SWING_END */

	return new Insets(r.top + Math.max(title_height, shadow + title_height / 2),
				   r.left + shadow,
				   r.bottom + shadow+2,
				   r.right + shadow+3);
}

/** Draws an ETCHED_IN shadow. */
protected void drawShadow(Graphics g, int x, int y, int w, int h) {
	h -= shadow;
	w -= shadow;
	g.setColor(BWTUtil.darker(getBackground()));
	for (int i=0; i < shadow/2; i++, x++, y++)
		g.drawRect(x, y, w-2*i-3, h-2*i-3);

	g.setColor(BWTUtil.brighter(getBackground()));
	for (int i=0; i < shadow/2; i++, x++, y++)
		g.drawRect(x, y, w-2*i-3, h-2*i-3);
}

/** Draws the title. */
protected void drawTitle(Graphics gc) {
	gc.setColor(getBackground());
	gc.fillRect(title_rect.x-1, title_rect.y,
				title_rect.width+2, title_rect.height);
	gc.clipRect(shadow, 0, size().width - 2 * shadow, size().height);
	gc.setColor(getForeground());
	gc.setFont(getFont());
	BWTUtil.draw(this, gc, title, title_align, title_rect);
}

/**
 * Draws the shadows and title.
 */
public void paintInterior(Graphics gc) {
	int offset = title_height / 2;
/* AWT_START 
	gc.clearRect(0, 0, size().width, size().height);
 AWT_END */
// Workaround for swing idiosyncracy -- clearRect() in a frame launched from 
// an applet always fills in white.  We want the parent's background color.
/* SWING_START */
    if (getParent() != null)
        gc.setColor(getParent().getBackground());
    gc.fillRect(0, 0, size().width, size().height);
/* SWING_END */
	drawShadow(gc, 0, offset, size().width, size().height - offset);
	if (title != null)
		drawTitle(gc);
}

/** Includes the title width in the preferred size. */
protected int preferredWidth() {
/* AWT_START 
    Dimension pref = super.preferredSize();
 AWT_END */
/* SWING_START */
	Dimension pref = super.getPreferredSize();
/* SWING_END */

	Insets in = JCComponent.getInsets(this);
	return Math.max(pref.width, title_width+4 + in.left + in.right);
}

}
