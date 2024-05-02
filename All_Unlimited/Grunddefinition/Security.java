/*
    All_Unlimited-Grunddefinition-Security.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
//import All_Unlimited.Allgemein.Passwort;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
// import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Anzeige.Zahl1;

//import All_Unlimited.Hauptmaske.TCalc;
// import java.awt.Cursor;

import All_Unlimited.Allgemein.Parameter;

public class Security extends All_Unlimited.Allgemein.Formular
{
  private static Security Self;

  public static Security get(Global rg)
  {
    if (Self==null)
      Self=new Security(rg);
    else
      Self.show();
    return Self;
  }

  public static void free()
  {
     if (Self != null)
     {
       Self.g.winInfo("Security.free");
       Self.thisFrame.dispose();
       Self = null;
     }
  }

  private Security(Global glob)
	{
		super("Security",null,glob);
		g=glob;
		g.winInfo("Security.create");
		String[] s = new String[] {g.TabTabellenname.getBezeichnungS("Computer"),g.getShow("IP"),g.getShow("gesperrt"),g.getShow("eingeloggt"),g.getShow("Benutzer")
                    ,g.getShow("Anlage"),g.getShow("seit")/*,g.getBegriffS("Show","Anzahl")*/,g.getShow("zuletzt_Token"),g.getShow("Mandant")
                ,g.getShow("berechtigt"),g.getShow("HS"),g.getShow("Version"),g.getShow("Verlauf-Zeit"),g.getShow("Verlauf-Formular"),g.getShow("Verlauf-Stamm"),g.getShow("Verlauf-Dauer"),g.getShow("Web")};
		OutComputer.setColumnButtons(s);
		OutComputer.setNumColumns(s.length-(g.SuperUser()?0:6));
		OutComputer.setColumnLabelSortMethod(Sort.sortMethod);
		OutComputer.setBackground(Color.white);
		OutComputer.setRootVisible(false);
        OutComputer.setAllowMultipleSelections(true);

		Build();

//		BtnEntsperren.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ev)
//			{
//				Entsperren();
//			}
//		});

		BtnRaus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
        Tabellenspeicher Tab=Reinigung.Eingeloggt(g,0);
                          int pane = new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,(JFrame)thisFrame,g).showDialog("Update_einspielen",Tab);
                          if(pane == Message.YES_OPTION && Prozesse.checkDeaktiv(g,false,false,thisJFrame()))
                          {
                        	  g.SaveVerlauf("Raus");
                            g.bSchutz = true;
                            int iPU=g.getPU();//SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                            if (iPU>0)
                            {
                              g.exec("Update parameter set "+g.int1()+"=2 where aic_parameter=" + iPU);
                              g.fixInfo("Rauswurf aktiviert");
                              Global.iInfos=Global.iInfos|Global.ALLEIN;
                              g.exec("Update computer set cbits="+Global.iInfos+" where aic_computer="+g.getComputer());
                            }
                            else
                              Static.printError("Security.BtnRaus: Parameter Update nicht gefunden!");
                            g.sendWebDB("logoutDB",null,thisJFrame());
//                            AClient.send_AServer("deaktiv",g);
                            /*Parameter P = new Parameter(g, "Datenbank");
                            P.setParameter("Update", "", 2, 0, 0, 0, false, false);
                            P.free();*/

                          }
			}
		});
		
		BtnRausWeb.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ev)
                {
                  JCOutlinerNode Node = OutComputer.getSelectedNode();
                  String sCB=Sort.gets(Node.getLabel(),0)+" ("+Sort.gets(Node.getLabel(),3)+")";
                  Vector Vec=(Vector)Node.getLabel();
                  //g.fixtestError("Nod="+Vec);
                  if (Vec.size()>16 && Sort.gets(Vec, 16).equals("x"))
                  {
                	  int pane = new Message(Message.YES_NO_OPTION, (JFrame) thisFrame, g).showDialog("web_ausloggen",new String[] {sCB});
                      if (pane == Message.YES_OPTION)
                      {
                    	  //g.fixtestError("LogoutOne:"+g.getDB()+"/"+Sort.gets(Vec,3)+"/"+Node.getUserData());
                    	  g.sendLogout(Sort.gets(Vec,3),(int)Node.getUserData());
                    	  //g.fixtestError("vor sleep");
                    	  g.SaveVerlauf("RausWeb");
                    	  Static.sleep(50);
                    	  //g.fixtestError("vor refresh");
                    	  fillOutliner();
                      }
                	  return;
                  }
                }
       });

                BtnRaus1.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          JCOutlinerNode Node = OutComputer.getSelectedNode();
                          String sCB=Sort.gets(Node.getLabel(),0)+" ("+Sort.gets(Node.getLabel(),3)+")";
                          Vector Vec=(Vector)Node.getLabel();
                          boolean bWeb=Vec.size()>16 && Sort.gets(Vec, 16).equals("x");
                          String s="select "+(g.MS() || g.MySQL() ? "min(mom)":g.Oracle()?"(sysdate-mom)*24*3600":"seconds(mom,now())")+
                              " from logging where aic_logging="+Node.getUserData()+" and aus is null";
                          int iTime=g.MS() || g.MySQL() ? 0:SQL.getInteger(g,s,-1,null);
