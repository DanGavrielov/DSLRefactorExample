package solved

fun main() {
    html {
        head {
            title = "My Html Page"
            css {

            }
        }
        body {
            h1 {

            }
            p {
                style {

                }
            }
        }
    }
}

interface HtmlElement {
    val style: CssScope
    fun style(block: CssScope.() -> Unit)
}

interface ParagraphScope: HtmlElement

object ParagraphScopeImpl: ParagraphScope {
    override val style: CssScope = object : CssScope {}
    override fun style(block: CssScope.() -> Unit) {
        style.apply(block)
    }
}

interface BodyScope {
    fun h1(block: () -> Unit)
    fun p(block: ParagraphScope.() -> Unit)
}

object BodyScopeImpl: BodyScope {
    override fun h1(block: () -> Unit) {
        block()
    }

    override fun p(block: ParagraphScope.() -> Unit) {
        ParagraphScopeImpl.block()
    }
}

interface HeadScope {
    var title: String
    fun css(block: CssScope.() -> Unit)
}

object HeadScopeImpl: HeadScope {
    override var title: String = ""
    override fun css(block: CssScope.() -> Unit) {
        CssScopeImpl.block()
    }
}

interface CssScope

object CssScopeImpl: CssScope

fun html(block: HtmlScope.() -> Unit) {
    HtmlScopeImpl.block()
}

interface HtmlScope {
    fun head(block: HeadScope.() -> Unit)
    fun body(block: BodyScope.() -> Unit)
}

object HtmlScopeImpl: HtmlScope {
    override fun head(block: HeadScope.() -> Unit) {
        HeadScopeImpl.block()
    }

    override fun body(block: BodyScope.() -> Unit) {
        BodyScopeImpl.block()
    }
}