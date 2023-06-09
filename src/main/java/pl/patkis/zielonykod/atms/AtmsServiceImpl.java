package pl.patkis.zielonykod.atms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AtmsServiceImpl implements AtmsService {

    @Override
    public List<Atm> calculateOrder(List<Task> tasks) {
        Map<Integer, Map<Integer, AtmEx>> maps = new HashMap<>();
        int index = 0;
        for (Task task : tasks) {
            updateATM(maps, task, index);
            index++;
        }
        List<Atm> result = new ArrayList<>(tasks.size());
        List<Integer> regions = new ArrayList<>(maps.keySet());
        regions.sort((r1, r2) -> r1 - r2);
        for (int region : regions) {
            List<AtmEx> list = new ArrayList<>(maps.get(region).values());
            list.sort((a1, a2) -> {
                return a1.priority == a2.priority ? a1.index - a2.index : a1.priority - a2.priority;
            });
            list.forEach((item) -> result.add(item.atm));
        }
        return result;
    }

    private void updateATM(Map<Integer, Map<Integer, AtmEx>> maps, Task task, int index) {
        Map<Integer, AtmEx> map = getRegion(maps, task.region);
        AtmEx item = map.get(task.atmId);
        if (item == null) {
            item = new AtmEx();
            item.index = index;
            item.priority = 4;
            Atm atm = new Atm();
            atm.region = task.region;
            atm.atmId = task.atmId;
            item.atm = atm;
            map.put(task.atmId, item);
        }
        char c = task.requestType.charAt(1);
        int priority = c == 'A' ? 1 : c == 'R' ? 2 : c == 'I' ? 3 : 4;
        if (priority < item.priority) {
            item.priority = priority;
        }
    }

    private Map<Integer, AtmEx> getRegion(Map<Integer, Map<Integer, AtmEx>> maps, int region) {
        Map<Integer, AtmEx> result = maps.get(region);
        if (result == null) {
            result = new HashMap<>();
            maps.put(region, result);
        }
        return result;
    }
    
}
