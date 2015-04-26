package com.austinv11.customnpctweaks;

import net.minecraft.command.CommandBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import noppes.npcs.controllers.Line;
import noppes.npcs.entity.EntityNPCInterface;

public class CustomNPCHooks {
	
	public static void onSaySurrounding(EntityNPCInterface npcInterface, Line line) {
		IChatComponent component = CommandBase.func_147176_a(npcInterface, new String[]{line.text}, 0, true);
		MinecraftForge.EVENT_BUS.post(new ServerChatEvent(new CustomNPCEntityPlayerMPImpl(npcInterface), line.text, 
				new ChatComponentTranslation("chat.type.announcement", new Object[] {npcInterface.getCommandSenderName(), component})));
	}
}
