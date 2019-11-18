package demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = factory.createEntityManager();

        insertPerson(em);
        int countAwesome;
        int countTotal;
        Query query = em.createQuery("select a.city from Address a JOIN Person p ON (a.SSN = p.SSN) WHERE p.isAwesome = TRUE ");
        List<String> result = query.getResultList();
        Query query2 = em.createQuery("SELECT NEW demo.AwesomePeopleCount(p.isAwesome, count(p.SSN)) from Person p GROUP BY p.isAwesome");
        List<AwesomePeopleCount> result2 = query2.getResultList();
        System.out.print("cities with awesome people: ");
        for (String a : result){
            System.out.print(a + " - ");
        }
        System.out.println("");
        AwesomePeopleCount finalResult = new AwesomePeopleCount();
        for (AwesomePeopleCount a : result2) {
            finalResult.setCount(a.getCount());
            finalResult.setNotCount(a.getNotCount());
        }
        System.out.println("In total there are " + finalResult.getCount() + " awesome and " + finalResult.getNotCount() + " not awesome people\"");;

        /*var queryRes1 = await src
                .Where(p => p.IsAwesome)
                .SelectMany(p => p.Addresses)
                .Select(a => a.City)
                .Distinct()
                .OrderBy(n => n)
                .ToListAsync();
            var queryRes2 = await src
                .GroupBy(p => p.IsAwesome)
                .Select(g => new { Awesome = g.Key, Count = g.Count() })
                .ToDictionaryAsync(t => t.Awesome, t => t.Count);*/
        em.close();
        factory.close();
    }

    private static void insertPerson(EntityManager em ){
        em.getTransaction().begin();
        Person newPerson = new Person();
        newPerson.setsSSN("5555050670");
        newPerson.setFirstName("Java");
        newPerson.setLastName("Student");
        newPerson.setDateOfBirth(Date.from(LocalDate.of(1970,6,5).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        newPerson.setAwesome(false);
        newPerson.setAwesomeness(-8.12);
        List<Address> ad = new ArrayList<Address>();
        ad.add(new Address(1273, "AT", "Linz", "Limesstrasse", 12, newPerson));
        newPerson.setAddresses(ad);
        em.persist(newPerson);
        em.getTransaction().commit();
    }
}
