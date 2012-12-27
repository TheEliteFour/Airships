/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.net.xeology.Steamship;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Xeology
 */
public class Chest extends KeyHandler {

    static KeyBinding open = new KeyBinding("Open", Keyboard.KEY_R);
    static GuiAirship gui;
    
    public Chest() {                
                super(new KeyBinding[]{open}, new boolean[]{false});
        }
    
    @Override
    public void keyDown(EnumSet<TickType> es, KeyBinding kb, boolean bln, boolean bln1) {
	Entity ent=ModLoader.getMinecraftInstance().thePlayer.ridingEntity;
	if (ent==null){
	    return;
	}
	if (ent instanceof EntityAirship && gui==null){
	    //ModLoader.getMinecraftInstance().displayGuiScreen(new GuiAirship(ModLoader.getMinecraftInstance().thePlayer.inventory, (EntityAirship) ModLoader.getMinecraftInstance().thePlayer.ridingEntity));
	    ModLoader.getMinecraftInstance().thePlayer.openGui(Steamship.instance, 0, null, 0, 0, 0);
	}
    }
    
    

    @Override
    public void keyUp(EnumSet<TickType> es, KeyBinding kb, boolean bln) {
	
    }

    @Override
    public EnumSet<TickType> ticks() {
	return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
	return "Open Boiler";
    }
    
}
