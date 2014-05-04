/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.keko.code;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/*
 * There is a slight difference between the performance of the methods bellow.
 * addElementLastPosition - is faster because set link just in the 
 * newElement.prior and lastElement.next
 * 
 * addElementLastPosition - is faster because just sets values for 
 * newElement.prior and lastElement.next
 * 
 * addElementSecondPosition - is slower because needs set values of 
 * firstElement.next, newElement.prior, newElement.next and 
 * secondElement.priorThere is a slight difference between 
 * the performance of the methods bellow.
 *  
 */
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Measurement(iterations = 3)
public class AddBenchmark {

	@Param({"100"})
    private int numberOfElements;
	
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AddBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
   
    @Benchmark
	public void addElementFirstPosition() {
    	LinkedList<Integer> myLinkedList = new LinkedList<Integer>();        
        for(int i = 0;i<numberOfElements;i++)
        {
            myLinkedList.addFirst(i);
        }
	}
    
    @Benchmark
	public void addElementSecondPosition() {
    	LinkedList<Integer> myLinkedList = new LinkedList<Integer>();
    	myLinkedList.add(1);
    	myLinkedList.add(2);
        for(int i = 2;i<numberOfElements;i++)
        {
            myLinkedList.add(1, i);
        }
	}
    
    @Benchmark
	public void addElementNormalPosition() {
    	LinkedList<Integer> myLinkedList = new LinkedList<Integer>();        
        for(int i = 0;i<numberOfElements;i++)
        {
            myLinkedList.add(i);
        }
	}
    
    @Benchmark
	public void addElementLastPosition() {
    	LinkedList<Integer> myLinkedList = new LinkedList<Integer>();        
        for(int i = 0;i<numberOfElements;i++)
        {
            myLinkedList.addLast(i);
        }
	}
}
/*
Benchmark                              (numberOfElements)  Mode  Cnt     Score      Error  Units
AddBenchmark.addElementFirstPosition                  100  avgt    3   971,826 ± 2527,937  ns/op
AddBenchmark.addElementLastPosition                   100  avgt    3   983,376 ± 2571,256  ns/op
AddBenchmark.addElementNormalPosition                 100  avgt    3   923,088 ± 1270,430  ns/op
AddBenchmark.addElementSecondPosition                 100  avgt    3  1100,417 ± 1470,667  ns/op
*/