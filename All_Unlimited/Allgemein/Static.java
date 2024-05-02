/*
    All_Unlimited-Allgemein-Static.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Anzeige.Memo1;
import All_Unlimited.Allgemein.Anzeige.Zahl1;

import java.util.Collections;     // für Mac-Adresse
import java.net.NetworkInterface; // für Mac-Adresse
//import java.io.IOException;
import java.math.BigDecimal;


//import java.io.IOException;
import java.util.Base64;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.nio.channels.FileChannel;

public class Static extends java.lang.Object
{
	public static Date CalcWestEaster(int y)
	{
	// So berechnet man Ostern ...
	// gilt nur innerhalb 1900 und 2099
	// vielen Dank an Dr. Wolfgang Klein! :-)
	  int d = (y < 1700 ? 10 : y < 1800 ? 11 : y < 1900 ? 12 : y < 2100 ? 13 :
		   y < 2200 ? 14 : y < 2300 ? 15 : 16);

	  int m = (y < 1700 ? 202 : y < 1900 ? 203 : y < 2200 ? 204 :
		   y < 2300 ? 205 : 206);

	  int q = y / 4;
	  int b = (m - 11* (y % 19)) % 30;
	  if (b==28) b=27;
	  if (b==29) b=28;
	  int c = (y - (-q) - (-b) - d) % 7;

	  return new DateWOD(y, 3, 28 - (-b) -c).getTime();
	}
	
	public static Date CalcEastEaster(int jahr)
	{
//		int k = jahr/100;
		int m = 15;// + (3*k+3)/4 - (8*k+13)/25;
		int s = 0;//2 - (3*k+3)/4;
		int a = jahr % 19; // % bezeichnet die Modulo-Funktion
		int d = (19*a+m) % 30;
		int r = d/29 + d/28 - (d/29)*(a/11);
		int og = 21 + d - r;
		int sz = 7 - ((jahr+jahr/4+s) % 7);
		int oe = 7 - ((og-sz) % 7);
		
		return new DateWOD(jahr, 3, 13+og + oe).getTime();
	}

        public static void beep()
        {
        	if (bBeep)
        	{
        		if (TK==null)
        			TK=Toolkit.getDefaultToolkit();
        		TK.beep();
        	}
        	else
        		System.out.print("*");
        }

        public static String ErrorFile()
        {
          return DirError+sIP/*+"_"+sSub*/+(sUser==null ? "":"_"+sUser);
        }

        public static void printError(String s)
        {
          printError(s,true);
        }

	public static void printError(String s,boolean bAdd)
	{
          if (iMaxError>0 && iError>iMaxError)
          {
            iError++;
            return;
          }
          beep();
          System.err.println("Error:"+s);		
          if (bAdd)
            addError(s);
          if (DirError != null)
			Save.prot(new java.io.File(ErrorFile()+".err"),s);
//          if (bKonsole)
//        	  JavaKonsole.get();
	}

        public static void addError(String s)
        {
          iError++;
          //sError=sError==null ? s:sError+";"+s;
          if (VecError==null)
            VecError=new Vector<String>();
          VecError.addElement(s);
        }

        public static int getError()
        {
          return iError;//VecError==null ? 0:VecError.size();
        }

        public static String getErrorString()
        {
          return getErrorString(";");
        }

        public static String getErrorString(String sTrenn)
        {
          String sError=null;
          if (VecError!=null && VecError.size()>0)
          {
            sError=VecError.elementAt(0);
            for(int i=1;i<VecError.size();i++)
              sError+=sTrenn+VecError.elementAt(i);
          }
          return sError;
        }

        public static void setMaxError(int i)
        {
          iMaxError=i;
        }

        public static void clearError()
        {
          if (iError>0)
            System.err.println(iError+" Fehler aufgetreten");
          iError=0;
          //sError=null;
          VecError=null;
          iMaxError=-1;
        }

	public static String replaceString(String s, String sOld, String sNew)
	{
		for(int iA=s.indexOf(sOld);iA!=-1;iA=s.indexOf(sOld,iA+sNew.length()))
		{
			s=s.substring(0,iA)+sNew+s.substring(iA+sOld.length());
		}

		return(s);
	}

        public static long Mem()
        {
          return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        }
        
        public static double Used()
        {
        	Runtime rt=Runtime.getRuntime();
        	return (rt.totalMemory()-rt.freeMemory())*1.0/rt.maxMemory();
        }

        public static String Mem(boolean b)
        {
          return b ? "; Mem="+new Zahl1(Static.Used(),"##0.0 %")+": "+(Mem()/1024/1024)+" MB":"";
        }

	public static String changeUmlaute(String s)
	{
		/*s=replaceString(s,"ö","oe");
		s=replaceString(s,"Ö","Oe");
		s=replaceString(s,"ä","ae");
		s=replaceString(s,"Ä","Ae");
		s=replaceString(s,"ü","ue");
		s=replaceString(s,"Ü","Ue");
		s=replaceString(s,"ß","ss");*/
             s=s.replaceAll("ö","oe");
             s=s.replaceAll("Ö","Oe");
             s=s.replaceAll("ä","ae");
             s=s.replaceAll("Ä","Ae");
             s=s.replaceAll("ü","ue");
             s=s.replaceAll("Ü","Ue");
             s=s.replaceAll("ß","ss");
		return(s);
	}

        public static String changeK1(String s)
        {
          s=s.replaceAll(",","<K>");
          s=s.replaceAll("\"","<HK>");
          return(s);
        }

        public static String changeK2(String s)
        {
          if (s==null)
            return "";
          s=s.replaceAll("<K>",",");
          s=s.replaceAll("<HK>","\"");
          return(s);
        }

        public static String forExport(String s,boolean bForExport)
        {
          if (bForExport && (s.indexOf(",")>0 || s.indexOf("\r")>0 || s.indexOf("\n")>0))
          {
            s=s.replaceAll("\r","<CR>").replaceAll("\n","<LF>").replaceAll(",","<K2>");
            //g.progInfo("geändert auf:"+s);
          }
          return s;
        }

        public static String fromImport(String s)
        {
          if (s!=null && s.length()>0)
            s=s.replaceAll("<CR>","\r").replaceAll("<LF>","\n").replaceAll("<K2>",",");
          return s;
        }
        
        private static String forSQL(String str)
        {
        	if (str == null) { 
                return null; 
            } 
     
            if (str.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]","").length() < 1) { 
                return str; 
            } 
     
            String clean_string = str; 
            if (Static.bMySQL)
            {
              clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\"); 
              clean_string = clean_string.replaceAll("\\n","\\\\n"); 
              clean_string = clean_string.replaceAll("\\r", "\\\\r");
              clean_string = clean_string.replaceAll("'", "\\\\'");   
              clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
            }
            else
              clean_string = clean_string.replaceAll("'", "''"); 
            clean_string = clean_string.replaceAll("\\t", "\\\\t"); 
            clean_string = clean_string.replaceAll("\\00", "\\\\0"); 
            return clean_string; 
        }

        public static String StringForSQL(String s)
        {
          return StringForSQL(s,iMemoMax);
        }

	public static String StringForSQL(String s,int iAnz)
	{
		if (s.length()>iAnz)
            s=s.substring(0,iAnz);
		s=forSQL(s);
          
//		String sOld="'";
//		boolean bA = false;
//		boolean bE = false;
//
//		for(int iA=s.indexOf("'");iA!=-1;iA=s.indexOf("'",iA))
//		{
//			if(iA==0)
//			{
//				s="'''"+s.substring(iA+1);
//				bA=true;
//				iA=iA+3;
//			}
//			else if(iA==s.length()-1)
//			{
//				s=s.substring(0,iA)+"'''";
//				bE=true;
//				iA=iA+3;
//			}
//			else
//			{
//				s=s.substring(0,iA)+"''"+s.substring(iA+sOld.length());
//				iA=iA+2;
//			}
//		}
//
//		if(!bA)
//			s="'"+s;
//
//		if(!bE)
//			s=s+"'";
//
//		return(s);
          return "'"+s+"'";
	}

