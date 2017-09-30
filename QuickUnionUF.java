public class QuickUnionUF
{
  private int[] id;

  QuickUnionUF(int N)
  {
    id = new int[N];
    for(int i=0;i<N;i++)
      id[i] = i;
  }

  private int root(int i)
  {
    // chase parent pointers until you reach the root
    while(id[i] != i) i = id[i];
    return i;
  }

  public boolean connected(int p, int q)
  {
    //check if p and q have the same root
    return root(p) == root(q);
  }

  public void union(int p, int q)
  {
    // change root of p to point to root of q(make p a child of q's root)
    int rootp = root(p);
    int rootq = root(q);
    id[rootp] = rootq;
  }

  public static void main(String[] args) {

    int N = StdIn.readInt();
    QuickUnionUF quuf = new QuickUnionUF(N);
    while(!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if(!quuf.connected(p, q))
      {
        quuf.union(p, q);
        StdOut.println(p + " " + q);
      }
    }
  }
}
