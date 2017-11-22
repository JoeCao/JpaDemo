package club.newtech.jpademo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@EqualsAndHashCode
@ToString
public class Person {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();


    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setOwner(this);
    }

    public void removePhone(String number) {
        List<Phone> matched = this.phones.stream()
                .filter(phone -> phone.getNumber()
                        .equals(number)).collect(Collectors.toList());
        if (matched != null && matched.size() == 1) {
            Phone p = matched.get(0);
            this.phones.remove(p);
            p.setOwner(null);
        }
    }
}
