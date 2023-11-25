package hadi.co.sample.api.utils.docx;

import hadi.co.sample.api.utils.io.TemplateProcessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import org.docx4j.convert.in.xhtml.ImportXHTMLProperties;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.RFonts;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DocxGenerator {

    @SneakyThrows
    public byte[] generateDocx(String templateName, Map<String, Object> variables) {
        ImportXHTMLProperties.setProperty("docx4j-ImportXHTML.Bidi.Heuristic", true);

        String stringFromFile = TemplateProcessor.processTemplate(templateName , variables);

        String unescaped = stringFromFile;
        if (stringFromFile.contains("&lt;/")) {
            unescaped = StringEscapeUtils.unescapeHtml4(stringFromFile);
        }
        stringFromFile = unescaped;

        String baseURL = "file:///tmp";
        // Setup font mapping
        RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
        rfonts.setAscii("B Nazanin");
        rfonts.setHAnsi("B Nazanin");
        rfonts.setCs("B Nazanin");
        XHTMLImporterImpl.addFontMapping("B Nazanin", rfonts);

        // Create an empty docx package
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(
                PageSizePaper.A4, false
        );

        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
        ndp.unmarshalDefaultNumbering();

        // Convert the XHTML, and add it into the empty docx we made
        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        XHTMLImporter.setHyperlinkStyle("Hyperlink");
        wordMLPackage.getMainDocumentPart().getContent().addAll(
                XHTMLImporter.convert(stringFromFile, baseURL));


//        wordMLPackage.save(new java.io.File("OUT_from_XHTML.docx"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wordMLPackage.save(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
