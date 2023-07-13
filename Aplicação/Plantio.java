package banco1;

public class Plantio {
    public Planta planta;
    public int IDplantio;
    public int Quantidade;
    public String DataDePlantio;
    
	public Plantio(Planta planta, int IDplantio, int quantidade, String data) {
		this.planta = planta;
		this.IDplantio = IDplantio;
		this.Quantidade = quantidade;
	}
	
	public Plantio(Planta planta, int quantidade, String data) {
		this.planta = planta;
		this.Quantidade = quantidade;
		this.DataDePlantio = data;
	}
	
	public Plantio() {
	
	}

	protected Planta getPlanta() {
		return this.planta;
	}

	protected void setPlanta(Planta planta) {
		this.planta = planta;
	}

	protected int getIDplantio() {
		return this.IDplantio;
	}

	protected void setIDplantio(int iDplantio) {
		this.IDplantio = iDplantio;
	}

	protected int getQuantidade() {
		return this.Quantidade;
	}

	protected void setQuantidade(int quantidade) {
		this.Quantidade = quantidade;
	}

	protected String getDataDePlantio() {
		return this.DataDePlantio;
	}

	protected void setDataDePlantio(String data) {
		this.DataDePlantio = data;
	}

}
