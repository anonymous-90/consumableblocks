############
# current value key
# 1 = Ice Enabled
# 2 = Ice Disabled
# 3 = Ice Enabled
# 4 = Ice Disabled
# 5 = obsidian enabled
# 6 = obsidian disabled
# 7 = furnace enabled
# 8 = furnace disabled
# 99 = slime status
# 100 = Ice Status
# 101 = Obsidian Status
# 102 = furnace Status
########### 
# --- ICE (Button 1) ---

# 1. Ice disabled
execute if score @s config matches 2 run title @s[advancements={consumableblocks:ice_enabled=false,consumableblocks:ice_unlock=true}] actionbar {"text":"Ice Alrady Disabled","color":"dark_red","bold":true}
execute if score @s config matches 2 run title @s[advancements={consumableblocks:ice_enabled=true,consumableblocks:ice_unlock=true}] actionbar {"text":"Ice Disabled","color":"red","bold":true}
execute if score @s config matches 2 run advancement revoke @s[advancements={consumableblocks:ice_enabled=true,consumableblocks:ice_unlock=true}] only consumableblocks:ice_enabled

# Ice Enabled
execute if score @s config matches 1 run title @s[advancements={consumableblocks:ice_enabled=true,consumableblocks:ice_unlock=true}] actionbar {"text":"Ice Already Enabled","color":"dark_red","bold":true}
execute if score @s config matches 1 run title @s[advancements={consumableblocks:ice_enabled=false,consumableblocks:ice_unlock=true}] actionbar {"text":"Ice Enabled","color":"gold","bold":true}
execute if score @s config matches 1 run advancement grant @s[advancements={consumableblocks:ice_enabled=false,consumableblocks:ice_unlock=true}] only consumableblocks:ice_enabled
# --- Ice Status --
execute if score @s[advancements={consumableblocks:ice_enabled=true,consumableblocks:ice_unlock=true}] config matches 100 run tellraw @s {"text":"Status: Ice Enabled","color":"dark_purple","bold":true}
execute if score @s[advancements={consumableblocks:ice_enabled=false,consumableblocks:ice_unlock=true}] config matches 100 run tellraw @s {"text":"Status: Ice Disabled","color":"dark_purple","bold":true}


# --- SLIME (Button 2) ---

# 1. slime disabled
execute if score @s config matches 4 run title @s[advancements={consumableblocks:slime_enabled=false,consumableblocks:slime_unlock=true}] actionbar {"text":"Slime Already Disabled","color":"dark_red","bold":true}
execute if score @s config matches 4 run title @s[advancements={consumableblocks:slime_enabled=true,consumableblocks:slime_unlock=true}] actionbar {"text":"Slime Disabled","color":"red","bold":true}
execute if score @s config matches 4 run advancement revoke @s[advancements={consumableblocks:slime_enabled=true,consumableblocks:slime_unlock=true}] only consumableblocks:slime_enabled

# 2. slime enabled
execute if score @s config matches 3 run title @s[advancements={consumableblocks:slime_enabled=true,consumableblocks:slime_unlock=true}] actionbar {"text":"Slime Already Enabled","color":"dark_red","bold":true}
execute if score @s config matches 3 run title @s[advancements={consumableblocks:slime_enabled=false,consumableblocks:slime_unlock=true}] actionbar {"text":"Slime Enabled","color":"gold","bold":true}
execute if score @s config matches 3 run advancement grant @s[advancements={consumableblocks:slime_enabled=false,consumableblocks:slime_unlock=true}] only consumableblocks:slime_enabled
# Slime Status
execute if score @s[advancements={consumableblocks:slime_enabled=true,consumableblocks:slime_unlock=true}] config matches 99 run tellraw @s {"text":"Status: Slime Enabled","color":"dark_purple","bold":true}
execute if score @s[advancements={consumableblocks:slime_enabled=false,consumableblocks:slime_unlock=true}] config matches 99 run tellraw @s {"text":"Status: Slime Disabled","color":"dark_purple","bold":true}

#-----------------------------------------------------
# --- Obsidian (Button 3) ---

