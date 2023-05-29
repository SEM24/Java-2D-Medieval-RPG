package com.khomsi.game.ai;

public class Node {
    Node parent;
    public int col;
    public int row;
    int gCost, hCost, fCost;
    boolean solid, open, checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
