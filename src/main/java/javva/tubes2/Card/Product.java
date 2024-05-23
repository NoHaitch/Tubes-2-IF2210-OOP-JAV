package javva.tubes2.Card;

public class Product extends Card{
    private Integer addedWeight;
    private Integer addedMoney;

    public Product(String nameString, String tyString, String imgpth, Integer addWeight, Integer addMoney){
        super(nameString, tyString, imgpth);

        addedWeight = addWeight;
        addedMoney = addMoney;
    }

    public Product(Product p) {
        super(p.getName(), p.getType(), p.getPath()) ;

        addedWeight = p.getAddedWeight() ;
        addedMoney = p.getAddedMoney() ;
    }

    public Integer getAddedMoney() {
        return addedMoney;
    }

    public void setAddedMoney(Integer addedMoney) {
        this.addedMoney = addedMoney;
    }

    public Integer getAddedWeight() {
        return addedWeight;
    }

    public void setAddedWeight(Integer addedWeight) {
        this.addedWeight = addedWeight;
    }
}