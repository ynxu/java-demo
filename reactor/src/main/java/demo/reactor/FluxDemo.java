package demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FluxDemo {
    public static void main(String[] args) {
        Flux<String> map = Flux.just(1, 2, 3, 4, 5, 6).map(i -> "hello " + i);
        map.subscribe(System.out::println,System.err::println,()-> System.out.println("completed!"));
        Mono<String> just = Mono.just(1).map(i -> "hello" + i);
        Integer[] intArray = {1, 2, 3, 4, 5};
        Flux.fromArray(intArray);
        List<Integer> list = Arrays.asList(intArray);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
        Flux.just();
        Flux.empty();
        Mono.empty();
        Mono.justOrEmpty(Optional.empty());
        Flux.error(new Exception("some error"));
        Mono.error(new Exception("some error"));
    }


}
