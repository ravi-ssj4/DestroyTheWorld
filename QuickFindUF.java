public class QuickFindUF
{
  //our data structure to support our Union-Find algorithm, an ID array
  private int[] id;

  //a constructor to initialize the id array
  public QuickFindUF(int N)
  {
    id = new int[N];
    //set id of each object to itself to represent connection via connected components
    for(int i=0;i<N;i++)
      id[i] = i;
  }

  public boolean connected(int p, int q)
  {
    //check whether p and q are in the same connected component
    return id[p] == id[q];
  }

  public void union(int p, int q)
  {
    int pid = id[p];
    int qid = id[q];
    //change all entries with id[p] to id[q]
    for(int i=0;i<id.length;i++)
    {
      if(id[i] == pid) id[i] = qid;
    }
  }

  public void printArray()
  {
    for(int i=0;i<id.length;i++)
      StdOut.print(id[i]);
  }
  public static void main(String[] args) {
    int N = StdIn.readInt();
    QuickFindUF qfuf = new QuickFindUF(N);
    while(!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if(!qfuf.connected(p, q))
      {
        qfuf.union(p, q);
        StdOut.println(p + " " + q);
      }
    }
  }
}
