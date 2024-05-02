/*
    All_Unlimited-Allgemein-Tabellenspeicher.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import java.text.DecimalFormatSymbols;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import jclass.util.JCVector;
import All_Unlimited.Allgemein.Anzeige.BewEig;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.Memo1;
//import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Anzeige.AddUp;
import All_Unlimited.Allgemein.Anzeige.Html;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Print.DruckHS;

import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.TextField;

import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
//import javax.swing.JWindow;

import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCActionListener;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;

public class Tabellenspeicher extends java.lang.Object implements Serializable
{
    /**
     * clearAll Method
     */

    private static final long serialVersionUID = -123456L;

    public void clearAll()
    {
		for(int i=0;i<VecInhalt.size();i++)
			((Vector)VecInhalt.elementAt(i)).removeAllElements();
		//VecInhalt.removeAllElements();
		iPos=0;
		//for(int i=0;i<VecTitel.size();i++)
		//	VecInhalt.addElement(new Vector());
		bOut=true;
    }
    public int getPos()
    {
        return bOut ? -1:iPos;
    }
	@SuppressWarnings("unchecked")
	public boolean setPos(int i)
	{
		VecSpalte=(Vector) VecInhalt.elementAt(0);
		bOut = VecSpalte.size()<=i;
		if(!bOut)
			iPos=i;
		return(!bOut);
	}
	public void push()
	{
		//g.progInfo("Push "+iPos+"/"+bOut);
		int iBits=(bOut?1:0)+(bEof?2:0)+(bBof?4:0);
		stack.push(new Integer(iPos+iBits*0x01000000));
		//g.fixInfo("Push "+iPos+"/"+bOut+"/"+bEof+"/"+bBof+"/"+stack);
	}

        public boolean Empty_Stack()
        {
          return stack.empty();
        }

        public String getPush()
        {
          if (stack.empty())
              return "";
          else
          {
            String s=" push=[";
            for(int i=0;i<stack.size();i++)
            {
              int i2=stack.elementAt(i).intValue();
              int iBits=i2 / 0x01000000;
              s += (i > 0 ? "," : "") + (i2 % 0x01000000)+((iBits&1)>0 ?" Out":"")+
                  ((iBits&2)>0 ?" Eof":"")+((iBits&4)>0 ?" Bof":"");
            }
            return s+"]";
          }
        }

        public void popAll()
        {
          while (!stack.empty())
            stack.pop();
        }

	public void pop()
	{
		if(stack.empty())
			g.defInfo("Tabellenspeicher.pop: Es wurde schon komplett zurückgesetzt!");
		else
		{
			int i=stack.pop().intValue();
			int iBits=i / 0x01000000;
			iPos=i % 0x01000000;
			bOut = (iBits&1)>0;
                        if (iPos>=size())
                          bOut=true;
			bEof = (iBits&2)>0;
			bBof = (iBits&4)>0;
			//g.fixInfo("Pop "+iPos+"/"+bOut+"/"+bEof+"/"+bBof+"/"+stack);
		}
		//g.progInfo("Pop "+iPos+"/"+bOut+"/"+stack);
		//if (bOut)
		//	iPos=VecSpalte.size()-1;
	}
	
	public int getAic(String rsKennung)
	{
          if (rsKennung.equals(""))
              return 0;
          int iPos=getPos(sKennung,rsKennung);
          return iPos<0 ? 0:getI(iPos,sAIC);
	}
	
	public int getAicIC(String rsKennung)
	{
          if (rsKennung.equals(""))
              return 0;
          int iPos=getPosIC(sKennung,rsKennung);
          return iPos<0 ? 0:getI(iPos,sAIC);
	}
	
	public void change()
	{
		if(stack.empty())
			g.defInfo("Tabellenspeicher.change: Es wurde schon komplett zurückgesetzt!");
		else
		{
			stack.pop();
			push();
			//stack.push(new Integer(bOut?-2:iPos));
			//g.progInfo("change "+iPos+"/"+bOut+"/"+stack);
		}
	}
	
	public String printStack()
	{
		return ""+stack;
	}
	
    public String getBezeichnung(String rsKennung)
    {
		return posInhalt(sKennung,rsKennung) ? getS(sBezeichnung) : rsKennung;
    }

    public String getBezeichnungS(String rsKennung)
    {
      //push();
      //String s=getBezeichnung(rsKennung);
      //pop();
    	int i=getPos(sKennung,rsKennung);
      return i<0 ? rsKennung:getS(i,sBezeichnung);
    }

  public String getBezeichnung(int iAic)
  {
    return posInhalt(sAIC,iAic) ? (String)getInhalt(sBezeichnung) : ""+iAic;
  }

  public String getBezeichnungS(int iAic)
  {
	  return getBezeichnungS(iAic,false);
  }

  public String getBezeichnungS(int iAic,boolean bAic)
  {
    //push();
    //String s=getBezeichnung(iAic);
    //pop();
    if (iAic==0 && !sAIC.equals("Nr"))
      return "";
    int i=getPos(sAIC,iAic);
    return i<0 ? ""+iAic:getS(i,sBezeichnung)+(bAic ? " ("+iAic+")":"");
  }
  
  public String getBez(int iAic)
  {
	  if (iAic==0)
		  return null;
	  int i=getPos(sAIC,iAic);
	  return i<0 ? "Aic="+iAic:Static.beiLeer(getS(i,sBezeichnung),getS(i,sKennung));
  }
  
  public String getBezO(int iAic)
  {
	  if (iAic<=0)
		  return Static.sLeer;
	  int i=getPos(sAIC,iAic);
	  return i<0 ? Static.sLeer:getS(i,sBezeichnung);
  }

  public String getInfoS(int iAic,String sInfo)
  {
    int i=getPos(sAIC,iAic);
    return i<0 ? "":getS(i,sInfo);
  }
  
  public String getGS(int iAic,String sInfo)
  {
    int i=getPos(sGruppe,iAic);
    return i<0 ? "":getS(i,sInfo);
  }

  public String getKennung(int iAic)
  {
    //return posInhalt(sAIC,iAic) ? (String)getInhalt(sKennung) : ""+iAic;
	if (iAic==0)
		return "";
    int i=getPos(sAIC,iAic);
    return i<0 ? "Aic="+iAic:getS(i,sKennung);
  }

	public Tabellenspeicher(Global rg,Tabellenspeicher Tab2)
	{
		g = rg;
		//g.add("Tc");
		copyFrom(Tab2);
		bLeer = false;
		Count.add(Count.Tabellenspeicher);
	}

	public Tabellenspeicher(Global rg,String[] sT)
	{
		g = rg;
		//g.add("T-");
                VecBezeichnung=new Vector<String>();
		for(int i=0;i<sT.length;i++)
		{
                  VecBezeichnung.addElement(sT[i]);
                  VecTitel.addElement(sT[i].toUpperCase());
                  VecInhalt.addElement(new Vector());
		}
		bLeer = true;
		Count.add(Count.TabLeer);
	}

      public void addTitel(String s)
      {
        int iAnz=size();
        VecTitel.addElement(s.toUpperCase());
        if (VecBezeichnung!=null)
          VecBezeichnung.addElement(s);
        Vector<Object> Vec=new Vector<Object>();
        for (int i=0;i<iAnz;i++)
          Vec.addElement(null);
        VecInhalt.addElement(Vec);
      }

	public Tabellenspeicher(Global rg,Vector Vec)
	{
		g = rg;
		//g.add("T-");
		//VecBezeichnung=Vec;
		VecBezeichnung=new Vector<String>();
		int iNull=0;
		for(int i=0;i<Vec.size();i++)
		{
			if (Vec.elementAt(i)==null)
				iNull++;
			else
			{
				String s = ((String)Vec.elementAt(i)).toUpperCase();
				while(VecTitel.contains(s))
					s+="+";
				VecTitel.addElement(s);
				VecBezeichnung.addElement((String)Vec.elementAt(i));
			}
		}
		if (iNull>0)
			g.progInfo(iNull+" Nullspalten");
		//g.progInfo("VecBezeichnung="+VecBezeichnung);
		//g.progInfo("VecTitel="+VecTitel);
		for(int i=0;i<VecTitel.size();i++)
			VecInhalt.addElement(new Vector());
		bLeer = true;
		Count.add(Count.TabLeer);
	}

        public Tabellenspeicher(Tabellenspeicher TabSpalten,Global rg)
	{
          g = rg;
          VecBezeichnung=new Vector<String>();
          boolean bCalc=TabSpalten.exists("Abfrage");
          int iAbfrage=bCalc ? TabSpalten.getI("Abfrage"):0;
          TabSpalten.push();
          if (bCalc)
            TabSpalten.posInhalt("Abfrage",iAbfrage);
          else
            TabSpalten.moveFirst();
          for(;!TabSpalten.eof() && (!bCalc || TabSpalten.getI("Abfrage")==iAbfrage);TabSpalten.moveNext())
          {
             VecTitel.addElement(TabSpalten.getS("Kennung").toUpperCase());
             VecBezeichnung.addElement(TabSpalten.getS("Bezeichnung"));
          }
          TabSpalten.pop();
          for(int i=0;i<VecTitel.size();i++)
            VecInhalt.addElement(new Vector());
          bLeer = true;
          Count.add(Count.TabLeer);
        }

        public void setTitel(String sOld,String sNeu)
        {
          if (VecBezeichnung==null)
            VecBezeichnung=new Vector<String>(VecTitel);
          int i = VecTitel.indexOf(sOld.toUpperCase());
          if (i>-1)
            VecBezeichnung.setElementAt(sNeu,i);
        }

        public Tabellenspeicher(Global rg,String s,boolean bRichtig, String sFile)
        {
          this(rg,s,null,bRichtig,sFile);
        }

        public Tabellenspeicher(Global rg,String s,String sBind,boolean bRichtig, String sFile)
        {
          g = rg;
          bLeer = false;
          Count.add(Count.Tabellenspeicher);
          sTT=sFile;
          long lClock=Static.get_ms();
          if (!Static.cache() || sFile==null)
            refresh(g,s, bRichtig, false,sBind);
          else if (LoadAU(sFile))
          {
            if (g.QrySave())
              g.saveSqlTime("Load", s.length(), Static.get_ms() - lClock, s, ""+getAnzahlElemente(null),sBind);
          }
          else
          {
            refresh(g,s,bRichtig,false,sBind);
            if (Static.cache())
              SaveAU(sFile);
          }
          moveFirst();
        }

        public Tabellenspeicher(Global rg,String s,boolean bRichtig)
        {
          this(rg,s,null,bRichtig);
        }

	public Tabellenspeicher(Global rg,String s,String sBind,boolean bRichtig)
	{
		//long lClock = Static.get_ms();
		//int j=0;
		g = rg;
		//g.add("T");
		//if (!bRichtig)
		//	g.printInfo(1,"Tabellenspeicher nicht Richtig:\n"+s);
		sSQL=s;
                sBV=sBind;
		refresh(g,s,bRichtig,false,sBind);
		//System.gc();
		bLeer = false;
		Count.add(Count.Tabellenspeicher);
		//g.addInfo("new Tabellenspeicher("+j+" Zeilen):"+(Static.get_ms()-lClock));
	}

	public void finalize()
	{
		//g.progInfo("finalize:"+this);
		clearAll();
		VecInhalt.removeAllElements();
		VecTitel.removeAllElements();
		Count.sub(bLeer ? Count.TabLeer : Count.Tabellenspeicher);
	}

	public void refresh(Global g)
	{
	  refresh(g,sSQL,true,true,null);
	}

//	private void refreshxx(String s)
//	{
//		refresh(s,true,true,null);
//	}

        /*private void refresh(String s,boolean bRichtig,boolean bRefresh)
        {
          powersoft.powerj.db.java_sql.Query Qry = new powersoft.powerj.db.java_sql.Query(g,null,false);
          Qry.setSQL(s);
          if (g.SafetyOpen(Qry))
          {
                try
                {
                        java.sql.ResultSet resultSet = (java.sql.ResultSet)(Qry.getResultSetObject());
                        java.sql.ResultSetMetaData meda=resultSet.getMetaData();
                        for(int i=1;i<=meda.getColumnCount();i++)
                        {
                                if(bRefresh)
                                        ((Vector)VecInhalt.elementAt(i-1)).removeAllElements();
                                else
                                {
                                        VecTitel.addElement(meda.getColumnName(i).toUpperCase().trim());
                                        VecInhalt.addElement(new Vector());
                                }
                        }
                        Qry.moveFirst();
                        while(!Qry.eof())
                        {
                                for(int i=1;i<=meda.getColumnCount();i++)
                                {
                                        String sSpalte = meda.getColumnName(i);
                                        if (bRichtig)
                                                {
                                                        powersoft.powerj.db.ConstantDataValue iVor = Qry.getValue(sSpalte);
                                                        int iTyp=meda.getColumnType(i);
                                                        int iPrec=meda.getPrecision(i);
                                                        boolean bNum=iTyp==java.sql.Types.NUMERIC || iTyp==java.sql.Types.DECIMAL;
                                                        if (iVor.isNull())
                                                                addInhalt(sSpalte,null);
                                                        else if(iTyp==java.sql.Types.TINYINT || iTyp==java.sql.Types.BIT || bNum && iPrec==1)	//Boolean
                                                                addInhalt(sSpalte,new Boolean(iVor.getIntNoThrow()==1));
                                                        else if(iTyp==java.sql.Types.INTEGER || iTyp==java.sql.Types.SMALLINT || bNum && (iPrec>1 && iPrec<11 || iPrec==0 && (sSpalte.charAt(0)=='V' || sSpalte.charAt(0)=='W')))	//Integer
                                                                addInhalt(sSpalte,iVor.getIntNoThrow());
                                                        else if(iTyp==java.sql.Types.BIGINT || bNum && iPrec>10 && iPrec<21)	//Longint
                                                                addInhalt(sSpalte,new Long(iVor.getLongNoThrow()));
                                                        else if(iTyp==java.sql.Types.CHAR || iTyp==java.sql.Types.VARCHAR && iPrec<1000)	//String
                                                                addInhalt(sSpalte,iVor.getStringNoThrow());
                                                        else if(iTyp==java.sql.Types.DOUBLE || iTyp==java.sql.Types.FLOAT || bNum)	//Double
                                                                addInhalt(sSpalte,new Double(iVor.getDoubleNoThrow()));
                                                        else if(iTyp==java.sql.Types.TIMESTAMP) // || iTyp==java.sql.Types.DATE && g.Oracle())	//DateTime
                                                                addInhalt(sSpalte,iVor.getTimestampNoThrow());
                                                        else if(iTyp==java.sql.Types.DATE)	//Date
                                                                addInhalt(sSpalte,iVor.getDateNoThrow());
                                                        else if(meda.getColumnType(i)==java.sql.Types.TIME)	//Time
                                                                addInhalt(sSpalte,iVor.getTimeNoThrow());
                                                        else if(iTyp==java.sql.Types.LONGVARCHAR || iTyp==java.sql.Types.VARCHAR)	//Memo
                                                                addInhalt(sSpalte,new Memo1(iVor.getStringNoThrow()));
                                                        else
                                                        {
                                                                addInhalt(sSpalte,Qry.getStringValue(sSpalte));
                                                                g.defInfo("Tabellenspeicher: Ungültiger Datentyp in Spalte "+sSpalte+":"+meda.getColumnTypeName(i)+'('+iTyp+')');
                                                        }
                                                }
                                        else
                                                addInhalt(sSpalte,Qry.getStringValue(sSpalte));
                                }
                                Qry.moveNext();
                         }
                }
                catch(java.sql.SQLException e)
                {
                        Static.printError("Tabellenspeicher: SQLException-"+e);
                        bFehler=true;
                }
                catch(java.lang.NullPointerException e)
                {
                  Static.printError("Tabellenspeicher: SQL-Befehl liefert kein Ergebnis:\n"+s);
                  bFehler=true;
                }
                moveFirst();
        }
        else
        {
                bFehler=true;
        }
        Qry.destroy(true,true);
      }*/

	public void refresh(Global t,String s,boolean bRichtig,boolean bRefresh,String sBind)
	{
        	long lClock2 = Static.get_ms();
                if (!bRefresh)
                  VecBezeichnung=new Vector<String>();
                /*if (!g.SaveConnect())
                {
                  Static.printError("Tabellenspeicher.refresh: keine Datenbankverbindung:"+s);
                }*/
            try
			{
                          g=t;
                          java.sql.ResultSet resultSet = g.open2(s,false,sBind);
                          if (resultSet==null)
                          {
                            bFehler=true;
                            return;
                          }
                          popAll();
                          java.sql.ResultSetMetaData meda=resultSet.getMetaData();
				for(int i=1;i<=meda.getColumnCount();i++)
				{
					if(bRefresh)
						((Vector)VecInhalt.elementAt(i-1)).removeAllElements();
					else
					{
						VecTitel.addElement(meda.getColumnLabel(i).toUpperCase().trim());
                                                VecBezeichnung.addElement(meda.getColumnLabel(i));
						VecInhalt.addElement(new Vector());
					}
					//g.testInfo(meda.getColumnLabel(i)+"/"+meda.getColumnName(i)+":"+meda.getColumnType(i));
				}
                                //resultSet.next();
                                //g.progInfo("Warning=<"+resultSet.getWarnings()+">");
                                //if (resultSet.next())
                                if (!bRichtig)
                                	Transact.fixError(g,"Tabellenspeicher.refresh: mit false aufgerufen:\n"+s);
				while(resultSet.next())
				{
					for(int i=1;i<=meda.getColumnCount();i++)
					{
						String sSpalte = meda.getColumnLabel(i);
						if (bRichtig)
                                                {
                                                        int iTyp=meda.getColumnType(i);
                                                        int iPrec=meda.getPrecision(i);
                                                        boolean bNum=iTyp==java.sql.Types.NUMERIC || iTyp==java.sql.Types.DECIMAL || g.MySQL() && iPrec==1 && iTyp==java.sql.Types.VARBINARY;
                                                        Object Obj=null;
                                                        //if (g.bISQL)
                                                        //  g.progInfo(sSpalte+':'+iTyp+"/"+iPrec+"/"+resultSet.getString(i));//+"/"+resultSet.getInt(i));
                                                        //g.fixtestError(sSpalte+':'+iTyp+"/"+iPrec+"/"+resultSet.getString(i));//+"/"+resultSet.getInt(i));
                                                        if (iTyp==java.sql.Types.NULL)
                                                          Obj = null;
                                                        else if(iTyp==java.sql.Types.TINYINT || iTyp==java.sql.Types.BIT || bNum && iPrec==1)	//Boolean
                                                          Obj=(g.MySQL() && !g.My8() ? resultSet.getInt(i)==1:resultSet.getBoolean(i)) ? Boolean.TRUE:Boolean.FALSE;
                                                        /*{
                                                          //if (g.MySQL())
                                                          //  System.out.println("Boolean="+resultSet.getByte(i)+",MySQL="+g.MySQL());//+"/"+resultSet.getInt(i)+"/"+resultSet.getBoolean(i));
                                                          boolean b=resultSet.getBoolean(i);
                                                          Obj = g.MySQL() ? !resultSet.wasNull() : b ? Boolean.TRUE : Boolean.FALSE;
                                                        }*/
                                                        else if(iTyp==java.sql.Types.INTEGER || iTyp==java.sql.Types.SMALLINT || bNum && (iPrec>1 && iPrec<11 || iPrec==0 && (sSpalte.charAt(0)=='V' || sSpalte.charAt(0)=='W')))	//Integer
                                                                Obj=new Integer(resultSet.getInt(i));
                                                        else if(iTyp==java.sql.Types.BIGINT || bNum && iPrec>10 && iPrec<21)	//Longint
                                                                Obj=new Long(resultSet.getLong(i));
                                                        else if(iTyp==java.sql.Types.CHAR || iTyp==java.sql.Types.VARCHAR && iPrec<1000)	//String
                                                                Obj=resultSet.getString(i);
                                                        //else if(meda.getColumnType(i)==java.sql.Types.FLOAT || meda.getColumnType(i)==java.sql.Types.REAL)	//Float (Single)
                                                        //	addInhalt(sSpalte,new Float(iVor.getFloatNoThrow()));
                                                        else if(iTyp==java.sql.Types.DOUBLE || iTyp==java.sql.Types.FLOAT || bNum)	//Double
                                                                Obj=new Double(resultSet.getDouble(i));
                                                        //else if(iTyp==java.sql.Types.FLOAT)	//Float
                                                        //	addInhalt(sSpalte,new Double(iVor.getDoubleNoThrow()));
                                                        else if(iTyp==java.sql.Types.TIMESTAMP || iTyp==-3 && g.MySQL() || iTyp==java.sql.Types.DATE && g.Oracle())	//DateTime
                                                        {
                                                          Date dt=resultSet.getTimestamp(i);
                                                          Obj = g.ASA() || g.MySQL() ? dt==null?null:new java.sql.Timestamp(dt.getTime()) : dt;
                                                        }
                                                        else if(iTyp==-2 && g.MySQL())
                                                        {

                                                        }
                                                        else if(iTyp==java.sql.Types.DATE) // || g.MS() && iTyp==java.sql.Types.BINARY)	//Date
                                                                Obj=resultSet.getDate(i);
                                                        else if(meda.getColumnType(i)==java.sql.Types.TIME)	//Time
                                                                Obj=resultSet.getTime(i);
                                                        //else if(iTyp==-100)
                                                        //	addInhalt(sSpalte,iVor.getTimestampNoThrow()); //!!! funktioniert nicht
                                                        //else if(iTyp==java.sql.Types.LONGVARBINARY)	//Bild
                                                        //	addInhalt(sSpalte,new ImageIcon(iVor.getBytesNoThrow()).getImage());
                                                        else if(iTyp==java.sql.Types.LONGVARCHAR || iTyp==java.sql.Types.VARCHAR || iTyp==java.sql.Types.CLOB)	//Memo
                                                        {
                                                          Obj=resultSet.getString(i);
                                                          //g.progInfo("Memo="+Obj);
                                                          Obj = resultSet.wasNull() ? null : new Memo1((String)Obj);
                                                        }
                                                        else
                                                        {
                                                                Obj=resultSet.getString(i);
                                                                g.defInfo("Tabellenspeicher: Ungültiger Datentyp in Spalte "+sSpalte+":"+meda.getColumnTypeName(i)+'('+iTyp+')');
                                                        }
                                                        addInhalt(sSpalte,resultSet.wasNull()?null:Obj);
                                                }
						else
                                                {
                                                  //int iTyp=meda.getColumnType(i);
                                                  //g.testInfo(sSpalte+":"+iTyp+"/"+meda.getColumnTypeName(i)+"/"+resultSet.getString(i));
                                                  Object Obj=resultSet.getString(i);
                                                  /*if(iTyp==java.sql.Types.TINYINT || iTyp==java.sql.Types.BIT)
                                                    Obj=new Boolean(resultSet.getBoolean(i));
                                                  else if(iTyp==java.sql.Types.INTEGER)
                                                    Obj=new Integer(resultSet.getInt(i));
                                                  else if(iTyp==java.sql.Types.BIGINT)
                                                    Obj=new Long(resultSet.getLong(i));
                                                  else if(iTyp==java.sql.Types.DOUBLE)
                                                    Obj=new Double(resultSet.getDouble(i));
                                                  else if(iTyp==java.sql.Types.TIMESTAMP)
                                                    Obj=resultSet.getTimestamp(i);
                                                  else if(iTyp==java.sql.Types.DATE)
                                                    Obj=resultSet.getDate(i);
                                                  else
                                                    Obj=resultSet.getString(i);*/
                                                  addInhalt(sSpalte,resultSet.wasNull()?null:Obj);
                                                  //addInhalt(sSpalte, bCall ? resultSet.getString(i) : Qry.getStringValue(sSpalte));
                                                }
					}
                                        //resultSet.next();
				}
                                resultSet.getStatement().close();
                                resultSet.close();
                                if (g.QrySave())
                                  g.saveSqlTime("tab", s.length(), Static.get_ms()-lClock2, s, ""+getAnzahlElemente(null),sBind);
			}
			catch(java.sql.SQLException e)
			{
				Transact.fixError(g,s);
				Transact.fixError(g,"Tabellenspeicher: SQLException-"+e);
                                if (g.QrySave())
                                  g.saveSqlTime("tab-error", s.length(), Static.get_ms()-lClock2, s, "-1",sBind);
				bFehler=true;
			}
                        catch(java.lang.NullPointerException e)
                        {
                          //g.fixInfo("Tabellenspeicher:"+e);
                        	Transact.fixError(g,"Tabellenspeicher: SQL-Befehl liefert kein Ergebnis:"+e+"\n"+s);
                          bFehler=true;
                        }
                        if (!bFehler)
                          moveFirst();

		//if (g.Debug())
		//	g.testInfo("--Tab:"+getAnzahlElemente(null)+" Zeilen: SQL="+(lClock2-lClock)+"ms, füllen="+(Static.get_ms()-lClock2)+"ms, refresh="+bRefresh);
		//g.saveSqlTime("Tab",getAnzahlElemente(null),Static.get_ms()-lClock);
	}

        public boolean getDir(Stack Sta,int iAb)
        {
          String sExt=""+Sta.pop();
          String sDir=""+Sta.pop();
          if (g!=null)
          g.testInfo("getDir:"+sDir+"/"+sExt);
          File[] fil = new File(sDir).listFiles();
          if (fil==null)
            return false;
          for(int i=0;i<fil.length;i++)
            if (sExt.equals("*") || fil[i].getName().endsWith("." + sExt))
            {
              newLine();
              //g.testInfo(i+".:"+fil[i].getName());
              putElementAt(iPos,iAb,fil[i].getName());
              putElementAt(iPos,iAb+1,new DateWOD(fil[i].lastModified()).toTimestamp());
              putElementAt(iPos,iAb+2,new Long(fil[i].length()));
            }
          return true;
        }

	public void checkBezeichnung()
	{
		moveFirst();
		while (!eof())
		{
			if (getS(sBezeichnung).equals(""))
				setInhalt(sBezeichnung,getInhalt(sKennung));
			moveNext();
		}
	}

	public boolean existInhalt(String sSpaltBez, Object ObjWert)
	{
		Vector Vec = getVecSpalte(sSpaltBez);
		return Vec == null ? false : Vec.indexOf(ObjWert) != -1;
	}

	public int getposInhalt(String sSpaltBez, Object ObjWert)
	{
		Vector Vec = getVecSpalte(sSpaltBez);
		return Vec == null ? -1 : Vec.indexOf(ObjWert);
	}
	
