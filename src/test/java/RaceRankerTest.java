import com.foxminded.racer.RaceRanker;
import com.foxminded.util.FileToListConverter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class RaceRankerTest {
    RaceRanker raceRanker = new RaceRanker(new FileToListConverter(), "start.log", "end.log", "abbreviations.txt");

    @Test
    void getLapRanking_nullAsInputFile_exceptionIsExpected() {
        RaceRanker raceRanker1 = new RaceRanker(new FileToListConverter(), null, null, null);
        assertThrows(NullPointerException.class, () -> raceRanker1.getLapRanking());
    }

    @Test
    void getLapRanking_normalInput_sortedListOfRacersAndTheirTimesExpected() {

        List<String> actualResultAsList = null;
        try {
            actualResultAsList = raceRanker.getLapRanking();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] actualResult = new String[actualResultAsList.size()];
        actualResultAsList.toArray(actualResult);
        String[] expectedResult = new String[] {
               "1.Sebastian Vettel  | FERRARI                   | 1:04.415",
               "2.Daniel Ricciardo  | RED BULL RACING TAG HEUER | 1:12.013",
               "3.Valtteri Bottas   | MERCEDES                  | 1:12.434",
               "4.Lewis Hamilton    | MERCEDES                  | 1:12.460",
               "5.Stoffel Vandoorne | MCLAREN RENAULT           | 1:12.463",
               "6.Kimi Raikkonen    | FERRARI                   | 1:12.639",
               "7.Fernando Alonso   | MCLAREN RENAULT           | 1:12.657",
               "8.Sergey Sirotkin   | WILLIAMS MERCEDES         | 1:12.706",
               "9.Charles Leclerc   | SAUBER FERRARI            | 1:12.829",
               "10.Sergio Perez     | FORCE INDIA MERCEDES      | 1:12.848",
               "11.Romain Grosjean  | HAAS FERRARI              | 1:12.930",
               "12.Pierre Gasly     | SCUDERIA TORO ROSSO HONDA | 1:12.941",
               "13.Carlos Sainz     | RENAULT                   | 1:12.950",
               "14.Esteban Ocon     | FORCE INDIA MERCEDES      | 1:13.028",
               "15.Nico Hulkenberg  | RENAULT                   | 1:13.065\n" +
               "----------------------------------------------------------",
               "16.Brendon Hartley  | SCUDERIA TORO ROSSO HONDA | 1:13.179",
               "17.Marcus Ericsson  | SAUBER FERRARI            | 1:13.265",
               "18.Lance Stroll     | WILLIAMS MERCEDES         | 1:13.323",
               "19.Kevin Magnussen  | HAAS FERRARI              | 1:13.393"
        };

        assertArrayEquals(expectedResult, actualResult);


    }
}

