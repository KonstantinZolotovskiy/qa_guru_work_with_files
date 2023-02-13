package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseFilesTests {

    ClassLoader cl = ParseFilesTests.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("zipForTest.zip");
                ZipInputStream zis = new ZipInputStream(resource);
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("csv.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[0]).contains("Ivan");
                } else if (entry.getName().contains("Table.xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue()).contains("Semenov");
                } else if (entry.getName().contains("English.pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Christina Latham-Koenig");
                }
            }
        }
    }
}
