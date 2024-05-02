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
// RCSID -- $RCSfile: JCContainer.java $ $Revision: 2.37 $ $Date: 2000/12/19 00:06:55 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.base.BaseComponent;
import jclass.util.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */
/* SWING11_START
import com.sun.java.swing.SwingUtilities;
import com.sun.java.swing.border.Border;
import com.sun.java.swing.JComponent;
 SWING11_END */
/* SWING12_START */
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JComponent;
/* SWING12_END */

/**
 * The base class for all BWT containers.<p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=#setBackground>setBackground</a></td></tr><tr><td>
 * Foreground          </td><td><a href=#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=#setInsets>setInsets</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UserData            </td><td><a href=#setUserData>setUserData</a></td></tr>
 * </table><p>
 */
public class JCContainer extends
/* AWT_START 
Panel {
 AWT_END */
/* SWING_START */
JComponent {
/* SWING_END */

public static final String about = JCComponent.about;
public static final String version = JCComponent.version;

/** The parent applet. */
transient protected Applet 	applet;

/** Current double-buffer image. */
transient protected Image dblbuffer_image;

/** default "ActionButton" */
/* JDK110_START */
protected JCButton action_button = null;
/* JDK110_END */

/** current "ActionButton" (read-only) */
/* JDK110_START */
protected JCButton current_action_button = null;
/* JDK110_END */

/* SWING_START */
protected Border swing_border = null;
/* SWING_END */

static JCConverter conv = new JCConverter();
int	pref_width = BWTEnum.NOVALUE, pref_height = BWTEnum.NOVALUE;

/* JDK102_START
private String name;
 JDK102_END */
Object 				userdata;
Insets				insets;
transient boolean	in_pref_size = false;
boolean				double_buffer = false;

// Area currently being repainted, relative to component's origin.
transient Rectangle paint_rect;

protected boolean has_focus;

/** This creates an empty container. No parameters are read from an HTML file. */
public JCContainer() {
	this(null, null);
}

/** This creates a container which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCContainer(Applet applet, String name) {
	super();
	this.applet = applet;
	setName(name);

/* JDK110_START */
	addFocusListener(new ContainerFocusListener());
/* JDK110_END */
/* SWING_START */
	setLayout(new FlowLayout());
/* SWING_END */

	if (getClass().getName().equals("jclass.bwt.JCContainer"))
		 getParameters(applet);
}

/**
 * Returns whether container has focus or not.
 */
/* JDK110_START */
public boolean hasFocus() {
	return has_focus;
}

protected class ContainerFocusListener implements FocusListener {
	public void focusGained(FocusEvent e) {
		has_focus = true;
	}

	public void focusLost(FocusEvent e) {
		has_focus = false;
	}
} // ContainerFocusListener
/* JDK110_END */

/** Gets a parameter for this container. */
String getParam(String param) {
	return conv.getParam(applet, this, getName(), param);
}

/** Reads the parameter values from the HTML page using the container's applet.
 * The values will override those previously set.
 * Subclasses may override this method to set their own values - in this case,
 * the method should first call super.getParameters().
 */
protected void getParameters() {
	ContainerConverter.getParams(this);
}

/** Reads the parameter values from the HTML page using the specified applet.
 * The values will override those previously set.
 * @see java.applet.Applet#getParameter
 */
public void getParameters(Applet applet) {
	getParameters(applet, null);
}

/** Reads the parameter values from the file.
 * The values will override those previously set.
 * @param applet if not null and in a browser, its documentBase() is used to construct a complete filename if necessary
 * @param file if an http protocol is not specified (if a ":" is not present), the current working directory is used
 */
public void getParameters(Applet applet, String file) {
	this.applet = applet;

	if (file == null)
		file = getParam("paramFile");
	if (file != null)
		JCUtilConverter.setParamSource(this, JCFile.read(applet, file));

	// Read HTML file if running in a browser, or file if it is not null
	if (file != null || getAppletContext() != null) {
		getParameters();
		if (isDisplayable()) {
			addNotify();
			repaint();
		}
	}
}

