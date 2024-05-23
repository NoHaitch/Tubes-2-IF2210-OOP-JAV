package javva.tubes2.Card;

public class Harvestable extends Card{
    private Product product;
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Product harvest() throws NotReadyToHarvest{
        throw new NotReadyToHarvest();
    };
    public Boolean isHarvestReady(){
        return false;
    }

    public Harvestable(String nameString, String tyString, String imgPath, Product prod){
        super(nameString, tyString, imgPath);
        product = prod;
    }

    public Harvestable(String nameString, String tyString){
        super(nameString, tyString, "");
        product = new Product(nameString, tyString, tyString, null, null);
    }
}

class NotReadyToHarvest extends Exception {
    NotReadyToHarvest(){
        super("Object not ready for harvest");
    }
}