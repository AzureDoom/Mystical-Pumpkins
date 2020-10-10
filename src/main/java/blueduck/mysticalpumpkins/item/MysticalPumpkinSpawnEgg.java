package blueduck.mysticalpumpkins.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

public class MysticalPumpkinSpawnEgg extends SpawnEggItem {

    public static final ArrayList<MysticalPumpkinSpawnEgg> SPAWN_EGGS = new ArrayList<>();

    public int primColor;
    public int secColor;

    Supplier<? extends EntityType<?>> type;
    public MysticalPumpkinSpawnEgg(Supplier<? extends EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn, Item.Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
        type = typeIn;
        primColor = primaryColorIn;
        secColor = secondaryColorIn;
        SPAWN_EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return type.get();
    }


    public static void SetupStuff() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                entitytype.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };
        for (final SpawnEggItem egg : SPAWN_EGGS) {
            EGGS.put(egg.getType(null), egg);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primColor : this.secColor;
    }

}
