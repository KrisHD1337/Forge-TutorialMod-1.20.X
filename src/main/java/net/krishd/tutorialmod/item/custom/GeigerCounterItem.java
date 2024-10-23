package net.krishd.tutorialmod.item.custom;

import net.krishd.tutorialmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class GeigerCounterItem extends Item {
    public GeigerCounterItem(Properties properties) {
        super(properties);
    }

    private double findNearestRadioactiveBlock(Level level, Vec3 playerPos) {
        double nearestDistance = Double.MAX_VALUE;
        int searchRadius = 20;

        BlockPos playerBlockPos = new BlockPos((int) playerPos.x(), (int) playerPos.y(), (int) playerPos.z());

        for (BlockPos pos : BlockPos.betweenClosed(playerBlockPos.offset(-searchRadius, -searchRadius, -searchRadius),
                                                    playerBlockPos.offset(searchRadius, searchRadius, searchRadius))) {
            Block block = level.getBlockState(pos).getBlock();

            // Check if the block is in the radioactive_blocks tag
            if (block.defaultBlockState().is(ModTags.Blocks.RADIOACTIVE_BLOCKS)) {
                Vec3 blockPos = new Vec3(pos.getX(), pos.getY(), pos.getZ());
                double distance = playerPos.distanceTo(blockPos);

                // Log for debugging purposes
                System.out.println("Radioactive block found at " + pos + " with distance " + distance);

                if (distance < nearestDistance) {
                    nearestDistance = distance;
                }
            }
        }

        return nearestDistance == Double.MAX_VALUE ? -1 : nearestDistance;
    }

    public float getTexture(Level level, Vec3 playerPos) {
        double distance = findNearestRadioactiveBlock(level, playerPos);

        // Log the distance to see if it is calculating correctly
        System.out.println("Distance to nearest radioactive block: " + distance);

        if (distance == -1) {
            return 0.0f; // No radioactive block found
        }

        if (distance < 5.0) {
            return 0.75f; // Closest texture (lvl4)
        } else if (distance < 10.0) {
            return 0.5f; // Medium texture (lvl3)
        } else if (distance < 15.0) {
            return 0.25f; // Far texture (lvl2)
        } else {
            return 0.0f; // Default texture (lvl1)
        }
    }
}
