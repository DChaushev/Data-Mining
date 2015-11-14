
import com.fmi.se.dm.hw4.Population;
import com.fmi.se.dm.hw4.PopulationBuilder;

/**
 *
 * @author Dimitar
 */
public class Main {

    public static void main(String[] args) {
        Population p = PopulationBuilder.initPopulation(100);
        p.sort();
        System.out.println(p);
        for (int i = 0; i < 10000; i++) {
            p = p.evolvePopulation();
            p.sort();
            System.out.println(p);
        }
    }
}
