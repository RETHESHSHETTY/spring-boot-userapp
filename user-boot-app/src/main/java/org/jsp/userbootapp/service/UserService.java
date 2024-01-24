package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
 @Autowired
 private  UserDao userDao;
 
 public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(userDao.saveUser(user));
		structure.setMessage("user savedd");
		structure.setStatuscode(HttpStatus.CREATED.value());
		return structure;
	}
 
 
 public ResponseStructure<User> updateUser( User user) {
		ResponseStructure<User> structure= new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(user.getId());
		if (recUser.isPresent()) {
			User dbuser = recUser.get();
			dbuser.setEmail(user.getEmail());
			dbuser.setName(user.getName());
			dbuser.setPhone(user.getPhone());
			dbuser.setPassword(user.getPassword());
			structure.setData(userDao.saveUser(dbuser));
			structure.setMessage("User updated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			
			
			
			return structure;
		}
		structure.setData(null);
		structure.setMessage("cannot update User as Id is Invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
		
 }
 
 
 public ResponseStructure <User> findById(int id) {
		ResponseStructure<User> structure= new ResponseStructure<>();
		
		Optional<User> dbuser = userDao.findById(id);
		if (dbuser.isPresent()) {
			structure.setData(dbuser.get());
			structure.setMessage("User found");
			structure.setStatuscode(HttpStatus.OK.value());
			return structure;
		}
		
		structure.setData(null);
		structure.setMessage("cannot find User as Id is Invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
		
	}

 
 public  ResponseStructure <Boolean>   deleteById( int id) {
		ResponseStructure<Boolean> structure= new ResponseStructure<>();
		Optional<User> dbuser = userDao.findById(id);
		if (dbuser.isPresent()) {
			 
			structure.setData(true);
			structure.setMessage("User deleted");
			structure.setStatuscode(HttpStatus.OK.value());
		userDao.deleteById(id);
			return structure;
		}
		structure.setData(false);
		structure.setMessage("cannot delete User as Id is Invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
 
 public  ResponseStructure <List<User>> findAll() {
		ResponseStructure <List<User>> structure= new ResponseStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("list of users");
		structure.setStatuscode(HttpStatus.OK.value());
		return structure;
 }
 public ResponseStructure <User> verifyUser(long phone,String password){
	 ResponseStructure<User> structure= new ResponseStructure<>();
	 Optional<User> recUser = userDao.verify(phone,password);
	 if(recUser.isPresent()) {
		 structure.setData(recUser.get());
			structure.setMessage("verification successfull");
			structure.setStatuscode(HttpStatus.OK.value());
		 return structure;
	 }
	 structure.setData(null);
		structure.setMessage("invalid phone or password");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
 }

}
 