//	public boolean posDate(String sSpaltBez,DateWOD dt)
//	{
//		moveBefore();
//		return posNextDate(sSpaltBez,dt);
//	}
	
	public boolean posNextDate(String sSpaltBez,DateWOD dt,boolean bStart)
	{
		int i=-1;
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			int iPos2=bStart ? -1:iPos;
			while (i<0 && iPos2<size()-1)
			{
				iPos2++;
//				double d2=Sort.getf(VecSpalte.elementAt(iPos2));
				DateWOD dt2=Sort.getDW(VecSpalte.elementAt(iPos2));
				boolean b=dt.compareDay('=',dt2);
//				g.fixtestError("Pos="+iPos2+" -> "+dt2+" -> "+b);
				if (b)
					i=iPos2;
			}
		}
		checkPos(i);
		return i>=0;
	}
	
//	public boolean posDateTime(String sSpaltBez,DateWOD dt)
//	{
//		moveBefore();
//		return posNextDateTime(sSpaltBez,dt);
//	}
	
	public boolean posNextDateTime(String sSpaltBez,DateWOD dt,boolean bStart)
	{
		int i=-1;
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			int iPos2=bStart ? -1:iPos;
			while (i<0 && iPos2<size()-1)
			{
				iPos2++;
				boolean b=Static.Gleich2(dt, VecSpalte.elementAt(iPos2)); //dt.compareDay('=',dt2);
				if (b)
					i=iPos2;
			}
		}
		checkPos(i);
		return i>=0;
	}
	
	public boolean posNotDate(String sSpaltBez,DateWOD dt,boolean bDate,boolean bStart)
	{
//		return posNotInhalt(sSpaltBez,dt.toTimestamp(),bStart);
//		if (bStart)
//			moveBefore();
		int i=-1;
		getSpalte(sSpaltBez);
		if(!bFehler)
		{
			int iPos2=bStart ? -1:iPos;
			while (i<0 && iPos2<size()-1)
			{
				iPos2++;
				boolean b=bDate ? dt.compareDay('=',Sort.getDW(VecSpalte.elementAt(iPos2))):Static.Gleich2(dt, VecSpalte.elementAt(iPos2));
				if (!b)
					i=iPos2;
			}
		}
		checkPos(i);
		return i>=0;
	}

	public boolean posNextInhalt(String sSpaltBez,int i)
	{
		return posNextInhalt(sSpaltBez,new Integer(i));
	}
	
	private void checkPos(int i)
	{
		bPos= i != -1;
        if(bPos)
        {
                iPos=i;
                bEof=false;
                bBof=false;
                bOut=false;
        }
        else
                bOut=true;
	}

        public boolean posNextInhalt(String sSpaltBez, Object ObjWert, Object ObjWert2)
                {
                        int i;
                        getSpalte(sSpaltBez);

                        if(!bFehler)
                        {
                          i = VecSpalte.indexOf(ObjWert,iPos+1);
                          int i2 = VecSpalte.indexOf(ObjWert2,iPos+1);
                          i=i2 !=-1 && (i2<i || i==-1) ? i2:i;
                          checkPos(i);
                        }
                        else
                          bPos=false;
                        return(bPos);
                }

        public boolean posNextInhalt3(String sSpaltBez, Object ObjWert, Object ObjWert2, Object ObjWert3)
        {
                int i;
                getSpalte(sSpaltBez);
                boolean bNull= ObjWert3.equals(0);

                if(!bFehler)
                {
                  i = VecSpalte.indexOf(ObjWert,iPos+1);
                  int i2 = VecSpalte.indexOf(ObjWert2,iPos+1);
                  i= i<0 || i2>=0 && i>i2 ? i2:i;
                  int i3 = VecSpalte.indexOf(ObjWert3,iPos+1);
                  i= i<0 || i3>=0 && i>i3 ? i3:i;
                  int i4 = bNull ? VecSpalte.indexOf(null,iPos+1):-1;
                  i= i<0 || i4>=0 && i>i4 ? i4:i;
//                  i= i3 !=-1 && (i3<i || i==-1) ? i3: i2 !=-1 && (i2<i || i==-1) ? i2:i;
                  checkPos(i);
                }
                else
                  bPos=false;
                return(bPos);
        }


	public boolean posNextInhalt(String sSpaltBez, Object ObjWert)
	{
		int i;
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			i=VecSpalte.indexOf(ObjWert,iPos+1);
			bPos= i != -1;
			if(bPos)
			{
				iPos=i;
				bEof=false;
				bBof=false;
				bOut=false;
			}
			else
				bOut=true;
		}
		else
		  bPos=false;
		return(bPos);
	}
	
	public boolean posNextZahl(String sSpaltBez,double d,double dif)
	{
//		g.fixtestError("posNextZahl mit "+sSpaltBez+"/"+d+"/"+dif);
		int i=-1;
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			int iPos2=iPos;
			while (i<0 && iPos2<size()-1)
			{
				iPos2++;
				double d2=Sort.getf(VecSpalte.elementAt(iPos2));
//				g.fixtestError("Pos="+iPos2+" -> "+d2+" -> "+Math.abs(d2-d));
				if (Math.abs(d2-d)<dif)
				{
					i=iPos2;
					iPos=i;
				}
			}
		}
		else
			  bPos=false;
		return i>=0;
	}
	
	public boolean posPrevZahl(String sSpaltBez,double d,double dif)
	{
		g.fixtestError("posPrevZahl mit "+sSpaltBez+"/"+d+"/"+dif);
		int i=-1;
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			int iPos2=iPos;
			while (i<0 && iPos2>0)
			{
				iPos2--;
				double d2=Sort.getf(VecSpalte.elementAt(iPos2));
				g.fixtestError("Pos="+iPos2+" -> "+d2+" -> "+Math.abs(d2-d));
				if (Math.abs(d2-d)<dif)
				{
					i=iPos2;
					iPos=i;
				}
			}
		}
		else
			  bPos=false;
		return i>=0;
	}

        public boolean posNotInhalt(String sSpaltBez,Object Obj,boolean bStart)
        {
          getSpalte(sSpaltBez);
          if(!bFehler)
          {
            bPos=false;
            for (int i=bStart?0:iPos+1;!bPos && i<VecSpalte.size();i++)
              if(Static.Ungleich(VecSpalte.elementAt(i),Obj))
              {
                iPos=i;
                bPos=true;
              }
            if(bPos)
            {
              bEof = false;
              bBof = false;
              bOut = false;
            }
            else
              bOut = true;
          }
          else
            bPos=false;
          return(bPos);
        }

        public boolean posNotInhalt(String sSpaltBez,Object Obj1,Object Obj2,boolean bStart)
        {
          getSpalte(sSpaltBez);
          if(!bFehler)
          {
            bPos=false;
            for (int i=bStart?0:iPos+1;!bPos && i<VecSpalte.size();i++)
              if(Static.Ungleich(VecSpalte.elementAt(i),Obj1) && Static.Ungleich(VecSpalte.elementAt(i),Obj2))
              {
                iPos=i;
                bPos=true;
              }
            if(bPos)
            {
              bEof = false;
              bBof = false;
              bOut = false;
            }
            else
              bOut = true;
          }
          else
            bPos=false;
          return(bPos);
        }

        public boolean posNull(String sSpaltBez,boolean b)
        {
          getSpalte(sSpaltBez);
          if(!bFehler)
          {
            bPos=false;
            for (int i=0;!bPos && i<VecSpalte.size();i++)
              if(b==(VecSpalte.elementAt(i)==null))
              {
                iPos=i;
                bPos=true;
              }
            if(bPos)
            {
              bEof = false;
              bBof = false;
              bOut = false;
            }
            else
              bOut = true;
          }
          else
            bPos=false;
          return(bPos);
        }

  /*********/
  /** neu **/
  /*********/

  public int getPos(String sSpaltBez,int i)
  {
	//return getPos(sSpaltBez,(Object)new Integer(i));
    Vector Vec=getVecSpalte(sSpaltBez);
    int i1=Vec.indexOf(new Integer(i));
    if (i1<0)
      i1=Vec.indexOf(new Long(i));
    return i1;
  }

  public int getPos(String sSpaltBez, Object ObjWert)
  {
	if (ObjWert instanceof Integer)
		return getPos(sSpaltBez,((Integer)ObjWert).intValue());
  	Vector Vec=getVecSpalte(sSpaltBez);
  	return Vec==null ? -1:Vec.indexOf(ObjWert);
  }
  
  public int getPosIC(String sSpaltBez,String sWert)
  {
	  Vector Vec=getVecSpalte(sSpaltBez);
	  for(int i=0;i<Vec.size();i++)
          if (Sort.gets(Vec,i).equalsIgnoreCase(sWert))
            return i;
	  return -1;
  }

   public int getPos2(Object rsGruppe,String rsKennung)
  {
  	Vector Vec=getVecSpalte(sGruppe);
  	int i=Vec.indexOf(rsGruppe);
  	if (i<0)
  	{
  	  g.defInfo2("Tab.getPos2: Gruppe "+Static.print(rsGruppe)+" nicht gefunden");
  	  //showGrid();
  	  return -1;
  	}
  	Vector Vec2=getVecSpalte(sKennung);
  	i=Vec2.indexOf(rsKennung,i);
  	return i>=0 && Vec.elementAt(i).equals(rsGruppe) ? i:-1;
  }
   
  public boolean Gleich(Object Obj1, Object Obj2)
  {
	  if (Obj1 instanceof Integer || Obj2 instanceof Integer)
		  return Sort.geti(Obj2)==Sort.geti(Obj1);
	  else if (Obj1!=null)
		  return Obj1.equals(Obj2);
	  else
		  return false;
  }

  public int getPos3(Object Obj1, Object Obj2)
    {
	  //g.fixtestError("getPos3:"+sGruppe+"="+Obj1+", "+sAIC+"="+Obj2);
      if (Obj1==null || isEmpty())
        return -1;
//      int iPos=Obj1 instanceof Integer ? getPos(sGruppe,((Integer)Obj1).intValue()): getPos(sGruppe,Obj1);
      int iPos=getPos(sGruppe,Obj1);
      //g.progInfo("getPos3:"+iPos+"/"+size());
      if(iPos>=0)
      {
    	  //g.fixtestError("getPos3 "+sGruppe+"="+Obj1+"->"+iPos);
              for(;iPos>=0 && iPos+1<size() && Gleich(getInhalt(sGruppe,iPos),Obj1) && !Gleich(getInhalt(sAIC,iPos),Obj2);iPos++);
          //g.fixtestError("getPos3 "+sAIC+"="+Obj2+"->"+iPos);
              /*if(out())
                  moveLast();
              else*/ if(!Gleich(Obj1,getInhalt(sGruppe,iPos)) || !Gleich(Obj2,getInhalt(sAIC,iPos)))
                      iPos=-1;
              //bOut = false;
      }
      //else
      //  moveLast();
      return iPos;
    }

  public int getNextPos(int iPos,String sSpaltBez, Object ObjWert)
  {
    Vector Vec=getVecSpalte(sSpaltBez);
    return Vec.indexOf(ObjWert,iPos+1);
  }

  public int getNextPos(int iPos,String sSpaltBez, int i)
  {
    Vector Vec=getVecSpalte(sSpaltBez);
    //g.fixInfo("getNextPos "+i+" aus Vec="+Vec);
    int i1=Vec.indexOf(new Integer(i),iPos+1);
    if (i1<0)
      i1=Vec.indexOf(new Long(i),iPos+1);
    //g.fixInfo("getNextPos "+sSpaltBez+": "+iPos+"->"+i1);
    return i1;
  }

  public String getS(int i,String sSpaltBez)
  {
	return Sort.gets(getInhalt(sSpaltBez,i));
  }

  public int getI(int i,String sSpaltBez)
  {
	return Sort.geti(getInhalt(sSpaltBez,i));
  }

  public double getF(int i,String sSpaltBez)
  {
    return Sort.getf(getInhalt(sSpaltBez,i));
  }

  public Integer getInt(int i,String sSpaltBez)
  {
    Object Obj=getInhalt(sSpaltBez,i);
    //g.testInfo("getInt "+sSpaltBez+":"+Obj+"/"+Static.className(Obj));
    return Sort.getInt(Obj);
  }

  public java.util.Date getDate(int i,String sSpaltBez)
  {
          return getdate(getInhalt(sSpaltBez,i));
  }

  public int getI_AIC(String sSpaltBez,int iAic)
  {
      int i=getPos(sAIC,iAic);
      return i<0 ? 0:Sort.geti(getInhalt(sSpaltBez,i));
  }

  public String getS_AIC(String sSpaltBez,int iAic)
  {
      int iPos=getPos(sAIC,iAic);
      return iPos<0 ? Static.sLeer:getS(iPos,sSpaltBez);
  }

  public boolean getB(int i,String sSpaltBez)
  {
    	return getb(getInhalt(sSpaltBez,i));
  }

  public String getM(int i,String sSpaltBez)
  {
    return getm(getInhalt(sSpaltBez,i));
  }

  public java.sql.Timestamp getTimestamp(int i,String sSpaltBez)
  {
	return gettimestamp(getInhalt(sSpaltBez,i));
  }

  public long getL(int i,String sSpaltBez)
  {
	return getl(getInhalt(sSpaltBez,i));
  }

  public boolean isNull(int i,String sSpaltBez)
  {
    return getInhalt(sSpaltBez,i)==null;
  }

  public void setInhalt(int i,String sSpaltBez, Object Obj)
  {
    Vector<Object> Vec=getVecSpalte(sSpaltBez);
    boolean bFehler = i<0 || i>=Vec.size();
    if (bFehler)
    	Transact.fixError(g,getTitel()+".setInhalt(i,s,Obj): Spalte "+sSpaltBez+" hat keine Zeile "+i);
    else
      Vec.setElementAt(Obj,i);
  }

  public void setInhalt0(int iPos,String sSpaltBez, int i)
  {
    setInhalt(iPos,sSpaltBez,i==0 ? null:new Integer(i));
  }

  @SuppressWarnings("unchecked")
  public int newLine(int iPos2)
  {
    int iPos3=iPos2;
    for (int i = 0; i < VecTitel.size(); i++)
    {
      Vector<Object> Vec = (Vector)VecInhalt.elementAt(i);
      if (iPos2>=0)
        Vec.insertElementAt(null, iPos2);
      else
      {
        Vec.addElement(null);
        iPos3 = Vec.size() - 1;
      }
    }
    return iPos3;
  }
  
  public int ersetze(String s,int iAlt,int iNeu)
  {
	  int iAnz=0;
	  for(int i=0;i<size();i++)
		  if (getI(i,s)==iAlt)
		  {
			  setInhalt(i,s,iNeu);
			  iAnz++;
		  }
	 return iAnz;
  }

  /*********/
  /** alt **/
  /*********/

	public boolean posInhalt(String sSpaltBez,int i)
	{
		return posInhalt(sSpaltBez,new Integer(i),new Long(i));
	}

        public boolean posInhalt(String sSpaltBez,double d,int iAb)
        {
          d=Static.Round6(d);
          Vector Vec=getVecSpalte(sSpaltBez);
          for (int i=iAb+1;i<Vec.size();i++)
            if(d==Static.Round6(Sort.getf(Vec.elementAt(i))))
            {
              iPos=i;
              bEof=false;
              bBof=false;
              bOut=false;
              return true;
            }
          bOut=true;
          return false;
        }

	public boolean posInhalt(String sSpaltBez, Object ObjWert)
	{
		int i;
		getSpalte(sSpaltBez);

		if(bFehler)
                  bPos=false;
                else if (ObjWert==null)
                  return posNull(sSpaltBez,true);
                else
		{
            i=VecSpalte.indexOf(ObjWert);
			bPos= i != -1;
			if(bPos)
			{
				iPos=i;
				bEof=false;
				bBof=false;
				bOut=false;
			}
			else
				bOut=true;
		}
		if (bN)
		  {
		    iN++;
		    g.testInfo("!! "+getTitel()+".posInhalt("+sSpaltBez+","+ObjWert+")="+iPos+"/"+bPos+"|"+iN);
                    /*if (Static.bTest)
                      return 5/iNull>1;*/
                    Fehlerausgabe();
		  }
		return(bPos);
	}
	
	public boolean posBigger(String sSpaltBez, Object ObjWert)
	{
//		g.fixtestError("posBigger "+Sort.getf(ObjWert)+" in Spalte "+sSpaltBez);
		getSpalte(sSpaltBez);

		if(bFehler || ObjWert==null)
          bPos=false;
        else
		{
        	Vector Vec=getVecSpalte(sSpaltBez);
            for (int i=0;i<Vec.size();i++)
              if(Sort.getf(ObjWert)<Sort.getf(Vec.elementAt(i)))
				{
					iPos=i;
					bEof=false;
					bBof=false;
					bOut=false;
					return true;
				}
		}
		bOut=true;
		return false;
	}
	
	public boolean posTrue(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
        if(!bFehler)
        {
          bPos=false;
          for (int i=0;!bPos && i<VecSpalte.size();i++)
            if(getb(VecSpalte.elementAt(i)))
            {
              iPos=i;
              bPos=true;
            }
          if(bPos)
          {
            bEof = false;
            bBof = false;
            bOut = false;
          }
          else
            bOut = true;
        }
        else
          bPos=false;
		return bPos;
	}
	
	public boolean posFalse(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
        if(!bFehler)
        {
          bPos=false;
          for (int i=0;!bPos && i<VecSpalte.size();i++)
            if(!getb(VecSpalte.elementAt(i)))
            {
              iPos=i;
              bPos=true;
//              g.fixtestError("posFalse "+i+"->"+iPos+"/"+bPos);
            }
          if(bPos)
          {
            bEof = false;
            bBof = false;
            bOut = false;
          }
          else
            bOut = true;
        }
        else
          bPos=false;
		return bPos;
	}

        public boolean posGroup(String sSpaltBez, Object Obj1, Object Obj2, Object Obj3,boolean bNext)
        {
          boolean bPos2=false;
          if (bOut)
            return false;
          else
          {
            push();
            Object Obj = getInhalt(sGruppe);
            if (bNext)
              moveNext();
            else
              posInhalt(sGruppe,Obj);
            boolean bNull= Obj3!=null && Obj3.equals(0);
            while (!bPos2 && !bOut && Obj.equals(getInhalt(sGruppe)))
            {
              if (Static.Gleich(Obj1,getInhalt(sSpaltBez)) || Obj2!=null && Static.Gleich(Obj2,getInhalt(sSpaltBez)) || Obj3!=null && Static.Gleich(Obj3,getInhalt(sSpaltBez)) || bNull && Static.Gleich(null, getInhalt(sSpaltBez)))
                bPos2=true;
              else
                moveNext();
            }
            if (bPos2)
              stack.pop();
            else
              pop();
          }
          return bPos2;
        }
        
        public boolean posNotGroup(String sSpaltBez, Object Obj1, Object Obj2, Object Obj3,boolean bNext)
        {
          boolean bPos2=false;
          if (bOut)
            return false;
          else
          {
            push();
            Object Obj = getInhalt(sGruppe);
            if (bNext)
              moveNext();
            else
              posInhalt(sGruppe,Obj);
            while (!bPos2 && !bOut && Obj.equals(getInhalt(sGruppe)))
            {
              if (Static.Gleich(Obj1,getInhalt(sSpaltBez)) || Obj2!=null && Static.Gleich(Obj2,getInhalt(sSpaltBez)) || Obj3!=null && Static.Gleich(Obj3,getInhalt(sSpaltBez)))
            	moveNext();
              else
                bPos2=true;
            }
            if (bPos2)
              stack.pop();
            else
              pop();
          }
          return bPos2;
        }

        public boolean posGroupNotNull(String sSpaltBez, boolean bNext)
        {
          boolean bPos2=false;
          if (bOut)
            return false;
          else
          {
            push();
            Object Obj = getInhalt(sGruppe);
            if (bNext)
              moveNext();
            else
              posInhalt(sGruppe,Obj);
            while (!bPos2 && !bOut && Obj.equals(getInhalt(sGruppe)))
            {
              if (getInhalt(sSpaltBez) != null)
                bPos2=true;
              else
                moveNext();
            }
            if (bPos2)
              stack.pop();
            else
              pop();
          }
          return bPos2;
        }

	public int posLikeD(int iP,String sSpaltBez,String sK,boolean bVoll,boolean bCase)
	{
	  Vector Vec=getVecSpalte(sSpaltBez);
	  for (int i=iP+1;i<Vec.size();i++)
	    if(WertOk((String)Vec.elementAt(i),sK,bVoll,bCase))
	      return i;
	  if (iP>-1)
	    for (int i=0;i<iP;i++)
	      if(WertOk((String)Vec.elementAt(i),sK,bVoll,bCase))
		return i;
	  return iP;
	}

	public int posLikeU(int iP,String sSpaltBez,String sK,boolean bVoll,boolean bCase)
	{
	  Vector Vec=getVecSpalte(sSpaltBez);
	  if (iP>0)
	    for (int i=iP-1;i>-1;i--)
	      if(WertOk((String)Vec.elementAt(i),sK,bVoll,bCase))
		return i;
	  for (int i=Vec.size()-1;i>iP;i--)
	      if(WertOk((String)Vec.elementAt(i),sK,bVoll,bCase))
		return i;
	  return -1;
	}

        private static boolean WertOk(String s1,String s2,boolean bVoll,boolean bCase)
        {
          if (s1==null)
            return false;
          if (!bCase)
          {
            //System.out.println("WertOk:"+s1+"/"+s2);
            s1=(s1==null?"":s1.toUpperCase());
            s2=s2.toUpperCase();
          }
          if (bVoll)
            return s1.indexOf(s2)>-1;
          else
            return s1.startsWith(s2);
        }

        public boolean posInhalt2(String sSpaltBez,Vector Vec)
        {
          bPos = false;
          getSpalte(sSpaltBez);
          if (!bFehler)
          {
            for (int i=0;!bPos && i<VecSpalte.size();i++)
              if (Vec.contains(VecSpalte.elementAt(i)))
              {
                bPos = true;
                iPos = i;
              }
          }
          if (bPos)
          {
            bEof = false;
            bBof = false;
            bOut = false;
          }
          else
            bOut=true;
          return bPos;
        }

        public boolean posInhalt(String sSpaltBez, Object ObjWert, Object ObjWert2)
        {
          int i;
          getSpalte(sSpaltBez);

          if (!bFehler)
          {
            i = VecSpalte.indexOf(ObjWert);
            int i2 = VecSpalte.indexOf(ObjWert2);
            i=i2 !=-1 && (i2<i || i==-1) ? i2:i;
            bPos = i != -1;
            if (bPos)
            {
              iPos = i;
              bEof = false;
              bBof = false;
              bOut = false;
            }
            else
              bOut = true;
          }
          else
            bPos = false;
          if (bN)
	  {
	    iN++;
	    g.testInfo("!! "+getTitel()+".posInhalt("+sSpaltBez+","+ObjWert+","+ObjWert2+")="+iPos+"/"+bPos+"|"+iN);
            /*if (Static.bTest)
              return 5/iNull>1;*/
            Fehlerausgabe();
	  }
          return (bPos);
        }

        public boolean posInhalt(String sSpaltBez, Object ObjWert, Object ObjWert2, Object ObjWert3)
        {
          int i;
          getSpalte(sSpaltBez);

          if (!bFehler)
          {
            i = VecSpalte.indexOf(ObjWert);
            int i2 = VecSpalte.indexOf(ObjWert2);
            int i3 = VecSpalte.indexOf(ObjWert3);
            i=i3 !=-1 && (i3<i || i==-1) ? i3: i2 !=-1 && (i2<i || i==-1) ? i2:i;
            bPos = i != -1;
            if (bPos)
            {
              iPos = i;
              bEof = false;
              bBof = false;
              bOut = false;
            }
            else
              bOut = true;
          }
          else
            bPos = false;
          if (bN)
          {
            iN++;
            g.testInfo("! "+getTitel()+".posInhalt("+sSpaltBez+","+ObjWert+","+ObjWert2+")="+iPos+"/"+bPos+"|"+iN);
          }
          return (bPos);
        }

	public void posLast(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
		if(!bFehler && !bOut)
			iPos=VecSpalte.lastIndexOf(VecSpalte.elementAt(iPos));
	}

	public String getBezeichnung(Object rsGruppe,String rsKennung)
	{
		//bEof = false;
		getSpalte(sGruppe);
		int i=VecSpalte.indexOf(rsGruppe);
		if(i != -1)
		{
			getSpalte(sKennung);
			i=VecSpalte.indexOf(rsKennung,i);
			bOut = i == -1;
			if(!bOut)
				iPos = i;
			//g.debugInfo("Tabellenspeicher:getBezeichnung"+rsGruppe+"/"+rsKennung+"->"+i+"/"+getInhalt(sGruppe));
			bPos = !bOut && rsGruppe.equals(getInhalt(sGruppe));
			if(bPos)
			{
				bBof = false;
				bEof = false;
				//bOut = false;
				return((String)getInhalt(sBezeichnung));
			}
			else
			{
				iPos=VecSpalte.size()-1;
				bOut=true;
				g.printInfo(1,getTitel()+".getBezeichnung:"+rsKennung+" in Gruppe "+rsGruppe+" nicht gefunden!");
			}
		}
		else
		{
			bPos = false;
			Transact.fixError(g,getTitel()+".getBezeichnung(): Gruppe "+rsGruppe+" nicht gefunden!");
		}
		return(rsKennung);
	}

	public void setTitel(String rsTT)
	{
	  bN=bN2;
	  sTT=rsTT;
	}

	public void setTitel2(String rsTT)
	{
	  sTT=rsTT;
	}

        public String getTitel()
        {
          return sTT==null ? "Tabellenspeicher":sTT;
        }

	public boolean addInhalt(String sSpaltBez, int i)
	{
		return addInhalt(sSpaltBez,i==0 ? Static.Int0:new Integer(i));
	}

        public boolean addInhalt0(String sSpaltBez, int i)
        {
                return addInhalt(sSpaltBez,i==0 ? null:new Integer(i));
        }

	public boolean addInhalt(String sSpaltBez, Object ObjWert)
	{
		getSpalte(sSpaltBez);
		if(!bFehler)
		{
			if (bInsert)
				VecSpalte.insertElementAt(ObjWert,iPos);
			else
				VecSpalte.addElement(ObjWert);
			//bEof=false;
		}
		return(!bFehler);
	}

	public boolean setInhalt(String sSpaltBez, int i)
	{
		return setInhalt(sSpaltBez,i==0 ? Static.Int0:new Integer(i));
	}

        public boolean setInhalt0(String sSpaltBez, int i)
        {
                return setInhalt(sSpaltBez,i==0 ? null:new Integer(i));
        }

        public boolean setInhaltS(String sSpaltBez, Object ObjWert)
        {
          String s=""+ObjWert;
          if(s.indexOf("\n") != -1)
            s = s.substring(0, s.indexOf("\n"));
          return setInhalt(sSpaltBez,s);
        }


	public boolean setInhalt(String sSpaltBez, Object ObjWert)
	{
		getSpalte(sSpaltBez);
		if(!bFehler)
		{
			if (iPos>=VecSpalte.size() || iPos<0)
                        {
                          if (iAnzFmom<iAnzFmax)
                        	  Transact.fixError(g,getTitel() + ".setInhalt(): Kann nicht auf Zeile " +(bOut?"out":""+(iPos+1)) + " die Spalte " + sSpaltBez + " auf " + ObjWert + " ändern, da nur " +
                                            VecSpalte.size() + " Zeilen vorhanden sind!");
                          Fehlerausgabe();
                        }
			else
			  VecSpalte.setElementAt(ObjWert,iPos);
		}
		return(!bFehler);
	}

	/*public boolean setInhalt(int i, Object ObjWert)
	{
		bFehler = i<0 || i>=VecInhalt.size();
		if (bFehler)
			Static.printError("Tabellenspeicher.setInhalt(): Spalte "+i+" existiert nicht!");
		else
			if (iPos>=((Vector) VecInhalt.elementAt(i)).size() || iPos<0)
				Static.printError("Tabellenspeicher.setInhalt(): Kann nicht auf Zeile "+iPos+" die Spalte "+i+" auf "+ObjWert+" ändern, da nur "+((Vector) VecInhalt.elementAt(i)).size()+" Zeilen vorhanden sind!");
			else
				((Vector) VecInhalt.elementAt(i)).setElementAt(ObjWert,iPos);
		return !bFehler;
	}*/

	public boolean putInhalt(String sSpaltBez, int i,boolean bNeu)
	{
		return putInhalt(sSpaltBez,i==0 ? Static.Int0:new Integer(i),bNeu);
	}

	public boolean putInhalt(String sSpaltBez, Object ObjWert,boolean bNeu)
	{
		getSpalte(sSpaltBez);

		if(!bFehler)
		{
			if (bNeu)
			{
				if (bInsert)
					VecSpalte.insertElementAt(ObjWert,iPos);
				else
					VecSpalte.addElement(ObjWert);
			}
			else
				VecSpalte.setElementAt(ObjWert,iPos);

                        if (bN)
                          Fehlerausgabe();
			//VecInhalt.setElementAt(VecSpalte,VecTitel.indexOf(sSpaltBez.toUpperCase()));
		}
		return(!bFehler);
	}

	/*public void putInhalt(int iAic,String rsKennung,String rsBezeichnung,boolean bNeu)
	{
		if (bNeu)
			addInhalt(sAIC,iAic);
		else
			posInhalt(sAIC,iAic);
		putInhalt(sKennung,rsKennung,bNeu);
		//if(rsBezeichnung.equals(""))
			putInhalt(sBezeichnung,Static.beiLeer(rsBezeichnung,rsKennung),bNeu);
		//else
		//	putInhalt(sBezeichnung,rsBezeichnung,bNeu);
	}*/

        public int getPos(int iAic,String rsKennung,boolean bUp,String rsBezeichnung,boolean bNeu,int iPos)
        {
          if (!bNeu)
            iPos=getPos(sAIC,iAic);
          if (iPos<0 || bNeu)
          {
            iPos = newLine(iPos);
            setInhalt(iPos,sAIC,iAic);
          }
          setInhalt(iPos,sKennung,bUp ? rsKennung.toUpperCase():rsKennung);
          setInhalt(iPos,sBezeichnung,Static.beiLeer(rsBezeichnung,rsKennung));
          return iPos;
        }

	public boolean exists(String sSpaltBez)
	{
		int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
		return i != -1;
	}

	public boolean exists(int i)
	{
		return i<VecInhalt.size() && i>=0;
	}

        public boolean gleich(String s1,String s2)
        {
          Object Obj1=getInhalt(s1);
          Object Obj2=getInhalt(s2);
          return Obj1==null ? Obj2==null:Obj1.equals(Obj2);
        }

	public boolean isNull(String sSpaltBez)
	{
		return getInhalt(sSpaltBez)==null;
	}

        public boolean nextIsNull(String sSpaltBez)
        {
          push();
          moveNext();
          boolean b=!eof() && getInhalt(sSpaltBez)==null;
          pop();
          return b;
        }

	public Object getInhalt(String sSpaltBez,int riPos)
	{
		Vector Vec=getVecSpalte(sSpaltBez);
		boolean bFehler2 = riPos<0 || riPos>=Vec.size();
		if (bFehler2 && !bFehler)
		{
                  if (iAnzFmom<iAnzFmax)
                	  Transact.fixError(g,getTitel()+".getInhalt(String,int): Spalte "+sSpaltBez+" hat keine Zeile "+riPos);
                  Fehlerausgabe();
                        //showGrid(sTT);

		}
		if (bFehler2)
			bFehler=true;
		return(bFehler ? null : Vec.elementAt(riPos));
	}

	private void Fehlerausgabe()
	{
		Fehlerausgabe(null);
	}
			
        private void Fehlerausgabe(String sSpalte)
        {
          if (sSpalte != null && !VecErrorSpalten.contains(sSpalte))
          {
        	  VecErrorSpalten.addElement(sSpalte);
          	  g.printStackTrace(new Exception());
          }
          if (/*(Static.bInfoExcept || g.Def()) &&*/ sSpalte==null && iAnzFmom<iAnzFmax)
          {
        	g.printStackTrace(new Exception());
            if (!Static.bWeb && g.Def() && Static.bX11)
            	showGrid(getTitel());
          }
          else if (iAnzFmom==iAnzFmax)
            g.fixInfo("Tabellenspeicher "+getTitel()+" bereits über "+iAnzFmom+" Fehler");
          iAnzFmom++;
          Static.clearCache();
        }

	public Object getInhalt(String sSpaltBez)
	{

		getSpalte(sSpaltBez);

		if(bOut)
			g.printInfo(1,"Spalte "+sSpaltBez+": "+getTitel()+" steht auf OUT");
		if (bN)
		  {
		    iN++;
		    g.testInfo("!! "+getTitel()+".getInhalt("+sSpaltBez+","+iPos+")="+VecSpalte.elementAt(iPos)+"|"+iN);
                    Fehlerausgabe();
                    /*if (Static.bTest)
                      return 5/iNull>1;*/
		  }
		return(bFehler || bOut ? null : VecSpalte.elementAt(iPos));
	}

        @SuppressWarnings("unchecked")
	public Object getInhalt(int i)
        {
          if (i<VecInhalt.size() && i>=0)
            VecSpalte=(Vector) VecInhalt.elementAt(i);
          else
          {
        	  Transact.fixError(g,"Spalte "+i+" ist nicht vorhanden");
            return null;
          }
          return(bFehler || bOut ? null : VecSpalte.elementAt(iPos));
        }

	public static java.util.Date getdate(Object Obj)
	{
		return Obj == null ? null : Obj instanceof Zeit ? ((Zeit)Obj).getDate():Obj instanceof DateWOD ? ((DateWOD)Obj).getTime() : (java.util.Date)Obj;
	}

        public java.sql.Date getd(Object Obj)
        {
          return Obj == null ? null : Obj instanceof java.sql.Date ? (java.sql.Date)Obj:new java.sql.Date(((Date)Obj).getTime());
        }

	public java.sql.Timestamp gettimestamp(Object Obj)
	{
		return Obj == null ? null : Obj instanceof DateWOD ? ((DateWOD)Obj).toTimestamp() : Obj instanceof java.sql.Timestamp ? (java.sql.Timestamp)Obj:new java.sql.Timestamp(((Date)Obj).getTime());
	}

    public static int geti(String s)
    {
    	int i=s.indexOf('.');
    	if (i>0)
    	  s=s.substring(0, i);
    	return new Integer(s).intValue();
    }
	
	public static int geti(Object Obj)
	{
		return Obj == null || Obj.equals("") ? 0 : Obj instanceof Integer ? ((Integer)Obj).intValue() :
                    Obj instanceof Boolean ? Obj==Boolean.TRUE ? 1:0:Obj instanceof BewEig ? ((BewEig)Obj).Eig():
				Obj instanceof Long ? ((Long)Obj).intValue() : Obj instanceof Combo ? ((Combo)Obj).getAic() :
				Obj instanceof Double ? ((Double)Obj).intValue():geti(Obj instanceof String ? (String)Obj:Obj.toString());
	}

	public static long getl(Object Obj)
	{
		return Obj == null || Obj.equals("") ? 0l : Obj instanceof Long ? ((Long)Obj).longValue() :
				Obj instanceof Integer ? ((Integer)Obj).longValue() :
                                Obj instanceof Date ? ((Date)Obj).getTime():
				Obj instanceof Double ? ((Double)Obj).longValue():new Long(Obj instanceof String ? (String)Obj:Obj.toString()).longValue();
	}

	public static String getm(Object Obj)
	{
		return Obj==null ? "" : Obj instanceof Memo1 ? ((Memo1)Obj).getValue(): Obj.toString();
	}

	public static boolean getb(Object Obj)
	{
		//getSpalte(sSpaltBez);
		//Object Obj = bFehler || bEof ? null:VecSpalte.elementAt(iPos);
		return Obj == null ? false : Obj instanceof Boolean ? ((Boolean)Obj).booleanValue():!Obj.toString().startsWith("0") && !Obj.toString().startsWith("N");
	}

	public java.util.Date getDate(String sSpaltBez)
	{
		return getdate(getInhalt(sSpaltBez));
	}

        public java.sql.Date getD(String sSpaltBez)
        {
                return getd(getInhalt(sSpaltBez));
        }

	public java.sql.Timestamp getTimestamp(String sSpaltBez)
	{
		return gettimestamp(getInhalt(sSpaltBez));
	}

	public boolean getB(String sSpaltBez)
	{
		return getb(getInhalt(sSpaltBez));
	}

        public boolean getB(int i)
        {
          return getb(getInhalt(i));
        }

	public int getI(String sSpaltBez)
	{
		//getSpalte(sSpaltBez);
		//return bFehler || bEof ? 0 : geti(VecSpalte.elementAt(iPos));
                try
                {
                  return geti(getInhalt(sSpaltBez));
                }
                catch(Exception e)
                {
                	Transact.fixError(g,"Exception in Tabellenspeicher.getI: Titel="+getTitel()+", Spalte="+sSpaltBez+", Obj="+Static.print(getInhalt(sSpaltBez)));
                	Transact.printStackTrace(g, e);
                 return 0;
                }
	}

	public long getL(String sSpaltBez)
	{
		return getl(getInhalt(sSpaltBez));
	}

	public Integer getInt(String sSpaltBez)
	{
		Object Obj=getInhalt(sSpaltBez);
		if (Obj != null && !(Obj instanceof Integer) && !(Obj instanceof Long) && !(Obj instanceof Double) && !(Obj instanceof String))
			Transact.fixError(g,getTitel()+".getInt("+sSpaltBez+"): Nicht mit "+Obj.getClass().getName()+" möglich");
                return Sort.getInt(Obj);
		//return Obj instanceof Integer ? (Integer)Obj : Obj instanceof Long ? new Integer(((Long)Obj).intValue()) :
                //    Obj instanceof Double ? new Integer(((Double)Obj).intValue()):null;
	}

	public double getF(String sSpaltBez)
	{
		//getSpalte(sSpaltBez);
		//return bFehler || bEof ? 0.0 : getf(VecSpalte.elementAt(iPos));
		return Sort.getf(getInhalt(sSpaltBez));
	}

        public double getF2(String sSpaltBez)
        {
                //getSpalte(sSpaltBez);
                //return bFehler || bEof ? 0.0 : getf(VecSpalte.elementAt(iPos));
                return Sort.getf2(getInhalt(sSpaltBez));
        }

	public String getM(String sSpaltBez)
	{
		//getSpalte(sSpaltBez);
		//return bFehler || bEof || VecSpalte.elementAt(iPos)==null ? "" : VecSpalte.elementAt(iPos).toString();
		return getm(getInhalt(sSpaltBez));
	}

	public String getS(String sSpaltBez)
	{
		//getSpalte(sSpaltBez);
		//return bFehler || bEof || VecSpalte.elementAt(iPos)==null ? "" : VecSpalte.elementAt(iPos).toString();
		return Sort.gets(getInhalt(sSpaltBez));
	}

        public String getS(int i)
        {
          return Sort.gets(getInhalt(i));
        }

        public String getM(int i)
        {
          return getm(getInhalt(i));
        }

        public boolean isNull(int i)
	{
          return getInhalt(i) == null || getS(i).equals("");
        }
        
        public void addS(String sSpaltBez,String s)
        {
          setInhalt(sSpaltBez,getS(sSpaltBez)+s);
        }

        public void addI(String sSpaltBez,int i)
        {
          setInhalt(sSpaltBez,getI(sSpaltBez)+i);
        }

        public void addI(int iPos,String sSpaltBez,int i)
        {
          setInhalt(iPos,sSpaltBez,getI(iPos,sSpaltBez)+i);
        }

        public void addTS(String sSpaltBez,long l)
        {
          setInhalt(sSpaltBez,new java.sql.Timestamp(((java.sql.Timestamp)getInhalt(sSpaltBez)).getTime()+l));
        }

        public void Saldo(String s1,String s2,int i)
        {
          for(moveFirst();!eof();moveNext())
          {
            i+=geti(getInhalt(s1));
            setInhalt(s2,i);
          }
        }

	public double sum(String sSpaltBez)
	{
          /*//g.fixInfo("sum "+sSpaltBez+":");
		getSpalte(sSpaltBez);
		double d=0;
		for(int i=0;i<VecSpalte.size();i++)
                {
                  double d2=Sort.getf(VecSpalte.elementAt(i));
                  //g.fixInfo(i+":"+d2);
                  d += d2;
                }
		return d;*/
          return sum(sSpaltBez,0);
	}

        public double sum(String sSpaltBez,int iAb)
        {
                getSpalte(sSpaltBez);
                double d=0;
                for(int i=iAb;i<VecSpalte.size();i++)
                {
                  double d2=Sort.getf(VecSpalte.elementAt(i));
                  //g.fixInfo(i+":"+d2);
                  d += d2;
                }
                return d;
        }

        public Zahl1 proz(String sSpaltBez,String sFormat)
        {
          double d=sum(sSpaltBez);
          return d==0 ? null:new Zahl1(getF(sSpaltBez)/d,sFormat);
        }

        public double sumGroup(String sSpaltBez,boolean bAlle)
        {
          //g.progInfo("sumGroup "+sSpaltBez+ " mit Gruppe "+sGruppe);
          if (bOut)
            return 0.0;
          else
          {
            double d=0;
            push();
            Object Obj=getInhalt(sGruppe);
            if (bAlle)
            	moveFirst();
            while (!bOut && (bAlle || Obj.equals(getInhalt(sGruppe))))
            {
            	if (Obj.equals(getInhalt(sGruppe)))
            		d += getF(sSpaltBez);
            	moveNext();
            }
            pop();
            return d;
          }
        }

        public double sumVec(String sSpaltBez,Vector Vec)
        {
          //if (bOut)
          //  return 0.0;
          //else
          {
            double d=0;
            push();

            //Object Obj=getInhalt(sGruppe);
            for (moveFirst();!bOut;moveNext())
              if (Vec.contains(getInhalt(sGruppe)))
                d += getF(sSpaltBez);
            pop();
            return d;
          }
        }


	public int count(String sSpaltBez,Object Obj)
	{
		getSpalte(sSpaltBez);
		if (VecSpalte.size()==0)
			return 0;
		else
		{
			int iCount=0;
			for(int i=0;i<VecSpalte.size();i++)
				if (Obj.equals(VecSpalte.elementAt(i)))
					iCount++;
			return iCount;
		}
	}

        public int countGroup(String sSpaltBez)
        {
          if (bOut)
            return 0;
          else
          {
            int iAnz=0;
            Object Obj = getInhalt(sGruppe);
            push();
            Vector<Object> Vec = sSpaltBez==null ? null:new Vector<Object>();
            for(moveFirst();!out();moveNext())
              if (Obj.equals(getInhalt(sGruppe)))
              {
                if (sSpaltBez==null)
                  iAnz++;
                else if (!Vec.contains(getInhalt(sSpaltBez)))
                {
                  Vec.addElement(getInhalt(sSpaltBez));
                  iAnz++;
                }
              }
            pop();
            return iAnz;
          }
        }
        
    public double max(String sSpaltBez)
    {
    	return max(sSpaltBez,false);
    }

	public double max(String sSpaltBez,boolean bGroup)
	{
		getSpalte(sSpaltBez);
		if (VecSpalte.size()==0)
		{
			g.printInfo(1,"max-Bildung nur mit Werten möglich");
			return 0.0;
		}
		double d=Double.MIN_VALUE;//Sort.getf(VecSpalte.elementAt(0));
		Object Obj=bGroup ? getInhalt(sGruppe):null;
		for(int i=0;i<VecSpalte.size();i++)
			if (!bGroup || Static.Gleich(Obj, getInhalt(sGruppe,i)))
				d=Math.max(d,Sort.getf(VecSpalte.elementAt(i)));
		return d==Double.MIN_VALUE?0:d;
	}

	public double min(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
		if (VecSpalte.size()==0)
		{
			g.printInfo(1,"min-Bildung nur mit Werten möglich");
			return 0.0;
		}
		double d=Double.MAX_VALUE;//Sort.getf(VecSpalte.elementAt(0));
		for(int i=0;i<VecSpalte.size();i++)
			if(VecSpalte.elementAt(i) != null)
				d=Math.min(d,Sort.getf(VecSpalte.elementAt(i)));
		return d==Double.MAX_VALUE?0:d;
	}

	public void posmin(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
		if (VecSpalte.size()==0)
		{
			//g.printInfo(1,"min-Bildung nur mit Werten möglich");
			bOut=true;
		}
		else
		{
			double d=Double.MAX_VALUE;//Sort.getf(VecSpalte.elementAt(0));
			iPos=-1;
			for(int i=0;i<VecSpalte.size();i++)
				if(VecSpalte.elementAt(i) != null)
				{
					double d2=Sort.getf(VecSpalte.elementAt(i));
					if (d2<d)
					{
						d=d2;
						iPos=i;
					}
				}
			bOut=iPos == -1;
		}
	}

	public void posmax(String sSpaltBez)
	{
		getSpalte(sSpaltBez);
		if (VecSpalte.size()==0)
		{
			//g.printInfo(1,"max-Bildung nur mit Werten möglich");
			bOut=true;
		}
		double d=Sort.getf(VecSpalte.elementAt(0));
		iPos=0;
		bOut=false;
		for(int i=1;i<VecSpalte.size();i++)
		{
			double d2=Sort.getf(VecSpalte.elementAt(i));
			if (d2>d)
			{
				d=d2;
				iPos=i;
			}
		}
	}

	public double avg(String sSpaltBez)
	{
		return sum(sSpaltBez)/(VecSpalte.size()==0 ? 1:VecSpalte.size());
	}

        public void mul(String sSpaltBez,long l)
        {
          getSpalte(sSpaltBez);
          for(int i=0;i<VecSpalte.size();i++)
            VecSpalte.setElementAt(new Long(getl(VecSpalte.elementAt(i))*l),i);
        }

        public Stack<Integer> toVecAic(String sSpaltBez)
	{
		Vector Vec = getVecSpalte(sSpaltBez);
                Stack<Integer> Vec2=new Stack<Integer>();
                for(int i=0;i<Vec.size();i++)
                {
                  Object Obj=Vec.elementAt(i);
                  if (Obj != null && !Vec2.contains(Obj))
                    Vec2.push(Sort.getInt(Obj));
                }
		return Vec2;
	}

        public Stack<Integer> groupVecAic(String sSpaltBez)
        {
                push();
                Stack<Integer> Vec2=new Stack<Integer>();
                Object Obj = getInhalt(sGruppe);
                posInhalt(sGruppe,Obj);
                while (!bOut && Static.Gleich(Obj,getInhalt(sGruppe)))//Obj.equals(getInhalt(sGruppe)))
                {
                  Object Obj2=getInhalt(sSpaltBez);
                  if (Obj2 != null && !Vec2.contains(Obj2))
                    Vec2.push(Sort.getInt(Obj2));
                  moveNext();
                }
                pop();
                return Vec2;
        }

        public Vector<Integer> groupVecAic(int iAic)
        {
          Vector<Integer> Vec2=new Vector<Integer>();
          for(int i=0;i<size();i++)
            if (getI(i,sGruppe)==iAic)
              Vec2.addElement(getI(i,sAIC));
          return Vec2;
        }

	@SuppressWarnings("unchecked")
	public Vector<Object> getVecSpalte(String sSpaltBez)
	{
		int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
		bFehler = i == -1;
		if (bFehler)
                {
                  if (iAnzFmom<iAnzFmax)
                  {
                    Transact.fixError(g,getTitel() + ".getVecSpalte: Spalte " + sSpaltBez + " nicht gefunden !!!");
                    Transact.fixError(g,"vorhanden:" + VecTitel);
                    Fehlerausgabe(sSpaltBez);
                  }
                  //if (g.Prog())
                    
                  //if (5/iNull>1)
                  // bFehler=true;
                }
		return(bFehler ? null : (Vector) VecInhalt.elementAt(i));
	}

	@SuppressWarnings("unchecked")
	public Vector<Integer> getVecSpalteI(String sSpaltBez)
	{
		int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
		bFehler = i == -1;
		if (bFehler)
                {
					Transact.fixError(g,getTitel()+".getVecSpalteI: Spalte " + sSpaltBez + " nicht gefunden !!!");
					Transact.fixError(g,"vorhanden:"+VecTitel);
                  Fehlerausgabe(sSpaltBez);
                }
		return(bFehler ? null : (Vector) VecInhalt.elementAt(i));
	}

	@SuppressWarnings("unchecked")
	public Vector<Integer> getVecD(String sSpaltBez)
	{
		int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
		bFehler = i == -1;
		if (bFehler)
                {
					Transact.fixError(g,getTitel()+".getVecD: Spalte " + sSpaltBez + " nicht gefunden !!!");
					Transact.fixError(g,"vorhanden:"+VecTitel);
                  Fehlerausgabe(sSpaltBez);
                  return null;
                }
		Vector<Integer> Vec0=new Vector<Integer>();
		Vector Vec=(Vector)VecInhalt.elementAt(i);
		//g.progInfo("getVecD: Vec ="+Vec);
		for(i=0;i<Vec.size();i++)
                {
                  int iV=Sort.geti(Vec,i);
                  if (!Vec0.contains(iV))
                    Vec0.addElement(iV);
                }
		//g.progInfo("getVecD: Vec0="+Vec0);
		return Vec0;
	}

        public Vector getVec2Spalte(String sSpaltBez)
        {
		int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
		bFehler = i == -1;
		if (bFehler)
                {
				  Transact.fixError(g,getTitel()+".getVec2Spalte: Spalte " + sSpaltBez + " nicht gefunden !!!");
                  Fehlerausgabe(sSpaltBez);
                  return null;
                }
                Vector Vec2=(Vector) VecInhalt.elementAt(i);
                Vector<Object> Vec3=new Vector<Object>();
                for(i=0;i<Vec2.size();i++)
                {
                  Vector Vec4=(Vector)Vec2.elementAt(i);
                  if (Vec4 != null)
                    for(int i2=0;i2<Vec4.size();i2++)
                      if (!Vec3.contains(Vec4.elementAt(i2)))
                        Vec3.addElement(Vec4.elementAt(i2));
                }
		return Vec3;
	}

	private void getSpalte(String sSpaltBez)
	{
		VecSpalte=getVecSpalte(sSpaltBez);
	}

        public Vector getVec(String sSpaltBez,Object Obj,String sSp2)
        {
          //g.progInfo("Tab.getVec:"+sSpaltBez+"/"+Obj+"/"+sSp2);
          push();
          Vector<Object> Vec = new Vector<Object>();
          for(moveFirst(); !eof(); moveNext())
            if(!isNull(sSpaltBez) && getInhalt(sSpaltBez).equals(Obj) && getInhalt(sSp2)!=null)
              Vec.addElement(getInhalt(sSp2));
          pop();
          return Vec;
        }

        public Vector getVecKennung(String s) // für DefImport
        {
          Vector<String> Vec=new Vector<String>();
          for(moveFirst();!eof();moveNext())
            if(getS(sGruppe).equals(s))
               Vec.addElement(getS(sKennung));
          return Vec;
        }

	public boolean isEmpty()
	{
		return VecTitel.size()==0 || ((Vector)VecInhalt.elementAt(0)).size()==0;
	}

        public int size()
        {
          return VecTitel.size()==0?0:((Vector)VecInhalt.elementAt(0)).size();
        }

	public int getAnzahlElemente(String sSpaltBez)
	{
		if (sSpaltBez==null)
			return VecTitel.size()==0?0:((Vector)VecInhalt.elementAt(0)).size();
		getSpalte(sSpaltBez);
		if (bFehler)
			Transact.fixError(g,getTitel()+".getAnzahlElemente(): Spalte <"+sSpaltBez+"< nicht gefunden!");
		return(bFehler?0:VecSpalte.size());
	}

	public int count_distinct(String sSpaltBez)
	{
		bFehler = sSpaltBez==null;
		if(!bFehler)
			getSpalte(sSpaltBez);
		if (bFehler)
		{
			Transact.fixError(g,getTitel()+".count_distinct(): Spalte <"+sSpaltBez+"< nicht gefunden!");
			return 0;
		}
		else
		{
			Vector<Object> Vec = new Vector<Object>();
			for(int i=0;i<VecSpalte.size();i++)
			{
				if (!Vec.contains(VecSpalte.elementAt(i)))
					Vec.addElement(VecSpalte.elementAt(i));
			}
			return Vec.size();
		}
	}

	public void moveBefore()
	{
		iPos=-1;
		bOut=true;
	}

	@SuppressWarnings("unchecked")
	public boolean moveFirst()
	{
		//VecSpalte=(Vector) VecInhalt.elementAt(0);
		iPos=0;
		bBof=true;
		bFehler=VecInhalt.size()==0;
		if (bFehler)
			g.defInfo(getTitel()+".moveFirst(): Fehler");
		else
			VecSpalte = (Vector)VecInhalt.elementAt(0);
		bOut=bFehler || VecSpalte.size()==0;
		bEof=bOut;
                return !bOut;
	}

	@SuppressWarnings("unchecked")
	public boolean moveNext()
	{
		bFehler=VecInhalt.size()==0;
		if (!bFehler)
			VecSpalte=(Vector) VecInhalt.elementAt(0);
		int iAnz=bFehler ? 0 : VecSpalte.size();
                if (bOut && bBof && iAnz>0)
                {
                  bOut= false;
                  if (iPos==-1)
                  {
                	  iPos++;
                	  if (g!=null)
                		  g.fixtestError("moveNext: Pos auf "+iPos+" erhöht, bei out, bof und Anz="+iAnz);
                  }
                }
                else
                {
                  bOut = iAnz < iPos + 2;
                  if (!bOut)
                    iPos++;
                }
                bEof = bOut;
		bBof=(iAnz==0);
		return(!bOut);
	}

    @SuppressWarnings("unchecked")
	public boolean movePrevious()
    {
            bFehler=VecInhalt.size()==0;
            if (!bFehler)
                    VecSpalte=(Vector) VecInhalt.elementAt(0);
                int iAnz=bFehler ? 0 : VecSpalte.size();

                if (bOut && bEof && iAnz>0)
                  bOut= false;
                else
                {
                  bOut = iPos < 1;
                  if (!bOut)
                    iPos--;
                }
                bBof = bOut;
		bEof=(iAnz==0);

		return(!bOut);
    }

	public boolean moveLast()
	{
		//VecSpalte=(Vector) VecInhalt.elementAt(0);
		bFehler=VecInhalt.size()==0;
		if (bFehler)
			g.defInfo(getTitel()+".moveLast(): Fehler");

		int iAnz=bFehler?0:((Vector) VecInhalt.elementAt(0)).size();
		bOut= iAnz==0;
		iPos= bOut ? 0:iAnz-1;

		bEof=true;
		bBof=bOut;

		return(bFehler);
	}

	public boolean isLast()
	{
		bFehler=VecInhalt.size()==0;
		return bFehler || ((Vector) VecInhalt.elementAt(0)).size()-1==iPos;
	}

	public boolean eof()
	{
		return(bEof);
	}

	public boolean bof()
	{
		return(bBof);
	}

	public boolean out()
	{
		if (!bOut && iPos==-1 && g!=null)
			g.fixtestError("out="+bOut+" bei Pos="+iPos);
		return(bOut);
	}

	public boolean fehler()
	{
		return(bFehler);
	}

	public boolean pos()
	{
		return bPos;
	}

	@SuppressWarnings("unchecked")
	public static Tabellenspeicher getSelected(Global rg,JCOutliner Gid)
	{
	  /*JCComponent[] Labels=Gid.getColumnLabels() Gid.getHeader().getLabels();
	  for (int i=0;i<Labels.length;i++)
	    Global.fixInfo("Tab.getSelected="+Static.print(((jclass.bwt.HeaderButton)Labels[i])).);*/
	  Tabellenspeicher Tab=new Tabellenspeicher(rg,Gid.getColumnLabels());
	  JCOutlinerNode[] Nodes=Gid.getSelectedNodes();
          Vector<Integer> Vec=new Vector<Integer>();
	  for (int i=0;i<Nodes.length;i++)
          {
            Integer IntAic=new Integer(Sort.geti(Nodes[i].getUserData()));
            if (!Vec.contains(IntAic))
            {
              Vec.addElement(IntAic);
              for(int i2=0;i2<Tab.VecTitel.size();i2++)
               ((Vector)Tab.VecInhalt.elementAt(i2)).addElement(i2<((Vector)Nodes[i].getLabel()).size() ? ((Vector)Nodes[i].getLabel()).elementAt(i2):null);
            }
          }
	  Tab.moveBefore();
	  return Tab;
	}

	public void showGrid()
	{
		showGrid("Tabellenspeicher",null);
	}
	
	public void showGrid(String sTitel)
	{
		showGrid(sTitel,null);
	}


	
