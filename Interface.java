import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Interface {

	private JFrame frame;
	private JTextArea txtrErro;
	private JTextField Ini00;
	private JTextField Ini01;
	private JTextField Ini02;
	private JTextField Ini10;
	private JTextField Ini11;
	private JTextField Ini12;
	private JTextField Ini20;
	private JTextField Ini21;
	private JTextField Ini22;
	private JTextField Limite;
	private JTextField Ini03;
	private JTextField Ini13;
	private JTextField Ini14;
	private JTextField Ini04;
	private JTextField Ini23;
	private JTextField Ini24;
	private JTextField Ini30;
	private JTextField Ini31;
	private JTextField Ini40;
	private JTextField Ini41;
	private JTextField Ini32;
	private JTextField Ini42;
	private JTextField Ini33;
	private JTextField Ini43;
	private JTextField Ini34;
	private JTextField Ini44;
	private JTextField Met00;
	private JTextField Met01;
	private JTextField Met02;
	private JTextField Met03;
	private JTextField Met04;
	private JTextField Met10;
	private JTextField Met11;
	private JTextField Met12;
	private JTextField Met13;
	private JTextField Met14;
	private JTextField Met20;
	private JTextField Met21;
	private JTextField Met22;
	private JTextField Met23;
	private JTextField Met24;
	private JTextField Met30;
	private JTextField Met31;
	private JTextField Met32;
	private JTextField Met33;
	private JTextField Met34;
	private JTextField Met40;
	private JTextField Met41;
	private JTextField Met42;
	private JTextField Met43;
	private JTextField Met44;
	private JTextField textFieldEmbaralhar;
	private JTextPane txtpnNmeroDeMovimentos;
	private JSpinner spinnerMatrixSize = new JSpinner();
	private ArrayList<JTextField> iniMatrix = new ArrayList<JTextField>(), metMatrix = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBounds(0, 0, 784, 64);
		frame.getContentPane().add(TitlePanel);
		TitlePanel.setLayout(null);
		
		JTextPane txtpnBrazAraujoDa = new JTextPane();
		txtpnBrazAraujoDa.setEditable(false);
		txtpnBrazAraujoDa.setBackground(UIManager.getColor("menu"));
		txtpnBrazAraujoDa.setForeground(Color.BLACK);
		txtpnBrazAraujoDa.setText("Braz Araujo da Silva Junior");
		txtpnBrazAraujoDa.setBounds(286, 45, 134, 20);
		TitlePanel.add(txtpnBrazAraujoDa);
		
		JTextPane txtpnQuebraCabeaDo = new JTextPane();
		txtpnQuebraCabeaDo.setEditable(false);
		txtpnQuebraCabeaDo.setFont(new Font("Stencil", Font.PLAIN, 40));
		txtpnQuebraCabeaDo.setBackground(UIManager.getColor("menu"));
		txtpnQuebraCabeaDo.setText("Quebra-Cabe\u00E7a Deslizante");
		txtpnQuebraCabeaDo.setBounds(114, 11, 556, 47);
		TitlePanel.add(txtpnQuebraCabeaDo);
		
		JPanel InPanel = new JPanel();
		InPanel.setBounds(0, 64, 280, 686);
		frame.getContentPane().add(InPanel);
		InPanel.setLayout(null);
		
				JButton btnEmbaralhar = new JButton("Embaralhar");
				btnEmbaralhar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Estado embaralhamento = new Estado();
						if( verificarEntradas() ) {
							criarEstadoInicial(embaralhamento);
							if( txtrErro.getText().equals("") )	{
								embaralhamento.embaralhar(Integer.parseInt(textFieldEmbaralhar.getText()), (int)spinnerMatrixSize.getValue());
								setMatrizInicial(embaralhamento);								
							}
						}
					}
				});
				btnEmbaralhar.setBounds(182, 197, 88, 23);
				InPanel.add(btnEmbaralhar);
		
		JTextPane txtpnDigiteSuasEntradas = new JTextPane();
		txtpnDigiteSuasEntradas.setBounds(37, 0, 196, 31);
		txtpnDigiteSuasEntradas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnDigiteSuasEntradas.setBackground(UIManager.getColor("menu"));
		txtpnDigiteSuasEntradas.setEditable(false);
		txtpnDigiteSuasEntradas.setText("Digite suas entradas:");
		InPanel.add(txtpnDigiteSuasEntradas);
		
		Ini00 = new JTextField();
		Ini00.setHorizontalAlignment(SwingConstants.CENTER);
		Ini00.setText("1");
		Ini00.setBounds(10, 56, 20, 20);
		InPanel.add(Ini00);
		Ini00.setColumns(1);
		
		Ini01 = new JTextField();
		Ini01.setHorizontalAlignment(SwingConstants.CENTER);
		Ini01.setText("2");
		Ini01.setColumns(1);
		Ini01.setBounds(37, 56, 20, 20);
		InPanel.add(Ini01);
		
		Ini02 = new JTextField();
		Ini02.setHorizontalAlignment(SwingConstants.CENTER);
		Ini02.setText("3");
		Ini02.setColumns(1);
		Ini02.setBounds(63, 56, 20, 20);
		InPanel.add(Ini02);
		
		Ini03 = new JTextField();
		Ini03.setEnabled(false);
		Ini03.setText("9");
		Ini03.setHorizontalAlignment(SwingConstants.CENTER);
		Ini03.setColumns(1);
		Ini03.setBounds(89, 56, 20, 20);
		InPanel.add(Ini03);
		
		Ini04 = new JTextField();
		Ini04.setEnabled(false);
		Ini04.setText("9");
		Ini04.setHorizontalAlignment(SwingConstants.CENTER);
		Ini04.setColumns(1);
		Ini04.setBounds(115, 56, 20, 20);
		InPanel.add(Ini04);
		
		Ini10 = new JTextField();
		Ini10.setHorizontalAlignment(SwingConstants.CENTER);
		Ini10.setText("4");
		Ini10.setColumns(1);
		Ini10.setBounds(10, 82, 20, 20);
		InPanel.add(Ini10);
		
		Ini11 = new JTextField();
		Ini11.setHorizontalAlignment(SwingConstants.CENTER);
		Ini11.setText("5");
		Ini11.setColumns(1);
		Ini11.setBounds(37, 82, 20, 20);
		InPanel.add(Ini11);
		
		Ini12 = new JTextField();
		Ini12.setHorizontalAlignment(SwingConstants.CENTER);
		Ini12.setText("6");
		Ini12.setColumns(1);
		Ini12.setBounds(63, 82, 20, 20);
		InPanel.add(Ini12);
		
		Ini13 = new JTextField();
		Ini13.setEnabled(false);
		Ini13.setText("9");
		Ini13.setHorizontalAlignment(SwingConstants.CENTER);
		Ini13.setColumns(1);
		Ini13.setBounds(89, 82, 20, 20);
		InPanel.add(Ini13);
		
		Ini14 = new JTextField();
		Ini14.setEnabled(false);
		Ini14.setText("9");
		Ini14.setHorizontalAlignment(SwingConstants.CENTER);
		Ini14.setColumns(1);
		Ini14.setBounds(115, 82, 20, 20);
		InPanel.add(Ini14);
		
		Ini20 = new JTextField();
		Ini20.setHorizontalAlignment(SwingConstants.CENTER);
		Ini20.setText("7");
		Ini20.setColumns(1);
		Ini20.setBounds(10, 106, 20, 20);
		InPanel.add(Ini20);
		
		Ini21 = new JTextField();
		Ini21.setHorizontalAlignment(SwingConstants.CENTER);
		Ini21.setText("8");
		Ini21.setColumns(1);
		Ini21.setBounds(37, 106, 20, 20);
		InPanel.add(Ini21);
		
		Ini22 = new JTextField();
		Ini22.setText("0");
		Ini22.setHorizontalAlignment(SwingConstants.CENTER);
		Ini22.setColumns(1);
		Ini22.setBounds(63, 106, 20, 20);
		InPanel.add(Ini22);
		
		Ini23 = new JTextField();
		Ini23.setEnabled(false);
		Ini23.setText("9");
		Ini23.setHorizontalAlignment(SwingConstants.CENTER);
		Ini23.setColumns(1);
		Ini23.setBounds(89, 106, 20, 20);
		InPanel.add(Ini23);
		
		Ini24 = new JTextField();
		Ini24.setEnabled(false);
		Ini24.setText("9");
		Ini24.setHorizontalAlignment(SwingConstants.CENTER);
		Ini24.setColumns(1);
		Ini24.setBounds(115, 106, 20, 20);
		InPanel.add(Ini24);
		
		Ini30 = new JTextField();
		Ini30.setEnabled(false);
		Ini30.setText("9");
		Ini30.setHorizontalAlignment(SwingConstants.CENTER);
		Ini30.setColumns(1);
		Ini30.setBounds(10, 132, 20, 20);
		InPanel.add(Ini30);
		
		Ini31 = new JTextField();
		Ini31.setEnabled(false);
		Ini31.setText("9");
		Ini31.setHorizontalAlignment(SwingConstants.CENTER);
		Ini31.setColumns(1);
		Ini31.setBounds(37, 132, 20, 20);
		InPanel.add(Ini31);
		
		Ini32 = new JTextField();
		Ini32.setEnabled(false);
		Ini32.setText("9");
		Ini32.setHorizontalAlignment(SwingConstants.CENTER);
		Ini32.setColumns(1);
		Ini32.setBounds(63, 132, 20, 20);
		InPanel.add(Ini32);
		
		Ini33 = new JTextField();
		Ini33.setEnabled(false);
		Ini33.setText("9");
		Ini33.setHorizontalAlignment(SwingConstants.CENTER);
		Ini33.setColumns(1);
		Ini33.setBounds(89, 132, 20, 20);
		InPanel.add(Ini33);
		
		Ini34 = new JTextField();
		Ini34.setEnabled(false);
		Ini34.setText("9");
		Ini34.setHorizontalAlignment(SwingConstants.CENTER);
		Ini34.setColumns(1);
		Ini34.setBounds(115, 132, 20, 20);
		InPanel.add(Ini34);
		
		Ini40 = new JTextField();
		Ini40.setEnabled(false);
		Ini40.setText("9");
		Ini40.setHorizontalAlignment(SwingConstants.CENTER);
		Ini40.setColumns(1);
		Ini40.setBounds(10, 156, 20, 20);
		InPanel.add(Ini40);
		
		Ini41 = new JTextField();
		Ini41.setEnabled(false);
		Ini41.setText("9");
		Ini41.setHorizontalAlignment(SwingConstants.CENTER);
		Ini41.setColumns(1);
		Ini41.setBounds(37, 156, 20, 20);
		InPanel.add(Ini41);
		
		Ini42 = new JTextField();
		Ini42.setEnabled(false);
		Ini42.setText("9");
		Ini42.setHorizontalAlignment(SwingConstants.CENTER);
		Ini42.setColumns(1);
		Ini42.setBounds(63, 156, 20, 20);
		InPanel.add(Ini42);
		
		Ini43 = new JTextField();
		Ini43.setEnabled(false);
		Ini43.setText("9");
		Ini43.setHorizontalAlignment(SwingConstants.CENTER);
		Ini43.setColumns(1);
		Ini43.setBounds(89, 156, 20, 20);
		InPanel.add(Ini43);
		
		Ini44 = new JTextField();
		Ini44.setEnabled(false);
		Ini44.setText("9");
		Ini44.setHorizontalAlignment(SwingConstants.CENTER);
		Ini44.setColumns(1);
		Ini44.setBounds(115, 156, 20, 20);
		InPanel.add(Ini44);
		
		Met00 = new JTextField();
		Met00.setText("1");
		Met00.setHorizontalAlignment(SwingConstants.CENTER);
		Met00.setColumns(1);
		Met00.setBounds(145, 56, 20, 20);
		InPanel.add(Met00);
		
		Met01 = new JTextField();
		Met01.setText("2");
		Met01.setHorizontalAlignment(SwingConstants.CENTER);
		Met01.setColumns(1);
		Met01.setBounds(172, 56, 20, 20);
		InPanel.add(Met01);
		
		Met02 = new JTextField();
		Met02.setText("3");
		Met02.setHorizontalAlignment(SwingConstants.CENTER);
		Met02.setColumns(1);
		Met02.setBounds(198, 56, 20, 20);
		InPanel.add(Met02);
		
		Met03 = new JTextField();
		Met03.setEnabled(false);
		Met03.setText("9");
		Met03.setHorizontalAlignment(SwingConstants.CENTER);
		Met03.setColumns(1);
		Met03.setBounds(224, 56, 20, 20);
		InPanel.add(Met03);
		
		Met04 = new JTextField();
		Met04.setEnabled(false);
		Met04.setText("9");
		Met04.setHorizontalAlignment(SwingConstants.CENTER);
		Met04.setColumns(1);
		Met04.setBounds(250, 56, 20, 20);
		InPanel.add(Met04);
		
		Met10 = new JTextField();
		Met10.setText("4");
		Met10.setHorizontalAlignment(SwingConstants.CENTER);
		Met10.setColumns(1);
		Met10.setBounds(145, 82, 20, 20);
		InPanel.add(Met10);
		
		Met11 = new JTextField();
		Met11.setText("5");
		Met11.setHorizontalAlignment(SwingConstants.CENTER);
		Met11.setColumns(1);
		Met11.setBounds(172, 82, 20, 20);
		InPanel.add(Met11);
		
		Met12 = new JTextField();
		Met12.setText("6");
		Met12.setHorizontalAlignment(SwingConstants.CENTER);
		Met12.setColumns(1);
		Met12.setBounds(198, 82, 20, 20);
		InPanel.add(Met12);
		
		Met13 = new JTextField();
		Met13.setEnabled(false);
		Met13.setText("9");
		Met13.setHorizontalAlignment(SwingConstants.CENTER);
		Met13.setColumns(1);
		Met13.setBounds(224, 82, 20, 20);
		InPanel.add(Met13);
		
		Met14 = new JTextField();
		Met14.setEnabled(false);
		Met14.setText("9");
		Met14.setHorizontalAlignment(SwingConstants.CENTER);
		Met14.setColumns(1);
		Met14.setBounds(250, 82, 20, 20);
		InPanel.add(Met14);
		
		Met20 = new JTextField();
		Met20.setText("7");
		Met20.setHorizontalAlignment(SwingConstants.CENTER);
		Met20.setColumns(1);
		Met20.setBounds(145, 106, 20, 20);
		InPanel.add(Met20);
		
		Met21 = new JTextField();
		Met21.setText("8");
		Met21.setHorizontalAlignment(SwingConstants.CENTER);
		Met21.setColumns(1);
		Met21.setBounds(172, 106, 20, 20);
		InPanel.add(Met21);
		
		Met22 = new JTextField();
		Met22.setText("0");
		Met22.setHorizontalAlignment(SwingConstants.CENTER);
		Met22.setColumns(1);
		Met22.setBounds(198, 106, 20, 20);
		InPanel.add(Met22);
		
		Met23 = new JTextField();
		Met23.setEnabled(false);
		Met23.setText("9");
		Met23.setHorizontalAlignment(SwingConstants.CENTER);
		Met23.setColumns(1);
		Met23.setBounds(224, 106, 20, 20);
		InPanel.add(Met23);
		
		Met24 = new JTextField();
		Met24.setEnabled(false);
		Met24.setText("9");
		Met24.setHorizontalAlignment(SwingConstants.CENTER);
		Met24.setColumns(1);
		Met24.setBounds(250, 106, 20, 20);
		InPanel.add(Met24);
		
		Met30 = new JTextField();
		Met30.setEnabled(false);
		Met30.setText("9");
		Met30.setHorizontalAlignment(SwingConstants.CENTER);
		Met30.setColumns(1);
		Met30.setBounds(145, 132, 20, 20);
		InPanel.add(Met30);
		
		Met31 = new JTextField();
		Met31.setEnabled(false);
		Met31.setText("9");
		Met31.setHorizontalAlignment(SwingConstants.CENTER);
		Met31.setColumns(1);
		Met31.setBounds(172, 132, 20, 20);
		InPanel.add(Met31);
		
		Met32 = new JTextField();
		Met32.setEnabled(false);
		Met32.setText("9");
		Met32.setHorizontalAlignment(SwingConstants.CENTER);
		Met32.setColumns(1);
		Met32.setBounds(198, 132, 20, 20);
		InPanel.add(Met32);
		
		Met33 = new JTextField();
		Met33.setEnabled(false);
		Met33.setText("9");
		Met33.setHorizontalAlignment(SwingConstants.CENTER);
		Met33.setColumns(1);
		Met33.setBounds(224, 132, 20, 20);
		InPanel.add(Met33);
		
		Met34 = new JTextField();
		Met34.setEnabled(false);
		Met34.setText("9");
		Met34.setHorizontalAlignment(SwingConstants.CENTER);
		Met34.setColumns(1);
		Met34.setBounds(250, 132, 20, 20);
		InPanel.add(Met34);
		
		Met40 = new JTextField();
		Met40.setEnabled(false);
		Met40.setText("9");
		Met40.setHorizontalAlignment(SwingConstants.CENTER);
		Met40.setColumns(1);
		Met40.setBounds(145, 156, 20, 20);
		InPanel.add(Met40);
		
		Met41 = new JTextField();
		Met41.setEnabled(false);
		Met41.setText("9");
		Met41.setHorizontalAlignment(SwingConstants.CENTER);
		Met41.setColumns(1);
		Met41.setBounds(172, 156, 20, 20);
		InPanel.add(Met41);
		
		Met42 = new JTextField();
		Met42.setEnabled(false);
		Met42.setText("9");
		Met42.setHorizontalAlignment(SwingConstants.CENTER);
		Met42.setColumns(1);
		Met42.setBounds(198, 156, 20, 20);
		InPanel.add(Met42);
		
		Met43 = new JTextField();
		Met43.setEnabled(false);
		Met43.setText("9");
		Met43.setHorizontalAlignment(SwingConstants.CENTER);
		Met43.setColumns(1);
		Met43.setBounds(224, 156, 20, 20);
		InPanel.add(Met43);
		
		Met44 = new JTextField();
		Met44.setEnabled(false);
		Met44.setText("9");
		Met44.setHorizontalAlignment(SwingConstants.CENTER);
		Met44.setColumns(1);
		Met44.setBounds(250, 156, 20, 20);
		InPanel.add(Met44);
		
		JTextPane txtpnEstadoInicial = new JTextPane();
		txtpnEstadoInicial.setText("Estado Inicial");
		txtpnEstadoInicial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnEstadoInicial.setEditable(false);
		txtpnEstadoInicial.setBackground(SystemColor.menu);
		txtpnEstadoInicial.setBounds(29, 28, 90, 20);
		InPanel.add(txtpnEstadoInicial);
		
		JTextPane txtpnEstadoMeta = new JTextPane();
		txtpnEstadoMeta.setText("Estado Meta");
		txtpnEstadoMeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnEstadoMeta.setEditable(false);
		txtpnEstadoMeta.setBackground(SystemColor.menu);
		txtpnEstadoMeta.setBounds(154, 28, 90, 20);
		InPanel.add(txtpnEstadoMeta);
		
		spinnerMatrixSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				iniMatrix.clear();			metMatrix.clear();
				iniMatrix.add(Ini00);		metMatrix.add(Met00);
				iniMatrix.add(Ini01);		metMatrix.add(Met01);
				iniMatrix.add(Ini10);		metMatrix.add(Met10);
				iniMatrix.add(Ini11);		metMatrix.add(Met11);
				Ini00.setEnabled(false); Ini01.setEnabled(false); Ini02.setEnabled(false); Ini03.setEnabled(false); Ini04.setEnabled(false);
				Ini10.setEnabled(false); Ini11.setEnabled(false); Ini12.setEnabled(false); Ini13.setEnabled(false); Ini14.setEnabled(false);
				Ini20.setEnabled(false); Ini21.setEnabled(false); Ini22.setEnabled(false); Ini23.setEnabled(false); Ini24.setEnabled(false);
				Ini30.setEnabled(false); Ini31.setEnabled(false); Ini32.setEnabled(false); Ini33.setEnabled(false); Ini34.setEnabled(false);
				Ini40.setEnabled(false); Ini41.setEnabled(false); Ini42.setEnabled(false); Ini43.setEnabled(false); Ini44.setEnabled(false);
				Met00.setEnabled(false); Met01.setEnabled(false); Met02.setEnabled(false); Met03.setEnabled(false); Met04.setEnabled(false);
				Met10.setEnabled(false); Met11.setEnabled(false); Met12.setEnabled(false); Met13.setEnabled(false); Met14.setEnabled(false);
				Met20.setEnabled(false); Met21.setEnabled(false); Met22.setEnabled(false); Met23.setEnabled(false); Met24.setEnabled(false);
				Met30.setEnabled(false); Met31.setEnabled(false); Met32.setEnabled(false); Met33.setEnabled(false); Met34.setEnabled(false);
				Met40.setEnabled(false); Met41.setEnabled(false); Met42.setEnabled(false); Met43.setEnabled(false); Met44.setEnabled(false);
				//*/
				int matrixSize = (int) spinnerMatrixSize.getValue();
				if( matrixSize == 2 ) {
					Ini00.setText("1");	Ini01.setText("2");
					Ini10.setText("3");	Ini11.setText("0");
					Met00.setText("1");	Met01.setText("2");
					Met10.setText("3");	Met11.setText("0");
				}
				if( matrixSize >= 3 ) {
					iniMatrix.add(Ini02);		metMatrix.add(Met02);
					iniMatrix.add(Ini12);		metMatrix.add(Met12);
					iniMatrix.add(Ini22);		metMatrix.add(Met22);
					iniMatrix.add(Ini21);		metMatrix.add(Met21);
					iniMatrix.add(Ini20);		metMatrix.add(Met20);

					//Ini02.setEnabled(true);		Met02.setEnabled(true);
					//Ini12.setEnabled(true);		Met12.setEnabled(true);
					//Ini22.setEnabled(true);		Met22.setEnabled(true);
					//Ini21.setEnabled(true);		Met21.setEnabled(true);
					//Ini20.setEnabled(true);		Met20.setEnabled(true);
				}
				if( matrixSize >= 4 ) {
					iniMatrix.add(Ini03);		metMatrix.add(Met03);
					iniMatrix.add(Ini13);		metMatrix.add(Met13);
					iniMatrix.add(Ini23);		metMatrix.add(Met23);
					iniMatrix.add(Ini33);		metMatrix.add(Met33);
					iniMatrix.add(Ini32);		metMatrix.add(Met32);
					iniMatrix.add(Ini31);		metMatrix.add(Met31);
					iniMatrix.add(Ini30);		metMatrix.add(Met30);

					//Ini03.setEnabled(true);		Met03.setEnabled(true);
					//Ini13.setEnabled(true);		Met13.setEnabled(true);
					//Ini23.setEnabled(true);		Met23.setEnabled(true);
					//Ini33.setEnabled(true);		Met33.setEnabled(true);
					//Ini32.setEnabled(true);		Met32.setEnabled(true);
					//Ini31.setEnabled(true);		Met31.setEnabled(true);
					//Ini30.setEnabled(true);		Met30.setEnabled(true);
				}
				if( matrixSize == 5) {
					iniMatrix.add(Ini04);		metMatrix.add(Met04);
					iniMatrix.add(Ini14);		metMatrix.add(Met14);
					iniMatrix.add(Ini24);		metMatrix.add(Met24);
					iniMatrix.add(Ini34);		metMatrix.add(Met34);
					iniMatrix.add(Ini44);		metMatrix.add(Met44);
					iniMatrix.add(Ini43);		metMatrix.add(Met43);
					iniMatrix.add(Ini42);		metMatrix.add(Met42);
					iniMatrix.add(Ini41);		metMatrix.add(Met41);
					iniMatrix.add(Ini40);		metMatrix.add(Met40);					
				}
				for( JTextField entrada: iniMatrix ) {
					entrada.setEnabled(true);
				}
				for( JTextField entrada: metMatrix ) {
					entrada.setEnabled(true);
				}
				switch(matrixSize)	{
				case 2:
					Ini00.setText("1");		Ini01.setText("2");
					Ini10.setText("3");		Ini11.setText("0");
					Met00.setText("1");		Met01.setText("2");
					Met10.setText("3");		Met11.setText("0");
					break;
				case 3:
					Ini00.setText("1");		Ini01.setText("2"); 	Ini02.setText("3");
					Ini10.setText("4");		Ini11.setText("5");		Ini12.setText("6");
					Ini20.setText("7");		Ini21.setText("8"); 	Ini22.setText("0");
					Met00.setText("1");		Met01.setText("2"); 	Met02.setText("3");
					Met10.setText("4");		Met11.setText("5");		Met12.setText("6");
					Met20.setText("7");		Met21.setText("8"); 	Met22.setText("0");
					break;
				case 4:
					Ini00.setText("1");		Ini01.setText("2"); 	Ini02.setText("3");		Ini03.setText("4");
					Ini10.setText("5");		Ini11.setText("6");		Ini12.setText("7");		Ini13.setText("8");
					Ini20.setText("9");		Ini21.setText("10"); 	Ini22.setText("11");	Ini23.setText("12");
					Ini30.setText("13");	Ini31.setText("14"); 	Ini32.setText("15");	Ini33.setText("0");
					Met00.setText("1");		Met01.setText("2"); 	Met02.setText("3");		Met03.setText("4");
					Met10.setText("5");		Met11.setText("6");		Met12.setText("7");		Met13.setText("8");
					Met20.setText("9");		Met21.setText("10"); 	Met22.setText("11");	Met23.setText("12");
					Met30.setText("13");	Met31.setText("14"); 	Met32.setText("15");	Met33.setText("0");
					break;
				case 5:
					Ini00.setText("1");		Ini01.setText("2"); 	Ini02.setText("3");		Ini03.setText("4");		Ini04.setText("5");
					Ini10.setText("6");		Ini11.setText("7");		Ini12.setText("8");		Ini13.setText("9");		Ini14.setText("10");
					Ini20.setText("11");	Ini21.setText("12"); 	Ini22.setText("13");	Ini23.setText("14");	Ini24.setText("15");
					Ini30.setText("16");	Ini31.setText("17"); 	Ini32.setText("18");	Ini33.setText("19");	Ini34.setText("20");
					Ini40.setText("21");	Ini41.setText("22"); 	Ini42.setText("23");	Ini43.setText("24");	Ini44.setText("0");
					Met00.setText("1");		Met01.setText("2"); 	Met02.setText("3");		Met03.setText("4");		Met04.setText("5");
					Met10.setText("6");		Met11.setText("7");		Met12.setText("8");		Met13.setText("9");		Met14.setText("10");
					Met20.setText("11");	Met21.setText("12"); 	Met22.setText("13");	Met23.setText("14");	Met24.setText("15");
					Met30.setText("16");	Met31.setText("17"); 	Met32.setText("18");	Met33.setText("19");	Met34.setText("20");
					Met40.setText("21");	Met41.setText("22"); 	Met42.setText("23");	Met43.setText("24");	Met44.setText("0");
					break;
				}
				
			}
		});
		spinnerMatrixSize.setModel(new SpinnerNumberModel(3, 2, 5, 1));
		spinnerMatrixSize.setBounds(47, 198, 29, 20);
		InPanel.add(spinnerMatrixSize);
		
		JTextPane txtpnMatriz = new JTextPane();
		txtpnMatriz.setText("Tamanho da Matriz:");
		txtpnMatriz.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtpnMatriz.setEditable(false);
		txtpnMatriz.setBackground(SystemColor.menu);
		txtpnMatriz.setBounds(10, 181, 67, 34);
		InPanel.add(txtpnMatriz);
		
		txtrErro = new JTextArea();
		txtrErro.setBackground(UIManager.getColor("menu"));
		txtrErro.setForeground(Color.RED);
		txtrErro.setWrapStyleWord(true);
		txtrErro.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrErro.setLineWrap(true);
		txtrErro.setEditable(false);
		txtrErro.setBounds(10, 246, 260, 116);
		InPanel.add(txtrErro);
		
		JTextPane txtpnEscolhaOMtodo = new JTextPane();
		txtpnEscolhaOMtodo.setText("Escolha o m\u00E9todo de resolu\u00E7\u00E3o:");
		txtpnEscolhaOMtodo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnEscolhaOMtodo.setEditable(false);
		txtpnEscolhaOMtodo.setBackground(SystemColor.menu);
		txtpnEscolhaOMtodo.setBounds(10, 368, 260, 31);
		InPanel.add(txtpnEscolhaOMtodo);

		ButtonGroup btngrpMetodo = new ButtonGroup();
		
		textFieldEmbaralhar = new JTextField();
		textFieldEmbaralhar.setText("15");
		textFieldEmbaralhar.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmbaralhar.setColumns(10);
		textFieldEmbaralhar.setBounds(134, 198, 39, 20);
		InPanel.add(textFieldEmbaralhar);
				
		txtpnNmeroDeMovimentos = new JTextPane();
		txtpnNmeroDeMovimentos.setText("N\u00FAmero de Movimentos aleat\u00F3rios:");
		txtpnNmeroDeMovimentos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtpnNmeroDeMovimentos.setEditable(false);
		txtpnNmeroDeMovimentos.setBackground(SystemColor.menu);
		txtpnNmeroDeMovimentos.setBounds(83, 181, 119, 34);
		InPanel.add(txtpnNmeroDeMovimentos);
		
		JRadioButton rdbtnBuscaEmLargura = new JRadioButton("[BFS] Busca em Largura");
		rdbtnBuscaEmLargura.setSelected(true);
		rdbtnBuscaEmLargura.setBounds(10, 406, 260, 23);
		InPanel.add(rdbtnBuscaEmLargura);
		btngrpMetodo.add(rdbtnBuscaEmLargura);

		JRadioButton rdbtnBuscaEmProfundidade = new JRadioButton("[DFS] Busca em Profundidade");
		rdbtnBuscaEmProfundidade.setBounds(10, 432, 260, 23);
		InPanel.add(rdbtnBuscaEmProfundidade);
		btngrpMetodo.add(rdbtnBuscaEmProfundidade);
		
		JRadioButton rdbtnBuscaEmProfundidadeIterativa = new JRadioButton("[IDS] Busca em Profundidade Iterativa");
		rdbtnBuscaEmProfundidadeIterativa.setBounds(10, 458, 260, 23);
		InPanel.add(rdbtnBuscaEmProfundidadeIterativa);
		btngrpMetodo.add(rdbtnBuscaEmProfundidadeIterativa);

		JRadioButton rdbtnA = new JRadioButton("[A*] h = Nums fora da meta");
		rdbtnA.setBounds(10, 484, 260, 23);
		InPanel.add(rdbtnA);
		btngrpMetodo.add(rdbtnA);
		
		JRadioButton rdbtnAManhatan = new JRadioButton("[A*] h = Distância de Manhatan");
		rdbtnAManhatan.setBounds(10, 507, 260, 23);
		InPanel.add(rdbtnAManhatan);
		btngrpMetodo.add(rdbtnAManhatan);
		
		JRadioButton rdbtnRandomRestartHill = new JRadioButton("[RRHC] Random Restart Hill Climbing");
		rdbtnRandomRestartHill.setBounds(10, 533, 260, 23);
		InPanel.add(rdbtnRandomRestartHill);
		btngrpMetodo.add(rdbtnRandomRestartHill);

		
		JPanel OutPanel = new JPanel();
		OutPanel.setBounds(279, 64, 505, 686);
		frame.getContentPane().add(OutPanel);
		OutPanel.setLayout(null);
		
		JTextPane txtpnCaminhoParaA = new JTextPane();
		txtpnCaminhoParaA.setText("Caminho para\r\na Solu\u00E7\u00E3o:");
		txtpnCaminhoParaA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnCaminhoParaA.setEditable(false);
		txtpnCaminhoParaA.setBackground(SystemColor.menu);
		txtpnCaminhoParaA.setBounds(10, 11, 132, 56);
		OutPanel.add(txtpnCaminhoParaA);
		
		JScrollPane scrollPaneCam = new JScrollPane();
		scrollPaneCam.setBounds(10, 72, 132, 614);
		OutPanel.add(scrollPaneCam);
		
		JTextArea Caminho = new JTextArea();
		Caminho.setEditable(false);
		Caminho.setBackground(UIManager.getColor("menu"));
		scrollPaneCam.setViewportView(Caminho);
		
		JTextPane txtpnrvoreDeExplorao = new JTextPane();
		txtpnrvoreDeExplorao.setText("\u00C1rvore de explora\u00E7\u00E3o:");
		txtpnrvoreDeExplorao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnrvoreDeExplorao.setEditable(false);
		txtpnrvoreDeExplorao.setBackground(SystemColor.menu);
		txtpnrvoreDeExplorao.setBounds(231, 22, 202, 31);
		OutPanel.add(txtpnrvoreDeExplorao);
		
		JScrollPane scrollPaneArv = new JScrollPane();
		scrollPaneArv.setBounds(154, 72, 341, 564);
		OutPanel.add(scrollPaneArv);
		
		JTextArea Arvore = new JTextArea();
		Arvore.setBackground(UIManager.getColor("menu"));
		Arvore.setEditable(false);
		scrollPaneArv.setViewportView(Arvore);
		
