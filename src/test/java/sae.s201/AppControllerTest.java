import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe de test pour AppController.
 */
public class AppControllerTest {

    /**
     * Test de la méthode getLieux().
     */
    @Test
    public void testGetLieux() {
        // Création d'une liste de seismes de test
        List<SeismeCSV> seismes = Arrays.asList(
                new SeismeCSV("Lieu 1", ...),
        new SeismeCSV("Lieu 2", ...),
        new SeismeCSV("Lieu 3", ...)
        );

        AppController appController = new AppController();

        // Appel de la méthode à tester
        ArrayList<String> lieux = appController.getLieux(seismes);

        // Vérification du résultat attendu
        assertEquals(3, lieux.size());
        assertTrue(lieux.contains("Lieu 1"));
        assertTrue(lieux.contains("Lieu 2"));
        assertTrue(lieux.contains("Lieu 3"));
    }

    /**
     * Test de la méthode triData().
     */
    @Test
    public void testTriData() {
        // Création d'une liste de seismes de test
        List<SeismeCSV> seismes = Arrays.asList(
                new SeismeCSV("Lieu 1", "2022", ...),
        new SeismeCSV("Lieu 2", "2023", ...),
        new SeismeCSV("Lieu 3", "2023", ...)
        );

        AppController appController = new AppController();

        // Appel de la méthode à tester avec différents paramètres
        List<SeismeCSV> result1 = appController.triData(true, false); // Filtrer par année
        List<SeismeCSV> result2 = appController.triData(false, true); // Filtrer par lieu
        List<SeismeCSV> result3 = appController.triData(true, true); // Filtrer par année et lieu
        List<SeismeCSV> result4 = appController.triData(false, false); // Aucun filtre

        // Vérification des résultats attendus
        assertEquals(1, result1.size());
        assertEquals(2, result2.size());
        assertEquals(2, result3.size());
        assertEquals(3, result4.size());
    }

    /**
     * Test de la méthode GraphIntens().
     */
    @Test
    public void testGraphIntens() {
        // Création d'une liste de seismes de test
        List<SeismeCSV> seismes = Arrays.asList(
                new SeismeCSV("Lieu 1", "2022", "1", ...),
        new SeismeCSV("Lieu 2", "2023", "3.5", ...),
        new SeismeCSV("Lieu 3", "2023", "6.5", ...)
        );

        AppController appController = new AppController();

        // Appel de la méthode à tester
        ArrayList<Integer> intensites = appController.GraphIntens(seismes);

        // Vérification du résultat attendu
        assertEquals(18, intensites.size());
        assertEquals(1, (int)intensites.get(0));
        assertEquals(0, (int)intensites.get(1));
        assertEquals(1, (int)intensites.get(5));
        assertEquals(1, (int)intensites.get(11));
    }
}
