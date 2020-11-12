package springmvckarkas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @Column(name = "applicant_id")
    private BigDecimal id;

    @Column(name = "profession_id")
    private BigDecimal profession_id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "entrance_year")
    private Integer entrance_year;

}
