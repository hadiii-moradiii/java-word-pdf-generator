package hadi.co.sample.api.utils.pdf;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.openhtmltopdf.bidi.support.ICUBidiReorderer;

public class CustomICUBidiReorderer extends ICUBidiReorderer {
    @Override
    public String shapeText(String text) {
        try {
            return new PersianShaping(ArabicShaping.LETTERS_SHAPE).shape(text);
        } catch (ArabicShapingException e) {
            e.printStackTrace();
        }

        return super.shapeText(text);
    }

    @Override
    public String reorderRTLTextToLTR(String text) {
        return new StringBuilder(text)
                .reverse().toString();
    }
}
