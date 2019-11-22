package com.github.Rolven95.timer;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author ruowen chengruowen@vip.qq.com
 * @title: TimeRecorder
 * @projectName common_utils
 * @description: Record all steps' time cost and given label for performance debug log.
 * @date 2019/5/9 18:26
 */
public class TimeRecorder {
    private String name; // Name of this recorder.
    private ArrayList<String> label = new ArrayList<>(); // label name for a code chunk
    private ArrayList<Long> timing = new ArrayList<>(); // time cost for relating labeled chunk

    /**
     * Must give an init name for each timer.
     */
    private TimeRecorder() {
    }

    /**
     * Once the timer been initialized, timer runs.
     *
     * @param recorderName Normally we can use method name for this.
     */
    public TimeRecorder(String recorderName) {
        if (StringUtils.isEmpty(recorderName)) {
            throw new IllegalArgumentException("Name could not be empty!");
        }
        this.name = recorderName;
        label.add("Start");
        timing.add(System.currentTimeMillis());
    }

    /**
     * Mark down the time cost after last record time(after timer started if first time call this method)
     *
     * @param labelName label name
     */
    public void record(String labelName) {
        if (StringUtils.isEmpty(labelName)) {
            throw new IllegalArgumentException("Empty String");
        }
        label.add(labelName);
        timing.add(System.currentTimeMillis());
    }

    /**
     * Stop the timer and return formatted report. Change log format if necessary.
     * e.g.: log.info(timer.onTaskEndAndReturnReport());
     */
    public String onTaskEndAndReturnReport() {
        int keySetSize = label.size();
        StringBuilder reportToReturn = new StringBuilder();

        // 报告开头
        reportToReturn.append("Performance Report: TimerName:").append(name).append("{");

        // 添加点数据
        for (int i = 1; i < keySetSize; i++) { // 中间点
            reportToReturn.append(label.get(i)).append(":").append(timing.get(i) - timing.get(i - 1)).append("ms; ");
        }

        String lastPointName = label.get(keySetSize - 1);
        long lastPoint = timing.get(keySetSize - 1);
        reportToReturn.append(lastPointName).append(" to the end").append(":").append(System.currentTimeMillis() - lastPoint).append("ms; ");
        reportToReturn.append("total:").append(System.currentTimeMillis() - timing.get(0)).append("ms");
        reportToReturn.append("}");
        return reportToReturn.toString();
    }
}