/*    */ package jclass.cell.renderers;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import jclass.cell.CellInfo;
/*    */ import jclass.util.JCImageCreator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RawImageCellRenderer
/*    */   extends ScaledImageCellRenderer
/*    */ {
/* 76 */   protected Canvas component = new Canvas();
/*    */   
/*    */ 
/*    */ 
/*    */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*    */   {
/* 82 */     if ((paramObject != null) && (!(paramObject instanceof Image))) {
/* 83 */       paramObject = JCImageCreator.getImage((byte[])paramObject, this.component);
/*    */     }
/* 85 */     super.draw(paramGraphics, paramCellInfo, paramObject, paramBoolean);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*    */   {
/* 92 */     if ((paramObject != null) && (!(paramObject instanceof Image))) {
/* 93 */       paramObject = JCImageCreator.getImage((byte[])paramObject, this.component);
/*    */     }
/* 95 */     return super.getPreferredSize(paramCellInfo, paramObject);
/*    */   }
/*    */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\RawImageCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */