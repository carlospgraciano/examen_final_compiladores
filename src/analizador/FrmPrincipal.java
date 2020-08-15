package analizador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java_cup.runtime.Symbol;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextArea txtEntrada;
	private JTextArea txtSalidaSin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setResizable(false);
		setTitle("Analizador sintactico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Limpiar ambos text areas (Lexico y sintactico) */
				analizadorSintactico();
			}
		});
		btnAnalizar.setBounds(618, 11, 254, 47);
		contentPane.add(btnAnalizar);
		
		txtEntrada = new JTextArea();
		txtEntrada.setBounds(10, 128, 566, 594);
		contentPane.add(txtEntrada);
		
		JLabel lblAnalizador = new JLabel("Analizador");
		lblAnalizador.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblAnalizador.setBounds(254, 17, 184, 32);
		contentPane.add(lblAnalizador);
		
		txtSalidaSin = new JTextArea();
		txtSalidaSin.setEditable(false);
		txtSalidaSin.setBounds(596, 128, 288, 594);
		contentPane.add(txtSalidaSin);
		
		JLabel lblAnalizadorSintctico = new JLabel("Analizador sint\u00E1ctico");
		lblAnalizadorSintctico.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAnalizadorSintctico.setBounds(640, 76, 221, 41);
		contentPane.add(lblAnalizadorSintctico);
		
		JLabel lblCodigoFuente = new JLabel("Codigo fuente");
		lblCodigoFuente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCodigoFuente.setBounds(181, 76, 159, 41);
		contentPane.add(lblCodigoFuente);
	}
	
	private void analizadorSintactico() {
		String ST = txtEntrada.getText();
		Sintax s = new Sintax(new analizador.LexerCup(new StringReader(ST)));
		
		try {
			Object resultado = s.parse().value;
			txtSalidaSin.setText("Análisis realizado correctamente \n" + ST + " RESULTADO = " + resultado);
			txtSalidaSin.setForeground(new Color(25, 111, 61));
		} catch (Exception e) {
			Symbol sym = s.getS();
			System.out.println(e);
			txtSalidaSin.setText("ERROR");
//			txtSalidaSin.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
			txtSalidaSin.setForeground(Color.red);
		}
	}
}