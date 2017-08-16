package il.ac.bgu.cs.bp.bpjs.diningphil;

import static org.junit.Assert.*;

import org.junit.Test;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.StringBProgram;
import il.ac.bgu.cs.bp.bpjs.events.BEvent;

public class TestEquals {

	static final String P1 = 
			"bp.registerBThread(\"BThread 1\", function() {" +
			"	while (true) {" +
			"		bsync({request: bp.Event(\"X\")});" +
			"		bsync({wait : bp.Event(\"X\")});" + 
			"	}" + 
			"});" 
		;
		
	
	
	@Test
	public void test1() throws Exception {
		// Create a program
		final BProgram bprog = new StringBProgram(P1);

		Node[] nodes = new Node[10];
		
        BEvent eventX = new BEvent("X");
        // Discard initial node, as it has no event, and so can't
        // be used in the even/odd equalities later.
		nodes[0] = Node.getInitialNode(bprog).getNextNode(eventX);
		
		for ( int i=1; i<10; i++ ) {
			nodes[i] = nodes[i-1].getNextNode(eventX);
		}

		for ( int i=1; i<10; i+=2 ) {
			assertEquals(nodes[0], nodes[i-1]);
			assertEquals(nodes[1], nodes[  i]);
		}
	}

	
	@Test
	public void test2() throws Exception {
		final BProgram bprog = new SingleResourceBProgram("BPJSDiningPhil.js");
        bprog.putInGlobalScope("PHILOSOPHER_COUNT", 5);
		
        String events[] = {"Pick1R", "Pick2R", "Pick3R", "Pick4R", "Pick5R"};
		Node[] nodes = new Node[events.length+1];
		 
		
		nodes[0] = Node.getInitialNode(bprog);
		
		
		for( int i=0; i<events.length; i++) {
			nodes[i+1] = nodes[i].getNextNode(new BEvent(events[i]));
		}

		for(int i=0; i<nodes.length; i++) {
			for(int j=0; j<nodes.length; j++) {
				if( i!= j) {
					assertFalse(nodes[i].equals(nodes[j]));
					assertFalse(nodes[i].hashCode() == nodes[j].hashCode());
				}
			}
		}
	}

	
}
