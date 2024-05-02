package All_Unlimited.Allgemein.Eingabe;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import All_Unlimited.Allgemein.Static;

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
public class SpinBox extends JSpinner
{
  /*private int iMin=0;
  private int iMax=1000000;
  private int iInc=1;*/

  /**
	 * 
	 */
	private static final long serialVersionUID = -2431238044238407325L;
	private int iMin=0;
	private int iMax=0;
	private int iOld=0;

public SpinBox()
  {
    this(0,0,1000000,1,java.awt.Color.WHITE);
    //super(new SpinnerNumberModel(50, 0, 100, 5));
    //setEditor(new Zahl(0));
  }

  public SpinBox(int iV,int riMin,int riMax,int iStep,java.awt.Color Col)
  {
    super(new SpinnerNumberModel(iV, riMin, riMax, iStep));
    iMin=riMin;
    iMax=riMax;
    setPreferredSize(new java.awt.Dimension(50,20));
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setBackground(Col);
    addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e) {
		//System.out.println("SpinBoxJahr:"+e.getWheelRotation());
		int i=getIntValue()-e.getWheelRotation()*iStep;
		if (i<=iMax && i>=iMin)
		{
			setValue(i>iMax?iMax:i<iMin?iMin:i);			
		}
      }
    });
    addChangeListener(new ChangeListener() {
    	public void stateChanged(ChangeEvent ae)
    	{
    		CheckColor();
    	}
	});
    /*JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor)getEditor()  ;
    JFormattedTextField ftf = defEditor.getTextField() ;
    InternationalFormatter intFormatter = (InternationalFormatter)ftf.getFormatter() ;
    DecimalFormat decimalFormat = (DecimalFormat)intFormatter.getFormat() ;
    decimalFormat.applyPattern("###0");*/
    //setEditor(new Zahl(0));
  }

  /*public SpinBox(boolean bJahr)
  {
    super(new SpinnerDateModel());
    DateEditor jahr = new JSpinner.DateEditor(this, "yyyy");
  }*/


  public void setIntValue(int i)
  {
    setValue(i);
    //((Zahl)getEditor()).setValue(i);
    iOld=i;
    CheckColor();
  }

  public int getIntValue()
  {
    return ((Integer)getValue()).intValue();
    //return ((Zahl)getEditor()).intValue();
  }

  public void setMinimum(int i)
  {
    ((SpinnerNumberModel)getModel()).setMinimum(new Integer(i));
    //iMin=i;
  }

  public void setMaximum(int i)
  {
    ((SpinnerNumberModel)getModel()).setMaximum(new Integer(i));
    //iMax=i;
  }
/*
  public void setIncrement(int i)
  {
    ((SpinnerNumberModel)getModel()).setStepSize(new Integer(i));
    //iInc=i;
  }*/

  public void setEditable(boolean b)
  {
    //System.out.println("SpinBox="+All_Unlimited.Allgemein.Static.className(getEditor()));
    //((Zahl)getEditor()).setEditable(b);
    //getEditor().setEnabled(b);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setEditable(b);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setHorizontalAlignment(b ? JFormattedTextField.RIGHT:JFormattedTextField.CENTER);
  }
  
  public boolean Modified()
  {
	  //System.err.println("Spinbox.Modified:"+(iOld!=getIntValue())+":"+getIntValue()+"/"+iOld);
	  return iOld!=getIntValue();
  }

  public int getOld()
  {
    return iOld;
  }
  
  private void CheckColor()
  {
	  ((JSpinner.DefaultEditor)getEditor()).getTextField().setBackground(Static.ColEdt(Modified(),true/*!Static.bND || isEditable()*/));
  }
  
  public void Reset2()
  {
    setValue(iOld);
    CheckColor();
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
