package com.gabriel.ferreira.souto.msdoacao.application.service;

import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.domain.interfaces.IDoacaoRepository;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doacao.Doacao;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doador.DoadorResponse;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.DoacaoNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.ExceptionResponse;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants.ErrorConstants;
import com.gabriel.ferreira.souto.msdoacao.infra.feignClient.DoadorControllerClient;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoacaoService implements IDoacaoService {
    private final ModelMapper _modelMapper;
    private final IDoacaoRepository _doacaoRepository;
    private final DoadorControllerClient _doadorControllerClient;

    public DoacaoService(ModelMapper modelMapper, IDoacaoRepository doacaoRepository, DoadorControllerClient doadorControllerClient) {
        _modelMapper = modelMapper;
        _doacaoRepository = doacaoRepository;
        _doadorControllerClient = doadorControllerClient;
    }

    @Override
    public DoacaoDTO criarDoacao(DoacaoDTO doacaoDTO) {
        ResponseEntity<DoadorResponse> doadorResponse = _doadorControllerClient.buscarDoadorComId(doacaoDTO.getDoadorId());
        if (doadorResponse == null){
            throw new RuntimeException("Doador não encontrado");
        }
        Doacao doacao = _modelMapper.map(doacaoDTO, Doacao.class);
        return _modelMapper.map(_doacaoRepository.save(doacao), DoacaoDTO.class);
    }

    @Override
    public List<DoacaoDTO> listarTodasAsDoacao() {
        List<Doacao> doacoes = _doacaoRepository.findAll();
        return doacoes.stream().map(
                doacao -> _modelMapper.map(doacao, DoacaoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoacaoDTO buscarDoacaoPorId(Integer doacaoId) {
        Optional<Doacao> doacao = _doacaoRepository.findById(doacaoId);
        if (doacao.isEmpty()){
            throw new DoacaoNaoEncontradoException(
                    new ExceptionResponse(ErrorCodes.DOACAO_NAO_ENCONTRADA, ErrorConstants.DOACAO_NAO_ENCONTRADO));
        }
        return _modelMapper.map(doacao, DoacaoDTO.class);
    }
}
