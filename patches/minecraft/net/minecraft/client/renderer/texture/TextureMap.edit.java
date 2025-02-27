
# Eagler Context Redacted Diff
# Copyright (c) 2025 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 5

> INSERT  1 : 2  @  1

+ import java.util.Collection;

> DELETE  1  @  1 : 2

> INSERT  4 : 20  @  4

+ 
+ import com.carrotsearch.hppc.IntIndexedContainer;
+ import com.carrotsearch.hppc.cursors.IntCursor;
+ import com.google.common.collect.Lists;
+ import com.google.common.collect.Maps;
+ 
+ import net.lax1dude.eaglercraft.v1_8.HString;
+ import net.lax1dude.eaglercraft.v1_8.internal.IFramebufferGL;
+ import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
+ import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
+ import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
+ import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.texture.EaglerTextureAtlasSpritePBR;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.texture.PBRTextureMapUtils;

> DELETE  2  @  2 : 8

> CHANGE  9 : 13  @  9 : 11

~ import net.optifine.BetterGrass;
~ import net.optifine.ConnectedTextures;
~ import net.optifine.CustomItems;
~ import net.optifine.util.CounterInt;

> INSERT  1 : 3  @  1

+ import static net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL.*;
+ 

> CHANGE  4 : 7  @  4 : 7

~ 	private final List<EaglerTextureAtlasSprite> listAnimatedSprites;
~ 	private final Map<String, EaglerTextureAtlasSprite> mapRegisteredSprites;
~ 	private final Map<String, EaglerTextureAtlasSprite> mapUploadedSprites;

> CHANGE  3 : 12  @  3 : 4

~ 	private final EaglerTextureAtlasSprite missingImage;
~ 	private final EaglerTextureAtlasSpritePBR missingImagePBR;
~ 	private int width;
~ 	private int height;
~ 	private boolean isEaglerPBRMode = false;
~ 	public int eaglerPBRMaterialTexture = -1;
~ 	private boolean hasAllocatedEaglerPBRMaterialTexture = false;
~ 	private boolean isGLES2 = false;
~ 	private CounterInt counterIndexInMap;

> INSERT  1 : 7  @  1

+ 	public static final int _GL_FRAMEBUFFER = 0x8D40;
+ 	public static final int _GL_COLOR_ATTACHMENT0 = 0x8CE0;
+ 
+ 	private IFramebufferGL[] copyColorFramebuffer = null;
+ 	private IFramebufferGL[] copyMaterialFramebuffer = null;
+ 

> CHANGE  8 : 10  @  8 : 9

~ 		this.missingImage = new EaglerTextureAtlasSprite("missingno");
~ 		this.missingImagePBR = new EaglerTextureAtlasSpritePBR("missingno");

> INSERT  2 : 4  @  2

+ 		this.isGLES2 = EaglercraftGPU.checkOpenGLESVersion() == 200;
+ 		this.counterIndexInMap = new CounterInt(0);

> INSERT  9 : 28  @  9

+ 		int idx = this.counterIndexInMap.nextValue();
+ 		this.missingImage.setIndexInMap(idx);
+ 		this.missingImagePBR.setIconWidth(16);
+ 		this.missingImagePBR.setIconHeight(16);
+ 		int[][][] aint2 = new int[3][this.mipmapLevels + 1][];
+ 		aint2[0][0] = aint;
+ 		int[] missingNormals = new int[256];
+ 		for (int i = 0; i < missingNormals.length; ++i) {
+ 			missingNormals[i] = 0xFF7F7F;
+ 		}
+ 		aint2[1][0] = missingNormals;
+ 		int[] missingMaterial = new int[256];
+ 		for (int i = 0; i < missingMaterial.length; ++i) {
+ 			missingMaterial[i] = 0x00000077;
+ 		}
+ 		aint2[2][0] = missingMaterial;
+ 		this.missingImagePBR.setFramesTextureDataPBR(new List[] { Lists.newArrayList(new int[][][] { aint2[0] }),
+ 				Lists.newArrayList(new int[][][] { aint2[1] }), Lists.newArrayList(new int[][][] { aint2[2] }) });
+ 		this.missingImagePBR.setIndexInMap(idx);

> DELETE  6  @  6 : 7

> INSERT  3 : 4  @  3

+ 		destroyAnimationCaches();

> INSERT  1 : 2  @  1

+ 		this.counterIndexInMap.reset();

