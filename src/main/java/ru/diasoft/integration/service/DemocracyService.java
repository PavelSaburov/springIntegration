package ru.diasoft.integration.service;

import ru.diasoft.integration.domain.Oil;

public interface DemocracyService {
    Oil sendDemocracyToCountry(String country);
}
