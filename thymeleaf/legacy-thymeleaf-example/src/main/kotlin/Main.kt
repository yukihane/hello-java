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
    Files.createDirectories(dir)

    val engine = buildTemplateEngine()

    // output/01output.html
    // 最もシンプルなサンプル
    Files.newBufferedWriter(dir.resolve("01output.html")).use { writer ->
        val ctx = Context()
        ctx.variables.apply {
            put("greet", "Hello, world!")
        }
        engine.process("01basic", ctx, writer)
    }

    // output/02output.html
    // thymeleaf-layout-dialect の decorator 利用サンプル
    Files.newBufferedWriter(dir.resolve("02output.html")).use { writer ->
        val ctx = Context()
        // https://ultraq.github.io/thymeleaf-layout-dialect/processors/decorate/
        engine.process("02content", ctx, writer)
    }

    // output/03output.html
    // th:fragment の利用サンプル
    // https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#parameterizable-fragment-signatures
    Files.newBufferedWriter(dir.resolve("03output.html")).use { writer ->
        val ctx = Context()
        ctx.variables.apply {
            put("items", listOf(Item("item01"), Item("item02"), Item("item03")))
        }
        engine.process("03list-and-fragment", ctx, writer)
    }

}

fun buildTemplateEngine(): TemplateEngine {
    // https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#creating-and-configuring-the-template-engine
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "templates/"
        suffix = ".html"
        templateMode = StandardTemplateModeHandlers.LEGACYHTML5.templateModeName
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

data class Item(
    val name: String,
)
