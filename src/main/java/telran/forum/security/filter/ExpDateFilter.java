package telran.forum.security.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.forum.configuration.AccountConfiguration;
import telran.forum.dao.UserAccountRepository;
import telran.forum.model.UserAccount;
import telran.forum.service.UserAccountCredentials;

@Service
@Order(20)
public class ExpDateFilter implements Filter {

	
	@Autowired
	AccountConfiguration configuration;
	
	@Autowired
	UserAccountRepository repository;
	
	
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		if(req.getServletPath().startsWith("/account")) 
		{
			String auth=req.getHeader("Authorization");
			UserAccountCredentials credentials= configuration.tokenDecode(auth);
			UserAccount account=repository.findById(credentials.getLogin()).orElse(null);
			if(account==null)
			{
				resp.sendError(401,"Unauthorized");
			}
			else if(!account.getPassword().equals(credentials.getPassword())) {
				resp.sendError(403,"Forbidden");
			}
			if(LocalDateTime.now().isAfter(account.getExpDate()))
			{
				resp.sendError(408,"TimeOut");
			}
		}
		chain.doFilter(req, resp);
	}

}
