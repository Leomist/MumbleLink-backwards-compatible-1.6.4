/*
 mod_MumbleLink - Positional Audio Communication for Minecraft with Mumble
 Copyright 2011-2013 zsawyer (http://sourceforge.net/users/zsawyer)

 This file is part of mod_MumbleLink
 (http://sourceforge.net/projects/modmumblelink/).

 mod_MumbleLink is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 mod_MumbleLink is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with mod_MumbleLink.  If not, see <http://www.gnu.org/licenses/>.

 */
package zsawyer.mods.mumblelink.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Proxy for Minecraft instance that provides safe access to fields
 * that may have different names across different Minecraft versions
 * 
 * @author zsawyer
 */
public class MinecraftProxy {

	private Minecraft minecraft;

	public MinecraftProxy(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	/**
	 * Gets the current player, handling different field names across versions
	 * 
	 * @return the current player, or null if not found or field access fails
	 */
	public EntityPlayer getPlayer() {
		Object playerObj = ReflectionHelper.getField(minecraft, "thePlayer",
				"player");
		if (playerObj instanceof EntityPlayer) {
			return (EntityPlayer) playerObj;
		}
		return null;
	}

	/**
	 * Gets the current world, handling different field names across versions
	 * 
	 * @return the current world, or null if not found or field access fails
	 */
	public World getWorld() {
		Object worldObj = ReflectionHelper.getField(minecraft, "theWorld", "world");
		if (worldObj instanceof World) {
			return (World) worldObj;
		}
		return null;
	}

	/**
	 * Gets the underlying Minecraft instance
	 * 
	 * @return the Minecraft instance
	 */
	public Minecraft getMinecraft() {
		return minecraft;
	}
}
