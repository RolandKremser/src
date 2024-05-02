/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ImageObserver;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageCellRenderer
/*     */   implements CellRenderer, ImageObserver
/*     */ {
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  80 */     if (paramObject == null) {
/*  81 */       return;
/*     */     }
/*  83 */     paramGraphics.setFont(paramCellInfo.getFont());
/*  84 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : 
/*  85 */       paramCellInfo.getForeground());
/*     */     
/*  87 */     Point localPoint = getAlignmentOffset(paramCellInfo, paramObject);
/*  88 */     paramGraphics.drawImage((Image)paramObject, localPoint.x, localPoint.y, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point getAlignmentOffset(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/*  95 */     Point localPoint = new Point(0, 0);
/*     */     
/*     */ 
/*  98 */     int i = paramCellInfo.getHorizontalAlignment();
/*  99 */     if (i == 2) {
/* 100 */       localPoint.x += paramCellInfo.getDrawingArea().width - ((Image)paramObject).getWidth(this);
/* 101 */     } else if (i == 1) {
/* 102 */       localPoint.x += (paramCellInfo.getDrawingArea().width - ((Image)paramObject).getWidth(this)) / 2;
/*     */     }
/*     */     
/* 105 */     i = paramCellInfo.getVerticalAlignment();
/* 106 */     if (i == 1) {
/* 107 */       localPoint.y += (paramCellInfo.getDrawingArea().height - ((Image)paramObject).getHeight(this)) / 2;
/* 108 */     } else if (i == 2) {
/* 109 */       localPoint.y += paramCellInfo.getDrawingArea().height - ((Image)paramObject).getHeight(this);
/*     */     }
/* 111 */     return localPoint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 118 */     if (paramObject == null) {
/* 119 */       return null;
/*     */     }
/* 121 */     Image localImage = (Image)paramObject;
/* 122 */     return new Dimension(localImage.getWidth(this), localImage.getHeight(this));
/*     */   }
/*     */   
/*     */   public boolean imageUpdate(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 127 */     switch (paramInt1)
/*     */     {
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\ImageCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */