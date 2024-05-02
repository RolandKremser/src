/*
    All_Unlimited-Grunddefinition-Lizenz.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.util.zip.ZipEntry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
//import jclass.util.JCqsort;
import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.SpinBox;

//import java.awt.Dimension;

import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Hauptmaske.Aufruf;

public class Lizenz extends All_Unlimited.Allgemein.Formular
{
	public static Lizenz get(Global rg)
	{
		if (Self == null)
		  Self=new Lizenz(rg);
		else
		  Self.show();
		return Self;
	}
	
	 public static void free()
	  {
	    if(Self != null) {
	      Self.g.winInfo("Lizenz.free");
	      Self.thisFrame.dispose();
	      Self = null;
	    }
	  }
	
	private Lizenz(Global glob)
	{
		super("Lizenz",null,glob);
		g=glob;
		Self=this;
		g.winInfo("Lizenz.create");
                if (!g.Verwaltung())
                {
                    new Message(Message.WARNING_MESSAGE,null,g).showDialog("keineBerechtigung");
                    thisFrame.dispose();
                    return;
                }
		String[] s = new String[] {g.getShow("Tabelle"),g.getShow("Anzahl")};
		OutLizenz.setColumnButtons(s);
		OutLizenz.setNumColumns(s.length);

		OutLizenz.setRootVisible(false);
		OutTabellen1.setRootVisible(false);
		OutTabellen2.setRootVisible(false);
		OutTabellen1.setColumnButtons(new String[] {g.getShow("Bezeichnung")});
		//OutTabellen1.setColumnDisplayWidth(0, 250);
		OutTabellen2.setColumnButtons(new String[] {g.getShow("Bezeichnung")});
		//OutTabellen2.setColumnDisplayWidth(0, 250);
		OutVerwendung.setRootVisible(false);
        s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Prog")};
        OutVerwendung.setColumnButtons(s);
        OutVerwendung.setNumColumns(s.length);
		OutLizenz.setAllowMultipleSelections(true);
		OutTabellen1.setAllowMultipleSelections(true);
		OutTabellen2.setAllowMultipleSelections(true);
		OutVerwendung.setAllowMultipleSelections(true);
		//OutLizenz.setColumnLabelSort(false);
		//OutTabellen1.setColumnLabelSort(false);
		//OutTabellen2.setColumnLabelSort(false);
		//OutVerwendung.setColumnLabelSort(false);

		CboMandant = new ComboSort(g,ComboSort.Farbe);
		CboMandant.fillDefTable("MANDANT",false);
		CboMandant.setSelectedAIC(g.getMandant());
        CboMandant.setPreferredSize(new java.awt.Dimension(200,20));
		CboMandantKopieren = new ComboSort(g);
		CboMandantKopieren.fillDefTable("Mandant",false);
		SpnAnzahl.setMinimum(-1);

		FEExport = new FileEingabe(g);

		Build();
                BtnEditOk=g.getButton("Ok");
                BtnEditAbbruch=g.getButton("Abbruch");
                CboForm=new ComboSort(g);
                CboForm.fillCbo("SELECT Begriff.AIC_Begriff,Begriff.Kennung,DefBezeichnung Bezeichnung from begriff"+g.join("code","Begriff")+" where code.kennung='Start'","Begriff",true,false);
                CboHMandant = new ComboSort(g);
                CboHMandant.fillDefTable("Mandant",false);
                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    //Object Btn = ev.getSource();
                    String s = ev.getActionCommand();
                    g.progInfo("UserManager.Command=" + s);
                    if (s.equals("New"))
                      EdtMandant(0);
                    else if (s.equals("Edt"))
                      EdtMandant(CboMandant.getSelectedAIC());
                    else if (s.equals("Show"))
                      ShowMandant();
                    else if (s.equals("EditOk"))
                      MandantSpeichern();
                    else if (s.equals("EditAbbruch"))
                      DlgEdit.setVisible(false);
                    else if (s.equals("Del"))
                    {
                      DelMandant(CboMandant.getSelectedAIC(),CboMandant.getSelectedBezeichnung());
                    }
                    else if (s.equals("LizenzHinzufuegen"))
                    {
                        LizenzHinzufuegen();
                        EnableButtons();
                    }
                    else if (s.equals("LizenzEntfernen"))
                    {
                            LizenzEntfernen();
                            EnableButtons();
                    }
                    else if (s.equals("VerwendungHinzufuegen"))
                    {
                            VerwendungHinzufuegen();
                            EnableButtons();
                    }
                    else if (s.equals("VerwendungEntfernen"))
                    {
                            VerwendungEntfernen();
                            EnableButtons();
                    }
                    else if (s.equals("Speichern"))
                    {
                           Save();
                           EnableButtons();
                    }
                    else if (s.equals("Ruecksetzen"))
                    {
                           Laden();
                           EnableButtons();
                    }
                    else if (s.equals("Kopieren"))
                    {
                      Static.centerComponent(FrmKopieren,thisFrame);
                      FrmKopieren.setVisible(true);
                    }

                  }
                };
                g.BtnAdd(getButton("Neu"),"New",AL);
                BtnEdtMandant=getButton("Edit");
                g.BtnAdd(BtnEdtMandant,"Edt",AL);
                g.BtnAdd(getButton("show"),"Show",AL);
                g.BtnAdd(BtnEditOk,"EditOk",AL);
                g.BtnAdd(BtnEditAbbruch,"EditAbbruch",AL);
                BtnDelMandant=getButton("Loeschen");
                g.BtnAdd(BtnDelMandant,"Del",AL);
                g.BtnAdd(BtnLizenzHinzufuegen,"LizenzHinzufuegen",AL);
                g.BtnAdd(BtnLizenzEntfernen,"LizenzEntfernen",AL);
                g.BtnAdd(BtnVerwendungHinzufuegen,"VerwendungHinzufuegen",AL);
                g.BtnAdd(BtnVerwendungEntfernen,"VerwendungEntfernen",AL);
                g.BtnAdd(BtnSpeichern,"Speichern",AL);
                g.BtnAdd(BtnRuecksetzen,"Ruecksetzen",AL);
                g.BtnAdd(BtnKopieren,"Kopieren",AL);

		BtnOkKopieren.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				bKopieren=true;
				CboMandant.setSelectedAIC(CboMandantKopieren.getSelectedAIC());
				FrmKopieren.setVisible(false);
				bKopieren=false;
			}
		});

		BtnAbbruchKopieren.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FrmKopieren.setVisible(false);
			}
		});

		BtnBeenden.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(bSpeichern && g.Def())
				{
					int iAntwort=new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern");
					if(iAntwort==Message.YES_OPTION)
						Save();
				}
				hide();
			}
		});

		BtnExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FrmExport.setVisible(true);
			}
		});

		BtnExportOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Exportieren(FEExport.getValue(),CboMandant.getSelectedAIC());
				FrmExport.setVisible(false);
			}
		});

		BtnExportAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FrmExport.setVisible(false);
			}
		});



		CboMandant.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					if(bSpeichern)
					{
						int iAntwort=new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Speichern");
						if(iAntwort==Message.YES_OPTION)
							Save();
					}

					if(!bKopieren)
					{
						Laden();
					}
					else
					{
						iAIC_Mandant=CboMandant.getSelectedAIC();
						bSpeichern=true;
					}
					EnableButtons();
				}
			}
		});

		OutLizenz.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
						selectNodes(true);
						EnableButtons();
			}
		});

		OutTabellen1.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
						selectNodes(false);
						EnableButtons();
			}
		});

		OutVerwendung.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{

			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
				EnableButtons();
			}
		});

		OutTabellen2.addItemListener(new JCOutlinerListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{

			}

			public void outlinerFolderStateChangeBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerFolderStateChangeEnd(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectBegin(JCOutlinerEvent e)
			{
			}

			public void outlinerNodeSelectEnd(JCOutlinerEvent e)
			{
				EnableButtons();
			}
		});

                SpnAnzahl.addChangeListener(new ChangeListener()
                {
                  public void stateChanged(ChangeEvent ae)
                  {
                      ChangeAnzahl();
                      EnableButtons();
                  }
		});

		Laden();

		show();
		EnableButtons();
	}

        private void ShowMandant()
        {
          JDialog Frm = new JDialog((JFrame)thisFrame,"Mandanten",true);
          AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
          String [] s = new String[]{g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Startformular"),g.getShow("Versuche"),g.getShow("Verbindungen"),g.getShow("Stempelimport"),g.getShow("Aic"),g.getShow("Tod")};
          Out.setColumnButtons(s);
          Out.setNumColumns(s.length);
          Out.setRootVisible(false);
          Frm.getContentPane().add("Center",Out);
          JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
          NodeRoot.removeChildren();
          fillNode(0,NodeRoot);
          Out.folderChanged(NodeRoot);
          Frm.pack();
          //Static.makeVisible(Out,NodeMom);
          Static.centerComponent(Frm,thisFrame);
          Frm.setVisible(true);
        }

        private void fillNode(int riMandant,JCOutlinerFolderNode NodeP)
        {
        	JCOutlinerNodeStyle StyTod = new JCOutlinerNodeStyle();
            StyTod.setForeground(g.ColTod);
          for (int i = 0; i < g.TabMandanten.size(); i++)
            if (g.TabMandanten.getI(i, "man_aic_mandant") == riMandant)
            {
              Vector<Object> VecSpalten = new Vector<Object>();
              VecSpalten.addElement(g.TabMandanten.getS(i, "Bezeichnung"));
              VecSpalten.addElement(g.TabMandanten.getS(i, "Kennung"));
              VecSpalten.addElement(g.TabMandanten.getI(i, "aic_begriff")==0 ? Static.sKein:g.getBegBez(g.TabMandanten.getI(i, "aic_begriff")));
              int iV=g.TabMandanten.getI(i, "Versuche");
              VecSpalten.addElement((iV & g.VMAX)==g.VMAX ? -1:iV & g.VMAX);
              VecSpalten.addElement(g.TabMandanten.getI(i, "Verbindungen"));
              VecSpalten.addElement(Static.JaNein2((iV&g.TIMP)>0));
              VecSpalten.addElement(g.TabMandanten.getInhalt("Aic_Mandant", i));
              boolean bTod=g.TabMandanten.getB(i, "Tod");
              VecSpalten.addElement(Static.JaNein2(bTod));
              JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecSpalten, NodeP);
              Node.setUserData(g.TabMandanten.getInhalt("Aic_Mandant", i));
              if (bTod)
            	  Node.setStyle(StyTod);
              //Node.setStyle(g.getStyle(g.getRolleGif(g.TabMandanten.getI(i, "Aic_Mandant")))); //(Image)g.TabRollen.getInhalt("Bild")));
              //if (g.TabMandanten.getI(i, "Aic") == CboHMandant.getSelectedAIC())
              //  NodeMom = Node;
              fillNode(g.TabMandanten.getI(i, "Aic_Mandant"), Node);
            }
        }

        private void EdtMandant(int i)
        {
          iAIC_Mandant=i;
          //g.progInfo("EdtMandant "+i);
          String sTitel= i == 0 ? "neuer Mandant" : "Mandant bearbeiten";
          if (DlgEdit==null)
          {
            DlgEdit = new JDialog((JFrame)this.thisFrame,sTitel, true);
            DlgEdit.getContentPane().setLayout(new BorderLayout(2, 2));
            JPanel Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
            g.addLabel(Pnl, "Bezeichnung",TxtBezeichnung);
            g.addLabel(Pnl, "Kennung",TxtKennung);
            g.addLabel(Pnl, "Passwort",TxtPW1);
            g.addLabel(Pnl, "Passwort2",TxtPW2);
            g.addLabel(Pnl, "Startformular",CboForm);
            g.addLabel(Pnl, "Vorgaenger",CboHMandant);
            g.addLabel(Pnl, "Versuche",EdtVersuche);
            g.addLabel(Pnl, "Verbindungen",EdtConnects);
            Pnl.add(new JLabel());
            Pnl.add(new JLabel());
            DlgEdit.getContentPane().add("West", Pnl);
            Pnl = new JPanel(new GridLayout(0, 1, 2, 2));
            Pnl.add(TxtBezeichnung);
            Pnl.add(TxtKennung);
            Pnl.add(TxtPW1);
            Pnl.add(TxtPW2);
            Pnl.add(CboForm);
            Pnl.add(CboHMandant);
            EdtVersuche.setMinimum(-1);
            EdtVersuche.setMaximum(14);
            EdtConnects.setMinimum(-1);
            CbxTIMP=g.getCheckbox("Stempelimport");
            CbxTod=g.getCheckbox("Tod");
            Pnl.add(EdtVersuche);
            Pnl.add(EdtConnects);
            Pnl.add(new JLabel("Wichtig: zuerst Stempelimport ausschalten, dann nach ca 30 min auf inaktiv"));
            Pnl.add(g.addTwo(CbxTIMP,CbxTod));
            //TxtMemo.setPreferredSize(new Dimension(100,100));
            //Pnl.add("South",TxtMemo);
            DlgEdit.getContentPane().add("Center", Pnl);
            Pnl = new JPanel(new GridLayout(1, 0, 2, 2));
            Pnl.add(BtnEditOk);
            Pnl.add(BtnEditAbbruch);
            DlgEdit.getContentPane().add("South", Pnl);
            DlgEdit.pack();
            Static.centerComponent(DlgEdit,thisFrame);
          }
          else
            DlgEdit.setTitle(sTitel);
          if (i>0)
          {
            SQL Qry=new SQL(g);
            Qry.open("select *"+g.AU_Bezeichnung("mandant")+" from mandant where aic_mandant="+i);
            TxtBezeichnung.setText(Qry.getS("Bezeichnung"));
            TxtKennung.setText(Qry.getS("Kennung"));
            TxtPW1.setValue(g.getPassword(Qry.getS("Passwort"),g.PWR,0));
            TxtPW2.setValue(g.getPassword(Qry.getS("Passwort"),g.PWR,0));
            CboForm.setSelectedAIC(Qry.getI("AIC_Begriff"));
            int iVersuche=Qry.getI("Versuche");
            CbxTIMP.setSelected(iVersuche>0 && (iVersuche&g.TIMP)>0);
            CbxTod.setSelected(Qry.getB("Tod"));
            if (iVersuche>0) iVersuche&=g.VMAX;
            EdtVersuche.setValue(iVersuche==0 ? 3:iVersuche==g.VMAX ? -1:iVersuche);
            EdtConnects.setValue(Qry.getI("Verbindungen")==0 ? -1:Qry.getI("Verbindungen"));
            CboHMandant.setSelectedAIC(Qry.getI("Man_AIC_Mandant"));
            Qry.free();
          }
          else
          {
            TxtBezeichnung.setText("");
            TxtKennung.setText("");
            TxtPW1.setValue("");
            TxtPW2.setValue("");
            CbxTIMP.setSelected(false);
            CbxTod.setSelected(false);
            EdtVersuche.setValue(3);
            EdtConnects.setValue(1);
            CboHMandant.setSelectedAIC(g.getMandant());
          }
          DlgEdit.setVisible(true);
        }

        private void MandantSpeichern()
        {
          String sKen=TxtKennung.getText();
          String sPW=new String(TxtPW1.getPassword());
          boolean bTod=CbxTod.isSelected();
          if (sKen.equals("") || sKen.indexOf(' ')>=0 && (iAIC_Mandant==0 || !bTod))
            new Message(Message.WARNING_MESSAGE, DlgEdit, g,10).showDialog("KennungLeer"+(sKen.equals("")?"":"zeichen"));
          else if (sPW.equals("") && (iAIC_Mandant==0 || !bTod) || !sPW.equals(new String(TxtPW2.getPassword())))
            new Message(Message.WARNING_MESSAGE,DlgEdit,g,10).showDialog("Passwort"+(sPW.equals("") ? "":"_ungleich"));
          else
          {
            SQL Qry = new SQL(g);
            Qry.add("Kennung", sKen);
            if (!bTod)
            	Qry.add("Passwort", g.PasswordConvert(sPW, g.PWR, 0));
            Qry.add0("Aic_Begriff", CboForm.getSelectedAIC());
            //if (g.getMandant()!=iAIC_Mandant)
            Qry.add0("MAN_AIC_MANDANT", CboHMandant.getSelectedAIC());
            int iV=Sort.geti(EdtVersuche.getValue());
            Qry.add("Versuche", (iV==-1 ? g.VMAX:iV)+(CbxTIMP.isSelected()?g.TIMP:0));
            Qry.add0("Tod",bTod);
            Qry.add("Aic_Logging",g.getLog());
            Qry.add("Verbindungen", EdtConnects.getValue());
            boolean bNeu = iAIC_Mandant == 0;
            if (bNeu)
              iAIC_Mandant = Qry.insert("Mandant", true);
            else
              Qry.update("Mandant", iAIC_Mandant);
            Qry.free();
            if (TxtBezeichnung.Modified())
              g.setBezeichnung(TxtBezeichnung.getOld(), TxtBezeichnung.getText(), g.TabTabellenname.getAic("MANDANT"), iAIC_Mandant, 0);
            DlgEdit.setVisible(false);
            g.LoadMandant(); // 20.8.2019: immer neu Laden, damit show richtig           
            if (bNeu || CbxTod.Modified())
            {
              CboMandant.fillDefTable("MANDANT", false);
              CboMandant.setSelectedAIC(iAIC_Mandant);
            }
          }
        }
        
        private int checkM(Tabellenspeicher Tab,int iM,String sTab,String sDel)
        {
        	int iAnz=SQL.getInteger(g,"select count(*) from "+sTab+" where aic_mandant="+iM+(sDel==null ?"":" and "+sDel+" is null"));
        	int iAnz2=sDel==null ? 0:SQL.getInteger(g,"select count(*) from "+sTab+" where aic_mandant="+iM+" and "+sDel+" is not null");
        	if (iAnz>0 || iAnz2>0)
        	{
        		Tab.addInhalt("Tabelle",sTab);
        		Tab.addInhalt("Daten",iAnz);
        		Tab.addInhalt("entfernt",iAnz2);
        	}
        	return iAnz+iAnz2;
        }
        
        private Tabellenspeicher getDelTab(int i)
        {
        	Tabellenspeicher TabM=new Tabellenspeicher(g,new String[] {"Tabelle","Daten","entfernt"});
            //checkM(TabM,i,"Stamm_Protokoll","pro_aic_protokoll");
            Vector<Integer> Vec=SQL.getVector("select distinct aic_stammtyp2 from stamm_protokoll where aic_Mandant="+i, g);
            if (Vec.size()>0)
          	  TabM=new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Stammtyp")+" Tabelle,(select count(*) from stamm_protokoll where aic_stammtyp2=aic_stammtyp and aic_mandant="+i+" and pro_aic_protokoll is null) Daten"+
          			  ",(select count(*) from stamm_protokoll where aic_stammtyp2=aic_stammtyp and aic_mandant="+i+" and pro_aic_protokoll is not null) entfernt from stammtyp where"+g.in("aic_stammtyp", Vec),true);
            //select distinct stt.kennung,(select from stammtyp stt join stamm_protokoll p on stt.aic_stammtyp=p.aic_stammtyp2 where aic_mandant=5 group by aic_stammtyp
            //checkM(TabM,i,"Stammpool","pro2_aic_protokoll");
            checkM(TabM,i,"Bew_pool","pro_aic_protokoll");
            checkM(TabM,i,"Zwischenspeicher","pro_aic_protokoll");
            checkM(TabM,i,"Abschluss","pro_aic_protokoll");
            checkM(TabM,i,"Benutzer","geloescht");
            checkM(TabM,i,"Benutzergruppe",null);
            checkM(TabM,i,"Computer","geloescht");
            checkM(TabM,i,"Logging",null);
            checkM(TabM,i,"Parameter",null);
            checkM(TabM,i,"Begriff",null);
            checkM(TabM,i,"Layout",null);
            //g.fixtestInfo("Anz="+iAnz);
            return TabM;
        }

        private void DelMandant(int i,String sBez)
        {      
          if (new Message(Message.YES_NO_OPTION+Message.SHOW_TAB, (JFrame)thisFrame, g).showDialog("Loeschen", new String[] {CboMandant.getSelectedBezeichnung()},getDelTab(i)) == Message.YES_OPTION)
          {
        	  g.diskInfo("Versuche "+sBez+" zu löschen");
        	  g.delInfo("update computer set geloescht="+g.now()+",aic_mandant=null where aic_mandant="+i,"Computer");
        	  boolean bOk=g.delInfo("delete from abschluss where aic_mandant="+i,"Abschlüsse");
        	  bOk=bOk && g.delInfo("delete from zwischenspeicher where aic_mandant="+i,"aus Zwischenspeicher");
        	  bOk=bOk && g.delInfo("delete from Parameter where aic_mandant="+i,"aus Parameter");
        	  bOk=bOk && g.delInfo("delete from berechtigung where aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+i+")","Benutzergruppe-Berechtigungen");
        	  bOk=bOk && g.delInfo("delete from benutzer_zuordnung where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Benutzer-Zuordnungen");
        	  bOk=bOk && g.delInfo("delete from benutzer_zuordnung where aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+i+")","Benutzergruppe-Zuordnungen");
        	  bOk=bOk && g.delInfo("delete from spalten2 where aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+i+")","Spalten2 laut Benutzergruppe");
        	  bOk=bOk && g.delInfo("delete from spalten2 where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Spalten2 laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from spalte_z2 where aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+i+")","spalte_z2 laut Benutzergruppe");
        	  bOk=bOk && g.delInfo("delete from spalte_z2 where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Spalte_z2 laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from spalte_user where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Spalte_User laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from druck where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Druck laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from Raster where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Raster laut Benutzer");
        	  
        	  g.delInfo("update benutzer set geloescht="+g.now()+" where geloescht is null and aic_mandant="+i,"Benutzer markiert");
        	  g.exec("update benutzer set aic_logging=null where aic_mandant="+i);
        	  // Bew löschen
        	  if (SQL.getInteger(g, "select count(*) from bew_pool where aic_mandant="+i)>0)
        	  {
	        	  String sBewIn=" in (select aic_bew_pool from bew_pool where aic_mandant="+i+")";
	        	  bOk=bOk && g.delInfo("delete from bew_Wert where aic_bew_pool"+sBewIn,"aus Bew_Wert");
	        	  bOk=bOk && g.delInfo("delete from bew_spalte where aic_bew_pool"+sBewIn,"aus Bew_Spalte");
	        	  bOk=bOk && g.delInfo("delete from bew_boolean where aic_bew_pool"+sBewIn,"aus Bew_Boolean");
	        	  bOk=bOk && g.delInfo("delete from bew_begriff where aic_bew_pool"+sBewIn,"aus Bew_Begriff");
	        	  bOk=bOk && g.delInfo("delete from bew_von_bis where aic_bew_pool"+sBewIn,"aus Bew_Von_Bis");
	        	  bOk=bOk && g.delInfo("delete from bew_Aic where aic_bew_pool"+sBewIn,"aus Bew_Aic");
	        	  bOk=bOk && g.delInfo("delete from verlauf where aic_bew_pool"+sBewIn,"aus Verlauf laut Bew_Pool");
	        	  bOk=bOk && g.delInfo("delete from stammpool where aic_stamm is null and aic_bew_pool"+sBewIn,"aus Stammpool laut Bew_Pool");
	        	  bOk=bOk && g.delInfo("delete from bew_stamm where aic_bew_pool"+sBewIn,"aus Bew_stamm");
	        	  bOk=bOk && g.delInfo("delete from bew_pool where aic_mandant="+i,"aus Bew_Pool");
        	  }
        	  // Stamm löschen
        	  bOk=bOk && g.delInfo("delete from stammpool where aic_stamm in (select aic_stamm from stamm_protokoll where aic_mandant="+i+")","aus Stammpool");
        	  bOk=bOk && g.delInfo("delete from verlauf where aic_stamm in (select aic_stamm from stamm_protokoll where aic_mandant="+i+")","aus Verlauf laut Stamm");
        	  bOk=bOk && g.delInfo("delete from stamm_protokoll where aic_mandant="+i,"aus Stamm_protokoll");
        	  Vector<Integer> VecB=SQL.getVector("select aic_begriff from abfrage where aic_benutzergruppe in (select aic_benutzergruppe from benutzergruppe where aic_mandant="+i+")",g);
        	  if (VecB.size()>0)
        		  for(int iP=0;iP<VecB.size();iP++)
        		  {
        			int iB=Sort.geti(VecB, iP);
        			String sBBez=SQL.getString(g, "select defbezeichnung from begriff where aic_begriff="+iB);
        			g.exec("update begriff set Tod=1 where aic_begriff="+iB);
        			delVInfo("update abfrage set aic_benutzergruppe=null where aic_begriff="+iB,"Benutzergruppe von Abfrage "+sBBez);
        		  }
        	  VecB=SQL.getVector("select aic_begriff from abfrage where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")",g);
        	  if (VecB.size()>0)
        		  for(int iP=0;iP<VecB.size();iP++)
        		  {
        			int iB=Sort.geti(VecB, iP);
        			String sBBez=SQL.getString(g, "select defbezeichnung from begriff where aic_begriff="+iB);
        			g.exec("update begriff set Tod=1 where aic_begriff="+iB);
        			delVInfo("update abfrage set aic_benutzer=null where aic_begriff="+iB,"Benutzer von Abfrage "+sBBez);
        		  }
        	  bOk=bOk && g.delInfo("delete from abschluss_zuordnung where aic_benutzergruppe in (select aic_benutzergruppe from Benutzergruppe where aic_mandant="+i+")","Abschluss-Zuordnung");
        	  bOk=bOk && g.delInfo("delete from Benutzergruppe where aic_mandant="+i,"Benutzergruppen");
        	  //TODO Bew laut Protokoll laut Logging löschen
        	  Vector<Integer> VecBew=SQL.getVector("select aic_bew_pool from bew_pool where aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))",g);
        	  if (VecBew.size()>0)
        	  {
			    int iAnz=Aufruf.destroyAll(g,VecBew);
			    g.SaveVerlauf(getBegriff()/*g.getBegriffAicS("Frame", "Lizenz")*/, 0, 0, 0, iAnz+" Bew laut Protokoll laut Logging entfernt",0);
        	  }
        	  delVInfo("Stamm_protokoll where aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))","Stamm_Protokoll laut Protokoll laut Logging von "+sBez);      	  
        	  delVInfo("Stamm_protokoll where pro_aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))","Stamm_Protokoll laut Protokoll2 laut Logging von "+sBez);
        	  delVInfo("Stammpool where aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))","Stammpool laut Protokoll laut Logging von "+sBez);      	  
        	  delVInfo("Stammpool where pro_aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))","Stammpool laut Protokoll2 laut Logging von "+sBez);
        	  delVInfo("Stammpool where pro2_aic_protokoll in (select aic_protokoll from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+"))","Stammpool laut Protokoll3 laut Logging von "+sBez);
        	  bOk=bOk && g.delInfo("delete from protokoll where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus Protokoll");
        	  bOk=bOk && g.delInfo("delete from Parameter where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus Parameter laut Logging");
        	  bOk=bOk && g.delInfo("delete from verlauf where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus Verlauf laut Logging");
        	  bOk=bOk && g.delInfo("delete from defverlauf where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus DefVerlauf laut Logging");
        	  g.delInfo("delete from fensterpos where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus Fensterpos laut Logging");
        	  Vector<Integer> VecH=SQL.getVector("select aic_hauptschirm from hauptschirm where aic_logging in (select aic_logging from logging where aic_mandant="+i+")", g);
        	  bOk=bOk && g.delInfo("delete from ansicht where"+g.in("aic_hauptschirm", VecH),"aus Ansicht");
        	  bOk=bOk && g.delInfo("delete from hauptschirm where"+g.in("aic_hauptschirm", VecH),"aus Hauptschirm");
        	  bOk=bOk && g.delInfo("delete from fehler where aic_logging in (select aic_logging from logging where aic_mandant="+i+")","aus Fehler laut Logging");
        	  g.exec("update begriff set aic_logging=null where aic_logging in (select aic_logging from logging where aic_mandant="+i+")");
        	  bOk=bOk && g.delInfo("delete from Logging where aic_mandant="+i,"aus Logging");
        	  //Protokoll laut Logging laut benutzer
        	  bOk=bOk && g.delInfo("delete from Protokoll where aic_logging in (select aic_logging from logging where aic_Benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+"))","aus Protokoll laut Logging laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from Logging where aic_Benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","aus Logging laut Benutzer");
        	  bOk=bOk && g.delInfo("delete from Lizenz where aic_mandant="+i,"aus Lizenz");
        	  
        	  //Bew laut Protokoll laut Benutzer löschen
        	  VecBew=SQL.getVector("select aic_bew_pool from bew_pool where aic_protokoll in (select aic_protokoll from protokoll where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+"))",g);
        	  if (VecBew.size()>0)
        	  {
			    int iAnz=Aufruf.destroyAll(g,VecBew);
			    g.SaveVerlauf(getBegriff()/*g.getBegriffAicS("Frame", "Lizenz")*/, 0, 0, 0, iAnz+" Bew laut Protokoll laut Benutzer entfernt",0);
        	  }
        	  bOk=bOk && g.delInfo("delete from protokoll where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","aus Protokoll laut Benutzer");
        	  delVInfo("parameter where aic_benutzer in (select aic_benutzer from benutzer where aic_mandant="+i+")","Parameter laut Benutzer");
        	  if (bOk) delVInfo("Benutzer where aic_mandant="+i,"Benutzer");
        	  Vector<Integer> VecLayout=SQL.getVector("select aic_layout from layout where aic_mandant="+i,g);
        	  if (VecLayout.size()>0)
        	  {
        		  g.exec("delete from druck where aic_layout"+Static.SQL_in(VecLayout));
        		  delVInfo("Layout where aic_mandant="+i,"Layout");
        	  }
        	  delVInfo("Zeile where aic_mandant="+i,"Zeile(n)");
        	  delVInfo("Feiertag_zuordnung where aic_mandant="+i,"Feiertag-Zuordnung");
        	  g.exec("update begriff set tod=1,aic_mandant=1 where aic_mandant="+i);
        	  if (bOk)
        	  {   		 
	            if (g.exec("delete from mandant where aic_mandant=" + i)>0)
	            {
	            	g.diskInfo("DelMandant " + i+" -> Mandant "+sBez+" gelöscht");
	            	g.SaveVerlauf(getBegriff()/*g.getBegriffAicS("Frame", "Lizenz")*/, 0, 0, 0,"Mandant "+sBez+" entfernt",0);
	            	g.LoadMandant();
	            	new Message(Message.INFORMATION_MESSAGE,thisJFrame(),g).showDialog("Mandant_entfernt",new String[] {sBez});
	            	CboMandant.fillDefTable("MANDANT", false);
	            }
        	  }
        	  else
        	  {
        		g.exec("update mandant set tod=1 where aic_mandant=" + i);
        		new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,thisJFrame(),g).showDialog("MandantNichtEntfernt", new String[] {CboMandant.getSelectedBezeichnung()},getDelTab(i));
        	  }
        	  
          }
        }
        
    private void delVInfo(String sSQL,String sInfo)
    {
      int iAnz=g.exec(sSQL.startsWith("update") ? sSQL:"delete from "+sSQL);
  	  if (iAnz>0)
  	  {
  		  g.SaveVerlauf(getBegriff(), 0, 0, 0, iAnz+" "+sInfo+" entfernt",0);
  		  g.diskInfo(iAnz+" "+sInfo+" entfernt");
  	  }
    }

	private void Build()
	{

		BtnSpeichern = getButton("Speichern");
		if(BtnSpeichern==null) BtnSpeichern = new JButton();
		BtnRuecksetzen = getButton("Ruecksetzen");
		if(BtnRuecksetzen==null) BtnRuecksetzen = new JButton();
		BtnLizenzHinzufuegen = getButton(">");
		if(BtnLizenzHinzufuegen==null) BtnLizenzHinzufuegen = new JButton();
		BtnLizenzEntfernen = getButton("<");
		if(BtnLizenzEntfernen==null) BtnLizenzEntfernen = new JButton();
		BtnVerwendungHinzufuegen = getButton(">2");
		if(BtnVerwendungHinzufuegen==null) BtnVerwendungHinzufuegen = new JButton();
		BtnVerwendungEntfernen = getButton("<2");
		if(BtnVerwendungEntfernen==null) BtnVerwendungEntfernen = new JButton();
		BtnKopieren = getButton("Kopieren");
		if(BtnKopieren==null) BtnKopieren = new JButton();
		BtnBeenden = getButton("Beenden");
		if(BtnBeenden==null) BtnBeenden = new JButton();
		BtnExport = getButton("Export");
		if(BtnExport==null) BtnExport = new JButton();
                /*BtnNew=getButton("Neu");
                if(BtnNew==null) BtnNew=new JButton();
                BtnDel=getButton("Loeschen");
                if(BtnDel==null) BtnDel=new JButton();
                BtnEdt=getButton("Edit");
                if(BtnEdt==null) BtnEdt=new JButton();*/


		BtnOkKopieren = g.getButton("Ok");
		if(BtnOkKopieren==null) BtnOkKopieren = new JButton();
		BtnAbbruchKopieren = g.getButton("Abbruch");
		if(BtnAbbruchKopieren==null) BtnAbbruchKopieren = new JButton();
		BtnExportOk = g.getButton("Ok");
		if(BtnExportOk==null) BtnExportOk = new JButton();
		BtnExportAbbruch = g.getButton("Abbruch");
		if(BtnExportAbbruch==null) BtnExportAbbruch = new JButton();

		JPanel Pnl_Outliner_Tabellen1 = getFrei("Outliner Tabellen1");
		JPanel Pnl_Outliner_Tabellen2 = getFrei("Outliner Tabellen2");
		JPanel Pnl_Outliner_Lizenz = getFrei("Outliner Lizenz");
		JPanel Pnl_Outliner_Verwendung = getFrei("Outliner Verwendung");
		JPanel Pnl_Spinbox = getFrei("Spinbox");
		JPanel Pnl_Mandant = getFrei("Mandant");

		if(Pnl_Outliner_Tabellen1!=null)
		{
			Pnl_Outliner_Tabellen1.setLayout(new BorderLayout(2,2));
			Pnl_Outliner_Tabellen1.add("Center",OutTabellen1);
		}
		if(Pnl_Outliner_Tabellen2!=null)
		{
			Pnl_Outliner_Tabellen2.setLayout(new BorderLayout(2,2));
			Pnl_Outliner_Tabellen2.add("Center",OutTabellen2);
		}
		if(Pnl_Outliner_Lizenz!=null)
		{
			Pnl_Outliner_Lizenz.setLayout(new BorderLayout(2,2));
			Pnl_Outliner_Lizenz.add("Center",OutLizenz);
		}
		if(Pnl_Outliner_Verwendung!=null)
		{
			Pnl_Outliner_Verwendung.setLayout(new BorderLayout(2,2));
			Pnl_Outliner_Verwendung.add("Center",OutVerwendung);
		}
		if(Pnl_Spinbox!=null)
		{
			Pnl_Spinbox.setLayout(new BorderLayout(2,2));
			Pnl_Spinbox.add("Center",SpnAnzahl);
		}
		if(Pnl_Mandant!=null)
		{
			Pnl_Mandant.setLayout(new BorderLayout(2,2));
			Pnl_Mandant.add("Center",CboMandant);
		}

		FrmKopieren = new JDialog((Frame)thisFrame,true);
		FrmKopieren.setTitle(g.getBegriffS("Show","Kopieren"));
		FrmKopieren.getContentPane().setLayout(new BorderLayout(2,2));
		FrmKopieren.getContentPane().add("West",new JLabel(g.getBegriffS("Show","Nach")+":"));
		FrmKopieren.getContentPane().add("Center",CboMandantKopieren);
		JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
		Pnl.add(BtnOkKopieren);
		Pnl.add(BtnAbbruchKopieren);
		FrmKopieren.getContentPane().add("South",Pnl);
		FrmKopieren.setSize(260,85);

		FrmExport = new JDialog((Frame)thisFrame,true);
		FrmExport.setTitle(g.getBegriffS("Show","Export"));
		FrmExport.getContentPane().setLayout(new BorderLayout(2,2));
		Pnl = new JPanel(new GridLayout(1,0,2,2));
		Pnl.add(BtnExportOk);
		Pnl.add(BtnExportAbbruch);
		FrmExport.getContentPane().add("South",Pnl);
		FrmExport.getContentPane().add("North",FEExport);
		FrmExport.setSize(260,85);

		OutTabellen1.setVisible(g.Def());
		OutTabellen2.setVisible(g.Def());
		//OutLizenz.setVisible(g.Def());
		//OutVerwendung.setVisible(g.Def());
		BtnExport.setEnabled(g.Def());
	}

	private void Laden()
	{
		((JCOutlinerFolderNode)OutLizenz.getRootNode()).removeChildren();
		((JCOutlinerFolderNode)OutTabellen1.getRootNode()).removeChildren();
		((JCOutlinerFolderNode)OutVerwendung.getRootNode()).removeChildren();
		((JCOutlinerFolderNode)OutTabellen2.getRootNode()).removeChildren();

		String[] sTabellen = new String[] {"STAMMTYP","ROLLE"};
		SQL Qry = new SQL(g);

		for(int i=0;i<sTabellen.length;i++)
		{
			int iPos=g.TabTabellenname.getPos("Kennung",sTabellen[i]);
			JCOutlinerNodeStyle Sty=g.getTabStyle(g.TabTabellenname.getI(iPos,"Aic"));

			if(Qry.open("SELECT AIC_"+sTabellen[i]+" AIC "+g.AU_Bezeichnung2(sTabellen[i],sTabellen[i]+".AIC_"+sTabellen[i],"Kennung")+" Bezeichnung, Kennung, (SELECT Anzahl FROM Lizenz WHERE Lizenz.AIC_Tabellenname="+g.TabTabellenname.getI(iPos,"AIC")+
				" AND AIC_Fremd="+sTabellen[i]+".AIC_"+sTabellen[i]+" AND AIC_Mandant="+CboMandant.getSelectedAIC()+") Anzahl FROM "+sTabellen[i]+
				(sTabellen[i].equals("BEGRIFFGRUPPE")?" WHERE Code is null":sTabellen[i].equals("STAMMTYP")?" WHERE "+g.bit("SttBits",g.cstLizenz):"")+" ORDER BY BEZEICHNUNG"))
			{
				Vector<Comparable> VecVisible = new Vector<Comparable>();
				Vector<Integer> VecInvisible = new Vector<Integer>();

				VecVisible.addElement(g.TabTabellenname.getS(iPos,"Bezeichnung"));
				VecInvisible.addElement(new Integer(g.TabTabellenname.getI(iPos,"AIC")));

				JCOutlinerFolderNode NodeParentTabellen1 = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutTabellen1.getRootNode());
				NodeParentTabellen1.setUserData(VecInvisible);
				NodeParentTabellen1.setStyle(Sty);
				//NodeParentTabellen1.setState(BWTEnum.FOLDER_CLOSED);

				JCOutlinerFolderNode NodeParentLizenz = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutLizenz.getRootNode());
				NodeParentLizenz.setUserData(VecInvisible);
				NodeParentLizenz.setStyle(Sty);

				while(!Qry.eof())
				{
					VecVisible = new Vector<Comparable>();
					VecInvisible = new Vector<Integer>();

					VecVisible.addElement(Qry.getS("Bezeichnung"));
					if(!Qry.isNull("Anzahl"))
						VecVisible.addElement(new Integer(Qry.getI("Anzahl")));
					VecInvisible.addElement(new Integer(Qry.getI("AIC")));

					JCOutlinerNode Node = new JCOutlinerNode(VecVisible,Qry.isNull("Anzahl")?NodeParentTabellen1:NodeParentLizenz);
					Node.setUserData(VecInvisible);
					Node.setStyle(Sty);

					Qry.moveNext();
				}

				Qry.close();
			}
			else
				Static.printError("Lizenz.Laden(): Fehler beim Lesen ("+sTabellen+")!!!");
		}

		//sTabellen = new String[] {"Applikation","Frame","Button","Modell","Druck"};
		sTabellen = new String[] {"Modul"};
		int iPosT=g.TabTabellenname.getPos("Kennung","BEGRIFF");
		JCOutlinerNodeStyle Sty=g.getStyle(g.getImage("Begriffgruppe","Modul"));

		for(int i=0;i<sTabellen.length;i++)
		{
			int iPosBG=g.TabBegriffgruppen.getPos("Kennung",sTabellen[i]);

			if(Qry.open("SELECT AIC_Begriff AIC,DefBezeichnung "+/*g.AU_Bezeichnung2("Begriff","Begriff.AIC_Begriff","Begriff.Kennung")+*/" Bezeichnung, Begriff.Kennung,Begriff.prog,"+
                                    " (SELECT count(*) Anzahl FROM Lizenz WHERE Lizenz.AIC_Tabellenname="+g.TabTabellenname.getI(iPosT,"AIC")+" AND AIC_Fremd=Begriff.AIC_Begriff AND AIC_Mandant="+CboMandant.getSelectedAIC()+
                                    ") Anzahl FROM Begriff"+g.join("Begriffgruppe","Begriff")+" WHERE Begriffgruppe.Kennung='"+sTabellen[i]+"'"+(sTabellen[i].equals("Frame")?" AND AIC_Stammtyp IS NULL":"")+" ORDER BY BEZEICHNUNG"))
			{
				Vector<String> VecVisible = new Vector<String>();
				Vector<Integer> VecInvisible = new Vector<Integer>();

				VecVisible.addElement(g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung"));
				VecInvisible.addElement(new Integer(g.TabBegriffgruppen.getI(iPosBG,"AIC")));

				JCOutlinerFolderNode NodeParentTabellen2 = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutTabellen2.getRootNode());
				NodeParentTabellen2.setUserData(VecInvisible);
				//NodeParentTabellen2.setState(BWTEnum.FOLDER_CLOSED);
				NodeParentTabellen2.setStyle(Sty);
				JCOutlinerFolderNode NodeParentVerwendung = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutVerwendung.getRootNode());
				NodeParentVerwendung.setUserData(VecInvisible);
				NodeParentVerwendung.setStyle(Sty);

				while(!Qry.eof())
				{
					VecVisible = new Vector<String>();
					VecInvisible = new Vector<Integer>();

					VecVisible.addElement(Qry.getS("Bezeichnung"));
                                        VecVisible.addElement(Qry.getS("Kennung"));
                                        VecVisible.addElement(g.TabCodes.getBezeichnungS(Qry.getI("Prog")));
					VecInvisible.addElement(new Integer(Qry.getI("AIC")));

					JCOutlinerNode Node = new JCOutlinerNode(VecVisible,Qry.getI("Anzahl")==0?NodeParentTabellen2:NodeParentVerwendung);
					Node.setUserData(VecInvisible);
					Node.setStyle(Sty);
					Qry.moveNext();
				}

				Qry.close();
			}
			else
				Static.printError("Lizenz.Laden(): Fehler beim Lesen ("+sTabellen+")!!!");
		}

		Qry.free();

		iAIC_Mandant=CboMandant.getSelectedAIC();
		bSpeichern=false;

		OutLizenz.sortByColumn(0,Sort.sortMethod);
		OutTabellen1.sortByColumn(0,Sort.sortMethod);
		OutVerwendung.sortByColumn(0,Sort.sortMethod);
		OutTabellen2.sortByColumn(0,Sort.sortMethod);

		OutLizenz.folderChanged(OutLizenz.getRootNode());
		OutTabellen1.folderChanged(OutLizenz.getRootNode());
		OutVerwendung.folderChanged(OutLizenz.getRootNode());
		OutTabellen2.folderChanged(OutLizenz.getRootNode());

	}

	private void selectNodes(boolean bLizenz)
	{
		//bSelectNodes=true;
		if(bLizenz)
			OutTabellen1.selectNode(OutTabellen1.getRootNode(),null);
		else
			OutLizenz.selectNode(OutLizenz.getRootNode(),null);
		//bSelectNodes=false;
	}

	@SuppressWarnings("unchecked")
	private void LizenzHinzufuegen()
	{
		JCOutlinerNode[] NodesTabelle1 = OutTabellen1.getSelectedNodes();
		JCOutlinerFolderNode NodeParent=null;

		if(NodesTabelle1!=null && NodesTabelle1.length>0 && NodesTabelle1[0].getLevel()==2)
		{
			int iPos=0;
			Vector VecParents = ((JCOutlinerFolderNode)OutLizenz.getRootNode()).getChildren();
			int iAIC_Tabellen1_Parent = ((Integer)((Vector)NodesTabelle1[0].getParent().getUserData()).elementAt(0)).intValue();
			for(;iPos<VecParents.size() && ((Integer)((Vector)((JCOutlinerFolderNode)VecParents.elementAt(iPos)).getUserData()).elementAt(0)).intValue()!=iAIC_Tabellen1_Parent;iPos++);

			if(iPos<VecParents.size())
				NodeParent=(JCOutlinerFolderNode)VecParents.elementAt(iPos);
		}

		for(int i=0;NodesTabelle1!=null && NodeParent!=null && i<NodesTabelle1.length;i++)
		{
			Vector<Object> VecVisible = new Vector<Object>((Vector)NodesTabelle1[i].getLabel());
			Vector VecInvisible = new Vector((Vector)NodesTabelle1[i].getUserData());

			VecVisible.addElement(new Integer(SpnAnzahl.getIntValue()));

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
			Node.setUserData(VecInvisible);

			NodesTabelle1[i].getParent().removeChild(NodesTabelle1[i]);
		}

		//OutLizenz.sortByColumn(0,Sort.sortMethod,JCqsort.ASCENDING);
		OutTabellen1.folderChanged(OutTabellen1.getRootNode());
		OutLizenz.folderChanged(NodeParent);

		bSpeichern=true;
	}

	@SuppressWarnings("unchecked")
	private void LizenzEntfernen()
	{
		JCOutlinerNode[] NodesLizenz = OutLizenz.getSelectedNodes();
		JCOutlinerFolderNode NodeParent=null;

		if(NodesLizenz.length>0 && NodesLizenz[0].getLevel()==2)
		{
			int iPos=0;
			Vector VecParents = ((JCOutlinerFolderNode)OutTabellen1.getRootNode()).getChildren();
			int iAIC_Lizenz_Parent = ((Integer)((Vector)NodesLizenz[0].getParent().getUserData()).elementAt(0)).intValue();
			for(;iPos<VecParents.size() && ((Integer)((Vector)((JCOutlinerFolderNode)VecParents.elementAt(iPos)).getUserData()).elementAt(0)).intValue()!=iAIC_Lizenz_Parent;iPos++);

			if(iPos<VecParents.size())
				NodeParent=(JCOutlinerFolderNode)VecParents.elementAt(iPos);
		}

		for(int i=0;NodeParent!=null && i<NodesLizenz.length;i++)
		{
			Vector<Object> VecVisible = new Vector<Object>();
			Vector VecInvisible = new Vector((Vector)NodesLizenz[i].getUserData());

			VecVisible.addElement(((Vector)NodesLizenz[i].getLabel()).elementAt(0));

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
			Node.setUserData(VecInvisible);

			NodesLizenz[i].getParent().removeChild(NodesLizenz[i]);
		}

		OutTabellen1.sortByColumn(0,Sort.sortMethod);
		OutTabellen1.folderChanged(NodeParent);
		OutLizenz.folderChanged(OutLizenz.getRootNode());

		bSpeichern=true;
	}

	@SuppressWarnings("unchecked")
	public void VerwendungHinzufuegen()
	{
		JCOutlinerNode[] NodesTabelle2 = OutTabellen2.getSelectedNodes();
		JCOutlinerFolderNode NodeParent=null;

		if(NodesTabelle2.length>0 && NodesTabelle2[0].getLevel()==2)
		{
			int iPos=0;
			Vector VecParents = ((JCOutlinerFolderNode)OutVerwendung.getRootNode()).getChildren();
			int iAIC_Tabellen2_Parent = ((Integer)((Vector)NodesTabelle2[0].getParent().getUserData()).elementAt(0)).intValue();
			for(;iPos<VecParents.size() && ((Integer)((Vector)((JCOutlinerFolderNode)VecParents.elementAt(iPos)).getUserData()).elementAt(0)).intValue()!=iAIC_Tabellen2_Parent;iPos++);

			if(iPos<VecParents.size())
				NodeParent=(JCOutlinerFolderNode)VecParents.elementAt(iPos);
		}

		for(int i=0;NodeParent!=null && i<NodesTabelle2.length;i++)
		{
			Vector VecVisible = new Vector((Vector)NodesTabelle2[i].getLabel());
			Vector VecInvisible = new Vector((Vector)NodesTabelle2[i].getUserData());

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
			Node.setUserData(VecInvisible);

			NodesTabelle2[i].getParent().removeChild(NodesTabelle2[i]);
		}

		OutVerwendung.sortByColumn(0,Sort.sortMethod);
		OutTabellen2.folderChanged(OutTabellen2.getRootNode());
		OutVerwendung.folderChanged(NodeParent);

		bSpeichern=true;
	}

	@SuppressWarnings("unchecked")
	public void VerwendungEntfernen()
	{
		JCOutlinerNode[] NodesVerwendung = OutVerwendung.getSelectedNodes();
		JCOutlinerFolderNode NodeParent=null;

		if(NodesVerwendung.length>0 && NodesVerwendung[0].getLevel()==2)
		{
			int iPos=0;
			Vector VecParents = ((JCOutlinerFolderNode)OutTabellen2.getRootNode()).getChildren();
			int iAIC_Verwendung_Parent = ((Integer)((Vector)NodesVerwendung[0].getParent().getUserData()).elementAt(0)).intValue();
			for(;iPos<VecParents.size() && ((Integer)((Vector)((JCOutlinerFolderNode)VecParents.elementAt(iPos)).getUserData()).elementAt(0)).intValue()!=iAIC_Verwendung_Parent;iPos++);

			if(iPos<VecParents.size())
				NodeParent=(JCOutlinerFolderNode)VecParents.elementAt(iPos);
		}

		for(int i=0;NodeParent!=null && i<NodesVerwendung.length;i++)
		{
			Vector VecVisible = new Vector((Vector)NodesVerwendung[i].getLabel());
			Vector VecInvisible = new Vector((Vector)NodesVerwendung[i].getUserData());

			JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeParent);
			Node.setUserData(VecInvisible);

			NodesVerwendung[i].getParent().removeChild(NodesVerwendung[i]);
		}

		OutTabellen2.sortByColumn(0,Sort.sortMethod);
		OutTabellen2.folderChanged(NodeParent);
		OutVerwendung.folderChanged(OutVerwendung.getRootNode());

		bSpeichern=true;
	}

	private void Save()
	{
		SQL Qry = new SQL(g);
		Qry.exec("DELETE FROM Lizenz WHERE AIC_Mandant="+iAIC_Mandant);

		Vector VecParents = ((JCOutlinerFolderNode)OutLizenz.getRootNode()).getChildren();

		for(int i=0;VecParents!=null && i<VecParents.size();i++)
		{
			JCOutlinerFolderNode NodeParent = (JCOutlinerFolderNode)VecParents.elementAt(i);
			Vector VecNodes = NodeParent.getChildren();

			for(int j=0;VecNodes!=null && j<VecNodes.size();j++)
			{
				JCOutlinerNode Node = (JCOutlinerNode)VecNodes.elementAt(j);
				Qry.add("AIC_Mandant",iAIC_Mandant);
				Qry.add("AIC_Tabellenname",(Integer)((Vector)NodeParent.getUserData()).elementAt(0));
				Qry.add("AIC_Fremd",(Integer)((Vector)Node.getUserData()).elementAt(0));
				Qry.add("Anzahl",(Integer)((Vector)Node.getLabel()).elementAt(1));
				Qry.insert("Lizenz",false);
			}
		}

		int iPosT=g.TabTabellenname.getPos("Kennung","BEGRIFF");
		VecParents = ((JCOutlinerFolderNode)OutVerwendung.getRootNode()).getChildren();

		for(int i=0;VecParents!=null && i<VecParents.size();i++)
		{
			JCOutlinerFolderNode NodeParent = (JCOutlinerFolderNode)VecParents.elementAt(i);
			Vector VecNodes = NodeParent.getChildren();

			for(int j=0;VecNodes!=null && j<VecNodes.size();j++)
			{
				JCOutlinerNode Node = (JCOutlinerNode)VecNodes.elementAt(j);
				Qry.add("AIC_Mandant",iAIC_Mandant);
				Qry.add("AIC_Tabellenname",g.TabTabellenname.getI(iPosT,"AIC"));
				Qry.add("AIC_Fremd",(Integer)((Vector)Node.getUserData()).elementAt(0));
				Qry.add("Anzahl","");
				Qry.insert("Lizenz",false);
			}
		}

		Qry.free();

		bSpeichern=false;
	}

	@SuppressWarnings("unchecked")
	private void ChangeAnzahl()
	{
		JCOutlinerNode[] NodeSelected = OutLizenz.getSelectedNodes();

		for(int i=0;i<NodeSelected.length;i++)
		{
			if(NodeSelected[i].getLevel()==2)
			{
				((Vector)NodeSelected[i].getLabel()).setElementAt(new Integer(SpnAnzahl.getIntValue()),1);
				bSpeichern=true;
			}
		}

		OutLizenz.folderChanged(OutLizenz.getRootNode());
	}

	private void EnableButtons()
	{
		JCOutlinerNode[] Nodes = OutVerwendung.getSelectedNodes();
		BtnVerwendungEntfernen.setEnabled(Nodes!=null && Nodes.length>0 && Nodes[0].getLevel()==2 && g.Def());

		Nodes = OutTabellen2.getSelectedNodes();
		BtnVerwendungHinzufuegen.setEnabled(Nodes!=null && Nodes.length>0 && Nodes[0].getLevel()==2 && g.Def());

		Nodes = OutLizenz.getSelectedNodes();
		BtnLizenzEntfernen.setEnabled(Nodes!=null && Nodes.length>0 && Nodes[0].getLevel()==2 && g.Def());

		Nodes = OutTabellen1.getSelectedNodes();
		BtnLizenzHinzufuegen.setEnabled(Nodes!=null && Nodes.length>0 && Nodes[0].getLevel()==2 && g.Def());

		BtnRuecksetzen.setEnabled(bSpeichern);
		BtnSpeichern.setEnabled(bSpeichern && g.Def());
		BtnKopieren.setEnabled(!bSpeichern && g.Def());
		int iM=CboMandant.getSelectedAIC();
		BtnDelMandant.setEnabled(iM>1 && iM!=g.getMandant());
		BtnEdtMandant.setEnabled(iM>1 || g.Def());
	}

	private void Exportieren(String sFilename,int iAIC_Mandant)
	{
		SQL Qry = new SQL(g);
		AUZip Zip = new AUZip(sFilename,false);

		if(Qry.open("select kennung,Verbindungen from mandant where aic_mandant="+iAIC_Mandant) && !Qry.eof())
		{
			Zip.putNextEntry(new ZipEntry("Version.up"));
			Zip.SaveInteger(new Integer(Version.getVer()));
			Zip.SaveInteger(new Integer(cstVersion));
			Zip.closeEntry();

			Zip.putNextEntry(new ZipEntry("Mandant.liz"));
			Zip.SaveString2(Qry.getS("kennung"));
                        Zip.SaveInteger(new Integer(Qry.getI("Verbindungen")));
			Zip.closeEntry();
                        int iBG=g.TabBegriffgruppen.getAic("Modul");
			if(Qry.open("select begriff.kennung begriff,code.kennung Programm from lizenz join begriff on aic_begriff=aic_fremd and aic_begriffgruppe="+iBG+
                                    " join code on begriff.prog=code.aic_code where aic_tabellenname="+g.iTabBegriff+" and lizenz.aic_mandant="+iAIC_Mandant) && !Qry.eof())
			{
				Zip.putNextEntry(new ZipEntry("Begriff.liz"));
				for(;!Qry.eof();Qry.moveNext())
				{
					Zip.SaveString2(Qry.getS("begriff"));
                                        Zip.SaveString2(Qry.getS("Programm"));
				}
				Zip.closeEntry();
			}
			if(Qry.open("select stammtyp.kennung,anzahl from lizenz"+g.join("tabellenname","lizenz")+",stammtyp where aic_stammtyp=aic_fremd and tabellenname.kennung='Stammtyp' and lizenz.aic_mandant="+iAIC_Mandant) && !Qry.eof())
			{
				//g.printInfo("Anfang");
				Zip.putNextEntry(new ZipEntry("Stammtyp.liz"));
				for(;!Qry.eof();Qry.moveNext())
				{
					Zip.SaveString2(Qry.getS("kennung"));
					Zip.SaveInteger(Qry.getInt("anzahl"));
					//g.printInfo(Qry.getS("kennung")+" "+Qry.getI("anzahl"));
				}
				Zip.closeEntry();
				//g.printInfo("Ende");
			}
                        if(Qry.open("select Rolle.kennung,anzahl from lizenz"+g.join("tabellenname","lizenz")+",rolle where aic_rolle=aic_fremd and tabellenname.kennung='ROLLE' and lizenz.aic_mandant="+iAIC_Mandant) && !Qry.eof())
                        {
                                //g.printInfo("Anfang");
                                Zip.putNextEntry(new ZipEntry("Rolle.liz"));
                                for(;!Qry.eof();Qry.moveNext())
                                {
                                        Zip.SaveString2(Qry.getS("kennung"));
                                        Zip.SaveInteger(Qry.getInt("anzahl"));
                                        //g.printInfo(Qry.getS("kennung")+" "+Qry.getI("anzahl"));
                                }
                                Zip.closeEntry();
                                //g.printInfo("Ende");
                        }
		}
		Zip.close();
                Qry.free();
	}

