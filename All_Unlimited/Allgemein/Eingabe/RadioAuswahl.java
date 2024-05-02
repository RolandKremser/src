package All_Unlimited.Allgemein.Eingabe;

import javax.swing.*;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Tabellenspeicher;
import java.awt.FlowLayout;
import All_Unlimited.Allgemein.Static;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
public class RadioAuswahl extends JPanel
{
  private static final long serialVersionUID = -4958348301303764494L;
  private Tabellenspeicher Tab;
//  private int iAic=-1;
  private int iLast=-1;
  private boolean bEdit=true;

  public RadioAuswahl(Global g,int iEig)
  {
    setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
    if (iEig>0)
    	Tab=g.readTabAW(iEig,false);
    else if (iEig<0)
    	Tab=readCode(g,-iEig);
    ButtonGroup RadGroup=new ButtonGroup();
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
    {
      JRadioButton Rbn=new JRadioButton(Tab.getS("Bez"));
      Rbn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          CheckColor();
        }
      });
      Tab.addInhalt("Komp",Rbn);
      Rbn.setFont(Global.fontBezeichnung);
      RadGroup.add(Rbn);
      add(Rbn);
    }
  }
  
  private Tabellenspeicher readCode(Global g,int iBG)
  {
	  
	  int iPos=g.TabCodes.getPos("Gruppe",iBG);
	  Tabellenspeicher Tab = new Tabellenspeicher(g, new String[] {"Aic", "Bez", "Nr","Komp"});
	  int i=0;
	  while (iPos>=0 && g.TabCodes.getI(iPos,"Gruppe")==iBG)
	  {
          i++;
		  Tab.addInhalt("Aic", g.TabCodes.getI(iPos, "aic"));
          Tab.addInhalt("Bez", g.TabCodes.getS(iPos, "Bezeichnung").substring(0, 3));
          Tab.addInhalt("Nr", i);
          iPos++;
	  }
	  return Tab;
  }

  public void setNr(int iNr)
  {
    iLast=iNr;
    if (Tab.posInhalt("Nr",iNr))
      ((JRadioButton)Tab.getInhalt("Komp")).setSelected(true);
    //setValue(((SpinnerListModel)getModel()).getList().get(iNr));
    CheckColor();
  }

  public void setAic(int iAic)
  {
      setNr(getNr(iAic));
  }

  public int getAic()
  {
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      if (((JRadioButton)Tab.getInhalt("Komp")).isSelected())
        return Tab.getI("Aic");
    return -1;
  }

  public int getNr()
  {
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      if (((JRadioButton)Tab.getInhalt("Komp")).isSelected())
        return Tab.getI("Nr");
    return -1;
  }

  public String getValue()
  {
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      if (((JRadioButton)Tab.getInhalt("Komp")).isSelected())
        return Tab.getS("Bez");
    return "< null >";
  }

  public void Reset()
  {
    setNr(getNr());
  }

  public void Reset2()
  {
    setNr(iLast);
  }

  public int getAic(int iNr)
  {
    return /*iNr>0 && */Tab.posInhalt("Nr",iNr) ? Tab.getI("Aic"):0;
  }

  public String getBez(int iNr)
  {
      return Tab.posInhalt("Nr",iNr) ? Tab.getS("Bez"):"";
  }

  public int getNr(int iAic)
  {
    return iAic>0 && Tab.posInhalt("Aic",iAic) ? Tab.getI("Nr"):0;
  }

  public boolean isNull()
  {
    return getNr()==0;
  }

  public boolean Modified()
  {
    return iLast!=getNr();
  }

  public void setEnabled(boolean b)
  {
    bEdit=b;
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      ((JRadioButton)Tab.getInhalt("Komp")).setEnabled(b);
  }

  private void setBack(Color Col)
  {
    setBackground(Col);
    for(Tab.moveFirst();!Tab.out();Tab.moveNext())
      ((JRadioButton)Tab.getInhalt("Komp")).setBackground(Col);
  }

  private void CheckColor()
  {
    setBack(Modified()? Static.ColChange:bEdit ? Color.WHITE:getParent().getBackground());
  }



}
