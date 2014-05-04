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
public class LoopBenchmark {
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
                .include(LoopBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	
	@Benchmark
	public void loopStandarFor() {
		
        for (int i = 0; i < myFriends.size(); i++) {
            System.out.println(myFriends.get(i));
        }
	}
	
	@Benchmark
	public void loopStandarWhile() {        
        int j = 0;
        while (j < myFriends.size()) {
            System.out.println(myFriends.get(j++));
        }        
	}
	
	@Benchmark
	public void loopGettingObject() {        
        for (String friend : myFriends) {
            System.out.println(friend);
        }
	}
    
    @Benchmark
    public void loopForEach() {
        myFriends.forEach(friend -> System.out.println(friend));
    }
   
    @Benchmark
    public void loopIterator() {
        Iterator<String> friendIterator = myFriends.iterator();
        while (friendIterator.hasNext()) {
            System.out.println(friendIterator.next());
        }
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
Benchmark                        Mode  Cnt      Score        Error  Units
LoopBenchmark.loopForEach        avgt    3  37426,368 ±   6078,831  ns/op
LoopBenchmark.loopGettingObject  avgt    3  33146,545 ±  12145,639  ns/op
LoopBenchmark.loopIterator       avgt    3  33145,011 ±  16208,341  ns/op
LoopBenchmark.loopStandarFor     avgt    3  51738,112 ± 202620,107  ns/op
LoopBenchmark.loopStandarWhile   avgt    3  44660,831 ±  28419,164  ns/op
*/