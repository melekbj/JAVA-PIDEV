/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utile.TunisieMap;

import java.util.ArrayList;

/**
 *
 * @author Plop
 */
public class State {
    private String key;
    private ArrayList<Integer> value;
    
    
    public State(String key, ArrayList<Integer> value) {
        this.key = key;
        this.value = value;
    }

    State() {}

  
      
    public String getKey() {
        return key;
    }

    public ArrayList<Integer> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "State{" + "key=" + key + ", value=" + value + '}';
    }
    
}
