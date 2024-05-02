package All_Unlimited.Grunddefinition;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Version;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import All_Unlimited.Allgemein.Static;
import jclass.bwt.JCOutlinerComponent;
import jclass.bwt.JCOutlinerNode;
//import All_Unlimited.Allgemein.Sort;
import jclass.bwt.JCOutlinerNodeStyle;

import java.util.Vector;

import All_Unlimited.Allgemein.Anzeige.Zeit;
//import java.awt.Color;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.8
 */
public class Prozesse extends Formular
{
  private Global g;
  private int iAnz=0;
  //private int iPU=0;
  private JLabel Lbl;
  private ActionListener AL;
  private static Prozesse Self;
  private JButton BtnTimerDeaktivieren;
  private JButton BtnTimerAktivieren;
  private JButton BtnTimerSchliessen;
  private AUOutliner Out = new AUOutliner(null);

  public static Prozesse get(Global rg)
  {
    if (Self==null)
      Self=new Prozesse(rg);
    else
      Self.Refresh();
    return Self;
  }

  public static void free()
  {
     if (Self != null)
     {
       Self.g.winInfo("Prozesse.free");
       Self.thisFrame.dispose();
       Self = null;
     }
  }

  private Prozesse(Global rg)
  {
    super("Prozesse", null, rg);
    g = rg;
    g.winInfo("Prozesse.create");
    Aufbau();
    Refresh();
  }
  
//  public static boolean checkDeaktiv(Global g)
//  {
//	  return checkDeaktiv(g,false);
//  }
  
  public static boolean checkDeaktiv(Global g,boolean bReset,boolean bFrage,JFrame Fom)
  {
	  int iStatus=AClient.getStatus();
	  if (iStatus>0)
	  {
		  new Message(Message.WARNING_MESSAGE, Fom, g).showDialog("AServer_aktiv",new String[] {AClient.getStatus(iStatus, g)});
		  return false;
	  }
	  if (bReset)
	  {
		  if (iStatus==0 && new Message(Message.YES_NO_OPTION,Fom,g).showDialog("AServer_reset")== Message.YES_OPTION)
			  ;
		  else
			  return false;
	  }
	  if (bFrage && new Message(Message.YES_NO_OPTION,Fom,g).showDialog("Timer_Deaktivieren")== Message.NO_OPTION)
		  return false;
	  if (iStatus==0)
		  AClient.send_AServer(bReset ? "reset":"deaktiv",g);
	  return true;
  }

