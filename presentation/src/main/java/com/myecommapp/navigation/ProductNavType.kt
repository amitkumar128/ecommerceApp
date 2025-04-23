package com.myecommapp.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.myecommapp.model.UiProductModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64


val productNavType = object : NavType<UiProductModel>(isNullableAllowed = false) {
    @SuppressLint("NewApi")
    override fun get(bundle: Bundle, key: String): UiProductModel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            return bundle.getParcelable(key, UiProductModel::class.java)
        return bundle.getParcelable(key) as? UiProductModel

    }

    @SuppressLint("NewApi")
    override fun parseValue(value: String): UiProductModel {
        val item = Json.decodeFromString<UiProductModel>(value)
        return item.copy(

                image = URLDecoder.decode(item.image, "UTF-8"),
                description = String(Base64.getDecoder().decode(item.description.toByteArray())).replace("/","_"),
                title = String(Base64.getDecoder().decode(item.description.toByteArray())).replace("/","_")
        )

    }

    @SuppressLint("NewApi")
    override fun serializeAsValue(value: UiProductModel): String {
        return Json.encodeToString(
            value.copy(
                image = URLEncoder.encode(value.image, "UTF-8"),
                description = String(
                    Base64.getEncoder().encode(value.description.toByteArray())
                ).replace("/", "_"),
                title = String(Base64.getEncoder().encode(value.description.toByteArray())).replace(
                    "/",
                    "_"
                )
            )
        )
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: UiProductModel
    ) {
        bundle.putParcelable(key, value)

    }

}