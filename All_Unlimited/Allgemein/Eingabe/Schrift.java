/*
    All_Unlimited-Allgemein-Eingabe-Schrift.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Anzeige.Combo;
import javax.swing.JSplitPane;

public class Schrift extends javax.swing.JPanel
{
    /**
	 *
	 */
	private static final long serialVersionUID = 5200507377888639066L;	// add your data members here
	private Global g;
	private Schrift FomThis;
	private ComboSort Cbo;
	private JButton Btn;
        private static JDialog FomAuswahl=null;
	//private static Window FomParent=null;
	private static Tabellenspeicher TabSchrift=null;
        private static Tabellenspeicher TabAuswahl=null;

	private static AUOutliner Gid;
	private static Text EdtBezeichnung;
	private static Text EdtKennung;
	private Text EdtSA_Bezeichnung;
	private Text EdtSA_Kennung;
	private static ComboSort CboSchriftart;
	private static ComboSort CboFarbe;
        private static ComboSort CboFarbeHG;
	private static SpinBox EdtSize;
	private static AUCheckBox CbxFett;
	private static AUCheckBox CbxKursiv;
	private static AUTextArea Memo;
	private int iAic=0;
        private static int iAnz=0;
        private int iMom;
        private static int iMom2;
        private static boolean bSave=false;
        //private int iVorschau=0;

        public Schrift(Global rg)
	{
		g = rg;
		FomThis = this;
		Build();
	}

	public static void free()
	{
		if (FomAuswahl!=null)
		{
			//g.testInfo("Schrift.free");
			FomAuswahl.dispose();
			FomAuswahl=null;
                        TabAuswahl=null;
			TabSchrift=null;
			Gid=null;
		}
	}

	private void Build()
	{
          //FomParent=rFomParent;
          iAnz++;
          iMom=iAnz;
          if (TabAuswahl==null)
              TabAuswahl = new Tabellenspeicher(g,new String[] {"Nr","Cbo"});
		//TabSchrift.showGrid();
          TabAuswahl.addInhalt("Nr",iMom);
		Cbo = new ComboSort(g);
		Gid_refresh(0,false);
        TabAuswahl.addInhalt("Cbo",Cbo);
		setLayout(new BorderLayout(2,2));
		add("Center",Cbo);

		if (g.Def())
		{
			Btn = g.getButton("...");
			Btn.setMargin(g.inset);
			add("East",Btn);
			Btn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
                                  //g.progInfo("Btn : Mom="+iMom);
                                  iMom2=iMom;
                                  bSave=false;
					if(FomAuswahl==null)
                                          createFomAuswahl();
					if(TabSchrift.posInhalt("Aic",Cbo.getSelectedAIC()))
                                          Gid.selectNode((JCOutlinerNode)TabSchrift.getInhalt("Knoten"),null);
                                        FomAuswahl.setVisible(true);
				}
			});
		}
	}
	
	private void Gid_refresh(int iAic,boolean bRefresh)
	{
		if (bRefresh || TabSchrift==null)
			TabSchrift=new Tabellenspeicher(g,"select aic_schrift as aic,null Knoten,Schrift.kennung,"+g.SIZE()+",bold,italic,aic_Farbe,far_aic_farbe,Schriftart.kennung as Art"+g.AU_Bezeichnung("Schrift")+" from Schrift"+g.join("schriftart","Schrift")+(g.Def()?" ":" where Schrift.kennung not like '.%'"),true);		
		Cbo.fillCbo(TabSchrift,true);
		if (bRefresh)
		{
			JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Gid.getRootNode();
			NodeRoot.removeChildren();
			g.fixtestError("Schrift: Gid_refresh");
			Vector VecItems = Cbo.getItems();
			for(int i=0;i<VecItems.size();i++)
			{
				Combo Coo=(Combo)VecItems.elementAt(i);
	                        if (TabSchrift.posInhalt("Aic",Coo.getAic()))
	                        {
	                          Vector<Object> Vec = new Vector<Object>();
	                          Vec.addElement(Coo);
	                          Vec.addElement(TabSchrift.getS("Kennung"));
	                          Vec.addElement(TabSchrift.getI("Aic"));
	                          JCOutlinerNode Nod=new JCOutlinerNode(Vec, NodeRoot);
	                          Nod.setUserData(TabSchrift.getI("Aic"));
	                          TabSchrift.setInhalt("Knoten", Nod);
	                        }
			}
			Gid.folderChanged(NodeRoot);
			if (iAic>0)
				g.select(iAic, Gid);
		}
	}

	private void createFomAuswahl()
	{
		Gid = new AUOutliner(new JCOutlinerFolderNode("Root"));
		Gid.setRootVisible(false);
        String[] s = new String[] {g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Aic")};
        Gid.setColumnButtons(s);
        Gid.setNumColumns(s.length);
		Gid_refresh(0,true);
		//FomAuswahl=FomParent==null || FomParent instanceof JFrame ? new JDialog((JFrame)FomParent,"Schrift-Auswahl",true):
                //    new JDialog((JDialog)FomParent,"Schrift-Auswahl",true);
                FomAuswahl=new JDialog((JFrame)null,"Schrift-Auswahl",true);
		JPanel PnlCenter=new JPanel(new BorderLayout());
		JPanel PnlEingabe=new JPanel(new BorderLayout());
		JPanel PnlText=new JPanel(new GridLayout(0,1));
		JPanel PnlWert=new JPanel(new GridLayout(0,1));

		EdtBezeichnung=new Text("",80);
		EdtKennung=new Text("",40,Text.KENNUNG);
		CboSchriftart=new ComboSort(g);
		CboSchriftart.fillSchrift();
		CboFarbe=new ComboSort(g);
		CboFarbe.fillFarbe(true);
        CboFarbeHG=new ComboSort(g);
		CboFarbeHG.fillFarbe(true);
		EdtSize = new SpinBox();
		EdtSize.setIntValue(8);
		EdtSize.setMinimum(4);
		CbxFett=new AUCheckBox();
		CbxKursiv=new AUCheckBox();
		Memo=new AUTextArea();
		Memo.setText("THE FIVE BOXING WIZARDS JUMP QUICKLY.\n\nSphinx of black quartz judge my vow.");

		ItemListener ILiVorschau=new ItemListener()
                {
                  public void itemStateChanged(ItemEvent e)
                  {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                      Vorschau();
                    }
                  }
                };
                ActionListener ActVorschau=new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    Vorschau();
                  }
                };

                CboSchriftart.addItemListener(ILiVorschau);
                CboFarbe.addItemListener(ILiVorschau);
                CboFarbeHG.addItemListener(ILiVorschau);
                EdtSize.addChangeListener(new ChangeListener()
                {
                  public void stateChanged(ChangeEvent ae)
                  {
                    Vorschau();
                  }
                });
                CbxFett.addActionListener(ActVorschau);
                CbxKursiv.addActionListener(ActVorschau);


		Gid.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent ev)
			{
				if(ev.getStateChange()==ItemEvent.SELECTED)
				{
                                  Object Obj=((JCOutliner)ev.getSource()).getSelectedNode().getLabel();
                                  if (Obj instanceof Vector)
                                    Obj=((Vector)Obj).elementAt(0);
					if (Obj != null && Obj instanceof Combo && TabSchrift.posInhalt("Aic",((Combo)Obj).getAic()))
					{
						iAic=TabSchrift.getI("Aic");
						EdtBezeichnung.setText(TabSchrift.getS("Bezeichnung"));
						EdtKennung.setText(TabSchrift.getS("Kennung"));
						CboSchriftart.setSelectedKennung2(TabSchrift.getS("Art"));
						//g.progInfo("Schrift: soll="+TabSchrift.getS("Art")+", ist="+CboSchriftart.getSelectedKennung());
						EdtSize.setIntValue(TabSchrift.getI("Size"));
						CbxFett.setSelected(TabSchrift.getB("BOLD"));
						CbxKursiv.setSelected(TabSchrift.getB("ITALIC"));
						CboFarbe.setSelectedAIC(TabSchrift.getI("AIC_Farbe"));
                                                CboFarbeHG.setSelectedAIC(TabSchrift.getI("Far_AIC_Farbe"));
						Vorschau();
					}
				}
			}
		});

		PnlText.add(new JLabel(g.getBegriffS("Label","Bezeichnung:")));
		PnlWert.add(EdtBezeichnung);
		PnlText.add(new JLabel(g.getBegriffS("Label","Kennung:")));
		PnlWert.add(EdtKennung);
		PnlText.add(new JLabel(g.getBegriffS("Label","Schriftart:")));
		JPanel PnlSchriftart=new JPanel(new BorderLayout(2,0));
		PnlSchriftart.add("Center",CboSchriftart);
		JButton BtnSP=g.getButton("...");
		PnlSchriftart.add("East",BtnSP);
		PnlWert.add(PnlSchriftart);
		PnlText.add(new JLabel(g.getBegriffS("Label","Schriftgroesse:")));
		PnlWert.add(EdtSize);
		PnlText.add(new JLabel(g.getBegriffS("Label","Fett")));
		PnlWert.add(CbxFett);
		PnlText.add(new JLabel(g.getBegriffS("Label","Kursiv")));
		PnlWert.add(CbxKursiv);
		PnlText.add(new JLabel(g.getBegriffS("Label","Schriftfarbe:")));
		PnlWert.add(CboFarbe);
                PnlText.add(new JLabel(g.getBegriffS("Label","Hintergrundfarbe:")));
		PnlWert.add(CboFarbeHG);
		PnlEingabe.add("West",PnlText);
		PnlEingabe.add("Center",PnlWert);

		PnlCenter.add("North",PnlEingabe);
		PnlCenter.add("Center",Memo);

                ActionListener AL=new ActionListener()
                    {
                      public void actionPerformed(ActionEvent ev)
                      {
                        String s = ev.getActionCommand();
                        if (s.equals("new"))
                        {
                          iAic = 0;
                          EdtBezeichnung.setText("");
                          EdtKennung.setText("");
                        }
                        else if (s.equals("save"))
                          Save();
                        else if (s.equals("del"))
                          Del();
                        else if (s.equals("show"))
                          ShowSchrift();
                        else if (s.equals("Ok"))
                        {
                          ComboSort Cbo1=TabAuswahl.posInhalt("Nr",iMom2)?(ComboSort)TabAuswahl.getInhalt("Cbo"):null;
                          fillAll();
                          Cbo1.setSelectedAIC(iAic);
                          FomAuswahl.setVisible(false);
                        }
                        else if (s.equals("Abbruch"))
                        {
                          fillAll();
                          FomAuswahl.setVisible(false);
                        }
                        else if (s.equals("SP"))
                        {
                        	NeueSchriftart();
                        }
                      }
                    };
        g.BtnAdd(BtnSP,"SP",AL); // neue Schriftart
		JButton BtnNeu=g.getButton("Neu");
		g.BtnAdd(BtnNeu,"new",AL);
		JButton BtnSave=g.getButton("Speichern");
        g.BtnAdd(BtnSave,"save",AL);
		JButton BtnShow=g.getButton("show");
        g.BtnAdd(BtnShow,"show",AL);
		//BtnShow.addActionListener(ActVorschau);
		JButton BtnDel=g.getButton("Loeschen");
                g.BtnAdd(BtnDel,"del",AL);
		JButton BtnOk=g.getButton("Ok");
                g.BtnAdd(BtnOk,"Ok",AL);
		JButton BtnAbbruch=g.getButton("Abbruch");
                g.BtnAdd(BtnAbbruch,"Abbruch",AL);
		//JPanel PnlSouth=new JPanel(new GridLayout(2,3));
		JPanel PnlSouth=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
                PnlSouth.add(BtnShow);
		PnlSouth.add(BtnNeu);
                PnlSouth.add(BtnSave);
		PnlSouth.add(BtnDel);
		PnlSouth.add(BtnOk);
		PnlSouth.add(BtnAbbruch);

                FomAuswahl.getContentPane().setLayout(new BorderLayout(6,3));
                JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//,Gid,Pnl3);
                splitPane.setOneTouchExpandable(true);
                splitPane.setResizeWeight(0.65);
                splitPane.add(Gid);
                splitPane.add(PnlCenter);
		//FomAuswahl.getContentPane().add("West",Gid);
		FomAuswahl.getContentPane().add("South",PnlSouth);
		FomAuswahl.getContentPane().add("Center",splitPane);
        FomAuswahl.setSize(500,580);
		//FomAuswahl.pack();
		Static.centerComponent(FomAuswahl,FomThis);
	}
	
	private void NeueSchriftart()
	{
		//g.fixtestError("NeueSchriftart");
		JDialog FomSP=new JDialog(FomAuswahl,"neue Schriftart",true);
		JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
		JPanel PnlText=new JPanel(new GridLayout(0,1,2,2));
		JPanel PnlWert=new JPanel(new GridLayout(0,1,2,2));
		PnlText.add(new JLabel(g.getBegriffS("Label","Bezeichnung:")));
		if (EdtSA_Kennung==null)
		{
			EdtSA_Bezeichnung=new Text("",80);
			EdtSA_Kennung=new Text("",40,Text.KENNUNG);
		}
		else
		{
			EdtSA_Bezeichnung.setText("");
			EdtSA_Kennung.setText("");
		}
		PnlWert.add(EdtSA_Bezeichnung);
		PnlText.add(new JLabel(g.getBegriffS("Label","Kennung:")));
		PnlWert.add(EdtSA_Kennung);
		JButton BtnOk=g.getButton("Ok");
		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//g.fixtestError("Speichern noch nicht implementiert");
				String sKen=EdtSA_Kennung.getText();
				String sBez=EdtSA_Bezeichnung.getText();
				if (sKen.equals("") || sBez.equals("")) 
					Static.printError("Bezeichnung oder Kennung fehlt");
				else
				{
					int iArt=SQL.getInteger(g,"select aic_schriftart from schriftart where kennung='"+sKen+"'");
					g.fixtestError("neue Schriftart:"+sBez+"/"+sKen+"/"+iArt);
					SQL Qry=new SQL(g);
					if (iArt==0)
					{
						Qry.add("Kennung",sKen);
						iArt=Qry.insert("Schriftart",true);
						CboSchriftart.addItem(sBez, iArt, sKen);
						CboSchriftart.setSelectedKennung(sKen);
					}
					if (EdtSA_Bezeichnung.Modified())
					{
						int iTab=g.TabTabellenname.getAic("SCHRIFTART");
						g.setBezeichnung("",sBez,iTab,iArt,0);
					}
					FomSP.setVisible(false);
				}
			}
		});
		JButton BtnAbbruch=g.getButton("Abbruch");
		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FomSP.setVisible(false);
			}
		});
		PnlS.add(BtnOk);
		PnlS.add(BtnAbbruch);
		FomSP.getContentPane().add("South",PnlS);
		FomSP.getContentPane().add("West",PnlText);
		FomSP.getContentPane().add("Center",PnlWert);
		FomSP.pack();
		Static.centerComponent(FomSP,FomAuswahl);
		FomSP.setVisible(true);
	}

        private void ShowSchrift()
        {
          new Tabellenspeicher(g,"select "+g.AU_Bezeichnungo("Begriffgruppe","begriff")+" Gruppe,defbezeichnung,kennung from begriff where aic_schrift="+iAic,true).showGrid(EdtBezeichnung.getText(),FomAuswahl);
        }

        private void fillAll()
        {
          if (bSave)
          {
            g.progInfo("Schrift.fillAll");
            for (TabAuswahl.moveFirst();!TabAuswahl.eof();TabAuswahl.moveNext())
            {
              ComboSort Cbo2=(ComboSort)TabAuswahl.getInhalt("Cbo");
              int i=Cbo2.getSelectedAIC();
              Cbo2.fillDefTable("Schrift",true);
              Cbo2.setSelectedAIC(i);
            }
          }
        }

	private void Vorschau()
	{
          //iVorschau++;
          //g.progInfo("Vorschau "+iVorschau);
		String sFont=CboSchriftart.getSelectedKennung();
		g.fixtestError("Vorschau mit "+sFont);
		Font ft=null;
		if (sFont.startsWith("."))
		{
			sFont=sFont.substring(1);
			g.fixtestError("nehme "+sFont+" von TTF");
			try
			{
				ft=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/TTF/"+sFont+".ttf"));
				ft=ft.deriveFont((float)EdtSize.getIntValue());
				if (CbxFett.isSelected())
					ft=ft.deriveFont(Font.BOLD);
				if (CbxKursiv.isSelected())
					ft=ft.deriveFont(Font.ITALIC);
			}
			catch(Exception e)
			{
				Static.printError("cant't create "+sFont+"-Font:"+e);
			}
		}
		else
			ft=new Font(sFont,(CbxFett.isSelected()?Font.BOLD:0)+(CbxKursiv.isSelected()?Font.ITALIC:0),EdtSize.getIntValue());
		Memo.Edt.setFont(ft);
		Memo.Edt.setForeground(CboFarbe.isNull() ? Color.BLACK:new Color((int)CboFarbe.getSelectedFaktor()));
        Memo.Edt.setBackground(CboFarbeHG.isNull() ? Color.WHITE:new Color((int)CboFarbeHG.getSelectedFaktor()));
        if (Static.bDefShow)
          Gid.setFont(ft);
	}

        private void Del()
        {
            if (EdtKennung.getText().startsWith("."))
            {
              new Message(Message.WARNING_MESSAGE, g.getFomLeer(), g).showDialog("Systemschrift", new String[] {EdtKennung.getText()});
              return;
            }
            SQL Qry=new SQL(g);
            String s1=Qry.list("kennung","raster where aic_schrift="+iAic+" or  sch_aic_schrift="+iAic+
                                    " or  sch2_aic_schrift="+iAic+" or  sch3_aic_schrift="+iAic+" or  sch4_aic_schrift="+iAic+
                                    " or  sch5_aic_schrift="+iAic+" or  sch6_aic_schrift="+iAic);
            String s2=Qry.list("kennung","begriff where aic_schrift="+iAic);
            String s=(s1.equals(" ")?"":"\nRaster:"+s1)+(s2.equals(" ")?"":"\nBegriff:"+s2);
            if (!s.equals(""))
            {
              new Message(Message.WARNING_MESSAGE, g.getFomLeer(), g).showDialog("BereitsVerwendet", new String[] {s});
              return;
            }
            boolean bRefresh=false;
            if(new Message(Message.YES_NO_OPTION,g.getFomLeer(),g).showDialog("Loeschen",new String[] {"["+Gid.getSelectedNode().getLabel()+"]"})==Message.YES_OPTION)
                  if (g.exec("delete from Schrift where aic_schrift="+iAic)>-2)
                  {
                	g.SaveDefVerlauf(g.getBegriffAicS("Show", "Schrift"), "Schrift entfernt: "+EdtKennung.getText());
                    bSave=true;
//                    JCOutlinerNode Nod=Gid.getSelectedNode();
//                    Gid.selectNode(Gid.getNextNode(Nod),null);
//                    Nod.getParent().removeChild(Nod);
//                    Gid.folderChanged(Gid.getRootNode());
                    g.LoadSchrift(true,g.getFontFaktor());
                    bRefresh=true;
                  }
            Qry.free();
            if (bRefresh)
            	Gid_refresh(0,true);
        }

	private void Save()
	{
          if (EdtKennung.isNull())
          {
            new Message(Message.ERROR_MESSAGE, FomAuswahl,g,10).showDialog("KennungLeer");
            return;
          }
          else if(SQL.exists(g,"select aic_Schrift from Schrift where aic_Schrift<> "+iAic+" and Kennung='"+EdtKennung.getText()+"'"))
          {
            new Message(Message.WARNING_MESSAGE, FomAuswahl, g,10).showDialog("KennungVorhanden");
            return;
          }

          bSave=true;
		String sArt=CboSchriftart.getSelectedKennung();
		//Transact.fixInfo(sArt+":"+sArt.length());
		int iArt=SQL.getInteger(g,"select aic_schriftart from schriftart where kennung='"+sArt+"'");
		SQL Qry=new SQL(g);
		if (iArt==0)
		{
			Qry.add("Kennung",sArt);
			iArt=Qry.insert("Schriftart",true);
		}
		Qry.add("Aic_schriftart",iArt);
		Qry.add(g.SIZE(),EdtSize.getIntValue());
		Qry.add("Bold",CbxFett.isSelected());
		Qry.add("Italic",CbxKursiv.isSelected());
		Qry.add("Kennung",EdtKennung.getText());
		Qry.add0("Aic_Farbe",CboFarbe.getSelectedAIC());
                Qry.add0("Far_Aic_Farbe",CboFarbeHG.getSelectedAIC());
		boolean bNeu=iAic==0;
                if (bNeu)
		{
			iAic=Qry.insert("Schrift",true);
			g.SaveDefVerlauf(g.getBegriffAicS("Show", "Schrift"), "neue Schrift: "+EdtKennung.getText());
			Cbo.addItem(EdtBezeichnung.getText(),iAic,EdtKennung.getText(),0);
//			TabSchrift.newLine();
//			TabSchrift.setInhalt("Aic",iAic);
//                        Nod=new JCOutlinerNode(new Combo(EdtBezeichnung.getText(),iAic,EdtKennung.getText(),0),(JCOutlinerFolderNode)Gid.getRootNode());
//			TabSchrift.setInhalt("Knoten",Nod);

			//Gid.folderChanged(Gid.getRootNode());
		}
		else
		{
			Qry.update("Schrift",iAic);
			g.SaveDefVerlauf(g.getBegriffAicS("Show", "Schrift"), "Schrift geändert: "+EdtKennung.getText());	
		}
		//g.putTabSchrift(iAic,EdtKennung.getText(),EdtBezeichnung.getText(),new Font(sArt,(CbxFett.isSelected()?Font.BOLD:0)+(CbxKursiv.isSelected()?Font.ITALIC:0),EdtSize.getIntValue()),CboFarbe.getSelectedAIC(),CboFarbeHG.getSelectedAIC(),bNeu);
                g.LoadSchrift(true,g.getFontFaktor());
		//int iTab=0;
		//if (g.TabTabellenname.posInhalt("Kennung","SCHRIFT"))
		int iTab=g.TabTabellenname.getAic("SCHRIFT");
		//else
		//	Static.printError("Tabelle Begriff nicht gefunden");
		g.setBezeichnung("",EdtBezeichnung.getText(),iTab,iAic,0);

		TabSchrift.setInhalt("Bezeichnung",EdtBezeichnung.getText());
		TabSchrift.setInhalt("Kennung",EdtKennung.getText());
		TabSchrift.setInhalt("Size",EdtSize.getIntValue());
		TabSchrift.setInhalt("Bold",CbxFett.isSelected());
		TabSchrift.setInhalt("Italic",CbxKursiv.isSelected());
		TabSchrift.setInhalt("Art",sArt);
		TabSchrift.setInhalt("AIC_Farbe",CboFarbe.getSelectedAIC());
                TabSchrift.setInhalt("Far_AIC_Farbe",CboFarbeHG.getSelectedAIC());
                if (bNeu)
                {
                	g.LoadSchrift(true,g.getFontFaktor());
                	Gid_refresh(iAic,true);
                }
                  //Static.makeVisible(Gid,Nod);
		//TabSchrift.showGrid();
	}

	public void setSelectedAIC(int i)
	{
		Cbo.setSelectedAIC(i);
	}

	public int getSelectedAIC()
	{
		return Cbo.getSelectedAIC();
	}

	public String getSelectedBezeichnung()
	{
		return Cbo.getSelectedBezeichnung();
	}

	public String getBezeichnung(int i)
	{
		return Cbo.getBezeichnung(i);
	}

        public ComboSort getCboSchrift()
        {
          return Cbo;
        }

        public Font getSelectedFont()
        {
          return g.getSchrift(Cbo.getSelectedAIC(),Global.fontTitel);
        }
        
    public void setEnabled(boolean b)
    {
    	Cbo.setEnabled(b);
    	if (Btn != null)
    		Btn.setEnabled(b);
    }

	/*private void fillSchrift(ComboSort Cbo)
	{
		String[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int iLaenge=fonts.length;
		for(int i=0;i<iLaenge;i++)
			Cbo.addItem("",i,fonts[i],0.0);
	}*/
}

