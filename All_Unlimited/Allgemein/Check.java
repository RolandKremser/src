/*
    All_Unlimited-Allgemein-Check.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.util.Date;
import java.awt.Component;

import javax.swing.JLabel;

import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.HierarchieAnzeige;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUBits;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUComboList;
import All_Unlimited.Allgemein.Eingabe.AUFarbe;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUPasswort;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.AUEkitCore;
//import All_Unlimited.Allgemein.Eingabe.HtmlEingabe;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.EMail;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.GPS_Eingabe;
import All_Unlimited.Allgemein.Eingabe.HierarchieEingabe;
import All_Unlimited.Allgemein.Eingabe.MassEingabe;
import All_Unlimited.Allgemein.Eingabe.RolleEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.VonBisEingabe;
import All_Unlimited.Allgemein.Eingabe.WWW;
import All_Unlimited.Allgemein.Eingabe.WaehrungsEingabe;
import All_Unlimited.Allgemein.Eingabe.Zahl;
import All_Unlimited.Allgemein.Eingabe.Zahl2;
import All_Unlimited.Allgemein.Eingabe.SpinBoxAuswahl;
import All_Unlimited.Allgemein.Eingabe.RadioAuswahl;

public class Check extends java.lang.Object
{
	public static boolean isNull(Object Obj)
	{
		boolean bIsNull;
		if(Obj==null)
                  bIsNull = true;
		else if(Obj instanceof HierarchieEingabe)
                  bIsNull = ((HierarchieEingabe)Obj).isNull();
                else if(Obj instanceof AUComboList)
                  bIsNull = ((AUComboList)Obj).isNull();
                else if (Obj instanceof SpinBoxAuswahl)
                  bIsNull=((SpinBoxAuswahl)Obj).isNull();
                else if (Obj instanceof RadioAuswahl)
                  bIsNull=((RadioAuswahl)Obj).isNull();
		else
                  bIsNull = All_Unlimited.Hauptmaske.Import.isNull(Obj);
		return bIsNull;
	}

        public static boolean wasNull(Object Obj)
        {
          if (Obj==null)
            return false;
          else if(Obj instanceof HierarchieEingabe)
            return ((HierarchieEingabe)Obj).wasNull();
          else if(Obj instanceof AUComboList)
            return ((AUComboList)Obj).wasNull();
          else  if (Obj instanceof AUCheckBox)
            return ((AUCheckBox)Obj).wasNull();
          else
            return false;
        }

        public static void Reset(Object Obj)
        {
          if (Obj instanceof ComboSort)
            ((ComboSort)Obj).Reset();
          else if (Obj instanceof AUComboList)
            ((AUComboList)Obj).getComboBox().setAktAIC(((AUComboList)Obj).getComboBox().getSelectedAIC());
          else if (Obj instanceof Datum)
            ((Datum)Obj).setAktDate(((Datum)Obj).getDateTime());
          else if (Obj instanceof AUTextArea)
            ((AUTextArea)Obj).setAktText(((AUTextArea)Obj).getValue());
          else if (Obj instanceof AUEkitCore)
            ((AUEkitCore)Obj).setAktText(((AUEkitCore)Obj).getValue());
//          else if (Obj instanceof HtmlEingabe)
//              ((HtmlEingabe)Obj).setAktText(((HtmlEingabe)Obj).getValue());
          else if (Obj instanceof Text)
            ((Text)Obj).setAktText(((Text)Obj).getText());
          else if (Obj instanceof AUCheckBox)
            ((AUCheckBox)Obj).setAktSelected(((AUCheckBox)Obj).isSelected());
          else if (Obj instanceof Zahl)
            ((Zahl)Obj).setAktValue(((Zahl)Obj).getDouble());
          else if (Obj instanceof Zahl2)
            ((Zahl2)Obj).Edt.setAktValue(((Zahl2)Obj).Edt.getDouble());
          else if (Obj instanceof WWW)
            ((WWW)Obj).setAktValue(((WWW)Obj).getValue());
          else if (Obj instanceof HierarchieEingabe)
            ((HierarchieEingabe)Obj).setStamm(((HierarchieEingabe)Obj).getValueStamm());
          else if (Obj instanceof MassEingabe)
            ((MassEingabe)Obj).Reset();
          else if (Obj instanceof WaehrungsEingabe)
            ((WaehrungsEingabe)Obj).setAktValue(((WaehrungsEingabe)Obj).getAbsValue(),((WaehrungsEingabe)Obj).getWaehrung());
          else if (Obj instanceof EMail)
            ((EMail)Obj).setAktValue(((EMail)Obj).getValue());
          else if (Obj instanceof FileEingabe)
            ((FileEingabe)Obj).setAktValue(((FileEingabe)Obj).getValue());
          else if (Obj instanceof VonBisEingabe)
            ((VonBisEingabe)Obj).Reset();
          else if (Obj instanceof BildEingabe)
            ((BildEingabe)Obj).Reset();
          else if (Obj instanceof GPS_Eingabe)
            ((GPS_Eingabe)Obj).Reset();
          else if (Obj instanceof AUFarbe)
            ((AUFarbe)Obj).Reset();
          else if (Obj instanceof AUPasswort)
            ((AUPasswort)Obj).Reset();
          else if (Obj instanceof AUOutliner)
            ((AUOutliner)Obj).Reset();
          else if (Obj instanceof SpinBoxAuswahl)
            ((SpinBoxAuswahl)Obj).Reset();
          else if (Obj instanceof RadioAuswahl)
            ((RadioAuswahl)Obj).Reset();
          else
                {
                        Static.printError("Check.Reset(): Objekt "+Obj.getClass().getName()+" kann nicht rückgesetzt werden!");
                }
        }

        public static void Reset2(Object Obj)
        {
          if (Obj instanceof ComboSort)
            ((ComboSort)Obj).Reset2();
          else if (Obj instanceof AUComboList)
            ((AUComboList)Obj).getComboBox().Reset2();
          else if (Obj instanceof RolleEingabe)
            ((RolleEingabe)Obj).Cbo.Reset2();
          else if (Obj instanceof Datum)
            ((Datum)Obj).Reset2();
          else if (Obj instanceof AUTextArea)
            ((AUTextArea)Obj).Reset2();
          else if (Obj instanceof AUEkitCore)
            ((AUEkitCore)Obj).Reset2();
//          else if (Obj instanceof HtmlEingabe)
//              ((HtmlEingabe)Obj).Reset2();
          else if (Obj instanceof Text)
            ((Text)Obj).Reset2();
          else if (Obj instanceof AUCheckBox)
            ((AUCheckBox)Obj).Reset2();
          else if (Obj instanceof Zahl)
            ((Zahl)Obj).Reset2();
          else if (Obj instanceof Zahl2)
            ((Zahl2)Obj).Reset2();
          else if (Obj instanceof WWW)
            ((WWW)Obj).Reset2();
          else if (Obj instanceof HierarchieEingabe)
            ((HierarchieEingabe)Obj).Reset2();
          else if (Obj instanceof MassEingabe)
            ((MassEingabe)Obj).Reset2();
          else if (Obj instanceof WaehrungsEingabe)
            ((WaehrungsEingabe)Obj).Reset2();
          else if (Obj instanceof EMail)
            ((EMail)Obj).Reset2();
          else if (Obj instanceof FileEingabe)
            ((FileEingabe)Obj).Reset2();
          else if (Obj instanceof VonBisEingabe)
            ((VonBisEingabe)Obj).Reset2();
          else if (Obj instanceof BildEingabe)
            ((BildEingabe)Obj).Reset2();
          else if (Obj instanceof GPS_Eingabe)
              ((GPS_Eingabe)Obj).Reset2();
          else if (Obj instanceof AUFarbe)
            ((AUFarbe)Obj).Reset2();
          else if (Obj instanceof AUPasswort)
            ((AUPasswort)Obj).Reset2();
          else if (Obj instanceof SpinBoxAuswahl)
            ((SpinBoxAuswahl)Obj).Reset2();
          else if (Obj instanceof RadioAuswahl)
            ((RadioAuswahl)Obj).Reset2();
          else if (Obj instanceof AUOutliner)
            ((AUOutliner)Obj).Reset2();
          else
                {
                        Static.printError("Check.Reset2(): Objekt "+Obj.getClass().getName()+" kann nicht rückgesetzt werden!");
                }
        }

	public static boolean Modified(Object Obj)
	{
		//System.out.println("Modified:"+Obj);
		//System.out.println("  Class="+Obj.getClass().getName());
		boolean bModified;
		if (Obj==null)
			bModified=false;
                else if (Obj instanceof RolleEingabe) // R
			bModified=((RolleEingabe)Obj).Cbo.Modified();
                else if (Obj instanceof AUCheckBox) // R
			bModified=((AUCheckBox)Obj).Modified();
		else if (Obj instanceof Zahl)       // R
			bModified=((Zahl)Obj).Modified();
                else if (Obj instanceof Zahl2)       // R
			bModified=((Zahl2)Obj).Edt.Modified();
		else if (Obj instanceof ComboSort)  // R
			bModified=((ComboSort)Obj).Modified();
		else if (Obj instanceof WWW)        // R
			bModified=((WWW)Obj).Modified();
		else if (Obj instanceof AUTextArea) // R
			bModified=((AUTextArea)Obj).Modified();
		else if (Obj instanceof AUEkitCore) // R
			bModified=((AUEkitCore)Obj).Modified();
//        else if (Obj instanceof HtmlEingabe) // R
//			bModified=((HtmlEingabe)Obj).Modified();
		else if (Obj instanceof BildEingabe)// R
			bModified=((BildEingabe)Obj).Modified();
		else if (Obj instanceof AUComboList)// R
			bModified=((AUComboList)Obj).Modified();
		else if (Obj instanceof HierarchieEingabe) // R
			bModified=((HierarchieEingabe)Obj).Modified();
		else if (Obj instanceof Datum)      // R
			bModified=((Datum)Obj).Modified();
		else if (Obj instanceof VonBisEingabe) // R
			bModified=((VonBisEingabe)Obj).Modified();
		else if (Obj instanceof MassEingabe) // R
			bModified=((MassEingabe)Obj).Modified();
		else if (Obj instanceof WaehrungsEingabe) // R
			bModified=((WaehrungsEingabe)Obj).Modified();
		else if (Obj instanceof GPS_Eingabe)
			bModified=((GPS_Eingabe)Obj).Modified();
		else if (Obj instanceof AUOutliner)
			bModified=((AUOutliner)Obj).Modified();
		else if (Obj instanceof Text) // R
			bModified=((Text)Obj).Modified();
		else if (Obj instanceof EMail)// R
			bModified=((EMail)Obj).Modified();
		else if (Obj instanceof FileEingabe)// R
			bModified=((FileEingabe)Obj).Modified();
		else if (Obj instanceof AUFarbe)
			bModified=((AUFarbe)Obj).Modified();
		else if (Obj instanceof AUBits)
			bModified=((AUBits)Obj).Modified();
		else if (Obj instanceof AUPasswort)
			bModified=((AUPasswort)Obj).Modified();
                else if (Obj instanceof SpinBoxAuswahl)
			bModified=((SpinBoxAuswahl)Obj).Modified();
                else if (Obj instanceof RadioAuswahl)
			bModified=((RadioAuswahl)Obj).Modified();
		else if (Obj instanceof JLabel)
			bModified=false;
		else
		{
			Static.printError("Check.Modified(): Objekt "+Obj.getClass().getName()+" kann nicht auf Veränderung geprüft werden!");
			bModified = false;
		}
//		if (bModified)
//			System.err.println("Modified "+Static.print(Obj));
		return bModified;
	}

	public static boolean isValid(Object Obj)
	{
		boolean b=true;
		if(Obj instanceof Datum)
			b=((Datum)Obj).isValid2();
		else if(Obj instanceof VonBisEingabe)
			b=((VonBisEingabe)Obj).isValid2();
		else if(Obj instanceof AUComboList)
			b=((AUComboList)Obj).isValid2();
		//else if(Obj instanceof WWW)
		//	b=((WWW)Obj).isValid2();
		return b;
	}

        public static Component clone(Object Obj)
        {
          if(Obj instanceof AUComboList)
            return ((AUComboList)Obj).clone();
          else if(Obj instanceof Zahl)
              return ((Zahl)Obj).clone();
          else if(Obj instanceof AUCheckBox)
            return ((AUCheckBox)Obj).clone();
          else if(Obj instanceof HierarchieEingabe)
            return ((HierarchieEingabe)Obj).clone();
          else
            return null;
        }

        public static void setEditable(String s,Object Obj,boolean b)
        {
          if(Obj instanceof ComboSort)
            ((ComboSort)Obj).setEditable1(b);
          else if(Obj instanceof RolleEingabe)
            ((RolleEingabe)Obj).setEditable(b);
          else if(Obj instanceof AUComboList)
            ((AUComboList)Obj).setEditable(b);
          else if(Obj instanceof HierarchieEingabe)
            ((HierarchieEingabe)Obj).setEditable(b);
          else if(Obj instanceof AUCheckBox)
            ((AUCheckBox)Obj).setEditable(b);
          else if(Obj instanceof Datum)
            ((Datum)Obj).setEditable(b);
          else if(Obj instanceof AUTextArea)
            ((AUTextArea)Obj).setEditable(b);
          else if(Obj instanceof AUEkitCore)
            ((AUEkitCore)Obj).setEditable(b);
//          else if(Obj instanceof HtmlEingabe)
//            ((HtmlEingabe)Obj).setEditable(b);
          else if(Obj instanceof EMail)
            ((EMail)Obj).setEditable(b);
          else if(Obj instanceof WWW)
            ((WWW)Obj).setEditable(b);
          else if(Obj instanceof Text)
            ((Text)Obj).setEditable(b);
          else if(Obj instanceof WaehrungsEingabe)
            ((WaehrungsEingabe)Obj).setEditable(b);
          else if(Obj instanceof MassEingabe)
            ((MassEingabe)Obj).setEditable(b);
          else if(Obj instanceof GPS_Eingabe)
            ((GPS_Eingabe)Obj).setEditable(b);
          else if(Obj instanceof Zahl)
            ((Zahl)Obj).setEditable(b);
          else if(Obj instanceof Zahl2)
            ((Zahl2)Obj).Edt.setEditable(b);
          else if(Obj instanceof FileEingabe)
            ((FileEingabe)Obj).setEditable(b);
          else if(Obj instanceof VonBisEingabe)
            ((VonBisEingabe)Obj).setEditable(b);
          else if(Obj instanceof AUFarbe)
            ((AUFarbe)Obj).setEditable(b);
          else if(Obj instanceof AUPasswort)
            ((AUPasswort)Obj).setEditable(b);
          else if(Obj instanceof SpinBoxAuswahl)
            ((SpinBoxAuswahl)Obj).setEditable(b);
          else if(Obj instanceof RadioAuswahl)
            ((RadioAuswahl)Obj).setEnabled(b);
          else if(Obj instanceof BildEingabe || Obj instanceof javax.swing.JLabel)
            ;
          else
            Static.printError("Check.setEditable für "+s+" mit "+Static.className(Obj)+" nicht möglich!");
          //if(Obj instanceof TextField)
          //  ((TextField)Obj).setEditable(Obj);
        }

        public static boolean Vorhanden(Global g,int iAic,int iRolle,Zeit z)
        {
          if (z==null)
            return true;
          //if (sDT.equals("BewStamm") || sDT.equals("BewHierarchie") || sDT.equals("Gruppe") || sDT.equals("Hierarchie"))
          if (iAic>0)
          {
            //int iAic = getI(Obj);
            //if (iAic==0)
            //  return true;
            String sDate=g.DateTimeToString(new DateWOD(z.getDate()).setTimeZero().toDate());
            return SQL.exists(g,"select * from stammview2 where aic_stamm="+iAic+" and aic_rolle"+(iRolle==0? " is null":"="+iRolle)+
                              " and (eintritt is null or eintritt<="+sDate+") and (austritt is null or austritt>="+sDate+")");
          }
          else
            return true;
        }

	public static boolean inRange(Object Obj,Integer min,Integer max)
	{
		boolean bu=true;
		boolean bo=true;
		if(Obj instanceof Zahl || Obj instanceof Zahl2)
		{
			bu=min==null || isNull(Obj) || min.intValue()<=getF(Obj);
			bo=max==null || isNull(Obj) || max.intValue()>=getF(Obj);
		}
                else if(Obj instanceof WaehrungsEingabe)
		{
			bu=min==null || min.intValue()<=((WaehrungsEingabe)Obj).getAbsValue();
			bo=max==null || max.intValue()>=((WaehrungsEingabe)Obj).getAbsValue();
		}
		else if(Obj instanceof MassEingabe)
		{
			bu=min==null || min.intValue()<=((MassEingabe)Obj).getAbsValue();
			bo=max==null || max.intValue()>=((MassEingabe)Obj).getAbsValue();
		}
		else if(Obj instanceof VonBisEingabe)
		{
			bu=min==null || min.intValue()<=((VonBisEingabe)Obj).getDauer();
			bo=max==null || max.intValue()>=((VonBisEingabe)Obj).getDauer();
		}
                /*else if(Obj instanceof Datum && min==null)
                {
                  bu=true;
                  long l=((Datum)Obj).DaysChanged();
                  System.out.println("Tage geändert:"+l);
                  bo=l<31;
                }*/
		return bu && bo;
	}

        public static boolean inZR(Object Obj,Global g)
        {
          Date dt=((Zeit)Obj).getDate();
          if (dt == null)
            return true;
          else
            return !dt.before(g.getVon()) && dt.before(g.getBis());
        }

        public static String getS2(Object Obj)//,Global g,String sDt)
        {
          if (Obj instanceof String)
                  return ((String)Obj);
          else if (Obj instanceof Text)
                  return ((Text)Obj).getText();
          else if (Obj instanceof ComboSort)
			return ((ComboSort)Obj).getSelectedItem().toString();
          else if (Obj instanceof EMail)
                  return ((EMail)Obj).getValue();
          else if (Obj instanceof WWW)
                  return ((WWW)Obj).getValue();
          else if (Obj instanceof AUTextArea)
                  return ((AUTextArea)Obj).getValue();
          else if (Obj instanceof AUEkitCore)
                  return ((AUEkitCore)Obj).getValue();
//          else if (Obj instanceof HtmlEingabe)
//              return ((HtmlEingabe)Obj).getValue();
          else
          {
            //g.progInfo("getLaenge von Objekt "+Obj.getClass().getName()+"/"+sDt+" nicht ermittelbar");
            return null;
          }
        }

	public static String getS(Object Obj)
	{
		if (Obj instanceof String)
			return (String)Obj;
		else if (Obj instanceof Text)
			return ((Text)Obj).getText();
                else if (Obj instanceof AUComboList)
			return ((AUComboList)Obj).getComboBox().getSelectedItem().toString();
		else if (Obj instanceof ComboSort)
			return ((ComboSort)Obj).getSelectedItem().toString();
		else if (Obj instanceof Combo)
			return Obj.toString();
		else if (Obj instanceof EMail)
			return ((EMail)Obj).getValue();
		else if (Obj instanceof WWW)
			return ((WWW)Obj).getValue();
        else if (Obj instanceof AUTextArea)
			return ((AUTextArea)Obj).getValue();
        else if (Obj instanceof AUEkitCore)
			return ((AUEkitCore)Obj).getValue();
//        else if (Obj instanceof HtmlEingabe)
//			return ((HtmlEingabe)Obj).getValue();
        else if (Obj instanceof Datum)
            return ((Datum)Obj).toString();
		else
		{
			Static.printError("Check.getS(): Objekt "+Obj.getClass().getName()+" kann nicht in String verwandelt werden!");
			return "";
		}
	}

	public static int getI(Object Obj)
	{
		if (Obj instanceof AUComboList)
			return ((AUComboList)Obj).getComboBox().getSelectedAIC();
		else if (Obj instanceof Combo)
			return ((Combo)Obj).getAic();
                else if (Obj instanceof HierarchieAnzeige)
			return ((HierarchieAnzeige)Obj).getValueStamm();
                else if (Obj instanceof HierarchieEingabe)
                  return ((HierarchieEingabe)Obj).getValueStamm();
                else if(Obj instanceof AUOutliner)
                  return ((Integer)((AUOutliner)Obj).getSelectedNode().getUserData()).intValue();
                else if(Obj instanceof SpinBoxAuswahl)
                  return ((SpinBoxAuswahl)Obj).getAic();
                else if(Obj instanceof RadioAuswahl)
                  return ((RadioAuswahl)Obj).getAic();
		else
		{
			Static.printError("Check.getI(): Objekt "+Obj.getClass().getName()+" kann nicht AIC ermitteln!");
			return 0;
		}
	}

        public static double getF(Object Obj)
        {
          if (Obj instanceof Zahl)
            return ((Zahl)Obj).doubleValue();
          else if (Obj instanceof Zahl2)
            return ((Zahl2)Obj).Edt.doubleValue();
          else
            return Sort.getf(Obj);
        }

        public static int getMaxLaenge(String sDt,int iL)
        {
          int iL2=sDt.equals("StringSehrKurz") ? 10:sDt.equals("StringKurz")||sDt.equals("StringKurzOhne") ? 30:
              sDt.equals("String60")||sDt.equals("E-Mail") ? 60:
              sDt.equals("StringLang")||sDt.equals("Stringx") ? 256:Static.iMemoMax;
          return iL<1 || iL2<iL ? iL2:iL;
        }

        public static boolean zuLang(Object Obj,String sDt,int iL)
        {
          if (Obj==null || !(Obj instanceof String))
            return false;
          iL=getMaxLaenge(sDt,iL);
          return (((String)Obj).length()>iL);
        }

	public static boolean nichtEindeutig(Global g,Object Obj,String sDatentyp,int iEig,int iStt)
	{
          if (isNull(Obj))
            return false;

		if (sDatentyp.equals("WWW") || sDatentyp.equals("Memo") || sDatentyp.equals("Pfad") || sDatentyp.equals("Filename"))
			sDatentyp = "StringLang";
		else if (sDatentyp.equals("StringKurzOhne"))
			sDatentyp = "StringKurz";
		else if (sDatentyp.equals("E-Mail"))
			sDatentyp = "String60";
		else if (sDatentyp.equals("Auto_von_bis"))
			sDatentyp = "von_bis";
                /*else if (sDatentyp.equals("Stringx"))
                {
                  int iL=getS(Obj).length();
                  sDatentyp=iL<11?"StringSehrKurz":iL<31?"StringKurz":iL<61?"String60":iL<256?"StringLang":"Text";
                }*/

		if (iStt>0)
		{
			if (sDatentyp.equals("Integer") || sDatentyp.equals("Double"))
				return SQL.exists(g,"select aic_stammpool from stammpool p,stammview2 v where pro2_aic_protokoll is null and p.aic_stamm=v.aic_stamm and aic_eigenschaft="+iEig+
					" and aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and spalte_double="+getF(Obj));
			if (sDatentyp.startsWith("String") || sDatentyp.equals("Text"))
				return SQL.exists(g,"select aic_stammpool from stammpool p join daten_"+sDatentyp+" d on d.aic_daten=p.aic_daten,stammview2 v where pro2_aic_protokoll is null and p.aic_stamm=v.aic_stamm and aic_eigenschaft="+iEig+
					" and aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and spalte_"+sDatentyp+"="+Static.StringForSQL(getS(Obj)));
			if (sDatentyp.startsWith("Gruppe") || sDatentyp.equals("Hierarchie"))
				return SQL.exists(g,"select aic_stammpool from stammpool p,stammview2 v where pro2_aic_protokoll is null and p.aic_stamm=v.aic_stamm and aic_eigenschaft="+iEig+
					" and aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and sta_aic_stamm="+getI(Obj));
			if (sDatentyp.startsWith("Datum"))
				return SQL.exists(g,"select aic_stammpool from stammpool p,stammview2 v where pro2_aic_protokoll is null and p.aic_stamm=v.aic_stamm and aic_eigenschaft="+iEig+
					" and aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and gultig_von="+Obj);
			if (sDatentyp.startsWith("Kennung"))
				return SQL.exists(g,"select aic_stamm from stammview2 where aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and Kennung="+Static.StringForSQL(getS(Obj)));
			if (sDatentyp.startsWith("Bezeichnung"))
				return SQL.exists(g,"select aic_stamm from stammview2 where aic_mandant="+g.getMandant()+" and aic_stammtyp="+iStt+" and Bezeichnung="+Static.StringForSQL(getS(Obj)));
			else
				return true;
		}
		else
			return true;
	}
    
	/**
	* prüft auf Monatsabschluß: iBegriff..Formular, Obj..neues Datum, Obj2..altes Datum
	*/
	public static boolean Monatsabschluss(Global g/*,int iBegriff*/,int iBew,int iStamm,Object Obj,Object Obj2)
	{
		Date dt=g.getAbschlussDate(/*iBegriff,*/iBew,iStamm);
                g.testInfo("Check.Monatsabschluss:"+iBew+"/"+iStamm+"="+Obj+"/"+Obj2+"/"+dt);
		if (dt==null)
			return false;
		else
		{
			//System.out.println("-> Monatsabschluss="+dt);
			long l=Long.MAX_VALUE;
			if (Obj instanceof Datum)
			{
				l=((Datum)Obj).isNull() ? Long.MAX_VALUE:((Datum)Obj).getDate().getTime();
				if (Obj2 != null && !((Datum)Obj2).isNull())
					l=Math.min(l,((Datum)Obj2).getDate().getTime());
			}
			else if(Obj instanceof Zeit)
			{
				l=((Zeit)Obj).isNull() ? Long.MAX_VALUE:((Zeit)Obj).getValue();
				if (Obj2 != null && !((Zeit)Obj2).isNull())
					l=Math.min(l,((Zeit)Obj2).getValue());
			}
			else if(Obj instanceof VonBis)
			{
				l=((VonBis)Obj).isNull() ? Long.MAX_VALUE:((VonBis)Obj).getVon().getTime();
				if (Obj2 != null && !((VonBis)Obj2).isNull())
					l=Math.min(l,((VonBis)Obj2).getVon().getTime());
			}
			else
				Static.printError("Check.Monatsabschluss(): Objekt "+Obj.getClass().getName()+" kann nicht auf Monatsabschluss geprüft werden!");
			return l<dt.getTime();
		}
	}

// add your data members here
}

