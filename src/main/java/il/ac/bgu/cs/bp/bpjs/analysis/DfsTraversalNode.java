package il.ac.bgu.cs.bp.bpjs.analysis;

import il.ac.bgu.cs.bp.bpjs.bprogramio.BProgramSyncSnapshotCloner;
import il.ac.bgu.cs.bp.bpjs.exceptions.BPjsRuntimeException;

import java.util.*;

import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.BProgramSyncSnapshot;
import il.ac.bgu.cs.bp.bpjs.model.BEvent;

import java.util.concurrent.ExecutorService;

/**
 * A single node in a program's execution tree. Contains the program's state,
 * and the last event to happen when getting to this state.
 *
 * @author Gera
 * @author Reut
 * @author michael
 */
public class DfsTraversalNode {

    /**
     * Get the initial nod for a run of the passed {@code BPorgram}.
     *
     * @param bp The {@link BProgram} being verified.
     * @param exSvc The executor service that will run the threads
     * @return Initial node for the BProgram run
     * @throws Exception in case there's an error with the executed JavaScript code.
     * @deprecated Use the inside code, this whole class might be going away soon.
     */
    public static DfsTraversalNode getInitialNode(BProgram bp, ExecutorService exSvc, List<BProgramRunnerListener> listeners) throws Exception {
        BProgramSyncSnapshot seed = bp.setup().start(exSvc);

        return new DfsTraversalNode(bp, seed, listeners, null);
    }

    /**
     * Get the initial nod for a run of the passed {@code BPorgram}.
     *
     * @param bp The {@link BProgram} being verified.
     * @param exSvc The executor service that will run the threads
     * @return Initial node for the BProgram run
     * @throws Exception in case there's an error with the executed JavaScript code.
     * @deprecated Use the inside code, this whole class might be going away soon.
     */
    public static DfsTraversalNode getInitialNode(BProgram bp, ExecutorService exSvc) throws Exception {
        return getInitialNode(bp, exSvc, Collections.emptyList());
    }

    private final BProgramSyncSnapshot systemState;
    private final BProgram bp;
    private final Set<BEvent> selectableEvents;
    private final BEvent lastEvent;
    private final Iterator<BEvent> iterator;
    private final List<BProgramRunnerListener> listeners;

    protected DfsTraversalNode(BProgram bp, BProgramSyncSnapshot systemState, List<BProgramRunnerListener> listeners, BEvent e) {
        this.bp = bp;
        this.systemState = systemState;
        this.listeners = listeners;
        this.lastEvent = e;

        if (bp != null) {
            selectableEvents = bp.getEventSelectionStrategy().selectableEvents(systemState);
            ArrayList<BEvent> eventOrdered = new ArrayList<>(selectableEvents);
            Collections.shuffle(eventOrdered);
            iterator = eventOrdered.iterator();
        } else {
            selectableEvents = Collections.<BEvent>emptySet();
            iterator = selectableEvents.iterator();
        }

    }

    private String stateString() {

        StringBuilder str = new StringBuilder();
        systemState.getBThreadSnapshots().forEach(
                s -> str.append("\t").append(s.toString()).append(" {").append(s.getSyncStatement()).append("} \n"));

        return str.toString();
    }

    @Override
    public String toString() {
        return ((lastEvent != null) ? "\n\tevent: " + lastEvent + "\n" : "") + stateString();
    }

    /**
     * Get a Node object for each possible state of the system after triggering
     * the given event.
     *
     * @param e the selected event
     * @param exSvc The executor service that will run the threads
     * @return State of the BProgram after event {@code e} was selected while
     * the program was at {@code this} node's state.
     * @throws BPjsRuntimeException  In case there's an error running the JavaScript code.
     */
    public DfsTraversalNode getNextNode(BEvent e, ExecutorService exSvc) throws BPjsRuntimeException {
        try {
            return new DfsTraversalNode(bp, BProgramSyncSnapshotCloner.clone(systemState).triggerEvent(e, exSvc, listeners), listeners, e);
        } catch ( InterruptedException ie ) {
            throw new BPjsRuntimeException("Thread interrupted during event invocaiton", ie);
        }
    }

    /**
     * Get the events that can be triggered at the state.
     *
     * @return An iterator for the set of requested and not blocked events.
     */
    public Iterator<BEvent> getEventIterator() {
        return iterator;
    }

    public BEvent getLastEvent() {
        return lastEvent;
    }

    public BProgramSyncSnapshot getSystemState() {
        return systemState;
    }

    public Set<BEvent> getSelectableEvents() {
        return selectableEvents;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(systemState);
        //result = prime * result + Objects.hash(lastEvent);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DfsTraversalNode)) {
            return false;
        }

        DfsTraversalNode other = (DfsTraversalNode) obj;
        if (!Objects.equals(lastEvent, other.getLastEvent())) {
            return false;
        }

        return Objects.equals(systemState, other.getSystemState());
    }

}
