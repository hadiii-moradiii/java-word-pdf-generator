package hadi.co.sample.api.utils.pdf;

import hadi.co.sample.api.utils.BaseUnitTestClass;
import hadi.co.sample.api.utils.SampleSubDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class PdfGeneratorTest extends BaseUnitTestClass {

    @Autowired
    private PdfGenerator pdfGenerator;

    @SneakyThrows
    @Test
    void should_generatePdf() {

        List<SampleSubDto> sampleSubDtoList = List.of(SampleSubDto.builder().sample1("تست").sample2("تست 2").build());

        Map<String, Object> variables = new HashMap<>();
        variables.put("contract", "تست");
        variables.put("sublist", sampleSubDtoList);


        byte[] repsone = pdfGenerator.generatePdf("sampleForm", variables);

        try (FileOutputStream fos = new FileOutputStream("test.pdf")) {
            fos.write(repsone);
            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
        }
    }
}