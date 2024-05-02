package All_Unlimited.Grunddefinition;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.Datum;

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
public class CleanDate extends Formular
{
  private Global g;
  private JCOutliner Out = new JCOutliner(new JCOutlinerFolderNode(""));
  private Datum DatS1;
  private Datum DatS2;
  private Datum DatB1;
  private Datum DatB2;

  public CleanDate(Global rg)
  {
    super("CleanDate", null, rg);
    g = rg;

    getFrei("Outliner").add("Center",Out);
    Out.setColumnLabelSortMethod(Sort.sortMethod);
    Out.setRootVisible(false);
    Out.setFont(Global.fontBezeichnung);
    Out.setColumnButtons(new jclass.util.JCVector(new String[] {"Tabelle","Anzahl"}));
    Out.setNumColumns(2);

    fillEingabe();
    Events();
    Refresh();
    show();
  }

  private void fillEingabe()
  {
    JPanel PnlEingabe = getFrei("Eingabe");
    PnlEingabe.setLayout(new BorderLayout(2,2));
    JPanel Pnl = new JPanel(new GridLayout(0,1,2,2));
    g.addLabel2(Pnl,"Stammdatum-min");
    g.addLabel2(Pnl,"Stammdatum-max");
    g.addLabel2(Pnl,"Bewdatum-min");
    g.addLabel2(Pnl,"Bewdatum-max");
    PnlEingabe.add("West",Pnl);
    Pnl = new JPanel(new GridLayout(0,1,2,2));
    DatS1=new Datum(g,"yyyy-MM-dd");
    DatS2=new Datum(g,"yyyy-MM-dd");
    DatB1=new Datum(g,"yyyy-MM-dd");
    DatB2=new Datum(g,"yyyy-MM-dd");
    DateWOD DW=new DateWOD(1920,1,1);
    DatS1.setDate(DW.toDate());
    DW=new DateWOD(1960,1,1);
    DatB1.setDate(DW.toDate());
    DW=new DateWOD();
    DW.setDay1();
    DW.nextYear();
    DatB2.setDate(DW.toDate());
    DW.nextYear();
    DatS2.setDate(DW.toDate());
    Pnl.add(DatS1);
    Pnl.add(DatS2);
    Pnl.add(DatB1);
    Pnl.add(DatB2);
    PnlEingabe.add("Center",Pnl);
  }

