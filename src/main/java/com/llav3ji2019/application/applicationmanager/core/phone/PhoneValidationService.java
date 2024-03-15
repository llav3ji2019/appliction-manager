package com.llav3ji2019.application.applicationmanager.core.phone;

import com.kuliginstepan.dadata.client.domain.organization.Phone;
import com.llav3ji2019.application.applicationmanager.core.phone.client.PhoneValidationClientService;
import com.llav3ji2019.application.applicationmanager.public_interface.exception.ApiException;
import com.llav3ji2019.application.applicationmanager.public_interface.phone.PhoneValidatorV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneValidationService implements PhoneValidatorV1 {
    private final PhoneValidationClientService client;

    public String validatePhone(String phoneNumber) {
        Phone response = client.sendValidationRequest(phoneNumber)
                .orElseThrow(() -> new ApiException("No response fount"));

        //qc = 0	Российский телефон, распознан уверенно
        //qc = 7	Иностранный телефон, распознан уверенно
        String qc = response.getQc();
        if (qc.equals("2")) {
            throw new ApiException("Phone number is invalid");
        }

        return response.getPhoneNumber();
    }
}
