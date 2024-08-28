package com.fracta7.crafter.data.local.db

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromTagList(tags: List<String>): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun toTagList(tagsString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(tagsString, listType)
    }

    @TypeConverter
    fun fromRequirementsMap(requirements: Map<String, Int>): String {
        return gson.toJson(requirements)
    }

    @TypeConverter
    fun toRequirementsMap(requirementsString: String): Map<String, Int> {
        val mapType = object : TypeToken<Map<String, Int>>() {}.type
        return gson.fromJson(requirementsString, mapType)
    }
}