//                          g.fixtestError("Raus nach "+iTime+"s, Web="+bWeb);
                          if (iTime==-1)
                          {
                            Node.getParent().removeChild(Node);
                            OutComputer.folderChanged(OutComputer.getRootNode());
                            return;
                          }
                          if (g.MS() || g.MySQL())
                          {
                            java.sql.Timestamp ts=SQL.getTimestamp(g,s);
                            iTime=(int)(Sort.getf(SQL.getNow(g))-Sort.getf(ts));
                            g.progInfo("iTime=" + iTime+"/"+ts);
                          }
                          
                          if (iTime>0) // statt 240 || bWeb)
                          {
                            int pane = new Message(Message.YES_NO_OPTION, (JFrame) thisFrame, g).showDialog("Computer_ausloggen",new String[] {sCB, bWeb ? "-":"" + iTime});
                            if (pane == Message.YES_OPTION)
                            {
                              int iL=(int)Node.getUserData();
                              if (iL>0)
                              {
                                Vector<Object> VecN=(Vector<Object>)Node.getLabel();
                                Sort.gets(VecN, 0);
                                g.SaveVerlauf(g.getBegriffAicS("Button","Raus1"),0,0,0,"Benutzer "+Sort.gets(VecN, 3)+" von Computer "+Sort.gets(VecN, 0)+" bei Logging "+iL+" nach "+iTime+" s ausloggen",0);                          
                                g.exec("UPDATE Logging SET aus="+g.now()+",status="+(Transact.MANUELL+(bWeb ? Transact.WEBLOG:0))+" WHERE aus is null and aic_logging="+iL);
                                Node.getParent().removeChild(Node);
                              }
                            }
                          }
                          // else
                          // {
                          //   //new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("zu_kurz");
                          //   int pane = new Message(Message.YES_NO_OPTION, (JFrame) thisFrame, g).showDialog("ausloggen_zwingen",new String[] {sCB});
                          //   if (pane == Message.YES_OPTION)
                          //   {
                          //     int iPU=SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                          //     if (iPU>0)
                          //     {
                          //       Parameter P=new Parameter(g, "Datenbank");
                          //       P.ErmittleParameter("raus",false);
                          //       P.addGruppen();
                          //       P.add(g.int1(),Node.getUserData());
                          //       P.add("aic_logging",g.getLog());
                          //       P.insert("parameter",false);
                          //       g.exec("Update parameter set "+g.int1()+"=6 where aic_parameter=" + iPU);
                          //     }
                          //   }
                          // }
                          OutComputer.folderChanged(OutComputer.getRootNode());
                        }
                });

		/*BtnPasswort.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Passwort Pass=new Passwort((JFrame)thisFrame,g);
				Pass.setPasswort("Benutzer",g.getBenutzer(),true);
			}
		});*/

                ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnRefresh);
