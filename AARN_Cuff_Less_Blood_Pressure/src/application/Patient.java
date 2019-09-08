package application;

public class Patient {
String nom;
String prenom;
Date dateN;
String sexe;
String Profession;
Date Date_der_consult;
String medicaments;
String Historique_chirur;
String diagnostic;


public Patient(String nom, String prenom, Date dateN, String sexe, String profession, Date date_der_consult,
		String medicaments, String historique_chirur, String diagnostic) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.dateN = dateN;
	this.sexe = sexe;
	Profession = profession;
	Date_der_consult = date_der_consult;
	this.medicaments = medicaments;
	Historique_chirur = historique_chirur;
	this.diagnostic = diagnostic;
	
}
public String getDiagnostic() {
	return diagnostic;
}
public void setDiagnostic(String diagnostic) {
	this.diagnostic = diagnostic;
}
public Patient() {
	// TODO Auto-generated constructor stub
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public Date getDateN() {
	return dateN;
}
public void setDateN(Date dateN) {
	this.dateN = dateN;
}
public String getSexe() {
	return sexe;
}
public void setSexe(String sexe) {
	this.sexe = sexe;
}
public String getProfession() {
	return Profession;
}
public void setProfession(String profession) {
	Profession = profession;
}
public Date getDate_der_consult() {
	return Date_der_consult;
}
public void setDate_der_consult(Date date_der_consult) {
	Date_der_consult = date_der_consult;
}
public String getMedicaments() {
	return medicaments;
}
public void setMedicaments(String medicaments) {
	this.medicaments = medicaments;
}
public String getHistorique_chirur() {
	return Historique_chirur;
}
public void setHistorique_chirur(String historique_chirur) {
	Historique_chirur = historique_chirur;
}
}
