/*
    All_Unlimited-Allgemein-Eingabe-BildEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
//import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
//import javax.swing.JFrame;

import All_Unlimited.Allgemein.Bild;
import All_Unlimited.Allgemein.FTP;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SimpleFileFilter;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.SQL;
//import javax.swing.ImageIcon;
import java.awt.Window;

public class BildEingabe extends javax.swing.JButton
{

/**
	 *
	 */
	private static final long serialVersionUID = 3561535609107185552L;
public BildEingabe(Window w,Global glob)
{
	VecPic = new Vector<Image>();
	VecText = new Vector<String>();
	VecPic.addElement(null);
	VecText.addElement("");
        Build(VecPic, VecText, new Integer(0), glob);
}

/*private void setMandant(int iStamm,Global rg)
{
        sMandant= iStamm==-1 ? "":SQL.getString(rg,"select mandant.kennung from mandant join stamm where aic_stamm="+iStamm)+"\\";
}*/

/*public BildEingabe(Vector Vec, JFrame frame,Global glob)
{
	Build(Vec, Vec, null, frame, glob);
}

public BildEingabe(Vector rVecPic,Vector rVecText, int iElement, JFrame frame, Global glob)
{
	Build(rVecPic, rVecText, new Integer(iElement), frame, glob);
}

public BildEingabe(Image Img, String sText, JFrame frame, Global glob)
{
	VecPic = new Vector();
	VecText = new Vector();
	VecPic.addElement(Img);
	VecText.addElement(sText);
	Build(VecPic, VecText, new Integer(0), frame, glob);
}*/

private void Build(Vector<Image> VecP, Vector<String> VecT, Integer iE, Global glob)
{
	g=glob;
	//FrmParent = frame;
	VecPic = VecP;
	VecText = VecT;
	iElem = iE;
	VecPicOld = new Vector<Image>(VecPic);
	VecTextOld = new Vector<String>(VecText);

	setMargin(new Insets(0,0,0,0));
    setBackground(Global.ColBild);
	showImageOnButton();
}

public void showImageOnButton()
{
  //if (bDaten)
  //  g.progInfo("showImageOnButton:"+getFilename());
  //else
  //{
	Image Img = getImage();
	if(Img!=null)
	{
          //g.progInfo("showImageOnButton: not null");
		setText("");
		setIcon(new ImageIcon(iScaleX>=0&&iScaleY>=0?shrink(Img,iScaleX,iScaleY,Image.SCALE_SMOOTH):Img));
		//setBackground(Color.YELLOW);
		g.setTooltip(getFilename(),this);
	}
	else
	{
		/*g.getBegriff("Button","...");
		if (g.TabBegriffe.getS("BildFile").equals(""))
		{
			setIcon(null);
			setText(g.TabBegriffe.getS("Bezeichnung"));
                        //g.progInfo("showImageOnButton: Bezeichnung="+g.TabBegriffe.getS("Bezeichnung"));
		}
		else*/
		if (ImgStd==null)
                  ImgStd=new ImageIcon(getClass().getResource("/images/"+(Static.bND ? "add_photo_ND.png":"Bildauswahl.gif")));
                  setText(ImgStd==null?"*":"");
                  setIcon(ImgStd);
                  g.setTooltip(null,this);
                        //g.progInfo("showImageOnButton: BildFile="+g.TabBegriffe.getS("BildFile"));
	}
  //}
}

