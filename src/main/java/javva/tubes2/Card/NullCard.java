package javva.tubes2.Card;

public class NullCard extends Harvestable{
    public NullCard(){
        super("null", "null");
    }

    public Boolean isHarvestReady(){
        return false;
    }

    public Product harvest() throws NotReadyToHarvest{
        throw new NotReadyToHarvest();
    }
}