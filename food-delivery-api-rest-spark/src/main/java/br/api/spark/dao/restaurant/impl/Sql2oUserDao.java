package br.api.spark.dao.restaurant.impl;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import br.api.spark.dao.restaurant.OrderProductDao;
import br.api.spark.dao.restaurant.UserDao;
import br.api.spark.model.Order;
import br.api.spark.model.User;

public class Sql2oUserDao implements UserDao {

	private final Sql2o sql2o;
	
    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
    	
        String sql = "INSERT INTO users "
        		+ "(email, password, name, lastName, active, roleId) "
        		+ "VALUES (:email, :password, :name, :lastName, :active, :roleId)";
        
		try (Connection con = sql2o.open()) {
			int id = (int) con.createQuery(sql, true).bind(user).executeUpdate().getKey();
			user.setId(id);
			
		} catch (Sql2oException ex) {
			System.out.println(ex);
		}
		
    }

    @Override
    public List<User> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void deleteById(int id) {
    	
    		String sql = "DELETE from users WHERE id=:id";
        
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

	@Override
	public User findById(int id) {
		try(Connection con = sql2o.open()){
          return con.createQuery("SELECT * FROM users WHERE id = :id")
                  .addParameter("id", id)
                  .executeAndFetchFirst(User.class);
		}
	}

}
