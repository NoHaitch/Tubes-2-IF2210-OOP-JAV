package javva.tubes2.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javva.tubes2.Card.*;
import java.lang.Thread;

public class Field extends Thread{
    public ArrayList<Harvestable> content;
    private int capacity;
    private Boolean bear_attack;
    public ArrayList<Integer> available_integer;
    private List<Integer> protect ;
    
    Field(int cap){
        capacity = cap;
        content = new ArrayList<>(capacity);
        for(int i = 0 ; i < cap ; i++){
            content.add(i, new NullCard());
        }
        protect = new ArrayList<>() ;
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
        protect = new ArrayList<>() ;
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
        for(int i = 0 ; i < protect.size() ; i++){
            if(protect.get(i) == index){
                protect.remove(i);
            }
        }
    }

    public ArrayList<Harvestable> getContent() {
        return content;
    }

    public void setContent(ArrayList<Harvestable> content) {
        this.content = content;
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

    public boolean useAccelerate(int field_index) throws Throwable{
        if (getElement(field_index).getType().equals("Plant")) {
            Plants target = (Plants)getElement(field_index) ;
            target.setProgress(target.getProgress() + 2) ;
        }
        else {
            Animal target = (Animal)getElement(field_index) ;
            target.setWeight(target.getWeight() + 5) ;
        }  
        return true ; 
    }

    public boolean useDelay(int field_index) throws Throwable {
        if (getElement(field_index).getType().equals("Plant")) {
            Plants target = (Plants)getElement(field_index) ;
            if (target.getProgress() < 0) {
                return false ;
            }
            target.setProgress(target.getProgress() - 2) ;
        }
        else {
            Animal target = (Animal)getElement(field_index) ;
            if (target.getWeight() < 0) {
                return false ;
            }
            target.setWeight(target.getWeight() - 5) ;
        }  
        return true ;
    }

    public boolean useInstantHarvest(int field_index) throws Throwable{
        if (getElement(field_index).getType().equals("Plant")) {
            Plants target = (Plants)getElement(field_index) ;
            target.setProgress(target.getHarvestLimit());
            return true ;
        }
        else {
            Animal target = (Animal)getElement(field_index) ;
            target.setWeight(target.getHarvestWeight());
            return true ;
        } 
    }

    public boolean useDestroy(int field_index) throws Throwable {
        if (getElement(field_index).getProtect()) {
            getElement(field_index).setProtect(false); ;
            return true ;
        }
        else {
            removeElement(field_index);
            return true ;
        }    
    }

    public boolean useProtect(int field_index) throws Throwable {
        if (getElement(field_index).getProtect()) {
            return false ; // sudah memiliki efek protect
        }
        else {
            getElement(field_index).setProtect(true) ;
            return true ;
        }   
    }

    public boolean useTrap(int field_index) throws Throwable {
        if (getElement(field_index).getTrap()) {
            return false ;
        }         
        else {
            getElement(field_index).setTrap(true) ;
            return true ;
        }
    }

    // public boolean useItem(Card item, int index) throws Throwable{
    //     // Harvestable target = getElement(index) ;
    //     if (getElement(index).getName() == "null") {
    //         return false ;
    //     }

    //     if (item.getName() == "Accelerate") {
    //         if (getElement(index).getType() == "Plant") {
    //             Plants target = (Plants)getElement(index) ;
    //             target.setProgress(target.getProgress() + 2) ;
    //         }
    //         else {
    //             Animal target = (Animal)getElement(index) ;
    //             target.setWeight(target.getWeight() + 5) ;
    //         }  
    //         return true ;      
    //     }
    //     else if (item.getName() == "Delay") {
    //         if (getElement(index).getType() == "Plant") {
    //             Plants target = (Plants)getElement(index) ;
    //             if (target.getProgress() < 0) {
    //                 return false ;
    //             }
    //             target.setProgress(target.getProgress() - 2) ;
    //         }
    //         else {
    //             Animal target = (Animal)getElement(index) ;
    //             if (target.getWeight() < 0) {
    //                 return false ;
    //             }
    //             target.setWeight(target.getWeight() - 5) ;
    //         }  
    //         return true ;
    //     }
    //     else if (item.getName() == "InstantHarvest") {
    //         if (getElement(index).getType() == "Plant") {
    //             Plants target = (Plants)getElement(index) ;
    //             target.setProgress(target.getHarvestLimit());
    //             return true ;
    //         }
    //         else {
    //             Animal target = (Animal)getElement(index) ;
    //             target.setWeight(target.getHarvestWeight());
    //             return true ;
    //         }  
    //     }
    //     else if (item.getName() == "Destroy") {
    //         // if (getElement(index).getName() == "null") {
    //         //     return false ;
    //         // }
    //         if (getElement(index).getProtect()) {
    //             getElement(index).setProtect(false); ;
    //             return true ;
    //         }
    //         else {
    //             removeElement(index);
    //             return true ;
    //         }
    //     }
    //     else if (item.getName() == "Protect") {
    //         // if (getElement(index).getName() == "null") {
    //         //     return false ;
    //         // }
    //         if (getElement(index).getProtect()) {
    //             return false ; // sudah memiliki efek protect
    //         }
    //         else {
    //             getElement(index).setProtect(true) ;
    //             return true ;
    //         }          
    //     }
    //     else if (item.getName() == "Trap") {
    //         if (getElement(index).getTrap()) {
    //             return false ;
    //         }         
    //         else {
    //             getElement(index).setTrap(true) ;
    //             return true ;
    //         }
    //     }
    //     else {
    //         return false ;
    //     }
    // }
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

    public void initiateBearEvent(){
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
        
    }

    public void run(){
        initiateBearEvent();
    }

    public synchronized void bearDestroy(ArrayList<Integer> destroy_zone){
        System.out.println(destroy_zone);
        for(int i = 0 ; i < destroy_zone.size() ; i++){
            if(!content.get(destroy_zone.get(i)).getName().equals("null") && !protect.contains(i)){
                try {
                    removeElement(destroy_zone.get(i));
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        notifyAll();
    }

    public synchronized void printContent(){
        if(bear_attack){
            try {
                System.out.println("nigga");
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

        yoi.start();

        try {
            TimeUnit.SECONDS.sleep(1);
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