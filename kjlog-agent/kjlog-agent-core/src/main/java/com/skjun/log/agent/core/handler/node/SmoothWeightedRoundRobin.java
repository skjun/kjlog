package com.skjun.log.agent.core.handler.node;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SmoothWeightedRoundRobin {
    private volatile List<Node> nodeList = new ArrayList<>(); // 保存权重
    private ReentrantLock lock = new ReentrantLock();


    public SmoothWeightedRoundRobin() {
    }

    /**
     * 增加node节点
     *
     * @param node
     */
    public void addNode(Node node) {
        try {
            lock.lock();
            nodeList.add(node);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 清空节点信息
     */
    public void clearNode() {
        try {
            lock.lock();
            nodeList.clear();
        } finally {
            lock.unlock();
        }
    }

    public Node select() {
        try {
            lock.lock();
            if (nodeList != null && !nodeList.isEmpty()) {
                return this.selectInner();
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    private Node selectInner() {
        try {
            lock.lock();

            if (nodeList == null || nodeList.isEmpty()) {
                return null;
            }

            int totalWeight = 0;
            Node maxNode = null;
            int maxWeight = 0;

            for (int i = 0; i < nodeList.size(); i++) {
                Node n = nodeList.get(i);
                totalWeight += n.getWeight();

                // 每个节点的当前权重要加上原始的权重
                n.setCurrentWeight(n.getCurrentWeight() + n.getWeight());

                // 保存当前权重最大的节点
                if (maxNode == null || maxWeight < n.getCurrentWeight()) {
                    maxNode = n;
                    maxWeight = n.getCurrentWeight();
                }
            }
            // 被选中的节点权重减掉总权重
            maxNode.setCurrentWeight(maxNode.getCurrentWeight() - totalWeight);
//        nodeList.forEach(node -> System.out.print(node.getCurrentWeight()));
            return maxNode;
        } finally {
            lock.unlock();
        }
    }

    public List<Node> getNodeList() {
        try {
            lock.lock();
            return nodeList;
        } finally {
            lock.unlock();
        }
    }

}