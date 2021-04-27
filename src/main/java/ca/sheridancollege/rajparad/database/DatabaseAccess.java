package ca.sheridancollege.rajparad.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.rajparad.bean.Item;



@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public ArrayList<Item> getAll(){
		
		ArrayList<Item> list;
		
		String sql = "select * from rajparad";
		
		list = (ArrayList<Item>) jdbc.query(sql,
				new BeanPropertyRowMapper<Item>(Item.class));
		return list;
				
	}
	

	public void insertItem(Item item) {
		String query="INSERT INTO rajparad (description,color,weight, quantity )"
				+ " VALUES (:description,:color,:weight, :quantity )";

		MapSqlParameterSource namedParameters = 
						new MapSqlParameterSource();
		namedParameters.addValue("description", item.getDescription());
		namedParameters.addValue("color", item.getColor());		
		namedParameters.addValue("weight", item.getWeight());
		namedParameters.addValue("quantity", item.getQuantity());
		
		jdbc.update(query, namedParameters);
	}
	
	public Item  getItemById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM rajparad WHERE id=:id";
		parameters.addValue("id", id);
		ArrayList<Item> appointment= (ArrayList<Item>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Item>(Item.class));
		if (appointment.size()>0)
			return appointment.get(0);
		return null;
	}
	public void modifyItem(Item i) {
		String sql = "update rajparad set description=:d ,color=:c ,weight=:w , quantity=:q where id=:i";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("d",i.getDescription());
		params.put("c",i.getColor());
		params.put("w", i.getWeight());
		params.put("q", i.getQuantity());
		params.put("i",i.getId());
		jdbc.update(sql, params);
	}
		
	public void deleteItemById(int id) {
		String sql="delete from rajparad where id=:id";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		jdbc.update(sql, params);
	}
	
	public void deleteAllItem() {
		String sql="delete from rajparad" ;
		HashMap<String, Object> params = new HashMap<String, Object>();
	
		jdbc.update(sql, params);
	}
	
	public void deleteQuaItem(int q) {
	
		String sql="delete from rajparad where quantity = :q" ;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("q", 0);
		jdbc.update(sql, params);
	}
	
	// For Rest
	
	
	public Long save(Item i) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO rajparad(description,color,weight,quantity) VALUES(:description,:color,:weight,:quantity)";
		namedParameters.addValue("description", i.getDescription());
		namedParameters.addValue("color", i.getColor());		
		namedParameters.addValue("weight", i.getWeight());
		namedParameters.addValue("quantity", i.getQuantity());
		
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}
	
	public List<Item> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM rajparad";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));
	}
	public List<Item> findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM rajparad WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));
	}
}

