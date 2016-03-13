package br.com.datastructures.aula4;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    private static final Map<Integer, Integer> pecas = new LinkedHashMap<>();
    static{
        pecas.put(3, 1);//   -
        pecas.put(2, 2);//   --
        pecas.put(1, 3);//   ---
    }
    
    public static void main(String[] args) {
        //A primeira torre inicia com todas as peças
        Torre torre1 = new Torre();
        torre1.add(pecas.get(1));
        torre1.add(pecas.get(2));
        torre1.add(pecas.get(3));
        
        Torre torre2 = new Torre();
        Torre torre3 = new Torre();
        
        
    }
    
    
    private static class Torre extends LinkedList{}
}
