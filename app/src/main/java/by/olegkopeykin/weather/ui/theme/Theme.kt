package by.olegkopeykin.weather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
	// Primary brand color.
	primary = BlueGray800,
	primaryVariant = BlueGrayDark,
	onPrimary = Black,
	// Secondary brand color.
	secondary = Pink800,
	secondaryVariant = PinkDark,
	onSecondary = White,
	background = BlueGrayDark,
	onBackground = Black
)

private val LightColorPalette = lightColors(
	// Primary brand color.
	primary = White,
	primaryVariant = White,
	onPrimary = BlueGrayDark,
	// Secondary brand color.
	secondary = Pink800,
	secondaryVariant = PinkDark,
	onSecondary = BlueGray800,
	background = White,
	onBackground = BlueGrayDark
)

@Composable
fun WeatherApplicationTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors = if (darkTheme) {
		DarkColorPalette
	} else {
		LightColorPalette
	}

	MaterialTheme(
		colors = colors,
		content = content
	)
}
