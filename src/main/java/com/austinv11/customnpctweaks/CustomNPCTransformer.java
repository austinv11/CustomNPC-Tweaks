package com.austinv11.customnpctweaks;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class CustomNPCTransformer implements IClassTransformer, Opcodes {
	
	@Override
	public byte[] transform(String className, String newClassName, byte[] byteCode) {
		if (className.equals("noppes.npcs.entity.EntityNPCInterface"))
			return transformNPCInterface(byteCode);
		return byteCode;
	}
	
	public byte[] transformNPCInterface(byte[] byteCode) {
		FMLLog.log(Level.INFO, "Patching CustomNPCs chat functions");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(byteCode);
		classReader.accept(classNode, 0);
		for (MethodNode methodNode : classNode.methods) {
			if (methodNode.name.contains("saySurrounding")) {
				InsnList instructions = new InsnList();
				instructions.add(new VarInsnNode(ALOAD, 0));
				instructions.add(new VarInsnNode(ALOAD, 1));
				instructions.add(new MethodInsnNode(INVOKESTATIC, "com/austinv11/customnpctweaks/CustomNPCHooks", "onSaySurrounding", 
						"(Lnoppes/npcs/entity/EntityNPCInterface;Lnoppes/npcs/controllers/Line;)V", false));
				methodNode.instructions.insert(instructions);
			}
		}
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
