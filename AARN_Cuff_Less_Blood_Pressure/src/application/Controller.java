package application;

import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

import java.io.File;
import java.io.FileOutputStream; 


public class Controller implements Initializable {
	public static int nbcolonne=0;
	public static  MatlabProxyFactory factory;
	public static  MatlabProxy proxy;
    public static String filename = "C:\\Users\\AMINA\\Desktop\\Patients.xls" ;
	public static String path = "cd C:\\Users\\AMINA\\eclipse-workspace\\AARN_Cuff_Less_Blood_Pressure\\src\\application";
    public static boolean matlab_lance=false;
    public static HSSFSheet sheet ;
    public static HSSFWorkbook workbook;

	@FXML
	public TextField text_profession,text_nom,text_prenom;
	
	@FXML
	public TextArea text_medicaments,text_chirurgies,text_abp_systo,text_abp_di,text_diagnostic;
	
	@FXML
	public TextArea text_so,text_why;
	@FXML
	public ComboBox combo_date_jour,combo_date_mois,combo_date_annee;
	
	@FXML
	public ComboBox combo_consult_jour,combo_consult_mois,combo_consult_annee;
	
	@FXML
	public ComboBox combo_gender;
	
	
	@FXML 
	public Button btn_lancer_matlab;
	
	@FXML 
	public Button btn_lancer;
	@FXML
	public ComboBox combo_input;
	@FXML
	public ComboBox combo_patient;
	@FXML
	public LineChart lineChart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  ArrayList<String> list_patients=List_patient();
		  ArrayList<String> list_sexe=List_sexe();
		  ArrayList<String> list_jour=List_jour();
		  ArrayList<String> list_mois=List_mois();
		  ArrayList<String> list_annee=List_annee();
	        
         
		 //Interface
	     ObservableList obList = FXCollections.observableList(list_patients);
	     combo_patient.setItems(obList);
	     
	     obList = FXCollections.observableList(list_sexe);
	     combo_gender.setItems(obList);

	     obList = FXCollections.observableList(list_jour);
	     combo_date_jour.setItems(obList);
	     combo_consult_jour.setItems(obList);

	     obList = FXCollections.observableList(list_mois);
	     combo_date_mois.setItems(obList);
	     combo_consult_mois.setItems(obList);
	     
	     obList = FXCollections.observableList(list_annee);
	     combo_date_annee.setItems(obList);
	     combo_consult_annee.setItems(obList);

	     combo_gender.getSelectionModel().selectFirst();
	     combo_patient.getSelectionModel().selectFirst();
	     combo_date_jour.getSelectionModel().selectFirst();
	     combo_consult_jour.getSelectionModel().selectFirst();
	     combo_date_mois.getSelectionModel().selectFirst();
	     combo_consult_mois.getSelectionModel().selectFirst();
	     combo_date_annee.getSelectionModel().selectFirst();
	     combo_consult_annee.getSelectionModel().selectFirst();
	     
	     
	     // Excel Patient
	     workbook = new HSSFWorkbook();
	     sheet = workbook.createSheet("Patients");  
	    
         HSSFRow rowhead = sheet.createRow((short)0);
         rowhead.createCell(0).setCellValue("Nom");
         rowhead.createCell(1).setCellValue("Prenom");
         rowhead.createCell(2).setCellValue("Date de naissance");
         rowhead.createCell(3).setCellValue("Sexe");
         rowhead.createCell(4).setCellValue("Profession");
         rowhead.createCell(5).setCellValue("Derniere consultation");
         rowhead.createCell(6).setCellValue("Medicaments");
         rowhead.createCell(7).setCellValue("Historique chirurgicale");
         rowhead.createCell(8).setCellValue("Diagnostic");
         nbcolonne++;


