package net.krishd.tutorialmod.block.custom;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.List;

public class NuclearWasteBlock extends Block {
    public NuclearWasteBlock(Properties properties) {
        super(properties);
    }

    // Called when the block is placed or loaded in the world
    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) { // Ensure this runs only on the server-side
            ((ServerLevel) world).scheduleTick(pos, this, 20); // Schedule the first tick after 1 second (20 ticks)
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        System.out.println("Tick method called at position: " + pos); // Debug statement to show the position of the block

        // Get all players within a 20-block radius
        List<Player> players = world.getEntitiesOfClass(Player.class, new AABB(pos).inflate(20));

        // Apply effects to each player based on their proximity
        for (Player player : players) {
            applyEffects(world, pos, player);
        }

        // Reschedule the next tick
        world.scheduleTick(pos, this, 20); // Reschedule to tick every second (20 game ticks)
    }

    private void applyEffects(ServerLevel world, BlockPos pos, Player player) {
        // Calculate the squared distance between the player and the block
        double distanceSqr = player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());

        // Apply different levels of Poison effect based on proximity
        if (distanceSqr < 4) { // Very close to the block
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0)); // Wither for 5 seconds, level 1
        } else if (distanceSqr < 16) { // A bit farther
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1)); // Poison for 5 seconds, level 2
        } else if (distanceSqr < 100) { // Within 10 blocks
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0)); // Poison for 5 seconds, level 1
        }
    }
}
