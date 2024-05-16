package com.gabriel.ferreira.souto.msdoacao.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.domain.interfaces.IDoacaoRepository;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doacao.Doacao;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doador.DoadorResponse;
import com.gabriel.ferreira.souto.msdoacao.domain.model.estoque.Estoque;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.*;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants.ErrorConstants;
import com.gabriel.ferreira.souto.msdoacao.infra.feignClient.IDoadorControllerClient;
import com.gabriel.ferreira.souto.msdoacao.infra.rabbitMQQueue.EmitirEstoqueDeSanguePublisher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoacaoService implements IDoacaoService {
    private final ModelMapper _modelMapper;
    private final IDoacaoRepository _doacaoRepository;
    private final IDoadorControllerClient _doadorControllerClient;
    private final EmitirEstoqueDeSanguePublisher _emitirEstoqueDeSanguePublisher;

    public DoacaoService(ModelMapper modelMapper, IDoacaoRepository doacaoRepository, IDoadorControllerClient doadorControllerClient, EmitirEstoqueDeSanguePublisher emitirEstoqueDeSanguePublisher) {
        _modelMapper = modelMapper;
        _doacaoRepository = doacaoRepository;
        _doadorControllerClient = doadorControllerClient;
        _emitirEstoqueDeSanguePublisher = emitirEstoqueDeSanguePublisher;
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
        if (idade < 18) {
            throw new IdadeInvalidaException(new ExceptionResponse(ErrorCodes.IDADE_INVALIDA, ErrorConstants.IDADE_INVALIDA));
        }
    }

    @Override
    public DoacaoDTO criarDoacao(DoacaoDTO doacaoDTO) throws JsonProcessingException {
        DoadorResponse doadorResponse = _doadorControllerClient.buscarDoadorPorCpf(doacaoDTO.getCpf());
        if (doacaoDTO.getSangueML() < 420 || doacaoDTO.getSangueML() > 470) {
            throw new QuantidadeDeSangueInvalidaException(
                    new ExceptionResponse(ErrorCodes.QUANTIDADE_DE_SANGUE_INVALIDA, ErrorConstants.QUANTIDADE_DE_SANGUE_INVALIDA)
            );
        }
        if (doadorResponse.getPeso() < 50) {
            throw new PesoInvalidoException(new ExceptionResponse(ErrorCodes.PESO_INVALIDO, ErrorConstants.PESO_INVALIDO));
        }
        validarIdade(doadorResponse.getAniversario());
        verificarUltimaDoacao(doadorResponse.getGenero(), doadorResponse.getCpf());


        Doacao doacao = _modelMapper.map(doacaoDTO, Doacao.class);
        doacao.setDiaDaDoacao(new Date());
        Estoque estoque = new Estoque(doadorResponse.getTipoSanguineo(), doacao.getSangueML());
        _emitirEstoqueDeSanguePublisher.emitirEstoqueDeSangue(estoque);
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

    public void verificarUltimaDoacao(String genero, String cpf) {
        Doacao doacao = _doacaoRepository.findFirstByCpfOrderByIdDesc(cpf);
        if (doacao == null) {
            return;
        }
        Date hoje = new Date();
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