//        public static String StringForMySQL(String s)
//        {
//          if (s != null)
//            for(int iA=s.indexOf("\\");iA!=-1;iA=s.indexOf("\\",iA))
//            {
//                  s=s.substring(0,iA)+"\\\\"+s.substring(iA+1);
//                  iA=iA+2;
//            }
//            return(s);
//        }

	public static String SQL_Format(Object Obj)
	{
		return Obj == null ? "null" : Obj instanceof Date ? "'"+DateTimeToString((Date)Obj)+"'":
			Obj instanceof String ? StringForSQL((String)Obj):Obj instanceof Boolean?Obj.equals(Boolean.TRUE)?"1":"0":Obj.toString();
	}

        public static String SQL_in2(Vector Vec)
        {
          if (Vec==null || Vec.size()==0)
            return "=-1";
          else if (Vec.size()==1)
            return "="+Vec.elementAt(0);
          else
            return SQL_in(Vec);
        }

	public static String SQL_in(Vector Vec)
	{
		if (Vec==null || Vec.size()==0)
		{
			//System.err.println("Error: SQL_in ohne Vec aufgerufen");
			return " is null ";
		}
		String sIn=" in("+SQL_Format(Vec.elementAt(0));

		for(int i=1;i<Vec.size();i++)
			sIn=sIn+","+SQL_Format(Vec.elementAt(i));

		sIn=sIn+")";

		return (sIn);
	}

	public static String printZahl(long l,int iAnz)
	{
		String s=new java.text.DecimalFormat("0,000").format(l);
		return rightString(s,iAnz);
	}

	public static String rightString(String s,int iAnz)
	{
		for (int i=s.length();i<iAnz;i++)
			s=' '+s;
		return s;
	}

//        public static Date StringToDate(String s,String sFormat)
//        {
//          return new java.text.SimpleDateFormat(sFormat).parse(s, new java.text.ParsePosition(0));
//        }
//        
//     public static String DateToString(Date date)
//     {
//    	return DateToStr(date,"yyyy/MM/dd");
//     }

//	public static String DateToStr(Date date,String sFormat)
//    {
//		return date == null ? Static.sLeer: new SimpleDateFormat(sFormat).format(date);
//    }

        public static Integer DateToInt(Date date)
        {
          return date == null ? Int0:new Integer(new SimpleDateFormat("yyyyMMdd").format(date));
        }
        
        public static Date StrToDate(String s)
        {
        	return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(s, new java.text.ParsePosition(0));
        }
        
        public static Date StrToDateTime(String s)
        {
        	return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s, new java.text.ParsePosition(0));
        }
        		

	public static String CutNull(String s)
	{
		int i=-1;
		do
		{
			i++;
		}
		while(i+1<s.length()&&s.charAt(i)=='0');

		return s.substring(i);
	}

	public static String FillSpace(String s,int iAnz)
	{
		return FillSpace("",s,iAnz);
	}

        /*public static String FillSpace(String s,int iAnz,int i2)
        {
                return FillSpace(i2==0?"":"",s,iAnz);
        }*/

	public static String FillSpace(String sDatentyp,String s,int iAnz)
	{
		int iL=s.length();
		if (iL==iAnz)
			return s;
		else if (iL>iAnz)
			return s.substring(0,iAnz);
		else
		{
			String s2="";
			for(int i=iL;i<iAnz;i++)
				s2+=' ';
			return sDatentyp.endsWith("Waehrung") || sDatentyp.equals("BewWaehrung2") || sDatentyp.endsWith("Mass") || sDatentyp.equals("BewMass2") || sDatentyp.equals("CalcField")
			|| sDatentyp.equals("BewDauer") || sDatentyp.equals("Einheiten") || sDatentyp.equals("Aic") || sDatentyp.equals("SysAic") || sDatentyp.equals("Integer")
                        || sDatentyp.equals("Double") || sDatentyp.equals("BewZahl") || sDatentyp.equals("BewZahl2") || sDatentyp.equals("BewCount") || sDatentyp.equals("BewVon_Bis") ? s2+s:s+s2;
		}
	}

	public static String FillNull(String s,int iAnz)
	{
		int iL=s.length();
		if (iL==iAnz)
			return s;
		else if (iL>iAnz)
			return s.substring(iL-iAnz);
		else
		{
			String s2="";
			for(int i=iL;i<iAnz;i++)
				s2+='0';
			return s2+s;
		}
	}

	public static String FillNull(long lZahl,int iAnz)
	{
		String s="";
		for(int i=(""+lZahl).length();i<iAnz;i++)
			s=s+'0';
		return (lZahl<0?"-":"")+s+Math.abs(lZahl);
	}

	public static String VorNull(int i)
	{
		return (i<10 ?"0":"")+i;
	}


	public static String DateTimeToString(Date date)
	{
		return date == null ? "":new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
	}

        public static boolean Ungleich(Object Obj1, Object Obj2)
        {
          return Obj1==null ? Obj2 != null :Obj2==null ? true:!Obj1.equals(Obj2);
        }

	public static boolean Gleich(Object Obj1, Object Obj2)
	{
          //System.out.println("Obj1="+Obj1+"/"+className(Obj1)+", Obj2="+Obj2+"/"+className(Obj2));
	  boolean b= Obj1==null && Obj2==null ? true : Obj1==null || Obj2==null ? false : /*Obj1.equals(Obj2); */ Obj1 instanceof Image ? Obj1.equals(Obj2) :
	    Obj1 instanceof Date ?((Date)Obj1).getTime()==((Date)Obj2).getTime():
	    Obj1 instanceof Memo1 ? ((Memo1)Obj1).equals(Obj2):Obj1.toString().equals(Obj2.toString());
          //System.out.println("->"+b);
          return b;
	}

	public static boolean Gleich2(Object Obj1, Object Obj2)
	{
//	  System.err.println("Gleich2: Obj1="+(Obj1==null ? "null":Obj1 instanceof Date ? DateWOD.Format("yyyyMMddHHmmss", (Date)Obj1):Obj1.toString())+", Obj2="+(Obj2==null ? "null":Obj2 instanceof Date ? DateWOD.Format("yyyyMMddHHmmss", (Date)Obj2):Obj2.toString()));
	  if (Obj1!=null && !(Obj1 instanceof DateWOD) && !(Obj1 instanceof Date))
	  {
		  Static.printError("Gleich2 mit "+print(Obj1)+" nicht möglich");
		  return false;
	  }
	  if (Obj2!=null && !(Obj2 instanceof DateWOD) && !(Obj2 instanceof Date))
	  {
		  Static.printError("Gleich2 mit "+print(Obj2)+" nicht möglich");
		  return false;
	  }
		return Obj1==null && Obj2==null ? true : Obj1==null || Obj2==null ? false :
		  DateWOD.Format(null,"yyyyMMddHHmmss", Obj1 instanceof DateWOD ? ((DateWOD)Obj1).getTime():(Date)Obj1).equals(DateWOD.Format(null,"yyyyMMddHHmmss", Obj2 instanceof DateWOD ? ((DateWOD)Obj2).getTime():(Date)Obj2));
	}

	public static String beiLeer(String s,String s2)
	{
		return s == null || s.equals(sLeer) ? s2:s;
	}

        public static boolean Leer(Object s)
        {
          return s == null || s instanceof String && s.equals(sLeer);
        }

        public static int bei0(int i1,int i2)
        {
          return i1==0 ? i2:i1;
        }

        public static int bei(int i1,int i,int i2)
        {
          return i1==i ? i2:i1;
        }

        public static String cin(String s,String c)
        {
          return s+(s.endsWith(c)?"":c);
        }

	public static void OpenURL(String sURL,String sTitel)
	{
            if (sURL.startsWith("help:"))
              sURL= HilfeVerzeichnis+sURL.substring(5);
            else
              sURL = sURL.startsWith("mailto:") || sURL.indexOf(":\\")>=0 || sURL.indexOf(":/")>=0 ? sURL : "http://"+sURL;
            if (!bWindows || sURL.startsWith("http:"))
              sURL=toHttpFile(sURL);
            String s=sURL;
              try
              {
                //if (sURL.startsWith("file:") && !bWindows) sURL='"'+sURL+'"';
                //System.out.println("Aufruf von <"+sURL+">");
                s=(bWindows ? "rundll32.exe url.dll,FileProtocolHandler ":bLinux ? "xdg-open ":"open ") + sURL;
                Runtime.getRuntime().exec(s);
                //System.out.println("BS-exec:"+s);
              }
              catch(IOException io)
              {
                printError("OpenURL mit "+s+" nicht aufrufbar:"+io);
              }
	}
	
	public static void PrintFile(Global g,String sURL)//,String sTitel)
	{
              //sURL=toHttpFile(sURL);
              if (sURL.startsWith("file:"))
            	  sURL=sURL.substring(7);
              try
              {
                g.fixtestError("Druck von <"+sURL+">");
                //Runtime.getRuntime().exec((bWindows ? "PDFtoPrinter ":"lp ") + '"'+sURL+'"');
                String[] cmdArray = new String[2];
                cmdArray[0] = bWindows ? "PDFtoPrinter":"lp";
                cmdArray[1] = sURL;
                Runtime.getRuntime().exec(cmdArray,null);
              }
              catch(IOException io)
              {
                printError("PrintFile mit URL="+sURL+" nicht aufrufbar:"+io);
              }
	}

	public static void OpenURL(String sURL)
	{
		OpenURL(sURL,"All Unlimited");
	}

