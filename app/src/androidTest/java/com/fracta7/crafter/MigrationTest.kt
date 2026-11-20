package com.fracta7.crafter

import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fracta7.crafter.data.local.db.AppDatabase
import com.fracta7.crafter.data.local.db.MIGRATION_1_3
import com.fracta7.crafter.data.local.db.MIGRATION_2_3
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    companion object {
        private const val TEST_DB = "migration-test"
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java
    )

    @Test
    fun migrate1To3DropsLegacyTablesAndCreatesProjectsTable() {
        createLegacyDatabase(version = 1) { db ->
            db.execSQL("CREATE TABLE IF NOT EXISTS `items` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `stackSize` INTEGER, `craftable` INTEGER, `tags` TEXT, `custom` INTEGER)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `recipes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `result` TEXT, `resultQuantity` INTEGER, `requirements` TEXT, `recipeType` TEXT, `custom` INTEGER)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `item` TEXT, `custom` INTEGER)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `recipe_types` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `item` TEXT, `custom` INTEGER)")
            db.execSQL("INSERT INTO `items` (`id`, `name`, `stackSize`, `craftable`, `tags`, `custom`) VALUES ('custom_gem', 'Custom Gem', 64, 0, '[\"other\"]', 1)")
        }

        val db = helper.runMigrationsAndValidate(TEST_DB, 3, true, MIGRATION_1_3)
        db.query(
            "SELECT name FROM sqlite_master WHERE type='table' AND name IN ('items','recipes','categories','recipe_types')"
        ).use { cursor ->
            assertEquals(0, cursor.count)
        }
        db.execSQL(
            "INSERT INTO `projects` (`name`, `isDraft`, `targetsJson`, `ownedInventoryJson`, `selectedRecipesJson`, `createdAt`, `updatedAt`) VALUES ('Test', 0, '{\"stone\":1}', '{}', '{}', 1, 1)"
        )
        db.query("SELECT name FROM `projects`").use { cursor ->
            assertTrue(cursor.moveToFirst())
            assertEquals("Test", cursor.getString(0))
        }
    }

    @Test
    fun migrate2To3DropsLegacyTablesAndCreatesProjectsTable() {
        createLegacyDatabase(version = 2) { db ->
            db.execSQL("CREATE TABLE IF NOT EXISTS `items` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `stackSize` INTEGER, `craftable` INTEGER, `tags` TEXT)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `recipes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `result` TEXT, `resultQuantity` INTEGER, `requirements` TEXT, `recipeType` TEXT, `custom` INTEGER)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `item` TEXT, `custom` INTEGER)")
            db.execSQL("CREATE TABLE IF NOT EXISTS `recipe_types` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `id` TEXT, `name` TEXT, `item` TEXT, `custom` INTEGER)")
        }

        val db = helper.runMigrationsAndValidate(TEST_DB, 3, true, MIGRATION_2_3)
        db.query(
            "SELECT name FROM sqlite_master WHERE type='table' AND name IN ('items','recipes','categories','recipe_types')"
        ).use { cursor ->
            assertEquals(0, cursor.count)
        }
        db.execSQL(
            "INSERT INTO `projects` (`name`, `isDraft`, `targetsJson`, `ownedInventoryJson`, `selectedRecipesJson`, `createdAt`, `updatedAt`) VALUES ('Draft', 1, '{}', '{}', '{}', 2, 2)"
        )
        db.query("SELECT isDraft FROM `projects`").use { cursor ->
            assertTrue(cursor.moveToFirst())
            assertEquals(1, cursor.getInt(0))
        }
    }

    private fun createLegacyDatabase(version: Int, setup: (SQLiteDatabase) -> Unit) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase(TEST_DB)
        val dbFile = context.getDatabasePath(TEST_DB)
        dbFile.parentFile?.mkdirs()
        SQLiteDatabase.openOrCreateDatabase(dbFile, null).use { db ->
            setup(db)
            db.version = version
        }
    }
}
