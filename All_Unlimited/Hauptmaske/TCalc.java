package All_Unlimited.Hauptmaske;

import java.awt.Window;
import java.net.Socket;
//import java.util.Scanner;

import javax.swing.JFrame;
import java.util.Vector;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Formular;
import javax.swing.JButton;
import java.io.*;
import All_Unlimited.Allgemein.Gauge;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class TCalc
{
  //private static final int VERSION=4;

  public static Tabellenspeicher TabCalc=null;
  private static Calc c=null;
  //private static Formular FomAServer=null;
  //private static AUOutliner GidAServer=null;
  //private static Tabellenspeicher TabAServer=null;
  //private static Vector<Integer> VecAServer=new Vector<Integer>();
  //private static int iAb=0;
  //private static javax.swing.Timer timer=null;
  //private static String sAServer=null;
  public static String sErgebnis=null;

  /*public static void free()
  {
    if (timer != null)
    {
      timer.stop();
      timer=null;
    }
    if (FomAServer != null)
    {
      FomAServer.thisFrame.dispose();
      FomAServer=null;
    }
  }*/

  /*private static String writeAServer(String s,boolean bGet,Vector Vec)
  {
    Static.printError("writeAServer:"+s);
    Static.printError("Appikation-Server momentan nicht verfügbar!");
    return "";
  }*/

  /*public static String writeAServer(String s,boolean bGet,Vector Vec)
  {
    long lClock=Static.get_µs();
    //String sServer=Static.sAServer;
    String sIP=sAServer.substring(0,sAServer.indexOf(':'));
    int iPort=Integer.parseInt(sAServer.substring(sAServer.indexOf(':')+1));
    String sReturn="";
    try
    {
      Socket server = new Socket(sIP, iPort);
      //OutputStream out = server.getOutputStream();
      //out.write(s.getBytes());
      PrintWriter out = new PrintWriter(server.getOutputStream(),true);
      out.println(s+(Vec==null?"":"*"+Vec.size()));
      if (Vec != null)
        for(int i=0;i<Vec.size();i++)
          out.println(Vec.elementAt(i));
      if (bGet)
      {
        Scanner in =new Scanner(server.getInputStream());
        sReturn =in.nextLine();
      }
    }
    catch (Exception e2)
      {
        //bError=true;
        Static.printError("Problem mit Socket-Server "+sAServer+":"+e2);
        sAServer=null;
      }
    long lClock2=Static.get_µs();
    System.out.println("[writeAServer:"+s+"->"+sReturn+"]:"+(lClock2-lClock)+" µs");
    return sReturn;
  }

  public static void closeAServer()
  {
    if (sAServer != null)
      writeAServer("X",false,null);
  }*/

  public static Tabellenspeicher getAbfrage(Global g,int iModell,int iBitsM,Vector Vec,int iAbfrage, JFrame Fom)
  {
    long lClock = Static.get_ms();
    Socket server=AClient.connect();
    if (server==null)
      return null;
    else
      try
      {
        ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        out.writeObject("calc");
        out.writeObject(""+iModell);
        out.writeObject(g.getZA(0));
        out.writeObject(g.getVon());
        out.writeObject(g.getBis());
        out.writeObject(""+g.getMandant());
        out.writeObject(g.saveJokerStt(false));
        out.writeObject(Vec);
        out.writeObject(""+iAbfrage);
        out.writeObject(""+iBitsM);
        out.writeObject(""+g.getBenutzer());
        out.writeObject(""+g.getSprache());
        out.writeObject(""+g.getLand());
        out.writeObject(""+g.getZone());
        //out.writeObject(P);
        Tabellenspeicher Tab=null;
        //if (iAbfrage>0 || (iBitsM & Global.cstThread)==0)
        {
          ObjectInputStream in = new ObjectInputStream(server.getInputStream());
          String s = (String)in.readObject();
          Gauge Gau=null;
          while (!s.equals("tab"))
          {
            //Tabellenspeicher Tab1=(Tabellenspeicher)in.readObject();
            //Tab1.showGrid("Tab1");
            //g.fixInfo("retour:"+s);
            if (s.equals("joker"))
              g.loadJokerStt((Tabellenspeicher)in.readObject());
            else if (s.equals("info"))
            {
              boolean bInfo=in.readBoolean();
              boolean b2=in.readBoolean();
              String sDaten = (String)in.readObject();
              Message Msg = new Message(bInfo ? Message.INFORMATION_MESSAGE:Message.WARNING_MESSAGE, Fom, g,bInfo ? b2 ? 30:10:20);
              if (bInfo || !b2)
                Msg.showDialog(bInfo ? b2 ? "Testmeldung":"Info":"Modellfehler",new String[] {sDaten});
              else
              {
                Msg.sGruppe="Fehlermeldung";
                Msg.showDialog(sDaten);
              }
            }
            else if (s.equals("msg"))
            {
              Message Msg = new Message(Message.YES_NO_OPTION, Fom, g);
              if (in.readBoolean())
                Msg.sGruppe = "Fehlermeldung";
              String sTitel = (String)in.readObject();
              String sDaten = (String)in.readObject();
              g.fixInfo("msg:" + sTitel + "/" + sDaten);
              //out.writeBoolean(Msg.showDialog(sTitel,new String[] {sDaten}) == Message.YES_OPTION);
              boolean b = Msg.showDialog(sTitel, new String[]
                                         {sDaten}) == Message.YES_OPTION;
              String sb = b ? "1" : "0";
              out.writeObject(sb);
              g.fixInfo("write boolean=" + sb);
            }
            else if (s.equals("dlg"))
            {
              Tabellenspeicher TabDlg = (Tabellenspeicher)in.readObject();
              String sTitel = (String)in.readObject();
              //g.fixInfo("sTitel="+sTitel);
              int i = in.readInt();
              //g.fixInfo("dlg:" + sTitel + "/" + i);
              Tabellenspeicher TabSp = (Tabellenspeicher)in.readObject();
              //new Tabellenspeicher(g,TabSp).showGrid("Spalten für:"+sTitel);
              boolean b = new Modell_Dialog(Fom, g, sTitel, i,0/*Qry*/, TabDlg, TabSp).bOk;
              out.writeObject(TabDlg);
              //g.fixInfo("write Tab");
              String sb = b ? "1" : "0";
              out.writeObject(sb);
              //g.fixInfo("write boolean=" + sb);
            }
            else if (s.equals("GaugeOpen"))
            {
              String sText = (String)in.readObject();
              int i=in.readInt();
              boolean b=in.readBoolean();
              Gau = new Gauge(sText, 0, i, "", g, b);
            }
            else if (s.equals("GaugeMom"))
            {
              String sText = (String)in.readObject();
              int i=in.readInt();
              if (i<-1)
                Gau.setText(sText);
              else if (i>-1)
                Gau.setText(sText,i);
            }
            else if (s.equals("GaugeClose") && Gau!=null)
            {
              Gau.setText("", Gau.getMax());
              Gau.free();
              Gau = null;
            }
            s = (String)in.readObject();
          }
          //if (iAbfrage>0)
          Tab = (Tabellenspeicher)in.readObject();
          //if (iAbfrage==0)
          //  Tab.showGrid("Tab");
        }
        out.close();
        server.close();
        if (iAbfrage>0)
          g.clockInfo("getAbfrage " + iModell + "/" + iAbfrage, lClock);
        return Tab;
      }
      catch (Exception e2)
      {
        //e2.printStackTrace();
        g.printError("TCalc.getAbfrage:" + e2,g.ModellToBegriff(iModell));
        return null;
      }
  }

  public static void TimerBerechnung(Global g,Formular Fom,int iAic)
  {
    long lClock = Static.get_ms();
    String s = "select aic_bew_Pool aic,gueltig,(select aic_begriff from bew_begriff p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerModell +
        ") aic_begriff" +
        ",(select aic_stamm from bew_stamm p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerStamm + ") stamm" +
        ",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerZR + ") von" +
        ",(select bis from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerZR + ") bis" +
        ",(select spalte_boolean from bew_boolean p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerSperre + ") Sperre" +
        ",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerFort + ") fort" +
        ",(select von from bew_von_bis p2 where p.aic_bew_pool=p2.aic_bew_pool and aic_eigenschaft=" + g.iTimerFertig + ") fertig from bew_pool p where aic_bew_pool=" + iAic;
    Tabellenspeicher Tab = new Tabellenspeicher(g, s, true); //.showGrid("Timer "+iAic);
    g.clockInfo("TimerBerechnung " + iAic, lClock);
    if (Tab.getB("Sperre") && g.delSperren()==0)
      new Message(Message.WARNING_MESSAGE, Fom.thisJFrame(), g).showDialog("wird_bereits_berechnet");
    else
    {
      g.bISQL=true;
      SQL Qry = new SQL(g);
      Qry.add("aic_bew_pool", iAic);
      Qry.add("aic_eigenschaft", g.iTimerSperre);
      Qry.add("spalte_boolean", 1);
      Qry.add("aic_logging",g.getLog());
      if (Qry.insert("bew_boolean", false) < 0)
      {
        new Message(Message.WARNING_MESSAGE, Fom.thisJFrame(), g).showDialog("wird_bereits_berechnet");
        Qry.free();
        g.bISQL=false;
        return;
      }
      g.bISQL=false;
      java.sql.Timestamp tsVon = g.getVon();
      java.sql.Timestamp tsBis = g.getBis();
      g.setVon(Tab.getTimestamp("von"));
      g.setBis(Tab.getTimestamp("bis"));
      g.testInfo("von-bis="+g.getVon()+"-"+g.getBis());
      //new Calc(null, g, Tab.getI("aic_begriff"), true, Static.AicToVec(Tab.getI("stamm")), false);
      int iPosB=g.TabBegriffe.getPos("Aic",Tab.getI("aic_begriff"));
      Berechnen(g,Tab.getI("aic_begriff"),iPosB>=0?g.TabBegriffe.getI(iPosB,"bits"):0,Tab.getI("stamm"),null,Fom,0,null);
      if (Tab.getInhalt("fort") == null)
      {
        g.exec("delete from Bew_von_bis where aic_bew_pool=" + iAic + " and aic_eigenschaft=" + g.iTimerFertig);
        Qry.add("aic_bew_pool", iAic);
        Qry.add("aic_eigenschaft", g.iTimerFertig);
        Qry.addnow("von");
        Qry.add("dauer", 0);
        Qry.insert("Bew_von_bis", false);
        Qry.exec("update bew_pool set LDATE2="+Static.DateToInt(new java.util.Date())+" where aic_bew_pool="+iAic);
      }
      Qry.free();
      g.exec("delete from bew_boolean where aic_bew_pool=" + iAic + " and aic_eigenschaft=" + g.iTimerSperre);
      g.setVon(tsVon);
      g.setBis(tsBis);
    }
  }

