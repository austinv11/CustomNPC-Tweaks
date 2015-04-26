package com.austinv11.customnpctweaks;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class CustomNPCTransformerPlugin implements IFMLLoadingPlugin {
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[]{CustomNPCTransformer.class.getName()};
	}
	
	@Override
	public String getModContainerClass() {
		return null;
	}
	
	@Override
	public String getSetupClass() {
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) {
		
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
