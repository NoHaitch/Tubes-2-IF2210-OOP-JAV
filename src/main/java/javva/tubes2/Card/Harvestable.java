package javva.tubes2.Card;

public class Harvestable extends Card{
    private Product product;
    private boolean protect ;
    private boolean trap ;

    public boolean getTrap() {
        return this.trap ;
    }

    public void setTrap(boolean trap) {
        this.trap = trap ;
    }

    public boolean getProtect() {
        return this.protect ;
    }

    public void setProtect(boolean protect) {
        this.protect = protect;
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

    public Harvestable(String nameString, String tyString, String imgPath, Product prod){
        super(nameString, tyString, imgPath);
        product = prod;
        this.protect = false ;
        this.trap = false ;
    }

    public Harvestable(String nameString, String tyString, String imgPath, Product prod, boolean protect, boolean trap){
        super(nameString, tyString, imgPath);
        product = prod;
        this.protect = protect ;
        this.trap = trap ;
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