/*public static boolean IsAlive()
{
  //long lClock = Static.get_ms();
  if (sAServer == null)
    return false;
  String s=writeAServer("A",true,null);
  //g.clockInfo("SoapIsAlive-"+s,lClock);
  return s.equals("B");
}*/

/*public static int Berechnen(Global g,int iModell,int iStamm,Vector VecAic,Window thisFrame,int iProt)
{
  g.checkModelle();
  int iPos=g.TabModelle.getPos("aic_modell", iModell);
  if (iPos>=0)
    return Berechnen(g,g.TabModelle.getI(iPos,"aic_begriff"),g.TabModelle.getI(iPos,"bits")|Global.cstMenge,iStamm,VecAic,thisFrame,iProt);
  else
  {
    Static.printError("TCalc.Berechnen: Modell "+iModell+" nicht gefunden");
    return -1;
  }
}*/

public static void setButton(Global g,JButton Btn,String s,boolean b)
{
  if (Btn!=null)
  {
    //Btn.setEnabled(b);
    //g.fixtestInfo(Save.now()+"->setButton:"+Btn.getText()+"->"+b);
    Btn.setText(b ? s:g.getShow("berechne"));
    //Btn.setForeground(b ? g.ColThread:g.ColCalc);
    //Btn.getParent().repaint();
  }
}

