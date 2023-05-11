package pl.patkis.zielonykod.onlinegame;

import java.util.LinkedList;
import java.util.List;

import pl.patkis.zielonykod.util.IteratedLinkedList;
import pl.patkis.zielonykod.util.Iterator;

public class OnlinegameSolver {

    private IteratedLinkedList<Clan> iteratedLinkedList;
    private Iterator<Clan> current;
    private int groupCount;
    private int currentCount;
    private int maximum;

    OnlinegameSolver(Players players) {
        groupCount = players.groupCount;
        maximum = 0;
        players.clans.sort((c1, c2) -> {
            return c1.points == c2.points ? c1.numberOfPlayers - c2.numberOfPlayers : c2.points - c1.points;
        });
        iteratedLinkedList = new IteratedLinkedList<>(players.clans);
    }

    public List<List<Clan>> solve() {
        List<List<Clan>> result = new LinkedList<>();
        while (true) {
            List<Clan> group = getGroup();
            if (group.size() == 0) break;
            result.add(group);
        }
        return result;
    }

    private List<Clan> getGroup() {
        List<Clan> result = new LinkedList<>();
        reset();
        while (true) {
            Clan clan = pop();
            if (clan == null) break;
            result.add(clan);
            if (maximum >= currentCount) break;
        }
        if (maximum < currentCount) {
            maximum = currentCount;
        }
        return result;
    }

    private Clan pop() {
        Iterator<Clan> it = current;
        while (it != iteratedLinkedList.end()) {
            if (it.value().numberOfPlayers <= currentCount) {
                current = iteratedLinkedList.remove(it);
                currentCount -= it.value().numberOfPlayers;
                return it.value();
            }
            it = it.next();
        }
        return null;
    }

    private void reset() {
        current = iteratedLinkedList.begin();
        currentCount = groupCount;
    }
    
}
