package javva.tubes2.Player;
import java.util.ArrayList;

import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Card;
import javva.tubes2.Card.Harvestable;
import javva.tubes2.Card.NullCard;
import javva.tubes2.Card.Product;

public class Field {
    private ArrayList<Harvestable> content;
    private int capacity;

    Field(int cap){
        capacity = cap;
        content = new ArrayList<>(capacity);
        for(int i = 0 ; i < cap ; i++){
            content.add(i, new NullCard());
        }
    }

    public Harvestable getElement(int index) throws Throwable{
        if(index >= capacity || index < 0){
            throw new IndexOutOfRange();
        }

        if(content.get(index).getName() == "null"){
            throw new EmptyElement();
        }
        return content.get(index);
    }

    public void addElement(Harvestable addition, int index) throws Throwable {
        if(index >= capacity || index < 0){
            throw new IndexOutOfRange();
        }    
        if(content.get(index).getName() != "null"){
            throw new EmptyElement();
        }
        content.set(index, addition) ;
    }

    public void removeElement(int index) throws Throwable{
        if(index >= capacity || index < 0){
            throw new IndexOutOfRange();
        }

        if(content.get(index).getName() == "null"){
            throw new EmptyElement();
        }
        content.set(index, new NullCard());
    }

    public Product getAndRemove(int index) throws Throwable{
        if(index >= capacity || index < 0){
            throw new IndexOutOfRange();
        }

        if(content.get(index).getName().equals("null")){
            throw new EmptyElement();
        }
        Product ret = getElement(index).getProduct();
        removeElement(index);

        return ret;
    }

    ArrayList<Product> harvestAll(){
        ArrayList<Product> harvestret = new ArrayList<>();

        for(int i = 0 ; i < capacity ; i++){
            if(!(content.get(i) instanceof NullCard)){
                if(content.get(i).isHarvestReady()){
                    try {
                        harvestret.add(this.getAndRemove(i));
                    } catch (Throwable e) {

                    }
                }
            }
        }
        return harvestret;
    }
}

class IndexOutOfRange extends Throwable{
    public IndexOutOfRange(){
        super("Index out of range");
    }
}

class EmptyElement extends Throwable{
    public EmptyElement(){
        super("Empty element");
    }
}