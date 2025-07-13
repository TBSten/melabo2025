import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.meta

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "me.tbsten.melabl2025"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            lang = "ja"
            description.set("BOW")
            head.add {
                meta("og:title", content = "MeLABO 2025")
                meta("og:description", content = "BOW")
                meta("og:image", content = "https://tbsten.github.io/melabo2025/ogp-2.png")
                meta("og:type", content = "website")
                meta("og:site_name", content = "MeLABO 2025")
                meta("twitter:card", content = "summary_large_image")
                meta("twitter:site", content = "@tsuba__zutomaro")
                meta("twitter:image", content = "https://tbsten.github.io/melabo2025/ogp-2.png")
            }
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("melabl2025" /*, includeServer = true*/)

    sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here if building a fullstack app
//        }

        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
