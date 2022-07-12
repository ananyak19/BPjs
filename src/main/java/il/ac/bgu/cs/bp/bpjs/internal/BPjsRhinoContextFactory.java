/*
 * The MIT License
 *
 * Copyright 2022 michael.
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
package il.ac.bgu.cs.bp.bpjs.internal;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;

/**
 * A context factory for all BPjs' contexts.
 * 
 * @author michael
 */
public class BPjsRhinoContextFactory extends ContextFactory {

    @Override
    protected boolean hasFeature(Context cx, int featureIndex) {
        switch (featureIndex) {
            
            case Context.FEATURE_LOCATION_INFORMATION_IN_ERROR:
                return true;

            case Context.FEATURE_INTEGER_WITHOUT_DECIMAL_PLACE:
                return true;

            case Context.FEATURE_ENABLE_JAVA_MAP_ACCESS:
                return true;
        }
        // defaults
        return super.hasFeature(cx, featureIndex); 
    }

    @Override
    protected Context makeContext() {
        Context cx = super.makeContext();
        cx.setOptimizationLevel(-1); // must use interpreter mode for continuations to work
        if ( cx.getLanguageVersion() != Context.VERSION_ES6 ) {
            cx.setLanguageVersion(Context.VERSION_ES6);
        }
        return cx;
    }
    
    
    
}
