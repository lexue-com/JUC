package com.mashibing.juc.c_35_QuestionsOfAlibaba;

/**
 * @auther LXY
 * @create 2022/5/10 15:03
 * @Description TODO
 */
public class ShowMeTheDifference {
    //指出以下两段程序的差别，并分析
//    答案：
//        第二种写法比第一种写法出现不一致性的概率要小，因为我们在方法完成之前，读不到中间状态的脏数据
//        尽量少暴露线程计算过程的中间状态
//        能用范围小的变量，不用范围大的变量
    final class Accumulator {
        private double result = 0.0D;
        public void addAll( double[] values) {
            for(double value : values) {
                result += value;
            }
        }
    }
    final class Accumulator2 {
        private double result = 0.0D;
        public void addAll( double[] values) {
            double sum = 0.0D;
            for(double value : values) {
                sum += value;
            }
            result += sum;
        }
    }
}
