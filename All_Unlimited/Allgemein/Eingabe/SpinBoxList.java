package All_Unlimited.Allgemein.Eingabe;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Arrays;

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
public class SpinBoxList extends JSpinner
{
  //String[] s1=DateWOD.DFS.getMonths();

  /**
	 * 
	 */
	private static final long serialVersionUID = -4958348301303764491L;
	private int iMax=0;

public SpinBoxList(java.util.Vector Vec)
  {
    super();
    setModel(new SpinnerListModel(Vec.toArray()));
    iMax=Vec.size();
    immer();
  }

  public SpinBoxList(Object[] Obj,int iAnz,java.awt.Color Col)
  {
    super();
    setModel(new SpinnerListModel(Arrays.asList(Obj).subList(0, iAnz)));
    iMax=iAnz;
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setBackground(Col);
    immer();
  }
  
  private void immer()
  {
    setPreferredSize(new java.awt.Dimension(120,20));
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setEditable(false);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.CENTER);
    addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e) {
	//System.out.println("SpinBoxJahr:"+e.getWheelRotation());
	int i=getSelectedKennung()-e.getWheelRotation();
	if (i<=iMax && i>0)
	  setSelectedKennung(i);//i>iMax?1:i<1?iMax:i);
      }
    });
  }

  public int getSelectedKennung()
  {
    return ((SpinnerListModel)getModel()).getList().indexOf(getValue())+1;
  }

  public void setSelectedKennung(int i)
  {
    setValue(((SpinnerListModel)getModel()).getList().get(i-1));
  }

}
