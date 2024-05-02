package All_Unlimited.Allgemein.Eingabe;

import javax.swing.*;
import com.hexidec.ekit.EkitCore;
import All_Unlimited.Allgemein.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import com.hexidec.util.Translatrix;
import javax.swing.text.StyledEditorKit;
import java.util.Locale;
//import java.util.Hashtable;
//import java.awt.Toolkit;
import com.hexidec.ekit.component.JButtonNoFocus;
import com.hexidec.ekit.component.JToggleButtonNoFocus;
//import com.hexidec.ekit.action.CustomAction;
//import javax.swing.text.DefaultEditorKit;
import java.awt.FlowLayout;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.ImageView;
//import com.hexidec.ekit.action.StylesAction;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.12
 */
public class AUEkitCore extends JPanel
{
  private static final long serialVersionUID = -4958348301303764493L;
  private Global g;
  public JEditorPane Edt;
  private EkitCore ek=null;
  private String sOldText="";
  private static String sLeer=null;
  private JPopupMenu popupEK;
  private JPopupMenu popup;
  private ActionListener PopList;
  public JMenuItem MnuH;
  public JMenuItem MnuEdt;
  private JCheckBoxMenuItem CbxMB=null;
  private JCheckBoxMenuItem CbxTBM=null;
  private JCheckBoxMenuItem CbxTBF=null;
  private JCheckBoxMenuItem CbxTBS=null;
  //private JCheckBoxMenuItem CbxAU=null;
  private JPanel PnlN;
  //private JToolBar jToolBarMain;
  //private boolean bClose=false;
  private JDialog Dlg;
  private static String sBez;
  private static int iBegriff;
  private static int iFF;
  private static int iTBits;
  private boolean bEdit=false;
  private int iLaenge=Static.iMemoMax;

  public AUEkitCore(Global rg,Window Fom,int riBits)
  {
        Build(rg,Fom,riBits);
  }

  public void setMaxLength(int i)
  {
	  iLaenge=i;
  }

