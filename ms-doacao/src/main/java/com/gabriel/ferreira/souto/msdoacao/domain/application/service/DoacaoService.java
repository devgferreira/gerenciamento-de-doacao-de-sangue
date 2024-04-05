package com.gabriel.ferreira.souto.msdoacao.domain.application.service;

import com.gabriel.ferreira.souto.msdoacao.domain.interfaces.IDoacaoRepository;
import com.gabriel.ferreira.souto.msdoacao.infra.feignClient.DoadorControllerClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService {
    private final ModelMapper _modelMapper;
    private final IDoacaoRepository _doacaoRepository;
    private final DoadorControllerClient _doadorControllerClient;

    public DoacaoService(ModelMapper modelMapper, IDoacaoRepository doacaoRepository, DoadorControllerClient doadorControllerClient) {
        _modelMapper = modelMapper;
        _doacaoRepository = doacaoRepository;
        _doadorControllerClient = doadorControllerClient;
    }
}
