/*
    All_Unlimited-Allgemein-Eingabe-FileEingabe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingConstants;


import All_Unlimited.Allgemein.FTP;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.SimpleFileFilter;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.SQL;

public class FileEingabe extends javax.swing.JPanel
{
/*public FileEingabe(String sFilename, Global glob)
{
	super(new BorderLayout(2,2));
	g=glob;
	Build();
	setValue(sFilename);
}*/

/**
	 *
	 */
	private static final long serialVersionUID = 6090617663302668321L;
public FileEingabe(Global glob)
{
	this(glob,true,true,0,true);
}

public FileEingabe(Global glob,boolean bFile)
{
        this(glob,bFile,true,0,true);
}

public FileEingabe(Global glob,boolean bFile,boolean bNeben,int iEig,boolean bEdit)
{
	super(new BorderLayout(2,2));
	g=glob;
	Build(bFile,bNeben,iEig,bEdit);
}


private void Build(boolean rbFile,boolean bNeben,int riEig,boolean bEdit)
{
	bNeben=true;
	iEig=riEig;
	if (g!=null && iEig>0)
	{
		Parameter Para=new Parameter(g,"Pfad");
		sPfad=Para.getParameter("Eig"+iEig, true, false);
		g.fixtestInfo("!!! FileEingabe.Eig="+iEig+" -> Pfad="+sPfad);		
	}	
	BtnFileDialog = g!=null?g.getButton("FD"):new JButton("...");
        //BtnFileDialog.setSize(5,5);
        bFile=rbFile;
        if (g!=null)
        {
          setFont(Transact.fontStandard);
        }
        JPanel PnlN=new JPanel(new BorderLayout());
        PnlN.add("North",TxtFile);
	add("Center",PnlN);
	bEditFix=bEdit;
	setEditable(bEdit);
	//g.fixtestError("FileEingabe: set Edit auf "+bEdit);
	if (Static.bStern && (g==null || !bFile ))
		add("East",BtnFileDialog);
        else if (Static.bStern)
	{
		JPanel Pnl=new JPanel(new GridLayout(bNeben?1:2,bNeben?2:1,bNeben?2:0,0));
                //TxtFile.setBorder(new javax.swing.border.EmptyBorder(1,1,5,1));
                BtnAufruf = g.getButton("FA");
                int i16=16*g.getFontFaktor()/100;
                if (bNeben || !bEdit)
                {
                  BtnAufruf.setPreferredSize(new Dimension(i16,i16));
                  BtnFileDialog.setPreferredSize(new Dimension(i16,i16));
                }
                else
                {
                  BtnAufruf.setPreferredSize(new Dimension(i16,i16/2));
                  BtnFileDialog.setPreferredSize(new Dimension(i16,i16/2));
                  BtnAufruf.setBorder(null);
                  BtnFileDialog.setBorder(null);
                }
                //BtnAufruf.setSize(5,5);
                //BtnFileDialog.setVerticalTextPosition(SwingConstants.BOTTOM);
                //BtnAufruf.setVerticalTextPosition(SwingConstants.BOTTOM);
		BtnAufruf.setMargin(g.inset0);
                BtnFileDialog.setMargin(g.inset0);
             if (bEdit)
                Pnl.add(BtnFileDialog);
		Pnl.add(BtnAufruf);
		BtnAufruf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
                PnlN=new JPanel(new BorderLayout());
                PnlN.add("North",Pnl);
		add("East",PnlN);
	}
		if (bEdit)
		{
          TxtFile.addKeyListener(new KeyListener()
          {
                public void keyPressed(KeyEvent e)
                {
                }
                public void keyReleased(KeyEvent e)
                {
                  CheckColor();
                }
                public void keyTyped(KeyEvent e)
                {
                  if(e.getKeyChar()=='*')
                  {
                    OpenList();
                    e.consume();
                  }
                  else if(e.getKeyChar()=='?')
                  {
                    openFile();
                    e.consume();
                  }
                }
          });
          TxtFile.setToolTipText("<html>* ... Fileauswahl<p>? ... Fileaufruf</html>");
          TxtFile.addMouseListener(new MouseListener()
	      {
	        public void mousePressed(MouseEvent ev)
	        {}

	        public void mouseClicked(MouseEvent ev)
	        {
	          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
	          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	          {
	            if (ev.getModifiersEx()==MouseEvent.SHIFT_DOWN_MASK)
	              openFile();
	            else
	              OpenList();
	          }
	        }

	        public void mouseEntered(MouseEvent ev)
	        {}

	        public void mouseExited(MouseEvent ev)
	        {}

	        public void mouseReleased(MouseEvent ev)
	        {}
	      });       

          BtnFileDialog.addActionListener(new ActionListener()
	        {
	                public void actionPerformed(ActionEvent e)
	                {
	                        OpenList();
	                }
	        });
		}
		else
			TxtFile.setEditable(false);
}

      private void CheckColor()
        {
          TxtFile.setBackground(Modified()? Static.ColChange:Color.WHITE);
        }