// add your data members here
private Global g;
private static Lizenz Self=null;

private JButton BtnLizenzHinzufuegen;
private JButton BtnLizenzEntfernen;
private JButton BtnVerwendungHinzufuegen;
private JButton BtnVerwendungEntfernen;
private JButton BtnRuecksetzen;
private JButton BtnSpeichern;
private JButton BtnBeenden;
private JButton BtnKopieren;
private JButton BtnExport;
private JButton BtnEdtMandant;
private JButton BtnDelMandant;
//private JButton BtnNew;
//private JButton BtnDel;
//private JButton BtnEdt;
private JButton BtnEditOk;
private JButton BtnEditAbbruch;

private AUOutliner OutLizenz = new AUOutliner(new JCOutlinerFolderNode("Root Lizenz"));
private AUOutliner OutVerwendung = new AUOutliner(new JCOutlinerFolderNode("Root Verwendung"));
private AUOutliner OutTabellen1 = new AUOutliner(new JCOutlinerFolderNode("Root Tabellen1"));
private AUOutliner OutTabellen2 = new AUOutliner(new JCOutlinerFolderNode("Root Tabellen2"));

private SpinBox SpnAnzahl = new SpinBox();
private ComboSort CboMandant;

//private boolean bSelectNodes=false;
private boolean bSpeichern=false;

private int iAIC_Mandant=0;

private JDialog FrmKopieren;
private ComboSort CboMandantKopieren;
private JButton BtnOkKopieren;
private JButton BtnAbbruchKopieren;

private JDialog FrmExport;
private JButton BtnExportOk;
private JButton BtnExportAbbruch;
private FileEingabe FEExport;
private JDialog DlgEdit;
private Text TxtBezeichnung=new Text("",80);
private Text TxtKennung=new Text("",40,Text.KENNUNG);
private AUPasswort TxtPW1=new AUPasswort();
private AUPasswort TxtPW2=new AUPasswort();
private ComboSort CboForm;
private ComboSort CboHMandant;
private SpinBox EdtVersuche = new SpinBox();
private SpinBox EdtConnects = new SpinBox();
private AUCheckBox CbxTIMP;
private AUCheckBox CbxTod;
boolean bKopieren=false;

public static int cstVersion=4;
}

