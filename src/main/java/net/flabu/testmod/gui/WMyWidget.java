package net.flabu.testmod.gui;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class WMyWidget extends WWidget {

    private static final Identifier TEXTURE = new Identifier("libgui", "textures/widget/dark_widgets.png");

    public WMyWidget(){
        this.height = 16;
        this.width = 20;
    }

    @Override
    public boolean canResize() {
        return false;
    }

    @Override
    public void paint(DrawContext context, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.texturedRect(context, x, y, width, height, TEXTURE, 0x00000000);
    }

}
