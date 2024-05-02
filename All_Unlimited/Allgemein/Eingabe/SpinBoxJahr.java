package All_Unlimited.Allgemein.Eingabe;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class SpinBoxJahr extends JSpinner
{
  /**
	 *
	 */
private static final long serialVersionUID = -2608147119582896188L;

private int iAb=0;

public SpinBoxJahr(java.awt.Color Col,int riAb)
  {
    super();
    iAb=riAb;
    Vector<Object> Vec=new Vector<Object>();
    for (int i=iAb;i<2101;i++)
      Vec.addElement(""+i);
    setModel(new SpinnerListModel(Vec.toArray()));
    /*SpinnerDateModel    dateModel    = new SpinnerDateModel();
    setModel(dateModel);
    //setAlignmentY(CENTER_ALIGNMENT);
    setEditor(new JSpinner.DateEditor(this, " yyyy "));*/
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setEditable(false);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.CENTER);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setBackground(Col);
    addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e) {
	//System.out.println("SpinBoxJahr:"+e.getWheelRotation());
	setIntValue(getIntValue()-e.getWheelRotation());
      }
    });
    //((JSpinner.DefaultEditor)getEditor()).setAlignmentY(CENTER_ALIGNMENT);
    //((JSpinner.DateEditor)getEditor()).setEditable(false);
  }

  public int getIntValue()
  {
    //System.out.println(All_Unlimited.Allgemein.Static.print(getValue()));
    //return ((Date)getValue()).getYear()+1900;
    return Integer.parseInt((String)getValue());
  }

  public void setIntValue(int i)
  {
    //setValue(new Date(i-1900,0,1));
    if (i<iAb)
      i=iAb;
    setValue(""+i);
    //return 0;
  }

  /*public Object getNextValue()
  {
    int i=getIntValue();
    if (i>=iMax)
      return null;
    else
      return new Integer(i+iInc);
  }

  public Object getPreviousValue()
  {
    int i=getIntValue();
    if (i<=iMin)
      return null;
    else
      return new Integer(i-iInc);
  }*/

}
