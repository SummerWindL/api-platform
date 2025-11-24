package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.api.bean.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

import static junit.framework.TestCase.*;

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<APIResponse> resultFuture = new CompletableFuture<>();
        resultFuture.complete(new CompletableFutureTest().doSomething());
        log.info("resultFuture result: {}",resultFuture.get());
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> log.info("hello"));
        log .info("future result: {}",future.get());
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello!");
        assertEquals("hello!", future2.get());

        log.info("resultFuture result: {}",resultFuture.isDone());

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!");
        assertEquals("hello!world!", future1.get());
        // 这次调用将被忽略。
        future.thenApply(s -> s + "nice!");
        assertEquals("hello!world!", future1.get());
        log.info("future1 result: {}",future1.get());
        CompletableFuture<String> future3 = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!");
        assertEquals("hello!world!nice!", future3.get());
        log.info("future3 result: {}",future3.get());

        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenAccept(System.out::println);//hello!world!nice!

        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenRun(() -> System.out.println("hello!"));//hello!

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "hello!")
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // 这里没有抛出异常所有为 null
                    assertNull(ex);
                });
        assertEquals("hello!", future4.get());
        CompletableFuture<String> future5
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : "world!";
        });
        assertEquals("world!", future5.get());

    }

    /**
     * 创建CompletableFuture对象
     */
    @Test
    public void createFunctionTypeOne() throws ExecutionException, InterruptedException {
        CompletableFuture<APIResponse> resultFuture = new CompletableFuture<>();
        resultFuture.complete(doSomething());
        log.info("resultFuture result: {}",resultFuture.get());
    }

    /**
     * 创建CompletableFuture对象 知道返回值 静态方法调用
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void createFunctionTypeTwo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("hello!");
        assertEquals("hello!",stringCompletableFuture.get());
    }

    /**
     * 静态方法runAsync 无返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> hello = CompletableFuture.runAsync(() -> log.info("hello"));
        log.info("hello result: {}",hello.get());
    }

    /**
     * 静态方法supplyAsync 有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello!");
        assertEquals("hello!", future.get());
    }

    /**
     * 处理异步结算的结果
     * thenApply()
     * thenAccept()  不需要知道返回结果 异步
     * thenRun() 不需要知道返回结果 不能访问异步计算结果
     * whenComplete()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void processAsyncResult() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello!").thenApply(s -> s + "world!");
        future.thenApply(s -> s + "nice!"); // 本次调用被忽略
        assertEquals("hello!world!", future.get());
        //链式调用
        CompletableFuture<String> futureLink = CompletableFuture.supplyAsync(() -> "hello!")
                .thenApply(s -> s + "world!")
                .thenApply(s -> s + "nice!");
        assertEquals("hello!world!nice!", futureLink.get());

        // 可以访问异步计算结果
        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenAccept(System.out::println);//hello!world!nice!
        // 无法访问异步计算结果
        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenRun(() -> System.out.println("hello!"));//hello!

        CompletableFuture<APIResponse> futureWhenComplete = CompletableFuture.supplyAsync(this::doSomething)
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // 这里没有抛出异常所有为 null
                    assertNull(ex);
                });
        log.info("futureWhenComplete result: {}",JSONObject.toJSONString(futureWhenComplete.get()));
        assertEquals(JSONObject.toJSONString(new APIResponse("业务处理成功")), JSONObject.toJSONString(futureWhenComplete.get()));
    }

    /**
     * 异常处理 handle
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void handleAsyncExceptionTypeOne() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : "world!";
        });
        assertEquals("world!", future.get());
    }

    /**
     * 异常处理 exceptionally
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void handleAsyncExceptionTypeTwo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).exceptionally(ex -> {
            System.out.println(ex.toString());// CompletionException
            return "world!";
        });
        assertEquals("world!", future.get());

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
// ...
        completableFuture.completeExceptionally(
                new RuntimeException("Calculation failed!"));
// ...
        completableFuture.get(); // ExecutionException
    }

    /**
     * 组合completableFuture，将前一个任务返回的结果当作下一个任务的入参
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void compose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        assertEquals("hello!world!", future.get());
    }

    /**
     * 组合多个CompletableFuture
     * thenCompose() 可以链接两个 CompletableFuture 对象，并将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
     * thenCombine() 会在两个任务都执行完成后，把两个任务的结果合并。两个任务是并行执行的，它们之间并没有先后依赖顺序。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void combine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> "world!"), (s1, s2) -> s1 + s2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "nice!"));
        assertEquals("hello!world!nice!", completableFuture.get());
    }

    /**
     * 任务组合操作acceptEitherAsync()会在异步任务 1 和异步任务 2 中的任意一个完成时触发执行任务 3，
     * 但是需要注意，这个触发时机是不确定的。如果任务 1 和任务 2 都还未完成，那么任务 3 就不能被执行。
     */
    @Test
    public void acceptEither(){
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1执行完毕，当前时间：" + System.currentTimeMillis());
            return "task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2执行完毕，当前时间：" + System.currentTimeMillis());
            return "task2";
        });

        task.acceptEitherAsync(task2, (res) -> {
            System.out.println("任务3开始执行，当前时间：" + System.currentTimeMillis());
            System.out.println("上一个任务的结果为：" + res);
        });
        // 等待任务执行完成
        task.join();
     // 增加一些延迟时间，确保异步任务有足够的时间完成
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * allOf() 方法会等到所有的 CompletableFuture 都运行完成之后再返回
     * anyOf() 方法会等到任意一个 CompletableFuture 运行完成之后再返回
     */
    @Test
    public void allOfAndanyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> task1 =
                CompletableFuture.runAsync(()->{

                    log.info("task1 start {}",Thread.currentThread().getName());
                });
        CompletableFuture<Void> task2 =
                CompletableFuture.runAsync(()->{
                    //自定义业务操作
                    log .info("task2 start");
                });
        CompletableFuture<String> task3 =
                CompletableFuture.supplyAsync(()->{
                    //自定义业务操作
                    log.info("task3 start");
                    return "task3";
                });
        CompletableFuture<Void> task4 =
                CompletableFuture.runAsync(()->{
                    //自定义业务操作
                    log.info("task4 start");
                });
        CompletableFuture<Void> task5 =
                CompletableFuture.runAsync(()->{
                    //自定义业务操作
                    log.info("task5 start");
                });
        CompletableFuture<Void> task6 =
                CompletableFuture.runAsync(()->{
                    //自定义业务操作
                    log.info("task6 start");
                });
        CompletableFuture<Void> headerFuture=CompletableFuture.allOf(task1,task2,task3,task4,task5,task6);

        try {
            headerFuture.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("all done. ");

        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future1 done...");
            }
            return "abc";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future2 done...");
            }
            return "efg";
        });
        // allOf() 1、2结束后返回