/** Sets the converter to be used for converting strings to internal values.
 * @see jclass.util.JCConverter
 */
public static void setConverter(JCConverter c) { conv = c; }

/** Gets the converter used for converting strings to internal values.
 * @see jclass.util.JCConverter
 */
public static JCConverter getConverter() { return conv; }

/** Specifies that java.awt.SystemColor colors are to be used for new components.
 * If false (default), or if running under JDK 1.0, the parent's colors are used.
 * @see java.awt.SystemColor
 */
public static void useSystemColors(boolean v) {
	JCComponent.useSystemColors(v);
}

/** Gets the name of the component, as set in the constructor or by setName.
 * If no name has been set, a 0-length string will be returned.
 * @see #setName
 */
/* JDK102_START
public String getName() { return name != null ? name : ""; }
 JDK102_END */

/**
 * Sets the name of the component.
 */
/* JDK102_START
public void setName(String name) { this.name = name; }
 JDK102_END */

/** Returns vendor information. */
public String getAbout() { return about; }

/** Provided for beans property editors - has no effect. */
public void setAbout(String s) {}

/** Returns the current product version. */
public String getVersion() { return version; }

/** Provided for beans property editors - has no effect. */
public void setVersion(String s) {}

/** Gets the UserData value.
 * @see #setUserData */
public Object getUserData() { return userdata; }

/** Sets an application-defined object that can be attached to the container. */
public void setUserData(Object v) { userdata = v; }

public void requestFocus() {
	// A container should never have focus so be pass this request on to
	// our first traversable child; if we have no children then we do nothing
	Component components[] = getComponents();
	for (int i = 0; i < components.length; i++) {
		Component c = components[i];
		/* JDK110_START */
		// isFocusTraversable() is a JDK 1.1. only method so we don't call it
		// in 102
		if (c.isFocusTraversable() == true)
			/* JDK110_END */
			c.requestFocus();

			// Break was placed in here to counter for PR 14686
			// requestFocus() should be called only for the first child and not
			// all the children.
			break;

	}
}

//
// Routines to handle installation of listeners to make sure that every
// component in the hierarchy underneath the top level Container has the
// proper kind of listener attached.  An non-button need to have a
// keyListener attached (to listen for enter keystrokes) and any container
// needs to have a ComponentListener attach to listen for new children (so
// that the new children can have listeners added to them as well)
//

/**
 * Gets the default ActionButton
 * @see #setActionButton
 */
/* JDK110_START */
public JCButton getActionButton() { return action_button; }
/* JDK110_END */

/**
 * Inner class to contain action and focus listeners for ActionButton
 * implementation
 */
/* JDK110_START */
class ActionButtonListener extends Object
    implements FocusListener, KeyListener, ContainerListener {
/* JDK110_END */

/**
 * invoke the actionListeners of the current actionButton
 */
/* JDK110_START */
private void invokeActionListeners(KeyEvent e) {
	if (current_action_button == null) {
		return;
	}

	JCActionEvent a = new JCActionEvent(current_action_button,
									e.getID(),
									current_action_button.getActionCommand(),
									e.getModifiers());
	for (int i = 0; i < current_action_button.actionListeners.size(); i++) {
		((JCActionListener) current_action_button.actionListeners.elementAt(i)).actionPerformed(a);
	}
}

// Invoked when a key has been typed. This event occurs when a key press is
// followed by a key release.
public void keyTyped(KeyEvent e) { }

// Invoked when a key has been pressed.
public void keyPressed(KeyEvent e) {
	Component c = (Component) e.getSource();
	if (c instanceof JCButton) {
		return;
	}

	if (e.getKeyCode() == e.VK_ENTER) {
		invokeActionListeners(e);
	}
}

// Invoked when a key has been pressed.
public void keyReleased(KeyEvent e) {
}

// Invoked when focus is gained
public void focusGained(FocusEvent e) {
	Component c = (Component) e.getSource();
	if (c instanceof JCButton) {
		// set ActionButton to current button
		if (c != current_action_button) {
			if (current_action_button != null) {
				current_action_button.setIsActionButton(false);
			}
			current_action_button = (JCButton) c;
			current_action_button.setIsActionButton(true);
		}
	}
	else {
		// revert action button to default action button
		if (current_action_button != action_button) {
			if (current_action_button != null) {
				current_action_button.setIsActionButton(false);
			}
			current_action_button = action_button;
			if (current_action_button != null) {
				current_action_button.setIsActionButton(true);
			}
		}
	}
}

// invoked when focus is lost
public void focusLost(FocusEvent e) {
	// Currently do nothing here
}

// invoked when component is added to container
public void componentAdded(ContainerEvent e) {
	installListenerOnComponent(e.getChild());
}

// invoked when component is removed to container
public void componentRemoved(ContainerEvent e) { }

} // end of ActionButtonListener inner class

