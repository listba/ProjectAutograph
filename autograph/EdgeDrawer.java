package autograph;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;

import autograph.Node.NodeShape;

public class EdgeDrawer {
   private final static int SELF_ARC_WIDTH = 50;
   private final static int SELF_ARC_HEIGHT = 50;

  
   public EdgeDrawer(){
   }
   
   /**
    * Takes a graphics object and sets its style based on the edge's style
    * @param gr2 - the graphics object that will do the drawing at some point.
    */
   public static void mSetGraphicsStyle(Graphics2D gr2, Edge e){
      gr2.setColor(e.mGetEdgeColor());
      switch (e.mGetEdgeStyle()){
      case DOTTED:
         BasicStroke dotted =  new BasicStroke(
               1f, 
               BasicStroke.CAP_ROUND, 
               BasicStroke.JOIN_ROUND, 
               1f, 
               new float[] {2f}, 
               0f);
         gr2.setStroke(dotted);
         break;
      case DASHED:
         float dash1[] = {10.0f};
         BasicStroke dashed =
               new BasicStroke(1.0f,
                     BasicStroke.CAP_BUTT,
                     BasicStroke.JOIN_MITER,
                     10.0f, dash1, 0.0f);
         gr2.setStroke(dashed);
         break;
      case SOLID:
      default:
         break;
      }
   }

   public static void mSetSelectedStyle(Graphics2D gr2){
      float dash1[] = {10.0f};
      BasicStroke dashed =
            new BasicStroke(5.0f,
                  BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER,
                  10.0f, dash1, 0.0f);
      gr2.setStroke(dashed);
      gr2.setColor(Color.cyan);
   }
   
   /**
    * Calculates the edge label position and draws it to the panel.
    * @param g - the graphics object doing the drawing.
    * @param e - the Edge to calculate the label position for.
    * @param startX - the start point of the edge's x coordinate
    * @param startY - the start point of the edge's y coordinate
    * @param endX - the end point of the edge's x coordinate
    * @param endY - the end point of the edge's y coordinate
    */
   public static void mDrawStraightEdgeLabel(Graphics g, Edge e, int startX, int startY, int endX, int endY){
	   if(e.mGetLabel() != null && !e.mGetLabel().isEmpty() && g != null){
	      Graphics2D g2d = (Graphics2D)g.create();
	      g2d.setColor(e.mGetLabelColor());
	      g2d.setFont(e.mGetFont());
	      double dy = startY - endY;
	      double dx = startX - endX;
	      double theta = Math.atan2(dy, dx);
      
	      //calculate the label position.
      
    	  //rotate the text so the edge label is printed parallel with the edge
    	  if(Math.toDegrees(theta) < 90 && Math.toDegrees(theta) > 0){
    		  //first quadrant
    		  //rotate by theta so the text will be readable
    		  g2d.rotate(theta,  ((startX + endX)/2)+2, ((startY + endY)/2)-2);
	    	  g2d.drawString(e.mGetLabel(), ((startX + endX)/2)+2, ((startY + endY)/2)-2);
    	  }
    	  else if(Math.toDegrees(theta) <=0 && Math.toDegrees(theta)>-90){
    		  //fourth quadrant
    		  //rotate by theta so the text will be readable
    		  g2d.rotate(theta,  ((startX + endX)/2)-2, ((startY + endY)/2)-2);
	    	  g2d.drawString(e.mGetLabel(), ((startX + endX)/2)-2, ((startY + endY)/2)-2);
    	  }
    	  else if(Math.toDegrees(theta)<=-90){
    		  //third quadrant
    		//rotate by theta + 180 so the text won't be upside down.
    		  g2d.rotate(theta + Math.toRadians(180),  ((startX + endX)/2)+2, ((startY + endY)/2)-2);
	    	  g2d.drawString(e.mGetLabel(), ((startX + endX)/2)+2, ((startY + endY)/2)-2);
    	  }
    	  else{
    		  //second quadrant
    		  //rotate by theta + 180 so the text won't be upside down.
    		  g2d.rotate(theta + Math.toRadians(180),  ((startX + endX)/2)-2, ((startY + endY)/2)-2);
    		  g2d.drawString(e.mGetLabel(), ((startX + endX)/2)-2, ((startY + endY)/2)-2);
    	  }
    	  g2d.dispose();
      } 
   }
   
