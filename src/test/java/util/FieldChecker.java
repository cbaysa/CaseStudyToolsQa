package util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

public class FieldChecker {

    public static final Logger log = LogManager.getLogger(FieldChecker.class);

    public static void verifyFieldsFromCsv(String...fields) {
        for (int i = 0; i <fields.length ; i++) {
            Assertions.assertThat(fields[i]).isNotEmpty();
            log.info("Column {} value: {} has been read from CSV",i+1,fields[i]);
        }
    }
}
