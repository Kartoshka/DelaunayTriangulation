import java.util.*;

final int maxWidth =400;
final int maxHeight =250;

int maxNumPoints = 25;
int numPoints =(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints];
Geometry g= new Geometry();

void setup()
{  
  println(numPoints);
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(int)(maxHeight*Math.random()));    
  }
  Arrays.sort(points);
  
  
  ArrayList triangles = new ArrayList<Triangle>();
  ArrayList edgeBuffer;// = new ArrayList<Point>();
 
 
  
  background(255);
}


void draw()
{
     fill(0);
     for(int i=0; i<numPoints;i++)
     {
      ellipse(points[i].x,points[i].y,10,10);
      if((i+2)<numPoints)
     {
       noFill();
       double[] coords = g.getCircumCircle(points[i],points[i+1],points[i+2]);
       ellipse((float)coords[0],(float)coords[1],(float)coords[2],(float)coords[2]);
     }
     }
     

     
}