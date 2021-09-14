package dev.example.beans.robots;

public abstract class T1000FactoryAbstract implements RobotFactoryInterface {

    /**
     * needed CGLIB
     * @return
     */
    @Override
    public abstract RobotInterface createRobot();
}
