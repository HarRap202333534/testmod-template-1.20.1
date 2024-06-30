package net.flabu.testmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.text.Text;
import net.minecraft.util.JsonHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CustomRecipe {
    static JsonObject readJson(String fileName) throws IOException {

        /*ObjectMapper mapper = new ObjectMapper();
        InputStream is = Item.class.getResourceAsStream("/test2.json");
        Object o = mapper.readValue(is, Item.class  );*/

        String path = "../src/main/resources/data/testmod/recipes/";

        Object o = new JsonParser().parse(new FileReader(path+fileName));


        JsonObject j = (JsonObject) o;


        return j;
    }

    static void decrementStack(int quantity, Item item, Inventory inv){
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

    static void incrementStack(int quantity, Item item, Inventory inv){
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

    static void craft(int outputQuantity, JsonObject json){
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
            if(NbIngOwn.size() > 0 && ingOwn.size() > 0 && NbIngOwn.size() == NbIngNeed.size()){
                if(NbIngOwn.get(j) >= NbIngNeed.get(j)){
                    canCraft = true;
                }
                else{
                    canCraft = false;
                    break;
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