//        public static void OpenURL(java.applet.Applet app,String sURL)
//        {
//          OpenURL(app,sURL,"All Unlimited");
//        }

//         public static void OpenURL(String sURL,String sTitel)
//         {
//           OpenURL(app,sURL,sTitel);
//         }


        public static URL toURL(String sFile)
        {
          if (sFile==null || sFile.startsWith("null"))
            return null;
          URL u = null;
          try {
            u = new URL(sFile);
          }
          catch(java.net.MalformedURLException e)
          {
            printError("Static.toURL: MalformedURLException - "+e);
          }
          return u;
        }

        public static String toHttpFile(String s)
        {
          String s2=s.replaceAll(" ","%20").replaceAll("ä","%C3%A4").replaceAll("ö","%C3%B6").replaceAll("ü","%C3%BC");
          s2=s2.replaceAll("ß","%C3%9F").replaceAll("Ä","%C3%84").replaceAll("Ö","%C3%96").replaceAll("Ü","%C3%9C").replaceAll("é","%C3%A9");
          if (s.equals(s2))
            return s;
          else
          {
            if (bInfoBild)
              System.err.println("toHttpFile: File geändert von <"+s+"> auf <"+s2+">");
            return s2;
          }
        }
        
        // private static boolean hasPath(String sFile)
        // {
        // 	if (Leer(sFile))
        // 		return true;
        // 	return sFile.startsWith("file:") || sFile.startsWith("http:") || sFile.startsWith("https:");
        // }

        public static Image LoadImage(String sFile,String sDir)
        {
//          if (bInfoBild)
//            System.out.println("   *** LoadImage "+(hasPath(sFile) ?"":"in "+sDir+":")+sFile);
          //if (sFile != null)
          //  System.err.println("LoadImage:<"+sFile+">");
          if (sDir.startsWith("http"))
            sFile=toHttpFile(sFile);
          if (bInfoBild)
              System.out.println("   *** LoadImage "+sFile);
          return sFile == null /*|| sFile.startsWith("file:")*/ ? null: sFile.startsWith("direkt:") ? getImage(sFile.substring(7)):
              bBilder && !sFile.equals("") ? sFile.startsWith("file:") || sFile.startsWith("http:") || sFile.startsWith("https:") ? getImage(sFile):
              (!sDir.startsWith("file:") || new java.io.File(sDir.substring(6)+sFile).exists()) ?
              getImage(sDir+sFile) : null:null;
        }

        public static Image getImage(String s)
        {
          //if(new File(s).exists())
          //URL u=toURL(s);
          //System.out.println("*** getImage "+u);
          //Image Img=app==null ? null:app.getImage(u);
          Image Img=null;
          try
          {
          if (Leer(s))
        	  ;
          else if (s.length()>7 && s.startsWith("file:") && s.charAt(6)==File.separator.charAt(0))
        	  Img=ImageIO.read(new File(s.substring(7)));//TK.getImage(s.substring(7));
          else
        	  Img=ImageIO.read(toURL(s));//TK.getImage(toURL(s));
          }
          catch (Exception e) {};
          //Image Img=TK.getImage(s);
          //System.out.println("Img="+Img.getWidth(ii.getImageObserver()));
          return Img==null || Img.getWidth(new javax.swing.ImageIcon(Img).getImageObserver())<0 ? null:Img;
        }

	public static void addActionListener(JButton Btn, ActionListener Listener)
	{
		if(Btn!=null)
			Btn.addActionListener(Listener);
	}

	public static void addActionListener(JComboBox Cbx, ActionListener Listener)
	{
		if(Cbx!=null)
			Cbx.addActionListener(Listener);
	}

	public static void addContainer(Container Con, Component Com)
	{
		if(Con!=null)
			Con.add(Com);
	}

        public static void getAll(JCOutlinerNode Nod,Vector<JCOutlinerNode> Vec)
        {
          //if (Vec==null)
          //  Vec=new Vector<JCOutlinerNode>();
          Vector Vec2=Nod.getChildren();
          if (Vec2 != null)
            for (int i=0;i<Vec2.size();i++)
            {
              JCOutlinerNode NodNew=(JCOutlinerNode)Vec2.elementAt(i);
              Vec.addElement(NodNew);
              getAll(NodNew,Vec);
            }
        }
        
    public static void makeVisible(JCOutliner Gid,JCOutlinerNode Nod)
    {
    	makeVisible(Gid,Nod,false);
    }

	public static void makeVisible(JCOutliner Gid,JCOutlinerNode Nod,boolean bFCL)
	{
          try
          {
          //Gid.setFocusNode(Nod,null);
		/*Gid.selectNode(Nod,null);
		Gid.folderChanged(Gid.getRootNode());
		Gid.repaint();*/
//System.out.println("makeVisible:"+Nod);
                /*Vector Vec=Nod.getParent().getChildren();
                //sleep(50);
                JCOutlinerNode Nod2=(JCOutlinerNode)Vec.elementAt(Vec.size()-1);
                Gid.selectNode(Nod2,null);
                Gid.makeNodeVisible(Nod2);
                System.out.println("mV1:"+Nod2);
                Gid.folderChanged(Gid.getRootNode());
                Gid.repaint();
                sleep(100);*/
	  	if (Gid==null)
	  	{
	  	  Gid=Nod.getOutliner();
	  	  JCOutlinerFolderNode Nod2=Nod.getParent();
	  	  if (Nod2 != null)
	  	   while(Nod2.getLevel() > 0) {
	  	    Nod2.setState(BWTEnum.FOLDER_OPEN_ALL);
	  	    Nod2 = Nod2.getParent();
	  	  }
	  	}
	  	//System.err.println("makeVisible1:"+Gid.getSelectedNode()+"/"+Nod);
	  	if (!bFCL)
               Gid.folderChanged(Gid.getRootNode());
               if (Nod==null)
                 Nod=Gid.getSelectedNode();
               else
                 Gid.selectNode(Nod,null);
               //if (Nod==null || Nod.getLevel()<1)
               //  return;
        //System.err.println("makeVisible2:"+Gid.getSelectedNode()+"/"+Nod);
               try
               {
                Gid.makeNodeVisible(Nod);
                if (bFCL)
                	Gid.folderChanged(Gid.getRootNode()); // nach unten, da sonst HS-EF-Sync nicht klappt
               }
               catch(Exception e) {};
        //System.err.println("makeVisible3:"+Gid.getSelectedNode()+"/"+Nod);
                //System.out.println("mV2:"+Nod);
//                Gid.folderChanged(Gid.getRootNode());
//                Gid.repaint();

                /*Vector Vec=Gid.getVisibleNodes();
                int i=0;
                while (i<5 && !Vec.contains(Nod))
                {
                  System.out.println("Vec="+Vec);
                  Gid.makeNodeVisible(Nod);
                  Gid.folderChanged(Gid.getRootNode());
                  Gid.repaint();
                  sleep(50);
                  Vec=Gid.getVisibleNodes();
                  i++;
                }
                System.out.println("makeVisible:"+Nod+":"+i);*/

    //Gid.selectNode(Nod,null);
    //Gid.folderChanged(Gid.getRootNode());
    //Gid.repaint();
          }
          catch(Exception e)
          {
                  printError("Static.makeVisible:"+e);
                  e.printStackTrace();
          }
	}

        public static void repaint(Window win)
        {
          win.validate();
          /*Dimension dim=win.getSize();
          win.setSize(50,50);
          sleep(10);
          win.setSize(dim);*/
        }

	public static void CopyKnoten(JCOutlinerNode NodVon,JCOutlinerNode NodNach)
	{
		NodNach.setLabel(NodVon.getLabel());
		if(NodVon.getUserData() != null)
			NodNach.setUserData(NodVon.getUserData());
		if (NodNach.getChildren() != null)
			((JCOutlinerFolderNode)NodNach).removeChildren();
		Vector Vec=NodVon.getChildren();
		if (Vec != null)
			for (int i=0;i<Vec.size();i++)
				CopyKnoten((JCOutlinerNode)Vec.elementAt(i),(JCOutlinerNode)new JCOutlinerFolderNode("",(JCOutlinerFolderNode)NodNach));
	}

	/*public static String ErsteZeile(String s)
	{
		int i=s.indexOf(10);
		return i==-1 ? s : s.substring(0,i);
	}*/

