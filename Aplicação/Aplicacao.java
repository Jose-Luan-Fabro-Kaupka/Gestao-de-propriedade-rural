package banco1;

public class Aplicacao {
	public int IDaplicacao;
	public int Quantidade;
	public Plantio plantio;
	public Insumo insumo;
	public String DataDeAplicacao;

	public Aplicacao(int IDaplicacao, Insumo insumo, Plantio plantio, int Quantidade, String data) {
		this.IDaplicacao = IDaplicacao;
		this.insumo = insumo;
		this.plantio = plantio;
		this.Quantidade = Quantidade;
		this.DataDeAplicacao = data;
	}

	public Aplicacao() {
		
	}

	protected int getIDaplicacao() {
		return this.IDaplicacao;
	}

	protected void setIDaplicacao(int iDaplicacao) {
		this.IDaplicacao = iDaplicacao;
	}

	protected Plantio getPlantio() {
		return this.plantio;
	}

	protected void setPlantio(Plantio plantio) {
		this.plantio = plantio;
	}

	protected int getQuantidade() {
		return this.Quantidade;
	}

	protected void setQuantidade(int quantidade) {
		this.Quantidade = quantidade;
	}

	protected Insumo getInsumo() {
		return this.insumo;
	}

	protected void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	protected String getDataDeAplicacao() {
		return this.DataDeAplicacao;
	}

	protected void setDataDeAplicacao(String data) {
		this.DataDeAplicacao = data;
	}


}
