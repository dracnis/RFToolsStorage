package mcjty.rftoolsstorage.items;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class StorageModuleItemWrapper implements ICapabilityProvider {

    private final LazyOptional<IItemHandler> holder = LazyOptional.of(() -> createHandler());

    private final UUID uuid;
    private ItemStackHandler handler;

    public StorageModuleItemWrapper(UUID uuid) {
        this.uuid = uuid;
    }

    private ItemStackHandler createHandler() {
        if (handler == null) {
            handler = new ItemStackHandler(100);
        }
        return handler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return holder.cast();
    }
}