# 1. obsidian disabled
execute if score @s config matches 6 run title @s[advancements={consumableblocks:obsidian_enabled=false,consumableblocks:obsidian_unlock=true}] actionbar {"text":"Obsidian Already Disabled","color":"dark_red","bold":true}
execute if score @s config matches 6 run title @s[advancements={consumableblocks:obsidian_enabled=true,consumableblocks:obsidian_unlock=true}] actionbar {"text":"Obsidian Disabled","color":"red","bold":true}
execute if score @s config matches 6 run advancement revoke @s[advancements={consumableblocks:obsidian_enabled=true,consumableblocks:obsidian_unlock=true}] only consumableblocks:obsidian_enabled

# 2. obsidian enabled
execute if score @s config matches 5 run title @s[advancements={consumableblocks:obsidian_enabled=true,consumableblocks:obsidian_unlock=true}] actionbar {"text":"Obsidian Already Enabled","color":"dark_red","bold":true}
execute if score @s config matches 5 run title @s[advancements={consumableblocks:obsidian_enabled=false,consumableblocks:obsidian_unlock=true}] actionbar {"text":"Obsidian Enabled","color":"gold","bold":true}
execute if score @s config matches 5 run advancement grant @s[advancements={consumableblocks:obsidian_enabled=false,consumableblocks:obsidian_unlock=true}] only consumableblocks:obsidian_enabled
# obsidian Status
execute if score @s[advancements={consumableblocks:obsidian_enabled=true,consumableblocks:obsidian_unlock=true}] config matches 101 run tellraw @s {"text":"Status: Obsidian Enabled","color":"dark_purple","bold":true}
execute if score @s[advancements={consumableblocks:obsidian_enabled=false,consumableblocks:obsidian_unlock=true}] config matches 101 run tellraw @s {"text":"Status: Obsidian Disabled","color":"dark_purple","bold":true}
# --- furnace (Button 4) ---

# 1. furnace disabled
execute if score @s config matches 8 run title @s[advancements={consumableblocks:furnace_enabled=false,consumableblocks:furnace_unlock=true}] actionbar {"text":"Furnace Already Disabled","color":"dark_red","bold":true}
execute if score @s config matches 8 run title @s[advancements={consumableblocks:furnace_enabled=true,consumableblocks:furnace_unlock=true}] actionbar {"text":"Furnace Disabled","color":"red","bold":true}
execute if score @s config matches 8 run advancement revoke @s[advancements={consumableblocks:furnace_enabled=true,consumableblocks:furnace_unlock=true}] only consumableblocks:furnace_enabled

# 2. furnace enabled
execute if score @s config matches 7 run title @s[advancements={consumableblocks:furnace_enabled=true,consumableblocks:furnace_unlock=true}] actionbar {"text":"Furnace Already Enabled","color":"dark_red","bold":true}
execute if score @s config matches 7 run title @s[advancements={consumableblocks:furnace_enabled=false,consumableblocks:furnace_unlock=true}] actionbar {"text":"Furnace Enabled","color":"gold","bold":true}
execute if score @s config matches 7 run advancement grant @s[advancements={consumableblocks:furnace_enabled=false,consumableblocks:furnace_unlock=true}] only consumableblocks:furnace_enabled
# furnace Status
execute if score @s[advancements={consumableblocks:furnace_enabled=true,consumableblocks:furnace_unlock=true}] config matches 102 run tellraw @s {"text":"Status: Furnace Enabled","color":"dark_purple","bold":true}
execute if score @s[advancements={consumableblocks:furnace_enabled=false,consumableblocks:furnace_unlock=true}] config matches 102 run tellraw @s {"text":"Status: Furnace Disabled","color":"dark_purple","bold":true}


# recipe config

execute if score @s[tag=!in_world_craft] config matches 1001 run tag @s add in_world_craft 
execute if score @s[tag=in_world_craft] config matches 1000 run tag @s remove in_world_craft
execute if score @s config matches 1001 run function consumableblocks:craft/recipe_config
execute if score @s config matches 1000 run function consumableblocks:craft/recipe_config

#-----------------------------------------------------
# --- GLOBAL CLEANUP (At the very bottom) ---
# This catches both normal clicks (1, 2) and our safety flag (99) and resets them completely
scoreboard players set @s[scores={config=1..}] config 0
scoreboard players enable @s config