package pl.patkis.zielonykod.onlinegame;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OnlinegameServiceImpl implements OnlinegameService {

    @Override
    public List<List<Clan>> groupPlayers(Players players) {
        OnlinegameSolver solver = new OnlinegameSolver();
        return solver.solve(players);
    }
    
}
