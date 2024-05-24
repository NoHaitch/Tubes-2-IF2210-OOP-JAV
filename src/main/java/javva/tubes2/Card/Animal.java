package javva.tubes2.Card;

public class Animal extends Harvestable{
    private Integer weight;
    private Integer harvestWeight;

    public Animal(String nameString, String tyString, String imgpth, Product productRet, Integer wInteger, Integer harvbInteger){
        super(nameString, tyString, imgpth, productRet);

        weight = wInteger;
        harvestWeight = harvbInteger;
    }

    public Animal(String nameString, String tyString, String imgpth, Product productRet, Integer wInteger, Integer harvbInteger, boolean protect, boolean trap){
        super(nameString, tyString, imgpth, productRet, protect, trap);

        weight = wInteger;
        harvestWeight = harvbInteger;
    }

    public Animal(Animal a) {
        super(a.getName(), a.getType(), a.getPath(), a.getProduct()) ;

        weight = a.getWeight() ;
        harvestWeight = a.getHarvestWeight() ;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHarvestWeight() {
        return harvestWeight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHarvestWeight(Integer harvestWeight) {
        this.harvestWeight = harvestWeight;
    }

    @Override
    public Product harvest() throws NotReadyToHarvest{
        if(isHarvestReady()){
            return product ;
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
            if(feed.getType().equals("Vegetable")){
                weight += feed.getAddedWeight();
                return;
            }
        }

        if(feed.getType().equals("Vegetable") || feed.getType().equals("Meat")){
            weight += feed.getAddedWeight();
        }
    }
}