   /**
    * Draws the arrow head given the coordinates for the center points of the start and end nodes.
    * @param g - the graphics object to draw with
    * @param startX - the beginning x coordinate
    * @param startY - the beginning y coordinate
    * @param endX - the ending x coordinate
    * @param endY - the ending y coordinate
    * @param startNode - the node the arrow is pointing at.
    * @param bDrawingToSelf - true edge starts and finishes at same node (is handled differently than other edges)
    */
   private static void mDrawArrowHead(Graphics2D g2d, int startX, int startY, int endX, int endY, Node startNode, Boolean bDrawingToSelf){
		double dy = startY - endY;
		double dx = startX - endX;
		double theta = Math.atan2(dy, dx);
		
		double newStartX = (double)startX;
		double newStartY = (double)startY;
	    //KMW Note: Because we are passing in the center points for the nodes, we need to calculate the
	    //          the intersection of the edge and the node for each shape type and draw the arrow there.
		if(!bDrawingToSelf){
			
			//change the startX and startY components to be the points where the edge intersects with the node.
			double widthOffset = 0;
			double heightOffset = 0;
			int width = 0;
			switch(startNode.mGetShape()){
				case CIRCLE:
				case OVAL:
					width = startNode.mGetWidth();
					widthOffset = (width/2) * Math.cos(theta);
					heightOffset = (width/2) * Math.sin(theta);
					newStartX = startX - widthOffset;
					newStartY = startY - heightOffset;
					break;
				case SQUARE:
				case RECTANGLE:
					//KMW Note: We handle squares by looking for each edge and calculating
					//          a vertical and horizontal offset for each.
					width = startNode.mGetWidth();
					double degTheta = Math.toDegrees(theta);
				  	if((degTheta < 0 && degTheta >= -45) || (degTheta >= 0 && degTheta <= 45)){
				  		//we are on the left edge
				  		widthOffset = (width/2);
				  		heightOffset = (width/2) * Math.tan(theta);
				  		newStartX = startX - widthOffset;
				  		newStartY = startY - heightOffset;
				  	}
				  	else if((degTheta <= - 135 && degTheta >= -180) || (degTheta <= 180 && degTheta >= 135)){
				  		//we are on the right edge.
				  		widthOffset = (width/2);
				  		heightOffset = (width/2) * Math.tan(theta);
				  		newStartX = startX + widthOffset;
				  		newStartY = startY + heightOffset;
				  	}
				  	else if((degTheta < 135 && degTheta > 45)){
				  		//we are on the top edge
				  		heightOffset = (width/2);
				  		widthOffset = (width/2)/Math.tan(theta);
				  		newStartX = startX - widthOffset;
				  		newStartY = startY - heightOffset;
				  	}
				  	else{
				  		heightOffset = (width/2);
				  		widthOffset = (width/2)/Math.tan(theta);
				  		newStartX = startX + widthOffset;
				  		newStartY = startY + heightOffset;
				  	}
					break;
				  		
				case TRIANGLE:
					// We are going to do something slightly different to calculate the intersection point for the triangle.
				   // We know 3 points on the triangle and using those points we can calculate line equations for each of the
				   // lines of the triangle. We also know two points of the edge, so we can calculate the point of line equation
				   // for that line. Then calculate the point of intersection for that line and the other three, determine which
				   // intersection points are valid, and pick the closest one to the opposite end of the edge.
				   
				   // yEdge = mEdge(x) + bEdge
				   // yLine = mLine(x) + bLine
				   // 
				   // mLine(x) + bLine = mEdge(x) + bEdge
				   // (mLine - mEdge)(x) = bEdge - bLine
				   //
				   // x = (bEdge - bLine)/(mLine - mEdge)
				   // yLine = mLine((bEdge-bLine)/(mLine - mEdge)) + bLine
				   //
				   // Intersection Point = (x, yLine)
				   
				   double intersection1X = 0;
               double intersection1Y = 0;
               double intersection2X = 0;
               double intersection2Y = 0;
               double intersection3X = 0;
               double intersection3Y = 0;
				   
				   //slope and intersect of the edge.
				   double mEdge = dy/dx;
			      double bEdge = startY - (startX * mEdge);
			      
			      double topX = startNode.mGetCenterX();
			      double topY = startNode.mGetCenterY() - startNode.mGetHeight()/2;
			      
			      double bottomRightX = startNode.mGetCenterX() + startNode.mGetWidth()/2;
			      double bottomRightY = startNode.mGetCenterY() + startNode.mGetHeight()/2;
			      
			      double bottomLeftX = startNode.mGetCenterX() - startNode.mGetWidth()/2;
			      double bottomLeftY = startNode.mGetCenterY() + startNode.mGetHeight()/2;
			      
			      double mRight = (topY - bottomRightY)/(topX - bottomRightX);
			      double bRight = topY - (topX * mRight);
			      
			      double mLeft = (topY - bottomLeftY)/(topX - bottomLeftX);
			      double bLeft = topY - (topX * mLeft);
			      
			      intersection1X = (bEdge - bRight)/(mRight - mEdge);
			      intersection1Y = (mRight * intersection1X) + bRight;
			      
			      intersection2X = (bEdge - bLeft)/(mLeft - mEdge);
			      intersection2Y = (mLeft * intersection2X) + bLeft;
			      
			      // for bottom, y = bottomLeftY = bottomRightY;
               // yEdge = mEdge(x) + bEdge
               // (bottomLeftY - bEdge)/mEdge = x
			      intersection3X = (bottomLeftY - bEdge)/mEdge;
			      intersection3Y = bottomLeftY;
			      
			      double valid1X = 0;
			      double valid1Y = 0;
			      double valid2X = 0;
			      double valid2Y = 0;
			     
			      //we have 3 intersection points, only 2 should be valid...figure out which ones
			      
			      if(intersection1X >= bottomLeftX && intersection1X <= bottomRightX 
			            && intersection1Y >= topY && intersection1Y <= bottomLeftY){
			         valid1X = intersection1X;
			         valid1Y = intersection1Y;
			      }
			      if(intersection2X >= bottomLeftX && intersection2X <= bottomRightX
			            && intersection2Y >= topY && intersection2Y <= bottomLeftY){
			         if(!(valid1X == 0 && valid1Y == 0)){
			            valid2X = intersection2X;
			            valid2Y = intersection2Y;
			         }
			         else{
			            valid1X = intersection2X;
			            valid1Y = intersection2Y;
			         }
			      }
			      if(intersection3X >= bottomLeftX && intersection3X <= bottomRightX
			            && intersection3Y >= topY && intersection3Y <= bottomLeftY){
			         if(valid1X == 0 && valid1Y ==0){
			            System.out.println("Ooops, at least one should be valid by now");
			         }
			         else if(valid2Y == 0 && valid2X == 0){
			            valid2X = intersection3X;
			            valid2Y = intersection3Y;
			         }
			         //It will theoretically have 3 valid points if it is at a corner, but we don't
			         //care about that because it will just choose one of the two valid corner points.
			      }
			      
			      double distance1 = mCalculateDistanceBetweenPoints(endX, endY, valid1X, valid1Y);
			      double distance2 = mCalculateDistanceBetweenPoints(endX, endY, valid2X, valid2Y);
			      
			      if(distance1 < distance2){
			         newStartX = valid1X;
			         newStartY = valid1Y;
			      }
			      else{
			         newStartX = valid2X;
			         newStartY = valid2Y;
			      }
					break;
				default:
					break;
			}
		}
	  //otherwise mDrawArrowHead has been called with the points of intersection already
	  
		double phi = Math.toRadians(40);
		int barb = 20;
		  
		  
		double x, y, rho = theta + phi;
		for(int j = 0; j < 2; j++){
		   x = newStartX - barb * Math.cos(rho);
		   y = newStartY - barb * Math.sin(rho);
		   g2d.draw(new Line2D.Double(newStartX, newStartY, x, y));
		   rho = theta - phi;
		}
   }
   
