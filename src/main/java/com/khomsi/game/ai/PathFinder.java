package main.java.com.khomsi.game.ai;

import main.java.com.khomsi.game.main.GameManager;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    GameManager gameManager;
    Node[][] nodes;
    List<Node> openList = new ArrayList<>();
    public List<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GameManager gameManager) {
        this.gameManager = gameManager;
        instantiateNodes();
    }

    public void instantiateNodes() {
        nodes = new Node[GameManager.maxWorldCol][GameManager.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
            nodes[col][row] = new Node(col, row);
            col++;
            if (col == GameManager.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;
        while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
            //reset open, checked and solid states
            nodes[col][row].open = false;
            nodes[col][row].checked = false;
            nodes[col][row].solid = false;
            col++;
            if (col == GameManager.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        //Reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        //Set start, goal node
        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
            //Set solid node
            //Check tiles
            int tileNum = gameManager.tileManager.mapTileNum[gameManager.currentMap][col][row];
            if (gameManager.tileManager.tiles[tileNum].collision) {
                nodes[col][row].solid = true;
            }
            //Check interactive tiles
            for (int i = 0; i < gameManager.interactTile[1].length; i++) {
                if (gameManager.interactTile[gameManager.currentMap][i] != null &&
                        gameManager.interactTile[gameManager.currentMap][i].destructible) {
                    int itCol = gameManager.interactTile[gameManager.currentMap][i].worldX / GameManager.TILE_SIZE;
                    int itRow = gameManager.interactTile[gameManager.currentMap][i].worldY / GameManager.TILE_SIZE;
                    nodes[itCol][itRow].solid = true;
                }
            }
            //Set cost
            getCost(nodes[col][row]);
            col++;
            if (col == GameManager.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node) {
        //get G-COST(the distance from the start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        //H-Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        //F-COST
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;
            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0)
                //open up node
                openNode(nodes[col][row - 1]);
            if (col - 1 >= 0)
                //open left node
                openNode(nodes[col - 1][row]);
            if (col + 1 < GameManager.maxWorldCol)
                //open right node
                openNode(nodes[col + 1][row]);
            if (row + 1 < GameManager.maxWorldRow)
                //open down node
                openNode(nodes[col][row + 1]);

            //Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                //check if this node's F cost is better
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                //if F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            //if there is no node in the openList, end the loop
            if (openList.size() == 0) {
                break;
            }
            //After the loop, we get the best node which is our next step
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    private void trackThePath() {
        //Backtrack and draw the best path
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            //if the node isn't opened, add it to the openList
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
}