//	public TableView<Vector<Object>> showGrid(javafx.stage.Window stage,String sTitel)
//	{
//		return showGrid(stage,sTitel,true);
//	}
//	
//	public TableView<Vector<Object>> showGrid(javafx.stage.Window stage,String sTitel,boolean bModal)
//	{
//		boolean bHide=bModal && stage instanceof PopOver && ((PopOver)stage).isAutoHide();
//		if (bHide) ((PopOver)stage).setAutoHide(false);
//	      
//		Stage StgGrid=new Stage();
//		StgGrid.initModality(bModal ? Modality.APPLICATION_MODAL:Modality.NONE);
//		StgGrid.initOwner(stage);
//		StgGrid.setTitle(sTitel);
//		TableView<Vector<Object>> TV=getTableFX(g,StgGrid,this);
//		StgGrid.getScene().getWindow().setHeight(size()<23 ? size()*24+100:640);
//		if (bModal)
//		  StgGrid.showAndWait();
//		else
//	      StgGrid.show();	
//		if (bHide) ((PopOver)stage).setAutoHide(true);
//		return TV;
//		
//	}
	
//	public TableView<Vector<Object>> getTableFX(Transact g,Object fxPanel,Tabellenspeicher Tab)
//	{
//		//System.out.println("initFX "+Tab.getTitel());
//		TableView<Vector<Object>> Gid=new TableView<Vector<Object>>();
//		//fxPanel.setFont(Static.fontStandard);
//		Vector<String> VecTitel=Tab.VecBezeichnung;
//		for (int i=0;i<Tab.size();i++)
//		{
//			Vector<Object> Vec=new Vector<Object>();
//			for(int i2=0;i2<VecTitel.size();i2++)
//				Vec.addElement(Tab.getElementAt(i,i2));
//			if (g.Def()) Vec.addElement(i+1);
//			Gid.getItems().add(Vec);new Font(5);
//		}
//		for (int i=0;i<VecTitel.size();i++)
//		{
//			String sSpTitel=Sort.gets(VecTitel,i);
//			if (sSpTitel.equals("Style"))
//				iPosStyle=i;
//		}
//		for (int i=0;i<VecTitel.size()+(g.Def() ? 1:0);i++)
//		{
//			String sSpTitel=VecTitel.size()==i ? "Zeile":Sort.gets(VecTitel,i);
//			TableColumn Col=new TableColumn<String,String>(sSpTitel);
//			if (sSpTitel.equals("Style"))
//			{
//				Col.setPrefWidth(0);
//				//iPosStyle=i;
//			}
//			else
//				Col.setMaxWidth(700);
//			Col.setUserData(i);
//			Gid.getColumns().add(Col);
//			if (Tab.size()>0)
//			{
//			  Col.setCellValueFactory(new javafx.util.Callback<CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
//				@Override public javafx.beans.property.ReadOnlyObjectWrapper call(CellDataFeatures<Object, Object> p) 
//			    {
//			    	int i=(int)p.getTableColumn().getUserData();
//			    	Object Obj=p.getValue();
//			    	Object Obj2=Obj instanceof String ? Obj:Obj instanceof Vector ? i>=((Vector)Obj).size() ? "???":((Vector)Obj).elementAt(i):null;
//			    	//g.fixtestInfo(Tab.sTT+": "+i+"/"+Static.print(Obj2));
//			    	Object ObjUse=Obj2==null ? g.Prog() ? "<null>":"":Obj2 instanceof Region ? Static.print(Obj2)/*.replaceAll("(", " [").replaceAll(")","] ")*/:Obj2;
//			    	/*if (ObjUse instanceof String && ((String)ObjUse).length()>30)
//			    	{
//			    		g.fixtestInfo(((String)ObjUse).length()+":"+ObjUse);
//			    		ObjUse=ObjUse+"\n  ";		    		
//			    	}*/
//			    	return new ReadOnlyObjectWrapper(ObjUse);
//			    			//Obj instanceof String ? i==0?(String)Obj:"":Obj instanceof Region ? Static.print(Obj):(Object)((Vector)Obj).elementAt(i));
//			    	
//			    }
//			  });
//			  if (iPosStyle>0)
//				  Col.setCellFactory(new Callback<TableColumn<Object, Object>, TableCell<Object, Object>>() {
//					  @Override
//				        public TableCell<Object, Object> call(TableColumn<Object, Object> param) {
//				            TableCell<Object, Object> cell = new TableCell<Object, Object>() {	
//			            	@Override
//			                protected void updateItem(Object s, boolean bEmpty) {
//			                    super.updateItem(s, bEmpty);
//			                    if (s==null || bEmpty)
//			                    	setText(null);
//			                    else
//			                    {				                      
//			                    	TableRow row=getTableRow();
//			                    	Vector Vec=row != null && row.getItem() instanceof Vector ? (Vector)row.getItem():null;
//			                    	String sStyle=Vec != null && Vec.size()>1 ? Sort.gets(Vec, iPosStyle):null;
//			                    	//if (Vec!=null)
//			                    	//	g.fixtestInfo("Vec="+Vec+"-> Style="+sStyle);
//			                    	setText(""+s);
//			                    	if (!Static.Leer(sStyle))
//			                    		getStyleClass().add(sStyle);
//
//			                    }
//			            	}
//			            };
//	                   
//			            return cell;
//					  }
//				  });
//			}
//		}
//		//Gid.refresh();
//		Scene scene=new Scene(Gid);
//		//if (Static.cssShowGrid != null)
//		//g.fixtestInfo("   ***   setze Style für Tab:"+Static.sDefaultStyle);
//		//if (true)
//		//  return null;
////		FormularFX.setStyle(null,scene,0);
//		//if (Static.sDefaultStyle==null)
//		//	scene.getStylesheets().add(Static.sDefaultStyle);//Static.cssShowGrid);//g.getCss(g.getPosBegriff("Show","showGrid")));
//		if (fxPanel instanceof JFXPanel)
//			((JFXPanel)fxPanel).setScene(scene);
//		else if (fxPanel instanceof Stage)
//			((Stage)fxPanel).setScene(scene);
//		return Gid;
//	}
	
