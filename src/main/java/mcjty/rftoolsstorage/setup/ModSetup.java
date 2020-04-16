package mcjty.rftoolsstorage.setup;

import mcjty.lib.compat.MainCompatHandler;
import mcjty.lib.setup.DefaultModSetup;
import mcjty.rftoolsstorage.modules.craftingmanager.system.CraftingDeviceRegistry;
import mcjty.rftoolsstorage.modules.modularstorage.ModularStorageSetup;
import mcjty.rftoolsstorage.storage.ClientStorageHolder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup extends DefaultModSetup {

    public boolean xnet = false;

    public ClientStorageHolder clientStorageHolder = new ClientStorageHolder();
    public CraftingDeviceRegistry craftingDeviceRegistry = new CraftingDeviceRegistry();

    public ModSetup() {
        createTab("rftoolsstorage", () -> new ItemStack(ModularStorageSetup.STORAGE_MODULE0.get()));
    }

    @Override
    public void init(FMLCommonSetupEvent e) {
        super.init(e);
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        CommandHandler.registerCommands();
        RFToolsStorageMessages.registerMessages("rftoolsstorage");
        craftingDeviceRegistry.init();
    }

    public void initClient(FMLClientSetupEvent e) {
        ClientCommandHandler.registerCommands();
    }

    @Override
    protected void setupModCompat() {
        MainCompatHandler.registerWaila();
        MainCompatHandler.registerTOP();
        xnet = ModList.get().isLoaded("xnet");
    }
}
