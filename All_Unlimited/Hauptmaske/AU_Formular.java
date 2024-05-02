/*
    All_Unlimited-Hauptmaske-AU_Formular.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import All_Unlimited.Allgemein.Formular;
//import All_Unlimited.Allgemein.FormularFX;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Passwort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Grunddefinition.*;
import All_Unlimited.Print.Doku;
import All_Unlimited.Print.Druckdefinition;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;

//import java.net.URL;
//import java.applet.Applet;

public class AU_Formular extends All_Unlimited.Allgemein.Formular
{
	public AU_Formular(int riAic,Global glob)
	{
		super(riAic,glob);
		//g=glob;
		setTitle(g.getDB_Name()+(g.Def()?"AU_Formular - ":"")+getTitle());
                JButton BtnLogout=getButton("Logout");
                if (BtnLogout != null)
                {
                 g.BtnLogout=BtnLogout;
//                 Platform.setImplicitExit(false);
                 g.fixtestInfo("Platform.setImplicitExit(false)");
                 if (!g.bLogout)
                   BtnLogout.setEnabled(false);
		 Static.addActionListener(BtnLogout,new ActionListener()
		 {
                  public void actionPerformed(ActionEvent e)
                  {
                         //Tabellenspeicher TabAT=g.aktiveThreads(false);
                         if (g.ModellThread())
                           new Message(Message.ERROR_MESSAGE, (JFrame)thisFrame,g).showDialog("ModellThread");
                         else if (!g.SonstThread() || new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Theads_abbrechen") == Message.YES_OPTION)
                         {
                           g.freeThreads();
                          //g.TabFormulare.showGrid();
                          for (int iPos=0;iPos<g.TabFormulare.size();iPos++)
                          {
                            Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
                            boolean b=false;
                            int iSatz=0;
                            int iDaten=0;
                            if (Obj instanceof Hauptschirm)
                            {
                              b = ((Hauptschirm)Obj).isShowing();
                              if (b)
                                ((Hauptschirm)Obj).Beenden(false);
                            }
                            else if (Obj instanceof EingabeFormular)
                            {
                              b=((EingabeFormular)Obj).thisFrame.isShowing();
                              if (b)
                                ((EingabeFormular)Obj).Beenden(-1);
                              iSatz=((EingabeFormular)Obj).iStamm;
                              iDaten=((Formular)Obj).iDaten;
                            }
                            else if (Obj instanceof Formular)
                              b=((Formular)Obj).thisFrame.isShowing();
                            else if (Obj instanceof JFrame)
                              b=((JFrame)Obj).isShowing();
                            Window win=/*Obj instanceof FormularFX ? null ((FormularFX)Obj).thisFrame:*/Obj instanceof Formular ? ((Formular)Obj).thisFrame:(Window)Obj;
                            if (b)
                              g.setFenster(win, g.TabFormulare.getI(iPos,"Aic"),1+iDaten*2,iSatz,-1); // !100!
                            //g.setFensterBit(g.TabFormulare.getI(iPos,"Aic"),b?1:0);
                            if (win!=null && Obj != Static.FomStart)
                              try
                              {
                                win.dispose();
                              }
                              catch (Exception e2)
                              {
                                Static.printError("AU_Formular.BtnLogout: "+e2+" bei "+Static.print(win));
                              }
                          }

				saveFenster();
                                clearForm();
                                if (Static.bJBuilder)
                                {
                                  //TCalc.closeAServer();
                                  g.Logout(false);
                                  System.exit(0);
                                  //TCalc.free();
                                }
                if (Static.FomStart != null)
                	Static.FomStart.dispose();
			}
                    }
		 });
                }
		Static.addActionListener(getButton("Beenden"),new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hide();
			}
		});

                Static.addActionListener(getButton("Module"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          new Tabellenspeicher(g,"select defbezeichnung Modul,importzeit Zeit from begriff join begriffgruppe g on begriff.aic_begriffgruppe=g.aic_begriffgruppe where g.kennung='Modul' and importzeit is not null",true).showGrid("Module");
                        }
                });

                Static.addActionListener(getButton("WW"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          WW.start(g,(JFrame)thisFrame).show2();
                          //WW.showWW(g);
                        }
                });

                Static.addActionListener(getButton("Import"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          Import.start(g, (JFrame)thisFrame).show2(0);
                        }
                });
                
                Static.addActionListener(getButton("RefreshDB"),new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                        	g.sendWebDB("refreshDB",null,thisJFrame());
                        }
                });

		TabFormular.moveFirst();
		int iB=0;
		while(!TabFormular.out())
		{
		  Object Obj=TabFormular.getInhalt("Button");
		  if (Obj instanceof JButton)
		    ((JButton)Obj).addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					long lClock=Static.get_ms();
					//((JButton)e.getSource()).setEnabled(false);
					//thisFrame.repaint();

					TabFormular.posInhalt("Button",e.getSource());
                                        int iPos=g.TabFormulare.getPos("Aic",TabFormular.getI("Aic"));
                                        //g.fixInfo("*************** Suche "+TabFormular.getS("Kennung")+"->"+iPos);
					if(iPos>=0 && TabFormular.isNull("Comp"))
					{
						Object Obj = g.TabFormulare.getInhalt("Formular",iPos);
						//g.fixInfo("gefunden:"+Obj.getClass().getName()+"/"+TabFormular.getI("Aic"));
						if(Obj instanceof EingabeFormular)
						{
							((EingabeFormular)Obj).FuelleEigenschaften(g.getStamm(),true);
							//((JButton)e.getSource()).setEnabled(true);
						}
                        else if(Obj instanceof PersEinstellungen)
                          PersEinstellungen.get(g).show(thisFrame);
                        else if(Obj instanceof UserManager)
                          UserManager.get(g);
                        else if(Obj instanceof AdminPassword)
                        	AdminPassword.start(g);
                        else if(Obj instanceof CleanComputer)
                        	CleanComputer.get(g);
                        else if(Obj instanceof Logging)
                        	Logging.get(g);
                        else if(Obj instanceof DefAufgabe)
                        	DefAufgabe.get(g,0);
                        else if(Obj instanceof DefEigenschaft)
                        	DefEigenschaft.get(g,0);
                        else if(Obj instanceof Zuordnung2)
                            Obj=Zuordnung2.get(g);
                        else if(Obj instanceof DefPlanung)
                            Obj=DefPlanung.get(g,0);
                        else if(Obj instanceof Tsearch)
                            Obj=Tsearch.get(g,null);
                        else if(Obj instanceof DefFormular)
                            Obj=DefFormular.get(g,0);
                        else if(Obj instanceof Reinigung)
                        	Obj=Reinigung.get(g);
                        else if(Obj instanceof Sprache2)
                        	Obj=Sprache2.get(g);
                        else if(Obj instanceof Lizenz)
                            Obj=Lizenz.get(g);
                        else if(Obj instanceof DefModul)
                            Obj=DefModul.get(g,0);
                        else if(Obj instanceof Security)
                        	Obj = Security.get(g);
                        else if(Obj instanceof DefBew)
                            Obj=DefBew.get(g,0);
                        else if(Obj instanceof DefStt)
                            Obj=DefStt.get(g,0);
                        else if(Obj instanceof DefHtml)
                            Obj=DefHtml.get(g,0);
                        else if(Obj instanceof DefTerminal)
                            Obj = DefTerminal.get(g);
                        else if(Obj instanceof Druckdefinition)
                            Obj=Druckdefinition.get(g,false,0,0);
                        else if(Obj instanceof DefAbfrage)
                          fillDefAbfrage(g,((DefAbfrage)Obj).getNr());
                        else if(Obj instanceof Formular)
                        	((Formular)Obj).show();
						else if(Obj instanceof Window)
                        {
                          if (Obj instanceof JFrame)
                            ((JFrame)Obj).setState(Frame.NORMAL);
                          ((Window)Obj).setVisible(true);
                        }
//						else if(Obj instanceof FormularFX)
//							((FormularFX)Obj).show();
						else
							Static.printError("AU_Formular: Fehler in g.TabFormulare! "+Static.print(Obj)+" wird nicht unterstützt!");
					}
					else
					{
						Window win = Formular_Erzeugen(e);
						TabFormular.setInhalt("Formular",win);

						//HS1.setLocation(50,50);
						/*if(win!=null)
						{
							win.addWindowListener(new WindowListener()
							{
								public void windowClosed(WindowEvent e)
								{
                                                                  int iPos=TabFormular.getPos("Formular",e.getSource());
                                                                  g.testInfo("windowClosed:"+TabFormular.getI(iPos,"Aic"));
										//TabFormular.posInhalt("Formular",e.getSource());
										//((JButton)TabFormular.getInhalt("Button")).setEnabled(true);
										//TabFormular.setInhalt("Formular",null);
								}
								public void windowOpened(WindowEvent e)
								{
								}
								public void windowClosing(WindowEvent e)
								{
                                                                  //int iPos=TabFormular.getPos("Formular",e.getSource());
                                                                  //g.testInfo("windowClosing:"+TabFormular.getI(iPos,"Aic"));
								}
								public void windowIconified(WindowEvent e)
								{
								}
								public void windowDeiconified(WindowEvent e)
								{
                                                                  //g.testInfo("windowDeiconified");
								}
								public void windowActivated(WindowEvent e)
								{
								}
								public void windowDeactivated(WindowEvent e)
								{
                                                                  int iPos=TabFormular.getPos("Formular",e.getSource());
                                                                  g.testInfo("windowDeactivated"+TabFormular.getI(iPos,"Aic"));
									//if(!e.getWindow().isShowing())
                                                                        //  g.testInfo("Dieser Teil fehlt nun");
									/*{
                                                                          for (TabFormular.moveFirst();!TabFormular.out();TabFormular.moveNext())
                                                                          {
                                                                            Object Obj = g.TabFormulare.getInhalt("Formular");
                                                                            boolean b=false;
                                                                            if (Obj instanceof Hauptschirm)
                                                                            {
                                                                              b=((Hauptschirm)Obj).isShowing();
                                                                            }
                                                                            if (b)
                                                                              g.setFensterBit(TabFormular.getI("Aic"),1);
                                                                          }
										TabFormular.posInhalt("Formular",e.getSource());
										//((JButton)TabFormular.getInhalt("Button")).setEnabled(true);
										//TabFormular.setInhalt("Formular",null);
										if(!g.TabFormulare.existInhalt("Aic",TabFormular.getInhalt("Aic")))
										{
											e.getWindow().dispose();
											TabFormular.setInhalt("Formular",null);
										}
									}
								}
							});
						}*/
					}
					g.clockInfo(TabFormular.getS("Gruppe")+"/"+TabFormular.getS("Kennung"),lClock);
				}
			});
		  else
			iB++;
		  TabFormular.moveNext();
		}
		if (iB>0)
			Static.sleep(500*iB);
		  
		show();
	}
	

        private static Object fillDefAbfrage(Global g,int iNr)
        {
          long lClock3=Static.get_ms();    
          Object Obj=null;
          if (g.Def() && !g.Oracle())
          {
        	int iAic=SQL.getInteger(g,g.first("d.aic_begriff from logging l join defverlauf d on l.aic_logging=d.aic_logging and l.aic_benutzer="+g.getBenutzer()+
                                              " join begriff b on d.aic_begriff=b.aic_begriff and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage")+" order by d.aic_logging desc"));
            int iStt=iAic>0 ? SQL.getInteger(g,"select "+g.isnull()+"-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff="+iAic):g.iSttANR;
            lClock3=g.clockInfo3("+letzte Abfrage ermitteln", lClock3);
            Obj=DefAbfrage.get(g, new ShowAbfrage(g, iAic, Abfrage.cstBegriff), iStt,null, false, iNr);
          }
          else
            Obj=DefAbfrage.get(g, new ShowAbfrage(g, 0, Abfrage.cstAbfrage), g.iSttANR,null, false,iNr);
          lClock3=g.clockInfo3("+DefAbfrage laden", lClock3);
          ((DefAbfrage)Obj).show(false);
          lClock3=g.clockInfo3("+DefAbfrage zeigen", lClock3);
          return Obj;
        }

        public String toString()
        {
          return "AU_Formular - "+g.TabBegriffe.getBezeichnungS(getBegriff());
        }

	private Window Formular_Erzeugen(ActionEvent e)
	{
		//long lClock = Static.get_ms();
		((JFrame)this.thisFrame).setCursor(new Cursor(Cursor.WAIT_CURSOR));
		Object Obj=null;

		if(TabFormular.getS("Gruppe").equals("Applikation")) // Hauptschirm-Aufruf
                {
                  Obj = null;
                  ((JButton)e.getSource()).setEnabled(false);
                  thisFrame.repaint();
                  new Thread(new Runnable()
                  {
                    public void run()
                    {
                      int iPos=TabFormular.getPos();
                      Hauptschirm HS=Hauptschirm.get((String)TabFormular.getInhalt("Kennung",iPos), g);
                      //g.putTabFormulare(TabFormular.getI(iPos,"Aic"),0,Obj);
                      ((JButton)TabFormular.getInhalt("Button",iPos)).setEnabled(true);
                      if(!HS.bFP)
          				Static.centerComponent2(HS, thisFrame);
//                      g.fixtestError("run "+HS);
                    }
                  }).start();
                }
		else if(TabFormular.getS("Gruppe").startsWith("Eingabe") || !TabFormular.isNull("Comp"))
		{
			Obj=null;
//                        if (g.bErsatz)
//                        {
//                          new Message(Message.WARNING_MESSAGE, (JFrame)thisFrame, g).showDialog("kein_Ersatzrecht");
//                          ((JFrame)this.thisFrame).setCursor(Cursor.getDefaultCursor());
//                          return null;
//                        }

			//g.TabBegriffe.posInhalt("Aic",TabFormular.getI("Aic"));
			//int iStt=g.TabBegriffe.getI("Stt");
                        g.setSyncStamm(g.iSttANR,g.getStamm());
                        //g.fixInfo("*************** Aufruf "+TabFormular.getS("Kennung")+"->"+TabFormular.getInhalt("Comp"));
                        if (!TabFormular.isNull("Comp") && TabFormular.getInhalt("Comp") instanceof Vector)
                        {
                          JPopupMenu popup = new JPopupMenu();
                          Vector Vec=(Vector)TabFormular.getInhalt("Comp");
                          for (int i=0;i<Vec.size();i++)
                          {
                            int iAic=Sort.geti(Vec,i);
                            JMenuItem MnuEF = new JMenuItem(g.TabBegriffe.getBezeichnungS(iAic));
                            String sTT=g.TabBegriffe.getInfoS(iAic,"Tooltip");
                            if (!sTT.equals(""))
                              g.setTooltip(sTT,MnuEF);
                            MnuEF.setFont(g.fontStandard);
                            MnuEF.setActionCommand(""+iAic);
                            popup.add(MnuEF);
                            if (TabFormular.getS("Gruppe").equals("System")) 
                              MnuEF.addActionListener(new ActionListener()
                              {
                                public void actionPerformed(ActionEvent ev)
                                {
                                  OpenSystem(g,thisFrame,g.TabBegriffe.getKennung(Sort.geti(ev.getActionCommand())));
                                }
                              });
                            else
                            MnuEF.addActionListener(new ActionListener()
                            {
                              public void actionPerformed(ActionEvent ev)
                              {
                                EingabeFormular.HoleFormular(g,Sort.geti(ev.getActionCommand()),Static.AicToVec(g.getStamm()),g.getStamm(),false,null,0,true);
                              }
                            });
                          }
                          if (g.Def())
                          {
                            popup.addSeparator();
                            JMenuItem MnuEF = new JMenuItem("Formulare");
                            popup.add(MnuEF);
                            MnuEF.addActionListener(new ActionListener()
                            {
                              public void actionPerformed(ActionEvent ev)
                              {
                                TabFormular.showGrid("TabFormular");
                              }
                            });
                          }
                          popup.show((JButton)TabFormular.getInhalt("Button"), 0, 0);
                        }
                        else if (TabFormular.getS("Gruppe").equals("Eingabe"))
                          EingabeFormular.HoleFormular(g,TabFormular.getI("Aic"),Static.AicToVec(g.getStamm()),g.getStamm(),false);//.Titelzusatz(SQL.getString(g,"select bezeichnung from stammview where aic_stamm="+g.getStamm()));
                        else
                          EingabeFormular.HoleFormular(g,TabFormular.getI("Aic"),null,0,false);
			//((JButton)e.getSource()).setEnabled(true);
		}
		else if(TabFormular.getS("Gruppe").equals("Frame")) // Subformular-Aufruf z.B. Werkzeugwahl
			Obj=new AU_Formular(getForm(g,TabFormular.getI("Aic")),g);
		else if(TabFormular.getS("Gruppe").equals("Button"))
		{
			if(TabFormular.getS("Kennung").equals("Definition"))
				Obj=new Definition(g,"");
			else if(TabFormular.getS("Kennung").equals("Abfrage2"))
				Obj=fillDefAbfrage(g,1);
            else
				Static.printError("AU_Formular.Formular_Erzeugen: Knopf "+TabFormular.getS("Kennung")+" entspricht keinem Formular!");
		}
		else if(TabFormular.getS("Gruppe").equals("Modell")) // Modell-Aufruf: entfernt
		{
			//Obj=null;
			//SQL Qry=new SQL(g);
			//Qry.open("Select aic_bewegungstyp from modell join begriff where aic_begriff="+TabFormular.getI("Aic"));
			//new Calc(null,g,TabFormular.getI("Aic"),true,Static.AicToVec(g.getStamm()),false);
                        //Calc.BG(g,TabFormular.getI("Aic"),true,Static.AicToVec(g.getStamm()),false);
		  Static.printError(getTitle()+": Modell nur mehr aus EingabeFormular aufrufbar!");
			//Qry.free();
			//((JButton)e.getSource()).setEnabled(true);
		}
		else if(TabFormular.getS("Gruppe").equals("System")) // Systemformular-Aufruf
		{
                  Obj=OpenSystem(g,thisFrame,TabFormular.getS("Kennung"));
		}
		else
			Static.printError("AU_Formular.Formular_Erzeugen: Gruppe "+TabFormular.getS("Gruppe")+" ist keine Formular-Gruppe!");

		if(Obj!=null)
		{
			g.putTabFormulare(TabFormular.getI("Aic"),0,Obj);
			if(Obj instanceof Formular)
			{
				if (!((Formular)Obj).bFP)
				{
					if (Obj instanceof DefModell)
					{
						((DefModell)Obj).thisFrame.setLocation(thisFrame.getLocationOnScreen());
						Static.sleep(200);
						((DefModell)Obj).InitFrame();
					}
					else
						Static.centerComponent2(((Formular)Obj).thisFrame, thisFrame);					
				}
				Obj = ((Formular)Obj).thisFrame;
			}
			
//			g.fixtestError("Formular_Erzeugen:"+Static.print(Obj));
		}

		//g.printInfo("Formular "+TabFormular.getS("Kennung")+" :"+(Static.get_ms()-lClock));

		((JFrame)this.thisFrame).setCursor(Cursor.getDefaultCursor());
		return Obj instanceof Window ? ((Window)Obj):null;
	}

        public static Object OpenSystem(Global g,Window w,String s)
        {
          Object Obj=null;
          if(s.equals("Zuordnung"))
            Obj=new Zuordnung(g);
          else if(s.equals("Zuordnung2"))
            Obj=Zuordnung2.get(g);
          else if(s.equals("Formularerstellung"))
            Obj=DefFormular.get(g,0);
          else if(s.equals("DefEigenschaft"))
            Obj=DefEigenschaft.get(g,0);
          else if(s.equals("Sprache2"))
            Obj=Sprache2.get(g);
          else if(s.equals("translate-search"))
            Obj=Tsearch.get(g,null);
          else if(s.equals("Modell"))
            Obj=DefModell.get(g,0);
          else if(s.equals("Zeitraum"))
          {
                  Obj=Zeitraum.get(g);
                  ((Zeitraum)Obj).show();
          }
          else if(s.equals("Abschluss"))
          {
                  Obj=Abschluss.get(g);
                  ((Abschluss)Obj).show();
                  //((JButton)e.getSource()).setEnabled(true);
          }
          else if(s.equals("Abfrage"))
            Obj=fillDefAbfrage(g,0);                
          else if(s.equals("Benutzerpasswort"))
          {
                  Obj=new Passwort((Frame)w,g); // thisFrame
                  ((Passwort)Obj).setPasswort("Benutzer",g.getBenutzer(),true);
          }
          else if(s.equals("Security"))
          {
            Obj = Security.get(g);
//            ((Security)Obj).show();
          }
          else if(s.equals("PersEinstellungen"))
          {
            Obj = PersEinstellungen.get(g);
            ((PersEinstellungen)Obj).show(w); // thisFrame
          }
          /*else if(TabFormular.getS("Kennung").equals("Druckeinstellungen"))
          {
                  Obj=DruckConfig.get(g);
                  ((DruckConfig)Obj).show();
          }*/
          else if(s.equals("Lizenz"))
            Obj=Lizenz.get(g);
          else if(s.equals("ModulDefinition"))
            Obj=DefModul.get(g,0);
          else if(s.equals("User Manager"))
            Obj=UserManager.get(g);
          else if(s.equals("AdminPassword"))
              Obj=AdminPassword.start(g);
          else if(s.equals("CleanComputer"))
              Obj=CleanComputer.get(g);
          else if(s.equals("Logging"))
              Obj=Logging.get(g);
          else if(s.equals("Versionsupdate"))
            Obj=Versionsupdate.get(g);
          else if(s.equals("Druckdefinition"))
            Obj=Druckdefinition.get(g,false,0,0);
          else if(s.equals("AUInfo"))
            Obj=new AUInfo(g);
          else if(s.equals("DefTerminal"))
            Obj = DefTerminal.get(g);
          else if(s.equals("ISQL"))
            Obj=ISQL.get(g,w,false); // thisFrame
          else if(s.equals("Aufgabe"))
        	Obj=DefAufgabe.get(g,0);
          else if(s.equals("Reinigung"))
            Obj = Reinigung.get(g);
          else if(s.equals("Doku"))  // vorrübergehend wegen Diagrammausbau ausgeblendet (16.6.2010)
                  Obj=new Doku(g);
          else if(s.equals("DefPlanung"))
                  Obj=DefPlanung.get(g,0);
          else if(s.equals("DefBew"))
                  Obj=DefBew.get(g,0);
          else if(s.equals("DefStt"))
                  Obj=DefStt.get(g,0);
          else if(s.equals("DefHTML"))
                  Obj=DefHtml.get(g,0);
          else
                  Static.printError("AU_Formular.Formular_Erzeugen: Systemformular "+s+" wird nicht unterstützt!");
          return Obj;
        }


// add your data members here
//private Global g;
}

