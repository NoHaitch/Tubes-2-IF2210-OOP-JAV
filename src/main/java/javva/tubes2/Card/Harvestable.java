package javva.tubes2.Card;

public interface Harvestable {
    public Product harvest() throws NotReadyToHarvest;
    public Boolean isHarvestReady();
}

class NotReadyToHarvest extends Exception {
    NotReadyToHarvest(){
        super("Object not ready for harvest");
    }
}