   /**
    * Calculates the optimal access points on the node to use, and draws a straight line (or arrow) between the two nodes
    * @param e  - the edge to draw
    * @param startNode - the start node
    * @param endNode - the end node
    * @param g - the graphics object to do the drawing.
    */
   public static void mDrawStraightEdge(Graphics g, Edge e, Boolean selected) {
      Node startNode = e.mGetStartNode();
      Node endNode = e.mGetEndNode();
      
      int sNodeX = startNode.mGetCenterX();
      int sNodeY = startNode.mGetCenterY();
      int eNodeX = endNode.mGetCenterX();
      int eNodeY = endNode.mGetCenterY();

      e.mSetStartCoordinates(sNodeX, sNodeY);
      e.mSetEndCoordinates(eNodeX, eNodeY);

      Graphics2D g2d = (Graphics2D)g.create();
      if (selected)
      {
         mSetSelectedStyle(g2d);
         g2d.drawLine(sNodeX, sNodeY, eNodeX, eNodeY);
         g2d.dispose();
      }
      g2d = (Graphics2D)g.create();
      mSetGraphicsStyle(g2d, e);
      g2d.drawLine(sNodeX, sNodeY, eNodeX, eNodeY);

      mDrawStraightEdgeLabel(g, e, sNodeX, sNodeY, eNodeX, eNodeY);
      
      //KMW Note: for now we will only support one style of arrow. 
      //          at some point we may want to support the other types.
      switch(e.mGetDirection()){
      case NODIRECTION:
         //we are done. It will work
         break;
      case STARTDIRECTION:
         mDrawArrowHead(g2d, sNodeX, sNodeY, eNodeX, eNodeY, startNode, false);
         break;
      case ENDDIRECTION:
         mDrawArrowHead(g2d, eNodeX, eNodeY, sNodeX, sNodeY, endNode, false);
         break;
      case DOUBLEDIRECTION:
         mDrawArrowHead(g2d, sNodeX, sNodeY, eNodeX, eNodeY, startNode, false);
         mDrawArrowHead(g2d, eNodeX, eNodeY, sNodeX, sNodeY, endNode, false);
         break;
      }
      g2d.dispose();
   }
   
