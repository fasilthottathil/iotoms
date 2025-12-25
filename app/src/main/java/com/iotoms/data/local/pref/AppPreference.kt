package com.iotoms.data.local.pref

import android.content.Context
import androidx.core.content.edit

/**
 * Created by Fasil on 25/12/2025
 */
class AppPreference(context: Context) {
    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    companion object {
        const val PREF_NAME = "iotoms_app_pref"
        const val KEY_IS_LOGGED_IN = "is_logged_in"
        const val KEY_DOMAIN_NAME = "domain_name"
        const val KEY_BEARER_TOKEN = "bearer_token"
    }

    fun getBearerToken(): String? {
        return sharedPref.getString(KEY_BEARER_TOKEN, null)
    }

    fun setBearerToken(token: String) {
        sharedPref.edit {
            putString(KEY_BEARER_TOKEN, token)
            apply()
        }
    }

    fun getDomainName(): String? {
        return sharedPref.getString(KEY_DOMAIN_NAME, null)
    }

    fun setDomainName(domain: String) {
        sharedPref.edit {
            putString(KEY_DOMAIN_NAME, domain)
            apply()
        }
    }

}