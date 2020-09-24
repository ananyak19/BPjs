/*
 * The MIT License
 *
 * Copyright 2020 michael.
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
package il.ac.bgu.cs.bp.bpjs.execution.jsproxy;

import il.ac.bgu.cs.bp.bpjs.model.BThreadSyncSnapshot;

/**
 * JS object that exposes the b-thread metadata to b-thread JS code.
 * Available to the b-thread code via {@code bp.thread}.
 * 
 * @author michael
 */
public class BThreadDataProxy {
    
    private final BThreadSyncSnapshot btData;

    public BThreadDataProxy(BThreadSyncSnapshot btData) {
        this.btData = btData;
        name = btData.getName();
    }
    
    public final String name;
    
    public Object getData() {
        return btData.getData();
    }
    
    public void setData( Object someNewData ) {
        btData.setData(someNewData);
    }
}
