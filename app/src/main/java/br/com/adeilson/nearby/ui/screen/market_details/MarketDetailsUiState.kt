package br.com.adeilson.nearby.ui.screen.market_details

import br.com.adeilson.nearby.data.model.Rule

data class MarketDetailsUiState (
    val rules: List<Rule>? = null,
    val coupon: String? = null
)
