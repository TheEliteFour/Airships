/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.net.xeology.Steamship;

import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 *
 * @author Xeology
 */
public class ClientProxy{
    
    
    public void registerRenderInformation() 
  {
	RenderingRegistry.instance().registerEntityRenderingHandler(EntityAirship.class, new RenderAirship(new ModelAirship(),
		new ModelBalloon(), 3.0f));
    }
    
}
