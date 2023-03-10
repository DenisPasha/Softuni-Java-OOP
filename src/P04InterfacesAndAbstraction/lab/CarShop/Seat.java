package P04InterfacesAndAbstraction.lab.CarShop;

public class Seat implements Car{

   private String model;
    private String color;
    private int horsePower;
    private String countryProduced;

    public Seat(String model, String color, int horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    public String toString(){
        return String.format("This is %s produced in %s and have %d tires",this.getModel(),this.countryProduced(),this.TIRES);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }
}
