package All_Unlimited.Print;

//import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.util.Vector;

/**
 * MPanel dient zum zoomen innerhalb eines Panels
 * und wird von PreviewA benötigt
 */

public class MPanel extends Panel
{
	/**
	 *
	 */
	private static final long serialVersionUID = -482489469246346086L;
	private PrintManagerA pm=null;

        /**
         * erzeugt MPanel
         */

	public MPanel(PrintManagerA rpm)
	{

		pm=rpm;
		VecSeiten=pm.getPages();

		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		//setPreferredSize(new java.awt.Dimension(((int)pm.getFullWidth()+20)*iZoom, ((int)pm.getFullHeight()+20)*iZoom));
	}

        public int Anzahl()
        {
          return VecSeiten.size();
        }

        /**
         * Zoomt um den gewünschten Faktor
         */

        public void setZoom(double d)
        {
          dZoom=d;
          repaint();
          All_Unlimited.Allgemein.Static.sleep(100);
        }

        /*public double getZoom()
        {
          return dZoom;
        }

	public void setZoomp()
	{
          //setZoom(dZoom*1.26);
          dZoom*=1.26;
	}

	public void setZoomm()
	{
          if(dZoom>=1)
            dZoom/=1.26;
            //setZoom(dZoom/1.26);
          //else if (dZoom>=1)
          //  setZoom(dZoom-0.5);
	}*/

	/*public int iZoom()
	{
		return (int)dZoom;
	}*/

	/*public void next()
	{
		//str=s;//"Marcus123";
		//iZoom=1;
		if(iSeite+1<VecSeiten.size())
		{
			iSeite++;
			//setPreferredSize(new java.awt.Dimension(300, 350));
			repaint();
		}
	}

	public void previous()
	{
		if(iSeite!=0)
		{
			iSeite--;
			repaint();
		}
	}*/

      /**
       * zeigt gewünschte Seite
       */

	public void setPage(int iPage)
	{
		iSeite=iPage;
		repaint();
	}

	/*public String getString()
	{
		return str;
	}*/

	public double dZoom = 1.0;
	private int iSeite = 0;
	private Vector VecSeiten=null;
        //public boolean bPaint=true;

        /**
         * wird für Scroll-Balken-Darstellung benötigt
         */

	public Dimension getPreferredSize()
	{
		return new Dimension((int)((pm.getFullWidth()+20)*dZoom),(int)((pm.getFullHeight()+20)*dZoom));
        }

 /**
  * zeichnet Seite
  */

	public void paint(Graphics g)
	{
          //if (bPaint)
          {
            setSize((int)((pm.getFullWidth() + 20) * dZoom), (int)((pm.getFullHeight() + 20) * dZoom));
            //setPreferredSize(new java.awt.Dimension(((int)pm.getFullWidth()+20)*iZoom, ((int)pm.getFullHeight()+20)*iZoom));
            Graphics2D g2d = (Graphics2D)g;
            //g2d.setRenderingHints(pm.graphGlobal.getRenderingHints());
            //Paint p=pm.graphGlobal.getPaint();
            //g2d.setPaint(p);
            //System.out.println("Ts.OrientationQuer?:"+(pm.pfGlobal.getOrientation()==PageFormat.LANDSCAPE));
            g2d.setColor(Color.white);
            //int iSeitenbreite=(int)(210*dZoom);
            //int iSeitenhoehe=(int)(297*dZoom);
            //setPreferredSize(new java.awt.Dimension(((int)pm.getFullWidth()+20)*iZoom, ((int)pm.getFullHeight()+20)*iZoom));
            //System.out.println("Ts.OrientationQuer?:"+(pm.pfGlobal.getOrientation()==PageFormat.LANDSCAPE));

            g2d.fillRect(10, 10, (int)(pm.getFullWidth() * dZoom), (int)(pm.getFullHeight() * dZoom));
            g2d.translate(10, 10);
            //g2d.setClip(0,0,210*iZoom,297*iZoom);
            Vector VecSeite = (Vector)VecSeiten.elementAt(iSeite);
            g2d.setColor(Color.black);
            //g2d.scale(1.2,1.2);

            g2d = pm.makePage(g2d, VecSeite, (float)dZoom);
          }
	}
    // add your data members here
}
