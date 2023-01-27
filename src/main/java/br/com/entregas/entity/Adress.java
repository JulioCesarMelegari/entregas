package br.com.entregas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_adress")
public class Adress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Cliente é obrigatório")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;
	
	
	@Size(max = 80, message = "A rua não pode conter mais de 80 caracteres")
	private String street;
	
	@NotBlank(message = "Número é obrigatório")
	@Size(max = 8, message = "Número não pode conter mais de 8 caracteres")
	private String number;
	
	@NotBlank(message = "Bairro é obrigatório")
	@Size(max = 35, message = "Bairro não pode conter mais de 35 caracteres")
	private String district;
	
	@NotBlank(message = "Cidade é obrigatório")
	@Size(max = 20, message = "A cidade não pode conter mais de 20 caracteres")
	private String city;
	
}
