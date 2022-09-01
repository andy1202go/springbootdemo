package com.example.demo.core.util;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * 秒表测试
 * https://mp.weixin.qq.com/s/qI9IwSijXewlh8OHOTcstg
 *
 * @author liangbo
 * @date 2022/08/30
 */

@Slf4j
public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 传统计时方式
         */
        Long startTime = System.currentTimeMillis();
        // 你的业务代码
        Long endTime = System.currentTimeMillis();
        Long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("[传统计时]该段总共耗时：" + elapsedTime + "s");

        springStopWatchTest();

        apacheStopWatchTest();

        guavaStopWatchTest();
    }

    private static void springStopWatchTest() throws InterruptedException {
        /**
         * Spring的StopWatch
         */
        StopWatch stopWatch = new StopWatch();

        // 任务一模拟休眠1秒钟
        stopWatch.start("TaskOneName");
        Thread.sleep(1000 * 1);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务二模拟休眠2秒钟
        stopWatch.start("TaskTwoName");
        Thread.sleep(1000 * 2);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务三模拟休眠3秒钟
        stopWatch.start("TaskThreeName");
        Thread.sleep(1000 * 3);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

//        stopWatch.start(null);
//        Thread.sleep(1000 * 3);
//        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
//        stopWatch.stop();

        // 打印出耗时
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());
        // stop后它的值为null
        System.out.println(stopWatch.currentTaskName());

        // 最后一个任务的相关信息
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskInfo());

        // 任务总的耗时 如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
        System.out.println("所有任务总耗时：" + stopWatch.getTotalTimeMillis());
        System.out.println("任务总数：" + stopWatch.getTaskCount());
        System.out.println("所有任务详情：" + stopWatch.getTaskInfo());
    }

    private static void apacheStopWatchTest() throws InterruptedException {
        /**
         * Apache
         */
        //创建后立即start，常用
        org.apache.commons.lang3.time.StopWatch watch = org.apache.commons.lang3.time.StopWatch.createStarted();

        // StopWatch watch = new StopWatch();
        // watch.start();

        Thread.sleep(1000);
        System.out.println(watch.getTime());
        System.out.println("统计从开始到现在运行时间：" + watch.getTime() + "ms");

        Thread.sleep(1000);
        watch.split();
        System.out.println("从start到此刻为止的时间：" + watch.getTime());
        System.out.println("从开始到第一个切入点运行时间：" + watch.getSplitTime());


        Thread.sleep(1000);
        watch.split();
        System.out.println("从开始到第二个切入点运行时间：" + watch.getSplitTime());

//        // 复位后, 重新计时
//        watch.reset();
//        watch.start();
//        Thread.sleep(1000);
//        System.out.println("重新开始后到当前运行时间是：" + watch.getTime());

        // 暂停 与 恢复
        watch.suspend();
        System.out.println("暂停2秒钟");
        Thread.sleep(2000);

        System.out.println("从开始到第二个切入点运行时间：" + watch.getSplitTime());

        // 上面suspend，这里要想重新统计，需要恢复一下
        watch.resume();
        System.out.println("恢复后执行的时间是：" + watch.getTime());

        Thread.sleep(1000);
        watch.stop();

        System.out.println("花费的时间》》" + watch.getTime() + "ms");
        // 直接转成s
        System.out.println("花费的时间》》" + watch.getTime(TimeUnit.SECONDS) + "s");

        System.out.println("花费的时间》》" + watch.toString());
    }


    private static void guavaStopWatchTest() throws InterruptedException {
        Stopwatch gsw = Stopwatch.createStarted();
        Thread.sleep(1000);
        gsw.stop();
        System.out.println("gsw 记录时间："+gsw.elapsed());
    }
}