private ActionButtonListener action_button_listener = null;

private boolean listeners_installed = false;

private void installListenerOnComponent(Component c) {
	if (c instanceof Container) {
		((Container)c).addContainerListener(action_button_listener);
		// installListeners on the containers children
		installListener((Container)c);
	}
	// no else here since JComponent is subclassed from Container
	c.addFocusListener(action_button_listener);
	if (!(c instanceof JCButton)) {
		c.addKeyListener(action_button_listener);
	}
}

private void installListener(Container container) {
	Component components[] = container.getComponents();
	for (int i = 0; i < components.length; i++) {
		Component c = components[i];
		installListenerOnComponent(c);
	}
}

private void installListeners() {
	if (action_button_listener == null) {
		action_button_listener = new ActionButtonListener();
	}

	installListener(this);
}
/* JDK110_END */

/**
 * Sets the default ActionButton which is a JCButton whose actionListener is
 * to be invoked if the ENTER key is hit on any non-button component in the
 * heirarchy of components underneath the container.  If focus goes to
 * another JCButton then that becomes the current ActionButton but if focus
 * goes to another non-button component then the current ActionButton
 * switches back to the specified ActionButton.<p>
 * This is usually used in a dialog where the ActionButton is the "OK"
 * button<p>
 * Since this uses inner classes to isolate all the listener code that this
 * functionality requires; this is a JDK 1.1+ feature only
 * @see JCButton#setIsActionButton
 */
/* JDK110_START */
public void setActionButton(JCButton v) {
	action_button = v;

	if (listeners_installed == false) {
		installListeners();
	}

	// set current actionButton to the specified actionButton
	if (current_action_button != action_button) {
		if (current_action_button != null) {
			// turn off old button
			current_action_button.setIsActionButton(false);
		}

		// activate new current action button
		current_action_button = action_button;
		if (current_action_button != null) {
			current_action_button.setIsActionButton(true);
		}
	}
}
/* JDK110_END */

/**
 * Adds the specified component to this container, if it is not already a child.
 */
public Component add(Component comp) {
	return (comp.getParent() != this) ? super.add(comp) : comp;
}

/**
 * Adds the specified component to this container, if it is not already a child.
 */
/* AWT110_START 
protected void addImpl(Component comp, Object constraints, int index) {
	if (comp.getParent() != this) super.addImpl(comp, constraints, index);

}
 AWT110_END */

/* SWING_START */
protected void addImpl(Component comp, Object constraints, int index) {
	if (comp.getParent() != this) super.addImpl(comp, constraints, index);
	if (swing_border != null && (comp instanceof JComponent)) {
		((JComponent)comp).setBorder(swing_border);
	}
}
/* SWING_END */

/* SWING_START */

public void setToolTipText(String text) {
    super.setToolTipText(text);
    Component[] child = getComponents();
    for (int i=0;i < child.length;i++) {
        if(child[i] instanceof JComponent) {
            ((JComponent)child[i]).setToolTipText(text);
        }
    }
}

/* SWING_END */

