package br.com.zup.edu.pizzaria.pedidos.novopedido;

import br.com.zup.edu.pizzaria.pedidos.Endereco;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class EnderecoRequest {
    @JsonProperty
    private String rua;

    @JsonProperty
    private String numero;

    @JsonProperty
    private String complemento;

    @JsonProperty
    private String cep;

    /**
     * @deprecated para uso dos frameworks
     */
    @Deprecated
    public EnderecoRequest() { }

    @JsonCreator(mode = PROPERTIES)
    public EnderecoRequest(String rua,
                           String numero,
                           String complemento,
                           String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco paraEndereco() {

        return new Endereco(rua, numero, complemento, cep);
    }
}
