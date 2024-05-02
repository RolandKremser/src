/*
    All_Unlimited-Grunddefinition-CleanEig.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Sort;
import jclass.bwt.JCActionEvent;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import jclass.bwt.JCActionListener;
import All_Unlimited.Hauptmaske.Abfrage;

public class CleanEig extends javax.swing.JFrame
{
  /**
	 *
	 */
	private static final long serialVersionUID = -8103762445740986109L;
public static CleanEig get(Global rg,int iEig)
  {
    if(This==null)
      return new CleanEig(rg,iEig);
    else
    {
      This.FillGid(iEig);
      return This;
    }
  }

  public static void free()
  {
        if (This!=null)
        {
                This.g.testInfo("CleanEig.free");
                This.dispose();
                This=null;
        }
  }


    private CleanEig(Global rg,int iEig)
	{
		super("Eigenschaften reinigen");
		g=rg;
		iTabEig = g.TabTabellenname.getAic("EIGENSCHAFT");
		//Gid.getHeader().setForeground(Color.white);
		Gid.setForeground(Color.white);
		getContentPane().add("Center",Gid);
		JPanel Pnl = new JPanel(new GridLayout(1,0,5,5));
                BtnShow = g.getButton("show");
		BtnDelete = g.getButton("Loeschen");
		BtnBeenden = g.getButton("Beenden");
		Pnl.add(BtnShow);
		Pnl.add(BtnDelete);
                Pnl.add(BtnBeenden);
		getContentPane().add("South",Pnl);
		//show();
		FillGid(iEig);
		EnableButton();
                This=this;
		Gid.addItemListener(new JCItemListener()
		{
			public void itemStateChanged( JCItemEvent ev )
			{
				if(ev.getStateChange() == ItemEvent.SELECTED)
					EnableButton();
			}
		});
                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    if (s.equals("Show"))
                    {
                        java.util.Vector Vec=(java.util.Vector)Gid.getSelectedNode().getLabel();
                        showInfo(((Integer)Vec.elementAt(0)).intValue(),g,This,Sort.gets(Gid.getSelectedNode().getLabel(),2));
                    }
                    else if (s.equals("Delete"))
                      LoescheElement();
                    else if (s.equals("Beenden"))
                      dispose();
                  }
                };
                g.BtnAdd(BtnShow,"Show",AL);
                g.BtnAdd(BtnDelete,"Delete",AL);
                g.BtnAdd(BtnBeenden,"Beenden",AL);

                setSize(640,480);
	}

        public static String getModelle(int iAic,Global g)
        {
          return "select b.aic_begriff,b.kennung,b.DefBezeichnung Modell,modell.aic_modell,count(*) Anzahl from begriff b "+g.join("modell","b","begriff")+
              " join befehl2 on modell.aic_modell=befehl2.aic_modell"+g.join("spalte","befehl2")+g.join2("fixeigenschaft","spalte")+
              " where fixeigenschaft.aic_eigenschaft="+iAic+" group by modell.aic_modell,b.aic_begriff,b.kennung,b.DefBezeichnung";
        }

        public static void showInfo(int iAic,final Global g,JFrame thisFrame,String s)
        {
          JCActionListener AL=new JCActionListener() {
            public void actionPerformed(JCActionEvent ev)
            {
              JCOutlinerNode Nod = ((AUOutliner)ev.getSource()).getSelectedNode();
              int iAic = Sort.geti(Nod.getLabel(), 0);
              //g.testInfo("Begriff="+iAic);
              int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
              DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt, null, false, 1).show();
            }
          };

          JFrame DlgTab=new JFrame(s);
          JTabbedPane Pane = new JTabbedPane();
          VecBeg=new java.util.Vector<Integer>();
          Pane.add("Stammtyp",Global.getGid(null/*1*/,g,"select z.aic_stammtyp,kennung"+g.AU_Bezeichnung("Stammtyp")+",reihenfolge from stammtyp join stammtyp_zuordnung z on z.aic_stammtyp=stammtyp.aic_stammtyp where z.aic_eigenschaft="+iAic));
          Pane.add("Rolle",Global.getGid(null/*2*/,g,"select z.aic_Rolle,kennung"+g.AU_Bezeichnung("Rolle")+" from Rolle join Rolle_zuordnung z on z.aic_Rolle=Rolle.aic_Rolle where z.aic_eigenschaft="+iAic));
          Pane.add("Bewegungstyp",Global.getGid(null/*3*/,g,"select z.aic_bewegungstyp,kennung"+g.AU_Bezeichnung("Bewegungstyp")+",reihenfolge from bewegungstyp join bew_zuordnung z on z.aic_bewegungstyp=bewegungstyp.aic_bewegungstyp where z.aic_eigenschaft="+iAic));
          Pane.add("Eigenschaft",Global.getGid(null/*4*/,g,"select e.kennung Calc_Kennung"+g.AU_Bezeichnung("eigenschaft","e")+" calc_bezeichnung,e2.kennung"+g.AU_Bezeichnung("eigenschaft","e2")+" Eigenschaft,reihenfolge from eigenschaft e join eigenschaft_zuordnung z on e.aic_eigenschaft=z.aic_eigenschaft"+
                               " join eigenschaft e2 on z.eig_aic_eigenschaft=e2.aic_eigenschaft where z.aic_eigenschaft="+iAic+" or z.eig_aic_eigenschaft="+iAic));
          AUOutliner GidSp=Global.getGid(VecBeg/*5*/,g,"select b.aic_begriff,b.kennung,b.DefBezeichnung Abfrage"+g.AU_Bezeichnung("Stammtyp","b")+" Stammtyp"+g.AU_Bezeichnung("Bewegungstyp","b")+
                         " Bewegungstyp,b.web,count(*) Anzahl from begriff b "+g.join("abfrage","b","begriff")+g.join2("spalte","abfrage")+g.join2("fixeigenschaft","spalte")+
                         " where aic_eigenschaft="+iAic+" group by b.aic_begriff,b.kennung,b.DefBezeichnung,b.aic_stammtyp,b.aic_bewegungstyp,web");
          GidSp.addActionListener(AL);
          Pane.add("Abfrage-Spalte",GidSp);
          AUOutliner GidB=Global.getGid(VecBeg/*6*/,g,"select b.aic_begriff,b.kennung,b.DefBezeichnung Abfrage"+g.AU_Bezeichnung("Stammtyp","b")+" Stammtyp"+
                                                g.AU_Bezeichnung("Bewegungstyp","b")+" Bewegungstyp,(select kennung from begriff where aic_begriff=bedingung.aic_begriff) Vergleich,vergleichswert Wert"+
                                                " from begriff b "+g.join("abfrage","b","begriff")+g.join2("bedingung","abfrage")+g.join2("fixeigenschaft","bedingung")+" where aic_eigenschaft="+iAic);//+" group by b.aic_begriff,b.kennung,b.DefBezeichnung,b.aic_stammtyp,b.aic_bewegungstyp"));
          GidB.addActionListener(AL);
          Pane.add("Abfrage-Bedingung",GidB);
          Pane.add("Modell",Global.getGid(null/*VecBeg/*7*/,g,getModelle(iAic,g)));
          Pane.add("Befehle",Global.getGid(null/*12*/,g,"select (select defbezeichnung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where modell.aic_modell=befehl2.aic_modell) Modell"+
                                    ",c.aic_code"+/*g.AU_Bezeichnung("Begriffgruppe","c")+*/",(select kennung from Begriffgruppe where c.aic_begriffgruppe=aic_begriffgruppe) Gruppe,c.kennung,eingabe,Var,aic_modell,count(*) Anzahl from code c join befehl2 on c.aic_code=befehl2.aic_code"+g.join("spalte","befehl2")+
                                    g.join2("fixeigenschaft","spalte")+" where fixeigenschaft.aic_eigenschaft="+iAic+" group by c.aic_begriffgruppe,c.aic_code,c.kennung,eingabe,var,aic_modell"));
          Pane.add("Gruppen",Global.getGid(VecBeg/*8*/,g,"select z.aic_begriff,kennung,DefBezeichnung Gruppe,reihenfolge from gruppe_zuordnung z"+g.join("begriff","z")+" where z.aic_eigenschaft="+iAic));
          Pane.add("Stammpool1",Global.getGid(null/*9*/,g,"select aic_stammtyp"+g.AU_Bezeichnung2("Stammtyp","Stamm")+",count(*) Anzahl from stamm join stammpool p on stamm.aic_stamm=p.aic_stamm where p.pro2_aic_protokoll is null and p.aic_eigenschaft="+iAic+" group by aic_stammtyp"));
          Pane.add("Stammpool2",Global.getGid(null/*10*/,g,"select aic_bewegungstyp"+g.AU_Bezeichnung2("Bewegungstyp","Bew_Pool")+",count(*) Anzahl from Bew_Pool join stammpool p on Bew_Pool.aic_Bew_Pool=p.aic_Bew_Pool where Bew_Pool.pro_aic_protokoll is null and p.pro2_aic_protokoll is null and p.aic_eigenschaft="+iAic+" group by aic_bewegungstyp"));
          if (!VecBeg.isEmpty())
            Pane.add("Formular",Global.getGid(null/*11*/,g,"select distinct begriff.aic_begriff,begriff.kennung,begriff.defbezeichnung"+g.AU_Bezeichnung("Stammtyp","begriff")+" Stammtyp"+g.AU_Bezeichnung("Bewegungstyp","begriff")+
                                     " Bewegungstyp from begriff"+g.join2("formular","begriff")+g.join2("darstellung","formular")+" where darstellung.aic_begriff"+Static.SQL_in(VecBeg)));
          Pane.add("Modul",Global.getGid(null/*12*/,g,"select b.aic_begriff,b.kennung,b.defbezeichnung"+g.AU_Bezeichnung1("Code","b")+" Programm"+
                                    " from begriff b join modul m on b.aic_begriff=m.aic_begriff where aic_tabellenname="+g.TabTabellenname.getAic("EIGENSCHAFT")+" and aic_fremd="+iAic));
          DlgTab.getContentPane().add("Center",Pane);
          DlgTab.setSize(700,400);
          Static.centerComponent(DlgTab,thisFrame);
          DlgTab.setVisible(true);
        }

	private void EnableButton()
	{
		//g.printInfo("EnableButton");
		JCOutlinerNode Nod=Gid.getSelectedNode();
		//BtnDelete.setEnabled(Nod!=null && Nod.getLevel()==1 && (((Integer)((java.util.Vector)Nod.getLabel()).elementAt(5)).intValue()==0
		//	|| ((Integer)((java.util.Vector)Nod.getLabel()).elementAt(4)).intValue()==0));
		BtnDelete.setEnabled(Nod!=null && Nod.getLevel()==1 && !Nod.getStyle().getForeground().equals(ColRed));
	}

	private void LoescheElement()
	{
		java.util.Vector Vec=(java.util.Vector)Gid.getSelectedNode().getLabel();
		//Vec.elementAt(0);
                int pane = new Message(Message.YES_NO_OPTION,null,g).showDialog("Loeschen",new String[] {""+Vec.elementAt(2)});
		//int pane = JOptionPane.showConfirmDialog(this,"Wollen Sie wirklich die Eigenschaft <"+Vec.elementAt(2)+"> wirklich löschen?","Sicherheitsabfrage",JOptionPane.YES_NO_OPTION);
		if(pane == Message.YES_OPTION)
		{
			int iAic=Tabellenspeicher.geti(Vec.elementAt(0));
			g.printInfo("Lösche "+iAic);
			int iZu=Tabellenspeicher.geti(Vec.elementAt(4));
			int iDaten=Tabellenspeicher.geti(Vec.elementAt(5))+Tabellenspeicher.geti(Vec.elementAt(6));

			SQL Qry=new SQL(g);

			if (iDaten>0)
			{
				Qry.exec("delete from stammpool where aic_eigenschaft="+iAic);
				Qry.exec("delete from bew_von_bis where aic_eigenschaft="+iAic);
				Qry.exec("delete from bew_Wert where aic_eigenschaft="+iAic);
				Qry.exec("delete from bew_Stamm where aic_eigenschaft="+iAic);
                                Qry.exec("delete from bew_Begriff where aic_eigenschaft="+iAic);
				Qry.exec("delete from bew_Boolean where aic_eigenschaft="+iAic);
			}
			if (iZu>0)
			{
                          Qry.exec("update stammtyp set aic_eigenschaft=null where aic_eigenschaft="+iAic);
				Qry.exec("delete from stammtyp_zuordnung where aic_eigenschaft="+iAic);
				Qry.exec("delete from bew_zuordnung where aic_eigenschaft="+iAic);
				Qry.exec("delete from eigenschaft_zuordnung where aic_eigenschaft="+iAic+" or eig_aic_eigenschaft="+iAic);
				Qry.exec("delete from gruppe_zuordnung where aic_eigenschaft="+iAic);
                                Qry.exec("delete from rolle_zuordnung where aic_eigenschaft="+iAic);
				//Qry.exec("delete from FixEigenschaft where aic_eigenschaft="+iAic);
			}
			if (Qry.exec("delete from eigenschaft where aic_eigenschaft="+iAic))
			{
				Qry.exec("delete from bezeichnung where aic_tabellenname="+iTabEig+" and aic_fremd="+iAic);
				Qry.exec("delete from Daten_Memo where aic_tabellenname="+iTabEig+" and aic_fremd="+iAic);
				Qry.exec("delete from Daten_Bild where aic_tabellenname="+iTabEig+" and aic_fremd="+iAic);
                                g.TabEigenschaften.clearInhaltS("AIC",new Integer(iAic));
				Gid.getSelectedNode().getParent().removeChild(Gid.getSelectedNode());
				Gid.folderChanged(Gid.getRootNode());
			}
			else
				g.printInfo("Eigenschaft <"+Vec.elementAt(2)+"> kann nicht gelöscht werden !");

			Qry.free();
		}
	}

	private void FillGid(int iEig)
	{
		Tabellenspeicher Tab=new Tabellenspeicher(g,(g.Oracle() ? "select /*+ RULE */":"select")+" x.*,case when Abfragen>0 or Zugeordnet>0 and gueltig>0 then "+ColRed.getRGB()+" when Zugeordnet=0 and gueltig=0 and geloescht=0 then "+Color.green.getRGB()+" else "+Color.yellow.getRGB()+" end Farbe from "+
                  "(select aic_eigenschaft aic,e.kennung"+g.AU_Bezeichnung("Eigenschaft","e")+" Bezeichnung,begriff.kennung datentyp,"+
			// Zugeordnet:
			"(select count(*) from stammtyp z where z.aic_eigenschaft=e.aic_eigenschaft)+"+
                        "(select count(*) from stammtyp_zuordnung z where z.aic_eigenschaft=e.aic_eigenschaft)+"+
			"(select count(*) from bew_zuordnung z where z.aic_eigenschaft=e.aic_eigenschaft)+"+
			"(select count(*) from gruppe_zuordnung z where z.aic_eigenschaft=e.aic_eigenschaft)+"+
                        "(select count(*) from rolle_zuordnung z where z.aic_eigenschaft=e.aic_eigenschaft)+"+
			"(select count(*) from eigenschaft_zuordnung z where z.aic_eigenschaft=e.aic_eigenschaft or z.eig_aic_eigenschaft=e.aic_eigenschaft) Zugeordnet,"+
			"(select count(*) from eigenschaft z where z.eig_aic_eigenschaft=e.aic_eigenschaft)+"+
                        "(select count(*) from FixEigenschaft z where z.aic_eigenschaft=e.aic_eigenschaft) Abfragen,"+
			// Gültig:
			"(select count(*) from stammpool p where p.aic_eigenschaft=e.aic_eigenschaft and pro2_aic_protokoll is null)+"+
			"(select count(*) from Bew_von_bis p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is null)+"+
			"(select count(*) from Bew_Wert p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is null)+"+
			"(select count(*) from Bew_Stamm p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is null)+"+
                        "(select count(*) from Bew_Begriff p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is null)+"+
			"(select count(*) from Bew_Boolean p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is null) gueltig,"+
			//Gelöscht:
			"(select count(*) from stammpool p where p.aic_eigenschaft=e.aic_eigenschaft and pro2_aic_protokoll is not null)+"+
			"(select count(*) from Bew_von_bis p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is not null)+"+
			"(select count(*) from Bew_Wert p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is not null)+"+
			"(select count(*) from Bew_Stamm p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is not null)+"+
                        "(select count(*) from Bew_Begriff p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is not null)+"+
			"(select count(*) from Bew_Boolean p"+g.join("bew_pool","p")+" where p.aic_eigenschaft=e.aic_eigenschaft and pro_aic_protokoll is not null) geloescht"+
 			" from eigenschaft e"+g.join("begriff","e")+(iEig==0?"":" where e.aic_eigenschaft="+iEig)+") x order by gueltig,geloescht,zugeordnet,abfragen",true);
		Tab.checkBezeichnung();
                //Dimension dim=getSize();
                Tab.showGrid(Gid);
                Gid.setBackground(Color.darkGray);
                //setSize(dim);
		//setSize(Tab.showGrid(Gid)+40,400);
		//show();
	}

	// add your data members here
	private int iTabEig;
	private Global g;
	private AUOutliner Gid = new AUOutliner(new JCOutlinerFolderNode(""));
	private JButton BtnShow;
    public static JButton BtnDelete;
	private JButton BtnBeenden;
        private Color ColRed=new Color(1.0f,0.25f,0.25f);
        public static CleanEig This=null;
        private static java.util.Vector VecBeg=null;
}