//	public static void showGrid(Tabellenspeicher Tab, String s, String s2)
//	{
//		showGrid(Tab,s,s2,null);
//	}
	
	public static void showGrid(Tabellenspeicher Tab, String s, String s2,Window Fom)
	{
		showGrid(null,Tab,s,s2,Fom);
	}

        public static void showGrid(Global g,Tabellenspeicher Tab, String s, String s2,Window Fom)
        {
          if (Tab != null && s.equals(s2))
          {
        	if (g!=null) Tab.g=g;
            Tab.showGrid(s,Fom);
          }
        }

        public static boolean showGrid(Tabellenspeicher Tab, String s,JButton rBtn,Window Fom)
        {
          boolean b=Tab.Btn1==null;
          if (b)
            Tab.Btn1=rBtn;
          Tab.showGrid(s,Fom);
          return b;
        }

        /*public void showGrid2(JDialog Dlg,String sTitel,boolean bModal)
        {
          boolean b=FrmGrid==null;
          if (b)
          {
            FrmGrid = new JDialog(Dlg,sTitel,bModal);
            Grid = new JCOutliner();
            ((JDialog)FrmGrid).getContentPane().add("Center",Grid);
            //((JDialog)FrmGrid).setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            Static.Escape(null,((JDialog)FrmGrid),null);
          }
          showGrid(Grid);
          FrmGrid.pack();
          if (FrmGrid.getWidth()>1000 || FrmGrid.getHeight()>700)
          {
            int iWidth=Math.min(1000, FrmGrid.getWidth());
            int iHeight=Math.min(700, FrmGrid.getHeight());
            FrmGrid.setSize(iWidth, iHeight);
          }
          if (b)
            Static.centerComponent(FrmGrid,Dlg);
          FrmGrid.toFront();
          if (!bModal)
            FrmGrid.setVisible(true);
        }*/

        public void Export()
        {
        	Export(';');
        }

        public boolean Export(char cTrennzeichen)
        {
	          JFileChooser FC = new JFileChooser();
	          FC.setMultiSelectionEnabled(false);
	          if(JFileChooser.APPROVE_OPTION == FC.showDialog(null, "ok"))
	            return writeFile(cTrennzeichen, "" + FC.getSelectedFile());
	          else
	        	return false;
        }
        