         // Lancer Matlab
         /*
         try {
			
			factory = new MatlabProxyFactory();
			 proxy = factory.getProxy();
			 proxy.eval(path);
			 proxy.eval("load patient_test;");
			 proxy.eval("load input_test;");
			 proxy.eval("load output_test;");
			 proxy.eval("load meilleur_net;");
			 
		} catch (MatlabConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
	     
	}
	
	@FXML
	public void tab_eval() throws MatlabConnectionException, MatlabInvocationException {
		if (matlab_lance==false) {Session_matlab(); matlab_lance=true;}

	}
	
	public void Session_matlab() throws MatlabConnectionException, MatlabInvocationException {
		 factory = new MatlabProxyFactory();
		 proxy = factory.getProxy();
		 String path = "cd C:\\Users\\AMINA\\eclipse-workspace\\AARN_Cuff_Less_Blood_Pressure\\src\\application";
		 proxy.eval(path);
		 proxy.eval("load patient_test;");
		 proxy.eval("load input_test;");
		 proxy.eval("load output_test;");
		 proxy.eval("load meilleur_net;");
		 

	}

	@FXML
	public void Save_Patient_Excel() {
		Patient patient= new Patient();
		Date daten= new Date((String)combo_date_jour.getValue(),(String)combo_date_mois.getValue(),(String)combo_date_annee.getValue());
		Date date_consult= new Date((String)combo_consult_jour.getValue(),(String)combo_consult_mois.getValue(),(String)combo_consult_annee.getValue());
		patient.setNom((String)text_nom.getText());
		patient.setPrenom((String)text_prenom.getText());
		patient.setDateN(daten);
		patient.setDate_der_consult(date_consult);
		patient.setSexe((String)combo_gender.getValue());
		patient.setProfession((String)text_profession.getText());
		patient.setMedicaments((String)text_medicaments.getText());
		patient.setHistorique_chirur((String)text_chirurgies.getText());
		patient.setDiagnostic(text_diagnostic.getText());
		patient.toString();
		Save_Excel( patient);
	}
	
    public void Save_Excel(Patient patient) {
	        try {
	        	
	        	
	          
	            HSSFRow row = sheet.createRow((short)nbcolonne);
	            row.createCell(0).setCellValue(patient.getNom());
	            row.createCell(1).setCellValue(patient.getPrenom());
	            row.createCell(2).setCellValue(patient.getDateN().toString());
	            row.createCell(3).setCellValue(patient.getSexe());
	            row.createCell(4).setCellValue(patient.getProfession());
	            row.createCell(5).setCellValue(patient.getDate_der_consult().toString());
	            row.createCell(6).setCellValue(patient.getMedicaments());
	            row.createCell(7).setCellValue(patient.getHistorique_chirur());
	            row.createCell(8).setCellValue(patient.getDiagnostic());
	            nbcolonne++;

	            File f = new File(filename);
		            FileOutputStream fileOut = new FileOutputStream(filename);
		            workbook.write(fileOut);
		           
		       //fileOut.close();
	           //workbook.close();
	            
	            System.out.println("Your excel file has been generated!");

	        } catch ( Exception ex ) {
	            System.out.println(ex);
	        }
	    }

	@FXML
	private ArrayList<Double> ECG_PPG_patient() throws MatlabInvocationException, MatlabConnectionException{

	String patient=(String)combo_patient.getValue();
	int id_patient=Integer.valueOf(patient.substring(8, patient.length()));
	
	
    proxy.eval("mat=patient_test{"+String.valueOf(id_patient)+"};");
    proxy.eval("ppg=mat(1,1:250);");
    proxy.eval("ecg=mat(3,1:250);");
    
	 double []ecg = ((double[]) proxy.getVariable("ecg"));
	 double []ppg = ((double[]) proxy.getVariable("ppg"));


   
     
     if (lineChart.getData()!=null)
     {lineChart.getData().clear();}
     
	 XYChart.Series  series1  = new XYChart.Series<>();
	 XYChart.Series  series2  = new XYChart.Series<>();
	 


	 for (int i=0;i<250;i++) {
	    	series1.getData().add(new XYChart.Data(String.valueOf(i+1) ,ecg[i]));
	    	series2.getData().add(new XYChart.Data(String.valueOf(i+1) ,ppg[i]));
	    }

	 lineChart.getData().add(series1);
     lineChart.getData().add(series2);
     


	return null;
	}

	@FXML
	private void Afficher_ABP() throws MatlabInvocationException, MatlabConnectionException {
		String patient=(String)combo_patient.getValue();
		int id_patient=Integer.valueOf(patient.substring(8, patient.length()));
		
		 proxy.eval(" test= input_test(1:19,"+String.valueOf(id_patient)+");");
		// proxy.eval("test=test';");  
		 proxy.eval("out=sim(meilleur_net,test);");
		   
		 double []out = ((double[]) proxy.getVariable("out"));
		 
		 
		 out[0]=Integer.valueOf((int) (out[0]*100));
		 out[1]=Integer.valueOf((int) (out[1]*100));
		 out[0]=out[0]/10;
		 out[1]=out[1]/10;
		 text_abp_systo.setText(String.valueOf(out[0]));
		 text_abp_di.setText(String.valueOf(out[1]));
		 
		
		 //Causes et consequences
		 String causes=Causes(out[0],out[1]);
		 text_why.setText(causes);
		 String conseq=Consequences(out[0],out[1]);
		 text_so.setText(conseq);

	}

    private String Consequences(double systo, double diasto) {
     String conseq=" ";
    	if (HyperTension(systo,diasto)) {

		conseq="";
 		conseq=conseq+("Angine"+"\n");
 		conseq=conseq+("Infractus du myocarde"+"\n");
 		conseq=conseq+("Accident vasculaire cerebral"+"\n");
 		conseq=conseq+("Insuffisance cardiaque "+"\n");
 		conseq=conseq+("Insuffisance rénale"+"\n");
 		conseq=conseq+("Lesions à la retine (jusqu'a perte de vue)"+"\n");

    	}
		if (HypoTension(systo,diasto)) {
		conseq="";
 		conseq=conseq+("Etourdissement "+"\n");
 		conseq=conseq+("Bourdonnements de l'oreille"+"\n");
 		conseq=conseq+("Vertiges"+"\n");
 		conseq=conseq+("Troubles de la vue"+"\n");
 		conseq=conseq+("Fatigue intense"+"\n");
 		conseq=conseq+("Nausées"+"\n");
 		conseq=conseq+("Evanouissement"+"\n");


		}
		return conseq;
	}
    	
    private String Causes(double systo, double diasto) {
    	 String causes=" ";
     	if (HyperTension(systo,diasto)) {
 		causes="";
 		causes=causes+("Hérédité "+"\n");
 		causes=causes+("Obesité"+"\n");
 		causes=causes+("Sédentarité"+"\n");
 		causes=causes+("Tabagisme"+"\n");
 		causes=causes+("Abus d'alcool"+"\n");
 		causes=causes+("Stress"+"\n");
 		causes=causes+("Forte consommation de sel"+"\n");
 		causes=causes+("Probleme rénal "+"\n");
 		causes=causes+("Probleme endocrinien"+"\n");
 		causes=causes+("Anomalie congénitale de l'aorte"+"\n");
 		causes=causes+("Usage fréquent des anti-inflammatoires"+"\n");
 		causes=causes+("Usage fréquent des bronchodilatateurs"+"\n");
 		causes=causes+("Usage fréquent des décongestionnants nasaux"+"\n");
 		causes=causes+("Consommation de drogues"+"\n");
 		
 	
 		}
 		if (HypoTension(systo,diasto)) {
 		causes="Diabète"+"\n";
 		causes=causes+("Insuffisance des glandes surrénales"+"\n");
 		causes=causes+("Dysautonomie"+"\n");
 		causes=causes+("Tuberculose "+"\n");
 		causes=causes+("Syphilis"+"\n");
 		causes=causes+("Thyroïde"+"\n");
 		causes=causes+("Anémie"+"\n");
 		causes=causes+("Déshydratation"+"\n");
 		causes=causes+("Maladie de Parkinson"+"\n");
 		


 		}
 		return causes;
	}

	private boolean HypoTension(double systo, double diasto) {
		if ((systo<9.0)||(diasto<=6.0))
			return true;
		return false;
	}

	private boolean HyperTension(double systo, double diasto) {
		if ((systo>=14.0)&&(diasto>=9.0))
	    return true;
		return false;
	}

	private ArrayList<String> List_annee() {
		 ArrayList<String> list = new ArrayList<String>();
		 for (int i=2018;i>=1900;i--) {
		 list.add(String.valueOf(i));
		 }
		 return list;
	}

	private ArrayList<String> List_mois() {
		 ArrayList<String> list = new ArrayList<String>();
		 for (int i=1;i<=12;i++) {
		 list.add(String.valueOf(i));
		 }
		 return list;
	}

	private ArrayList<String> List_jour() {
		 ArrayList<String> list = new ArrayList<String>();
		 for (int i=1;i<=31;i++) {
		 list.add(String.valueOf(i));
		 }
		 return list;
	}

	private ArrayList<String> List_sexe() {
		 ArrayList<String> list = new ArrayList<String>();
		 
		 list.add("Masculin");
		 list.add("Feminin");
		 
		 return list;
	}

	private ArrayList<String> List_patient(){
		 ArrayList<String> list = new ArrayList<String>();
		 for (int i=1;i<=20;i++) {
		 list.add("Patient "+String.valueOf(i));
		 }
		 return list;
	}	
	
}