public void LadeBild()
{	
  //iScaleX=getWidth();
  //iScaleY=getHeight();
  Static.bRefreshStop=true;
  boolean bVorschau=true;
  String sFile=getFilename();
  g.fixtestError("LadeBild: D/F/DB="+bDaten+"/"+bFTP+"/"+bDB+"/"+sFile);
      if (bDaten)
        if (bFTP || bDB ? !sFile.equals(""):Static.bImgM)
          sDir=Static.DirImageStamm;
        else
        {
//        	g.fixtestError("vor bVorschau");
          bVorschau=false;
          if(FC==null)
          {
//        	  g.fixtestError("erzeuge JFileChooser");
            FC = new JFileChooser();
            FC.setMultiSelectionEnabled(false);
            FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {"GIF","JPG","JPEG","PNG"},"Bilder (*.gif,*.jpg,*.png)"));
            //for(g.TabDateitypen.moveFirst();!g.TabDateitypen.eof();g.TabDateitypen.moveNext())
            //  FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {g.TabDateitypen.getS("ext")},g.TabDateitypen.getS("Bezeichnung")+" (*."+g.TabDateitypen.getS("ext")+")"));
            //g.progInfo("setCurrentDirectory:"+Static.ImageVerzeichnis+(Static.bImgM ? sMandant+"\\":""));
            FC.setCurrentDirectory(new java.io.File((Static.DirImageStamm/*+(Static.bImgM ? sMandant+"\\":"")*/).substring(6)));
          }
          String sFS=File.separator; // bei MS: \\ sonst /
          if (!sFile.equals(""))
          {
//            g.fixtestInfo("setSelectedFile:"+sFile);
            FC.setSelectedFile(new java.io.File( (sFile.startsWith("file:"+sFS) ? sFile :
                                                  Static.DirImageStamm/* + "\\" +(Static.bImgM ? sMandant + "\\" : "")*/ + sFile).substring(6)));
          }
          if(JFileChooser.APPROVE_OPTION == FC.showDialog(null,g.getBegriffS("Button","Ok")))
          {
            String s=""+FC.getSelectedFile();
            if (bFTP)
              s=FTP.CopyTo(g,"FTP_Bild",s);
            else
              s=(s.startsWith(sFS)?"file:":"file:"+sFS)+s;
            //g.fixInfo("Bild-Filename="+s);
            VecText.setElementAt(s, iElem.intValue());
            VecPic.setElementAt(bFTP ? g.LoadImage(s,Static.DirImageStamm):g.LoadImage(s,sDir), iElem.intValue());
          }
        }
    //g.fixtestInfo("LadeBild "+bVorschau+"/"+sDir+"/"+VecText);
    if (bVorschau)
    {
      int iPos=/*bDB ? -1:*/g.TabFormulare.getPos("AIC", g.getBegriffAicS("Frame", "Bild"));
      if (iPos>=0)
      {
        Bild bild = (Bild)g.TabFormulare.getInhalt("Formular",iPos);
        bild.setValue(VecPic, VecText, iElem.intValue(), sDir,bDB);
        bild.show();
      }
      else
      {
        Bild bild = new Bild(VecPic, VecText, iElem.intValue(), /*FrmParent,*/ g, bDB ? null:sDir);
        /*if (!bDB)*/ g.putTabFormulare(bild.getBegriff(), 0, bild);
      }
    }
    showImageOnButton();
}

/*public void setValue(Vector Vec)
{
	VecPic = Vec;
	VecText = Vec;
	iElem = null;
        VecPicOld = new Vector(VecPic);
	VecTextOld = new Vector(VecText);
	showImageOnButton();
}

public void setValue(Vector rVecPic,Vector rVecText, int iElement)
{
	VecPic = rVecPic;
	VecText = rVecText;
	iElem = new Integer(iElement);
	VecPicOld = new Vector(VecPic);
	VecTextOld = new Vector(VecText);
	showImageOnButton();
}*/

public void setValue(int iPos,int iZustand)
{
	if (iPos<0)
		Delete();
	else
	{
		String s=g.TabBegriffe.getS(iPos,iZustand==Global.iSpFX ? "BildFX":iZustand==Global.iSpSw ? "BildFile":iZustand==Global.iZSel ? "BildSel":"Bildxx");
		setValue(g.LoadImage(s),s,null);
	}
}

public void setValue(Image Img, String sText)
{
        setValue(Img,sText,null);
}

public void setValue(Image Img, String sText,String rsMandant)
{
        bDaten=rsMandant != null;
        //if (bDaten)
        //  sMandant=rsMandant;
	VecPic = new Vector<Image>();
	VecText = new Vector<String>();
	VecPic.addElement(Img);
	VecText.addElement(sText);
	iElem = new Integer(0);
	VecPicOld = new Vector<Image>(VecPic);
	VecTextOld = new Vector<String>(VecText);
	showImageOnButton();
}

public void setValue(String sText)
{
        bDaten=true;
        //if (bDaten)
        //  sMandant=rsMandant;
        VecPic = new Vector<Image>();
        VecText = new Vector<String>();
        sDir=Static.DirImageStamm;
        bLoad=true;
        VecPic.addElement(null);
        VecText.addElement(sText);
        iElem = new Integer(0);
        VecPicOld = new Vector<Image>(VecPic);
        VecTextOld = new Vector<String>(VecText);
        showImageOnButton();
}

