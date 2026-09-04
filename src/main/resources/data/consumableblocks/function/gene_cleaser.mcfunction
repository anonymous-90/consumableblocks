#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Ice_cleanser=true}}] run advancement revoke @s only consumable_blocks:eat_ice
#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Ice_cleanser=true}}] run attribute @s movement_speed modifier remove ice_eaten


#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Obsidian_cleanser=true}}] run advancement revoke @s only consumable_blocks:eat_obsidian
#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Obsidian_cleanser=true}}] run attribute @s minecraft:explosion_knockback_resistance modifier remove obsidian_eaten


#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Slime_cleanser=true}}] run advancement revoke @s only consumable_blocks:eat_slime
#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Slime_cleanser=true}}] run attribute @s bounciness modifier remove slime_eaten

#execute if entity @s[advancements={consumable_blocks:gene_cleaser={Furnace_cleanser=true}}] run advancement revoke @s only consumable_blocks:eat_furnace
# to be added
#advancement revoke @s only consumable_blocks:gene_cleaser