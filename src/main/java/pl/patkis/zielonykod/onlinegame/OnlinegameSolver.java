package pl.patkis.zielonykod.onlinegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.patkis.zielonykod.util.IteratedLinkedList;
import pl.patkis.zielonykod.util.Iterator;

public class OnlinegameSolver {

    int groupCount;
    IteratedLinkedList<IteratedLinkedList<Clan>> listOfLists;

    public OnlinegameSolver() {}

    public List<List<Clan>> solve(Players players) {
        prepareList(players);
        List<List<Clan>> result = new LinkedList<>();
        while (true) {
            List<Clan> group = getGroup();
            if (group.size() == 0) break;
            result.add(group);
        }
        return result;
    }

    private void prepareList(Players players) {
        players.clans.sort((c1, c2) -> {
            return c1.points == c2.points ? c1.numberOfPlayers - c2.numberOfPlayers : c2.points - c1.points;
        });
        Map<Integer, IteratedLinkedList<Clan>> map = new HashMap<>();
        for (Clan clan : players.clans) {
            IteratedLinkedList<Clan> list = map.get(clan.numberOfPlayers);
            if (list == null) {
                list = new IteratedLinkedList<>();
                map.put(clan.numberOfPlayers, list);
            }
            list.add(clan);
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort(null);
        groupCount = players.groupCount;
        listOfLists = new IteratedLinkedList<>();
        for (Integer key : keys) {
            listOfLists.add(map.get(key));
        }
    }

    private List<Clan> getGroup() {
        List<Clan> result = new LinkedList<>();
        int count = groupCount;
        while (true) {
            Clan clan = pop(count);
            if (clan == null) break;
            result.add(clan);
            count -= clan.numberOfPlayers;
        }
        return result;
    }

    private Clan pop(int numberOfPlayers) {
        Iterator<IteratedLinkedList<Clan>> maxIt = listOfLists.begin();
        if (maxIt == listOfLists.end() || maxIt.value().begin().value().numberOfPlayers > numberOfPlayers) {
            return null;
        }
        for (Iterator<IteratedLinkedList<Clan>> it = maxIt.next(); it != listOfLists.end(); it = it.next()) {
            if (it.value().begin().value().numberOfPlayers > numberOfPlayers) {
                break;
            }
            if (it.value().begin().value().points > maxIt.value().begin().value().points) {
                maxIt = it;
            }
        }
        Clan value = maxIt.value().begin().value();
        maxIt.value().remove(maxIt.value().begin());
        if (maxIt.value().isEmpty()) {
            listOfLists.remove(maxIt);
        }
        return value;
    }
    
}
