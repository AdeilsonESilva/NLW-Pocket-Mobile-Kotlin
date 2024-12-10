package br.com.adeilson.nearby.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NearbyButton(modifier: Modifier = Modifier,
                 text: String? = null,
                 @DrawableRes iconRes: Int? = null,
                 onClick: () -> Unit,
) {
    Button(modifier = modifier, onClick = onClick) {}
}

@Preview
@Composable
private fun NewButtonPreview() {
    NearbyButton(modifier = Modifier.fillMaxWidth(), text="Confirmar") {  }
}