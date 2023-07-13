package banco1;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONObject;
import org.json.JSONString;

import com.mysql.cj.xdevapi.JsonString;

public class TelaLogin extends JFrame{
	private static final long serialVersionUID = 1L;

	public TelaLogin() {
		super("Login");
		setar();
		setLocationRelativeTo(null);
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
		return "/home/" + retorno + "/Sistema/";
	}
	
	private void setar() {
		Container janela = getContentPane();
		setLayout(null);
		
		JLabel url = new JLabel("URL: "); 
		JLabel usuario = new JLabel("Usuário: ");
		JLabel senha = new JLabel("Senha: ");

		url.setBounds(50, 40, 100, 20);
		usuario.setBounds(50, 100, 100, 20);
		senha.setBounds(50, 160, 100, 20);
		
		Font fonte = new Font("Serif", Font.PLAIN, 18);
		
		url.setFont(fonte);
		usuario.setFont(fonte);
		senha.setFont(fonte);
		
		JTextField texturl = new JTextField();
		JTextField textus = new JTextField();
		JPasswordField textsen = new JPasswordField();
		
		texturl.setFont(fonte);
		textus.setFont(fonte);
		textsen.setFont(fonte);
		
		texturl.setBounds(150, 42, 200, 20);
		textus.setBounds(150, 102, 200, 20);
		textsen.setBounds(150, 162, 200, 20);
		
		JCheckBox salvarurl = new JCheckBox("Salvar URL");
		JCheckBox salvarusuario = new JCheckBox("Salvar usuário");
		JCheckBox salvarsenha = new JCheckBox("Salvar senha");
		JCheckBox login = new JCheckBox("<html>Logar com<br>dados salvos</html>");
		
		salvarurl.setFont(fonte);
		salvarusuario.setFont(fonte);
		salvarsenha.setFont(fonte);
		login.setFont(fonte);
		
		salvarurl.setBounds(380, 42, 200, 20);
		salvarusuario.setBounds(380, 102, 200, 20);
		salvarsenha.setBounds(380, 162, 200, 20);
		login.setBounds(380, 202, 200, 40);
		
		JButton ok = new JButton("OK");
		ok.setBounds(190, 205, 200, 20);
		ok.setSize(100, 40);
		ok.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				BancoDeDados banco = null;
				//"jdbc:mysql://localhost:3306/Roilou", "root", "JKFLjkfl123+"
				/*	URL padrao: jdbc:mysql://localhost:3306/Dados
				 *	Usuário padrao = root 
				*/
				
				if (login.isSelected() == true) {
					File file = new File(leitorCaminho() ,"DadosLogin.json");
					String line;
					String jstring = "";
					
					try  {
						BufferedReader br = new BufferedReader(new FileReader(file));
						try {
							while ((line = br.readLine()) != null) {
								jstring += line;
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JSONObject jobject = new JSONObject(jstring);
					
					String urlr = jobject.getString("URL");
					String usuarior = jobject.getString("Usuario");
					String senhar = jobject.getString("Senha");
					
					banco = new BancoDeDados(urlr, usuarior, senhar);
					
				} else if(salvarurl.isSelected() == true || salvarusuario.isSelected() == true || salvarsenha.isSelected() == true) {
					
					/*
					 *Este método ainda nao está pronto, nao forcar para entrar nele.
					 */
					
					File file = new File(leitorCaminho() ,"DadosLogin.json");
					JSONObject jobject = new JSONObject();
					
					if(file.exists() == false) {
						try {
							file.createNewFile();
						} catch (IOException e1) {
							
						}
					}
					
					if(salvarurl.isSelected() == true) {
						jobject.put("URL", texturl.getText());
					}
					
					if(salvarusuario.isSelected() == true) {
						jobject.put("Usuario", textus.getText());
					}
					
					if(salvarsenha.isSelected() == true) {
						jobject.put("Senha", String.valueOf(textsen.getPassword()));
					}
					
					try {
						try {
							FileWriter filew = new FileWriter(file);
							try {
								filew.write(jobject.toString());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}catch (Exception e0) {
							// TODO: handle exception
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					banco = new BancoDeDados(texturl.getText(), textus.getText(), String.valueOf(textsen.getPassword()));

				} else {
					banco = new BancoDeDados(texturl.getText(), textus.getText(), String.valueOf(textsen.getPassword()));
				}
				
				if(banco.EstaConectado() == true) {
					TelaInicial telainicial= new TelaInicial(banco);
					telainicial.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		janela.add(url);
		janela.add(usuario);
		janela.add(senha);
		janela.add(texturl);
		janela.add(textus);
		janela.add(textsen);
		janela.add(salvarurl);
		janela.add(salvarusuario);
		janela.add(salvarsenha);
		janela.add(login);
		janela.add(ok);
		
		setSize(590, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
