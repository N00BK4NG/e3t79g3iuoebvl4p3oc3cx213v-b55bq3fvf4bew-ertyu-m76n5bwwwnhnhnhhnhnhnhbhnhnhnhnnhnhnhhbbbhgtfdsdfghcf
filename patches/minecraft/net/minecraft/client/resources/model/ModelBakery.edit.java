
# Eagler Context Redacted Diff
# Copyright (c) 2025 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 8

> CHANGE  4 : 5  @  4 : 7

~ import java.nio.charset.StandardCharsets;

> DELETE  7  @  7 : 8

> INSERT  1 : 18  @  1

+ import java.util.Set;
+ 
+ import com.google.common.base.Charsets;
+ import com.google.common.base.Joiner;
+ import com.google.common.collect.Lists;
+ import com.google.common.collect.Maps;
+ import com.google.common.collect.Sets;
+ 
+ import net.lax1dude.eaglercraft.v1_8.IOUtils;
+ import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
+ import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
+ import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.BlockVertexIDs;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.VertexMarkerState;
+ import net.minecraft.block.Block;
+ import net.minecraft.block.state.IBlockState;
+ import net.minecraft.client.Minecraft;

> INSERT  8 : 10  @  8

+ import net.minecraft.client.renderer.block.statemap.IStateMapper;
+ import net.minecraft.client.renderer.block.statemap.StateMapperBase;

> DELETE  1  @  1 : 2

> DELETE  3  @  3 : 9

> CHANGE  7 : 8  @  7 : 10

~ import net.optifine.CustomItems;

> CHANGE  20 : 21  @  20 : 21

~ 	private final Map<ResourceLocation, EaglerTextureAtlasSprite> sprites = Maps.newHashMap();

> INSERT  60 : 61  @  60

+ 					LOGGER.warn(var6);

> CHANGE  2 : 4  @  2 : 3

~ 				LOGGER.warn("Unable to load definition " + modelresourcelocation);
~ 				LOGGER.warn(exception);

> CHANGE  39 : 40  @  39 : 40

~ 			modelblockdefinition = new ModelBlockDefinition((ArrayList<ModelBlockDefinition>) arraylist);

> CHANGE  22 : 24  @  22 : 23

~ 								+ modelresourcelocation + "\'");
~ 						LOGGER.warn(exception);

> CHANGE  18 : 19  @  18 : 19

~ 			String str;

> CHANGE  2 : 4  @  2 : 4

~ 				str = (String) BUILT_IN_MODELS.get(s1);
~ 				if (str == null) {

> DELETE  2  @  2 : 4

> CHANGE  2 : 5  @  2 : 3

~ 				try (InputStream is = iresource.getInputStream()) {
~ 					str = IOUtils.inputStreamToString(is, StandardCharsets.UTF_8);
~ 				}

> CHANGE  2 : 4  @  2 : 10

~ 			ModelBlock modelblock = ModelBlock.deserialize(str);
~ 			modelblock.name = parResourceLocation.toString();

> CHANGE  1 : 2  @  1 : 2

~ 			return modelblock;

> INSERT  4 : 5  @  4

+ 		String path = parResourceLocation.getResourcePath();

> CHANGE  1 : 2  @  1 : 2

~ 				((path.startsWith("mcpatcher/") || path.startsWith("optifine/")) ? "" : "models/") + path + ".json");

> CHANGE  15 : 17  @  15 : 16

~ 								+ Item.itemRegistry.getNameForObject(item) + "\'");
~ 						LOGGER.warn(exception);

> INSERT  7 : 23  @  7

+ 	public void loadItemModel(String p_loadItemModel_1_, ResourceLocation p_loadItemModel_2_,
+ 			ResourceLocation p_loadItemModel_3_) {
+ 		this.itemLocations.put(p_loadItemModel_1_, p_loadItemModel_2_);
+ 
+ 		if (this.models.get(p_loadItemModel_2_) == null) {
+ 			try {
+ 				ModelBlock modelblock = this.loadModel(p_loadItemModel_2_);
+ 				this.models.put(p_loadItemModel_2_, modelblock);
+ 			} catch (Exception exception) {
+ 				LOGGER.warn("Unable to load item model: \'{}\' for item: \'{}\'",
+ 						new Object[] { p_loadItemModel_2_, p_loadItemModel_3_ });
+ 				LOGGER.warn(exception.getClass().getName() + ": " + exception.getMessage());
+ 			}
+ 		}
+ 	}
+ 

> INSERT  107 : 109  @  107

+ 		CustomItems.update();
+ 		CustomItems.loadModels(this);