//		BtnRefresh.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent ev)
//			{
//				fillOutliner();
//			}
//		});

		Action cancelKeyAction = new AbstractAction()
                {
                    /**
					 *
					 */
					private static final long serialVersionUID = -5570942711038080987L;

					public void actionPerformed(ActionEvent e) {
                      hide();
                    }
                };
                BtnBeenden.addActionListener(cancelKeyAction);
                Static.Escape(BtnBeenden,thisFrame,cancelKeyAction);

		OutComputer.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent ev)
			{
				if(ev.getStateChange()==ItemEvent.SELECTED)
					EnableButtons();
			}
		});

                /*for (TabFormular.moveFirst();!TabFormular.out();TabFormular.moveNext())
                  if (TabFormular.getS("Gruppe").equals("Modell"))
                  {
                    ((JButton)TabFormular.getInhalt("Button")).addActionListener(new ActionListener()
                          {
                                  public void actionPerformed(ActionEvent e)
                                  {
                                    //Modellberechnung((JButton)e.getSource(),e.getModifiers()==17,false); // shift
                                    if (TabFormular.posInhalt("Button",e.getSource()))
                                    {
                                      thisFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                                      int iPos = g.TabBegriffe.getPos("Aic", TabFormular.getI("Aic"));
                                      int iMbits=g.TabBegriffe.getI(iPos,"bits");
                                      JCOutlinerNode Node = OutComputer.getSelectedNode();
                                      Vector Vec=g.getAics(OutComputer);
                                      int iLog=Sort.geti(Node.getUserData());
                                      int iStamm=SQL.getInteger(g,"select aic_stamm from benutzer b join logging l on b.aic_benutzer=l.aic_benutzer where aus is null and l.aic_Logging="+iLog);
                                      Vec=SQL.getVector("select aic_stamm from benutzer b join logging l on b.aic_benutzer=l.aic_benutzer where aus is null and l.aic_Logging"+Static.SQL_in(Vec),g);
                                      if (iStamm>0)
                                        TCalc.Berechnen(g, TabFormular.getI("Aic"), iMbits, iStamm, Vec, Self, 0,(JButton)e.getSource());
                                      thisFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                    }
                                  }
			});
                  }*/

		show();
	}

	private void Build()
	{
		ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
//            if (s.equals("Entsperren"))
//            	Entsperren();
//            else 
            if (s.equals("Refresh"))
            	fillOutliner();
            else if (s.equals("LogoutDB"))
            	g.sendWebDB("logoutDB",null,thisJFrame());
            else if (s.equals("OkDB"))
            	g.sendWebDB("okDB",null,thisJFrame());
          }
        };