// add your data members here

	public static boolean allow_up_down(JCOutlinerNode OutBuildNod,boolean bRauf)
	{
	  return allow_up_down(OutBuildNod,bRauf,1);
	}

	public static boolean allow_up_down(JCOutlinerNode OutBuildNod,boolean bRauf,int iAnz)
	{
		//boolean bAllow = OutBuildNod!=(OutBuild.getRootNode());
		Vector VecKnoten = OutBuildNod==null || OutBuildNod.getParent()==null ? null : OutBuildNod.getParent().getChildren();
		return(VecKnoten == null? false : bRauf ? VecKnoten.indexOf(OutBuildNod)>0:VecKnoten.indexOf(OutBuildNod)<VecKnoten.size()-iAnz);
	}

	@SuppressWarnings("unchecked")
	public static void move_up_down(JCOutlinerNode OutBuildNod,boolean bRauf)
	{
		Vector<JCOutlinerNode> VecKnoten = OutBuildNod.getParent().getChildren();
		int iPos=VecKnoten.indexOf(OutBuildNod);
                if (bRauf && iPos>0 || !bRauf && iPos<VecKnoten.size()-1)
                {
                  VecKnoten.removeElement(OutBuildNod);
                  VecKnoten.insertElementAt(OutBuildNod, bRauf ? iPos - 1 : iPos + 1);
                  OutBuildNod.getOutliner().folderChanged(OutBuildNod.getParent());
                }
	}

        public static Point getMiddle(Component com2)
        {
        	try
        	{
	          if (com2==null/* || !com2.isVisible()*/ || com2.getLocationOnScreen()==null)
	            return P0;
	//          if (com2 instanceof JFrame)
	//        	  System.err.println("getMiddle von "+((JFrame)com2).getTitle()+": "+com2.getX()+"/"+com2.getY());
	          return new Point(com2.getLocationOnScreen().x+com2.getSize().width/2,com2.getLocationOnScreen().y+com2.getSize().height/2);
        	}
        	catch(Exception e)
        	{
        		return P0;
        	}
        }

        public static void centerComponent(Component com1, Component com2)
        {
          centerComponent(com1,getMiddle(com2),0);
        }
        
        public static void centerComponent2(Component com1, Component com2)
        {
        	checkMonitor();
//        	if (com1 instanceof JFrame)
//        		System.err.println("centerComponent2F von "+((JFrame)com1).getTitle()+": "+com1.getX()+"/"+com1.getY()+"; com2="+com2);
//        	else if (com1 instanceof JDialog)
//        		System.err.println("centerComponent2D von "+((JDialog)com1).getTitle()+": "+com1.getX()+"/"+com1.getY()+"; com2="+com2);
        	int iP=0;
        	int iX2=com2.getLocationOnScreen().x;
        	int iW2=com2.getSize().width;
        	int iY2=com2.getLocationOnScreen().y;
        	int iH2=com2.getSize().height;
    		for (int i=0;i<TabMonitor.size();i++)
    		  if ((iX2>=TabMonitor.getI(i,"x")) && (iX2<TabMonitor.getI(i,"xm")) && (iY2>=TabMonitor.getI(i,"y")) && (iY2<TabMonitor.getI(i,"ym")))
    			  iP=i;
    		int iXmax=TabMonitor.getI(iP,"xm");
//        	int iXmin=TabMonitor.getI(iP,"x");
        	int iYmin=TabMonitor.getI(iP,"y");
//        	int iwP=TabMonitor.getI(iP,"w");
//        	int ihP=TabMonitor.getI(iP,"h");
        	
        	int iW=com1.getSize().width;
            int iH=com1.getSize().height;
        	int ix=(iX2+iW2+iW)<iXmax ? iX2+iW2:iX2;
        	if (ix+iW > iXmax)
        		ix=iXmax-iW;
        	int iy=iY2+iH2/2-iH/2;
        	if (iy<iYmin)
        		iy=iYmin;
        	com1.setLocation(ix,iy);
        }
        
    public static Tabellenspeicher getTabMonitor()
    {
    	checkMonitor();
    	return TabMonitor;
    }
    
    public static Rectangle getMonRec(Component com)
    {
    	checkMonitor();
    	int iX2=com.getLocationOnScreen().x;
    	int iY2=com.getLocationOnScreen().y;
    	for (int i=0;i<TabMonitor.size();i++)
  		  if ((iX2>=TabMonitor.getI(i,"x")) && (iX2<TabMonitor.getI(i,"xm")) && (iY2>=TabMonitor.getI(i,"y")) && (iY2<TabMonitor.getI(i,"ym")))
  			  return (Rectangle)TabMonitor.getInhalt("Rec", i);
    	return null;
    }
    
    public static int getMon(int iX2,int iY2)
    {
    	checkMonitor();
    	for (int i=0;i<TabMonitor.size();i++)
    		  if ((iX2>=TabMonitor.getI(i,"x")) && (iX2<TabMonitor.getI(i,"xm")) && (iY2>=TabMonitor.getI(i,"y")) && (iY2<TabMonitor.getI(i,"ym")))
    			  return TabMonitor.getI(i,"Nr");
    	return 0;
    }

    private static void checkMonitor()
    {
    	if (TabMonitor==null)
    	{
    		TabMonitor=new Tabellenspeicher(null,new String[] {"Nr","x","y","xm","ym","w","h","text","Rec"});
    		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    		GraphicsDevice[] gs = ge.getScreenDevices();

    		for (int i3 = 0; i3 < gs.length; i3++) {
    			//DisplayMode dm = gs[i3].getDisplayMode();
    			Rectangle dm = gs[i3].getDefaultConfiguration().getBounds();
    			//s+="Screen"+i3+": Pos="+(int)dm.getX()+","+(int)dm.getY()+" W=" + (int)dm.getWidth()+" H: " + (int)dm.getHeight()+"\n";
    			TabMonitor.addInhalt("Nr", i3+1);
    			TabMonitor.addInhalt("x", dm.getX());
    			TabMonitor.addInhalt("y", dm.getY());
    			TabMonitor.addInhalt("w", dm.getWidth());
    			TabMonitor.addInhalt("h", dm.getHeight());
    			TabMonitor.addInhalt("xm", dm.getMaxX());
    			TabMonitor.addInhalt("ym", dm.getMaxY());
    			TabMonitor.addInhalt("text",gs[i3].getDefaultConfiguration().getDevice().getIDstring());
    			TabMonitor.addInhalt("Rec",dm);
//    			printError("Screen"+(i3+1)+":"+(int)dm.getX()+","+(int)dm.getY()+" W=" + (int)dm.getWidth()+" H: " + (int)dm.getHeight()+"/"+TabMonitor.getS("text"));
//    			printError("Screen"+(i3+1)+":"+gs[i3].getDefaultConfiguration().getDevice().getAvailableAcceleratedMemory()+","+gs[i3].getDefaultConfiguration().getDevice().getIDstring()+","+gs[i3].getDefaultConfiguration());
    		}
    	}
    }
        
    public static void centerComponent(Component com1, Point P,int iNr)
	{
    	checkMonitor();
//    	if (com1 instanceof JFrame)
//    		System.err.println("centerComponentF von "+((JFrame)com1).getTitle()+": "+com1.getX()+"/"+com1.getY()+"; P="+P);
//    	else if (com1 instanceof JDialog)
//    		System.err.println("centerComponentD von "+((JDialog)com1).getTitle()+": "+com1.getX()+"/"+com1.getY()+"; P="+P);
    	boolean b0=P==null || P==P0;
    	int iwP=0;
    	int ihP=0;
    	int iXmax=iMaxX;
    	int iXmin=iMinX;
    	int iYmin=iMinY;
    	if (b0)
    	{
    		iwP=TabMonitor.getI(0,"w");
    		ihP=TabMonitor.getI(0,"h");
    	}
    	else
    	{
    		int iP=0;
    		for (int i=0;i<TabMonitor.size();i++)
    		  if ((P.x>=TabMonitor.getI(i,"x")) && (P.x<TabMonitor.getI(i,"xm")) && (P.y>=TabMonitor.getI(i,"y")) && (P.y<TabMonitor.getI(i,"ym")))
    			  iP=i;
    		iXmax=TabMonitor.getI(iP,"xm");
        	iXmin=TabMonitor.getI(iP,"x");
        	iYmin=TabMonitor.getI(iP,"y");
        	iwP=TabMonitor.getI(iP,"w");
        	ihP=TabMonitor.getI(iP,"h");
//        	System.err.println("centerComponent auf "+iP+": "+iXmin+"/"+iYmin+"-"+iXmax+": "+iwP+"x"+ihP);
    	}
		//Dimension DimS=TK.getScreenSize();
                //Dimension Dim = com2==null || com2.getLocationOnScreen()==null ? DimS:com2.getSize();
                int iW=com1.getSize().width;
                int iH=com1.getSize().height;
		int ix=(b0 ? iwP/2: P.x)-iW/2;
		int iy=(b0 ? ihP/2+120: P.y)-iH/2;
                if (ix+iW>iXmax)
                  ix=iXmax-iW;
                if (ix<iXmin)// || ix<0 && Math.abs(ix)<iW/2)
                  ix=iXmin;
                if (iNr>0 &&iy>0)
                {
                  iy-=(iH+5)*iNr;
                  while (iy<0)
                    iy+=ihP;
                }
                if (iy<iYmin)
                {
//                	Global.fixInfoS("centerComponent: ändere iy von "+iy+" auf "+iYmin);
                	iy=iYmin;            
                }
                //Global.fixInfo("centerComponent:"+ix+"/"+iy+"/"+iW+"/"+iH);
		com1.setLocation(ix,iH>ihP-120 ? Math.max(25,iYmin):iy<0 ? 120:iy+iH>ihP+iYmin?ihP+iYmin-iH:iy);
                if (iH>ihP-50)
                  com1.setSize(iW,ihP-50);
	}

	public static String JaNein(boolean b)
	{
		return b ? sJa:sNein;
	}
	
	public static String JaNein2(boolean b)
	{
		if (bX)
			return b ? "x":sLeer;
		else
			return b ? sJa:sNein;
	}

	public static void addVectorI(Vector<Integer> Vec1, Vector<Integer> Vec2)
	{
		for(int i=0;i<Vec2.size();i++)
			if(!Vec1.contains(Vec2.elementAt(i)))
				Vec1.addElement(Vec2.elementAt(i));
	}

        public static void addVectorI(Stack<Integer> Vec1, Stack<Integer> Vec2)
        {
                for(int i=0;i<Vec2.size();i++)
                        if(!Vec1.contains(Vec2.elementAt(i)))
                                Vec1.addElement(Vec2.elementAt(i));
        }

        public static void addVectorS(Vector<String> Vec1, Vector<String> Vec2)
        {
                for(int i=0;i<Vec2.size();i++)
                        if(!Vec1.contains(Vec2.elementAt(i)))
                                Vec1.addElement(Vec2.elementAt(i));
        }

	public static void addVector(Vector<Object> Vec1, Vector<Object> Vec2)
	{
		for(int i=0;i<Vec2.size();i++)
			if(!Vec1.contains(Vec2.elementAt(i)))
				Vec1.addElement(Vec2.elementAt(i));
	}

        public static Vector getMinus(Vector Vec)
        {
          Vector<Integer> Vec2=new Vector<Integer>();
          for(int i=0;i<Vec.size();i++)
          {
            int i2 = ((Integer)Vec.elementAt(i)).intValue();
            if (i2<0)
              Vec2.addElement(new Integer(-i2));
          }
          //System.out.println("getMinus="+Vec2);
          return Vec2;
        }

	public static String FormatTS(Object Obj,String sZeitart)
	{
		if (Obj == null)
			return "";
		else if (sZeitart.equals("Woche"))
		{
			DateWOD DW=new DateWOD((Date)Obj);
			while (DW.getMonth()==12 && DW.get(Calendar.WEEK_OF_YEAR)==1)
				DW.tomorrow();
                        while (DW.getMonth()==1 && DW.get(Calendar.WEEK_OF_YEAR)==53)
				DW.yesterday();
			return sKW+DW.Format("ww/yy");
			//Obj=DW.getDate();
		}
		else
			return new java.text.SimpleDateFormat(sZeitart.equals("Jahr") || sZeitart.equals("Quartal")?"yyyy":sZeitart.equals("Monat")?"MMM_yy":"dd.MM.",DateWOD.DFS).format((Date)Obj)
				+(sZeitart.equals("Quartal")?"Q"+((new DateWOD((Date)Obj).getMonth()+2)/3):"");
	}

        /*public static String FormatTS3(Object Obj)
        {
          return FormatTS3(Obj,sZeitart);
        }*/

        public static String FormatTS3(Object Obj,String rsZeitart)
	{
        if (rsZeitart==null)
        	rsZeitart="Tag";
		if (Obj == null)
			return "";
		else if (rsZeitart.equals("Woche"))
		{
			DateWOD DW=new DateWOD((Date)Obj);
			while (DW.getMonth()==12 && DW.get(Calendar.WEEK_OF_YEAR)==1)
				DW.tomorrow();
                        while (DW.getMonth()==1 && DW.get(Calendar.WEEK_OF_YEAR)==53)
				DW.yesterday();
			return sKW+" "+DW.Format("ww/yyyy");
			//Obj=DW.getDate();
		}
		else
			return new java.text.SimpleDateFormat(rsZeitart.equals("Jahr") || rsZeitart.equals("Quartal")?"yyyy":rsZeitart.equals("Monat")?"MMMM yyyy":"dd.MM.yyyy",DateWOD.DFS).format((Date)Obj)
				+(rsZeitart.equals("Quartal")?" Q"+((new DateWOD((Date)Obj).getMonth()+2)/3):"");
	}

	public static String FormatTS2(boolean b,Object Obj,String sZeitart)
	{
		DateWOD DW=null;
		if (sZeitart.equals("Woche"))
		{
			DW=new DateWOD((Date)Obj);
			DW.nextWeek();
			DW.yesterday();
		}
		return b ? new java.text.SimpleDateFormat("EEE, dd.MM.",DateWOD.DFS).format((Date)Obj):
                    FormatTS(Obj,sZeitart)+(sZeitart.equals("Tag")?'\n'+new java.text.SimpleDateFormat("EEE",DateWOD.DFS).format((Date)Obj):
			sZeitart.equals("Woche")?'\n'+new java.text.SimpleDateFormat("d.M-").format((Date)Obj)+DW.Format("d.M"):"");
	}

	public static String hm(double d)
	{
		int iMin=(int)Math.round(Math.abs(d)/60.0);
		return (d<0?"-":"")+(iMin/60)+":"+VorNull(iMin%60);
	}

	public static Vector<Integer> AicToVec(int i)
	{
		Vector<Integer> Vec = new Vector<Integer>();
		Vec.addElement(new Integer(i));
		return Vec;
	}

	public static  String VecToString(Vector Vec)
	{
		String s="";
		for (int i=0;i<Vec.size();i++)
		{
			s+="<br />"+(i+1)+". "+Vec.elementAt(i); // statt \n am 19.9.2014: 3024
		}
		return s;
	}

        public static long get_ms()
        {
          return System.currentTimeMillis();
          //return System.nanoTime()/1000000l;
        }

        public static long get_Mikro_s()
        {
          return System.nanoTime()/1000;
        }

        public static String Mikro(long lClock)
        {
          long lClock2 = Static.get_Mikro_s() - lClock;
          if (lClock2<0)
            lClock2=0;
          return new BigDecimal(lClock2*0.000001).setScale(4,BigDecimal.ROUND_HALF_UP)+" s";
        }

        public static long get_ms2()
        {
          return System.currentTimeMillis();
        }

	public static void sleep(int iMillis)
	{
          if (iMillis<1)
            return;
		try
		{
			Thread.sleep(iMillis);
		}
		catch(Exception e)
		{
			printError("Static.sleep:"+e);
		}
	}

        public static String print(Object Obj)
        {
          return Obj==null?"null":Obj+" ("+Obj.getClass().getName()+")";
        }

        public static String hash(Object Obj)
        {
          return Obj==null?"< null >":Obj.getClass().getName() + "@" + Integer.toHexString(Obj.hashCode());
        }

	public static String className(Object Obj)
	{
		return Obj==null?"null":Obj.getClass().getName();
	}

	public static String cutString(String sText)
	{
		int iIndex=0;
		int iVorn=0;
		//int iHinten=0;
		int iDifferenz=500;
		boolean bFirst=true;
		sText=sText.trim();
		while((iIndex=sText.indexOf(" ",iIndex))!=-1)
		{
			int iHVorn=iIndex;
			iIndex++;
			int iHHinten=sText.length()-iIndex;
			int iDHilfe=iHVorn>iHHinten?iHVorn-iHHinten:iHHinten-iHVorn;
			if(bFirst||iDHilfe<iDifferenz)
			{
				iDifferenz=iDHilfe;
				iVorn=iHVorn;
				//iHinten=iHHinten;
				bFirst=false;
			}
		}
		String sVorn="";
		String sHinten="";
		if(iVorn!=0)
		{
			sVorn=sText.substring(0,iVorn);
			sHinten=sText.substring(iVorn+1);
			if(sVorn.startsWith("\\|"))
				sHinten="\\|"+sHinten;

		}


		return (iVorn!=0?sVorn+"\n"+sHinten:sText).replace('_',' ');
	}

	public static String addToString(String s)
	{
		int i=s.length();
		for(;i>0 && Character.isDigit(s.charAt(i-1));i--);
		if (i==s.length())
			return s+"1";
		else
		{
			String sZahl1=s.substring(i);
			String sZahl2=""+(Long.parseLong(sZahl1)+1);
			
			return s.substring(0,i)+FillNull(sZahl2,s.length()-i+(sZahl2.length()>s.length()-i ?1:0));
		}
	}

	public static String MiroCalc(String s,boolean bMiro)
	{
		String sHelp="00000000000000000000000000000000";
		long l=Long.parseLong(s,bMiro?10:16);
		s=Long.toBinaryString(l);
		int i=32-s.length();
		s=sHelp.substring(0,i)+s;

		String sH="";
		for(i=s.length();i>0;i--)
			sH=sH+s.substring(i-1,i);

		if(bMiro)
		{
			l=Long.parseLong(sH,2);
			s=Long.toHexString(l);
			i=8-s.length();
			s=sHelp.substring(0,i)+s;
			s=s.toUpperCase();
		}
		else
		s=""+Long.parseLong(sH,2);

		return(s);
	}

        public static void Escape(JButton Btn,final Window Win,Action cancelKeyAction)
        {
          //if (Btn==null || Btn==BtnBeenden)
          if (cancelKeyAction==null)
          {
            if (Btn==null)
            {
            	printError("Escape mit null wird nicht mehr unterstützt");
            	return;
//              Btn = BtnBeenden;
//              JPanel Pnl=new JPanel(new GridLayout(1,0,5,5));
//              Pnl.add(new JLabel());
//              Pnl.add(new JLabel());
//              Pnl.add(Btn);
//              (Win instanceof JFrame ? ((JFrame)Win).getContentPane():((JDialog)Win).getContentPane()).add("South",Pnl);
            }
            //System.out.println("BtnESC-Anzahl:"+Btn.getActionListeners().length);
            //System.out.println("BtnExp-Anzahl:"+BtnExport.getActionListeners().length);
            //if (Btn.getActionListeners().length==0)
            if (actESC!=null)
              Btn.removeActionListener(actESC);
            actESC=new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                Win.dispose();
              }
            };
            Btn.addActionListener(actESC);

            cancelKeyAction = new AbstractAction()
            {
              /**
				 *
				 */
				private static final long serialVersionUID = -4797374200549155042L;

			public void actionPerformed(ActionEvent e)
              {
                Win.dispose();
              }
            };
          }
          KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE/*, false*/);
          InputMap inputMap = Btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
          ActionMap actionMap = Btn.getActionMap();
          if(inputMap != null && actionMap != null)
          {
            inputMap.put(cancelKeyStroke, "cancel");
            actionMap.put("cancel", cancelKeyAction);
          }
        }

    public static void createTemp(String sDB,int riCache)
    {
      //Transact.fixInfo("createTemp:"+sDB+" Sep:"+File.separator);
      String Dir=!Leer(sDB) && sDB.contains(File.separator) ? sDB:System.getProperty("user.home")+File.separator+"AU"+File.separator+(Leer(sDB)?"":sDB+File.separator);
      if (DirError==null)
        DirError=Dir;
      iCache=riCache;
      //if (iCache != NIE)
      //{
        DirTemp = Dir;
        System.out.println("DirTemp=" + DirTemp);
      //}
      File Fil=new File(Dir);
      if (!Fil.exists())
        Fil.mkdirs();
    }

    public static String getTemp()
    {
      if (DirTemp==null)
        return System.getProperty("user.home")+File.separator+"AU"+File.separator;
      else
        return DirTemp;
    }

    public static boolean cache()
    {
      return DirTemp != null && (iCache==IMMER || iCache==AUTO && !bLocal);
    }

    public static boolean ComboLeer()
    {
      return iComboLeer==IMMER || iComboLeer==AUTO && (!bLocal || bSlow);
    }

    public static File getTempFile(String sFileName)
    {
      if (sFileName.indexOf(".AU")<0)
        sFileName+=".AU";
      return new File(DirTemp + sFileName);
    }

    public static int clearCache()
    {
      return clearCache("AU0")+clearCache("AU")+clearCache("AU2");
    }

    public static int clearCache(String sExt)
    {
      if (DirTemp==null)
        return 0;
      int iAnz=0;
      File[] fil = new File(DirTemp).listFiles();
      for(int i=0;i<fil.length;i++)
        if (fil[i].getName().endsWith("."+sExt))
        {
          if (fil[i].delete())
            iAnz++;
          //System.out.println("File" + i + ":" + fil[i]);
        }
      if (iAnz>0)
        System.out.println("Cache "+sExt+" gelöscht: "+iAnz+" Datei"+(iAnz>1?"en":""));
      return iAnz;
      //fil.delete();
    }

    public static int clearAbfrage(int iAbfrage)
    {
      int iAnz=0;
      File[] fil = new File(DirTemp).listFiles();

      for(int i=0;i<fil.length;i++)
      {
        String sN=fil[i].getName();
        if(sN.startsWith("Bed"+iAbfrage+".AU") || sN.startsWith("FixBed"+iAbfrage+".AU") ||
            sN.startsWith("FixSp"+iAbfrage+".AU") || sN.startsWith("Spalten"+iAbfrage+"_") ||
             sN.startsWith("SpalteC"+iAbfrage+".AU") || sN.startsWith("SpalteZ"+iAbfrage+"_"))
        {
          if(fil[i].delete())
            iAnz++;
          //System.out.println("File" + i + ":" + fil[i]);
        }
      }
      System.out.println("Cache gelöscht: "+iAnz+" Dateien");
      return iAnz;
      //fil.delete();
    }

    public static double Round6(double d)
    {
      double d2= Math.rint(d*1000000)/1000000;
      if (Math.abs(d2)<0.01)
        return Math.rint(d*1000000000000.0)/1000000000000.0;
      else if (Math.abs(d2)>100000000)
        return Math.rint(d*100)/100;
      else
        return d2;
    }

  public static String getMacAddress()
  {
    String result="";
    try
    {
      for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces()))
      {
        byte[] hardwareAddress = ni.getHardwareAddress();
        if (hardwareAddress != null)
        {
          for (int i = 0; i < hardwareAddress.length; i++)
            result += String.format((i == 0 ? "" : "-") + "%02X", hardwareAddress[i]);
          return result;
        }
      }
    }
    catch(Exception e)
    {
      printError("Static.getMacAddress:"+e);
      return null;
    }
    System.out.println("Mac-Adresse=:"+result);
    return result;
  }

