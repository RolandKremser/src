/*
    All_Unlimited-Allgemein-Parameter.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.util.Vector;

public class Parameter extends SQL
{
    // add your data members here
	private Global g;
	private int iHaupt;
	private int iNeben;

	public String sParameterzeile="";
    public int iAic=0;
	public int int1=0;
	public int int2=0;
	public int int3=0;
	public int int4=0;
	public boolean bGefunden=false;

	public Parameter(Global rg,String sHaupt)
	{
		//setTransactionObject( Tra );
		super(rg);
		g=rg;
		ErmittleParameter(sHaupt,true);
	}

	public void ErmittleParameter(String s,boolean bHaupt)
	{
		int iAic=getInteger("select aic_"+(bHaupt?"haupt":"neben")+"gruppe from "+(bHaupt?"haupt":"neben")+"gruppe where kennung='"+s+"'");
		if (iAic==0)
		{
			add("Kennung",s);
			if (bHaupt)
				iHaupt = insert("Hauptgruppe",true);
			else
				iNeben = insert("Nebengruppe",true);
		}
		else
			if (bHaupt)
				iHaupt = iAic;
			else
				iNeben = iAic;
	}

        public String getWhere(String sNeben)
        {
          ErmittleParameter(sNeben,false);
          return " where aic_hauptgruppe="+iHaupt+" and Aic_Nebengruppe="+iNeben;
        }

        public String getBWhere(String sNeben)
        {
          return getWhere(sNeben)+" and parameter.aic_benutzer="+g.getBenutzer();
        }

        // für DefTerminal und LDAP und setMParameter
        public void delMParameter(String sNeben,boolean bMandant)
        {
        	delMParameter(sNeben,bMandant ? 0:-1);
        }
        
        public void delMParameter(String sNeben,int iMandant)
        {
          ErmittleParameter(sNeben,false);
          deleteFrom("Parameter","Parameter where aic_hauptgruppe="+iHaupt+" and Aic_Nebengruppe="+iNeben+
                     (iMandant>=0 ? " and aic_benutzer is null and aic_mandant="+(iMandant>0 ? iMandant:g.getMandant()):" and aic_benutzer="+g.getBenutzer()));
        }
        
//        public void setMParameter(String sNeben,String sParameter,int i1,int i2,int i3,int i4,boolean bMandant)
//        {
//        	
//        }

	public void setMParameter(String sNeben,String sParameter,int i1,int i2,int i3,int i4,int iMandant)
	{
        delMParameter(sNeben,iMandant);
		if (iMandant>=0)
          add("aic_mandant",iMandant>0 ? iMandant:g.getMandant());
        else
          add("Aic_Benutzer",g.getBenutzer());
		addGruppen();
		sParameterzeile = sParameter;
		int1 = i1;
		int2 = i2;
		int3 = i3;
		int4 = i4;
		add("Parameterzeile",sParameter);
		add("int1",i1);
		add("int2",i2);
		add("int3",i3);
		add("int4",i4);
        add("aic_logging",g.getLog());
		insert("Parameter",false);
		//g.bZeigeInfo = b;
	}

	public void setParameter(String sNeben,String sParameter,int i1,int i2,int i3,int i4,boolean bBenutzer,boolean bComputer)
	{
		//boolean b = g.bZeigeInfo;
		//g.bZeigeInfo = false;
		ErmittleParameter(sNeben,false);
		Vector<Integer> VecP=getVector("select aic_parameter from parameter where Aic_Hauptgruppe="+iHaupt+" and Aic_Nebengruppe="+iNeben+
				(bBenutzer ? " and Aic_Benutzer="+g.getBenutzer():"")+(bComputer ? " and Aic_Computer="+g.getComputer():""),g);
		int iP=VecP.size()>0 ? Sort.geti(VecP, 0):0;
		if (VecP.size()>1)
		  for(int i=1;i<VecP.size();i++)
		  {
			  int iP2=Sort.geti(VecP, i);
			  if (g.exec("delete from Parameter where aic_Parameter="+iP2)>0)
				  g.fixtestError("Parameter "+iP2+" entfernt, da doppelt");
		  }
		if (iP<1)
		{
		  if (bBenutzer)
			add("Aic_Benutzer",g.getBenutzer());
		  if (bComputer)
			add("Aic_Computer",g.getComputer());
		  addGruppen();
		}
//		delete("Parameter");
		sParameterzeile = sParameter;
		int1 = i1;
		int2 = i2;
		int3 = i3;
		int4 = i4;
		add("Parameterzeile",sParameter);
		add("int1",i1);
		add("int2",i2);
		add("int3",i3);
		add("int4",i4);
		if (g.getLog()>0)
			add("aic_logging",g.getLog());
		if (iP>0)
			update("Parameter",iP);
		else
			insert("Parameter",false);
		//g.bZeigeInfo = b;
	}

	public void addGruppen()
	{
		add("Aic_Hauptgruppe",iHaupt);
		add("Aic_Nebengruppe",iNeben);
	}

	public void setMParameter(String sNeben,String sParameter,boolean bMandant)
	{
                delMParameter(sNeben,bMandant);
		//ErmittleParameter(sNeben,false);
		//deleteFrom("Parameter","Parameter where aic_hauptgruppe="+iHaupt+" and Aic_Nebengruppe="+iNeben+(bMandant?" and aic_mandant="+g.getMandant():" and aic_benutzer="+g.getBenutzer()));
                if (bMandant)
                  add("aic_mandant",g.getMandant());
                else
                  add("Aic_Benutzer",g.getBenutzer());
		addGruppen();
		add("Parameterzeile",sParameter);
		add("aic_logging",g.getLog());
		insert("Parameter",false);
	}

	public void setParameter(String sNeben,String sParameter,boolean bBenutzer,boolean bComputer)
	{
		//boolean b = g.bZeigeInfo;
		//g.bZeigeInfo = false;
		ErmittleParameter(sNeben,false);
		if (bBenutzer)
			add("Aic_Benutzer",g.getBenutzer());
		if (bComputer)
			add("Aic_Computer",g.getComputer());
		addGruppen();
		delete("Parameter");
		add("Parameterzeile",sParameter);
		if (g.getLog()>0)
			add("aic_logging",g.getLog());
		insert("Parameter",false);
		//g.bZeigeInfo = b;
	}

	public void setBParameter(String sNeben,Vector<Object> Vec,boolean bInteger)
	{
		ErmittleParameter(sNeben,false);
		add("Aic_Benutzer",g.getBenutzer());
		addGruppen();
		delete("Parameter");
		clear();
		for(int i=0;i<Vec.size();i++)
		{
			add("Aic_Benutzer",g.getBenutzer());
			add("Aic_Hauptgruppe",iHaupt);
			add("Aic_Nebengruppe",iNeben);
			if (bInteger)
				add("Parameterzeile",(Integer)Vec.elementAt(i));
			else
				add("Parameterzeile",(String)Vec.elementAt(i));
			add("aic_logging",g.getLog());
			insert("Parameter",false);
		}
	}
	
	public void setMParameter(String sNeben,Tabellenspeicher TabParameter,boolean bMandant)
    {
		setMParameter(sNeben,TabParameter,bMandant ? 0:-1);
    }
	
        public void setMParameter(String sNeben,Tabellenspeicher TabParameter,int iMandant)
        {
          delMParameter(sNeben,iMandant);
          //ErmittleParameter(sNeben,false);
          //deleteFrom("Parameter","Parameter where aic_hauptgruppe="+iHaupt+" and Aic_Nebengruppe="+iNeben+(bMandant?" and aic_mandant="+g.getMandant():" and aic_benutzer="+g.getBenutzer()));
          if (iMandant>=0)
            add("aic_mandant",iMandant>0 ? iMandant:g.getMandant());
          else
            add("Aic_Benutzer",g.getBenutzer());
          addGruppen();
          delete("Parameter");
          clear();
          TabParameter.moveFirst();
          while(!TabParameter.eof())
          {
              if(iMandant<0)
                  add("Aic_Benutzer",g.getBenutzer());
              add("Aic_Hauptgruppe",iHaupt);
              add("Aic_Nebengruppe",iNeben);
              add("int1",TabParameter.getI("int1"));
              add("int2",TabParameter.getI("int2"));
              add("int3",TabParameter.getI("int3"));
              add("int4",TabParameter.getI("int4"));
              add("aic_logging",g.getLog());
              insert("Parameter",false);
              TabParameter.moveNext();
          }
        }

	public void setBParameter(String sNeben,Tabellenspeicher TabParameter)
	{
		ErmittleParameter(sNeben,false);
		add("Aic_Benutzer",g.getBenutzer());
		addGruppen();
		delete("Parameter");
		clear();
		TabParameter.moveFirst();
		while(!TabParameter.eof())
		{
			add("Aic_Benutzer",g.getBenutzer());
			add("Aic_Hauptgruppe",iHaupt);
			add("Aic_Nebengruppe",iNeben);
			add("int1",TabParameter.getI("int1"));
			add("int2",TabParameter.getI("int2"));
			add("int3",TabParameter.getI("int3"));
			add("int4",TabParameter.getI("int4"));
			add("aic_logging",g.getLog());
			insert("Parameter",false);
			TabParameter.moveNext();
		}
	}

	public void setHauptgruppe(String sHaupt)
	{
		ErmittleParameter(sHaupt,true);
	}
	
	public String getMParameter(String sNeben,boolean bMandant)
	{
		return getMParameter(sNeben,bMandant ? 0:-1);
	}

	public String getMParameter(String sNeben,int iMandant)
	{
          //g.progInfo("getMParameter für "+g.getMandant());
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select Parameterzeile,"+g.int1_int4()+" from Parameter"+g.join("nebengruppe","Parameter")+
                  " where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and (Aic_Benutzer is null and aic_Mandant="+(iMandant>0 ? iMandant:g.getMandant())+
                  (iMandant>=0?")":" or Aic_Benutzer="+g.getBenutzer()+") order by aic_benutzer"),true);
		bGefunden = !Tab.eof();
		if (bGefunden)
		{
			Tab.moveLast();
			int1 = Tab.getI("int1");
			int2 = Tab.getI("int2");
			int3 = Tab.getI("int3");
			int4 = Tab.getI("int4");
			sParameterzeile = Tab.getS("Parameterzeile");
		}
		//close();
		//g.bZeigeInfo = b;
		return(bGefunden ? sParameterzeile : "");
	}

        public String getMParameterH(String sNeben)
        {
          int iM=g.getMandant();
          bGefunden=false;
          g.testInfo("getMParameterH("+sNeben+") für "+iM);
          String s="select Parameterzeile,"+g.int1_int4()+" from Parameter"+g.join("nebengruppe","Parameter")+
                  " where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and (Parameter.Aic_Benutzer is null and aic_Mandant=?)";
          Tabellenspeicher Tab;
          Tabellenspeicher TabM=null;
          while (iM>0 && !bGefunden)
          {
            Tab=new Tabellenspeicher(g,s,""+iM,true);
            bGefunden = !Tab.isEmpty();
            if (bGefunden)
            {
              Tab.moveLast();
              int1 = Tab.getI("int1");
              int2 = Tab.getI("int2");
              int3 = Tab.getI("int3");
              int4 = Tab.getI("int4");
              sParameterzeile = Tab.getS("Parameterzeile");
              g.testInfo(sNeben+" gefunden:"+int1+"/"+int2+"/"+int3+"/"+int4+"/"+sParameterzeile);
            }
            else
            {
              if (TabM==null)
                TabM=new Tabellenspeicher(g,"select aic_mandant,kennung,man_aic_mandant from mandant",true);
              TabM.posInhalt("aic_mandant",iM);
              String sM=TabM.getS("Kennung");
              iM=TabM.getI("man_aic_mandant");
              if (TabM.posInhalt("aic_mandant",iM))
                g.fixInfo("MParameter "+sNeben+" nicht gefunden bei "+sM+", verwende deshalb "+TabM.getS("Kennung"));
              else
                g.fixInfo("MParameter "+sNeben+" überhaupt nicht gefunden!");
            }
          }
          //close();
          //g.bZeigeInfo = b;
          return(bGefunden ? sParameterzeile : "");
        }

	public String getParameter(String sNeben,boolean bBenutzer,boolean bComputer)
	{
		//boolean b = g.bZeigeInfo;
		//g.bZeigeInfo = false;
		//ErmittleParameter(sNeben,false);
		String s="select aic_parameter,Parameterzeile,"+g.int1_int4()+" from Parameter p join nebengruppe n on p.aic_nebengruppe=n.aic_nebengruppe where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt;
		if (bBenutzer)
			s = s + " and Aic_Benutzer="+g.getBenutzer();
		if (bComputer)
			s = s + " and aic_computer="+g.getComputer();
		//s = getString(s);
		open(s);
		bGefunden = !eof();
		if (bGefunden)
		{
                  iAic=getI("aic_parameter");
			int1 = getI("int1");
			int2 = getI("int2");
			int3 = getI("int3");
			int4 = getI("int4");
			sParameterzeile = getS("Parameterzeile");
		}
		close();
		//g.bZeigeInfo = b;
		return(bGefunden ? sParameterzeile : "");
	}

	public int getBParameter(String sNeben,Vector<Object> Vec,boolean bInteger)
	{
		int i=0;
		open("select Parameterzeile from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and Aic_Benutzer="+g.getBenutzer());
		while (!eof())
		{
			if (bInteger)
				Vec.addElement(new Integer(getS("Parameterzeile")));
			else
				Vec.addElement(getS("Parameterzeile"));
			moveNext();
			i++;
		}
		close();
		return(i);
	}

        public Tabellenspeicher getParameter(String sNeben)
        {
                return new Tabellenspeicher(g,"select Parameterzeile,"+g.int1_int4()+",aic_parameter from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" order by aic_parameter",true);
        }

        public Tabellenspeicher getMParaTab(String sNeben,int iMandant)
        {
        	String s="select Parameterzeile,"+g.int1_int4()+",aic_parameter from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+
                    " and aic_mandant"+(iMandant>=0 ? "="+(iMandant>0 ? iMandant:g.getMandant()):" is null")+" order by aic_parameter";
//        	g.fixtestError("getMParaTab: "+s);
          return new Tabellenspeicher(g,s,true);
        }

	/*public Tabellenspeicher getBParameter(String sNeben)
	{
		return new Tabellenspeicher(g,"select "+g.int1_int4()+",aic_parameter from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and Aic_Benutzer="+g.getBenutzer()+" order by aic_parameter",true);
	}

        public Tabellenspeicher getMTab(String sNeben,boolean b)
        {
          boolean bMandant=b || !exists(g,"select aic_parameter from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and Aic_Benutzer="+g.getBenutzer());
          return new Tabellenspeicher(g,"select "+g.int1_int4()+",aic_parameter from Logging"+g.join2("Parameter","Logging")+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+
                (bMandant ? " and Parameter.Aic_Benutzer is null and aic_Mandant="+g.getMandant():" and Parameter.Aic_Benutzer="+g.getBenutzer())+" order by Parameter.aic_parameter",true);
         }

	public Tabellenspeicher getCParameter(String sNeben)
	{
		return new Tabellenspeicher(g,"select AIC_Parameter,"+g.int1()+","+g.int2()+" from Parameter"+g.join("nebengruppe","Parameter")+" where kennung ='"+sNeben+"' and aic_hauptgruppe="+iHaupt+" and Aic_Computer="+g.getComputer()+"order by "+g.int1(),true);
	}*/

        public void deleteAll()
        {
          g.fixInfo("lösche "+g.exec("delete from parameter where aic_hauptgruppe="+iHaupt+" and Aic_Benutzer="+g.getBenutzer())+" Parameter");
        }

}

