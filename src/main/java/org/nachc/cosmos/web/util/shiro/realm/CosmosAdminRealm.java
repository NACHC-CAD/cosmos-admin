package org.nachc.cosmos.web.util.shiro.realm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.PersonDvo;
import org.nachc.cosmos.web.util.params.MySqlParams;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nach.core.util.web.security.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CosmosAdminRealm extends SimpleAccountRealm {

	/**
	 * Authentication method is based on SimpleAccountRealm
	 * doGetAuthenticationInfo(AuthenticationToken token) method.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("* * * DOING CUSTOM AUTHN * * *");
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		Subject subject = SecurityUtils.getSubject();
		log.info("Got subject: " + subject);
		String isLoggedOff = subject.getSession().getAttribute("") + "";
		log.info("Is logged off: " + isLoggedOff);
		SimpleAccount account = null;
		if (upToken != null && upToken.getUsername() != null && upToken.getPassword() != null && upToken.getPassword().length > 0) {
			String uid = upToken.getUsername();
			String pwd = new String(upToken.getPassword());
			log.info("Doing login for user: " + uid);
			boolean isValid = this.authenticate(uid, pwd);
			if (isValid) {
				account = new SimpleAccount(uid, pwd, getName());
				account.setCredentials(pwd);
				account.addRole("ROLE_ADMIN");
			} else {
				account = null;
				log.info("Credentials failed for user: " + uid);
			}
		} else {
			account = null;
			log.info("UID OR PWD IS NULL");
		}
		// account.setObjectPermissions(permissions);
		log.info("Done with custom authn");
		return account;
	}

	/**
	 * Authorization method is based on SimpleAccountRealm
	 * doGetAuthorizationInfo(PrincipalCollection principals) method.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("* * * DOING CUSTOM AUTHZ * * *");
		String username = getUsername(principals);
		USERS_LOCK.readLock().lock();
		try {
			SimpleAccount rtn = this.users.get(username);
			log.info("Done with custom authz.");
			return rtn;
		} finally {
			USERS_LOCK.readLock().unlock();
		}
	}

	private boolean authenticate(String uid, String pwd) {
		String key = MySqlParams.getKey();
		log.info("looking for: " + uid);
		Connection conn = this.getConnection();
		PersonDvo dvo = Dao.find(new PersonDvo(), "username", uid, conn);
		if (dvo == null) {
			log.info("Did not find: " + uid);
			return false;
		}
		log.info("Authenticating password for: " + uid);
		String salt = dvo.getSalt();
		PasswordUtil util = new PasswordUtil(salt, key);
		String expected = dvo.getPassword();
		boolean rtn = util.authenticate(pwd, expected);
		log.info("Authenicated (" + uid + "): " + rtn);
		return rtn;
	}

	public Connection getConnection() {
		DataSource dataSource = null;
		Connection connection = null;
		try {
			InitialContext initContext;
			initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup("java:/MySqlDS");
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
