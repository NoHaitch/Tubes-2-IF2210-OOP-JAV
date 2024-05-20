package javva.tubes2.Card;

public class Plants extends Card implements Harvestable{
    private Product product;
    private Integer progress;
    private Integer harvestLimit;

    Plants(String nameString, String tyString, Product retProduct, Integer harvestLim){
        super(nameString, tyString);
        product = retProduct;
        progress = 0;
        harvestLimit = harvestLim;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Boolean isHarvestReady(){
        return progress >= harvestLimit;
    }

    public Product harvest() throws NotReadyToHarvest{
        if(isHarvestReady()){
            return product;
        }
        throw new NotReadyToHarvest();
    }

    public void grow(){
        progress++;
    }
}