//  public static BufferedImage decodeToImage(String imageString)
//  {
//      BufferedImage image = null;
//      byte[] imageByte;
//      try {
//          BASE64Decoder decoder = new BASE64Decoder();
//          imageByte = decoder.decodeBuffer(imageString);
//          ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//          image = ImageIO.read(bis);
//          bis.close();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//      return image;
//  }
  
  public static byte[] decodeToImage(String sBase64)
  {
//	  def parts = sBase64.tokenize(",");
//	  def imageString = parts[1];
	  String imageString=sBase64.substring(sBase64.indexOf(',')+1);
	  //System.out.println("decodeToImage:"+imageString);
	  byte[] decoded = Base64.getDecoder().decode(imageString);
//	  BASE64Decoder decoder = new BASE64Decoder();
//	  imageByte = decoder.decodeBuffer(imageString);
	  return decoded;
  }
  
  public static String encodeToString(BufferedImage image, String type)
  {
    if (bInfoBild)
      System.out.println("encodeToString "+type);
      String imageString = null;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();

      try {
          ImageIO.write(image, type, bos);
          byte[] imageBytes = bos.toByteArray();
          Base64.Encoder encoder = Base64.getMimeEncoder();
          imageString = encoder.encodeToString(imageBytes);
          bos.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return imageString;
  }
  
  public static BufferedImage imageToBufferedImage(String sFile,Image im) {
	  BufferedImage bi=null;
	  try
	  {
	     bi = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB /*TYPE_INT_ARGB_PRE*/);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(im, 0, 0, null);
	     bg.dispose();
	  }
	  catch (Exception e)
	  {
		  printError("Static.imageToBufferedImage mit "+sFile+": "+e);
		  return null;
	  }
	  return bi;
	  }

  public static String ImageToString(Image Img,String sExt)
  {
    if (Img!=null)
    {
        String s= "<img src=\"data:image/" + sExt + ";base64," + encodeToString(imageToBufferedImage(sExt,Img)/*((sun.awt.image.ToolkitImage)Img).getBufferedImage()*/, sExt); 
        return s;
    }
    else
      return null;
  }

  public static String ImageToString(String sFile)
  {
    if (bInfoBild)
      System.out.println("ImageToString "+sFile);
    try
    {
      String sExt=sFile.substring(sFile.lastIndexOf('.') + 1);
      return "<img src=\"data:image/"+sExt+";base64,"+encodeToString(ImageIO.read(new File(sFile)), sExt); 
    }
    catch (IOException e)
    {
      printError(sFile + " kann nicht geladen werden");
      return sLeer;
    }
  }

  public static void setAutoAus(char c)
  {
    iAutoAus=2*3600;
    if (c!='-' && c!='+')
    {
      try
      {
        if (c=='*')
          iAutoAus=12*3600;
        else if (c=='h')
          iAutoAus=1800;
        else
          iAutoAus=Integer.parseInt(""+c)*3600;
        if (iAutoAus==0)
          iAutoAus=300;
      }
      catch(Exception e)
      {
          printError("AutoAus nicht setzbar");
      }
    }
    System.out.println("AutoAus="+(iAutoAus/60)+" min");
  }

  public static String KillLeerzeilen(String s,boolean bClearHtml)
  {
    boolean bHtml=bClearHtml && s != null && s.startsWith("<html>");
    if (bHtml)
      s=s.substring(6,s.length()-7);
    s = s.trim();
    if (bClearHtml && s.length()>10)
    {
    	int iB1=s.indexOf("<body>");
    	int iB2=s.indexOf("</body>");
    	if (iB1>=0 && iB2>0)
    		s=s.substring(iB1+6, iB2);
    }
    int iV=0;
    int i=s.length();
    while (i != iV)
    {
      iV=i;
      s = s.replaceAll("\n\n", "\n");
      s = s.replaceAll("\n ", "\n");
      i=s.length();
    }
    if (bClearHtml && !bHtml)
      s = s.replaceAll("\n", "<br>");

    return s;
  }

  /*public static int count(String s,String sSub)
  {
    return s.split(sSub).length;
  }*/

  public static boolean copyFile(final File src, final File dest)
  {
	//Transact.fixInfo("kopiere "+src.getAbsolutePath()+" nach "+dest.getAbsolutePath());
    FileChannel srcChannel=null;
    FileChannel destChannel=null;
    try
    {
      srcChannel = new FileInputStream(src).getChannel();
      destChannel = new FileOutputStream(dest).getChannel();
      srcChannel.transferTo(0, srcChannel.size(), destChannel);
      if (srcChannel != null) srcChannel.close();
      if (destChannel != null) destChannel.close();
      //System.out.println(src.getName()+" kopiert");
    }
    catch (IOException e)
    {
      printError("Static.copyFile: konnte "+src.getAbsolutePath()+" nicht nach "+dest.getAbsolutePath()+" kopieren");
      e.printStackTrace();
      return false;
   }
   return true;
 }

 public static String generatePW(int length)
    {
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!+-.*_";//"!\"#$%&'()*+,-./:;<=>?@";
        return generatePW(length, digits, special, alphabet);
    }

 public static String generatePW(int length, String ... alphabets)
    {
        if (alphabets.length == 0)
        {
            throw new IllegalArgumentException(
                "At least one alphabet must be given");
        }
        StringBuffer result = new StringBuffer();
        StringBuffer all = new StringBuffer();
        for (int i=0; i<alphabets.length; i++)
        {
            if (alphabets[i] == null ||
                alphabets[i].equals(""))
            {
                throw new IllegalArgumentException(
                    "Invalid alphabet: "+alphabets[i]);
            }
            StringBuffer sb = new StringBuffer(alphabets[i]);
            result.append(selectRandom(sb, 1));
            if (result.length() == length)
            {
                return shuffle(result).toString();
            }
            all.append(sb);
        }
        result.append(selectRandom(all, length - result.length()));
        return shuffle(result).toString();
    }

    private static java.util.Random random = new java.util.Random();

    private static StringBuffer selectRandom(StringBuffer alphabet, int n)
        {
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<n; i++)
            {
                sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            return sb;
    }

    private static StringBuffer shuffle(StringBuffer sb)
    {
        for (int i=0; i<sb.length(); i++)
        {
            int i0 = random.nextInt(sb.length());
            int i1 = random.nextInt(sb.length());
            char c = sb.charAt(i0);
            sb.setCharAt(i0, sb.charAt(i1));
            sb.setCharAt(i1, c);
        }
        return sb;
    }
    
    public static String makeString(String s, String[] sArray)
    {
    	String sText = new String(s);
    	if (sArray!=null)
    	 for(int i=sArray.length;i>0;i--)
    	 {
    		int iIndex = sText.indexOf("/"+i);
    		if(iIndex!=-1)
    			sText = sText.substring(0,iIndex)+sArray[i-1]+sText.substring(iIndex+(("/"+i).length()),sText.length());
    	 }

    	return sText;
    }
    
    public static boolean noDirError()
    {
    	return Gleich(DirError,DirTemp);
    }
    
    public static boolean Java8()
    {
//    	if (sJavaVer==null)
//    		sJavaVer=System.getProperty("java.version");
    	return System.getProperty("java.version").startsWith("1.8");
    }
    
    public static void addJavaKonsole()
    {
    	StreamInfo=new TextAreaOutputStream(EdtInfoKonsole,10000,false);
    	StreamError=new TextAreaOutputStream(EdtErrorKonsole,10000,true);
    	PrintStream conInfo=new PrintStream(StreamInfo);
    	PrintStream conError=new PrintStream(StreamError);
    	System.setOut(conInfo);
    	System.setErr(conError);
    	//bKonsole=true;
    }
    
    public static Font getOutFont()
    {
    	return /*Static.bOpenJDK*/Static.fontOutliner!=null ? Static.fontOutliner:Static.fontStandard;
    }
    
