package br.com.adeilson.nearby.core.network

import br.com.adeilson.nearby.core.network.KtorHttpClient.httpClientAndroid
import br.com.adeilson.nearby.data.model.Category
import br.com.adeilson.nearby.data.model.Coupon
import br.com.adeilson.nearby.data.model.Market
import br.com.adeilson.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {
    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_PHYSICAL_BASE_URL = "http://192.168.1.217:3333"

    private const val BASE_URL = LOCAL_HOST_PHYSICAL_BASE_URL

    // 4 - gerar cupom a partir da leitura do qrcode

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories: List<Category> = httpClientAndroid.get("$BASE_URL/categories").body()

        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets: List<Market> =
            httpClientAndroid.get("$BASE_URL/markets/category/$categoryId").body()

        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val marketDetails: MarketDetails =
            httpClientAndroid.get("$BASE_URL/markets/$marketId").body()

        Result.success(marketDetails)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon =
            httpClientAndroid.patch("$BASE_URL/coupons/$marketId").body<Coupon>()

        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }
}