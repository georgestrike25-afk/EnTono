package com.afinador.acustico

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.afinador.acustico.ui.components.GuitarHeadstock
import com.afinador.acustico.ui.theme.AfinadorTheme
import com.afinador.core.TuningPresets
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** El mástil no debe fallar cuando su área mide 0 (pasa al redimensionar o en pantalla dividida). */
@RunWith(AndroidJUnit4::class)
class GuitarHeadstockLayoutTest {

    @get:Rule
    val rule = createComposeRule()

    private fun headstockIn(width: Int, height: Int) {
        rule.setContent {
            AfinadorTheme(dark = true) {
                Box(Modifier.size(width.dp, height.dp)) {
                    GuitarHeadstock(
                        targets = TuningPresets.STANDARD.targets(440.0, 0),
                        activeString = 1,
                        statusColor = Color.Green,
                        hasPitch = false,
                        selectedString = 1,
                        onSelectString = {},
                    )
                }
            }
        }
        rule.waitForIdle()
    }

    @Test
    fun altoCeroNoFalla() = headstockIn(360, 0)

    @Test
    fun anchoCeroNoFalla() = headstockIn(0, 300)

    @Test
    fun tamanoMuyChicoNoFalla() = headstockIn(40, 20)
}