  private void Aufbau()
  {
    AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            g.progInfo("Prozesse.Command=" + s);
            if (s.equals("Refresh"))
              Refresh();
            /*else if (s.equals("Logging"))
            {
              if (Reinigung.Ausgeloggt(g, (JFrame)thisFrame, 1))
                new Message(Message.INFORMATION_MESSAGE, null, g).showDialog("allein eingeloggt");
            }*/
            else if (s.equals("Beenden"))
              hide();
            else if (s.startsWith("Timer"))
            {
              int iPU=g.getPU();
              if (s.equals("Timer_deakt") && checkDeaktiv(g,false,true,thisJFrame()))
              {
            		  g.SaveVerlauf("Timer deaktivieren");
            		  g.exec("Update parameter set " + g.int1() + "=3 where aic_parameter=" + iPU);         
              }
              else if (s.equals("Timer_close") && new Message(Message.YES_NO_OPTION,thisJFrame(),g).showDialog("Timer_Schliessen")== Message.YES_OPTION)
              {
            	  g.SaveVerlauf("Timer schliessen");
                g.exec("Update parameter set " + g.int1() + "=4 where aic_parameter=" + iPU);
                AClient.send_AServer("x",g);
                AClient.setNull();
              }
              else if (s.equals("Timer_akt"))
              {
            	  g.SaveVerlauf("Timer aktivieren");
                g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
                AClient.send_AServer("aktiv",g);
              }
              checkStatus();
            }

          }
        };

    //JPanel PnlTextfeld = getFrei("Textfeld");
    Lbl=new JLabel();
    Lbl.setFont(g.fontTitel);
    g.initOutliner(Out,new String[] {"Computer","IP","Benutzer","Anlage","seit","zuletzt","Mandant","Version"});

    getFrei("Textfeld").add(Lbl);
    getFrei("Outliner").add(Out);
    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
    //g.BtnAdd(getButton("Logging"),"Logging",AL);
    g.BtnAdd(getButton("Beenden"),"Beenden",AL);
    BtnTimerDeaktivieren=g.BtnAdd(getButton("Timer deaktivieren"),"Timer_deakt",AL);
    BtnTimerAktivieren=g.BtnAdd(getButton("Timer aktivieren"),"Timer_akt",AL);
    BtnTimerSchliessen=g.BtnAdd(getButton("Timer schliessen"),"Timer_close",AL);
  }

  private void checkStatus()
  {
    int iPU=g.getPU();
    g.progInfo("checkStatus bei "+iPU);
    int iStatus= iPU>0 ? SQL.getInteger(g,"select "+g.int1()+" from parameter where aic_parameter="+iPU):-2;
    Lbl.setText(g.TabStatus.getBezeichnungS(iStatus)+" ("+iStatus+")"+(g.Prog()?", Aufruf="+iAnz:""));
    BtnTimerDeaktivieren.setEnabled(iStatus==0);
    BtnTimerAktivieren.setEnabled(iStatus==2 || iStatus==3 || iStatus==4 || iStatus==6);
    BtnTimerSchliessen.setEnabled(iStatus==0 || iStatus==3 || iStatus==6); // damit Timer schließen auch bei einzelausloggen möglich ist
  }

  private void FillOutliner()
  {
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    SQL Qry = new SQL(g);

    if(Qry.open("SELECT computer.aic_computer as aic,logging.aic_logging,logging.aic_mandant,computer.kennung as computer,ip_adresse,benutzer.kennung as benutzer,Benutzer.Bits,ein seit,mom last"+
                            ",(select kennung from mandant where aic_mandant=logging.aic_mandant) Mandant,(select kennung from code where aic_code=logging.aic_code) Anlage,logging.version"+
                            " FROM computer JOIN logging on computer.aic_computer=logging.aic_computer JOIN benutzer on benutzer.aic_benutzer=logging.aic_benutzer"+
                            " WHERE aus is null and logging.aic_code is not null"+(g.bLizenzFrei ? "":g.getReadMandanten(false,"logging"))))
                            //(g.ASA()?" group by computer,benutzer,ip_adresse,aic":" group by computer.kennung,benutzer.kennung,ip_adresse,computer.aic_computer")))
    {
            for(;!Qry.eof();Qry.moveNext())
            {
                    Vector<Object> VecVisible = new Vector<Object>();
                    VecVisible.addElement(Qry.getS("computer"));
                    VecVisible.addElement(Qry.getS("ip_adresse"));
                    VecVisible.addElement(Qry.getS("benutzer"));
                    VecVisible.addElement(Qry.getS("anlage"));
                    VecVisible.addElement(new Zeit(Qry.getTS("seit"),"yyyy-MM-dd HH:mm"));
                    VecVisible.addElement(new Zeit(Qry.getTS("last"),"yyyy-MM-dd HH:mm"));
                    VecVisible.addElement(Qry.getS("mandant"));
                    VecVisible.addElement(Version.getS(Qry.getI("Version")));
                    JCOutlinerNode Node = new JCOutlinerNode(VecVisible,NodeRoot);
                    Node.setUserData(new Integer(Qry.getI("aic_logging")));
                    JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(Node.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():Node.getStyle());
                    //StyNode.setForeground(Qry.getI("aic_logging")!=g.getLog()?new Color(0,150,0):Color.blue);

                    Node.setStyle(StyNode);
            }

    }
    else
      Static.printError("Prozesse.fillOutliner(): ");

    Out.folderChanged(NodeRoot);
    Qry.free();
  }

  private void Refresh()
  {
    iAnz++;
    checkStatus();
    FillOutliner();
    //Lbl.setText("Aufruf "+iAnz);
    show();
  }
}
