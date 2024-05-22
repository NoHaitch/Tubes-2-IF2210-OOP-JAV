package javva.tubes2.Card;

public class Product extends Card{
    private Integer addedWeight;
    private Integer addedMoney;

    Product(String nameString, String tyString, String imgpth, Integer addWeight, Integer addMoney){
        super(nameString, tyString, imgpth);

        addedWeight = addWeight;
        addedMoney = addMoney;
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