package javva.tubes2.Card;

public class Harvestable extends Card{
    private Product product;
    private Boolean protected_flag;
    private Boolean attacked;
    
    public Harvestable(String nameString, String tyString, String imgPath, Product prod){
        super(nameString, tyString, imgPath);
        product = prod;
        protected_flag = false;
        attacked = false;
    }

    public Harvestable(String nameString, String tyString){
        super(nameString, tyString, "");
        product = new Product(nameString, tyString, tyString, null, null);
        protected_flag = false;
    }

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

    public Boolean getProtectedFlag() {
        return protected_flag;
    }

    public void setProtectedFlag(Boolean protected_flag) {
        this.protected_flag = protected_flag;
    }

    public Boolean getAttacked() {
        return attacked;
    }

    public void setAttacked(Boolean attacked) {
        this.attacked = attacked;
    }
}

class NotReadyToHarvest extends Exception {
    NotReadyToHarvest(){
        super("Object not ready for harvest");
    }
}