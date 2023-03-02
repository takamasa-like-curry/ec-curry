package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

@Repository
public class ToppingRepository {
 
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * Toppingオブジェクトを生成するローマッパー
	 */
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER =new BeanPropertyRowMapper<>(Topping.class);
//【4】詳細画面
	/**
	 * トッピング一覧を表示します
	 * @return　トッピング一覧情報
	 */
	public List<Topping> findAll(){
		String sql="SELECT id,name,price_m,price_l FROM toppings ORDER BY id;";
		List<Topping> toppingList=template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}

}