> INSERT  6 : 26  @  6

+ 	public void deleteGlTexture() {
+ 		super.deleteGlTexture();
+ 		if (eaglerPBRMaterialTexture != -1) {
+ 			GlStateManager.deleteTexture(eaglerPBRMaterialTexture);
+ 			eaglerPBRMaterialTexture = -1;
+ 		}
+ 		if (copyColorFramebuffer != null) {
+ 			for (int i = 0; i < copyColorFramebuffer.length; ++i) {
+ 				_wglDeleteFramebuffer(copyColorFramebuffer[i]);
+ 			}
+ 			copyColorFramebuffer = null;
+ 		}
+ 		if (copyMaterialFramebuffer != null) {
+ 			for (int i = 0; i < copyMaterialFramebuffer.length; ++i) {
+ 				_wglDeleteFramebuffer(copyMaterialFramebuffer[i]);
+ 			}
+ 			copyMaterialFramebuffer = null;
+ 		}
+ 	}
+ 

> INSERT  1 : 4  @  1

+ 		ConnectedTextures.updateIcons(this);
+ 		CustomItems.updateIcons(this);
+ 		BetterGrass.updateIcons(this);

> INSERT  7 : 43  @  7

+ 		if (copyColorFramebuffer != null) {
+ 			for (int l = 0; l < copyColorFramebuffer.length; ++l) {
+ 				_wglDeleteFramebuffer(copyColorFramebuffer[l]);
+ 			}
+ 			copyColorFramebuffer = null;
+ 		}
+ 
+ 		if (isEaglerPBRMode) {
+ 			if (eaglerPBRMaterialTexture == -1) {
+ 				eaglerPBRMaterialTexture = GlStateManager.generateTexture();
+ 				hasAllocatedEaglerPBRMaterialTexture = false;
+ 			}
+ 			if (copyMaterialFramebuffer == null) {
+ 				GlStateManager.bindTexture(eaglerPBRMaterialTexture);
+ 				copyMaterialFramebuffer = new IFramebufferGL[this.mipmapLevels + 1];
+ 				for (int l = 0; l < copyMaterialFramebuffer.length; ++l) {
+ 					copyMaterialFramebuffer[l] = _wglCreateFramebuffer();
+ 					_wglBindFramebuffer(_GL_FRAMEBUFFER, copyMaterialFramebuffer[l]);
+ 					_wglFramebufferTexture2D(_GL_FRAMEBUFFER, _GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D,
+ 							EaglercraftGPU.getNativeTexture(eaglerPBRMaterialTexture), l);
+ 				}
+ 				_wglBindFramebuffer(_GL_FRAMEBUFFER, null);
+ 			}
+ 		} else {
+ 			if (eaglerPBRMaterialTexture != -1) {
+ 				GlStateManager.deleteTexture(eaglerPBRMaterialTexture);
+ 				eaglerPBRMaterialTexture = -1;
+ 			}
+ 			if (copyMaterialFramebuffer != null) {
+ 				for (int l = 0; l < copyMaterialFramebuffer.length; ++l) {
+ 					_wglDeleteFramebuffer(copyMaterialFramebuffer[l]);
+ 				}
+ 				copyMaterialFramebuffer = null;
+ 			}
+ 		}
+ 

> CHANGE  1 : 3  @  1 : 2

~ 			EaglerTextureAtlasSprite textureatlassprite = (EaglerTextureAtlasSprite) entry.getValue();
~ 			textureatlassprite.updateIndexInMap(this.counterIndexInMap);

> INSERT  3 : 106  @  3

