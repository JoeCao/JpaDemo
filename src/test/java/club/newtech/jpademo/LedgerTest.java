package club.newtech.jpademo;

import club.newtech.jpademo.domain.Ledger;
import club.newtech.jpademo.domain.Type;
import club.newtech.jpademo.repository.LedgerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest

public class LedgerTest {
    @Resource
    LedgerRepository ledgerRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSave() {
        Ledger ledger = new Ledger().id("test1").userId("A12344").acctState(1).lockedState(0);
        ledger.initialize(Type.SHOP_FUNDS, 100);
        ledger.initialize(Type.QIANMI_COIN, 100);
        Assert.assertEquals(100, ledger.getBalance(Type.SHOP_FUNDS));
        ledgerRepository.save(ledger);
        List<Ledger> list = ledgerRepository.findAll();

        Assert.assertEquals(1, list.size());
        Ledger restore = ledgerRepository.findOne("test1");
        Assert.assertEquals(1, restore.acctState());
        System.out.println(restore);
        Assert.assertEquals(100, restore.getBalance(Type.SHOP_FUNDS));
        restore.accounts().remove(Type.QIANMI_COIN);
        ledgerRepository.save(restore);
        Ledger three = ledgerRepository.findOne("test1");
        System.out.println(three);
        Assert.assertEquals(1, three.accounts().size());
    }
}