/** Enables all the container's children. */
public void enable() {
	if (isEnabled()) return;
	super.enable();
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		child[i].enable();
}

/** Disables all the container's children. */
public void disable() {
	if (!isEnabled()) return;
	super.disable();
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++)
		child[i].disable();
}

/**
 * Overridden to work around a JavaStation bug related to
 * isShowing() always returning false which prevented
 * components from being redrawn.
 */
public boolean isShowing() {
	return JCEnvironment.isJavaOS() ? true : super.isShowing();
}

/**
 * Overridden so the container will repaint immediately when setting the
 * background color.
 */
public void setBackground(Color background) {
	super.setBackground(background);
	repaint();
}

/**
 * Overridden so the container will repaint immediately when setting the
 * foreground color.
 */
public void setForeground(Color foreground) {
	super.setForeground(foreground);
	repaint();
}

/** Overrides Component.repaint(), which sometimes doesn't call paint(). */
/* AWT_START 
public void repaint() {
	if (getPeer() == null || !isShowing())
		return;
	repaint(0, 0, size().width, size().height);
	Component[] child = getComponents();
	for (int i=0; i < child.length; i++) {
		if (child[i] instanceof JCContainer || child[i] instanceof Canvas)
			child[i].repaint();
	}
}
 AWT_END */
/**
 * Repaints part of the component by calling paint directly.
 */
public void repaint(Rectangle r) {
	repaint(r.x, r.y, r.width, r.height);
}

/**
 * Repaints part of the component by calling paint directly.
 */
public void repaint(int x, int y, int width, int height) {
	Graphics gc = null;

	synchronized (this) {
		if (!isDisplayable() || !isShowing() || width <= 0 || height <= 0) return;
		int w = size().width, h = size().height;

		// Check that the area intersects the container
		if (w <= x || h <= y || x + width < 0 || y + height < 0) return;

		if (x+width > w) width = w - x;
		if (y+height > h) height = h - y;

		gc = getGraphics();
		gc.clipRect(x, y, width, height);
	}
	paint(gc);
	gc.dispose();
}

/** Forces the parent to relayout this container. */
public void updateParent() {
	if (getParent() != null) {
		invalidate();
		getParent().invalidate();	// Netscape bug
		getParent().validate();
	}
}

public void addNotify() {

    if (!isDisplayable()) super.addNotify();
    if (applet == null)
	    applet = BWTUtil.getApplet(this);
/* JDK110_START */
    enableEvents(AWTEvent.MOUSE_EVENT_MASK);
/* JDK110_END */
    if (!isEnabled()) {
	    Component[] child = getComponents();
	    for (int i=0; i < child.length; i++)
		    child[i].disable();
    }
}

/** Gets the container's applet's context. The applet context
 * lets an applet control the applet's environment which is
 * usually the browser or the applet viewer.
 * @return null if the container is not in an applet or the applet is not in
 * a browser
 * @see java.applet.Applet#getAppletContext
 */
public AppletContext getAppletContext() {
	return BWTUtil.getAppletContext(applet);
}

/**
 * Overridden only in JDK 1.0.2 returns preferredSize()
 * Not overriden in JDK 1.1 since the behaviour is correct
 */
/* JDK102_START
public Dimension minimumSize() {
	return preferredSize();
}
 JDK102_END */


/** Sets the container's margins.
 * This value may be set from an HTML file using a PARAM tag with a "Insets"
 * name and an Insets value.
 * @see java.awt.Container#insets
 * @see java.awt.Insets
 * @see jclass.util.JCConverter#toInsets
 */
public void setInsets(Insets insets) {
	this.insets = insets;
}

/** Returns the previously set insets,
 * or the superclass' insets if none have been set.
 * @see #setInsets
 */
/* AWT_START 
public Insets insets() {
 AWT_END */
/* SWING_START */
public Insets getInsets() {
/* SWING_END */

	return insets != null ? insets :
/* AWT_START 
		super.insets();
 AWT_END */
/* SWING_START */
		super.getInsets();
/* SWING_END */
}

