package banco1;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private BancoDeDados banco = null;

	public TelaInicial(BancoDeDados banco) {
		super("Roilou 1.0");
		
		this.banco = banco;
		
		setLayout(null);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		JButton novap = new JButton("<html>Inserir Nova <br>Espécie/Variedade</html>");
		JButton novop = new JButton("Inserir Novo Plantio");
		JButton novoi = new JButton("Inserir Novo Insumo");
		JButton novaa = new JButton("<html>Inserir Nova<br>Aplicacao de Insumos</html>");
		JButton relatp = new JButton("Gerar Relatorio de Plantios");
		JButton relata = new JButton("<html>Gerar Relatorio de<br>Aplicacao de Insumos</html>");
		
		novap.setFont(fonte);
		novop.setFont(fonte);
		novoi.setFont(fonte);
		novaa.setFont(fonte);
		relatp.setFont(fonte);
		relata.setFont(fonte);
		
		novap.setBounds(22, 100, 290, 200);
		novop.setBounds(335, 100, 290, 200);
		novoi.setBounds(650, 100, 290, 200);
		novaa.setBounds(965, 100, 290, 200);
		relatp.setBounds(1280, 100, 290, 200);
		relata.setBounds(1600, 100, 290, 200);
		
		Icon ic = new ImageIcon("/home" + this.leitorCaminho() + "/Sistema/fundo.jpg");
		JLabel imagem = new JLabel(ic);
		imagem.setLocation(500, 500);
		add(imagem);
		
		novap.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaPlanta();
			}
		});
		
		novop.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovoPlantio();
			}
		});
		
		novoi.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovoInsumo();
			}
		});
		
		novaa.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaAplicacao();
			}
		});
		
		relatp.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorioPlantio();
			}
		});
		
		relata.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorioAplicacoes();
			}
		});
		
		
		
		add(novap);
		add(novop);
		add(novoi);
		add(novaa);
		add(relatp);
		add(relata);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
	
	private String ajustarData(String data) {
		String aux1 = data.substring(0, 3);
		String aux2 = data.substring(3, 5);
		String aux3 = data.substring(5);
		
		data = "'" + aux3.replace("/", "") + "/" + aux2 + "/" + aux1.replace("/", "") + "'";
		return data;
	}
	
	private void NovaPlanta() {
		
		JFrame frame = new JFrame("Nova Espécie/Variedade");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel nome = new JLabel("Nome: ");
		JLabel tipo = new JLabel("Tipo: ");
		JLabel prod = new JLabel("Produção: ");
		JLabel orig = new JLabel("Origem: ");
		
		nome.setBounds(50, 40, 100, 30);
		tipo.setBounds(50, 100, 100, 30);
		prod.setBounds(50, 160, 100, 30);
		orig.setBounds(50, 220, 100, 30);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		nome.setFont(fonte);
		tipo.setFont(fonte);
		prod.setFont(fonte);
		orig.setFont(fonte);
		
		JTextField nomet = new JTextField();
		JTextField tipot = new JTextField();
		JTextField prodt = new JTextField();
		JTextField origt = new JTextField();
		
		nomet.setFont(fonte);
		tipot.setFont(fonte);
		prodt.setFont(fonte);
		origt.setFont(fonte);
		
		nomet.setBounds(200, 45, 200, 20);
		tipot.setBounds(200, 105, 200, 20);
		prodt.setBounds(200, 165, 200, 20);
		origt.setBounds(200, 225, 200, 20);
		
		JButton ok = new JButton("OK");
		ok.setBounds(190, 280, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Planta planta = new Planta(nomet.getText(), tipot.getText(), prodt.getText(), origt.getText());
					banco.InserePlanta(planta);
					frame.setVisible(false);
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				
				}
			}
		});
		
		janela.add(nome);
		janela.add(tipo);
		janela.add(prod);
		janela.add(orig);
		janela.add(nomet);
		janela.add(tipot);
		janela.add(prodt);
		janela.add(origt);
		janela.add(ok);
		
		janela.setSize(500, 400);
		frame.add(janela);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void NovoPlantio() {
		Plantio plantio = new Plantio();
		
		JFrame frame = new JFrame("Novo Plantio");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel nome = new JLabel("<html>Nome da <br>Espécie/Variedade: </html>");
		JLabel qtd = new JLabel("Quantidade: ");
		JLabel datap = new JLabel("Data de Plantio: ");
		
		nome.setBounds(50, 40, 180, 60);
		qtd.setBounds(50, 115, 180, 20);
		datap.setBounds(50, 160, 180, 20);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		nome.setFont(fonte);
		qtd.setFont(fonte);
		datap.setFont(fonte);
		
		MaskFormatter data = null;
		
		try {
			
			data = new MaskFormatter("##/##/####");
		
		} catch (ParseException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, "Falha ao escrever a data", "Erro", 1);
		}
		
		JTextField nomet = new JTextField();
		JTextField qtdt = new JTextField();
		JFormattedTextField datat = new JFormattedTextField(data);
		
		nomet.setFont(fonte);
		qtdt.setFont(fonte);
		datat.setFont(fonte);
		Icon lupa = new ImageIcon("/home/" + leitorCaminho() + "/Sistema/lupa.png");
		JButton pesquisar = new JButton();
		pesquisar.setIcon(lupa);
		
		nomet.setBounds(250, 60, 200, 20);
		qtdt.setBounds(250, 115, 200, 20);
		datat.setBounds(250, 160, 100, 20);
		pesquisar.setBounds(455, 60, 19, 19);
		
		pesquisar.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Planta> ret = banco.PesquisarPlanta(nomet.getText());
				
				JFrame pesquisa = new JFrame("Selecione a Espécie/Variedade");
				pesquisa.setSize(600, 800);
				Container container = new Container();
				container.setSize(600, 800);
				JLabel descricao = new JLabel("Nome                      Tipo                      Produção                      Origem");
				descricao.setBounds(105, 0, 600, 20);
				ArrayList<BotaoPlanta> botoes = new ArrayList<BotaoPlanta>();
				
				for(int i = 0; i < ret.size(); i++) {
					BotaoPlanta botao = new BotaoPlanta(ret.get(i));
					botao.setText(ret.get(i).getNomePlanta() + "                      " + ret.get(i).getTipo() + "                      " + ret.get(i).getProducao() + "                      " + ret.get(i).getOrigem());
					botao.setBounds(0, 20 + 20 * i, 600, 20);
					botao.addActionListener((ActionListener) new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							plantio.setPlanta(botao.getPlanta());
							pesquisa.setVisible(false);
						}
					});
					botoes.add(botao);
				}
				
				for(int i = 0; i < botoes.size(); i++) {
					container.add(botoes.get(i));
				}
				container.add(descricao);
				pesquisa.add(container);
				pesquisa.setLocationRelativeTo(null);
				pesquisa.setVisible(true);
			}
		});
		
		
		JButton ok = new JButton("OK");
		ok.setBounds(200, 205, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					plantio.setQuantidade(Integer.parseInt(qtdt.getText()));
					plantio.setDataDePlantio(ajustarData(datat.getText()));
					banco.InserePlantio(plantio);
					frame.setVisible(false);
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				}
			}
		});
		
		janela.add(nome);
		janela.add(qtd);
		janela.add(datap);
		janela.add(nomet);
		janela.add(qtdt);
		janela.add(datat);
		janela.add(ok);
		janela.add(pesquisar);
		
		janela.setSize(500, 300);
		frame.add(janela);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void NovoInsumo() {
		JFrame frame = new JFrame("Novo Insumo");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel nome = new JLabel("Nome do Insumo: ");
		JLabel tipo = new JLabel("Tipo de Insumo: ");
		
		nome.setBounds(50, 20, 180, 60);
		tipo.setBounds(50, 100, 180, 25);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		nome.setFont(fonte);
		tipo.setFont(fonte);
		
		JTextField nomet = new JTextField();
		JTextField tipot = new JTextField();
		
		nomet.setFont(fonte);
		tipot.setFont(fonte);
		
		nomet.setBounds(250, 42, 200, 20);
		tipot.setBounds(250, 103, 200, 20);
		
		JButton ok = new JButton("OK");
		ok.setBounds(200, 160, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Insumo insumo = new Insumo(nomet.getText(), tipot.getText());
					banco.InsereInsumo(insumo);
					frame.setVisible(false);	
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				
				}
			}
		});
		
		janela.add(nome);
		janela.add(tipo);
		janela.add(nomet);
		janela.add(tipot);
		janela.add(ok);
		
		janela.setSize(500, 275);
		frame.add(janela);
		frame.setSize(500, 275);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void NovaAplicacao() {
		
		Aplicacao aplicacao = new Aplicacao();
		
		JFrame frame = new JFrame("Nova Aplicação de Insumos");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel nome = new JLabel("<html>Nome do<br>Insumo: </html>");
		JLabel qtd = new JLabel("Quantidade: ");
		JLabel datap = new JLabel("<html>Data de<br>Plantio: </html>");
		JLabel dataa = new JLabel("<html>Data de<br>Aplicação: </html>");
		
		nome.setBounds(50, 40, 100, 40);
		qtd.setBounds(50, 100, 120, 20);
		datap.setBounds(50, 160, 100, 40);
		dataa.setBounds(50, 220, 100, 50);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		nome.setFont(fonte);
		qtd.setFont(fonte);
		datap.setFont(fonte);
		dataa.setFont(fonte);
		
		MaskFormatter data = null;
		
		try {
			
			data = new MaskFormatter("##/##/####");
		
		} catch (ParseException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, "Falha ao escrever a data", "Erro", 1);
		}
		
		JTextField nomet = new JTextField();
		JTextField qtdt = new JTextField();
		JFormattedTextField datapit = new JFormattedTextField(data);
		JFormattedTextField datapft = new JFormattedTextField(data);
		JFormattedTextField dataat = new JFormattedTextField(data);
		
		nomet.setFont(fonte);
		qtdt.setFont(fonte);
		datapit.setFont(fonte);
		datapft.setFont(fonte);
		dataat.setFont(fonte);
		
		nomet.setBounds(200, 50, 205, 20);
		qtdt.setBounds(200, 102, 205, 20);
		datapit.setBounds(200, 170, 100, 20);
		datapft.setBounds(305, 170, 100, 20);
		dataat.setBounds(200, 235, 100, 20);
		
		Icon lupa = new ImageIcon("/home/" + leitorCaminho() + "/Sistema/lupa.png");
		JButton pesquisari = new JButton();
		pesquisari.setIcon(lupa);
		
		pesquisari.setBounds(410, 50, 19, 19);
		
		pesquisari.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Insumo> ret = banco.PesquisarInsumo(nomet.getText());
				
				JFrame pesquisa = new JFrame("Selecione o Insumo");
				pesquisa.setSize(600, 800);
				Container container = new Container();
				container.setSize(600, 800);
				JLabel descricao = new JLabel("Nome                      Tipo");
				descricao.setBounds(230, 0, 600, 20);
				ArrayList<BotaoInsumo> botoes = new ArrayList<BotaoInsumo>();
				
				for(int i = 0; i < ret.size(); i++) {
					BotaoInsumo botao = new BotaoInsumo(ret.get(i));
					botao.setText(ret.get(i).getNomeDoInsumo() + "                      " + ret.get(i).getTipoDeInsumo());
					botao.setBounds(0, 20 + 20 * i, 600, 20);
					botao.addActionListener((ActionListener) new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aplicacao.setInsumo(botao.getInsumo());
							pesquisa.setVisible(false);
						}
					});
					botoes.add(botao);
				}
				
				for(int i = 0; i < botoes.size(); i++) {
					container.add(botoes.get(i));
				}
				container.add(descricao);
				pesquisa.add(container);
				pesquisa.setLocationRelativeTo(null);
				pesquisa.setVisible(true);
			}
		});
		
		JButton pesquisarp = new JButton();
		pesquisarp.setIcon(lupa);
		
		pesquisarp.setBounds(410, 170, 19, 19);
		
		pesquisarp.addActionListener((ActionListener) new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String datai = ajustarData(datapit.getText());
				String dataf = ajustarData(datapft.getText());
				
				ArrayList<Plantio> ret = banco.PesquisarPlantios(datai, dataf);
				
				JFrame pesquisa = new JFrame("Selecione o Plantio");
				pesquisa.setSize(600, 800);
				Container container = new Container();
				container.setSize(600, 800);
				ArrayList<BotaoPlantio> botoes = new ArrayList<BotaoPlantio>();
				
				for(int i = 0; i < ret.size(); i++) {
					BotaoPlantio botao = new BotaoPlantio(ret.get(i));
					botao.setText(ret.get(i).getPlanta().getNomePlanta() + "                      " + ret.get(i).getQuantidade() + "                      " + ret.get(i).getDataDePlantio());
					botao.setBounds(0, 20 * i, 600, 20);
					botao.addActionListener((ActionListener) new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aplicacao.setPlantio(botao.getPlantio());
							pesquisa.setVisible(false);
						}
					});
					botoes.add(botao);
				}
				
				for(int i = 0; i < botoes.size(); i++) {
					container.add(botoes.get(i));
				}
				pesquisa.add(container);
				pesquisa.setLocationRelativeTo(null);
				pesquisa.setVisible(true);
			}
		});
		
		JButton ok = new JButton("OK");
		ok.setBounds(200, 300, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					aplicacao.setQuantidade(Integer.parseInt(qtdt.getText()));
					aplicacao.setDataDeAplicacao(ajustarData(dataat.getText()));
					banco.InsereAplicacao(aplicacao);;
					frame.setVisible(false);
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				}
			}
		});
		
		janela.add(nome);
		janela.add(qtd);
		janela.add(datap);
		janela.add(dataa);
		janela.add(nomet);
		janela.add(qtdt);
		janela.add(datapit);
		janela.add(datapft);
		janela.add(dataat);
		janela.add(ok);
		janela.add(pesquisari);
		janela.add(pesquisarp);
		
		janela.setSize(500, 400);
		frame.add(janela);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void GerarRelatorioPlantio() {
		JFrame frame = new JFrame("Gerar Relatório de Plantios");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel datai = new JLabel("Data Inicial: ");
		JLabel dataf = new JLabel("Data Final: ");
		
		datai.setBounds(50, 40, 180, 60);
		dataf.setBounds(50, 95, 180, 60);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		datai.setFont(fonte);
		dataf.setFont(fonte);
		
		MaskFormatter data = null;
		
		try {
			
			data = new MaskFormatter("##/##/####");
		
		} catch (ParseException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, "Falha ao escrever a data", "Erro", 1);
		}
		
		JFormattedTextField datait = new JFormattedTextField(data);
		JFormattedTextField dataft = new JFormattedTextField(data);
		
		datait.setFont(fonte);
		dataft.setFont(fonte);
		
		datait.setBounds(250, 60, 100, 20);
		dataft.setBounds(250, 115, 100, 20);
		
		JButton ok = new JButton("OK");
		ok.setBounds(150, 170, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String datain = datait.getText();
					String aux1 = datain.substring(0, 2);
					String aux2 = datain.substring(3, 5);
					String aux3 = datain.substring(6);
					
					datain = "'" + aux3 + "/" + aux2 + "/" + aux1 + "'";
					String datafin = dataft.getText();
					String aux4 = datafin.substring(0, 2);
					String aux5 = datafin.substring(3, 5);
					String aux6 = datafin.substring(6);
					
					datafin = "'" + aux6 + "/" + aux5 + "/" + aux4 + "'";
					banco.SalvarJsonPlantios(datain, datafin);
					frame.setVisible(false);
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				}
			}
		});
		
		janela.add(datai);
		janela.add(dataf);
		janela.add(datait);
		janela.add(dataft);
		janela.add(ok);
		
		janela.setSize(400, 280);
		frame.add(janela);
		frame.setSize(400, 280);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	private void GerarRelatorioAplicacoes() {
		JFrame frame = new JFrame("Gerar Relatório de Aplicacoes de Insumos");
		
		Container janela = new Container();
		frame.setLayout(null);
		
		JLabel datai = new JLabel("Data Inicial: ");
		JLabel dataf = new JLabel("Data Final: ");
		
		datai.setBounds(50, 40, 180, 60);
		dataf.setBounds(50, 95, 180, 60);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		datai.setFont(fonte);
		dataf.setFont(fonte);
		
		MaskFormatter data = null;
		
		try {
			
			data = new MaskFormatter("##/##/####");
		
		} catch (ParseException e) {
			JOptionPane erro = new JOptionPane("Erro");
			erro.setName("Erro");
			JOptionPane.showMessageDialog(erro, "Falha ao escrever a data", "Erro", 1);
		}
		
		JFormattedTextField datait = new JFormattedTextField(data);
		JFormattedTextField dataft = new JFormattedTextField(data);
		
		datait.setFont(fonte);
		dataft.setFont(fonte);
		
		datait.setBounds(250, 60, 100, 20);
		dataft.setBounds(250, 115, 100, 20);
		
		JButton ok = new JButton("OK");
		ok.setBounds(150, 170, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String datain = datait.getText();
					String aux1 = datain.substring(0, 2);
					String aux2 = datain.substring(3, 5);
					String aux3 = datain.substring(6);
					
					datain = "'" + aux3 + "/" + aux2 + "/" + aux1 + "'";
					String datafin = dataft.getText();
					String aux4 = datafin.substring(0, 2);
					String aux5 = datafin.substring(3, 5);
					String aux6 = datafin.substring(6);
					
					datafin = "'" + aux6 + "/" + aux5 + "/" + aux4 + "'";
					banco.SalvarJsonAplicacoes(datain, datafin);
					frame.setVisible(false);
				} catch (Exception e2) {
					JOptionPane erro = new JOptionPane("Erro");
					JOptionPane.showMessageDialog(erro, "Insira todas as informações necessárias!", "Erro", 1);
				
				}
			}
		});
		
		janela.add(datai);
		janela.add(dataf);
		janela.add(datait);
		janela.add(dataft);
		janela.add(ok);
		
		janela.setSize(400, 280);
		frame.add(janela);
		frame.setSize(400, 280);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
