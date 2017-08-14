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

#-dontwarn com.google.errorprone.**
#-dontwarn sun.misc.Unsafe
#-dontwarn com.google.j2objc.**
#-dontwarn junit.**
#-dontwarn org.jmock.**
#-dontwarn java.lang.**
#-dontwarn com.huawei.**
#-dontwarn com.igexin.**
#-dontwarn java.nio.**
#-dontwarn java.beans.**
#-dontwarn javax.lang.model.**
#-dontwarn org.easymock.**
#-dontwarn org.**
#
#-keep class com.google.errorprone.**{*;}
#-keep class sun.misc.Unsafe{*;}
#-keep class com.google.j2objc.**{*;}
#-keep class org.jmock.**{*;}
#-keep class java.lang.**{*;}
#-keep class com.huawei.**{*;}
#-keep class com.igexin.**{*;}
#-keep class java.nio.**{*;}
#-keep class java.beans.**{*;}
#-keep class javax.lang.model.**{*;}
#-keep class org.easymock.**{*;}

-dontusemixedcaseclassnames          #混淆时不使用大小写混合类名

-verbose                             #打印混淆的详细信息
#-optimizations !code/simplification/arithmetic,!field/*/!class/merging/*
-dontoptimize
-dontpreverify                       #不进行预校验,Android不需要,可加快混淆速度。
-ignorewarnings
-dontobfuscate

#v4包不混淆
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }

-keep class android.support.v7.app.** { *; }
-keep interface android.support.v7.app.** { *; }




-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }


-keep public class * extends android.support.v7.app.AppCompatActivity
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service

-keepattributes Signature #过滤泛型（不写可能出现类型转换错误）

-keepnames class * implements java.io.Serializable #需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）

-keepclassmembers class * implements java.io.Serializable{
static final long serialVersionUID;
private static final java.io.ObjectStreamField[] serialPersistentFields;
!static !transient <fields>;
private void writeObject(java.io.ObjectOutputStream);
private void readObject(java.io.ObjectInputStream);
java.lang.Object writeReplace();
java.lang.Object readResolve();
}

-keepclassmembernames class com.example.shixi_a.myapplication.bean.** { *; }  #转换JSON的JavaBean，类成员名称保护，使其不被混淆

-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

-keepclassmembers class **.R$* {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-dontwarn com.igexin.**
-keep c;ass com.igexin.** {*;}