//        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(future1, future2);
//        completableFuture.join();
//        assertTrue(completableFuture.isDone());
//        System.out.println("all futures done...");

        CompletableFuture<Object> f = CompletableFuture.anyOf(future1, future2);
        System.out.println(f.get());

    }

    /**
     * 使用自定义线程池
     * 我们上面的代码示例中，为了方便，都没有选择自定义线程池。实际项目中，这是不可取的。
     * CompletableFuture 默认使用全局共享的 ForkJoinPool.commonPool() 作为执行器，
     * 所有未指定执行器的异步任务都会使用该线程池。这意味着应用程序、多个库或框架（如 Spring、第三方库）若都依赖 CompletableFuture，
     * 默认情况下它们都会共享同一个线程池。虽然 ForkJoinPool 效率很高，但当同时提交大量任务时，
     * 可能会导致资源竞争和线程饥饿，进而影响系统性能。为避免这些问题，
     * 建议为 CompletableFuture 提供自定义线程池，带来以下优势：
     * 隔离性：为不同任务分配独立的线程池，避免全局线程池资源争夺。
     * 资源控制：根据任务特性调整线程池大小和队列类型，优化性能表现。
     * 异常处理：通过自定义 ThreadFactory 更好地处理线程中的异常情况。
     * @return
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    @Test
    public void usageCompletableFuture() {
        // 为不同任务使用自定义线程池
        CompletableFuture.runAsync(() -> {
            //...
        }, executor);
        // 尽量避免使用get方法，因为会阻塞当前线程，如果要用最好设置超时时间
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, world!";
        });

        // 获取异步任务的返回值，设置超时时间为 5 秒
        try {
            String result = future.get(5, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 使用 whenComplete 方法可以在任务完成时触发回调函数，并正确地处理异常，而不是让异常被吞噬或丢失。
     * 使用 exceptionally 方法可以处理异常并重新抛出，以便异常能够传播到后续阶段，而不是让异常被忽略或终止。
     * 使用 handle 方法可以处理正常的返回结果和异常，并返回一个新的结果，而不是让异常影响正常的业务逻辑。
     * 使用 CompletableFuture.allOf 方法可以组合多个 CompletableFuture，并统一处理所有任务的异常，而不是让异常处理过于冗长或重复。
     */


    public APIResponse doSomething(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            log.info("开始执行");
        });
        CompletableFuture<Void> threadFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                log.info("耗时处理");
                threadFuture.complete(null); // 标记任务完成
            } catch (InterruptedException e) {
                threadFuture.completeExceptionally(e);
                throw new RuntimeException(e);
            }
        }).start();
        try {
            threadFuture.get(); // 等待线程执行完成
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        return new APIResponse("业务处理成功");
    }


}
