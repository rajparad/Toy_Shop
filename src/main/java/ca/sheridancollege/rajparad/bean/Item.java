package ca.sheridancollege.rajparad.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

		private long id;
		private String description;
		private String color;
		private double weight;
		private int quantity;
}