> INSERT  18 : 19  @  18

+ 		boolean deferred = Minecraft.getMinecraft().gameSettings.shaders;

> INSERT  9 : 34  @  9

+ 					if (deferred) {
+ 						ModelBlock currentBlockModel = modelblock;
+ 						ResourceLocation currentResourceLocation = modelblockdefinition$variant.getModelLocation();
+ 						int blockId = -1;
+ 						do {
+ 							blockId = BlockVertexIDs.modelToID.getOrDefault(currentResourceLocation.toString(), -1);
+ 							if (blockId != -1) {
+ 								break;
+ 							}
+ 							currentResourceLocation = currentBlockModel.getParentLocation();
+ 							currentBlockModel = models.get(currentResourceLocation);
+ 						} while (currentBlockModel != null);
+ 						if (blockId != -1) {
+ 							VertexMarkerState.markId = blockId;
+ 							try {
+ 								weightedbakedmodel$builder.add(
+ 										this.bakeModel(modelblock, modelblockdefinition$variant.getRotation(),
+ 												modelblockdefinition$variant.isUvLocked()),
+ 										modelblockdefinition$variant.getWeight());
+ 							} finally {
+ 								VertexMarkerState.markId = 0;
+ 							}
+ 							continue;
+ 						}
+ 					}

> CHANGE  47 : 48  @  47 : 48

~ 		for (ModelResourceLocation modelresourcelocation : (List<ModelResourceLocation>) arraylist) {

> CHANGE  19 : 20  @  19 : 20

~ 		EaglerTextureAtlasSprite textureatlassprite = (EaglerTextureAtlasSprite) this.sprites

> CHANGE  7 : 8  @  7 : 8

~ 				EaglerTextureAtlasSprite textureatlassprite1 = (EaglerTextureAtlasSprite) this.sprites

> CHANGE  16 : 17  @  16 : 17

~ 			EaglerTextureAtlasSprite parTextureAtlasSprite, EnumFacing parEnumFacing, ModelRotation parModelRotation,

> CHANGE  17 : 18  @  17 : 18

~ 		List arraydeque = Lists.newLinkedList();

> CHANGE  11 : 12  @  11 : 12

~ 			ResourceLocation resourcelocation2 = (ResourceLocation) arraydeque.remove(0);

> CHANGE  14 : 16  @  14 : 15

~ 						+ "; unable to load model: \'" + resourcelocation2 + "\'");
~ 				LOGGER.warn(exception);

> CHANGE  50 : 52  @  50 : 52

~ 				for (ResourceLocation resourcelocation : (Set<ResourceLocation>) set) {
~ 					EaglerTextureAtlasSprite textureatlassprite = texturemap.registerSprite(resourcelocation);

> CHANGE  21 : 22  @  21 : 22

~ 							EaglerTextureAtlasSprite.setLocationNameCompass(resourcelocation2.toString());

> CHANGE  2 : 3  @  2 : 3

~ 							EaglerTextureAtlasSprite.setLocationNameClock(resourcelocation2.toString());

> CHANGE  52 : 53  @  52 : 53

~ 		for (EaglerTextureAtlasSprite textureatlassprite : this.sprites.values()) {

> INSERT  11 : 42  @  11

+ 	// eagler hack
+ 	public String getBaseTextureForBlockPre(int blockId, int metadata) {
+ 		Block block = Block.blockRegistry.getObjectById(blockId);
+ 		if (block != null) {
+ 			IBlockState state = block.getStateFromMeta(metadata);
+ 			if (state != null) {
+ 				IStateMapper mapper = blockModelShapes.getBlockStateMapper().blockStateMap.get(block);
+ 				ModelResourceLocation loc = null;
+ 				if (mapper != null) {
+ 					loc = mapper.putStateModelLocations(block).get(state);
+ 				}
+ 				if (loc == null) {
+ 					loc = new ModelResourceLocation(Block.blockRegistry.getNameForObject(block),
+ 							StateMapperBase.getPropertyString(state.getProperties()));
+ 				}
+ 				ModelBlockDefinition.Variants v = variants.get(loc);
+ 				if (v != null && v.getVariants().size() > 0) {
+ 					ModelBlockDefinition.Variant vv = v.getVariants().get(0);
+ 					ModelBlock model = this.models.get(vv.getModelLocation());
+ 					if (model != null) {
+ 						String name = model.resolveTextureName("particle");
+ 						if (name != null) {
+ 							return name;
+ 						}
+ 					}
+ 				}
+ 			}
+ 		}
+ 		return null;
+ 	}
+ 

> EOF
