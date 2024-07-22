package net.flabu.testmod.mixin;

import net.flabu.testmod.Testmod;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.URL;

@Mixin(PlayerEntity.class)
public class ExampleMixin {
	String url = "https://cdn.discordapp.com/attachments/733774120252080181/1263417486624424010/Test.png?ex=669a28b9&is=6698d739&hm=b8bbe86b1a95807ccf71accafa2ab0777134f5c307ddf0fbe558e8657426d0b1&";


	@Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
	public void writeCustomDataNbt(NbtCompound nbt, CallbackInfo ci){
		nbt.putString("url", url);
		Testmod.LOGGER.info("DOONENNEN");
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker")
	protected void initDataTracker(CallbackInfo ci){
		
		DataTracker tracker = ((PlayerEntity)(Object)this).getDataTracker();
		((PlayerEntity)(Object)this).getDataTracker().startTracking(getData(), "https://cdn.discordapp.com/attachments/733774120252080181/1263417486624424010/Test.png?ex=669a28b9&is=6698d739&hm=b8bbe86b1a95807ccf71accafa2ab0777134f5c307ddf0fbe558e8657426d0b1&");
	}
	
	public TrackedData<String> getData(){
		return DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);
	}

}