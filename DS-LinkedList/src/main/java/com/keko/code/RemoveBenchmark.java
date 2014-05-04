package com.keko.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/*
 *  In the test bellow we can see that
 *  removing the last element is faster,
 *  because the other elements do not 
 *  be shifted
 */
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Measurement(iterations = 3)
public class RemoveBenchmark {
	@Param({"10000"})
    private int qtyOfElements;
	private List<Integer> listOfElements;
	
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RemoveBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	@Setup
    public void setup() {
		listOfElements = new ArrayList<Integer>();
        for (int i=0; i < qtyOfElements; i++) {
        	listOfElements.add(i);
        }
	}
	
	@Benchmark
	public void removingFirsElement() {
		while (listOfElements.isEmpty() == false){
			listOfElements.remove(0);
        }
	}
	
	@Benchmark
	public void removingMiddleElement() {
		
		while (listOfElements.isEmpty() == false){
			listOfElements.remove((int) Math.floor(listOfElements.size() / 2));
        }
	}		
	
}
/*

Benchmark                              (qtyOfElements)  Mode  Cnt  Score   Error  Units
RemoveBenchmark.removingFirsElement              10000  avgt    3  1,589 ± 3,843  ns/op
RemoveBenchmark.removingMiddleElement            10000  avgt    3  1,613 ± 4,763  ns/op*/