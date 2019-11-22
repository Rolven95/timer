package com.github.Rolven95.timer;

import org.junit.Test;

public class TimeRecorderTest {

    /**
     * Full example
     * <p>
     * Output:Performance Report: TimerName:BucketSort{create:2ms; load:22ms; sortBucket:0:13ms; sortBucket:1:14ms; merge:30ms; merge to the end:0ms; total:81ms}
     */
    @Test
    public void example() {
        TimeRecorder time = new TimeRecorder("BucketSort");

        fakeCreateBuckets();
        time.record("create");

        fakeLoadBucket();
        time.record("load");

        for (int i = 0; i < 2; i++) {
            fakeSortBucket();
            time.record("sortBucket:" + i);
        }

        fakeMergeBuckets();
        time.record("merge");

        System.out.println(time.onTaskEndAndReturnReport());
    }

    /**
     * Total cost 13ms
     */
    @Test
    public void benchMark() {
        TimeRecorder time = new TimeRecorder("benchMark");
        for (int i = 0; i < 10000; i++) {
            time.record("this is a very long lable, no:" + i);
        }
        System.out.println(time.onTaskEndAndReturnReport());
    }

    private void fakeCreateBuckets() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fakeLoadBucket() {
        try {
            Thread.sleep(22);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fakeSortBucket() {
        try {
            Thread.sleep(13);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fakeMergeBuckets() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}