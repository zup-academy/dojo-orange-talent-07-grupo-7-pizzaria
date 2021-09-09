package br.com.zup.edu.pizzaria.pedidos.novopedido;

import br.com.zup.edu.pizzaria.pedidos.Item;
import br.com.zup.edu.pizzaria.pedidos.Pedido;
import br.com.zup.edu.pizzaria.pedidos.TipoDeBorda;
import br.com.zup.edu.pizzaria.pizzas.Pizza;
import br.com.zup.edu.pizzaria.pizzas.PizzaRepository;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ItemRequest {

    @NotNull
    private Long pizzaId;

    @NotNull
    private TipoDeBorda borda;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ItemRequest(Long pizzaId, TipoDeBorda borda) {
        this.pizzaId = pizzaId;
        this.borda = borda;
    }

    public Item paraItem(Pedido pedido, PizzaRepository repository) {

        Optional<Pizza> possivelPizza = repository.findById(pizzaId);

        return new Item(pedido, borda, possivelPizza.get());
    }
}
