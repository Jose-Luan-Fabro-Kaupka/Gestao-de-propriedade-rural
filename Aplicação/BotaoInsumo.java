package banco1;

import javax.swing.JButton;

public class BotaoInsumo extends JButton{
	private static final long serialVersionUID = 1L;
	private Insumo insumo;

	public BotaoInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	
	protected Insumo getInsumo() {
		return insumo;
	}
}
