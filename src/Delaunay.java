import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Delaunay extends JPanel{
	//Point generation boundary
	final int maxWidth =400;
	final int maxHeight =250;
	
	//Points
	int numPoints =250;
	Point[] points = new Point[numPoints];
	//Triangles
	ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("test");
		frame.add(new Delaunay());
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	Delaunay()
	{ 
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Martlet.png"));
		} catch (IOException e) {
		}
		int w = img.getWidth();
		int h = img.getHeight();
	int[][] pixels = new int[w][h];

	for( int i = 0; i < w; i++ )
	    for( int j = 0; j < h; j++ )
	        pixels[i][j] = img.getRGB( i, j );
	
	  for(int i=0; i<numPoints;i++)
	  {
		  points[i]= new Point(maxWidth*Math.random(),maxHeight*Math.random());        
	  }
		JPanel self = this;
		new Thread()
		{
		    public void run() {
		      
		      //Arrays.sort(points);
		   	  //Initialize super triangle
		   	  Triangle st = new Triangle();
		   	  st.p1 = new Point(maxWidth/2 -2*maxWidth,maxHeight/2 -maxWidth);
		      st.p2 = new Point( maxWidth/2, maxHeight/2 + 2.0f * maxWidth);
		      st.p3 = new Point( maxWidth/2 + 2.0f * maxWidth, maxHeight/2 - maxWidth);
		   	  //Add supertriangle to triangle list
		   	  triangles.add(st);
		   	  
		   	  //EdgeBuffer 
		   	  ArrayList<Point> edgeBuffer = new ArrayList<Point>();

		   	  //for every sample point
		   	  for(int v =0; v<numPoints;v++)
		   	  {
		   	    //Initialize edge buffer
		   	    edgeBuffer.clear();
	
		   	    for(int j=triangles.size()-1;j>=0;j--)
		   	    {
		   	      Triangle  t = (Triangle)triangles.get(j);
		   	      //If point in circumcircle, add triangle edges to edgebuffer remove triangle from list
		   	      if(t.CircumCircle(points[v]))
		   	      {
		   	    	edgeBuffer.add(t.p1);
		   	    	edgeBuffer.add(t.p2);
		   	    	edgeBuffer.add(t.p2);
		   	    	edgeBuffer.add(t.p3);
		   	    	edgeBuffer.add(t.p3);
		   	    	edgeBuffer.add(t.p1);
		   	        triangles.remove(j);     
		   	      }
		   	    }
		   	    //Delete double specified edges
		   	    deleteDuplicateEdges(edgeBuffer);
		   	    //Fill triangle list
		   	    for(int e=0; e<edgeBuffer.size();e+=2)
		   	    {
		   	      Point p1 = edgeBuffer.get(e);
		   	      Point p2 = edgeBuffer.get(e+1);
		   	      if(p1 == null || p2 ==null)
		   	      {
		   	    	 continue;
		   	      }
		   	      triangles.add(new Triangle(p1,p2,points[v]));
		   	    }		   	    
		   	    //Draw after each point
		   	    self.repaint();
		   	    try { Thread.sleep(15);} catch(Exception e) {}
		   	  }
		   	  //Remove all triangles shared with super triangles
		   	  		   	    
		   	  for (int i = triangles.size()-1; i >= 0; i--) {
			      Triangle t = (Triangle)triangles.get(i);
			      if (t.sharesVertex(st)) {
			        triangles.remove(i);
			      }
			    }
		   	    self.repaint();
		    }
		}.start();
	 
	}

	void deleteDuplicateEdges(ArrayList<Point> edgeBuffer)
	{
		for(int e =0; e<edgeBuffer.size()-1;e+=2)
		{
			Point a = (Point)edgeBuffer.get(e);
			Point b = (Point)edgeBuffer.get(e+1);

			for(int e2 =e+2;e2<edgeBuffer.size();e2+=2)
			{
				Point c = (Point)edgeBuffer.get(e2);
				Point d = (Point)edgeBuffer.get(e2+1);		
				if(a==c && b==d || a==d && b==c)
				{
					edgeBuffer.set(e,null);
					edgeBuffer.set(e+1,null);
					edgeBuffer.set(e2,null);
					edgeBuffer.set(e2+1,null);
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
	      g.fillOval((int)points[i].x -2,(int)points[i].y-2,5,5);    
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
