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

import java.lang.reflect.Field;

/**
 * Utility class for safe reflection access to Minecraft fields
 * Handles different obfuscation and naming conventions across Minecraft versions
 * 
 * @author zsawyer
 */
public class ReflectionHelper {

	/**
	 * Safely gets a field from an object, trying multiple possible field names
	 * 
	 * @param obj the object to get the field from
	 * @param fieldNames the field names to try, in order of preference
	 * @return the field value if found and accessible, or null if the object is null,
	 *         no matching field exists, or the field cannot be accessed
	 */
	public static Object getField(Object obj, String... fieldNames) {
		if (obj == null) {
			return null;
		}

		Class<?> clazz = obj.getClass();
		for (String fieldName : fieldNames) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(obj);
			} catch (NoSuchFieldException e) {
				// Try next field name
			} catch (IllegalAccessException e) {
				// Try next field name
			}
		}

		return null;
	}
}
