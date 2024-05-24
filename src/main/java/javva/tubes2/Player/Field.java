package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.Random;

import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Harvestable;
import javva.tubes2.Card.NullCard;
import javva.tubes2.Card.Product;

import java.lang.Thread;

public class Field extends Thread{
    public ArrayList<Harvestable> content;
    private int capacity;
    private Boolean bear_attack;
    public ArrayList<Integer> available_integer;
    
    Field(int cap){
        capacity = cap;
        content = new ArrayList<>(capacity);
        for(int i = 0 ; i < cap ; i++){
            content.add(i, new NullCard());
        }
        bear_attack = false;
        available_integer = generateAvailInteger();
    }

    Field(){
        capacity = 20;
        content = new ArrayList<>(capacity);
        for(int i = 0 ; i < 20 ; i++){
            content.add(i, new NullCard());
        }
        bear_attack = false;
        available_integer = generateAvailInteger();
    }

    public synchronized Harvestable getElement(int index) throws Throwable{
        if(index >= capacity || index < 0){
            throw new IndexOutOfRange();
        }

        if(content.get(index).getName().equals("null")){
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

        if(content.get(index).getName().equals("null")){
            throw new EmptyElement();
        }
        content.set(index, new NullCard());
    }

    public synchronized Product getAndRemove(int index) throws Throwable{
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

    private ArrayList<Integer> generateAvailInteger(Integer row, Integer column){
        ArrayList<Integer> ret = new ArrayList<>();
        
        for(int i = 0 ; i <= row - 2; i++){
            for(int j = 0 ; j <= column - 3 ; j++){
                ret.add(j + i*column);
            }
        }
        
        return ret;
    }

    private ArrayList<Integer> generateAvailInteger(){
        return generateAvailInteger(4,5);
    }

    private ArrayList<Integer> getBearGrid(){
        ArrayList<Integer> ret = new ArrayList<>();
        Integer range = available_integer.size();

        Integer index = new Random().nextInt(range);
        index = available_integer.get(index);

        for(int i = 0 ; i < 2 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                ret.add(index + i*5 + j);
            }
        }

        return ret;
    }

    public synchronized void initiateBearEvent(){
        bear_attack = true;
        System.out.println("Attack begin");

        ArrayList<Integer> attack_zone = getBearGrid();

        try {
            Thread.sleep(4*1000);
        } catch (Throwable e){

        }
        
        bearDestroy(attack_zone);
        System.out.println("Attack finished");
        bear_attack = false;
        notifyAll();
    }

    public void run(){
        initiateBearEvent();
    }

    public void bearDestroy(ArrayList<Integer> destroy_zone){
        System.out.println(destroy_zone);
        for(int i = 0 ; i < destroy_zone.size() ; i++){
            if(!content.get(destroy_zone.get(i)).getName().equals("null")){
                try {
                    removeElement(destroy_zone.get(i));
                    System.out.println(getElement(destroy_zone.get(i)));
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public synchronized void printContent(){

        while (bear_attack == true) {
            try {
                wait();
            } catch (Throwable e){

            }
        }
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                System.out.print(content.get(i*5 + j).getName().substring(0,3) + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String args[]){
        Field yoi = new Field();

        for(int i = 0 ; i < 20 ; i++){
            try{
                yoi.addElement(new Animal("aaa", "aaa", "aaaa", null, 3,4), i);
            } catch(Throwable e){
                System.out.println(e.getMessage());
            }
        }
        try {
            // yoi.removeElement(0);

        } catch (Throwable e){

        }
        // yoi.printContent();

        yoi.start();

        try {
            Thread.sleep(20);
        } catch (Throwable e){

        }
        
        yoi.printContent();

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