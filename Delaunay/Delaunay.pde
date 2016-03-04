import java.util.Arrays;

final int maxWidth =400;
final int maxHeight =250;

int maxNumPoints = 25;
int numPoints =(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints];
int[][] adjacencyList = new int[numPoints][numPoints];
Geometry g= new Geometry();

void setup()
{  
  println(numPoints);
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(int)(maxHeight*Math.random()));
    for(int c=0; c<numPoints;c++)
    {
      adjacencyList[i][c]=0;//Math.random()<0.2?1:0;
    }
  }
  Arrays.sort(points);
  background(255);
}

void draw()
{
     fill(0);
     for(int i=0; i<numPoints;i++)
     {
      ellipse(points[i].x,points[i].y,10,10);
      for(int e=0; e<numPoints;e++)
      {
        if(adjacencyList[i][e]==1)
        {
          line(points[i].x,points[i].y,points[e].x,points[e].y);
        }
      }
      if((i+2)<numPoints)
     {
       noFill();
       double[] coords = g.circumcircleCoords(points[i],points[i+1],points[i+2]);
       ellipse((float)coords[0],(float)coords[1],(float)coords[2],(float)coords[2]);
     }
     }
     

     
}