package net.flabu.testmod.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.fabricmc.fabric.api.util.TriState;
import net.flabu.testmod.item.CustomModItems;
import net.flabu.testmod.item.ModItems;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.net.URL;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestGui2 extends LightweightGuiDescription {
    public TestGui2(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(288, 216);

        WLabel label = new WLabel(Text.literal("Crafting"));
        root.add(label, 7, 1);


        class Craft extends WPlainPanel{
            WButton button;

            public Craft() {
                button = new WButton();
                button.setIcon(new ItemIcon(new ItemStack(ModItems.TEST_RES)));
                this.add(button, 18+4, 2, 20, 16);
                this.setSize(20, 16);
            }
        }


        ArrayList<ItemStack> data = new ArrayList<>();
        data.add(new ItemStack(ModItems.TEST_RES));
        data.add(new ItemStack(ModItems.CUSTOM_TEST));
        data.add(new ItemStack(ModItems.CUSTOM_TEST2));

        BiConsumer<ItemStack, Craft> configurator = (ItemStack stack, Craft craft) -> {
            craft.button.setIcon(new ItemIcon(stack));
        };



        WListPanel<ItemStack, Craft> list = new WListPanel<>(data, Craft::new, configurator);
        list.setListItemHeight(16);
        root.add(list,0, 2, 7, 6);

        WButton button = new WButton(Text.literal("Liste des crafts"));
        button.setOnClick(() -> {
            boolean bl = false;
            if(bl == false){
                bl = true;

            }

        });

        root.add(button, 7, 3, 3, 6);




        /*WGridPanel grid = new WGridPanel();
        WScrollBar scroll = new WScrollBar(Axis.VERTICAL);
        grid.add(scroll, 4, 1, 1, grid.getHeight());
        ArrayList<Craft> buttons = new ArrayList<>();
        buttons.add(new Craft());

        root.add(grid,1, 1, 7, 6);*/
    }

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }
}
