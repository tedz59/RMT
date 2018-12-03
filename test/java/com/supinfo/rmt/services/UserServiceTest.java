package com.supinfo.rmt.services;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("User service Tests")
class UserServiceTest {

	private UserService userService;

	@Mock
	private EntityManager emMocked;

	@Mock
	private TypedQuery mockedQuery;

	@Mock
	private TypedQuery noResultMockedQuery;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);

		userService = new UserService();
		userService.setEm(emMocked);

		when(emMocked.createQuery(anyString(), eq(User.class))).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("username", "toto")).thenReturn(mockedQuery);
		when(mockedQuery.setParameter("password", "titi")).thenReturn(mockedQuery);

		User user = new Employee();
		user.setId(1L);
		when(mockedQuery.getSingleResult()).thenReturn(user);
	}

	@DisplayName("When i call the good username and password, i get an user.")
	@Test
	void shouldReturnLoggedUser() {

		Optional<User> user = userService.login("toto", "titi");

		assertThat(user).isNotNull();

		assertThat(user.isPresent()).isTrue();

		assertThat(user.get().getId()).isEqualTo(1L);

		verify(mockedQuery, times(1)).setParameter("username", "toto");
		verify(mockedQuery, times(1)).setParameter("password", "titi");
		verify(mockedQuery, times(1)).getSingleResult();

	}

	@DisplayName("When i not call the good username and password, i get an empty result.")
	@Test
	void shouldReturnNullIfNotFound() {

		when(mockedQuery.setParameter("username", "nouser")).thenReturn(noResultMockedQuery);
		when(noResultMockedQuery.setParameter(anyString(), anyString())).thenReturn(noResultMockedQuery);
		when(noResultMockedQuery.getSingleResult()).thenThrow(new NoResultException());


		Optional<User> user = userService.login("nouser", "titi");

		assertThat(user).isNotNull();
		assertThat(user.isPresent()).isFalse();

		verify(mockedQuery, times(1)).setParameter("username", "nouser");
		verify(noResultMockedQuery, times(1)).setParameter("password", "titi");
		verify(noResultMockedQuery, times(1)).getSingleResult();
	}
}