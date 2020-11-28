package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity(name = "assets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private long id;
    @NotBlank(message = "{name.required}")
    @Column(name = "name")
    private String name;

    public Asset(String name) {
        this.name = name;
    }

}