/** Gets the subclass' preferred width (default: 100). The subclass should
 * not include the inset sizes in its calculation;
 * these will be added by JCContainer.
 */
protected int preferredWidth() { return -1; }

/** Gets the subclass' preferred height (default: 100). The subclass should
 * not include the inset sizes in its calculation;
 * these will be added by JCContainer.
 */
protected int preferredHeight() { return -1; }

/** Sets the container's preferred size. If either dimension is set to
 * BWTEnum.NOVALUE, it is calculated by the subclass.
* This value may be set from an HTML file using a PARAM tag with a "preferredWidth"
 * name and an int value, and/or a "preferredHeight" name and an int value.
 * @see java.awt.Component#preferredSize
 * @see jclass.util.JCConverter#toInt
 */
public void setPreferredSize(int w, int h) {
	pref_width = w; pref_height = h;
}

/**
 * Returns the preferred size of this container.
 * If the app has set the preferred size, it is returned. Otherwise
 * the subclass' preferredWidth and/or preferredHeight method is called.
 * @see #setPreferredSize
 * @see #preferredWidth
 * @see #preferredHeight
 */
/* AWT_START 
public Dimension preferredSize() {
 AWT_END */
/* SWING_START */
public Dimension getPreferredSize() {
/* SWING_END */

	if (in_pref_size) {
/* AWT_START 
		return super.preferredSize();
 AWT_END */
/* SWING_START */
		return super.getPreferredSize();
/* SWING_END */
	}
	in_pref_size = true;
	int w = pref_width != BWTEnum.NOVALUE ? pref_width : preferredWidth();
	w = (w < 0) ?
		Math.max(
/* AWT_START 
				 super.preferredSize().width,
 AWT_END */
/* SWING_START */
				 super.getPreferredSize().width,
/* SWING_END */
				 20) :
		w + JCComponent.getInsets(this).left +
		JCComponent.getInsets(this).right;

	int h = pref_height != BWTEnum.NOVALUE ? pref_height : preferredHeight();
	h = (h < 0) ?
		Math.max(
/* AWT_START 
				 super.preferredSize().height,
 AWT_END */
/* SWING_START */
				 super.getPreferredSize().height,
/* SWING_END */
				 20) :
		h + JCComponent.getInsets(this).top +
		JCComponent.getInsets(this).bottom;
	in_pref_size = false;
	return new Dimension(w, h);
}

/**
 * Reshapes the Container to the specified bounding box.
 */
/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */
	synchronized (this) {
		if (x == location().x && y == location().y
			&& width == size().width && height == size().height)
			return;
		if (width < 0 || height < 0) return;
	}
/* AWT_START 
	super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, width, height);
/* SWING_END */
	invalidate();
	validate();
}

/** Sets all the children of instance JComponent with the specified border */
/* SWING_START */
protected void setChildrenBorder(Border border) {
	for (int i = 0; i < getComponentCount(); i++) {
		Component c = getComponent(i);
		if (c instanceof JComponent) {
			((JComponent)c).setBorder(border);
		}
	}
}
/* SWING_END */

/** Sets the cursor in the parent frame. */
public void setCursor(int cursor) {
	BWTUtil.setCursor(this, cursor);
}

/** Gets the child at the specified position. */
public Component getComponent(int pos) {
	Component child[] = getComponents();
	return pos < child.length ? child[pos] : null;
}

/** Returns the position of the child, or BWTEnum.NOTFOUND. */
public int getComponent(Component comp) {
	Component child[] = getComponents();
	for (int i=0; i < child.length; i++)
		if (child[i] == comp) return i;
	return BWTEnum.NOTFOUND;
}

/** Creates a double-buffer image
 * @return the Graphics instance for the image
 */
protected synchronized Graphics getDoubleBufferGraphics() {
	dblbuffer_image = BWTUtil.createImage(this, size().width, size().height);
	return dblbuffer_image != null ? dblbuffer_image.getGraphics() : null;
}

