/**
 * 
 */
package eight;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author Mokrzu
 *
 */
public class Graph {
	
	public Node[] nodes;
	
	public int time;
	public int number;
	public Vector<Component> components;
	public Vector<TreeSet<Integer>> comSets;
	
	public Stack<Node> first;
	public Stack<Node> second;
	
	public void initialize() {
		
		for (Node u : nodes) {
			
			u.color = false;
			u.previous = null;
		}
		
		time = 0;
		number = 0;
		components = new Vector<Component>();
		
		first = new Stack<Node>();
		second = new Stack<Node>();
		
		comSets = new Vector<TreeSet<Integer>>();
		
		for (Node u : nodes) {
			if (u.color == false) {
				
				comSets.add(new TreeSet<Integer>());
				visit(u);
				
				while (!first.empty()) { 
					components.lastElement().edgesBegin.add(first.pop());
					components.lastElement().edgesEnd.add(second.pop());
				}
			}
		}
	}


	public void visit(Node u) {
		
		comSets.lastElement().add(u.value);
		
		u.color = true;
		u.lowValue = u.d = ++time;
		
		for (int k : u.vertexArray) {
			
			Node v = nodes[k];
			
			if (v != u.previous) {
				if (v.color == false) {
					
					v.previous = u;
					first.push(u);
					second.push(v);
					
					visit(v);
					
					if (v.lowValue >= u.d) {
						
						number++;
						components.add(new Component());
						
						Node y, z;
						do {
							y = first.pop();
							z = second.pop();
							components.get(number - 1).edgesBegin.add(y);
							components.get(number - 1).edgesEnd.add(z);
							//components.get(number - 1).value = number;
						} while (y != u && z != v);
					} else {
						u.lowValue = u.lowValue < v.lowValue ? u.lowValue : v.lowValue;
					}
				} else {
					
					if (v.d < u.d) {
						first.push(u);
						second.push(v);
						u.lowValue = u.lowValue < v.d ? u.lowValue : v.d;
					}
				}
			}
		}
	}
	
	public Boolean checkGraph() {
		
		for (Node u : nodes) {
			Boolean t = false;
			for (int i : u.vertexArray) {				
				if (u.value - 1 == i) {
					t = true;
				}
			}
			if (t) {
				return false;
			}
		} 
		
		for (Node u : nodes) {
			for (int i : u.vertexArray) {
				Boolean b = false;
				for (int j : nodes[i].vertexArray) {
					if (j == u.value - 1) {
						b = true;
						break;
					}
				}			
				if (b == false) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public Vector<Set<Integer>> getComponents() {
		
		Vector<Set<Integer>> vec = new Vector<Set<Integer>>();
		for (Component e : components) {
			
			Set<Integer> set = new TreeSet<Integer>();
			
			for (int l=0; l < e.edgesBegin.size(); l++) {
				set.add(e.edgesBegin.get(l).value);
				set.add(e.edgesEnd.get(l).value);
			}
			
			vec.add(set);
		}
		
		/*
		while (!first.empty()) {
			
			int l = 0;
			for (int f : vec.lastElement()) {
				l = nodes[f - 1].lowValue;
			}
			
			if (first.peek().lowValue == l) { 
				vec.lastElement().add(first.pop().value);
				vec.lastElement().add(second.pop().value);
			} else {
				vec.add(new TreeSet<Integer>());
				vec.lastElement().add(first.pop().value);
				vec.lastElement().add(second.pop().value);
			}
		}
		*/
		return vec;
	}
	
	public Vector<Integer> getSplitNodes() {
		
		Vector<Integer> vec = new Vector<Integer>();
		Vector<Integer> ans = new Vector<Integer>();
		
		for (Node u : nodes) {
				vec.add(u.value);
		} 
		
		for (int i : vec) {
			
			int counter = 0;
			
			for (Set<Integer> set : getComponents()) {
				if (set.contains(i)) {
					counter++;
				}
			}
			
			if (counter > 1) {
				ans.add(i);
			}
		}
		
		return ans;
	}
	
	public Vector<TreeSet<Integer>> getComSets() {
		return comSets;
	}
}












