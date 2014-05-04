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

@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Measurement(iterations = 3)
public class ContaisBenchmark {

	@Param({"100"})
    private int qtyOfElements;
	private List<Integer> listOfElements;
	
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ContaisBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	
	@Setup
    public void setup() {
        listOfElements = new ArrayList<Integer>();
        for (int i=0; i <= qtyOfElements; i++) {
        	listOfElements.add(i);
        }
    }
	
	@Benchmark
	public void searchFirstElement() {
		listOfElements.contains(0);
	}
	
	@Benchmark
	public void searchMiddleElement() {
		listOfElements.contains((int) Math.floor(qtyOfElements / 2));
	}
	
	@Benchmark
	public void searchLastElement() {
		listOfElements.contains(qtyOfElements);
	}
	
	@Benchmark
	public void searchElementNotExists() {
		listOfElements.contains(qtyOfElements+1);
	}	
	
}
/*
Benchmark                                (qtyOfElements)  Mode  Cnt    Score     Error  Units
ContaisBenchmark.searchElementNotExists              100  avgt    3  148,457 ± 364,746  ns/op
ContaisBenchmark.searchFirstElement                  100  avgt    3    3,115 ±   4,229  ns/op
ContaisBenchmark.searchLastElement                   100  avgt    3  139,409 ± 237,977  ns/op
ContaisBenchmark.searchMiddleElement                 100  avgt    3   77,226 ±  25,344  ns/op
*/