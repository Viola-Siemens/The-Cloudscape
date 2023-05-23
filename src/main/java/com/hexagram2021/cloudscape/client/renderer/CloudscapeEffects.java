package com.hexagram2021.cloudscape.client.renderer;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class CloudscapeEffects extends DimensionSpecialEffects {
	public CloudscapeEffects() {
		super(0.0F, false, DimensionSpecialEffects.SkyType.NORMAL, false, false);
	}

	@Override @NotNull
	public Vec3 getBrightnessDependentFogColor(@NotNull Vec3 rgb, float time) {
		return rgb;
	}

	@Override
	public boolean isFoggyAt(int p_108898_, int p_108899_) {
		return true;
	}
}