   /**
    * Draws a curved edge from the right point on the node to the top point on the same node.
    * startNode - the node this edge is connected to.
    */
   public static void mDrawEdgeToSelf(Graphics g, Edge e, Boolean selected) {
      Node startNode = e.mGetStartNode();
      int nodeCenterX = startNode.mGetCenterX();
      int nodeUpperLeftY = startNode.mGetUpperLeftY();
      
      //TODO: Handle for triangles.
      
      //We want to draw a portion of an ellipse/circle that starts at the right most
      //portion of the node and intersects at the middle of the top
      //of the node.
      //
      //To accomplish this we need to draw 75% of the ellipse/circle, with the initial
      //drawing point starting at 270 degrees and drawing a total of 270 degrees. The
      //top left of this ellipse/circle will need to be at the centerX location and 
      //an appropriate distance above the upper left y portion of the node.
      
      int arcUpperLeftX = nodeCenterX;
      int arcUpperLeftY = nodeUpperLeftY - startNode.mGetHeight()/2;
      
      Graphics2D g2d = (Graphics2D)g.create();
      e.mSetStartCoordinates(nodeCenterX + startNode.mGetWidth()/2, startNode.mGetCenterY());
      e.mSetEndCoordinates(nodeCenterX, startNode.mGetCenterY() - startNode.mGetHeight()/2);
      if(selected)
      {
         mSetSelectedStyle(g2d);
         g2d.drawArc(arcUpperLeftX, arcUpperLeftY, SELF_ARC_WIDTH, SELF_ARC_HEIGHT, 270, 270);
         g2d.dispose();
         g2d = (Graphics2D)g.create();
      }
      mSetGraphicsStyle(g2d, e);
      g2d.drawArc(arcUpperLeftX, arcUpperLeftY, SELF_ARC_WIDTH, SELF_ARC_HEIGHT, 270, 270);
      mDrawEdgeLabelForSelf(g, e);
      switch(e.mGetDirection()){
         case NODIRECTION:
            break;
         case STARTDIRECTION:
            //we are going to pretend that it is an arrow coming from the direct right 
            //and call mDrawArrowHead with data to indicate this.
            mDrawArrowHead(g2d, e.mGetStartX(), e.mGetStartY(), e.mGetStartX()+50, e.mGetStartY(), startNode, true);
            break;
         case ENDDIRECTION:
            //we are going to pretend that it is an arrow coming from directly above 
            //and call mDrawArrowHead with data to indicate this.
            mDrawArrowHead(g2d, e.mGetEndX(), e.mGetEndY(), e.mGetEndX(), e.mGetEndY()-50, startNode, true);
            break;
         case DOUBLEDIRECTION:
            mDrawArrowHead(g2d, e.mGetStartX(), e.mGetStartY(), e.mGetStartX()+50, e.mGetStartY(), startNode, true);
            mDrawArrowHead(g2d, e.mGetEndX(), e.mGetEndY(), e.mGetEndX(), e.mGetEndY()-50, startNode, true);
            break;
         default:
            break;
      }
      g2d.dispose();
   }
   
