import java.util.*;

final int maxWidth =400;
final int maxHeight =250;

int maxNumPoints = 25;
int numPoints =(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints+3];
ArrayList triangles = new ArrayList<Triangle>();
Geometry g= new Geometry();

void setup()
{  
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(int)(maxHeight*Math.random()));    
  }
  
  
  
  ArrayList edgeBuffer;// = new ArrayList<Point>();
  //Initialize super triangle
  Triangle st = new Triangle(maxWidth, maxHeight);
  //Add supertriangle to vertex list
  points[numPoints] = st.p1;
  points[numPoints+1] = st.p2;
  points[numPoints+2]=st.p3;
  triangles.add(st);
  for(int v =0; v<points.length;v++)
  {
    edgeBuffer = new ArrayList<Point>();
    for(int t=0; t<triangles.size();t++)
    {
      Triangle T = (Triangle)triangles.get(t);
      if(!T.isInCircumCircle(points[v]))
      {
        edgeBuffer.add(T.p1);
        edgeBuffer.add(T.p2);
        edgeBuffer.add(T.p2);
        edgeBuffer.add(T.p3);
        edgeBuffer.add(T.p3);
        edgeBuffer.add(T.p1);
        triangles.remove(t);
      }
    }
    println(edgeBuffer.size());

    //deleteDuplicates
    for(int edge=edgeBuffer.size()-2; edge>=0;edge-=2)
    {
      Point a = (Point)edgeBuffer.get(edge);
      Point b = (Point)edgeBuffer.get(edge+1);
      
      for(int compare=edge-2;compare>=0;compare-=2)
      {
         Point c = (Point)edgeBuffer.get(compare);
         Point d = (Point)edgeBuffer.get(compare+1); 
         if(a.equalTo(c) && b.equalTo(d) ||a.equalTo(d) && b.equalTo(c))
         {
           edgeBuffer.remove(a);
           edgeBuffer.remove(b);
           edgeBuffer.remove(c);
           edgeBuffer.remove(d);
           edge-=4;
         }
      }
    }
    for(int edge=edgeBuffer.size()-2; edge>=0;edge-=2)
    {
        triangles.add(new Triangle(points[v],(Point)edgeBuffer.get(edge),(Point)edgeBuffer.get(edge+1)));
    }
    
  }
  println(triangles.size());
  background(255);
}


void draw()
{
     fill(0);
     for(int i=0; i<numPoints;i++)
     {
      ellipse((float)points[i].x,(float)points[i].y,10,10);    
     }
     for(int t=0; t<triangles.size();t++)
     {
       Triangle T = (Triangle)triangles.get(t);
       line((float)T.p1.x,(float)T.p1.y,(float)T.p2.x,(float)T.p2.y);
       line((float)T.p2.x,(float)T.p2.y,(float)T.p3.x,(float)T.p3.y);
       line((float)T.p3.x,(float)T.p3.y,(float)T.p1.x,(float)T.p1.y);
     }

     
}