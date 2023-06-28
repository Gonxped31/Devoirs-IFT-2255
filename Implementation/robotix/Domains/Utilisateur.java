package Robotix.Domains;

public class Utilisateur extends Membre {

	private String prenom;
	private String pseudo;
	private int point;
	private Set<Utilisateur> listeUtilisateurSuivi;
	private ArrayList<Interet> listInteret;
	private Set<Utilisateur> listeSuiveur;
	private ArrayList<String> listeNotifications;
	private ArrayList<Robot> listeRobot;
	private ArrayList<Tache> listeTaches;
	private ArrayList<Action> listeActions;

	/**
	 * 
	 * @param utilisateurSuivi
	 */
	public void suivreUtilisateur(Utilisateur utilisateurSuivi) {
		// TODO - implement Utilisateur.suivreUtilisateur
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param choix
	 * @param nouvelInfo
	 * @param utilisateur
	 */
	public void modifierProfile(String choix, String nouvelInfo, Utilisateur utilisateur) {
		// TODO - implement Utilisateur.modifierProfile
		throw new UnsupportedOperationException();
	}

	public boolean enregistrerRobot() {
		// TODO - implement Utilisateur.enregistrerRobot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utilisateur
	 */
	public ArrayList<Robot> afficherEtatRobot(Utilisateur utilisateur) {
		// TODO - implement Utilisateur.afficherEtatRobot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fournisseur
	 * @param typecomposant
	 */
	public void acheterComposant(Founisseur fournisseur, TypeComposante typecomposant) {
		// TODO - implement Utilisateur.acheterComposant
		throw new UnsupportedOperationException();
	}

	public Fournisseur trouverFournisseur() {
		// TODO - implement Utilisateur.trouverFournisseur
		throw new UnsupportedOperationException();
	}

	public void voirActiviteesMaintenues() {
		// TODO - implement Utilisateur.voirActiviteesMaintenues
		throw new UnsupportedOperationException();
	}

	public void participerActivite() {
		// TODO - implement Utilisateur.participerActivite
		throw new UnsupportedOperationException();
	}

	public void afficherMetriqueFlotte() {
		// TODO - implement Utilisateur.afficherMetriqueFlotte
		throw new UnsupportedOperationException();
	}

	public void voirPointEtClassement() {
		// TODO - implement Utilisateur.voirPointEtClassement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param robot
	 * @param tache
	 */
	public void assignerTacheRobot(Robot robot, Tache tache) {
		// TODO - implement Utilisateur.assignerTacheRobot
		throw new UnsupportedOperationException();
	}

	public void notifier() {
		// TODO - implement Utilisateur.notifier
		throw new UnsupportedOperationException();
	}

	public void gererPoint() {
		// TODO - implement Utilisateur.gererPoint
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param robot
	 * @param tache
	 */
	public void assignerTacheRobot(Robot robot, Tache tache) {
		// TODO - implement Utilisateur.assignerTacheRobot
		throw new UnsupportedOperationException();
	}

}