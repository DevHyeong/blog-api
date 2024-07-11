package org.example.springwebtest.leraning.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ParallelLearning {


    @Test
    void test(){
        ExecutorService exexcutor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<>();

    }



    static class ForkJoinSumTask extends RecursiveTask<Integer> {
        static final int SPLIT_THRESHOLD = 10;
        int[] values;
        int startPos;
        int endPos;

        public ForkJoinSumTask(int[] values){
            this(values, 0, values.length);
        }

        public ForkJoinSumTask(int[] values, int startPos, int endPos) {
            this.values = values;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        protected Integer compute() {
            return null;
        }
    }

}
