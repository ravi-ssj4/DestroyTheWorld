public class UFFindLargest
{
  private int[] id;
  private int[] sz;
  private int[] largest;

  UFFindLargest(int N)
  {
    id = new int[N];
    sz = new int[N];
    largest = new int[N];
    for(int i=0;i<N;i++)
    {
      id[i] = i;
      sz[i] = 1;
      //largest of each element's connected component initially is the element itself
      largest[i] = i;
    }
  }

  private int root(int i)
  {
    // chase parent pointers until you reach the root

    while(id[i] != i)
    {
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  public int find(int i)
  {
      return largest[root(i)];
  }


  public boolean connected(int p, int q)
  {
    //check if p and q have the same root
    return root(p) == root(q);
  }

  public void union(int p, int q)
  {

    int rootp = root(p);
    int rootq = root(q);

    if(rootp == rootq) return;

// if p's tree is smaller than q's tree, then make p's tree point to q's tree's root
// and increase q's trees' size by p's tree
// else make q's tree point to p's tree's root and increase p's tree's size by q's tree
    int largestInP = largest[rootp];
    int largestInQ = largest[rootq];
    if(sz[rootp] <= sz[rootq])
    {
      id[rootp] = rootq;
      sz[rootq] = sz[rootq] + sz[rootp];
      if(largestInP > largestInQ)
        largest[rootq] = largestInP;

    }
    else
    {
      id[rootq] = rootp;
      sz[rootp] = sz[rootp] + sz[rootq];
      if(largestInQ > largestInP)
        largest[rootp] = largestInQ;
    }
  }

  public static void main(String[] args) {

    int N = StdIn.readInt();
    UFFindLargest fl = new UFFindLargest(N);
    while(!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if(!fl.connected(p, q))
      {
        fl.union(p, q);
        StdOut.println(p + " " + q);

        for(int k=0;k<N;k++)
        {
            StdOut.println("Largest element in cc containing the element " + k + " is: " + fl.find(k));
        }
      }

    }
  }
}