//    public static Font getTabFont()
//    {
//    	return Static.fontTable!=null ? Static.fontTable:getOutFont();
//    }
    
    public static void setND(boolean b)
    {
    	bND=b;
    	if (b)
    	{
    		UIManager.put("Button.background",  Global.ColBtn);
//    		UIManager.put("Button.disabledText", Color.YELLOW);
    		UIManager.put("ToggleButton.background", Global.ColToolbar);  
    	    UIManager.put("ToggleButton.selected",  Global.ColTSelect);
    	    UIManager.put("Button.border", new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 2, 2, 2)));
    	    UIManager.put("ToggleButton.border", new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 2, 2, 2)));
    	    //ColWhite = Color.decode("#FFE4E1");
    	    bRahmen=false;
    	}
    	else
    	{
    		//ColWhite=Color.WHITE;
    		UIManager.put("Button.background",null);
    		UIManager.put("ToggleButton.background",  null);
    		UIManager.put("ToggleButton.selected",  null);
    	    UIManager.put("Button.border", null);
    	    UIManager.put("ToggleButton.border", null);
    	}
    	if (Static.DirImageOD != null)
    		Static.DirImageSys=bND ? Static.beiLeer(Static.DirImageND,Static.DirImageOD):Static.DirImageOD;
      // Transact.printStackTraceS(new Exception());
    }
    
    public static void setNI(boolean b)
    {
    	bNI=b;
    	if (b)
    		sBild="BildFX";
    	else
    		sBild="BildFile";		
    }
    
    public static Color ColEdt(boolean bModified,boolean bEditable)
    {
    	return bModified ? Static.ColChange:bEditable ? bND ? Static.ColWhite:Color.WHITE:Static.ColEF;
    }
    
    public static String getIcon(int iIA)
    {
    	String sIcon=null;
    	if (bND)
    		sIcon=iIA==IA_Top ? "Top_ND":iIA==IA_checked ? "cbx1":iIA==IA_unchecked ? "cbx0":iIA==IA_checked2 ? "cbx1d":iIA==IA_unchecked2 ? "cbx0d":"falsepass";
    	else
    		sIcon=iIA==IA_Top ? "mandant":iIA==IA_checked ? "ok":iIA==IA_unchecked ? "cancel":iIA==IA_checked2 ? "ok2":iIA==IA_unchecked2 ? "cancel2":"falsepass";
    	return "/images/"+sIcon+".png";
    }
    
    public static int IA_Top=1;
    public static int IA_checked=2;
    public static int IA_unchecked=3;
    public static int IA_checked2=4;
    public static int IA_unchecked2=5;