//        public boolean Export(char cTrennzeichen,Stage stage)
//        {
//        	FileChooser FC=new FileChooser();
//      	  File file=FC.showSaveDialog(stage);
//      	  g.fixtestInfo("Export:"+(file==null ? "-":file.getAbsolutePath()));
//      	  if (file!=null)
//      		  return writeFile(cTrennzeichen,file.getAbsolutePath());
//      	  else
//      		  return false;
//        }
        
        public void Druck()
        {
        	//DruckHS.printOutliner(g,sTitle,true,Grid);
        	DruckHS dh = new DruckHS(g, "Tabellenspeicher",0,0,0);// iLayout,((iDBits&DAUSW)>0 ? Drucken.cstDruckerauswahl:0)+((iDBits&FARBE)>0 ? Drucken.cstDFFarbe:0) /*iDruckBits*/,0 /*Stamm*/);
            dh.setDTitel(sTitle,false,Global.fontTitel);
            dh.printTitel(false,true,false,false);
            dh.addOutliner(Grid,null,0/*iVorlage*/, 0/*Drucken.cstGesamtsumme*/, 0, null);
            dh.vorschau();
        }

        public static void LineToMemo(JCOutliner Grid,JEditorPane Memo,Vector<String> VecBezeichnung,JSplitPane PnlC)
        {
          String s="";
          if (Grid.getSelectedNode().getLevel()>0)
          {
            Vector Vec=(Vector)Grid.getSelectedNode().getLabel();
            for (int i=0;i<Vec.size();i++)
            {
              String sMom=null;
              if (Vec.elementAt(i) instanceof Memo1)
                sMom= ((Memo1)Vec.elementAt(i)).getValue();
              else if (Vec.elementAt(i) instanceof Html)
                sMom= ((Html)Vec.elementAt(i)).getValue();
              else if (Vec.elementAt(i) instanceof String && (("" + Vec.elementAt(i)).contains("\n") || ((String)Vec.elementAt(i)).length() > 40))
                sMom= (String)Vec.elementAt(i);
              if (sMom!=null) s+="<b>" +Sort.gets(VecBezeichnung,i)+":</b> "+Static.KillLeerzeilen(sMom,true)+ "<p>";
            }
          }
          //System.out.println("LineToMemo="+s);
          boolean bLeer=s.equals("");
          Memo.setText(bLeer ? s:"<span style=\"font-family:"+Static.sFont+"\">"+s+"</span>");
          //Memo.setPreferredSize(new Dimension(150,bLeer?1:34));
          //Memo.setMinimumSize(new Dimension(150,bLeer?1:50));
          Memo.setVisible(!bLeer);
          PnlC.resetToPreferredSizes();
        }

        public void showGrid(String sTitel,Window FomModal)
        {
          showGrid(sTitel,FomModal,false,null);
        }

        public void showGrid(String sTitel,Window FomModal,boolean bModal)
        {
                  showGrid(sTitel,FomModal,bModal,null);
        }
        
        
    private void fillCboSpalten(JCOutliner Gid)
    {
    	if (CboSpalten==null)
    	{
          String[] sAry=Gid.getColumnLabels();
  		  // g.fixtestError("fillCboSpalten:"+sAry);
        CboSpalten=new ComboSort(g,ComboSort.kein);
        CboSpalten2=new ComboSort(g,ComboSort.kein);
  		  if (sAry != null)
          for(int i=0;i<sAry.length;i++)
          {
            CboSpalten.addItem(sAry[i], i, sAry[i]);
            CboSpalten2.addItem(sAry[i], i, sAry[i]);
            // g.fixtestError("Spalte dazu:"+sAry[i]);
          }
  		  	CboSpalten2.addItemListener(new ItemListener()
  				  {
  			  			public void itemStateChanged(ItemEvent ev)
  			  			{
  			  				if (ev.getStateChange()==ItemEvent.SELECTED)
  			  					showColumnInTextfield(Gid);
  			  			}
  				  });
    	}
    }
    
    public void refillCbo(Vector Vec)
    {
    	CboSpalten.Clear(false);
    	for(int i=0;i<Vec.size();i++)
  			CboSpalten.addItem(Sort.gets(Vec,i), i, Sort.gets(Vec,i));
      CboSpalten2.Clear(false);
    	for(int i=0;i<Vec.size();i++)
  			CboSpalten2.addItem(Sort.gets(Vec,i), i, Sort.gets(Vec,i));
    }
    
    public void setTabBtn(int i,JButton Btn)
    {
    	if (i==1)
    		Btn1=Btn;
    	else if (i==2)
    		Btn2=Btn;
    }

    private void initFilter(JCOutliner Gid)
    {
      // g.fixtestError("initFilter");
      BtnI.setEnabled(false);
      showGrid(Gid,null);
    }

    private void setFilter(JCOutliner Gid,String s,int iCol)
    {
      // g.fixtestError("setFilter"+iCol+":"+s);
      JCVector Vec=Gid.getRootNode().getChildren();
      if (Vec != null && Vec.size()>0)
        for (int i=0;i<Vec.size();i++)
        {
          JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
          Vector<Object> Vec2=(Vector<Object>)Nod.getLabel();
          if (!(""+Vec2.elementAt(iCol)).startsWith(s))
          {
            // g.fixtestError("Zeile "+i+" weg");
            Nod.getParent().removeChild(Nod);
            i--;
          }
        }
        Gid.folderChanged(Gid.getRootNode());
        BtnI.setEnabled(true);
    }
    
    public void addSuche(JPanel Pnl,JPanel PnlBtn,Window win,JCOutliner Gid)
    {
    	fillCboSpalten(Gid);
      JPanel PnlW=new JPanel(new GridLayout(2,2,0,3));
      JPanel PnlC=new JPanel(new GridLayout(2,1,0,3));
      JPanel PnlE=new JPanel(new GridLayout(2,1,0,3));
      PnlW.add(g.getLabel("Suche_Filter"));
      PnlW.add(CboSpalten);
      PnlW.add(g.getLabel("Daten"));
      PnlW.add(CboSpalten2);
      // PnlO.add(new JLabel("Suche/Filter: "));
		  // PnlO.add(CboSpalten);
      // PnlU.add("West",CboSpalten2);
      if (Edt2==null)
		    Edt2=new Text("",100);
      // PnlU.add("Center",Edt2);
		  if (Edt==null)
		    Edt=new Text("",100);
  	  PnlC.add(Edt);
      PnlC.add(Edt2);
  	  ActionListener actList = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
      	  String s=e.getActionCommand();
      	  if (s.equals("Export"))
            Export();
      	  else if (s.equals("Druck"))
            Druck();
      	  else if (s.equals("show"))
      		  showPDF(win,Edt.getText());
      	  else if (s.equals("S"))
      		  Suche(Gid,Edt.getText(),CboSpalten.getSelectedAIC());
          else if (s.equals("F"))
            setFilter(Gid,Edt.getText(),CboSpalten.getSelectedAIC());
          else if (s.equals("I"))
            initFilter(Gid);
        }
      };
      JPanel PnlBtn1=new JPanel(new GridLayout(1,0,5,0));
      JButton BtnS=g.getButton("S",null,actList);
      PnlBtn1.add(BtnS);
      PnlBtn1.add(g.getButton("F",null,actList));
      BtnI=g.getButton("I",null,actList);
      BtnI.setEnabled(false);
      PnlBtn1.add(BtnI);
      PnlE.add(PnlBtn1);
      if (win instanceof JDialog)
      	((JDialog)win).getRootPane().setDefaultButton(BtnS);
      else if (win instanceof JFrame)
      	((JFrame)win).getRootPane().setDefaultButton(BtnS);
	  	//Pnl.add(new JLabel("  "));       
	    PnlBtn.add(g.getButton("show",null,actList));
      PnlBtn.add(g.getButton("Export",null,actList));
      PnlBtn.add(g.getButton("Druck",null,actList));
      PnlE.add(PnlBtn);
      Pnl.add("West",PnlW);
      Pnl.add("Center",PnlC);
      Pnl.add("East",PnlE);
    }
        
    public void addSouth(Container CP,Window win,JCOutliner Gid)
    {
      // JPanel Pnl=new JPanel(new GridLayout(2, 1,2,2));
      JPanel Pnl=new JPanel(new BorderLayout(5, 5));
    	// JPanel PnlO=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));//new GridLayout(1,0,5,5));
      JPanel PnlBtn=new JPanel(new GridLayout(1,0,3,0));

    	  if (g!=null)// && g.Def())
	          addSuche(Pnl,PnlBtn,win,Gid);
      	  if (Btn1 != null)
            PnlBtn.add(Btn1);
      	  if (Btn2 != null)
            PnlBtn.add(Btn2);
      	  JButton BtnBeenden=g.getButton("Beenden");
          PnlBtn.add(BtnBeenden);
          Static.Escape(BtnBeenden,g==null || win==null ? FrmGrid:win,null);
          // PnlU.add("East",PnlBtn);
          // Pnl.add(PnlO);
          // Pnl.add(PnlU);
          CP.add("South",Pnl);
    }
    
    private void showPDF(Window win,String sFile)
    {
    	g.fixtestError("showPDF "+sFile);
    	int iIndexUS=sFile.indexOf("_");
    	if (iIndexUS<1)
    		return;
    	int iDaten=Integer.parseInt(sFile.substring(0,iIndexUS));
    	if (iDaten>0)
    	{
    		String s=SQL.getDoku(g,iDaten);
    		g.fixtestError("PDF="+s);
    		if (s!=null)
    			g.openFile(s);
    		else
    		{
    			Image Img=SQL.getImage(g, iDaten);
    			if (Img != null)
    			{
    				//Formular Fom=new Formular("Bild",g.getFomLeer(),g);
    				JDialog Dlg=new JDialog(win,sFile);
    				//JPanel PnlBild=Fom.getFrei("Bild");
    				JLabel LblBild=new JLabel("");
    				LblBild.setIcon(new ImageIcon(Img));
    				JScrollPane PnlSP=new JScrollPane(LblBild);
    				Dlg.getContentPane().add("Center",PnlSP);
    				Dlg.pack();
    				Dlg.setVisible(true);
    				Static.centerComponent(Dlg, win);				
    			}
    		}
    	}
    }
    
    private void Suche(JCOutliner Gid,String s,int iCol)
    {
    	//g.fixtestError("Suche "+s+" in Spalte "+iCol);
    	JCOutlinerNode NodSel=Gid.getSelectedNode();
    	JCOutlinerNode Nod=null;//Out.getNextNode(NodSel);
    	while (Nod==null || Nod!=NodSel)
    	{
    		Nod=Gid.getNextNode(Nod==null ? NodSel:Nod);
    		if (Nod==null)
    			Nod=Gid.getNextNode(Gid.getRootNode());
    		//g.fixtestError("Nod="+Nod.getLabel());
    		if (iCol<0 && (""+Nod.getLabel()).indexOf(s)>-1)
	    	{
	    			Static.makeVisible(Gid, Nod);
	    			return;
	    	}  
	    	else if (iCol >= 0)
	    	{
	    		Object Obj=Nod.getLabel();
	    		if (Obj != null && Obj instanceof Vector)
	    		{
	    			//Vector Vec=(Vector)Obj;
	    			String s2=Sort.gets(Obj, iCol).toLowerCase();
	    			if (s2.startsWith(s.toLowerCase()))
	    			{
	    				Static.makeVisible(Gid, Nod);
		    			return;
	    			}
	    		}
	    	}
    	}
    }
    
    public void showColumnInTextfield(JCOutliner Gid)
    {
    	if (CboSpalten2 != null && Edt2 != null)
    	{
    		int iPosS=CboSpalten2.getSelectedAIC();
    		JCOutlinerNode Nod=Gid.getSelectedNode();
    		if (Nod!=null && Nod.getLabel()!=null && Nod.getLabel() instanceof Vector)
    		{
    			Vector Vec=(Vector)Gid.getSelectedNode().getLabel();
    			Edt2.setText(Sort.gets(Vec, iPosS));
    		}
    		//Edt.setText(getS(CboSpalten.getSelectedKennung()));
    	}
    }

	public void showGrid(String sTitel,Window FomModal,boolean bModal,String sUserdata)
	{
		//Grid = FrmGrid==null ? new JCOutliner(new JCOutlinerFolderNode("")):(JCOutliner)(FrmGrid instanceof JFrame ? ((JFrame)FrmGrid).getContentPane():((JDialog)FrmGrid).getContentPane()).getComponent(0);
		Grid = Grid==null ? new AUOutliner(new JCOutlinerFolderNode("")):Grid;
                boolean bFirst=FrmGrid==null;
    Container CP=null; 
		if(bFirst)
		{
			sTitle=sTitel;
			FrmGrid=FomModal==null ? (Window)new JFrame(sTitel):FomModal instanceof JFrame?new JDialog((JFrame)FomModal ,sTitel,bModal):new JDialog((JDialog)FomModal ,sTitel,bModal);
			if (FomModal==null)
                          ((JFrame)FrmGrid).setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        else
                          ((JDialog)FrmGrid).setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			CP = FrmGrid instanceof JFrame ? ((JFrame)FrmGrid).getContentPane():((JDialog)FrmGrid).getContentPane();
			CP.setLayout(new BorderLayout());
			//CP.add("North",new JLabel("Test !!!"));
			
                        /*final JTextArea Memo =new JTextArea();
                        Memo.setLineWrap(true);
                        Memo.setWrapStyleWord(true);*/
                        final JEditorPane Memo= new JEditorPane();
                        Memo.setEditable(false);
                        Memo.setContentType("text/html");
                        Memo.setFont(Transact.fontStandard);
                        Memo.setPreferredSize(new Dimension(150,34));
                        final JSplitPane PnlC = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,Memo,Grid);
                        PnlC.setContinuousLayout(true);
                        PnlC.setOneTouchExpandable(true);
                        PnlC.setResizeWeight(0.1);
                        CP.add("Center",PnlC);
                        // 
                        //CP.add("North",Memo);
			//iHeight = 300;
                        Grid.addItemListener(new JCOutlinerListener()
                        {
                          public void itemStateChanged(JCItemEvent e) {}
                          public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
                          public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
                          public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

                          public void outlinerNodeSelectEnd(JCOutlinerEvent e)
                          {
                            LineToMemo(Grid,Memo,VecBezeichnung,PnlC);
                            FrmGrid.addNotify();
                            showColumnInTextfield(Grid);
                          }
                        });

			FrmGrid.addWindowListener(new WindowListener()
			{
				public void windowClosed(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowClosed");
				}
				public void windowOpened(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowOpened");
				}
				public void windowClosing(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowClosing");
					///FrmGrid.dispose();
					///FrmGrid=null;
				}
				public void windowIconified(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowIconified");
				}
				public void windowDeiconified(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowDeiconified");
				}
				public void windowActivated(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowActivated");
					//int iAnz=getAnzahlElemente(null);
					//g.fixInfo("Size="+FrmGrid.getSize()+"/"+iAnz);

					///if (FrmGrid.getSize().height<40)
					///{

						//int iMass=0;
						for(int i=0;i<VecTitel.size()+1;i++)
						{
							if(Grid.getColumnWidth(i) > 350)
								Grid.setColumnWidth(i,350);
                                                        //Grid.repaint();
							//iMass += Grid.getColumnWidth(i);
						}
						//FrmGrid.setSize(Math.min(iMass+40,1000),Math.min(Grid.getNodeHeight()*getAnzahlElemente(null)+155,500));

                                                //g.progInfo("Tab.windowActivated");

                                                ///FrmGrid.pack();

                                                //Static.centerComponent(FrmGrid,FomModal);
                                                Grid.folderChanged(Grid.getRootNode());
						//g.fixInfo("Size="+FrmGrid.getSize()+"/"+iAnz);

						///FrmGrid.show();
					///}
				}
				public void windowDeactivated(WindowEvent e)
				{
					//g.fixInfo("FrmGrid.windowDeactivated");
				}
			});
		}
		else
		{
			if (FrmGrid instanceof JFrame)
                ((JFrame)FrmGrid).setTitle(sTitel);
			else
				((JDialog)FrmGrid).setTitle(sTitel);
			((JCOutlinerFolderNode)Grid.getRootNode()).removeChildren();
			//FrmGrid.getContentPane().removeAll();
			//FrmGrid.getContentPane().add("Center",Grid);
		}
		//g.fixInfo("vor show");
		//if (FomModal==null)
		showGrid(Grid,sUserdata);
    if (CP!=null)
      addSouth(CP,FrmGrid,Grid);
                if (bFirst)
                {
                  FrmGrid.pack();
                  Dimension dim=FrmGrid.getSize();
                  if (dim.width>800 || dim.height>500)
                    FrmGrid.setSize(Math.min(dim.width,800),Math.min(dim.height,500));
                  if (FomModal!=null && FomModal.isShowing())// FrmGrid.isShowing())
                    Static.centerComponent(FrmGrid, FomModal);
                }
                FrmGrid.toFront();
                FrmGrid.setVisible(true);
		//g.fixInfo("vor showGrid");
		//int iMass=showGrid(Grid);
		//g.printInfo("iMass="+iMass);

		//FrmGrid.show();

	}

        public int showGrid(JCOutliner Grid)
        {
          return showGrid(Grid,null);
        }

	@SuppressWarnings("unchecked")
	public int showGrid(JCOutliner Grid,String sUD)
	{
                if (Static.bWeb)
                	Transact.fixError(g,"showGrid im Web nicht möglich");
		JCOutlinerFolderNode GridRoot = (JCOutlinerFolderNode)Grid.getRootNode();
		if(GridRoot!=null)
			GridRoot.removeChildren();
		else
		{
			GridRoot = new JCOutlinerFolderNode("");
			Grid.setRootNode(GridRoot);
		}
                if (g !=null)
                  Grid.setBackground(Transact.ColBackground);
		//int iMom=bOut?-1:iPos;
		//push();
                int iPosF=VecTitel.indexOf("FARBE");
        int iAnz=getAnzahlElemente(null);
		boolean bFarbe = iPosF > -1 && (iAnz==0 || !(getInhalt("Farbe",0) instanceof String));
		//JCOutliner Grid = new JCOutliner(GridRoot);
		Vector VecSpalten = new Vector(VecBezeichnung==null?VecTitel:VecBezeichnung);
                if (bFarbe)
                  VecSpalten.removeElementAt(iPosF);
                if (g!=null && g.Def())
                  VecSpalten.addElement("Nr.");
		Grid.setColumnButtons(new jclass.util.JCVector(VecSpalten));
		Grid.setNumColumns(VecSpalten.size());
		Grid.setRootVisible(false);
		Grid.setColumnLabelSortMethod(Sort.sortMethod);
                Grid.selectNode(GridRoot,null);
		//OutBuild.setColumnWidth(0,200);
        boolean bBild=false;
        for(int i2=0;i2<iAnz;i2++)
		{
			Vector Vec = new Vector();
			for(int i=0;i<VecTitel.size();i++)
			{
				if (!bFarbe || !VecTitel.elementAt(i).equals("FARBE"))
				{
					Object Obj = getInhalt(VecTitel.elementAt(i),i2);
                                        //if (Obj instanceof Memo1 && g!=null && g.Prog())
                                        //  Obj=((Memo1)Obj).getValue();
                                        if (Obj instanceof Memo1)
                                          Obj=Html.check(((Memo1)Obj).getValue());
				//g.fixtestInfo(""+VecTitel.elementAt(i)+':'+Obj+'/'+(Obj==null?"":Obj.getClass().getName()));
					Vec.addElement(Obj == null? g==null || g.Prog() ? "<null>":null:Obj instanceof JButton ? ((JButton)Obj).getText()://Obj instanceof Region ? Static.print(Obj):
                                                       Obj instanceof Component ? ""+Obj/*Static.hash(Obj)*/:g!=null && g.Prog() && g.Debug() && g.Info() ? Sort.gets(Obj)+"/"+Static.hash(Obj):
                                                       Obj instanceof JCOutlinerNode ? Sort.gets(((JCOutlinerNode)Obj).getLabel()):
                                                       Obj instanceof Double && ((Double)Obj).doubleValue()==((Double)Obj).intValue() ? ((Double)Obj).intValue():
                                                       Obj instanceof Boolean ? Static.JaNein2((Boolean)Obj):
                                                       Obj instanceof java.sql.Timestamp ? Static.bZeitzone ? new Zeit(g,(Date)Obj,"dd.MM.yyyy HH:mm"):Transact.TS_String(Obj):
                                                       Obj instanceof Vector ? Sort.gets2((Vector)Obj,';'):
                                                       /*Obj instanceof String && (g==null || g.Prog()) ? ((String)Obj).replace(' ','.'):*/Obj instanceof String ? ((String)Obj).replaceAll("\r","<CR>"):Obj);
					if (Obj instanceof Image)
						bBild=true;
				}
				//Obj instanceof java.sql.Date ? Static.DateToString((Date)Obj) : Obj instanceof java.sql.Timestamp ? Static.DateTimeToString((Date)Obj) : Obj);
				//g.printInfo("nach"+VecTitel.elementAt(i));
			}
			//g.printInfo("iPos="+iPos);
                        if (g!=null && g.Def())
                          Vec.addElement(new Integer(i2));

			JCOutlinerNode Nod=new JCOutlinerNode(Vec,GridRoot);
                        if (sUD != null)
                          Nod.setUserData(getI(i2,sUD));
			if (bFarbe)
			{
				JCOutlinerNodeStyle Sty=new JCOutlinerNodeStyle();
                                //Sty.setItemIcon(null);
				Sty.setForeground(new Color(getI(i2,"FARBE")));
				Nod.setStyle(Sty);
			}
                        else
                          Nod.setStyle(new JCOutlinerNodeStyle());
			if (i2==iPos)
				Grid.selectNode(Nod,null);
		}
		if (bBild)
			Grid.setNodeHeight(100);
		Grid.folderChanged(GridRoot);

		int iMass=0;
		for(int i=0;i<VecSpalten.size();i++)
		{
                  //g.fixInfo("Spalte "+i+": "+Grid.getColumnDisplayWidth(i));
			if(Grid.getColumnDisplayWidth(i) > 250)
                          Grid.setColumnDisplayWidth(i,250);
			iMass += Grid.getColumnWidth(i);
		}
		//pop();
		return iMass;
	}
	
	public void changeTitel(String sOld,String sNeu)
	{
		int i = VecTitel.indexOf(sOld.toUpperCase());
		if (i>=0)
		{
			VecTitel.setElementAt(sNeu.toUpperCase(), i);
//			if (g!=null)
//				g.fixtestError("Tab.changeTitel von "+sOld+" auf "+sNeu.toUpperCase());
		}
	}

	@SuppressWarnings("unchecked")
	public void copyLine()
	{
		//int iPosOld=iPos;
		for(int i=0;i<VecTitel.size();i++)
		{
			Vector Vec=(Vector) VecInhalt.elementAt(i);
			if (bInsert)
				Vec.insertElementAt(bOut?null:Vec.elementAt(iPos),iPos);
			else
				Vec.addElement(bOut?null:Vec.elementAt(iPos));
		}
		if (!bInsert)
			iPos=((Vector)VecInhalt.elementAt(0)).size()-1;

		bOut = false;
		bEof = false;
		bFehler = false;
		bBof = false;
	}

        public void copyLine2()
        {
          bInsert=true;
          copyLine();
          moveNext();
          bInsert=false;
        }

	@SuppressWarnings("unchecked")
	public int newLine()
	{
		for(int i=0;i<VecTitel.size();i++)
		{
			Vector<Object> Vec=(Vector) VecInhalt.elementAt(i);
			if (bInsert)
				Vec.insertElementAt(null,iPos);
			else
			{
				Vec.addElement(null);
				iPos=Vec.size()-1;
			}
		}
		bOut = false;
		bEof = false;
		bFehler = false;
		bBof = false;
                return iPos;
		//g.debugInfo("newLine: insert="+bInsert+", pos="+iPos);
	}

        @SuppressWarnings("unchecked")
        public void newLine2()
	{
          iPos++;
		for(int i=0;i<VecTitel.size();i++)
		{
			Vector<Object> Vec=(Vector) VecInhalt.elementAt(i);
			if (bInsert && Vec.size()>iPos)
				Vec.insertElementAt(null,iPos);
			else
			{
				Vec.addElement(null);
				iPos=Vec.size()-1;
			}
		}
		bOut = false;
		bEof = false;
		bFehler = false;
		bBof = false;
		//g.debugInfo("newLine: insert="+bInsert+", pos="+iPos);
	}

	@SuppressWarnings("unchecked")
	public void addLine(Vector VecNeu)
	{
		if (VecNeu.size() == VecTitel.size())
		{
			for(int i=0;i<VecTitel.size();i++)
			{
				Vector<Object> Vec=(Vector) VecInhalt.elementAt(i);
				if (bInsert)
					Vec.insertElementAt(VecNeu.elementAt(i),iPos);
				else
					Vec.addElement(VecNeu.elementAt(i));
			}
			if (!bInsert)
				iPos=((Vector)VecInhalt.elementAt(0)).size()-1;
			bOut = false;
			bEof = false;
			bFehler = false;
			bBof = false;
			//g.debugInfo("newLine: insert="+bInsert+", pos="+iPos);
		}
		else
			Transact.fixError(g,getTitel()+".addLine: Spaltenanzahl stimmt nicht überein!");
	}

	public void deleteData(String sSpaltBez)
	{
		Vector<Object> Vec=getVecSpalte(sSpaltBez);
		if (Vec != null)
		for(int i=0;i<Vec.size();i++)
			Vec.setElementAt(null,i);
	}

	public void delColumn(String sSpaltBez)
	{
	  int i = VecTitel.indexOf(sSpaltBez.toUpperCase());
	  if (i==-1)
          {
		  	Transact.fixError(g,getTitel()+".delColumn: Spalte " + sSpaltBez + " nicht gefunden !!!");
		  	Transact.fixError(g,"vorhanden:"+VecTitel);
          }
	  else
	  {
	    VecTitel.removeElementAt(i);
	    VecInhalt.removeElementAt(i);
	  }
	}

	public void delIf(String sSpaltBez,Object Obj)
	{
	  int iR = VecTitel.indexOf(sSpaltBez.toUpperCase());
	  if (iR==-1)
          {
		  	Transact.fixError(g,getTitel()+".delIf: Spalte " + sSpaltBez + " nicht gefunden !!!");
		  	Transact.fixError(g,"vorhanden:"+VecTitel);
            return;
          }
	  else
	  {
	    boolean b=true;
	    Vector Vec=(Vector)VecInhalt.elementAt(iR);
	    for(int i=0;i<Vec.size();i++)
	      if (!Static.Gleich(Vec.elementAt(i),Obj))
		b=false;
	    if (b)
	    {
	      VecTitel.removeElementAt(iR);
              if (VecBezeichnung != null)
                VecBezeichnung.removeElementAt(iR);
	      VecInhalt.removeElementAt(iR);
	    }
	  }
	}

	public void delIf(String sSpaltBez,boolean b)
	{
	  int iR = VecTitel.indexOf(sSpaltBez.toUpperCase());
	  if (iR==-1)
          {
		  	Transact.fixError(g,getTitel()+".delIf: Spalte " + sSpaltBez + " nicht gefunden !!!");
		  	Transact.fixError(g,"vorhanden:"+VecTitel);
          }
	  else
	  {
	    if (b)
	    {
	      VecTitel.removeElementAt(iR);
	      VecInhalt.removeElementAt(iR);
	    }
	  }
	}
	
	private void clearIf(boolean b,Tabellenspeicher Tab)
	{
		if (b)
		{
			if (Tab!=null)
          		Tab.copyLine(this);
			moveNext();
        }
        else if (Tab==null)
        	clearInhalt();
        else
        	moveNext(); 
	}
	
	public void clearNot(String SpaltBez,double d,char c,Tabellenspeicher Tab)
    {
		for(moveFirst();!eof();)
        {
		  double d2=getF(SpaltBez);
		  clearIf(c=='=' && d2==d || c=='<' && d2<d || c=='>' && d2>d || c=='≥' && d2>=d || c=='≤' && d2<=d || (c=='≠' || c=='!') && d2 != d,Tab);
        }
    }
	
	public void clearNot(String sSpaltBez,DateWOD dt,char c,Tabellenspeicher Tab)
    {
		for(moveFirst();!eof();)
        {
		  DateWOD dt2=Sort.getDW(getDate(sSpaltBez));
		  if (dt==null || dt.isNull() || dt2==null || dt2.isNull())
			  clearIf(c=='=' && (dt==null || dt.isNull()) && (dt==null || dt.isNull()),Tab);
		  else
			  clearIf(dt2.compareDay(c,dt),Tab);
        }
    }
	
	public void clearNot(String SpaltBez,Object Obj,Tabellenspeicher Tab)
    {
		for(moveFirst();!eof();)
        {
			clearIf(Obj==Boolean.TRUE ? getB(SpaltBez): Static.Gleich(getInhalt(SpaltBez),Obj),Tab);
        }
    }
	
	public void clearNotS(String SpaltBez,String s,Tabellenspeicher Tab)
    {
		for(moveFirst();!eof();)
        {
		  String s2=getS(SpaltBez);
		  clearIf(s2!=null && s2.startsWith(s),Tab);
        }
    }
	
	public void clearNull(String SpaltBez,Tabellenspeicher Tab)
    {
		for(moveFirst();!eof();)
        {
		  //String s2=getS(SpaltBez);
			clearIf(!isNull(SpaltBez),Tab);
        }
    }
	
	public void clearNotNull(String SpaltBez,Tabellenspeicher Tab,boolean b)
    {
		for(moveFirst();!eof();)
        {
			clearIf(b ? !getB(SpaltBez): isNull(SpaltBez),Tab);
//        	  moveNext();
//          else
//        	  clearInhalt();
        }
    }

        public void clearWithVec(String SpaltBez,Vector Vec)
        {
          if (Vec==null)
            return;
          int iAnz=getAnzahlElemente(null);
          for(moveFirst();!eof();)
          {
            //g.testInfo("check "+getI(SpaltBez));
            if (Vec.contains(getInhalt(SpaltBez)))
              moveNext();
            else
              clearInhalt();
          }
          g.fixInfo("clearWithVec:"+iAnz+"->"+getAnzahlElemente(null));
        }
        
        public int remove(String sSpalte,int i)
        {
        	int iAnz=0;
        	for(moveFirst();!eof();)
            {
              //g.testInfo("check "+getI(SpaltBez));
              if (getI(sSpalte)==i)
              {
            	  clearInhalt();
            	  iAnz++;
              }
              else
                moveNext();
            }
        	return iAnz;
        }

        /*public void clearWithTab(Tabellenspeicher Tab)
        {
         for(moveFirst();!eof();)
         {
           boolean bDa=true;
           int iAic=getI("aic_stamm");
           if (Tab.posInhalt("aic_stamm",iAic))
             for(;bDa && !Tab.eof() && Tab.getI("aic_stamm")==iAic;Tab.moveNext())
               bDa=g.getVon().before(Tab.getTimestamp("aus")) || !Tab.isNull("ein") && g.getBis().after(Tab.getTimestamp("ein"));
           if (bDa)
             moveNext();
           else
             clearInhalt();
         }
       }*/

	public boolean clearInhaltS(String SpaltBez,Object ObjWert)
	{
		//boolean bOk=posInhalt(SpaltBez,ObjWert);
                int iPos2=getPos(SpaltBez,ObjWert);
		if(iPos2>=0)
                  clearInhalt(iPos2);

		return iPos2>=0;
	}

        public void clearInhalt(int iPos2)
        {
          if (size()>iPos2)
           for(int i = 0; i < VecTitel.size(); i++)
           {
        	   Vector Vec=(Vector)VecInhalt.elementAt(i);
        	   if (Vec.size()>iPos2)
        		   Vec.removeElementAt(iPos2);
        	   else
        		   g.fixtestError((sTT==null?"":sTT+": ")+"clearInhalt: "+Sort.gets(VecTitel, i)+" hat keine Zeile "+iPos2);
           }
          else
        	  g.fixtestError((sTT==null?"":sTT+": ")+"clearInhalt mit "+iPos2+" nicht möglich");
        }

        public boolean clearInhalt(String SpaltBez,Object ObjWert)
        {
                boolean bOk=posInhalt(SpaltBez,ObjWert);

                if(bOk)
                        clearInhalt();

                return(bOk);
        }

	public void clearInhalt()
	{
          if (!bOut)
          {
            for(int i = 0; i < VecTitel.size(); i++) {
              Vector Vec=VecInhalt.elementAt(i);
              if (iPos>=0 && Vec.size()>iPos)
            	  Vec.removeElementAt(iPos);
              else
            	  Static.printError((sTT==null?"Tabellenspeicher":sTT)+".clearInhalt: Spalte "+VecTitel.elementAt(i)+" hat keine Zeile "+iPos);
            }
            int iAnz = ((Vector)VecInhalt.elementAt(0)).size();
            if(iAnz == iPos && iAnz > 0)
              iPos--;
            bOut = iAnz == 0 || iAnz <= iPos;
            bBof = bOut;
            bEof = bOut;
          }
	}

	public void clearInhalt2()
	{
	  if (!bOut)
          {
            for(int i = 0; i < VecTitel.size(); i++)
              ((Vector)VecInhalt.elementAt(i)).removeElementAt(iPos);
            int iAnz = ((Vector)VecInhalt.elementAt(0)).size();
            iPos--;
            bOut = iPos<0;
            bBof = bOut;
            bEof = iAnz == 0;
          }
	}

        public void clearWith(Vector Vec)
        {
          for(moveFirst();!eof();)
          {
            if (Vec.contains(getInhalt(sGruppe)))
                moveNext();
            else
              clearInhalt();
          }
        }

        public void popClearInhalt()
        {
          pop();
          //g.progInfo("popClearInhalt: iPos="+iPos);
          for(int i=0;i<VecTitel.size();i++)
            ((Vector)VecInhalt.elementAt(i)).setSize(iPos+1);
        }

        public void compress2(Tabellenspeicher Tab,Tabellenspeicher Tab2)
        {
          //g.testInfo("compress2");
          Vector<String> VecG=new Vector<String>();
          boolean bCalc=Tab.exists("Abfrage");
          int iAbfrage=0;
          if (bCalc)
          {
            iAbfrage=Tab.getI("Abfrage");
            Tab.posInhalt("Abfrage", iAbfrage);
          }
          else
            Tab.moveFirst();
          for(;!Tab.eof() && (!bCalc || Tab.getI("Abfrage")==iAbfrage);Tab.moveNext())
            if ((Tab.getI("bits")&Abfrage.cstGruppiert) >0)
              VecG.addElement(Tab.getS("Kennung"));
          moveFirst();
          //g.progInfo("compress2: VecG="+VecG);
          while (!bOut)
          {
            Vector<Object> VecM=new Vector<Object>();
            for (int i=0;i<VecG.size();i++)
              VecM.addElement(getInhalt(VecG.elementAt(i)));
            //g.progInfo("compress2: VecM="+VecM);
            //g.progInfo(getI(iPos,"aic_bew_pool")+" first");
            if (bCalc) Tab.posInhalt("Abfrage", iAbfrage); else Tab.moveFirst();
              for(;!Tab.eof() && (!bCalc || Tab.getI("Abfrage")==iAbfrage);Tab.moveNext())
              {
                String sArt = Tab.isNull("Ergebnisart") ? null : bCalc ? Tab.getS("Ergebnisart") : Tab2.getKennung(Tab.getI("Ergebnisart"));
                if (sArt != null && (sArt.equals("count") || sArt.equals("count_distinct")))
                {
                  String s=null;
                  boolean bVonBis=Tab.getS("Datentyp").equals("BewVon_Bis");
                  if (!Tab.exists("Kennung3") && bVonBis)
                    s="d"+Tab.getS("Kennung");
                  else
                    s=Tab.getS(bVonBis?"Kennung3":"Kennung");
                  //Vector<Object> Vec=getVecSpalte(s!=null?s:Tab.getS(bVonBis?"Kennung3":"Kennung"));
                  AddUp Obj = new AddUp((Tab.getI("bits")&Abfrage.cstKeineEinheit)==0);
                  if (sArt.equals("count_distinct"))
                    Obj.add2(getInhalt(s,iPos));
                  else if (sArt.equals("count"))
                	Obj.add();
                  setInhalt(iPos,s,Obj);
                  if (bVonBis)
                  {
                    setInhalt(iPos,'v'+s.substring(1),null);
                    setInhalt(iPos,'b'+s.substring(1),null);
                  }
                }
              }
            moveNext();
            boolean bGleich=!bOut;
            for (int i=0;i<VecG.size();i++)
              bGleich=bGleich&Static.Gleich(VecM.elementAt(i),getInhalt(VecG.elementAt(i)));
            while (bGleich)
            {
              if (bCalc) Tab.posInhalt("Abfrage", iAbfrage); else Tab.moveFirst();
              for(;!Tab.eof() && (!bCalc || Tab.getI("Abfrage")==iAbfrage);Tab.moveNext())
              {
                String sArt = Tab.isNull("Ergebnisart") ? null : bCalc ? Tab.getS("Ergebnisart"):Tab2.getKennung(Tab.getI("Ergebnisart"));
                //g.testInfo("Ergebnisart="+sArt);
                if (sArt != null)
                {
                  boolean bPeriode=(Tab.getI("Bits")&Abfrage.cstPeriode)>0;
                  Vector<String> VecMehr=Tab.exists("mehr") ? (Vector)Tab.getInhalt("mehr"):null;
                  if (bPeriode && VecMehr==null)
                  {
                	  VecMehr=new Vector<String>();
                	  VecMehr.addElement("p0");
                  }
                  String s=null;
                  if (!Tab.exists("Kennung3") && Tab.getS("Datentyp").equals("BewVon_Bis"))
                    s="d"+Tab.getS("Kennung");
                  for (int i=0;i<(VecMehr!=null ? VecMehr.size():1);i++)
                  {
                	  String s2=s!=null?s:Tab.getS(Tab.getS("Datentyp").equals("BewVon_Bis")?"Kennung3":"Kennung");
                	  if (VecMehr!=null)
                			  s2+= VecMehr.elementAt(i);
                	  Vector<Object> Vec=getVecSpalte(s2);
//                	  g.fixtestError("compress2: "+s2+"->"+Vec);
	                  Object Obj=Vec==null ? null:Vec.elementAt(iPos);
	                  Object Obj2=Vec==null ? null:Vec.elementAt(iPos-1);
	                  Object Obj3=null;
	                  if (sArt.equals("count") || sArt.equals("count_distinct"))
	                  {
	                    //g.progInfo(getI(iPos,"aic_bew_pool")+":"+Obj+"/"+Obj2);
	                    Obj3=Obj2;
	                    boolean b=Obj3==null || !(Obj3 instanceof AddUp);
	                    if (b)
	                      Obj3 = new AddUp((Tab.getI("bits")&0x02000000/*cstKeineEinheit*/)==0);
	                    if (sArt.equals("count"))
	                    {
	                      ((AddUp)Obj3).add();
	                      if (b)((AddUp)Obj3).add();
	                    }
	                    else if (sArt.equals("count_distinct"))
	                    {
	                      ((AddUp)Obj3).add2(Obj);
	                      if (b) ((AddUp)Obj3).add2(Obj2);
	                    }
	                  }
	                  else
	                    Obj3=sArt.equals("last") ? Obj: sArt.equals("sum") ? new Double(Sort.getf(Obj)+Sort.getf(Obj2)):
	                      sArt.equals("min") ? Obj==null ? Obj2:Obj2==null ? Obj:Sort.getf(Obj)<Sort.getf(Obj2) ? Obj:Obj2:
	                      sArt.equals("max") ? Obj==null ? Obj2:Obj2==null ? Obj:Sort.getf(Obj)>Sort.getf(Obj2) ? Obj:Obj2:(Object)"???";
	                  if (Vec != null)
	                	  Vec.setElementAt(Obj3,iPos-1);
                  }
                }
              }
              int i=iPos;
              clearInhalt();
              setPos(i);
              bGleich=!bOut;
              for (i=0;i<VecG.size();i++)
                bGleich=bGleich&Static.Gleich(VecM.elementAt(i),getInhalt(VecG.elementAt(i)));
            }
          }
          moveFirst();
        }

        @SuppressWarnings("unchecked")
        public void compress(String sSp,boolean bSum)
        {
          //int iAbfrage=TabSpalten.getI("Abfrage");
          //String sSp=TabSpalten.getS("Kennung");
          //showGrid();
          moveFirst();
          while (!bOut)
          {
            String sGleich=getS(sSp);
            moveNext();
            while (!bOut && getS(sSp).equals(sGleich))
            {
              //for(TabSpalten.posInhalt("Abfrage",iAbfrage);!TabSpalten.eof() && TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
              if (bSum)
              for(int i=0;i<VecTitel.size();i++)
              {
                Vector<Object> Vec=(Vector) VecInhalt.elementAt(i);
                Object Obj=Vec.elementAt(iPos);
                Object Obj2=Vec.elementAt(iPos-1);
                Object Obj3=Obj instanceof Double || Obj2 instanceof Double ? new Double(Sort.getf(Obj)+Sort.getf(Obj2)):
                                 Obj==null || Obj2==null || !Obj.equals(Obj2) ? Obj2:Obj;
                //g.progInfo(i+":"+Static.print(Obj)+"/"+Static.print(Obj2)+"->"+Obj3);
                Vec.setElementAt(Obj3,iPos-1);
              }
              int i=iPos;
              clearInhalt();
              setPos(i);
            }
          }
          moveFirst();
        }

        public boolean posInhalt(int i,int i2)
        {
          return posInhalt(new Integer(i),new Integer(i2));
        }

        public int getPos(int i1,int i2)
        {
          for(int i=0;i<size();i++)
            if (getI(i,sGruppe)==i1 && getI(i,sAIC)==i2)
              return i;
          return -1;
        }

        public boolean posIC(String sSp,String s)
        {
          for(moveFirst();!out();moveNext())
            if (getS(sSp).equalsIgnoreCase(s))
              return true;
          return false;
        }

	public boolean posInhalt(Object Obj1, Object Obj2)
	{
	    //g.progInfo("posInhalt("+sGruppe+'='+Obj1+", "+sAIC+'='+Obj2);
            if (Obj1==null || isEmpty())
            {
              bInsert=false;
              return false;
            }
		if(posInhalt(sGruppe,Obj1) || Obj1 instanceof Integer && posInhalt(sGruppe,((Integer)Obj1).longValue()))
		{
                  Obj1=getInhalt(sGruppe);
			for(;!out() && getInhalt(sGruppe).equals(Obj1) && !getInhalt(sAIC).equals(Obj2);moveNext());

			if(out())
			    moveLast();
			else if(!Obj1.equals(getInhalt(sGruppe)))
				movePrevious();
			//bOut = false;
		}
		else
			moveLast();

		bInsert=Obj1.equals(getInhalt(sGruppe)) && !Obj2.equals(getInhalt(sAIC)) ;
		return(Obj1.equals(getInhalt(sGruppe)) && Obj2.equals(getInhalt(sAIC)));

	}

	@SuppressWarnings("unchecked")
	public boolean verschieben(int riVon, int riNach)
	{

		boolean bOk=false;
		Object ObjElement;
		VecSpalte = (Vector)VecInhalt.elementAt(0);
		int iVecSize = VecSpalte.size();
		//System.err.println("verschieben von "+riVon+" nach "+riNach+" Größe="+iVecSize);
		bOk= iVecSize>riVon && riVon>=0 && riNach>=0;
		for(int i=0;i<VecInhalt.size() && bOk && riVon!=riNach;i++)
		{
			//System.err.print(i);
			VecSpalte = (Vector)VecInhalt.elementAt(i);
			ObjElement=VecSpalte.elementAt(riVon);
			if(iVecSize>riNach)
				VecSpalte.insertElementAt(ObjElement,riNach);
			else
				VecSpalte.addElement(ObjElement);
			VecSpalte.removeElementAt(riVon+(riVon>riNach ? 1:0));
		}
		//System.err.println("");
		return(bOk);
	}

	public void zusammenfassen(String sSpaltBez)
	{
		Vector VecHaupt = getVecSpalte(sSpaltBez);
		Object ObjVorher = VecHaupt.elementAt(0);
		Object ObjAktuell;
		int iPosGefunden;

		for(int i=1;i<VecHaupt.size();i++)
		{
			ObjAktuell=VecHaupt.elementAt(i);
			if(!ObjAktuell.equals(ObjVorher) && (iPosGefunden=VecHaupt.lastIndexOf(ObjAktuell,i-1))!=-1)
				verschieben(i,iPosGefunden);
			else
				ObjVorher=ObjAktuell;
		}
	}

	public void group_sort(String sSpaltBez, String sSortSpaltBez)
	{
		Vector VecHaupt = getVecSpalte(sSpaltBez);
		Vector VecSort = getVecSpalte(sSortSpaltBez);

		int iPosGefunden;

		for(int i=1;i<VecHaupt.size();i++)
		{
			iPosGefunden=VecHaupt.lastIndexOf(VecHaupt.elementAt(i),i-1);
			if(iPosGefunden!=-1)
			{
				while((iPosGefunden>=0) && VecHaupt.elementAt(i).equals(VecHaupt.elementAt(iPosGefunden)) && (geti(VecSort.elementAt(iPosGefunden))>geti(VecSort.elementAt(i))))
					iPosGefunden--;

				verschieben(i,iPosGefunden+1);
			}
		}
	}

	public void sort(String sSpaltBez,boolean bASC)
	{

		//long lClock = Static.get_ms();
		int iAnzahl=((Vector)VecInhalt.elementAt(0)).size();
		if (iAnzahl<2)
			return;

		/*Vector<Object> VecInhalt2=new Vector<Object>();
		for(int i=0;i<iAnzahl;i++)
		{
			Vector<Object> Vec=new Vector<Object>();
			for(int i2=0;i2<VecTitel.size();i2++)
				Vec.addElement(((Vector)VecInhalt.elementAt(i2)).elementAt(i));
			VecInhalt2.addElement(Vec);
		}*/

		int[] indexes = Sort.sort(getVecSpalte(sSpaltBez),bASC);//new JCqsort(VecInhalt2,Sort.sortMethod).sort(VecTitel.indexOf(sSpaltBez.toUpperCase()),bASC?JCqsort.ASCENDING:JCqsort.DESCENDING);
		for(int iSpalte=0;iSpalte<VecInhalt.size();iSpalte++)
		{
			Vector<Object> Vec=new Vector<Object>();
			Vector VecAlt=(Vector) VecInhalt.elementAt(iSpalte);
                        for(int i=0;i<VecAlt.size();i++)
				Vec.addElement(VecAlt.elementAt(indexes[i]));
			/*for(int i=0;i<iAnzahl;i++)
				Vec.addElement(null);
			for(int i=0;i<iAnzahl;i++)
				Vec.setElementAt(VecAlt.elementAt(i),indexes[i]);*/
			VecInhalt.setElementAt(Vec,iSpalte);
		}

		//g.fixInfo("sort("+sSpaltBez+"):"+iAnzahl+"Stk="+(Static.get_ms()-lClock)+" ms");
	}

