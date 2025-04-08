plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
}

android {
	namespace = "br.com.zafcode.olimpo"
	compileSdk = 35
	
	defaultConfig {
		applicationId = "br.com.zafcode.olimpo"
		minSdk = 26
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	
	kotlinOptions {
		jvmTarget = "1.8"
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
	
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	
	implementation(libs.google.fonts)
	
	implementation(libs.voyager.navigator)
	implementation(libs.voyager.transitions)

	implementation(libs.retrofit)
	implementation(libs.retrofit.gson)

	implementation(libs.richtext.ui)
	implementation(libs.richtext.ui.material3)
	implementation(libs.richtext.commonmark)
	
	debugImplementation(libs.androidx.ui.tooling)
}