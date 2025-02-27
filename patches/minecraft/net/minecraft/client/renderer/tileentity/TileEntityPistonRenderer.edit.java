
# Eagler Context Redacted Diff
# Copyright (c) 2025 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 6  @  2

+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.VertexFormat;
+ import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;

> DELETE  7  @  7 : 8

> DELETE  2  @  2 : 3

> DELETE  1  @  1 : 2

> CHANGE  28 : 30  @  28 : 29

~ 			worldrenderer.begin(7, DeferredStateManager.isDeferredRenderer() ? VertexFormat.BLOCK_SHADERS
~ 					: DefaultVertexFormats.BLOCK);

> INSERT  38 : 40  @  38

+ 			GlStateManager.enableCull();
+ 			GlStateManager.disableBlend();

> EOF
