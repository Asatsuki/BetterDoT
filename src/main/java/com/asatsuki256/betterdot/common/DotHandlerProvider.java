package com.asatsuki256.betterdot.common;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DotHandlerProvider implements ICapabilitySerializable<CompoundTag> {

    private final DotHandler dotHandler = new DotHandler();
    private final LazyOptional<DotHandler> dotHandlerOptional = LazyOptional.of(() -> dotHandler);

    public void invalidate() {
        dotHandlerOptional.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return dotHandlerOptional.cast();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        CompoundTag mapNbt = new CompoundTag();
        for (String key : dotHandler.iTicks.keySet()) {
            mapNbt.putInt(key, dotHandler.iTicks.get(key));
        }

        nbt.put("i_ticks", mapNbt);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag mapNbt = nbt.getCompound("i_ticks");
        dotHandler.iTicks.clear();
        for (String key : nbt.getAllKeys()) {
            dotHandler.iTicks.put(key, mapNbt.getInt(key));
        }
    }
}
