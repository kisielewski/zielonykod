package pl.patkis.zielonykod.atms;

import java.util.List;

public interface AtmsService {
    List<Atm> calculateOrder(List<Task> tasks);
}