public void OpenList()
{
  createFileChooser();
  FC.setFileSelectionMode(bFile ? JFileChooser.FILES_ONLY:JFileChooser.DIRECTORIES_ONLY);
  if (!getValue().equals("") && !bDB)
    FC.setSelectedFile(new java.io.File(getValue()));
  else if (!Static.Leer(sPfad))
	FC.setCurrentDirectory(new java.io.File(sPfad));
  //g.fixtestError("Pfad: Soll="+sPfad+", Ist="+FC.getCurrentDirectory());
  int i=-1;
  try
  {
    i=FC.showDialog(null,g==null?"ok":g.getBegriffS("Button","Ok"));
  }
  catch(Exception e)
  {
    Static.printError("OpenList:"+e);
  }
  if(JFileChooser.APPROVE_OPTION == i)//(bSaveDialog?FC.showSaveDialog(null):FC.showOpenDialog(null)))
  {
    String s="" + FC.getSelectedFile();
    if (bFTP)
      s=FTP.CopyTo(g,"FTP_Doku",s);
    TxtFile.setText(s);
    String sPfadNeu=FC.getCurrentDirectory().toString();
    if (!Static.Gleich(sPfad, sPfadNeu) && iEig>0 && g!=null && g.getBenutzer()>0)
    {
    	Parameter Para=new Parameter(g,"Pfad");
    	Para.setParameter("Eig"+iEig, sPfadNeu, true, false);
    	sPfad=sPfadNeu;
    }
  }
  CheckColor();
}

/*
private void Open(String sFile)
{
	if (!sFile.equals("") && new File(sFile).exists())
		try
		{
			String sExt=sFile.substring(sFile.lastIndexOf(".")+1).toUpperCase();
			System.out.println("Erweiterung="+sExt);
			String sProg=sExt.equals("DOC") ? "WINWORD":sExt.equals("XLS") ? "EXCEL":sExt.equals("PPS") ? "POWERPNT":null;
			if (sProg != null)
				Runtime.getRuntime().exec("\"C:\\Programme\\Microsoft Office\\Office10\\"+sProg+".EXE\" \""+sFile+"\"");
		}catch(IOException io)
		{
			Static.printError("FileEingabe.Open: IOException - "+io);
		}
}
*/

public void openFile()
{
	long lClock=Static.get_ms();
  if (isNull())
  {
    g.fixtestInfo("keine Datei zum Öffnen");
    return;
  }
  String s=getValue();
  String sFile=s;
  if (bDB && s.indexOf("_")>0)
  {
    int iDaten=Integer.parseInt(s.substring(0,s.indexOf("_")));
    s=SQL.getDoku(g,iDaten);
    //Static.printError("FileEingabe.openFile: fehlt noch:"+s);
  }
  if (s==null)
  {
    g.fixtestInfo("Datei ["+sFile+"] nicht ermittelbar");
    return;
  }
  g.clockInfo2("Load File "+sFile,lClock);
  lClock=Static.get_ms();
  if (bFTP && Static.DirDoku.startsWith("http:"))
    Static.OpenURL(Static.DirDoku+s);
  else
    g.openFile((bFTP?Static.DirDoku:"")+s);
  g.clockInfo2("Open File "+sFile,lClock);
}

