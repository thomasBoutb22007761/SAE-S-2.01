import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Cette classe contient des tests unitaires pour la classe SeismeCSV.
 */
public class SeismeCSVTest {

    private SeismeCSV seismeCSV;
    private String csvFilePath = "test.csv";

    /**
     * Configure les prérequis pour chaque test.
     */
    @BeforeEach
    public void setUp() {
        seismeCSV = new SeismeCSV(csvFilePath);
    }

    /**
     * Teste l'importation des données à partir d'un fichier CSV.
     */
    @Test
    public void testImportDataFromCSV() {
        File file = new File(csvFilePath);
        Assertions.assertDoesNotThrow(() -> seismeCSV.importDataFromCSV(file));
        List<SeismeCSV> listData = seismeCSV.getListData();
        Assertions.assertNotNull(listData);
        Assertions.assertFalse(listData.isEmpty());
    }

    /**
     * Teste le filtrage par année et région des données.
     */
    @Test
    public void testFiltreAL() {
        List<SeismeCSV> filteredList = seismeCSV.filtreAL(seismeCSV.getListData(), 2022, "Region1");
        Assertions.assertNotNull(filteredList);
        Assertions.assertFalse(filteredList.isEmpty());
    }

    /**
     * Teste le filtrage par année des données.
     */
    @Test
    public void testFiltreA() {
        List<SeismeCSV> filteredList = seismeCSV.filtreA(seismeCSV.getListData(), 2022);
        Assertions.assertNotNull(filteredList);
        Assertions.assertFalse(filteredList.isEmpty());
    }

    /**
     * Teste le filtrage par région des données.
     */
    @Test
    public void testFiltreL() {
        List<SeismeCSV> filteredList = seismeCSV.filtreL(seismeCSV.getListData(), "Region1");
        Assertions.assertNotNull(filteredList);
        Assertions.assertFalse(filteredList.isEmpty());
    }
}
