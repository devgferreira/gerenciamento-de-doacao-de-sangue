package com.gabriel.ferreira.souto.msdoacao.application.service;

import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.domain.interfaces.IDoacaoRepository;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doacao.Doacao;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doador.DoadorResponse;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.*;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants.ErrorConstants;
import com.gabriel.ferreira.souto.msdoacao.infra.feignClient.DoadorControllerClient;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
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
        DoadorResponse doadorResponse = _doadorControllerClient.buscarDoadorComId(doacaoDTO.getDoadorId());
        if(doadorResponse.getPeso() < 50){
            throw new PesoInvalidoException(new ExceptionResponse(ErrorCodes.PESO_INVALIDO, ErrorConstants.PESO_INVALIDO));
        }
        validarIdade(doadorResponse.getAniversario());
        verificarUltimaDoacao(doadorResponse.getGenero(), doadorResponse.getId());

        Doacao doacao = _modelMapper.map(doacaoDTO, Doacao.class);
        doacao.setDiaDaDoacao(new Date());
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

        Doacao doacao = _doacaoRepository.findById(doacaoId).orElseThrow(
                () ->
                        new DoacaoNaoEncontradoException(
                                new ExceptionResponse(ErrorCodes.DOACAO_NAO_ENCONTRADA,
                                        ErrorConstants.DOACAO_NAO_ENCONTRADO))
        );

        return _modelMapper.map(doacao, DoacaoDTO.class);
    }

    public static void validarIdade(Date dataNasc) {
        Calendar nasc = Calendar.getInstance();
        nasc.setTime(dataNasc);
        Calendar atual = Calendar.getInstance();

        int idade = atual.get(Calendar.YEAR) - nasc.get(Calendar.YEAR);

        if (atual.get(Calendar.MONTH) < nasc.get(Calendar.MONTH)) {
            idade--;
        } else if (atual.get(Calendar.MONTH) == nasc.get(Calendar.MONTH)
                && atual.get(Calendar.DAY_OF_MONTH) < nasc.get(Calendar.DAY_OF_MONTH)) {
            idade--;
        }
        if(idade < 18){
            throw new IdadeInvalidaException(new ExceptionResponse(ErrorCodes.IDADE_INVALIDA, ErrorConstants.IDADE_INVALIDA));
        }
    }
    public void verificarUltimaDoacao(String genero, Integer doadorId) {
        Date hoje = new Date();
        Doacao doacao = _doacaoRepository.findFirstByDoadorIdOrderByIdDesc(doadorId);
        if(doacao == null){
           return;
        }
        long diferenca = hoje.getTime() - doacao.getDiaDaDoacao().getTime();
        long ultimaDoacao = diferenca / (1000 * 60 * 60 * 24);
        boolean verificarUltimaDoacaoM = ultimaDoacao < 90 && genero.equals("M");
        boolean verificarUltimaDoacaoF = ultimaDoacao < 60 && genero.equals("F");
        if (verificarUltimaDoacaoM) {
            throw new NaoEPossivelDoarMException(
                    new ExceptionResponse(ErrorCodes.NAO_E_POSSIVEL_DOAR_M,
                    ErrorConstants.NAO_E_POSIVEL_DOAR_M));
        }
        if (verificarUltimaDoacaoF) {
            throw new NaoEPossivelDoarFException(
                    new ExceptionResponse(ErrorCodes.NAO_E_POSSIVEL_DOAR_F,
                            ErrorConstants.NAO_E_POSSIVEL_DOAR_F));
        }
    }
}
