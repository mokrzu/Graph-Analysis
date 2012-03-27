/**
 * 
 */
package eight;

import java.util.Vector;

/**
 * @author Mokrzu
 *
 */
public class Component {
	public Vector<Node> edgesBegin;
	public Vector<Node> edgesEnd;
	public int value;
	
	public Component() {
		
		edgesBegin = new Vector<Node>();
		edgesEnd   = new Vector<Node>();
	}
}
