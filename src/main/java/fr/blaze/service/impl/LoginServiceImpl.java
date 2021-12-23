package fr.blaze.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.stereotype.Service;

import fr.blaze.config.CasProperties;
import fr.blaze.security.TokenProvider;
import fr.blaze.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private CasProperties casProperties;

    @Autowired
    private TicketValidator ticketValidator;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public String serviceUrl(String returnUrl, HttpServletResponse response) {
        String baseUrl = CommonUtils.constructServiceUrl(null, response,
                this.serviceProperties.getService(), null,
                this.serviceProperties.getServiceParameter(),
                this.serviceProperties.getArtifactParameter(),
                true);

        if (returnUrl != null) {
            return baseUrl + "?returnUrl=" + returnUrl;
        } else {
            return baseUrl;
        }
    }

    @Override
    public String loginUrl(String returnUrl, HttpServletResponse response) {
        String fullUrl = casProperties.getBaseUrl() + casProperties.getLoginEndpoint();

        return CommonUtils.constructRedirectUrl(fullUrl,
                this.serviceProperties.getServiceParameter(), serviceUrl(returnUrl, response),
                this.serviceProperties.isSendRenew(), false);
    }

    @Override
    public String validate(String ticket, String returnUrl, HttpServletResponse response) {
        String serviceUrl = serviceUrl(returnUrl, response);

        try {
            Assertion assertion = ticketValidator.validate(ticket, serviceUrl);
            String username = assertion.getPrincipal().getName();

            return tokenProvider.createToken(username);

        } catch (TicketValidationException e) {
            return null;
        }
    }
}
