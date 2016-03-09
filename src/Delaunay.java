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
	Point[] points = new Point[numPoints+3];
	LinkedList<Triangle> triangles = new LinkedList<Triangle>();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new Delaunay());
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	Delaunay()
	{ 

	  /*for(int i=0; i<numPoints;i++)
	  {
	    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(maxHeight*Math.random()));    
	  }*/
		JPanel self = this;
		new Thread()
		{
		    public void run() {
		    	 points[0] = new Point(200,200);
		   	  points[1] = new Point(400,200);
		   	  points[2] = new Point(400,400);
		   	  points[3] = new Point(200,400);
		   	  points[4] = new Point(243,329);
		   	  
		   	  
		   	  
		   	  
		   	  LinkedList<Point> edgeBuffer;
		   	  //Initialize super triangle
		   	  Triangle st = new Triangle(800,500);
		   	  //Add supertriangle to vertex list
		   	  points[numPoints] = st.p1;
		   	  points[numPoints+1] = st.p2;
		   	  points[numPoints+2]=st.p3;
		   	  triangles.add(st);
		   	  //for every sample point
		   	  for(int v =0; v<numPoints;v++)
		   	  {
		   	    //Initialize edge buffer
		   	    edgeBuffer = new LinkedList<Point>();
		   	    ListIterator<Triangle> tIt = triangles.listIterator();
		   	    //For each triangle
		   	    while(tIt.hasNext())
		   	    {
		   	      Triangle t = tIt.next();
		   	      //If point in circumcircle, add triangle edges to edgebuffer remove triange from list
		   	      if(t.CircumCircle(points[v]))
		   	      {
		   	        edgeBuffer.add(t.p1);
		   	        edgeBuffer.add(t.p2);
		   	        edgeBuffer.add(t.p2);
		   	        edgeBuffer.add(t.p3);
		   	        edgeBuffer.add(t.p3);
		   	        edgeBuffer.add(t.p1);
		   	        tIt.remove();
		   	      }
		   	    }
		   	    //Delete double specifed edges
		   	    deleteDuplicateEdges(edgeBuffer);
		   	    //Fill triangle list
		   	    ListIterator<Point> edgeIt = edgeBuffer.listIterator();
		   	    while(edgeIt.hasNext())
		   	    {
		   	      Point a = edgeIt.next();
		   	      Point b = edgeIt.next();
		   	      triangles.add(new Triangle(a,b,points[v]));
		   	    }
		   	    //triangulation
		   	    
		   	    //Draw after each point
		   	    self.repaint();
		   	    try { Thread.sleep(1000);} catch(Exception e) {}
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
		   	    
		   	    System.out.println(triangles.size());
		   	    self.repaint();
		   	    
		    }
		}.start();
	 
	}

	void deleteDuplicateEdges(LinkedList<Point> edgeBuffer)
	{
	  ListIterator<Point> edgeIt = edgeBuffer.listIterator();
	  while(edgeIt.hasNext())
	  {
	    Point a = edgeIt.next();
	    Point b = edgeIt.next();
	    
	    ArrayList<Integer> remove = new ArrayList<Integer>();
	    
	    ListIterator<Point> secondIterator = edgeBuffer.listIterator(edgeIt.nextIndex());

	    while(secondIterator.hasNext())
	    {
	      //Out of bounds
	      Point c = secondIterator.next();
	      Point d = secondIterator.next();
	      
	      if(a.equalTo(c) && b.equalTo(b)||a.equalTo(d)&&b.equalTo(c))
	      {
	        remove.add(edgeIt.previousIndex()-1);
	        remove.add(edgeIt.previousIndex());
	        remove.add(secondIterator.previousIndex()-1);
	        remove.add(secondIterator.previousIndex());
	        
	        break;
	      }
	    }
	    for(int i = remove.size() - 1; i >= 0; i--) {
	      edgeBuffer.remove(remove.get(i));
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
