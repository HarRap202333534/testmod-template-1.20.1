package net.flabu.testmod.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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
}
