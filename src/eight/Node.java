/**
 * 
 */
package eight;

/**
 * @author Mokrzu
 *
 */
public class Node {
	
	public int value;
	public int[] vertexArray;
	public int lowValue;
	public int d; 
	public Boolean color; // white == false && red == true
	public Node previous;
	
	public Node(int v, int x) {
		value = v;
		vertexArray = new int[x];
	}
	
	public String showVertexArray() {
		
		String ans = "";
		
		for (int i : vertexArray) {
			i++;
			ans += (i + " ");
		}	
		return ans;
	}
}