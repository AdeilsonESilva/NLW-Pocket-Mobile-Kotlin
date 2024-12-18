package br.com.adeilson.nearby.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.adeilson.nearby.data.model.Market
import br.com.adeilson.nearby.data.model.mock.mockUserLocation
import br.com.adeilson.nearby.ui.components.category.NearbyCategoryFilterChipList
import br.com.adeilson.nearby.ui.components.home.NearbyGoogleMap
import br.com.adeilson.nearby.ui.components.market.NearbyMarketCardList
import br.com.adeilson.nearby.ui.theme.Gray100
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val configuration = LocalConfiguration.current

    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetState,
        sheetContainerColor = Gray100,
        sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            if (!uiState.markets.isNullOrEmpty())
                NearbyMarketCardList(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
//                        markets = mockMarkets,
                    markets = uiState.markets,
                    onMarketClick = { selectedMarket ->
                        onNavigateToMarketDetails(selectedMarket)
                    }
                )
        },
        content = {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(mockUserLocation, 13f)
            }
            val uiSettings by remember {
                mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it
                            .calculateBottomPadding()
                            .minus(8.dp)
                    )
            ) {
                NearbyGoogleMap(uiState = uiState)

                if (!uiState.categories.isNullOrEmpty())
                    NearbyCategoryFilterChipList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
//                            categories = mockCategories,
                        categories = uiState.categories,
                        onSelectedCategoryChanged = { selectedCategory ->
                            onEvent(HomeUiEvent.OnFetchMarkets(categoryId = selectedCategory.id))
//                                isBottomSheetOpened = false
                        }
                    )
            }
        }
    )
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onNavigateToMarketDetails = {}, uiState = HomeUiState(), onEvent = {})
}