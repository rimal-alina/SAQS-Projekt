package model;

public interface StationInterface {
    // getter
    public String getStationId();
    public String getDate();
    public int getTarget();
    public int getActual();
    public int getVariance();
    // setter for all attributes, which should be editable after inizialisation
    public void setDate(String date);
    public void setActual(int actual);
}
