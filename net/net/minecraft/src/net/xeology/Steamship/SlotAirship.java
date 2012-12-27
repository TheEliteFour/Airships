/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.net.xeology.Steamship;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 *
 * @author Xeology
 */
public class SlotAirship extends Slot {

    private EntityPlayer player;
    
    public SlotAirship(EntityPlayer player, IInventory playerinventory, int slot, int x, int y) {
	super(playerinventory, slot, x, y);
	this.player = player;
    }
    
    
}
