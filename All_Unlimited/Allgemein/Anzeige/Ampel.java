package All_Unlimited.Allgemein.Anzeige;

import java.awt.Image;

import javax.swing.ImageIcon;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
//import javax.swing.ImageIcon;

public class Ampel
{
  public static final int cstOff=0;
  public static final int cstGreen=1;
  public static final int cstYellow=2;
  public static final int cstRed=3;

  private static Image[] Img= {null,null,null,null};
  //private static javafx.scene.image.Image[] ImgFX= {null,null,null,null};
  private static int[] iAic = {0,0,0,0};
  private static String[] sBez = {"o","g","y","r"};

  private static Image getImage(Global g,String s)
  {
	  Image Img=g.getImage(s);
	  if (g.Mal2() && Img != null)
	  {
		  ImageIcon ImI = new ImageIcon(Img);
		  Img=Img.getScaledInstance(Math.round(ImI.getIconWidth()*2),Math.round(ImI.getIconHeight()*2),Image.SCALE_AREA_AVERAGING);
		  g.fixtestError("Ampel "+s+" *2 -> "+(ImI.getIconWidth()*2)+"x"+(ImI.getIconHeight()*2));
	  }
	  return Img;
  }
  
  public static void refresh(Global g)
  {
	   if (Static.bND)
		   for(int i=0;i<4;i++)
			   Img[i]=getImage(g,i+"_Ampel.png");
	   else
	   {
	      Img[cstOff]=getImage(g,"Ampel-grau.png");
	      Img[cstRed]=getImage(g,"Ampel-rot.png");
	      Img[cstYellow]=getImage(g,"Ampel-gelb.png");
	      Img[cstGreen]=getImage(g,"Ampel-gruen.png");
	   }
  }
  
  public static void check(Global g)
  {
    if (Img[cstOff]==null)
    {
      refresh(g);
      
      String sSQL="select v.aic_stamm,v.kennung,v.bezeichnung from stammview2 v join stammtyp s on v.aic_stammtyp=s.aic_stammtyp and s.kennung='Status'";
      Tabellenspeicher Tab=new Tabellenspeicher(g,sSQL,true,"Ampel");
      if (Tab.isEmpty())
        Static.printError("Ampeln nicht gefunden");
      for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
      {
        String s=Tab.getS("Kennung");
        int i=5;
        if (s.equals("OFF"))
          i=cstOff;
        else if (s.equals("GREEN"))
          i=cstGreen;
        else if (s.equals("YELLOW"))
          i=cstYellow;
        else if (s.equals("RED"))
          i=cstRed;
        if (i<4)
        {
          iAic[i]=Tab.getI("aic_stamm");
          sBez[i]=Tab.getS("bezeichnung");
//          g.testInfo(" !!! Ampel !!! "+i+":"+iAic[i]+"/"+sBez[i]+"/"+Img[i]);
        }
      }
    }
  }
  
  public static void checkFX()
  {
//	  if (ImgFX[cstOff]==null)
//	  {
//		  ImgFX[cstOff]=new javafx.scene.image.Image(/*getClass().getClassLoader().getResourceAsStream(*/"/images/Ampel-grau.png");
//		  ImgFX[cstRed]=new javafx.scene.image.Image("/images/Ampel-rot.png");
//		  ImgFX[cstYellow]=new javafx.scene.image.Image("/images/Ampel-gelb.png");
//		  ImgFX[cstGreen]=new javafx.scene.image.Image("/images/Ampel-gruen.png");
//	  }
  }

  public static Image set(int iStatus)
  {
    //check(g);
    if (iStatus>=cstOff && iStatus<=cstRed)
      return Img[iStatus];
    else
    {
      Static.printError("Ampel: Status "+iStatus+" nicht möglich");
      return null;
    }
  }

  public static Image setStamm(int iStamm)
  {
    //check(g);
    if (iStamm==iAic[cstRed])
      return Img[cstRed];
    else if (iStamm==iAic[cstYellow])
      return Img[cstYellow];
    else if (iStamm==iAic[cstGreen])
      return Img[cstGreen];
    else if (iStamm==iAic[cstOff] || iStamm==0)
      return Img[cstOff];
    else
    {
      Static.printError("Ampel: setStamm "+iStamm+" nicht möglich");
      return null;
    }
  }
  
//  public static javafx.scene.image.Image setStammFX(int iStamm)
//  {
//	  checkFX();
//	  if (iStamm==iAic[cstRed])
//	      return ImgFX[cstRed];
//	    else if (iStamm==iAic[cstYellow])
//	      return ImgFX[cstYellow];
//	    else if (iStamm==iAic[cstGreen])
//	      return ImgFX[cstGreen];
//	    else if (iStamm==iAic[cstOff] || iStamm==0)
//	      return ImgFX[cstOff];
//	    else
//	    {
//	      Static.printError("Ampel: setStammFX "+iStamm+" nicht möglich");
//	      return null;
//	    }
//  }

  public static boolean isAmpel(int iStamm)
  {
    for(int i=0;i<4;i++)
      if (iStamm==iAic[i])
        return true;
    return false;
  }

  public static int getNr(int iStamm)
  {
	  for(int i=0;i<4;i++)
	      if (iStamm==iAic[i])
	        return i;
	    return 0;
  }

  public static int getStamm(Image rImg)
  {
    if (rImg==null)
      return 0;
    for(int i=0;i<4;i++)
      if (Img[i]==rImg)
        return iAic[i];
    return -1;
  }

  public static String getBezeichnung(Image rImg)
  {
    //System.out.println("getBezeichnung von "+rImg);
    if (rImg==null)
      return "";
    for(int i=0;i<4;i++)
      if (Img[i]==rImg)
        return sBez[i];
    return "Error";
  }

}