//		BtnEntsperren = g.BtnAdd(getButton("Entsperren"),"Entsperren",AL);
//		if(BtnEntsperren==null) BtnEntsperren = new JButton();
		JButton BtnLogoutDB = g.BtnAdd(getButton("LogoutDB"),"LogoutDB",AL);
		if (BtnLogoutDB != null && !g.Def())
			BtnLogoutDB.setVisible(false);
		JButton BtnOKDB = g.BtnAdd(getButton("OkDB"),"OkDB",AL);
		if (BtnOKDB != null && !g.Def())
			BtnOKDB.setVisible(false);
		//BtnPasswort = getButton("Benutzerpasswort");
		//if(BtnPasswort==null) BtnPasswort =new JButton();
		BtnRefresh = g.BtnAdd(getButton("Refresh"),"Refresh",AL);
		if(BtnRefresh==null) BtnRefresh =new JButton();
		BtnRaus = getButton("Raus");
		if(BtnRaus==null) BtnRaus =new JButton();
        BtnRaus1 = getButton("Raus1");
        if(BtnRaus1==null) BtnRaus1 =new JButton();
        BtnRausWeb = getButton("RausWeb");
        if(BtnRausWeb==null) BtnRausWeb =new JButton();
        BtnBeenden = getButton("Beenden");
		if(BtnBeenden==null) BtnBeenden = new JButton();

		JPanel Pnl_Outliner = getFrei("Outliner");

		if(Pnl_Outliner!=null)
		{
			Pnl_Outliner.setLayout(new BorderLayout(2,2));
			Pnl_Outliner.add("Center",OutComputer);
		}

	}

	public void show()
	{
		fillOutliner();
		super.show();
	}

	private void fillOutliner()
	{
		JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)OutComputer.getRootNode();
		NodeRoot.removeChildren();
		SQL Qry = new SQL(g);
                Vector<Integer> VecRoot = new Vector<Integer>();
                for (int i=0;i<11;i++)
                  VecRoot.addElement(null);
                NodeRoot.setLabel(VecRoot);
                if (g.SuperUser())
                {
                	OutComputer.setRootVisible(true);
                	JCOutlinerNodeStyle StyRoot=g.getStyle(null);
                	OutComputer.getRootNode().setStyle(StyRoot);	
                }
		if(Qry.open("SELECT aic_computer,kennung,ip_adresse,gesperrt FROM Computer WHERE gesperrt is not null"))
		{
			for(;!Qry.eof();Qry.moveNext())
			{
				Vector<Object> VecVisible = new Vector<Object>();
				VecVisible.addElement(Qry.getS("kennung"));
				VecVisible.addElement(Qry.getS("ip_adresse"));
				VecVisible.addElement(new Zeit(Qry.getTS("gesperrt"),"yyyy-MM-dd HH:mm"));
                                Sort.add(VecRoot,2);
				VecVisible.addElement(null);
				VecVisible.addElement(null);
				VecVisible.addElement(null);
				VecVisible.addElement(null);
				JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeRoot);
				Node.setUserData(new Integer(-Qry.getI("aic_computer")));
				JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
				StyNode.setForeground(Color.red);
				Node.setStyle(StyNode);
			}

		}
		else
			Static.printError("Security.fillOutliner(): ");

		if(Qry.open("SELECT computer.aic_computer as aic,logging.aic_logging,logging.aic_mandant,computer.kennung as computer,ip_adresse,benutzer.kennung as benutzer"+g.AU_Bezeichnung("benutzer")+
				",Benutzer.Bits,ein seit,mom last,status,(select kennung from mandant where aic_mandant=logging.aic_mandant) Mandant,(select kennung from code where aic_code=logging.aic_code) Anlage,logging.version"+
                            " FROM computer JOIN logging on computer.aic_computer=logging.aic_computer JOIN benutzer on benutzer.aic_benutzer=logging.aic_benutzer WHERE aus is null"+
                            (g.bUP ? "":g.UserManager() ? g.getReadMandanten(false,"logging"):" and benutzer.aic_benutzer="+g.getBenutzer())))
                            //(g.ASA()?" group by computer,benutzer,ip_adresse,aic":" group by computer.kennung,benutzer.kennung,ip_adresse,computer.aic_computer")))
		{
			for(;!Qry.eof();Qry.moveNext())
			{
				Vector<Object> VecVisible = new Vector<Object>();
				VecVisible.addElement(Qry.getS("computer"));
				VecVisible.addElement(Qry.getS("ip_adresse"));
				VecVisible.addElement(null);
				VecVisible.addElement(Qry.getS("benutzer"));
				VecVisible.addElement(Qry.getS("Bezeichnung"));
                                Sort.add(VecRoot,3);
                                VecVisible.addElement(Qry.getS("anlage"));
                                if (!Qry.isNull("anlage")) Sort.add(VecRoot,5);
				VecVisible.addElement(new Zeit(Qry.getTS("seit"),"yyyy-MM-dd HH:mm"));
				//VecVisible.addElement(new Integer(Qry.getI("Anzahl")));
				VecVisible.addElement(new Zeit(Qry.getTS("last"),"yyyy-MM-dd HH:mm"));
                                VecVisible.addElement(Qry.getS("mandant"));
                                int iBits=Qry.getI("bits");
                                VecVisible.addElement(getBerechtigung(g,iBits));
                                if ((iBits&(Global.cstBenutzerEbene))>0) Sort.add(VecRoot,9);
                                boolean bHS=(Qry.getI("status")&Transact.ADMIN)>0/*(iBits&(Global.cstBenutzerEbene+Global.cstHS))>0 && Qry.isNull("anlage")*/ && Qry.getI("Aic_Mandant")==g.getMandant();
                                VecVisible.addElement(Static.JaNein2(bHS)); 
                                VecVisible.addElement(Version.getS(Qry.getI("Version")));
                                int iLog=Qry.getI("aic_logging");
                                Tabellenspeicher TabVerlauf=new Tabellenspeicher(g,"select timestamp,aic_stamm,dauer,(select defbezeichnung from begriff where aic_begriff=verlauf.aic_begriff) zuletzt"+
                                    " from verlauf where aic_verlauf= (select max(aic_verlauf) from verlauf where aic_logging=?)",""+iLog,true,null);
                                if (TabVerlauf.size()>0)
                                {
                                  VecVisible.addElement(new Zeit(TabVerlauf.getTimestamp("timestamp"),"yyyy-MM-dd HH:mm"));
                                  VecVisible.addElement(TabVerlauf.getS("zuletzt"));
                                  VecVisible.addElement(TabVerlauf.isNull("aic_stamm")?"":g.getStamm(TabVerlauf.getI("aic_stamm")));
                                  VecVisible.addElement(TabVerlauf.isNull("Dauer") ?"":new Zahl1(TabVerlauf.getF("Dauer")/1000.0,"0.00")+" s");
                                }
                                else
                                {
                                  VecVisible.addElement(null);
                                  VecVisible.addElement(null);
                                  VecVisible.addElement(null);
                                  VecVisible.addElement(null);
                                }
                                VecVisible.addElement(Static.JaNein2((Qry.getI("status")&Transact.WEBLOG)>0));
                                if (bHS) Sort.add(VecRoot,10);
				JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeRoot);
                                Node.setUserData(new Integer(iLog));
				JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
				StyNode.setForeground(iLog!=g.getLog()?new Color(0,150,0):Color.blue);

				Node.setStyle(StyNode);
			}

		}
		else
			Static.printError("Security.fillOutliner(): ");

		OutComputer.folderChanged(NodeRoot);

		Qry.free();
                setTitle(sFormularBezeichnung+g.getConnectInfo());
	}

