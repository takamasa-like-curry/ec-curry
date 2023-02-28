package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.repository.UserRepository;

/**
 * ユーザー登録の業務処理を実施するサービス.
 * 
 * @author hayashiasuka
 *
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー登録を行う.
	 * 
	 * @param registerUserForm
	 */
	public void registerUser(RegisterUserForm registerUserForm) {

		User user = new User();
		BeanUtils.copyProperties(registerUserForm, user);
		String name = registerUserForm.getLastName() + registerUserForm.getFirstName();
		user.setName(name);
		userRepository.insert(user);

	}

	/**
	 * 入力されたEmailがすでに登録されているか確認する.
	 * 
	 * @param email
	 * @return
	 */
	public User searchByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (email.equals(user.getEmail())) {
			return user;
		} else {
			return null;
		}
	}

}
