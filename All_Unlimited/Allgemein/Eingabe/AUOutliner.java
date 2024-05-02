/*
    All_Unlimited-Allgemein-Eingabe-AUOutliner.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerFolderNode;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import java.awt.Color;
//import jclass.bwt.JCOutlinerComponent;
//import jclass.bwt.JCOutlinerNodeStyle;
//import javax.swing.ImageIcon;

public class AUOutliner extends jclass.bwt.JCOutliner
{
/**
	 *
	 */
	private static final long serialVersionUID = 3951264109491610962L;

        public AUOutliner()
        {
          super ();
          events();
        }

        public AUOutliner(JCOutlinerFolderNode Nod)
        {
          super (Nod==null ? new JCOutlinerFolderNode(""):Nod);
          events();
        }

private void events()
{
  setColumnLabelSortMethod(Sort.sortMethod);
  setFont(Static.getOutFont());
  /*JCOutlinerNodeStyle StyFolder = (new JCOutlinerComponent()).getDefaultNodeStyle();
  StyFolder.setFolderOpenIcon(new ImageIcon(getClass().getResource("/images/open.png")).getImage());
  StyFolder.setFolderClosedIcon(new ImageIcon(getClass().getResource("/images/close.png")).getImage());*/
  setBackground(Color.white);
  addMouseWheelListener(new MouseWheelListener()
  {
    public void mouseWheelMoved(MouseWheelEvent e) {
      //int i=getVertScrollbar();
      //i++;
      /*int i1=e.getWheelRotation();
      long l1=System.nanoTime();
      if (l1-l>500000000l)
        i=i1;
      l=l1;*/
      scrollVertical(getVertScrollbar().getValue()+e.getWheelRotation()*100);
      //System.out.println(l+":"+i+"/"+i1);
    }
  });
}

public boolean isNull()
{
	return getSelectedNode()==null;
}

public boolean Modified()
{
	JCOutlinerNode Node = getSelectedNode();
	return NodeOld==null && Node==null?false:NodeOld==null || Node==null?true:!NodeOld.equals(Node);
}

public void selectNode(JCOutlinerNode Node)
{
  if (Node != null)
    super.selectNode(Node,null);
  NodeOld=Node;
}

public void Reset()
{
  NodeOld=getSelectedNode();
}

public void Reset2()
{
  selectNode(NodeOld);
}

public int getSelect(int i)
      {
        return Sort.geti(getSelectedNode().getUserData(),i);
      }

public void copy()
{
	System.err.println("AUOutliner.copy");
	getOutliner().copyToClipboard(null);
}

public void paste()
{
	System.err.println("AUOutliner.paste");
	getOutliner().pasteFromClipboard(null);
}

// add your data members here
private JCOutlinerNode NodeOld=null;
//private int i=0;
//private long l=0;
}

