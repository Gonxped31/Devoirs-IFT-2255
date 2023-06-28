package Robotix.Domains;

public class Robot {

	private UUID numeroSerie;
	private String nom;
	private String type;
	private List<Composant> composants;
	private int position;
	private int vitesse;
	private int niveauBatterie;
	private List<String> listeAction;

	/**
	 * 
	 * @param tache
	 * @param date
	 */
	public void executerTache(Tache tache, Date date) {
		// TODO - implement Robot.executerTache
		throw new UnsupportedOperationException();
	}

}