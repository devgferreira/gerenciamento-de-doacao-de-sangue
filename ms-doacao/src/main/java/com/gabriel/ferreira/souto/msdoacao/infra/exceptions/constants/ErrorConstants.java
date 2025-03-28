package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String DOACAO_NAO_ENCONTRADO = "Ms-Doacao - Doação não encontrado.";
    public static final String IDADE_INVALIDA = "Ms-Doacao - Idade inválida, idade miníma para doação é de 18 anos.";
    public static final String PESO_INVALIDO = "Ms-Doacao - Peso inválido, minímo de 50kg para realizar a doação.";
    public static final String NAO_E_POSIVEL_DOAR_M = "Ms-Doacao - A Doação tem um tempo de espera de 90 em 90 dias para homens.";
    public static final String QUANTIDADE_DE_SANGUE_INVALIDA = "Ms-Doacao - Quantidade de mililitros de sangue doados deve ser entre 420ml e 470ml.";
    public static final String NAO_E_POSSIVEL_DOAR_F = "Ms-Doacao - A Doação tem um tempo de espera de 60 em 60 dias para mulheres.";
}
