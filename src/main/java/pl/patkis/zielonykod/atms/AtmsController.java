package pl.patkis.zielonykod.atms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atms")
public class AtmsController {

    private final AtmsService atmsService;

    @Autowired
    public AtmsController(AtmsService atmsService) {
        this.atmsService = atmsService;
    }

    @PostMapping("/calculateOrder")
    public List<Atm> calculateOrder(@RequestBody List<Task> tasks) {
        return atmsService.calculateOrder(tasks);
    }
    
}