+ 			if (isEaglerPBRMode) {
+ 				try {
+ 					IResource iresource = resourceManager.getResource(resourcelocation1);
+ 					ImageData[] abufferedimageColor = new ImageData[1 + this.mipmapLevels];
+ 					ImageData[] abufferedimageNormal = new ImageData[1 + this.mipmapLevels];
+ 					ImageData[] abufferedimageMaterial = new ImageData[1 + this.mipmapLevels];
+ 					abufferedimageColor[0] = TextureUtil.readBufferedImage(iresource.getInputStream());
+ 					abufferedimageNormal[0] = PBRTextureMapUtils.locateCompanionTexture(resourceManager, iresource,
+ 							"_n");
+ 					abufferedimageMaterial[0] = PBRTextureMapUtils.locateCompanionTexture(resourceManager, iresource,
+ 							"_s");
+ 					boolean dontAnimateNormals = false;
+ 					boolean dontAnimateMaterial = false;
+ 					if (abufferedimageNormal[0] == null) {
+ 						abufferedimageNormal[0] = PBRTextureMapUtils.defaultNormalsTexture;
+ 						dontAnimateNormals = true;
+ 					}
+ 					if (abufferedimageMaterial[0] == null) {
+ 						abufferedimageMaterial[0] = PBRTextureMapUtils.generateMaterialTextureFor(
+ 								textureatlassprite.getIconName(), textureatlassprite.optifineBaseTextureName);
+ 						dontAnimateMaterial = true;
+ 					}
+ 					PBRTextureMapUtils.unifySizes(0, abufferedimageColor, abufferedimageNormal, abufferedimageMaterial);
+ 
+ 					TextureMetadataSection texturemetadatasection = (TextureMetadataSection) iresource
+ 							.getMetadata("texture");
+ 					if (texturemetadatasection != null) {
+ 						IntIndexedContainer list = texturemetadatasection.getListMipmaps();
+ 						if (!list.isEmpty()) {
+ 							int l = abufferedimageColor[0].width;
+ 							int i1 = abufferedimageColor[0].height;
+ 							if (MathHelper.roundUpToPowerOfTwo(l) != l || MathHelper.roundUpToPowerOfTwo(i1) != i1) {
+ 								throw new RuntimeException(
+ 										"Unable to load extra miplevels, source-texture is not power of two");
+ 							}
+ 						}
+ 						for (IntCursor cur : list) {
+ 							int i2 = cur.value;
+ 							if (i2 > 0 && i2 < abufferedimageColor.length - 1 && abufferedimageColor[i2] == null) {
+ 								ResourceLocation resourcelocation2 = this.completeResourceLocation(resourcelocation,
+ 										i2);
+ 
+ 								try {
+ 									IResource mipLevelResource = resourceManager.getResource(resourcelocation2);
+ 									abufferedimageColor[i2] = TextureUtil
+ 											.readBufferedImage(mipLevelResource.getInputStream());
+ 									abufferedimageNormal[i2] = PBRTextureMapUtils
+ 											.locateCompanionTexture(resourceManager, mipLevelResource, "_n");
+ 									abufferedimageMaterial[i2] = PBRTextureMapUtils
+ 											.locateCompanionTexture(resourceManager, mipLevelResource, "_s");
+ 									if (abufferedimageNormal[i2] == null) {
+ 										abufferedimageNormal[i2] = PBRTextureMapUtils.defaultNormalsTexture;
+ 									}
+ 									if (abufferedimageMaterial[i2] == null) {
+ 										abufferedimageMaterial[i2] = PBRTextureMapUtils.generateMaterialTextureFor(
+ 												textureatlassprite.getIconName(),
+ 												textureatlassprite.optifineBaseTextureName);
+ 									}
+ 									PBRTextureMapUtils.unifySizes(i2, abufferedimageColor, abufferedimageNormal,
+ 											abufferedimageMaterial);
+ 									if ((abufferedimageColor[0].width >> i2) != abufferedimageColor[i2].width) {
+ 										throw new IOException("Mipmap level " + i2 + " is the wrong size, should be "
+ 												+ (abufferedimageColor[0].width >> i2) + " pixels");
+ 									}
+ 								} catch (Throwable exc) {
+ 									logger.error("Unable to load miplevel {} from: {}", i2, resourcelocation2);
+ 									logger.error(exc);
+ 								}
+ 							}
+ 						}
+ 					}
+ 
+ 					AnimationMetadataSection animationmetadatasection = (AnimationMetadataSection) iresource
+ 							.getMetadata("animation");
+ 					textureatlassprite.loadSpritePBR(
+ 							new ImageData[][] { abufferedimageColor, abufferedimageNormal, abufferedimageMaterial },
+ 							animationmetadatasection, dontAnimateNormals, dontAnimateMaterial);
+ 				} catch (RuntimeException runtimeexception) {
+ 					logger.error("Unable to parse metadata from " + resourcelocation1);
+ 					logger.error(runtimeexception);
+ 					continue;
+ 				} catch (IOException ioexception1) {
+ 					logger.error("Using missing texture, unable to load " + resourcelocation1);
+ 					logger.error(ioexception1);
+ 					continue;
+ 				}
+ 
+ 				j = Math.min(j, Math.min(textureatlassprite.getIconWidth(), textureatlassprite.getIconHeight()));
+ 				int l1 = Math.min(Integer.lowestOneBit(textureatlassprite.getIconWidth()),
+ 						Integer.lowestOneBit(textureatlassprite.getIconHeight()));
+ 				if (l1 < k) {
+ 					logger.warn("Texture {} with size {}x{} limits mip level from {} to {}",
+ 							new Object[] { resourcelocation1, Integer.valueOf(textureatlassprite.getIconWidth()),
+ 									Integer.valueOf(textureatlassprite.getIconHeight()),
+ 									Integer.valueOf(MathHelper.calculateLogBaseTwo(k)),
+ 									Integer.valueOf(MathHelper.calculateLogBaseTwo(l1)) });
+ 					k = l1;
+ 				}
+ 
+ 				stitcher.addSprite(textureatlassprite);
+ 				continue;
+ 			}
+ 