public int getDatenAic()
{
  String s=TxtFile.getText();
  if (bDB && !Modified() && !isNull())
    return Integer.parseInt(s.substring(0,s.indexOf("_")));
  else
    return 0;
}

public void setValue(String s)
{
	sOld = s;
	TxtFile.setText(s);
}

public void setText(String s)
{
        TxtFile.setText2(s);
}

public void setAktValue(String s)
{
	sOld = s;
}

public String getValue()
{
	return TxtFile.getText();
}

public boolean isNull()
      {
        String s=getValue();
        return s==null || s.equals("");
      }

public boolean Modified()
{
	return !TxtFile.getText().equals(sOld);
}

public void Reset2()
{
  TxtFile.setText(sOld);
}


/*public void SaveDialog(boolean bSave)
{
	bSaveDialog = bSave;
}*/

public void setFileFilter(String sExplanation,String [] sExtension)
{
	createFileChooser();
	FC.addChoosableFileFilter(new SimpleFileFilter(sExtension,sExplanation));
}

public void resetChoosableFileFilters()
{
	createFileChooser();
	FC.resetChoosableFileFilters();
}

public void setFileSelectionMode(int iMode)
{
	createFileChooser();
	FC.setFileSelectionMode(iMode);
}

public String getPath()
{
        String s=TxtFile.getText();
        s=s.substring(0,s.lastIndexOf(File.separator));
        return s;
}

private void createFileChooser()
{
	if(FC==null)
	{
		FC = new JFileChooser();
		FC.setMultiSelectionEnabled(false);
		if (g != null)
		{
                  if (bDB || bFTP)
                  {
                    FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {"PDF"}, "Portable Document Format (*.PDF)"));
                    FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {"DOC"}, "Word Document (*.DOC)"));
                  }
                  //else
                  //  FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {"EXE"},"Programm (*.EXE)"));
			/*for(g.TabDateitypen.moveFirst();!g.TabDateitypen.eof();g.TabDateitypen.moveNext())
				FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {g.TabDateitypen.getS("ext")},g.TabDateitypen.getS("Bezeichnung")+" (*."+g.TabDateitypen.getS("ext")+")"));*/
		}
                FC.setAcceptAllFileFilterUsed(true);
                //FC.addChoosableFileFilter(new SimpleFileFilter(new String[] {"*"},"Alle (*.*)"));
	}
}

public void setEnabled(boolean b)
{
	TxtFile.setEnabled(b);
	BtnFileDialog.setEnabled(b);
}

public boolean isEnabled()
{
	return TxtFile.isEnabled()&&BtnFileDialog.isEnabled();
}

public void setFont(Font font)
{
        if (BtnFileDialog !=null)
        {
          //if (g!=null)
          //  font=g.fontStandard;
          TxtFile.setFont(font);
          BtnFileDialog.setFont(font);
          if (BtnAufruf != null)
            BtnAufruf.setFont(font);
        }
}

public void setBackground(Color c)
{
  if (TxtFile !=null)
  {
    TxtFile.setBackground(c);
    if (Static.bStern)
      BtnAufruf.setBackground(c);
  }
}


public void setEditable(boolean b)
{
  TxtFile.setEditable(b && bEditFix);
  BtnFileDialog.setEnabled(b);
}

public void setFTP(boolean b)
{
  bFTP=b;
}

public void setDB(boolean b)
{
  bDB=b;
}

public JTextField getFileEditor()
{
	return TxtFile;
}

// add your data members here
private Global g;
private Text TxtFile = new Text("",100,Text.FILE);
private JButton BtnFileDialog;
private JButton BtnAufruf;
private String sOld = "";
//private boolean bSaveDialog = false;
private JFileChooser FC=null;
private boolean bFile;
private boolean bFTP=false;
private boolean bDB=false;
private String sPfad=null;
private int iEig=0;
private boolean bEditFix=true;
}

