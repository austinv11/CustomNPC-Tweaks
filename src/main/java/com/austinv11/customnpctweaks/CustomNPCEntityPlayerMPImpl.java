package com.austinv11.customnpctweaks;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.WorldServer;
import noppes.npcs.entity.EntityNPCInterface;

public class CustomNPCEntityPlayerMPImpl extends EntityPlayerMP {
	
	private String name;
	
	public CustomNPCEntityPlayerMPImpl(EntityNPCInterface sender) {
		super(MinecraftServer.getServer(), (WorldServer) sender.getEntityWorld(),
				new GameProfile(null, sender.getCommandSenderName()), new ItemInWorldManager(sender.getEntityWorld()));
		name = sender.getCommandSenderName();
		this.posX = sender.getPlayerCoordinates().posX;
		this.posY = sender.getPlayerCoordinates().posY;
		this.posZ = sender.getPlayerCoordinates().posZ;
	}
	
	@Override
	public String getDisplayName() {
		return name;
	}
	
	@Override
	public String getCommandSenderName() {
		return name;
	}
}
