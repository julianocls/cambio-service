package br.com.jcls.cambioservice;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CambioServiceApplicationTests {

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

}
