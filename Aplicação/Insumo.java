package banco1;

public class Insumo {
	public int IDinsumo;
	public String TipoDeInsumo;
	public String NomeDoInsumo;
	
	public Insumo(int IDinsumo, String TipoDeInsumo, String NomeDoInsumo) {
		this.IDinsumo = IDinsumo;
		this.TipoDeInsumo = TipoDeInsumo;
		this.NomeDoInsumo = NomeDoInsumo;
	}
	
	public Insumo(String NomeDoInsumo, String TipoDeInsumo) {
		this.TipoDeInsumo = TipoDeInsumo;
		this.NomeDoInsumo = NomeDoInsumo;
	}

	protected int getIDinsumo() {
		return IDinsumo;
	}

	protected String getTipoDeInsumo() {
		return TipoDeInsumo;
	}

	protected String getNomeDoInsumo() {
		return NomeDoInsumo;
	}

}