public static JTextArea EdtInfoKonsole = new JTextArea();
public static JTextArea EdtErrorKonsole = new JTextArea();
public static TextAreaOutputStream StreamInfo=null;
public static TextAreaOutputStream StreamError=null;
//public static boolean bKonsole=false;

public static final int NIE=0;
public static final int IMMER=1;
public static final int AUTO=-1;
public static int iWeb=AUTO;

//public static java.applet.Applet app;
public static JFrame FomStart=null;
//public static FormularFX FomStartFX=null;
//public static String ImageVerzeichnis = "@image@";
public static String HilfeVerzeichnis = "@hilfe@";
//public static String FehlerVerzeichnis = "@fehler@";
static String DirTemp=null;
//public static boolean bCachen=true;
public static String DirError=null;
public static String DirErrorWin=null;
public static String DirErrorMac=null;
public static String DirImageSys=null;
public static String DirImageOD=null; // Sys-Image für altes Design
public static String DirImageND=null; // Sys-Image für neues Design
public static String DirImageDef=null;
public static String DirImageStamm=null;
public static String DirImageFile=null;
public static String DirDoku=null;
public static String sWeb="";
//public static String DirCss=null;
//public static String DirCssDefault=null;
//public static String DirFxml=null;
//public static int iStyleTyp=1; // 1..modena, 2..caspian
public static String sIP="IPxxx";
public static String sUser=null;
public static String sDefaultStyle=null;
public static boolean bBilder = false;
public static boolean bImgM = false;
//public static boolean bNetscape = false;
public static boolean bSpeichernAnzeigen = false;
//public static boolean bSpeichernFrage = false;
public static boolean bFormatSortiert = false;
//public static boolean bH_dez=false;
//public static boolean bH_min=false;
//public static int iAicStunde=0;
//public static boolean bLeerLassen = true;
public static int iComboLeer=NIE;
public static int iCache=NIE;
public static int iAServer=NIE;
public static boolean bLocal=false;
public static boolean bSlow=false;
public static boolean bTestFenster = false;
public static boolean bTest=false;
public static boolean bAutoEdit = false;
public static final String sLeer= "";
public static final Integer Int0 = new Integer(0);
//public static final long lGMT = new DateWOD().getTimezoneOffset();
//public static String sJavaVer ="xxx";
//public static int iZeitart;
//public static String sZeitart="Tag"; // !!!
public static boolean bZR_Leiste=false;
//public static int iID=6162;
//public static String sESC = "ESC";
//public static JButton BtnBeenden=new JButton("Beenden");
//public static JButton BtnExport=new JButton("export");
//public static JButton BtnDruck=new JButton("druck");
public static JButton BtnAbbruch=new JButton("cancel");
//public static Dimension D=new Dimension(120,24);
public static String sJa = "J";
public static String sNein = "N";
public static String sKein = "<->";
public static String sKW = "KW";
public static String sCount="count";
public static String sValRequired="*";
public static String sValZuLang="zu lang";
public static String sValZuHoch="zu hoch";
public static String sInValid="ungültig";
public static final Date dt0=new DateWOD(2000,1,1).getTime();
public static boolean bSave=false;
public static boolean bMemo=true;
public static boolean bDefaultLook=true;
public static char cLook='+';
public static boolean bSQL=false;
public static long lAbfAb=0;
// public static boolean bComputerLock=true;
public static boolean bStern=false;
//public static boolean bLeiste=true;
public static char cURL='+';
public static boolean bWindows=true;
public static boolean bLinux=false;
private static ActionListener actESC=null;
public static boolean bDefBezeichnung=false;
public static boolean bShowSize=false;
public static boolean bStdSize=false;
//public static boolean bOriginal=false;
public static boolean bDefShow=false;
public static boolean bZeitzone=false;
public static boolean bInfoBild=false;
public static boolean bInfoLeer=false;
public static boolean bInfoTT=false;
public static boolean bInfoExcept=false;
public static boolean bInfoWin=false; // zeigt setzen und holen der Fensterpos an
public static boolean bInfoTod=false; // zeigt zugriff auf Tode Begriffe an
public static boolean bInfoSQL=false;
public static boolean bInfoEMail=false;
public static boolean bHtmlDruck=false;
//public static boolean bLDATE=true;
public static String sAbfrageErsatz=null;
public static String sImportModell=null;
//public static boolean bMac=true;
//public static boolean bHost=false;
//public static boolean bHIC=false;
public static char cMacArt='+';
public static boolean bX11=true;
//public static boolean bAR=false;
public static boolean bRefreshStop=false;
//public static boolean bView2=false;
public static boolean bVerlauf=true;
//public static boolean bDefVerlauf=true;
public static boolean bInsert2=false;
public static boolean bCheckZR=false;
public static int iESleep=5; // sleep zwischen E-Mails
public static int iETimer=60; // Timer in s für 2FA
// public static int iTT=120; // Token-Time default 2 h
// public static int iLTT=14; // Long-Token-Time default 14 Tage
//public static int iSBshow=3; // Snackbar-Anzeige [s]
public static int iBenML=6; // Benutzerkennung-Mindestlänge
public static int iPWM=8020; // PW-Mindestlänge, Großb., Ziffern, Sonderzeichen
//public static int iPWMZ=2; // PW-Mindestziffern
//public static int iPWMS=0; // PW-Mindestsonderzeichen
public static int iPWMM=0; // PW-Gültigkeit in Monate
public static boolean bNurJeder=false;
public static boolean bJBuilder=false;
private static int iError=0;
//private static String sError=null;
private static Vector<String> VecError=null;
public static boolean bMandantensuche=false;
public static boolean bShowDB=false;
//public static boolean bShowStyle=false;
//public static boolean bStringX=true;
public static boolean bProtFehler=true;
public static boolean bTranslate=false;
public static boolean bRahmen=false;
public static boolean bNurStart=false;
public static boolean bOhneProt=false;
public static boolean bLizenz=true;
public static boolean bNotfall=false;
public static boolean bLtHtml=false;
public static boolean bAzure=false;
//public static boolean bJavaFX=false;
//public static boolean bAlert=true;
//public static boolean bCenterStage=false;
//public static boolean bBorderPane=true;
//public static boolean bFixTest=true;
//public static boolean bMaterialFX=true;
public static String sLand=null;
public static String CP="UTF-8";// "Cp1252";//=ANSI, ASCII="Cp850";
public static String sFont="Arial,sans-serif; font-size:9px; color:black";
public static String sFontMessage="Arial,sans-serif; font-size:15px; color:darkblue";
public static Color ColChange = Color.cyan;
public static Color ColWhite = Color.WHITE;
public static Color ColMemo = Color.YELLOW;
public static Color ColLinie = Color.BLACK;
private static int iMaxError=-1;
public static int iAutoAus=0;
public static int iDelSK=1; // sehr kurz behalten
public static int iDelK=7;  // Kurzes behalten
public static int iDel=30;  // gelöschtes reproduzierbares nach 2 Wochen löschen
public static int iDelL=366;// Langes behalten (z.B. für Lohn)
public static int iDel2=0; // gelöschtes nach x Jahren löschen (0..nicht löschen)
public static int iMaxDel=100;

