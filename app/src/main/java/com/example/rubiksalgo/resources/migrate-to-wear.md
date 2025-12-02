## User

i need to run my current runnign andorid suimple app on wearos galaxy watch 4 classic with movable bezel running Wear OS 5 i need to chaneg the navigayteion to move right in the ui using tghe bezel lkike in the app we did using HorizontalPager(
    state = pagerState, // <--- This tracks the swipe position [@HomeScreen.kt](file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt) [@MainActivity.kt](file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/MainActivity.kt) create a migration plan with list of changes required to run this andorid app on wear os, keepit simple follow insdutry code and uptodate apis and juistify eveyr change

## Assistant

<thinking>
The user wants to migrate their current Android app to Wear OS for the Galaxy Watch 4 Classic with a movable bezel. They specifically want to use the bezel for navigation (moving right in the UI) like they were using HorizontalPager in their current app.

Let me analyze what needs to be done:

1. First, I should look at the current project structure to understand what we're working with
2. Create a migration plan that covers:
   - Wear OS dependencies and configuration
   - Replacing Material3 with Wear OS Compose libraries
   - Implementing bezel/rotary input support
   - Adapting the UI for round/small screens
   - Handling the HorizontalPager with rotary input

Key changes needed:
1. Update build.gradle files to include Wear OS dependencies
2. Update AndroidManifest.xml for Wear OS
3. Convert from Material3 to Wear Compose components
4. Implement rotary input handling for the bezel
5. Adapt UI for round screens (Galaxy Watch 4 Classic has a round display)
6. Replace Scaffold with Wear OS equivalents
7. Handle the HorizontalPager with rotary input integration

Let me first explore the project structure to understand what we have.
</thinking>

I'll help you create a comprehensive migration plan to convert your Android app to Wear OS with bezel navigation support. Let me first explore your project structure to understand what we're working with.

**Tool Call: List the `wearos/rubiks-algo` directory's contents**
Status: Completed

# Folders:
wearos/rubiks-algo/.gradle
wearos/rubiks-algo/.idea
wearos/rubiks-algo/.kotlin
wearos/rubiks-algo/app
wearos/rubiks-algo/gradle

# Files:
wearos/rubiks-algo/.gitignore
wearos/rubiks-algo/LICENSE
wearos/rubiks-algo/build.gradle.kts
wearos/rubiks-algo/emulate.sh
wearos/rubiks-algo/gradle.properties
wearos/rubiks-algo/gradlew
wearos/rubiks-algo/gradlew.bat
wearos/rubiks-algo/local.properties
wearos/rubiks-algo/run.sh
wearos/rubiks-algo/settings.gradle.kts


**Tool Call: Read file `rubiks-algo/app/build.gradle.kts`**
Status: Completed

```wearos/rubiks-algo/app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.rubiksalgo"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.rubiksalgo"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
```


**Tool Call: Read file `rubiks-algo/app/src/main/AndroidManifest.xml`**
Status: Completed

```wearos/rubiks-algo/app/src/main/AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.RubiksAlgo">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.RubiksAlgo">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```


**Tool Call: 1 match**
Status: Completed

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt

**Tool Call: 1 match**
Status: Completed

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt

**Tool Call: Read file `rubiks-algo/app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt`**
Status: Completed

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/components/RubiksCard.kt
package com.example.rubiksalgo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubiksalgo.model.RubiksStep

@Composable
fun RubiksCard(
    step: RubiksStep,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f), // Slightly smaller to make room for buttons
        shape = RoundedCornerShape(24.dp), // More rounded corners (Modern look)
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Step Number Badge
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Step ${step.id}",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Instruction
            Text(
                text = step.instruction,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Algorithm (The Hero Section)
            // We put it in a box to give it a slight background highlight
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    .padding(16.dp)
            ) {
                Text(
                    text = step.algorithm,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontSize = 32.sp, // Force large size
                        letterSpacing = 2.sp
                    ),
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary // Makes it pop!
                )
            }

            // Note
            if (step.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "💡 ${step.note}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
```


**Tool Call: Read file `rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt`**
Status: Completed

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/data/RubiksRepository.kt
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


**Tool Call: 1 match**
Status: Completed

file:///Users/mk677/Developer/wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt

**Tool Call: Read file `rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt`**
Status: Completed

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/model/RubiksStep.kt
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


## Assistant

Perfect! Now I have a complete understanding of your project. Let me create a comprehensive migration plan for converting this Android app to Wear OS with bezel navigation support.

## 📋 Wear OS Migration Plan

### **Overview**
Migrating from Android (Material3) to Wear OS requires adapting for round screens, smaller displays, and implementing rotary input (bezel) navigation. We'll use Wear Compose with industry-standard practices and up-to-date APIs (Wear Compose 1.3+).

---

## **Required Changes**

### **1. Build Configuration (build.gradle.kts)**

**Changes:**
- Add Wear OS dependencies
- Add Horologist library for rotary input handling (Google's official Wear OS toolkit)
- Update minSdk to 30 (Wear OS 3.0+ requirement for Wear Compose)

**Justification:**
- `wear-compose-material` & `wear-compose-foundation` are official Wear OS Compose libraries optimized for round screens
- `horologist-compose-layout` & `horologist-rotary` provide production-ready rotary input handling (maintained by Google)
- Wear OS 5 on Galaxy Watch 4 requires these modern libraries

```wearos/rubiks-algo/app/build.gradle.kts
dependencies {
    // Wear OS Core
    implementation("androidx.wear:wear:1.3.0")
    
    // Wear Compose (replaces Material3)
    implementation("androidx.wear.compose:compose-material:1.3.1")
    implementation("androidx.wear.compose:compose-foundation:1.3.1")
    implementation("androidx.wear.compose:compose-navigation:1.3.1")
    
    // Horologist - Google's Wear OS toolkit for rotary input
    implementation("com.google.android.horologist:horologist-compose-layout:0.6.8")
    implementation("com.google.android.horologist:horologist-composables:0.6.8")
    
    // Existing dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    
    // Keep for icons
    implementation(libs.androidx.compose.material3)
}
```

---

### **2. AndroidManifest.xml Updates**

**Changes:**
- Add `WAKE_LOCK` permission for always-on display support
- Add `<uses-feature>` for watch hardware requirement
- Add `<meta-data>` for standalone app configuration

**Justification:**
- Wear OS apps require explicit watch hardware declaration
- Standalone flag indicates app runs independently without phone
- WAKE_LOCK allows app to show during always-on mode

```wearos/rubiks-algo/app/src/main/AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- Wear OS Permissions -->
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <!-- Declare this is a Wear OS app -->
    <uses-feature android:name="android.hardware.type.watch" />

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.RubiksAlgo">
        
        <!-- Wear OS metadata -->
        <meta-data
            android:name="com.google.android.wearable.standalone"
            android:value="true" />

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.RubiksAlgo">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

---

### **3. MainActivity.kt - Wear Theme Setup**

**Changes:**
- Replace `MaterialTheme` with `MaterialTheme` from Wear Compose
- Use Wear-specific theme configuration

**Justification:**
- Wear Compose theme automatically handles round screen insets
- Provides optimized touch targets for small screens
- Includes proper color schemes for AMOLED displays

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/MainActivity.kt
package com.example.rubiksalgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import com.example.rubiksalgo.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Wear OS Material Theme (optimized for watches)
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}
```

---

### **4. HomeScreen.kt - Complete Wear OS Redesign**

**Changes:**
- Replace `Scaffold` with `ScalingLazyColumn` (Wear OS standard)
- Integrate `HorizontalPager` with `rotaryWithScroll` modifier for bezel input
- Remove top app bar (too large for watch screens)
- Simplify navigation (bezel + page indicator)
- Use responsive layout modifiers from Horologist

**Justification:**
- `ScalingLazyColumn` is Wear OS's standard scrollable container with automatic edge fading
- `rotaryWithScroll` from Horologist connects bezel rotation to pager navigation (official Google approach)
- Removing buttons saves screen space; bezel provides better UX on watches
- `HierarchicalFocusCoordinator` ensures rotary events route to the pager properly

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt
package com.example.rubiksalgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import com.example.rubiksalgo.data.RubiksRepository
import com.example.rubiksalgo.ui.components.RubiksCard
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen() {
    val steps = remember { RubiksRepository.getAllSteps() }
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val scrollableState = rememberScalingLazyListState()

    Scaffold(
        timeText = {
            // TimeText shows at top, scrolls away when user interacts
            TimeText(
                modifier = Modifier.scrollAway(scrollableState)
            )
        },
        vignette = {
            // Vignette provides edge fade for round screens
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            // Shows current page position on right edge (like Progress indicator)
            PositionIndicator(
                pagerState = pagerState
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Page Counter (compact for watch)
            Text(
                text = "${pagerState.currentPage + 1}/${steps.size}",
                style = MaterialTheme.typography.caption1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            // HorizontalPager with Rotary (Bezel) Support
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    // 🔥 THIS IS THE KEY: Connects bezel rotation to pager
                    .rotaryWithScroll(
                        scrollableState = pagerState,
                        focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
                    ),
                pageSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) { page ->
                RubiksCard(step = steps[page])
            }
        }
    }
}
```

