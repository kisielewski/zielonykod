package pl.patkis.zielonykod.onlinegame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OnlinegameSolver {

    LinkedList<LinkedList<Clan>> listOfLists = new LinkedList<>();
    int groupCount;

    public OnlinegameSolver() {}

    public List<List<Clan>> solve(Players players) {
        groupCount = players.groupCount;
        players.clans.sort((c1, c2) -> {
            return c1.numberOfPlayers == c2.numberOfPlayers ? c2.points - c1.points : c1.numberOfPlayers - c2.numberOfPlayers;
        });
        int num = -1;
        LinkedList<Clan> list = new LinkedList<>();
        for (Clan clan : players.clans) {
            if (num != clan.numberOfPlayers) {
                list = new LinkedList<>();
                listOfLists.addLast(list);
                num = clan.numberOfPlayers;
            }
            list.addLast(clan);
        }
        List<List<Clan>> result = new LinkedList<>();
        while (true) {
            List<Clan> group = getGroup();
            if (group.size() == 0) break;
            result.add(group);
        }
        return result;
    }

    private List<Clan> getGroup() {
        LinkedList<Clan> result = new LinkedList<>();
        int count = groupCount;
        while (true) {
            Clan clan = pop(count);
            if (clan == null) break;
            result.addLast(clan);
            count -= clan.numberOfPlayers;
        }
        return result;
    }

    private Clan pop(int numberOfPlayers) {
        Iterator<LinkedList<Clan>> it = listOfLists.iterator();
        if (!it.hasNext()) {
            return null;
        }
        LinkedList<Clan> max = it.next();
        if (max.element().numberOfPlayers > numberOfPlayers) {
            return null;
        }
        while (it.hasNext()) {
            LinkedList<Clan> list = it.next();
            if (list.element().numberOfPlayers > numberOfPlayers) {
                break;
            }
            if (list.element().points > max.element().points) {
                max = list;
            }
        }
        Clan value = max.remove();
        if (max.isEmpty()) {
            listOfLists.remove(max);
        }
        return value;
    }
    
}
