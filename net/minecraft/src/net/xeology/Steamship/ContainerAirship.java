// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
package net.minecraft.src.net.xeology.Steamship;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// Referenced classes of package net.minecraft.src:
//            Container, Slot, SlotFurnace, InventoryPlayer, 
//            ICrafting, TileEntityFurnace, ItemStack, EntityPlayer
public class ContainerAirship extends Container {

    private IInventory lowerChestInventory;
    private int numRows;
    private EntityAirship airship;

    public ContainerAirship(InventoryPlayer inventoryplayer, EntityAirship entityairship)
    {
        addSlotToContainer(new Slot(entityairship, 13, 134, 16));
        addSlotToContainer(new Slot(entityairship, 12, 134, 52));
	
        for (int i = 0; i < 3; i++)
        {
            for (int l = 0; l < 4; l++)
            {
                addSlotToContainer(new Slot(entityairship, l + i * 4, 8 + l * 18, 16 + i * 18));
            }
        }
	
        bindPlayerInventory(inventoryplayer);
    }
    
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 9; j++) {
                                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                                8 + j * 18, 84 + i * 18));
                        }
                }

                for (int i = 0; i < 9; i++) {
                        addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
                }
        }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i < numRows * 9)
            {
                if (!mergeItemStack(itemstack1, numRows * 9, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }        

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public ItemStack getStackInSlot(EntityPlayer player,int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 0)
            {
                mergeItemStack(itemstack1, 9, 45, true);
            }
            else if (i >= 9 && i < 36)
            {
                mergeItemStack(itemstack1, 36, 45, false);
            }
            else if (i >= 36 && i < 45)
            {
                mergeItemStack(itemstack1, 9, 36, false);
            }
            else
            {
                mergeItemStack(itemstack1, 9, 45, false);
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(player,itemstack1);
            }
            else
            {
                return null;
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }
}
