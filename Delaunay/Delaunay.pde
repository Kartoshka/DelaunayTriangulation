import java.util.Arrays;

final int maxWidth =800;
final int maxHeight =500;

int maxNumPoints = 25;
int numPoints =(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints];
int[][] adjacencyList = new int[numPoints][numPoints];

void setup()
{  
  println(numPoints);
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random()),(int)(maxHeight*Math.random()));
    for(int c=0; c<numPoints;c++)
    {
      adjacencyList[i][c]=0;
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
     }     
}