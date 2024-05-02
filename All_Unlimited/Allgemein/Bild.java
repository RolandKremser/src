/*
    All_Unlimited-Allgemein-Bild.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import All_Unlimited.Allgemein.Anzeige.Zeit;

public class Bild extends All_Unlimited.Allgemein.Formular
{
    /*public Bild(Vector Vec,JFrame frame,Global glob)
    {
		super("Bild",frame,glob);
		g = glob;
		Build();

		VecPic=Vec;
		VecText=Vec;
		iPic=1;
		iText=0;

		if(!bHTTP)
		{
			fillOutliner();
			select(iText<VecText.size()?(String)VecText.elementAt(iText):"");
		}
		else
		{
			TxtBild.setText(iText<VecText.size()?(String)VecText.elementAt(iText):"");
			Img = g.LoadImage(TxtBild.getText());
			LblBild.setIcon(Img!=null?new ImageIcon(Img):null);
		}

		show();
	}*/


	@SuppressWarnings("unchecked")
	public Bild(Vector rVecPic,Vector rVecText,int iElement/*,JFrame frame*/,Global glob,String sDir)
	{
		super("Bild",glob.getFomLeer()/*frame*/,glob);
		g = glob;
                //g.testInfo("********************************* Erzeuge Bild "+sMandant+iElement);
                boolean bDB=sDir==null;
                if (bDB)
                  sDir="";
		Build(sDir);

		VecPic=rVecPic;
		VecText=rVecText;
		iPic=iElement;
		iText=iElement;

		if(!bHTTP)
		{

			select(iText<VecText.size()? VecText.elementAt(iText):"");
		}
		else
		{
			String sFile=iText<VecText.size()? VecText.elementAt(iText):"";
			TxtBild.setText(sFile);
			Img = bDB ? VecPic.elementAt(0):g.LoadImage(TxtBild.getText(),sVerzeichnis);
			ImageIcon II=Img!=null?new ImageIcon(Img):null;
			LblBild.setIcon(II);
			if (!Static.Leer(sFile) && II != null)
			{
//				g.fixtestError("Bild="+sFile);
				setTitle(sFile+" ("+II.getIconWidth()+"x"+II.getIconHeight()+")");
			}
		}
		//LblBild.setBackground(Color.gray);
		show();
 	}

	@SuppressWarnings("unchecked")
	public void setValue(Vector rVecPic, Vector rVecText, int iElement,String sDir,boolean bDB)
	{
          checkDir(sDir);
		VecPic=rVecPic;
		VecText=rVecText;
		iPic=iElement;
		iText=iElement;
                //g.fixtestInfo("Bild.setValue:"+bDB+"/"+bHTTP+"/"+VecText.elementAt(iText));
		if(!bHTTP && !bDB)
                {
                  //if (!sMandant.equals(rsMandant))
                  //  fillOutliner(rsMandant);
                  select(iText < VecText.size() ? VecText.elementAt(iText) : "");
                }
		else
		{
			String sFile=iText<VecText.size()? VecText.elementAt(iText):"";
			TxtBild.setText(sFile);
			Img = bDB ? VecPic.elementAt(0):g.LoadImage(TxtBild.getText(),sVerzeichnis);
			ImageIcon II=Img!=null?new ImageIcon(Img):null;
            LblBild.setIcon(II);
            if (!Static.Leer(sFile) && II != null)
			{
//				g.fixtestError("Bild="+sFile);
				setTitle(sFile+" ("+II.getIconWidth()+"x"+II.getIconHeight()+")");
			}
		}
	}

        private void checkDir(String sDir)
        {
          if (!sDir.equals(sDir2))
          {
        	  sVerzeichnis=sDir;
        	  sDir2 = sDir;
        	  if (sDir2.equals(Static.DirImageSys) && Static.DirImageFile!=null) 
        		  sDir2=Static.DirImageFile;
            int iIndex = sDir2.indexOf("file:");
            bHTTP = iIndex==-1;
            if(!bHTTP)
            {
              fillOutliner();
            }
          }
        }

	private void Build(final String sDir)
	{

		BtnOk = getButton("Ok");
		if(BtnOk==null) BtnOk = new JButton();
                BtnShow = getButton("show");
                if(BtnShow==null) BtnShow = new JButton();
		if(BtnOk==null) BtnOk = new JButton();
		BtnAbbruch = getButton("Abbruch");
		if(BtnAbbruch==null) BtnAbbruch = new JButton();

		JPanel Pnl_Outliner = getFrei("Outliner");
		JPanel Pnl_Bild = getFrei("Bild");
		JPanel Pnl_Textfeld = getFrei("Textfeld");
        String sDir2=sDir.equals(Static.DirImageSys) && Static.DirImageFile!=null ? Static.DirImageFile:sDir;
		
        bHTTP = sDir2.indexOf("file:")==-1;
		if(Pnl_Outliner!=null && !bHTTP)
		{
			Pnl_Outliner.setLayout(new BorderLayout(2,2));
			Pnl_Outliner.add("Center",OutBild);
		}
		if(Pnl_Bild!=null)
		{
			Pnl_Bild.setLayout(new BorderLayout(2,2));
                        JScrollPane PnlSP = new JScrollPane(LblBild);
                        /*Pnl_Bild.setBackground(g.ColBild);
                        PnlSP.setBackground(Color.GREEN);
                        LblBild.setBackground(Color.BLUE);
                        LblBild.setOpaque(true);*/
                        Pnl_Bild.add("Center",PnlSP);
		}
		if(Pnl_Textfeld!=null)
		{
			Pnl_Textfeld.setLayout(new BorderLayout(2,2));
                        Pnl_Textfeld.add("Center",TxtBild);
		}

		String[] s = new String[] {g.getBegriffS("Show","Filename"),g.getBegriffS("Show","Filegroesse"),g.getBegriffS("Show","Datum")};
		//String[] s = new String[] {"Filename","Filegroesse","Datum"};
		OutBild.setColumnButtons(s);
		OutBild.setNumColumns(s.length);
		OutBild.setColumnLabelSortMethod(Sort.sortMethod);
		OutBild.setRootVisible(false);
		LblBild.setBackground(Global.ColBild);
		LblBild.setOpaque(true);
		LblBild.setHorizontalAlignment(SwingConstants.CENTER);

                checkDir(sDir);
                ActionListener AL=new ActionListener()
                {
                  public void actionPerformed(ActionEvent ev)
                  {
                    String s = ev.getActionCommand();
                    if (s.equals("Show"))
                      Static.OpenURL(sDir);
                    else if (s.equals("Ok"))
                    {

                              while(iText>=VecText.size())
                                      VecText.addElement(null);

                              while(iPic>=VecPic.size())
                                      VecPic.addElement(null);

                              //VecText.setElementAt((String)((Vector)OutBild.getSelectedNode().getUserData()).elementAt(0),iText);
                              //VecPic.setElementAt((ImageIcon)LblBild.getIcon(),iPic);

                              VecText.setElementAt(TxtBild.getText(),iText);
                              VecPic.setElementAt(Img,iPic);

                              hide();
                      }
                    else if (s.equals("Abbruch"))
                      hide();
                  }
                };
                g.BtnAdd(BtnShow,"Show",AL);
		g.BtnAdd(BtnOk,"Ok",AL);
		g.BtnAdd(BtnAbbruch,"Abbruch",AL);


		OutBild.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED && !bChange)
				{
					bChange=true;
					JCOutlinerNode Node = OutBild.getSelectedNode();
					boolean bEnabled = Node!=null && Node.getLevel()==1;
					BtnOk.setEnabled(bEnabled);

					if(bEnabled)
					{
						String sFile=(String)((Vector)Node.getUserData()).elementAt(0);
						Img = g.LoadImage(sFile,sVerzeichnis);
						LblBild.setIcon(Img!=null?new ImageIcon(Img):null);
						TxtBild.setText(sFile);
					}
					else
					{
						LblBild.setIcon(null);
						TxtBild.setText("");
					}
					bChange=false;
				}
			}
		});

		TxtBild.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyTyped(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
				if(!bChange)
				{
					bChange=true;
					if(!bHTTP)
						select(TxtBild.getText());

					String sFile = TxtBild.getText();
					int i = sFile.indexOf(".");
					if(i!=-1 && sFile.length()>=i+4)
					{
						Img = g.LoadImage(sFile,sVerzeichnis);
						LblBild.setIcon(Img!=null?new ImageIcon(Img):null);
                                                BtnOk.setEnabled(Img!=null);
					}

					bChange=false;
				}
			}
		});

	}

	private void fillOutliner()
	{
          g.progInfo("************************************* Bild.fillOutliner");
          //sMandant=rsMandant;
          //String sVerz=sVerzeichnis;

          String sDir = sDir2.substring(sDir2.indexOf("file:") + 6);
          ((JCOutlinerFolderNode)OutBild.getRootNode()).removeChildren();
		Vector<Object> VecVisible = new Vector<Object>();
		Vector<String> VecInvisible = new Vector<String>();
		VecVisible.addElement(Static.sKein);
		VecVisible.addElement(null);
		VecVisible.addElement(null);
		VecInvisible.addElement("");

		JCOutlinerNode Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutBild.getRootNode());
		Node.setUserData(VecInvisible);

		String[] s = new File(sDir/*+(sMandant.equals("")?"":sMandant+"\\")*/).list();
		//String[] s = new File(Static.ImageVerzeichnis).list();
		//g.fixtestInfo("fill von Bild:"+sDir+"->"+s);
		if (s!=null)
		  for(int i=0;i<s.length;i++)
		  {
			File f = new File(sDir+s[i]);
			//File f = new File(Static.ImageVerzeichnis+s[i]);
                        String sFN=f.getName().toLowerCase();
			if(f.isFile() && (sFN.endsWith(".gif") || sFN.endsWith(".jpg") || sFN.endsWith(".png")))
			{
				VecVisible = new Vector<Object>();
				VecInvisible = new Vector<String>();
				VecVisible.addElement(f.getName());
				VecVisible.addElement(new Integer(""+f.length()));
				VecVisible.addElement(new Zeit(new Date(f.lastModified()),"dd/MM/yyyy HH:mm:ss"));
				VecInvisible.addElement(f.getName());

				Node = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutBild.getRootNode());
				Node.setUserData(VecInvisible);
			}
		  }
	}

	private void select(String sTitel)
	{
		Vector VecNodes = ((JCOutlinerFolderNode)OutBild.getRootNode()).getChildren();

		JCOutlinerNode Node = null;
		for(int i=0;Node==null && i<VecNodes.size();i++)
		{
			JCOutlinerNode NodeLook = (JCOutlinerNode)VecNodes.elementAt(i);
			Vector VecInvisible = (Vector)NodeLook.getUserData();
			if(((String)VecInvisible.elementAt(0)).equals(sTitel))
				Node = NodeLook;
		}

		if(Node!=null)
		{
			//OutBild.selectNode(Node,null);
			Static.makeVisible(OutBild,Node);
			//Img = Static.LoadImage((String)((Vector)Node.getUserData()).elementAt(0));
			//LblBild.setIcon(Img!=null?new ImageIcon(Img):null);
		}
		else
		{
			OutBild.selectNode(OutBild.getRootNode(),null);
			//LblBild.setIcon(null);
		}
	}
// add your data members here

private Global g;
private Vector<Image> VecPic;
private Vector<String> VecText;
private int iPic;
private int iText;

private JTextField TxtBild = new JTextField();
private AUOutliner OutBild = new AUOutliner(new JCOutlinerFolderNode(""));
private JLabel LblBild = new JLabel();
private JButton BtnOk;
private JButton BtnShow;
private JButton BtnAbbruch;
private Image Img = null;

private String sVerzeichnis="";
private String sDir2=null;
//private String sMandant="";
private boolean bHTTP=false;
private boolean bChange=false;
}