   /**
    * Draws an edge label assuming the edge is connecting a node to itself.
    * @param g - the graphics object for drawing
    * @param e - the edge to draw the label for.
    */
   private static void mDrawEdgeLabelForSelf(Graphics g, Edge e){
	  if(e != null && e.mGetLabel().isEmpty()){
	      Graphics2D g2d = (Graphics2D)g.create();
	      g2d.setColor(e.mGetLabelColor());
	      g2d.setFont(e.mGetFont());
	      //calculate the label position.
	      int labelX;
	      int labelY;
	      
	      Node startNode = e.mGetStartNode();
	      
	      //The center of the arc that makes up the edge is located at the upper right
	      //corner of the node. So we need to find that position.
	      
	      int upperRightX = startNode.mGetUpperLeftX() + startNode.mGetWidth();
	      int upperRightY = startNode.mGetUpperLeftY();
	      
	      labelX = upperRightX + SELF_ARC_WIDTH/2;
	      labelY = upperRightY - SELF_ARC_HEIGHT/2;
	      
	      g2d.drawString(e.mGetLabel(), labelX, labelY);
	  }
   }

   /**
    * Draws a paired (twin) edge based on the value of the vPairPosition of the Edge.
    * @param g - graphics object for drawing
    * @param e - the edge to draw
    * @param selected - whether or not this edge is selected.
    */
   public static void mDrawPairedEdge(Graphics g, Edge e, Boolean selected) {
      int startCenterX = e.mGetStartNode().mGetCenterX();
      int startCenterY = e.mGetStartNode().mGetCenterY();
      int endCenterX = e.mGetEndNode().mGetCenterX();
      int endCenterY = e.mGetEndNode().mGetCenterY();
      
      double dy = startCenterY - endCenterY;
      double dx = startCenterX - endCenterX;
      double theta = Math.atan2(dy, dx);
      
      int newStartX = 0;
      int newStartY = 0;
      int newEndX = 0;
      int newEndY = 0;
            
      switch(e.mGetPairPosition()){
         case FIRST:
        	if((Math.toDegrees(theta) > 0 && Math.toDegrees(theta) < 90) || Math.toDegrees(theta) < -90){
        		newStartX = startCenterX + e.mGetStartNode().mGetWidth()/4;
	            newStartY = startCenterY - e.mGetStartNode().mGetHeight()/4;
	            newEndX = endCenterX + e.mGetEndNode().mGetWidth()/4;
	            newEndY = endCenterY - e.mGetEndNode().mGetHeight()/4;
        	}
        	else{
	            //we will move the points to the left and up for each node for the first edge.
	            newStartX = startCenterX - e.mGetStartNode().mGetWidth()/4;
	            newStartY = startCenterY - e.mGetStartNode().mGetHeight()/4;
	            newEndX = endCenterX - e.mGetEndNode().mGetWidth()/4;
	            newEndY = endCenterY - e.mGetEndNode().mGetHeight()/4;
        	}
            break;
         case SECOND:
        	 if((Math.toDegrees(theta) > 0 && Math.toDegrees(theta) < 90) || Math.toDegrees(theta) < -90){
         		newStartX = startCenterX - e.mGetStartNode().mGetWidth()/4;
 	            newStartY = startCenterY + e.mGetStartNode().mGetHeight()/4;
 	            newEndX = endCenterX - e.mGetEndNode().mGetWidth()/4;
 	            newEndY = endCenterY + e.mGetEndNode().mGetHeight()/4;
         	}
        	else{
	            //we will move the points to the right and down for each node for the second edge
	            newStartX = startCenterX + e.mGetStartNode().mGetWidth()/4;
	            newStartY = startCenterY + e.mGetStartNode().mGetHeight()/4;
	            newEndX = endCenterX + e.mGetEndNode().mGetWidth()/4;
	            newEndY = endCenterY + e.mGetEndNode().mGetHeight()/4;
         	}
            break;
         case UNPAIRED:
            break;
      }
      Graphics2D g2d = (Graphics2D)g.create();
      if(selected)
      {
        mSetSelectedStyle(g2d);
        g2d.drawLine(newStartX, newStartY, newEndX, newEndY);
        g2d.dispose();
        g2d = (Graphics2D)g.create();
      }
      mSetGraphicsStyle(g2d, e);
      g2d.drawLine(newStartX, newStartY, newEndX, newEndY);
      
      e.mSetStartCoordinates(newStartX, newStartY);
      e.mSetEndCoordinates(newEndX, newEndY);
      
      mDrawStraightEdgeLabel(g, e, newStartX, newStartY, newEndX, newEndY);
      switch (e.mGetDirection()){
      case STARTDIRECTION:
         mDrawPairedArrowHead(g, e, e.mGetStartNode());
         break;
      case ENDDIRECTION:
         mDrawPairedArrowHead(g, e, e.mGetEndNode());
         break;
      case DOUBLEDIRECTION:
         mDrawPairedArrowHead(g, e, e.mGetStartNode());
         mDrawPairedArrowHead(g, e, e.mGetEndNode());
         break;
      }
      g2d.dispose();
   }

