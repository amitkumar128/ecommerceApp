# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep the MainActivity class and its methods
-keep class com.myecommapp.MainActivity {
    <init>();
    public <methods>;
}

# Keep the BottomNavigationBar composable function
-keepclassmembers class com.myecommapp.MainActivity {
    public void BottomNavigationBar*;
}

# Keep all classes and functions inside the navigation package
-keep class com.myecommapp.navigation.** {
    <init>();
    public <methods>;
}

# Keep all classes and functions inside the ui.feature package
-keep class com.myecommapp.ui.feature.** {
    <init>();
    public <methods>;
}

# Keep the sealed class BottomNavItems and its subclasses
-keep class com.myecommapp.BottomNavItems {
    <init>();
    public static class*;
}


# Keep all Composable functions (marked with @Composable annotation)
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# Keep the reflection for NavHost routing
-keepclassmembers class ** {
    @androidx.navigation.NavHost <methods>;
}

# Keep Kotlin metadata for reflection
-keepclassmembers class kotlin.Metadata {
    *;
}

# Preserve generic type information for reflection
-keepattributes Signature, InnerClasses
