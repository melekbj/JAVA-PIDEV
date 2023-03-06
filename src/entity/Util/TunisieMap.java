package entity.Util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Plop
 */
public class TunisieMap {
   private ArrayList<State> adjacencyList ;
    static TunisieMap instance;
    private   ArrayList<Integer> Ariana_adj = new ArrayList<>(Arrays.asList(3,13,22));
    private   ArrayList<Integer> Beja_adj = new ArrayList<>(Arrays.asList(3,13,23,18,6));
    private   ArrayList<Integer> Ben_arous_adj = new ArrayList<>(Arrays.asList(22,13,23,15));
    private   ArrayList<Integer> Bizerte_adj = new ArrayList<>(Arrays.asList(0,13,1));
    private   ArrayList<Integer> Gabes_adj = new ArrayList<>(Arrays.asList(16,17,5,9,11));
    private   ArrayList<Integer> Gafsa_adj = new ArrayList<>(Arrays.asList(8,17,4,9,21));
    private   ArrayList<Integer> Jendouba_adj = new ArrayList<>(Arrays.asList(1,18,10));
    private   ArrayList<Integer> Kairouan_adj = new ArrayList<>(Arrays.asList(23,19,12,16,17,18));
    private   ArrayList<Integer> Kasserine_adj = new ArrayList<>(Arrays.asList(10,18,17,5));
    private   ArrayList<Integer> Kebili_adj = new ArrayList<>(Arrays.asList(21,5,4,11,20));
    private   ArrayList<Integer> Le_Kef_adj = new ArrayList<>(Arrays.asList(6,18,8));
    private   ArrayList<Integer> Mednine_adj = new ArrayList<>(Arrays.asList(4,9,20));
    private   ArrayList<Integer> Mahdia_adj = new ArrayList<>(Arrays.asList(14,19,7,16));
    private   ArrayList<Integer> Manouba_adj = new ArrayList<>(Arrays.asList(0,22,2,23,1,3));
    private   ArrayList<Integer> Monastir_adj = new ArrayList<>(Arrays.asList(19,12));
    private   ArrayList<Integer> Nabeul_adj = new ArrayList<>(Arrays.asList(2,23,19));
    private   ArrayList<Integer> Sfax_adj = new ArrayList<>(Arrays.asList(12,7,17,4));
    private   ArrayList<Integer> Sidi_Bou_Zid_adj = new ArrayList<>(Arrays.asList(18,7,16,4,5,8));
    private   ArrayList<Integer> Siliana_adj = new ArrayList<>(Arrays.asList(1,23,7,17,8,10,6));
    private   ArrayList<Integer> Sousse_adj = new ArrayList<>(Arrays.asList(15,23,7,12,14));
    private   ArrayList<Integer> Tataouine_adj = new ArrayList<>(Arrays.asList(11,9));
    private   ArrayList<Integer> Tozeur_adj = new ArrayList<>(Arrays.asList(5,9));
    private   ArrayList<Integer> Tunis_adj = new ArrayList<>(Arrays.asList(0,2,13));
    private   ArrayList<Integer> Zaghouan_adj = new ArrayList<>(Arrays.asList(2,13,1,18,7,19,15,22));

    private TunisieMap(){
        adjacencyList=new ArrayList<State>();
this.adjacencyList.add(new State("Ariana", Ariana_adj));
this.adjacencyList.add(new State("Beja", Beja_adj));
this.adjacencyList.add(new State("Ben-arous", Ben_arous_adj));
this.adjacencyList.add(new State("Bizerte", Bizerte_adj));
this.adjacencyList.add(new State("Gabes", Gabes_adj));
this.adjacencyList.add(new State("Gafsa", Gafsa_adj));
this.adjacencyList.add(new State("Jendouba", Jendouba_adj));
this.adjacencyList.add(new State("Kairouan", Kairouan_adj));
this.adjacencyList.add(new State("Kasserine", Kasserine_adj));
this.adjacencyList.add(new State("Kebili", Kebili_adj));
this.adjacencyList.add(new State("Le Kef", Le_Kef_adj));
this.adjacencyList.add(new State("Mednine", Mednine_adj));
this.adjacencyList.add(new State("Mahdia", Mahdia_adj));
this.adjacencyList.add(new State("Manouba", Manouba_adj));
this.adjacencyList.add(new State("Monastir", Monastir_adj));
this.adjacencyList.add(new State("Nabeul", Nabeul_adj));
this.adjacencyList.add(new State("Sfax", Sfax_adj));
this.adjacencyList.add(new State("Sidi Bou Zid", Sidi_Bou_Zid_adj));
this.adjacencyList.add(new State("Siliana", Siliana_adj));
this.adjacencyList.add(new State("Sousse", Sousse_adj));
this.adjacencyList.add(new State("Tataouine", Tataouine_adj));
this.adjacencyList.add(new State("Tozeur", Tozeur_adj));
this.adjacencyList.add(new State("Tunis", Tunis_adj));
this.adjacencyList.add(new State("Zaghouan", Zaghouan_adj));
    }
    public static TunisieMap getInstance()
    {
        if (instance==null)
                return instance=new TunisieMap();
        return instance;
    }

    public ArrayList<State> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        return "TunisieMap{" + "adjacencyList=" + adjacencyList + '}';
    }
    
}
