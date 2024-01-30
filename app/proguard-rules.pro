-optimizationpasses 65
-keepattributes Signature

-dontusemixedcaseclassnames
-keep class okhttp3.** { *; }
-keepclassmembers enum * { *; }
-keep class okio.** { *; }
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
-dontskipnonpubliclibraryclasses
-dontoptimize
-dontpreverify

