package ro.usv.lab09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BinaryTreePrinter {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static <E extends Comparable<?>> String printNode(Nod<E> root) {
        int maxLevel = BinaryTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        StringBuilder stringBuilder1 = stringBuilder;
        stringBuilder = new StringBuilder();
        return String.valueOf(stringBuilder1);
    }

    private static <E extends Comparable<?>> void printNodeInternal(List<Nod<E>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BinaryTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BinaryTreePrinter.printWhitespaces(firstSpaces);

        List<Nod<E>> newNodes = new ArrayList<>();
        for (Nod<E> node : nodes) {
            if (node != null) {
                //System.out.print(node.info);
                stringBuilder.append(node.info);
                newNodes.add(node.stg);
                newNodes.add(node.dr);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                //System.out.print(" ");
                stringBuilder.append(" ");
            }

            BinaryTreePrinter.printWhitespaces(betweenSpaces);
        }
        //System.out.println();
        stringBuilder.append("\n");

        for (int i = 1; i <= endgeLines; i++) {
            for (Nod<E> node : nodes) {
                BinaryTreePrinter.printWhitespaces(firstSpaces - i);
                if (node == null) {
                    BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (node.stg != null) {
                    //System.out.print("/");
                    stringBuilder.append("/");
                } else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(i + i - 1);

                if (node.dr != null) {
                    //System.out.print("\\");
                    stringBuilder.append("\\");
                } else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            //System.out.println();
            stringBuilder.append("\n");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        stringBuilder.append(" ".repeat(Math.max(0, count)));
    }

    private static <E extends Comparable<?>> int maxLevel(Nod<E> node) {
        if (node == null)
            return 0;

        return Math.max(BinaryTreePrinter.maxLevel(node.stg), BinaryTreePrinter.maxLevel(node.dr)) + 1;
    }

    private static <E> boolean isAllElementsNull(List<E> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
