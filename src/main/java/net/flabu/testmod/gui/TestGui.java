package net.flabu.testmod.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import mod.azure.azurelib.util.JsonUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.impl.lib.gson.JsonReader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Environment(EnvType.CLIENT)
public class TestGui extends Screen {

    public TestGui() {
        super(Text.literal("Test"));
    }

    public static Identifier BACKGROUND = new Identifier("testmod", "textures/gui/demo_background.png");

    public ButtonWidget button1;
    public ButtonWidget button2;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                    System.out.println("You clicked button1!");
                    try {
                        craft(1, readJson("test_recipe.json"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .dimensions((this.width - 240) / 2, (this.height - 156) / 2, 104, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                    System.out.println("You clicked button2!");
                })
                .dimensions((this.width + 32) / 2, (this.height - 156) / 2, 104, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }

    public JsonObject readJson(String fileName) throws IOException {

        /*ObjectMapper mapper = new ObjectMapper();
        InputStream is = Item.class.getResourceAsStream("/test2.json");
        Object o = mapper.readValue(is, Item.class  );*/

        Object o = new JsonParser().parse(new FileReader("C:\\Users\\lolra\\OneDrive\\Bureau\\testmod-template-1.20.1\\src\\main\\resources\\data\\testmod\\recipes\\test_recipe.json"));


        JsonObject j = (JsonObject) o;


        return j;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, 1602211792, 1602211792);
        context.drawItem(MinecraftClient.getInstance().player.getMainHandStack(), mouseX, mouseY);
        this.renderBackground(context);
        context.drawTexture(BACKGROUND, (this.width - 248) / 2, (this.height - 164) / 2, 0, 0, 248, 164);
        context.getMatrices().push();
        context.getMatrices().scale(2.0f, 2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2 / 2, 30, 0xFFFFFF);
        context.getMatrices().pop();
        super.render(context, mouseX, mouseY, delta);
    }

    public void decrementStack(int quantity, Item item, Inventory inv){
        for(int j = 0; j < inv.size(); j++){
            if(inv.getStack(j).getItem() == item){
                for(int i = 0; i < quantity; i++){
                    if(inv.getStack(j).getCount() > 0){
                        inv.getStack(j).decrement(1);
                    }
                    if(inv.getStack(j).getCount() == 0){
                        inv.setStack(j, new ItemStack(Items.AIR));
                    }
                }
            }
        }
    }

    public void incrementStack(int quantity, Item item, Inventory inv){
        for(int i = 0; i < inv.size(); i++){
            if(inv.getStack(i).getItem() == item && inv.getStack(i).getCount() < inv.getStack(i).getMaxCount()){
                inv.getStack(i).increment(1);
                break;
            }
            else if(inv.getStack(i).isEmpty()){
                inv.setStack(i, new ItemStack(item));
                break;
            }
        }
    }

    public void craft(int outputQuantity, JsonObject json){
        Inventory inv = MinecraftClient.getInstance().player.getInventory();
        List<Item> ingOwn = new ArrayList<>();
        List<Integer> NbIngOwn = new ArrayList<>();
        boolean canCraft = false;

        ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
        JsonArray ing = JsonHelper.getArray(json, "ingredients");
        JsonArray quantity = JsonHelper.getArray(json, "quantity");
        List<Item> ingNeed = new ArrayList<>();
        List<Integer> NbIngNeed = new ArrayList<>();

        for(int i = 0; i < quantity.size(); i++){
            NbIngNeed.add(quantity.get(i).getAsInt());
        }

        for(int i = 0; i <  ing.size(); i++){
            ingNeed.add(ShapedRecipe.getItem(ing.get(i).getAsJsonObject()));
        }

        for(int j = 0; j < ingNeed.size(); j++){
            for (int p = 0; p < inv.size(); p++){
                if(inv.getStack(p).getItem() == ingNeed.get(j)){
                    int m = inv.getStack(p).getCount();
                    if(!ingOwn.contains(ingNeed.get(j))){
                        ingOwn.add(inv.getStack(p).getItem());
                    }
                    if(NbIngOwn.size() == ingOwn.size()){
                        NbIngOwn.set(ingOwn.size() - 1, m + NbIngOwn.get(ingOwn.size() - 1));
                    }
                    else{
                        NbIngOwn.add(ingOwn.size() - 1, m);
                    }
                }
            }
        }

        for(int j = 0; j < ingNeed.size(); j++){
            if(NbIngOwn.size() > 0 && ingOwn.size() > 0){
                if(NbIngOwn.get(j) >= NbIngNeed.get(j)){
                    canCraft = true;
                }
                else{
                    canCraft = false;
                }
            }
        }
        if(canCraft){
            incrementStack(outputQuantity, output.getItem(), inv);
            for(int i = 0; i < ingNeed.size(); i++){
                decrementStack(NbIngNeed.get(i), ingNeed.get(i), inv);
            }
        }
        else{
            MinecraftClient.getInstance().player.sendMessage(Text.literal("Vous n'avez pas les ingrédients nécessaires."));
        }
    }
}
