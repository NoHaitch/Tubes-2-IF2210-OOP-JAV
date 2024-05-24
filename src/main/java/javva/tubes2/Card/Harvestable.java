package javva.tubes2.Card;

public class Harvestable extends Card{
    private Product product;
    private Boolean attacked;
    private Boolean protect;
    private Boolean trap;
    
    public Harvestable(String nameString, String tyString){
        super(nameString, tyString, "");
        product = new Product(nameString, tyString, tyString, null, null);
        protect = false;
        trap = false;
    }

    public Harvestable(String nameString, String tyString, String imgString, Product prod){
        super(nameString, tyString, imgString);
        product = prod;
        protect = false;
        trap = false;
    }

    public Harvestable(String nameString, String tyString, String imgPath, Product prod, Boolean protect, Boolean trap){
        super(nameString, tyString, imgPath);
        product = prod;
        this.protect = protect;
        this.trap = trap;

    }

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