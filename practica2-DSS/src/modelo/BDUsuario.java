package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {
	private static final String PERSISTENCE_UNIT_NAME = "usuario";
	private static EntityManagerFactory factoria;
	
	public static void insertar(Usuario usuario) {
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		if(!existeEmail(usuario.getEmail())) {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		}
	}
	
	
	public static void eliminar(Usuario usuario) {
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		if(existeEmail(usuario.getEmail())) {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
			q.setParameter("email", usuario.getEmail());
			
			Usuario resultado = (Usuario) q.getSingleResult();
			resultado.setName(usuario.getName());
			resultado.setApellidos(usuario.getApellidos());
			
			em.getTransaction().begin();
			if (!em.contains(usuario)) {
			    usuario = em.merge(usuario);
			}
			em.remove(usuario);
			em.getTransaction().commit();
			em.close();
			
		}
	}
	
	
	public static void actualizar(Usuario usuario) {
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		if(!existeEmail(usuario.getEmail())) {
			Query q = em.createQuery("SELECT * FROM Usuario u WHERE u.email = :email");
			q.setParameter("email", usuario.getEmail());
			
			Usuario resultado = (Usuario) q.getSingleResult();
			resultado.setName(usuario.getName());
			resultado.setApellidos(usuario.getApellidos());
			
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
			
		} 
	}


	public static List<Usuario> listarUsuarios() {
		
		if(factoria == null)
			factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		
		EntityManager em = factoria.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario u");
		
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = q.getResultList();
		
		em.close();
		
		
		
		return resultado;
	}


	public static boolean existeEmail(String email) {
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
		q.setParameter("email", email);
		
		int tamanio_consulta = q.getResultList().size();
		
		if( tamanio_consulta == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	


	public static Usuario seleccionarUsuario(String email) {
		Usuario user = null;
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
		q.setParameter("email", email);
		
		int tamanio_consulta = q.getResultList().size();
		
		if( tamanio_consulta != 0) {
			user = (Usuario) q.getSingleResult();
		}
		
		return user;
	}
}