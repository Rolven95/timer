    Simple timer, could mark code chunk time cost with given lable, and sum up all time point as formatted log report.
    
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
    
    Out put will be:
    Performance Report: TimerName:BucketSort{create:2ms; load:22ms; sortBucket:0:13ms; sortBucket:1:14ms; merge:30ms; merge to the end:0ms; total:81ms}
