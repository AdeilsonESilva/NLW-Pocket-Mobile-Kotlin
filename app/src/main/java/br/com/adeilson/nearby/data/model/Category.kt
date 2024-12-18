package br.com.adeilson.nearby.data.model

import androidx.annotation.DrawableRes
import br.com.adeilson.nearby.ui.components.category.CategoryFilterChipView
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val name: String
) {
    @get:DrawableRes
    val icon: Int?
        get() = CategoryFilterChipView.fromDescription(description = name)?.icon
}
