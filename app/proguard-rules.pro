# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn com.google.errorprone.**
-dontwarn sun.misc.Unsafe
-dontwarn com.google.j2objc.**
-dontwarn junit.**
-dontwarn org.jmock.**
-dontwarn java.lang.**
-dontwarn com.huawei.**
-dontwarn com.igexin.**
-dontwarn java.nio.**
-dontwarn java.beans.**
-dontwarn javax.lang.model.**
-dontwarn org.easymock.**
-dontwarn org.**

-keep class com.google.errorprone.**{*;}
-keep class sun.misc.Unsafe{*;}
-keep class com.google.j2objc.**{*;}
-keep class org.jmock.**{*;}
-keep class java.lang.**{*;}
-keep class com.huawei.**{*;}
-keep class com.igexin.**{*;}
-keep class java.nio.**{*;}
-keep class java.beans.**{*;}
-keep class javax.lang.model.**{*;}
-keep class org.easymock.**{*;}