---

### **5. RubiksCard.kt - Wear OS Optimized Component**

**Changes:**
- Replace `ElevatedCard` with `Card` from Wear Compose
- Reduce padding and sizes for smaller screens
- Simplify typography for readability
- Remove excessive decorations

**Justification:**
- Wear Compose `Card` is optimized for round screens with proper insets
- Galaxy Watch 4 Classic has ~1.4" display (450x450px) - needs compact layout
- Larger touch targets (48dp minimum) for accessibility
- Monospace font at smaller size for better readability

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/components/RubiksCard.kt
package com.example.rubiksalgo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.rubiksalgo.model.RubiksStep

@Composable
fun RubiksCard(
    step: RubiksStep,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /* Optional: Add haptic feedback or detail view */ },
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f), // Adjusted for watch screen
        backgroundPainter = androidx.wear.compose.material.CardDefaults.cardBackgroundPainter(
            startBackgroundColor = MaterialTheme.colors.surface,
            endBackgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Reduced from 24dp
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            // Step Number Badge (more compact)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colors.primary)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Step ${step.id}",
                    style = MaterialTheme.typography.caption1,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Instruction (smaller text for watch)
            Text(
                text = step.instruction,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Algorithm - The Hero Section
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background.copy(alpha = 0.6f))
                    .padding(12.dp)
            ) {
                Text(
                    text = step.algorithm,
                    style = MaterialTheme.typography.title2.copy(
                        fontSize = 24.sp, // Adjusted from 32sp
                        letterSpacing = 1.5.sp
                    ),
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.primary
                )
            }

            // Note (if present)
            if (step.note.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "💡 ${step.note}",
                    style = MaterialTheme.typography.caption2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}
