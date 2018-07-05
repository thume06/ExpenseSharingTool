public class Expense {

    private float cost;
    private float[] distribution;
    private String[] people;

    public Expense(float c, float[] d, String[] p){
        cost = c;
        distribution = d;
        people = p;
    }

    //Getters and setters

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float[] getDistribution() {
        return distribution;
    }

    public void setDistribution(float[] distribution) {
        this.distribution = distribution;
    }

    public String[] getPeople() {
        return people;
    }

    public void setPeople(String[] people) {
        this.people = people;
    }
}
