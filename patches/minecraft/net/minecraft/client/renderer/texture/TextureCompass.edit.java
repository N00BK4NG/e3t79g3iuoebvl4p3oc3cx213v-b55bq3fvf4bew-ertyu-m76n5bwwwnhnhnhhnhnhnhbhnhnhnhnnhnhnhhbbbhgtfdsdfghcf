
# Eagler Context Redacted Diff
# Copyright (c) 2025 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 3  @  2

+ import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;

> DELETE  1  @  1 : 3

> CHANGE  4 : 5  @  4 : 5

~ public class TextureCompass extends EaglerTextureAtlasSprite {

> CHANGE  62 : 69  @  62 : 64

~ 				currentAnimUpdater = (mapWidth, mapHeight, mapLevel) -> {
~ 					animationCache.copyFrameToTex2D(this.frameCounter, mapLevel, this.originX >> mapLevel,
~ 							this.originY >> mapLevel, this.width >> mapLevel, this.height >> mapLevel, mapWidth,
~ 							mapHeight);
~ 				};
~ 			} else {
~ 				currentAnimUpdater = null;

> INSERT  2 : 4  @  2

+ 		} else {
+ 			currentAnimUpdater = null;

> INSERT  2 : 3  @  2

+ 

> EOF
