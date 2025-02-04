package examples.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // デバッガでフィールドを参照しようとすると toString() を呼び出す
    // 実体が必要になりselectが呼ばれる
    @Override
    public String toString() {
        return String.format("id: %d, name: %s", id, name);
    }
}
