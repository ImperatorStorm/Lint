/*
 * Lint
 * Copyright (C) 2020 hYdos, Valoeghese, ramidzkh
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.hydos.lint.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.hydos.lint.mixinimpl.SoundShitCache;
import net.minecraft.client.sound.BiomeEffectSoundPlayer;
import net.minecraft.world.biome.Biome;

@Mixin(BiomeEffectSoundPlayer.class)
public class BiomeEffectSoundPlayerMixin {
	@Shadow
	private Biome activeBiome;

	@Inject(
			at = @At(value = "HEAD"),
			method = "tick")
	public void musicGood1(CallbackInfo info) {
		if (this.activeBiome != null) {
			SoundShitCache.prev = this.activeBiome.getLoopSound();
		}
	}

	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lit/unimi/dsi/fastutil/objects/Object2ObjectArrayMap;values()Lit/unimi/dsi/fastutil/objects/ObjectCollection;",
					ordinal = 1),
			method = "tick")
	public void musicGood2(CallbackInfo info) {
		SoundShitCache.next = this.activeBiome.getLoopSound();
	}
}