package fameg.semestre.estruturadados.torredehanoi;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author matheus
 */
public class DiskBuilder {
    
    private Map<Integer, String> disks = new LinkedHashMap<>();

    public DiskBuilder(Map<Integer, String> disk) {
        this.disks = disk;
    }
    
    //TODO: Verificar padrão para inputar os discos;
    public DiskBuilder insertDisk(int numberOfDisks){
        disks.put(numberOfDisks, "");
        return this;
    }
}