public static int Berechnen(final Global g,final int iAicBegriff,final int iMbits,int iStamm,Vector VecAic,final Formular Fom,final int iProt,final JButton Btn)
      {
        g.fixtestInfo("Berechnen:"+g.getBegBez(iAicBegriff)+" mit "+((iMbits&Global.cstBew)>0 ? "Bew=":"Stamm=")+iStamm);
        g.progInfo("----------------------------------- Berechnen"+iAicBegriff+":"+iStamm+"/"+VecAic);
        final long lClock = Static.get_ms();
        int iVerlauf=g.SaveVerlauf(iAicBegriff,(iMbits&Global.cstBew)>0?0:iStamm,(iMbits&Global.cstBew)>0?iStamm:0);
        if ((iMbits& Global.cstOhneStamm)>0)
          iStamm=0;
        final Vector Vec=(iMbits&Global.cstMenge)>0 && VecAic!=null && VecAic.size()>0 ? VecAic: Static.AicToVec(iStamm);
        //boolean bError=true;
        String sError=null;
        int iQry=0;
        Tabellenspeicher Tab=null;
        //boolean bSS= AClient.sAServer != null && !g.Debug() &&(iMbits&Global.cstAServer)>0;// && IsAlive();
        if (AClient.UseAServer(iMbits) && !g.Debug())
        {
            //final int iStamm2=iStamm;
            /*SQL Qry=new SQL(g);
            Qry.add("AIC_Logging",g.getLog());
            Qry.add("AIC_Begriff",iAicBegriff);
            Qry.add0("AIC_Stamm",iStamm);
            Qry.add("VON",g.getVon());
            Qry.add("BIS",g.getBis());
            Qry.addnow("ERSTELLT");
            final int iAic=Qry.insert("ASERVER",true);
            showAServer(g);
            VecAServer.addElement(new Integer(iAic));
            if (timer != null && FomAServer != null && FomAServer.thisFrame.isVisible())
              timer.start();
            Qry.free();*/
          if ((iMbits & Global.cstThread)==0)
            Tab=TCalc.getAbfrage(g,g.BegriffToModell(iAicBegriff),iMbits,Vec,0,Fom.thisJFrame());
          else
          {
            //if (Btn!=null)
            //{
            //  Btn.setEnabled(false);
            //}
            new Thread(new Runnable()
            {
              public void run()
              {
                String s=Btn==null?null:Btn.getText();
                setButton(g,Btn,null,false);
                /*if (Btn!=null)
                {
                  Btn.setForeground(g.ColCalc);
                  Btn.setText();
                  Btn.setEnabled(false);
                }*/
                TCalc.getAbfrage(g,g.BegriffToModell(iAicBegriff),iMbits,Vec,0,Fom.thisJFrame());
                String sBez=g.TabBegriffe.getBezeichnungS(iAicBegriff);
                g.clockInfo("Berechne im Hintergrund am Server: "+sBez,lClock);
                setButton(g,Btn,s,true);
                Fom.bCalc2=false;
                new Message(Message.INFORMATION_MESSAGE, Fom.thisJFrame(), g, 0).showDialog("berechnet_im_Hintergrund", new String[] {sBez,Fom.getBez()});
                /*if (Btn!=null)
                {
                  Btn.setEnabled(true);
                  Btn.setText(s);
                  Btn.setForeground(g.ColThread);
                }*/
              }
            }).start();
            return 0;
          }
          if (Tab!=null)
          {
              sError = Tab.posInhalt("Bezeichnung", "Error") ? Tab.getS("Inhalt") : null;
              iQry = Tab.posInhalt("Bezeichnung", "Qry") ? Tab.getI("Inhalt") : 0;
              sErgebnis = Tab.posInhalt("Bezeichnung", "Ergebnis") ? Tab.getS("Inhalt") : null;
          }
          g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClock,false);
          g.clockInfo("Berechne am Server: "+g.TabBegriffe.getBezeichnungS(iAicBegriff),lClock);
          //lClock = Static.get_ms();
          //bError=false;
            /*new Thread(new Runnable()
            {
              public void run()
              {
                try
                {
                  //long lClock2 = Static.get_ms();
                  //g.testInfo("Starte AServer-Berechnung");
                  writeAServer("C"+iAic+":"+iAicBegriff + ":" + iStamm2+":"+g.getMandant()+":"+iMbits+":"+g.getVon().getTime()+":"+g.getBis().getTime(),true,Vec);
                  //g.clockInfo("AServer-Berechnung:"+s,lClock2);
                  if (Static.bSpeichernAnzeigen)
                  {
                    boolean bBerechnet=false;
                    while (!bBerechnet)
                    {
                      long lClocks1=Static.get_µs();
                      String sEnde=SQL.getString(g,"select ende from aserver where aic_aserver=?",""+iAic);
                      //long lClocks2=Static.get_µs();
                      bBerechnet=sEnde != null && !sEnde.equals("");
                      g.clockµInfo("AServer "+iAic+"->"+sEnde,lClocks1);
                      Static.sleep(1000);
                    }
                    String sModell = g.TabBegriffe.getBezeichnungS(iAicBegriff);
                    String sStamm = iStamm2 > 0 ? g.getBegriffS("Show", "fuer") + " " +g.getStamm(iStamm2):null;
                        //SQL.getString(g, "select bezeichnung from stammview2 where aic_rolle is null and aic_stamm=" + iStamm2):null;
                    new Message(Message.INFORMATION_MESSAGE, (JFrame)thisFrame, g, 5).showDialog("berechnet", new String[] {sModell + (sStamm == null ? "" : " " + sStamm)});
                  }
                }
                catch(Exception e) {
                  Static.printError("AServer-Fehler:" + e);
                }
              }
            }).start();*/
        }
        /*if (bError)
        {
          int pane = new Message(Message.YES_NO_OPTION,(JFrame)thisFrame,g).showDialog("Lokal_Rechnen");
          bError= pane == Message.YES_OPTION;
        }*/

        if (Tab == null)
        {
          c=null;
          Calc.Msg=null;
          if ((iMbits&(Global.cstAbbruch+Global.cstThread))==0)
            Calc(g,iMbits,iAicBegriff,Fom.thisFrame,iProt,Vec);
          else
          {
            final java.sql.Timestamp dtVonC=(java.sql.Timestamp)g.getVon().clone();
            final java.sql.Timestamp dtBisC=(java.sql.Timestamp)g.getBis().clone();
            if (g.runThread(iAicBegriff))
            {
              new Message(Message.INFORMATION_MESSAGE, Fom.thisJFrame(), g).showDialog("laeuft_bereits");
              return -1;
            }
            if ((iMbits&Global.cstAbbruch)>0)
            {
              Calc.Msg = new Message(Message.CANCEL_OPTION, Fom.thisJFrame(), g, -2);
              Calc.Msg.showDialog("rechne", new String[] {g.getBegBez(iAicBegriff)});
            }
            new Thread(new Runnable()
            {
              public void run()
              {
        	try
        	{
                  boolean bThread=(iMbits&Global.cstThread)>0;
                  String s=Btn==null?null:Btn.getText();
                  if (bThread) setButton(g,Btn,null,false);
                  Global g2=bThread ? new Global(g,true):null;
                  g.startThread(Thread.currentThread(),g2,iAicBegriff);
                  long lClock2=Static.get_ms();
        	  Calc.bPause=false;
                  g2.setVonBis(dtVonC,dtBisC);
                  //g2.setDebug(true);
        	  Calc(bThread ? g2:g,iMbits,iAicBegriff,Fom.thisFrame,iProt,Vec);
                  if (g2 != null)
                  {
                    g.testInfo("Bereich:"+g2.getVon()+" - "+g2.getBis());
                    g2.unConnect();
                  }
                  else
                    g.testInfo("g2 ist null");
        	  if (Calc.Msg != null)
                  {
                    Calc.Msg.dispose();
                    Calc.Msg = null;
                  }
        	      String sBez=g.getBegBez(iAicBegriff);
                  g.clockInfo("Thread "+sBez,lClock2);
                  g.endThread(Thread.currentThread());
                  if (bThread)
                  {
                    setButton(g, Btn, s, true);
                    Fom.bCalc2=false;
                    new Message(Message.INFORMATION_MESSAGE, Fom.thisJFrame(), g, 0).showDialog("berechnet_im_Hintergrund", new String[] {sBez,Fom.getBez()});
                  }
        	}
        	catch(Exception e) {
        	  Static.printError("Modellfehler:" + e);
        	}
              }
            }).start();
            if (Calc.Msg != null)
            {
              Calc.Msg.setVisible(true);
              Calc.bPause = true;
            }
          }
          if (c!=null && (iMbits&Global.cstRecalc)>0)
          {
            if (TabCalc==null)
              TabCalc=new Tabellenspeicher(g,new String[] {"Aic","Calc"});
            TabCalc.addInhalt("Aic",iAicBegriff);
            TabCalc.addInhalt("Calc",c);
          }
          if (c==null && TabCalc != null)
            c=(Calc)TabCalc.getInhalt("Calc");
          sError=c==null ? null:c.Fehler();
          long lDlgZeit= c==null ? 0:c.DlgZeit();
          //bError=sError != null;
          g.SaveVerlaufFertig(iVerlauf,Static.get_ms()-lClock,sError != null);
          g.clockInfo("Berechne am Client "+g.TabBegriffe.getBezeichnungS(iAicBegriff)+"("+iAicBegriff+"/"+Vec+")",lClock+lDlgZeit);
          if ((iMbits& Global.cstErgebnis)>0)
            sErgebnis=c.getErgebnis();
          if (sError != null && !sError.equals(""))
            new Message(Message.WARNING_MESSAGE, Fom.thisJFrame(), g).showDialog("Modell_Fehler",new String[] {""+sError});
          else
            iQry=(iMbits& Global.cstUseQry)>0 ? c.getQry():0;
        }
        return sError != null ? -1:iQry;
      }

      private static void Calc(Global g,int iMbits,int iAicBegriff,Window thisFrame, int iProt, Vector Vec)
      {
      if (TabCalc==null || (iMbits&Global.cstRecalc)==0 || !TabCalc.posInhalt("Aic",iAicBegriff))
        c=new Calc(thisFrame,g,iAicBegriff,true,Vec,-1,null,iProt);
      else
        ((Calc)TabCalc.getInhalt("Calc")).reCalc(Vec,true);
      }

      /*private static void RefreshTab(Global g)
      {
        long lClock=Static.get_µs();
        TabAServer=new Tabellenspeicher(g,"select aic_begriff Modell,s.bezeichnung Stammsatz,von,bis,Anfang,Ende"+
                               ",(case when Status>0 then '"+Static.sJa+"' else '"+Static.sNein+"' end) Fehler"+
                               ",(case when "+g.bit("Art",2)+" then '"+Static.sJa+"' else '"+Static.sNein+"' end) recalc,aic_aserver aic,null farbe from aserver"+
                               //" from begriff b join aserver on b.aic_begriff=aserver.aic_begriff"+
                               " left outer join stammview2 s on aserver.aic_stamm=s.aic_stamm and s.aic_rolle is null where aserver.aic_logging="+g.getLog()+
                               (iAb>0?" and aic_aserver>"+iAb:"")+" order by aic_aserver desc",true);
        for (TabAServer.moveFirst();!TabAServer.out();TabAServer.moveNext())
        {
          TabAServer.setInhalt("Modell", g.TabBegriffe.getBezeichnungS(TabAServer.getI("Modell")));
          TabAServer.setInhalt("Farbe",(TabAServer.isNull("Ende") ? Color.RED:Color.black).getRGB());
        }
        TabAServer.showGrid(GidAServer);
        if (!TabAServer.posNull("Ende",true))
          timer.stop();
        else
        {
          int lC=(int)(Static.get_µs()-lClock)/20;
          timer.setDelay(lC<500 ? 500:lC>5000? 5000:lC);
        }
        //long lClock2=Static.get_µs();
        g.clockµInfo(Save.now()+" RefreshTab",lClock);
      }*/

      /*public static void showAServer(final Global g)
      {
        if (FomAServer==null)
        {
          FomAServer=new Formular("AServer",null,g);//new JFrame(g.getBegriff("Dialog","AServer"));
          GidAServer = new AUOutliner();
          FomAServer.getFrei("Outliner").add(GidAServer);

          final JButton BtnRefresh=FomAServer.getButton("Refresh");
          final JButton BtnClear=FomAServer.getButton("Clear");
          JButton BtnBeenden=FomAServer.getButton("Beenden");
          ActionListener AL=new ActionListener()
          {
            public void actionPerformed(ActionEvent ev)
            {
              Object Btn = ev.getSource();
              if (Btn == BtnRefresh)
                RefreshTab(g);
              else if (Btn == BtnClear)
                iAb=(int)TabAServer.max("aic");
            }
          };
          Static.addActionListener(BtnRefresh,AL);
          Static.addActionListener(BtnClear,AL);
          if (BtnBeenden != null)
          {
            Action cancelKeyAction = new AbstractAction()
            {
              private static final long serialVersionUID = -2873973642077210456L;

              public void actionPerformed(ActionEvent e)
              {
                FomAServer.hide();
              }
            };
            Static.Escape(BtnBeenden, FomAServer.thisFrame, cancelKeyAction);
            BtnBeenden.addActionListener(cancelKeyAction);
          }
          timer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if (FomAServer.thisFrame.isVisible())
                RefreshTab(g);
            }
          });
          timer.setDelay(1000);
          if (!VecAServer.isEmpty())
            timer.start();
        }
        RefreshTab(g);
        FomAServer.show();
      }*/

}
