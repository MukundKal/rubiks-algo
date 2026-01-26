## User

explain this porject to me structuire, all lionks and tech and librabries and important architectual poitns as an expereiced software eng who jkoined this porject and will ocntribute [@README.md](file:///Users/mk677/Developer/wearos/rubiks-algo/README.md)

## Assistant

<thinking>
The user wants me to explain the project structure, links, technologies, libraries, and important architectural points. They've provided the README which gives me some basic info, but I need to explore the project structure to give a comprehensive overview.

Let me start by listing the project directory to understand the structure, then I'll look for key files like build configs, source files, etc.
</thinking>

I'll explore the project structure to give you a comprehensive overview of this Wear OS app.

**Tool Call: List the `rubiks-algo` directory's contents**
Status: Completed

# Folders:
rubiks-algo/.gradle
rubiks-algo/.idea
rubiks-algo/.kotlin
rubiks-algo/app
rubiks-algo/build
rubiks-algo/gradle

# Files:
rubiks-algo/.gitignore
rubiks-algo/LICENSE
rubiks-algo/README.md
rubiks-algo/build.gradle.kts
rubiks-algo/emulate-watch.sh
rubiks-algo/emulate.sh
rubiks-algo/gradle.properties
rubiks-algo/gradlew
rubiks-algo/gradlew.bat
rubiks-algo/local.properties
rubiks-algo/run-watch.sh
rubiks-algo/run.sh
rubiks-algo/settings.gradle.kts


**Tool Call: List the `rubiks-algo/app` directory's contents**
Status: Completed

# Folders:
rubiks-algo/app/build
rubiks-algo/app/src

# Files:
rubiks-algo/app/build.gradle.kts
rubiks-algo/app/proguard-rules.pro


**Tool Call: Read file `build.gradle.kts`**
Status: Completed

```rubiks-algo/build.gradle.kts
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
```


**Tool Call: Read file `settings.gradle.kts`**
Status: Completed

```rubiks-algo/settings.gradle.kts
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RubiksAlgo"
include(":app")

```


**Tool Call: Read file `app/build.gradle.kts`**
Status: Completed

```rubiks-algo/app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.rubiksalgo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.rubiksalgo"
        minSdk = 30  // Wear OS 3.0+ requirement
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    
    // Compose BOM and UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    
    // Wear OS Compose - Official Wear OS UI components
    implementation("androidx.wear:wear:1.3.0")
    implementation("androidx.wear.compose:compose-material:1.3.1")
    implementation("androidx.wear.compose:compose-foundation:1.3.1")
    implementation("androidx.wear.compose:compose-navigation:1.3.1")
    
    // Horologist - Google's official Wear OS toolkit for rotary input (bezel)
    implementation("com.google.android.horologist:horologist-compose-layout:0.6.8")
    implementation("com.google.android.horologist:horologist-composables:0.6.8")
    
    // Keep Material3 for icons compatibility
    implementation(libs.androidx.compose.material3)
    
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    
    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

```


**Tool Call: List the `rubiks-algo/app/src` directory's contents**
Status: Completed

# Folders:
rubiks-algo/app/src/androidTest
rubiks-algo/app/src/main
rubiks-algo/app/src/test


**Tool Call: List the `rubiks-algo/app/src/main` directory's contents**
Status: Completed

# Folders:
rubiks-algo/app/src/main/java
rubiks-algo/app/src/main/res

# Files:
rubiks-algo/app/src/main/AndroidManifest.xml


**Tool Call: 10 matches**
Status: Completed

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/androidTest/java/com/example/rubiksalgo/ExampleInstrumentedTest.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/MainActivity.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/theme/Color.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/theme/Theme.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/theme/Type.kt

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/test/java/com/example/rubiksalgo/ExampleUnitTest.kt

**Tool Call: Read file `app/src/main/AndroidManifest.xml`**
Status: Completed

```rubiks-algo/app/src/main/AndroidManifest.xml
<?xml version="1.0" encoding="utf-8" ?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- Wear OS Permissions -->
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <!-- Declare this is a Wear OS app - required for watch deployment -->
    <uses-feature android:name="android.hardware.type.watch" />

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.RubiksAlgo"
    >

        <!-- Wear OS metadata - indicates app runs standalone without phone -->
        <meta-data
            android:name="com.google.android.wearable.standalone"
            android:value="true"
        />

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.RubiksAlgo"
        >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>

```


**Tool Call: Read file `app/src/main/java/com/example/rubiksalgo/MainActivity.kt`**
Status: Completed

```rubiks-algo/app/src/main/java/com/example/rubiksalgo/MainActivity.kt
package com.example.rubiksalgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import com.example.rubiksalgo.ui.screens.HomeScreen

/**
 * MainActivity for Wear OS with Dark Mode
 *
 * Forces dark theme optimized for AMOLED displays on Galaxy Watch
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Custom dark colors for Wear OS (AMOLED optimized)
            val darkColors =
                    Colors(
                            primary = Color(0xFF82B1FF), // Light blue for dark mode
                            primaryVariant = Color(0xFF448AFF), // Darker blue
                            secondary = Color(0xFFFFAB40), // Orange accent
                            secondaryVariant = Color(0xFFFF9100), // Darker orange
                            background = Color.Black, // Pure black for AMOLED
                            surface = Color(0xFF1E1E1E), // Dark gray for cards
                            error = Color(0xFFCF6679), // Red for errors
                            onPrimary = Color.Black, // Text on primary
                            onSecondary = Color.Black, // Text on secondary
                            onBackground = Color.White, // Text on background
                            onSurface = Color.White, // Text on surface
                            onSurfaceVariant = Color(0xFFBBBBBB), // Dimmed text
                            onError = Color.Black // Text on error
                    )

            MaterialTheme(colors = darkColors) { HomeScreen() }
        }
    }
}

```


**Tool Call: Read file `app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt`**
Status: Completed

```rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt
package com.example.rubiksalgo.model

/**
 * Data Transfer Object (DTO) representing a single step in the algorithm.
 * * Industry Standard:
 * - This is a "Data Class" which automatically generates getters, setters, equals(), and hashcode().
 * - It has no dependency on Android framework (Context, Views), making it unit testable.
 */
data class RubiksStep(
    val id: Int,
    val instruction: String,
    val algorithm: String,
    val note: String = ""
)
```


**Tool Call: Read file `app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt`**
Status: Completed

```rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt
package com.example.rubiksalgo.data

import com.example.rubiksalgo.model.RubiksStep

/**
 * Repository Object.
 * * Industry Standard:
 * - The "Repository Pattern" abstracts the data source.
 * - The UI doesn't know if this data is hardcoded, from a Database (Room), or an API (Retrofit).
 * - We use an 'object' (Singleton) here for simplicity, but in a larger app, this would be a class
 * injected via Dependency Injection (Hilt/Koin).
 */
object RubiksRepository {

    // The source of truth for our data
    val steps =
            listOf(
                    RubiksStep(1, "Flip edge", "F U' R U"),
                    RubiksStep(2, "Move edge to down (right)", "U R U' R' U' F' U F"),
                    RubiksStep(3, "Move edge to down (left)", "U' L' U L U F U' F'"),
                    // RubiksStep(4, "Reset (wrong orientation)", "U R U' R' U' F' U F U2"),
                    RubiksStep(4, "Move to top Yellow Cross", "F R U R' U' F'"),
                    RubiksStep(
                            5,
                            "Swap front/left edges",
                            "R U R' U R U2 R' U",
                            "Cross edges match centers"
                    ),
                    RubiksStep(
                            6,
                            "Position yellow corners",
                            "U R U' L' U R' U' L",
                            "Repeat x N times"
                    ),
                    RubiksStep(7, "Orient yellow corners", "R' D' R D", "Repeat until solved")
            )

    fun getAllSteps(): List<RubiksStep> {
        return steps
    }
}

```


**Tool Call: Read file `app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt`**
Status: Completed

```rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt
package com.example.rubiksalgo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.rubiksalgo.data.RubiksRepository
import com.example.rubiksalgo.ui.components.RubiksCard
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

/**
 * HomeScreen for Wear OS with Rotary (Bezel) Navigation
 *
 * Features:
 * - Dark mode optimized for AMOLED displays
 * - Physical bezel rotation support with auto-snap
 * - Visual feedback when bezel is used
 * - Swipe gestures as fallback
 */
@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()

    // Track bezel feedback
    var showBezelFeedback by remember { mutableStateOf(false) }
    var lastScrollTime by remember { mutableStateOf(0L) }

    // Request focus when screen loads (CRITICAL for bezel to work)
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }

    // Monitor scrolling and snap to nearest page when it settles
    LaunchedEffect(pagerState.currentPage, pagerState.currentPageOffsetFraction) {
        if (pagerState.currentPageOffsetFraction != 0f) {
            lastScrollTime = System.currentTimeMillis()
        }
    }

    // Snap to nearest page after scrolling settles
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress && pagerState.currentPageOffsetFraction != 0f) {
            // Wait a bit to see if more scrolling happens
            delay(100)

            if (!pagerState.isScrollInProgress) {
                // Calculate nearest page
                val currentOffset = pagerState.currentPageOffsetFraction
                val targetPage =
                        if (currentOffset.roundToInt() == 0) {
                            pagerState.currentPage
                        } else {
                            pagerState.currentPage + currentOffset.roundToInt()
                        }

                // Snap to the target page
                if (targetPage in 0 until steps.size) {
                    pagerState.animateScrollToPage(targetPage)
                }
            }
        }
    }

    // Show feedback when page changes
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage > 0 || pagerState.settledPage > 0) {
            showBezelFeedback = true
            delay(800)
            showBezelFeedback = false
        }
    }

    Scaffold(
            timeText = { TimeText() },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            pageIndicator = {
                HorizontalPageIndicator(
                        pageIndicatorState =
                                object : PageIndicatorState {
                                    override val pageCount: Int
                                        get() = steps.size
                                    override val pageOffset: Float
                                        get() = 0f
                                    override val selectedPage: Int
                                        get() = pagerState.currentPage
                                }
                )
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main Content Column with DARK BACKGROUND
            Column(
                    modifier =
                            Modifier.fillMaxSize()
                                    .background(Color.Black)
                                    .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Page Counter
                Text(
                        text = "${pagerState.currentPage + 1}/${steps.size}",
                        style = MaterialTheme.typography.caption1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                // HorizontalPager with Rotary (Bezel) Support
                HorizontalPager(
                        state = pagerState,
                        modifier =
                                Modifier.weight(1f)
                                        .fillMaxWidth()
                                        .focusRequester(focusRequester)
                                        .focusTarget()
                                        .rotaryWithScroll(
                                                scrollableState = pagerState,
                                                focusRequester = focusRequester
                                        ),
                        pageSpacing = 0.dp,
                        contentPadding = PaddingValues(0.dp)
                ) { page -> RubiksCard(step = steps[page]) }
            }

            // Bezel Feedback Toast at Bottom
            AnimatedVisibility(
                    visible = showBezelFeedback,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 6.dp)
            ) {
                Box(
                        modifier =
                                Modifier.background(
                                                color =
                                                        MaterialTheme.colors.primary.copy(
                                                                alpha = 0.9f
                                                        ),
                                                shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                            text = "🔄 Bezel",
                            style = MaterialTheme.typography.caption2,
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

```


**Tool Call: Read file `app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt`**
Status: Completed

```rubiks-algo/app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt
package com.example.rubiksalgo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.rubiksalgo.model.RubiksStep

/**
 * RubiksCard for Wear OS - Ultra Compact Version
 *
 * Optimized for small watch screens with scrollable content Prioritizes showing the algorithm
 * prominently
 */
@Composable
fun RubiksCard(step: RubiksStep, modifier: Modifier = Modifier) {
    Card(
            onClick = {},
            modifier = modifier.fillMaxWidth().fillMaxHeight(0.88f),
            backgroundPainter =
                    CardDefaults.cardBackgroundPainter(
                            startBackgroundColor = MaterialTheme.colors.surface,
                            endBackgroundColor = MaterialTheme.colors.surface
                    )
    ) {
        // Make content scrollable to ensure everything is accessible
        Column(
                modifier =
                        Modifier.fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {

            // Note (if present) - very compact - moved this to the top
            if (step.note.isNotEmpty()) {
                Text(
                        text = "💡 ${step.note}",
                        style = MaterialTheme.typography.caption3,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            // Step Number Badge (very compact)
            Box(
                    modifier =
                            Modifier.clip(RoundedCornerShape(50))
                                    .background(MaterialTheme.colors.primary)
                                    .padding(horizontal = 10.dp, vertical = 1.dp)
            ) {
                Text(
                        text = "Step ${step.id}",
                        style = MaterialTheme.typography.caption2,
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Instruction (compact)
            Text(
                    text = step.instruction,
                    style = MaterialTheme.typography.caption1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 3
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Algorithm - THE MOST IMPORTANT PART
            // Large, bold, and impossible to miss
            Box(
                    modifier =
                            Modifier.fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colors.primary.copy(alpha = 0.15f))
                                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text(
                        text = step.algorithm,
                        style =
                                MaterialTheme.typography.title1.copy(
                                        fontSize = 22.sp,
                                        letterSpacing = 1.sp
                                ),
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

```


**Tool Call: Read file `gradle/libs.versions.toml`**
Status: Completed

```rubiks-algo/gradle/libs.versions.toml
[versions]
agp = "8.13.1"
kotlin = "2.0.21"
coreKtx = "1.10.1"
junit = "4.13.2"
junitVersion = "1.1.5"
espressoCore = "3.5.1"
lifecycleRuntimeKtx = "2.6.1"
activityCompose = "1.8.0"
composeBom = "2024.09.00"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }


```


## Assistant

Perfect! Now let me check the helper scripts to complete the picture:

**Tool Call: Read file `run-watch.sh`**
Status: Completed

```rubiks-algo/run-watch.sh
./gradlew assembleDebug

./gradlew clean assembleDebug

./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity



adb pair 192.168.1.16:45993
adb connect 192.168.1.16:45735
adb disconnect 192.168.1.16:45993
adb devices

export ANDROID_SERIAL=192.168.1.16:45735 && ./gradlew installDebug && adb shell am start -n com.example.rubiksalgo/.MainActivity

```


**Tool Call: Read file `emulate-watch.sh`**
Status: Completed

```rubiks-algo/emulate-watch.sh
~/Library/Android/sdk/emulator/emulator -list-avds

~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager list device | grep "wear"

echo "no" | ~/Library/Android/sdk/cmdline-tools/latest/bin/avdmanager create avd \
  --name "WearOS_Watch" \
  --package "system-images;android-33;android-wear;arm64-v8a" \
  --device "wearos_large_round" \
  --tag "android-wear"

~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch

./gradlew clean assembleDebug


export ANDROID_SERIAL=emulator-5554 && ./gradlew installDebug && adb -s emulator-5554 shell am start -n com.example.rubiksalgo/.MainActivity

```


## Assistant

Perfect! Now I have a complete picture. Let me give you a comprehensive breakdown:

---

## **Project Overview: Rubiks Algo Wear OS App** 🎯

This is a **Wear OS 3.0+ application** that provides a cheat sheet for Rubik's Cube algorithms optimized for smartwatches with physical bezel support (like Samsung Galaxy Watch). Think of it as a wrist-mounted flashcard deck for solving algorithms.

---

## **📁 Project Structure**

```/dev/null/structure.txt#L1-15
rubiks-algo/
├── app/
│   ├── src/main/java/com/example/rubiksalgo/
│   │   ├── MainActivity.kt                    # Entry point
│   │   ├── model/RubiksStep.kt               # Data model
│   │   ├── data/RubiksRepository.kt          # Data source (Repository pattern)
│   │   ├── ui/
│   │   │   ├── screens/HomeScreen.kt         # Main UI screen with paging + bezel
│   │   │   ├── components/RubiksCard.kt      # Individual card for each algorithm
│   │   │   └── theme/                        # Color, typography, theme configs
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts                          # Root build config
├── settings.gradle.kts
├── run-watch.sh                              # Deploy to physical watch
└── emulate-watch.sh                          # Run on emulator
```

---

## **🛠️ Tech Stack & Libraries**

### **Core Android/Kotlin:**
- **Kotlin 2.0.21** - Modern JVM target 17
- **Android Gradle Plugin 8.13.1**
- **Min SDK 30** (Wear OS 3.0+), Target SDK 36

### **UI Framework - Jetpack Compose for Wear OS:**
- **`androidx.wear.compose:compose-material`** (v1.3.1) - Wear OS Material Design components
- **`androidx.wear.compose:compose-foundation`** (v1.3.1) - Core Wear OS UI building blocks
- **`androidx.wear.compose:compose-navigation`** (v1.3.1) - Navigation for watch screens
- **`androidx.compose.foundation.pager.HorizontalPager`** - Horizontal swipeable pages

### **Rotary Input (Physical Bezel Support):**
- **Horologist** (Google's official Wear OS toolkit):
  - `horologist-compose-layout` (v0.6.8)
  - `horologist-composables` (v0.6.8)
  - Provides `rotaryWithScroll()` modifier for bezel rotation

### **Dependencies Management:**
- Uses **Gradle Version Catalog** (`libs.versions.toml`)
- Compose BOM (Bill of Materials) for version alignment

---

## **🏗️ Architecture & Design Patterns**

### **1. Repository Pattern**
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt#L1-13
/**
 * Repository Object.
 * * Industry Standard:
 * - The "Repository Pattern" abstracts the data source.
 * - The UI doesn't know if this data is hardcoded, from a Database (Room), or an API (Retrofit).
 * - We use an 'object' (Singleton) here for simplicity, but in a larger app, this would be a class
 * injected via Dependency Injection (Hilt/Koin).
 */
object RubiksRepository {

    // The source of truth for our data
    val steps =
            listOf(
```

- **Single source of truth** for algorithm data
- Currently uses hardcoded data, but easily swappable for Room DB or network API
- Singleton pattern (`object`) - would use DI (Hilt/Koin) in production

### **2. Data Class (DTO)**
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt#L1-13
/**
 * Data Transfer Object (DTO) representing a single step in the algorithm.
 * * Industry Standard:
 * - This is a "Data Class" which automatically generates getters, setters, equals(), and hashcode().
 * - It has no dependency on Android framework (Context, Views), making it unit testable.
 */
data class RubiksStep(
    val id: Int,
    val instruction: String,
    val algorithm: String,
    val note: String = ""
)
```

- Immutable data model
- Framework-agnostic (easily unit testable)
- Kotlin data class auto-generates `equals()`, `hashCode()`, `copy()`

### **3. Composition over Inheritance**
- Full **Jetpack Compose** UI (no XML layouts)
- Composable functions as reusable components (`RubiksCard`, `HomeScreen`)

---

## **🎨 Key Architectural Decisions**

### **AMOLED Battery Optimization:**
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/MainActivity.kt#L23-32
            val darkColors =
                    Colors(
                            primary = Color(0xFF82B1FF), // Light blue for dark mode
                            primaryVariant = Color(0xFF448AFF), // Darker blue
                            secondary = Color(0xFFFFAB40), // Orange accent
                            secondaryVariant = Color(0xFFFF9100), // Darker orange
                            background = Color.Black, // Pure black for AMOLED
                            surface = Color(0xFF1E1E1E), // Dark gray for cards
                            error = Color(0xFFCF6679), // Red for errors
                            onPrimary = Color.Black, // Text on primary
```

- **Pure Black background** (`Color.Black`) - AMOLED pixels turn OFF, saving battery
- Dark surface colors for cards

### **Physical Bezel Support (Rotary Input):**
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt#L157-162
                        Modifier.weight(1f)
                                        .fillMaxWidth()
                                        .focusRequester(focusRequester)
                                        .focusTarget()
                                        .rotaryWithScroll(
                                                scrollableState = pagerState,
```

- Uses **Horologist's `rotaryWithScroll()`** to map bezel rotation to page scrolling
- **Focus management** is critical - must request focus for bezel to work:
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt#L48-52
    // Request focus when screen loads (CRITICAL for bezel to work)
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }
```

### **Auto-Snap Physics (TODO Item!):**
```rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt#L61-84
    // Snap to nearest page after scrolling settles
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress && pagerState.currentPageOffsetFraction != 0f) {
            // Wait a bit to see if more scrolling happens
            delay(100)

            if (!pagerState.isScrollInProgress) {
                // Calculate nearest page
                val currentOffset = pagerState.currentPageOffsetFraction
                val targetPage =
                        if (currentOffset.roundToInt() == 0) {
                            pagerState.currentPage
                        } else {
                            pagerState.currentPage + currentOffset.roundToInt()
                        }

                // Snap to the target page
                if (targetPage in 0 until steps.size) {
                    pagerState.animateScrollToPage(targetPage)
                }
            }
        }
    }
```

- Currently has custom snapping logic with 100ms debounce
- **TODO in README**: "fix the rotatory bezel snapping physics"
- This is where you'd contribute improvements!

### **Visual Feedback:**
- Animated toast appears when bezel is used ("🔄 Bezel")
- Page indicator shows position
- Vignette effect at top/bottom

---

## **📱 Wear OS Specific Features**

### **Manifest Configuration:**
```rubiks-algo/app/src/main/AndroidManifest.xml#L7-8
    <!-- Declare this is a Wear OS app - required for watch deployment -->
    <uses-feature android:name="android.hardware.type.watch" />
```
- Declares hardware type as watch
- Standalone mode (no companion phone app needed):
```rubiks-algo/app/src/main/AndroidManifest.xml#L21-24
        <!-- Wear OS metadata - indicates app runs standalone without phone -->
        <meta-data
            android:name="com.google.android.wearable.standalone"
            android:value="true"
        />
```

### **Wear OS Components Used:**
- **`TimeText`** - Standard watch time display at top
- **`Vignette`** - Curved screen edge fade effect
- **`HorizontalPageIndicator`** - Dots showing page position
- **`Scaffold`** - Wear OS screen layout container

---

## **🚀 Deployment Scripts**

### **Physical Watch Deployment:**
```rubiks-algo/run-watch.sh#L8-10
adb pair 192.168.1.16:45993
adb connect 192.168.1.16:45735
adb disconnect 192.168.1.16:45993
```
- WiFi ADB pairing for physical Galaxy Watch
- Builds and installs APK, launches MainActivity

### **Emulator:**
```rubiks-algo/emulate-watch.sh#L8-11
~/Library/Android/sdk/emulator/emulator -avd WearOS_Watch

./gradlew clean assembleDebug

```
- Creates Wear OS emulator (large round)
- Uses `android-33` wear system image

---

## **🎯 Data Flow**

1. **`MainActivity.onCreate()`** → Sets dark theme, launches `HomeScreen()`
2. **`HomeScreen()`** → Fetches data from `RubiksRepository.getAllSteps()`
3. **`HorizontalPager`** → Creates pages for each `RubiksStep`
4. **`RubiksCard()`** → Renders individual algorithm card
5. **User rotates bezel** → `rotaryWithScroll()` translates to pager scroll → Snap logic kicks in

---

## **🐛 Known Issues & TODO**

### **From README:**
> "TODO: fix the rotatory bezel snapping physics"

### **What This Means:**
- The auto-snap logic in `HomeScreen.kt` (lines 61-84) needs refinement
- Likely feels janky on physical hardware
- Possible improvements:
  - Better velocity detection
  - Smoother animation curves
  - Threshold tuning for when to snap

---

## **🔑 Key Contribution Areas**

1. **Bezel Physics** - Improve snapping/scrolling feel
2. **Data Layer** - Add Room DB or remote API support
3. **Features** - Search, favorites, custom algorithms
4. **UI Polish** - Animations, haptic feedback
5. **Testing** - Currently no real tests (just boilerplate)

---

## **💡 Pro Tips for Contributing**

1. **Test on real hardware** - Emulator bezel != physical bezel feel
2. **Battery testing** - AMOLED optimizations matter on watches
3. **Wear OS guidelines** - [Google Wear Design Guidelines](https://developer.android.com/design/ui/wear)
4. **Horologist docs** - [GitHub](https://google.github.io/horologist/) for rotary input best practices

Let me know which area you want to dive into first! The bezel snapping physics seems like the hottest ticket item. 🎢
