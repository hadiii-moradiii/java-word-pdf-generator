package hadi.co.sample.api.utils.io;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Locale;
import java.util.Map;

public class TemplateProcessor {

    public static String processTemplate(String templateName, Map<String, Object> variables) {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("static/templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setForceTemplateMode(true);

        templateEngine.setTemplateResolver(templateResolver);

        Context ctx = new Context(new Locale("fa"));

        for (var variable : variables.entrySet()) {
            ctx.setVariable(variable.getKey(), variable.getValue());
        }

        return templateEngine.process(templateName, ctx);

    }

}
