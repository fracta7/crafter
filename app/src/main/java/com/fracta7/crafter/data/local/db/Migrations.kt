package com.fracta7.crafter.data.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

private fun migrateLegacyDatabaseToV3(db: SupportSQLiteDatabase) {
    db.execSQL("DROP TABLE IF EXISTS `items`")
    db.execSQL("DROP TABLE IF EXISTS `recipes`")
    db.execSQL("DROP TABLE IF EXISTS `categories`")
    db.execSQL("DROP TABLE IF EXISTS `recipe_types`")
    db.execSQL(
        """
        CREATE TABLE IF NOT EXISTS `projects` (
            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `name` TEXT NOT NULL,
            `isDraft` INTEGER NOT NULL,
            `targetsJson` TEXT NOT NULL,
            `ownedInventoryJson` TEXT NOT NULL,
            `selectedRecipesJson` TEXT NOT NULL,
            `createdAt` INTEGER NOT NULL,
            `updatedAt` INTEGER NOT NULL
        )
        """.trimIndent()
    )
}

val MIGRATION_1_3 = object : Migration(1, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        migrateLegacyDatabaseToV3(db)
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        migrateLegacyDatabaseToV3(db)
    }
}
