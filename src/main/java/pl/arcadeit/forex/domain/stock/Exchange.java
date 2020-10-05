package pl.arcadeit.forex.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

@Entity(name="exchange")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exchange {

    @Id
    @Column(name = "code")
    @NotEmpty
    private String code;

    @Column(name = "name")
    @NotEmpty
    private String name;

    // Market Identifier Code
    @Column(name = "mic")
    @NotEmpty
    private String mic;

    @Column(name = "timezone")
    @NotEmpty
    private String timezone;

    @Column(name = "hour_start")
    @NotNull
    private Time startHour;

    @Column(name = "hour_end")
    @NotNull
    private Time endHour;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;
}
