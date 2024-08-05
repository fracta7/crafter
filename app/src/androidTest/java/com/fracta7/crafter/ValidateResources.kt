package com.fracta7.crafter

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fracta7.crafter.data.repository.itemsInit
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidateResources {

    @Test
    fun validateItemImages() {
        val items = itemsInit()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val missingImages = validateItemImages(items, context)

        assertTrue("Some items are missing images.", missingImages.isEmpty())
    }
}

private fun validateItemImages(items: List<Item>, context: Context): List<ItemID> {
    val missingImages = mutableListOf<ItemID>()

    for (item in items) {
        val drawableResourceId = getDrawableId(context, item.id)
        if (drawableResourceId == 0) {
            println("\"${item.id}\" - FAIL")
            missingImages.add(item.id)
        } else {
            println("\"${item.id}\" - OK")
        }
    }

    if (missingImages.isNotEmpty()) {
        println("=== LIST OF ITEMS WITH MISSING IMAGES ===")
        missingImages.forEach { println("\"$it\"") }
    } else {
        println("All items have associated images.")
    }

    return missingImages
}

private fun getDrawableId(context: Context, itemId: String): Int {
    return context.resources.getIdentifier(itemId, "drawable", context.packageName)
}