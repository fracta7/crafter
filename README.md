![app icon](crafter.png)

# Crafter

Crafter is companion app for Minecraft. It helps with crafting recipes, builds a recipe tree so you can efficiently craft multiple items. 

**_Currently in development_**.

## Table of Contents

- [Crafter](#crafter)
    - [Table of Contents](#table-of-contents)
    - [Introduction](#introduction)
    - [Features](#features)
    - [TODOs](#todos)
    - [Building](#building)
    - [Usage](#usage)
    - [Contributing](#contributing)
    - [Adding new recipes and items](#adding-new-recipes-and-items)
    - [License](#license)
    - [Contact](#contact)

## Introduction

Projects aims to assist with crafting long list of items for Minecraft. There are some websites provide such functionality but no mobile application and using native application is better than webapp sometimes thus this project is being developed.

## Features

List of key features and functionalities of the project.

- UI for recipe steps
- List of raw materials for recipe list
- Tracking leftovers
- add multiple recipes for single item
- add recipe switch between multiple recipes

## TODOs

List of features to do:

- Using existing items from inventory
- Recipe tree visualization
- Adding new items and recipes

## Building

Step-by-step instructions on how to build and set up the project.

1. Clone the repository: `git clone https://github.com/fracta7/crafter.git`
2. Navigate to the project directory: `cd crafter`
3. Open with Android Studio.
4. Build and run.

## Usage

Simply search for items and them to you crafting list, then click `craft!`

Screenshots:

<img src="images/screen1.png" alt="Main Menu"/>
<img src="images/screen2.png" alt="Crafting Menu"/>

## Contributing

If you wish to contribute, feel free. Fork the project and do Pull Request, I will check the code and if everything is good, it will be merged. :)

## Adding new recipes and items

In order to add new item and recipes, follow these steps:

- add entry in `com.fracta7.crafter.data.repository.ItemsInit.kt` for items
- add entry in `com.fracta7.crafter.data.repository.RecipesInit.kt` for recipes

Item data class is structured following way:

```kotlin
typealias ItemID = String
typealias TagID = String

/**
 * Minecraft Item.
 * @property id string id of an item.
 * @property name name of an item.
 * @property stackSize stack size of an item.
 * @property craftable indicates if it is craftable.
 * @property tags contains tags for categories.
 */
data class Item(
  val id: ItemID,
  val name: String,
  val stackSize: Int,
  val craftable: Boolean,
  val tags: List<TagID> = listOf("other")
)
```

Recipe is structured following way:

```kotlin
typealias RecipeTypeID = String

/**
 * Data class to represent recipes
 * @property result result of recipe
 * @property resultQuantity quantity of resulting item
 * @property requirements a map of required items mapped to their quantity.
 * @property recipeType defines the recipe type (crafting, smelting etc.).
 */
data class Recipe(
  val result: ItemID,
  val resultQuantity: Int,
  val requirements: Map<ItemID, Int>,
  val recipeType: RecipeTypeID
)
```

Recipe Types:

```kotlin
typealias RecipeTypeID = String
typealias RecipeTypeItemID = String

/**
 * data class to represent different recipe types.
 * @property id represents the ID of recipe.
 * @property name represents the name of recipe.
 * @property item is the itemID of recipe representation
 */
data class RecipeType(
  val id: RecipeTypeID,
  val name: String,
  val item: RecipeTypeItemID
)
```

Category:

```kotlin
/**
 * Represents item category
 * @property id is the ID of category
 * @property name is the name of category
 * @property item is the item ID of category
*/
data class Category(
    val id: TagID,
    val name: TagName,
    val item: ItemID
)
```

To add new item to the registry, add a new line in `ItemsInit.kt` with item properties in the function body inside the list:

```kotlin
fun itemsInit(): List<Item> {
    return listOf(
        Item(id = "stone", name = "Stone", stackSize = 64, craftable = false, tags = listOf("natural")),
        Item(id = "diamond", name = "Diamond", stackSize = 64, craftable = false, tags = listOf("ingredients")),
        //... add new items here
        )
}
```

To add new recipe to the registry, add a new line in `RecipesInit.kt` with recipe properties in the function body inside the list:

```kotlin
fun recipesInit(): List<Recipe> {
    return listOf(
        Recipe(
            result = "polished_granite",
            resultQuantity = 1,
            requirements = mapOf("granite" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_diorite",
            resultQuantity = 1,
            requirements = mapOf("diorite" to 1),
            recipeType = "cutting"
        ),
        //... add new recipes here
    )
}
```      

To add new RecipeType, go to `RecipeTypesInit.kt` and add a new entry in `recipeTypesInit(): List<RecipeType>`

```kotlin
fun recipeTypesInit(): List<RecipeType> {
    return listOf(
        RecipeType(id = "crafting_shapeless", name = "Crafting", item = "crafting_table"),
        RecipeType(id = "smelting", name = "Smelting", item = "furnace"),
        // new entries
    )
}
```

To add a new tag, go to `TagsInit.kt` and add new entry to a function `tagsInit()`:

```kotlin
fun tagsInit(): List<Category> {
    return listOf(
        Category(id = "building", name = "Building Blocks", item = "bricks"),
        Category(id = "colored", name = "Colored Blocks", item = "cyan_wool"),
        // new entry
    )
}
```

## License
MIT License

Copyright (c) 2024 Javokhir Matnazarov

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Contact

If you wish to contact me, my Email: `fracta7@gmail.com`