public static int iMemoMax=Version.V18() ? 64000:30000; // V 5.11: 4000

public static int iMaxX=1020;
public static int iMaxY=768;
public static int iMinX=0;
public static int iMinY=0;

public static boolean bSpWho=false;
public static boolean bFuDate=false;

//public static String cssShowGrid=null;
public static String sDefaultPW="start";

  public static long lLast=0;
  public static Color ColEF = null;
  public static java.awt.Font fontStandard=null;
  public static java.awt.Font fontOutliner=null;
  //public static java.awt.Font fontTable=null;
  public static char cOOM='-';
  public static Point P0=new Point();
  private static Toolkit TK=null;
  public static long lStart=System.currentTimeMillis();//System.nanoTime();
  
//  public static boolean bUseDefaultCss=true;
  public static boolean bBeep=false;
  public static boolean bMySQL=false;
  public static int iProg=0;
  
//  private static String sJavaVer=null;
  public static boolean bAutoKonsole=true;
  public static boolean bOpenJDK=false;
  public static boolean bWeb=false;// ob neuer Web-Zugriff über RestFul
  public static boolean bAll=true; // ob über All und somit Debug-Dialog möglich
  public static boolean bX=true; // ob x statt Ja/Nein
  public static boolean bND=false; // neues Design
  public static boolean bDS=false; // Design gesetzt;
  public static boolean bNI=false; // neue Images (aus JavaFX)
  public static boolean bMojave=false;
  public static boolean bLNC=false; // Log-Name Casesensitiv
  public static boolean bInternerTimer=true;
  public static String sBild="BildFile";
  private static Tabellenspeicher TabMonitor=null;
  public static boolean bKeineEinheit=false;
  public static boolean bKeinLDate=false;
  public static Vector<String> VecErrorAll=null;
  
  public static Date dt1970=new DateWOD(1970,1,1).toDate();
  public static Date dt1970old=new Date(0);
}

