import nz.net.ultraq.thymeleaf.LayoutDialect
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.StandardTemplateModeHandlers
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.FileWriter
import java.nio.charset.StandardCharsets

fun main() {
    val engine = buildTemplateEngine()
    val ctx = Context()
    ctx.variables.apply {
        put("greet", "Hello, world!")
    }

    System.out.writer().use { writer ->
        engine.process("home", ctx, writer)
    }

}

fun buildTemplateEngine(): TemplateEngine {
    // https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#creating-and-configuring-the-template-engine
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "templates/"
        suffix = ".html"
        templateMode = StandardTemplateModeHandlers.LEGACYHTML5.templateModeName
//        templateMode = StandardTemplateModeHandlers.XHTML.templateModeName
        characterEncoding = StandardCharsets.UTF_8.displayName()
        isCacheable = false
    }

    val engine = TemplateEngine().apply {
        setTemplateResolver(templateResolver)
        // https://ultraq.github.io/thymeleaf-layout-dialect/getting-started/#usage
        addDialect(LayoutDialect())
    }

    return engine
}
