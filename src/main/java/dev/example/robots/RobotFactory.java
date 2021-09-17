package dev.example.robots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RobotFactory {
    List<RobotInterface> robots;

    @Autowired
    public RobotFactory(List<RobotInterface> robots) {
        this.robots = robots;
        System.out.println("Factory: ******** " + this);
        createDoubles();
    }

    private void createDoubles(){
        for (RobotInterface robot: robots) {
            robot.getInstance();
            robot.getInstance();
        }
    }
}
