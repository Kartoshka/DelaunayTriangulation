import java.util.*;

final int maxWidth =400;
final int maxHeight =250;

int maxNumPoints = 25;
int numPoints =5;//(int)(maxNumPoints*Math.random());
Point[] points = new Point[numPoints+3];
Triangle[] triangles = new Triangle[3*numPoints];
Geometry g= new Geometry();

void setup()
{  
  size(800,500);
  for(int i=0; i<numPoints;i++)
  {
    points[i] = new Point((int)(maxWidth*Math.random())+maxWidth/2,maxHeight/2+(maxHeight*Math.random()));    
  }
  
  
  
  Point[] edgeBuffer;
  //Initialize super triangle
  Triangle st = new Triangle(800,500);
  //Add supertriangle to vertex list
  points[numPoints] = st.p1;
  points[numPoints+1] = st.p2;
  points[numPoints+2]=st.p3;
  triangles[0] = st;
  //for every sample point
  for(int v =0; v<numPoints;v++)
  {
    edgeBuffer = new Point[4*numPoints];
  }

   
  //Remove all triangles shared with super triangles
    background(255);
}

void deleteDuplicateEdges(Point[] edgeBuffer)
{
  for(int i=edgeBuffer.length;i>0;i-=2)
  {
    Point a = edgeBuffer[i];
    Point b = edgeBuffer[i-1];
    
    for(int j=i; j>0;j-=2)
    {
      //Out of bounds
      Point c = edgeBuffer[j];
      Point d = edgeBuffer[j-1];
      
      if(a.equalTo(c) && b.equalTo(b)||a.equalTo(d)&&b.equalTo(c))
      {
        //Tag duplicate edges with a -1
        edgeBuffer[i].x =-1;
        edgeBuffer[i-1].x =-1;
        edgeBuffer[j].x =-1;
        edgeBuffer[j-1].x =-1;
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