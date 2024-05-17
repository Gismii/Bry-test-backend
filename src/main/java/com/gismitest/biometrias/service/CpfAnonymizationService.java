package com.gismitest.biometrias.service;

import org.springframework.stereotype.Service;

@Service
public class CpfAnonymizationService {

    public String anonymize(String cpf) {

        return "*********" + cpf.substring(61);
    }
}
