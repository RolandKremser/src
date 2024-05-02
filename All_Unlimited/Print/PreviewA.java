package All_Unlimited.Print;

//import javax.swing.*;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Global;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
/*import java.awt.Component;
import javax.swing.JDialog;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;*/
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * PreviewA dient zum zoomen mit Maustasten auf ScrollPane
 */

public class PreviewA extends ScrollPane
{

	/**
	 *
	 */
	private static final long serialVersionUID = 5940252578452469638L;
	private MPanel pnl;
        private Point P;
        private Global g;
        private boolean bNeu=false;

        /**
         * erzeugt Preview für neuen Druck
         */

	public PreviewA(PrintManagerA pm,Global rg)
	{
		setBackground(Color.lightGray);
                g=rg;
		pnl=new MPanel(pm);
		pnl.addMouseListener(
			new MouseListener(){
				   public void mouseClicked( java.awt.event.MouseEvent e )
				   {
                                     /*double dZ=pnl.getZoom();
                                     Point P=getScrollPosition();
                                     if(e.getModifiers() == MouseEvent.BUTTON3_MASK)
                                       pnl.setZoomm();
                                     else
                                       pnl.setZoomp();
                                     doLayout();
                                     double d=pnl.getZoom()/dZ;
                                     P.x=P.x-e.getX()+(int)Math.round(d*e.getX());
                                     P.y=P.y-e.getY()+(int)Math.round(d*e.getY());
                                     setScrollPosition(P);*/
                                     //setScrollPosition((int)Math.round(e.getX()*pnl.getZoom()/dZ),(int)Math.round(e.getY()*pnl.getZoom()/dZ));
                                     if(e.getModifiers() == MouseEvent.BUTTON2_MASK)
                                           bNeu=!bNeu;
                                     else if (Static.bDefBezeichnung || bNeu)
                                     {
                                       if (e.getButton() == 3 || e.getButton() == 1 && e.getModifiersEx() == Global.iRM)
                                         showPopup(e);
                                     }
                                     else
                                     {
                                       if(e.getModifiers() == MouseEvent.BUTTON3_MASK)
                                         WheelZoom(1,e.getX(),e.getY());
                                       else
                                         WheelZoom(-1,e.getX(),e.getY());
                                     }

				   }
  				   public void mousePressed( java.awt.event.MouseEvent e )
                                     {
                                       if (Static.bDefBezeichnung || bNeu)
                                         P=e.getPoint();
                                     }
 				   public void mouseReleased( java.awt.event.MouseEvent e )
                                    {
                                      if ((Static.bDefBezeichnung || bNeu) && e.getModifiers() == MouseEvent.BUTTON1_MASK)
                                      {
                                        Point P2 = getScrollPosition();
                                        P2.x=P2.x-(int)Math.round(e.getPoint().getX())+P.x;
                                        P2.y=P2.y-(int)Math.round(e.getPoint().getY())+P.y;
                                        setScrollPosition(P2);
                                      }
                                    }
  				   public void mouseEntered( java.awt.event.MouseEvent e ){}
				   public void mouseExited( java.awt.event.MouseEvent e ){}
			}
			);

                 pnl.addMouseWheelListener(new MouseWheelListener()
                 {
                  public void mouseWheelMoved(MouseWheelEvent e)
                  {
                    if (Static.bDefBezeichnung || bNeu)
                      WheelZoom(e.getWheelRotation(),e.getX(),e.getY());
                  }
                 });

		add(pnl);
	}

        private void WheelZoom(int iW,int iX,int iY)
        {
          double dZ=pnl.dZoom;//pnl.getZoom();
          P=getScrollPosition();
          //int iW=e.getWheelRotation();
          for (int i=0;i<Math.abs(iW);i++)
            if (iW<0)
            {
              if (pnl.dZoom<16)
                pnl.dZoom *= 1.1892; //pnl.setZoomp();
            }
            else if(pnl.dZoom>=1)
              pnl.dZoom/=1.1892;//pnl.setZoomm();
          //pnl.bPaint=true;
          doLayout();
          double d=pnl.dZoom/dZ;
          P.x=P.x-iX+(int)Math.round(d*iX);
          P.y=P.y-iY+(int)Math.round(d*iY);
          setScrollPosition(P);
        }

        private void addZoom(JMenu Mnu,int iZoom)
        {
          JMenuItem MnuI=new JMenuItem(iZoom+" %");
          MnuI.setActionCommand(""+iZoom);
          Mnu.add(MnuI);
          MnuI.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                double d=Double.parseDouble(ev.getActionCommand())/100;
                setZoom(d);
                setScrollPosition(new Point(0,0));
              }
            });
        }

        private void addSeite(JMenu Mnu,int iSeite)
        {
          JMenuItem MnuI=new JMenuItem(iSeite+"");
          MnuI.setActionCommand(""+iSeite);
          Mnu.add(MnuI);
          MnuI.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                int i=Integer.parseInt(ev.getActionCommand());
                //g.getFomLeer().setVisible(false);
                gotoPage(i);
              }
            });
        }

        private void showPopup(MouseEvent ev)
        {
          //long lClock = Static.get_ms();
          JPopupMenu popup = new JPopupMenu();
          JMenu popZoom=new JMenu("Zoom");
          popup.add(popZoom);
          addZoom(popZoom,75);
          addZoom(popZoom,100);
          addZoom(popZoom,120);
          addZoom(popZoom,150);
          addZoom(popZoom,200);
          addZoom(popZoom,400);
          addZoom(popZoom,800);
          if (pnl.Anzahl()>1)
          {
            JMenu popSeite=new JMenu("Seite");
            popup.add(popSeite);
            for (int i=0;i<pnl.Anzahl();i++)
              addSeite(popSeite,i+1);
          }
          popup.addSeparator();
          popup.add(new JMenuItem(new Zahl1(pnl.dZoom,"0.0")+" x ("+getScrollPosition().x+"/"+getScrollPosition().y+")"));
          //final JDialog fom=new JDialog(g.getFomLeer(),true);
          //fom.setResizable(false);
          g.getFomLeer().setVisible(true);
          g.getFomLeer().setLocation(ev.getX()+pnl.getLocationOnScreen().x, ev.getY()+pnl.getLocationOnScreen().y);
          popup.show(g.getFomLeer(), 0,0);
          popup.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
              //g.fixInfo("Canceled");
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
              //g.fixInfo("Becoming Invisible");
              g.getFomLeer().setVisible(false);
            }

            public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
              //g.fixInfo("Becoming Visible");
            }
          }
);

          /*popup.addFocusListener(new FocusListener()
          {
                        public void focusGained(FocusEvent fe)
                        {

                        }
                        public void focusLost(FocusEvent fe)
                        {
                          fom.dispose();
                        }
          });*/
          //popup.invalidate();
          //popup.setOpaque(true);
          //Global.fixInfo("showPopup "+ev.getSource().getClass().getName()+"/"+ev.getX()+"/"+ev.getY());
        }

	/*public void nextPage()
	{
		pnl.next();
	}

	public void previousPage()
	{
		pnl.previous();
	}*/

	/*public void firstPage()
	{
	}

	public void lastPage()
	{
	}*/

      /**
       * geht zur gewünschten Seite
       */

	public void gotoPage(int iPage)
	{
          pnl.setPage(iPage-1);
	}

        /**
         * Zoomt um den gewünschten Faktor
         */

        public void setZoom(double d)
        {
          pnl.setZoom(d);
        }


}


