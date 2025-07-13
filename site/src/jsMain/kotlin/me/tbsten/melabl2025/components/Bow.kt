package me.tbsten.melabl2025.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.SVGFillType
import com.varabyte.kobweb.compose.dom.svg.SVGSvgAttrsScope
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun Bow(
    state: BowState,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        BowBackground(
            attrs = Modifier
                .then(BowBackground.StyleSheet.toModifier())
                .thenIf(
                    state.status.isAttacheAnimationModifier,
                    Modifier
                        .animation(
                            BowBackground.Keyframes.toAnimation(
                                duration = 0.25.s,
                                timingFunction = AnimationTimingFunction.Linear,
                                fillMode = AnimationFillMode.Forwards,
                            )
                        )
                )
                .attrsModifier {
                    onAnimationEnd {
                        state.status = BowState.Status.Finished
                    }
                }
                .userSelect(UserSelect.None)
                .toAttrs()
        )

        Row(
            modifier = Modifier
                .then(BowText.StyleSheet.toModifier())
                .thenIf(
                    state.status.isAttacheAnimationModifier,
                    Modifier
                        .animation(
                            BowText.Keyframes.toAnimation(
                                duration = 0.75.s,
                                timingFunction = AnimationTimingFunction("linear(0, 1.32, 1.00, 1.00, 1.00)"),
                                fillMode = AnimationFillMode.Forwards,
                            )
                        )
                )
        ) {
            Span(attrs = Modifier.rotate((-7.39).deg).toAttrs()) {
                Text("B")
            }
            Span(attrs = Modifier.toAttrs()) {
                Text("o")
            }
            Span(attrs = Modifier.rotate((8.31).deg).toAttrs()) {
                Text("w")
            }
        }
    }
}

class BowState {
    var status by mutableStateOf(Status.NotStarted)

    fun start() {
        status = Status.Animating
    }

    enum class Status(val isAttacheAnimationModifier: Boolean) {
        NotStarted(false),
        Animating(true),
        Finished(true),
    }
}

@Composable
private fun BowBackground(attrs: SVGSvgAttrsScope.() -> Unit = {}) {
    Svg(attrs = {
        width(310)
        height(223)
        viewBox(0, 0, 310, 223)
        fill(SVGFillType.None)
        attrs()
    }) {
        Path(attrs = {
            d("M183.5 0L209.5 84.5L265 35V97.5L310 106.5L270.5 148.5L310 215L224 169.5L199 223L155.5 177L69 215L90 163.5L0 133.5L69 106.5L74 35L140 91.5L183.5 0Z")
            fill(Color("#ECE338"))
        })
    }
}

object BowText {
    val initial =
        Modifier
            .scale(0)
            .opacity(0)

    val StyleSheet = CssStyle {
        base {
            initial
                .fontSize(120.px)
                .fontWeight(FontWeight.Bold)
                .color(Color("#E45128"))
        }

        Breakpoint.MD {
            initial
                .fontSize((120 * 1.25).px)
        }
    }
    val Keyframes = Keyframes {
        from {
            initial
        }

        to {
            Modifier
                .scale(1)
                .opacity(1)
        }
    }
}

object BowBackground {
    val initial =
        Modifier
            .scale(0)
            .opacity(0)

    val StyleSheet = CssStyle {
        base {
            initial
                .width(310.px)
                .height(Height.FitContent)
        }

        Breakpoint.MD {
            Modifier
                .width((310 * 1.25).px)
        }
    }
    val Keyframes = Keyframes {
        from {
            initial
        }
        50.percent {
            Modifier
                .opacity(1)
        }
        to {
            Modifier
                .scale(1)
                .opacity(1)
        }
    }
}
