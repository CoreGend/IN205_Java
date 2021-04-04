package ensta;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ensta.librarymanager.models.*;

/**
 * Unit test for models
 *
 */
public class ModeleTest
{
	@Test
	public void testLivre()
	{
		Livre livre_0 = new Livre("La Petite Sirène", "Hans C. Andersen", "9780836249187");
		Livre livre_1 = new Livre("Le vilain petit canard", "Hans C. Andersen", "9781400150496");
		Livre livre_2 = new Livre("La reine des neiges", "Hans C. Andersen", "9781483040035");
		
		assertEquals("Livre [id=0, titre=La Petite Sirène, auteur=Hans C. Andersen, isbn=9780836249187]", livre_0.toString());
	
		assertEquals(0, livre_0.getId());
		assertEquals("La Petite Sirène", livre_0.getTitre());
		assertEquals("Hans C. Andersen",livre_0.getAuteur());
		assertEquals("9780836249187", livre_0.getIsbn());
	
		assertEquals(1, livre_1.getId());
		assertEquals(2, livre_2.getId());
	}
	
	@Test
	public void testMembre()
	{
		Membre membre_0 = new Membre("Lenotre", "Antoine", "Rue du marché", "aln@gmail.com", "0601020304", Abonnement.BASIC);
		assertEquals("Membre [id=0, nom=Lenotre, prenom=Antoine, adresse=Rue du marché, email=aln@gmail.com, telephone=0601020304, abonnement=BASIC]", membre_0.toString());
	}
}