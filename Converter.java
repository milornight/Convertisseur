import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.lang.Exception; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;



public class Converter extends JFrame implements ActionListener, ChangeListener, MouseListener {
  private JPanel panneau,ligne1,west,center,east,ligne3;
  private JTextField txtEuro,txtDollard;
  //private JTextField txtTaux;
  private JSpinner txtTaux;
  private JButton quitter;
  private JLabel euro,dollard,fleche,taux,unite;
  private double Taux;

  public Converter(){
	super();
	this.setTitle("convertisseur"); //title
    this.setSize(250,140);			//taille
	this.setLocationRelativeTo(null); // se positionner au centre de l'ecran
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // termine le processus si on clique sur la croix           
    
    
    //on compose l'interface en 5 parties et on le assemble dans panneau
    panneau = new JPanel();
    ligne1 = new JPanel();
    west = new JPanel();
    east = new JPanel();
    center = new JPanel();
    ligne3 = new JPanel();


	//instanciation des JLabel
    euro = new JLabel();
    dollard = new JLabel();
    fleche = new JLabel();
    taux = new JLabel();
    unite = new JLabel();
    euro.setText("euro");
    dollard.setText("$");
    fleche.setText(" => ");
    taux.setText(" Taux: 1 euro = ");
    unite.setText("$");
	
	
	//instanciation de boutton
    quitter= new JButton("Quitter");


	//ajouter les ecouteurs sur le bouton
	quitter.addMouseListener(this);
  
  
	//instanciation des cases textes
	txtEuro = new JTextField("",5);
    txtDollard = new JTextField("",5);
    //txtTaux = new JTextField("",5);
    SpinnerModel numModel = new SpinnerNumberModel(1.56, 0.10, 10.00, 0.01); //valeur initiale, valeur minimum, valeur maximum , step
    txtTaux = new JSpinner(numModel);
    
    
    //ajouter les ecouteurs sur ces cases textes
    txtEuro.addActionListener(this);
    txtDollard.addActionListener(this);
    //txtTaux.setText(Double.toString(Taux));
    //txtTaux.addActionListener(this);
	txtTaux.addChangeListener(this);
	
	
	//assemblage
    ligne1.add(txtEuro);
    ligne1.add(euro);
    ligne1.add(fleche);
    ligne1.add(txtDollard);
    ligne1.add(dollard);
    
    west.add(taux);
    center.add(txtTaux);
    east.add(unite);
    
    ligne3.add(quitter);
    
    panneau.setLayout(new BorderLayout());
    panneau.add(ligne1,BorderLayout.NORTH);
    panneau.add(west,BorderLayout.WEST);
    panneau.add(center,BorderLayout.CENTER);
    panneau.add(east,BorderLayout.EAST);
    panneau.add(ligne3,BorderLayout.SOUTH);

    this.setContentPane(panneau);
    this.setVisible(true); //rend visible


    }

	public double toDollards(double s) throws Exception{
		if (s < 0){
			throw new Exception("La valeur entree ne peux pas etre negaive!");
		}
		return s/Taux;
		
	}

	public double toEuros(double s) throws Exception{
		if (s < 0){
			throw new Exception("La valeur entree ne peux pas etre negaive!");
		}
		return s*Taux;
	}

	public void setTaux(double t) throws Exception{
		if (t < 0){
			throw new Exception("La valeur entree ne peux pas etre negaive!");
		}
		Taux=t;
		//this.txtTaux.setText(Double.toString(Taux));
		this.txtTaux.setValue(Taux);
	}
	
	public double getTaux(){
		return Taux;
	}
	
	//arrondir la valeur en millier
	public double arrondi(double n) {
	double val = n;
	val=( Math.round(val*1000));
	val = val/1000;
	
	return val;
}
	
	
	public void actionPerformed(ActionEvent e) {
		
		try{
			/*if(e.getSource().equals(txtTaux)) {
				double newTaux = Double.parseDouble(txtTaux.getText());
				setTaux(newTaux);
			}*/
		
		
			if(e.getSource().equals(txtEuro)){ // quand on tape des valeurs dans la case du euro 
				double converE = Double.parseDouble(txtEuro.getText());
				txtDollard.setText(Double.toString(this.arrondi(toEuros(converE))));
				this.fleche.setText(" => ");
			}
			
			if(e.getSource().equals(txtDollard)){ // quand on tape des valeurs dans la case du dollard
				double converD = Double.parseDouble(txtDollard.getText());
				txtEuro.setText(Double.toString(this.arrondi(toDollards(converD))));
				this.fleche.setText(" <= ");
			}
		}
		catch (Exception event){
			JOptionPane.showMessageDialog(null,"Rentre la valeur positive merci!", "Warning", JOptionPane.ERROR_MESSAGE);
		}
			
		
	}
	
	
	//fonction pour fermer la fenetre quand on presser sur le bouton quitter
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == quitter) {
	    	System.exit(0);
		}
	}
	
	 public void mouseClicked(MouseEvent ev) {}
     public void mouseReleased(MouseEvent ev) {}
     public void mouseEntered(MouseEvent ev) {}
     public void mouseExited(MouseEvent ev) {}
	
	
	//fonction pour changer la valeur de taux a l'aide Jspinner
	public void stateChanged(ChangeEvent e){
		try{
			if (e.getSource() == txtTaux) {
				double val = (double) txtTaux.getValue();
				if (val < 0.01 || val > 100.00) {
					setTaux(1.00);
					txtTaux.setValue(1.00);
				}
				else {
					setTaux(val);
				}	
			}
		}
		catch (Exception event){
			JOptionPane.showMessageDialog(null,"Rentre la valeur positive merci!", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
}
		


