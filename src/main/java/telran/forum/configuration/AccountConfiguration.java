package telran.forum.configuration;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import telran.forum.exceptions.UserAuthenticationException;
import telran.forum.service.UserAccountCredentials;

@Configuration
@ManagedResource
public class AccountConfiguration {

	@Value("${exp.value}")
	long expPeriod;

	@ManagedAttribute
	public long getExpPeriod() {
		return expPeriod;
	}

	@ManagedAttribute
	public void setExpPeriod(long expPeriod) {
		this.expPeriod = expPeriod;
	}

	public UserAccountCredentials tokenDecode(String token) {
		try {
			int pos = token.indexOf(" ");
			token = token.substring(pos + 1);
			String credential = new String(Base64.getDecoder().decode(token));
			String[] credentials = credential.split(":");
			return new UserAccountCredentials(credentials[0], credentials[1]);
		} catch (Exception e) {
			throw new UserAuthenticationException();
		}
	}

}
