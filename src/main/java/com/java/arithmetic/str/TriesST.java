package com.java.arithmetic.str;

/**
 * @author: lifanfan
 * @date: 2019/2/22 10:41
 * @description: 单词查找树算法
 */
public class TriesST<T> {
    /**
     * 基数
     */
    private static int R = 256;
    /**
     * 单词查找树的根节点
     */
    private Node root;

    private static class Node {
        /**
         * 键相关联的值
         */
        private Object val;
        /**
         * 指向其他节点的对象的引用
         */
        private Node[] next = new Node[R];
    }

    public T get(String key) {
        Node node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return (T) node.val;
    }

    /**
     * 返回node作为根节点的子单词查找树中与key相关联的节点
     * @param node
     * @param key
     * @param d
     * @return
     */
    private Node get(Node node, String key, int d) {
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            return node;
        }
        // 键key的第d个字符
        char c = key.charAt(d);
        return get(node.next[c], key, d+1);
    }

    public void put(String key, T t) {
        root = put(root, key, t,0);
    }

    /**
     * 如果key存在于以node为根节点的子单词查找树中，则更新以key的最后一个字符为节点的val值
     * 否则，则将key各个字符组成子单词查找树并插入到node节点中
     * @param node
     * @param key
     * @param t
     * @param d
     * @return
     */
    private Node put(Node node, String key, T t, int d) {
        if (node == null) {
            node = new Node();
        }
        if (d == key.length()) {
            node.val = t;
            return node;
        }
        // 键key的第d个字符
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, t, d+1);
        return node;
    }

    public static void main(String[] args) {
        TriesST<Integer> st = new TriesST<>();
        st.put("sea",2);
        st.put("by",4);
        st.put("sells", 1);
        st.put("she", 0);
        st.put("shells", 3);
        st.put("the", 5);
        System.out.println(st.get("sea"));
        System.out.println(st.get("by"));
        System.out.println(st.get("sells"));
        System.out.println(st.get("she"));
        System.out.println(st.get("shells"));
        System.out.println(st.get("the"));
    }
}
