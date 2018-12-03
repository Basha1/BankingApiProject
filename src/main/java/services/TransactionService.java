package services;

import com.mycompany.mavenproject1.NotFoundException;
import models.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transactions")
public class TransactionService {

    EntityManager entityManager;

    public TransactionService() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        entityManager = emfactory.createEntityManager();
    }


     //curl -v -H "Accept: application/json" -H  http://localhost:8080/api/transactions
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getTransactions() {
        List<Transaction> list = allEntries();
        GenericEntity entity = new GenericEntity<List<Transaction>>(list) {
        };
        return Response.ok(entity).build();

    }

    public List<Transaction> allEntries() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> rootEntry = cq.from(Transaction.class);
        CriteriaQuery<Transaction> all = cq.select(rootEntry);
        TypedQuery<Transaction> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    // curl "http://localhost:8080/api/transactions/{id}"
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Transaction getTransaction(@PathParam("id") int id) {
        Transaction test = entityManager.find(Transaction.class, id);
        if (test == null) {
            throw new NotFoundException("No Transaction Matching This id");
        }
        return test;

    }


    /*
    @DELETE
    @Path("/{id}")
    public Response deleteTransaction(@PathParam("id") int id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(transaction);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (transaction == null) {
            throw new NotFoundException("Delete: Transaction with" + id + "not found");
        }
        return ;
    }
    */
  

    
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Transaction c) {
        System.out.println(c);
        entityManager.getTransaction().begin();

        entityManager.persist(c);
        entityManager.getTransaction().commit();

        entityManager.close();
//        entityManager.close();

        return Response.status(200).entity(c).build();
    }

}
