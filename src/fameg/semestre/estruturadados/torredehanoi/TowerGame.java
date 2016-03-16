/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fameg.semestre.estruturadados.torredehanoi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class TowerGame {
    private static final int LIMITE_TORRE_3 = 3;
    private static final int REINICIAR = 0;
    
    private static final Map<Integer, Integer> DISCOS = new HashMap<>();
    static {
        DISCOS.put(3, 1);
        DISCOS.put(2, 2);
        DISCOS.put(1, 3);
    }
    
    private static final Map<Integer, String> DISCO_IMAGENS = new HashMap<>();
    static {
        DISCO_IMAGENS.put(1, "   -");
        DISCO_IMAGENS.put(2, "  ---");
        DISCO_IMAGENS.put(3, " -----");
    }
     
    private final Map<Integer, List<Integer>> TORRES = new LinkedHashMap<>();
    
    public void play() throws WinException {
        List<Integer> torreUm = new LinkedList<>();
        List<Integer> torreDois = new LinkedList<>();
        List<Integer> torreTres = new LinkedList<>();
        
        TORRES.put(1, torreUm);
        TORRES.put(2, torreDois);
        TORRES.put(3, torreTres);
        
        DISCOS.keySet()
                .stream()
                .sorted((a, b) -> b.compareTo(a))
                //O jogo inicializa a primeira torre com as peças.
                .forEach(getTorre(1)::add);

        mostrarTorres();
        
        int numeroMovimentos = 0;
        
        Scanner in = new Scanner(System.in);
        while(1 > 0) {
            println("Move from tower: (Zero to Restart!)");
            int torreSaida = in.nextInt();
            
            if(torreSaida == REINICIAR) 
                play();
            
            println("To tower: ");
            int torreEntrada = in.nextInt();
            println("");
            
            if(saoIguais(torreSaida, torreEntrada)) {
                println("You are trying to move a disk from a tower to itselfs.");
                continue;
            }
            
            if (!movimentarDisco(torreEntrada, torreSaida))
                continue;
            
            mostrarTorres();
            
            numeroMovimentos++;  
             
            if(saoIguais(torreTres.size(), LIMITE_TORRE_3)) break;
        }
        throw new WinException(numeroMovimentos);
    }
     
    private void println(String word) {
        System.out.println(word);
    }
 
    private boolean saoIguais(int indexOne, int indexTwo) {
        return indexOne == indexTwo;
    }
     
    private boolean movimentarDisco(int torreEntrada, int torreSaida){
        if(!temDiscosNaTorre(getTorre(torreSaida))) 
            return false;
        //Insere a peça na torre de entrada
        getTorre(torreEntrada).add(getUltimoDiscoDaTorre(torreSaida));
        //Remove a peça na torre de saída
        getTorre(torreSaida).remove(getUltimoDiscoDaTorre(torreSaida));
        return true;
    }
 
    private List<Integer> getTorre(int towerOut) {
        return TORRES.get(towerOut);
    }
   
    private Integer getUltimoDiscoDaTorre(int towerOut) {
        return getTorre(towerOut).get(getTorre(towerOut).size() - 1);
    }
    
     
    private void mostrarTorres() {
        println("**************");
        TORRES.keySet().stream().forEach(o -> {
            println("Tower " + o);
            TORRES.get(o).stream().sorted((a,b) -> a.compareTo(b)).forEach(t -> println(DISCO_IMAGENS.get(t)));
        });
    }
    
    private boolean temDiscosNaTorre(List torre) {
        boolean result = torre.size() > 0;
        if(!result) println("There are no disks for this tower. ");
        return result;
    }
}
