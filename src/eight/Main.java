/**
 * 
 */
package eight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Mokrzu
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		File f = new File("input0.txt");
		Scanner sc = new Scanner(f);
		
		int numb = sc.nextInt();
		Graph g = new Graph();
		g.nodes = new Node[numb];
		
		int n;
		
		for (int i = 0; i < numb; i++) {
			
			n = sc.nextInt();
			g.nodes[i] = new Node(i + 1, n);
	
			for (int j=0; j<n; j++) {
				g.nodes[i].vertexArray[j] = sc.nextInt() - 1;
			}
		}
		
		g.initialize();
		System.out.println("N: " + g.number);
		
		
		for (Set<Integer> e : g.getComponents()) {
			System.out.println("-------------");
			System.out.print(e.size() > 2 ? "C: " : "B: ");
			for (int i : e) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
		System.out.print("\n< ");
		for (int it : g.getSplitNodes()) {
			System.out.print(it + " ");
		}
		System.out.print(">\n");
		
		for(TreeSet<Integer> t : g.getComSets()) {
			System.out.print("\n[ ");
			for (int it : t) {
				System.out.print(it + " ");
			}
			System.out.print("]");
		}
		
		System.out.println("\n");
		
		for (int k=1; k <= g.nodes.length; k++) {
			
			System.out.println("vertex: " + k + ", array: " + g.nodes[k-1].showVertexArray() + ", low: " + g.nodes[k-1].lowValue + ".");
		}
		
		System.out.print("Correct graph: " + g.checkGraph());
	}
}
