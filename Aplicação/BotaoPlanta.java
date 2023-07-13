package banco1;

import javax.swing.JButton;

public class BotaoPlanta extends JButton{
	private static final long serialVersionUID = 1L;
	private Planta planta;
	
	public BotaoPlanta(Planta planta) {
		this.planta = planta;
	}
	
	protected Planta getPlanta() {
		return planta;
	}
}
