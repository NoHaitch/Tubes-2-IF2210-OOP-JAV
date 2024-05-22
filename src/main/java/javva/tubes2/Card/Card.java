package javva.tubes2.Card;

public class Card {
    private String name;
    private String type;
    private String imagepath;

    public Card(String nameString, String tyString, String imgpth){
        name = nameString;
        type = tyString;
        imagepath = imgpth;
    }

    public Card(String nameString, String tyString){
        name = nameString;
        type = tyString;
        imagepath = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
