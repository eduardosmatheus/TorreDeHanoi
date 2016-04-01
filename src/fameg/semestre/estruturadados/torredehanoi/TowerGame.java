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
    
    private static final int LIMITE_TORRE = 3;
    private static final int REINICIAR = 0;
    private int numeroMovimentos;
    
    private static final List<Integer> DISCOS = new LinkedList<>();
    static {
        DISCOS.add(6);
        DISCOS.add(5);
        DISCOS.add(4);
        DISCOS.add(3);
        DISCOS.add(2);
        DISCOS.add(1);
    }
    
    private static final Map<Integer, String> DISCO_IMAGENS = new HashMap<>();
    static {
        DISCO_IMAGENS.put(1, "       -");
        DISCO_IMAGENS.put(2, "      ---");
        DISCO_IMAGENS.put(3, "     -----");
        DISCO_IMAGENS.put(4, "    -------");
        DISCO_IMAGENS.put(5, "   ---------");
        DISCO_IMAGENS.put(6, "  -----------");
    }
     
    private final Map<Integer, List<Integer>> TORRES = new LinkedHashMap<>();
    
    public void play() throws WinException {
        List<Integer> torreUm = new LinkedList<>();
        List<Integer> torreDois = new LinkedList<>();
        List<Integer> torreTres = new LinkedList<>();
        TORRES.put(1, torreUm);
        TORRES.put(2, torreDois);
        TORRES.put(3, torreTres);
        DISCOS.stream()
              .sorted((a, b) -> b.compareTo(a))
              .forEach(getTorre(1)::add);
        numeroMovimentos = 0;
        Scanner in = new Scanner(System.in);
        mostrarTorres();
        movimentar(in, torreUm, torreDois, torreTres);
    }

    private void movimentar(Scanner in, List<Integer> torreUm, List<Integer> torreDois, List<Integer> torreTres) throws WinException {
        try {

            println("Mover da torre: (Informe o número da torre ou zero para Reiniciar!)");
            int torreSaida = in.nextInt();

            if(torreSaida == REINICIAR) 
                play();

            println("Para torre: ");
            int torreEntrada = in.nextInt();
            println("");
            
            validaSeSaoIguais(torreSaida, torreEntrada);

            validaDiscosNaTorre(torreSaida);
            
            validaDiscos(torreSaida, torreEntrada);
            
            movimentarDisco(torreEntrada, torreSaida);
            
            numeroMovimentos++;  

            if(torreTres.size() == LIMITE_TORRE) 
                throw new WinException(numeroMovimentos);
            
            mostrarTorres();
        } catch(InvalidMovementException e) {
            mostrarTorres();
            System.out.println(e.getMessage());
        }
        movimentar(in, torreUm, torreDois, torreTres);
    }

    private void validaDiscos(int torreSaida, int torreEntrada) throws InvalidMovementException {
        if(!existeDiscoNaTorre(torreEntrada))
            return;
        
        if(getUltimoDiscoDaTorre(torreEntrada) < getUltimoDiscoDaTorre(torreSaida))
            throw new InvalidMovementException("Não pode movimentar um disco maior para uma torre com o disco menor ");
    }

    private void validaSeSaoIguais(int torreSaida, int torreEntrada) throws InvalidMovementException {
        if(saoIguais(torreSaida, torreEntrada))
            throw new InvalidMovementException("Você está tentando mover a torre para um local invalido");
    }
     
    private void println(String word) {
        System.out.println(word);
    }
 
    private boolean saoIguais(int indexOne, int indexTwo) {
        return indexOne == indexTwo;
    }
     
    private void movimentarDisco(int torreEntrada, int torreSaida) {
        //Insere a peça na torre de entrada
        getTorre(torreEntrada).add(getUltimoDiscoDaTorre(torreSaida));
        //Remove a peça na torre de saída
        getTorre(torreSaida).remove(getUltimoDiscoDaTorre(torreSaida));
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
            println("Torre " + o);
            TORRES.get(o).stream().sorted((a,b) -> a.compareTo(b)).forEach(
                t -> println(DISCO_IMAGENS.get(t)
                ));
        });
    }
    
    private void validaDiscosNaTorre(int torre) throws InvalidMovementException {
        boolean result = existeDiscoNaTorre(torre);
        if(!result) throw new InvalidMovementException("Não existem discos na torre. ");
    }

    private boolean existeDiscoNaTorre(int torre) {
        return getTorre(torre).size() > 0;
    }
}
