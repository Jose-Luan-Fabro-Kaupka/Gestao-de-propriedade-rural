package banco1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Classe usada para estabelecer conexao com um banco de dados.
 * Cada parte que pode gerar uma excecao exibe um JOptionPane com a excecao, o objetivo é
 * que seja exibido para o usuário final, para que ele possa verificar se nao fez algo de errado e corrigir.
 */

public class BancoDeDados {
	private Connection connection = null;
	private Statement statement = null;
	private int res;
	private ResultSet resul;
	
	public BancoDeDados(String url, String usuario, String senha) {
		
		try {
			this.connection = DriverManager.getConnection(url, usuario, senha);
			this.statement = this.connection.createStatement();
		}catch(Exception e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, "Algum dos dados informado está incorreto!", "Erro", res);
		}
	}
	
	
	public boolean EstaConectado() {
		if(this.connection != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	protected void InserePlanta(Planta planta) {
		try {
			String str = "INSERT INTO Plantas(NomePlanta, Tipo, Producao, Origem) VALUES(" + "'" + planta.getNomePlanta() + "'" + ", " + "'" + planta.getTipo() + "'"+ ", " + "'" + planta.getProducao() + "'" + ", " + "'" + planta.getOrigem() + "'" + ");";
			
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void InserePlantio(Plantio plantio) {
		try {
			String str = "INSERT INTO Plantio(IDplanta, Quantidade, DataDePlantio) VALUES(" + plantio.planta.getIDplanta() + ", " + plantio.getQuantidade() + ", " + plantio.getDataDePlantio() + ");";
			
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void InsereInsumo(Insumo insumo) {
		try {
			String str = "INSERT INTO Insumos(NomeDoInsumo, TipoDeInsumo) VALUES(" + "'" + insumo.getNomeDoInsumo() + "'" + ", " + "'" + insumo.getTipoDeInsumo() + "'" + ");";
			
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void InsereAplicacao(Aplicacao aplicacao) {
		try {
			String str = "INSERT INTO AplicacaoDeInsumos(IDinsumo, IDplantio, Quantidade, DataDeAplicacao) VALUES(" + aplicacao.getInsumo().getIDinsumo() + ", "+ aplicacao.getPlantio().getIDplantio() + ", " + aplicacao.getQuantidade() + ", " + aplicacao.getDataDeAplicacao() +");";
			
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected ArrayList<Planta> PesquisarPlanta(String nome){
		String str = "SELECT * FROM Plantas WHERE NomePlanta LIKE '%" + nome + "%';";
		ArrayList<Planta> ret = new ArrayList<Planta>();
		
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(this.resul != null && this.resul.next()) {
				Planta planta = new Planta(this.resul.getInt("IDplanta"), resul.getString("NomePlanta"), resul.getString("Tipo"), resul.getString("Producao"), resul.getString("Origem"));
				ret.add(planta);
			}
			
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		return (ArrayList<Planta>) ret;
	}
	
	
	protected ArrayList<Plantio> PesquisarPlantios(String data1, String data2) {
		
		String str;
		
		if (data1 != null && data2 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio BETWEEN CAST(" + data1 + "AS DATE)" + "AND CAST(" + data2 + " AS DATE) ORDER BY DataDePlantio;";
		} else if (data2 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio <= " + data2 + " ORDER BY DataDePlantio;";
		}  else if (data1 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio >= " + data1 + " ORDER BY DataDePlantio;";
		} else {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta ORDER BY DataDePlantio;";
		}
		
		ArrayList<Plantio> ret = new ArrayList<Plantio>();
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(resul != null && resul.next()) {
				int idpto = resul.getInt("IDplantio");
				int idpta = resul.getInt("IDplanta");
				int qtd = resul.getInt("Quantidade");
				String data = resul.getString("DataDePlantio");
				String nome = resul.getString("NomePlanta");
				String tipo = resul.getString("Tipo");
				String producao = resul.getString("Producao");
				String origem = resul.getString("Origem");
				
				Planta planta = new Planta(idpta, nome, tipo, producao, origem);				
				Plantio plantio = new Plantio(planta, idpto, qtd, data);
				ret.add(plantio);
			}
			return (ArrayList<Plantio>) ret;
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e, "Erro", res);
		}
		return (ArrayList<Plantio>) ret;
	}
	
	
	protected ArrayList<Insumo> PesquisarInsumo(String nome){
		String str = "SELECT * FROM Insumos WHERE NomeDoInsumo LIKE '%" + nome + "%';";
		ArrayList<Insumo> ret = new ArrayList<Insumo>();
		
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(this.resul != null && this.resul.next()) {
				Insumo insumo = new Insumo(this.resul.getInt("IDinsumo"), resul.getString("NomeDoInsumo"), resul.getString("TipoDeInsumo"));
				ret.add(insumo);
			}
			
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		return (ArrayList<Insumo>) ret;
	}
	
	
	protected ArrayList<String> PesquisarAplicacoes(String data1, String data2) {
		
		String str;
		
		
		if (data1 != null && data2 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDeAplicacao BETWEEN CAST(" + data1 + "AS DATE)" + "AND CAST(" + data2 + " AS DATE) ORDER BY DataDeAplicacao;";
		} else if (data2 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDeAplicacao <= " + data2 + " ORDER BY DataDePlantio;";
		}  else if (data1 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDeAplicacao >= " + data1 + " ORDER BY DataDePlantio;";
		} else {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta ORDER BY DataDeAplicacao;";
		}
		
		List<String> ret = new ArrayList<String>();
		
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(resul.next()) {
				int idap = resul.getInt("IDaplicacao");
				int idpto = resul.getInt("IDplantio");
				int qtd = resul.getInt("Quantidade");
				String data = resul.getString("DataDeAplicacao");
				int idin = resul.getInt("IDinsumo");
				String nomein = resul.getString("NomeDoInsumo");
				String tipoin = resul.getString("TipoDeInsumo");
				int idpta = resul.getInt("IDplanta");
				String nome = resul.getString("NomePlanta");
				
				ret.add(idap + " " + idin + " " + nomein + " " + tipoin + " " + idpto + " " + qtd + " " +  idpta + " " + nome + " " + data + "\n");
			}	
			return (ArrayList<String>) ret;
			
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e, "Erro", res);
		}
		return (ArrayList<String>) ret;
	}
	
	
	protected void TruncatePlantas() {
		String str = "TRUNCATE TABLE Plantas;";
		try {
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void TruncateInsumos() {
		String str = "TRUNCATE TABLE Insumos;";
		try {
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void TruncateAplicacoes() {
		String str = "TRUNCATE TABLE AplicacaoDeInsumos;";
		try {
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void TruncatePlantio() {
		String str = "TRUNCATE TABLE Plantio;";
		try {
			this.res = this.statement.executeUpdate(str);
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	private String leitorCaminho() {
		String retorno = null; 
		try {
	            Process process = Runtime.getRuntime().exec("whoami");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            retorno = reader.readLine();
	            reader.close();
	            
	        } catch (IOException e) {
	        	JOptionPane erro = new JOptionPane("Erro");
				erro.setName("Erro");
				JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", 1);
	        }
		return retorno;
	}
	
	
	protected void SalvarJsonPlantios(String data1, String data2) {
		/*
		 * Serve para salvar a tabela dos plantios em um arquivo .json.
		 * Esse arquivo será usado para gerar um relatório dos plantios em pdf.
		 */
		
		String str;
		
		JSONObject jobject = new JSONObject();
		
		if (data1 != null && data2 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio BETWEEN CAST(" + data1 + "AS DATE)" + "AND CAST(" + data2 + " AS DATE) ORDER BY DataDePlantio;";
		} else if (data2 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio <= " + data2 + " ORDER BY DataDePlantio;";
		}  else if (data1 != null) {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta WHERE DataDePlantio >= " + data1 + " ORDER BY DataDePlantio;";
		} else {
			str = "SELECT * FROM Plantio INNER JOIN Plantas ON Plantio.IDplanta = Plantas.IDplanta ORDER BY DataDePlantio;";
		}
		
		List<Integer> qtds = new ArrayList<Integer>();
		
		List<String> datas = new ArrayList<String>();
		List<String> nomes = new ArrayList<String>();
		List<String> tipos = new ArrayList<String>();
		List<String> producoes = new ArrayList<String>();
		List<String> origens = new ArrayList<String>();
		
		int qtd;
		String data;
		String nome;
		String tipo;
		String producao;
		String origem;
		
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(resul.next()) {
				
				qtd = resul.getInt("Quantidade");
				qtds.add(qtd);
				
				data = resul.getString("DataDePlantio");
				datas.add(data);
				
				nome = resul.getString("NomePlanta");
				nomes.add(nome);
				
				tipo = resul.getString("Tipo");
				tipos.add(tipo);
				
				producao = resul.getString("Producao");
				producoes.add(producao);
				
				origem = resul.getString("Origem");
				origens.add(origem);
			}
			
			JSONArray jaqtd = new JSONArray();
			for (int i = 0; i < qtds.size(); i++) {
				jaqtd.put(qtds.get(i));
			}
			
			JSONArray jadata = new JSONArray();
			for (int i = 0; i < datas.size(); i++) {
				jadata.put(datas.get(i));
			}
			
			JSONArray janome = new JSONArray();
			for (int i = 0; i < nomes.size(); i++) {
				janome.put(nomes.get(i));
			}
			
			JSONArray jatipo = new JSONArray();
			for (int i = 0; i < tipos.size(); i++) {
				jatipo.put(tipos.get(i));
			}
			
			JSONArray japroducao = new JSONArray();
			for (int i = 0; i < producoes.size(); i++) {
				japroducao.put(producoes.get(i));
			}
			
			JSONArray jaorigem = new JSONArray();
			for (int i = 0; i < origens.size(); i++) {
				jaorigem.put(origens.get(i));
			}
			
			jobject.put("Quantidade", jaqtd);
			jobject.put("Data De Plantio", jadata);
			jobject.put("Nome Da Espécie/Variedade", janome);
			jobject.put("Tipo", jatipo);
			jobject.put("Producao", japroducao);
			jobject.put("Origem", jaorigem);
			
			
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		try (FileWriter file = new FileWriter("/home/" + leitorCaminho() + "/Sistema/Plantios.json")){
			file.write(jobject.toString());
			
		} catch (IOException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		try {
			ProcessBuilder processBuil = new ProcessBuilder("python", "/home/" + leitorCaminho() + "/PycharmProjects/pythonProject2/GeradorDeRelatoriosPlantios.py");
			Process process = processBuil.start();
		/*
		 * Processo para chamar o script em python para criar um dataframe com os dados salvos no json
		 * criado anteriormente, e exportar em PDF. 	
		 */
			
		} catch (Exception e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
	
	
	protected void SalvarJsonAplicacoes(String data1, String data2) {
		/*
		 * Serve para salvar a tabela das aplicacoes de insumos em um arquivo .json.
		 * Esse arquivo será usado para gerar um relatório dos aplicacoes de insumos em pdf.
		 */
		
		JSONObject jobject = new JSONObject();
		
		String str;
		
		if (data1 != null && data2 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDePlantio BETWEEN CAST(" + data1 + "AS DATE)" + "AND CAST(" + data2 + " AS DATE) ORDER BY DataDeAplicacao;";
		} else if (data2 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDePlantio <= " + data2 + " ORDER BY DataDePlantio;";
		}  else if (data1 != null) {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta WHERE DataDePlantio >= " + data1 + " ORDER BY DataDePlantio;";
		} else {
			str = "SELECT * FROM AplicacaoDeInsumos INNER JOIN Insumos ON Insumos.IDinsumo = AplicacaoDeInsumos.IDinsumo INNER JOIN Plantio ON Plantio.IDplantio = AplicacaoDeInsumos.IDplantio INNER JOIN Plantas ON Plantas.IDplanta = Plantio.IDplanta ORDER BY DataDeAplicacao;";
		}
		
		List<Integer> qtds = new ArrayList<Integer>();
		
		List<String> nomes = new ArrayList<String>();
		List<String> datas = new ArrayList<String>();
		List<String> tipos = new ArrayList<String>();
		List<String> nomeplantas = new ArrayList<String>();
		List<String> datasdeplantio = new ArrayList<String>();
		
		try {
			this.resul = this.statement.executeQuery(str);
			
			while(resul.next()) {
				qtds.add(resul.getInt("Quantidade"));
				
				nomes.add(resul.getString("NomeDoInsumo"));
				
				tipos.add(resul.getString("TipoDeInsumo"));
				
				datas.add(resul.getString("DataDeAplicacao"));
				
				nomeplantas.add(resul.getString("NomePlanta"));
				
				datasdeplantio.add(resul.getString("DataDePlantio"));
				
			}
			
			JSONArray jaqtd = new JSONArray();
			for (int i = 0; i < qtds.size(); i++) {
				jaqtd.put(qtds.get(i));
			}
			
			JSONArray janome = new JSONArray();
			for (int i = 0; i < nomes.size(); i++) {
				janome.put(nomes.get(i));
			}
			
			JSONArray jatipo = new JSONArray();
			for (int i = 0; i < tipos.size(); i++) {
				jatipo.put(tipos.get(i));
			}
			
			JSONArray jadata = new JSONArray();
			for (int i = 0; i < datas.size(); i++) {
				jadata.put(datas.get(i));
			}
			
			JSONArray janomeplanta = new JSONArray();
			for (int i = 0; i < nomeplantas.size(); i++) {
				janomeplanta.put(nomeplantas.get(i));
			}
			
			JSONArray jadatadeplantio = new JSONArray();
			for (int i = 0; i < datasdeplantio.size(); i++) {
				jadatadeplantio.put(datasdeplantio.get(i));
			}
			
			jobject.put("Quantidade", jaqtd);
			jobject.put("Nome Do Insumo", janome);
			jobject.put("Tipo De Insumo", jatipo);
			jobject.put("Data De Aplicacao", jadata);
			jobject.put("Nome Da Espécie/Variedade", janomeplanta);
			jobject.put("Data De Plantio", jadatadeplantio);

		
		} catch (SQLException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		try (FileWriter file = new FileWriter("/home/" + leitorCaminho() + "/Sistema/AplicacaoDeInsumos.json")){
			file.write(jobject.toString());
			
		} catch (IOException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
		
		
		try {
			ProcessBuilder processBuil = new ProcessBuilder("python", "/home/" + leitorCaminho() + "/PycharmProjects/pythonProject2/GeradorDeRelatoriosAplicacoes.py");
			Process process = processBuil.start();
			
		/*
		 * Processo para chamar o script em python para criar um dataframe com os dados salvos no json
		 * criado anteriormente, e exportar em PDF. 	
		 */
			
		} catch (Exception e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, e.getMessage(), "Erro", res);
		}
	}
}
