package javva.tubes2.GameMaster;

import javva.tubes2.Player.*;
import javva.tubes2.CardConfig;
import javva.tubes2.Shop;
import javva.tubes2.Card.*;

import java.util.*;

public class GameMaster {
    public Player current_player;

    public Player player1;
    public Player player2;

    public Boolean player_turn = true;

    public int turn = 0;
    public int avail_deck_count = 40;

    public Shop main_shop;
    public CardConfig config;

    public GameMaster(){
        player1 = new Player();
        player2 = new Player();
        current_player = player1;
        config = CardConfig.getInstance();
    }

    public void changeTurn(){
        if(turn > 19){
            // win condition
        } else {
            if(player_turn){
                player_turn = false;
                player1 = current_player;
                current_player = player2;
            } else {
                player_turn = true;
                player2 = current_player;
                current_player = player1;
            }
        }
        turn++;
    }

    public void addToDeck(List<Card> list_of_card){
        int count = 0;
        try {
            for(int i = 0 ; i < list_of_card.size() ; i++){
                current_player.addToActiveDeck(list_of_card.get(i));
                count++;
            }
        } catch (Throwable e){

        } finally {
            avail_deck_count -= count;
        }

    }
}
