import java.util.*;

final int maxWidth =400;
final int maxHeight =250;

int maxNumPoints = 25;
int numPoints =5;//(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints+3];
ArrayList triangles = new ArrayList<Triangle>();
Geometry g= new Geometry();

void setup()
{  
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(maxHeight*Math.random()));    
  }
  
  
  
  ArrayList edgeBuffer;// = new ArrayList<Point>();
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
    edgeBuffer = new ArrayList<Point>();
    for(int t=triangles.size(); t>0;)
    {
      Triangle T = (Triangle)triangles.get(--t);
      if(T.isInCircumCircle(points[v]))
      {
        edgeBuffer.add(T.p1);
        edgeBuffer.add(T.p2);
        edgeBuffer.add(T.p2);
        edgeBuffer.add(T.p3);
        edgeBuffer.add(T.p3);
        edgeBuffer.add(T.p1);
        t--;
      }
      deleteDuplicateEdges(edgeBuffer);
      for(int i=0; i<edgeBuffer.size();)
      {
        triangles.add(new Triangle(points[v],(Point)edgeBuffer.get(i++),(Point)edgeBuffer.get(i++)));
      }
    }
    
    for(int t=triangles.size(); t>0;)
     {
       Triangle T = (Triangle)triangles.get(--t);
       if(st.sharesVertex(T))
       {
         triangles.remove(t--);
       }
     }
  }
  //Remove all triangles shared with super triangles
    background(255);
}

void deleteDuplicateEdges(ArrayList edgeBuffer)
{
  for(int i=edgeBuffer.size();i>0;)
  {
    Point a = ((Point)edgeBuffer.get(--i));
    Point b = ((Point)edgeBuffer.get(--i));
    
    for(int j=i; j>0;)
    {
      //Out of bounds
      Point c = ((Point)edgeBuffer.get(--j));
      Point d = ((Point)edgeBuffer.get(--j));
      
      if(a.equalTo(c) && b.equalTo(b)||a.equalTo(d)&&b.equalTo(c))
      {
        edgeBuffer.remove(a);
        edgeBuffer.remove(b);
        edgeBuffer.remove(c);
        edgeBuffer.remove(d);
        i-=2;
        break;
      }
    }
  }
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