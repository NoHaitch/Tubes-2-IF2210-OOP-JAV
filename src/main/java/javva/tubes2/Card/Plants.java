package javva.tubes2.Card;

public class Plants extends Harvestable{
    private Integer progress;
    private Integer harvestLimit;

    public Plants(String nameString, String tyString, String imgpth, Product retProduct, Integer harvestLim){
        super(nameString, tyString, imgpth, retProduct);
        progress = 0;
        harvestLimit = harvestLim;
    }

    public Plants(String nameString, String tyString, String imgpth, Product retProduct, Integer harvestLim, boolean protect, boolean trap){
        super(nameString, tyString, imgpth, retProduct, protect, trap);
        progress = 0;
        harvestLimit = harvestLim;
    }

    public Plants(Plants p) {
        super(p.getName(), p.getType(), p.getPath(), p.getProduct()) ;

        progress = p.getProgress() ;
        harvestLimit = p.getHarvestLimit() ;
    }
    public Integer getHarvestLimit() {
        return harvestLimit;
    }

    public void setHarvestLimit(Integer harvestLimit) {
        this.harvestLimit = harvestLimit;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public Boolean isHarvestReady(){
        return progress >= harvestLimit;
    }

    @Override
    public Product harvest() throws NotReadyToHarvest{
        if(isHarvestReady()){
            return getProduct();
        }
        throw new NotReadyToHarvest();
    }

    public void grow(){
        progress++;
    }
}
