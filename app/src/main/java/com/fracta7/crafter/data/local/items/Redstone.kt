package com.fracta7.crafter.data.local.items

import com.fracta7.crafter.domain.model.Item

fun getRedstoneItems(): List<Item> {
    return listOf(
        Item(id = "redstone", name = "Redstone Dust", stackSize = 64, decomposable = false, tags = listOf("redstone", "ingredients")),
        Item(id = "repeater", name = "Redstone Repeater", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "comparator", name = "Redstone Comparator", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "target", name = "Target", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "lever", name = "Lever", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "calibrated_sculk_sensor", name = "Calibrated Sculk Sensor", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "tripwire_hook", name = "Tripwire Hook", stackSize = 16, decomposable = true, tags = listOf("redstone")),
        Item(id = "string", name = "String", stackSize = 64, decomposable = false, tags = listOf("redstone", "ingredients")),
        Item(id = "daylight_detector", name = "Daylight Detector", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "piston", name = "Piston", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "sticky_piston", name = "Sticky Piston", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "dispenser", name = "Dispenser", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "dropper", name = "Dropper", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "crafter", name = "Crafter", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "hopper", name = "Hopper", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "trapped_chest", name = "Trapped Chest", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "observer", name = "Observer", stackSize = 64, decomposable = true, tags = listOf("redstone")),
        Item(id = "rail", name = "Rail", stackSize = 64, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "powered_rail", name = "Powered Rail", stackSize = 64, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "detector_rail", name = "Detector Rail", stackSize = 64, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "activator_rail", name = "Activator Rail", stackSize = 64, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "minecart", name = "Minecart", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "hopper_minecart", name = "Minecart with Hopper", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "chest_minecart", name = "Minecart with Chest", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "furnace_minecart", name = "Minecart with Furnace", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "tnt_minecart", name = "Minecart with TNT", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "oak_chest_boat", name = "Oak Boat with Chest", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "bamboo_chest_raft", name = "Bamboo Raft with Chest", stackSize = 1, decomposable = true, tags = listOf("redstone", "tools")),
        Item(id = "tnt", name = "TNT", stackSize = 64, decomposable = true, tags = listOf("redstone", "combat")),
    )
}