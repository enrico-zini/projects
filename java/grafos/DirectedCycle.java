import java.util.HashMap;
import java.util.Map;

public class DirectedCycle {

    private enum Mark {
        WHITE, GRAY, BLACK
    };

    private Digraph g;
    private Map<String, Mark> marked;
    private boolean hasCycle;

    public DirectedCycle(Digraph g) {
        this.g = g;
        marked = new HashMap<>();
        hasCycle = false;
        for (String v : g.getVerts())
            marked.put(v, Mark.WHITE);
        for (String v : g.getVerts()) {
            if (marked.get(v) == Mark.WHITE) {
                hasCycle = containsCycle(v);
                if (hasCycle)
                    break;
            }
        }
    }

    public boolean containsCycle() {
        return hasCycle;
    }

    private boolean containsCycle(String v) {
        marked.put(v, Mark.GRAY);
        for (String w : g.getAdj(v)) {
            if (marked.get(w) == Mark.GRAY)
                return true;
            else if (marked.get(w) == Mark.WHITE) {
                if (containsCycle(w))
                    return true;
            }
        }
        marked.put(v, Mark.BLACK);
        return false;
    }
}