package io.github.cepr0.doublejoin;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "children")
public class Child implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "partition_key")
	private Integer partitionKey;

	@Column(name = "parent_id")
	private Integer parentId;

	private String name;

	public Child(Integer partitionKey, Integer parentId, String name) {
		this.partitionKey = partitionKey;
		this.parentId = parentId;
		this.name = name;
	}
}
