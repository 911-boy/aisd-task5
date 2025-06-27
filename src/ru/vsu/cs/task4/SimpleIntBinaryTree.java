package ru.vsu.cs.task4;

public class SimpleIntBinaryTree {
    public class SimpleTreeNode {
        public int value;
        public SimpleTreeNode left, right;
        public SimpleTreeNode(int value) { this.value = value; }
    }
    public SimpleTreeNode root = null;

    private static class IndexWrapper { public int index = 0; }

    public void fromBracketNotation(String s) {
        IndexWrapper iw = new IndexWrapper();
        root = fromBracketStr(s, iw);
    }

    private SimpleTreeNode fromBracketStr(String s, IndexWrapper iw) {
        skipSpaces(s, iw);
        if (iw.index >= s.length()) return null;
        int from = iw.index;
        while (iw.index < s.length() && !Character.isWhitespace(s.charAt(iw.index)) && "(),".indexOf(s.charAt(iw.index)) < 0) {
            iw.index++;
        }
        if (from == iw.index) return null;
        int value = Integer.parseInt(s.substring(from, iw.index));
        SimpleTreeNode node = new SimpleTreeNode(value);
        skipSpaces(s, iw);
        if (iw.index < s.length() && s.charAt(iw.index) == '(') {
            iw.index++;
            skipSpaces(s, iw);
            if (iw.index < s.length() && s.charAt(iw.index) != ',') {
                node.left = fromBracketStr(s, iw);
                skipSpaces(s, iw);
            }
            if (iw.index < s.length() && s.charAt(iw.index) == ',') {
                iw.index++;
                skipSpaces(s, iw);
                if (iw.index < s.length() && s.charAt(iw.index) != ')') {
                    node.right = fromBracketStr(s, iw);
                    skipSpaces(s, iw);
                }
            }
            if (iw.index < s.length() && s.charAt(iw.index) == ')') {
                iw.index++;
            }
        }
        return node;
    }

    private void skipSpaces(String s, IndexWrapper iw) {
        while (iw.index < s.length() && Character.isWhitespace(s.charAt(iw.index))) {
            iw.index++;
        }
    }

    public Integer findMaxLeaf() {
        return findMaxLeaf(root);
    }
    private Integer findMaxLeaf(SimpleTreeNode node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) return node.value;
        Integer left = findMaxLeaf(node.left);
        Integer right = findMaxLeaf(node.right);
        if (left == null) return right;
        if (right == null) return left;
        return Math.max(left, right);
    }
}