  private void Events()
  {
    ActionListener AL = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String s = e.getActionCommand();
        g.progInfo("Action="+s);
        if (s.equals("Refresh"))
          Refresh();
        else if (s.equals("show"))
          Show();
        else if (s.equals("Loeschen"))
          Delete();
      }
    };
    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    g.BtnAdd(getButton("show"),"show",AL);
    g.BtnAdd(getButton("Loeschen"),"Loeschen",AL);

    JButton BtnBeenden = getButton("Beenden");
    if (BtnBeenden != null)
    {
      Action cancelKeyAction = new AbstractAction()
      {
        /**
		 *
		 */
		private static final long serialVersionUID = 3733556555408322981L;

		public void actionPerformed(ActionEvent e) {
          hide();
        }
      };
      BtnBeenden.addActionListener(cancelKeyAction);
      Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);
    }
  }

  private void add(JCOutlinerFolderNode NodRoot,int iNr,String sTab,String sWhere)
  {
    int i=SQL.getInteger(g,"select count(*) from "+sTab+sWhere);
    if (i>0)
    {
      JCOutlinerNode Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {sTab,new Integer(i)}),NodRoot);
      Nod.setUserData(new Integer(iNr));
    }
  }

  private void Refresh()
  {
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    add(NodeRoot,1,"bew_pool"," where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    add(NodeRoot,2,"stamm_protokoll"," where pro_aic_protokoll is null and (eintritt<"+g.DateTimeToString(DatS1.getDate())+" or eintritt>"+g.DateTimeToString(DatS2.getDate())+")");
    add(NodeRoot,3,"stamm_protokoll"," where pro_aic_protokoll is null and (austritt<"+g.DateTimeToString(DatS1.getDate())+" or austritt>"+g.DateTimeToString(DatS2.getDate())+")");
    add(NodeRoot,4,"stammpool"," where pro2_aic_protokoll is null and (gultig_von<"+g.DateTimeToString(DatS1.getDate())+" or gultig_von>"+g.DateTimeToString(DatS2.getDate())+")");
    add(NodeRoot,5,"zwischenspeicher"," where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    if (g.Def())
      add(NodeRoot,6,"zwischenspeicher"," where (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    Out.folderChanged(null);
  }

  private void Show()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int i=Nod.getLevel()>0 ? Sort.geti(Nod.getUserData()):0;
    String s=i==1 ? "select aic_bew_pool,gueltig"+g.AU_Bezeichnung3("bewegungstyp","p")+
        ",(select bezeichnung from stammview2 where aic_stamm=anr and aic_rolle is null) anr1"+
        ",(select bezeichnung from stammview2 where aic_stamm=anr and aic_rolle is null) anr2"+
        " from bew_pool p where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")":
        i==2 ? "select aic_stamm,eintritt,bezeichnung from stammview2 where (eintritt<"+g.DateTimeToString(DatS1.getDate())+" or eintritt>"+g.DateTimeToString(DatS2.getDate())+")":
        i==3 ? "select aic_stamm,austritt,bezeichnung from stammview2 where (austritt<"+g.DateTimeToString(DatS1.getDate())+" or austritt>"+g.DateTimeToString(DatS2.getDate())+")":
        i==4 ? "select aic_stamm,gultig_von,(select bezeichnung from stammview2 where aic_stamm=p.aic_stamm and aic_rolle is null) Stamm"+g.AU_Bezeichnung3("eigenschaft","p")+
            " from stammpool p where pro2_aic_protokoll is null and (gultig_von<"+g.DateTimeToString(DatS1.getDate())+" or gultig_von>"+g.DateTimeToString(DatS2.getDate())+")":
        i==5 ? "select aic_zwischenspeicher,gueltig,kennung,zwischentext from zwischenspeicher"+
            " where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")":
        i==6 ? "select aic_zwischenspeicher,gueltig,pro_aic_protokoll,kennung,zwischentext from zwischenspeicher"+
            " where (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")":null;

    if (s != null)
      new Tabellenspeicher(g,s,true).showGrid();
  }

  private void Delete()
  {
    JCOutlinerNode Nod=Out.getSelectedNode();
    int i=Nod.getLevel()>0 ? Sort.geti(Nod.getUserData()):0;
    int iProt=g.Protokoll("Entfernen",getBegriff());
    if (i==1)
      g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+
             " or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    else if (i==2)
      g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and (eintritt<"+g.DateTimeToString(DatS1.getDate())+
             " or eintritt>"+g.DateTimeToString(DatS2.getDate())+")");
    else if (i==3)
      g.exec("update stamm_protokoll set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and (austritt<"+g.DateTimeToString(DatS1.getDate())+
             " or austritt>"+g.DateTimeToString(DatS2.getDate())+")");
    else if (i==4)
      g.exec("update stammpool set pro2_aic_protokoll="+iProt+" where pro2_aic_protokoll is null and (gultig_von<"+g.DateTimeToString(DatS1.getDate())+
             " or gultig_von>"+g.DateTimeToString(DatS2.getDate())+")");
    else if (i==5)
      g.exec("update zwischenspeicher set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and (gueltig<"+g.DateTimeToString(DatB1.getDate())+
             " or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    else if (i==6)
          g.exec("delete from zwischenspeicher where (gueltig<"+g.DateTimeToString(DatB1.getDate())+" or gueltig>"+g.DateTimeToString(DatB2.getDate())+")");
    Refresh();
  }
}
