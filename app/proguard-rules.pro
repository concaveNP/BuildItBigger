# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/silver/Development/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-keep public class com.concavenp.nanodegree.androidlib.JokeActivity
-verbose

# From the documentation it sounds like this option is the nuclear one and to be
# avoided if possible.  Just doing it now to get going as I've been
# stuck on ProGuard for a while now.
-ignorewarnings

#-dontwarn okio.**
#-dontwarn sun.misc.Unsafe
