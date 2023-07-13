package banco1;

import javax.swing.JButton;

public class BotaoPlantio extends JButton{
	private static final long serialVersionUID = 1L;
	private Plantio plantio;

	public BotaoPlantio(Plantio plantio) {
		this.plantio = plantio;
	}
	
	protected Plantio getPlantio() {
		return plantio;
	}
}
