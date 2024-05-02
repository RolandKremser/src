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
/*     */ 
/*     */ public class ScaledImageCellRenderer
/*     */   implements CellRenderer, ImageObserver
/*     */ {
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  81 */     if (paramObject == null) {
/*  82 */       return;
/*     */     }
/*  84 */     paramGraphics.setFont(paramCellInfo.getFont());
/*  85 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : 
/*  86 */       paramCellInfo.getForeground());
/*  87 */     Rectangle localRectangle = paramCellInfo.getDrawingArea();
/*  88 */     Point localPoint = new Point(0, 0);
/*  89 */     if ((((Image)paramObject).getWidth(this) < localRectangle.width) && 
/*  90 */       (((Image)paramObject).getHeight(this) < localRectangle.height)) {
/*  91 */       localPoint = getAlignmentOffset(paramCellInfo, paramObject);
/*     */     }
/*     */     
/*  94 */     paramGraphics.drawImage((Image)paramObject, localPoint.x, localPoint.y, localRectangle.width, localRectangle.height, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point getAlignmentOffset(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 101 */     Point localPoint = new Point(0, 0);
/*     */     
/*     */ 
/* 104 */     int i = paramCellInfo.getHorizontalAlignment();
/* 105 */     if (i == 2) {
/* 106 */       localPoint.x += paramCellInfo.getDrawingArea().width - ((Image)paramObject).getWidth(this);
/* 107 */     } else if (i == 1) {
/* 108 */       localPoint.x += (paramCellInfo.getDrawingArea().width - ((Image)paramObject).getWidth(this)) / 2;
/*     */     }
/*     */     
/* 111 */     i = paramCellInfo.getVerticalAlignment();
/* 112 */     if (i == 1) {
/* 113 */       localPoint.y += (paramCellInfo.getDrawingArea().height - ((Image)paramObject).getHeight(this)) / 2;
/* 114 */     } else if (i == 2) {
/* 115 */       localPoint.y += paramCellInfo.getDrawingArea().height - ((Image)paramObject).getHeight(this);
/*     */     }
/* 117 */     return localPoint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 124 */     if (paramObject == null) {
/* 125 */       return null;
/*     */     }
/* 127 */     Image localImage = (Image)paramObject;
/* 128 */     return new Dimension(localImage.getWidth(this), localImage.getHeight(this));
/*     */   }
/*     */   
/*     */   public boolean imageUpdate(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 133 */     switch (paramInt1)
/*     */     {
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\ScaledImageCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */