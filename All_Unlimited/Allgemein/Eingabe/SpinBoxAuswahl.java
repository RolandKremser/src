package All_Unlimited.Allgemein.Eingabe;

import javax.swing.*;
import All_Unlimited.Allgemein.*;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import All_Unlimited.Allgemein.Anzeige.Combo;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.12
 */
public class SpinBoxAuswahl extends JSpinner
{
  private static final long serialVersionUID = -4958348301303764492L;
  private int iMax=0;
  private Tabellenspeicher Tab;
  private int iLast=0;
  private boolean bEdit=true;

  public SpinBoxAuswahl(Global g,int iEig)
  {
    this(g,iEig,false);
  }

  public SpinBoxAuswahl(Global g,int iEig,boolean bDef)
  {
    super();
    Tab=g.readTabAW(iEig,bDef);
    setModel(new SpinnerListModel(Tab.getVecSpalte("Bez").toArray()));
    setFont(Transact.fontStandard);
    iMax=Tab.size();
    setNr(0);
    setPreferredSize(new java.awt.Dimension(120,20));
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setEditable(false);
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.CENTER);
    ((JSpinner.DefaultEditor)getEditor()).getSpinner().addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e)
      {
        CheckColor();
      }
    });

    addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e) {
        int i=getNr()-e.getWheelRotation();
        if (i<iMax && i>=0)
          setValue(((SpinnerListModel)getModel()).getList().get(i));
        CheckColor();
      }
    });
  }

  /*private void readTab(Global g,int iEig)
  {
    Tab=new Tabellenspeicher(g,new String[] {"Aic","Bez","Nr"});
    for(int iPos=g.TabAuswahl.getPos("aic_eigenschaft",iEig);iPos<g.TabAuswahl.size() && g.TabAuswahl.getI(iPos,"aic_eigenschaft")==iEig;iPos++)
    {
      Tab.addInhalt("Aic",g.TabAuswahl.getI(iPos,"aic_auswahl"));
      Tab.addInhalt("Bez",g.TabAuswahl.getS(iPos,"Bezeichnung"));
      Tab.addInhalt("Nr",g.TabAuswahl.getI(iPos,"Nr"));
    }
  }*/

  public int getAic(int iNr)
  {
    return iNr>0 && Tab.posInhalt("Nr",iNr) ? Tab.getI("Aic"):0;
  }

  public int getNr(int iAic)
  {
    return iAic>0 && Tab.posInhalt("Aic",iAic) ? Tab.getI("Nr"):0;
  }

  public void setNr(int iNr)
  {
    iLast=iNr;
    setValue(((SpinnerListModel)getModel()).getList().get(iNr));
    CheckColor();
  }

  /*public void setValue(String s)
  {
    super.setValue(s);
    iLast=getNr();
    CheckColor();
  }*/

  public void setAic(int iAic)
  {
    setNr(getNr(iAic));
  }

  public void setCbo(Combo Cbo)
  {
    setNr(Cbo==null || !Cbo.use ? 0:getNr(Cbo.getAic()));
  }

  public Combo getCbo()
  {
    return new Combo(""+getValue(),getAic(),getNr()>0);
  }

  private int getNr()
  {
    return ((SpinnerListModel)getModel()).getList().indexOf(getValue());
  }

  public void Reset()
  {
    setNr(getNr());
  }

  public void Reset2()
  {
    setNr(iLast);
  }

  public int getAic()
  {
    String sBez=""+getValue();
    return Tab.posInhalt("Bez",sBez) ? Tab.getI("Aic"):0;
  }

  public boolean isNull()
  {
    return getNr()==0;
  }

  public boolean Modified()
  {
    return iLast!=getNr();
  }

  public void setEditable(boolean b)
  {
    bEdit=b;
    setEnabled(b);
  }

  private void CheckColor()
  {
    ((JSpinner.DefaultEditor)getEditor()).getTextField().setBackground(Static.ColEdt(Modified(),bEdit));
  }


}
