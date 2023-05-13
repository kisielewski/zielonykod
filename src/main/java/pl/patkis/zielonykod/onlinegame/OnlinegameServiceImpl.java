package pl.patkis.zielonykod.onlinegame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OnlinegameServiceImpl implements OnlinegameService {

    @Override
    public List<List<Clan>> groupPlayers(Players players) {
        LinkedList<LinkedList<Clan>> listOfLists = new LinkedList<>();
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
            List<Clan> group = getGroup(listOfLists, players.groupCount);
            if (group.size() == 0) break;
            result.add(group);
        }
        return result;
    }

    private List<Clan> getGroup(LinkedList<LinkedList<Clan>> listOfLists, int count) {
        LinkedList<Clan> result = new LinkedList<>();
        while (true) {
            Clan clan = pop(listOfLists, count);
            if (clan == null) break;
            result.addLast(clan);
            count -= clan.numberOfPlayers;
        }
        return result;
    }

    private Clan pop(LinkedList<LinkedList<Clan>> listOfLists, int count) {
        Iterator<LinkedList<Clan>> it = listOfLists.iterator();
        if (!it.hasNext()) {
            return null;
        }
        LinkedList<Clan> max = it.next();
        if (max.element().numberOfPlayers > count) {
            return null;
        }
        while (it.hasNext()) {
            LinkedList<Clan> list = it.next();
            if (list.element().numberOfPlayers > count) {
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
