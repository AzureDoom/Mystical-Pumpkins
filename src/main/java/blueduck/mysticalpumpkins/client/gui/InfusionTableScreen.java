package blueduck.mysticalpumpkins.client.gui;

import blueduck.mysticalpumpkins.container.InfusionTableContainer;
import blueduck.mysticalpumpkins.utils.SpecialConstants;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class InfusionTableScreen extends ContainerScreen<InfusionTableContainer> {

	public InfusionTableScreen(InfusionTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void func_230430_a_(MatrixStack stack, int mouseX, int mouseY, float p_230430_4_) {
		this.func_230446_a_(stack);
		super.func_230430_a_(stack, mouseX, mouseY, p_230430_4_);
		this.func_230459_a_(stack, mouseX, mouseY);
	}

	@Override
	public void func_230450_a_(MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_230706_i_.getTextureManager().bindTexture(SpecialConstants.PUMPKIN_INFUSER_TEX);

		this.func_238474_b_(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		int infusingScale = this.container.infusingScaled();
		this.func_238474_b_(
				matrixStack,
				this.guiLeft + 71,  // x of starting replacing point (up)
				this.guiTop + 30,   // y of starting replacing point (left)
				176,                // x of thing that replaces (up)
				0,                  // y of thing that replaces (left)
				infusingScale,
				17
		);
	}
}