/*
    All_Unlimited-Hauptmaske-OLEopen.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
//import java.io.BufferedInputStream;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;

import jclass.bwt.BWTEnum;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;

public class OLEopen extends java.lang.Object
{
    public OLEopen(Global rg)//,String sDot)
	{
		g=rg;
		//if(g.Debug())
		{
			//TabSpalten.showGrid();
			//TabDaten.showGrid();
		}

		try
		{
			/*File fileWord=new File("WordInp.txt");
			int iCount=0;
			while(fileWord.exists()&&iCount<50)
			{
				iCount++;
				try{Thread.sleep(200);}catch(Exception e){}
			}//*/
			wordInput = new File("C:\\WordInp.txt");
			wordInputWriter = new FileWriter(wordInput);
			//output(sDot);
			bFileCanRead=true;
			//OLEshowNMBookmarks(g.Debug());
		}catch(IOException ioe)
		{
			bFileCanRead=false;
			Static.printError("OLEopen:"+ioe.toString());

		}

		//this.sDot=sDot;



	}

	public void openDot(String sDot)
	{
		output("[OPENDOT]");
		output(sDot);
	}

	public void OLEtable(String sTextmarke, Tabellenspeicher TabSpalten, Tabellenspeicher TabDaten,long lAbfrageBits)
	{
		output("[TABLE]");
		output(sTextmarke);
		int iSpalten=0;
		boolean bKeinTitel=(lAbfrageBits&Abfrage.cstKeinTitel)>0;
		int iAbfrage=TabSpalten.getI("Abfrage");
		for(TabSpalten.posInhalt("Abfrage",iAbfrage);!TabSpalten.eof()&&TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
			iSpalten++;
		output(""+iSpalten);
		output(""+(TabDaten.getAnzahlElemente(null)+(bKeinTitel?0:1)));
		output(""+bKeinTitel);
		try
		{
			String s="";
			TabSpalten.posInhalt("Abfrage",iAbfrage);
			s=""+((TabSpalten.getI("bits")&Abfrage.cstSteuern)>0);
			for(TabSpalten.moveNext();!TabSpalten.eof()&&TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
			{
				s+=";"+((TabSpalten.getI("bits")&Abfrage.cstSteuern)>0);
			}
			output(s);
			TabSpalten.posInhalt("Abfrage",iAbfrage);
			if(!bKeinTitel)
			{
				TabSpalten.posInhalt("Abfrage",iAbfrage);
				s=TabSpalten.getS("bezeichnung");
				for(TabSpalten.moveNext();!TabSpalten.eof()&&TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
					s+="^"+TabSpalten.getS("bezeichnung");
				output(s);
			}
			TabSpalten.posInhalt("Abfrage",iAbfrage);
			wordInput=null;
			wordInputWriter.close();
			TabDaten.CalcExport(false,true,"^","C:\\WordInp.txt",TabSpalten,true,false,false);
			wordInput = new File("WordInp.txt");
			wordInputWriter = new FileWriter("C:\\WordInp.txt",true);



			TabSpalten.posInhalt("Abfrage",iAbfrage);
			boolean bSumme=((TabSpalten.getI("bits")&Abfrage.cstSumme)>0);
			String sSumme=""+((TabSpalten.getI("bits")&Abfrage.cstSumme)>0)+"["+convertFormat(TabSpalten.getS("format"))+"]";
			//boolean bSumme=(TabSpalten.getI("bits")&Abfrage.cstSumme)>0;
			s=""+getAlign(TabSpalten.getS("Datentyp"));
			//sFormat+=((TabSpalten.getI("bits")&Abfrage.cstSumme)>0)?convertFormat(TabSpalten.getS("format")):"";
			for(TabSpalten.moveNext();!TabSpalten.eof()&&TabSpalten.getI("Abfrage")==iAbfrage;TabSpalten.moveNext())
			{

				s+=";"+getAlign(TabSpalten.getS("Datentyp"));
				sSumme+=";"+((TabSpalten.getI("bits")&Abfrage.cstSumme)>0)+"["+convertFormat(TabSpalten.getS("format"))+"]";
				//sFormat+=((TabSpalten.getI("bits")&Abfrage.cstSumme)>0)?";"+convertFormat(TabSpalten.getS("format")):"";
				bSumme=bSumme||((TabSpalten.getI("bits")&Abfrage.cstSumme)>0);


			}
			output(""+bSumme);
			if(bSumme)
				output(sSumme);


			output(s);
		}catch(IOException ioe)
		{
			Static.printError("OLEtable: "+ioe);
		}

	}

	private String getAlign(String sDatentyp)
	{
		int i= g.getAlignment(sDatentyp,0);
		return i==BWTEnum.TOPLEFT || i==BWTEnum.MIDDLELEFT ?"LEFT":i==BWTEnum.TOPRIGHT || i==BWTEnum.MIDDLERIGHT ? "RIGHT":"CENTER";
	}

	public void OLEoneRow(Tabellenspeicher TabSpalten,Tabellenspeicher TabDaten)
	{
		this.TabDaten=TabDaten;
		this.TabSpalten=TabSpalten;
		OLEwrite(false);
	}

	public void OLEallRow(Tabellenspeicher TabSpalten,Tabellenspeicher TabDaten)
	{
		this.TabDaten=TabDaten;
		this.TabSpalten=TabSpalten;
		OLEwrite(true);
	}



	public void OLEprint()
	{
		OLEprint(false,1);
		//output("[PRINT]");
		//output("[QUIT]");
		//output("[CLOSE]");
		//closeWordInput();
		//exec();
	}

	public void OLEprint(boolean bCopy,int iCopies)
	{
		output("[PRINT]");
		output(""+iCopies);
		output(""+bCopy);
		//output("[QUIT]");
		//output("[CLOSE]");
		//closeWordInput();
		//exec();
	}

	public void OLEsave(String sDocPath)
	{
		output("[SAVEFILE]");
		output(sDocPath);
		//output("[QUIT]");
		//output("[CLOSE]");
		//closeWordInput();
		//exec();
	}

	public void OLEshowNMBookmarks(boolean bShow)
	{
		output("[SHOWERRORS"+(bShow?"ON]":"OFF]"));
	}

	private void OLEwrite(boolean bAlle)
	{
		int iAbfrage=TabSpalten.getI("Abfrage");

		output("[TEXT]");
		//output(""+TabSpalten.getahlElemente(null));
		TabDaten.push();
		if(bAlle)
			TabDaten.moveFirst();//AnzahlElemente(null));
		//output(""+TabDaten.getAnz
		for(;!TabDaten.eof();TabDaten.moveNext())
		{
			TabSpalten.posInhalt("Abfrage",iAbfrage);
			//output("@createNewDocumentFromTemplate",sDot);
			while(!TabSpalten.eof()&&TabSpalten.getI("Abfrage")==iAbfrage)
			{
				output(TabSpalten.getS("bezeichnung"));
				String sText=""+TabDaten.TabWertToObj(TabSpalten,false);
				sText=Static.replaceString(sText,"\n","\r\n");
				output(sText);
				output("*endString*");
				TabSpalten.moveNext();
			}
			if(!bAlle)
				break;
		}
		output("[TEXTEND]");
		TabDaten.pop();
	}

	public void OLEquit()
	{
		output("[QUIT]");
	}

	public void OLEclose()
	{
		output("[CLOSE]");
		closeWordInput();
		exec();
	}

	private void closeWordInput()
	{
		try
		{
			wordInput=null;
			wordInputWriter.close();
		}catch (Exception e)
		{
			Static.printError( "OLEopen.closeWordInput:" + e );
		}

	}

	private void output(String key)
	{
		String record= key;
		if (wordInputWriter == null)
		{
			if (!bFileCanRead)
				return;
		}
		try
		{
			wordInputWriter.write(record + "\r\n");
			wordInputWriter.flush();
		}
		catch ( IOException e2 )
		{
			Static.printError( "OLEopen.output:" + e2 );
		}
  	}

	private boolean exec()
	{
    	//closeWordInput();ImageIcon
    	try
		{
			Thread.sleep(1000);
		}catch(Exception e)
		{
			Static.printError("OLEopen.exec().Thread.sleep():"+e);
		}
		String cmd;
    	cmd = "OLEbridge.exe";
    	try
		{


			//boolean bRunning=true;
			//Process p=
				Runtime.getRuntime().exec(cmd);
			/*while(bRunning)
			{
				try
				{
					int iExit=p.exitValue();
					bRunning=false;
				}catch(Exception e){}
			}*/

    	}
    	catch (Exception e)
		{
      		if(new File("WordInp.txt").exists())
				new File("WordInp.txt").delete();
			e.printStackTrace();
      		Static.printError(cmd + " kann nicht ausgeführt werden.");
      		Static.printError("Bitte prüfen ob OLEbridge.exe in java\bin verzeichnis liegt");
      		return false;
    	}
    	return true;
  	}

	private String convertFormat(String sFormat)
	{
		sFormat.replace(',','|');
		sFormat.replace('.',',');
		sFormat.replace('|','.');
		return sFormat;
	}


	/*private void getResourceStreamBytePerByte()
	{
		Class clazz = getClass();
		InputStream is = null;
		try
		{
			is = clazz.getResourceAsStream("/resource/OLEbridge.exe");
			FileOutputStream fos=new FileOutputStream("C:\\ob.exe");
			int iByte=0;
			while((iByte=is.read())!=-1)
				fos.write(iByte);
			fos.close();
			is.close();
		}catch(Exception e)
		{
			System.out.println("====> Fehler beim Erstellen von ob.exe");
		}

	}*/



	/*private void getResourceStream()
 	{
   		//String resname = "/" + pkgname.replace('.', '/') + "/" + fname;
   		Class clazz = getClass();
		InputStream isd = null;

		try
		{


			isd = clazz.getResourceAsStream("/resource/OLEbridge.exe");
			BufferedInputStream is=new BufferedInputStream(isd);
			long lAvailable=is.available();
			System.out.println("==> iAvailable:"+lAvailable);
			FileOutputStream fos=new FileOutputStream("C:\\ob.exe");
			byte []  bytes = new byte[200];
			int iRead=200;
			if(lAvailable<200)
			{
				iRead=(int)lAvailable;
				bytes=new byte[iRead];
			}



			int iCount=0;
			while(is.read(bytes,0,iRead)!=-1&&lAvailable!=0)
			{
				//sText=sText+new String(bytes);
				//encodeText(bytes,iRead);
				fos.write(bytes);
				lAvailable=is.available();

				if(iCount==100)
				{
					System.out.println("Available Bytes: "+lAvailable);
					iCount=0;
				}
				if(lAvailable<200)
					iRead=(int)lAvailable;
				bytes =new byte[iRead];
				iCount++;

			}

			fos.close();

			is.close();
		}catch(Exception e)
		{
			System.out.println("====> Fehler beim Erstellen von ob.exe");
		}

	}*/




	private Tabellenspeicher TabDaten=null;
	private Tabellenspeicher TabSpalten=null;
	//private String sDot="";
	private File wordInput;
  	private FileWriter wordInputWriter;
	private boolean bFileCanRead;
	//private boolean bFirstOpen;
	private Global g;





	// add your data members here
}