  private void Build(Global rg,Window Fom,int iBits)
  {
    g=rg;
    if (ek==null)
    {
      ek = new EkitCore(); //"",null,null,null,null,false,false,false,true,"de","DE",false,false,false,false);
      Translatrix.setBundleName("com.hexidec.ekit.LanguageResources");
      Locale baseLocale = g.getSprache() == 1 ? new Locale("de", "DE") : new Locale("en", "UK");
      Translatrix.setLocale(baseLocale);
      //setToolbar();
      if (sLeer == null)
      {
        ek.setDocumentText("");
        sLeer = ek.getDocumentText();
      }
      int iPos=g.getPosBegriff("Dialog","EkitCore-Memo");
      sBez=iPos<0 ? "EkitCore-Memo":g.getBegBez2(iPos);
      iBegriff=iPos<0 ? 0:g.TabBegriffe.getI(iPos,"Aic");
      iFF=g.getFontFaktor();
      iTBits=iPos<0 ? 0:g.getFensterBits(iBegriff)/2;
      if (iTBits==0) iTBits=4;
    }

    Dlg=Fom instanceof JFrame ? new JDialog((JFrame)Fom,sBez,true):new JDialog((JDialog)Fom,sBez,true);

    setLayout(new BorderLayout());
    PnlN=new JPanel(new GridLayout(0,1));
    //PnlN.setBackground(Static.ColEF);
    setBackground(Static.ColEF);
    Dlg.getContentPane().add("North",PnlN);
    ek.getTextPane().setFont(Transact.fontStandard);
    Dlg.getContentPane().add("Center",ek);
    JPanel PnlS = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
      JButton BtnOk = g.getButton("Ok");
      //BtnOk.setEnabled(bEdit);
      JButton BtnAbbruch = g.getButton("Abbruch");
      //Dlg.getRootPane().setDefaultButton(BtnOk);
      PnlS.add(BtnOk);
      PnlS.add(BtnAbbruch);
      /*BtnOk.addActionListener(new ActionListener()
      {
              public void actionPerformed(ActionEvent ev)
              {
                      Edt.setText(ek.getDocumentText());
                      bClose=true;
                      CheckColor();
                      iTBits=(CbxMB.isSelected()?1:0)+(CbxTBM.isSelected()?2:0)+(CbxTBF.isSelected()?4:0)+(CbxTBS.isSelected()?8:0)+(CbxAU.isSelected()?16:0);
                      if (iBegriff>0)
                        g.setFenster(Dlg, iBegriff,iTBits*2,0,iFF);
                      Dlg.dispose();
              }
      });
      BtnAbbruch.addActionListener(new ActionListener()
      {
              public void actionPerformed(ActionEvent ev)
              {
                bClose=true;
                Dlg.dispose();
              }
      });*/
    Dlg.getContentPane().add("South",PnlS);
    if (iBegriff>0)
      g.getFenster(Dlg,iBegriff,false,0,0,600,500,iFF);
    else
      Dlg.pack();

    Edt=new JEditorPane();
    //Edt.setText("");
    Edt.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    //Edt.setFont(g.fontStandard);
    Edt.setContentType("text/html");
    Edt.setEnabled(true);
    Edt.setEditorKit(new HTMLEditorKit() {
      private static final long serialVersionUID =-4958348301303764495L;
      @Override
      public ViewFactory getViewFactory() {
          return new HTMLFactory() {

              @Override
              public View create(Element elem) {
                  View view = super.create(elem);
                  if (view instanceof ImageView) {
                      ((ImageView) view).setLoadsSynchronously(true);
                  }
                  return view;
              }
          };
      }
    });

    Edt.addFocusListener(new FocusListener()
    {
          public void focusGained(FocusEvent fe)
          {
            // if (bClose)
            //   bClose=false;
            // else
            // {
            //   ek.setDocumentText(Edt.getText());
            //   Dlg.setVisible(true);
            // }
          }
          public void focusLost(FocusEvent fe)
          {
            g.fixtestError("Memo:"+Edt.getText()+"***");
          }
    });

    add("Center",Edt);

    popupEK= new JPopupMenu();
    popup= new JPopupMenu();
        PopList = new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();;
            g.progInfo("AUEkitCore-action:"+s);
            if (s.equals("Edit"))
              show2(null);
            else if (s.equals("cut"))
              ek.getTextPane().cut();
            else if (s.equals("copy"))
              ek.getTextPane().copy();
            else if (s.equals("paste"))
              ek.getTextPane().paste();
            else if (s.equals("selectAll"))
              ek.getTextPane().selectAll();
            else if (s.equals("Undo"))
              Reset2();
            else if (s.equals("Druck"))
            {
              g.fixtestInfo("..drucken");
              try {
                Edt.print();
              } catch (Exception ex) { System.err.println("Druckfehler: "+ex); }

              //PrintUtilities.printComponent(Edt, false);
            }
            else if (s.equals("Anzahl"))
            {
              int iAnz=getValue().length();
              new Message(Message.INFORMATION_MESSAGE, null, g).showDialog("Anzahl_Zeichen",new String[] {""+iAnz,""+(iLaenge-iAnz)});
              //g.fixInfo("Zeichen:"+Edt.getText().length());
            }
            else if (s.equals("Source"))
            	showSource();
            else if (s.equals("Refresh") || s.startsWith("ToolBar") || s.equals("MenuBar"))
              Refresh();
            else if (s.startsWith("Dlg"))
            {
              DlgClose(s.equals("DlgOk"));
              /*if (s.equals("DlgOk"))
              {
                Edt.setText(ek.getDocumentText());
                CheckColor();
                iTBits=(CbxMB.isSelected()?1:0)+(CbxTBM.isSelected()?2:0)+(CbxTBF.isSelected()?4:0)+(CbxTBS.isSelected()?8:0)+(CbxAU.isSelected()?16:0);
                if (iBegriff>0)
                  g.setFenster(Dlg, iBegriff,iTBits*2,0,iFF);
              }
              //bClose=true;
              Dlg.dispose();*/
            }

          }
        };
        g.BtnAdd(BtnOk,"DlgOk",PopList);
        g.BtnAdd(BtnAbbruch,"DlgAbbruch",PopList);
        StyledEditorKit.BoldAction actionFontBold        = new StyledEditorKit.BoldAction();
        JMenuItem jmiBold = /*g.addMenuItem("Fett",popup,null,actionFontBold);*/new JMenuItem(actionFontBold);
        jmiBold.setText(Translatrix.getTranslationString("FontBold"));
        jmiBold.setAccelerator(KeyStroke.getKeyStroke('B', java.awt.event.InputEvent.CTRL_DOWN_MASK, false));
      //if(showMenuIcons) { jmiBold.setIcon(getEkitIcon("Bold")); }
        popupEK.add(jmiBold);
        StyledEditorKit.ItalicAction actionFontItalic = new StyledEditorKit.ItalicAction();
        JMenuItem jmiItalic = /*g.addMenuItem("Kursiv",popup,null,actionFontItalic);*/new JMenuItem(actionFontItalic);
        jmiItalic.setText(Translatrix.getTranslationString("FontItalic"));
        jmiItalic.setAccelerator(KeyStroke.getKeyStroke('I', java.awt.event.InputEvent.CTRL_DOWN_MASK, false)); // java.awt.Event.CTRL_MASK
      //if(showMenuIcons) { jmiItalic.setIcon(getEkitIcon("Italic")); }
        popupEK.add(jmiItalic);
        popupEK.addSeparator();
        MnuEdt=g.addMenuItem("Edit",popup,null,PopList);
        popup.addSeparator();
        g.addMenuItem("cut",popup,null,PopList);
        g.addMenuItem("copy",popup,null,PopList);
        g.addMenuItem("paste",popup,null,PopList);
        g.addMenuItem("selectAll",popup,null,PopList);
        g.addMenuItem("Undo",popup,null,PopList);
        g.addMenuItem("Anzahl",popup,null,PopList);
        //popup.addSeparator();
        //if (g.SuperUser())
        //  CbxMB=g.addCbxItem("MenuBar",popupEK,(iTBits&1)>0,null,PopList);
        CbxTBM=g.addCbxItem("ToolBarMain",popupEK,(iTBits&2)>0,null,PopList);
        CbxTBF=g.addCbxItem("ToolBarFormat",popupEK,(iTBits&4)>0,null,PopList);
        if (g.Def())
        {
          CbxTBS = g.addCbxItem("ToolBarStyles", popupEK, (iTBits & 8) > 0, null, PopList);
          //CbxAU = g.addCbxItem("ToolBarAU", popupEK, (iTBits & 16) > 0, null, PopList);
        }
        g.addMenuItem("Refresh",popupEK,null,PopList);
        if (iTBits>0)
          Refresh();
        if (g!= null && g.History())// && (iBits&HISTORY)>0)
        {
          popup.addSeparator();
          MnuH = g.addMenuItem("History", popup,null,null);
          if (g.Def())
          {
            g.addMenuItem("Druck", popup, null, PopList);
            g.addMenuItem("Source", popup, null, PopList);
          }
        }
        //ek.keyReleased(CheckColor());
        //ek.add(popup);
        Edt.addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev)  { }

          public void mouseClicked(MouseEvent ev)
          {
            if (bEdit && ev.getModifiers() == MouseEvent.BUTTON1_MASK && ev.getClickCount() == 2)
              show2(null);
            else if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM))
              popup.show((Component)ev.getSource(), ev.getX(), ev.getY());

          }

            public void mouseEntered(MouseEvent ev) { }
            public void mouseExited(MouseEvent ev)  { }
            public void mouseReleased(MouseEvent ev) { }
        });
        Edt.addKeyListener(new KeyListener()
                {
                  public void keyPressed(KeyEvent e)
                  {
                    /*int iCode=e.getKeyCode();
                    int iMod=e.getModifiers();
                    if ((iCode==10 || iCode==27) && iMod==0)
                    {
                      e.consume();
                      show2(null);
                    }*/
                  }

                  public void keyReleased(KeyEvent e)
                  {
                    CheckColor();
                  }

                  public void keyTyped(KeyEvent e) {}
                });
        ek.getTextPane().addMouseListener(new MouseListener()
        {
          public void mousePressed(MouseEvent ev)  { }

          public void mouseClicked(MouseEvent ev)
          {
            //g.fixtestInfo("ek.mouseClicked:"+ev.getButton()+"/"+ev.getModifiersEx());
            if ((ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM))
              popupEK.show((Component)ev.getSource(), ev.getX(), ev.getY());
          }

          public void mouseEntered(MouseEvent ev) { }
          public void mouseExited(MouseEvent ev)  { }
          public void mouseReleased(MouseEvent ev) { }
        });
        ek.getTextPane().addKeyListener(new KeyListener()
                {
                  public void keyPressed(KeyEvent e)
                  {
                    int iCode=e.getKeyCode();
                    String s=KeyEvent.getKeyText(iCode);
                    if (iCode==13)
                      e.setKeyCode(10);
                    if (iCode==10 && ek.getCaretPosition()>5)
                      e.setModifiers(1);
                    int iMod=e.getModifiers();
                    iCode=e.getKeyCode();
                    g.fixtestInfo("ek.keyPressed:"+s+"("+iCode+")/"+iMod+", Pos="+ek.getCaretPosition());
                    if ((/*iCode==10 ||*/ iCode==27) && iMod==0)
                      DlgClose(iCode==10);
                  }

                  public void keyReleased(KeyEvent e)
                  {
                    //CheckColor();
                  }

                  public void keyTyped(KeyEvent e)
                  {
                    /*char cKey=e.getKeyChar();
                    if (cKey==13)
                    {
                      e.setKeyChar((char)10);
                      g.fixInfo("ek.keyTyped: "+(cKey+0)+"->"+(e.getKeyChar()+0));
                    }*/
                  }
                });
  }

  private void showSource()
  {
          JDialog Dlg=new JDialog(new JFrame(),g.getBegriffS("Button", "Source"),false);
          JTextArea TxtSource=new JTextArea();
          TxtSource.setWrapStyleWord(true);
          TxtSource.setText(Edt.getText());
          Dlg.add(TxtSource, BorderLayout.CENTER);
          Dlg.pack();
          Static.centerComponent(Dlg, Edt);
          Dlg.setVisible(true);
  }

  private void DlgClose(boolean bOk)
  {
    if (bOk)
    {
      String s=ek.getDocumentText();
      int iPos=s.indexOf("<font class=\"(none)*\">");
      int iAnz=0;
      while (iPos > -1)
      {
        int iPos2 = s.indexOf("</font>", iPos + 1);
        iAnz++;
        //System.out.println("Pos:" + iPos + "/" + iPos2);
        s = s.substring(0, iPos) + s.substring(iPos + 22, iPos2) + s.substring(iPos2 + 7);
        iPos = s.indexOf("<font class=\"(none)*\">");
      }
      if (iAnz>0)
        g.fixtestInfo(iAnz+" font(none) entfernt");
      Edt.setText(s);
      CheckColor();
      iTBits = (CbxMB!=null && CbxMB.isSelected() ? 1 : 0) + (CbxTBM.isSelected() ? 2 : 0) + (CbxTBF.isSelected() ? 4 : 0) +
          (CbxTBS!=null && CbxTBS.isSelected() ? 8 : 0) /*+ (CbxAU!=null && CbxAU.isSelected() ? 16 : 0)*/;
      if (iBegriff > 0)
        g.setFenster(Dlg, iBegriff, iTBits * 2, 0, iFF);
    }
    //bClose = true;
    Dlg.dispose();
  }

  private void Refresh()
  {
    PnlN.removeAll();
    /*if (CbxMB!=null && CbxMB.isSelected())*/ PnlN.add(ek.getMenuBar());
    if (CbxTBM.isSelected()) PnlN.add(TBT(ek.getToolBarMain(true)));
    if (CbxTBF.isSelected()) PnlN.add(TBT(ek.getToolBarFormat(true)));
    if (CbxTBS!=null && CbxTBS.isSelected()) PnlN.add(TBT(ek.getToolBarStyles(true)));
    //if (CbxAU!=null && CbxAU.isSelected()) PnlN.add(jToolBarMain);
    PnlN.revalidate();
  }

  /*private ImageIcon getEkitIcon(String iconName)
  {
     return new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons\\" + iconName + "HK.png")));
   }*/

  /*private JButtonNoFocus setToolBtn(JButtonNoFocus Btn,String sBtn)
  {
    //JButtonNoFocus Btn= new JButtonNoFocus(;
    Btn.setIcon(g.LoadImageIcon(g.getPosBegriff("Button","Tb_"+sBtn)));
    Btn.setText(null);
    Btn.setToolTipText(Translatrix.getTranslationString(sBtn));
    return Btn;
  }*/

  private JToolBar TBT(JToolBar Tb)
  {
    for (int i=0;i<Tb.getComponentCount();i++)
    {
      Object Obj=Tb.getComponentAtIndex(i);
      if (Obj instanceof JButtonNoFocus || Obj instanceof JToggleButtonNoFocus)
      {
        String s=Obj instanceof JButtonNoFocus ? ((JButtonNoFocus)Obj).getToolTipText():((JToggleButtonNoFocus)Obj).getToolTipText();
        if (s.endsWith("*"))
        {
          if (s.indexOf("Document")>0 || s.startsWith("Insert"))
          {
            Tb.remove(i);
            i--;
            //(JButtonNoFocus)Obj).setVisible(false);
          }
          else
          {
            s = s.substring(0, s.length() - 1);
            if (s.equals("Find")) s = "Search";
            if (s.equals("Source")) s = "ViewSource";
            if (s.equals("Create Hyperlink")) s = "InsertAnchor";
            if (s.equals("Strike-through")) s = "FontStrike";
            if (s.equals("Bold") || s.equals("Italic") || s.equals("Underline") || s.equals("Subscript") || s.equals("Superscript")) s="Font"+s;
            if (s.indexOf(" ") > 0)s = s.replaceAll(" ", "");
            if (s.indexOf("List")>0) s="List"+s.substring(0,s.length()-4);
            if (Obj instanceof JButtonNoFocus)
              ((JButtonNoFocus)Obj).setToolTipText(Translatrix.getTranslationString(s));
            else
              ((JToggleButtonNoFocus)Obj).setToolTipText(Translatrix.getTranslationString(s));
          }
          //g.fixInfo(i + ":" +((JButtonNoFocus)Obj).getName()+"/"+((JButtonNoFocus)Obj).getToolTipText()+"/"+((JButtonNoFocus)Obj).getActionCommand());
        }
      }
      //else
      //  g.fixInfo(i + ":" +Static.print(Obj));

    }
    return Tb;
  }

  /*private void setToolbar()
  {
    jToolBarMain = new JToolBar(JToolBar.HORIZONTAL);
    jToolBarMain.setFloatable(false);
    JButtonNoFocus jbtnCut = setToolBtn(new JButtonNoFocus(new DefaultEditorKit.CutAction()),"Cut");
    JButtonNoFocus jbtnCopy = setToolBtn(new JButtonNoFocus(new DefaultEditorKit.CopyAction()),"Copy");
    JButtonNoFocus jbtnPaste = setToolBtn(new JButtonNoFocus(new DefaultEditorKit.PasteAction()),"Paste");
    ImageIcon Img = g.LoadImageIcon(g.getPosBegriff("Button","show"));
    JToggleButtonNoFocus jtbtnViewSource = new JToggleButtonNoFocus(Img);
    jtbtnViewSource.setToolTipText(Translatrix.getTranslationString("ViewSource"));
    jtbtnViewSource.addActionListener(ek);
    jtbtnViewSource.setActionCommand("viewsource");
    jToolBarMain.add(jbtnCut);
    jToolBarMain.add(jbtnCopy);
    jToolBarMain.add(jbtnPaste);
    jToolBarMain.add(new JToolBar.Separator());
    jToolBarMain.add(jtbtnViewSource);
  }*/

  private void CheckColor()
  {
    Edt.setBackground(Modified() ? Static.ColChange : Color.WHITE);
  }
  
  public void show2(String s)
  {
	  show2(s,null);
  }

  public void show2(String s,Component com2)
  {
    if (s==null)
      ek.setDocumentText(Edt.getText());
    else
    {
      setText(s);
      ek.setDocumentText(s);
    }
    if (com2 != null)
    	Static.centerComponent(Dlg, com2);
    Dlg.setVisible(true);
  }

  public void setText(String s)
  {
    if (s!=null && s.indexOf("<body")<0)
      s=s.replaceAll("\n","<br>");
    Edt.setText(s);
    if (Dlg!=null && Dlg.isVisible())
      ek.setDocumentText(s);
    sOldText=getValue();
    CheckColor();
  }

  public void setAktText(String s)
  {
    sOldText = s;
    CheckColor();
  }

  public String getValue()
  {
    return Edt.getText();//ek.getDocumentText();
  }

  public String toString()
  {
    return Edt.getText();//ek.getDocumentText();
  }

  public boolean Modified()
  {
    boolean b=!Static.Gleich(sOldText,getValue());
    return b;
  }

public boolean isNull()
{
  return getValue().equals(sLeer);
}

public void setEditable(boolean b)
{
  bEdit=b;
  Edt.setEditable(b);
  if (MnuEdt!=null)
    MnuEdt.setEnabled(b);
}

public void Reset2()
{
  setText(sOldText);
}

}
