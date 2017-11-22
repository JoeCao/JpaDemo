package club.newtech.jpademo;

import club.newtech.jpademo.domain.Person;
import club.newtech.jpademo.domain.Phone;
import club.newtech.jpademo.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(false)
    public void testAddPhone() {
        Person person = new Person();
        person.setId(1L);
        person.setName("joe");
        Phone phone1 = new Phone();
        phone1.setId(1L);
        phone1.setNumber("13900001111");
        phone1.setOwner(person);
        Phone phone2 = new Phone();
        phone2.setId(2L);
        phone2.setNumber("13900002222");
        phone2.setOwner(person);

        person.addPhone(phone1);
        person.addPhone(phone2);

        personRepository.save(person);

        Person restore = personRepository.findOne(1L);
        Assert.assertNotNull(restore);
        Assert.assertEquals(2, restore.getPhones().size());

        restore.removePhone("13900002222");
        personRepository.save(restore);
//        entityManager.persist(person);
        Person restore2 = personRepository.findOne(1L);
        Assert.assertNotNull(restore2);
        Assert.assertEquals(1, restore2.getPhones().size());

    }
}