> CHANGE  2 : 3  @  2 : 3

~ 				ImageData[] abufferedimage = new ImageData[1 + this.mipmapLevels];

> CHANGE  4 : 5  @  4 : 5

~ 					IntIndexedContainer list = texturemetadatasection.getListMipmaps();

> CHANGE  1 : 3  @  1 : 3

~ 						int l = abufferedimage[0].width;
~ 						int i1 = abufferedimage[0].height;

> CHANGE  6 : 8  @  6 : 10

~ 					for (IntCursor cur : list) {
~ 						int i2 = cur.value;

> CHANGE  8 : 10  @  8 : 9

~ 										new Object[] { Integer.valueOf(i2), resourcelocation2 });
~ 								logger.error(ioexception);

> CHANGE  9 : 11  @  9 : 10

~ 				logger.error("Unable to parse metadata from " + resourcelocation1);
~ 				logger.error(runtimeexception);

> CHANGE  2 : 4  @  2 : 3

~ 				logger.error("Using missing texture, unable to load " + resourcelocation1);
~ 				logger.error(ioexception1);

> CHANGE  26 : 27  @  26 : 27

~ 		for (final EaglerTextureAtlasSprite textureatlassprite1 : this.mapRegisteredSprites.values()) {

> CHANGE  25 : 32  @  25 : 27

~ 		if (isEaglerPBRMode) {
~ 			this.missingImagePBR.generateMipmaps(this.mipmapLevels);
~ 			stitcher.addSprite(this.missingImagePBR);
~ 		} else {
~ 			this.missingImage.generateMipmaps(this.mipmapLevels);
~ 			stitcher.addSprite(this.missingImage);
~ 		}

> INSERT  9 : 10  @  9

+ 		regenerateIfNotAllocated();

> INSERT  2 : 24  @  2

+ 
+ 		if (isEaglerPBRMode) {
+ 			if (hasAllocatedEaglerPBRMaterialTexture) {
+ 				EaglercraftGPU.regenerateTexture(eaglerPBRMaterialTexture);
+ 			}
+ 			hasAllocatedEaglerPBRMaterialTexture = true;
+ 			TextureUtil.allocateTextureImpl(eaglerPBRMaterialTexture, this.mipmapLevels, stitcher.getCurrentWidth(),
+ 					stitcher.getCurrentHeight() * 2);
+ 		}
+ 
+ 		TextureUtil.bindTexture(this.glTextureId);
+ 
+ 		copyColorFramebuffer = new IFramebufferGL[this.mipmapLevels + 1];
+ 		for (int l = 0; l < copyColorFramebuffer.length; ++l) {
+ 			copyColorFramebuffer[l] = _wglCreateFramebuffer();
+ 			_wglBindFramebuffer(_GL_FRAMEBUFFER, copyColorFramebuffer[l]);
+ 			_wglFramebufferTexture2D(_GL_FRAMEBUFFER, _GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D,
+ 					EaglercraftGPU.getNativeTexture(this.glTextureId), l);
+ 		}
+ 
+ 		_wglBindFramebuffer(_GL_FRAMEBUFFER, null);
+ 

> CHANGE  2 : 8  @  2 : 3

~ 		width = stitcher.getCurrentWidth();
~ 		height = stitcher.getCurrentHeight();
~ 
~ 		List<EaglerTextureAtlasSprite> spriteList = stitcher.getStichSlots();
~ 		for (int l = 0, m = spriteList.size(); l < m; ++l) {
~ 			EaglerTextureAtlasSprite textureatlassprite2 = spriteList.get(l);

> INSERT  5 : 6  @  5

+ 				TextureUtil.bindTexture(this.glTextureId);

> INSERT  3 : 13  @  3

+ 				if (isEaglerPBRMode) {
+ 					TextureUtil.bindTexture(eaglerPBRMaterialTexture);
+ 					int[][][] pixels = ((EaglerTextureAtlasSpritePBR) textureatlassprite2).getFramePBRTextureData(0);
+ 					TextureUtil.uploadTextureMipmap(pixels[1], textureatlassprite2.getIconWidth(),
+ 							textureatlassprite2.getIconHeight(), textureatlassprite2.getOriginX(),
+ 							textureatlassprite2.getOriginY(), false, false);
+ 					TextureUtil.uploadTextureMipmap(pixels[2], textureatlassprite2.getIconWidth(),
+ 							textureatlassprite2.getIconHeight(), textureatlassprite2.getOriginX(),
+ 							textureatlassprite2.getOriginY() + height, false, false);
+ 				}

> CHANGE  13 : 14  @  13 : 14

~ 		for (EaglerTextureAtlasSprite textureatlassprite3 : (Collection<EaglerTextureAtlasSprite>) hashmap.values()) {

> INSERT  3 : 4  @  3

+ 		_wglBindFramebuffer(_GL_FRAMEBUFFER, null);

> CHANGE  3 : 12  @  3 : 8

~ 		return isAbsoluteLocation(location)
~ 				? new ResourceLocation(location.getResourceDomain(), location.getResourcePath() + ".png")
~ 				: (parInt1 == 0
~ 						? new ResourceLocation(location.getResourceDomain(),
~ 								HString.format("%s/%s%s",
~ 										new Object[] { this.basePath, location.getResourcePath(), ".png" }))
~ 						: new ResourceLocation(location.getResourceDomain(),
~ 								HString.format("%s/mipmaps/%s.%d%s", new Object[] { this.basePath,
~ 										location.getResourcePath(), Integer.valueOf(parInt1), ".png" })));

> CHANGE  2 : 14  @  2 : 4

~ 	private boolean isAbsoluteLocation(ResourceLocation p_isAbsoluteLocation_1_) {
~ 		String s = p_isAbsoluteLocation_1_.getResourcePath();
~ 		return this.isAbsoluteLocationPath(s);
~ 	}
~ 
~ 	private boolean isAbsoluteLocationPath(String p_isAbsoluteLocationPath_1_) {
~ 		String s = p_isAbsoluteLocationPath_1_.toLowerCase();
~ 		return s.startsWith("mcpatcher/") || s.startsWith("optifine/");
~ 	}
~ 
~ 	public EaglerTextureAtlasSprite getAtlasSprite(String iconName) {
~ 		EaglerTextureAtlasSprite textureatlassprite = (EaglerTextureAtlasSprite) this.mapUploadedSprites.get(iconName);

> CHANGE  1 : 2  @  1 : 2

~ 			textureatlassprite = isEaglerPBRMode ? missingImagePBR : missingImage;

> CHANGE  6 : 28  @  6 : 7

~ 		if (isEaglerPBRMode) {
~ 			for (int j = 0, l = this.listAnimatedSprites.size(); j < l; ++j) {
~ 				this.listAnimatedSprites.get(j).updateAnimationPBR();
~ 			}
~ 			for (int i = 0; i < copyColorFramebuffer.length; ++i) {
~ 				int w = width >> i;
~ 				int h = height >> i;
~ 				_wglBindFramebuffer(_GL_FRAMEBUFFER, copyColorFramebuffer[i]);
~ 				GlStateManager.viewport(0, 0, w, h);
~ 				for (int j = 0, l = this.listAnimatedSprites.size(); j < l; ++j) {
~ 					this.listAnimatedSprites.get(j).copyAnimationFramePBR(0, w, h, i);
~ 				}
~ 				_wglBindFramebuffer(_GL_FRAMEBUFFER, copyMaterialFramebuffer[i]);
~ 				h <<= 1;
~ 				GlStateManager.viewport(0, 0, w, h);
~ 				for (int j = 0, l = this.listAnimatedSprites.size(); j < l; ++j) {
~ 					this.listAnimatedSprites.get(j).copyAnimationFramePBR(1, w, h, i);
~ 				}
~ 			}
~ 			_wglBindFramebuffer(_GL_FRAMEBUFFER, null);
~ 			return;
~ 		}

> CHANGE  1 : 3  @  1 : 3

~ 		for (int j = 0, l = this.listAnimatedSprites.size(); j < l; ++j) {
~ 			this.listAnimatedSprites.get(j).updateAnimation();

> INSERT  2 : 13  @  2

+ 		for (int i = 0; i < copyColorFramebuffer.length; ++i) {
+ 			int w = width >> i;
+ 			int h = height >> i;
+ 			_wglBindFramebuffer(_GL_FRAMEBUFFER, copyColorFramebuffer[i]);
+ 			GlStateManager.viewport(0, 0, w, h);
+ 			for (int j = 0, l = this.listAnimatedSprites.size(); j < l; ++j) {
+ 				this.listAnimatedSprites.get(j).copyAnimationFrame(w, h, i);
+ 			}
+ 		}
+ 
+ 		_wglBindFramebuffer(_GL_FRAMEBUFFER, null);

> CHANGE  2 : 13  @  2 : 3

~ 	private void destroyAnimationCaches() {
~ 		for (int i = 0, l = this.listAnimatedSprites.size(); i < l; ++i) {
~ 			this.listAnimatedSprites.get(i).clearFramesTextureData();
~ 		}
~ 	}
~ 
~ 	public EaglerTextureAtlasSprite registerSprite(ResourceLocation location) {
~ 		return registerSprite(location, null);
~ 	}
~ 
~ 	public EaglerTextureAtlasSprite registerSprite(ResourceLocation location, String locationOptifineBase) {

> CHANGE  3 : 5  @  3 : 4

~ 			EaglerTextureAtlasSprite textureatlassprite = (EaglerTextureAtlasSprite) this.mapRegisteredSprites
~ 					.get(location.toString());

> CHANGE  1 : 7  @  1 : 2

~ 				if (isEaglerPBRMode) {
~ 					textureatlassprite = EaglerTextureAtlasSpritePBR.makeAtlasSprite(location);
~ 				} else {
~ 					textureatlassprite = EaglerTextureAtlasSprite.makeAtlasSprite(location);
~ 				}
~ 				textureatlassprite.optifineBaseTextureName = locationOptifineBase;

> INSERT  1 : 2  @  1

+ 				textureatlassprite.updateIndexInMap(this.counterIndexInMap);

> CHANGE  11 : 17  @  11 : 12

~ 		if (!isGLES2) {
~ 			this.mipmapLevels = mipmapLevelsIn;
~ 		} else {
~ 			this.mipmapLevels = 0; // Due to limitations in OpenGL ES 2.0 texture completeness, its easier to just
~ 									// make this zero
~ 		}

> CHANGE  2 : 4  @  2 : 4

~ 	public EaglerTextureAtlasSprite getMissingSprite() {
~ 		return isEaglerPBRMode ? missingImagePBR : missingImage;

> INSERT  1 : 36  @  1

+ 
+ 	public int getWidth() {
+ 		return width;
+ 	}
+ 
+ 	public int getHeight() {
+ 		return height;
+ 	}
+ 
+ 	public void setEnablePBREagler(boolean enable) {
+ 		isEaglerPBRMode = enable;
+ 	}
+ 
+ 	public void setBlurMipmapDirect0(boolean parFlag, boolean parFlag2) {
+ 		if (isGLES2) {
+ 			super.setBlurMipmapDirect0(parFlag, false);
+ 		} else {
+ 			super.setBlurMipmapDirect0(parFlag, parFlag2);
+ 			if (isEaglerPBRMode && eaglerPBRMaterialTexture != -1) {
+ 				GlStateManager.setActiveTexture(33986);
+ 				GlStateManager.bindTexture(eaglerPBRMaterialTexture);
+ 				super.setBlurMipmapDirect0(parFlag, parFlag2);
+ 				GlStateManager.setActiveTexture(33984);
+ 			}
+ 		}
+ 	}
+ 
+ 	public EaglerTextureAtlasSprite getSpriteSafe(String iconName) {
+ 		ResourceLocation resourcelocation = new ResourceLocation(iconName);
+ 		return this.mapRegisteredSprites.get(resourcelocation.toString());
+ 	}
+ 
+ 	public int getCountRegisteredSprites() {
+ 		return this.counterIndexInMap.getValue();
+ 	}

> EOF
