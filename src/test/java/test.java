
import FFSSM.Club;
import FFSSM.GroupeSanguin;
import FFSSM.Licence;
import FFSSM.Moniteur;
import FFSSM.Plongee;
import FFSSM.Plongeur;
import FFSSM.Site;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author damie
 */
public class test {
    
    private Moniteur m1;
    private Plongeur pl1, pl2;
    private Club c1, c2;
    private Licence l1;
    private Site s1;
    private Plongee p1, p2, p3;
    private LocalDate ajd;
    private LocalDate passe;
    private LocalDate valide;
    
    @BeforeEach
	public void setUp() throws Exception {
            ajd = LocalDate.now();
            passe= LocalDate.of(2015, 3, 5);
            valide = LocalDate.of(2020, 11, 3);
            m1 = new Moniteur("1", "mon1", "monite", "adresse", "telephone", passe, 1, 0, GroupeSanguin.APLUS);
            pl1 = new Plongeur("2", "plon", "plongeur", "adresse", "0123456789", passe, 1, GroupeSanguin.APLUS);
            pl2 = new Plongeur("2", "plon", "plongeur", "adresse", "0123456789", valide, 1, GroupeSanguin.APLUS);
            c1 = new Club(m1, "club1", "0123456789");
            c2 = new Club(m1, "club2", "0123456789");
            l1 = new Licence(pl1, "1", valide, 1, c1);
            s1 = new Site("site1", "details");
            p1 = new Plongee(s1, m1, ajd, 20, 1);
            p2 = new Plongee(s1, m1, ajd, 20, 1);
            p3 = new Plongee(s1, m1, ajd, 20, 1);
            
	}

	@Test
	public void testLicencePlongeur() {
            pl1.ajouteLicence("2", valide, c1);
            Licence l2 = new Licence(pl1, "2", valide, 1, c1);
            assertEquals(pl1.getLastLicence().getNumero(),l2.getNumero());
            assertEquals(pl1.getLastLicence().getPossesseur(),l2.getPossesseur());
	}
        
        @Test
	public void testLicencePlongeurValide() {
            pl1.ajouteLicence("2", passe, c1);
            assertFalse(pl1.getLastLicence().estValide(ajd));
            
            pl1.ajouteLicence("3", valide, c1);
            assertEquals(2, pl1.getLicence().size());
            assertTrue(pl1.getLastLicence().estValide(ajd));
	}
        
        @Test
	public void testMoniteurEmplois() {
            m1.nouvelleEmbauche(c1, passe);
            m1.emplois().get(m1.emplois().size()-1).estTerminee();
            m1.nouvelleEmbauche(c2, valide);
            assertEquals(c2.getPresident(), m1);
            try {
                assertEquals(m1.employeurActuel(), Optional.of(c2));
            } catch (Exception ex) {

            }
	}
        
        @Test
	public void testPlongee() {
            pl1.ajouteLicence("1", passe, c1);
            pl2.ajouteLicence("2", valide, c1);
            p1.ajouteParticipant(pl1);
            
            p1.ajouteParticipant(pl2);
            assertEquals(2, p1.participant.size());
            assertFalse(p1.estConforme());
            p1.participant.remove(pl1);
            assertTrue(p1.estConforme());
	}
        
        @Test
	public void testClubPlongeeNConforme() {
            pl1.ajouteLicence("1", passe, c1);
            pl2.ajouteLicence("2", valide, c1);
            p1.ajouteParticipant(pl1);
            p1.ajouteParticipant(pl2);
            p2.ajouteParticipant(pl2);
            c1.organisePlongee(p1);
            assertEquals(1, c1.plongeesNonConformes().size());           
	}
}
