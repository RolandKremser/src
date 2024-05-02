package All_Unlimited.Allgemein.Eingabe;

import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;//TitledBorder;
//import sun.swing.SwingUtilities2;

//import All_Unlimited.Allgemein.Check;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class EF_Eingabe extends JPanel
{

  /**
	 *
	 */
private static final long serialVersionUID = 3680394396256195086L;
private Component Comp;
private Component CompShow;
private JLabel LblTitel;
//private static int i=TitledBorder.BOTTOM;//5;

  /*public EF_Eingabe(Global g,Component rComp,String sBezeichnung,boolean bEdit,String sToolTip)
  {
    setLayout(new GridLayout(2,1,1,1));
    //JLabel Lbl=new JLabel(sBezeichnung);
    JButton Btn=new JButton();
    //Btn.setMargin(new Insets(1,1,1,1));
    //JPanel Pnl=new JPanel();
    //Pnl.setSize(1,1);
    Btn.setFocusable(false);
    //Btn.setMinimumSize(new Dimension(0,0));
    TitledBorder Lbl=new TitledBorder(sBezeichnung);
    Lbl.setTitleFont(g.fontEF_Bez);
    Lbl.setTitlePosition(TitledBorder.BELOW_BOTTOM);
    //Lbl.setTitleJustification(i);
    i++;
    Btn.setBorder(Lbl);
    if (sToolTip !=null)
      Btn.setToolTipText(sToolTip);
    //Lbl.setAlignmentY(SwingConstants.TOP);
    Comp=rComp;
    if (bEdit)
      CompShow=Comp;
    else
    {
      CompShow = new JLabel("  " + Check.getS(Comp));
      //((JLabel)CompShow).setAlignmentY(CompShow.BOTTOM_ALIGNMENT);
    }
    CompShow.setFont(g.fontStandard);
    add(CompShow);
    add(Btn);
  }*/

  public EF_Eingabe(Global g,Component rComp,String sText,String sBezeichnung,boolean bEdit,boolean bZwingend,String sToolTip,String sDatentyp)
  {
	  //g.fixtestError("EF_Eingabe:"+sBezeichnung+"/"+sDatentyp+"/"+bEdit+":"+rComp);
    setLayout(new BorderLayout());
    setBackground(Static.ColEF);
    if (rComp==null || sDatentyp.equals("Filler"))
      g.defInfo2("Filler "+sBezeichnung+" wird leer gelassen");
    else if (rComp instanceof AUCheckBox)
    {
      rComp.setBackground(Static.ColEF);
      ((AUCheckBox)rComp).setFont(Global.fontEF_Bez);
      ((AUCheckBox)rComp).setForeground(Global.ColEF_Bez);
      //g.setTooltip(sToolTip,(AUCheckBox)rComp);
      g.setEigTooltip(sBezeichnung,sToolTip,(AUCheckBox)rComp,sDatentyp);
      rComp.setEnabled(bEdit);
      //if(sToolTip != null && !sToolTip.equals(""))
      //  ((AUCheckBox)rComp).setToolTipText(sToolTip);
    }
    else if (sDatentyp.equals("Radio") || sDatentyp.equals("Buttons"))
    {
      TitledBorder Lbl=new TitledBorder(sBezeichnung);
      Lbl.setTitleFont(Global.fontTitel);
      Lbl.setTitlePosition(TitledBorder.TOP);
      setBorder(Lbl);
      g.setTooltip(sToolTip,this);
    }
    else
    {
      /*TitledBorder Lbl = new TitledBorder(sBezeichnung);
      Lbl.setBorder(new EmptyBorder(1,1,1,1));
      Lbl.setTitleFont(bZwingend ? g.fontEF_Bez2:g.fontEF_Bez);
      Lbl.setTitleColor(bZwingend ? g.ColEF_Bez2:g.ColEF_Bez);
      Lbl.setTitlePosition(i); //TitledBorder.BELOW_BOTTOM);
      setBorder(Lbl);
      */
      LblTitel= new JLabel(sBezeichnung);
      LblTitel.setFont(bZwingend ? Global.fontEF_Bez2:Global.fontEF_Bez);
      LblTitel.setForeground(bZwingend ? Global.ColEF_Bez2:Global.ColEF_Bez);
      //i++;
      //Lbl.getB;
      //rComp.setFont(g.fontStandard);
      //g.setTooltip(sToolTip,this);
      g.setEigTooltip(sBezeichnung,sToolTip,this,sDatentyp);
      /*java.awt.Font font = bZwingend ? g.fontEF_Bez2:g.fontEF_Bez;
      java.awt.FontMetrics fm = rComp.getFontMetrics(font);
      javax.swing.JComponent jc = (rComp instanceof javax.swing.JComponent) ? (javax.swing.JComponent)rComp : null;
      setMinimumSize(new java.awt.Dimension(SwingUtilities2.stringWidth(jc, fm, Lbl.getTitle()),20)); */
      add(sDatentyp.equals("Bild") || sDatentyp.equals("Memo") || sDatentyp.equals("Text") ? "North":"South",LblTitel);
      //setPreferredSize(Lbl.getMinimumSize(Comp));
    }
    //Lbl.setAlignmentY(SwingConstants.TOP);
    Comp=rComp;
    if (bEdit || rComp instanceof AUCheckBox)
      CompShow=Comp;
    else
    {
      //if (Comp instanceof Datum)
      //  g.progInfo("------------------->>>>>>>>>>>> Datum bei "+sBezeichnung);
      CompShow = new JLabel(sText); //" " + Check.getS(Comp));
      //((JLabel)CompShow).setAlignmentY(CompShow.BOTTOM_ALIGNMENT);
    }
    add(sDatentyp.equals("Bild") || sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Radio") || sDatentyp.equals("Buttons") ? "Center":"West",CompShow);
    /*JPanel Pnl=new JPanel();
    Pnl.setForeground(g.ColEF_Bez);
    Pnl.setPreferredSize(new java.awt.Dimension(50,20));
    add("Center",Pnl);*/
    //add(Btn);
  }

  public Component getComp()
  {
    return Comp;
  }

  public String getTitel()
  {
    return LblTitel.getText();
  }

  public void setTitel(String sBezeichnung)
  {
    LblTitel.setText(sBezeichnung);
  }

}
