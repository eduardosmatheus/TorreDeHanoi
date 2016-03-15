package fameg.semestre.estruturadados.torredehanoi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lucas
 */
public class RunnerTower {

    private static final Map<Integer, Integer> disks = new HashMap<>();
    static {
        disks.put(6, 1);
        disks.put(5, 2);
        disks.put(4, 3);
        disks.put(3, 4);
        disks.put(2, 5);
        disks.put(1, 6);
    }
    
    private static final Map<Integer, String> disksImages = new HashMap<>();
    static {
        disksImages.put(1, "-");
        disksImages.put(2, "--");
        disksImages.put(3, "---");
        disksImages.put(4, "----");
        disksImages.put(5, "-----");
        disksImages.put(6, "------");
    }
    
    private static final Map<Integer, List<Integer>> towerWithKey = new LinkedHashMap<>();
    
    /**numeroDaTorreDeSaida = torre de saída
    * numeroDaTorreDeEntrada = torre de entrada

    * Buscar a torre de 
    * entrada em {@link towerWithKey}, filtrando pelo parâmetro torreDeEntrada, 
    * remover a peça escolhida da torreDeSaida, e inserir a peça na torreDeEntrada, 
    * peça será pega pelo tamanho da lista - 1 (zero Index).

    * Permitir continuar a execução, mesmo se não tiver disco na torre inicial.
     * @param args
    */
    public static void main(String[] args) {
        List<Integer> towerOne = new LinkedList<>();
        List<Integer> towerTwo = new LinkedList<>();
        List<Integer> towerThree = new LinkedList<>();
        
        towerWithKey.put(1, towerOne);
        towerWithKey.put(2, towerTwo);
        towerWithKey.put(3, towerThree);
        
        disks.keySet()
                .stream()
                .sorted((a, b) -> b.compareTo(a))
                //O jogo inicializa a primeira torre com as peças.
                .forEach(towerWithKey.get(1)::add);

        showTowers(towerOne, towerTwo, towerThree);
        
        Scanner in = new Scanner(System.in);
        while(1 > 0) {
            System.out.print("Move from tower: ");
            int towerOut = in.nextInt();
            System.out.print("To tower: ");
            int towerIn = in.nextInt();
            System.out.println("");
            
            if(towerOut == towerIn) {
                System.out.println("Something is wrong! try again.");
                System.out.println("Continuing....");
            }
            
            removeAndPut(towerIn, towerOut);
            
            showTowers(towerOne, towerTwo, towerThree);
            
            if(towerThree.stream().count() == 6) break;
        }
        System.out.println("You win! ");
    }
 
    private static void removeAndPut(int towerIn, int towerOut){
        if(!haveDiskInTower(towerWithKey.get(towerOut))) {
            System.out.println("Continuing....");
        }
        List<Integer> torreDeEntrada = towerWithKey.get(towerIn);
        List<Integer> torreDeSaida = towerWithKey.get(towerOut);
        
        torreDeEntrada.add(torreDeSaida.size() - 1);
        torreDeSaida.remove(torreDeSaida.get(torreDeSaida.size() - 1));
    }
    
    private static void showTowers(List tower1, List tower2, List tower3) {
        System.out.println("**************");
        System.out.println("Tower ONE ");
        tower1.stream().forEach(o -> System.out.println(disksImages.get(o)));
        System.out.println("Tower TWO ");
        tower2.stream().forEach(o -> System.out.println(disksImages.get(o)));
        System.out.println("Tower THREE ");
        tower3.stream().forEach(o -> System.out.println(disksImages.get(o)));
        System.out.println("**************");
    }

    private static boolean haveDiskInTower(List list) {
        boolean result = list.stream().count() > 0;
        if(!result) System.out.println("There are no disks for this tower. ");
        return result;
    }
}
