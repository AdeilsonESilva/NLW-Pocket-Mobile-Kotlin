package br.com.adeilson.nearby.ui.screen.home

import br.com.adeilson.nearby.data.model.Category
import br.com.adeilson.nearby.data.model.Market
import com.google.android.gms.maps.model.LatLng

data class HomeUiState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocations: List<LatLng>? = null
)
