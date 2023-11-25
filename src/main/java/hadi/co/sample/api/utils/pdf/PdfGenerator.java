package hadi.co.sample.api.utils.pdf;

import com.openhtmltopdf.bidi.support.ICUBidiSplitter;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import hadi.co.sample.api.utils.io.TemplateProcessor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;


@Component
public class PdfGenerator {


    @SneakyThrows
    public byte[] generatePdf(String templateName, Map<String, Object> variables) {

        var template = TemplateProcessor.processTemplate(templateName, variables);

        template = template.replace("<!DOCTYPE html>", "<!DOCTYPE html PUBLIC\n" +
                " \"-//OPENHTMLTOPDF//DOC XHTML Character Entities Only 1.0//EN\" \"\">");
        String tmpTemplate = "/tmp/html-template-" + UUID.randomUUID() + ".html";
        Files.writeString(Paths.get(tmpTemplate), template);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.defaultTextDirection(BaseRendererBuilder.TextDirection.RTL);
            builder.defaultTextDirection(BaseRendererBuilder.TextDirection.RTL);
            builder.useFont(() -> PdfGenerator.class.getResourceAsStream("/static/fonts/ttf/BNAZANIN.TTF"), "B NAZANIN");
            builder.useUnicodeBidiSplitter(new ICUBidiSplitter.ICUBidiSplitterFactory());
            builder.useUnicodeBidiReorderer(new CustomICUBidiReorderer());
            builder.withHtmlContent(template, Paths.get(tmpTemplate).toString());
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }


}
