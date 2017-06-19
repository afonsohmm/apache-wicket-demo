package com.jjdev.tasks;

import com.jjdev.tasks.login.JLogin;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author jgilson
 */
public class JApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return JLogin.class;
    }

    @Override
    protected void init() {
        super.init();
        setRootRequestMapper(new CryptoMapper(getRootRequestMapper(), this));
    }
    
    

}