public void setAktValue(Image Img, String sText)
{
	iElem = new Integer(0);
	VecPicOld = new Vector<Image>();
	VecTextOld = new Vector<String>();
	VecPicOld.addElement(Img);
	VecTextOld.addElement(sText);
}

public Image getImage()
{
  //g.fixInfo("getImage:"+bLoad+"/"+bDB+"/"+getFilename());
  if (bLoad && getFilename()!=null)
  {
    if (isShowing())
    {
      if (bDB)
      {
        String s=getFilename();
        if (s.indexOf("_")>0)
        {
          int iDaten=Integer.parseInt(s.substring(0,s.indexOf("_")));
          VecPic.setElementAt(SQL.getImage(g,iDaten),0);
        }
        else if (!s.equals(""))
          Static.printError("getImage von DB fehlerhaft:"+s);
      }
      else
        VecPic.setElementAt(g.LoadImage(getFilename(), sDir), 0);
      bLoad=false;
    }
    else
      return null;
  }
  return iElem==null? VecPic.elementAt(1):VecPic.elementAt(iElem.intValue());
}

public String getFilename()
{
	return iElem==null? VecText.elementAt(0):VecText.elementAt(iElem.intValue());
}

public boolean isNull()
{
	return getFilename().equals("");
}

public boolean Modified()
{

	String s1 = iElem==null? VecTextOld.elementAt(0):VecTextOld.elementAt(iElem.intValue());
        //System.out.println("Modified:"+s1+"/"+getFilename());
        //g.testInfo("Bild.Modified:"+s1+"/"+getFilename()+"->"+Static.Ungleich(s1,getFilename()));
	return Static.Ungleich(s1,getFilename());
}

public void Reset()
{
  VecTextOld.setElementAt(getFilename(),iElem.intValue());
}

public void Reset2()
{
  String s1 = iElem==null? VecTextOld.elementAt(0):VecTextOld.elementAt(iElem.intValue());
  setValue(null,s1);
}

public void setScale(int x, int y)
{
	iScaleX=x;
	iScaleY=y;
	showImageOnButton();
}

public void activateEvent()
{
	addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			g.fixtestError("BildEingabe.action für Dir="+sDir);
                  if (bEdit)
                	  LadeBild();
		}
	});
}

public static Image shrink(Image rImg, int iMaxX,int iMaxY,int iScale)
{
	ImageIcon Img = new ImageIcon(rImg);
	float iWidth = Img.getIconWidth();
	float iHeight = Img.getIconHeight();

	if(iMaxX<iWidth)
	{
		iHeight=iHeight/iWidth*iMaxX;
		iWidth = iMaxX;
	}

	if(iMaxY<iHeight)
	{
		iWidth=iWidth/iHeight*iMaxY;
		iHeight = iMaxY;
	}

	return rImg.getScaledInstance(Math.round(iWidth),Math.round(iHeight),iScale);
}

public void Delete()
{
	Delete(false);
}

public void Delete(boolean rbDaten)
{
  if (rbDaten)
    bDaten=true;
  if (bEdit || rbDaten)
  {
    VecText.setElementAt("", iElem.intValue());
    VecPic.setElementAt(null, iElem.intValue());
    showImageOnButton();
  }
}

/*public void setDelete(JButton BtnD)
{
        BtnD.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Delete();
          }
        });
}*/

public void setDir(String rsDir)
{
  sDir=rsDir;
}

public void setEditable(boolean b)
{
  bEdit=b;
}

public void setFTP(boolean b)
{
  bFTP=b;
}

public void setDB(boolean b)
{
  bDB=b;
}

// add your data members here
private Global g;
private Vector<Image> VecPic;
private Vector<String> VecText;
private Vector<Image> VecPicOld;
private Vector<String> VecTextOld;
private Integer iElem;
//private JFrame FrmParent;
private int iScaleX = 64;
private int iScaleY = 18;
//private String sMandant="";
private String sDir=Static.DirImageSys;
private boolean bDaten=false;
private JFileChooser FC=null;
private boolean bEdit=true;
private boolean bFTP=false;
private boolean bDB=false;
public static ImageIcon ImgStd=null;
private boolean bLoad=false;
}