```

---

## **Summary of Key Changes**

| **Component** | **Change** | **Justification** |
|---------------|------------|-------------------|
| **Dependencies** | Add Wear Compose + Horologist | Official Wear OS libraries with rotary support |
| **Manifest** | Add watch feature + permissions | Required for Wear OS app deployment |
| **MainActivity** | Use Wear MaterialTheme | Handles round screen insets automatically |
| **HomeScreen** | Add `rotaryWithScroll` modifier | Connects bezel rotation to HorizontalPager |
| **HomeScreen** | Replace Scaffold with Wear Scaffold | Adds TimeText, Vignette, PositionIndicator |
| **HomeScreen** | Remove buttons | Bezel provides better navigation on watches |
| **RubiksCard** | Use Wear Card component | Optimized for round screens |
| **RubiksCard** | Reduce sizes & padding | Fits 450x450px display of Galaxy Watch 4 |

---

## **How Bezel Navigation Works**

The magic happens with the `rotaryWithScroll` modifier:

```wearos/rubiks-algo/app/src/main/java/com/example/rubiksalgo/ui/screens/HomeScreen.kt
.rotaryWithScroll(
    scrollableState = pagerState,
    focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
)
```

- **When user rotates bezel clockwise** → Pager moves to next card
- **When user rotates bezel counter-clockwise** → Pager moves to previous card
- `focusRequester` ensures rotary events are captured by the pager
- Horologist handles velocity and fling behavior automatically

---

## **Testing on Galaxy Watch 4 Classic**

1. Enable Developer Mode on watch
2. Connect via ADB: `adb connect <watch-ip>:5555`
3. Build and install: `./gradlew installDebug`
4. Rotate bezel to navigate between Rubik's cube steps

The app will now work seamlessly with the physical bezel on your Galaxy Watch 4 Classic! 🎉
