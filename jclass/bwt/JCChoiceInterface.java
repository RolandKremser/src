
package jclass.bwt;

public interface JCChoiceInterface {

/**
 * Add a listener to catch and selections made by the object that implements
 * a JCChoiceInterface
 */
	//void addJCChoiceListener(JCChoiceListener listener);

void addItemListener(JCItemListener listener);

/**
 * Set the text of the list of choices
 */
void setItems(String items[]);

/**
 * adds an item to the list
 */
void add(String item);

/**
 * Get the index into the list of the current selected item
 */
int getSelectedIndex();

}
 
