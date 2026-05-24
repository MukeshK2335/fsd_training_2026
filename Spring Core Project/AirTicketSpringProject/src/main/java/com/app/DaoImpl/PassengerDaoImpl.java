package com.app.DaoImpl;

import com.app.Dao.PassengerDao;
import com.app.exception.PassengerNotFoundException;
import com.app.model.Passenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class PassengerDaoImpl implements PassengerDao {
    private final JdbcTemplate jdbcTemplate;

    public PassengerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPassenger(Passenger passenger) {
        String sql="insert into passenger(address,contactNumber,email,gender,name)" +
                "values(?,?,?,?,?) ";
        jdbcTemplate.update(sql,passenger.getAddress(),passenger.getContactNumber(),passenger.getEmail(),
                passenger.getGender(),passenger.getName());

    }

    @Override
    public void deletePassengerById(int id) throws PassengerNotFoundException {
        String sql="delete from passenger where id=?";
        int n=jdbcTemplate.update(sql,id);
        if(n==0){
            throw new PassengerNotFoundException("Invalid ID");
        }
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        String sql="update passenger set email=? where id=?";
        int r=jdbcTemplate.update(sql,passenger.getEmail(),passenger.getId());
        if(r==0){
            throw new PassengerNotFoundException("Invalid ID");
        }
    }

    @Override
    public Passenger getPassengerById(int id){
        String sql="select * from passenger where id=?";
        return jdbcTemplate.queryForObject(sql,mapper(),id);
    }

    @Override
    public List<Passenger> getAllPassenger() {
        String sql="select * from passenger";
        return jdbcTemplate.query(sql,mapper());
    }

    private RowMapper<Passenger> mapper(){
        return (rs,rowa)->{
            return new Passenger(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("contactNumber"),
                    rs.getString("address"),
                    rs.getString("email")
            );

        };
    }

}
