import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Delaunay extends JPanel{
	
	final int maxWidth =400;
	final int maxHeight =250;

	int maxNumPoints = 25;
	int numPoints =5;//(int)(maxNumPoints*Math.random());
	Point[] points = new Point[numPoints];
	ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new Delaunay());
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	Delaunay()
	{ 

//	  for(int i=0; i<numPoints;i++)
//	  {
//	    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(maxHeight*Math.random()));    
//	  }
		JPanel self = this;
		new Thread()
		{
		    public void run() {
		      points[0] = new Point(200,200);
		   	  points[1] = new Point(400,200);
		   	  points[2] = new Point(400,400);
		   	  points[3] = new Point(200,400);
		   	  points[4] = new Point(243,329);
		   	  
		   	  ArrayList<Edge> edgeBuffer = new ArrayList<Edge>();
		   	  //Initialize super triangle
		   	  Triangle st = new Triangle(800,500);
		   	  //Add supertriangle to triangle list
		   	  triangles.add(st);
		   	  //for every sample point
		   	  for(int v =0; v<numPoints;v++)
		   	  {
		   	    //Initialize edge buffer
		   	    edgeBuffer.clear();

		   	    for(int j=triangles.size()-1;j>=0;j--)
		   	    {
		   	      Triangle  t = (Triangle)triangles.get(j);
		   	      //If point in circumcircle, add triangle edges to edgebuffer remove triangle from list
		   	      if(t.circumCircle(points[v]))
		   	      {
		   	    	edgeBuffer.add(new Edge(new Point(t.p1),new Point(t.p2)));
		   	    	edgeBuffer.add(new Edge(new Point(t.p2),new Point(t.p3)));
		   	    	edgeBuffer.add(new Edge(new Point(t.p3),new Point(t.p1)));
		   	        triangles.remove(j);     
		   	      }
		   	    }
		   	    //Delete double specified edges
		   	    deleteDuplicateEdges(edgeBuffer);
		   	    //Fill triangle list
		   	    for(int e=0; e<edgeBuffer.size();e++)
		   	    {
		   	      Edge a = edgeBuffer.get(e);
		   	      if(a.p1 == null || a.p2 ==null)
		   	    	  continue;
		   	      triangles.add(new Triangle(new Point(a.p1),new Point(a.p2),new Point(points[v])));
		   	    }		   	    
		   	    //Draw after each point
		   	    self.repaint();
		   	    try { Thread.sleep(250);} catch(Exception e) {}
		   	  }
		   	  //Remove all triangles shared with super triangles
		   	  
		   	    ListIterator<Triangle> tIt = triangles.listIterator();
		   	    
		   	    //For each triangle
		   	    while(tIt.hasNext())
		   	    {
		   	      Triangle compare =tIt.next();
		   	    if(compare.sharesVertex(st))
		   	      tIt.remove();
		   	    }
		   	    self.repaint();
		   	    System.out.println("done");
		    }
		}.start();
	 
	}

	void deleteDuplicateEdges(ArrayList<Edge> edgeBuffer)
	{
		for(int e =0; e<edgeBuffer.size()-1;e++)
		{
			Edge a = edgeBuffer.get(e);
			for(int e2 =e;e2<edgeBuffer.size();e2++)
			{
				Edge c= edgeBuffer.get(e2);
				if(c.p1 !=null && c.p2 !=null && a.p1 !=null && a.p2 !=null){
					if((a.p1.equalTo(c.p1) && a.p2.equalTo(c.p2))||(a.p1.equalTo(c.p2)&& a.p2.equalTo(c.p1)))
					{
						a.p1 =null;
						a.p2 =null;
						c.p1 =null;
						c.p2=null;
					}
				}
			}
		}		
	}
		

	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	     for(int i=0; i<numPoints;i++)
	     {
	      g.fillOval((int)points[i].x,(int)points[i].y,10,10);    
	     }
	     for(int t=0; t<triangles.size();t++)
	     {
	       Triangle T = (Triangle)triangles.get(t);
	       g.drawLine((int)T.p1.x,(int)T.p1.y,(int)T.p2.x,(int)T.p2.y);
	       g.drawLine((int)T.p2.x,(int)T.p2.y,(int)T.p3.x,(int)T.p3.y);
	       g.drawLine((int)T.p3.x,(int)T.p3.y,(int)T.p1.x,(int)T.p1.y);
	     }

	     
	}

}
