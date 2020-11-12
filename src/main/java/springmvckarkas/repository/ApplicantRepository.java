package springmvckarkas.repository;

import springmvckarkas.model.Applicant;

import java.math.BigDecimal;

public interface ApplicantRepository {
    Applicant get(BigDecimal id);
}
