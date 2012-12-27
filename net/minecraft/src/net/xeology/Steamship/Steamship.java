package net.minecraft.src.net.xeology.Steamship;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.Map;
import java.util.Properties;
import java.io.*;
import java.lang.reflect.Constructor;
import net.minecraft.block.Block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

@Mod(modid = "Steamship", name = "Steamship", version = "1.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
        clientPacketHandlerSpec = @SidedPacketHandler(channels = {"Steamship" }, packetHandler = ClientPacketHandler.class),
        serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Steamship" }, packetHandler = ServerPacketHandler.class))

public class Steamship{
    
@Instance("Steamship")
public static Steamship instance;
     
    public static int spriteindex;
    public static Item airShip = (new ItemAirship(4242)).setIconIndex(
	    ModLoader.addOverride("/gui/items.png", "/gui/airship.png"))
	    .setItemName("Airship");
    public static Item engine = (new Item(4243)).setIconIndex(
	    ModLoader.addOverride("/gui/items.png", "/gui/engine.png"))
	    .setItemName("Engine");
    public static Item balloon = (new Item(4244)).setIconIndex(
	    ModLoader.addOverride("/gui/items.png", "/gui/balloon.png"))
	    .setItemName("Balloon");
    public static int KEY_UP = Keyboard.KEY_SPACE;
    public static int KEY_DOWN = Keyboard.KEY_LSHIFT;
    //public static int KEY_CHEST = Keyboard.KEY_R;
    public static int KEY_FIRE = Keyboard.KEY_LCONTROL;
    public static boolean SHOW_BOILER = false;
    public static GuiIngame chat;
    private static World world;

    public String Version() {
	//
	return "Steamship V1.2 for 1.4.6";
    }

   
    public String getVersion() {
	// TODO Auto-generated method stub	
	return "1.2/1.4.6";
    }

  @Init
    public void load(FMLInitializationEvent event) {
      instance=this;
      
      // Read properties file.
	Properties properties = new Properties();
	try {
	    properties.load(new FileInputStream(Minecraft.getMinecraftDir()
		    + "/airship.properties"));

	    KEY_UP = Keyboard.getKeyIndex(properties.getProperty("ascend"));
	    KEY_DOWN = Keyboard.getKeyIndex(properties.getProperty("descend"));
	    //KEY_CHEST = Keyboard.getKeyIndex(properties.getProperty("chest"));
	    KEY_FIRE = Keyboard.getKeyIndex(properties.getProperty("fire"));
	    String temp = properties.getProperty("boiler");
	    if (temp.contains("show")) {
		SHOW_BOILER = true;
	    } else {
		SHOW_BOILER = false;
	    }

	} catch (IOException e) {
	    properties.setProperty("ascend", Keyboard
		    .getKeyName(Keyboard.KEY_SPACE));
	    properties.setProperty("descend", Keyboard
		    .getKeyName(Keyboard.KEY_LSHIFT));
	    //properties.setProperty("chest", Keyboard
	//	    .getKeyName(Keyboard.KEY_RSHIFT));
	    properties.setProperty("fire", Keyboard
		    .getKeyName(Keyboard.KEY_LCONTROL));
	    properties.setProperty("boiler", "show");

	}

	// Write properties file.
	try {
	    properties.store(new FileOutputStream(Minecraft.getMinecraftDir()
		    + "/airship.properties"), null);
	} catch (IOException e) {
	}
      
	// ModLoader.AddName(airShip, "Airship");
	ModLoader.addName(airShip, "Airship");
	// Engine
	ModLoader.addName(engine, "Engine");
	// Balloon
	ModLoader.addName(balloon, "Balloon");
	// Airship w/o Engines
	int id=ModLoader.getUniqueEntityId();
	RenderingRegistry.instance().registerEntityRenderingHandler(EntityAirship.class, new RenderAirship(new ModelAirship(),
		new ModelBalloon(), 3.0f));
	EntityRegistry.registerModEntity(EntityAirship.class, "Airship", id, this, 250, 1, true);
	EntityRegistry.registerGlobalEntityID(EntityAirship.class, "Airship", id);
	//ModLoader.registerEntityID(EntityAirship.class, "Airship", id);
	//ModLoader.addEntityTracker(this, EntityAirship.class, id, 80, 1, false);

	ModLoader.addRecipe(new ItemStack(airShip, 1), new Object[]{
		    "XBX",
		    "EFE",
		    "XDX",
		    Character.valueOf('X'), Item.silk,
		    Character.valueOf('B'), balloon,
		    Character.valueOf('C'), Block.chest,
		    Character.valueOf('E'), engine,
		    Character.valueOf('L'), Block.dispenser,
		    Character.valueOf('D'), Item.boat,
		    Character.valueOf('F'), Block.stoneOvenIdle,});

	ModLoader.addRecipe(new ItemStack(engine, 1), new Object[]{
		    "###",
		    "#X#",
		    "###",
		    Character.valueOf('#'), Item.ingotIron,
		    Character.valueOf('X'), Block.pistonBase});

//		ModLoader.AddRecipe(new ItemStack(engine, 1), new Object[] { 
//				"###",
//				"#B#", 
//				"###", 
//			Character.valueOf('#'), Item.ingotIron,
//			Character.valueOf('B'), Block.stoneOvenIdle });

	ModLoader.addRecipe(new ItemStack(balloon, 1), new Object[]{
		    "###",
		    "# #",
		    "###",
		    Character.valueOf('#'), Item.leather});

	
	//ModLoader.setInGameHook(this, true, false);
	//ModLoader.registerPacketChannel(this, "ZajAirship");
	
	KeyBindingRegistry.registerKeyBinding(new Chest());
	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());	
	
	 
    }
    
}
