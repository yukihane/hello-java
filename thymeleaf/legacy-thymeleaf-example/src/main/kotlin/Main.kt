import nz.net.ultraq.thymeleaf.LayoutDialect
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.StandardTemplateModeHandlers
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val dir = Paths.get("output")
    Files.createDirectory(dir)

    val engine = buildTemplateEngine()

    Files.newBufferedWriter(dir.resolve("01output.html")).use { writer ->
        val ctx = Context()
        ctx.variables.apply {
            put("greet", "Hello, world!")
        }
        engine.process("01basic", ctx, writer)
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
