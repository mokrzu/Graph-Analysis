
package eight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.*;

public class Tester extends TestCase {

	public void testAll() throws FileNotFoundException {
		
		for (int i=0; i<10; i++) {
			
			File in_path = new File("input" + i + ".txt");
			File out_path = new File("output" + i + ".txt");

			Scanner input = new Scanner(in_path);
			Scanner output = new Scanner(out_path);
			
			int n = input.nextInt();
			Graph g = new Graph();
			g.nodes = new Node[n];
			
			for (int k = 0; k < n; k++) {
				
				int len = input.nextInt();
				g.nodes[k] = new Node(k + 1, len);
				
				for (int l=0; l<len; l++) {
					
					g.nodes[k].vertexArray[l] = input.nextInt() - 1;
				}
			}
			
			g.initialize();
			
			System.out.println("N: " + g.number);
			
			
			for (Set<Integer> e : g.getComponents()) {
				System.out.println("-------------");
				System.out.print(e.size() > 2 ? "C: " : "B: ");
				for (int it : e) {
					System.out.print(it + " ");
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
				
				System.out.println("vertex: " + k + ", a: " + g.nodes[k-1].vertexArray.length + ", array: " + g.nodes[k-1].showVertexArray() + ", low: " + g.nodes[k-1].lowValue + ".");
			}
			
			System.out.print("Correct graph: " + g.checkGraph());
			
			
			System.out.println("\n==================\n==================");
		}
	}
}
