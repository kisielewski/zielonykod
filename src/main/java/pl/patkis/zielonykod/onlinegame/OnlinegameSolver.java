package pl.patkis.zielonykod.onlinegame;

import java.util.LinkedList;
import java.util.List;

import pl.patkis.zielonykod.util.IteratedLinkedList;
import pl.patkis.zielonykod.util.Iterator;

public class OnlinegameSolver {

    private IteratedLinkedList<Clan> _iteratedLinkedList;
    private Iterator<Clan> _current;
    private int _groupCount;
    private int _currentCount;
    private int _maximum;

    OnlinegameSolver(Players players) {
        _groupCount = players.groupCount;
        _maximum = 0;
        players.clans.sort((c1, c2) -> {
            return c1.points == c2.points ? c1.numberOfPlayers - c2.numberOfPlayers : c2.points - c1.points;
        });
        _iteratedLinkedList = new IteratedLinkedList<>(players.clans);
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
            if (_maximum >= _currentCount) break;
        }
        if (_maximum < _currentCount) {
            _maximum = _currentCount;
        }
        return result;
    }

    private Clan pop() {
        Iterator<Clan> it = _current;
        while (it != null) {
            if (it.value().numberOfPlayers <= _currentCount) {
                _current = _iteratedLinkedList.remove(it);
                _currentCount -= it.value().numberOfPlayers;
                return it.value();
            }
            it = it.next();
        }
        return null;
    }

    private void reset() {
        _current = _iteratedLinkedList.begin();
        _currentCount = _groupCount;
    }
    
}
