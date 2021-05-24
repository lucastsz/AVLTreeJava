package arvoreAVL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Impressao {

    public static <T extends Comparable<?>> void printNode(No root) {
        int maxLevel = Impressao.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<No> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || Impressao.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        Impressao.printWhitespaces(firstSpaces);

        List<No> newNodes = new ArrayList<No>();
        for (No node : nodes) {
            if (node != null) {
                System.out.print(node.dado);
                newNodes.add(node.esquerdo);
                newNodes.add(node.direito);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            Impressao.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Impressao.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    Impressao.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).esquerdo != null)
                    System.out.print("/");
                else
                    Impressao.printWhitespaces(1);

                Impressao.printWhitespaces(i + i - 1);

                if (nodes.get(j).direito != null)
                    System.out.print("\\");
                else
                    Impressao.printWhitespaces(1);

                Impressao.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(No node) {
        if (node == null)
            return 0;

        return Math.max(Impressao.maxLevel(node.esquerdo), Impressao.maxLevel(node.direito)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}