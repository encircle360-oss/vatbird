package com.encircle360.oss.vatbird.service;

import com.encircle360.oss.vatbird.dto.CountryCode;
import eu.europa.ec.CheckVatPortType;
import eu.europa.ec.CheckVatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import java.net.URL;

@Slf4j
@Service
public class EUVatValidationService {

    @Value("${eu.vies.webservice.wsdl}")
    private URL euWebserviceWsdl;
    private CheckVatPortType checkVatClient;

    public Boolean checkVatId(CountryCode countryCode, String vatId) {
        Holder<String> coutryCode = new Holder<>(countryCode.name());
        Holder<String> vatNumber = new Holder<>(vatId);
        Holder<Boolean> isValid = new Holder<>();
        Holder<XMLGregorianCalendar> requestDate = new Holder<>();
        Holder<String> name = new Holder<>();
        Holder<String> address = new Holder<>();

        log.debug("Checking vatId {}.", countryCode.name() + vatId);
        checkVatClient.checkVat(coutryCode, vatNumber, requestDate, isValid, name, address);
        log.debug("VatId {} isValid {}.)", countryCode.name() + vatId, isValid.value);

        return isValid.value;
    }

    private void ensureCheckVatClient() {
        if(this.checkVatClient == null) {
            CheckVatService checkVatService = new CheckVatService(this.euWebserviceWsdl);
            this.checkVatClient = checkVatService.getCheckVatPort();
        }
    }

    @PostConstruct
    public void init() {
        this.ensureCheckVatClient();
    }
}