//		JButton btnGerarArquivos = new JButton("Gerar Arquivos");
//		btnGerarArquivos.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				//Main.gerarArquivos(); 
//				//Main.removerInvalidos();
//				//Main.agruparEstatisticas();
//				//Main.agruparExecValidas();
//			}
//		});
//		btnGerarArquivos.setBounds(247, 647, 164, 23);
//		OutPanel.add(btnGerarArquivos);
		
		JScrollPane scrollPaneSol = new JScrollPane();
		scrollPaneSol.setBounds(10, 609, 260, 77);
		InPanel.add(scrollPaneSol);
		
		JTextArea Solubilidade = new JTextArea();
		Solubilidade.setBackground(UIManager.getColor("menu"));
		Solubilidade.setEditable(false);
		scrollPaneSol.setViewportView(Solubilidade);

		Limite = new JTextField();
		Limite.setText("10000");
		Limite.setHorizontalAlignment(SwingConstants.CENTER);
		Limite.setColumns(10);
		Limite.setBounds(78, 563, 77, 20);
		InPanel.add(Limite);
		
		JCheckBox chckbxLimite = new JCheckBox("Limite");
		chckbxLimite.setSelected(true);
		chckbxLimite.setBounds(10, 559, 53, 23);
		InPanel.add(chckbxLimite);
		
		JCheckBox chckbxCiclo = new JCheckBox("Impedir Ciclos");
		chckbxCiclo.setSelected(true);
		chckbxCiclo.setBounds(10, 585, 99, 23);
		InPanel.add(chckbxCiclo);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(165, 575, 89, 23);
		InPanel.add(btnExecutar);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estado inicial = new Estado(), meta = new Estado();
				if( verificarEntradas() ) {
					criarEstadoInicial(inicial);
					if( txtrErro.getText().equals("") )	{
						criarEstadoMeta(meta);
						if( txtrErro.getText().equals("") )	{
							//String aux = "";
							//if( (aux = Main.verificarSolucionabilidade(inicial,meta,(int)spinnerMatrixSize.getValue())).contains("Solucionável!") ) {
								if( chckbxLimite.isSelected() ) {
									if(rdbtnBuscaEmLargura.isSelected()) {
										Main.Buscar("BuscaEmLargura",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnBuscaEmProfundidade.isSelected() ) {
										Main.Buscar("BuscaEmProfundidade",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnBuscaEmProfundidadeIterativa.isSelected() ) {
										Main.Buscar("BuscaEmProfundidadeIterativa",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnA.isSelected() ){
										Main.Buscar("AestrelaNumForaDoMeta",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnAManhatan.isSelected() ){
										Main.Buscar("AestrelaManhatan",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else {
										Main.Buscar("RandonRestartHillClimbing",inicial,meta,Integer.parseInt(Limite.getText()),(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									if( Main.numTestados >= Integer.parseInt(Limite.getText()) &&
										!rdbtnRandomRestartHill.isSelected() ) {
										txtrErro.setText("Busca mal sucedida - Limite de testes alcançado.");										
									} else {
										Caminho.setText(Main.CaminhoParaASolucao((int)spinnerMatrixSize.getValue()));
										Arvore.setText(Main.ArvoreDeExploracao("",Main.raiz,0,(int)spinnerMatrixSize.getValue()));
										txtrErro.setText(Main.Estatisticas((int)spinnerMatrixSize.getValue()));
										txtrErro.setForeground(Color.BLUE);										
									}
								} else {
									if(rdbtnBuscaEmLargura.isSelected()) {
										Main.Buscar("BuscaEmLargura",inicial,meta,-1,(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnBuscaEmProfundidadeIterativa.isSelected() ) {
										Main.Buscar("BuscaEmProfundidadeIterativa",inicial,meta,-1,(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnA.isSelected() ){
										Main.Buscar("AestrelaNumForaDoMeta",inicial,meta,-1,(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else if( rdbtnAManhatan.isSelected() ){
										Main.Buscar("AestrelaManhatan",inicial,meta,-1,(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									else {
										Main.Buscar("RandonRestartHillClimbing",inicial,meta,-1,(int)spinnerMatrixSize.getValue(),chckbxCiclo.isSelected());
									}
									Caminho.setText(Main.CaminhoParaASolucao((int)spinnerMatrixSize.getValue()));
									Arvore.setText(Main.ArvoreDeExploracao("",Main.raiz,0,(int)spinnerMatrixSize.getValue()));
									txtrErro.setText(Main.Estatisticas((int)spinnerMatrixSize.getValue()));
									txtrErro.setForeground(Color.BLUE);
								}
								Main.reset();
							//} else {
							//	txtrErro.setText("Entrada Insolucionável - Este estado meta é inalcançável a partir deste estado inicial!");
							//}
							//Solubilidade.setText(aux);
						}
					}
				}
			}
		});
		
		JTextArea txtrUtilizePara = new JTextArea();
		txtrUtilizePara.setText("Utilize \"0\" para representar o espa\u00E7o vazio.");
		txtrUtilizePara.setWrapStyleWord(true);
		txtrUtilizePara.setLineWrap(true);
		txtrUtilizePara.setForeground(Color.BLACK);
		txtrUtilizePara.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrUtilizePara.setEditable(false);
		txtrUtilizePara.setBackground(SystemColor.menu);
		txtrUtilizePara.setBounds(10, 226, 260, 20);
		InPanel.add(txtrUtilizePara);
		
	}
	//Sistema de localização e indicação de erros de entrada
	private boolean verificarEntradas() {
		txtrErro.setForeground(Color.RED);
		
		for(JTextField entrada: iniMatrix) {
			try{ Integer.parseInt(entrada.getText()); }
			catch(NumberFormatException n){
				txtrErro.setText("Entrada Inválida - A matriz do estado inicial possui elemento(s) que não é(são) número(s)!");
				return false;
			}
		}
		for(JTextField entrada: metMatrix) {
			try{ Integer.parseInt(entrada.getText()); }
			catch(NumberFormatException n){
				txtrErro.setText("Entrada Inválida - A matriz do estado meta possui elemento(s) que não é(são) número(s)!");
				return false;
			}
		}
		txtrErro.setText("");
		tirarDestaques();
		return true;
	}
	
	private void setMatrizInicial(Estado alvo) {
		Ini00.setText(alvo.getEspaco()[0][0] + "");	Ini01.setText(alvo.getEspaco()[0][1] + "");	Ini02.setText(alvo.getEspaco()[0][2] + "");	Ini03.setText(alvo.getEspaco()[0][3] + "");	Ini04.setText(alvo.getEspaco()[0][4] + "");
		Ini10.setText(alvo.getEspaco()[1][0] + "");	Ini11.setText(alvo.getEspaco()[1][1] + "");	Ini12.setText(alvo.getEspaco()[1][2] + "");	Ini13.setText(alvo.getEspaco()[1][3] + "");	Ini14.setText(alvo.getEspaco()[1][4] + "");
		Ini20.setText(alvo.getEspaco()[2][0] + "");	Ini21.setText(alvo.getEspaco()[2][1] + "");	Ini22.setText(alvo.getEspaco()[2][2] + "");	Ini23.setText(alvo.getEspaco()[2][3] + "");	Ini24.setText(alvo.getEspaco()[2][4] + "");
		Ini30.setText(alvo.getEspaco()[3][0] + "");	Ini31.setText(alvo.getEspaco()[3][1] + "");	Ini32.setText(alvo.getEspaco()[3][2] + "");	Ini33.setText(alvo.getEspaco()[3][3] + "");	Ini34.setText(alvo.getEspaco()[3][4] + "");
		Ini40.setText(alvo.getEspaco()[4][0] + "");	Ini41.setText(alvo.getEspaco()[4][1] + "");	Ini42.setText(alvo.getEspaco()[4][2] + "");	Ini43.setText(alvo.getEspaco()[4][3] + "");	Ini44.setText(alvo.getEspaco()[4][4] + "");
	}
	
	private void criarEstadoInicial(Estado inicial) {
		int e;
		int matrixSize = (int) spinnerMatrixSize.getValue();
		if( (e = inicial.setEspaco(Integer.parseInt(Ini00.getText()),0,0,matrixSize)) < 0 ) {
			if( (e = inicial.setEspaco(Integer.parseInt(Ini01.getText()),0,1,matrixSize)) < 0 ) {
				if( (e = inicial.setEspaco(Integer.parseInt(Ini10.getText()),1,0,matrixSize)) < 0 ) {
					if( (e = inicial.setEspaco(Integer.parseInt(Ini11.getText()),1,1,matrixSize)) < 0 ) {
						if( matrixSize >= 3 ) {
							if( (e = inicial.setEspaco(Integer.parseInt(Ini02.getText()),0,2,matrixSize)) < 0 ) {
								if( (e = inicial.setEspaco(Integer.parseInt(Ini12.getText()),1,2,matrixSize)) < 0 ) {
									if( (e = inicial.setEspaco(Integer.parseInt(Ini22.getText()),2,2,matrixSize)) < 0 ) {
										if( (e = inicial.setEspaco(Integer.parseInt(Ini21.getText()),2,1,matrixSize)) < 0 ) {
											if( (e = inicial.setEspaco(Integer.parseInt(Ini20.getText()),2,0,matrixSize)) < 0 ) {
												if( matrixSize >= 4 ) {
													if( (e = inicial.setEspaco(Integer.parseInt(Ini03.getText()),0,3,matrixSize)) < 0 ) {
														if( (e = inicial.setEspaco(Integer.parseInt(Ini13.getText()),1,3,matrixSize)) < 0 ) {
															if( (e = inicial.setEspaco(Integer.parseInt(Ini23.getText()),2,3,matrixSize)) < 0 ) {
																if( (e = inicial.setEspaco(Integer.parseInt(Ini33.getText()),3,3,matrixSize)) < 0 ) {
																	if( (e = inicial.setEspaco(Integer.parseInt(Ini32.getText()),3,2,matrixSize)) < 0 ) {
																		if( (e = inicial.setEspaco(Integer.parseInt(Ini31.getText()),3,1,matrixSize)) < 0 ) {
																			if( (e = inicial.setEspaco(Integer.parseInt(Ini30.getText()),3,0,matrixSize)) < 0 ) {
																				if( matrixSize == 5 ) {
																					if( (e = inicial.setEspaco(Integer.parseInt(Ini04.getText()),0,4,matrixSize)) < 0 ) {
																						if( (e = inicial.setEspaco(Integer.parseInt(Ini14.getText()),1,4,matrixSize)) < 0 ) {
																							if( (e = inicial.setEspaco(Integer.parseInt(Ini24.getText()),2,4,matrixSize)) < 0 ) {
																								if( (e = inicial.setEspaco(Integer.parseInt(Ini34.getText()),3,4,matrixSize)) < 0 ) {
																									if( (e = inicial.setEspaco(Integer.parseInt(Ini44.getText()),4,4,matrixSize)) < 0 ) {
																										if( (e = inicial.setEspaco(Integer.parseInt(Ini43.getText()),4,3,matrixSize)) < 0 ) {
																											if( (e = inicial.setEspaco(Integer.parseInt(Ini42.getText()),4,2,matrixSize)) < 0 ) {
																												if( (e = inicial.setEspaco(Integer.parseInt(Ini41.getText()),4,1,matrixSize)) < 0 ) {
																													if( (e = inicial.setEspaco(Integer.parseInt(Ini40.getText()),4,0,matrixSize)) < 0 ) {
																														txtrErro.setText("");
																														tirarDestaques();
																													} else {
																														entradaInvalida(true,40,e);
																													}
																												} else {
																													entradaInvalida(true,41,e);
																												}
																											} else {
																												entradaInvalida(true,42,e);
																											}
																										} else {
																											entradaInvalida(true,43,e);
																										}
																									} else {
																										entradaInvalida(true,44,e);
																									}
																								} else {
																									entradaInvalida(true,34,e);
																								}
																							} else {
																								entradaInvalida(true,24,e);
																							}
																						} else {
																							entradaInvalida(true,14,e);
																						}
																					} else {
																						entradaInvalida(true,04,e);
																					}
																				} else {
																					txtrErro.setText("");
																					tirarDestaques();
																				}
																			} else {
																				entradaInvalida(true,30,e);
																			}												
																		} else {
																			entradaInvalida(true,31,e);
																		}											
																	} else {
																		entradaInvalida(true,32,e);
																	}										
																} else {
																	entradaInvalida(true,33,e);
																}
															} else {
																entradaInvalida(true,23,e);
															}
														} else {
															entradaInvalida(true,13,e);
														}
													} else {
														entradaInvalida(true,03,e);
													}
												} else {
													txtrErro.setText("");
													tirarDestaques();
												}
											} else {
												entradaInvalida(true,20,e);
											}										
										} else {
											entradaInvalida(true,21,e);
										}
									} else {
										entradaInvalida(true,22,e);
									}
								} else {
									entradaInvalida(true,12,e);
								}							
							} else {
								entradaInvalida(true,02,e);
							}
						} else {
							txtrErro.setText("");
							tirarDestaques();
						}
					} else {
						entradaInvalida(true,11,e);
					}
				} else {
					entradaInvalida(true,10,e);
				}
			} else {
				entradaInvalida(true,01,e);
			}			
		} else {
			entradaInvalida(true,00,e);
		}
	}
	
	private void criarEstadoMeta(Estado meta) {
		int e;
		int matrixSize = (int) spinnerMatrixSize.getValue();
		if( (e = meta.setEspaco(Integer.parseInt(Met00.getText()),0,0,matrixSize)) < 0 ) {
			if( (e = meta.setEspaco(Integer.parseInt(Met01.getText()),0,1,matrixSize)) < 0 ) {
				if( (e = meta.setEspaco(Integer.parseInt(Met10.getText()),1,0,matrixSize)) < 0 ) {
					if( (e = meta.setEspaco(Integer.parseInt(Met11.getText()),1,1,matrixSize)) < 0 ) {
						if( matrixSize >= 3 ) {
							if( (e = meta.setEspaco(Integer.parseInt(Met02.getText()),0,2,matrixSize)) < 0 ) {
								if( (e = meta.setEspaco(Integer.parseInt(Met12.getText()),1,2,matrixSize)) < 0 ) {
									if( (e = meta.setEspaco(Integer.parseInt(Met22.getText()),2,2,matrixSize)) < 0 ) {
										if( (e = meta.setEspaco(Integer.parseInt(Met21.getText()),2,1,matrixSize)) < 0 ) {
											if( (e = meta.setEspaco(Integer.parseInt(Met20.getText()),2,0,matrixSize)) < 0 ) {
												if( matrixSize >= 4 ) {
													if( (e = meta.setEspaco(Integer.parseInt(Met03.getText()),0,3,matrixSize)) < 0 ) {
														if( (e = meta.setEspaco(Integer.parseInt(Met13.getText()),1,3,matrixSize)) < 0 ) {
															if( (e = meta.setEspaco(Integer.parseInt(Met23.getText()),2,3,matrixSize)) < 0 ) {
																if( (e = meta.setEspaco(Integer.parseInt(Met33.getText()),3,3,matrixSize)) < 0 ) {
																	if( (e = meta.setEspaco(Integer.parseInt(Met32.getText()),3,2,matrixSize)) < 0 ) {
																		if( (e = meta.setEspaco(Integer.parseInt(Met31.getText()),3,1,matrixSize)) < 0 ) {
																			if( (e = meta.setEspaco(Integer.parseInt(Met30.getText()),3,0,matrixSize)) < 0 ) {
																				if( matrixSize == 5 ) {
																					if( (e = meta.setEspaco(Integer.parseInt(Met04.getText()),0,4,matrixSize)) < 0 ) {
																						if( (e = meta.setEspaco(Integer.parseInt(Met14.getText()),1,4,matrixSize)) < 0 ) {
																							if( (e = meta.setEspaco(Integer.parseInt(Met24.getText()),2,4,matrixSize)) < 0 ) {
																								if( (e = meta.setEspaco(Integer.parseInt(Met34.getText()),3,4,matrixSize)) < 0 ) {
																									if( (e = meta.setEspaco(Integer.parseInt(Met44.getText()),4,4,matrixSize)) < 0 ) {
																										if( (e = meta.setEspaco(Integer.parseInt(Met43.getText()),4,3,matrixSize)) < 0 ) {
																											if( (e = meta.setEspaco(Integer.parseInt(Met42.getText()),4,2,matrixSize)) < 0 ) {
																												if( (e = meta.setEspaco(Integer.parseInt(Met41.getText()),4,1,matrixSize)) < 0 ) {
																													if( (e = meta.setEspaco(Integer.parseInt(Met40.getText()),4,0,matrixSize)) < 0 ) {
																														txtrErro.setText("");
																														tirarDestaques();
																													} else {
																														entradaInvalida(false,40,e);
																													}
																												} else {
																													entradaInvalida(false,41,e);
																												}
																											} else {
																												entradaInvalida(false,42,e);
																											}
																										} else {
																											entradaInvalida(false,43,e);
																										}
																									} else {
																										entradaInvalida(false,44,e);
																									}
																								} else {
																									entradaInvalida(false,34,e);
																								}
																							} else {
																								entradaInvalida(false,24,e);
																							}
																						} else {
																							entradaInvalida(false,14,e);
																						}
																					} else {
																						entradaInvalida(false,04,e);
																					}
																				} else {
																					txtrErro.setText("");
																					tirarDestaques();
																				}
																			} else {
																				entradaInvalida(false,30,e);
																			}												
																		} else {
																			entradaInvalida(false,31,e);
																		}											
																	} else {
																		entradaInvalida(false,32,e);
																	}										
																} else {
																	entradaInvalida(false,33,e);
																}
															} else {
																entradaInvalida(false,23,e);
															}
														} else {
															entradaInvalida(false,13,e);
														}
													} else {
														entradaInvalida(false,03,e);
													}
												} else {
													txtrErro.setText("");
													tirarDestaques();
												}
											} else {
												entradaInvalida(false,20,e);
											}										
										} else {
											entradaInvalida(false,21,e);
										}
									} else {
										entradaInvalida(false,22,e);
									}
								} else {
									entradaInvalida(false,12,e);
								}							
							} else {
								entradaInvalida(false,02,e);
							}
						} else {
							txtrErro.setText("");
							tirarDestaques();
						}
					} else {
						entradaInvalida(false,11,e);
					}
				} else {
					entradaInvalida(false,10,e);
				}
			} else {
				entradaInvalida(false,01,e);
			}			
		} else {
			entradaInvalida(false,00,e);
		}
	}
	
	private void entradaInvalida( boolean Inicial, int pos, int e) {
		int matrixSize = (int) spinnerMatrixSize.getValue();
		if (Inicial) {
			if( e == 100 ) {
				destacarErro(true,pos,-1);
				txtrErro.setText("Entrada Inválida - Estado Inicial com a posição (" + pos/10 + "," + pos%10 + ") fora do intervalo {0," + (matrixSize*matrixSize-1) + "}");
			} else {
				destacarErro(true,pos,e);
				txtrErro.setText("Entrada Inválida - Estado Inicial com posições repetidas");
			}
		} else {
			if( e == 100 ) {
				destacarErro(false,pos,-1);
				txtrErro.setText("Entrada Inválida - Estado Meta com a posição (" + pos/10 + "," + pos%10 + ") fora do intervalo {0," + (matrixSize*matrixSize-1) + "}");
			} else {
				destacarErro(false,pos,e);
				txtrErro.setText("Entrada Inválida - Estado Meta com posições repetidas");
			}
		}
	}
	
	private void destacarErro(boolean Inicial, int pos1, int pos2) {
		tirarDestaques();
		if( Inicial ) {
			destacarPosIni(pos1);
			destacarPosIni(pos2);
		}
		else {
			destacarPosMeta(pos1);
			destacarPosMeta(pos2);
		}
	}
	
	private void tirarDestaques() {
		for( JTextField entrada: iniMatrix ) {
			entrada.setForeground(Color.BLACK);
		}
		for( JTextField entrada: metMatrix ) {
			entrada.setForeground(Color.BLACK);
		}
		//Ini00.setForeground(Color.BLACK); Ini10.setForeground(Color.BLACK); Ini20.setForeground(Color.BLACK);
		//Ini01.setForeground(Color.BLACK); Ini11.setForeground(Color.BLACK); Ini21.setForeground(Color.BLACK);
		//Ini02.setForeground(Color.BLACK); Ini12.setForeground(Color.BLACK); Ini22.setForeground(Color.BLACK);
	}
	
	private void destacarPosIni(int pos) {
		switch(pos){
		case 00:
			Ini00.setForeground(Color.RED);
			break;
		case 01:
			Ini01.setForeground(Color.RED);
			break;
		case 02:
			Ini02.setForeground(Color.RED);
			break;
		case 03:
			Ini03.setForeground(Color.RED);
			break;
		case 04:
			Ini04.setForeground(Color.RED);
			break;
		case 10:
			Ini10.setForeground(Color.RED);
			break;
		case 11:
			Ini11.setForeground(Color.RED);
			break;
		case 12:
			Ini12.setForeground(Color.RED);
			break;
		case 13:
			Ini13.setForeground(Color.RED);
			break;
		case 14:
			Ini14.setForeground(Color.RED);
			break;
		case 20:
			Ini20.setForeground(Color.RED);
			break;
		case 21:
			Ini21.setForeground(Color.RED);
			break;
		case 22:
			Ini22.setForeground(Color.RED);
			break;
		case 23:
			Ini23.setForeground(Color.RED);
			break;
		case 24:
			Ini24.setForeground(Color.RED);
			break;
		case 30:
			Ini30.setForeground(Color.RED);
			break;
		case 31:
			Ini31.setForeground(Color.RED);
			break;
		case 32:
			Ini32.setForeground(Color.RED);
			break;
		case 33:
			Ini33.setForeground(Color.RED);
			break;
		case 34:
			Ini34.setForeground(Color.RED);
			break;
		case 40:
			Ini40.setForeground(Color.RED);
			break;
		case 41:
			Ini41.setForeground(Color.RED);
			break;
		case 42:
			Ini42.setForeground(Color.RED);
			break;
		case 43:
			Ini43.setForeground(Color.RED);
			break;
		case 44:
			Ini44.setForeground(Color.RED);
			break;
		default:
			break;
		}		
	}
	
	private void destacarPosMeta(int pos) {
		switch(pos){
		case 00:
			Met00.setForeground(Color.RED);
			break;
		case 01:
			Met01.setForeground(Color.RED);
			break;
		case 02:
			Met02.setForeground(Color.RED);
			break;
		case 03:
			Met03.setForeground(Color.RED);
			break;
		case 04:
			Met04.setForeground(Color.RED);
			break;
		case 10:
			Met10.setForeground(Color.RED);
			break;
		case 11:
			Met11.setForeground(Color.RED);
			break;
		case 12:
			Met12.setForeground(Color.RED);
			break;
		case 13:
			Met13.setForeground(Color.RED);
			break;
		case 14:
			Met14.setForeground(Color.RED);
			break;
		case 20:
			Met20.setForeground(Color.RED);
			break;
		case 21:
			Met21.setForeground(Color.RED);
			break;
		case 22:
			Met22.setForeground(Color.RED);
			break;
		case 23:
			Met23.setForeground(Color.RED);
			break;
		case 24:
			Met24.setForeground(Color.RED);
			break;
		case 30:
			Met30.setForeground(Color.RED);
			break;
		case 31:
			Met31.setForeground(Color.RED);
			break;
		case 32:
			Met32.setForeground(Color.RED);
			break;
		case 33:
			Met33.setForeground(Color.RED);
			break;
		case 34:
			Met34.setForeground(Color.RED);
			break;
		case 40:
			Met40.setForeground(Color.RED);
			break;
		case 41:
			Met41.setForeground(Color.RED);
			break;
		case 42:
			Met42.setForeground(Color.RED);
			break;
		case 43:
			Met43.setForeground(Color.RED);
			break;
		case 44:
			Met44.setForeground(Color.RED);
			break;
		default:
			break;
		}
	}
}
