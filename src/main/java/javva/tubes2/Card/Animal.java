package javva.tubes2.Card;

public class Animal extends Harvestable{
    private Product product;
    private Integer weight;
    private Integer harvestWeight;
    private String activeEffect;

    public Animal(String nameString, String tyString, String imgpth, Product productRet, Integer wInteger, Integer harvbInteger){
        super(nameString, tyString, imgpth, productRet);

        weight = wInteger;
        harvestWeight = harvbInteger;
        activeEffect = "";
    }

    public Animal(Animal a) {
        super(a.getName(), a.getType(), a.getPath(), a.getProduct()) ;

        weight = a.getWeight() ;
        harvestWeight = a.getHarvestWeight() ;
        activeEffect = a.getActiveEffect() ;
    }
    
    public Product getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHarvestWeight() {
        return harvestWeight;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHarvestWeight(Integer harvestWeight) {
        this.harvestWeight = harvestWeight;
    }

    public String getActiveEffect() {
        return activeEffect;
    }

    public void setActiveEffect(String activeEffect) {
        this.activeEffect = activeEffect;
    }

    @Override
    public Product harvest() throws NotReadyToHarvest{
        if(isHarvestReady()){
            return product;
        }
        throw new NotReadyToHarvest();
    }

    @Override
    public Boolean isHarvestReady(){
        return weight >= harvestWeight;
    }

    public Boolean canEat(){
        return !isHarvestReady();
    }

    public void feed(Product feed){
        if(getType().equals("Carnivore")){
            if(feed.getType().equals("Meat")){
                weight += feed.getAddedWeight();
                return;
            }
        }

        if(getType().equals("Herbivore")){
            if(feed.getType().equals("Plants")){
                weight += feed.getAddedWeight();
                return;
            }
        }

        if(feed.getType().equals("Plants") || feed.getType().equals("Meat")){
            weight += feed.getAddedWeight();
        }
    }
}
