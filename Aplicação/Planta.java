package banco1;

public class Planta{
	public int IDplanta;
	public String NomePlanta;
	public String Tipo;
	public String Producao;
	public String Origem;
    
	public Planta(int IDplanta, String NomePlanta, String tipo, String producao, String origem) {
		this.IDplanta = IDplanta;
		this.NomePlanta = NomePlanta;
		this.Tipo = tipo;
		this.Producao = producao;
		this.Origem = origem;
	}
	
	public Planta(String NomePlanta, String tipo, String producao, String origem) {
		this.NomePlanta = NomePlanta;
		this.Tipo = tipo;
		this.Producao = producao;
		this.Origem = origem;
	}

	protected int getIDplanta() {
		return IDplanta;
	}

	protected String getNomePlanta() {
		return NomePlanta;
	}

	protected String getTipo() {
		return Tipo;
	}

	protected String getProducao() {
		return Producao;
	}

	protected String getOrigem() {
		return Origem;
	}

}