/*
	public void sortOld(String sSpaltBez,boolean bASC)
	{
		long lClock = Static.get_ms();
		JCVector vec_sort = new JCVector(getVecSpalte(sSpaltBez));
		if (vec_sort.size()<2)
			return;
		int[] indexes = new int[vec_sort.size()];
			vec_sort.sort(0,vec_sort.size()-1,Sort.sortVecMethod,indexes);
		for(int iSpalte=0;iSpalte<VecInhalt.size();iSpalte++)
		{
			Vector Vec=new Vector();
			Vector VecAlt=(Vector) VecInhalt.elementAt(iSpalte);
			for(int i=0;i<vec_sort.size();i++)
				Vec.addElement(VecAlt.elementAt(indexes[bASC?i:vec_sort.size()-i-1]));
			VecInhalt.setElementAt(Vec,iSpalte);
		}
		g.fixInfo("sort("+sSpaltBez+"):"+vec_sort.size()+"Stk="+(Static.get_ms()-lClock)+" ms");
	}
*/
	@SuppressWarnings("unchecked")
	public void addSpalten(Tabellenspeicher Tab2,int iAb)
        {
          for(int i=iAb;i<Tab2.VecTitel.size();i++)
          {
            VecTitel.addElement(""+Tab2.VecTitel.elementAt(i));
            VecInhalt.addElement(new Vector((Vector)Tab2.VecInhalt.elementAt(i)));
          }
        }

        public void copyFrom(Tabellenspeicher Tab2)
	{
		//g.progInfo("copyFrom "+Tab2+"->"+this);
        	if (Tab2==null)
        	{
        		g.fixtestError("Tabellenspeicher.copyFrom: Tab2 ist NULL und kann nicht kopiert werden");
        		return;
        	}
		VecTitel = new Vector<String>();
		VecInhalt = new Vector<Vector>();
		if (Tab2.VecBezeichnung != null)
			VecBezeichnung = new Vector<String>();
		for(int i=0;i<Tab2.VecTitel.size();i++)
		{
			if (Tab2.VecBezeichnung != null && Tab2.VecBezeichnung.size()>i)
		          VecBezeichnung.addElement(Tab2.VecBezeichnung.elementAt(i));
			VecTitel.addElement(Tab2.VecTitel.elementAt(i));
			Vector<Object> Vec = new Vector<Object>();
			Vector VecAlt = (Vector)Tab2.VecInhalt.elementAt(i);
			for(int i2=0;i2<VecAlt.size();i2++)
				Vec.addElement(VecAlt.elementAt(i2));
			VecInhalt.addElement(Vec);
		}
		iPos = Tab2.iPos;
		bEof = Tab2.bEof;
		bBof = Tab2.bBof;
		bOut = Tab2.bOut;
		bFehler = Tab2.bFehler;
	}

        @SuppressWarnings("unchecked")
        public void copyLine(Tabellenspeicher Tab2)
        {
          //int iP=Tab2.getPos();
          for(int i=0;i<Tab2.VecTitel.size();i++)
          {
            ((Vector) VecInhalt.elementAt(i)).addElement(Tab2.getInhalt(i));
          }
        }
        
        @SuppressWarnings("unchecked")
    	public void copyLine(Tabellenspeicher Tab2,int iP)
            {
              //int iP=Tab2.getPos();
              for(int i=0;i<Tab2.VecTitel.size();i++)
              {
                ((Vector) VecInhalt.elementAt(i)).setElementAt(Tab2.getInhalt(i),iP);
              }
            }

        @SuppressWarnings("unchecked")
        public void copyTo(Tabellenspeicher Tab2)
        {
          //Tab2.clearAll();
          for (int i=0;i<Tab2.VecTitel.size();i++)
          {
            String sSpalte=Tab2.VecTitel.elementAt(i);
            if (exists(sSpalte) && i>0)
              Tab2.VecInhalt.setElementAt(new Vector(getVecSpalte(sSpalte)),i);
            else
            {
              Vector Vec=new Vector();
              int iAnzahl=((Vector)VecInhalt.elementAt(0)).size();
              for(int i2=0;i2<iAnzahl;i2++)
                Vec.addElement(null);
              Tab2.VecInhalt.setElementAt(Vec,i);
              //Static.printError("Tabellenspeicher.copyTo: Spalte " + sSpalte + " nicht gefunden!");
            }
          }
          Tab2.moveFirst();
        }

        @SuppressWarnings("unchecked")
        public void addTo(Tabellenspeicher Tab2,boolean bFirst)
        {
          //Tab2.clearAll();
          for (int i=0;i<Tab2.VecTitel.size();i++)
          {
            String sSpalte=Tab2.VecTitel.elementAt(i);
            int iAnzahl=((Vector)VecInhalt.elementAt(0)).size();
            Vector<Object> VecWrite=(Vector)Tab2.VecInhalt.elementAt(i);
            if (exists(sSpalte) && (bFirst || i>0))
            {
              Vector Vec=getVecSpalte(sSpalte);
              for (int i2 = 0; i2 < iAnzahl; i2++)
                VecWrite.addElement(Vec.elementAt(i2));
            }
            else
              for(int i2=0;i2<iAnzahl;i2++)
                VecWrite.addElement(null);
          }
        }

        @SuppressWarnings("unchecked")
        public void addTab(Tabellenspeicher Tab2)
        {
          if (Tab2==null)
            return;
          for(int i=0;i<Tab2.VecTitel.size();i++)
          {
            Vector<Object> Vec = (Vector)VecInhalt.elementAt(i);
            Vector VecPlus = (Vector)Tab2.VecInhalt.elementAt(i);
            for(int i2=0;i2<VecPlus.size();i2++)
              Vec.addElement(VecPlus.elementAt(i2));
            //VecInhalt.setElementAt(Vec,i);
          }
        }

        @SuppressWarnings("unchecked")
        public void addTabS(Tabellenspeicher Tab2)
        {
          if (Tab2==null)
            return;
          for(int i=0;i<Tab2.VecTitel.size();i++)
          {
            Vector<Object> Vec = (Vector)VecInhalt.elementAt(i);
            Vector VecPlus = (Vector)Tab2.VecInhalt.elementAt(i);
            for(int i2=0;i2<VecPlus.size();i2++)
              Vec.addElement(""+VecPlus.elementAt(i2));
            //VecInhalt.setElementAt(Vec,i);
          }
        }

        @SuppressWarnings("unchecked")
        public void Fremd_Import(Stack Sta,Tabellenspeicher TabSpalten,boolean bODBC)
        {
          Global g=new Global();
          String sConn=""+Sta.pop();
          String sTab=""+Sta.pop();
          String sPW=Sta.isEmpty() ? null:""+Sta.pop();
          String sName=Sta.isEmpty() ? null:""+Sta.pop();
          //t.fixInfo("ODBC_Import mit "+sConn+"/"+sName+"/"+sPW+"/"+sTab);
          if (bODBC)
        	  Transact.fixError(g,"Fremd_Import: ODBC wird nicht mehr unterstützt");//t.connect2(sConn,sName,sPW);
          else
            g.connect3(sConn,sName,sPW);

          Tabellenspeicher Tab2=new Tabellenspeicher(g,"select * from "+sTab,true);
          g.disconnect();

          //Tab2.showGrid("ODBC");

          int iAbfrage=TabSpalten.getI("Abfrage");
          int iAnz=0;
          for(TabSpalten.posInhalt("Abfrage",iAbfrage);!TabSpalten.eof() && TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
          {
            Vector Vec=new Vector(Tab2.getVecSpalte(TabSpalten.getS("Bezeichnung")));
            //g.fixInfo(TabSpalten.getS("Bezeichnung")+":"+(Vec==null?-1:Vec.size())+"->"+TabSpalten.getS("Kennung")+":"+
            //    VecTitel.indexOf(TabSpalten.getS("Kennung").toUpperCase()));
            if (Vec != null)
            {
              VecInhalt.setElementAt(Vec, VecTitel.indexOf(TabSpalten.getS("Kennung").toUpperCase()));
              if (iAnz==0)
              {
                iAnz = Vec.size();
                bEof = iAnz==0;
                bOut = iAnz==0;
              }
            }
          }
          for (int i=0;i<VecTitel.size();i++)
            if (((Vector)VecInhalt.elementAt(i)).size()==0)
              for (int i2=0;i2<iAnz;i2++)
                ((Vector)VecInhalt.elementAt(i)).addElement(null);
        }

        private boolean writeFile(char cTrennzeichen,String sFile)
        {
          bFileError=false;
          try
          {
            FileOutputStream fos;
            String s;
            boolean bTrenn=cTrennzeichen!=0;
            if (new File(sFile).exists())
              fos=new FileOutputStream(sFile,true);
            else
            {
              fos = new FileOutputStream(sFile, false);
              if (bTrenn)
              {
            	s = "" + VecBezeichnung.elementAt(0);
            	for (int i = 1; i < VecBezeichnung.size(); i++)
            	  s += cTrennzeichen + "" + VecBezeichnung.elementAt(i);
            	s += "\r\n";
            	fos.write(s.getBytes()); //Static.CP));
              }
            }
            for(moveFirst();!eof();moveNext())
            {
              s=""+getElementAt(iPos,0);
              if (!bTrenn)
            	  s=s.replace("\r", "");
              for(int i=1;i<VecTitel.size();i++)
              {
                Object Obj=getElementAt(iPos, i);
                s += cTrennzeichen + "" + (Obj==null?"":Obj instanceof Date?new Zeit(g,(Date)Obj,"yyyy/MM/dd HH:mm"):
                                           Obj instanceof String || Obj instanceof Memo1 ? getm(Obj).replaceAll("\r","<CR>").replaceAll("\n","<LF>"):Obj);
              }
              s+="\r\n";
              fos.write(s.getBytes());//Static.CP));
            }
            fos.close();
            return true;
          }
          catch (IOException ex)
          {
            bFileError=true;
            Transact.fixError(g,getTitel()+".writeFile: open/close - "+ex);
            return false;
          }
        }

        /*public void readFile(char cTrennzeichen,String sFile)
        {
          readFile(cTrennzeichen,sFile,false);
        }*/

        private Object tryInt(String s,boolean b)
        {
          if (b)
            try
            {
              return new Integer(s);
            }
            catch (Exception e)
            {}
          return s;
        }

	@SuppressWarnings("unchecked")
	public void readFile(char cTrennzeichen,String sFile,boolean bTitel)
	{
          //g.fixInfo("readFile "+sFile+";"+cTrennzeichen+";"+bTitel);
          if (!sFile.startsWith("http") && !sFile.startsWith("file:"))
            sFile="file:"+(File.separator.equals("/") ? "//":"\\")+sFile;
          //g.fixInfo("readFile:"+sFile);
          bFileError=false;
		clearAll();
		try
		{
			java.net.URL url=new java.net.URL(sFile);
			InputStream is=(InputStream)url.getContent();
			//DataInputStream Buf=new DataInputStream(is);
			BufferedReader Buf=new BufferedReader(new InputStreamReader(is));
                        int iPlus=0;
                        if (bTitel)
                          Buf.readLine();
                        else
                          iPlus=1;
			String s=Buf.readLine();
			while (s != null)
			{
				newLine();
				int i2=0;
				while (s.indexOf(cTrennzeichen)>-1)
				{
					int i=s.indexOf(cTrennzeichen);
					((Vector) VecInhalt.elementAt(i2+iPlus)).setElementAt(tryInt(s.substring(0,i),bTitel),iPos);
					i2++;
					s=s.substring(i+1);
				}
                                if (i2+iPlus < VecInhalt.size())
                                  ((Vector) VecInhalt.elementAt(i2+iPlus)).setElementAt(tryInt(s,bTitel),iPos);
				s=Buf.readLine();
			}
			Buf.close();

		}catch(IOException io)
		{
                  bFileError=true;
                  Transact.fixError(g,getTitel()+".readFile(): IOException - "+io);
		}
		moveFirst();
	}

	public void CalcImport(String sTrennzeichen,Object fil,Tabellenspeicher Tab,boolean bTitel)
	{
		char cTrennzeichen=sTrennzeichen==null || sTrennzeichen.equals("")?0:sTrennzeichen.equals("Tab")?9:sTrennzeichen.charAt(0);
                bFileError=false;
		clearAll();
		//try
		//{
			BufferedReader Buf=null;
			if (fil instanceof File)
			{

				try
				{
                                  InputStreamReader FR=new InputStreamReader(new FileInputStream((File)fil),Static.CP);
					Buf=new BufferedReader(FR);
                                        if (bTitel)
                                          Buf.readLine();
				}
                                catch(FileNotFoundException io)
				{
                                  bFileError=true;
                                  Transact.fixError(g,getTitel()+".CalcImport(): FileNotFound - "+fil);
                                  return;
				}
                                catch(IOException io)
                                {
                                  bFileError=true;
                                  Transact.fixError(g,getTitel()+".CalcImport(): IOException - "+fil);
                                  return;
                                }
			}

			int iAbfrage=Tab.getI("Abfrage");
			String s=readLine(Buf==null?fil:Buf,cTrennzeichen==0?Tab:null,iAbfrage);
			while (s != null && iAnzFehler<5)
			{
				newLine();
				int i2=0;
				Tab.posInhalt("Abfrage",iAbfrage);
                                boolean b1=s.startsWith("\"");
				int i=ZeichenAnzahl(s,cTrennzeichen,iAbfrage,Tab,b1);
				while (iAnzFehler<5 && i>-1)
				{
					//((Vector) VecInhalt.elementAt(i2+1)).setElementAt(s.substring(0,i),iPos);
					ObjToTab(iAbfrage,Tab,s.substring(b1?1:0,i));
					//setInhalt(Tab.getS("Kennung3"),s.substring(0,i));
					i2++;
					s=s.substring(i+(cTrennzeichen==0?0:b1?2:1));
					Tab.moveNext();
                                        b1=s.startsWith("\"");
					i=ZeichenAnzahl(s,cTrennzeichen,iAbfrage,Tab,b1);
				}
				//((Vector) VecInhalt.elementAt(i2+1)).setElementAt(s,iPos);
				if (cTrennzeichen>0)
					ObjToTab(iAbfrage,Tab,b1?s.substring(1,s.length()-1):s);
				s=readLine(Buf==null?fil:Buf,cTrennzeichen==0?Tab:null,iAbfrage);
			}

		if (Buf != null)
		{
			try
			{
				Buf.close();
			}catch(IOException io)
			{
                          bFileError=true;
				Transact.fixError(g,getTitel()+".CalcImport(): Buf.close() - "+io);
			}
		}
		moveFirst();
	}

	private String readLine(Object Buf,Tabellenspeicher Tab,int iAbfrage)
	{
          bFileError=false;
		try
		{
			if (Tab==null)
				return Buf instanceof BufferedReader ? ((BufferedReader)Buf).readLine():((LineNumberReader)Buf).readLine();
			{
				int i=0;
				for(Tab.posInhalt("Abfrage",iAbfrage);!Tab.eof() && Tab.getI("Abfrage")==iAbfrage;Tab.moveNext())
					i+=getLaenge(Tab);
				char [] cbuf=new char[i];
				return (Buf instanceof BufferedReader ? ((BufferedReader)Buf).read(cbuf,0,i):((LineNumberReader)Buf).read(cbuf,0,i))==-1?null:new String(cbuf);
			}
		}catch(IOException io)
		{
                  bFileError=true;
			Transact.fixError(g,getTitel()+".readLine(): IOException - "+io);
			return null;
		}
	}

        public String getAF(String sAic,String sAuswert,int iLaenge)
        {
          if (sAuswert.equals("SV_Datum"))
            return isNull(sAic) ? "00000000" : "" + new Zeit(g,(Date)getInhalt(sAic), "ddMMyyyy");
          else if (sAuswert.equals("SV_Boolean"))
              return getB(sAic)?"J":"N";
          else if (iLaenge > 0)
          {
            if (sAuswert.equals("String"))
              return Static.FillSpace(getM(sAic), iLaenge);
            else if (sAuswert.equals("SV_Zahl"))
              return Static.FillNull(getL(sAic), iLaenge);
            else if (sAuswert.equals("SV_Zahl2"))
            {
              DecimalFormatSymbols DFS=new DecimalFormatSymbols();
              DFS.setDecimalSeparator('.');
              return Static.FillSpace("Waehrung",new java.text.DecimalFormat("0.00",DFS).format(getF(sAic)), iLaenge);
              //return Static.FillSpace("Waehrung","" + getL(sAic), iLaenge);
            }
            else if (sAuswert.equals("SV_Cent"))
              return Static.FillNull(Math.round(getF(sAic) * 100), iLaenge);
            else if (sAuswert.equals("SV_Cent2"))
              return Static.FillSpace("Waehrung","" + new Zahl1(getF(sAic) * 100, "0"), iLaenge);
            else if (sAuswert.equals("L1_Cent"))
              return /*getF(sAic)==0 ? Static.FillSpace("", iLaenge+1):*/(getF(sAic)<0?"-":"+")+Static.FillNull("" + new Zahl1(Math.abs(getF(sAic) * 100), "0"), iLaenge);
            else
              return null;
          }
          else
            return null;
        }

	public int getLaenge(Tabellenspeicher Tab)
	{
		if (Tab.getS("Format").equals(""))
		{
		//return Tab.getS("Format").equals("") ? Tab.getI("Laenge"):Tab.getS("Format").length();
			int i=Tab.getI("Laenge");
			return i<0 || (i&511)==501 ? -1:i&511;
		}
		else
                {
                  int i=Tab.getS("Format").indexOf(";");
                  return i>0 ? i:Tab.getS("Format").length();
                }
	}

	private int ZeichenAnzahl(String s,char cTrennzeichen,int iAbf,Tabellenspeicher Tab,boolean b1)
	{
		if (cTrennzeichen==0)
		{
			if(!Tab.eof() && Tab.getI("Abfrage")==iAbf)
			{
				int i=getLaenge(Tab);
				return s.length()<i?-1:i;
			}
			else
				return -1;
		}
		else
                {
                  if (b1)
                    return s.indexOf("\""+cTrennzeichen);
                  else
                    return s.indexOf(cTrennzeichen);
                }
	}
	
	public static String nurZiffern(String s)
	{
		if(s==null || s.matches("\\d+"))
			return s;
		else
		{
			String s2="";
			for (int i=0;i<s.length();i++)
			{
				char c=s.charAt(i);
				if (c>='0' && c<='9')
					s2+=c;
			}
			Static.printError("nurZiffern->"+s+" geändert auf "+s2);
			return s2;
		}
	}

	private void ObjToTab(int iAbf,Tabellenspeicher Tab,String s)
	{
		if(!Tab.eof() && Tab.getI("Abfrage")==iAbf)
		{
			if (s!=null)
				s=s.trim();
			if ((Tab.getI("bits2")&Abfrage.cstZiffern)>0)
				s=nurZiffern(s);
			if(!Tab.getS("Datentyp").equals("Filler"))
				setInhalt(Tab.getS("Kennung3"),s.trim());
		}
		else
		{
			Transact.fixError(g,getTitel()+".ObjToTab: Spaltenanzahl stimmt nicht überein!");
			iAnzFehler++;
		}
	}

        @SuppressWarnings("unchecked")
        public boolean LoadAU(String sFileName)
        {
          //long lClock2 = Static.get_ms();
          int i2=0;
          if (Static.getTempFile(sFileName).exists())
          {
            try
            {
                char cSep=9;
                BufferedReader Buf = new BufferedReader(new FileReader(Static.getTempFile(sFileName)));
                // Titelzeile einlesen
                String s=Buf.readLine();
                //int i2=0;
                while (s.indexOf(cSep)>-1)
                {
                  int i=s.indexOf(cSep);
                  VecTitel.addElement(s.substring(0,i));
                  VecInhalt.addElement(new Vector());
                  //i2++;
                  s=s.substring(i+1);
                }
                VecTitel.addElement(s);
                VecInhalt.addElement(new Vector());
                // Daten einlesen
                s=Buf.readLine();
                while (s != null)
                {
                        newLine();
                        i2=0;
                        while (s.indexOf(cSep)>-1)
                        {
                                int i=s.indexOf(cSep);
                                ((Vector) VecInhalt.elementAt(i2)).setElementAt(StoObj(s.substring(0,i)),iPos);
                                i2++;
                                s=s.substring(i+1);
                        }
                        ((Vector) VecInhalt.elementAt(i2)).setElementAt(StoObj(s),iPos);
                        s=Buf.readLine();
                }
                Buf.close();
                //g.clockInfo("LoadAU "+sFileName,lClock2);
                //showGrid(sFileName);
                return true;
            }catch(Exception io)
            {
                    Transact.fixError(g,getTitel()+".LoadAU(): Exception - "+io+" in Zeile "+iPos+", Spalte "+i2);
                    Static.iCache=Static.NIE;
                    Transact.fixError(g,"! Cache abgeschaltet !");
                    return false;
            }
          }
          else
            return false;
        }

        public void SaveAU(String sFileName)
        {
          //long lClock2 = Static.get_ms();
          char cSep=9;
          try
          {
            FileOutputStream fos = new FileOutputStream(Static.getTempFile(sFileName), false);
            String s=""+VecTitel.elementAt(0);
            for(int i=1;i<VecTitel.size();i++)
              s+=cSep+""+VecTitel.elementAt(i);
            s+="\r\n";
            fos.write(s.getBytes());
            for(moveFirst();!eof();moveNext())
            {
              s=ObjToS(getElementAt(iPos,0));
              for(int i=1;i<VecTitel.size();i++)
              {
                //String s2=ObjToS(getElementAt(iPos, i));
                //s += cSep + "" + (Obj==null?"":Obj instanceof Date?new Zeit((Date)Obj,"yyyy/MM/dd"):Obj);
                s+=cSep+ObjToS(getElementAt(iPos, i));
              }
              s+="\r\n";
              fos.write(s.getBytes());
            }
            fos.close();
          }
          catch (IOException ex)
          {
            bFileError=true;
            Transact.fixError(g,getTitel()+".SaveAU: open/close - "+ex);
          }
          //g.clockInfo("SaveAU "+sFileName,lClock2);
        }

        private String ObjToS(Object Obj)
        {
          if (Obj==null)
            return "n";
          else if (Obj instanceof Integer)
            return "i"+Obj;
          else if (Obj instanceof Boolean)
            return "b"+(((Boolean)Obj).booleanValue()?1:0);
          else if (Obj instanceof Long)
            return "l"+Obj;
          else if (Obj instanceof Double)
            return "f"+Obj;
          else if (Obj instanceof Date)
            return "t"+new Zeit(g,(Date)Obj,"yyyyMMddHHmmss");
          else if (Obj instanceof String)
            return "s"+SforFile((String)Obj);
          else if (Obj instanceof Memo1)
            return "m" + SforFile(((Memo1)Obj).getValue());
          else
            return "<???>";
        }

        private String SforFile(String s)
        {
          s=s.replaceAll("\t","<TAB>");
          s=s.replaceAll("\n","<LF>");
          s=s.replaceAll("\r","<CR>");
          return s;
        }

        private String SfromFile(String s)
        {
          s=s.replaceAll("<TAB>","\t");
          s=s.replaceAll("<LF>","\n");
          s=s.replaceAll("<CR>","\r");
          return s;
        }


        private Object StoObj(String s)
        {
          char c=s.charAt(0);
          if (c!='n' && s.length()>1)
            s=s.substring(1);
          if (c=='n')
            return null;
          else if (c=='i')
            return new Integer(s);
          else if (c=='b')
            return s.equals("1")?Boolean.TRUE:Boolean.FALSE;
          else if (c=='l')
            return new Long(s);
          else if (c=='f')
            return new Double(s);
          else if (c=='t')
            return new java.sql.Timestamp(new java.text.SimpleDateFormat("yyyyMMddHHmmss").parse(s,new java.text.ParsePosition(0)).getTime());
          else if (c=='s')
            return SfromFile(s);
          else if (c=='m')
            return new Memo1(SfromFile(s));
          else
            return "<???>";
        }

	/*public void CalcExport(boolean bAlle,String sTrennzeichen,String sFileName,Tabellenspeicher Tab,boolean bAnhaengen)
	{
		CalcExport(bAlle,sTrennzeichen,sFileName,Tab,bAnhaengen,false);
	}*/

	public void CalcExport(boolean bTitel,boolean bAlle,String sTrennzeichen,String sFileName,Tabellenspeicher Tab,boolean bAnhaengen,boolean bFill,boolean rbMass)
	{
          bMass=rbMass;
		g.testInfo("CalcExport: Filename="+sFileName+", Trennzeichen="+sTrennzeichen);
                bFileError=false;
		try
		{
			FileOutputStream fos=new FileOutputStream(Static.beiLeer(sFileName,"C:\\temp\\test.txt"),bAnhaengen);
			CalcExport(bTitel,bAlle,sTrennzeichen,fos,Tab,bFill);
			fos.close();
		}
		catch (IOException ex)
		{
                  bFileError=true;
			Transact.fixError(g,getTitel()+".CalcExport: open/close - "+ex);
		}
	}

	public void CalcExport(boolean bTitel,boolean bAlle,String sTrennzeichen,Object fos,Tabellenspeicher Tab,boolean bFill)
	{

		//Tab.showGrid(sFileName);
		char cTrennzeichen=sTrennzeichen==null || sTrennzeichen.equals("")?0:sTrennzeichen.equals("Tab")?9:sTrennzeichen.charAt(0);
                bFileError=false;
		String s;
		try
		{
			int iAbfrage=Tab.getI("Abfrage");
                        Tab.push();
			/*int iSpalten=VecInhalt.size();
			for(int i=0; i<iSpalten;i++)
				s += (String)VecTitel.elementAt(i) + (iSpalten==i+1?"\n":";");
			fos.write(s.getBytes());*/
			//int iAnz = getAnzahlElemente(null);//((Vector)VecInhalt.elementAt(0)).size();
			//for(int i=0; i<iAnz;i++)
                        if (bTitel)
                        {
                          Tab.posInhalt("Abfrage",iAbfrage);
                          s=Tab.getS("Bezeichnung");
                          for(Tab.moveNext();!Tab.eof() && Tab.getI("Abfrage")==iAbfrage;Tab.moveNext())
                            if ((Tab.getI("bits")&0x1000)==0) /* cstUnsichtbar */
                              s+=cTrennzeichen+Tab.getS("Bezeichnung");
                          s+="\r\n";
                          ((FileOutputStream)fos).write(s.getBytes(Static.CP));
                        }
			push();
			if (bAlle)
				moveFirst();
			for(;!eof();moveNext())
			{
				//s=null;
				/*for(int j=0;j<iSpalten;j++)
				{
					Object Obj = ((Vector)VecInhalt.elementAt(j)).elementAt(i);
					s += (Obj!=null ? Obj.toString() : "")  + (iSpalten==j+1?"\n":";");
				}*/

                                /*
                                for(Tab.posInhalt("Abfrage",iAbfrage);!Tab.eof() && Tab.getI("Abfrage")==iAbfrage;Tab.moveNext())
					if ((Tab.getI("bits")&0x1000)==0)
					{
						if (cTrennzeichen==0)
							s= s==null ? getWert(Tab,true):s+getWert(Tab,true);
						else
						{
							boolean bHK=(Tab.getI("bits")&0x0800)>0;
							s = (s==null ? "":s+cTrennzeichen)+(bHK?"\"":"")+
								(cTrennzeichen==',' && !bHK ? Static.replaceString(getWert(Tab,bFill),",","."):getWert(Tab,bFill))+(bHK?"\"":"");
						}
					}
                                  s+="\r\n";
				*/
                                s=getLine(Tab,iAbfrage,bFill,""+cTrennzeichen)+"\r\n";
				if (fos instanceof FileOutputStream)
					((FileOutputStream)fos).write(s.getBytes(Static.CP));
				else if (fos instanceof BufferedWriter)	// FTP
					((BufferedWriter)fos).write(s);
				else
					Transact.fixError(g,Static.className(fos)+" wird nicht unterstützt");
				if(!bAlle)
					break;
			}
			pop();
                        Tab.pop();
		}
		catch (IOException ex)
		{
                  bFileError=true;
			Transact.fixError(g,getTitel()+".CalcExport: write - "+ex);
		}
	}
	
	public String print()
	{
		String s="[";
		for (int i=0;i<size();i++)
		{
			if (i>0) s+=",";
			for (int i2=0;i2<VecTitel.size();i2++)
				s+=(i2==0 ? "(":",")+getInhalt(VecTitel.elementAt(i2), i);
			s+=")";
		}
		return s+"]";
	}
	
	public String getSQL()
	{
		return sSQL;
	}
	
	public String getTitelzeile(Tabellenspeicher Tab,String sTrenn)
	{
		String s=null;
		int iAbfrage=Tab.getI("Abfrage");
		for (int iPos=Tab.getPos("Abfrage", iAbfrage);Tab.size()>iPos && Tab.getI(iPos,"Abfrage")==iAbfrage;iPos++)
		  if ((Tab.getI("bits")&0x1000)==0) // nicht unsichtbar
			if (s==null)
				s=Tab.getS(iPos,"Bezeichnung");
			else
				s+=sTrenn+Tab.getS(iPos,"Bezeichnung");
		return s;
	}

        public String toString(Tabellenspeicher Tab,String sTrenn)
        {
          String s="";
          push();
          int iAbfrage=Tab.getI("Abfrage");
          moveFirst();
          for(;!eof();moveNext())
          {
            s+=getLine(Tab,iAbfrage,true,sTrenn);
          }
          pop();
          return s;
        }

        public String LineToString(Tabellenspeicher Tab)
        {
          return getLine(Tab,Tab.getI("Abfrage"),true,null);
        }

        private String getLine(Tabellenspeicher Tab,int iAbfrage,boolean bFill,String sTrenn)
        {
          String s=null;
          if (iAbfrage==0)
        	  Tab.moveFirst();
          else
        	  Tab.posInhalt("Abfrage",iAbfrage);
          for(;!Tab.eof() && (iAbfrage==0 || Tab.getI("Abfrage")==iAbfrage);Tab.moveNext())
              if ((Tab.getI("bits")&0x1000)==0) /* cstUnsichtbar */
              {
                      if (sTrenn==null)
                              s= s==null ? getWert(Tab,true):s+getWert(Tab,true);
                      else
                      {
                              boolean bHK=(Tab.getI("bits")&0x0800)>0;/* cstHochkomma */
                              s = (s==null ? "":s+sTrenn)+(bHK?"\"":"")+
                                      (sTrenn.contains(",") && !bHK ? Static.replaceString(getWert(Tab,bFill),",","."):getWert(Tab,bFill))+(bHK?"\"":"");
                      }
              }
          return s;
        }

	private String getWert(Tabellenspeicher Tab,boolean bFill)
	{
          if (!Tab.isNull("EF"))
            return getAF(Tab.getS("Kennung3"),Tab.getS("EF"),Tab.getI("Laenge"));
          String s=""+TabWertToObj(Tab,true);
          String s2= bFill && getLaenge(Tab)>0 ? Static.FillSpace(Tab.getS("Datentyp"),s,getLaenge(Tab)) : s;
//          g.fixtestError("Tab.getWert:"+s+" -> "+s2+", L="+getLaenge(Tab));
          return s2;
	}

	public Object TabWertToObj(Tabellenspeicher Tab,boolean bReplace)
	{

		String sDatentyp=Tab.getS("Datentyp");
		if (sDatentyp.equals("Filler"))
			return "";
		//g.progInfo(s+"("+sDatentyp+")");
		String sKenn=sDatentyp.equals("BewVon_Bis") || sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("Einheiten") ?
			Tab.getS("Kennung3").substring(1):Tab.getS("Kennung3");
//		g.fixtestError("TabWertToObj "+sDatentyp+": Format="+Tab.getS("Format")+"/"+(sDatentyp.equals("Double") ? ""+getF(sKenn):getS(sKenn)));			
		if (sDatentyp.equals("BewVon_Bis") && (!bMass || Tab.getS("Format").equals("-")))
			return new VonBis(g,getInhalt("V"+sKenn),getInhalt("B"+sKenn),getF("D"+sKenn),Tab.getS("Format"));
		else if (sDatentyp.equals("von_bis") || sDatentyp.equals("Auto_von_bis") || sDatentyp.equals("BewVon_Bis"))
			return new VonBis(g,getInhalt("V"+sKenn),getInhalt("B"+sKenn),Tab.getS("Format"));
		else if (getInhalt((sDatentyp.equals("Einheiten")?"V":"")+sKenn)==null)
			return "";
		else if (sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2"))
			return Tab.getS("Format").equals("") ? ""+new Double(getF(sKenn)):""+new Zahl1(getF(sKenn),Tab.getS("Format"));
		else if (sDatentyp.equals("Double") || sDatentyp.equals("BewDauer") || sDatentyp.startsWith("BewZahl") || sDatentyp.equals("CalcDauer"))
			return new Zahl1(getF(sKenn),Tab.getS("Format"));
                else if (sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2"))
                  return new Zahl1(getF(sKenn)/(bMass ? getFaktor(Tab.getI("Mass"),g):1),Tab.getS("Format"));
		else if (sDatentyp.equals("Integer"))
			return Tab.getS("Format").equals("") ? getInhalt(sKenn):new Zahl1(getI(sKenn),Tab.getS("Format"));
		else if (sDatentyp.equals("Einheiten"))
			return new Zahl1(getF("E"+sKenn),Tab.getS("Format"));
		else if (sDatentyp.equals("Boolean") || sDatentyp.equals("BewBoolean"))
			return getB(sKenn)?"J":"N";//Static.JaNein(getB(sKenn));
		else if (sDatentyp.equals("Eintritt") || sDatentyp.equals("Austritt") || sDatentyp.equals("EinAustritt") || sDatentyp.equals("Datum") || sDatentyp.equals("CalcDatum") || sDatentyp.equals("Stichtag"))
                {
                  //g.progInfo("TabWertToObj:"+sDatentyp+"/"+sKenn);
                  return new Zeit(g,getDate(sKenn), Static.beiLeer(Tab.getS("Format"), "dd.MM.yyyy"));
                }
		else if (sDatentyp.equals("BewDatum") || sDatentyp.equals("BewDatum2"))
			return new Zeit(g,(Date)getInhalt(sKenn),Static.beiLeer(Tab.getS("Format"),"dd.MM.yyyy HH:mm"));
		else if (sDatentyp.equals("Memo") || sDatentyp.equals("Text"))
			return bReplace ? Static.replaceString(getM(sKenn),"\n"," "):getM(sKenn);
		else
			return getInhalt(sKenn);
	}

	public Object getElementAt(int iRow, int iColumn)
	{
		Object Obj = null;
		String sSpalte = VecTitel.size()>iColumn ? VecTitel.elementAt(iColumn) : null;
		if(sSpalte != null)
		{
			getSpalte(sSpalte);
			Obj = VecSpalte.size()>iRow ? VecSpalte.elementAt(iRow) : null;
		}
		return(Obj);
	}

//public static String DateToSJON(Date dt)
//	{
//		return "\""+new java.text.SimpleDateFormat("yyyy-MM-dd",webJSON.DFS).format(dt)+"T"+new java.text.SimpleDateFormat("HH:mm:ss.SSS",webJSON.DFS).format(dt)+"Z\"";
//	}

	public boolean putElementAt(int iRow, int iColumn, Object Obj)
	{
		boolean bOk = false;
		String sSpalte = VecTitel.size()>iColumn ? VecTitel.elementAt(iColumn) : null;
		if(sSpalte != null)
		{
			getSpalte(sSpalte);
			if(VecSpalte.size()>iRow)
			{
				VecSpalte.setElementAt(Obj,iRow);
				bOk = true;
			}

		}
		return(bOk);
	}

	public int getAnzahlSpalten()
	{
		return bFehler?-1:VecTitel.size();
	}

	public String getTitel(int i)
	{
		return i<0 || i>=VecTitel.size() ? null:VecTitel.elementAt(i);
	}

        public static double getFaktor(int iAic,Global g)
        {
          if (g.TabMass==null)
            g.TabMass = new Tabellenspeicher(g,new String[] {"Gruppe","Aic","Faktor","Kennung"});

                if (iAic==0)
                        return 0.0;
                else if (g.TabMass.posInhalt("Aic",iAic))
                {
                        //progInfo("getFaktor "+iAic+":"+TabMass.getS("Faktor"));
                        return g.TabMass.getF("Faktor");
                }
                else
                {
                        SQL Qry = new SQL(g);
                        Qry.open("select p1.sta_aic_stamm gruppe,stammview.aic_stamm aic,p2.spalte_double faktor,kennung from poolview p1,poolview p2 join stammview on stammview.aic_stamm=p2.aic_stamm where p1.aic_stamm="+iAic+
                        " and p2.aic_eigenschaft="+g.iEigFaktor+" and p2.sta_aic_stamm=p1.sta_aic_stamm");
                        //printInfo(Qry.getSQL());
                        while (!Qry.eof())
                        {
                        	g.TabMass.addInhalt("Gruppe",Qry.getI("Gruppe"));
                        	g.TabMass.addInhalt("Aic",Qry.getI("Aic"));
                        	g.TabMass.addInhalt("Faktor",new Double(Qry.getF("Faktor")));
                        	g.TabMass.addInhalt("Kennung",Qry.getS("Kennung"));
                            Qry.moveNext();
                        }
                        Qry.free();
                        //if (Prog())
                        //	TabMass.showGrid();
                        g.TabMass.posInhalt("Aic",iAic);
                        //progInfo("getFaktor "+iAic+": neu "+(TabMass.out()?"out":TabMass.getS("Faktor")));
                        return g.TabMass.out()?0.0:g.TabMass.getF("Faktor");
                }
        }
        
//        public void setDebug(boolean b)
//        {
//        	bDebug=b;
//        }

        private void writeObject(ObjectOutputStream oos) throws IOException
        {
          oos.defaultWriteObject();
        }

        private void readObject(ObjectInputStream ois) throws IOException
        {
          try
          {
            ois.defaultReadObject();
          }
          catch(Exception e)
          {
            Transact.fixError(g,"Tabellenspeicher.readObject"+e);
          }
        }
        
        public Vector<String> getSpalten()
        {
        	return VecTitel;
        }

        /*public static Tabellenspeicher getClone(Tabellenspeicher Tab)
        {
          if (Tab==null)
            return null;
          try
          {
            return (Tabellenspeicher)Tab.clone();
          }
          catch(Exception e)
          {
            Transact.fixError(g,"Tabellenspeicher.getClone: Clonen nicht möglich");
            return null;
          }
        }*/

        /*public Tabellenspeicher clone()
        {
          return super.clone();
        }*/
        
        public void addALG(JCActionListener rALG)
        {
        	if (ALG==null)
        	{
        		ALG=rALG;
        		Grid.addActionListener(ALG);
        	}
        }

    // add your data members here
  //public static Tabellenspeicher TabMass=null;
  public static Vector<String> VecErrorSpalten=new Vector<String>();
  public Vector<String> VecBezeichnung= null;
  public Vector<String> VecTitel= new Vector<String>();
  private Vector<Vector> VecInhalt= new Vector<Vector>();
  private Vector<Object> VecSpalte;
  private int iPos=0;
//  private boolean bDebug=false;
  //private int iPosS=0;
  private Stack<Integer> stack = new Stack<Integer>();
  private boolean bEof = true;
  private boolean bBof = true;
  public boolean bFileError = true;
  private boolean bFehler = false;
  private boolean bPos = true;
  private boolean bOut = true;
  public String sKennung="Kennung";
  public String sBezeichnung="Bezeichnung";
  public String sAIC="AIC";
  public String sGruppe="Gruppe";
  public boolean bInsert = false;
  transient public Window FrmGrid=null;
  transient public JCOutliner Grid=null;
  private JCActionListener ALG=null;
  transient public Global g;
  private boolean bLeer;
  private int iAnzFehler=0;
  private int iAnzFmom=0;
  private int iAnzFmax=5;
  //private static ActionListener actExport=null;
  private String sTT=null;
  private JButton Btn1=null;
  private JButton Btn2=null;
  private boolean bN=false;
  private static boolean bN2=true;
  private int iN=0;
  public String sSQL=null;
  public String sBV=null;
  public String sTitle=null;
  public boolean bMass=false;
  private int iPosStyle=-1;
  private ComboSort CboSpalten=null;
  private ComboSort CboSpalten2=null;
  //private JButton BtnS=null; 
  private Text Edt;
  private Text Edt2;
  private JButton BtnI=null;
  //private static int iNull=0;
  //public static boolean bAusgabe=false;
  //public char cTrennzeichen=';';
}

