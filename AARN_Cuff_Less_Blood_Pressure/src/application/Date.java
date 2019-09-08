package application;

public class Date {
String jour;
String mois;
String annee;
public Date(String jour, String mois, String annee) {
	super();
	this.jour = jour;
	this.mois = mois;
	this.annee = annee;
}
public String getJour() {
	return jour;
}
public void setJour(String jour) {
	this.jour = jour;
}
public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}
public String getAnnee() {
	return annee;
}
@Override
public String toString() {
	return  jour + "/" + mois + "/" + annee ;
}
public void setAnnee(String annee) {
	this.annee = annee;
}
}
