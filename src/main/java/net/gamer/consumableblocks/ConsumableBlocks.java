package net.gamer.consumableblocks;

import net.fabricmc.api.ModInitializer;
import net.gamer.consumableblocks.creativeTabs.ModCreativeModeTabs;
import net.gamer.consumableblocks.events.ModEvents;
import net.gamer.consumableblocks.fuelData.Fuel;
import net.gamer.consumableblocks.item.ModItems;
import net.gamer.consumableblocks.networking.ModPackets;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumableBlocks implements ModInitializer {
	public static final String MOD_ID = "consumableblocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModEvents.EventRegister();
		ModPackets.registerPackets();
		Fuel.FuelData.registerFuelData();
	}
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
