package dev.example.beans.details;

public class Head implements DetailInterface{

    public static final String BEAN_NAME = "headBean";

    private  String detailName = "head";
    private  String action = "think";

    public Head(){
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getName() {
        return detailName;
    }

    @Override
    public void action() {
        System.out.printf("%s %s%n", getName(), action);
    }

    @Override
    public String getBeanName() {
        return getName();
    }
}
