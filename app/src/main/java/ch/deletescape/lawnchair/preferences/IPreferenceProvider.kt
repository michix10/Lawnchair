package ch.deletescape.lawnchair.preferences

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import ch.deletescape.lawnchair.Launcher

interface IPreferenceProvider {

    // -------------------
    // SORTED by Feature
    // -------------------

    // -------------------
    // 1) App Theme
    // -------------------

    val theme: String
    val darkTheme: Boolean
    val themeMode: Int

    fun migrateThemePref(context: Context)
    fun migratePullDownPref(context: Context)

    // -------------------
    // 2) Apps Drawer
    // -------------------

    val allAppsOpacity: Float
    // defines if hidden apps should be shown in drawer for changing their hidden state
    val showHidden: Boolean
    val allAppsIconScale: Float
    val allAppsIconTextScale: Float
    val useCustomAllAppsTextColor: Boolean

    // -------------------
    // 3) Desktop
    // -------------------

    // defines if a pinch gesture opens the desktop edit page
    val pinchToOverview: Boolean
    val centerWallpaper: Boolean

    // -------------------
    // 4) Weather
    // -------------------
    val weatherProvider: String
    val weatherApiKey: String
    val weatherCity: String
    val weatherUnit: String

    // --------------
    // Unsorted...
    // --------------

    // -----------------
    // FEATURES
    // -----------------

    // When enabled the status bar may show dark icons based on the top of the wallpaper.
    val lightStatusBar: Boolean
    val hotseatShouldUseExtractedColors: Boolean
    fun hotseatShouldUseExtractedColorsCache(default: Boolean): Boolean
    fun hotseatShouldUseExtractedColorsCache(value: Boolean, commit: Boolean = false)
    fun lightStatusBarKeyCache(default: Boolean): Boolean
    fun lightStatusBarKeyCache(value: Boolean, commit: Boolean = false)
    val enableHapticFeedback: Boolean
    val keepScrollState: Boolean
    val useFullWidthSearchBar: Boolean
    val showVoiceSearchButton: Boolean
    val showPixelBar: Boolean
    val homeOpensDrawer: Boolean
    val usePixelIcons: Boolean
    val enableScreenRotation: Boolean
    val hideAppLabels: Boolean
    val hideAllAppsAppLabels: Boolean
    val allowFullWidthWidgets: Boolean
    val showGoogleNowTab: Boolean
    val transparentHotseat: Boolean
    val enableDynamicUi: Boolean
    val enableBlur: Boolean
    val enableVibrancy: Boolean
    val useWhiteGoogleIcon: Boolean
    val useRoundSearchBar: Boolean
    val enableBackportShortcuts: Boolean
    val showTopShadow: Boolean
    val hideHotseat: Boolean
    val enablePlanes: Boolean
    val showWeather: Boolean
    val enableEditing: Boolean
    val animatedClockIcon: Boolean
    val animateClockIconAlternativeClockApps: Boolean

    val pulldownAction: String

    // -----------------
    // PREFERENCES
    // -----------------

    val blurRadius: Float
    val blurMode : Int
    val labelColorHue: String
    val labelColorVariation: String
    fun alternateIcon(key: String): String?
    fun alternateIcon(key: String, alternateIcon: String, commit: Boolean = false)
    fun removeAlternateIcon(key: String)
    fun appVisibility(context: Context, key: String, visible: Boolean, commit: Boolean = false)
    fun appVisibility(context: Context, key: String): Boolean
    var previousBuildNumber : Int
    var overrideIconShape: String
    fun removeOverrideIconShape()
    fun itemAlias(key: String, default: String): String
    fun itemAlias(key: String, value: String, commit: Boolean = false)
    var extractedColorsPreference: String
    var wallpaperId: Int
    fun numRows(default: String): String
    fun numCols(default: String): String
    fun numColsDrawer(default: String): String
    fun numColsDrawer(value: String, commit: Boolean = false)
    fun numRowsDrawer(default: String): String
    fun numRowsDrawer(value: String, commit: Boolean = false)
    fun numHotseatIcons(default: String): String
    val iconScaleSB: Float
    val iconTextScaleSB: Float
    val iconPackPackage: String
    val hotseatIconScale: Float

    // -----------------
    // GENERAL - BITS
    // -----------------

    fun getIntPref(key: String, default: Int) : Int
    fun setIntPref(key: String, value: Int, commit: Boolean = false)

    // -----------------
    // STATES
    // -----------------

    var requiresIconCacheReload: Boolean
    var emptyDatabaseCreated: Boolean
    fun removeEmptyDatabaseCreated()
    fun userCreationTimeKeyExists(key: Long): Boolean
    fun userCreationTimeKey(key: Long): Long
    fun userCreationTimeKey(key: Long, value: Long, commit: Boolean = false)
    var appsPendingInstalls: Set<String>?
    var restoreTaskPending: Boolean
    fun migrationSrcWorkspaceSize(default: String): String
    fun migrationSrcWorkspaceSize(value: String, commit: Boolean = false)
    fun migrationSrcHotseatCount(default: Int): Int
    fun migrationSrcHotseatCount(value: Int, commit: Boolean = false)

    // -----------------
    // LAUNCHER
    // -----------------

    var appsViewShown: Boolean

    // -----------------
    // LISTENER, FUNCTIONS
    // -----------------

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)
    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)

    fun showSettings(launcher: Launcher, view: View)

    fun beginBlockingEdit()
    fun endBlockingEdit()
}

inline fun IPreferenceProvider.blockingEdit(body: IPreferenceProvider.() -> Unit) {
    beginBlockingEdit()
    body(this)
    endBlockingEdit()
}