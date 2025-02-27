/*
 * Copyright (c) 2022-2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package net.lax1dude.eaglercraft.v1_8.plugin.gateway_velocity.shit;

import net.lax1dude.eaglercraft.v1_8.plugin.gateway_velocity.EaglerXVelocityVersion;

public class CompatWarning {

	public static void displayCompatWarning() {
		String stfu = System.getProperty("eaglerxvelocity.stfu");
		if("true".equalsIgnoreCase(stfu)) {
			return;
		}
		String[] compatWarnings = new String[] {
				":>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>",
				":>  ",
				":>      EAGLERCRAFTXVELOCITY WARNING:",
				":>  ",
				":>  This plugin wasn\'t tested to be \'working\'",
				":>  with ANY version of Velocity (and forks)",
				":>  apart from the versions listed below:",
				":>  ",
				":>  - Velocity: " + EaglerXVelocityVersion.NATIVE_VELOCITY_BUILD,
				":>  - " + EaglerXVelocityVersion.NATIVE_VELOCITY_BUILD_DL,
				":>  ",
				":>  This is not a Bukkit/Spigot plugin!",
				":>  ",
				":>  Use \"-Deaglerxvelocity.stfu=true\" to hide",
				":>  ",
				":>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>:>"
		};
		for(int i = 0; i < compatWarnings.length; ++i) {
			System.err.println(compatWarnings[i]);
		}
	}

}