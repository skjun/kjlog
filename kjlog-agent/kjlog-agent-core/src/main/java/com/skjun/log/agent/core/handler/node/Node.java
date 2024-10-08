package com.skjun.log.agent.core.handler.node;


public class Node {
    private int weight = 100;  // 初始权重 （保持不变）
    private int currentWeight  = 100;// 当前权重

    private Object target;

    public Node(Object target, int weight) {
        this.weight = weight;
        this.currentWeight = weight ;
        this.target = target;
    }

    public Node(Object target) {
        this.target = target;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Object getTarget() {
        return target;
    }
}