   private static void mDrawPairedArrowHead(Graphics g, Edge e, Node arrowNode){
      
      Graphics2D g2d = (Graphics2D)g.create();
      mSetGraphicsStyle(g2d, e);
      
      double arrowX = 0;
      double arrowY = 0;
      
      //values for the line equation
      double dy;
      double dx;
      if(arrowNode == e.mGetStartNode()){
         dy = (double)e.mGetStartY() - (double)e.mGetEndY();
         dx = (double)e.mGetStartX() - (double)e.mGetEndX();
      }
      else{
         dy = (double)e.mGetEndY() - (double)e.mGetStartY();
         dx = (double)e.mGetEndX() - (double)e.mGetStartX();
      }
      double theta = Math.atan2(dy, dx);
      double m = dy/dx;
      double b = e.mGetStartY() - (e.mGetStartX() * m);
      
      switch(arrowNode.mGetShape()){
         case CIRCLE:
            //for circles we need to calculate the location where the line and the circle intersect.
            //Circle formula: (x - a)^2 (y - c)^2 = r^2
            //Expanded Circle:  x^2 -2ax + a^2 + y^2 - 2yc + c^2 - r^2 = 0
            //Line formula: y = mx + b
            //
            //Plug Line y into Expaned Circle: 
            //
            //               : x^2 - 2ax + a^2 + (mx + b)^2 - 2(mx + b)c + c^2 - r^2
            //               : x^2 - 2ax + a^2 + (m^2x^2 + 2mxb + b^2) - 2mxc - 2bc + c^2 - r^2 = 0
            //               : x^2 + (m^2)x^2 - 2ax - 2mxc +2mxb + a^2 + b^2 - 2bc + c^2 - r^2 = 0
            //               : (m^2 + 1)x^2 + (-2a - 2mc + 2mb)x + (a^2 + b^2 - 2bc + c^2 - r^2) = 0
            //
            //               : quadA = (2m^2 + 1)
            //               : quadB = (-2a - 2mc + 2mb)
            //               : quadC = (a^2 + b^2 - 2bc + c^2 - r^2)
            //
            //
            // and 
            //    m = slope of line
            //    a = centerX loc of node
            //    c = centerY loc of node
            //    b = y intercept of line
            //    r = radius of node
            
            double c = (double)arrowNode.mGetCenterY();
            double a = (double)arrowNode.mGetCenterX();
            
            double r = arrowNode.mGetWidth()/2;
            
            
            double quadB = (-2 * a) + (2 * b * m) - (2 * c * m);
            double quadA = 1 + (m * m);
            double quadC = (a*a) + (b*b) - 2*b*c + c*c - r*r;
            
            double posX = (-quadB + Math.sqrt((quadB * quadB) - (4 * quadA * quadC)))/(2* quadA);
            double posY = m*posX + b;
            
            double negX = (-quadB - Math.sqrt((quadB * quadB) - (4 * quadA * quadC)))/(2* quadA);
            double negY = m*negX + b;
            
            if(Math.toDegrees(theta) > 90 || Math.toDegrees(theta) < -90){
               arrowX = posX;
               arrowY = posY;
            }
            else{
               arrowX = negX;
               arrowY = negY;
            }
            
            break;
         case SQUARE:
            // for squares we need to calculate the points of intersection between the edge's line, and 
            // the line on the square that is the first to be intersected by the line
            // Furthermore, for every square we should have exactly 2 valid intersection points,
            // so we will need to calculate which one is the more appropriate one to use.
            // We know that the line equation is:
            //
            //     y = mx + b
            //
            // and we have already calculated m and b above.
            // We also know that the equations for the square can be summed up as:
            //    
            //    y1 = centerY - height/2; (s.t. x1 <= x <= x2)
            //    y2 = centerY + height/2; (s.t. x1 <= x <= x2)
            //    x1 = centerX - width/2;  (s.t. y1 <= y <= y2)
            //    x2 = centerX + width/2;  (s.t. y1 <= y <= y2)
            //
            // So now we need to plug in the values of y1, y2, x1, and x2 into the line equation
            // and determine which of the intersections works best for our arrow placement.
            
               double centerY = arrowNode.mGetCenterY();
               double centerX = arrowNode.mGetCenterX();
               double y1 = centerY - (arrowNode.mGetHeight()/2);
               double y2 = centerY + (arrowNode.mGetHeight()/2);
               double x1 = centerX - (arrowNode.mGetWidth()/2);
               double x2 = centerX + (arrowNode.mGetWidth()/2);
               
               double actualIntersect1X = 0;
               double actualIntersect1Y = 0;
               double actualIntersect2X = 0;
               double actualIntersect2Y = 0;
               
               double xIntersect1 = (y1 - b)/m; //this is point (xIntersect1, y1)
               double xIntersect2 = (y2 - b)/m; //this is point (xIntersect2, y1)
               
               double yIntersect1 = (m*x1) + b; //this is point (x1, yIntersect1)
               double yIntersect2 = (m*x2) + b; //this is point (x2, yIntersect2)
               
               if(xIntersect1 >= x1 && xIntersect1 <= x2){
                  //we have a valid line intersection point
                  actualIntersect1X = xIntersect1;
                  actualIntersect1Y = y1;
               }
               if(xIntersect2 >= x1 && xIntersect2 <=x2){
                  //we have a valid line intersection point
                  if(actualIntersect1X == 0 && actualIntersect1Y ==0){
                     //we haven't found a valid intersect yet, so populate the first point data.
                     actualIntersect1X = xIntersect2;
                     actualIntersect1Y = y2;
                  }
                  else{
                     //we have found a valid intersect already, so populate the second point's data.
                     actualIntersect2X = xIntersect2;
                     actualIntersect2Y = y2;
                  }
               }
               if(yIntersect1 >= y1 && yIntersect1 <= y2){
                  //we have a valid intersection point
                  if(actualIntersect1X == 0 && actualIntersect1Y == 0){
                     //we have not had a valid intersection point yet, so populate the first point data
                     actualIntersect1X = x1;
                     actualIntersect1Y = yIntersect1;
                  }
                  else if(actualIntersect2X == 0 && actualIntersect2Y == 0){
                     //we have already had a valid intersection point, but haven't had 2 of them, so populate the 
                     //second point's data.
                     actualIntersect2X = x1;
                     actualIntersect2Y = yIntersect1;
                  }
                  else{
                     //it thinks we have already had 2 valid intersection points, and that this one is a third. 
                     //This should not happen
                     System.out.println("Ooops...something borked.");
                  }
               }
               if(yIntersect2 >= y1 && yIntersect2 <= y2){
                  if(actualIntersect1X == 0 && actualIntersect1Y == 0){
                     System.out.println("Something borked, this should not be 0s");
                  }
                  else if(actualIntersect2X == 0 && actualIntersect2Y == 0){
                     actualIntersect2X = x2;
                     actualIntersect2Y = yIntersect2;
                  }
                  else{
                     System.out.println("Oops...something new borked.");
                  }
               }
            
               //ok, now we have our two potential points, and we need to select the appropriate one.
               
               if(arrowNode == e.mGetStartNode()){
                  //we are drawing in the start direction, so pick the point closest to the end point of the edge
                  double distance1 = mCalculateDistanceBetweenPoints(e.mGetEndX(), e.mGetEndY(), actualIntersect1X, actualIntersect1Y);
                  double distance2 = mCalculateDistanceBetweenPoints(e.mGetEndX(), e.mGetEndY(), actualIntersect2X, actualIntersect2Y);
                  
                  if(distance1 <= distance2){
                     arrowX = actualIntersect1X;
                     arrowY = actualIntersect1Y;
                  }
                  else{
                     arrowX = actualIntersect2X;
                     arrowY = actualIntersect2Y;
                  }
               }
               else{
                  //we are drawing in the end direction, so pick the point closest to the start point of the edge.
                  double distance1 = mCalculateDistanceBetweenPoints(e.mGetStartX(), e.mGetStartY(), actualIntersect1X, actualIntersect1Y);
                  double distance2 = mCalculateDistanceBetweenPoints(e.mGetStartX(), e.mGetStartY(), actualIntersect2X, actualIntersect2Y);
                  
                  if(distance1 <= distance2){
                     arrowX = actualIntersect1X;
                     arrowY = actualIntersect1Y;
                  }
                  else{
                     arrowX = actualIntersect2X;
                     arrowY = actualIntersect2Y;
                  }
               }
               
            break;
         case TRIANGLE:
            break;
      }
      
      double phi = Math.toRadians(40);
      int barb = 20;
      double x, y, rho = theta + phi;
      for(int j = 0; j < 2; j++){
         x = arrowX - barb * Math.cos(rho);
         y = arrowY - barb * Math.sin(rho);
         g2d.draw(new Line2D.Double(arrowX, arrowY, x, y));
         rho = theta - phi;
      }
      g2d.dispose();
   }
   
   /**
    * This function uses the Pythagorean theorem to calculate the distance between two points.
    * @param x1 - first point's x coordinate
    * @param y1 - first point's y coordinate
    * @param x2 - second point's x coordinate
    * @param y2 - second point's y coordinate
    * @return - distance between these points
    */
   private static double mCalculateDistanceBetweenPoints(double x1, double y1, double x2, double y2){
      double distance = 0;
      
      double dx = Math.abs(x1 - x2);
      double dy = Math.abs(y1 - y2);
      
      distance = Math.sqrt((dx*dx) + (dy*dy));
      
      return distance;
   }
}


