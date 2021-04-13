package demo.reactor.test;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;

public class ReactorTest {

    @Test
    public void test() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5, 6);
        StepVerifier.create(just).expectNext(1, 2, 3, 4, 5, 6).expectComplete().verify();

        Mono<Object> errorDemo = Mono.error(new Exception("error"));
        StepVerifier.create(errorDemo).expectErrorMessage("error").verify();

        Flux<Integer> mapDemo = Flux.range(1, 6).map(i -> i * i);
        StepVerifier.create(mapDemo).expectNext(1, 4, 9, 16, 25, 36).expectComplete().verify();

        Flux<String> flatMapDemo = Flux.just("flex", "mono").flatMap(s -> Flux.fromArray(s.split("\\s*"))).delayElements(Duration.ofMillis(100)).doOnNext(System.out::print);
        StepVerifier.create(flatMapDemo).expectNextCount(8).expectComplete().verify();

        Flux<Integer> filterDemo = Flux.range(1, 6).filter(i -> i % 2 == 1).map(i -> i * i);
        StepVerifier.create(filterDemo).expectNext(1, 9, 25).verifyComplete();

    }

    @Test
    public void testZip() throws InterruptedException {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        Flux<String> stringFlux = Flux.fromArray(desc.split("\\s+"));
        CountDownLatch countDownLatch = new CountDownLatch(1);
//        Flux.zip(stringFlux, Flux.interval(Duration.ofMillis(100)))
        stringFlux.zipWith(Flux.interval(Duration.ofMillis(500)))
                .subscribe(t -> System.out.println(t.getT1()), Throwable::printStackTrace, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void syncTask() throws InterruptedException {
        Supplier<String> strService = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Reactor!";
        };
        CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println(Thread.currentThread().getName());
        Mono.fromCallable(strService::get)    // 1
                .subscribeOn(Schedulers.boundedElastic())  // 2
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void errorHandler() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3)) // 1
                .onErrorReturn(-1)
                .map(i -> i * i)
                .subscribe(System.out::println, System.err::println, () -> System.out.println("complete"));
    }

    @Test
    public void doFinally() {
        LongAdder statsCancel = new LongAdder();    // 1
        Flux<String> flux =
                Flux.just("foo", "bar")
                        .doFinally(type -> {
                            System.out.println(type);
                            if (type == SignalType.CANCEL) {
                                statsCancel.increment();
                            } else if (type == SignalType.ON_NEXT) {
                                statsCancel.increment();
                            } else if (type == SignalType.ON_COMPLETE) {
                                statsCancel.add(4);
                            }
                        })
                        .take(2);   // 4
        flux.subscribe(System.out::println);
        System.out.println(statsCancel.longValue());
    }

    @Test
    public void retry() throws InterruptedException {
        Flux.range(1, 6)
                .map(i -> 10 / (3 - i))
                .retry(1)
//                .doFinally(System.out::println)
                .subscribe(System.out::println, System.err::println);
        Thread.sleep(100);
    }
    @Test
    public void testBackpressure() {
        Flux.range(1, 6)    // 1
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))    // 2
                .subscribe(new BaseSubscriber<Integer>() {  // 3
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) { // 4
                        System.out.println("Subscribed and make a request...");
                        request(1); // 5
                    }

                    @Override
                    protected void hookOnNext(Integer value) {  // 6
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 7
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");    // 8
                        request(1); // 9
                    }
                });
    }
    @Test
    public void a() {
        Map<Integer, Set<String>> demo = new HashMap<>();
        Set<String> orDefault = demo.getOrDefault(1, new HashSet<>());
        orDefault.add("a");
        demo.putIfAbsent(1, orDefault);
        System.out.println(demo);
    }
}
