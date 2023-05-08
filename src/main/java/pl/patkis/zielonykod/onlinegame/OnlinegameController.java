package pl.patkis.zielonykod.onlinegame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onlinegame")
public class OnlinegameController {
    
    private final OnlinegameService onlinegameService;

    @Autowired
    public OnlinegameController(OnlinegameService onlinegameService) {
        this.onlinegameService = onlinegameService;
    }

    @PostMapping("/calculate")
    public List<List<Clan>> calculate(@RequestBody Players players) {
        return onlinegameService.groupPlayers(players);
    }

}
