package me.tbsten.melabl2025.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.TransformOrigin
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.forms.Button
import kotlinx.browser.window
import me.tbsten.melabl2025.components.Bow
import me.tbsten.melabl2025.components.BowState
import me.tbsten.melabl2025.components.layouts.PageLayoutData
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Bow"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .flex(flexGrow = 1, flexShrink = 1)
            .fillMaxSize(),
    ) {
        val bows =
            remember {
                mutableStateListOf<
                        Pair<
                                BowState,
                                Pair<
                                        CSSSizeValue<CSSUnit.px>,
                                        CSSSizeValue<CSSUnit.px>
                                        >
                                >
                        >()
            }

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            bows.forEach { (state, offset) ->
                val (x, y) = offset
                Bow(
                    state = state,
                    modifier = Modifier
                        .translate(tx = x, ty = y)
                        .transformOrigin(TransformOrigin.Center)
                ).also {
                    LaunchedEffect(Unit) {
                        state.start()
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.zIndex(100)
        ) {
            Span(
                attrs = Modifier.fontSize(84.px).toAttrs()
            ) {
                Text("${bows.size}")
            }

            Button(onClick = {
                val rangeLimitRate = if (bows.size <= 5) 4 else 2
                val widthRange = (-window.innerWidth / rangeLimitRate)..(+window.innerWidth / rangeLimitRate)
                val heightRange = (-window.innerHeight / rangeLimitRate)..(+window.innerHeight / rangeLimitRate)
                val offset = (widthRange.random().px) to (heightRange.random().px)
                bows.add(BowState() to offset)
            }) {
                Text("Tap !")
            }
        }
    }
}
