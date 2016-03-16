/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fameg.semestre.estruturadados.torredehanoi;

/**
 *
 * @author lucas
 */
public class WinException extends Exception {

    public WinException(int moves) {
        super("Parabens você ganhou! Você ganhou!! Em " + moves + " movimentos. ");
    }
    
}
