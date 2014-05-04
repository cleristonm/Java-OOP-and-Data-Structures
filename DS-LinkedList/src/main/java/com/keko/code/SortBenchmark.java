package com.keko.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Measurement(iterations = 3)
public class SortBenchmark {
	/* 
    * https://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html
    * The size, isEmpty, get, set, iterator, and listIterator operations run in
    * constant time. The add operation runs in amortized constant time, that is,
    * adding n elements requires O(n) time. All of the other operations run in 
    * linear time (roughly speaking). The constant factor is low compared to 
    *  that for the LinkedList implementation.
    */
	
    /*
     *   Elements in an ArrayList are actually objects.
     *   To use other types, such as int, you must specify an equivalent wrapper
     *   class: Integer
     *   The default capacity of internal array is 10
     */

	List<String> myFriends;
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	
	@Benchmark
	// Sort using the compareTo of the Object
	public void sortUsingCollectionsSort() {
		Collections.sort(myFriends);
	}    

    // Sort using the sort() method. 
	// It needs a Comparator to specify as the
    // argument
	@Benchmark
	public void sortUsingSortMethod() {
	    myFriends.sort(new Comparator<String>() {
	        @Override
	        public int compare(String source, String target) {
	            return source.compareTo(target);
	        }
	    });
	}
	
	@Setup
    public void setup() {
		myFriends = new ArrayList();
        myFriends.add("Aslan");
        myFriends.add("Lucy");
        myFriends.add("Edmund");
        myFriends.add("Susan");
        myFriends.add("Peter");
        myFriends.add("Jadis");
        myFriends.add("Ginarrbrik");
        // Replacing an element
        myFriends.set(5, "Tumnus");
        // remove an element
        myFriends.remove(6);
    }

}
/*
Benchmark                               Mode  Cnt   Score    Error  Units
SortBenchmark.sortUsingCollectionsSort  avgt    3  45,369 ± 73,169  ns/op
SortBenchmark.sortUsingSortMethod       avgt    3  45,741 ± 38,335  ns/op
*/