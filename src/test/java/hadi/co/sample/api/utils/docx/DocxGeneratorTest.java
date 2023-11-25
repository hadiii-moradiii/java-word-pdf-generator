package hadi.co.sample.api.utils.docx;

import com.ibm.icu.text.DateFormat;
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


class DocxGeneratorTest extends BaseUnitTestClass {

    @Autowired
    private DocxGenerator docxGenerator;

    @SneakyThrows
    @Test
    void toDocx() {

        List<SampleSubDto> sampleSubDtoList = List.of(SampleSubDto.builder().sample1("تست").sample2("تست 2").build());

        Map<String, Object> variables = new HashMap<>();

        variables.put("contract", "تست");
        variables.put("sublist", sampleSubDtoList);


        byte[] repsone = docxGenerator.generateDocx("sampleForm", variables);
        try (FileOutputStream fos = new FileOutputStream("test.docx")) {
            fos.write(repsone);
        }
    }
}