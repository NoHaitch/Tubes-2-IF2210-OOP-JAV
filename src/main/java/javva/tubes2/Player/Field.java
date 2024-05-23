package javva.tubes2.Player;
import java.util.ArrayList;
import java.util.List;

import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Card;
import javva.tubes2.Card.Harvestable;
import javva.tubes2.Card.NullCard;
import javva.tubes2.Card.Product;

public class Field {
    private List<Harvestable> content;
    private int capacity;
    private List<Integer> protect ;

    Field(int cap){
        capacity = cap;
        content = new ArrayList<>(capacity);
        for(int i = 0 ; i < cap ; i++){
            content.add(i, new NullCard());
        }
        protect = new ArrayList<>() ;
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

    public ArrayList<Product> harvestAll(){
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

    public boolean useItem(Card item, int index) throws Throwable{
        // Harvestable target = getElement(index) ;
        if (getElement(index).getName() == "null") {
            throw new IndexInvalid() ;
        }

        if (item.getName() == "Accelerate") {
            if (getElement(index).getType() == "Plant") {
                Plants target = (Plants)getElement(index) ;
                target.setProgress(target.getProgress() + 2) ;
            }
            else {
                Animal target = (Animal)getElement(index) ;
                target.setWeight(target.getWeight() + 5) ;
            }  
            return true ;      
        }
        else if (item.getName() == "Delay") {
            if (getElement(index).getType() == "Plant") {
                Plants target = (Plants)getElement(index) ;
                if (target.getProgress() < 0) {
                    return false ;
                }
                target.setProgress(target.getProgress() - 2) ;
            }
            else {
                Animal target = (Animal)getElement(index) ;
                if (target.getWeight() < 0) {
                    return false ;
                }
                target.setWeight(target.getWeight() - 5) ;
            }  
            return true ;
        }
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