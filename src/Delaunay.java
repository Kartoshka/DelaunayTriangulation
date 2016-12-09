import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Delaunay extends JPanel{
	//Point generation boundary
	final static int SCREEN_WIDTH =800;
	final static int SCREEN_HEIGHT =500;
	
	final static int  borderSpacingX = 100;
	final static int borderSpacingY = 125;
	//Points
	int numPoints =200;
	Point[] points = new Point[numPoints];
	//Triangles
	ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	
	static Delaunay active;
	static JPanel triangulationPane = new JPanel();
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Delaunay");
		frame.setLayout(new BorderLayout());
		
		JButton triangulateBTN = new JButton("Triangulate");
		triangulateBTN.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(active!=null){
					frame.remove(active);
				}
				active = new Delaunay();
				frame.getContentPane().add(active,BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}
			
		});
		frame.getContentPane().add(triangulateBTN, BorderLayout.NORTH);
		
		frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	Delaunay()
	{ 	
	 
	 int maxWidth = SCREEN_WIDTH - borderSpacingY*2;
	 int maxHeight = SCREEN_HEIGHT - borderSpacingX*2;
	 
	  for(int i=0; i<numPoints;i++)
	  {
		 points[i]= new Point(borderSpacingY+maxWidth*Math.random(),borderSpacingX + maxHeight*Math.random()); 
		  //Fill points array with points depicting a spiral
		 //points[i] = new Point((250-0.5*i)*Math.cos(2*i) +250,(250-0.5*i)*Math.sin(2*i) +250);
	  }
	  
		JPanel self = this;
		new Thread()
		{
		    public void run() {
		   	  //Initialize super triangle
		   	  Triangle st = new Triangle();
		   	  st.p1 = new Point(SCREEN_WIDTH/2 -2*SCREEN_WIDTH,maxHeight/2 -SCREEN_WIDTH);
		      st.p2 = new Point( SCREEN_WIDTH/2, maxHeight/2 + 2.0f * SCREEN_WIDTH);
		      st.p3 = new Point( SCREEN_WIDTH/2 + 2.0f * SCREEN_WIDTH, maxHeight/2 - SCREEN_WIDTH);
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
//		   	    try { Thread.sleep(15);} catch(Exception e) {}
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
