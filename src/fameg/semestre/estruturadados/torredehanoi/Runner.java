package fameg.semestre.estruturadados.torredehanoi;

/**
 * @author lucas
 */
public class Runner { 
    
    public static void main(String[] args) throws Exception {
        try {
            TowerGame towerGame = new TowerGame();
            towerGame.play();
        }catch(WinException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
