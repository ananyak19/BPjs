/*
 * The MIT License
 *
 * Copyright 2017 michael.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package il.ac.bgu.cs.bp.bpjs.analysis;

import il.ac.bgu.cs.bp.bpjs.model.BProgramSyncSnapshot;

/**
 * A VisitedNodeStore that does not remember any visited node. When the program 
 * search graph is a tree, ensures that all the nodes are visited. When the program
 * search graph contains even a single loop, pretty much ensures an infinite verification
 * run. So use with caution.
 * 
 * @author michael
 */
public class ForgetfulVisitedStateStore implements VisitedStateStore {
    
    private long visitedCount=0;
    
    @Override
    public void store(BProgramSyncSnapshot bss) {
        visitedCount++;
    }

    @Override
    public boolean isVisited(BProgramSyncSnapshot bss) {
        return false;
    }
        
    @Override
    public void clear() {
        visitedCount=0;
    }

    @Override
    public long getVisitedStateCount() {
        return visitedCount;
    }
    
    
}
