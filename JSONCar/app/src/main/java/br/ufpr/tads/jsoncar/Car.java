package br.ufpr.tads.jsoncar;

public class Car {
    private int id;
    private String model, sign;

    public Car(int id, String model, String sign) {
        this.id = id;
        this.model = model;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