//	private void Entsperren()
//	{
//		JCOutlinerNode Node = OutComputer.getSelectedNode();
//		g.exec("UPDATE Computer SET gesperrt=null WHERE AIC_Computer="+(-Sort.geti(Node.getUserData())));
//		Node.getParent().removeChild(Node);
//		OutComputer.folderChanged(OutComputer.getRootNode());
//	}

	private void EnableButtons()
	{
		JCOutlinerNode Node = OutComputer.getSelectedNode();
                boolean bOk=Node!=null && Node.getLevel()>0;
//		BtnEntsperren.setEnabled(bOk && ((Vector)Node.getLabel()).elementAt(2)!=null);
		//BtnPasswort.setEnabled(bOk && Node.getStyle().getForeground()==Color.blue);
                boolean bSelf=bOk && Node.getStyle().getForeground()==Color.blue;
                BtnRaus.setEnabled(g.SuperUser() || g.bUP);
                BtnRaus1.setEnabled(bOk && (g.SuperUser() || g.bUP) && !(bOk && ((Vector)Node.getLabel()).elementAt(2)!=null) && !bSelf);
                Vector Vec=bOk ? (Vector)Node.getLabel():null;
                BtnRausWeb.setEnabled(bOk && !Static.Leer(Static.sWeb) && Vec.size()>16 && Sort.gets(Vec, 16).equals("x"));
                //BtnRaus1.setEnabled(true);
	}

      public static String getBerechtigung(Global g,int iBits)
      {
        int iBits2 = iBits & Global.cstBenutzerEbene;
        return g.getBegriffS("Radiobutton",iBits2 == Global.cstProg ? "Prog" : iBits2 == Global.cstDef ? "Def" : iBits2 == Global.cstVerw ? "Verwaltung" : iBits2 == Global.cstSuperUser ? "Superuser" : iBits2 == Global.cstUserManager ? "UserManager" : "Normal");
      }

// add your data members here
private Global g;
private AUOutliner OutComputer = new AUOutliner(new JCOutlinerFolderNode("Root"));

//private JButton BtnEntsperren;
//private JButton BtnPasswort;
private JButton BtnRefresh;
private JButton BtnBeenden;
private JButton BtnRaus;
private JButton BtnRaus1;
private JButton BtnRausWeb;
}

