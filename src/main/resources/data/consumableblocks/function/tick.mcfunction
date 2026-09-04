execute as @a[scores={config=1..}] run function consumableblocks:config/config_click
# execute if entity @e[type=warden] run data get entity @e[type=warden,limit=1] Health
# execute if entity @e[type=wither] run data get entity @e[type=warden,limit=1] Health

# data modify entity @e[nbt={NoAI:0b,CustomName:},type=!player,limit=1] NoAI set value 1b
# data modify storage test:health Health set from entity @e[type=skeleton,limit=1,sort=nearest] Health
# data modify entity @e[type=skeleton,limit=1,sort=nearest,nbt={CustomNameVisible:} 
# item replace entity @s weapon.mainhand with arrow[attribute_modifiers=[{type:"attack_damage",amount:7,operation: "add_value",display:{type:"default"},id:"superarrow",slot:"mainhand"}]] 1 
# item replace entity @s weapon.mainhand with bow[attribute_modifiers=[{type:"attack_damage",amount:7,operation: "add_value",display:{type:"default"},id:"superarrow",slot:"mainhand"}]] 1
# execute positioned as @s if entity @e[type=minecraft:item,name="Arrow"] if entity @e[type=item,name="Oak Log"] run summon item ~ ~ ~ {"Item":{id:"slime_block",count:1,components:{item_name:{text:"superslime",color:"dark_purple",bold:true},consumable:{animation:"eat",has_consume_particles:true,consume_seconds:3},enchantment_glint_override:true,lore:[{text:"Makes You Bouncy",color:"green",bold:true}],food:{nutrition:0,saturation:0,can_always_eat:true},attribute_modifiers:[{type:"block_interaction_range",operation:"add_value",amount:-999999999,id:"no_place",display:{type:"hidden"},slot:"mainhand"}],custom_data:{"consumable_blocks":"superSlime"}}}}
# execute if entity @e[type=item,name="superslime"] run kill @e[type=item,name=!"superslime"] 
scoreboard players enable @a config 
# test motion detection
# execute if entity @s[]
execute if entity @a[nbt={SelectedItem:{id:"minecraft:paper"}}] run function consumable_blocks:test
# crafting detection
execute if entity @a[tag=in_world_craft] run function consumableblocks:craft/crafting
function consumable_blocks:test

#################################################
