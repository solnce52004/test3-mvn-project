package dev.example.beans.details;

public class DetailAbstract implements DetailInterface {
    private String detailName;
    private String action;

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public void action() {
        System.out.printf(
                "%s %s%n",
                getDetailName(),
                getAction()
        );
    }

    @Override
    public String getBeanName() {
        return getDetailName();
    }
}