/** Called by paint, to allow subclasses to draw before their children are painted. */
public void paintInterior(Graphics gc) {}

/** Fills the component with the background color (called by paint). */
protected void fillBackground(Graphics gc) {
	gc.fillRect(0, 0, size().width, size().height);
}

/** Returns the area currently being painted, relative to the component's origin.
 * @return null if no area is being drawn
 * @see #paint
 */
public Rectangle getPaintRect() { return paint_rect; }

/** Paints all children. */
public void paint(Graphics gc) {
	if (gc == null || getBackground() == null || getParent() == null) return;
	Graphics draw_gc = gc;
	Rectangle rect = gc.getClipRect();

	if (rect == null)
		rect = new Rectangle(size());
	if (rect.width == 0 || rect.height == 0) return;
	paint_rect = rect;

	if (double_buffer) {
		Graphics dbl_gc = getDoubleBufferGraphics();
		if (dbl_gc == null)
			double_buffer = false;
		else {
			gc = dbl_gc;
			if (rect != null)
				gc.clipRect(rect.x, rect.y, rect.width, rect.height);
		}
	}

	gc.setColor(getBackground());
	fillBackground(gc);
	if (!gc.getColor() .equals(getBackground()))
		gc.setColor(getBackground());
	paintInterior(gc);

	if (double_buffer) {
		gc.dispose();
		draw_gc.drawImage(dblbuffer_image, 0, 0, null);
	}
	if (draw_gc != null) super.paint(draw_gc);
}

/**
 * Disables double-buffering, and calls Container.printAll
 */
public void printAll(Graphics gc) {
	boolean v = double_buffer;
	double_buffer = false;
	super.printAll(gc);
	double_buffer = v;
}

/* JDK110_START */

protected void processMouseEvent(MouseEvent mouse_ev) {
	Event ev = new Event(this, mouse_ev.getWhen(), mouse_ev.getID(),
				 mouse_ev.getX(), mouse_ev.getY(), 0, mouse_ev.getModifiers());
	ev.clickCount = mouse_ev.getClickCount();

	switch(ev.id) {
	case MouseEvent.MOUSE_PRESSED:
		mouseDown(ev, ev.x, ev.y);
		break;
	case MouseEvent.MOUSE_RELEASED:
		mouseUp(ev, ev.x, ev.y);
		break;
	case MouseEvent.MOUSE_ENTERED:
		mouseEnter(ev, ev.x, ev.y);
		break;
	case MouseEvent.MOUSE_EXITED:
		mouseExit(ev, ev.x, ev.y);
		break;
	}
	super.processMouseEvent(mouse_ev);
}

protected void processMouseMotionEvent(MouseEvent mouse_ev) {
	Event ev = new Event(this, mouse_ev.getWhen(), mouse_ev.getID(),
				  mouse_ev.getX(), mouse_ev.getY(), 0, mouse_ev.getModifiers());
	switch(ev.id) {
	case MouseEvent.MOUSE_MOVED:
		mouseMove(ev, ev.x, ev.y);
		break;
	case MouseEvent.MOUSE_DRAGGED:
		mouseDrag(ev, ev.x, ev.y);
		break;
	}
	super.processMouseMotionEvent(mouse_ev);
}
/* JDK110_END */

/**
 * This method shoud be called when the component is no longer needed.
 * It ensures that the component can be garbage collected properly.
 */
public void dispose() {
	Component[] children = getComponents();
	for (int i = 0; i < children.length; i++) {
		if (children[i] instanceof JCTextComponent) {
			((JCTextComponent)children[i]).dispose();
		}
		else if (children[i] instanceof JCArrowButton) {
			((JCArrowButton)children[i]).dispose();
		}
		else if (children[i] instanceof BaseComponent) {
			((BaseComponent)children[i]).dispose();
		}
		else if (children[i] instanceof JCContainer) {
			((JCContainer)children[i]).dispose();
		}

	}
}

}
