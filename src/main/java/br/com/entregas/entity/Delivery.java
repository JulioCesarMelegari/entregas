package br.com.entregas.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@javax.persistence.Entity
@Table(name = "tb_delivery")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Preencher com o nome do cliente")
	private String Client;
	
	@NotBlank(message = "Preencer com o enderço de entrega")
	private String deliveryAdress;
	
	@NotBlank(message = "O pedido é obrigatório")
	@Size(max = 20, message = "O pedido nao pode conter mais que 20 caracteres")
	private String order;
	
	@NotNull(message = "Preencer com o total do pedido")
	private double valueOrder;
	
	@NotNull(message = "Indique o entregador")
	private double deliveryMaan;
	
	@NotNull(message = "Preencer com a taxa de entrega")
	private double deliveryFee;
	

	private LocalDateTime dateregistration= LocalDateTime.now();
	
}
