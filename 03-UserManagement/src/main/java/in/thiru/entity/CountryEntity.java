package in.thiru.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer countryId;
	private String countryName;

}
