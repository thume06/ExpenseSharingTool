import java.util.ArrayList;

public class Expense {

    private float cost;
    private double distribution;
    private String category;
    private String payer;
    private ArrayList<String> debtors;

    public Expense(String cat, float c, String p, double d, ArrayList<String> db){
        category = cat;
        cost = c;
        payer = p;
        distribution = d;
        debtors = db;
    }

    //Getters and setters

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public ArrayList<String> getDebtors() {
        return debtors;
    }

    public void setDebtors(ArrayList<String> debtors) {
        this.debtors = debtors;
    }
}
